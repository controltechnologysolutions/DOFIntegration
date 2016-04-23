package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFUInt16;

/**
 * <b> Hygrometer </b><br />
 * <br />
 * <br />
 * This interface provides tha value of the humidity. <br />
 * <br />
 * 
 * IID: [1:{0211}] <br />
 * 
 * <b> Properties </b><br />
 * Humidity <br />
 * This property shows the value of the humidity. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class Hygrometer {

	/**
	 * <b> Permil </b><br />
	 * <br />
	 * Density. The unit is permil, Part Per Thousand. The range is from 0 to
	 * 1000. <br />
	 */
	public static final DOFType Permil = DOFUInt16.TYPE;

	/**
	 * Hygrometer InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{0211}]");

	/**
	 * This property shows the value of the humidity.
	 */
	public static final int PROPERTY_Humidity_ID = 1;

	/**
	 * Hygrometer Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_Humidity_ID, false, true, Permil).build();
}
