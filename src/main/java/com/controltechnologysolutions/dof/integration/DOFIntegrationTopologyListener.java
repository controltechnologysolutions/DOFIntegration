package com.controltechnologysolutions.dof.integration;

import org.opendof.core.oal.DOFObjectID;
import org.opendof.datatransfer.TopologyInformation;
import org.opendof.datatransfer.sink.Sink;
import org.opendof.datatransfer.sink.TopologyListener;

public class DOFIntegrationTopologyListener implements TopologyListener{

	public void persistTopology(Sink sink, DOFObjectID source, TopologyInformation topologyInformation) {
		// dummy
	}

}
