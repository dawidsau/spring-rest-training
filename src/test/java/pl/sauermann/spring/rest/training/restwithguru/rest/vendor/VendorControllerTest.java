package pl.sauermann.spring.rest.training.restwithguru.rest.vendor;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sauermann.spring.rest.training.restwithguru.rest.vendor.VendorController.BASE_URL;

//TODO IMPLEMENTATION OF 3 TESTS

public class VendorControllerTest {


    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }


    @Test
    public void shouldReturnVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/1");

        when(vendorService.getVendorById(1L)).thenReturn(vendorDTO);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", equalTo("Some text")));

    }

    @Test
    public void shouldRemoveVendorById() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendorById(1L);
    }

    @Test
    public void shouldInsertVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/1");

        when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendorUrl",equalTo(BASE_URL + "/1")));


    }

    @Test
    public void shouldReturnAllVendors() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/1");
        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO.setId(2L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/2");

        when(vendorService.getAllVendors()).thenReturn(Lists.newArrayList(vendorDTO, vendorDTO2));

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    public void shouldReplaceVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/1");

        when(vendorService.replaceVendor(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(put(BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorUrl",equalTo(BASE_URL + "/1")));

    }

    @Test
    public void shouldUpdateVendorById() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setText("Some text");
        vendorDTO.setVendorUrl(BASE_URL + "/1");

        when(vendorService.updateVendor(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO);

        mockMvc.perform(patch(BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorUrl",equalTo(BASE_URL + "/1")));

    }
}