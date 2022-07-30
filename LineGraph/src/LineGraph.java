import static java.lang.System.*;

import java.util.Scanner;
public class LineGraph {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		/*実際の各間の点数を順に記したdouble型配列*/
		double[] score = new double[9];
		/*グラフの点の位置を記録するint型2次元配列*/
		int[][] graph = new int[21][9];
		out.print("各科目の小数点を入力してください。\n");
		out.print("国語:");
		int Japanese = sc.nextInt();
		out.print("数学:");
		int Math = sc.nextInt();
		out.print("英語:");
		int English = sc.nextInt();
		
		/*double型配列scoreに点数と中間の点数を入れておく*/
		score[0] = Japanese;
		score[4] = Math;
		score[8] = English;
		/*国語と数学の間の増減率*/
		double JMtrend = (Math-Japanese)/4.0;
		for(int i=1;i<4;i++) {
			score[i] = Japanese + (JMtrend*i);
		}
		/*数学と英語の間の増減率*/
		double MEtrend = (English-Math)/4.0;
		for(int i=5;i<8;i++) {
			score[i] = Math + (MEtrend*(i-4));
		}
		/*グラフの点を記す縦方向の添字の数*/
		long[] subarray = subscript1(score);
		/*subarrayを使ってgraphに点の位置を記す(点の所に1)*/
		for(int k=0;k<subarray.length;k++) {
			graph[(int) ((graph.length)-(subarray[k])-1)][k] = 1;
		}
		/*1があるところには「*」ないところには空白を出力*/
		for(int j=0;j<graph.length;j++) {
			out.printf("%3d|", (100-5*j));
			for(int k=0;k<graph[j].length;k++) {
				if(graph[j][k]==1) {
					out.print("* ");
				}else {
					out.print("  ");
				}
			}
			out.print("\n");
		}
		out.print("\n");
		out.print("--------------------------\n");
		out.print("    国      数      英    \n");
	}
	
	/*数値ごとのグラフの点の場所*/
	static long[] subscript1(double[] array) {
		/*戻り値*/
		long[] subarray = new long[9];
		/*0から5ずつ数えて丸めた数になるまでのカウント*/
		int count=0;
		for(int i=0;i<subarray.length;i++) {
			/*小数点第1位を四捨五入*/
			long num1 = Math.round(array[i]);
			/*カウント初期化*/
			count=0;
			/*5ずつ減らす*/
			while(true){
				if(num1<5) {
					if(num1<=2) {
						break;
					}else if(num1>=3) {
						count++;
						break;
					}
				}
				num1-=5;
				count++;
			}
			/*カウント数をそれぞれの位置に格納*/
			subarray[i] = count;
		}
		return subarray;
	}
}
