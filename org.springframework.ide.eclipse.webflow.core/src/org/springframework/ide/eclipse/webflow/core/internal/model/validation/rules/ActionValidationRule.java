/*******************************************************************************
 * Copyright (c) 2005, 2007 Spring IDE Developers
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Spring IDE Developers - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.webflow.core.internal.model.validation.rules;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IMethod;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.ide.eclipse.core.MessageUtils;
import org.springframework.ide.eclipse.core.java.Introspector;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.validation.IValidationContext;
import org.springframework.ide.eclipse.core.model.validation.IValidationRule;
import org.springframework.ide.eclipse.webflow.core.internal.model.Action;
import org.springframework.ide.eclipse.webflow.core.internal.model.WebflowModelUtils;
import org.springframework.ide.eclipse.webflow.core.internal.model.validation.WebflowValidationContext;
import org.springframework.util.StringUtils;

/**
 * @author Christian Dupuis
 * @since 2.0
 */
public class ActionValidationRule implements
		IValidationRule<Action, WebflowValidationContext> {

	public boolean supports(IModelElement element, IValidationContext context) {
		return element instanceof Action
				&& context instanceof WebflowValidationContext;
	}

	public void validate(Action action, WebflowValidationContext context,
			IProgressMonitor monitor) {
		if (!StringUtils.hasText(action.getBean())) {
			context.error(this, "NO_BEAN_ATTRIBUTE", action,
					"Element 'action' requires 'bean' attribute");
		}
		else if (!WebflowModelUtils.isReferencedBeanFound(context
				.getWebflowConfig(), action.getBean())) {
			context.error(this, "INVALID_BEAN", action, MessageUtils
					.format("Referenced bean \"{0}\" cannot be found", action
							.getBean()));
		}
		if (StringUtils.hasText(action.getMethod())
				&& !Introspector.doesImplement(WebflowModelUtils.getActionType(
						context.getWebflowConfig(), action.getNode()),
						FactoryBean.class.getName())) {
			List<IMethod> methods = WebflowModelUtils.getActionMethods(context
					.getWebflowConfig(), action.getNode());
			boolean found = false;
			for (IMethod method : methods) {
				if (method.getElementName().equals(action.getMethod())) {
					found = true;
					break;
				}
			}
			if (!found) {
				context.error(this, "INVALID_ACTION_METHOD", action, MessageUtils
						.format("Referenced action method \"{0}\" cannot be found or is not a valid action method", action
							.getMethod()));
			}
		}
	}
}
