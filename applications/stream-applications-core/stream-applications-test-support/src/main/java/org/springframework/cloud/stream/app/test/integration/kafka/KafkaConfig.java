/*
 * Copyright 2020-2020 the original author or authors.
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

package org.springframework.cloud.stream.app.test.integration.kafka;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;

import org.springframework.cloud.stream.app.test.integration.StreamAppContainer;
import org.springframework.cloud.stream.app.test.integration.StreamAppContainerTestUtils;

/**
 * Initializes and starts a Kafka TestContainer and provides associated utilities for configuring a {@link KafkaStreamAppContainer}.
 * @author David Turanski
 */
public abstract class KafkaConfig {
	final static String BINDER = "kafka";

	final static Network network = Network.SHARED;

	public static StreamAppContainer prepackagedContainerFor(String appName, String version) {
		return new KafkaStreamAppContainer(
				StreamAppContainerTestUtils.prePackagedStreamAppImageName(appName, BINDER, version),
				kafka);
	}

	/**
	 * The KafkaContainer.
	 */
	public final static KafkaContainer kafka = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:5.5.1"))
					.withExposedPorts(9092, 9093)
					.withNetwork(network);

	static {
		kafka.start();
	}

}
