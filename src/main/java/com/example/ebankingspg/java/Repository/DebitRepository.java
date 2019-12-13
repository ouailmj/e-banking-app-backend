package com.example.ebankingspg.java.Repository;

import com.example.ebankingspg.java.model.Debit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DebitRepository extends JpaRepository<Debit, Long> {

}
