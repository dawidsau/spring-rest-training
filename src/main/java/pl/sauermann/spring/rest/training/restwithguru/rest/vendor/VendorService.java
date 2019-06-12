package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import java.util.List;

public interface VendorService {

    VendorDTO getVendorById(Long id);

    List<VendorDTO> getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    void deleteVendorById(Long id);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO replaceVendor(Long id, VendorDTO vendorDTO);
}
