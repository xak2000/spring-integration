/*
 * Copyright 2013-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.events;

import org.jspecify.annotations.Nullable;

import org.springframework.context.ApplicationEvent;

/**
 * Base class for all {@link ApplicationEvent}s generated by the framework.
 * Contains an optional cause field; a separate Exception event hierarchy
 * is not possible because of Java single inheritance (modules should make
 * all their events subclasses of 'xxxIntegrationEvent').
 *
 * @author Gary Russell
 * @since 3.0
 *
 */
@SuppressWarnings("serial")
public abstract class IntegrationEvent extends ApplicationEvent {

	protected final Throwable cause; // NOSONAR protected final

	public IntegrationEvent(Object source) {
		this(source, null);
	}

	public IntegrationEvent(Object source, @Nullable Throwable cause) {
		super(source);
		this.cause = cause;
	}

	@Nullable
	public Throwable getCause() {
		return this.cause;
	}

	/**
	 * Get the source as a specific type; the receiving variable must be declared with the
	 * correct type.
	 * @param <T> the type.
	 * @return the source.
	 * @since 5.4
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSourceAsType() {
		return (T) getSource();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [source=" + this.getSource() +
				(this.cause == null ? "" : ", cause=" + this.cause) + "]";
	}

}
