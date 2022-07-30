package com.devsuperior.dscatalog.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;

//Carrega o contexto, porém somente da camada web (teste de unidade: controlador)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean //Usar quando a classe de teste carrega o contexto da aplicação e precisa mockar algum bean do sistema.
	private ProductService service;
	
	private long existingId;
	private long nonExistingId;
	
	private ProductDTO productDTO;
	private PageImpl<ProductDTO> page;
	
	@BeforeEach 
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		
		productDTO = Factory.createProductDTO();		
		page = new PageImpl<>(List.of(productDTO));
		
		when(service.findAllPaged((Pageable)any())).thenReturn(page);
		
		when(service.findById(existingId)).thenReturn(productDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
	}
	
	@Test
	public void findAllPagedShouldReturnPage() throws Exception {
		/*ResultActions result = mockMvc.perform(get("/products")
				.accept(MediaType.APPLICATION_JSON)); //
		result.andExpect(status().isOk());*/
		mockMvc.perform(get("/products")
				.accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
	}
	
	@Test
	public void findByIdShouldReturnProductWhenIdExists() throws Exception {
		mockMvc.perform(get("/products/{id}", existingId)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.imgUrl").exists())
		.andExpect(jsonPath("$.moment").exists());
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		mockMvc.perform(get("/products/{id}", nonExistingId)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
}
