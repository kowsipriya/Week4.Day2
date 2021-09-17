package Week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsAssignment2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("--->Draggrable in frame");
		driver.get("https://jqueryui.com/draggable/");

		driver.switchTo().frame(0);
		WebElement draggable = driver.findElement(By.id("draggable"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(draggable, 92, 67).perform();

		System.out.println("--->Droppable in frame");
		driver.get("https://jqueryui.com/droppable");
		driver.switchTo().frame(0);
		WebElement draggable1 = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		builder.dragAndDrop(draggable1, droppable).perform();

		System.out.println("---->Resizable in frame");
		driver.get("https://jqueryui.com/resizable");
		driver.switchTo().frame(0);
		WebElement resizable = driver.findElement(By.xpath("//div[@id='resizable']/div"));
		builder.clickAndHold(resizable).moveByOffset(50, 0).release().perform();

		System.out.println("---->Sortable in frame");
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(0);
		WebElement sortsource = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement sortdest = driver.findElement(By.xpath("//li[text()='Item 5']"));
		builder.dragAndDrop(sortsource, sortdest).perform();
		builder.dragAndDropBy(sortdest, 0, 50).perform();

		System.out.println("---->Selectable in frame");
		driver.get("https://jqueryui.com/selectable/");
		driver.switchTo().frame(0);
		WebElement selectabletar = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement selectabledes = driver.findElement(By.xpath("//li[text()='Item 7']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		builder.clickAndHold(selectabletar).moveToElement(selectabledes).release().perform();

	}

}
