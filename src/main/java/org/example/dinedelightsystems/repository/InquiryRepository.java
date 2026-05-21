package org.example.dinedelightsystems.repository;

import org.example.dinedelightsystems.model.Inquiry;
import org.example.dinedelightsystems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByUserOrderByCreatedAtDesc(User user);
    List<Inquiry> findAllByOrderByCreatedAtDesc();
}

