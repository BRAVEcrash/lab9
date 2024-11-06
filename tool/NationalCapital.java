package tool;

import java.util.HashMap;
import java.util.Scanner;

public class NationalCapital {
	HashMap<String, String> hmCountry = new HashMap<String, String>();

	public NationalCapital() {
		// (문제9-1) 구현 위치(1)
		hmCountry.put("Korea", "Seoul");
		hmCountry.put("USA", "Washington");
		hmCountry.put("Japan", "Tokyo");
		hmCountry.put("China", "Beijing");
		hmCountry.put("UK", "London");
		hmCountry.put("France", "Paris");
		hmCountry.put("Canada", "Ottawa");
		hmCountry.put("Germany", "Berlin");
	}

	public void printDataSet() {
		// (문제9-1) 구현 위치(2)
		for (String country : hmCountry.keySet()) {
			System.out.println("Key: " + country + ", Value: " + hmCountry.get(country));
		}
	}

	public void capitalName(String country) {
		// (문제9-1) 구현 위치(3)
		String capital = hmCountry.get(country);
		if (capital != null) {
			System.out.println(country + " 의 수도: " + capital);
		} else {
			System.out.println("해당 국가가 데이터셋에 없습니다.");
		}
	}

	public static void main(String[] args) {
		NationalCapital nc = new NationalCapital();
		nc.printDataSet();

		Scanner scanner = new Scanner(System.in);
		System.out.print("국가 이름을 입력하시오: ");
		String country = scanner.nextLine();
		nc.capitalName(country);
		scanner.close();
	}
}
