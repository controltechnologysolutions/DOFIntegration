package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFFloat32;

/**
 * <b> Pyranometer </b><br />
 * This interface represents a pyranometer sensor, which measuers the solar
 * irradiance in the operational plane of the device. This corresponds to the
 * Plane of Array value in the Irradiance model of the SunSpec Alliance
 * Specification. Other data about the device (such as location) are provided on
 * different interfaces. <br />
 * <br />
 * 
 * IID: [1:{021C}] <br />
 * 
 * <b> Properties </b><br />
 * InsolationThe measurement of the amount of solar enegery received per square
 * meter. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * IrradianceThe measurement of irradiance across the planar surface. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class Pyranometer {

	/**
	 * <b> RadiationFluxDensity </b><br />
	 * The measure of radiation flux density incident on a surface. <br />
	 */
	public static final DOFType RadiationFluxDensity = DOFFloat32.TYPE;

	/**
	 * <b> Insolation </b><br />
	 * The measurement of the amount of solar energy received per square meter.
	 * <br />
	 */
	public static final DOFType Insolation = DOFFloat32.TYPE;

	/**
	 * Pyranometer InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{021C}]");

	/**
	 * The measurement of the amount of solar enegery received per square meter.
	 */
	public static final int PROPERTY_Insolation_ID = 0;

	/**
	 * The measurement of irradiance across the planar surface.
	 */
	public static final int PROPERTY_Irradiance_ID = 1;

	/**
	 * Pyranometer Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_Insolation_ID, false, true, Insolation)
			.addProperty(PROPERTY_Irradiance_ID, false, true, RadiationFluxDensity).build();
}