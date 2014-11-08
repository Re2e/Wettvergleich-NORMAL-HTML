
public class Game {
	
	public final int DECIMAL = 0;
	public final int AMERICAN = 1;
	public final int FRACTIONALup = 2;
	public final int FRACTIONALdown = 3;
	
	//private int id;
	private int version;
	private int website;
	private String team1;
	private String team2;
	/*
	private float chance1decimal;
	private float chance2decimal;
	private int ch1numerator;
	private int ch1denuminator;
	private int ch2numerator;
	private int ch2denuminator;
	private int chance1american;
	private int chance2american;
	*/
	private int usedSystem;
	private float[] chance1;
	private float[] chance2;
	
	
	public Game() {
		chance1 = new float[4];
		chance2 = new float[4];
	}
	
	public Game(int version){
		chance1 = new float[4];
		chance2 = new float[4];
		
		this.version = version;
	}
	
	public Game(int website, String team1, String team2, float chance1, float chance2, int usedSystem){
		this.chance1 = new float[4];
		this.chance2 = new float[4];
		
		this.website = website;
		this.team1 = team1;
		this.team2 = team2;
		this.chance1[usedSystem] = chance1;
		this.chance2[usedSystem] = chance2;
		this.usedSystem = usedSystem;
	}
	
	public float getChance1(int systemNo) {
		return chance1[systemNo];
	}
	
	public float getChance2(int systemNo) {
		return chance2[systemNo];
	}
	
	public void setChance1(float chance, int systemNo){
		chance1[systemNo] = chance;
	}
	
	public void setChance2(float chance, int systemNo){
		chance2[systemNo] = chance;
	}
	
	/*
	public int getCh1numerator() {
		return ch1numerator;
	}

	public void setCh1numerator(int ch1numerator) {
		this.ch1numerator = ch1numerator;
	}

	public int getCh1denuminator() {
		return ch1denuminator;
	}

	public void setCh1denuminator(int ch1denuminator) {
		this.ch1denuminator = ch1denuminator;
	}

	public int getCh2numerator() {
		return ch2numerator;
	}

	public void setCh2numerator(int ch2numerator) {
		this.ch2numerator = ch2numerator;
	}

	public int getCh2denuminator() {
		return ch2denuminator;
	}

	public void setCh2denuminator(int ch2denuminator) {
		this.ch2denuminator = ch2denuminator;
	}
	
	*/
	
	public int getWebsite() {
		return website;
	}

	public String getTeam1() {
		return team1;
	}

	public String getTeam2() {
		return team2;
	}
	
	/*

	public float getChance1decimal() {
		return chance1decimal;
	}

	public float getChance2decimal() {
		return chance2decimal;
	}
	
	public float getChance1american() {
		return chance1decimal;
	}

	public float getChance2american() {
		return chance2decimal;
	}
	
	*/

	public void setWebsite(int website) {
		this.website = website;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	
	/*

	public void setChance1decimal(float chance1) {
		this.chance1decimal = chance1;
	}

	public void setChance2decimal(float chance2) {
		this.chance2decimal = chance2;
	}
	
	public void setChance1american(int chance1) {
		this.chance1american = chance1;
	}

	public void setChance2american(int chance2) {
		this.chance2american = chance2;
	}
	
	*/
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean differentEnough() {
		if(Math.abs(chance1[this.DECIMAL] - chance2[this.DECIMAL]) > 0.01){	
			return true;
		} else {
			return false;
		}
	}

}
