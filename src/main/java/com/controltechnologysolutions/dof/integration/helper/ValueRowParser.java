package com.controltechnologysolutions.dof.integration.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opendof.core.oal.DOFValue;
import org.opendof.datatransfer.ValueSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controltechnologysolutions.dof.integration.dto.DeviceValue;

public class ValueRowParser {
	private static final Logger LOG = LoggerFactory.getLogger(ValueRowParser.class);

	public static List<DeviceValue> parseRow(String deviceID, ValueSet.Row row, ValueSet.Definition.Property[] properties){
		List<DeviceValue> devValues = new ArrayList<DeviceValue>();
		DOFValue[] values = row.getValues();
		if(values == null || values.length == 0){
			return devValues;
		}
		Date timestamp = row.getTimestamp();
		for(int i = 0; i < values.length; i++){
			DeviceValue devValue = parseValue(deviceID, timestamp, values[i], properties[i]);
			if(devValue != null){
				devValues.add(devValue);
			}
		}
		return devValues;
	}
	
	private static DeviceValue parseValue(String deviceID, Date timestamp, DOFValue value, ValueSet.Definition.Property property){
		DeviceValue devValue = new DeviceValue();
		String interfaceID = property.getInterfaceID().toStandardString();
		try {
			ConverterHelper.convertValue(value, devValue);
		} catch (Exception e) {
			LOG.error("Unable to parse value interface ({}) item ({}) value ({}).", interfaceID, property.getItemID(), value);
			LOG.error("With exception:", e);
			return null;
		}
		devValue.setDeviceID(deviceID);
		devValue.setTimestamp(timestamp.getTime());
		devValue.setInterfaceID(interfaceID);
		devValue.setItemID(property.getItemID());
		return devValue;
	}
}
