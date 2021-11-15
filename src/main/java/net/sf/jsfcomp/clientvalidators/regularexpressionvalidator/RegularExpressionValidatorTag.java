/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.regularexpressionvalidator;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

/**
 * @author Cagatay Civici
 */
public class RegularExpressionValidatorTag extends ClientValidatorTagBase {
    
    private String pattern = null;

    public void release() {
        super.release();
        pattern = null;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        
        if (pattern != null) {
            if (isValueReference(pattern)) {
                ValueBinding vb = getFacesContext().getApplication()
                        .createValueBinding(pattern);
                component.setValueBinding("pattern", vb);
            } else {
                component.getAttributes().put("pattern", pattern);
            }
        }
    }

    public String getComponentType() {
        return RegularExpressionValidator.COMPONENT_TYPE;
    }

    public String getRendererType() {
        return null;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}