package com.cy.dao;

import com.cy.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    public Emp findById(int id);

    public List<Emp> findAll();
}
