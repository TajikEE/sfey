package com.ridango.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ridango.payment.model.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {

}
