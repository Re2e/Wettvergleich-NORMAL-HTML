import java.util.ArrayList;


public class ChanceComparison {

	public void compare(ArrayList<ArrayList<Game>> mainGame){
		int longest;
		
		//get longest Array to know the Array with "ALL" the games which is used to compare others with
		longest = getLongestArray(mainGame);
	}
	
	private int getLongestArray(ArrayList<ArrayList<Game>> mainGame){
		int number=0;
		int length=mainGame.get(0).size();
		for (int i=1; i< mainGame.size() ;i++){
			if(mainGame.get(i).size() > length){
				number = i;
				length = mainGame.get(i).size();
			}
		}
		return number;
	}

}
