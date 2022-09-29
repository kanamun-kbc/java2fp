import static java.lang.System.*;

import java.util.Random;
import java.util.Scanner;

public class BlackJack {
	/*基本21点以下で最高点が勝ち
	 * 親と子が等しいなら親の勝ち, 互いに21以上(BUST)も親の勝ち
	 * 2～9はその数字
	 * 10,11(J),12(Q),13(K)は10点とみなす
	 * 1(A)は1(その他合計10超過)か11(その他合計10以下)とみなす
	 * 親は17以上になるまで引き続ける
	 * こっちが引き続ける間, 相手のカードを一枚のみ見れる
	 * */
	
	/*52枚のトランプの管理
	 *  使われたトランプはダブってはいけない
	 *   使われたトランプのライブラリを作るといいかも
	 * 点数の管理
	 *  10～13は10点, 1は他のカード次第で1または11
	 *   親と子の合計点を作り更新, 判定の仕方を考える
	 * */
	
	/*オブジェクト指向として
	 * トランプは文字列としてフィールドに用意しておく(表示用にも使うから)
	 * 行動は子が行い親が行う
	 * ランダム数2つをとり, トランプの柄と数字を取り出す
	 * その数字の組み合わせが同じものがとってしまうようなら, 
	 * ランダム数をとり続ける
	 * 引数合計点と何かを取り, (1をとった場合のメソッド)合計点の変更を行う?
	 * 合計点は
	 * 子に対しては20以下問い続ける(選択肢あり)
	 * 親に対しては17未満なら問い続ける
	 * MAX点数21点
	 * それぞれのトランプ配列にトランプをつなげていくメソッド
	 * */
	static final String[][] cardArray = 
			{{"[♠A]","[♠2]","[♠3]","[♠4]","[♠5]","[♠6]","[♠7]","[♠8]","[♠9]","[♠10]","[♠J]","[♠Q]","[♠K]"},
			{"[♥A]","[♥2]","[♥3]","[♥4]","[♥5]","[♥6]","[♥7]","[♥8]","[♥9]","[♥10]","[♥J]","[♥Q]","[♥K]"},
			{"[♦A]","[♦2]","[♦3]","[♦4]","[♦5]","[♦6]","[♦7]","[♦8]","[♦9]","[♦10]","[♦J]","[♦Q]","[♦K]"},
			{"[♣A]","[♣2]","[♣3]","[♣4]","[♣5]","[♣6]","[♣7]","[♣8]","[♣9]","[♣10]","[♣J]","[♣Q]","[♣K]"}};
	static final int[] cardScore = {1,2,3,4,5,6,7,8,9,10,10,10,10};
	static Random rand = new Random();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		/*プレイヤーの点数myScore,コンピュータの点数cpScore
		 * エース以外の点数を数える
		 * エースの点数はmyAce,cpAceとの調整を行う*/
		int myScore=0, cpScore=0;
		/*カードの柄がnum1,カード番号がnum2
		 * それぞれランダムで添え字*/
		int num1=0, num2=0;
		/*プレイヤーのエースの枚数myAce,コンピュータのエースの枚数cpAce*/
		int myAce=0, cpAce=0;
		/*エースの計算も加えたそれぞれの合計値*/
		int myTotal=0, cpTotal=0;
		/*使われていないカードは-1,
		 * 使われているカードは0とする*/
		int[][] randomNum = {{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,}};
		/*公開手札一覧*/
		StringBuilder myCard = new StringBuilder();
		StringBuilder cpCard = new StringBuilder();
		/*プレイヤーのターンが終わるまでの公開手札*/
		StringBuilder hidecpCard = new StringBuilder();
		
		/*初めのカード2枚配布コンピュータ*/
		for(int i=0;i<2;) {
			i++;;
			num1 = rand.nextInt(4);
			num2 = rand.nextInt(13);
			if(randomNum[num1][num2]==-1) {
				cpCard.append(cardArray[num1][num2]);
				cpScore+=cardScore[num2];
				if(i==1) {
					hidecpCard.append(cardArray[num1][num2]);
				}else {
					hidecpCard.append("[**]");
				}
				/*引いたカードは使われないように*/
				randomNum[num1][num2] = 0;
				/*エースの枚数カウンタ*/
				if(num2==0) {
					cpAce++;
					cpScore--;
				}
			}else {
				i--;
				continue;
			}
		}
		/*初めのカード2枚配布プレイヤー*/
		for(int i=0;i<2;) {
			i++;;
			num1 = rand.nextInt(4);
			num2 = rand.nextInt(13);
			if(randomNum[num1][num2]==-1) {
				myCard.append(cardArray[num1][num2]);
				myScore+=cardScore[num2];
				/*引いたカードは使われないように*/
				randomNum[num1][num2] = 0;
				/*エースの枚数カウンタ*/
				if(num2==0) {
					myAce++;
					myScore--;
				}
			}else {
				i--;
				continue;
			}
		}
		/*初めの表示*/
		out.print("ブラックジャック\n2枚配布します\nプレイヤーのターンが終わるまで親の手は一枚のみ公開されます\n");
		out.print("親はプレイヤーの手札を見ることができません\n");
		out.print("コンピュータの手:"+hidecpCard+"\n");
		out.print("プレイヤーの手  :"+myCard+"\n\n");
		/*プレイヤーのターン*/
		/*ヒットするかどうかのジャッジ*/
		String hit = "";
		do {
			out.print("もう1枚引きますか?(Y(ヒット)/N(スタンド)):");
			while(true) {
				hit=sc.nextLine();
				if(hit.equals("Y") || hit.equals("N")) {
					break;
				}else {
					out.print("入力ミスです。もう1枚引きますか?(Y(ヒット)/N(スタンド)):");
				}
			}
			if(hit.equals("Y")) {
				while(true){
		 			num1 = rand.nextInt(4);
					num2 = rand.nextInt(13);
					if(randomNum[num1][num2]==-1) {
						myCard.append(cardArray[num1][num2]);
						randomNum[num1][num2] = 0;
						myScore+=cardScore[num2];
						if(num2==0) {
							myAce++;
							myScore--;
						}
						break;
					}else {
						continue;
					}
				}
				out.print("コンピュータの手:"+hidecpCard+"\n");
				out.print("プレイヤーの手  :"+myCard+"\n\n");
			}else {
				break;
			}
		}while(hit.equals("Y"));
		/*プレイヤーがスタンドしたところから
		 * 一旦途中結果の表示
		 * 合計値を調整する必要がある*/
		/*コンピュータの合計値計算*/
		if(cpAce>0 && cpScore<=10) {
			cpTotal=cpAce+10+cpScore;
		}else if(cpAce>0 && cpScore>10){
			cpTotal=cpAce+cpScore;
		}else if(cpAce==0){
			cpTotal=cpScore;
		}
		/*プレイヤーの合計値計算*/
		if(myAce>0 && myScore<=10) {
			myTotal=myAce+10+myScore;
		}else if(myAce>0 && myScore>10){
			myTotal=myAce+myScore;
		}else if(myAce==0){
			myTotal=myScore;
		}
		out.print("コンピュータの手:"+cpCard+"="+cpTotal+"\n");
		out.print("プレイヤーの手  :"+myCard+"="+myTotal+"\n\n");
		/*ここでプレイヤーのターンが終了*/
		
		/*ここからコンピュータのターンが始まる*/
		/*コンピュータは合計値が17未満の間は引き続ける*/
		while(cpTotal<17){
			out.print("コンピュータが1枚引きます。(Enterキーで表示)");
			sc.nextLine();
			/*コンピュータのヒット*/
			while(true){
	 			num1 = rand.nextInt(4);
				num2 = rand.nextInt(13);
				if(randomNum[num1][num2]==-1) {
					cpCard.append(cardArray[num1][num2]);
					randomNum[num1][num2] = 0;
					cpScore+=cardScore[num2];
					if(num2==0) {
						cpAce++;
						cpScore--;
					}
					break;
				}else {
					continue;
				}
			}
			/*コンピュータの合計値計算*/
			if(cpAce>0 && cpScore<=10) {
				cpTotal=cpAce+10+cpScore;
			}else if(cpAce>0 && cpScore>10){
				cpTotal=cpAce+cpScore;
			}else if(cpAce==0){
				cpTotal=cpScore;
			}
			out.print("コンピュータの手:"+cpCard+"="+cpTotal+"\n");
			out.print("プレイヤーの手  :"+myCard+"="+myTotal+"\n\n");
		}
		/*ここでコンピュータがスタンド*/
		/*最終結果発表
		 * 合計点の調整を行う必要がある
		 * バーストしているか
		 * どちらが勝っているか*/
		out.print("コンピュータが終了しました。(Enterキーで表示)");
		sc.nextLine();
		out.print("***結果***\n");
		out.print("コンピュータの手:"+cpCard+"\n");
		out.print("プレイヤーの手  :"+myCard+"\n");
		/*バースト判定*/
		if(myTotal>21) {myTotal=0;}
		if(cpTotal>21) {cpTotal=0;}
		/*点数表示*/
		out.print("コンピュータ "+cpTotal+"点  プレイヤー "+myTotal+"点\n");
		/*比較
		 * 大きいほうが勝つ
		 * 同じ値ならコンピュータの勝ち*/
		if(cpTotal>=myTotal) {
			out.print("【あなたの負けです】");
		}else {
			out.print("【あなたの勝ちです】");
		}
		
	}
}
