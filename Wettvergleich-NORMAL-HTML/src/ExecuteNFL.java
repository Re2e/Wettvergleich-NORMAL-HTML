import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ExecuteNFL {

	public final int PINNACLE = 0;
	public final int PADDYPOWER = 1;
	public final int THEGREEK = 2;
	
	public float fractionalToDecimal(int up, int down){
		float tmp = (float) up/down+1;
		return tmp;
	}
	
	public float americanToDecimal(int val){
		float tmp;
		if(val > 0){
			tmp = (float) val/100+1;
		} else {
			tmp = (float) 100/(val*-1)+1;
		}
		return tmp;
	}
	
	
	public void checkPinnacleNFL(ArrayList<Game> pinnacleList){
		String surl = "http://www.pinnaclesports.com/League/Football/NFL/1/Lines.aspx";
		int beginTerm;
		int endTerm;
		int teamCount = 0;
		int chanceCount = 0;
		Game game = new Game(PINNACLE);
		String extract = "";
		
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         while ((str = in.readLine()) != null) {
	        	 if(str.contains("class=\"linesTeam\">")){
	        		beginTerm = str.indexOf("class=\"linesTeam\">") + 18;
	 	 			endTerm = str.indexOf("<", beginTerm);
	 	 			if(endTerm == -1){
	 	 				endTerm = str.length();
	 	 			}
	 	 			if(teamCount == 0){
	 	 				game.setTeam1(str.substring(beginTerm, endTerm));
	 	 				teamCount++;
	 	 			} else {
	 	 				game.setTeam2(str.substring(beginTerm, endTerm));
	 	 				teamCount = 0;
	 	 			}
	 	 			
	        	 }
	        	 if(str.contains("class=\"linesMLine\">&nbsp;&nbsp;&nbsp;")){
		 	 			beginTerm = str.indexOf("class=\"linesMLine\">&nbsp;&nbsp;&nbsp;") + 37;
		 	 			endTerm = str.indexOf("<", beginTerm);
		 	 			if(endTerm == -1){
		 	 				endTerm = str.length();
		 	 			}
		 	 			extract = str.substring(beginTerm, endTerm);
		 	 			if(chanceCount == 0){
		 	 				if(!(extract.startsWith("0") || extract.startsWith("1") || extract.startsWith("2") || extract.startsWith("3") || extract.startsWith("4") || extract.startsWith("5") || extract.startsWith("6") || extract.startsWith("7") || extract.startsWith("8") || extract.startsWith("9"))){
		 	 					game.setChance1((float) 0.0, game.DECIMAL);
		 	 				} else {
			 	 				game.setChance1(Float.parseFloat(str.substring(beginTerm, endTerm)), game.DECIMAL);
		 	 				}
		 	 				chanceCount++;
		 	 			} else {
		 	 				if(!(extract.startsWith("0") || extract.startsWith("1") || extract.startsWith("2") || extract.startsWith("3") || extract.startsWith("4") || extract.startsWith("5") || extract.startsWith("6") || extract.startsWith("7") || extract.startsWith("8") || extract.startsWith("9"))){
		 	 					game.setChance2((float) 0.0, game.DECIMAL);
		 	 				} else {
			 	 				game.setChance2(Float.parseFloat(str.substring(beginTerm, endTerm)), game.DECIMAL);
		 	 				}
		 	 				chanceCount = 0;
		 	 				
		 	 				if(game.differentEnough()){
		 	 					pinnacleList.add(game);
		 	 				}
		 	 				game = new Game(PINNACLE);
		 	 			}
	        	 	}

	        	 }
	         
	         in.close();
	      } catch (MalformedURLException e) {
	         System.out.println(e.getMessage());
	      } catch (IOException e) {
	         System.out.println(e.getMessage());
	      }
	}
	
	/*
	public void checkTheGreekNFL(ArrayList<Game> thegreekList){
		String surl = "http://www.thegreek.com/sportsbook/betting-lines/Football/NFL";
		int beginTerm;
		int endTerm;
		int teamCount = 0;
		Game game = new Game(THEGREEK);
		String extract = "";
		
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         while ((str = in.readLine()) != null) {
	        	 if(str.contains(("href=\"/sports/online/straight.asp?GameNum="))){
	        		 beginTerm = str.indexOf("\">")+2;
		 	 		 endTerm = str.indexOf("<", beginTerm);
		 	 		 extract = str.substring(beginTerm, endTerm);
		 	 		 
		 	 		 while(!(str = in.readLine()).contains("</tr>")){
		 	 		 
		 	 		  if((str.contains("<td width=45 align=right>"))){
		 	 			if(teamCount == 0){
		 	 				game.setTeam1(extract);
		 	 				endTerm = str.indexOf("<", 29);
		 	 				game.setCh1numerator(Integer.parseInt(str.substring(29,endTerm))); 
		 	 				if(game.getCh1numerator() > 0){
		 	 					game.setChance1((float) game.getCh1numerator()/100+1);
		 	 				} else {
		 	 					game.setChance1((float) 100/(game.getCh1numerator()*-1)+1);
		 	 				}
		 	 				teamCount++;
		 	 			} else {
		 	 				game.setTeam2(extract);
		 	 				endTerm = str.indexOf("<", 29);
		 	 				game.setCh2numerator(Integer.parseInt(str.substring(29,endTerm)));
		 	 				if(game.getCh2numerator() > 0){
		 	 					game.setChance2((float) game.getCh2numerator()/100+1);
		 	 				} else {
		 	 					game.setChance2((float) 100/(game.getCh2numerator()*-1)+1);
		 	 				}
		 	 				teamCount = 0;
		 	 				if(game.differentEnough()){
		 	 					thegreekList.add(game);
		 	 		        }
		 	 		        game = new Game(THEGREEK);
		 	 			} 
		 	 		  }
		 	 		 }
		 	 		
	        	 }
	        }
	         
	         in.close();
	      } catch (MalformedURLException e) {
	         System.out.println(e.getMessage());
	      } catch (IOException e) {
	         System.out.println(e.getMessage());
	      }
	}
	
	*/
	
	public void checkSourcecode(){
		String surl = "http://www.thegreek.com/sportsbook/betting-lines/Football/NFL";	
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         
	         System.out.println("-----------------------------------------sourcecode-----------------------------------");
	         
	         
	         while ((str = in.readLine()) != null) {
	        	 System.out.println(str);
	         }
	         
	         in.close();
	      } catch (MalformedURLException e) {
	         System.out.println(e.getMessage());
	      } catch (IOException e) {
	         System.out.println(e.getMessage());
	      }
	}
	
	public void checkSourcecodePrep(){
		int beginTerm;
		int endTerm;
		String extract = "";
		boolean runFurther = true;
		
		
		String surl = "http://www.thegreek.com/sportsbook/betting-lines/Football/NFL";	
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         while ((str = in.readLine()) != null) {
	        	 if((str.contains("<li class=\"name\""))){
	        		runFurther = true;
	        		 
	        		in.readLine();	
	        		if((str = in.readLine()).startsWith(" ")){
	        			System.out.println(str.substring(29, str.length()));
			 	 		System.out.println();
			 	 		
			 	 		while (runFurther) {
		        			str = in.readLine();
		        			if((str.contains("<li class=\"money-line \">"))){
		        		 		str = in.readLine();
		        		 		beginTerm = str.indexOf(">")+1;
		   		 	 		 	endTerm = str.indexOf("<", beginTerm);
		   		 	 		 	extract = str.substring(beginTerm, endTerm);
			        			System.out.println(extract);
					 	 		System.out.println();	
					 	 		
					 	 		runFurther = false;
		        			}
		        			
		        			//if contains money-line no-lines
		        			//dann wert auf 0 setzen
	        		}
	        		
	        		
	        		}
	        		
	        		
		 	 	}
	        	 /*
	        	 if((str.contains("<li class=\"money-line \">"))){
	        		 		str = in.readLine();
	        		 		beginTerm = str.indexOf(">")+1;
	   		 	 		 	endTerm = str.indexOf("<", beginTerm);
	   		 	 		 	extract = str.substring(beginTerm, endTerm);
		        			System.out.println(extract);
				 	 		System.out.println();			 	 		
			 	 	} 
	        	 */
	         }
	         
	         in.close();
	      } catch (MalformedURLException e) {
	         System.out.println(e.getMessage());
	      } catch (IOException e) {
	         System.out.println(e.getMessage());
	      }
	}
}
