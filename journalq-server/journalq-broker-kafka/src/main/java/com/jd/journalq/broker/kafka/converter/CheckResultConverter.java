package com.jd.journalq.broker.kafka.converter;

import com.jd.journalq.broker.kafka.KafkaErrorCode;
import com.jd.journalq.exception.JMQCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CheckResultConverter
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/1/14
 */
public class CheckResultConverter {

    protected static final Logger logger = LoggerFactory.getLogger(CheckResultConverter.class);

    public static short convertProduceCode(JMQCode code) {
        switch (code) {
            case FW_TOPIC_NOT_EXIST:
            case FW_PRODUCER_NOT_EXISTS:
            case FW_PUT_MESSAGE_TOPIC_NOT_WRITE: {
                return KafkaErrorCode.UNKNOWN_TOPIC_OR_PARTITION.getCode();
            }
            case FW_PRODUCE_MESSAGE_BROKER_NOT_LEADER:
            case FW_TOPIC_NO_PARTITIONGROUP: {
                return KafkaErrorCode.NOT_LEADER_FOR_PARTITION.getCode();
            }
            default : {
                logger.warn("unknown produce code {}", code);
                return KafkaErrorCode.UNKNOWN_TOPIC_OR_PARTITION.getCode();
            }
        }
    }

    public static short convertFetchCode(JMQCode code) {
        switch (code) {
            case FW_FETCH_TOPIC_MESSAGE_BROKER_NOT_LEADER:
            case FW_TOPIC_NO_PARTITIONGROUP: {
                return KafkaErrorCode.NOT_LEADER_FOR_PARTITION.getCode();
            }
            case FW_FETCH_TOPIC_MESSAGE_PAUSED:
            case FW_GET_MESSAGE_APP_CLIENT_IP_NOT_READ: {
                return KafkaErrorCode.NONE.getCode();
            }
            case FW_TOPIC_NOT_EXIST:
            case FW_CONSUMER_NOT_EXISTS: {
                return KafkaErrorCode.UNKNOWN_TOPIC_OR_PARTITION.getCode();
            }
            default : {
                logger.warn("unknown fetch code {}", code);
                return KafkaErrorCode.UNKNOWN_TOPIC_OR_PARTITION.getCode();
            }
        }
    }
}