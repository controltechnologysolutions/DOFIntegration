package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFFloat32;

/**
 * <b> Thermometer </b><br />
 * <br />
 * <br />
 * This interface provides the value of the temperature. <br />
 * <br />
 * 
 * IID: [1:{0212}] <br />
 * 
 * <b> Properties </b><br />
 * Temperature <br />
 * This property shows the temperature. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class Thermometer {

	/**
	 * <b> Temperature </b><br />
	 * <br />
	 * The unit is degree(Celsius). <br />
	 */
	public static final DOFType Temperature = DOFFloat32.TYPE;

	/**
	 * Thermometer InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{0212}]");
	
	/**
	 * This property shows the temperature.
	 */
	public static final int PROPERTY_Temperature_ID = 1;

	/**
	 * Thermometer Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_Temperature_ID, false, true, Temperature).build();
}
