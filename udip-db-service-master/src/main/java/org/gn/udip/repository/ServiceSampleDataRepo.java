package org.gn.udip.repository;

import java.util.Date;
import java.util.List;

import org.gn.udip.model.ServiceSampleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServiceSampleDataRepo extends JpaRepository<ServiceSampleData,Long>{
	
	@Modifying
    @Transactional 
    public List<ServiceSampleData> findByCreatedOnBefore(Date expiryDate);
	
	@Modifying
    @Transactional 
    public void deleteByCreatedOnBefore(Date expiryDate);

}
