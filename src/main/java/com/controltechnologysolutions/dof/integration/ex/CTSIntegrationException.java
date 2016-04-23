package com.controltechnologysolutions.dof.integration.ex;

public class CTSIntegrationException extends RuntimeException{

	/***/
	private static final long serialVersionUID = 687491722690693682L;

	public CTSIntegrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CTSIntegrationException(String message) {
		super(message);
	}

	public CTSIntegrationException(Throwable cause) {
		super(cause);
	}
}
