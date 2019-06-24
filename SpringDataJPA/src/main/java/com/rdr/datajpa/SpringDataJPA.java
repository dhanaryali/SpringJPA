package com.rdr.datajpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rdr.datajpa.model.Employee;
import com.rdr.datajpa.repo.EmployeeRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rdr.datajpa.repo")
@EntityScan(basePackages = "com.rdr.datajpa.model")
public class SpringDataJPA {

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJPA.class, args);
	}

	@Bean
	public String EmpDatabaseOperations() throws ParseException {
		String[] names = { "Suman", "Aditya", "Rams", "Raju", "Amir", "Bobby", "Karim", "Bhargav", "Amar", "Dhana" };
		String[] projects = { "Pega", "JPMC", "HomeServe", "Citi", "Wells", "JP Morgan", "JPMC", "Cisco", "AIG", "GE" };
		Employee emp = new Employee();
		emp.setName("Dhanaraju");
		emp.setSalary(10000.00);
		emp.setCommission(50.00);
		emp.setProject("Wells");
		emp.setbDate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-05-27"));

		Employee emp1 = new Employee();
		emp1.setName("Satya");
		emp1.setSalary(20000.00);
		emp1.setCommission(20.00);
		emp1.setProject("Wells");
		emp1.setbDate(new SimpleDateFormat("yyyy-MM-dd").parse("1988-04-24"));

		employeeRepository.save(emp);
		employeeRepository.save(emp1);
		for (int i = 0; i < 10; i++) {
			Employee e = new Employee();
			e.setName(names[i]);
			e.setSalary((int) (Math.random() * 100) * 1000);
			e.setCommission(i * 10.00);
			e.setProject(projects[i]);
			e.setbDate(new Date());
			employeeRepository.save(e);
		}

		employeeRepository.findAll().forEach(System.out::println);
		employeeRepository.findByProject("JPMC").forEach(System.out::println);
		employeeRepository.findByProjectAndName("JPMC", "Aditya").forEach(System.out::println);
		employeeRepository.findByNameStartingWith("D").forEach(System.out::println);
		employeeRepository.findByNameContaining("Ra").forEach(System.out::println);
		employeeRepository.findByNameEndingWith("r").forEach(System.out::println);
		employeeRepository.findBySalaryBetween(10000.00, 50000.00).forEach(System.out::println);
		employeeRepository.findByNameOrProject("Suman", "Cisco").forEach(System.out::println);
		employeeRepository.search("Bobby", "JP Morgan").forEach(System.out::println);
		return "success";
	}

	// @Bean
	public String sort() {
		employeeRepository.findAll(Sort.by("name")).forEach(System.out::println);
		employeeRepository.findAll(new Sort(Direction.DESC, "name").and(Sort.by("project")))
				.forEach(System.out::println);
		employeeRepository.findAll(new Sort(Direction.DESC, "name")).forEach(System.out::println);
		return "success";
	}

	@Bean
	public String pagination() {
		Pageable pageable = PageRequest.of(0, 3);
		Scanner scanner = new Scanner(System.in);
		char c = 'a';
		while (c != 'Q') {
			Page<Employee> page = employeeRepository.findAll(pageable);
			page.get().forEach(System.out::println);
			System.out.println("Please ener Next/Prev/Quit");
			c = scanner.next().charAt(0);
			if (c == 'N') {
				pageable = pageable.next();
			} else if (c == 'P') {
				pageable = pageable.previousOrFirst();
			}
		}
		scanner.close();
		return "success";
	}
}
