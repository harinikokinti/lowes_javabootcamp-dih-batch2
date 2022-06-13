package com.examples.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;
/*
addressability  - @RestController
accessability   - path = "/greetings"
representability - representability by methods to consume (GET) or produce (POST) data
 */

@RestController
public class GreetingsController {

    Logger logger = LoggerFactory.getLogger(GreetingsController.class);

    // http://localhost:8080/spring-rest-labs/greetings
    @RequestMapping(path = "/greetings", method = RequestMethod.GET, produces ={MediaType.TEXT_PLAIN_VALUE} )
    public String greetings() {
        return "Hello Spring REST";
    }



//    @RequestMapping(value = { "/", "/greetings" }, method = RequestMethod.GET)
//    public String greeting() {
//        return "Welcome to RESTful services training :)";
//    }
//
    @RequestMapping(value = "/greetings", method = RequestMethod.GET, headers = { "message" })
    public String getGreeting(@RequestHeader String message) {
        return "Welcome to RESTful services training :) - " + message;
    }
//
//    @RequestMapping(value = "/greetings", method = RequestMethod.GET, params = { "msg1", "msg2" })
//    public String getGreeting(@RequestParam(name = "msg1", required = true, defaultValue = "Hello") String msg1,
//                              @RequestParam(name = "msg2",required = false, defaultValue = "spring") String msg2) {
//        return "Welcome to RESTful services training :) - " + msg1 + " " +  msg2 ;
//    }
//
//    @RequestMapping(value = "/greetings", method = RequestMethod.GET, params = { "msg" })
//    public String getGreetingWithHeader(@RequestHeader(name = "msg", required = false, defaultValue = "Hello") String msg) {
//        return "Welcome to RESTful services training :) - " + msg;
//    }

//    @RequestMapping(value = "/greetings", method = RequestMethod.POST, consumes = { "application/json"}, produces = { "application/json"})
//    public Greetings postGreetingObject(@RequestBody Greetings greeting) {
//        greeting.setMessage(greeting.getMessage() + " - POST object mapping example");
//        return greeting;
//    }
//
//    @RequestMapping(value = "/greetings", method = RequestMethod.POST, consumes = { "application/xml"}, produces = { "application/xml"})
//    public Greetings postGreetingXml(@RequestBody Greetings greeting) {
//        greeting.setMessage(greeting.getMessage() + " - POST object mapping example");
//        return greeting;
//    }


    // consume and produce in both json & xml
    // content negotiation( to determine the media type/ format of req or res  for interaction bet client and server
    // add Accept = applicaiton/xml or json to specify the produces (which format the server shud produce the result)
//    @RequestMapping(value = "/greetings", method = RequestMethod.POST, consumes = { "application/xml", "application/json"},
//            produces = { "application/xml", "application/json"})
//    public Greetings postGreetingXmlAndJson(@RequestBody Greetings greeting) {
//        greeting.setMessage(greeting.getMessage() + " - POST object mapping example");
//        return greeting;
//    }

    // convert collection to json
//    @RequestMapping(value = "/greetings/collection", method = RequestMethod.POST)
//    public Map<String, Object> postGreetingMap(@RequestBody Map<String, Object> greeting) {
//        logger.info("Greetings collection: " + greeting);
//        greeting.put("updated", "POST collection mapping example");
//        return greeting;
//    }

//    // get collection data to json with just object no collection
//    @RequestMapping(value = "/greetings/collection", method = RequestMethod.POST)
//    public Greetings postGreeting(@RequestBody Greetings greeting) {
//        logger.info("Greetings collection with object : " + greeting);
//        return greeting;
//    }



//    @RequestMapping(value = "/greetings", method = RequestMethod.POST, consumes = { "text/plain" })
//    public @ResponseBody String postGreetingMap(@RequestBody String greeting) {
//        return "Welcome to RESTful services training :) - " + greeting;
//    }
//
//    @RequestMapping(value = "/greetings", method = RequestMethod.POST, consumes = { "application/xml"}, produces = { "application/json" })
//    public Greetings postGreetingObject(@RequestBody Greetings greeting) {
//        greeting.setMessage(greeting.getMessage() + " - POST object mapping example");
//        return greeting;
//    }
//
//    @RequestMapping(value = "/greetings/collection", method = RequestMethod.POST)
//    public Map<String, String> postGreetingMap(@RequestBody Map<String, String> greeting) {
//        greeting.put("updated", "POST collection mapping example");
//        return greeting;
//    }
//
//    @RequestMapping(value = "/greetings/entity", method = RequestMethod.POST)
//    public ResponseEntity<Greetings> postGreetingEntity(@RequestBody Greetings greeting) {
//        greeting.setMessage(greeting.getMessage() + " Updated");
//
//        HttpHeaders resHeaders = new HttpHeaders();
//        resHeaders.add("message", "POST entity mapping example");
//
//        ResponseEntity<Greetings> res = new ResponseEntity<Greetings>(greeting, resHeaders, HttpStatus.OK);
//        return res;
//    }
//
    @RequestMapping(value = "/greetings/{messageId}", method = RequestMethod.PUT)
    public Map<String, String> putGreetingWithPathVariable(@RequestBody Map<String, String> greeting,
                                                           @PathVariable String messageId, @MatrixVariable(required = false) String msgType) {
        greeting.put(messageId, "PUT collection mapping with path variable example");
        if (msgType != null) {
            greeting.put(msgType, "Testing");
        }
        return greeting;
    }
//
//    @RequestMapping(value = "/greetings/matrix/{messageId}", method = RequestMethod.PUT)
//    public Greetings putGreetingWithMatrixParameters(@RequestBody Greetings greeting,
//                                                     @MatrixVariable("messageId") String messageId) {
//        return greeting;
//    }


}
