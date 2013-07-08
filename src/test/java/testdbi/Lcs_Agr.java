package testdbi;

public class Lcs_Agr {

	static int c[][];
	static char b[][];
	public void lcs_Agrith(char X[],char Y[]){
		int m=X.length+1;
		int n=Y.length+1;
		int i,j;
		for(i=0;i<m;i++)
			c[i][0]=0;
		for(j=0;j<n;j++)
			c[0][j]=0;
		
		for(i=1;i<m;i++)
			for(j=1;j<n;j++){
				if(X[i-1]==Y[j-1]){
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]='$';//б����
				}
				else if(c[i-1][j]>=c[i][j-1]){
					c[i][j]=c[i-1][j];
					b[i][j]='^';//����
				}
				else{
					c[i][j]=c[i][j-1];
					b[i][j]='%';//����
				}
			}
	}
	
	public void print_LCS(char b[][],char X[],int i,int j){
		if(i==0||j==0) return;
		if(b[i][j]=='$'){
			print_LCS(b,X,i-1,j-1);
			System.out.println(X[i-1]);
		}
		else if(b[i][j]=='^'){
			print_LCS(b,X,i-1,j);
		}
		else{
			print_LCS(b,X,i,j-1);
		}
	}
	
	
	public static void main(String[] args) {
		char X[]={'A','B','C','B','D','A','B'};
		char Y[]={'B','D','C','A','B','A'};
		c=new int[10][10];
		b=new char[10][10];
		Lcs_Agr lcs=new Lcs_Agr();
		lcs.lcs_Agrith(X,Y);
		for(int i=0;i<=X.length;i++){
			for(int j=0;j<=Y.length;j++)
			{
				System.out.print(c[i][j]+" ");				
			}
			System.out.println();
		}
		lcs.print_LCS(b, X, X.length, Y.length);
	}

}
