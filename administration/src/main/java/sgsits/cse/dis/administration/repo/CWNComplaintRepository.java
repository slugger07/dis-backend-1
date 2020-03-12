package sgsits.cse.dis.administration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgsits.cse.dis.administration.model.CWNComplaint;
//CWN Maintenance

@Repository("")
public interface CWNComplaintRepository extends JpaRepository<CWNComplaint, String> {
//	List<CWNComplaints> findByCreatedBy(Long id);
//	List<CWNComplaints> findByLocationAndStatus(String location, String status);
//	List<CWNComplaints> findByLocation(String loc);
//	List<CWNComplaints> findByLocationAndStatusNot(String loc, String string);
//	List<CWNComplaints> findByLocationInAndStatus(List<String> location, String string);
	List<CWNComplaint> findByLocationInAndStatusNot(List<String> location, String string);
//	List<CWNComplaints> findByLocationIn(List<String> location);
}