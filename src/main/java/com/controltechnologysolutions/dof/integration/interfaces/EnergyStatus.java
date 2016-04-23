package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFFloat32;

/**
 * <b> Energy Status </b><br />
 * This interface represents the current status of energy production/consumption
 * of an object (energy system, battery, etc.). <br />
 * <br />
 * 
 * IID: [1:{0221}] <br />
 * 
 * <b> Properties </b><br />
 * VoltageA measure of the voltage output by the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * FrequencyA measure of the frequency output by the object. Zero (0) indicates
 * Direct Current (DC), and positive values indicate Alternating Current (AC) at
 * the indicated frequency. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * CurrentA measure of the current flow output by the object. As with power, the
 * sign of the current indicates the flow of current, with positive values
 * representing flow from the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Active PowerA measure of the active power output by the object. Positive
 * values represent power produced/supplied by the object to an entity (e.g., a
 * power grid), and negative values represent power consumed/withdrawn by the
 * object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * AC Reactive PowerA measure of the reactive power observed by the object.
 * Positive values represent power produced/supplied by the object to an entity
 * (e.g., a power grid), and negative values represent power consumed/withdrawn
 * by the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class EnergyStatus {

	/**
	 * <b> Voltage </b><br />
	 * A measure of voltage. <br />
	 */
	public static final DOFType Voltage = DOFFloat32.TYPE;

	/**
	 * <b> Frequency </b><br />
	 * A measure of frequency. <br />
	 */
	public static final DOFType Frequency = DOFFloat32.TYPE;

	/**
	 * <b> Current </b><br />
	 * A measure of current. <br />
	 */
	public static final DOFType Current = DOFFloat32.TYPE;

	/**
	 * <b> ActivePower </b><br />
	 * A measure of power. <br />
	 */
	public static final DOFType ActivePower = DOFFloat32.TYPE;

	/**
	 * <b> ReactivePower </b><br />
	 * A measure of power, in volt-ampere reactive (var). <br />
	 */
	public static final DOFType ReactivePower = DOFFloat32.TYPE;

	/**
	 * Energy Status InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{0221}]");

	/**
	 * A measure of the voltage output by the object.
	 */
	public static final int PROPERTY_Voltage_ID = 1;

	/**
	 * A measure of the frequency output by the object. Zero (0) indicates
	 * Direct Current (DC), and positive values indicate Alternating Current
	 * (AC) at the indicated frequency.
	 */
	public static final int PROPERTY_Frequency_ID = 2;

	/**
	 * A measure of the current flow output by the object. As with power, the
	 * sign of the current indicates the flow of current, with positive values
	 * representing flow from the object.
	 */
	public static final int PROPERTY_Current_ID = 3;

	/**
	 * A measure of the active power output by the object. Positive values
	 * represent power produced/supplied by the object to an entity (e.g., a
	 * power grid), and negative values represent power consumed/withdrawn by
	 * the object.
	 */
	public static final int PROPERTY_ActivePower_ID = 4;

	/**
	 * A measure of the reactive power observed by the object. Positive values
	 * represent power produced/supplied by the object to an entity (e.g., a
	 * power grid), and negative values represent power consumed/withdrawn by
	 * the object.
	 */
	public static final int PROPERTY_ACReactivePower_ID = 5;

	/**
	 * Energy Status Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_Voltage_ID, false, true, Voltage)
			.addProperty(PROPERTY_Frequency_ID, false, true, Frequency)
			.addProperty(PROPERTY_Current_ID, false, true, Current)
			.addProperty(PROPERTY_ActivePower_ID, false, true, ActivePower)
			.addProperty(PROPERTY_ACReactivePower_ID, false, true, ReactivePower).build();
}
