package space.aow.java.currencies.currencies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrenciesApplicationTests {

	@Autowired
	private CurrenciesApplication app;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(app).isNotNull();
	}

	@Test
	public void helloWorld() throws Exception {
		testHello("World");
	}

	@Test
	public void helloJava() throws Exception {
		testHello("Java");
	}

	@Test
	public void hello() throws Exception {
		testHello("");
	}

	private void testHello(String name) throws Exception {
		mockMvc.perform(getHelloRequest(name))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, " + (name.isEmpty() ? "World" : name) + "!")));
	}

	private MockHttpServletRequestBuilder getHelloRequest(String name) throws Exception {
		MockHttpServletRequestBuilder req = get("/hello");
		if (name.isEmpty()) {
			return req;
		}
		return req.param("name", name);
	}
}
