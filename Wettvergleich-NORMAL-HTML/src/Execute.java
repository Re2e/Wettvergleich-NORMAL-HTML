import java.io.*;
import java.net.*;
import java.util.*;

public class Execute {
	
	public final int PINNACLE = 0;
	public final int PADDYPOWER = 1;
	public final int THEGREEK = 2;
	public final int WSEX = 3;
	public Thread pinnacleThread;
	public Thread paddypowerThread;
	public Thread thegreekThread;
	public Thread wsexThread;
	ArrayList<Game> pinnacleList = new ArrayList<Game>();
	ArrayList<Game> paddypowerList = new ArrayList<Game>();
	ArrayList<Game> thegreekList = new ArrayList<Game>();
	ArrayList<Game> wsexList = new ArrayList<Game>(); 
	private Boolean donePinncale = false;
	private Boolean donePaddyPower = false;
	private Boolean doneTheGreek = false;
	private Boolean doneWSEX = false;
	
	public ArrayList<Game> getPinnacleList() {
		while(!donePinncale){
			;
		}
		return pinnacleList;
	}

	public ArrayList<Game> getPaddypowerList() {
		while(!donePaddyPower){
			;
		}
		return paddypowerList;
	}

	public ArrayList<Game> getThegreekList() {
		while(!doneTheGreek){
			;
		}
		return thegreekList;
	}

	public ArrayList<Game> getWsexList() {
		while(!doneWSEX){
			;
		}
		return wsexList;
	}
	
	/*

	public void threadsErstellen(){
		
		Runnable checkpinnacle = new Runnable(){
			public void run() {	
				donePinncale = false;
				String surl = "http://www.pinnaclesports.com/League/Basketball/NBA/1/Lines.aspx";
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
				 	 					game.setChance1((float) 0.0);
				 	 				} else {
					 	 				game.setChance1(Float.parseFloat(str.substring(beginTerm, endTerm)));
				 	 				}
				 	 				chanceCount++;
				 	 			} else {
				 	 				if(!(extract.startsWith("0") || extract.startsWith("1") || extract.startsWith("2") || extract.startsWith("3") || extract.startsWith("4") || extract.startsWith("5") || extract.startsWith("6") || extract.startsWith("7") || extract.startsWith("8") || extract.startsWith("9"))){
				 	 					game.setChance2((float) 0.0);
				 	 				} else {
					 	 				game.setChance2(Float.parseFloat(str.substring(beginTerm, endTerm)));
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
			      donePinncale = true;
			}	
		};
		// ENDE von Pinncle -----------------------------------------------------------------------
		
		Runnable checkpaddypower = new Runnable(){
			public void run() {	
				donePaddyPower = false;
				String surl = "http://www.paddypower.com/bet/basketball/us-basketball/nba-matches";
				int teamCount = 0;
				int chanceCount = 0;
				int slash = 0;
				Game game = new Game(PADDYPOWER);
				
				 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
			      try {
			         URL url = new URL(surl);
			         URLConnection conn = url.openConnection();
			         conn.addRequestProperty("User-Agent", userAgent);
			 
			         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			         String str;
			         while ((str = in.readLine()) != null) {
			        	 if(str.startsWith("												return false\"")){
			        		 in.readLine();
			        		 in.readLine();
			        		 str = in.readLine();
			        		 if(str.length() > 10 ){
			        			 if(teamCount == 0){
			        				game.setTeam1( str.substring(10, str.length()));
				 		 	 		teamCount++;
				 		 	 	 } else {
					 	 			game.setTeam2(str.substring(10, str.length()));
					 	 			teamCount = 0;
					 	 		 }
			        	     }
			        				 
			        	 }
			        	 
			        	 if(str.startsWith("										<a class=\"tipper\" href=\"javascript:void(0)\">")){
			        		 str = in.readLine();
			        		 str = str.substring(11, str.length());
			        		 if(!(str.startsWith("0") || str.startsWith("1") || str.startsWith("2") || str.startsWith("3") || str.startsWith("4") || str.startsWith("5") || str.startsWith("6") || str.startsWith("7") || str.startsWith("8") || str.startsWith("9"))){
			        			 	checkPaddyPowerNBA(paddypowerList);   //hier evtl die Parameter mit ubergebenm, die wichtig sind 
			        		 }
		     			 	 slash = str.indexOf("/");
			        		 if(chanceCount == 0){
			        			 	game.setCh1numerator(Integer.parseInt(str.substring(0, slash)));
			        			 	game.setCh1denuminator(Integer.parseInt(str.substring(slash+1, str.length())));
			        			 	game.setChance1((float) game.getCh1numerator()/game.getCh1denuminator()+1);
			        			 	
			        			 	
			        			 	
				 	 				chanceCount++;
				 	 		 } else {
				 	 			 	game.setCh2numerator(Integer.parseInt(str.substring(0, slash)));
				 	 			 	game.setCh2denuminator(Integer.parseInt(str.substring(slash+1, str.length())));
				 	 			 	game.setChance2((float) game.getCh2numerator()/game.getCh2denuminator()+1);
				 	 				chanceCount = 0;
				 	 				
				 	 				if(game.differentEnough()){
				 	 					paddypowerList.add(game);
				 	 		        }
				 	 		        game = new Game(PADDYPOWER);
				 	 		 }
			        	 }
			        }
			         
			         in.close();
			      } catch (MalformedURLException e) {
			         System.out.println(e.getMessage());
			      } catch (IOException e) {
			         System.out.println(e.getMessage());
			      }
			      donePaddyPower = true;
			}
		};
		// ENDE von PaddyPower ---------------------------------------------------------------------
		Runnable checkthegreek = new Runnable(){
			public void run() {	
				doneTheGreek = false;
				String surl = "http://www.thegreek.com/sports/basketball/basklines.asp?selection=subtype&subtype=NBA";
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
			      doneTheGreek = true;
			}
		};
		// ENDE von TheGreek ------------------------------------------------------------------------
		
		Runnable checkwsex = new Runnable(){
			public void run() {	
				doneWSEX = false;
				String surl = "http://www.wsex.com/line/PRO-BASKETBALL.html";
				int beginTerm;
				int endTerm;
				int teamCount = 0;
				int targetCount = 0;
				Game game = new Game(WSEX);
				
				 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
			      try {
			         URL url = new URL(surl);
			         URLConnection conn = url.openConnection();
			         conn.addRequestProperty("User-Agent", userAgent);
			 
			         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			         String str;
			         while ((str = in.readLine()) != null) {
			        	 if(str.startsWith("<tr class=lines-contest-oddrow>")){
			        		 str = in.readLine();
			        		 game.setTeam1(str.substring(4));
			        		 targetCount = 0;
			        	 }
			        	 if(str.startsWith("<tr class=lines-contest-evenrow>")){
			        		 str = in.readLine();
			        		 game.setTeam2(str.substring(4));
			        		 targetCount = 0;
			        	 }
			        	 if(str.contains("target=action>")){
			        		 	targetCount++;
			        		    beginTerm = str.indexOf("target=action>") + 14;
				 	 			endTerm = str.indexOf("<", beginTerm);
				 	 			if(targetCount == 3){
				 	 				if(teamCount == 0){
				 	 					game.setCh1numerator(Integer.parseInt(str.substring(beginTerm, endTerm)));
				 	 					if(game.getCh1numerator() > 0){
				 	 						game.setChance1((float) game.getCh1numerator()/100+1);
				 	 					} else {
				 	 						game.setChance1((float) 100/(game.getCh1numerator()*-1)+1);
				 	 					}
					 	 				teamCount++;
					 	 			} else {
					 	 				game.setCh2numerator(Integer.parseInt(str.substring(beginTerm, endTerm)));
					 	 				if(game.getCh2numerator() > 0){
				 	 						game.setChance2((float) game.getCh2numerator()/100+1);
				 	 					} else {
				 	 						game.setChance2((float) 100/(game.getCh2numerator()*-1)+1);
				 	 					}
					 	 				teamCount = 0;
					 	 				if(game.differentEnough()){
					 	 					wsexList.add(game);
					 	 				}
					 	 				game = new Game(WSEX);
					 	 			}
				 	 				targetCount = 0;
				 	 			}	
			        	 }
			        }
			         
			         in.close();
			      } catch (MalformedURLException e) {
			         System.out.println(e.getMessage());
			      } catch (IOException e) {
			         System.out.println(e.getMessage());
			      }
			      doneWSEX = true;
			}
		};
		// ENDE von WSEX-----------------------------------------------------------------------------
		pinnacleThread = new Thread(checkpinnacle);
		paddypowerThread = new Thread(checkpaddypower);
		thegreekThread = new Thread(checkthegreek);
		wsexThread = new Thread(checkwsex);
	}
	
	public void checkWebsites(ArrayList<Game> pinnacleList, ArrayList<Game> paddypowerList, ArrayList<Game> thegreekList, ArrayList<Game> wsexList){
		/*
		checkPinnacle(pinnacleList);
		checkPaddyPower(paddypowerList);
		checkTheGreek(thegreekList);      
		checkWSEX(wsexList);            
		//printMainList();
		
		
		Runnable zaehler = new Runnable(){
			public void run() {	
				System.out.println("In run");
			}	
		};
		System.out.println("warten");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("gewartet");
		Thread zaehl = new Thread(zaehler);
		zaehl.start();
		/*
		Game game = new Game(PINNACLE);
		game.setTeam1("Pin");
		pinnacleList.add(game);
		
		game = new Game(THEGREEK);
		game.setTeam1("greek");
		thegreekList.add(game);
		
	}
	
	private void checkPinnacleNBA(ArrayList<Game> pinnacleList){
		String surl = "http://www.pinnaclesports.com/League/Basketball/NBA/1/Lines.aspx";
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
		 	 					game.setChance1((float) 0.0);
		 	 				} else {
			 	 				game.setChance1(Float.parseFloat(str.substring(beginTerm, endTerm)));
		 	 				}
		 	 				chanceCount++;
		 	 			} else {
		 	 				if(!(extract.startsWith("0") || extract.startsWith("1") || extract.startsWith("2") || extract.startsWith("3") || extract.startsWith("4") || extract.startsWith("5") || extract.startsWith("6") || extract.startsWith("7") || extract.startsWith("8") || extract.startsWith("9"))){
		 	 					game.setChance2((float) 0.0);
		 	 				} else {
			 	 				game.setChance2(Float.parseFloat(str.substring(beginTerm, endTerm)));
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
	 
	private void checkPaddyPowerNBA(ArrayList<Game> paddypowerList){
		String surl = "http://www.paddypower.com/bet/basketball/us-basketball/nba-matches";
		int teamCount = 0;
		int chanceCount = 0;
		int slash = 0;
		Game game = new Game(PADDYPOWER);
		
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         while ((str = in.readLine()) != null) {
	        	 if(str.startsWith("												return false\"")){
	        		 in.readLine();
	        		 in.readLine();
	        		 str = in.readLine();
	        		 if(str.length() > 10 ){
	        			 if(teamCount == 0){
	        				game.setTeam1( str.substring(10, str.length()));
		 		 	 		teamCount++;
		 		 	 	 } else {
			 	 			game.setTeam2(str.substring(10, str.length()));
			 	 			teamCount = 0;
			 	 		 }
	        	     }
	        				 
	        	 }
	        	 
	        	 if(str.startsWith("										<a class=\"tipper\" href=\"javascript:void(0)\">")){
	        		 str = in.readLine();
	        		 str = str.substring(11, str.length());
	        		 if(!(str.startsWith("0") || str.startsWith("1") || str.startsWith("2") || str.startsWith("3") || str.startsWith("4") || str.startsWith("5") || str.startsWith("6") || str.startsWith("7") || str.startsWith("8") || str.startsWith("9"))){
	        			 	checkPaddyPowerNBA(paddypowerList);   //hier evtl die Parameter mit ubergebenm, die wichtig sind 
	        		 }
     			 	 slash = str.indexOf("/");
	        		 if(chanceCount == 0){
	        			 	game.setCh1numerator(Integer.parseInt(str.substring(0, slash)));
	        			 	game.setCh1denuminator(Integer.parseInt(str.substring(slash+1, str.length())));
	        			 	game.setChance1((float) game.getCh1numerator()/game.getCh1denuminator()+1);
	        			 	
	        			 	
	        			 	
		 	 				chanceCount++;
		 	 		 } else {
		 	 			 	game.setCh2numerator(Integer.parseInt(str.substring(0, slash)));
		 	 			 	game.setCh2denuminator(Integer.parseInt(str.substring(slash+1, str.length())));
		 	 			 	game.setChance2((float) game.getCh2numerator()/game.getCh2denuminator()+1);
		 	 				chanceCount = 0;
		 	 				
		 	 				if(game.differentEnough()){
		 	 					paddypowerList.add(game);
		 	 		        }
		 	 		        game = new Game(PADDYPOWER);
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
	
	private void checkTheGreekNBA(ArrayList<Game> thegreekList){
		String surl = "http://www.thegreek.com/sports/basketball/basklines.asp?selection=subtype&subtype=NBA";
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
	
	
	private void checkWSEXNBA(ArrayList<Game> wsexList){

		String surl = "http://www.wsex.com/line/PRO-BASKETBALL.html";
		int beginTerm;
		int endTerm;
		int teamCount = 0;
		int targetCount = 0;
		Game game = new Game(WSEX);
		
		 final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12";
	      try {
	         URL url = new URL(surl);
	         URLConnection conn = url.openConnection();
	         conn.addRequestProperty("User-Agent", userAgent);
	 
	         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String str;
	         while ((str = in.readLine()) != null) {
	        	 if(str.startsWith("<tr class=lines-contest-oddrow>")){
	        		 str = in.readLine();
	        		 game.setTeam1(str.substring(4));
	        		 targetCount = 0;
	        	 }
	        	 if(str.startsWith("<tr class=lines-contest-evenrow>")){
	        		 str = in.readLine();
	        		 game.setTeam2(str.substring(4));
	        		 targetCount = 0;
	        	 }
	        	 if(str.contains("target=action>")){
	        		 	targetCount++;
	        		    beginTerm = str.indexOf("target=action>") + 14;
		 	 			endTerm = str.indexOf("<", beginTerm);
		 	 			if(targetCount == 3){
		 	 				if(teamCount == 0){
		 	 					game.setCh1numerator(Integer.parseInt(str.substring(beginTerm, endTerm)));
		 	 					if(game.getCh1numerator() > 0){
		 	 						game.setChance1((float) game.getCh1numerator()/100+1);
		 	 					} else {
		 	 						game.setChance1((float) 100/(game.getCh1numerator()*-1)+1);
		 	 					}
			 	 				teamCount++;
			 	 			} else {
			 	 				game.setCh2numerator(Integer.parseInt(str.substring(beginTerm, endTerm)));
			 	 				if(game.getCh2numerator() > 0){
		 	 						game.setChance2((float) game.getCh2numerator()/100+1);
		 	 					} else {
		 	 						game.setChance2((float) 100/(game.getCh2numerator()*-1)+1);
		 	 					}
			 	 				teamCount = 0;
			 	 				if(game.differentEnough()){
			 	 					wsexList.add(game);
			 	 				}
			 	 				game = new Game(WSEX);
			 	 			}
		 	 				targetCount = 0;
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
	
	/*
	private void printMainList(){
		for(int i=0; i< mainList.size(); i++){
			System.out.println(mainList.get(i).getTeam1() +" "+ mainList.get(i).getChance1() +" "+ mainList.get(i).getTeam2() +" "+ mainList.get(i).getChance2());
		}
	}
    */

}
