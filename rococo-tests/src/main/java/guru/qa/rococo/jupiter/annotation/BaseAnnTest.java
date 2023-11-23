package guru.qa.rococo.jupiter.annotation;

import guru.qa.rococo.jupiter.extension.ApiLoginExtension;
import guru.qa.rococo.jupiter.extension.BrowserExtension;
import guru.qa.rococo.jupiter.extension.ContextHolderExtension;
import guru.qa.rococo.jupiter.extension.DataProviderExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({
        DataProviderExtension.class,
        ContextHolderExtension.class,
        ApiLoginExtension.class,
        BrowserExtension.class})
public @interface BaseAnnTest {
}
