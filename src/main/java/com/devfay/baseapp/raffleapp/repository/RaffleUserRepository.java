package com.devfay.baseapp.raffleapp.repository;

import com.devfay.baseapp.raffleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaffleUserRepository extends JpaRepository<User, Long> {


    @Nullable
    Optional<User> findFirstByNameLikeIgnoreCaseAllIgnoreCase(String name);
}
