import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String rome[] = { "I", "V", "X" };
		String opt[] = { "+", "-", "*", "/" };
		String arg[] = {};
		int arg1 = 0;
		int arg2 = 0;
		int sum = 0;
		int remainder = 0;

		while (true) {
			arg = null;
			arg1 = 0;
			arg2 = 0;
			sum = 0;
			remainder = 0;
			opt[0] = "+";

			System.out.println("입력");
			String command = reader.readLine();
			for (String str : opt) { // 연산자 알기
				if (command.contains(str)) {
					opt[0] = str; // opt[0]에 연산자 저장
					arg = command.split("\\" + str); // arg[0]=피연산자1, arg[1]=피연산자2
					break;
				}
			}
			if (arg.length == 0) {
				System.out.println("연산자가 없습니다.");
				continue;
			}
			if (arg.length == 1) {
				System.out.println("두번째 피연산자가 없습니다.");
				continue;
			}
			boolean isRome = true;
			for (int i = 0; i < arg[0].length(); i++) {
				if (!(arg[0].charAt(i) == 'I' || arg[0].charAt(i) == 'V' || arg[0].charAt(i) == 'X')) {
					isRome = false;
				}
			}
			for (int i = 0; i < arg[1].length(); i++) {
				if (!(arg[1].charAt(i) == 'I' || arg[1].charAt(i) == 'V' || arg[1].charAt(i) == 'X')) {
					isRome = false;
				}
			}
			if (isRome == false) {
				System.out.println("로마숫자만 입력할 수 있습니다.");
				continue;
			}

			boolean isVXContained = false;
			List<Integer> VXIndex1 = new ArrayList<>();
			for (int i = 0; i < arg[0].length(); i++) { // V와 X를 피연산자1 값에 더하고 인덱스 위치를 저장
				if (arg[0].charAt(i) == 'V') {
					VXIndex1.add(new Integer(i));
					arg1 += 5;
					isVXContained = true;
				} else if (arg[0].charAt(i) == 'X') {
					VXIndex1.add(new Integer(i));
					arg1 += 10;
					isVXContained = true;
				}

			}

			if (isVXContained == true) {// 피연산자에 VX포함
				for (int i = 0; i < arg[0].length(); i++) {

					if (arg[0].charAt(i) == 'I') {
						if (i < VXIndex1.get(0).intValue()) { // V와 X의 인덱스보다 왼쪽에있으면 1 빼기
							arg1 -= 1;

						} else if (i > VXIndex1.get(VXIndex1.size() - 1).intValue()) {// V와 X의 인덱스보다 오른쪽에 있으면 1 더하기
							arg1 += 1;

						}
					}

				}

			} else {
				for (int i = 0; i < arg[0].length(); i++) {
					arg1 += 1;
				}
			}
			//System.out.println(arg1); // 피연산자1 값 출력
			if (arg1 > 39) {
				System.out.println("1에서39의 로마숫자만 입력가능합니다.");
				continue;
			}
			///////////////////
			isVXContained = false;
			List<Integer> VXIndex2 = new ArrayList<>();
			for (int i = 0; i < arg[1].length(); i++) { // V와 X를 피연산자1 값에 더하고 인덱스 위치를 저장
				if (arg[1].charAt(i) == 'V') {
					VXIndex2.add(new Integer(i));
					arg2 += 5;
					isVXContained = true;
				} else if (arg[1].charAt(i) == 'X') {
					VXIndex2.add(new Integer(i));
					arg2 += 10;
					isVXContained = true;
				}

			}

			if (isVXContained == true) {// 피연산자에 VX포함
				for (int i = 0; i < arg[1].length(); i++) {

					if (arg[1].charAt(i) == 'I') {
						if (i < VXIndex2.get(0).intValue()) { // V와 X의 인덱스보다 왼쪽에있으면 1 빼기
							arg2 -= 1;

						} else if (i > VXIndex2.get(VXIndex2.size() - 1).intValue()) {// V와 X의 인덱스보다 오른쪽에 있으면 1 더하기
							arg2 += 1;

						}
					}

				}

			} else {
				for (int i = 0; i < arg[1].length(); i++) {
					arg2 += 1;
				}
			}
			if (arg2 > 39) {
				System.out.println("1에서39의 로마숫자만 입력가능합니다.");
				continue;
			}
			//System.out.println(arg2); // 피연산자2 값 출력

			if ((arg1 < arg2) && opt[0] == "-") {
				System.out.println("작은 수에서 큰 수를 뺄 수 없습니다.");
				continue;
			}
			if ((arg1 < arg2) && opt[0] == "/") {
				System.out.println("작은 수를 큰 수로 나눌 수 없습니다.");
				continue;
			}

			switch (opt[0]) {

			case "+":
				sum = arg1 + arg2;
				break;
			case "-":
				sum = arg1 - arg2;
				break;
			case "*":
				sum = arg1 * arg2;
				break;
			case "/":
				sum = arg1 / arg2;
				remainder = arg1 % arg2;
				break;
			}
			if (sum < 0 || sum > 39) {
				System.out.println("범위를 벗어났습니다.");
				continue;
			}
			//System.out.println(sum);
			if (opt[0].contentEquals("/")) {
				//System.out.println(remainder);
			}

			// 로마숫자로 결과값변환
			int xvi[] = new int[3];
			boolean isXEmpty = false;
			boolean isVEmpty = false;
			boolean isIEmpty = false;
			if (!(sum / 10 == 0)) {
				xvi[0] = sum / 10; // 10으로 나눈 몫,X의 개수
			} else
				isXEmpty = true;

			if ((sum % 10) > 5) {
				xvi[1] = 1; // 10으로 나눈 몫이 5보다 크면 V는 존재
			} else {
				isVEmpty = true;
			}

			boolean left = false;

			if (isIEmpty == false) {
				if (!(sum % 5 == 0)) {
					if ((sum % 5) <= 3) {
						// System.out.println(sum % 5);
						xvi[2] = sum % 5;// 5로 나눈 나머지가 3이하면 오른쪽에 I 배치
					} else {// 나머지가 4라면 왼쪽에 I 배치
						xvi[2] = 4;
						left = true;
					}
				} else
					isIEmpty = true;

			}

			// System.out.println(xvi[2]);

			List<String> result = new ArrayList<>();

			if (left == false) { // X부터시작
				if (isXEmpty == false) {
					for (int i = 0; i < xvi[0]; i++) {// x개수만큼 스트링 리스트에 추가

						result.add("X");
					}
				}
				if (isVEmpty == false) {
					if (xvi[1] == 1) {// V가 있다면
						result.add("V");
					}
				}

				if (isIEmpty == false) {
					for (int i = 0; i < xvi[2]; i++) {
						result.add("I");
					}
				}

			} else { // I부터 시작

				result.add("I"); // 왼쪽에 I가 붙는 경우라면 I는 반드시 하나이므로

				for (int i = 0; i < xvi[0]; i++) {
					result.add("X");
				}
				if (xvi[1] == 1) {
					result.add("V");
				}
			}

			String romeResult = "";
			for (String str : result) {
				romeResult += str;
			}
			if (opt[0] != "/") {
				System.out.println(romeResult);
			} else {

				// 로마숫자로 결과값변환
				int r_xvi[] = new int[3];
				boolean r_isXEmpty = false;
				boolean r_isVEmpty = false;
				boolean r_isIEmpty = false;
				if (!(remainder / 10 == 0)) {
					r_xvi[0] = remainder / 10; // 10으로 나눈 몫,X의 개수
				} else
					r_isXEmpty = true;

				if ((remainder % 10) > 5) {
					r_xvi[1] = 1; // 10으로 나눈 몫이 5보다 크면 V는 존재
				} else {
					r_isVEmpty = true;
				}

				boolean r_left = false;

				if (r_isIEmpty == false) {
					if (!(remainder % 5 == 0)) {
						if ((remainder % 5) <= 3) {
							// System.out.println(sum % 5);
							r_xvi[2] = remainder % 5;// 5로 나눈 나머지가 3이하면 오른쪽에 I 배치
						} else {// 나머지가 4라면 왼쪽에 I 배치
							r_xvi[2] = 4;
							r_left = true;
						}
					} else
						r_isIEmpty = true;

				}

				// System.out.println(xvi[2]);

				List<String> r_result = new ArrayList<>();

				if (r_left == false) { // X부터시작
					if (r_isXEmpty == false) {
						for (int i = 0; i < r_xvi[0]; i++) {// x개수만큼 스트링 리스트에 추가

							r_result.add("X");
						}
					}
					if (r_isVEmpty == false) {
						if (r_xvi[1] == 1) {// V가 있다면
							r_result.add("V");
						}
					}

					if (r_isIEmpty == false) {
						for (int i = 0; i < r_xvi[2]; i++) {
							r_result.add("I");
						}
					}

				} else { // I부터 시작

					r_result.add("I"); // 왼쪽에 I가 붙는 경우라면 I는 반드시 하나이므로

					for (int i = 0; i < r_xvi[0]; i++) {
						r_result.add("X");
					}
					if (r_xvi[1] == 1) {
						r_result.add("V");
					}
				}

				String r_romeResult = "";
				for (String str : r_result) {
					r_romeResult += str;
				}
				System.out.println("몫 " + romeResult + ", 나머지" + r_romeResult);
			}

		}
	}

	private static boolean isEmpty(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
