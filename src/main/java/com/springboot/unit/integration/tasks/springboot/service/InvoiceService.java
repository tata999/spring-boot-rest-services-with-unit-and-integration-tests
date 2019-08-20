package com.springboot.unit.integration.tasks.springboot.service;

import com.springboot.unit.integration.tasks.springboot.model.Course;
import com.springboot.unit.integration.tasks.springboot.model.Invoice;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InvoiceService {

	private static List<Invoice> invoices = new ArrayList<>();

	static {
		//Initialize Data
		Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course3 = new Course("Course3", "Spring Boot", "6K Invoices",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course course4 = new Course("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

		Invoice ranga = new Invoice("Invoice1", "Ranga Karanam",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Invoice satish = new Invoice("Invoice2", "Satish T",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		invoices.add(ranga);
		invoices.add(satish);
	}

	public List<Invoice> retrieveAllInvoices() {
		return invoices;
	}

	public Invoice retrieveInvoice(String invoiceId) {
		for (Invoice invoice : invoices) {
			if (invoice.getId().equals(invoiceId)) {
				return invoice;
			}
		}
		return null;
	}

	public List<Course> retrieveCourses(String invoiceId) {
		Invoice invoice = retrieveInvoice(invoiceId);

		if (invoice == null) {
			return null;
		}

		return invoice.getCourses();
	}

	public Course retrieveCourse(String invoiceId, String courseId) {
		Invoice invoice = retrieveInvoice(invoiceId);

		if (invoice == null) {
			return null;
		}

		for (Course course : invoice.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Course addCourse(String invoiceId, Course course) {
		Invoice invoice = retrieveInvoice(invoiceId);

		if (invoice == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);

		invoice.getCourses().add(course);

		return course;
	}
}