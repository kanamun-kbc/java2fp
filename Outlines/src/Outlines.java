import static java.lang.System.*;
public class Outlines {
	static final int pic[][]= {
			{0,0,0,0,0,0,0,0,0,0,0,0,},
			{0,0,0,1,1,1,1,1,0,0,0,0,},
			{0,0,1,1,1,1,1,1,1,0,0,0,},
			{0,0,1,1,1,1,1,1,1,1,1,0,},
			{0,1,1,1,1,1,1,1,1,1,1,0,},
			{0,1,1,1,1,1,1,1,1,1,1,0,},
			{0,1,1,1,1,1,1,1,0,0,0,0,},
			{0,0,1,1,1,1,1,1,0,0,0,0,},
			{0,0,1,1,1,1,1,1,1,1,0,0,},
			{0,0,0,1,1,1,1,1,1,1,0,0,},
			{0,0,0,1,1,1,1,1,1,0,0,0,},
			{0,0,0,0,0,0,0,0,0,0,0,0,}};
	public static void main(String[] args) {
		/*輪郭を記すint型2次元配列*/
		int res[][] = new int[12][12];
		/*探索範囲の基準点の座標(配列の添字)*/
		/*列方向下向きにx軸, 行方向右向きにy軸, 原点はpic[0][0]*/
		int x = 0, y = 0;
		/*一番初めの探索基準点の座標を保存しておくint型変数*/
		int originx = 0, originy = 0;
		/*次の探索位置を示す番号, 左下から反時計回りに1～8*/
		int root = 1;
		/*初めの探索位置を探すためにラスタ走査する*/
		RASTA1:for(int i=0;i<pic.length;i++) {
			for(int j=0;j<pic[i].length;j++) {
				/*見つけたら*/
				if(pic[i][j]==1) {
					/*初めに見つけた1の場所に1を*/
					res[i][j] = 1;
					/*座標保存*/
					x = i;
					originx = i;
					y = j;
					originy = j;
					break RASTA1;
				}
			}
		}
		/*次の探索開始地点rootは探索し終わった方向から相対的に見て右*/
		/*(探索終了root,次の探索開始地点root)とするなら*/
		/*(1,7),(2,8),(3,1),(4,2),(5,3),(6,4),(7,5),(8,6)*/
		/*探索する→探索先の座標と次のルートを決める.これを続ける*/
		while(true) {
			switch(root) {
			case 1:
				if(pic[x+1][y-1]==1) {
					/*1があったら1*/
					res[x+1][y-1]=1;
					/*次の探索基準点の変更*/
					x = x+1;
					y = y-1;
					/*次の探索開始点の変更*/
					root = 7;
					/*switch文からの脱出(while文からは脱出しない)*/
					break;
				}
			case 2:
				if(pic[x+1][y]==1) {
					res[x+1][y]=1;
					x = x+1;
					root = 8;
					break;
				}
			case 3:
				if(pic[x+1][y+1]==1) {
					res[x+1][y+1]=1;
					x = x+1;
					y = y+1;
					root = 1;
					break;
				}
			case 4:
				if(pic[x][y+1]==1) {
					res[x][y+1]=1;
					y = y+1;
					root = 2;
					break;
				}
			case 5:
				if(pic[x-1][y+1]==1) {
					res[x-1][y+1]=1;
					x = x-1;
					y = y+1;
					root = 3;
					break;
				}
			case 6:
				if(pic[x-1][y]==1) {
					res[x-1][y]=1;
					x = x-1;
					root = 4;
					break;
				}
			case 7:
				if(pic[x-1][y-1]==1) {
					res[x-1][y-1]=1;
					x = x-1;
					y = y-1;
					root = 5;
					break;
				}
			case 8:
				if(pic[x][y-1]==1) {
					res[x][y-1]=1;
					y = y-1;
					root = 6;
					break;
				}
				/*8まで探索して何もなければroot=1に戻って探索*/
				root = 1;
			}
			/*終了条件, 探索先が初めの探索基準地点になったら(1周したら)*/
			if(x==originx && y==originy) {break;}
		}
		/*処理前の図形の出力*/
		out.print("処理前\n");
		for(int i=0;i<pic.length;i++) {
			for(int j=0;j<pic[i].length;j++) {
				if(pic[i][j]==0) {
					out.print('□');
				}else if(pic[i][j]==1) {
					out.print('■');
				}
			}
			out.print("\n");
		}
		/*処理後の図形の出力*/
		out.print("処理後\n");
		for(int i=0;i<res.length;i++) {
			for(int j=0;j<res[i].length;j++) {
				if(res[i][j]==0) {
					out.print('□');
				}else if(res[i][j]==1){
					out.print('■');
				}
			}
			out.print("\n");
		}
		
	}
}
