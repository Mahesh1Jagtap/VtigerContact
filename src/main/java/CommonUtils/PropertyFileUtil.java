package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

	public String getDataFromPropertyFile(String Key) throws IOException
	{
		FileInputStream fis=new FileInputStream("src\\test\\resources\\LiveProjectVtiger.properties");
		Properties pt=new Properties();
		pt.load(fis);
		String value = pt.getProperty(Key);
		return value;
	}
}
