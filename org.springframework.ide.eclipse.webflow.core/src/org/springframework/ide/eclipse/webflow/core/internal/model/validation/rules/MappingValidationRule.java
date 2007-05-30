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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.convert.support.DefaultConversionService;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.core.MessageUtils;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.validation.IValidationContext;
import org.springframework.ide.eclipse.core.model.validation.IValidationRule;
import org.springframework.ide.eclipse.webflow.core.internal.model.Mapping;
import org.springframework.ide.eclipse.webflow.core.internal.model.validation.WebflowValidationContext;
import org.springframework.util.StringUtils;

/**
 * @author Christian Dupuis
 * @since 2.0
 */
public class MappingValidationRule implements
		IValidationRule<Mapping, WebflowValidationContext> {

	private ConversionService conversionService = null;

	public boolean supports(IModelElement element, IValidationContext context) {
		return element instanceof Mapping
				&& context instanceof WebflowValidationContext;
	}

	public void validate(Mapping mapping, WebflowValidationContext context,
			IProgressMonitor monitor) {

		if (!StringUtils.hasText(mapping.getSource())) {
			context
					.error(this, "NO_INPUT_ATTRIBUTE_ATTRIBUTE", mapping,
							"Element 'mapping' element requires 'input-attribute' attribute");
		}
		if (!StringUtils.hasText(mapping.getTarget())
				&& !StringUtils.hasText(mapping.getTargetCollection())) {
			context
					.error(
							this,
							"INVALID_USAGE_OF_TARGET_ATTRIBUTE",
							mapping,
							"Using 'target' and 'target-collection' attributes is not allowed on 'mapping' element");
		}
		if (StringUtils.hasText(mapping.getTo())
				&& getJavaType(mapping.getTo(), context) == null) {
			context.error(this, "NO_CLASS_FOUND", mapping, MessageUtils.format(
					"Class 'to' \"{0}\" cannot be resolved", mapping.getTo()));
		}
		if (StringUtils.hasText(mapping.getFrom())
				&& getJavaType(mapping.getFrom(), context) == null) {
			context.error(this, "NO_CLASS_FOUND", mapping, MessageUtils.format(
					"Class 'from' \"{0}\" cannot be resolved", mapping
							.getFrom()));
		}
	}

	private IType getJavaType(String className, WebflowValidationContext context) {
		IType type = BeansModelUtils.getJavaType(context.getWebflowConfig()
				.getProject().getProject(), className);
		if (type == null) {
			Class clazz = getConversionService().getClassByAlias(className);
			if (clazz != null) {
				type = BeansModelUtils.getJavaType(context.getWebflowConfig()
						.getProject().getProject(), clazz
						.getName());
			}
		}
		return type;
	}

	private ConversionService getConversionService() {
		if (this.conversionService == null) {
			this.conversionService = new DefaultConversionService();
		}
		return this.conversionService;
	}
}
