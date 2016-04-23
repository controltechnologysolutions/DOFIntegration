package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFUInt64;

/**
 * <b> Lifetime Energy Meter </b><br />
 * This interface represents the integrated power from a bi-directional meter.
 * This means that it measures the energy used or provided over time, in this
 * case over the lifetime of the providing object. This interface should only be
 * used if a true lifetime aggregation is possible. <br />
 * <br />
 * 
 * IID: [1:{0239}] <br />
 * 
 * <b> Properties </b><br />
 * OutputThe output energy. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * InputThe input energy. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class LifetimeEnergyMeter {

	/**
	 * <b> uint64_0 </b><br />
	 * The energy exchanged. <br />
	 */
	public static final DOFType uint64_0 = DOFUInt64.TYPE;

	/**
	 * Lifetime Energy Meter InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{0239}]");

	/**
	 * The output energy.
	 */
	public static final int PROPERTY_Output_ID = 0;

	/**
	 * The input energy.
	 */
	public static final int PROPERTY_Input_ID = 1;

	/**
	 * Lifetime Energy Meter Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_Output_ID, false, true, uint64_0)
			.addProperty(PROPERTY_Input_ID, false, true, uint64_0).build();
}
