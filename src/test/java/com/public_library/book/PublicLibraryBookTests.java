package com.public_library.book;

import com.public_library.dto.BookDTO;
import com.public_library.model.Book;
import com.public_library.request.LoginRequest;
import com.public_library.request.SignupRequest;
import com.public_library.response.JwtResponse;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PublicLibraryBookTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateBook() {
		String token = getJwtTokenFromResponseResponseEntity();

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		BookDTO book = new BookDTO(-1L,"Test Title Book for Creation969", "Test Book Author", "Test Genre");

		HttpEntity<BookDTO> request = new HttpEntity<>(book, headers);

		ResponseEntity<BookDTO> bookCreationResult = this.restTemplate.exchange("http://localhost:8000/api/v1/book", HttpMethod.POST, request, BookDTO.class);
		Assertions.assertTrue(bookCreationResult.getBody().identifier() > -1L);
	}

	@Test
	public void testReadBook() {
		String token = getJwtTokenFromResponseResponseEntity();

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		HttpEntity<BookDTO> request = new HttpEntity<>(headers);

		String bookTitleToSearch = "Dune";
		ResponseEntity<BookDTO> bookCreationResult = this.restTemplate.exchange("http://localhost:8000/api/v1/book/" + bookTitleToSearch, HttpMethod.GET, request, BookDTO.class);
		Assertions.assertEquals(bookCreationResult.getBody().title() , bookTitleToSearch);
	}

	@Test
	public void testUpdateBook() {
		String token = getJwtTokenFromResponseResponseEntity();

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		BookDTO book = new BookDTO(-1L,"Good Omens", "Test Book Author Good Omens", "TestG Good Omens");

		HttpEntity<BookDTO> request = new HttpEntity<>(book, headers);

		ResponseEntity<BookDTO> bookCreationResult = this.restTemplate.exchange("http://localhost:8000/api/v1/book", HttpMethod.PUT, request, BookDTO.class);
		Assertions.assertEquals(bookCreationResult.getBody().author() , book.author());
		Assertions.assertEquals(bookCreationResult.getBody().genre() , book.genre());
	}

	@Test
	public void testDeleteBook() {
		String token = getJwtTokenFromResponseResponseEntity();

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		HttpEntity<BookDTO> request = new HttpEntity<>(headers);

		String bookTitleToDelete = "Dune";
		this.restTemplate.exchange("http://localhost:8000/api/v1/book/" + bookTitleToDelete, HttpMethod.DELETE, request, BookDTO.class);
	}

	private String getJwtTokenFromResponseResponseEntity() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("testUser123");
		loginRequest.setPassword("password123");

		ResponseEntity<JwtResponse> signinResult = this.restTemplate.postForEntity("http://localhost:8000/api/v1/auth/signin", loginRequest, JwtResponse.class);
		return signinResult.getBody().getAccessToken();
	}

	@Test
	public void testLoadAllBooks() {
	}

}
