/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author Cagatay Civici
 */
public abstract class ClientValidatorTagBase extends UIComponentTag {

	private String componentToValidate = null;

	private String errorMessage = null;

	private String highlight = null;

	private String display = null;
	
	private String style = null;
	
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		
		if (componentToValidate != null) {
			if (isValueReference(componentToValidate)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(componentToValidate);
				component.setValueBinding("componentToValidate", vb);
			} else {
				component.getAttributes().put("componentToValidate", componentToValidate);
			}
		}

		if (errorMessage != null) {
			if (isValueReference(errorMessage)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(errorMessage);
				component.setValueBinding("errorMessage", vb);
			} else {
				component.getAttributes().put("errorMessage", errorMessage);
			}
		}

		if (highlight != null) {
			if (isValueReference(highlight)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(highlight);
				component.setValueBinding("highlight", vb);
			} else {
				component.getAttributes().put("highlight", Boolean.valueOf(highlight));
			}
		}

		if (display != null) {
			if (isValueReference(display)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(display);
				component.setValueBinding("display", vb);
			} else {
				component.getAttributes().put("display", display);
			}
		}
		
		if (style != null) {
			if (isValueReference(style)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(style);
				component.setValueBinding("style", vb);
			} else {
				component.getAttributes().put("style", style);
			}
		}
	}
	
	public void release() {
		super.release();
		componentToValidate = null;
		errorMessage = null;
		highlight = null;
		display = null;
		style = null;
	}

	public String getComponentToValidate() {
		return componentToValidate;
	}

	public void setComponentToValidate(String componentToValidate) {
		this.componentToValidate = componentToValidate;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}