package com.consumer;

import com.consumer.entity.WikimediaData;
import com.consumer.repository.WikimediaDataRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private WikimediaDataRepo repo;

    public KafkaConsumer(WikimediaDataRepo repo){
        this.repo=repo;
    }

     @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
     public void consume(String eventMessage){
         System.out.println("Consumer receives: "+eventMessage);
         WikimediaData WikimediaData=new WikimediaData();
         WikimediaData.setData(eventMessage);
         repo.save(WikimediaData);

     }
}
