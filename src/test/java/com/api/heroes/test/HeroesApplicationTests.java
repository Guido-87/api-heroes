package com.api.heroes.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class HeroesApplicationTests {

	private static final String USERS = "/users";
	private static final String TWO = "/2";
	private static final String TWENTY_FIVE = "/25";
	private static final String LOANS = "/loans?page=0&size=5";
	private static final String USER_ID = "&user_id=4";
	@ClassRule
	public static final SpringClassRule springClassRule = new SpringClassRule();
	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();
	@Autowired
	private MockMvc mockMvc;

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find a user by id
	 */
	@Test
	@Order(1)
	public void findUserById() throws Exception {
		String res = "{\"id\":2,\"email\":\"ma@roma.com\",\"firstName\":\"Marc\",\"lastName\":\"Anthony\","
				+ "\"loans\":[{\"id\":2,\"total\":2500,\"userId\":2}]}";
		mockMvc.perform(get(USERS + TWO)).andExpect(status().isOk()).andExpect(content().string(res));
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find a user by non existing id
	 */
	@Test
	@Order(2)
	public void findUserByNonExistingId() throws Exception {
		mockMvc.perform(get(USERS + TWENTY_FIVE)).andExpect(status().isNotFound());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test that multiple users are created
	 */
	@Test
	@Order(3)
	public void createUsers() throws Exception {
		String json = "{\"email\": \"cf@roma.com\", \"firstName\": \"Cleopatra\", \"lastName\": \"Filopator\"}";
		mockMvc.perform(post(USERS).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"email\": \"mb@roma.com\", \"firstName\": \"Marco\", \"lastName\": \"Bruto\"}";
		mockMvc.perform(post(USERS).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"email\": \"pm@roma.com\", \"firstName\": \"Pompeyo\", \"lastName\": \"Magno\"}";
		mockMvc.perform(post(USERS).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"email\": \"ma@roma.com\", \"firstName\": \"Marco\", \"lastName\": \"Agripa\"}";
		mockMvc.perform(post(USERS).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"email\": \"cm@roma.com\", \"firstName\": \"Cayo\", \"lastName\": \"Mecenas\"}";
		mockMvc.perform(post(USERS).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test deleting a user
	 */
	@Test
	@Order(4)
	public void deleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(USERS + TWO)).andExpect(status().isOk());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find loans by page and size
	 */
	@Test
	@Order(5)
	public void getLoansByPageAndSize() throws Exception {
		String res = "{\"items\":[{\"id\":1,\"total\":500,\"userId\":1},{\"id\":3,\"total\":3000,\"userId\":3},"
				+ "{\"id\":4,\"total\":1000,\"userId\":4},{\"id\":5,\"total\":1500,\"userId\":4},{\"id\":6,"
				+ "\"total\":1000,\"userId\":5}],\"paging\":{\"page\":0,\"size\":5,\"total\":7000}}";
		mockMvc.perform(get(LOANS)).andExpect(status().isOk()).andExpect(content().string(res));
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find loans by page, size and user_id
	 */
	@Test
	@Order(6)
	public void getLoansByPageSizeAndUserId() throws Exception {
		String res = "{\"items\":[{\"id\":4,\"total\":1000,\"userId\":4},{\"id\":5,\"total\":1500,\"userId\":4}],"
				+ "\"paging\":{\"page\":0,\"size\":5,\"total\":2500}}";
		mockMvc.perform(get(LOANS + USER_ID)).andExpect(status().isOk()).andExpect(content().string(res));
	}
}
