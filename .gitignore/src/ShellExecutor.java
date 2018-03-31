import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellExecutor {
	
		 private  String USERNAME ="P716054"; // username for remote host
		 private  String PASSWORD ="vipin012"; // password of the remote host
		 private  String host = "113.33.111.111"; // remote host address
		 private  int port=22;	
		 
		 
		 public List<String> executeFile(String scriptFileName)	 {
			 List<String> result = new ArrayList<String>(); 
			 try {
				 JSch jsch = new JSch(); 
				 Session session = jsch.getSession(USERNAME, host, port);
				 session.setConfig("StrictHostKeyChecking", "no");
				 session.setPassword(PASSWORD);
				 session.connect();
				 ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
				 InputStream in = channelExec.getInputStream();
				 channelExec.setCommand("./"+scriptFileName);
				 channelExec.connect();
				 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				 String line;
				 while ((line = reader.readLine()) != null)
					   {
					      result.add(line);
					   }
				 int exitStatus = channelExec.getExitStatus();
				 channelExec.disconnect();
				 session.disconnect();
				 if(exitStatus < 0){
					 System.out.println("Done, but exit status not set!");}
				 else if(exitStatus > 0){
					 System.out.println("Done, but with error!");  }
	 	         else{ System.out.println("Done!");
			         }
				 
			 }catch (Exception e) {
				 System.out.println("Error "+ e.getMessage());
			 }
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			return null;

}
}