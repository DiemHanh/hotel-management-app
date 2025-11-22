package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomTable {
    ROOM_NUMBER("Room Number"),
    ROOM_TYPE("Room Type"),
    FLOOR("Floor"),
    STATUS("Status");

    private final String value;
}
