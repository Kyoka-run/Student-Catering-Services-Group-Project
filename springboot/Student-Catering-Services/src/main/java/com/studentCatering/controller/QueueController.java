package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Queue;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.QueueRepository;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @GetMapping("/queues")
    public List<Queue> getQueues(
    		@RequestParam String start,
    		@RequestParam String end,
            @RequestParam(required = false) Integer sectionid,
            @RequestParam(required = false) Integer userid) {

    	Timestamp startTime = Timestamp.valueOf(start);
    	Timestamp endTime = Timestamp.valueOf(end);
    	
        // Check if both sectionId and userId are provided
        if (sectionid != null && userid != null) {
            return queueRepository.findBySectionIdAndUserIdAndOrderDateBetween(sectionid, userid, startTime, endTime);
        }
        // Check if only sectionId is provided
        if (sectionid != null) {
            return queueRepository.findBySectionIdAndOrderDateBetween(sectionid, startTime, endTime);
        }
        // Check if only userId is provided
        if (userid != null) {
            return queueRepository.findByUserIdAndOrderDateBetween(userid, startTime, endTime);
        }
        // If no query parameter is provided, return all queue records
        return queueRepository.findByOrderDateBetween(startTime, endTime);
    }
    

    @GetMapping("/queue")
    public Optional<Queue> getQueue(@RequestParam int id) {
        return queueRepository.findById(id);
    }
    
    @PostMapping("/queue_add")
    public ResponseEntity<Map<String, Object>> addQueue(Authentication auth, @RequestBody Queue queue) {
        Map<String, Object> response = new HashMap<>();
        try {
        	queue.setId(0);
            Queue savedQueue = queueRepository.save(queue);
            response.put("success", 1);
            response.put("id", savedQueue.getId());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/queue_delete")
    public ResponseEntity<Map<String, Object>> deleteQueue(Authentication auth, @RequestParam int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            queueRepository.deleteById(id);
            response.put("success", 1);
            response.put("id", id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
