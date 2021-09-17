package Week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Mouse hover on Men
		WebElement Men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(Men).perform();
		driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
		String Totalcount = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		System.out.println("Total Count of Jackets : " + Totalcount);
		String repItem = Totalcount.replaceAll("[^0-9]", "");
		int count = Integer.parseInt(repItem);
		System.out.println("Total count: " + count);
		String JAcketCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String repItem1 = JAcketCount.replaceAll("[^0-9]", "");
		int count1 = Integer.parseInt(repItem1);
		System.out.println("Count of the Jackets : " + count1);
		String Rain_JAcketCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String repItem2 = Rain_JAcketCount.replaceAll("[^0-9]", "");
		int count2 = Integer.parseInt(repItem2);
		System.out.println("Count of the Rain_JAckets : " + count2);
		int Sum = count1 + count2;
		System.out.println("sum" + Sum);
		if (count == Sum) {
			System.out.println("Count of the jackets are Matched");
		} else {
			System.out.println("Count of the jackets are Not Matched");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[contains(text(),'Jackets')]")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@value='Duke']/following::div[@class='common-checkboxIndicator'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		List<WebElement> myDukeElements = driver.findElements(By.tagName("h3"));
		System.out.println(myDukeElements.size());
		for (int i = 1; i < myDukeElements.size(); i++) {
			String val = myDukeElements.get(i).getText();
			if (val.equalsIgnoreCase("Duke")) {
				System.out.println("All products are Duke");
			} 
			else {
				System.out.println("All products are Not a Duke");
			}
		}
		//sort by
		WebElement sort =driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		builder.moveToElement(sort).perform();
		driver.findElement(By.xpath("(//label[@class='sort-label sort-selected'])[1]")).click();
		Thread.sleep(2000);
		WebElement price=driver.findElement(By.xpath("(//div[@class='product-productMetaInfo'])[1]//span[@class='product-discountedPrice']"));
		price.getText();
		System.out.println("price.getText()"+price.getText());
		price.click();
		Thread.sleep(4000);
		
		Set<String> Windowhandles=driver.getWindowHandles();
		List<String> windowList=new ArrayList<String>(Windowhandles);
		driver.switchTo().window(windowList.get(1));
		
		//Scrnshot
		File Screen=driver.getScreenshotAs(OutputType.FILE);
		File dest =new File("./Scrnshot/Myntra.png");
		FileUtils.copyFile(Screen, dest);
		
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.close();
	}

}
