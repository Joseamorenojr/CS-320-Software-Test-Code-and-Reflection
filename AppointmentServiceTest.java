package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.Appointment;
import service.AppointmentService;

//@author Jose Moreno
//Project 1
//SNHU CS-320

class AppointmentServiceTest {
	private AppointmentService testApptService;
	private Date testApptDate;
	
	// Setup of AppointmentService for use with the tests.
	
	@BeforeEach
	void setUp() {
		try {
			testApptDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-01");
		} catch (ParseException ex) {
			System.out.println(ex.getMessage());
		}
		
		testApptService = new AppointmentService();
		testApptService.addAppointment(new Appointment("1", testApptDate, "description1"));
		testApptService.addAppointment(new Appointment("2", testApptDate, "description2"));
	}
	
	@Test
	void testAddAppointment() {
		assertAll(
			() -> assertTrue(testApptService.addAppointment(new Appointment("3", testApptDate, "description3"))),
			() -> assertTrue(testApptService.getAppointments().size() == 3)
		);
	}
	
	@Test
	void testAddAppointmentThatAlreadyExists() {
		assertFalse(testApptService.addAppointment(new Appointment("2", testApptDate, "description2")));
	}

	@Test
	void testDeleteAppointmentWithValidId() {
		assertAll(
			() -> assertTrue(testApptService.deleteAppointment("2")),
			() -> assertTrue(testApptService.getAppointments().size() == 1)
		);
	}
	
	@Test
	void testDeleteAppointmentWithInvalidId() {
		assertAll(
			() -> assertFalse(testApptService.deleteAppointment("5")),
			() -> assertTrue(testApptService.getAppointments().size() == 2)
		);
	}
}