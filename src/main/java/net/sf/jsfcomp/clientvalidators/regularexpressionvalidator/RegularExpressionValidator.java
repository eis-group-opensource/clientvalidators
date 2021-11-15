/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.regularexpressionvalidator;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class RegularExpressionValidator extends ClientValidatorBase {
	
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.regularexpressionvalidator";
	
	private String pattern;

	public RegularExpressionValidator() {
		setRendererType(null);
	}

	public String getPattern() {
		if (pattern != null)
			return pattern;

		ValueBinding vb = getValueBinding("pattern");
		if(vb != null)
			return (String)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();
		if(errorMessage == null) {
			errorMessage = ClientValidatorUtils.getValidatonErrorMessage(ClientValidatorsConstants.MSG_KEY_REGULAR);
		}
		return errorMessage;
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[2];
		values[0] = super.saveState(context);
		values[1] = pattern;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		pattern = (String) values[1];
	}
}