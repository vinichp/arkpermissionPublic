package pinheiro.vini.askperm;

import static org.junit.Assert.assertEquals;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AskPermission {

	
	public void openNavigator() {

		/*
		System.out.print("Qual o seu usuario: ");
		String user = scanner.nextLine();
			
		*/
		String user = System.getenv("username");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Qual o motivo (digite enter para deixar o motivo igual o ip) :" );	
		String motivo = scanner.next();
		
		
		
		Console console = System.console();
		String password = "";
		
		
		if(console != null){
			password = new String(console.readPassword("Enter Password: "));
		}else {
			System.out.print("Digite a sua senha:" );	
			password = scanner.next();
		}
		
		scanner.close();
		
		scanner.close();

		System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedriver_win32\\chromedriver.exe");
		WebDriver navegador  = new ChromeDriver();
		
		try {
			
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		navegador.get("http://findmyip.com/meu-ip/");

		navegador.findElement(By.name("IDToken1")).sendKeys(user);
		navegador.findElement(By.name("IDToken2")).sendKeys(password);
		navegador.findElement(By.name("Login.Submit")).click();

		String ipResult = navegador.findElement(By.cssSelector("body")).getText().substring(17);
		if(motivo == null || motivo.isEmpty()) {
			motivo = ipResult;
		}

		navegador.get("https://selfservice.com/login");

		navegador.findElement(By.name("username")).sendKeys(user);
		navegador.findElement(By.name("password")).sendKeys(password);
		navegador.findElement(By.cssSelector(".submit")).click();

		navegador.get("https://selfservice.com/cpqd/2");

		navegador.findElement(By.name("value")).sendKeys(ipResult);
		navegador.findElements(By.name("value")).get(1).sendKeys(motivo);
		navegador.findElement(By.cssSelector("button")).click();

		}catch(Exception e) {
			System.out.println(e);
			
		}finally {
			navegador.quit();
		}
		
		assertEquals(1,1);
	}
}
