package org.gn.udip.repository;

import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.ServiceParserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceParserDetailRepo extends JpaRepository<ServiceParserDetail,Long>{
	
	ServiceParserDetail findByServiceMaster(ServiceMaster serviceMaster);

}
