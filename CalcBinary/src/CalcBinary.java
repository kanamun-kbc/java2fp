import static java.lang.System.*;

import java.util.Scanner;
public class CalcBinary {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		/*答えを格納するString型変数*/
		String ans = "";
		out.print("被乗数と乗数を2 進数で入力してください。\n");
		out.print("被乗数：");
		String multiplicand = sc.nextLine();
		out.print("乗  数：");
		String multiplier = sc.nextLine();
		/*2進数として扱いたい10進数long型ができる*/
		long ten_mulcand = str_to_bin(multiplicand);
		long ten_muler = str_to_bin(multiplier);
		/*一時的に計算結果を格納するlong型変数*/
		/*初期値は, 2進数として扱いたい10進数を10進数として計算した結果*/
		long ten_mul = ten_mulcand*ten_muler;
		/*最終的に使われる2進数の計算結果long型変数*/
		long bin = 0;
		/*binに加える数値の桁調整用カウンタ変数*/
		int i = 1;
		/*ten_mulを一桁ずつ論理シフトしていき0になるまで続ける*/
		/*下の桁から順に見ていく*/
		while(ten_mul>0) {
			/*binに加える0or1*/
			bin+=(ten_mul%2)*i;
			/*binに加えた0or1を元の数から減算*/
			ten_mul-=(ten_mul%2);
			/*1番下の桁に残った数を2進数基準で上の桁に繰り上げ*/
			ten_mul+=((ten_mul%2)-(ten_mul%2))/2;
			/*1番下の桁は用無しになったので0にする*/
			ten_mul-=(ten_mul%10-ten_mul%2);
			/*論理シフト*/
			ten_mul/=10;
			/*次binに加える0or1は1つ上の桁*/
			i*=10;
		}
		/*binには2進数表現のlong型変数が入っている*/
		/*これを文字列に変換しString型ansに格納する*/
		ans = String.valueOf(bin);
		/*出力*/
		out.printf("%s * %s = %s",multiplicand,multiplier,ans);
		/*終了*/
	}
	
	/*2進数表現の文字列→数値*/
	static long str_to_bin(String s) {
		/*返される数値は10進数であることに注意*/
		return Long.parseLong(s);
	}
	
	/*結局何に使うかわからないメソッド*/
//	/*整数→2進数表現の文字列*/
//	static void bin_to_str(long bin, String ans) {
//		ans = ans.concat(String.valueOf(bin));
//	}
	
}
