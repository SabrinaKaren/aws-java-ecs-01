package br.com.sabrina.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/sum/{val1}/{val2}")
    public ResponseEntity<?> dogTest(@PathVariable int val1, @PathVariable int val2) {
        int result = val1 + val2;
        LOG.info("Math controller - result: {}", result);
        return ResponseEntity.ok("Result: " + result);
    }
    
}