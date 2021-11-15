/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.comparevalidator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

/**
 * @author Jagatai
 */
public class CompareValidatorTag extends ClientValidatorTagBase {
	
	private String componentToCompare = null;
	
	private String operator = null;

	public void release() {
		super.release();
		componentToCompare = null;
		operator = null;
	}

	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		
		if (componentToCompare != null) {
			if (isValueReference(componentToCompare)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(componentToCompare);
				component.setValueBinding("componentToCompare", vb);
			} else {
				component.getAttributes().put("componentToCompare", componentToCompare);
			}
		}
		
		if (operator != null) {
			if (isValueReference(operator)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(operator);
				component.setValueBinding("operator", vb);
			} else {
				component.getAttributes().put("operator", operator);
			}
		}
	}

	public String getComponentType() {
		return CompareValidator.COMPONENT_TYPE;
	}

	public String getRendererType() {
		return null;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getComponentToCompare() {
		return componentToCompare;
	}

	public void setComponentToCompare(String componentToCompare) {
		this.componentToCompare = componentToCompare;
	}
	
}