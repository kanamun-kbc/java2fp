import static java.lang.System.*;

import java.util.Scanner;
public class StackTester {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		/*スタックポインタsp*/
		/*配列番号0から始めると実質データ数を示していることにもなる変数*/
		int sp = 0;
		/*要素数5のスタック*/
		int[] stack = new int[5];
		/*操作の選択肢*/
		int root = 0;
		LABEL1:while(true) {
			out.printf("現在のデータ数:%d / 5\n",sp);
			out.print("(1)プッシュ (2)ポップ (3)ピーク (4)ダンプ (0)終了：");
			root = sc.nextInt();
			switch(root) {
			case 0:
				out.print("終了します\n");
				break LABEL1;
			case 1:
				if(sp==5) {
					out.print("スタックが満杯です。\n\n");
				}else {
					out.print("データ:");
					int data1 = sc.nextInt();
					stack[sp] = data1;
					sp++;
					out.printf("%dをプッシュしました\n\n",data1);
				}
				break;
			case 2:
				if(sp==0) {
					out.print("スタックが空です\n\n");
				}else {
					int data2 = stack[sp-1];
					stack[sp-1] = 0;
					sp--;
					out.printf("ポップしたデータは%1dです。\n\n",data2);
				}
				break;
			case 3:
				if(sp==0) {
					out.print("ピークデータはありません。\n\n");
				}else {
					out.printf("ピークデータは%dです。\n\n",stack[sp-1]);
				}
				break;
			case 4:
				if(sp==0) {
					out.print("スタックが空です。\n\n");
				}else {
					for(int i=4;i>=0;i--) {
						out.print("["+i+"]");
						if(i>=sp) {
							out.print("\n");
						}else {
							out.println(stack[i]);
						}
					}
				}
				out.print("\n");
				break;
			}
		}
	}
}
