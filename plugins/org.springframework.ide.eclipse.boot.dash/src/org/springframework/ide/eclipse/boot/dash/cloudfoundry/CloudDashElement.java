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
package org.springframework.ide.eclipse.boot.dash.cloudfoundry;

import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.springframework.ide.eclipse.boot.dash.metadata.IPropertyStore;
import org.springframework.ide.eclipse.boot.dash.metadata.PropertyStoreApi;
import org.springframework.ide.eclipse.boot.dash.metadata.PropertyStoreFactory;
import org.springframework.ide.eclipse.boot.dash.model.RunState;
import org.springframework.ide.eclipse.boot.dash.model.RunTarget;
import org.springframework.ide.eclipse.boot.dash.model.UserInteractions;
import org.springframework.ide.eclipse.boot.dash.model.WrappingBootDashElement;
import org.springframework.ide.eclipse.boot.dash.model.requestmappings.RequestMapping;

public class CloudDashElement extends WrappingBootDashElement<String> {

	private final CloudFoundryRunTarget cloudTarget;

	private CloudApplication app;

	private final OperationsExecution operations;

	private PropertyStoreApi persistentProperties;

	private final IProject project;

	public CloudDashElement(CloudFoundryBootDashModel model, CloudApplication app, IProject project,
			OperationsExecution operations, IPropertyStore modelStore) {
		super(model, app.getName());
		this.cloudTarget = model.getCloudTarget();
		this.app = app;
		this.operations = operations;
		this.project = project;
		IPropertyStore backingStore = PropertyStoreFactory.createSubStore(delegate, modelStore);
		this.persistentProperties = PropertyStoreFactory.createApi(backingStore);
	}

	@Override
	public void stopAsync(UserInteractions ui) throws Exception {

		CloudApplicationDashOperation op = new CloudApplicationDashOperation("Stopping application", this,
				cloudTarget.getClient(), ui) {

			@Override
			protected CloudApplication doCloudOp(CloudFoundryOperations client, IProgressMonitor monitor)
					throws Exception {
				client.stopApplication(app.getName());
				// fetch an updated Cloud Application that reflects changes that
				// were
				// performed on it
				app = client.getApplication(element.getName());

				getParent().notifyElementChanged(getElement());

				return app;
			}
		};
		operations.runOp(op);
	}

	@Override
	public void restart(RunState runingOrDebugging, UserInteractions ui) throws Exception {

		CloudApplicationDashOperation op = new CloudApplicationDashOperation("Starting application", this,
				cloudTarget.getClient(), ui) {

			@Override
			protected CloudApplication doCloudOp(CloudFoundryOperations client, IProgressMonitor monitor)
					throws Exception {
				client.startApplication(app.getName());
				// fetch an updated Cloud Application that reflects changes that
				// were
				// performed on it
				app = client.getApplication(element.getName());

				getParent().notifyElementChanged(getElement());

				return app;
			}
		};
		operations.runOp(op);
	}

	@Override
	public void openConfig(UserInteractions ui) {

	}

	@Override
	public String getName() {
		return app.getName();
	}

	@Override
	public IJavaProject getJavaProject() {
		return getProject() != null ? JavaCore.create(getProject()) : null;
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public RunState getRunState() {
		if (app != null && app.getState() != null) {
			switch (this.app.getState()) {
			case STARTED:
				return RunState.RUNNING;
			case STOPPED:
				return RunState.INACTIVE;
			case UPDATING:
				return RunState.STARTING;
			}
		}

		return RunState.INACTIVE;
	}

	@Override
	public RunTarget getTarget() {
		return cloudTarget;
	}

	@Override
	public int getLivePort() {
		return 0;
	}

	@Override
	public String getLiveHost() {
		return null;
	}

	@Override
	public List<RequestMapping> getLiveRequestMappings() {
		return new ArrayList<RequestMapping>(0);
	}

	@Override
	public ILaunchConfiguration getActiveConfig() {
		return null;
	}

	@Override
	public ILaunchConfiguration getPreferredConfig() {
		return null;
	}

	@Override
	public void setPreferredConfig(ILaunchConfiguration config) {

	}

	@Override
	public String getDefaultRequestMappingPath() {
		return null;
	}

	@Override
	public void setDefaultRequestMapingPath(String defaultPath) {

	}

	@Override
	public int getActualInstances() {
		return 1;
	}

	@Override
	public int getDesiredInstances() {
		return 1;
	}

	@Override
	public PropertyStoreApi getPersistentProperties() {
		return persistentProperties;
	}
}
