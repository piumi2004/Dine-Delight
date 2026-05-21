package org.example.dinedelightsystems.repository;

import org.example.dinedelightsystems.model.Inquiry;
import org.example.dinedelightsystems.model.InquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryReplyRepository extends JpaRepository<InquiryReply, Long> {
    List<InquiryReply> findAllByInquiryOrderByCreatedAtAsc(Inquiry inquiry);
}

