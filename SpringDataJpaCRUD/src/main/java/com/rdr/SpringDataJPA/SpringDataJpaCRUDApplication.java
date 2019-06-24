package com.rdr.SpringDataJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rdr.SpringDataJPA.model.Department;
import com.rdr.SpringDataJPA.repo.DeptRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rdr.SpringDataJPA.repo")
@EntityScan(basePackages = "com.rdr.SpringDataJPA.model")
public class SpringDataJpaCRUDApplication {

	@Autowired
	DeptRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaCRUDApplication.class, args);
	}

	/**
	 * This method is to insert data into HSQL DB for doing DB operations
	 * 
	 * @return
	 */
	@Bean
	public String insertData() {

		for (int i = 10; i < 20; i++) {
			Department department = new Department();
			department.setDeptNo(i);
			department.setDeptName("DName" + i);
			if (i % 2 == 0) {
				department.setLocation("Hyd");
			} else {
				department.setLocation("Bangalore");
			}
			repo.save(department);
		}
		Department d = new Department();
		d.setDeptNo(10);
		d.setDeptName("Training");
		d.setLocation("Pune");
		repo.save(d);

		Department dept = new Department();
		dept.setDeptNo(101);
		dept.setDeptName("Training");
		dept.setLocation("Pune");
		repo.save(dept);

		Department dept1 = new Department();
		dept1.setDeptNo(102);
		dept1.setDeptName("Dev");
		dept1.setLocation("Hyd");
		repo.save(dept1);

		// repo.delete(30);
		repo.findAll().forEach(System.out::println);
		repo.findByLocation("Hyd").forEach(System.out::println);
		repo.findByDeptName("Training").forEach(System.out::println);
		repo.findByDeptNameAndLocation("Dev", "Hyd").forEach(System.out::println);
		return "success";
	}

}
