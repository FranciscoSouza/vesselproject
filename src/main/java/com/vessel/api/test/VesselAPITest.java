package com.vessel.api.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vessel.api.Boot;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boot.class)
@WebAppConfiguration
public class VesselAPITest {

	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private WebApplicationContext wac;
    
	final String BASE_URL = "http://localhost:8080/";

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Test Success that bring a valid position
     * @throws Exception
     */
	@Test
	public void locationFound() throws Exception {
		
		mockMvc.perform(get(BASE_URL + "/timeForLatLng?lat=53.5&lng=10.4"))
			.andExpect(jsonPath("$.status", is("Done")))
			.andExpect(jsonPath("$.data.timeZoneName", is("Mauritius, Oman, Seychelles, United Arab Emirates")))			
            .andExpect(jsonPath("$.data.offSetText", is("UTC+04:00")));
            
	}
	
	/**
     * Test Not Found location position
     * @throws Exception
     */
	@Test
	public void locationNotFound() throws Exception {
		mockMvc.perform(get(BASE_URL + "/timeForLatLng?lat=1055&lng=115555"))
			.andExpect(jsonPath("$.status", is("Not Found")));
			
	}
	
	/**
     * Test Failed that bring a Invalid position
     * @throws Exception
     */
	@Test
	public void locationError() throws Exception {
		mockMvc.perform(get(BASE_URL + "/timeForLatLng?lat=AAAA&lng=BBBB"))
			.andExpect(jsonPath("$.status", is("Fail")));
			
	}

}
