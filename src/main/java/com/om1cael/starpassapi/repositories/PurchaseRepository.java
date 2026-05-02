package com.om1cael.starpassapi.repositories;

import com.om1cael.starpassapi.models.Purchase;
import com.om1cael.starpassapi.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
