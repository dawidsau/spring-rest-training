package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/vendors";
    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO showVendorList(){
       return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable String id){
        return vendorService.getVendorById(new Long(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeVendorById(@PathVariable String id){
        vendorService.deleteVendorById(new Long(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO replaceVendorById(@PathVariable String id, VendorDTO vendorDTO){
        return vendorService.replaceVendor(new Long(id), vendorDTO);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendorById(@PathVariable String id, VendorDTO vendorDTO){
        return vendorService.updateVendor(new Long(id), vendorDTO);
    }

}
