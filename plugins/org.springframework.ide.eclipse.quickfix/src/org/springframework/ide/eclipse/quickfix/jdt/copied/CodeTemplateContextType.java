/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     John Kaplan, johnkaplantech@gmail.com - 108071 [code templates] template for body of newly created class
 *******************************************************************************/
package org.springframework.ide.eclipse.quickfix.jdt.copied;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

/**
  */
public class CodeTemplateContextType extends TemplateContextType {

	/* context types */
	public static final String CATCHBLOCK_CONTEXTTYPE = "catchblock_context"; //$NON-NLS-1$

	public static final String METHODBODY_CONTEXTTYPE = "methodbody_context"; //$NON-NLS-1$

	public static final String CONSTRUCTORBODY_CONTEXTTYPE = "constructorbody_context"; //$NON-NLS-1$

	public static final String GETTERBODY_CONTEXTTYPE = "getterbody_context"; //$NON-NLS-1$

	public static final String SETTERBODY_CONTEXTTYPE = "setterbody_context"; //$NON-NLS-1$

	public static final String NEWTYPE_CONTEXTTYPE = "newtype_context"; //$NON-NLS-1$

	public static final String CLASSBODY_CONTEXTTYPE = "classbody_context"; //$NON-NLS-1$

	public static final String INTERFACEBODY_CONTEXTTYPE = "interfacebody_context"; //$NON-NLS-1$

	public static final String ENUMBODY_CONTEXTTYPE = "enumbody_context"; //$NON-NLS-1$

	public static final String ANNOTATIONBODY_CONTEXTTYPE = "annotationbody_context"; //$NON-NLS-1$

	public static final String FILECOMMENT_CONTEXTTYPE = "filecomment_context"; //$NON-NLS-1$

	public static final String TYPECOMMENT_CONTEXTTYPE = "typecomment_context"; //$NON-NLS-1$

	public static final String FIELDCOMMENT_CONTEXTTYPE = "fieldcomment_context"; //$NON-NLS-1$

	public static final String METHODCOMMENT_CONTEXTTYPE = "methodcomment_context"; //$NON-NLS-1$

	public static final String CONSTRUCTORCOMMENT_CONTEXTTYPE = "constructorcomment_context"; //$NON-NLS-1$

	public static final String OVERRIDECOMMENT_CONTEXTTYPE = "overridecomment_context"; //$NON-NLS-1$

	public static final String DELEGATECOMMENT_CONTEXTTYPE = "delegatecomment_context"; //$NON-NLS-1$

	public static final String GETTERCOMMENT_CONTEXTTYPE = "gettercomment_context"; //$NON-NLS-1$

	public static final String SETTERCOMMENT_CONTEXTTYPE = "settercomment_context"; //$NON-NLS-1$

	/* templates */

	private static final String CODETEMPLATES_PREFIX = "org.eclipse.jdt.ui.text.codetemplates."; //$NON-NLS-1$

	public static final String COMMENT_SUFFIX = "comment"; //$NON-NLS-1$

	public static final String CATCHBLOCK_ID = CODETEMPLATES_PREFIX + "catchblock"; //$NON-NLS-1$

	public static final String METHODSTUB_ID = CODETEMPLATES_PREFIX + "methodbody"; //$NON-NLS-1$

	public static final String NEWTYPE_ID = CODETEMPLATES_PREFIX + "newtype"; //$NON-NLS-1$

	public static final String CONSTRUCTORSTUB_ID = CODETEMPLATES_PREFIX + "constructorbody"; //$NON-NLS-1$

	public static final String GETTERSTUB_ID = CODETEMPLATES_PREFIX + "getterbody"; //$NON-NLS-1$

	public static final String SETTERSTUB_ID = CODETEMPLATES_PREFIX + "setterbody"; //$NON-NLS-1$

	public static final String FILECOMMENT_ID = CODETEMPLATES_PREFIX + "file" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String TYPECOMMENT_ID = CODETEMPLATES_PREFIX + "type" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String CLASSBODY_ID = CODETEMPLATES_PREFIX + "classbody"; //$NON-NLS-1$

	public static final String INTERFACEBODY_ID = CODETEMPLATES_PREFIX + "interfacebody"; //$NON-NLS-1$

	public static final String ENUMBODY_ID = CODETEMPLATES_PREFIX + "enumbody"; //$NON-NLS-1$

	public static final String ANNOTATIONBODY_ID = CODETEMPLATES_PREFIX + "annotationbody"; //$NON-NLS-1$

	public static final String FIELDCOMMENT_ID = CODETEMPLATES_PREFIX + "field" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String METHODCOMMENT_ID = CODETEMPLATES_PREFIX + "method" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String CONSTRUCTORCOMMENT_ID = CODETEMPLATES_PREFIX + "constructor" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String OVERRIDECOMMENT_ID = CODETEMPLATES_PREFIX + "override" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String DELEGATECOMMENT_ID = CODETEMPLATES_PREFIX + "delegate" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String GETTERCOMMENT_ID = CODETEMPLATES_PREFIX + "getter" + COMMENT_SUFFIX; //$NON-NLS-1$

	public static final String SETTERCOMMENT_ID = CODETEMPLATES_PREFIX + "setter" + COMMENT_SUFFIX; //$NON-NLS-1$

	/* resolver types */
	public static final String EXCEPTION_TYPE = "exception_type"; //$NON-NLS-1$

	public static final String EXCEPTION_VAR = "exception_var"; //$NON-NLS-1$

	public static final String ENCLOSING_METHOD = "enclosing_method"; //$NON-NLS-1$

	public static final String ENCLOSING_TYPE = "enclosing_type"; //$NON-NLS-1$

	public static final String BODY_STATEMENT = "body_statement"; //$NON-NLS-1$

	public static final String FIELD = "field"; //$NON-NLS-1$

	public static final String FIELD_TYPE = "field_type"; //$NON-NLS-1$

	public static final String BARE_FIELD_NAME = "bare_field_name"; //$NON-NLS-1$

	public static final String PARAM = "param"; //$NON-NLS-1$

	public static final String RETURN_TYPE = "return_type"; //$NON-NLS-1$

	public static final String SEE_TO_OVERRIDDEN_TAG = "see_to_overridden"; //$NON-NLS-1$

	public static final String SEE_TO_TARGET_TAG = "see_to_target"; //$NON-NLS-1$

	public static final String TAGS = "tags"; //$NON-NLS-1$

	public static final String TYPENAME = "type_name"; //$NON-NLS-1$

	public static final String FILENAME = "file_name"; //$NON-NLS-1$

	public static final String PACKAGENAME = "package_name"; //$NON-NLS-1$

	public static final String PROJECTNAME = "project_name"; //$NON-NLS-1$

	public static final String PACKAGE_DECLARATION = "package_declaration"; //$NON-NLS-1$

	public static final String TYPE_DECLARATION = "type_declaration"; //$NON-NLS-1$

	public static final String CLASS_BODY = "classbody"; //$NON-NLS-1$

	public static final String INTERFACE_BODY = "interfacebody"; //$NON-NLS-1$

	public static final String ENUM_BODY = "enumbody"; //$NON-NLS-1$

	public static final String ANNOTATION_BODY = "annotationbody"; //$NON-NLS-1$

	public static final String TYPE_COMMENT = "typecomment"; //$NON-NLS-1$

	public static final String FILE_COMMENT = "filecomment"; //$NON-NLS-1$

	/**
	 * Resolver that resolves to the variable defined in the context.
	 */
	public static class CodeTemplateVariableResolver extends TemplateVariableResolver {
		public CodeTemplateVariableResolver(String type, String description) {
			super(type, description);
		}

		@Override
		protected String resolve(TemplateContext context) {
			return context.getVariable(getType());
		}
	}

}
