package com.vessel.api.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

		
	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    
	final String BASE_URL = "http://localhost:8080/";

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
			.andExpect(jsonPath("$.data.timeZoneName", is("Europe/Paris")))			
            .andExpect(jsonPath("$.data.offSetText", is("UTC+01:00")));
            
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
