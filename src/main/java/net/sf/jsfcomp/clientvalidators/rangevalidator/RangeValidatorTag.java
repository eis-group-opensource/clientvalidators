/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.rangevalidator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

/**
 * @author Cagatay Civici
 */
public class RangeValidatorTag extends ClientValidatorTagBase {

	private String minValue = null;

	private String maxValue = null;

	public void release() {
		super.release();
		minValue = null;
		maxValue = null;
	}

	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		if (minValue != null) {
			if (isValueReference(minValue)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(minValue);
				component.setValueBinding("minValue", vb);
			} else {
				component.getAttributes().put("minValue", Integer.valueOf(minValue));
			}
		}

		if (maxValue != null) {
			if (isValueReference(maxValue)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(maxValue);
				component.setValueBinding("maxValue", vb);
			} else {
				component.getAttributes().put("maxValue", Integer.valueOf(maxValue));
			}
		}
	}

	public String getComponentType() {
		return RangeValidator.COMPONENT_TYPE;
	}

	public String getRendererType() {
		return null;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
}

