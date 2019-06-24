package com.rdr.datajpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rdr.datajpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public List<Employee> findByProject(String project);
	public List<Employee> findByProjectAndName(String Project, String name);
	public List<Employee> findByNameStartingWith(String str);
	public List<Employee> findByNameContaining(String str);
	public List<Employee> findByNameEndingWith(String str);
	public List<Employee> findBySalaryBetween(double sal1, double sal2);
	public List<Employee> findByNameOrProject(String name, String project);
	
	@Query(value="select e from Employee e where e.name=?1 and e.project=?2")
	public List<Employee> search(String name, String project);
}
