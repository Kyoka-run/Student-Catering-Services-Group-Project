package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Item;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.ItemRepository;

import java.util.*;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public List<Item> getItems(
            @RequestParam(required = false) Integer sectionid,
            @RequestParam(required = false) Item.ItemStatus status) {

        // Check if both sectionId and status are provided
        if (sectionid != null && status != null) {
            return itemRepository.findBySectionIdAndStatus(sectionid, status);
        }
        // Check if only sectionId is provided
        if (sectionid != null) {
            return itemRepository.findBySectionId(sectionid);
        }
        // Check if only status is provided
        if (status != null) {
            return itemRepository.findByStatus(status);
        }
        // If no query parameter is provided, return all items
        return itemRepository.findAll();
    }
    

    @GetMapping("/item")
    public Optional<Item> getItem(@RequestParam int id) {
        return itemRepository.findById(id);
    }

    @GetMapping("/items_search")
    public List<Item> searchItems(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return itemRepository.searchItems(keyword);
        } else {
            return itemRepository.findAll();
        }
    }

    @PostMapping("/item_update")
    public ResponseEntity<Map<String, Object>> updateItem(Authentication auth, @RequestBody Item item) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (item.getId() == 0) {
                // Create a new record
                Item savedItem = itemRepository.save(item);
                response.put("success", 1);
                response.put("id", savedItem.getId());
            } else {
                // Update an existing record
                Item existingItem = itemRepository.findById(item.getId()).orElse(null);
                if (existingItem != null) {
                    existingItem.setName(item.getName());
                    existingItem.setSectionId(item.getSectionId());
                    existingItem.setQuantity(item.getQuantity());
                    existingItem.setValue(item.getValue());
                    existingItem.setStatus(item.getStatus());
                    itemRepository.save(existingItem);
                    response.put("success", 1);
                    response.put("id", existingItem.getId());
                } else {
                    response.put("success", 0);
                    response.put("error", "Item with id " + item.getId() + " not found.");
                }
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/item_delete")
    public ResponseEntity<Map<String, Object>> deleteItem(Authentication auth, @RequestParam int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            itemRepository.deleteById(id);
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
