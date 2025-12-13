import java.util.Optional;

public interface GetData {
     Optional<Bus> getOneObject();
     Optional<BusList> getNObjects(int N);
}
