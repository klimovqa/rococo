package guru.qa.rococo.rest;

import guru.qa.rococo.config.Config;
import guru.qa.rococo.jupiter.annotation.WebTest;

@WebTest
public abstract class BaseWebTest {
    protected static final Config CFG = Config.getInstance();
}
