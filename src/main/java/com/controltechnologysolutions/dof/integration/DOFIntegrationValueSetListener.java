package com.controltechnologysolutions.dof.integration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opendof.core.oal.DOFObjectID;
import org.opendof.datatransfer.DetailRequestStatus;
import org.opendof.datatransfer.ValueSet;
import org.opendof.datatransfer.ValueSet.Row;
import org.opendof.datatransfer.sink.Sink;
import org.opendof.datatransfer.sink.ValueSetListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controltechnologysolutions.dof.integration.dto.Device;
import com.controltechnologysolutions.dof.integration.dto.DeviceValue;
import com.controltechnologysolutions.dof.integration.dto.MessageDeliverCache;
import com.controltechnologysolutions.dof.integration.ex.CTSIntegrationException;
import com.controltechnologysolutions.dof.integration.helper.IntegrationDataSource;
import com.controltechnologysolutions.dof.integration.helper.PersistenceHelper;
import com.controltechnologysolutions.dof.integration.helper.ValueRowParser;

public class DOFIntegrationValueSetListener implements ValueSetListener {
	private static final Logger LOG = LoggerFactory.getLogger(DOFIntegrationValueSetListener.class);

	public void persistValueSet(Sink sink, DOFObjectID sourceID, ValueSet valueSet) throws Exception {
		Connection conn = null;
		try {
			String sourceIDString = sourceID.getDataString();
			LOG.info("Received value set from source ID: {}", sourceIDString);

			DOFObjectID deviceID = valueSet.getDeviceID();
			String deviceIDString = deviceID.toStandardString();

			MessageDeliverCache cache = new MessageDeliverCache();
			cache.setDeviceID(deviceIDString);
			cache.setStart(valueSet.getFirstTime().getTime());
			cache.setEnd(valueSet.getLastTime().getTime());

			conn = IntegrationDataSource.getConnection();

			// verify duplicate message
			if (PersistenceHelper.verifyMessageDelivered(cache, conn)) {
				return;
			}

			// parse device
			Device device = new Device();
			device.setDeviceID(deviceIDString);
			DOFObjectID parentID = valueSet.getParentID();
			if (parentID != null) {
				device.setParentDeviceID(parentID.toStandardString());
			}
			device.setQuantum(valueSet.getTimeQuantum());
			device.setPosition(valueSet.getPosition());

			// parse values
			ValueSet.Definition.Property[] properties = valueSet.getDefinition().getProperties();
			List<DeviceValue> deviceValues = new ArrayList<DeviceValue>();
			List<Row> rows = valueSet.getRows();
			LOG.info("{} row received.", rows.size());
			for (ValueSet.Row row : rows) {
				deviceValues.addAll(ValueRowParser.parseRow(deviceIDString, row, properties));
			}

			// persist data
			PersistenceHelper.save(device, cache, deviceValues, conn);
		} catch (Exception e) {
			String msg = "Failed to parse ValueSet on " + new Date() + ".";
			throw new CTSIntegrationException(msg, e);
		} finally {
			if(conn == null){
				return;
			}
			try {
				conn.close();
			} catch (Exception e2) {
				// nothing to do
			}
		}
	}

	public void requestComplete(Sink sink, long requestID, DetailRequestStatus status) throws Exception {
		// This is called when a data request sent from a sink has completed.
		LOG.info("Data Request completed for requestID: {} with status: {}", requestID, status.getValue());
	}

	public void valueSetsRemaining(Sink sink, DOFObjectID source, long remaining) {
		if (remaining > 25) {
			LOG.warn("{} messages remaining from source {}!", remaining, source);
		}
	}
}
