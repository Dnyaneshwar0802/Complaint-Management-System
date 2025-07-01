package com.cms.serviceimpl;

import com.cms.model.Complaint;
import com.cms.repository.ComplaintRepository;
import com.cms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Override
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }
}
