/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.lengthvalidator;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class LengthValidator extends ClientValidatorBase {
	
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.lengthvalidator";

	private Integer min;

	private Integer max;
	
	private Integer exactly;
	
	public Integer getMin() {
		if (min != null)
			return min;

		ValueBinding vb = getValueBinding("min");
		if(vb != null)
			return (Integer)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		if (max != null)
			return max;

		ValueBinding vb = getValueBinding("max");
		if(vb != null)
			return (Integer)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	public Integer getExactly() {
		if (exactly != null)
			return exactly;

		ValueBinding vb = getValueBinding("exactly");
		if(vb != null)
			return (Integer)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setExactly(Integer exactly) {
		this.exactly = exactly;
	}
	
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();
		if(errorMessage == null) {
			errorMessage = ClientValidatorUtils.getValidatonErrorMessage(ClientValidatorsConstants.MSG_KEY_LENGTH);
		}
		return errorMessage;
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[4];
		values[0] = super.saveState(context);
		values[1] = min;
		values[2] = max;
		values[3] = exactly;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		min = (Integer) values[1];
		max = (Integer) values[2];
		exactly = (Integer) values[3];
	}
}
