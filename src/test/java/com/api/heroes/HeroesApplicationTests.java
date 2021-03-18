package com.api.heroes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

	private static final String HEROES = "/heroes";
	private static final String ONE = "/1";
	private static final String TWO = "/2";
	private static final String TWENTY_FIVE = "/25";
	private static final String NAME = "?name=Spider";
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
	 *                   Test find a hero by id
	 */
	@Test
	@Order(1)
	public void findHeroById() throws Exception {
		String res = "{\"id\":1,\"name\":\"Spiderman\",\"power\":50.0}";
		mockMvc.perform(get(HEROES + ONE)).andExpect(status().isOk()).andExpect(content().string(res));
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find a hero by non existing id
	 */
	@Test
	@Order(2)
	public void findHeroByNonExistingId() throws Exception {
		mockMvc.perform(get(HEROES + TWENTY_FIVE)).andExpect(status().isNotFound());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test that multiple users are created
	 */
	@Test
	@Order(3)
	public void createHeroes() throws Exception {
		String json = "{\"name\": \"Rogue\", \"power\": 80.0}";
		mockMvc.perform(post(HEROES).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"name\": \"Deadpool\", \"power\": 90.0}";
		mockMvc.perform(post(HEROES).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"name\": \"Wolverine\", \"power\": 70.0}";
		mockMvc.perform(post(HEROES).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"name\": \"Batman\", \"power\": 50.0}";
		mockMvc.perform(post(HEROES).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		json = "{\"name\": \"Superman\", \"power\": 100.0}";
		mockMvc.perform(post(HEROES).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test deleting a hero
	 */
	@Test
	@Order(4)
	public void deleteHero() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(HEROES + ONE)).andExpect(status().isOk());
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find all heroes
	 */
	@Test
	@Order(5)
	public void getAllHeroes() throws Exception {
		String res = "{\"items\":[" + "{\"id\":2,\"name\":\"Scarlet Spider\",\"power\":70.0},"
				+ "{\"id\":3,\"name\":\"Venom\",\"power\":70.0}," + "{\"id\":4,\"name\":\"Carnage\",\"power\":80.0},"
				+ "{\"id\":5,\"name\":\"Black Cat\",\"power\":30.0}," + "{\"id\":6,\"name\":\"Rogue\",\"power\":80.0},"
				+ "{\"id\":7,\"name\":\"Deadpool\",\"power\":90.0},"
				+ "{\"id\":8,\"name\":\"Wolverine\",\"power\":70.0}," + "{\"id\":9,\"name\":\"Batman\",\"power\":50.0},"
				+ "{\"id\":10,\"name\":\"Superman\",\"power\":100.0}]}";
		mockMvc.perform(get(HEROES)).andExpect(status().isOk()).andExpect(content().string(res));
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   Test find heroes by name
	 */
	@Test
	@Order(6)
	public void getHeroesByName() throws Exception {
		String res = "{\"items\":[" + "{\"id\":2,\"name\":\"Scarlet Spider\",\"power\":70.0}]}";
		mockMvc.perform(get(HEROES + NAME)).andExpect(status().isOk()).andExpect(content().string(res));
	}

	/**
	 *
	 * @throws Exception
	 *
	 *                   It tests updating a hero
	 */
	@Test
	@Order(7)
	public void updateHero() throws Exception {
		String json = "{\"name\":\"Scarlet Spiderman\",\"power\":60.0}";
		mockMvc.perform(put(HEROES + TWO).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
}
