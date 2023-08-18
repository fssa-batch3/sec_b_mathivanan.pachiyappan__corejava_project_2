package in.fssa.evotingsystem;

import java.time.LocalDate;

import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.CandidateService;
import in.fssa.evotingsystem.service.ElectionService;
import in.fssa.evotingsystem.service.UserService;

public class AppTest {

	public static void main(String[] args) {


		System.out.println();
		System.out.println("//////////////////////   User   ////////////////////////////////");

		
		UserService newService = new UserService();

		try {
			User newUser = new User(9631185, 1234565890l, "StrongP@ssw0rd", "123 Main St", 12345, 1, true);
			
			// User Creation
			System.out.print("User Creation  = ");
			System.out.println(newService.create(newUser));

			// Update User
//			System.out.print("User Update   =  ");
//			System.out.println(newService.update(101, newUser));
//
//			// Delete User
//			System.out.print("User Delete   =  ");
//			System.out.print(newService.delete(963485));

			// Find By Id
			System.out.print("User Find By Id   =  ");
			System.out.println(newService.findById(103));

			// Get All Method
			System.out.print("Get All Method  =  ");
			System.out.println(newService.getAll());

			// Users Count
			System.out.print("Users Count  =  ");
			System.out.println(newService.count());

		} catch (Exception e) {
			e.printStackTrace();
		}

		

		System.out.println();
		System.out.println("//////////////////////   Election   ////////////////////////////////");
		
		ElectionService newEservice = new ElectionService();

		try {
			Election newElection = new Election(9128, "Local Election 2023", LocalDate.now(), "Community Center", 1, true);

			// User Creation
			System.out.print("Election Creation   = ");
			System.out.println(newEservice.create(newElection));

			// Update User
			System.out.print("Election Update   =  ");
			System.out.println(newEservice.update(101, newElection));

			// Delete User
			System.out.print("Election Delete   =  ");
			System.out.print(newEservice.delete(963485));

			// Find By Id
			System.out.print("Election Find By Id   =  ");
			System.out.println(newEservice.findById(103));


			// Get All Method
			System.out.print("Get All Method  =  ");
			System.out.println(newEservice.getAll());

			// Users Count
			System.out.print("Election Count  =  ");
			System.out.println(newEservice.count());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("//////////////////////   Candidate   ////////////////////////////////");
		
		CandidateService newCservice = new CandidateService();

		try {
			Candidate newCandidate = new Candidate(13244, "John Doe", LocalDate.now(), 2, 1, true);

			// User Creation
			System.out.print("Election Creation   = ");
			System.out.println(newCservice.create(newCandidate));

			// Update User
			System.out.print("Election Update   =  ");
			System.out.println(newCservice.update(101, newCandidate));

			// Delete User
			System.out.print("Election Delete   =  ");
			System.out.print(newCservice.delete(98988));

			// Find By Id
			System.out.print("Election Find By Id   =  ");
			System.out.println(newCservice.findById(2334));

			// Get All Method
			System.out.print("Get All Method  =  ");
			System.out.println(newCservice.getAll());

			// Users Count
			System.out.print("Election Count  =  ");
			System.out.println(newCservice.count());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
