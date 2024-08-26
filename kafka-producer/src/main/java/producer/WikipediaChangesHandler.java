package producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class WikipediaChangesHandler implements EventHandler {
    public KafkaTemplate<String,String> kafkaTemplate;

    public String topicName;


    public WikipediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate , String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName=topicName;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
      System.out.println("Event: "+messageEvent.getData());
      kafkaTemplate.send(topicName, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
