package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.ItemDTO;
import com.agnes.challenge.Entities.Item;
import com.agnes.challenge.Services.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    final
    ItemService itemService;

    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<List<ItemDTO>> getAllItems() {

        List<ItemDTO> allItemsDTO = itemService.getAllItems();
        return new ResponseEntity<>(allItemsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/items/id/{itemId}", produces = "application/json")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Integer itemId) {

        Item itemById = itemService.findByItemId(itemId);
        if (itemById != null) {
            ItemDTO itemDTO = new ItemDTO(itemById);
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item with the id " + itemId + " was found.");
        }
    }

    @PostMapping(value = "items", consumes = "application/json")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {

        Item item = itemService.saveNewItem(itemDTO);
        return new ResponseEntity<>(new ItemDTO(item), HttpStatus.OK);
    }

    @PutMapping(value = "/items/{itemId}", consumes = "application/json")
    public ResponseEntity<ItemDTO> updateProjectById(@PathVariable(name = "itemId") Integer itemId, @RequestBody ItemDTO itemDTO) {

        Item p = itemService.findByItemId(itemId);
        if (p != null) {
            Item item = itemService.updateItemById(itemId, itemDTO);
            return new ResponseEntity<>(new ItemDTO(item), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find item with the id: " + itemId);
        }
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteProjectById(@PathVariable Integer itemId) {

        if (itemService.isItemIdPresent(itemId)) {
            itemService.deleteItemById(itemId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item with the id " + itemId + " was found.");
        }
    }
}
