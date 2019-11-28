package ba.pehli.cinema.test;

import static org.junit.Assert.*;

import org.junit.Before;

import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import ba.pehli.cinema.controller.UsersController;
import ba.pehli.cinema.domain.User;
import ba.pehli.cinema.service.UserDao;
import ba.pehli.cinema.utils.EmailUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

    UsersController uc ;

    private static final Logger LOG = Logger.getLogger(UsersControllerTest.class.getName());
    @Mock
    private Model model;

    @Mock
    private UserDao userdao;

    @Mock
    private EmailUtils emailutils;

    @Mock
    private User user;


    @Before
    public void before(){

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);



        uc= new UsersController();
        uc.setMessageSource(messageSource);
        uc.setUserDao(userdao);
        uc.setEmailUtils(emailutils);
    }

    @Test
    public void testLoginFail() {
        try {

            RedirectAttributes flashAttributes;
            flashAttributes=Mockito.mock(RedirectAttributes.class);


            String status = uc.loginFail(model, flashAttributes, java.util.Locale.ENGLISH);
            LOG.info("---"+status+"---");
            assertEquals("Success", "redirect:/movies", status);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testLoginPass() {
        try {

            RedirectAttributes flashAttributes;
            flashAttributes=Mockito.mock(RedirectAttributes.class);
            String status = uc.loginFail(model, flashAttributes, java.util.Locale.ENGLISH);
            LOG.info("---"+status+"---");
            assertNotEquals("Success", "redirect:/user", status);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    @Test
    public void testShowRegistring() {

        try
        {
            String result = uc.showRegistring(model);
            assertEquals("Success", "users/register", result);
        }
        catch(Exception e)
        {

        }

    }


    @Test
    public void testVerify() {

        try
        {
            RedirectAttributes flashAttributes;
            flashAttributes=Mockito.mock(RedirectAttributes.class);

            String verifycode =uc.verify("12345", model, flashAttributes,Locale.ENGLISH);


            assertEquals("Success", "redirect:/users/register", verifycode);

        }
        catch(Exception e)
        {

        }

    }



    @Test
    public void testRegister() {

        try
        {
            MockHttpServletRequest request = new MockHttpServletRequest();


            RedirectAttributes flashAttributes;
            flashAttributes=Mockito.mock(RedirectAttributes.class);
            BindingResult bindingResult = Mockito.mock(BindingResult.class);
            String verifycode  = uc.register(user, bindingResult, model, request, flashAttributes,Locale.ENGLISH) ;

            assertEquals("Success", "redirect:/users/register", verifycode);

//            bindingResult.addError(new ObjectError("12234","12345"));
//            LOG.info("---"+ bindingResult.hasErrors()+"---");
//             verifycode  = uc.register(user, bindingResult, model, request, flashAttributes,Locale.ENGLISH) ;
//            assertEquals("Success", "redirect:/users/register", verifycode);

        }
        catch(Exception e)
        {

        }

    }









}
