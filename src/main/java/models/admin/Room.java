package models.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    public int roomNumber;
    public String roomType;
    public int floor;
    public String description;
    public Boolean status;
}
