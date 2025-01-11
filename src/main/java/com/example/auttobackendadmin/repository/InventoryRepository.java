package com.example.auttobackendadmin.repository;

import com.example.auttobackendadmin.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;


public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Inventory s WHERE s.id = :id AND s.status = 'ACTIVE'")
    Optional<Inventory> findByIdForUpdate(@Param("id") UUID id);
}
