/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.utils;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author Cagatay Civici
 */
public class ClientValidatorUtils {

	public static UIComponent findComponent(UIComponent root, String id) {
		if (id.equals(root.getId()))
			return root;

		UIComponent child = null;
		UIComponent component = null;
		Iterator children = root.getFacetsAndChildren();
		while (children.hasNext() && (component == null)) {
			child = (UIComponent) children.next();
			if (id.equals(child.getId())) {
				component = child;
				break;
			}
			component = findComponent(child, id);
			if (component != null) {
				break;
			}
		}
		return component;
	}

	public static UIComponent findComponentInView(String id) {
		UIComponent component = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			UIComponent root = context.getViewRoot();
			component = findComponent(root, id);
		}
		return component;
	}
	
	public static ResourceBundle getResourceBundle() {
		Application application = FacesContext.getCurrentInstance().getApplication();
		String messageBundle = application.getMessageBundle();
		Locale defaultLocale = application.getDefaultLocale();
		if(messageBundle == null || defaultLocale == null)
			return null;
		else
			return ResourceBundle.getBundle(application.getMessageBundle(), application.getDefaultLocale());
	}
	
	public static String getValidatonErrorMessage(String key) {
		if(getResourceBundle() != null)
			return getResourceBundle().getString(key);
		else
			return ClientValidatorsConstants.MSG_DEFAULT;
	}
	
	public static String getResourceName(Map map) {
		String resourceName = (String)map.get("name");
		return resourceName;
	}
	
	public static String getResourceType(Map map) {
		String resourceType = (String)map.get("type");
		return resourceType;
	}
	
	//We need these three resource types for now
	public static String getContentType(String resourceType) {
		String contentType = null;
		if(resourceType.equals("js"))
			contentType = "text/javascript";
		else if(resourceType.equals("css"))
			contentType = "text/css";
		
		return contentType;
	}
}
