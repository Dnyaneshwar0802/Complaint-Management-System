package com.cms.restcontroller;

import com.cms.model.Complaint;
import com.cms.model.Status;
import com.cms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/complaintrc")
public class ComplaintRestController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/complaints")
    public ResponseEntity<Complaint> raiseComplaint(@RequestBody Complaint complaint) {
        complaint.setStatus(Status.OPEN);
        complaint.setRaisedOn(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.save(complaint));
    }

    @GetMapping("/getAllComplaints")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
//           ( @RequestHeader(value = "X-ADMIN-KEY", required = false) String adminKey) {
//
//        if (!ADMIN_KEY.equals(adminKey)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        return ResponseEntity.ok(complaintService.findAll());
    }
    
}
