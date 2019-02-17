package org.launchcode.boot.store.rest;

import org.launchcode.boot.store.models.Brand;
import org.launchcode.boot.store.models.Category;
import org.launchcode.boot.store.models.OwnerAccountInfo;
import org.launchcode.boot.store.models.StoreInfo;
import org.launchcode.boot.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "store/rest")
public class StoreRestController {

    @Autowired
    private StoreInfoRepository storeInfoRepository;

    @Autowired
    private OwnerAccountInfoRepository ownerAccountInfoRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "brands")
    public Iterable<Brand> getBrands(){
        return brandRepository.findAll();
    }

    @GetMapping(value = "brand/{brandId}")
    public Brand findBrandById(@PathVariable int brandId){
        return brandRepository.findById(brandId).get();
    }

    @PostMapping(value = "brand",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveBrand(@RequestBody Brand brandName){
        brandRepository.save(brandName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "categories")
    public Iterable<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping(value = "category",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveCategory(@RequestBody Category category){
        categoryRepository.save(category);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "category/{categoryId}")
    public Category findCategoryById(@PathVariable int categoryId){
        return categoryRepository.findById(categoryId).get();
    }

    @GetMapping(value = "info/owner/{ownerAccountId}")
    public StoreInfo findStoreByOwnerAccountId(@PathVariable int ownerAccountId){
        return storeInfoRepository.findByOwnerAccountInfo(ownerAccountInfoRepository.findById(ownerAccountId).get());
    }

    @PostMapping(value = "info",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveStoreInfo(@RequestBody StoreInfo storeInfo) {
        addressRepository.save(storeInfo.getStoreAddress());
        addressRepository.save(storeInfo.getOwnerAccountInfo().getOwnerAddress());
        ownerAccountInfoRepository.save(storeInfo.getOwnerAccountInfo());
        storeInfoRepository.save(storeInfo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "owner/{email}")
    public OwnerAccountInfo findOwnerAccountByEmail(@PathVariable String email){
        return ownerAccountInfoRepository.findByEmail(email);
    }
}
