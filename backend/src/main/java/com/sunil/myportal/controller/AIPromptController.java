/*
package com.sunil.myportal.controller;

//import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt")
public class AIPromptController {

   private final ChatClient chatClient;

   public AIPromptController(ChatClient chatClient) {
      this.chatClient= chatClient;
   }

   @GetMapping("/hello")
   public String testAiPrompt() {
      String prompt= "Hello! What is Spring Boot?";
      return chatClient.call(prompt);//call(prompt);
   }
}*/
