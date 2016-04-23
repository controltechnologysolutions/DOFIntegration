package com.controltechnologysolutions.dof.integration.helper;

import org.opendof.core.oal.DOFType;
import org.opendof.core.oal.DOFValue;
import org.opendof.core.oal.value.DOFArray;

import com.controltechnologysolutions.dof.integration.dto.DeviceValue;

public class ConverterHelper {
	public static final void convertValue(DOFValue value, DeviceValue devValue) {
		DOFType type = null;
		try {
			type = value.getDOFType();
		} catch (UnsupportedOperationException e) {
			// it's a skip
			return;
		}

		switch (type.getTypeID()) {
		case DOFType.FLOAT32:
			Float floatValue = DOFType.asFloat(value);
			devValue.setValueFloat1(floatValue);
			return;
		case DOFType.UINT8:
		case DOFType.UINT16:
		case DOFType.UINT32:
		case DOFType.UINT64:
			Long uLongValue = DOFType.asULong(value).longValue();
			devValue.setValueLong(uLongValue);
			return;
		case DOFType.INT8:
		case DOFType.INT16:
		case DOFType.INT32:
		case DOFType.INT64:
			Long longValue = DOFType.asLong(value);
			devValue.setValueLong(longValue);
			return;
		case DOFType.ARRAY:
			DOFArray array = (DOFArray) value;
			if (DOFType.FLOAT32 != array.get(0).getDOFType().getTypeID() || array.size() != 3) {
				throw new RuntimeException("Unknown type of data.");
			}
			devValue.setValueFloat1(DOFType.asFloat(array.get(0)));
			devValue.setValueFloat2(DOFType.asFloat(array.get(1)));
			devValue.setValueFloat3(DOFType.asFloat(array.get(2)));
			return;
		default:
			throw new RuntimeException("Unknown type of data.");
		}
	}
}
