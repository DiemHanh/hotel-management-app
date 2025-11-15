package models.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private int roomNumber;
    private String roomType;
    private int floor;
    private String description;
    private Boolean status;
}
