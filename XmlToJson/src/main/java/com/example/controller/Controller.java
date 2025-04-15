package com.example.controller;



import com.example.util.XmlToJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convertXmlToJson(@RequestBody String xmlInput) {
        try {
            JsonNode jsonOutput = XmlToJsonConverter.convert(xmlInput);
            logger.info("XML successfully converted to JSON");
            return ResponseEntity.ok(jsonOutput);
        } catch (Exception e) {
            logger.error("Error during conversion: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "error", "Conversion failed",
                    "details", e.getMessage()
            ));
        }
    }
}
