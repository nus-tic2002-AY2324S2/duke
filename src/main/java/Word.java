public class Word {
    public static String[] wordDiary = new String[100];
    public static int listSize = 0;




    public void storeWord(String s){
        wordDiary[listSize] = s;
        listSize++;
    }

    public static void printWordDiary(){
        if (listSize == 0){
            System.out.println("List is empty!");
            return;
        }

        for (int i = 0, j = 1; i<listSize; i++, j++ ){
            System.out.print(j + ". ");
            System.out.println(wordDiary[i]);
        }
    }




}
