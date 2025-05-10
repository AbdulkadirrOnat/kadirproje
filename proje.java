import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalApp {

    static class Car {
        int id;
        String brand;
        String model;
        double dailyPrice;
        boolean available;
        int rentedDays;
        boolean isLuxury;

        Car(int id, String brand, String model, double dailyPrice, boolean isLuxury) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.dailyPrice = dailyPrice;
            this.available = true;
            this.rentedDays = 0;
            this.isLuxury = isLuxury;
        }

        @Override
        public String toString() {
            return id + " - " + brand + " " + model +
                    " | Ücret: " + dailyPrice + "₺/gün | " +
                    (isLuxury ? "Lüks | " : "") +
                    (available ? "Müsait" : "Kirada (" + rentedDays + " gün)");
        }

        double getTotalPrice() {
            double base = rentedDays * dailyPrice;
            double discount = 0;

            if (rentedDays >= 7) discount = 0.10; 
            else if (rentedDays >= 3) discount = 0.05;

            double total = base - (base * discount);

            if (isLuxury) total += 500; 

            return total;
        }
    }

    static class Rental {
        Car car;
        int days;
        String customerName;
        String customerId;
        String date;

        Rental(Car car, int days, String customerName, String customerId, String date) {
            this.car = car;
            this.days = days;
            this.customerName = customerName;
            this.customerId = customerId;
            this.date = date;
        }

        @Override
        public String toString() {
            return customerName + " (" + customerId + ") " +
                    "-> " + car.brand + " " + car.model + " | " +
                    days + " gün | Tarih: " + date +
                    " | Ödeme: " + car.getTotalPrice() + "₺";
        }
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        List<Rental> rentals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Araçları tanımla
        cars.add(new Car(1, "Toyota", "Corolla", 600, false));
        cars.add(new Car(2, "Honda", "Civic", 650, false));
        cars.add(new Car(3, "Ford", "Focus", 580, false));
        cars.add(new Car(4, "BMW", "320i", 1300, true));
        cars.add(new Car(5, "Audi", "A3", 1250, true));
        cars.add(new Car(6, "Mercedes", "C180", 1400, true));
        cars.add(new Car(7, "Hyundai", "Elantra", 550, false));
        cars.add(new Car(8, "Kia", "Ceed", 500, false));
        cars.add(new Car(9, "Peugeot", "308", 520, false));
        cars.add(new Car(10, "Renault", "Megane", 530, false));
        cars.add(new Car(11, "Volkswagen", "Golf", 750, false));
        cars.add(new Car(12, "Skoda", "Octavia", 620, false));
        cars.add(new Car(13, "Fiat", "Egea", 470, false));
        cars.add(new Car(14, "Opel", "Astra", 590, false));
        cars.add(new Car(15, "Mazda", "3", 600, false));
        cars.add(new Car(16, "Nissan", "Qashqai", 700, false));
        cars.add(new Car(17, "Chevrolet", "Malibu", 650, false));
        cars.add(new Car(18, "Subaru", "Impreza", 720, false));
        cars.add(new Car(19, "Seat", "Leon", 630, false));
        cars.add(new Car(20, "Volvo", "S60", 1150, true));
        cars.add(new Car(21, "Tesla", "Model 3", 1500, true));
        cars.add(new Car(22, "Lexus", "IS200", 1350, true));
        cars.add(new Car(23, "Jaguar", "XE", 1600, true));
        cars.add(new Car(24, "Alfa Romeo", "Giulia", 1250, true));
        cars.add(new Car(25, "Citroen", "C4", 490, false));

        int choice;

        do {
            System.out.println("\n=== Araç Kiralama Sistemi ===");
            System.out.println("1. Tüm Araçları Listele");
            System.out.println("2. Müsait Araçları Göster");
            System.out.println("3. Sadece Lüks Araçları Göster");
            System.out.println("4. Araç Kirala");
            System.out.println("5. Araç İade Et");
            System.out.println("6. Kiralama Geçmişi");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    for (Car car : cars) System.out.println(car);
                    break;

                case 2:
                    for (Car car : cars) {
                        if (car.available) System.out.println(car);
                    }
                    break;

                case 3:
                    for (Car car : cars) {
                        if (car.isLuxury) System.out.println(car);
                    }
                    break;

                case 4:
                    System.out.print("Kiralanacak aracın ID'si: ");
                    int rentId = scanner.nextInt();
                    scanner.nextLine();
                    Car selectedCar = null;

                    for (Car car : cars) {
                        if (car.id == rentId) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar == null) {
                        System.out.println("Araç bulunamadı.");
                        break;
                    }

                    if (!selectedCar.available) {
                        System.out.println("Araç şu anda kirada.");
                        break;
                    }

                    System.out.print("Ad Soyad: ");
                    String name = scanner.nextLine();
                    System.out.print("TC Kimlik No: ");
                    String tc = scanner.nextLine();
                    System.out.print("Kaç gün kiralanacak?: ");
                    int days = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tarih (GG/AA/YYYY): ");
                    String date = scanner.nextLine();

                    selectedCar.available = false;
                    selectedCar.rentedDays = days;
                    Rental rental = new Rental(selectedCar, days, name, tc, date);
                    rentals.add(rental);

                    System.out.println("\nAraç başarıyla kiralandı.");
                    System.out.println("Toplam Tutar: " + selectedCar.getTotalPrice() + "₺");
                    if (selectedCar.isLuxury) {
                        System.out.println("(Lüks araç teminat bedeli dahil!)");
                    }
                    break;

                case 5:
                    System.out.print("İade edilecek aracın ID'si: ");
                    int returnId = scanner.nextInt();
                    boolean found = false;

                    for (Car car : cars) {
                        if (car.id == returnId && !car.available) {
                            car.available = true;
                            System.out.println("Araç iade edildi: " + car.brand + " " + car.model);
                            System.out.println("Toplam Ödeme: " + car.getTotalPrice() + "₺");
                            car.rentedDays = 0;
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Araç bulunamadı veya zaten müsait.");
                    }
                    break;

                case 6:
                    if (rentals.isEmpty()) {
                        System.out.println("Kiralama geçmişi boş.");
                    } else {
                        System.out.println("\n=== Kiralama Geçmişi ===");
                        for (Rental rental : rentals) {
                            System.out.println(rental);
                        }
                    }
                    
