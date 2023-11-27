package boxOffice;

import java.util.Map;

import java.util.HashMap;

import boxOffice.Seat;

public class Venue {
    private Map<String, Seat> seats;


    public Venue(String seatConfig){
        seats = new HashMap<>();
        String lines[] = seatConfig.split("\n");
        int numRows = Integer.parseInt(lines[0]);

        for(int i = 1; i <= numRows; i++){
            String[] seatDetails = lines[i].split(" ");
            char row = (char)('A' + i-1);
            for(int j = 0; j < seatDetails.length; j++){
                SeatType seatType = seatDetails[j].equals("S") ? SeatType.STANDARD : SeatType.DELUXE;
                Seat seat = new Seat(row, j+1, seatType);
                seats.put(row + String.valueOf(j+1), seat);
            }//Printing problem check this over
        }


    }
    public Seat getSeat(char row, int seatNum){
        String key = row + String.valueOf(seatNum);
        if(!seats.containsKey(key)){
            throw new IllegalArgumentException("Invalid row or seat num");
        }
        return seats.get(key);

    }
    public void printDetails(){
        for(Map.Entry<String,Seat> entry : seats.entrySet()){
            System.out.println("Row: " + entry.getValue().getRow() + "| Seat Number: " +  entry.getValue().getseatNum() + "| Seat Type: " + entry.getValue().getSeatType() + "| Available: " + entry.getValue().getAvailability());
        }
    }
    public Map<String,Seat> getSeats(){
        return seats;
    }

    public static void main(String[] args){
        Venue thevenue = new Venue("3\nS D D S\nD D D\n S S S S S S");
        thevenue.printDetails();
    }

}
