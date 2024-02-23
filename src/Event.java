import java.util.Hashtable;

public class Event extends Task {
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    public Event(Hashtable<String,String> fields){
        super(fields.get("desc"));
        this.setStartDate(fields.get("sd"));
        this.setStartTime(fields.get("st"));
        this.setEndDate(fields.get("ed"));
        this.setEndTime(fields.get("et"));
    }

    @Override
    public String toString(){
        String info = String.format("start %s @ %s, end %s @ %s", getStartDate(), getStartTime(), getEndDate(), getEndTime());
        return String.format("[E]%s (%s)", super.toString(), info);
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
