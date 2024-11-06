package lab9;

import java.util.Scanner;
import tool.MoleGame;
import tool.NationalCapital;

public class Lab9 {
    public static void main(String[] args) {

        // 문제(9-1) 국가 수도 맞추기

        NationalCapital nc = new NationalCapital();
        nc.printDataSet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("국가 이름을 입력하시오: ");
            String s = sc.nextLine();
            if(s.equals("quit")) break;
            nc.capitalName(s);
        }


        // 문제(9-2) 두더지 게임

        new MoleGame();

    }
}
