package com.example.jpademo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.jpademo.entities.Customer;
import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.repositories.CustomerRepository;
import com.example.jpademo.viewmodels.CustomerCreateViewModel;
import com.example.jpademo.viewmodels.CustomerUpdateViewModel;
import com.example.jpademo.viewmodels.CustomerViewModel;

@Service
public class CustomerServiceImplementation implements CustomerService {
	private final CustomerRepository repository;

	public CustomerServiceImplementation(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CustomerViewModel> getAll() {
		return repository.findAll()
				.stream()
				.map(this::toViewModel)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerViewModel getById(long id) {
		return toViewModel(findById(id));
	}

	@Override
	public CustomerViewModel create(CustomerCreateViewModel viewModel) {
		Customer entity = toEntity(viewModel);
		repository.saveAndFlush(entity);
		return toViewModel(entity);
	}

	@Override
	public CustomerViewModel update(long id, CustomerUpdateViewModel viewModel) {
		Customer entity = findById(id);
		BeanUtils.copyProperties(viewModel, entity);
		repository.saveAndFlush(entity);
		return toViewModel(entity);
	}

	@Override
	public void deleteById(long id) {
		Customer entity = findById(id);
		repository.delete(entity);
	}
	
	private CustomerViewModel toViewModel(Customer entity) {
		CustomerViewModel viewModel = new CustomerViewModel();
		BeanUtils.copyProperties(entity, viewModel);
		return viewModel;
	}

	private Customer toEntity(CustomerCreateViewModel viewModel) {
		Customer entity = new Customer();
		BeanUtils.copyProperties(viewModel, entity);
		return entity;
	}
	
	private Customer findById(long id) {
		return repository.findById(id)
				.orElseThrow(
						() -> new RecordNotFoundException(
								String.format("Could not find the customer with id: %d", id)
								)
						);
	}
}
