import java.util.Optional;
import java.util.Random;

public class GetDataRandom implements GetData{

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
}