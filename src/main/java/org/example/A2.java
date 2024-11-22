package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Customer {
    private int id;
    private String lastname;
    private String name;
    private String middleName;
    private String address;
    private int numberOfCard;
    private int accountNumber;

    @Override
    public String toString() {
        return "Customers{" +
                "id= " + id +
                ", ФИО " + lastname + " " + name + " " + middleName +
                ", Номер карточки " + numberOfCard +
                ", Номер банковского счета " + accountNumber +
                "}";
    }

    public Customer(int id, String lastName, String name, String middleName, String address, int numberOfCard, int accountNumber) {
        this.id = id;
        this.lastname = lastName;
        this.name = name;
        this.middleName = middleName;
        this.address = address;
        this.numberOfCard = numberOfCard;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public static void printCustByAlhpabet(List<Customer> customers) {
        customers.sort(Comparator.comparing(Customer::getName));

        System.out.println("Вывод покупателей в алфавитном порядке имен: ");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public static void printRangeCard(List<Customer>customers,int rangeStart,int finishRange){
        System.out.println("Вывод покупателей у которых номер кредитной карточки больше " + rangeStart+ " и меньше "+ finishRange);

        for(Customer customer:customers){
            if(customer.getNumberOfCard()> rangeStart && customer.getNumberOfCard() < finishRange)
                System.out.println(customer);
        }
    }

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Петров", "Петр", "Иванович", "Москва", 12345, 123456789));
        customers.add(new Customer(2, "Сидоров", "Александр", "Владимирович", "Санкт-Петербург", 67890, 667889900));
        customers.add(new Customer(3, "Козлов", "Дмитрий", "Александрович", "Екатеринбург", 23456, 112233445));
        customers.add(new Customer(4, "Михайлова", "Марина", "Петровна", "Новосибирск", 98765, 998877655));

        printCustByAlhpabet(customers);
        System.out.println();
        printRangeCard(customers,10000,30000);


    }
}
