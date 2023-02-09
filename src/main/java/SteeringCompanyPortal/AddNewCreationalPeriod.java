/*
 * Copyright (c) 2023.
 */

package SteeringCompanyPortal;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.fail;
@SuppressWarnings("ALL")
public class AddNewCreationalPeriod {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "");
        driver = new ChromeDriver();
      //  baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        driver.get("https://portal-demo.np.transporticonline.com/Naqaba/index.xhtml");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);

    }

    public void LogintoPortal () throws Exception{
        driver.findElement(By.linkText("تسجيل الدخول")).click();
        //  driver.get("https://portal-demo.np.transporticonline.com/Naqaba/login.xhtml?dswid=-4096");
        driver.findElement(By.id("loginForm:j_username")).clear();
        driver.findElement(By.id("loginForm:j_username")).sendKeys("1000000000");//username
        driver.findElement(By.id("loginForm:j_password")).clear();
        driver.findElement(By.id("loginForm:j_password")).sendKeys("123");//password
        driver.findElement(By.xpath("//div[@id='loginForm:rememberme']/div[2]/span")).click();
        driver.findElement(By.xpath("//button[@id='loginForm:j_idt49']/span")).click();
       // driver.findElement(By.id("loginForm:j_idt36")).click();
        driver.findElement(By.id("loginForm:j_idt36")).clear();
        driver.findElement(By.id("loginForm:j_idt36")).sendKeys("1");//OTP
        driver.findElement(By.xpath("//button[@id='loginForm:j_idt49']/span")).click();
    }
    WebDriverWait wait;
    Actions actions;
    public void GotoPeriodProgramSetting () throws Exception{
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[1]")));
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]")));
        actions.clickAndHold().perform();
        // driver.get("https://portal-demo.np.transporticonline.com/Naqaba/home.xhtml?dswid=-4096");
        //  driver.findElement(By.linkText("appsنظام سيار")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/a[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"j_idt27:subMenu-menuSayyar\"]/a/span[1]")).click();
        // driver.findElement(By.linkText("settingsإعدادات نظام سيار")).click();
        driver.findElement(By.xpath("//*[@id=\"j_idt27:subMenu-SettingSayyar\"]/a/span[1]")).click();
        Thread.sleep(500);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"j_idt27:j_idt71\"]/a")));


        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"j_idt27:j_idt72\"]/a"))).perform();
        Thread.sleep(5000);

        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt27:j_idt71\"]/a")));
        driver.findElement(By.xpath("//*[@id=\"j_idt27:j_idt71\"]/a/span[1]")).click();
    }

    public void GotoSteeringPeriodProgramSetting () throws Exception{
        driver.findElement(By.xpath("//button[@id='primetable:1:j_idt829_button']/span[2]")).click();
        driver.findElement(By.xpath("//div[@id='primetable:1:j_idt829_menu']/ul/li[2]/a/span")).click();
    }
    public void AddSteeringOperationalPeriodProgramSetting () throws Exception{
         // driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/edit.xhtml?dswid=-4096");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='period-creation-table:j_idt846']/span[2]")));

        actions.moveToElement(driver.findElement(By.xpath("//button[@id='period-creation-table:j_idt846']/span[2]")));
        actions.perform();
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='period-creation-table:j_idt846']/span[2]")));

        driver.findElement(By.xpath("//button[@id='period-creation-table:j_idt846']/span[2]")).click();
        //   driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/addPackagesAndPeriods.xhtml?dswid=-4096");
        driver.findElement(By.id("iospDesc")).click();
        driver.findElement(By.id("iospDesc")).clear();
        driver.findElement(By.id("iospDesc")).sendKeys("فترة تشغيل جديدة");//operational period name
        driver.findElement(By.id("invStartDate")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='>'])[1]/following::select[1]")).click();
        Select MonthFrom=new Select(driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/select[1]")));
        MonthFrom.selectByValue("12/1444");
        driver.findElement(By.linkText("13")).click();
        driver.findElement(By.id("invEndDate")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='>'])[1]/following::select[1]")).click();
        Select MonthTo=new Select(driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/select[1]")));
        MonthTo.selectByValue("12/1444");
        driver.findElement(By.linkText("14")).click();
        driver.findElement(By.id("minValueRange_input")).sendKeys("10");
        driver.findElement(By.xpath("//button[@id='j_idt775']/span")).click();
        driver.findElement(By.id("rmmId_label")).click();
        driver.findElement(By.id("rmmId_1")).click();
        driver.findElement(By.id("busType_label")).click();
        driver.findElement(By.id("busType_3")).click();
        driver.findElement(By.xpath("//div[@id='category']/div/div/div/div[2]/span")).click();
        driver.findElement(By.xpath("//div[@id='category']/div/div[2]/div/div[2]/span")).click();
        driver.findElement(By.xpath("//div[@id='category']/div[2]/div[2]/div/div[2]")).click();
        actions.moveToElement(driver.findElement(By.id("minVehiclePrice"))).perform();
        //  driver.findElement(By.id("packMinValuePerc")).click();
        driver.findElement(By.id("packMinValuePerc")).clear();
        driver.findElement(By.id("packMinValuePerc")).sendKeys("9");
        //  driver.findElement(By.id("maxValueRange")).click();
        driver.findElement(By.id("maxValueRange")).clear();
        driver.findElement(By.id("maxValueRange")).sendKeys("11");
        //  driver.findElement(By.id("minVehiclePrice")).click();
        driver.findElement(By.id("minVehiclePrice")).clear();
        driver.findElement(By.id("minVehiclePrice")).sendKeys("1200");
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"j_idt846\"]"))).perform();

        driver.findElement(By.xpath("//*[@id=\"j_idt816\"]")).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt846\"]")));
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"j_idt846\"]/span[1]"))).perform();
Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"j_idt846\"]/span[1]")).click();
    }
    @Test
    public void testAddNewCreationalPeriod() throws Exception {
        LogintoPortal();
   //     driver.get("https://portal-demo.np.transporticonline.com/Naqaba/auth.xhtml?dswid=-4096");
        GotoPeriodProgramSetting();
   //     driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/List.xhtml?dswid=-4096");

    //    driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/edit.xhtml?dswid=-4096");
        GotoSteeringPeriodProgramSetting();
        AddSteeringOperationalPeriodProgramSetting();


     //   driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/edit.xhtml?dswid=-4096");
        driver.findElement(By.xpath("//button[@id='period-creation-table:47:j_idt840_button']/span[2]")).click();
        driver.findElement(By.xpath("//div[@id='period-creation-table:47:j_idt840_menu']/ul/li[2]/a/span")).click();
       // driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/addCreationPeriod.xhtml?dswid=-4096");
        driver.findElement(By.id("invStartDate")).click();
        driver.findElement(By.linkText("17")).click();
        driver.findElement(By.id("invEndDate")).click();
        driver.findElement(By.linkText("18")).click();
        driver.findElement(By.id("timeFrom")).click();
        driver.findElement(By.id("timeFrom")).click();
        driver.findElement(By.id("timeFrom")).clear();
        driver.findElement(By.id("timeFrom")).sendKeys("07:59");
        driver.findElement(By.xpath("//div[@id='period-form-grid_content']/div[2]/div[2]/span")).click();
        driver.findElement(By.id("timeTo")).click();
        driver.findElement(By.id("timeTo")).click();
        driver.findElement(By.id("timeTo")).clear();
        driver.findElement(By.id("timeTo")).sendKeys("10:00");
        driver.findElement(By.xpath("//div[@id='period-form-grid_content']/div[3]/div")).click();
        driver.findElement(By.id("minValueRange_input")).click();
        driver.findElement(By.xpath("//button[@id='j_idt770:0:j_idt794']/span")).click();
        driver.findElement(By.xpath("//button[@id='j_idt770:1:j_idt794']/span")).click();
        driver.findElement(By.id("j_idt770:2:j_idt794")).click();
        driver.findElement(By.xpath("//button[@id='j_idt797']/span")).click();
       // driver.get("https://portal-demo.np.transporticonline.com/Naqaba/pages/Naq/SystemSetUp/InventorySettingConfig/UmrahGds/edit.xhtml?dswid=-4096");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        //driver.quit();
        driver.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



}
