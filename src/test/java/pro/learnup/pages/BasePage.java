package pro.learnup.pages;

import lombok.Getter;
import pro.learnup.pages.blocks.HeaderBlock;

public class BasePage {
    @Getter
    private HeaderBlock headerBlock = new HeaderBlock();
}
