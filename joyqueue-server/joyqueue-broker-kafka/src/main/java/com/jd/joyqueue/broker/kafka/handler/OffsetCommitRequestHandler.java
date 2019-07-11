/**
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
package com.jd.joyqueue.broker.kafka.handler;


import com.jd.joyqueue.broker.kafka.KafkaCommandType;
import com.jd.joyqueue.broker.kafka.KafkaContext;
import com.jd.joyqueue.broker.kafka.KafkaContextAware;
import com.jd.joyqueue.broker.kafka.command.OffsetCommitRequest;
import com.jd.joyqueue.broker.kafka.command.OffsetCommitResponse;
import com.jd.joyqueue.broker.kafka.config.KafkaConfig;
import com.jd.joyqueue.broker.kafka.coordinator.group.GroupCoordinator;
import com.jd.joyqueue.broker.kafka.model.OffsetMetadataAndError;
import com.jd.joyqueue.network.transport.Transport;
import com.jd.joyqueue.network.transport.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * OffsetCommitRequestHandler
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/11/5
 */
public class OffsetCommitRequestHandler extends AbstractKafkaCommandHandler implements KafkaContextAware {

    protected static final Logger logger = LoggerFactory.getLogger(OffsetCommitRequestHandler.class);

    private GroupCoordinator groupCoordinator;
    private KafkaConfig config;

    @Override
    public void setKafkaContext(KafkaContext kafkaContext) {
        this.groupCoordinator = kafkaContext.getGroupCoordinator();
        this.config = kafkaContext.getConfig();
    }

    @Override
    public Command handle(Transport transport, Command command) {
        OffsetCommitRequest offsetCommitRequest = (OffsetCommitRequest) command.getPayload();

        Map<String, List<OffsetMetadataAndError>> result = groupCoordinator.handleCommitOffsets(offsetCommitRequest.getGroupId(), offsetCommitRequest.getMemberId(),
                offsetCommitRequest.getGroupGenerationId(), offsetCommitRequest.getOffsets());

        if (config.getLogDetail(offsetCommitRequest.getClientId())) {
            logger.info("offset commit request with correlation id {} from transport: {}, client {}, request: {}, result: {}",
                    transport, offsetCommitRequest.getCorrelationId(), offsetCommitRequest.getGroupId(), offsetCommitRequest, result);
        }

        OffsetCommitResponse offsetCommitResponse = new OffsetCommitResponse(result);
        return new Command(offsetCommitResponse);
    }

    @Override
    public int type() {
        return KafkaCommandType.OFFSET_COMMIT.getCode();
    }
}