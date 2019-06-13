package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import org.springframework.stereotype.Service;
import pl.sauermann.spring.rest.training.restwithguru.rest.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;

    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(
                vendorRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new));
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + id);
        return vendorDTO;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(v -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(v);
                    vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + v.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(
                vendorMapper.vendorDTOToVendor(vendorDTO)
        );
        VendorDTO result = vendorMapper.vendorToVendorDTO(savedVendor);
        result.setVendorUrl(VendorController.BASE_URL + "/" + result.getId());
        return result;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        return getVendorDTO(id, vendorDTO);
    }

    @Override
    public VendorDTO replaceVendor(Long id, VendorDTO vendorDTO) {
        return getVendorDTO(id, vendorDTO);
    }

    private VendorDTO getVendorDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        vendor.setText(vendorDTO.getText());
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO result = vendorMapper.vendorToVendorDTO(savedVendor);
        result.setVendorUrl(VendorController.BASE_URL + "/" + result.getId());
        return result;
    }
}
