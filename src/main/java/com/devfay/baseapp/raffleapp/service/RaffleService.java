package com.devfay.baseapp.raffleapp.service;

import com.devfay.baseapp.raffleapp.model.Raffle;
import com.devfay.baseapp.raffleapp.model.RaffleNumber;
import com.devfay.baseapp.raffleapp.model.User;
import com.devfay.baseapp.raffleapp.repository.RaffleNumberRepository;
import com.devfay.baseapp.raffleapp.repository.RaffleRepository;
import com.devfay.baseapp.raffleapp.repository.RaffleUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RaffleService {

    private RaffleRepository raffleRepository;

    private RaffleNumberRepository raffleNumberRepository;

    private RaffleUserRepository raffleUserRepository;

    public Raffle createRaffle(Raffle raffle) {
        Raffle newRaffle = raffleRepository.save(raffle);
        for (int i = 0; i < 100; i++) {
            RaffleNumber raffleNumber = new RaffleNumber();
            raffleNumber.setNumber(i);
            raffleNumber.setState("free");
            raffleNumber.setRaffle(newRaffle);
            raffleNumberRepository.save(raffleNumber);
        }
        return newRaffle;
    }

    public void assignTraderToRaffle(Long raffleId, Long traderId) {
        Raffle raffle = raffleRepository.findById(raffleId).orElseThrow(() -> new RuntimeException("Raffle not found"));
        User trader = raffleUserRepository.findById(traderId).orElseThrow(() -> new RuntimeException("Trader not found"));
        raffle.getTraders().add(trader);
        raffleRepository.save(raffle);
    }

    public List<Raffle> listRaffles() {
        return raffleRepository.findAll();
    }

    public Raffle getRaffle(Long id) {
        return raffleRepository.findById(id).orElseThrow(() -> new RuntimeException("Raffle not found"));
    }

    public List<RaffleNumber> getRaffleNumbers(Long raffleId) {
        Raffle raffle = getRaffle(raffleId);
        return raffleNumberRepository.findByRaffle(raffle);
    }

    public void save(RaffleNumber raffleNumber) {
        raffleNumberRepository.save(raffleNumber);
    }
}
