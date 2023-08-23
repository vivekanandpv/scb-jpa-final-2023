package com.example.jpademo.services;

import java.util.List;

import com.example.jpademo.viewmodels.CustomerCreateViewModel;
import com.example.jpademo.viewmodels.CustomerUpdateViewModel;
import com.example.jpademo.viewmodels.CustomerViewModel;

public interface CustomerService {
	List<CustomerViewModel> getAll();
	CustomerViewModel getById(long id);
	CustomerViewModel create(CustomerCreateViewModel viewModel);
	CustomerViewModel update(long id, CustomerUpdateViewModel viewModel);
	void deleteById(long id);
}
