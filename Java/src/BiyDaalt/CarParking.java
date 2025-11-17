package BiyDaalt;
import java.io.*;
import java.util.*;

class Car {
    public String plate;   // Машины дугаар

    public Car(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return plate;
    }
}

public class CarParking {
    public Stack<Car> garage;
    public int capacity;

    public CarParking(int capacity) {
        this.capacity = capacity;
        this.garage = new Stack<>();
    }

    public boolean isFull() {
        return garage.size() >= capacity;
    }

    public void input(String filename) {
        process(filename);
    }

    public void process(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNext()) {
                String cmd = sc.next();

                // Командын дараа заавал дугаар байх ёстой
                if (!sc.hasNext()) break;
                String plate = sc.next();

                if (cmd.equals("A")) {
                    arrival(new Car(plate));
                }
                else if (cmd.equals("D")) {
                    departure(new Car(plate));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл олдсонгүй: " + filename);
        }
    }

    public void arrival(Car car) {
        if (isFull()) {
            System.out.println("Arrival " + car + " -> Garage full, this car cannot enter.");
        } else {
            garage.push(car);
            System.out.println("Arrival " + car + " -> There is room.");
        }
    }

    public void departure(Car car) {
        if (garage.isEmpty()) {
            System.out.println("Departure " + car + " -> This car not in the garage.");
            return;
        }

        // Contains шалгалт (Car object → plate-аар)
        boolean found = false;
        for (Car c : garage) {
            if (c.plate.equals(car.plate)) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Departure " + car + " -> This car not in the garage.");
            return;
        }

        Stack<Car> temp = new Stack<>();

        // Тухайн машин урдаасаа гарч иртэл түр гаргана
        while (!garage.peek().plate.equals(car.plate)) {
            temp.push(garage.pop());
        }

        // Гол машиныг гаргах
        garage.pop();
        System.out.println("Departure " + car + " -> " + (temp.size() + 1) + " cars moved out.");

        // Түр гаргасан машинуудыг буцааж хийх
        while (!temp.isEmpty()) {
            garage.push(temp.pop());
        }
    }

    public void output() {
        System.out.println("===== Үлдсэн машинууд =====");
        if (garage.isEmpty()) {
            System.out.println("Гараж хоосон байна.");
        } else {
            for (Car c : garage) {
                System.out.println(c);
            }
        }
    }

    public static void main(String[] args) {
        CarParking carPark = new CarParking(10);
        carPark.input("cars.txt");
        carPark.output();
    }
}
