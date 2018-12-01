package com.succexa.shoppingbackend.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class })
@WebAppConfiguration
public class SecurityConfigTests {
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;


	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity())
				.defaultRequest(get("/").accept(MediaType.TEXT_HTML)).build();
	}

	@Test
	public void requestProtectedResourceRequiresAuthentication() throws Exception {
		mvc.perform(get("/")).andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	public void loginSuccess() throws Exception {
		mvc.perform(formLogin()).andExpect(redirectedUrl("/"));
	}

	@Test
	public void loginFailure() throws Exception {
		mvc.perform(formLogin().password("invalid")).andExpect(
				redirectedUrl("/login?error"));
	}

	@Test
	@WithMockUser
	public void requestProtectedResourceWithUser() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void composeMessageRequiresCsrfToken() throws Exception {
		MockHttpServletRequestBuilder composeMessage = post("/").param("summary",
				"New Message").param("text", "This is a new message");

		mvc.perform(composeMessage).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser
	public void composeMessage() throws Exception {
		MockHttpServletRequestBuilder composeMessage = post("/")
				.param("summary", "New Message").param("text", "This is a new message")
				.with(csrf());

		mvc.perform(composeMessage).andExpect(redirectedUrlPattern("/*"));
	}

	@Test
	@WithMockUser
	public void logoutRequiresCsrfToken() throws Exception {
		mvc.perform(post("/logout")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser
	public void logoutSuccess() throws Exception {
		mvc.perform(logout()).andExpect(redirectedUrl("/login?logout"))
				.andExpect(unauthenticated());
	}
}
*/