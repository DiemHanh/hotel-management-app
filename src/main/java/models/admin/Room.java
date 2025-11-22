package models.admin;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    public int roomNumber;
    public String roomType;
    public int floor;
    public String description;
    public Boolean status;

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", floor=" + floor +
                ", status=" + status +
                '}';
    }
}
