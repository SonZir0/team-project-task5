import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetDataFromInput implements GetData {
    public final ConsoleInputProcessor inputProcessor;

    public GetDataFromInput(ConsoleInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    private String getStringInput() {
        return inputProcessor.getNonEmptyString();
    }

    private Integer getMileageInput() {
        return inputProcessor.getPositiveInteger();
    }

    @Override
    public Bus getOneObject() {
        return getOneObject(false);
    }

    public Bus getOneObject(boolean isSilent) {
        if (!isSilent)
            System.out.print("\nВведите номер автобуса: ");
        Bus.Builder temp = new Bus.Builder().setNumber(getStringInput());
        if (!isSilent)
            System.out.print("Введите модель автобуса: ");
        temp.setModel(getStringInput());
        if (!isSilent)
            System.out.print("Введите пробег автобуса: ");
        return temp.setMileage(getMileageInput())
                .build();
    }

    @Override
    public List<Bus> getNObjects(int N) {
        return getNObjects(N, false);
    }

    public List<Bus> getNObjects(int N, boolean isSilent) {
        return Stream.generate(() -> getOneObject(isSilent))
                .limit(N)
                .collect(Collectors.toList());
    }
}
