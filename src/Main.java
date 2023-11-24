import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Insert the full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            List<Employee> emp = new ArrayList<>();
            String line = br.readLine();
            while (line != null){

                String[] fields = line.split(", ");
                String name = fields[0];
                String email = fields[1];
                double salary = Double.parseDouble(fields[2]);
                line = br.readLine();

                emp.add(new Employee(name, email, salary));
            }

            System.out.print("Enter salary: ");
            double readSalary = sc.nextDouble();

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            List<String> mails = emp.stream()
                    .filter(p -> p.getSalary() > readSalary)
                    .map(p -> p.getEmail()).sorted().collect(Collectors.toList());
            System.out.println("Email of people whose salary is more than R$" + readSalary + ":");
            mails.forEach(System.out::println);

            double sumSalaries = emp.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(x-> x.getSalary())
                    .reduce(0.0, (x, y) -> x+y);

            System.out.println("Sum of salary of people whose name starts with 'M': " + sumSalaries);



        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();

    }
}