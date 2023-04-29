package pro.learnup.ui.selenide.pages;

import lombok.Getter;
import pro.learnup.ui.selenide.pages.blocks.HeaderBlock;

public class BasePage {
    @Getter
    private HeaderBlock headerBlock = new HeaderBlock();
}
