package guru.qa.rococo;

import guru.qa.rococo.config.Config;
import guru.qa.rococo.jupiter.annotation.BaseAnnTest;

@BaseAnnTest
public abstract class BaseTest {
    protected static final Config CFG = Config.getInstance();
}
