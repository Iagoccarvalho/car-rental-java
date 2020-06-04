package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data:");
		System.out.print("Car model: ");
		String carModel = in.nextLine();
		System.out.print("Pickup (dd/mm/yyy hh:ss): ");
		Date start = sdf.parse(in.nextLine());
		System.out.print("Return (dd/mm/yyyy hh/ss): ");
		Date finish = sdf.parse(in.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = in.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = in.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println();
		System.out.println("INVOICE:");
		System.out.println("Basic payment: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: "+ String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: "+ String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		in.close();

	}

}
