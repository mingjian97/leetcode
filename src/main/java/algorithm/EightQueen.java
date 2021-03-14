package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangmingjian02
 * @create: 2021-03-14
 **/

class EightQueen {
    /**
     * 八皇后问题
     */
    public static void main(String[] args){
        List<List<String>> res = new EightQueen().solveNQueens(5);
        for(List<String> tmp:res){
            for(String s:tmp){
                System.out.println(s);
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] chars=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                chars[i][j]='.';
            }
        }
        backTrack(res,n,0,chars);
        return res;
    }

    public void backTrack(List<List<String>> res,int n,int row,char[][] chars){

        if(row==n){
            List<String> tmp = new ArrayList<>();
            for(int i=0;i<n;i++){
                tmp.add(new String(chars[i]));
            }
            res.add(tmp);
            return;
        }

        for(int col=0;col<n;col++){
            if(isValid(n,row,col,chars)){
                chars[row][col]='Q';
                backTrack(res,n,row+1,chars);
                chars[row][col]='.';
            }
        }
    }
    public boolean isValid(int n,int row,int col,char[][] chars){
        for(int i=row-1;i>=0;i--){
            char[] cs=chars[i];
            int leftj=col-(row-i);
            int rightj=col+(row-i);
            if(cs[col]=='Q'){
                return false;
            }
            if(leftj>=0&&cs[leftj]=='Q'){
                return false;
            }
            if(rightj<n&&cs[rightj]=='Q'){
                return false;
            }
        }
        return true;
    }


}

