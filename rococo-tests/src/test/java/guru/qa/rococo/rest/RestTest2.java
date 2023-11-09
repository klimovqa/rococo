package guru.qa.rococo.rest;

import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.dao.impl.AuthUserDAOHibernate;
import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("[REST]")
@DisplayName("[REST]")
public class RestTest2 {

    @DisplayName("REST: 3")
    @Tag("REST")
//    @Test
    void testRest(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
//    @Test
    @DisplayName("REST: 4")
    @Tag("REST")
    void testRest2(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
}
