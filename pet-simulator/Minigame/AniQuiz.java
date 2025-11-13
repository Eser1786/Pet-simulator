package Minigame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Pet.Pet;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;

public class AniQuiz {
    // Lớp nội (inner class) để lưu trữ câu hỏi

    // Lớp nội (inner class) để lưu trữ câu hỏi
    private static class Question {
        String query;
        String[] options;
        char correctAnswer; // 'A', 'B', 'C', or 'D'

        public Question(String query, String[] options, char correctAnswer) {
            this.query = query;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private List<Question> questionBank;
    private final int NUM_QUESTIONS = 5; // Sẽ luôn hỏi 5 câu
    private final int REWARD_PER_QUESTION = 10;

    public AniQuiz() {
        // CẬP NHẬT: Mở rộng ngân hàng câu hỏi
        questionBank = new ArrayList<>();
        // 7 câu cũ
        questionBank.add(new Question("How many feet does a horse have?", new String[]{"2", "4", "6", "8"}, 'B'));
        questionBank.add(new Question("What food does the rabbit known for?", new String[]{"Meat", "Fish", "Carrot", "Milk"}, 'C'));
        questionBank.add(new Question("What type of bird doesn't know how to fly?", new String[]{"Parrot", "Eagle", "Crow", "Penguin"}, 'D'));
        questionBank.add(new Question("What animal is known for 'The King of Jungle'?", new String[]{"Lion", "Tiger", "Panther", "Elephant"}, 'A'));
        questionBank.add(new Question("What sound does the dog make?", new String[]{"Meow Meow", "Woof Woof", "beeh beeh", "Buzz Buzz"}, 'B'));
        questionBank.add(new Question("What is the biggest animal on Earth?", new String[]{"Elephant", "Giraffe", "Blue Whales", "Polar Bear"}, 'C'));
        questionBank.add(new Question("What does the cat usually catch at home?", new String[]{"Rat", "Chicken", "Duck", "Horse"}, 'A'));
        
        // 5 câu mới
        questionBank.add(new Question("What animal sleep for the whole Winter?", new String[]{"Giraffe", "Bear", "Horse", "Apes"}, 'B'));
        questionBank.add(new Question("What fish is known for swimming backward to lay eggs?", new String[]{"Shark", "Whale", "Salmon", "Clown fish"}, 'C'));
        questionBank.add(new Question("What is the thing that bees take from flower to make honey?", new String[]{"Pollen", "Water", "Nectar", "leaf"}, 'C'));
        questionBank.add(new Question("What animal could change its skin color to blend in with the enviroment?", new String[]{"Chameleon", "Hippo", "Kangaroo", "Llama"}, 'A'));
        questionBank.add(new Question("What animal lays egg?", new String[]{"Cow", "Chicken", "Pig", "Sheep"}, 'B'));
    }

   
    public void playAniQuiz(Player player, Pet pet) throws InterruptedException { // <-- Chữ ký phương thức đã thay đổi
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        // Xáo trộn ngân hàng câu hỏi
        Collections.shuffle(questionBank);
        
        // Lấy 5 câu hỏi đầu tiên
        int questionsToAsk = Math.min(NUM_QUESTIONS, questionBank.size());

        typeWriter.write("Welcome to the AniQuiz!", 30, 100);
        typeWriter.write("You will have to answer " + questionsToAsk + " questions. Each question correct, you will get 10 coins.", 30, 150);
        typeWriter.write("Let's Begin!", 30, 100);
        for (int i = 0; i < questionsToAsk; i++) {
            Question q = questionBank.get(i);

            typeWriter.write("Question " + (i + 1) + ": " + q.query, 30, 100);
            typeWriter.write("  A. " + q.options[0], 20, 50);
            typeWriter.write("  B. " + q.options[1], 20, 50);
            typeWriter.write("  C. " + q.options[2], 20, 50);
            typeWriter.write("  D. " + q.options[3], 20, 100);
            
            System.out.print("What is your answer? (A, B, C, D): ");
            char userAnswer = ' ';
            try {
                userAnswer = scanner.next().toUpperCase().charAt(0);
            } catch (Exception e) {
                // Bỏ qua nếu nhập lỗi
            }

            if (userAnswer == q.correctAnswer) {
                textColor.greenText("Correct!");
                
               
                textColor.orangeText(pet.getName() + textColor.RESET +" is clapping its hands!");

                
                textColor.yellowText("+10 coins");
                score += REWARD_PER_QUESTION;
            } else {
                textColor.redText("Incorrect!");
                typeWriter.write("The correct answer is " + q.correctAnswer + ". " + q.options[q.correctAnswer - 'A'], 30, 100);
                
                textColor.orangeText(pet.getName() + textColor.RESET +" is cheering you up!");
            }
        }

        typeWriter.write("Game Over! you got " + score + " coins.", 30, 100);
        player.setCoin(player.getCoin() + score);
        typeWriter.write("Coins left: " , 30);
        textColor.yellowText(player.getCoin() + " coins");
    }























    
}
