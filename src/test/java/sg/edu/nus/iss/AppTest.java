package sg.edu.nus.iss;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sg.edu.nus.iss.server.Cookie;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void randomTest()
    {
        String cookieName = Cookie.getRandomCookie(\""C:\WSC\Code\TFIP\ibf-sdf-day04-workshop\sdfworkshop4\\");
        System.out.println(cookieName);
        assertNull(cookieName);
    }
}
