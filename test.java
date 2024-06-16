package appiumtests;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
 
public class test {
     
    public static void main(String[] args) {
         
        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid("ENUL630010");
         
        options.setCapability("appium:appPackage", "com.google.android.calculator");
        options.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("appium:automationName", "uiautomator2");
         
        try {
            AndroidDriver driver = new AndroidDriver(new URL("<a class="vglnk" href="http://127.0.0.1:4723" rel="nofollow"><span>http</span><span>://</span><span>127</span><span>.</span><span>0</span><span>.</span><span>0</span><span>.</span><span>1</span><span>:</span><span>4723</span></a>"), options);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   
    }
}
