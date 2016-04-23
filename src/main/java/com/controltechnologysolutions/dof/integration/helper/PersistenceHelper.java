package com.controltechnologysolutions.dof.integration.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controltechnologysolutions.dof.integration.dto.Device;
import com.controltechnologysolutions.dof.integration.dto.DeviceValue;
import com.controltechnologysolutions.dof.integration.dto.MessageDeliverCache;

public class PersistenceHelper {
	private static final Logger LOG = LoggerFactory.getLogger(PersistenceHelper.class);

	private static final String DEVICE_QUERY = "INSERT INTO PESCO_DEVICE "
			+ "(deviceid, name, parentdeviceid, quantum, position) VALUES (%s, %s, %s, %d, %d)";
	private static final String DEVICE_COUNT_QUERY = "SELECT COUNT(deviceid) FROM PESCO_DEVICE WHERE deviceid = '%s'";

	private static final String CACHE_QUERY = "INSERT INTO PESCO_MESSAGE_CACHE "
			+ "(deviceid, start, end) VALUES (%s, %d, %d)";
	private static final String CACHE_COUNT_QUERY = "SELECT COUNT(deviceid) FROM PESCO_MESSAGE_CACHE "
			+ "WHERE deviceid = '%s' AND start = %d AND end = %d";

	private static final String VALUE_QUERY = "INSERT INTO PESCO_DEVICE_VALUE "
			+ "(deviceid, timestamp, interfaceid, itemid, valuelong, valuefloat1, valuefloat2, valuefloat3) "
			+ "VALUES (%s, %d, %s, %d, %d, %s, %s, %s)";

	/**
	 * @param cache
	 * @return true if the message was already delivered.
	 * @throws SQLException
	 */
	public static boolean verifyMessageDelivered(MessageDeliverCache cache, Connection conn) throws SQLException {
		Date start = new Date(cache.getStart());
		Date end = new Date(cache.getEnd());
		if (isMessageDelivered(cache, conn)) {
			LOG.warn("Message from {} from {} to {} already processed.", cache.getDeviceID(), start, end);
			return true;
		}
		LOG.info("Message from {} from {} to {}.", cache.getDeviceID(), start, end);
		return false;
	}

	/**
	 * Saves device (if needed) and the values to the db
	 * 
	 * @param device
	 * @param cache
	 * @param values
	 * @throws SQLException
	 */
	public static void save(Device device, MessageDeliverCache cache, List<DeviceValue> values, Connection conn)
			throws SQLException {
		conn.setAutoCommit(false);
		if (!isDeviceSaved(device, conn)) {
			LOG.debug("Saving device {}...", device.getDeviceID());
			String query = saveDeviceQuery(device);
			update(query, conn);
		}

		LOG.debug("Saving message cache...", device.getDeviceID());
		String query = saveCacheQuery(cache);
		update(query, conn);

		List<String> queries = new ArrayList<String>();
		for (DeviceValue value : values) {
			queries.add(saveValueQuery(value));
		}
		LOG.debug("Saving values....", device.getDeviceID());
		batchUpdate(queries, conn);

		LOG.debug("Commit start.");
		conn.commit();
		LOG.debug("Commit end.");
	}

	private static boolean isDeviceSaved(Device device, Connection conn) throws SQLException {
		String deviceID = device.getDeviceID();
		Statement stmt = conn.createStatement();
		String query = String.format(DEVICE_COUNT_QUERY, deviceID);
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		int count = rs.getInt(1);
		rs.close();
		stmt.close();
		return count > 0;
	}

	private static String saveDeviceQuery(Device device) {
		String query = String.format(DEVICE_QUERY, //
				stringParam(device.getDeviceID()), //
				stringParam(device.getName()), //
				stringParam(device.getParentDeviceID()), //
				device.getQuantum(), //
				device.getPosition());
		return query;
	}

	private static String saveValueQuery(DeviceValue deviceValue) {
		String query = String.format(VALUE_QUERY, //
				stringParam(deviceValue.getDeviceID()), //
				deviceValue.getTimestamp(), //
				stringParam(deviceValue.getInterfaceID()), //
				deviceValue.getItemID(), //
				deviceValue.getValueLong(), //
				deviceValue.getValueFloat1(), //
				deviceValue.getValueFloat2(), //
				deviceValue.getValueFloat3());
		return query;
	}

	private static boolean isMessageDelivered(MessageDeliverCache deliverCache, Connection conn) throws SQLException {
		String deviceID = deliverCache.getDeviceID();
		Long start = deliverCache.getStart();
		Long end = deliverCache.getEnd();

		Statement stmt = conn.createStatement();
		String query = String.format(CACHE_COUNT_QUERY, deviceID, start, end);
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		int count = rs.getInt(1);
		rs.close();
		stmt.close();
		return count > 0;
	}

	private static String saveCacheQuery(MessageDeliverCache deliverCache) {
		String query = String.format(CACHE_QUERY, //
				stringParam(deliverCache.getDeviceID()), //
				deliverCache.getStart(), //
				deliverCache.getEnd());
		return query;
	}

	private static void update(String query, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	private static void batchUpdate(List<String> queries, Connection conn) throws SQLException {
		if (CollectionUtils.isEmpty(queries)) {
			return;
		}
		Statement stmt = conn.createStatement();
		for (String query : queries) {
			stmt.addBatch(query);
		}
		stmt.executeBatch();
		stmt.close();
	}

	private static String stringParam(String value) {
		if (value == null) {
			return null;
		}
		return "'" + value + "'";
	}
}
