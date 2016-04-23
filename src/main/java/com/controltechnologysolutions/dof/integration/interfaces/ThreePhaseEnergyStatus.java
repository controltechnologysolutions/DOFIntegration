package com.controltechnologysolutions.dof.integration.interfaces;

import org.opendof.core.oal.DOFInterface;
import org.opendof.core.oal.DOFInterfaceID;
import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.value.DOFArray;
import org.opendof.core.oal.value.DOFFloat32;

/**
 * <b> Three-Phase Energy Status </b><br />
 * This interface represents the current status of three-phase energy
 * production/consumption of an object (energy system, battery, etc.) This
 * interface consists of parallel arrays, where the status of a given phase uses
 * the same index across all arrays. <br />
 * <br />
 * 
 * IID: [1:{01000053}] <br />
 * 
 * <b> Properties </b><br />
 * Three-Phase Line-to-Line VoltageThree-phase voltage measurement across two
 * lines. The object model identifies the relationship of the two lines. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase VoltageThree-phase voltage measurement output by the object.
 * <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase FrequencyThree-phase frequency measurement output by the object.
 * Zero (0) indicates the phase is unavailable or not being used, and positive
 * values indicate Alternating Current (AC) at the indicated frequency. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase CurrentThree-phase current flow measurement output by the object.
 * As with power, the sign of the current indicates the flow of current, with
 * positive values representing flow from the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase Active PowerThree-phase active power measurement output by the
 * object. Positive values represent power produced/supplied by the object to an
 * entity (e.g., a power grid), and negative values represent power
 * consumed/withdrawn by the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase AC Reactive PowerThree-phase reactive power measurement observed
 * by the object. Positive values represent power produced/supplied by the
 * object to an entity (e.g., a power grid), and negative values represent power
 * consumed/withdrawn by the object. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase Apparent PowerThree-phase apparent power measurement output by
 * the object. This is defined as the magnitude of the complex power, which is
 * the vector sum of the active and reactive power. <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * Three-Phase Power FactorThree-phase ratio of active power to apparent power.
 * <br />
 * <li>Read: true</li>
 * <li>Write: false</li>
 * </ul>
 * 
 * 
 */

public class ThreePhaseEnergyStatus {

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
	 * A measure of real power. <br />
	 */
	public static final DOFType ActivePower = DOFFloat32.TYPE;

	/**
	 * <b> ReactivePower </b><br />
	 * A measure of power. <br />
	 */
	public static final DOFType ReactivePower = DOFFloat32.TYPE;

	/**
	 * <b> ApparentPower </b><br />
	 * A measure of apparent power. <br />
	 */
	public static final DOFType ApparentPower = DOFFloat32.TYPE;

	/**
	 * <b> Factor </b><br />
	 * A ratio. <br />
	 */
	public static final DOFType Factor = DOFFloat32.TYPE;

	/**
	 * <b> ThreePhaseVoltageArray </b><br />
	 * An array of measurements of voltage. <br />
	 */
	public static final DOFType ThreePhaseVoltageArray = new DOFArray.Type(Voltage, 3, 3);

	/**
	 * <b> ThreePhaseFrequencyArray </b><br />
	 * An array of measurements of frequency. <br />
	 */
	public static final DOFType ThreePhaseFrequencyArray = new DOFArray.Type(Frequency, 3, 3);

	/**
	 * <b> ThreePhaseCurrentArray </b><br />
	 * An array of measurements of current. <br />
	 */
	public static final DOFType ThreePhaseCurrentArray = new DOFArray.Type(Current, 3, 3);

	/**
	 * <b> ThreePhaseActivePowerArray </b><br />
	 * An array of measurements of real power. <br />
	 */
	public static final DOFType ThreePhaseActivePowerArray = new DOFArray.Type(ActivePower, 3, 3);

	/**
	 * <b> ThreePhaseReactivePowerArray </b><br />
	 * An array of measurements of reactive power. <br />
	 */
	public static final DOFType ThreePhaseReactivePowerArray = new DOFArray.Type(ReactivePower, 3, 3);

	/**
	 * <b> ThreePhaseApparentPowerArray </b><br />
	 * An array of measurements of apparent power. <br />
	 */
	public static final DOFType ThreePhaseApparentPowerArray = new DOFArray.Type(ApparentPower, 3, 3);

	/**
	 * <b> ThreePhaseFactorArray </b><br />
	 * An array of power factors. <br />
	 */
	public static final DOFType ThreePhaseFactorArray = new DOFArray.Type(Factor, 3, 3);

	/**
	 * Three-Phase Energy Status InterfaceID
	 */
	public static final DOFInterfaceID INTERFACEID = DOFInterfaceID.create("[1:{01000053}]");

	/**
	 * Three-phase voltage measurement across two lines. The object model
	 * identifies the relationship of the two lines.
	 */
	public static final int PROPERTY_ThreePhaseLinetoLineVoltage_ID = 1;

	/**
	 * Three-phase voltage measurement output by the object.
	 */
	public static final int PROPERTY_ThreePhaseVoltage_ID = 2;

	/**
	 * Three-phase frequency measurement output by the object. Zero (0)
	 * indicates the phase is unavailable or not being used, and positive values
	 * indicate Alternating Current (AC) at the indicated frequency.
	 */
	public static final int PROPERTY_ThreePhaseFrequency_ID = 3;

	/**
	 * Three-phase current flow measurement output by the object. As with power,
	 * the sign of the current indicates the flow of current, with positive
	 * values representing flow from the object.
	 */
	public static final int PROPERTY_ThreePhaseCurrent_ID = 4;

	/**
	 * Three-phase active power measurement output by the object. Positive
	 * values represent power produced/supplied by the object to an entity
	 * (e.g., a power grid), and negative values represent power
	 * consumed/withdrawn by the object.
	 */
	public static final int PROPERTY_ThreePhaseActivePower_ID = 5;

	/**
	 * Three-phase reactive power measurement observed by the object. Positive
	 * values represent power produced/supplied by the object to an entity
	 * (e.g., a power grid), and negative values represent power
	 * consumed/withdrawn by the object.
	 */
	public static final int PROPERTY_ThreePhaseACReactivePower_ID = 6;

	/**
	 * Three-phase apparent power measurement output by the object. This is
	 * defined as the magnitude of the complex power, which is the vector sum of
	 * the active and reactive power.
	 */
	public static final int PROPERTY_ThreePhaseApparentPower_ID = 7;

	/**
	 * Three-phase ratio of active power to apparent power.
	 */
	public static final int PROPERTY_ThreePhasePowerFactor_ID = 8;

	/**
	 * Three-Phase Energy Status Definition
	 */
	public static final DOFInterface DEFINITION = new DOFInterface.Builder(INTERFACEID)
			.addProperty(PROPERTY_ThreePhaseLinetoLineVoltage_ID, false, true, ThreePhaseVoltageArray)
			.addProperty(PROPERTY_ThreePhaseVoltage_ID, false, true, ThreePhaseVoltageArray)
			.addProperty(PROPERTY_ThreePhaseFrequency_ID, false, true, ThreePhaseFrequencyArray)
			.addProperty(PROPERTY_ThreePhaseCurrent_ID, false, true, ThreePhaseCurrentArray)
			.addProperty(PROPERTY_ThreePhaseActivePower_ID, false, true, ThreePhaseActivePowerArray)
			.addProperty(PROPERTY_ThreePhaseACReactivePower_ID, false, true, ThreePhaseReactivePowerArray)
			.addProperty(PROPERTY_ThreePhaseApparentPower_ID, false, true, ThreePhaseApparentPowerArray)
			.addProperty(PROPERTY_ThreePhasePowerFactor_ID, false, true, ThreePhaseFactorArray).build();
}