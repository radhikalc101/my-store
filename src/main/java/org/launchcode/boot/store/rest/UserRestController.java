package org.launchcode.boot.store.rest;

import org.launchcode.boot.store.models.Address;
import org.launchcode.boot.store.models.Brand;
import org.launchcode.boot.store.models.OwnerAccountInfo;
import org.launchcode.boot.store.repositories.AddressRepository;
import org.launchcode.boot.store.repositories.OwnerAccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "store/rest")
@Deprecated
public class UserRestController {

//    @Autowired
//    private OwnerAccountInfoRepository ownerAccountInfoRepository;
//
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @GetMapping(value = "owner/{email}")
//    public OwnerAccountInfo findOwnerAccountByEmail(@PathVariable String email){
//        return ownerAccountInfoRepository.findByEmail(email);
//    }

//    @PostMapping(value = "address",consumes = "application/json", produces = "application/json")
//    public ResponseEntity<String> saveAddress(@RequestBody Address address) {
//        addressRepository.save(address);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping(value = "owner",consumes = "application/json", produces = "application/json")
//    public ResponseEntity<String> saveOwner(@RequestBody OwnerAccountInfo ownerAccountInfo) {
//        ownerAccountInfoRepository.save(ownerAccountInfo);
//        return new ResponseEntity(HttpStatus.OK);
//    }

}
