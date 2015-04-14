/*******************************************************************************
 * Copyright (c) 2015 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.properties.editor.test;

import org.eclipse.jdt.internal.ui.propertiesfileeditor.PropertiesFileDocumentSetupParticipant;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Point;

/**
 * Basic 'simulated' editor. Contains text and a cursor position / selection.
 */
@SuppressWarnings("restriction")
public class MockEditor {

	int selectionStart;
	private int selectionEnd;
	Document document;

	public Document getDocument() {
		return document;
	}

	/**
	 * Create mock editor. Selection position is initialized by looking for the CURSOR string.
	 * <p>
	 * THe cursor string is not actually considered part of the text, but only a marker for
	 * the cursor position.
	 * <p>
	 * If one 'cursor' marker is present in the text the selection
	 * is length 0 and starts at the marker.
	 * <p>
	 * If two markers are present the selection is between the two
	 * markers.
	 * <p>
	 * If no markers are present the cursor is placed at the very end of the document.
	 */
	public MockEditor(String text) {
		selectionStart = text.indexOf(SpringPropertiesEditorTestHarness.CURSOR);
		if (selectionStart>=0) {
			text = text.substring(0,selectionStart) + text.substring(selectionStart+SpringPropertiesEditorTestHarness.CURSOR.length());
			selectionEnd = text.indexOf(SpringPropertiesEditorTestHarness.CURSOR, selectionStart);
			if (selectionEnd>=0) {
				text = text.substring(0, selectionEnd) + text.substring(selectionEnd+SpringPropertiesEditorTestHarness.CURSOR.length());
			}
		} else {
			//No CURSOR markers found
			selectionStart = text.length();
			selectionEnd = text.length();
		}
		this.document = new Document(text);
		PropertiesFileDocumentSetupParticipant.setupDocument(document);
	}

	/**
	 * Get the editor text, with cursor markers inserted (for easy textual comparison
	 * after applying a proposal)
	 */
	public String getText() {
		String text = document.get();
		text = text.substring(0, selectionEnd) + SpringPropertiesEditorTestHarness.CURSOR + text.substring(selectionEnd);
		if (selectionStart<selectionEnd) {
			text = text.substring(0,selectionStart) + SpringPropertiesEditorTestHarness.CURSOR + text.substring(selectionStart);
		}
		return text;
	}

	/**
	 * Set selection based on result returned by ICompletionProposal getSelection method.
	 */
	public void setSelection(Point selection) {
		if (selection!=null) {
			selectionStart = selection.x;
			selectionEnd = selectionStart+selection.y;
		}
	}

	public void apply(ICompletionProposal completion) {
		completion.apply(document);
		setSelection(completion.getSelection(document));
	}

	public String getText(int offset, int length) throws BadLocationException {
		if (offset>=document.getLength()) {
			//no bad location exception please. There's no text past the end of doc so return "". This is quite logical, thank you very much.
			return "";
		}
		return document.get(offset, length);
	}

}