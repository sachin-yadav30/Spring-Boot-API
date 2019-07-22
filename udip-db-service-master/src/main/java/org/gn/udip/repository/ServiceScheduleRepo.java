package org.gn.udip.repository;

import org.gn.udip.model.ServiceScheduleMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceScheduleRepo extends JpaRepository<ServiceScheduleMaster,Long>  {
	ServiceScheduleMaster findByServiceId(Long serviceId);
}
