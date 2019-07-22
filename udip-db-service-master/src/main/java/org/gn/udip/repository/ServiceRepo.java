package org.gn.udip.repository;

import org.gn.udip.model.ServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<ServiceMaster,Long> {

}
