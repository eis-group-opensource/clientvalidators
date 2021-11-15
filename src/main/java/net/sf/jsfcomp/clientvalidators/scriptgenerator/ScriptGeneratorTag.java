/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.scriptgenerator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author Cagatay Civici
 */
public class ScriptGeneratorTag extends UIComponentTag {
	
	private String form = null;
	
	private String popup = null;
    
    public void release() {
        super.release();
        form = null;
        popup = null;
    }
    
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        
        if (form != null) {
			if (isValueReference(form)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(form);
				component.setValueBinding("form", vb);
			} else {
				component.getAttributes().put("form", form);
			}
		}
        
        if (popup != null) {
			if (isValueReference(popup)) {
				ValueBinding vb = getFacesContext().getApplication().createValueBinding(popup);
				component.setValueBinding("popup", vb);
			} else {
				component.getAttributes().put("popup", Boolean.valueOf(popup));
			}
		}
    }
    
	public String getComponentType() {
		return ScriptGenerator.COMPONENT_TYPE;
	}
	
	public String getRendererType() {
		return null;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
	public String getPopup() {
		return popup;
	}

	public void setPopup(String popup) {
		this.popup = popup;
	}
}
