package com.springdatajpa.many2many.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdatajpa.many2many.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {

}
