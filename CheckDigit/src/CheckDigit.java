import static java.lang.System.*;

import java.util.Scanner;
public class CheckDigit {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		/*一旦カード番号と重みをString型変数で獲得する*/
		/*目的は, 後で一桁ずつに分解しint型配列に格納するため*/
		out.print("チェックディジット計算プログラム\n");
		out.print("カード番号:");
		String cNum = sc.nextLine();
		out.print("重    み  :");
		String cWeight = sc.nextLine();
		/*カード番号分解int型配列cardnum*/
		String[] cardNum = cNum.split("");
		int[] cardnum = new int[cNum.length()];
		for(int i=0;i<cNum.length();i++) {
			cardnum[i] = Integer.parseInt(cardNum[i]);
		}
		/*重み分解int型配列weight*/
		String[] cardWeight = cWeight.split("");
		int[] weight = new int[cWeight.length()];
		for(int j=0;j<cWeight.length();j++) {
			weight[j] = Integer.parseInt(cardWeight[j]);
		}
		/*チェックディジット付きカード番号を格納するint型配列cardnumChk*/
		int[] cardnumChk = new int[cNum.length()+1];
		/*メソッドを呼び出し表示*/
		switch(get_check_digit(cardnum,weight,cardnumChk)) {
		case 0:
			out.print("チェックディジット付きカード番号\n");
			for(int s: cardnumChk) {
				out.print(s);
			}
			break;
		case 1:
			if(cardnum.length!=16) {
				out.print("error!!! カード番号が16桁の数字ではありません\n");
			}
			if(weight.length!=16) {
				out.print("error!!! 重みが16桁の数字ではありません\n");
			}
			break;
		}
		/*終了*/
	}
	
	/*チェックディジット付きカード番号を取得するメソッド*/
	static int get_check_digit(int[] cardnum,int[] weight,int[] cardnumChk) {
		if(cardnum.length==16 && weight.length==16) {
			/*カード番号と重みの各桁乗算の合計int型変数NWsum*/
			int NWsum = 0;
			for(int i=0;i<cardnum.length;i++) {
				NWsum += cardnum[i]*weight[i];
			}
			/*チェックディジットをつけた番号を入れ込む*/
			for(int j=0;j<cardnum.length;j++) {
				cardnumChk[j] = cardnum[j];
			}
			cardnumChk[cardnumChk.length-1] = ((10-(NWsum%10)))%10;
			return 0;
		}else {
			return 1;
		}
		
	}
}
