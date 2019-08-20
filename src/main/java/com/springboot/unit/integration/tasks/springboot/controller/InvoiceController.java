package com.springboot.unit.integration.tasks.springboot.controller;
import com.springboot.unit.integration.tasks.springboot.model.Course;
import com.springboot.unit.integration.tasks.springboot.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/invoices/{invoiceId}/courses")
	public List<Course> retrieveCoursesForInvoice(@PathVariable String invoiceId) {
		return invoiceService.retrieveCourses(invoiceId);
	}
	
	@GetMapping("/invoices/{invoiceId}/courses/{courseId}")
	public Course retrieveDetailsForCourse(@PathVariable String invoiceId,
                                           @PathVariable String courseId) {
		return invoiceService.retrieveCourse(invoiceId, courseId);
	}
	
	@PostMapping("/invoices/{invoiceId}/courses")
	public ResponseEntity<Void> registerInvoiceForCourse(
			@PathVariable String invoiceId, @RequestBody Course newCourse) {

		Course course = invoiceService.addCourse(invoiceId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
