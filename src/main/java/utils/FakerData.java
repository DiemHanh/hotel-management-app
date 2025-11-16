package utils;

import com.github.javafaker.Faker;
import models.admin.Room;

import java.util.Locale;

public class FakerData {
    private Faker faker = new Faker((new Locale("vi")));
    int roomNumber = faker.number().numberBetween(10000, 99999);
    int floor = faker.number().numberBetween(1, 10);
    String description = faker.lorem().sentence(10);

    public Room roomInfo = new Room(roomNumber, null, floor, description, null);

}

