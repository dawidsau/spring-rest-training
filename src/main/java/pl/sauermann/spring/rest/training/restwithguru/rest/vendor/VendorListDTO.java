package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VendorListDTO {

    List<VendorDTO> vendors;
}
