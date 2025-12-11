import java.util.List;

public class DataGetter {
    private GetData getData;

    public DataGetter(GetData getData){
        this.getData=getData;

    }

    public void setGetData(GetData getData) {
        this.getData = getData;
    }

    Bus getOneObject(){
        return getData.getOneObject();
    }

    List<Bus> getNObjects(int N){
        return getData.getNObjects(N);
    }
}
