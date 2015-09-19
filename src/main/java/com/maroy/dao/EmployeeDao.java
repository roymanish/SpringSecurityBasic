package com.maroy.dao;

import java.util.List;

import com.maroy.entity.EmployeeEntity;

public interface EmployeeDao 
{
    public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
}
