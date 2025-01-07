package com.example.auttobackendadmin.repository;

import com.example.auttobackendadmin.entity.SeatByDateInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SeatByDateInventoryRepository extends JpaRepository<SeatByDateInventory, UUID> {
} 