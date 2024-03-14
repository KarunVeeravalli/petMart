package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	List<Orders> findAllByUserProfileId(Long id);

}
