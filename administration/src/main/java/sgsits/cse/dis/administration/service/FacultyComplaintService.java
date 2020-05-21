package sgsits.cse.dis.administration.service;

import java.util.List;

import sgsits.cse.dis.administration.model.FacultyComplaint;

public interface FacultyComplaintService {
	public List<FacultyComplaint> getResolvedComplaints(String userType, String id);
	public List<FacultyComplaint> getMyComplaints(String userType, String userId);
}
