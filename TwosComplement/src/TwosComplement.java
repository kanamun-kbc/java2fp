import static java.lang.System.*;

import java.util.Scanner;
public class TwosComplement {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		/*入力する数値int型変数(-128～127)*/
		int num = 0;
		/*8ビット絶対値数値での2進数保存先int型配列(必ず0か1を埋める)*/
		int[] numArray = new int[8];
		/*-128～127の間に入力値を収める*/
		do {
			out.print("数値を入力してください(-128～+127) :");
			num = sc.nextInt();
			if(num<=127 && num>=-128) {
				out.print("\n\n");
				break;
			}else {
				out.print("8ビット制限です. オーバーフローします\n");
				continue;
			}
		}while(true);
		/*計算をしやすいように一旦絶対値をとる*/
		int absNum = Math.abs(num);
		/*計算途中の商を保存するint型変数*/
		int shou = absNum;
		/*絶対値の10進数を2進数に変える*/
		for(int i=0;i<8;i++) {
			/*終了条件*/
			if(shou==2 || shou==3) {
				numArray[i] = shou%2;
				numArray[i+1] = 1;
				/*余りの桁を0で埋める*/
				for(int j=i+2;j<8;j++) {
					numArray[j] = 0;
				}
				break;
			}
			/*numが-1か1のとき*/
			if(shou==1) {
				numArray[i] = 1;
				for(int j=1;j<8;j++) {
					numArray[j] = 0;
				}
				break;
			}
			/*通常の処理*/
			numArray[i] = shou%2;
			shou /= 2;
		}
		/*numが0以上ならそのまま*/
		/*numが0未満なら絶対値で計算しているから*/
		/*2の補数を求める*/
		/*反転先の保存int型配列*/
		int[] numArrayR = new int[8];
		/*1加算した先の保存int型配列*/
		int[] numArrayP = new int[8];
		if(num<0) {
			/*反転*/
			for(int k=0;k<8;k++) {
				if(numArray[k]==0) {
					numArrayR[k] = 1;
				}else if(numArray[k]==1) {
					numArrayR[k] = 0;
				}
			}
			/*1加算*/
			for(int l=0;l<8;l++) {
				if(numArrayR[l]==0) {
					numArrayP[l] = 1;
					/*他比較していない桁はnumArrayRと同じ*/
					for(int s=l+1;s<8;s++) {
						numArrayP[s] = numArrayR[s];
					}
					break;
				}else if(numArrayR[l]==1) {
					numArrayP[l] = 0;
				}
			}
		}
		/*出力する*/
		for(int o=128;o>=1;o/=2) {
			out.printf("%4d",o);
		}
		out.print("\n");
		/*0以上ならnumArrayを使う*/
		/*0未満ならnumArrayPを使う*/
		if(num>=0) {
			for(int m=7;m>=0;m--) {
				if(numArray[m]==0) {
					out.print("  ×");
				}else if(numArray[m]==1) {
					out.print("  〇");
				}
			}
		}else if(num<0) {
			for(int n=7;n>=0;n--) {
				if(numArrayP[n]==0) {
					out.print(" ×");
				}else if(numArrayP[n]==1) {
					out.print(" ●");
				}
			}
		}
		/*終了*/
	}
}
