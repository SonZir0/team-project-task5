import java.util.Optional;
import java.util.Random;

public class GetDataRandom implements GetData, Testable {

    private final Random random = new Random();

    private static final String[] MODELS = {
            "Mercedes", "Volvo", "MAN", "Hyundai", "Scania", "Ikarus"
    };

    private String randomNumber() {
        char a = (char) ('A' + random.nextInt(26));
        char b = (char) ('A' + random.nextInt(26));
        int digits = random.nextInt(900) + 100; // 100-999
        return "" + a + digits + b;
    }

    private String randomModel() {
        return MODELS[random.nextInt(MODELS.length)];
    }

    private int randomMileage() {
        return random.nextInt(500_000); // 0 .. 499 999
    }

    @Override
    public Optional<Bus> getOneObject() {
        return Optional.of(new Bus.Builder()
                .setNumber(randomNumber())
                .setModel(randomModel())
                .setMileage(randomMileage())
                .build());
    }

    @Override
    public Optional<BusList> getNObjects(int N) {
        BusList list = new BusList();
        for (int i = 0; i < N; i++)
            getOneObject().ifPresent(list::add);
        return Optional.of(list);
    }

    @Override
    public void runAllTests() {
        System.out.println("Запускаем тесты в классе GetDataRandom:");
        System.out.println("\tТест полей полученного рандомного объекта: " + GetDataRandom.Tests.testRandObjFields());
        System.out.println("\tТест получения листа из N элементов: " + GetDataRandom.Tests.testOutputList());
    }

    static class Tests {
        static boolean testRandObjFields() {
                GetDataRandom dataFromRandom = new GetDataRandom();
                Bus temp = dataFromRandom.getOneObject().get();
                return temp.getNumber() != null &&
                        temp.getModel() != null &&
                        temp.getMileage() >= 0;
        }

        static boolean testOutputList() {
            GetDataRandom dataFromRandom = new GetDataRandom();
            BusList temp = dataFromRandom.getNObjects(5).get();
            BusList temp2 = dataFromRandom.getNObjects(0).get();
            BusList temp3 = dataFromRandom.getNObjects(101).get();
            return temp.size() == 5 &&
                    temp2.isEmpty() &&
                    temp3.size() == 101;
        }
    }
}