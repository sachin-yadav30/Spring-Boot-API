package org.gn.udip.repository;

import org.gn.udip.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepo extends JpaRepository<UserNotification,Long> {

}
