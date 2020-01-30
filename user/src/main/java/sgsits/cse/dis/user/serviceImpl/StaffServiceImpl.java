package sgsits.cse.dis.user.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import sgsits.cse.dis.user.exception.ConflictException;
import sgsits.cse.dis.user.message.request.AddNewUser;
import sgsits.cse.dis.user.message.response.FacultyData;
import sgsits.cse.dis.user.model.StaffProfile;
import sgsits.cse.dis.user.repo.StaffRepository;
import sgsits.cse.dis.user.service.StaffService;

@Component
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffRepository staffRepository;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Override
	public List<FacultyData> getFacultyData() {
		List<StaffProfile> staffProfiles = staffRepository.findByClasssOrClasssOrderByCurrentDesignation("I", "II");
		List<FacultyData> facultyData = new ArrayList<FacultyData>();
		for (StaffProfile faculty : staffProfiles) {
			facultyData.add(new FacultyData(faculty.getId(), faculty.getName(), faculty.getNameAcronym(),
					null, faculty.getCurrentDesignation(), faculty.getEmail(), faculty.getMobileNo(), faculty.getAlternateMobileNo()));
		}
		return facultyData;
	}

	@Override
	public List<FacultyData> getStaffData() {
		List<StaffProfile> staffProfiles = staffRepository.findByClasssOrClasssOrderByCurrentDesignation("III", "IV");
		List<FacultyData> staffData = new ArrayList<FacultyData>();
		for (StaffProfile faculty : staffProfiles) {
			staffData.add(new FacultyData(faculty.getId(), faculty.getName(), faculty.getNameAcronym(),
					null, faculty.getCurrentDesignation(), faculty.getEmail(), faculty.getMobileNo(), faculty.getAlternateMobileNo()));
		}
		return staffData;
	}

	@Override
	public String addNewMember(AddNewUser addNewUser,String addedBy) throws ConflictException,DataIntegrityViolationException{
		try{StaffProfile test = staffRepository.save(new StaffProfile(addedBy, simpleDateFormat.format(new Date()),addNewUser.getEmployeeId(),
				addNewUser.getName(), addNewUser.getCurrentDesignation(), addNewUser.getClasss(), 
				addNewUser.getType(), addNewUser.getEmail(), addNewUser.getDob(), addNewUser.getMobileNo(),
				addNewUser.getJoiningDate()));
				if(test.equals(null))
						throw new ConflictException("Unable to add member.");
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Eployee already Exists.");
		}
		return "Member added successfully";
	}
}