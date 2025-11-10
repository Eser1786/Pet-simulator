package Utils;


public class typeWriter {
    public static void write(String line, int miliSecond) throws InterruptedException{
        for(char c : line.toCharArray()){
            System.out.print(c);
            Thread.sleep(miliSecond);
        }   
    }

    public static void write(String line, int miliSecond, int secondBeforeNextLine) throws InterruptedException{
        for(char c : line.toCharArray()){
            System.out.print(c);
            Thread.sleep(miliSecond);
        }
        System.out.println();
        Thread.sleep(secondBeforeNextLine);

    }

    public static void writeWord(String line, int miliSecond, int timeWaitTillNextWord) throws InterruptedException{
        for(char c : line.toCharArray()){
            if(c != ' '){
                System.out.print(c);
                Thread.sleep(miliSecond);
            }
            else{
                System.out.print(c);
                Thread.sleep(timeWaitTillNextWord);
            }
        }





    }
}
