package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFUInt16;

/**
 * <b> Pressure </b><br />
 * This interface represents the atmospheric pressure properties of the
 * BaseMeteorological model in the SunSpec Alliance Specification. <br />
 * <br />
 * 
 * IID: [1:{023A}] <br />
 * 
 * <b> Properties </b><br />
 * barPressureThe atmospheric (barometric) pressure. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class Pressure {

	/**
	 * <b> Pressure </b><br />
	 * A measurement of pressure. <br />
	 */
	public static final DOFType Pressure = DOFUInt16.TYPE;

	/**
	 * Pressure InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{023A}]");

	/**
	 * The atmospheric (barometric) pressure.
	 */
	public static final int PROPERTY_barPressure_ID = 1;

	/**
	 * Pressure Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_barPressure_ID, false, true, Pressure).build();
}