package com.jas.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@AllArgsConstructor
public abstract class ModelService<T, R extends JpaRepository<T, Long>> {

	protected R repository;

	public Page<T> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}
	
	public Optional<T> findById(Long id) {
		return this.repository.findById(id);
	}
	
	public T save(T entity) {
		return this.repository.save(entity);
	}
	
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}
	
	public void delete(T entity) {
		this.repository.delete(entity);
	}
}
