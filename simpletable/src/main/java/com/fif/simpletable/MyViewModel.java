package com.fif.simpletable;

import org.zkoss.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

public class MyViewModel {

	public static class Employee {
		private String npk;
		private String name;
		private String email;
		private String phoneNumber;

		public Employee(String npk, String name, String email, String phoneNumber) {
			this.npk = npk;
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
		}

		public String getNpk() { return npk; }
		public void setNpk(String npk) { this.npk = npk; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String getEmail() { return email; }
		public void setEmail(String email) { this.email = email; }
		public String getPhoneNumber() { return phoneNumber; }
		public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	}

	private List<Employee> employees;
	private List<Employee> filteredEmployees;
	private String searchKeyword = "";
	private Employee selectedEmployee;
	private Employee editingEmployee = new Employee("", "", "", "");

	@Init
	public void init() {
		employees = new ArrayList<>(Arrays.asList(
				new Employee("001", "Ahmad", "ahmad@email.com", "08123456789"),
				new Employee("002", "Budi", "budi@email.com", "08129876543")
		));
		filteredEmployees = new ArrayList<>(employees);
	}

	@Command @NotifyChange("filteredEmployees")
	public void search() {
		if (searchKeyword == null || searchKeyword.isEmpty()) {
			filteredEmployees = new ArrayList<>(employees);
		} else {
			String keyword = searchKeyword.toLowerCase();
			filteredEmployees = employees.stream()
					.filter(e -> e.getNpk().toLowerCase().contains(keyword)
							|| e.getName().toLowerCase().contains(keyword)
							|| e.getEmail().toLowerCase().contains(keyword)
							|| e.getPhoneNumber().toLowerCase().contains(keyword))
					.collect(Collectors.toList());
		}
	}

	@Command @NotifyChange({"filteredEmployees", "editingEmployee"})
	public void add() {
		employees.add(new Employee(editingEmployee.getNpk(), editingEmployee.getName(), editingEmployee.getEmail(), editingEmployee.getPhoneNumber()));
		search();
		editingEmployee = new Employee("", "", "", "");
	}

	@Command @NotifyChange("selectedEmployee")
	public void edit(@BindingParam("employee") Employee employee) {
		selectedEmployee = employee;
	}

	@Command @NotifyChange({"filteredEmployees", "selectedEmployee"})
	public void update() {
		search();
		selectedEmployee = null;
	}

	@Command @NotifyChange("selectedEmployee")
	public void cancel() {
		selectedEmployee = null;
	}

	@Command @NotifyChange("filteredEmployees")
	public void delete(@BindingParam("employee") Employee employee) {
		employees.remove(employee);
		search();
	}

	// Getters and setters
	public List<Employee> getFilteredEmployees() { return filteredEmployees; }
	public String getSearchKeyword() { return searchKeyword; }
	public void setSearchKeyword(String searchKeyword) { this.searchKeyword = searchKeyword; }
	public Employee getEditingEmployee() { return editingEmployee; }
	public Employee getSelectedEmployee() { return selectedEmployee; }
}