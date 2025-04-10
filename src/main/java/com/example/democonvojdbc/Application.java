package com.example.democonvojdbc;

import com.github.mhewedy.convo.AbstractConversationHolder;
import com.github.mhewedy.convo.ConversationRepository;
import com.github.mhewedy.convo.annotations.Step;
import com.github.mhewedy.convo.annotations.TimeToLive;
import com.github.mhewedy.convo.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final StoreRepository storeRepository;
    private final ConversationRepository conversationRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("store repository implementation: {}", storeRepository);

        Long ownerId = 12345L;

        var conv = new MyConversation();
        conv.step1Data = "step1Data";
        conversationRepository.update(ownerId, conv);
        log.info("conv id: {}", conv.id);
        log.info("conv: {}", conversationRepository.findById(ownerId, conv.id, MyConversation.class));

        conv = conversationRepository.findById(ownerId, conv.id, MyConversation.class);
        log.info("conv: {}", conversationRepository.findById(ownerId, conv.id, MyConversation.class));
        conv.step2Data =  new MyConversation.Step2Data();
        conv.step2Data.v = 999;
        conversationRepository.update(ownerId, conv);

        log.info("conv: {}", conversationRepository.findById(ownerId, conv.id, MyConversation.class));

//        conversationRepository.remove(ownerId, conv.id, MyConversation.class);
    }

    @TimeToLive(duration = "PT5M")
    @ToString(callSuper = true)
    public static class MyConversation extends AbstractConversationHolder {
        @Step(1)
        public String step1Data;

        @Step(2)
        public Step2Data step2Data;

        public static class Step2Data {
            public Integer v;
        }
    }
}
