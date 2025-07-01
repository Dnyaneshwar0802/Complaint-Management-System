package com.cms.service;

import com.cms.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint save(Complaint complaint);

    List<Complaint> findAll();
}
