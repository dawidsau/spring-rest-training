package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    private VendorMapper vendorMapper = VendorMapper.INSTANCE;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository,vendorMapper);
    }

    @Test
    public void shouldReturnVendorById() {
        //given
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setText("Some Vendor");
        //when
        when(vendorRepository.findById(id)).thenReturn(java.util.Optional.of(vendor));
        VendorDTO vendorById = vendorService.getVendorById(id);
        //then
        assertEquals("Some Vendor", vendorById.getText());
        assertEquals(VendorController.BASE_URL +"/1", vendorById.getVendorUrl());
    }

    @Test
    public void shouldReturnAllVendors() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setText("First");
        Vendor vendor2 = new Vendor();
        vendor.setId(1L);
        vendor.setText("Second");
        Vendor vendor3 = new Vendor();
        vendor.setId(1L);
        vendor.setText("Third");

        List<Vendor> vendors = Lists.newArrayList(vendor,vendor2,vendor3);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> result = vendorService.getAllVendors();

        assertEquals(vendors.size(),result.size());
    }

    @Test
    public void shouldInsertVendor() {
        VendorDTO vendor = new VendorDTO();
        vendor.setText("First");

        Vendor vendorReturned = new Vendor();
        vendorReturned.setId(1L);
        vendorReturned.setText("First");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorReturned);

        VendorDTO result = vendorService.createNewVendor(vendor);

        assertEquals(vendorReturned.getText(),result.getText());
        assertEquals(vendorReturned.getId(),result.getId());
    }

    @Test
    public void shouldRemoveVendorById() {
        Long id = 1L;

        vendorService.deleteVendorById(id);

        verify(vendorRepository,times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdateVendorById() {
        Long id = 1L;
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setText("Old text");
        Vendor vendor = new Vendor();
        vendor.setText("Old text");
        Vendor vendorReturned = new Vendor();
        vendorReturned.setText("New Name");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorReturned);

        VendorDTO result = vendorService.updateVendor(id, vendorDTO);

        assertEquals(vendorReturned.getText(),result.getText());
    }

    @Test
    public void shouldReplaceVendorById() {
        Long id = 1L;
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setText("Old text");
        Vendor vendor = new Vendor();
        vendor.setText("Old text");
        Vendor vendorReturned = new Vendor();
        vendorReturned.setText("New Name");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorReturned);

        VendorDTO result = vendorService.replaceVendor(id, vendorDTO);

        assertEquals(vendorReturned.getText(),result.getText());
    }
}