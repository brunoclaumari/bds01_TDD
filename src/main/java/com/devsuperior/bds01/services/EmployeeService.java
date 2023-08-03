package com.devsuperior.bds01.services;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAllPaged(Pageable pageable){
		
		Page<EmployeeDTO> page;
		
		page = repository.findAll(pageable).map(x -> new EmployeeDTO(x));
		
		return page;		
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity = dtoToEmployee(dto);
		entity = repository.save(entity);
		
		return new EmployeeDTO(entity);
	}
	
	/*
	 * @Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity = myMapper.dtoToProduct(dto);		
		entity = productRepository.save(entity);
		
		return myMapper.productToDto(entity, entity.getCategories());
	}
	 * */
	public Employee dtoToEmployee(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		
		Department department = departmentRepository.getReferenceById(dto.getDepartmentId());
		
		entity.setDepartment(department);
		
		return entity;
	}

}
