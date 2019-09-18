import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProps {
    // to create method to get the keys from testdataconfig file
    Properties props = new Properties();
    public LoadProps() {
        try {
            FileInputStream inputStream= new FileInputStream("src\\main\\Resources\\TestDataConfig.properties");
            props.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperty(String key){

        return props.getProperty(key);
    }
    public void readPropTest(){
        System.out.println(getProperty("url"));
        System.out.println(getProperty("FirstName"));
    }
}
