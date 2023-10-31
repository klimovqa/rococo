package guru.qa.rococo.web;

import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.dao.impl.AuthUserDAOHibernate;
import guru.qa.rococo.db.model.auth.AuthUserEntity;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
@Epic("[WEB][rococo-frontend]: Художники")
@DisplayName("[WEB][rococo-frontend]: Художники")
public class SameTest {

    @DisplayName("WEB: Пользователь должен видеть список Художников")
    @Tag("WEB")
    @AllureId("500018")
    @Test
    void testWeb(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
    @DisplayName("WEB: Пользователь должен видеть список Художников 2")
    @Tag("WEB")
    @AllureId("500019")
    @Test
    void testWeb2(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
}
