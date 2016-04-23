package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFFloat32;
import org.opendof.core.oal.value.DOFInt16;

/**
 * <b> Wind </b><br />
 * This interface is used to record wind. <br />
 * <br />
 * 
 * IID: [1:{023B}] <br />
 * 
 * <b> Properties </b><br />
 * Wind SpeedThe wind speed. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Wind DirectionThe wind direction. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class Wind {

	/**
	 * <b> Speed </b><br />
	 * A measure of speed. <br />
	 */
	public static final DOFType Speed = DOFFloat32.TYPE;

	/**
	 * <b> Direction </b><br />
	 * A measure of direction. <br />
	 */
	public static final DOFType Direction = DOFInt16.TYPE;

	/**
	 * Wind InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{023B}]");

	/**
	 * The wind speed.
	 */
	public static final int PROPERTY_WindSpeed_ID = 1;

	/**
	 * The wind direction.
	 */
	public static final int PROPERTY_WindDirection_ID = 2;

	/**
	 * Wind Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_WindSpeed_ID, false, true, Speed)
			.addProperty(PROPERTY_WindDirection_ID, false, true, Direction).build();
}