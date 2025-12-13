import java.util.Optional;

public class DataGetter {
    private GetData getData;

    public DataGetter(GetData getData){
        this.getData=getData;

    }

    public void setGetData(GetData getData) {
        this.getData = getData;
    }

    Optional<Bus> getOneObject(){
        return getData.getOneObject();
    }

    Optional<BusList> getNObjects(int N){
        return getData.getNObjects(N);
    }
}
