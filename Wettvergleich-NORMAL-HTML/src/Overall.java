import java.util.ArrayList;


public class Overall {

	ArrayList<ArrayList<Game>> mainGame = new ArrayList<ArrayList<Game>>();
	
	ArrayList<Game> mainList = new ArrayList<Game>();
	/*
	ArrayList<Game> pinnacleList = new ArrayList<Game>();
	ArrayList<Game> paddypowerList = new ArrayList<Game>();
	ArrayList<Game> thegreekList = new ArrayList<Game>();
	ArrayList<Game> wsexList = new ArrayList<Game>();
	*/
	
	public static void main(String[] args) {
		Overall overall = new Overall();
		overall.execute();
	}
	
	public void execute(){
		/*
		mainGame.add(pinnacleList);
		mainGame.add(paddypowerList);
		mainGame.add(thegreekList);
		mainGame.add(wsexList);
		*/
		
		
		
		//einmalige sachen
		ExecuteNFL executeNFL = new ExecuteNFL();
		//ChanceComparison chanceComparison = new ChanceComparison();
		//execute.threadsErstellen();
		
		//zu wiederholende sachen
		//execute.pinnacleThread.start();
		//execute.paddypowerThread.start();
		//execute.thegreekThread.start();
		//execute.wsexThread.start();
		//mainGame.add(execute.getPinnacleList());
		//mainGame.add(execute.getPaddypowerList());
		//mainGame.add(execute.getThegreekList());
		//mainGame.add(execute.getWsexList());
		//printMainList();
		
		//executeNFL.checkPinnacleNFL(mainList);
		//printArrayList();
		
		//executeNFL.checkSourcecodePrep();		
		//executeNFL.checkSourcecode();
		
		//System.out.println(executeNFL.fractionalToDecimal(1, 125));
		//System.out.println(executeNFL.americanToDecimal(135));

		
		//chanceComparison.compare(mainGame);
	}
	
	private void printMainList(){
		for(int k=0; k < mainGame.size();k++){
			for(int i=0; i< mainGame.get(k).size(); i++){
				System.out.println(mainGame.get(k).get(i).getTeam1() +" "+ mainGame.get(k).get(i).getChance1(0) +" "+ mainGame.get(k).get(i).getTeam2() +" "+ mainGame.get(k).get(i).getChance2(0));
			}
			System.out.println("----------------------------------------------------------------------");
		}
	}
	
	private void printArrayList(){
		for(int i=0; i< mainList.size(); i++){
			System.out.println(mainList.get(i).getTeam1() +" "+ mainList.get(i).getChance1(0) +" "+ mainList.get(i).getTeam2() +" "+ mainList.get(i).getChance2(0));
		}
	}

}
