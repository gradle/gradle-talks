## Selenium 2

Originally known as “WebDriver”.

* Programmatic browser automation
* Java based - multiple language bindings
* Write once, automate any (supported) browser
* Sponsored by Google (and Facebook)
* Becoming a [W3C standard](http://www.w3.org/TR/webdriver/)

The industry standard for building web testing automation.

## Selenium API

Java based, with many language bindings.

    import org.openqa.selenium.*;
    import org.openqa.selenium.firefox.*;

    WebDriver driver = new FirefoxDriver();
    driver.get("http://google.com");
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();

<img src="img/browsers.png" style="margin-top: 2em"/>

## Mobile Browsers

Support for:

* Android
* iPad
* iPhone
* Blackberry

Works with emulators as well as real devices.

## Selenium Pros

* Industry standard
* Well known
* Easy to automate
* Active development
* Stable API
* Free & Open Source
* Test framework agnostic

## Selenium Cons

* Low level
* Not a complete solution
* You'll write your own framework on top
* Requires investment
* Requires _developers_

## Alternatives

* Sahi
    * More advanced, targeted at dedicated testers
    * Free & Paid versions
* ThoughtWorks Twist
    * Rebadged Sahi with extensions
* JavaScript based faking

Also, many “on top” frameworks.