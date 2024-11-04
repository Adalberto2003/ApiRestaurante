package com.mx.ApiRestaurante.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestaurante.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long>{
	public Mesas findByMunMesa(Long munMesa);
}
