package com.devfay.baseapp.raffleapp.repository;

import com.devfay.baseapp.raffleapp.model.Purchase;
import com.devfay.baseapp.raffleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPurchaser(User purchaser);

    @Query("SELECT p.trader, COUNT(p) FROM Purchase p WHERE p.state = 'completed' GROUP BY p.trader")
    List<Object[]> countPurchasesByTrader();
}