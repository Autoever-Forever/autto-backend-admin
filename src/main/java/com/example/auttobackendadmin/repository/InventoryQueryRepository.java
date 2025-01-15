package com.example.auttobackendadmin.repository;

import com.example.auttobackendadmin.common.domain.BaseStatus;
import com.example.auttobackendadmin.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InventoryQueryRepository {
    private final EntityManager entityManager;

    public Inventory findByIdForUpdate(UUID id) {
        return entityManager.createQuery(
            "SELECT i FROM Inventory i " +
            "WHERE i.id = :id " +
            "AND i.status = :status",
            Inventory.class)
            .setParameter("id", id)
            .setParameter("status", BaseStatus.ACTIVE)
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .getSingleResult();
    }
} 