package com.public_library.book;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PublicLibraryBookTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateBook() {
	}

	@Test
	public void testReadBook() {
	}

	@Test
	public void testUpdateBook() {
	}

	@Test
	public void testDeleteBook() {
	}

	@Test
	public void testLoadAllBooks() {
	}

}
