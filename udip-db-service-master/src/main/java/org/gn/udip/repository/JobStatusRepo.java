package org.gn.udip.repository;

import java.util.List;

import org.gn.udip.model.JobStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JobStatusRepo extends JpaRepository<JobStatusModel,Long> {
	
	@Modifying
    @Transactional 
    public void deleteByJobIdIn(List<Long> jobId);	

}
