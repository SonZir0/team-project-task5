import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetDataFromInput implements GetData {
    public final ConsoleInputProcessor inputProcessor;

    public GetDataFromInput(ConsoleInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    private String getStringInput(String msgToDisplay) {
        System.out.print(msgToDisplay);
        return inputProcessor.getNonEmptyString();
    }

    private Integer getMileageInput() {
        System.out.print("Введите пробег автобуса: ");
        return inputProcessor.getPositiveInteger();
    }

    @Override
    public Bus getOneObject() {
        return new Bus.Builder()
                .setNumber(getStringInput("\nВведите номер автобуса: "))
                .setModel(getStringInput("Введите модель автобуса: "))
                .setMileage(getMileageInput())
                .build();
    }

    @Override
    public List<Bus> getNObjects(int N) {
        return Stream.generate(this::getOneObject)
                .limit(N)
                .collect(Collectors.toList());
    }
}
