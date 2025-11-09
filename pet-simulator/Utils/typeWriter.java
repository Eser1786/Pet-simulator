package Utils;


public class typeWriter {
    public static void write(String line, int miliSecond) throws InterruptedException{
        for(char c : line.toCharArray()){
            System.out.print(c);
            Thread.sleep(miliSecond);
        }   
        System.out.println();
    }

    public static void write(String line, int miliSecond, int secondBeforeNextLine) throws InterruptedException{
        for(char c : line.toCharArray()){
            System.out.print(c);
            Thread.sleep(miliSecond);
        }
        System.out.println();
        Thread.sleep(secondBeforeNextLine);

    }
}
