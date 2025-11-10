package userPages;

import utils.Driver;

import java.util.ArrayList;

public class BasePage {
    public void switchBackToOriginalTab() {
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(0));// move to utils
    }
}
