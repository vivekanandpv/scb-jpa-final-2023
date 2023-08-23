package com.example.jpademo.apis;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpademo.services.CustomerService;
import com.example.jpademo.viewmodels.CustomerCreateViewModel;
import com.example.jpademo.viewmodels.CustomerUpdateViewModel;
import com.example.jpademo.viewmodels.CustomerViewModel;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerApi {
	private final CustomerService service;

	public CustomerApi(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<CustomerViewModel>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomerViewModel> getById(@PathVariable long id) {
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<CustomerViewModel> create(@Valid @RequestBody CustomerCreateViewModel viewModel) {
		return ResponseEntity.ok(service.create(viewModel));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CustomerViewModel> update(@PathVariable long id, @Valid @RequestBody CustomerUpdateViewModel viewModel) {
		return ResponseEntity.ok(service.update(id, viewModel));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
