package utils;

import java.util.ArrayList;

public class TabWindow {
    public void switchBackToOriginalTab() {
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(0));
    }
}
