package com.cms.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complaintId;
    private  String customerName;
    private String issueDescription;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime raisedOn;
    private LocalDateTime resolvedOn;

    public LocalDateTime getRaisedOn() {
        return raisedOn;
    }

    public void setRaisedOn(LocalDateTime raisedOn) {
        this.raisedOn = raisedOn;
    }

    public LocalDateTime getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDateTime resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", customerName='" + customerName + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", status=" + status +
                '}';
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
