package com.controltechnologysolutions.dof.integration;

import java.io.File;
import java.net.URL;

import org.opendof.core.ReconnectingStateListener;
import org.opendof.core.oal.DOF;
import org.opendof.core.oal.DOF.SecurityDesire;
import org.opendof.core.oal.DOFAddress;
import org.opendof.core.oal.DOFConnection;
import org.opendof.core.oal.DOFCredentials;
import org.opendof.core.oal.DOFException;
import org.opendof.core.oal.DOFObjectID;
import org.opendof.core.oal.DOFSystem;
import org.opendof.core.oal.security.DOFAuthenticationFailedException;
import org.opendof.core.oal.security.DOFSecurityException;
import org.opendof.datatransfer.sink.Sink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DOFAbstraction {
	private static final Logger LOG = LoggerFactory.getLogger(DOFAbstraction.class);
	
	public static final ReconnectingStateListener RECONECT_LISTENER = new ReconnectingStateListener();
	
	/**
	 * @return
	 */
	public static DOF createDOF() {
		LOG.info("Creating DOF...");
		DOF.Config myDofConfig = new DOF.Config.Builder().build();
		LOG.info("DOF CReated");
		return new DOF(myDofConfig);
	}

	/**
	 * @param path The credential file path.
	 * @return
	 */
	public static DOFCredentials createCredential(String path) {
		LOG.info("Creating credential for file {}...", path);
		try {
			URL credentialUrl = DOFAbstraction.class.getResource(path);
			File credentialFile = new File(credentialUrl.toURI());
			LOG.info("Credential Created");
			return DOFCredentials.create(credentialFile);
		} catch (Throwable t) {
			LOG.error("Fail to create the credential.", t);
			return null;
		}
	}

	/**
	 * @param dof
	 * @param address
	 * @param credential
	 * @param maxSilence The value that represents the maximum allowed silence on a connection.
	 * @param connectionTimeout
	 * @return
	 */
	public static DOFConnection createConnection(DOF dof, DOFAddress address, DOFCredentials credential, int maxSilence, int connectionTimeout) {
		LOG.info("Creating the connection to {}...", address);
		
		DOFConnection.Config myConnectionConfig = new DOFConnection.Config//
				.BuilderSecureStream(address, credential)//
						.setSecurityDesire(SecurityDesire.SECURE)//
						.setMaxReceiveSilence(maxSilence)//
						.build();

		DOFConnection connection = dof.createConnection(myConnectionConfig);
		LOG.info("Connection Created");

		try {
			LOG.info("Trying to connect with timeout of {}ms...", connectionTimeout);
			connection.connect(connectionTimeout);
			LOG.info("Connected");
		} catch (DOFException e) {
			LOG.error("Unable to connect", e);
			return null;
		}
		connection.addStateListener(RECONECT_LISTENER);
		return connection;
	}
	
	/**
	 * @param dof
	 * @param credential
	 * @param creationTimeout
	 * @return
	 */
	public static DOFSystem createDOFSystem(DOF dof, DOFCredentials credential, int creationTimeout) {
		LOG.info("Creating DOF system with timeout of {}ms...", creationTimeout);
		DOFSystem.Config systemConfig = new DOFSystem.Config.BuilderSecure(credential).setName("Test System").build();

		DOFSystem dofSystem = null;		
		try {
			dofSystem = dof.createSystem(systemConfig, creationTimeout);
		} catch (DOFAuthenticationFailedException afx) {
			LOG.error("Credentials failed. Please check your credentials.", afx);
			return dofSystem;
		} catch (DOFSecurityException ex) {
			LOG.error("Security exception.", ex);
			return dofSystem;
		} catch (DOFException ex) {
			LOG.error("DOFException.", ex);
			return dofSystem;
		}

		LOG.info("DOF System created.");
		return dofSystem;		
	}
	
	/**
	 * @param sinkId
	 * @param dofSystem
	 * @param operationTimeout
	 * @return
	 */
	public static Sink createSink(String sinkId, DOFSystem dofSystem, int operationTimeout) {
		LOG.info("Creating sink for ID {} and operation timeout of {}ms...", sinkId, operationTimeout);
		
		Sink sink = null;
		try{
			DOFObjectID sinkID = DOFObjectID.create(sinkId);
			Sink.Config sinkConfig = new Sink.Config.Builder(dofSystem, sinkID, new DataTransferStatusListener())
					.setInstanceID(Sink.Config.Builder.generateInstanceID())
					.setOperationTimeout(operationTimeout)
					.setValueSetListener(new DOFIntegrationValueSetListener()).build();
			sink = Sink.create(sinkConfig);
			LOG.info("Sink created.");	
        } catch (Exception e) {
        	LOG.error("Unable to create sink", e);
        	return null;
        }
		
		return sink;
	}
}
