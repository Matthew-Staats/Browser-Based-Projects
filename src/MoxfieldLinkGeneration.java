//import com.sun.org.apache.xpath.internal.operations.String;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MoxfieldLinkGeneration {
    public static Robot robot;
    public static void main(String[] args) throws IOException, InterruptedException, AWTException {
        MoxfieldLinkGeneration moxfieldLinkGeneration = new MoxfieldLinkGeneration();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//"C://Users//mstaa//Downloads//chromedriver_win32//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        ArrayList<String> lists= new ArrayList<>();
        generateArray(lists,new File("Original Moxfield Link.txt"));

        ArrayList<String> deckNames= new ArrayList<>();
        generateArray(deckNames,new File("deckNames.txt"));

        ArrayList<String> usernames= new ArrayList<>();
        generateArray(usernames,new File("userNames.txt"));

        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().fullscreen();
//        driver.manage().window().fullscreen();
//        driver.manage().window().maximize();
        robot = new Robot();

//        driver.manage().window().fullscreen();
        //Creating the deck lists in moxfield:
//        moxfieldLinkGeneration.login(driver,false);
        moxfieldLinkGeneration.printArray(moxfieldLinkGeneration.duplicateDeckLists(driver,lists,deckNames,usernames, false,250));

        /*//HTML steps:
        ArrayList<String> hashes= new ArrayList<>();
        generateArray(hashes,new File("hashes.txt"));

        ArrayList<String> embedURls = new ArrayList<>(//moxfieldLinkGeneration.duplicateDeckLists(driver,lists,deckNames,usernames)
        );
        generateArray(embedURls,new File("embeds.txt"));
//        moxfieldLinkGeneration.HTMLstep2(embedURls,deckNames,usernames,hashes);
//        moxfieldLinkGeneration.HTMLstep3(embedURls,deckNames,usernames,hashes);*/
    }

    private static void generateArray(ArrayList<String> lists, File file) throws FileNotFoundException {
        Scanner deckNameScanner = new Scanner(file);
        deckNameScanner.useDelimiter("\n");
        while(deckNameScanner.hasNext()) {
            lists.add(deckNameScanner.next());
        }
    }

    public void login(WebDriver driver,Boolean who) {
        // Navigate to Url
        driver.get("https://www.moxfield.com/account/signin?redirect=/");
        robot.keyPress(KeyEvent.VK_F11);
        while (!driver.getCurrentUrl().equals("https://www.moxfield.com/account/signin?redirect=/")) {
        }
        if (driver.getCurrentUrl().equals("https://www.moxfield.com/account/signin?redirect=/")) {
            WebElement emailField = driver.findElement(By.name("userName"));
            WebElement passwordField = driver.findElement(By.name("password"));
            if(who) {
                emailField.sendKeys("UntapOpenLeague");
                passwordField.sendKeys("RipNascar");
            }
            else {
                emailField.sendKeys("Niv");
                passwordField.sendKeys("1a74ebLg");
            }
            String loginClassName = "btn-ripple-container";
            WebElement loginButton = driver.findElement(By.className(loginClassName));
            loginButton.click();
        }
    }

    /**
     * @param driver
     * @param lists
     * @param deckNames
     * @param usernames
     * @return new links for the deck lists
     * @throws InterruptedException
     */
    public ArrayList<String> duplicateDeckLists(WebDriver driver, ArrayList<String> lists, ArrayList<String> deckNames, ArrayList<String> usernames, Boolean who, int waitTime) throws InterruptedException {
        ArrayList<String> newLinks = new ArrayList<>();
        try {
            login(driver,who);

            while (!driver.getCurrentUrl().equals("https://www.moxfield.com/")) {
            }
            for (int i = 0; i < lists.size(); i++) {
                String link = lists.get(i);
                driver.get(link);
                while (!driver.getCurrentUrl().trim().equalsIgnoreCase(link.trim())) {
                    System.out.println("Stuck! Trying to get to: "+link);
                    System.out.println("Currently at: "+driver.getCurrentUrl());
                }
                Thread.sleep(2*waitTime);
                WebElement subheader = driver.findElement(By.id("subheader-more"));
                subheader.click();
                Thread.sleep(waitTime);
                WebElement duplicate = driver.findElement(By.linkText("Duplicate"));
                duplicate.click();
                Thread.sleep(waitTime);
                WebElement deckName = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[2]/div[1]/div[1]/div[2]/div[1]/input[1]"));
                deckName.sendKeys(deckNames.get(i)+" by "+usernames.get(i));
                WebElement duplicate2 = driver.findElement(By.xpath("//button[@type='submit']//span[@class='btn-ripple-container']"));
                duplicate2.click();
                Thread.sleep(waitTime);
                String url = driver.getCurrentUrl();
                String embedURL = url.replaceAll("/decks/", "/embed/");
                System.out.println(embedURL);
                newLinks.add(embedURL);
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong");
            throw e;
        }

        System.out.println("Done");
        return newLinks;
    }
    public <E> void printArray(ArrayList<E> array){
        for (E elemtent:array) {
            System.out.println(elemtent);
        }
    }

    public ArrayList<String> HTMLstep2(ArrayList<String> lists, ArrayList<String> deckNames, ArrayList<String> usernames, ArrayList<String> hashes) throws InterruptedException {
        ArrayList<String> results = new ArrayList<>();
//        String result ="";
        for (int i = 0; i < deckNames.size(); i++) {
//            System.out.println("Got here "+i);

//            results.add(("<iframe src=\""+lists.get(i)+"\"id=\""+usernames.get(i)+" - "+deckNames.get(i)+" - "+hashes.get(i)+"\" frameborder=\""+0+"\" width=\"100%\" onload=\"moxfieldOnLoad(event)\" class=\"mtg_deck_title\"></iframe>"));
//            System.out.println(("<iframe src=\""+lists.get(i)+"\"id=\""+usernames.get(i)+" - "+deckNames.get(i)+" - "+hashes.get(i)+"\" frameborder=\""+0+"\" width=\"100%\" onload=\"moxfieldOnLoad(event)\" class=\"mtg_deck_title\"></iframe>"));
//            String test = "List: "+//lists.get(i)+
//                    " Deck name: "+//deckNames.get(i)+
//                    " username: "+//usernames.get(i)+
//                    " hashes: "//+hashes.get(i)
//            ;
//            String test = "<iframe src=\""+lists.get(i);
//            String test2 = "\"id=\""+usernames.get(i)+" - "+deckNames.get(i);
//            String test3 = " - "+hashes.get(i);
//            String test4 = "\" frameborder=\""+0+"\" width=\"100%\" onload=\"moxfieldOnLoad(event)\" class=\"mtg_deck_title\"></iframe>";
//            StringBuilder builder = new StringBuilder();
//            builder.append(test);
//            builder.append(test2);
//            test+=test2;
//            String.format()
            String test5 = "<iframe src=\""+lists.get(i)+"\"id=\""+usernames.get(i)+" - "+deckNames.get(i)+" - "+hashes.get(i)+"\" frameborder=\""+0+"\" width=\"100%\" onload=\"moxfieldOnLoad(event)\" class=\"mtg_deck_title\"></iframe>";
//            String format = String.format("<iframe src=\"%s\"id=\"%s - %s - %s\" frameborder=\"0\" width=\"100%\" onload=\"moxfieldOnLoad(event)\" class=\"mtg_deck_title\"></iframe>",lists.get(i),usernames.get(i),deckNames.get(i),hashes.get(i));
//            System.out.println(builder);
//            Thread.sleep(100);
//            result = test+test2;
            System.out.println();
//            System.out.println(result);
//            System.out.println(test5);
//            System.out.println(format);
//            System.out.println(test);
//            System.out.println(test2);
//            System.out.println(test3);
//            System.out.println(test4);
        }
//        for (int i = 0; i < ; i++) {
//
//        }
        printArray(results);
        return results;
    }
    public String HTMLstep3(ArrayList<String> lists, ArrayList<String> deckNames, ArrayList<String> usernames, ArrayList<String> hashes){
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            results.add("<option value=\""+usernames.get(i)+" - "+deckNames.get(i)+" - "+hashes.get(i)+"\">"+usernames.get(i)+" - "+deckNames.get(i)+" - "+hashes.get(i)+"</option>");
        }
        printArray(results);
        String result = "<input id=\"mySelect\" list=\"myList\" onchange=\"scrollToSelectedDeck()\" placeholder=\"Type to search decklists\">";
        for (String res:results) {
            result=result.concat("\n"+res);
        }
        System.out.println(result);
        return result;
    }
}
