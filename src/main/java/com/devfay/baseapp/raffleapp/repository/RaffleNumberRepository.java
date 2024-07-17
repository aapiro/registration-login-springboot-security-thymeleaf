package com.devfay.baseapp.raffleapp.repository;

import com.devfay.baseapp.raffleapp.model.Raffle;
import com.devfay.baseapp.raffleapp.model.RaffleNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaffleNumberRepository extends JpaRepository<RaffleNumber, Long> {
    List<RaffleNumber> findByRaffle(Raffle raffle);
    List<RaffleNumber> findByRaffleAndState(Raffle raffle, String state);
    Optional<RaffleNumber> findByRaffleIdAndNumber(Long raffleId, int number);
}
