package com.controltechnologysolutions.dof.integration;

import java.util.Scanner;

import org.opendof.core.oal.DOF;
import org.opendof.core.oal.DOFAddress;
import org.opendof.core.oal.DOFConnection;
import org.opendof.core.oal.DOFCredentials;
import org.opendof.core.oal.DOFException;
import org.opendof.core.oal.DOFSystem;
import org.opendof.core.oal.DOFSystem.State;
import org.opendof.core.transport.inet.InetTransport;
import org.opendof.datatransfer.sink.Sink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DOFIntegrationMain {
	private static final Logger LOG = LoggerFactory.getLogger(DOFIntegrationMain.class);
	
	public static final String CREDENTIAL_PATH = "/sink-cts.cred";
	public static final DOFAddress ADDRESS = InetTransport.createAddress("dsp.emit-networking.org", 3567);
	public static final int MAX_SILENCE = 45 * 1000; // 45s
	public static final int CONNECTION_TIMEOUT = 60 * 1000; // 60s
	public static final int SYSTEM_CREATION_TIMEOUT = 60 * 1000; // 60s
	public static final String SINK_ID = "[129:4003/stage/sink-cts]";
	public static final int SINK_OPERATION_TIMEOUT = 60 * 1000; // 60s

	private static DOF dof;
	private static DOFSystem dofSystem;
	private static DOFCredentials credential;
	private static DOFConnection connection;
	private static Sink sink;

	public static void main(String[] args) {
		LOG.info("Initing DOF Integration...");

		dof = DOFAbstraction.createDOF();

		credential = DOFAbstraction.createCredential(CREDENTIAL_PATH);
		if (credential == null) {
			destroy();
			return;
		}

		connection = DOFAbstraction.createConnection(dof, ADDRESS, credential, MAX_SILENCE, CONNECTION_TIMEOUT);
		if (connection == null) {
			destroy();
			return;
		}

		dofSystem = DOFAbstraction.createDOFSystem(dof, credential, SYSTEM_CREATION_TIMEOUT);
		if (dofSystem == null) {
			destroy();
			return;
		}
		dofSystem.addStateListener(new DOFSystem.StateListener(){
			public void removed(DOFSystem system, DOFException ex) {
				
			}

			public void stateChanged(DOFSystem system, State state) {
				if(!state.isAuthorized()){
					LOG.error("System not authorized.");
					return;
				}
				
				LOG.info("System authorized.");
				if(sink != null){
					return;
				}
				
				sink = DOFAbstraction.createSink(SINK_ID, system, SINK_OPERATION_TIMEOUT);
			}
		});

		Scanner keyboard = new Scanner(System.in);
		keyboard.next();
		keyboard.close();

		destroy();
	}

	private static void destroy() {
		if(sink != null){
			sink.close();
			LOG.info("Sink Closed.");		
		}
		DOFAbstraction.RECONECT_LISTENER.cancel();
		LOG.info("Reconnection Canceled.");
		if (dofSystem != null) {
			dofSystem.destroy();
			LOG.info("DOF System destroyed.");
		}
		if (connection != null) {
			connection.destroy();
			LOG.info("Connection destroyed.");
		}
		dof.destroy();
		LOG.info("DOF destroyed.");
	}
}
