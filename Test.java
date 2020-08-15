【编码题】字符串S由小写字母构成，长度为n。定义一种操作，每次都可以挑选字符串中任意的两个相邻字母进行交换。询问在至多交换m次之后，
字符串中最多有多少个连续的位置上的字母相同？


输入描述:
       第一行为一个字符串S与一个非负整数m。(1 <= |S| <= 1000, 1 <= m <= 1000000)
	   
输出描述:
       一个非负整数，表示操作之后，连续最长的相同字母数量。
示例1
        输入    abcbaa 2
        输出    2
		
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String line=sc.nextLine();
            String[] arr=line.split(" ");
            String str=arr[0];
            int m=Integer.parseInt(arr[1]);
            int res=1;
            for(char c='a';c<='z';c++){
                ArrayList<Integer> list=new ArrayList<>();
                for(int i=0;i<str.length();i++){
                    if(str.charAt(i)==c){
                        list.add(i);
                    }
                }
                
                int size=list.size();
                if(size<2) continue;
                
                int[][] dp=new int[size][size];
                for(int i=0;i<size-1;i++){
                    dp[i][i+1]=list.get(i+1)-list.get(i)-1;
                }
                
                for(int i=2;i<size;i++){
                    for(int left=0;left<size-i;left++){
                        int right=left+i;
                        dp[left][right]=dp[left+1][right-1]+(list.get(right)-list.get(left)-1)-(right-left-1);
                    }
                }
                
                for(int i=0;i<size;i++){
                    for(int j=i+1;j<size;j++){
                        if(dp[i][j]<=m){
                            res=Math.max(res,j-i+1);
                        }
                    }
                }
            }
            System.out.println(res);
        }
    }
}