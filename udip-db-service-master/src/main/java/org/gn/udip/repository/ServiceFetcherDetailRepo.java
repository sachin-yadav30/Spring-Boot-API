package org.gn.udip.repository;

import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFetcherDetailRepo extends JpaRepository<ServiceFetcherDetail,Long>{

	ServiceFetcherDetail findByServiceMaster(ServiceMaster serviceMaster);
}
