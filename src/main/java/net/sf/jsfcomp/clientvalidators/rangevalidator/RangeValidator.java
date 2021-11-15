/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.rangevalidator;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class RangeValidator extends ClientValidatorBase {
	
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.rangevalidator";

	private Integer minValue;

	private Integer maxValue;

	public RangeValidator() {
		setRendererType(null);
	}

	public Integer getMaxValue() {
		if (maxValue != null)
			return maxValue;

		ValueBinding vb = getValueBinding("maxValue");
		if(vb != null)
			return (Integer)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		if (minValue != null)
			return minValue;

		ValueBinding vb = getValueBinding("minValue");
		if(vb != null)
			return (Integer)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}
	
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();
		if(errorMessage == null) {
			errorMessage = ClientValidatorUtils.getValidatonErrorMessage(ClientValidatorsConstants.MSG_KEY_RANGE);
		}
		return errorMessage;
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = minValue;
		values[2] = maxValue;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		minValue = (Integer) values[1];
		maxValue = (Integer) values[2];
	}
}
