import java.util.Hashtable;

public class Deadline extends Task {
    private String endDate;
    private String endTime;
    public Deadline(Hashtable<String,String> fields){
        super(fields.get("desc"));
        this.setEndDate(fields.get("ed"));
        this.setEndTime(fields.get("et"));
    }
    @Override
    public String toString(){
        String info = String.format("end %s @ %s", this.getEndDate(), this.getEndTime());
        return String.format("[D]%s (%s)", super.toString(), info);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
