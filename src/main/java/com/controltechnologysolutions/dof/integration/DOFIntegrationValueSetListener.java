package com.controltechnologysolutions.dof.integration;

import java.util.Date;

import org.opendof.core.oal.DOFObjectID;
import org.opendof.core.oal.DOFValue;
import org.opendof.datatransfer.DetailRequestStatus;
import org.opendof.datatransfer.ValueSet;
import org.opendof.datatransfer.sink.Sink;
import org.opendof.datatransfer.sink.ValueSetListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DOFIntegrationValueSetListener implements ValueSetListener {
	private static final Logger LOG = LoggerFactory.getLogger(DOFIntegrationValueSetListener.class);

	public void persistValueSet(Sink sink, DOFObjectID sourceID, ValueSet valueSet) throws Exception {
		String sourceIDString = sourceID.getDataString();
		DOFObjectID deviceID = valueSet.getDeviceID();
		Date firstTime = valueSet.getFirstTime();
		Date lastTime = valueSet.getLastTime();
		DOFObjectID parentID = valueSet.getParentID();
		Integer topologyPosition = valueSet.getPosition();
		int quantum = valueSet.getTimeQuantum();
		LOG.info("Received value set from source ID: {}", sourceIDString);
		LOG.info("Value set is for device ID: {}", deviceID.toStandardString());
		if (parentID != null) { // It is possible that the parent is null
			LOG.info("Value set device has parent ID: {}", parentID.toStandardString());
		} else {
			LOG.info("Value set device has no parent");
		}

		if (topologyPosition != null) { // It is possible that the position is null
			LOG.info("Value set is in topology position: {}", topologyPosition);
		} else {
			LOG.info("Value set has no topology position");
		}

		LOG.info("Value set first time: {}", firstTime.toString());
		LOG.info("Value set last time: {}", lastTime.toString());
		LOG.info("Value set time quantum: {}", quantum);
		LOG.info("Value set row count: {}", valueSet.getRowCount());
        
        ValueSet.Definition.Property[] properties = valueSet.getDefinition().getProperties();
        
        for(int i = 0; i < properties.length; i++) {
        	ValueSet.Definition.Property prop = properties[i];
        	if(prop == null){
        		continue;
        	}
        	LOG.info("Prop type: {}", prop.getType());
        	LOG.info("Prop item id: {}", prop.getItemID());
        	LOG.info("Prop agreggation: {}", prop.getAggregation());
        	LOG.info("Prop interface id: {}", prop.getInterfaceID());
        }
        
        for(ValueSet.Row row : valueSet.getRows()){
        	LOG.info("   ValueSet Row timestamp: {}", row.getTimestamp());
        	//Parse the valueSet data
            DOFValue[] values = row.getValues(); 
            LOG.info("   ValueSet Row contains the following known data:");
            for(int i = 0; i < values.length; i++) {
            	DOFValue value = values[i];
	        	if(value == null){
	        		continue;
	        	}
	        	LOG.info("Value type: {}", value.getDOFType());
	        }
        }
	}

	public void requestComplete(Sink sink, long requestID, DetailRequestStatus status) throws Exception {
		// This is called when a data request sent from a sink has completed.
		LOG.info("Data Request completed for requestID: {} with status: {}", requestID, status.getValue());
	}

	public void valueSetsRemaining(Sink sink, DOFObjectID source, long remaining) {
	}
}
