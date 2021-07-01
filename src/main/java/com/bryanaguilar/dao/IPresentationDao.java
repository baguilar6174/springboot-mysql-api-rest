package com.bryanaguilar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bryanaguilar.entity.Presentation;

public interface IPresentationDao extends JpaRepository<Presentation, Long> {

}
