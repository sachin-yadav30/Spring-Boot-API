package org.gn.udip.repository;

import org.gn.udip.model.ServiceNotification;
import org.gn.udip.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceNotificationRepo extends JpaRepository<ServiceNotification,Long>{

	ServiceNotification findByServiceId(Long serviceId);
}
