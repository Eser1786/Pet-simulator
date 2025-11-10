package Utils;



public class textColor {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLACK = "\u001B[30m";
    public static final String ORANGE = "\u001B[38;5;208m";



    public static void redText(String line) throws InterruptedException{
        typeWriter.write(RED + line + RESET, 50,150);
    }
    public static void greenText(String line) throws InterruptedException{
        typeWriter.write(GREEN + line + RESET, 50,150);
    }
    public static void yellowText(String line) throws InterruptedException{
        typeWriter.write(YELLOW + line + RESET, 50,150);
    }
    public static void blueText(String line) throws InterruptedException{
        typeWriter.write(BLUE + line+ RESET, 50,150);
    }
    public static void blackText(String line) throws InterruptedException{
        typeWriter.write(BLACK + line+ RESET, 50,150);
    }

    public static void redText(int line) throws InterruptedException{
        typeWriter.write(RED + line + RESET, 50,150);
    }
    public static void greenText(int line) throws InterruptedException{
        typeWriter.write(GREEN + line + RESET, 50,150);
    }
    public static void yellowText(int line) throws InterruptedException{
        typeWriter.write(YELLOW + line+ RESET, 50,150);
    }
    public static void blueText(int line) throws InterruptedException{
        typeWriter.write(BLUE + line+ RESET, 50,150);
    }
    public static void blackText(int line) throws InterruptedException{
        typeWriter.write(BLACK + line+ RESET, 50,150);
    }

    public static void orangeText(String line) throws InterruptedException {
    typeWriter.write(ORANGE + line + RESET, 10, 10);
}

public static void orangeText(int line) throws InterruptedException {
    typeWriter.write(ORANGE + line + RESET, 10, 10);
}




}
