import static java.lang.System.*;

import java.util.Scanner;
public class SeatReservation {
	static Scanner sc = new Scanner(System.in);;
	/*ベースとなる席の状況*/
	/*席自体の形は大型工事がない限りないと思われるためfinal*/
	static final int [][] state = {
				{1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
				{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0}};
	public static void main(String[] args) {
	/*入力する数値*/
	int num;;
	/*0が入力されるまで続ける*/
		do {
		/*一列の重みを設定*/
		/*席の価値, 添字+1が席番号になっている*/
		int [] weight = {0,1,2,3,4,5,6,7,7,6,5,4,3,2,1,0};
		/*実際に記入する席の状況*/
		int [][] seat = new int [8][16];
		/*初期段階
		 * 一列の座席の重みを設定(先に設定)*/
		 /* 全座席を予約可能にする
		 * (再度入力させる場合のためにシート初期化)
		 * (入力の際は, 0のところには0, 1のところには1)*/
			for(int i=0;i<8;i++) {
				for(int j=0;j<16;j++) {
					seat[i][j] = state[i][j];;
					if(state[i][j]==0) {
						seat[i][j] = 0;;
					}else if(state[i][j]==1){
						seat[i][j] = 1;;
					}
				}
			}
		/*入力段階
		 * 予約座席数を入力待ち, 0なら終了それ以外なら下へ続く
		 * (int型の数値を入力待ち, 後で使う. 0なら終了)*/
			do {
				out.print("予約座席数を入力(1-16, 0:end) :");
				num = sc.nextInt();
				if(num<0) {
					out.print("負数が入力されました。\n正しい席数を入力してください。\n");;
					continue;
				}else if(num>16) {
					out.print("連続する席を予約できませんでした。\n");
					continue;
				}else {
					break;
				}
			}while(true);
			/*終了条件*/
			if(num==0) {
					out.print("終了します。");;
					break;
			}
		 /* 座席の前列から順番に"連続する座席"が確保できるかを調べる
		 * (列と確保できる席数の関係は
		 * (1,8),(2,12),(3,14),(4,16),(5,16),(6,16),(7,5),(8,5))
		 * 実質使うのは1～4列ではなかろうか
		 * (4,5,6列目の優先すべきは4>5>6, 7,8列目の優先度は7>8)*/
			/*座席数枠*/
			int[] zasekitotal = {8,12,14,16};
			/*4～,2～,1～,0～*/
			/*シート列変数
			 * 列番号retu
			 * 16超過の数字を入力されたらretuを0へ
			 * 確保できる席がないことを表示するつもり
			 * retu0のときは席確保がされないようなカウント変数にする*/
			int retu=0;
			if(num>8) {
				if(num>12) {
					if(num>14) {
						retu++;;
					}
					retu++;;
				}
				retu++;;
			}
		 /* (確保できる席の列がわかったら, 重さと添字とで比較, 
		 * 座席数num分繰り返す ((ここがやらせたい内容なのだと思う))
		 * (大きい重さの部分の添字番号を抽出し, それを列の席番号(添字)に利用)*/
			/*比較中に暫定で重いweight[j]のjを保存するint型変数*/
			int seki=0;;
			/*重さ比較用に暫定で重いweight[j]を保存するint型変数*/
			int weightNum = 0;
			for(int i=0;i<num;i++) {
				/*暫定添え字*/
				seki = 0;;
				/*暫定重さ*/
				weightNum = -1;
				for(int j=0;j<weight.length;j++) {
					if(weightNum<weight[j]) {
						seki=j;
						weightNum=weight[j];
					}
				}
				/*使われた重い席の所に-2を入れてかぶらないようにする*/
				weight[seki] = -2;;
				/*座られる席には-1を入れておく*/
				seat[retu][seki] = -1;;
			}
		/* 〇列〇番から〇席とシート状況を出力する
		 * (列の添字番号と席(重さ)の添字の最小最大)
		 * (出力の際は, 0のところには0, 1のところには-)
		 * (数値が16よりも大きい場合かな?)*/
			int leftseki = 0;;
			/*一番左の席の番号判定*/
			for(int k=0;k<8;k++) {
				if(seat[retu][k]==-1) {
					leftseki = k+1;;
					break;
				}
			}
			out.print((retu+1)+"列"+leftseki+"番から"+num+"席予約しました。\n");;
			for(int l=0;l<8;l++) {
				for(int m=0;m<16;m++) {
					if(seat[l][m]==1) {
						/*使っちゃダメな席*/
						out.print("-");;
					}else if(seat[l][m]==0){
						/*空席*/
						out.print("0");;
					}else if(seat[l][m]==-1) {
						/*確保された席*/
						out.print("1");
					}
				}
				out.print("\n");;
			}
			
			out.print("\n");;
			
		}while(true);
	}
}
