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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/complaintrc")
public class ComplaintRestController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/raiseComplaints")
    public ResponseEntity<Complaint> raiseComplaint(@RequestBody Complaint complaint) {
        complaint.setStatus(Status.OPEN);
        complaint.setRaisedOn(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.save(complaint));
    }

    @GetMapping("/CheckStatus/{id}")
    public ResponseEntity<Status> CheckStatus(@PathVariable ("id") long id) {
       Optional<Complaint> returnedComplaint=complaintService.findById(id);
        return returnedComplaint.map(complaint -> ResponseEntity.ok().body(complaint.getStatus())).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/updateStatus/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam Status newStatus) {

        Optional<Complaint> complaint = complaintService.findById(id);
        if(complaint.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Status currentStatus = complaint.get().getStatus();

        if (currentStatus == Status.RESOLVED) {
            return ResponseEntity.badRequest().body("Cannot update a resolved complaint.");
        }

        boolean isValid =
                (currentStatus == Status.OPEN && (newStatus == Status.IN_PROGRESS || newStatus == Status.RESOLVED)) ||
                        (currentStatus == Status.IN_PROGRESS && newStatus == Status.RESOLVED);

        if (!isValid) {
            return ResponseEntity.badRequest()
                    .body("Invalid status transition from " + currentStatus + " to " + newStatus);
        }

        complaint.get().setStatus(newStatus);
        if (newStatus == Status.RESOLVED) {
            complaint.get().setResolvedOn(LocalDateTime.now());
        }

        complaintService.save(complaint.get());
        return ResponseEntity.ok("Status updated successfully.");
    }
    @GetMapping("/statusCount")
    public ResponseEntity<Map<Status, Long>> getStatusCounts() {
        List<Complaint> complaints = complaintService.findAll();

        Map<Status, Long> statusCounts = complaints.stream()
                .collect(Collectors.groupingBy(Complaint::getStatus, Collectors.counting()));

        return ResponseEntity.ok(statusCounts);
    }
}
