/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.comparevalidator;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class CompareValidator extends ClientValidatorBase {

	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.comparevalidator";

	public final static String OPERATOR_EQ = "eq";

	public final static String OPERATOR_NOT = "not";

	private String componentToCompare;

	private String operator;

	public CompareValidator() {
		setRendererType(null);
	}

	public String getComponentToCompare() {
		if(componentToCompare != null)
			return componentToCompare;
		
		ValueBinding vb = getValueBinding("componentToCompare");
		if(vb != null)
			return (String)vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setComponentToCompare(String componentToCompare) {
		this.componentToCompare = componentToCompare;
	}

	public String getOperator() {
		if(operator != null)
			return operator;
		
		ValueBinding vb = getValueBinding("operator");
		if(vb != null)
			return (String)vb.getValue(getFacesContext());
		else
			return OPERATOR_EQ;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();
		if(errorMessage == null) {
			errorMessage = ClientValidatorUtils.getValidatonErrorMessage(ClientValidatorsConstants.MSG_KEY_COMPARE);
		}
		return errorMessage;
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = componentToCompare;
		values[2] = operator;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		componentToCompare = (String) values[1];
		operator = (String) values[2];
	}
}
