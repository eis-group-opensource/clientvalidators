/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.customvalidator;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;

/**
 * @author Cagatay Civici
 */
public class CustomValidator extends ClientValidatorBase {
	
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.customvalidator";
	
	private String function;
	
	private String params;

	public String getFunction() {
		if(function != null)
			return function;
		
		ValueBinding vb = getValueBinding("function");
		if(vb != null)
			return (String)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getParams() {
		if(params != null)
			return params;
		
		ValueBinding vb = getValueBinding("params");
		if(vb != null)
			return (String)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = function;
		values[2] = params;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		function = (String) values[1];
		params = (String) values[2];
	}
}
