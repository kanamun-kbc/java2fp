import static java.lang.System.*;

import java.util.Random;
import java.util.Scanner;

public class Trigraph {
	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	/*数字一覧*/
	static final String[] NumberArray = {"0","1","2","3","4","5","6","7","8","9"};
	/*英大文字と英小文字の一覧*/
	static final String[][] EnglishArray = 
		{{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"},
		{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"}};
	
	public static void main(String[] args) {
		/*先に使う変数を用意*/
		/*大小数の3種類を分岐するint型変数*/
		int threeRoot = 0;
		/*20回の回答カウンタ*/
		int ansCount = 0;
		/*問題の3か所中の1か所(答えになる場所)(0～2)int型変数*/
		int lcr = 0;
		/*正解と回答*/
		String answer = "", playerAnswer = "";
		out.print("□ 連続する三つの数字あるいは英字から欠けている文字を入力してください。\n");
		out.print("□ たとえば A ? C : と表示されたらB を入力します。\n");
		out.print("□          4 5 ? : と表示されたら6 を入力します。\n");
		/*エンターキー押すとスタート*/
		out.print("エンターキーを押すと始まります");
		sc.nextLine();
		/*スタート*/
		long start = System.currentTimeMillis();
		/*20回正解してもらう*/
		do {
			threeRoot = rand.nextInt(3);
			/*共通部分として
			 * 並んだ3つの中での答えの場所
			 * 左中右は先に決めておく*/
			lcr = rand.nextInt(3);
			/*問題の出力*/
			switch(threeRoot) {
			/*大文字小文字*/
			case 0:
			case 1:
				/*取り出す3つ要素の左の添字番号*/
				int engleftNum = rand.nextInt(24);
				/*engleftNumとlcrで答えの場所が決まったので正解answerに格納*/
				answer = EnglishArray[threeRoot][engleftNum+lcr];
				/*表示*/
				for(int l=engleftNum;l<=engleftNum+2;l++) {
					if(l==engleftNum+lcr) {
						/*答えの場所はモザイク*/
						out.print(" ?");
					}else {
						out.print(" "+EnglishArray[threeRoot][l]);
					}
				}
				out.print(" :");
				break;
			/*数字*/
			case 2:
				/*取り出す3つ要素の左の添字番号*/
				int numleftNum = rand.nextInt(8);
				/*numleftNumとlcrで答えの場所が決まったので正解answerに格納*/
				answer = NumberArray[numleftNum+lcr];
				/*表示*/
				for(int k=numleftNum;k<=numleftNum+2;k++) {
					if(k==numleftNum+lcr) {
						/*答えの場所はモザイク*/
						out.print(" ?");
					}else {
						out.print(" "+NumberArray[k]);
					}
				}
				out.print(" :");
				break;
			}
			/*問題の出力終了*/
			do {
			/*ここから正解するまで答えてもらう*/
			playerAnswer = sc.nextLine();
				if(playerAnswer.equals(answer)) {
					/*正解したなら*/
					ansCount++;;
					break;
				}else {
					out.print(" miss!!:");
					continue;
				}
			}while(true);
		}while(ansCount<20);
		/*ストップ*/
		long end = System.currentTimeMillis();
		/*単位がmsなので秒に直すために*/
		double keika = (end - start)/1000.0;
		/*経過時間表示*/
		out.printf("%.1f秒かかりました。\n",keika);
		/*それによって早いか遅いか*/
		if(keika>50.0) {
			out.print("鈍すぎます。");
		}else if(keika>40.0) {
			out.print("少し鈍いですね。");
		}else if(keika>34.0) {
			out.print("まあまあですね。");
		}else {
			out.print("素早いですね。");
		}
	}
	
}
