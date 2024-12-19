package org.jsp.event.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.event.entity.Event;
import org.jsp.event.entity.EventStatus;
import org.jsp.event.repository.EventRepository;
import org.jsp.event.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	EventRepository repo;
	
	@GetMapping("/hi")
	public String hi() {
		return "hello from Event app";
	}
	@Operation(summary = "Events saved",description = "Events saved successfull")
	
	@PostMapping
	public ResponseStructure<Event> saveEvent(@RequestBody Event event ) {
		
		Event save = repo.save(event);
		ResponseStructure<Event> structure=new ResponseStructure<Event>();
		structure.setHttpStatus(200);
		structure.setMessage("Event Saved Successfully");
		structure.setBody(save);
		return structure;
		
	}
	@GetMapping
	public ResponseStructure<List<Event>> findAllEvents(){
		List<Event> el = repo.findAll();
		if(el.size()>0) {
			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
			structure.setHttpStatus(200);
			structure.setMessage("All Events Found Successfully");
			structure.setBody(el);
			return structure;
		}
			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
			structure.setHttpStatus(404);
			structure.setMessage("No Events Found in Database.vvvc");
			structure.setBody(el);
			return structure;
	}
	@GetMapping("/{id}")
	public Event find(@PathVariable int id) {
		Optional<Event> optional = repo.findById(id);
		if(optional.isEmpty())
			return null;
		return optional.get();
	}
	@DeleteMapping
	public String deleteById(@PathVariable int id) {
		Optional<Event> optional = repo.findById(id);
		if(optional.isEmpty()) 
			return "Invalid Id";
		
		repo.deleteById(id);
		return "Deleted Success";
	}
	@GetMapping("/guest/{guest}")
	public List<Event> FindByName(@PathVariable String guest){
//		List<Event> events=new ArrayList<>();
//		List<Event> list = repo.findAll();
//		for(Event e:list) {
//			if(e.getGuest().equalsIgnoreCase(guest)) {
//				events.add(e);
//			}
//		}
//		return events;
		return repo.findByGuest(guest);
	}
	@GetMapping("/title/{title}")
	public List<Event> FindByTitle(@PathVariable String title){
		return repo.findByTitle(title);
	}
	@GetMapping("/status/{status}")
	public List<Event> FindByStatus(@PathVariable EventStatus status){
		return repo.findByStatus(status);
	}
}
