package com.jd.journalq.broker.kafka.command;

import com.jd.journalq.broker.kafka.KafkaCommandType;
import com.jd.journalq.broker.kafka.model.OffsetAndMetadata;

import java.util.List;
import java.util.Map;

/**
 * TxnOffsetCommitRequest
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/4/4
 */
public class TxnOffsetCommitRequest extends KafkaRequestOrResponse {

    private String transactionId;
    private String groupId;
    private long producerId;
    private short producerEpoch;
    private Map<String, List<OffsetAndMetadata>> offsetAndMetadata;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public short getProducerEpoch() {
        return producerEpoch;
    }

    public void setProducerEpoch(short producerEpoch) {
        this.producerEpoch = producerEpoch;
    }

    public void setOffsetAndMetadata(Map<String, List<OffsetAndMetadata>> offsetAndMetadata) {
        this.offsetAndMetadata = offsetAndMetadata;
    }

    public Map<String, List<OffsetAndMetadata>> getOffsetAndMetadata() {
        return offsetAndMetadata;
    }

    @Override
    public int type() {
        return KafkaCommandType.TXN_OFFSET_COMMIT.getCode();
    }
}
