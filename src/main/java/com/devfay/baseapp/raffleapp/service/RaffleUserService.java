package com.devfay.baseapp.raffleapp.service;

import com.devfay.baseapp.raffleapp.model.User;
import com.devfay.baseapp.raffleapp.repository.RaffleUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RaffleUserService {

    private RaffleUserRepository raffleUserRepository;

    public User register(User user) {
        // Registration logic
        return raffleUserRepository.save(user);
    }

    public User findByName(String name) {
        return Objects.requireNonNull(raffleUserRepository.findFirstByNameLikeIgnoreCaseAllIgnoreCase(name))
                .orElse(null);
    }

    public User save(User purchaser) {
        return raffleUserRepository.save(purchaser);
    }

    public Optional<User> findById(Long traderId) {
        return raffleUserRepository.findById(traderId);
    }

    // Other authentication methods
}
