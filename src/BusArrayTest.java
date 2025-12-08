package Tests;
import model.Bus;
import java.util.*;
public class BusArrayTest implements Testable {
    @Override
    public void runAllTests() {
        testSortByNumber();
        testSortByModel();
        testSortByMileage();
    }

    private void testSortByNumber() {
        System.out.print("Тест 1: Сортировка по номеру... ");
        try {
            List<Bus> buses = Arrays.asList(
                    new Bus.Builder().setNumber("С333СС").setModel("НЕФАЗ").setMileage(75000).build(),
                    new Bus.Builder().setNumber("А123ВС").setModel("ПАЗ").setMileage(45000).build()
            );

            BusSorter sorter = new BusSorter();
            sorter.setStrategy(new SortByNumber());
            sorter.sort(buses);

            boolean isSorted = buses.get(0).getNumber().equals("А123ВС");
            System.out.println(isSorted ? "ОК" : "ОШИБКА");
        } catch (Exception e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private void testSortByModel() {
        System.out.print("Тест 2: Сортировка по модели... ");
        try {
            List<Bus> buses = Arrays.asList(
                    new Bus.Builder().setNumber("С333СС").setModel("НЕФАЗ").setMileage(75000).build(),
                    new Bus.Builder().setNumber("А123ВС").setModel("ПАЗ").setMileage(45000).build()
            );

            BusSorter sorter = new BusSorter();
            sorter.setStrategy(new SortByByModel());
            sorter.sort(buses);

            boolean isSorted2 = buses.get(0).getByModel().equals("ПАЗ");
            System.out.println(isSorted2 ? "ОК" : "ОШИБКА");
        } catch (Exception e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private void testSortByMileage() {
        // Аналогично для пробега
        System.out.print("Тест 3: Сортировка по пробегу... ");
        try {
            List<Bus> buses = Arrays.asList(
                    new Bus.Builder().setNumber("С333СС").setModel("НЕФАЗ").setMileage(75000).build(),
                    new Bus.Builder().setNumber("А123ВС").setModel("ПАЗ").setMileage(45000).build()
            );

            BusSorter sorter = new BusSorter();
            sorter.setStrategy(new SortByMileage());
            sorter.sort(buses);

            boolean isSorted3 = buses.get(0).getMileage().equals("Method…");
            System.out.println(isSorted3 ? "ОК" : "ОШИБКА");
        } catch (Exception e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }
}
