import java.util.*;
import java.util.List;

public class TicTacToe {
    static List<Integer> playerPos = new ArrayList<>();
    static List<Integer> cpuPos = new ArrayList<>();
    public static void main(String[] args){
        char[][] gameBoard = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        while (checkWinner() == 0){
            System.out.println("Enter your choice : ");
            int playerChoice = in.nextInt();
            while (playerPos.contains(playerChoice) || cpuPos.contains(playerChoice)){
                System.out.println("Invalid choice, make another.");
                playerChoice = in.nextInt();
            }
            playerPos.add(playerChoice);
            input(playerChoice,gameBoard,"player");
            if (checkWinner() != 0){
                switch (checkWinner()){
                    case 1 -> System.out.println("Congratulations !!! You Won");
                    case -1 -> System.out.println("CPU Won!");
                    case -2 -> System.out.println("Draw.");
                }
                printGame(gameBoard);
                break;
            }
            int cpuChoice = random.nextInt(9) + 1;
            while (playerPos.contains(cpuChoice) || cpuPos.contains(cpuChoice)){
                cpuChoice = random.nextInt(9) + 1;
            }
            input(cpuChoice,gameBoard,"cpu");
            cpuPos.add(cpuChoice);
            printGame(gameBoard);
            if (checkWinner() != 0){
                switch (checkWinner()){
                    case 1 -> System.out.println("Congratulations !!! You Won");
                    case -1 -> System.out.println("CPU Won!");
                    case -2 -> System.out.println("Draw");
                }
                printGame(gameBoard);
                break;
            }
        }
    }
    public static void printGame(char[][] gameBoard){
        for (char[] line : gameBoard){
            for (char sym : line){
                System.out.print(sym+" ");
            }
            System.out.println();
        }
    }
    public static void input(int pos, char[][] gameBoard, String user){
        char symbol;
        if (user.equals("player")) symbol = 'x';
        else symbol = 'o';
        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
            default -> System.out.println("Enter valid choice");
        }
    }
    public static int checkWinner(){
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);
        List<List<Integer>> winCond = new ArrayList<>();
        winCond.add(topRow);
        winCond.add(midRow);
        winCond.add(botRow);
        winCond.add(leftCol);
        winCond.add(midCol);
        winCond.add(rightCol);
        winCond.add(cross1);
        winCond.add(cross2);
        for (List<Integer> list : winCond){
            if (new HashSet<>(playerPos).containsAll(list)){
                return 1;
            } else if (new HashSet<>(cpuPos).containsAll(list)) {
                return -1;
            } else if (cpuPos.size() + playerPos.size() == 9) {
                return -2;
            }
        }
        return 0;
    }
}
