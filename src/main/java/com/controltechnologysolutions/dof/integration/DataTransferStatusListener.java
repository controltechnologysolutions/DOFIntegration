package com.controltechnologysolutions.dof.integration;

import java.util.Date;

import org.opendof.datatransfer.StatusLevel;
import org.opendof.datatransfer.StatusListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTransferStatusListener implements StatusListener{
	private static final Logger LOG = LoggerFactory.getLogger(DataTransferStatusListener.class);

	public void statusChanged(StatusLevel severity, Date timestamp, String message, Exception ex) {
		if (severity.getValue() >= StatusLevel.ERROR.getValue()) {
			if(ex != null){
				LOG.error(message, ex);
			}
			else{
				LOG.error(message);				
			}
		} else if (severity.equals(StatusLevel.WARN)) {
			if(ex != null){
				LOG.warn(message, ex);
			}
			else{
				LOG.warn(message);				
			}
		} else if (severity.equals(StatusLevel.OK)) {
			LOG.info(message);
		}		
	}
}
