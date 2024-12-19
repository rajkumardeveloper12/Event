package org.jsp.event.repository;

import java.util.List;

import org.jsp.event.entity.Event;
import org.jsp.event.entity.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface EventRepository extends JpaRepository<Event, Integer>{

	
//	@Query("select e from Event e where e.title=:title")
//	List<Event> findEventByTitle(String title);
	List<Event> findByTitle(String title);
	
	List<Event> findByGuest(String guest);
	
	
	List<Event> findByStatus(EventStatus status);
}