/*
 * Copyright (c) 2023.
 */

package SteeringCompanyPortal;

import com.shaft.driver.SHAFT;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.IslamicChronology;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utils.DateConvert;
import java.time.Duration;

@SuppressWarnings("ALL")
public class AddNewCreationalPeriod {
    DateConvert DateConverter;
    Actions actions;
    By LoginBtn = By.xpath("//*[@id=\"nav\"]/li[7]/a");
    By UserNameTxt = By.id("loginForm:j_username");
    By PasswordTxt = By.id("loginForm:j_password");
    By RememberMeChkBx = By.xpath("//div[@id='loginForm:rememberme']/div[2]/span");
    By SigninBtn = By.xpath("//button[@id='loginForm:j_idt49']/span");
    By OTPTxt = By.id("loginForm:j_idt36");
    // WebDriverWait wait;
    By TICLogo = By.xpath("/html/body/div[1]/div[1]/div[1]");
    By SwithPanelBtn = By.xpath("/html/body/div[1]/div[1]/div[1]/a[2]");
    By SayyerMenu = By.xpath("//*[@id=\"j_idt27:subMenu-menuSayyar\"]/a/span[1]");
    By SayyerSubMenu = By.xpath("//*[@id=\"j_idt27:subMenu-SettingSayyar\"]/a/span[1]");
    By PeriodProgramSetting = By.xpath("//*[@id=\"j_idt27:j_idt71\"]/a/span[1]");
    By OTACommissionSetting = By.xpath("//*[@id=\"j_idt27:j_idt72\"]/a/span[1]");
    By ActionsBtn = By.xpath("//button[@id='primetable:1:j_idt829_button']/span[2]");
    By ActionsEditBtn = By.xpath("//div[@id='primetable:1:j_idt829_menu']/ul/li[2]/a/span");
    By AddOperationalPeriodBtn = By.xpath("//button[@id='period-creation-table:j_idt846']/span[2]");
    By OperationalPeriodNameTxt = By.id("iospDesc");
    By invStartDate = By.id("invStartDate");
    By Calender = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='>'])[1]/following::select[1]");
    By SelectMonth = By.xpath("/html/body/div[7]/div/div[2]/div/div/select[1]");
    By invEndDate = By.id("invEndDate");
    By minValueRange_input = By.id("minValueRange_input");
    By j_idt775Btn = By.xpath("//button[@id='j_idt775']/span");
    By rmmId_label = By.id("rmmId_label");
    By rmmId_1 = By.id("rmmId_1");
    By busType_label = By.id("busType_label");
    By busType_3 = By.id("busType_3");
    By category1 = By.xpath("//div[@id='category']/div/div/div/div[2]");
    By category2 = By.xpath("//div[@id='category']/div/div[2]/div/div[2]");
    By category3 = By.xpath("//div[@id='category']/div[2]/div[2]/div/div[2]");
    By minVehiclePrice = By.id("minVehiclePrice");
    By packMinValuePerc = By.id("packMinValuePerc");
    By maxValueRange = By.id("maxValueRange");
    By AddOperationalBtn = By.xpath("//*[@id=\"j_idt846\"]");
    By AddTemplatesBtn = By.xpath("//*[@id=\"j_idt816\"]");
    By LastOperationPeriodRow = By.xpath("//*[@id=\"period-creation-table_data\"]/tr[last()-1]");
    By LastOperationPeriodActionBtn = By.xpath("//*[@id=\"period-creation-table_data\"]/tr[last()-1]/td[last()-1]/span/button");
    By AddCreationalPeriodLastRow = By.xpath("//*[@class=\"ui-menu ui-menu-dynamic ui-widget ui-widget-content ui-corner-all ui-helper-clearfix ui-shadow\" ][last()]/ul/li[2]/a/span");
    By CreationalinvStartDate = By.id("invStartDate");
    By CreationalinvEndDate = By.id("invEndDate");
    By CreationalDayFrom = By.linkText("24");
    By CreationalDayTo = By.linkText("25");
    By TimeFrom = By.id("timeFrom");
    By TimeTo = By.id("timeTo");
    By period_form_grid2 = By.xpath("//div[@id='period-form-grid_content']/div[2]/div[2]/span");
    By period_form_grid3 = By.xpath("//div[@id='period-form-grid_content']/div[3]/div");
    By CreationalminValueRange_input = By.id("minValueRange_input");
    By AddCreationalBtn = By.xpath("//*[@id=\"j_idt770_data\"]/tr[1]/td[last()]/button/span");
    By AddCreationalBtn2 = By.xpath("//*[@id=\"j_idt770_data\"]/tr[2]/td[last()]/button/span");
    By AddCreationalBtn3 = By.xpath("//*[@id=\"j_idt770_data\"]/tr[3]/td[last()]/button/span");
    By AddCreationalBtn4 = By.xpath("//*[@id=\"j_idt797\"]/span[1]");
    By LastDateOperationalPeriod = By.xpath("//*[@id=\"period-creation-table_data\"]/tr[last()-1]/td[3]");
    LocalDate Today;
    private SHAFT.GUI.WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public void LogintoPortal() throws Exception {
        driver.element().click(LoginBtn);
        driver.element().waitToBeReady(UserNameTxt).type(UserNameTxt, "1000000000");
        driver.element().waitToBeReady(PasswordTxt).type(PasswordTxt, "123");
        driver.element().waitToBeReady(RememberMeChkBx).click(RememberMeChkBx);
        driver.element().waitToBeReady(SigninBtn).click(SigninBtn);
        driver.element().waitToBeReady(OTPTxt).type(OTPTxt, "1");
        driver.element().waitToBeReady(SigninBtn).click(SigninBtn);
    }

    public void GotoPeriodProgramSetting() throws Exception {
        driver.element().waitToBeReady(TICLogo).clickAndHold(TICLogo);
        driver.element().click(SwithPanelBtn);
        driver.element().waitToBeReady(SayyerMenu).click(SayyerMenu);
        driver.element().scrollToElement(SayyerSubMenu).waitToBeReady(SayyerSubMenu).click(SayyerSubMenu);
        WebElement OTACommissionSettingBtn = driver.getDriver().findElement(OTACommissionSetting);
        js.executeScript("arguments[0].scrollIntoView();", OTACommissionSettingBtn);
        WebElement PeriodProgramSettingBtn = driver.getDriver().findElement(PeriodProgramSetting);
        PeriodProgramSettingBtn.click();
    }

    public void GotoSteeringPeriodProgramSetting() throws Exception {
        driver.element().waitToBeReady(ActionsBtn).click(ActionsBtn);
        driver.element().waitToBeReady(ActionsEditBtn).click(ActionsEditBtn);
    }

    public void AddSteeringOperationalPeriodProgramSetting() throws Exception {
        WebElement LastDateOperationalPeriodTxt = driver.getDriver().findElement(LastDateOperationalPeriod);
        js.executeScript("arguments[0].scrollIntoView();", LastDateOperationalPeriodTxt);
        String LastDateOperationalPeriodString = LastDateOperationalPeriodTxt.getText().toString();
        String NewFromDateOperationPeriodSting = DateConverter.AddDayToHijriDate(LastDateOperationalPeriodString);
        String NewToDateOperationPeriodSting = DateConverter.AddDayToHijriDate(NewFromDateOperationPeriodSting);
        LocalDate NewFromDateOperationPeriod = DateConverter.HijriDate(NewFromDateOperationPeriodSting);
        LocalDate NewToDateOperationPeriod = DateConverter.HijriDate(NewToDateOperationPeriodSting);

        driver.element().waitToBeReady(AddOperationalPeriodBtn);
        WebElement AddOperationalPeriod = driver.getDriver().findElement(AddOperationalPeriodBtn);
        js.executeScript("arguments[0].scrollIntoView();", AddOperationalPeriod);
        driver.element().click(AddOperationalPeriodBtn);
        driver.element().type(OperationalPeriodNameTxt, "فترة تشغيل جديدة");
        driver.element().click(invStartDate);
        driver.element().click(Calender);
        driver.element().select(SelectMonth, NewFromDateOperationPeriod.toString("M/yyyy"));
        driver.element().click(By.linkText(NewFromDateOperationPeriod.toString("d")));
        driver.element().click(invEndDate);
        driver.element().click(Calender);
        driver.element().select(SelectMonth, NewToDateOperationPeriod.toString("M/yyyy"));
        driver.element().click(By.linkText(NewToDateOperationPeriod.toString("d")));
        driver.element().type(minValueRange_input, "10");
        driver.element().click(j_idt775Btn);
        driver.element().click(rmmId_label);
        driver.element().click(rmmId_1);
        driver.element().click(busType_label);
        driver.element().click(busType_3);
        WebElement minVehiclePriceTxt = driver.getDriver().findElement(minVehiclePrice);
        js.executeScript("arguments[0].scrollIntoView();", minVehiclePriceTxt);
        driver.getDriver().findElement(category1).click();
        driver.getDriver().findElement(category2).click();
        driver.getDriver().findElement(category3).click();
        js.executeScript("arguments[0].scrollIntoView();", minVehiclePriceTxt);
        driver.element().type(packMinValuePerc, "9");
        driver.element().type(maxValueRange, "11");
        driver.element().type(minVehiclePrice, "1200");
        WebElement AddButton = driver.getDriver().findElement(AddOperationalBtn);
        js.executeScript("arguments[0].scrollIntoView();", AddButton);
        driver.getDriver().findElement(AddTemplatesBtn).click();
        //driver.getDriver().findElement(AddOperationalBtn).click(); // for test

    }

    public void AddSteeringCreationalPeriodProgramSetting() throws Exception {
        WebElement LastOperationPeriodRowTxt = driver.getDriver().findElement(LastOperationPeriodRow);
        js.executeScript("arguments[0].scrollIntoView();", LastOperationPeriodRowTxt);
        driver.getDriver().findElement(LastOperationPeriodActionBtn).click();
        driver.getDriver().findElement(AddCreationalPeriodLastRow).click();
        driver.element().click(CreationalinvStartDate);
        driver.element().click(Calender);
        driver.element().waitToBeReady(SelectMonth).select(SelectMonth, Today.toString("M/yyyy"));
        driver.element().click(By.linkText(Today.toString("d")));
        driver.element().click(CreationalinvEndDate);
        driver.element().click(Calender);
        driver.element().waitToBeReady(SelectMonth).select(SelectMonth, Today.plusDays(1).toString("M/yyyy"));
        driver.element().click(By.linkText(Today.plusDays(1).toString("d")));
        driver.element().type(TimeFrom, "00:00");
        driver.element().click(period_form_grid2);
        driver.element().type(TimeTo, "23:00");
        WebElement period_form_grid3Btn33 = driver.getDriver().findElement(period_form_grid3);
        js.executeScript("arguments[0].scrollIntoView();", period_form_grid3Btn33);
        driver.getDriver().findElement(period_form_grid3).click();
        WebElement CreationalminValueRange_input1 = driver.getDriver().findElement(CreationalminValueRange_input);
        js.executeScript("arguments[0].scrollIntoView();", CreationalminValueRange_input1);
        CreationalminValueRange_input1.sendKeys("10");
        WebElement AddCreationalBtn44 = driver.getDriver().findElement(AddCreationalBtn4);
        js.executeScript("arguments[0].scrollIntoView();", AddCreationalBtn44);
        driver.getDriver().findElement(AddCreationalBtn).click();
        wait.until(ExpectedConditions.stalenessOf(driver.getDriver().findElement(AddCreationalBtn2)));
        driver.getDriver().findElement(AddCreationalBtn2).click();
        wait.until(ExpectedConditions.stalenessOf(driver.getDriver().findElement(AddCreationalBtn3)));
        driver.getDriver().findElement(AddCreationalBtn3).click();
        js.executeScript("arguments[0].scrollIntoView();", AddCreationalBtn44);
        //   driver.getDriver().findElement(AddCreationalBtn4).click(); // for test

    }

    @Test
    public void testAddNewCreationalPeriod() throws Exception {
        //  ConvertToHijri("2-2-2023");
        //AddDayToHijriDate("10/08/1444");
        LogintoPortal();
        GotoPeriodProgramSetting();
        GotoSteeringPeriodProgramSetting();
        AddSteeringOperationalPeriodProgramSetting();
        AddSteeringCreationalPeriodProgramSetting();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new SHAFT.GUI.WebDriver();
        js = (JavascriptExecutor) driver.getDriver();
        driver.browser().navigateToURL("https://portal-demo.np.transporticonline.com/Naqaba/index.xhtml");
        wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(30));
        actions = new Actions(driver.getDriver());
        DateTimeZone zone = DateTimeZone.forID("Asia/Riyadh");
        Chronology hijri = IslamicChronology.getInstance(zone);
        Today = new LocalDate(hijri);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}
