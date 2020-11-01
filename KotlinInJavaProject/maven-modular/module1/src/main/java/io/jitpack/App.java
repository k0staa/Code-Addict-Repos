package io.jitpack;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String GREETING = "Hello World!";
    
    public static void main( String[] args )
    {
        KotlinRulezService service = new KotlinRulezService();
        service.talkToMe(GREETING);
    }
}
