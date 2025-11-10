package User;

import TestBase.TestBaseUser;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class TC06 extends TestBaseUser {
    private static final Logger log = LoggerFactory.getLogger(TC06.class);
    Faker faker = new Faker(new Random(24));
    int numberAdult = faker.number().numberBetween(1, 5);

    @Test
    public void TC06() {
        log.info("Number of adults: " + numberAdult);
    }
}
