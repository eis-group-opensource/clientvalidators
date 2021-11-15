/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.validationsummary;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author Cagatay Civici
 */
public class ValidationSummaryTag extends UIComponentTag {
   
	private String style = null;
	
	public void release() {
	    super.release();
	    style = null;
	}
	
	protected void setProperties(UIComponent component) {
	    super.setProperties(component);
	    
	    if (style != null) {
			if (isValueReference(style)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(style);
				component.setValueBinding("style", vb);
			} else {
				component.getAttributes().put("style", style);
			}
		}
	}
	
	public String getComponentType() {
		return ValidationSummary.COMPONENT_TYPE;
	}
	
	public String getRendererType() {
		return null;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
