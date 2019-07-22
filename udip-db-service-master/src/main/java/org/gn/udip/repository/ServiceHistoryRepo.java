package org.gn.udip.repository;

import org.gn.udip.model.ServiceMasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceHistoryRepo extends JpaRepository<ServiceMasterHistory,Long>{

}
