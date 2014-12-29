package com.android.pet.lazyload;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
	private static DateFormat strformatter = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dateformatter = new SimpleDateFormat("MMM dd, yyyy");
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    public static String timeStr(long timeInSecs) {
		try {
			int hrs = (int) (timeInSecs/3600);
			int rem = (int) (timeInSecs%3600);
			int min = rem/60;
			int sec = rem%60;
			String secStr, minStr, hrStr;
			if(sec<10) {
				secStr = "0"+sec;
			} else {
				secStr = ""+sec;
			}
			if(min<10) {
				minStr = "0"+min;
			} else {
				minStr = ""+min;
			}
			if(hrs>0) {
				if(hrs<10) {
					hrStr = "0"+hrs;
				} else {
					hrStr = ""+hrs;
				}
				return hrStr+":"+minStr+":"+secStr;
			} else {
				return minStr+":"+secStr;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getPublishedDate(String date)
	{
		try {
			if(!"".equals(date)) {
				String arr[] = date.split("T", -1);
				if(arr!= null && arr.length>0) {
					Date d = (Date)strformatter.parse(arr[0]);
					return dateformatter.format(d);
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
    public static URI getEncodedUrl(String urlStr) throws URISyntaxException 
	{
		try {
			URL url = new URL(urlStr);
			return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new URI(urlStr);
	}
    
    public static boolean isInternetOn(Context c) {
		try {
			ConnectivityManager manager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
     
    public static int dpFromPx(float px, Context context)
    {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(float dp, Context context)
    {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}