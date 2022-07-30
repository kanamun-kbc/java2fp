import static java.lang.System.*;

import java.util.Random;
import java.util.Scanner;
public class WeekLearning {
	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	static final String [] DayEnglish= {"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
	static final String [] DayJapanese = {"日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"};
	public static void main(String[] args) {
		
		/*問題と回答がある配列の添字int型変数rand(7)*/
		int answerNum = 0;
		/*前回使った配列の添字を保存しておくint型変数*/
		int lastNum = rand.nextInt(7);
		/*回答を格納する変数String型*/
		String weekname;
		out.print("英語の曜日名を小文字で入力してください。\n");
		/*プレイヤーが続けるを判断している間は続ける*/
		do {
			/*答えとなる添字をダブらせないまでランダム数をとりまくる*/
				do {
					answerNum = rand.nextInt(7);
					/*前回使った添え字ならもう一度*/
					if(answerNum==lastNum) {
						continue;
					}else {
						/*使ってない添字ならそのまま,lastNumにそれを格納*/
						lastNum = answerNum;
						break;
					}
				}while(true);
			/*1つの問題が解決するまで*/
			do{
				/*問題の表示から始める*/
				out.print(DayJapanese[answerNum]+":");
				/*プレイヤーに回答してもらう*/
				weekname = sc.next();
				/*正解の時*/
				if(weekname.equals(DayEnglish[answerNum])) {
					out.print("正解です。\n");
					break;
				}else {
				/*不正解の時,再入力か正解を見るか*/
					out.print("違います。\nどうしますか？\n");
					out.print("1…再入力／0…正解を見る：");
					if(sc.nextInt()==1) {
						/*再入力*/
						continue;
					}else {
						/*正解を見る*/
						out.print(DayJapanese[answerNum]+"は\""+DayEnglish[answerNum]+"\"です。\n");
						break;
					}
				}
			}while(true);
			/*続けるかどうか*/
			out.print("1…続ける／0…終了：");
			if(sc.nextInt()==1) {
				/*続ける*/
				continue;
			}else {
				/*終了*/
				break;
			}
			
		}while(true);
		
		sc.close();
	}
}
