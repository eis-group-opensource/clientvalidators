/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.lengthvalidator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

/**
 * @author Cagatay Civici
 */
public class LengthValidatorTag extends ClientValidatorTagBase {

	private String min = null;

	private String max = null;
	
	private String exactly = null;

	public String getComponentType() {
		return LengthValidator.COMPONENT_TYPE;
	}

	public void release() {
		super.release();
		min = null;
		max = null;
		exactly = null;
	}

	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		if (min != null) {
			if (isValueReference(min)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(min);
				component.setValueBinding("min", vb);
			} else {
				component.getAttributes().put("min", Integer.valueOf(min));
			}
		}

		if (max != null) {
			if (isValueReference(max)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(max);
				component.setValueBinding("max", vb);
			} else {
				component.getAttributes().put("max", Integer.valueOf(max));
			}
		}
		
		if (exactly != null) {
			if (isValueReference(exactly)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(exactly);
				component.setValueBinding("exactly", vb);
			} else {
				component.getAttributes().put("exactly", Integer.valueOf(exactly));
			}
		}
	}

	public String getRendererType() {
		return null;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getExactly() {
		return exactly;
	}

	public void setExactly(String exactly) {
		this.exactly = exactly;
	}
}
