package utils;

import com.github.javafaker.Faker;
import models.admin.Room;
import models.user.UserInformation;

public class FakerData {
    private static Faker faker = new Faker();

    public static Room generateRandomRoom() {
        int roomNumber = faker.number().numberBetween(10000, 99999);
        int floor = faker.number().numberBetween(1, 10);
        String description = faker.lorem().sentence(10);
        return new Room(roomNumber, null, floor, description, null);
    }

    public static UserInformation generateRandomUser() {
        return new UserInformation(
                faker.name().fullName(),
                faker.name().username() + "@yopmail.com",
                faker.numerify("0#########"),
                faker.address().streetAddress()
        );
    }
}

