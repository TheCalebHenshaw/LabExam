package boxOffice;

import java.util.Objects;


public class Seat {
    private char row;
    private int seatNum;
    private SeatType seatType;
    private boolean availability = true;

    
    public Seat(char row, int seatNum, SeatType seatType) {
        if(row < 'A' || row > 'Z' || seatNum < 1){
            throw new IllegalArgumentException("Invalid seat number");
        }
        if(!(seatType instanceof SeatType && row==(char) row && seatNum== (int) seatNum)){
            throw new IllegalArgumentException("Not a valid seat");
        }

        this.row = row;
        this.seatNum = seatNum;
        this.seatType = seatType;
    }
    @Override
    public boolean equals(Object obj){ //Make sure that this works
        if(this == obj) return true;
        if(obj == null || getClass()!= obj.getClass()) return false;
        Seat seat = (Seat) obj;
        return Objects.equals(row, seat.row) && Objects.equals(seatNum, seat.seatNum) && Objects.equals(seatType, seat.seatType);
    }
    public int hashCode(){
        return Objects.hash(row, seatNum, seatType);
    }
    public String toString(){
        return "Seat(" + "row=" + row + "seatNumber=" + seatNum + "seatType=" + seatType + "isAvailable" + availability + ")";
    }





    public void setAvailability(boolean val){
        this.availability = val;
    }
    public char getRow(){
        return row;
    }
    public int getseatNum(){
        return seatNum;
    }
    public SeatType getSeatType(){
        return seatType;
    }
    public boolean getAvailability(){
        return availability;
    }
}