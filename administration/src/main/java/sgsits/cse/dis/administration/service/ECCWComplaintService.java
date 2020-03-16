package sgsits.cse.dis.administration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sgsits.cse.dis.administration.model.ECCWComplaint;
import sgsits.cse.dis.administration.request.ECCWComplaintForm;
import sgsits.cse.dis.administration.request.EditComplaintForm;

@Service
public interface ECCWComplaintService {
	List<ECCWComplaint> findAllRemainingComplaints(List<String> location);
	List<ECCWComplaint> addMultipleComplaint(List<ECCWComplaintForm> eccwComplaint, String userId);
	ECCWComplaint editComplaint(EditComplaintForm complaintForm, String userId);
}