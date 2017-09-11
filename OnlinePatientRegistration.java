import java.net.*;
class OnlinePatientRegistration
{
	OnlinePatientRegistration()
	{
		try
		{
            URL url = new URL("http://www.google.com");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
              connection = (HttpURLConnection) urlConnection;
            }
        } 
        catch (Exception e) 
		{ 
        } 
	}
}	