package guru.qa.rococo.rest;

import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.dao.impl.AuthUserDAOHibernate;
import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("[REST]")
@DisplayName("[REST]")
public class RestTest  extends BaseWebTest {

    @DisplayName("REST test")
    @Tag("REST")
//    @ApiLogin(
//            username = "dima",
//            password = "12345"
//    )
    //@Test
    void testRest(){
        System.out.println("##### start rest test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println("##### NAME: " + dima.getUsername());
        System.out.println("##### end rest test");
    }
//    @Test
    @DisplayName("REST: 2")
    @Tag("REST")
    void testRest2(){
        System.out.println("test");
        AuthUserDAO authUserDAO = new AuthUserDAOHibernate();
        AuthUserEntity dima = authUserDAO.findByName("dima");
        System.out.println(dima);
    }
}
