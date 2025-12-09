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

    BusList getNObjects(int N){
        return getData.getNObjects(N);
    }
}
