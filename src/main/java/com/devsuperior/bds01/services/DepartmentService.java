package com.devsuperior.bds01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll(){
		
//		List<DepartmentDTO> list = repository.findAll()
//				.stream()
//				.map(x -> new DepartmentDTO(x))
//				.sorted((d1,d2) -> d1.getName().compareTo(d2.getName()))
//				.toList();
		List<DepartmentDTO> list = repository.findAll(Sort.by("name"))
				.stream()
				.map(x -> new DepartmentDTO(x))				
				.toList();
		
		return list;
	}

}
