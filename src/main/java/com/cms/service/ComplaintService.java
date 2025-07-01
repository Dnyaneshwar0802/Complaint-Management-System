package com.cms.service;

import com.cms.model.Complaint;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    Complaint save(Complaint complaint);

    List<Complaint> findAll();

    Optional<Complaint> findById(Long id);
}
