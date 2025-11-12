package Minigame;

import java.util.Random;
import java.util.Scanner;

import Pet.Pet;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;

public class SpeedMath {
    private final int NUM_QUESTIONS = 5;
    private final int REWARD_PER_QUESTION = 10;

    
    public void playSpeedMath(Player player, Pet pet) throws InterruptedException { // <-- Chữ ký phương thức đã thay đổi
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;

        typeWriter.write("Welcome to SpedMath!", 30, 100);
        typeWriter.write("You will answer 5 questions. Each question you get correct, you will receive 10 coins.", 30, 150);

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            int a, b, operation, correctAnswer;
            String question;

            operation = random.nextInt(4); // 0: Cộng, 1: Trừ, 2: Nhân, 3: Chia

            // CẬP NHẬT LOGIC TẠO CÂU HỎI
            switch (operation) {
                case 0: // Cộng
                    a = random.nextInt(50) + 1; // 1-50
                    b = random.nextInt(50) + 1; // 1-50
                    question = "Question " + (i + 1) + ": " + a + " + " + b + " = ?";
                    correctAnswer = a + b;
                    break;
                case 1: // Trừ
                    a = random.nextInt(50) + 1; // 1-50
                    b = random.nextInt(a) + 1;  // Đảm bảo b <= a (kết quả không âm)
                    question = "Question " + (i + 1) + ": " + a + " - " + b + " = ?";
                    correctAnswer = a - b;
                    break;
                case 2: // Nhân
                    a = random.nextInt(12) + 1; // 1-12
                    b = random.nextInt(12) + 1; // 1-12
                    question = "Question " + (i + 1) + ": " + a + " * " + b + " = ?";
                    correctAnswer = a * b;
                    break;
                case 3: // Chia
                default:
                    b = random.nextInt(10) + 2; // Số chia từ 2-11
                    correctAnswer = random.nextInt(10) + 2; // Thương từ 2-11
                    a = b * correctAnswer; // Đảm bảo phép chia chẵn
                    question = "Question " + (i + 1) + ": " + a + " / " + b + " = ?";
                    break;
            }

            typeWriter.write(question, 30, 100);
            System.out.print("Your answer: ");
            
            int userAnswer;
            try {
                userAnswer = scanner.nextInt();
            } catch (Exception e) {
                typeWriter.write("The answer is not appropriate. This will count as Incorrect!", 30, 100);
                userAnswer = -9999; // Gán một giá trị chắc chắn sai
                scanner.next(); // Xóa bộ đệm scanner
            }


            if (userAnswer == correctAnswer) {
                textColor.greenText("Correct!");
                
                // === PHẦN CỔ VŨ ===
                typeWriter.write(pet.getName() + " is surprise upon your correct answer!", 50, 150);
                // Hoặc bạn có thể gọi pet.sound() nếu muốn
                // pet.sound();
                
                textColor.yellowText("+10 coins");
                score += REWARD_PER_QUESTION;
            } else {
                textColor.redText("Incorrect!");
                typeWriter.write("The correct answer is " + correctAnswer, 30, 100);
                
                // === PHẢN ỨNG KHI TRẢ LỜI SAI ===
                typeWriter.write(pet.getName() + " is trying to cheer you up by rubbing its head into your hand...", 50, 150);
            }
        }

        typeWriter.write("Game Over! You got " + score + " coins.", 30, 100);
        player.setCoin(player.getCoin() + score);
        typeWriter.write("Coins left: " , 30);
        textColor.yellowText(player.getCoin() + " coins");
    }
}
