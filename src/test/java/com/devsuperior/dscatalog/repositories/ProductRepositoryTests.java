package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;



//Carrega SOMENTE os componentes relacionados ao Spring Data JPA. 
//Cada teste é transacional e dá rollback ao final. (teste de unidade: repository)
@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repositoty; 
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
	}
	
	@Test
	public void deleteShouldDeleteObjectsWhenIdExists() {
		repositoty.deleteById(existingId);
		Optional<Product> result = repositoty.findById(existingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void deleteSholdThrowEmptyResultDataAccessExceptionWhenIdNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
			repositoty.deleteById(nonExistingId);
		});
	}
	
}
