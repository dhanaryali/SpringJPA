package com.rdr.SpringDataJPA.repo;

import org.springframework.data.repository.CrudRepository;

import com.rdr.SpringDataJPA.model.Department;

public interface DeptRepository extends CrudRepository<Department, Integer> {
	public Iterable<Department> findByLocation(String location);

	public Iterable<Department> findByDeptName(String departmentName);

	public Iterable<Department> findByDeptNameAndLocation(String departmentName, String location);
}
