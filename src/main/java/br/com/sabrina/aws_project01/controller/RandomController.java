package br.com.sabrina.aws_project01.controller;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/random")
public class RandomController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/word")
    public ResponseEntity<?> getRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8)+3];
        for(int i=0; i<word.length; i++) {
            word[i] = (char)('a' + random.nextInt(26));
        }
        LOG.info("Random controller - generated word: {}", word);
        return ResponseEntity.ok("Generated word: " + new String(word));
    }
    
}