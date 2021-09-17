package Week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		String title = driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// search as oneplus 9 pro
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro", Keys.ENTER);

		// get the price of first product
		String firstProd = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("First Product Price : " + firstProd);

		// print the no. of customer ratings of first product
		String customer = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
		System.out.println("No. of Customer ratings for First Product : " + customer);

		// mouse hover or click on the stars
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-popover'])[1]")).click();

		// get the percentage of ratings for the 5 star
		String attribute = driver.findElement(By.xpath("(//span[@class='a-size-base']/a[@class='a-link-normal'])[1]"))
				.getAttribute("title");
		System.out.println(attribute);

		// click on the first text link of image
		driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
		Thread.sleep(2000);

		// go to next window
		Set<String> windowHandles1 = driver.getWindowHandles();
		// System.out.println(windowHandles1.size());
		List<String> windowList1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(windowList1.get(windowList1.size() - 1));
		System.out.println(driver.getTitle());

		// take screenshot
		File screen = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/Amazon.png");
		FileUtils.copyFile(screen, dest);

		// get the price of the product
		String price = driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
		System.out.println("Price of the product : " + price);

		// click on the add cart
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(2000);

		// get the cart sub total amount
		String subTotal = driver
				.findElement(By.xpath("(//div[@class='a-column a-span11 a-text-left a-spacing-top-large']//span)[4]"))
				.getText();
		System.out.println("Cart Sub Total : " + subTotal);

		// verify the price and cart sub total are same
		if (price.equals(subTotal))
			System.out.println("It is Correct");
		else
			System.out.println("It is Not Correct");

	}

}
