/**
 * Created by shiva7071 on 07-11-2016.
 */
import java.util.Scanner;
import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {

    public static void main ( String[] args) throws ParseException {

        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter the Tumblr blog url");
        System.out.println("like: example.tumblr.com " );

        String input = scanner.next();  //getting input from user
        //String input = ("good.tumblr.com");

        String urlContent = urlReader("http://"+input+"/api/read/json?type=photo&num=0&start=0" );//getting the json object list as string

        //System.out.println( urlContent );
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(urlContent);// converting string to json
        Long no = (Long) obj.get( "posts-total");// total no of post present on that tumblr blog

        Integer postNo = Integer.valueOf(no.intValue());

        System.out.println("Total no of posts found is " +postNo );
        int reqPost ;

        do {

            System.out.println("enter the no of post you want");
            reqPost = scanner.nextInt();

        }while(postNo<=reqPost && reqPost==0);

        int post = 0;

        String urls="";

        while( post < reqPost )
        {
            reqPost = reqPost - post;
            urls +=getPost( input , post , reqPost );
            post += 50;
        }

        System.out.println("parsing completed");

        //System.out.println("\n\n\n");
        //System.out.println(urls);

        BufferedWriter bufferedWriter = null;
        try {

            File myFile = new File("C:/downloads/downloadList.txt");
            // check if file exist, otherwise create the file before writing
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            Writer writer = new FileWriter(myFile);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(urls);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
            } catch(Exception ex){
            }
        }

        System.out.println(" download links are saved in c:>downloads>downloadList.txt");

    }

    public static String urlReader (String _url )
    {
        String abc="";

        try {
            // get URL content
            URL url = new URL(_url);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = br.readLine()) != null)
                abc=inputLine;
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return(abc.substring(22,abc.length()-1));

    }

    public static String getPost ( String _url , int start , int num) throws ParseException {
        System.out.println("Parsing 50 urls from "+ start+ " with url:");
        System.out.println( "http://"+_url+"/api/read/json?type=photo&num="+num+"&start="+start);

        String urlContent = urlReader("http://"+_url+"/api/read/json?type=photo&num="+num+"&start="+start );

        //System.out.println(urlContent);

        JSONParser parser = new JSONParser();
        JSONObject objList = (JSONObject) parser.parse(urlContent);
        JSONArray arr = (JSONArray) objList.get("posts");
        // System.out.println( arr.size() );

        String result ="";

        for (int i = 0; i < arr.size(); i++)
        {

            JSONObject jsonobject = (JSONObject) arr.get(i);
            result +=(String)jsonobject.get("photo-url-1280")+ ("\n");

        }

        //System.out.println(result);

        return result;

    }

}