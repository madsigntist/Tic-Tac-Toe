package tictactoe;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] chars = "         ".split("");
        printCurrentState(chars);

        game(input, chars);
    }

    static void game(Scanner input, String[] chars) {
        while (winCheck(chars).equals("Game not finished")) {
            userInput(input, chars, "X");
            printCurrentState(chars);
            if (!winCheck(chars).equals("Game not finished")) break;
            userInput(input, chars, "O");
            printCurrentState(chars);
        }
        System.out.println(winCheck(chars));
    }

    static void userInput(Scanner input, String[] chars, String player) {
        String userMove = null;
        for (boolean userInputCheckBool = false; !userInputCheckBool;
             userInputCheckBool = userInputCheck(userMove, chars, player)) {
            System.out.print("Enter the coordinates: ");
            userMove = input.nextLine();
        }
    }

    static boolean userInputCheck(String userMove, String[] chars, String player) {
        String[] userMoveArray = userMove.split(" ");

        if (userMoveArray[0].matches("[0-9]") && userMoveArray[1].matches("[0-9]")) {
            if (userMoveArray[0].matches("[1-3]") && userMoveArray[1].matches("[1-3]")) {
                switch (userMove) {
                    case "1 1":
                        return charsOccupiedCheck(6, chars, player);
                    case "1 2":
                        return charsOccupiedCheck(3, chars, player);
                    case "1 3":
                        return charsOccupiedCheck(0, chars, player);
                    case "2 1":
                        return charsOccupiedCheck(7, chars, player);
                    case "2 2":
                        return charsOccupiedCheck(4, chars, player);
                    case "2 3":
                        return charsOccupiedCheck(1, chars, player);
                    case "3 1":
                        return charsOccupiedCheck(8, chars, player);
                    case "3 2":
                        return charsOccupiedCheck(5, chars, player);
                    default:
                        return charsOccupiedCheck(2, chars, player);
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        } else {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    static boolean charsOccupiedCheck(int charNumber, String[] chars, String player) {
        if (chars[charNumber].matches("[XO]")) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            chars[charNumber] = player;
            return true;
        }
    }

    static void printCurrentState(String[] chars) {
        System.out.printf("%n---------%n| %s %s %s |%n| %s %s %s |%n| %s %s %s |%n---------%n",
                chars[0], chars[1], chars[2], chars[3], chars[4], chars[5], chars[6], chars[7], chars[8]);
    }

    static String winCheck(String[] chars) {
        if (win(chars, "X") && win(chars, "O") || aLotMore(chars)) return "Impossible";
        else if (win(chars, "X")) return "X wins";
        else if (win(chars, "O")) return "O wins";
        else if (Arrays.asList(chars).contains(" ")) return "Game not finished";
        else return "Draw";
    }

    static boolean aLotMore(String[] chars) {
        return Math.abs((Collections.frequency(Arrays.asList(chars), "X") -
                Collections.frequency(Arrays.asList(chars), "O"))) > 1;
    }

    static boolean win(String[] chars, String player) {
        return  (chars[0].equals(player) && chars[1].equals(player) && chars[2].equals(player)) ||
                (chars[3].equals(player) && chars[4].equals(player) && chars[5].equals(player)) ||
                (chars[6].equals(player) && chars[7].equals(player) && chars[8].equals(player)) ||
                (chars[0].equals(player) && chars[3].equals(player) && chars[6].equals(player)) ||
                (chars[1].equals(player) && chars[4].equals(player) && chars[7].equals(player)) ||
                (chars[2].equals(player) && chars[5].equals(player) && chars[8].equals(player)) ||
                (chars[0].equals(player) && chars[4].equals(player) && chars[8].equals(player)) ||
                (chars[2].equals(player) && chars[4].equals(player) && chars[6].equals(player));
    }
}
