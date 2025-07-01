package com.cms.serviceimpl;

import com.cms.model.Complaint;
import com.cms.repository.ComplaintRepository;
import com.cms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Override
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }
}
