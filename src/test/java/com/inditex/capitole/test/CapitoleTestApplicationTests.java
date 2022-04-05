package com.inditex.capitole.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.capitole.test.dto.ResponseGenericDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CapitoleTestApplicationTests {

	private static final Logger log = LogManager.getLogger(CapitoleTestApplicationTests.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@ParameterizedTest
	@ValueSource(strings = {"2020-06-14-10.00.00", "2020-06-14-16.00.00", "2020-06-14-21.00.00", "2020-06-15-10.00.00", "2020-06-16-21.00.00"})
	void isOk_ShouldReturnValuesForDifferentDates(String date) throws Exception
	{
		String url = String.format("/api/v1/price/?date=%s&productId=35455&brandId=1", date);
		ResultActions perform = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

		var bodyJson = perform.andReturn().getResponse().getContentAsString();
		var body = mapper.readValue(bodyJson, ResponseGenericDto.class);
		log.info("Response Test: {}", body);
		assertAll(() ->
		{
			perform.andExpect(status().isOk());

			assertEquals(0, body.getCode());
			assertNotNull(body);
		});
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"  "})
	void isBlankOrNull_ShouldReturn4xxErrorForNullInputs(String date) throws Exception
	{
		String url = String.format("/api/v1/price/?date=%s&productId=35455&brandId=1", date);
		mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	void main()
	{
		assertDoesNotThrow(() -> CapitoleTestApplication.main(new String[] {}));
	}

}
