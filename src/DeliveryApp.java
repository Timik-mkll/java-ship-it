import javax.swing.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<FragileParcel> allReports = new ArrayList<>();
    private static ParcelBox<StandartParcel> standartParcelParcelBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>(50);
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(150);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    allReportStatus();
                    break;
                case 5:
                    checkBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Посмотреть статус всех посылок, поддерживающих трекинг");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Укажите тип посылки: 1 - Стандартная / 2 - Хрупкая / 3 - Скоропортящаяся");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Добавьте описание посылки: ");
        String description = scanner.nextLine();
        System.out.print("Укажите вес посылки: ");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Укажите адрес места назначения посылки: ");
        String deliveryAdress = scanner.nextLine();
        System.out.print("Укажите в какой день была отправлена посылка: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                StandartParcel standartParcel = new StandartParcel(description, weight, deliveryAdress, sendDay);
                allParcels.add(standartParcel);
                standartParcelParcelBox.addParcel(standartParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAdress, sendDay);
                allParcels.add(fragileParcel);
                allReports.add(fragileParcel);
                fragileParcelParcelBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.print("Укажите срок годности посылки в днях: ");
                int timeToLive = scanner.nextInt();
                PerishableParcel perishableParcel = new PerishableParcel(description,
                        weight, deliveryAdress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelParcelBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("К сожалению вы ввели неверный тип посылки, попробуйте ещё раз.");
                break;
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalDeliveryCost = 0;
        for (Parcel parcel : allParcels) {
            totalDeliveryCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость доставки всех посылок: " + totalDeliveryCost);
    }

    public static void allReportStatus() {
        System.out.print("Введите новое местоположение: ");
        String newLocation = scanner.nextLine();
        for (FragileParcel fragileParcel : allReports) {
            fragileParcel.reportStatus(newLocation);
        }
    }

    public static void checkBox() {
        System.out.println("Укажите тип коробки: 1 - Стандартная / 2 - Хрупкая / 3 - Скоропортящаяся");
        int boxType = scanner.nextInt();
        scanner.nextLine();
        switch (boxType) {
            case 1:
                System.out.println("Вот описание посылок в указанное коробке: ");
                standartParcelParcelBox.getAllParcels();
                break;
            case 2:
                System.out.println("Вот описание посылок в указанное коробке: ");
                fragileParcelParcelBox.getAllParcels();
                break;
            case 3:
                System.out.println("Вот описание посылок в указанное коробке: ");
                perishableParcelParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Указан неверный тип коробки. Пожалуйста, попробуйте ещё раз.");
                break;
        }
    }
}
