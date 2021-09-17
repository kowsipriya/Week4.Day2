package Week4.day2;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionAssign {

	public static void Sortable() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("--->Sortable");
		driver.get("http://www.leafground.com/pages/sortable.html");
		WebElement sortsrc = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement sortdest = driver.findElement(By.xpath("//li[text()='Item 5']"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(sortsrc, sortdest).perform();
		Thread.sleep(4000);
	}

	public static void Selectable() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("---->Selectable");
		driver.get("http://www.leafground.com/pages/selectable.html");
		WebElement selectsrc = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement selectdest = driver.findElement(By.xpath("//li[text()='Item 7']"));
		Actions builder = new Actions(driver);
		builder.clickAndHold(selectsrc).moveToElement(selectdest).release().perform();
	}

	public static void Drag() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("---->Drag");
		driver.get("http://www.leafground.com/pages/drag.html");
		WebElement drag = driver.findElement(By.id("draggable"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(drag, 67, 92).perform();
	}

	public static void MouseHover() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("---->Mouse Hover");
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.findElement(By.linkText("TestLeaf Courses")).click();
		WebElement hover = driver.findElement(By.linkText("RPA"));
		Actions builder = new Actions(driver);
		builder.moveToElement(hover).perform();
	}

	public static void DragandDrop() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("--->Drag and Drop");
		driver.get("http://www.leafground.com/pages/drop.html");
		WebElement drag1 = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(drag1, drop).perform();
	}

	public static void main(String[] args) throws InterruptedException {
		Sortable();
		Thread.sleep(6000);
		Selectable();
		Thread.sleep(6000);
		Drag();
		Thread.sleep(6000);
		MouseHover();
		Thread.sleep(6000);
		DragandDrop();
		
		
	}

}
