/*
 * Copyright (c) 2010-2014. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.test.saga;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Interface describing methods that can be executed after the first "given" state has been supplied. Either more
 * "given" state can be appended, or a transition to the definition of "when" can be made.
 *
 * @author Allard Buijze
 * @since 1.1
 */
public interface ContinuedGivenState extends WhenState {

    /**
     * Use this method to indicate that an aggregate with given identifier published certain events.
     * <p/>
     * Can be chained to build natural sentences:<br/> <code>andThenAggregate(someIdentifier).published(someEvents)
     * </code>
     *
     * @param aggregateIdentifier The identifier of the aggregate the events should appear to come from
     * @return an object that allows registration of the actual events to send
     */
    GivenAggregateEventPublisher andThenAggregate(Object aggregateIdentifier);

    /**
     * Simulate time shifts in the current given state. This can be useful when the time between given events is of
     * importance.
     *
     * @param elapsedTime The amount of time that will elapse
     * @return an object that allows registration of the actual events to send
     */
    ContinuedGivenState andThenTimeElapses(Duration elapsedTime);

    /**
     * Simulate time shifts in the current given state. This can be useful when the time between given events is of
     * importance.
     *
     * @param newDateTime The time to advance the clock to
     * @return an object that allows registration of the actual events to send
     */
    ContinuedGivenState andThenTimeAdvancesTo(DateTime newDateTime);

    /**
     * Indicates that the given <code>event</code> has been published in the past. This event is sent to the associated
     * sagas.
     *
     * @param event The event to publish
     * @return an object that allows chaining of more given state
     */
    ContinuedGivenState andThenAPublished(Object event);
}
