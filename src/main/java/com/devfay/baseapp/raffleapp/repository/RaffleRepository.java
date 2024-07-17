package com.devfay.baseapp.raffleapp.repository;

import com.devfay.baseapp.raffleapp.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
}