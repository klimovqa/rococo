package guru.qa.rococo.rest;

import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.dao.impl.AuthUserDAOHibernate;
import guru.qa.rococo.db.model.auth.AuthUserEntity;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("[REST][rococo-userdata]: Друзья")
@DisplayName("[REST][rococo-userdata]: Друзья")
public class SameTest {

    @Test
    @DisplayName("REST: Для пользователя должен возвращаться список друзей из rococo-userdata" +
            " при передаче includePending = false")
    @AllureId("200004")
    @Tag("REST")
    void testRest(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
    @Test
    @DisplayName("REST: Для пользователя должен возвращаться список друзей из rococo-userdata" +
            " при передаче includePending = true")
    @AllureId("200005")
    @Tag("REST")
    void testRest2(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
}
