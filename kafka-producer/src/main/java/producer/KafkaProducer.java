package producer;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    public String topicName = "wikimedia-topic";

    KafkaTemplate<String,String> kafkaTemplate;

//@autowired no need as it's only one bean
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        EventHandler handler=new WikipediaChangesHandler(kafkaTemplate,topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        //class that handles the connection to a server that sends events over HTTP using the Server-Sent Events (SSE) protocol.
        //This combination tells the EventSource where to connect (URI) and what to do with the data it receives (eventHandler).
        EventSource.Builder builder= new EventSource.Builder(handler, URI.create(url));

        //The builder.build() method finalizes the configuration and returns a new EventSource object.
        //The EventSource object encapsulates all the settings, such as the EventHandler and the URI, and is now ready to connect to the SSE endpoint.
        EventSource eventSource = builder.build();

        //starts the event listening process
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }


}
