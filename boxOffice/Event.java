package boxOffice;

import java.util.Map;
import java.util.HashMap;

public class Event {
    private Venue venue;
    private Map<SeatType, Integer> ticketPrices;
    private Map<String, Seat> reservedSeats;

    public Event(Venue venue){
        this.venue = venue;

        this.ticketPrices = new HashMap<>();
        this.ticketPrices.put(SeatType.STANDARD,10);
        this.ticketPrices.put(SeatType.DELUXE,20);
        this.reservedSeats = new HashMap<>();
    }


    public int reserveSeats(int numSeats, SeatType seatType){
        for(String key : venue.getSeats().keySet()){
            Seat currentSeat = venue.getSeats().get(key);
            if(currentSeat.getSeatType()== seatType && currentSeat.getAvailability()){
                int consectuvieAvailableSeats = countConsecutiveSeats(key,numSeats,seatType);
                if(consectuvieAvailableSeats >= numSeats){
                    return reserveConsecutiveSeats(key,numSeats,seatType);
                }
            }
        }
        return -1;
    }

    private int countConsecutiveSeats(String startKey, int numSeats, SeatType seatType){
        int count = 0;
        for(int i = 0; i < numSeats; i++){
            Seat seat = venue.getSeats().get(getAdjacentSeatKey(startKey,i));
            if(seat !=null && seat.getSeatType()== seatType && seat.getAvailability()){
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }



    private int reserveConsecutiveSeats(String startKey, int numSeats, SeatType seatType){
        int totalCost = 0;
        for(int i = 0; i < numSeats; i++){
            String key = getAdjacentSeatKey(startKey, i);
            Seat seat = venue.getSeats().get(key);
            seat.setAvailability(false);
            reservedSeats.put(key,seat);
            totalCost += ticketPrices.get(seatType);
        }
        return totalCost;
    }

    private String getAdjacentSeatKey(String key, int offset){
        char row = key.charAt(0);
        int seatNum = Integer.parseInt(key.substring(1)) + offset;
        return row + String.valueOf(seatNum);
    }

    public void returnSeat(char row, int seatNum){
        String seatnum = String.valueOf(seatNum);
        String key = row + seatnum;
        Seat seat = reservedSeats.get(key);
        if(seat == null){
            throw new IllegalArgumentException("Seat is not reserved or invalid seat number");
        }
        seat.setAvailability(true);
        reservedSeats.remove(key);

    }

    public static void main(String[] args){
        Venue thevenue = new Venue("3\nS D D S\nD D D\n S S S S S S");
        Event theevent = new Event(thevenue);

        theevent.reserveSeats(3,SeatType.STANDARD);
        thevenue.printDetails();
        theevent.returnSeat('C', 2);
        thevenue.printDetails();

    }
}
