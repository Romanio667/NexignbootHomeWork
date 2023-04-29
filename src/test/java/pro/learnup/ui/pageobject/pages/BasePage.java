package pro.learnup.ui.pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pro.learnup.ui.pageobject.pages.blocks.HeaderBlock;

public class BasePage extends BaseView {

    @Getter
    private HeaderBlock headerBlock = new HeaderBlock(webDriver);

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }
}
