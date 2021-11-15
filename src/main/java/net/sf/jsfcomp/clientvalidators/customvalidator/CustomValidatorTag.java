/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.customvalidator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

/**
 * @author Cagatay Civici
 */
public class CustomValidatorTag extends ClientValidatorTagBase {
	
	private String function = null;
	
	private String params = null;
	
	public void release() {
		super.release();
		function = null;
		params = null;
	}

	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		
		if (function != null) {
			if (isValueReference(function)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(function);
				component.setValueBinding("function", vb);
			} else {
				component.getAttributes().put("function", function);
			}
		}
		
		if (params != null) {
			if (isValueReference(params)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(params);
				component.setValueBinding("params", vb);
			} else {
				component.getAttributes().put("params", params);
			}
		}
		
	}
	
	public String getComponentType() {
		return CustomValidator.COMPONENT_TYPE;
	}

	public String getRendererType() {
		return null;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
