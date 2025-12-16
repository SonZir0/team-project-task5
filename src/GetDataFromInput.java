import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetDataFromInput implements GetData, Testable {
    public final ConsoleInputProcessor inputProcessor;

    public GetDataFromInput(ConsoleInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    private String getStringInput() {
        return inputProcessor.getNonEmptyString();
    }

    private Integer getMileageInput() {
        return inputProcessor.getNonNegativeInteger();
    }

    @Override
    public Optional<Bus> getOneObject() {
        return getOneObject(false);
    }

    public Optional<Bus> getOneObject(boolean isSilent) {
        if (!isSilent)
            System.out.print("\nВведите номер автобуса: ");
        Bus.Builder temp = new Bus.Builder().setNumber(getStringInput());
        if (!isSilent)
            System.out.print("Введите модель автобуса: ");
        temp.setModel(getStringInput());
        if (!isSilent)
            System.out.print("Введите пробег автобуса: ");
        return Optional.of(temp.setMileage(getMileageInput())
                .build());
    }

    @Override
    public Optional<BusList> getNObjects(int N) {
        return getNObjects(N, false);
    }

    public Optional<BusList> getNObjects(int N, boolean isSilent) {
        return Optional.of( Stream.generate(() -> getOneObject(isSilent).get())
                .limit(N)
                .collect(Collectors.toCollection(BusList::new)));
    }

    @Override
    public void runAllTests() {
        System.out.println("\nЗапускаем тесты в классе GetDataFromInput:");
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест получения одного объекта:",
                GetDataFromInput.Tests.testOutputObject());
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест получения листа из N элементов:",
                GetDataFromInput.Tests.testOutputList());
    }

    static class Tests {
        static boolean testOutputObject() {
            // testData имеет строки оканчивающиеся на пробелы. Все что находится до /s является частью строки
            String testData = """
                    517K
                    \t    RF-102-\t
                    +1000
                    Success?""";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            try (ConsoleInputProcessor cip = new ConsoleInputProcessor(is)) {
                GetDataFromInput dataFromInput = new GetDataFromInput(cip);
                Bus temp = dataFromInput.getOneObject(true).get();
                return temp.getNumber().equals("517K") &&
                        temp.getModel().equals("RF-102-") &&
                        temp.getMileage() == 1000;
            }
        }

        static boolean testOutputList() {
            String testData = """
                    517K
                    \t    RF-102-\t
                    +1000
                    
                    Mileage?
                    is it tasty?
                    -0
                    
                    12491
                    00000
                    +0
                    
                    qwerty
                    15
                    51
                    Success?""";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            try (ConsoleInputProcessor cip = new ConsoleInputProcessor(is)) {
                GetDataFromInput dataFromInput = new GetDataFromInput(cip);
                BusList temp = dataFromInput.getNObjects(4, true).get();
                BusList temp2 = dataFromInput.getNObjects(0, true).get();
                // все идущие подряд пустые строки будут пропущены за один вызов
                return temp.size() == 4 &&
                        temp.get(1).getNumber().equals("Mileage?") &&
                        temp.get(2).getModel().equals("00000") &&
                        temp.getLast().getMileage() == 51 &&
                        temp2.isEmpty();
            }
        }
    }
}
