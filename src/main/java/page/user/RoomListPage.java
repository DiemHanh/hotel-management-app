package page.user;

import models.user.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class RoomListPage {
    private final By roomContainerLocator = By.className("pop_item_description");
    private final By roomNameLocator = By.tagName("h5");
    private final By roomPriceLocator = By.className("green_text");
    private final By roomBtnToDetailLocator = By.cssSelector(".btn.btn-success.float-right");

    public List<Room> getAllRooms() {
        return Driver.getDriver().findElements(roomContainerLocator)
                .stream()
                .map(el -> new Room(
                        el.findElement(roomNameLocator).getText(),
                        Float.parseFloat(el.findElement(roomPriceLocator).getText().substring(1))
                )).collect(Collectors.toList());
    }

    public float getRoomPriceByName(String name) {
        WebElement currentRoom = Driver.getDriver().findElements(roomContainerLocator)
                .stream()
                .filter(f -> f.findElement(roomNameLocator).getText().equalsIgnoreCase(name))
                .findFirst().orElse(null);

        if (currentRoom == null) {
            System.out.println("There is not room named " + name);
            return 0;
        }

        return Float.parseFloat(currentRoom.findElement(roomPriceLocator).getText().substring(1));
    }

    public void openRoomDetailByIndex(int index) {
        WebElement currentRoom = Driver.getDriver().findElements(roomContainerLocator)
                .get(index - 1);

        currentRoom.findElement(roomBtnToDetailLocator).click();
    }

    public void openRoomDetailByName(String roomName) {
        List<WebElement> rooms = Driver.getDriver().findElements(roomContainerLocator);

        WebElement targetRoom = rooms.stream()
                .filter(room -> {
                    WebElement title = room.findElement(By.xpath(".//h5"));
                    return title.getText().equalsIgnoreCase(roomName);
                })
                .findFirst()
                .orElse(null);

        if (targetRoom == null) {
            System.out.println("Not found: " + roomName);
        } else {
            System.out.println("Room is found: " + roomName);
            targetRoom.findElement(roomBtnToDetailLocator).click();
        }
    }
}
