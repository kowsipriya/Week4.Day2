package Week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// 1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		// 2) Mouseover on Brands and Mouseover on Popular
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		WebElement loreal = driver.findElement(By.xpath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).moveToElement(popular).perform();
		// 3) Click L'Oreal Paris
		builder.moveToElement(loreal).click().perform();
		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		String lorealtitle = driver.findElement(By.xpath("//h1[@class='heading--fancy']")).getText();
		System.out.println("The title of the product is: " + lorealtitle);
		// 5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='popularity']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,250)", "");

		// 6) Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		String prod = "Shampoo";
		driver.findElement(By.xpath("//span[text()='" + prod + "']")).click();
		// 7) check whether the Filter is applied with Shampoo
		String filter = driver.findElement(By.xpath("(//span[text()='filters applied'])/following::li")).getText();
		System.out.println(filter);
		if (filter.contains(prod)) {
			System.out.println("Shampoo is filtered");
		} else {
			System.out.println("Shampoo is not filtered");
		}
		// 8) Click on L'Oreal Paris Colour Protect Shampoo
		Thread.sleep(4000);
		List<WebElement> listprod = driver.findElements(By.tagName("h2"));
		System.out.println(listprod.size());
		driver.findElement(By.xpath("//h2[@title=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		// 9) GO to the new window and select size as 175ml
		ArrayList<String> windowHandles1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles1.get(2));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		// 10) Print the MRP of the product
		Thread.sleep(2000);
		String mrp = driver.findElement(By.xpath("//div[@class='price-info']/span[2]")).getText();
		System.out.println("The MRP of the product is: " + mrp);
		// 11) Click on ADD to BAG
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='pull-left'])[1]")).click();
		// 12) Go to Shopping Bag s
		driver.findElement(By.className("AddBagIcon")).click();
		// 13) Print the Grand Total amount
		String grandtot = driver.findElement(By.xpath("//span[text()='Grand Total']/following::div")).getText();
		System.out.println("The grand total is: " + grandtot);
		// 14) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		// 15) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		// 16) Check if this grand total is the same in step 13
		String fintot = driver.findElement(By.xpath("//div[@class='value']/span")).getText();
		System.out.println(fintot);
		if (grandtot.equals(fintot)) {
			System.out.println("The final total is same as grand total");
		} else {
			System.out.println("The final total is not same as grand total");
		}
		// 17) Close all windows
		driver.quit();

	}

}
