package day7;

public class Testing {

	public static void main(String[] args) {
		City houston = new City("Houston", 2_300_000);
		City sanAntonio = new City("San Antonio", 1_400_000);
		City dallas = new City("Dallas", 1_300_000);
		City austin = new City("Austin", 960_000);
		City fortWorth = new City("Fort Worth", 920_000);
		State texas = new State("Texas", 29_000_000, dallas, austin, fortWorth, houston, sanAntonio);
		
		City losAngeles = new City("Los Angeles", 3_900_000);
		City sanDiego = new City("San Diego", 1_400_000);
		City sanJose = new City("San Jose", 1_000_000);
		City sanFrancisco = new City("San Francisco", 870_000);
		City fresno = new City("Fresno", 540_000);
		State california = new State("California", 39_500_000, sanFrancisco, losAngeles, fresno, sanJose, sanDiego);
		
		City jacksonville = new City("Jacksonville", 930_000);
		City miami = new City("Miami", 480_000);
		City tampa = new City("Tampa", 400_000);
		City orlando = new City("Orlando", 290_000);
		City stPetersburg = new City("St. Petersburg", 270_000);
		State florida = new State("Florida", 21_500_000, miami, tampa, orlando, jacksonville, stPetersburg);
		
		City nyc = new City("New York City", 8_200_000);
		City buffalo = new City("Buffalo", 250_000);
		City rochester = new City("Rochester", 200_000);
		City yonkers = new City("Yonkers", 200_000);
		City syracuse = new City("Syracuse", 140_000);
		State newYork = new State("New York", 20_000_000, nyc, rochester, yonkers, buffalo, syracuse);
		
		City philadelphia = new City("Philadelphia", 1_500_000);
		City pittsburgh = new City("Pittsburgh", 300_000);
		City allentown = new City("Allentown", 120_000);
		City erie = new City("Erie", 94_000);
		City reading = new City("Reading", 88_000);
		State pennsylvania = new State("Pennsylvania", 13_000_000, erie, reading, philadelphia, allentown, pittsburgh);
		
		
	}
	
}
