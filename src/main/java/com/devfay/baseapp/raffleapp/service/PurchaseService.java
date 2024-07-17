package com.devfay.baseapp.raffleapp.service;

import com.devfay.baseapp.raffleapp.model.Purchase;
import com.devfay.baseapp.raffleapp.model.RaffleNumber;
import com.devfay.baseapp.raffleapp.model.User;
import com.devfay.baseapp.raffleapp.repository.PurchaseRepository;
import com.devfay.baseapp.raffleapp.repository.RaffleUserRepository;
import com.devfay.baseapp.raffleapp.repository.RaffleNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PurchaseService {

    private RaffleNumberRepository raffleNumberRepository;
    private PurchaseRepository purchaseRepository;
    private RaffleUserRepository raffleUserRepository;

    public Purchase buyRaffleNumber(Purchase purchase) {
        RaffleNumber raffleNumber = raffleNumberRepository.findById(purchase.getRaffleNumber().getId())
                .orElseThrow(() -> new RuntimeException("Raffle number not found"));

        if (!raffleNumber.getState().equals("free")) {
            throw new RuntimeException("The raffle number is not available");
        }

        raffleNumber.setState("sold");
        raffleNumberRepository.save(raffleNumber);
        purchase.setState("completed");
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> listPurchasesByUser(User user) {
        return purchaseRepository.findByPurchaser(user);
    }

    public RaffleNumber getRaffleNumber(Long raffleId, int number) {
        return raffleNumberRepository.findByRaffleIdAndNumber(raffleId, number)
                .orElseThrow(() -> new RuntimeException("Raffle number not found"));
    }

    public User getUser(Long id) {
        return raffleUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByName(String name) {
        return Objects.requireNonNull(raffleUserRepository.findFirstByNameLikeIgnoreCaseAllIgnoreCase(name)).orElse(null);
    }

    public User save(User user) {
        return raffleUserRepository.save(user);
    }

    public User getAuthenticatedUser() {
        return raffleUserRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
