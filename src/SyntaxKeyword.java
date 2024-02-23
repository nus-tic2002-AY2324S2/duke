public class SyntaxKeyword {
    public static String startDateKeyword = "/sd";
    public static String endDateKeyword = "/ed";
    public static String startTimeKeyword = "/st";
    public static String endTimeKeyword = "/et";

    public static String[] getDeadlineKeywords(){
        return new String[]{endDateKeyword, endTimeKeyword};
    }

    public static String[] getEventKeywords(){
        return new String[]{startDateKeyword, startTimeKeyword, endDateKeyword, endTimeKeyword};
    }
}
