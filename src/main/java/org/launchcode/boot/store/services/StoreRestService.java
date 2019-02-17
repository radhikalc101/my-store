package org.launchcode.boot.store.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.boot.store.models.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class StoreRestService {
    static final String HOST = "http://localhost:8080";
//    static final String NP_HOST = "https://mystoreapi-np.ag.amazon.com";
//    static final String PROD_HOST = "https://mystoreapi.ag.amazon.com";
    static final String BASE_URL = HOST+"/store/rest";
    static final String BRANDS_URL = BASE_URL+"/brands";
    static final String BRAND_URL = BASE_URL+"/brand";
    static final String CATEGORIES_URL = BASE_URL+"/categories";
    static final String CATEGORY_URL = BASE_URL+"/category";
    static final String ITEM_URL = BASE_URL+"/item";
    static final String ITEMS_URL = BASE_URL+"/items";
    static final String IMAGE_URL = BASE_URL+"/image";
    static final String OWNER_ACCOUNT_URL = BASE_URL+"/owner";
    static final String STORE_INFO_URL = BASE_URL+"/info";
    static final String ADDRESS_URL = BASE_URL+"/address";

    private RestTemplate template = new RestTemplate();

    public Iterable<Brand> getAllBrands(){
        return template.getForObject(BRANDS_URL, Iterable.class);
    }

    public void saveBrand(Brand brand) {
        template.postForObject(BRAND_URL, brand, String.class);
    }

    public Brand findBrandById(int brandId) {
        return template.getForObject(BRAND_URL+"/"+brandId, Brand.class);
    }

    public Iterable<Category> getAllCategories(){
        return template.getForObject(CATEGORIES_URL, Iterable.class);
    }

    public void saveCategory(Category category) {
        template.postForObject(CATEGORY_URL, category, String.class);
    }

    public Category findCategoryById(int categoryId) {
        return template.getForObject(CATEGORY_URL+"/"+categoryId, Category.class);
    }

    public void saveItem(Item item) {
        template.postForObject(ITEM_URL, item, String.class);
    }

    public Item findItemById(int itemId) {
        return template.getForObject(ITEM_URL+"/"+itemId, Item.class);
    }
    public List<Item> findItemsByStore(int storeId) {
        Iterable<LinkedHashMap> response =  template.getForObject(ITEMS_URL+"/storeId/"+storeId, Iterable.class);
        List<Item> items = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (LinkedHashMap map : response) {
                byte[] json = mapper.writeValueAsBytes(map);
                items.add(mapper.readValue(json, Item.class));

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    public void deleteItemById(int itemId) {
        template.delete(ITEM_URL+"/delete/"+itemId);
    }

    public ImageFile getImageFile(int fileId) {
        return template.getForObject(IMAGE_URL+"/"+fileId, ImageFile.class);
    }

    public OwnerAccountInfo findOwnerAccountByEmail(String email) {
        return template.getForObject(OWNER_ACCOUNT_URL+"/"+email, OwnerAccountInfo.class);
    }

    public StoreInfo findStoreInfoByOwnerAccount(int ownerAccountId) {
        return template.getForObject(STORE_INFO_URL+"/owner/"+ownerAccountId, StoreInfo.class);
    }

    public void saveAddress(Address address) {
        template.postForObject(ADDRESS_URL, address, String.class);
    }

    public void saveOwner(OwnerAccountInfo ownerAccountInfo) {
        template.postForObject(OWNER_ACCOUNT_URL, ownerAccountInfo, String.class);
    }

    public void saveStoreInfo(StoreInfo storeInfo) {
        template.postForObject(STORE_INFO_URL, storeInfo, String.class);
    }
}
