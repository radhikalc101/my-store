package org.launchcode.boot.store.rest;

import org.launchcode.boot.store.repositories.ItemRepository;
import org.launchcode.boot.store.models.Item;
import org.launchcode.boot.store.repositories.StoreInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "store/rest" )
public class ItemRestController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreInfoRepository storeRepository;

    @GetMapping(value = "item/publish/{id}")
    public int publishItem(Model model, @PathVariable int id){
        System.out.println(id);
        Item item = itemRepository.findById(id).get();
        item.setPublished(!item.isPublished());
        itemRepository.save(item);
        return id;
    }

    @PostMapping(value = "item",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveItem(@RequestBody Item item){
        itemRepository.save(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "item/{itemId}")
    public Item findItemById(@PathVariable int itemId){
        return itemRepository.findById(itemId).get();
    }

    @DeleteMapping(value = "item/delete/{itemId}")
    public ResponseEntity<String> deleteItemById(@PathVariable int itemId){
        itemRepository.deleteById(itemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "items/storeId/{storeId}")
    public Iterable<Item> findItemsByStoreId(@PathVariable int storeId){
        return itemRepository.findByStoreInfo(storeRepository.findById(storeId).get());
    }
}
