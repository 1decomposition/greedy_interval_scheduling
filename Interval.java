
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author justincoy
 */
public class Interval {

    /**
     * Greedy algorithm for interval scheduling. Selects max subset
     * of compatible jobs based on start/end times.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFile = args[0];
        ArrayList<job> list = new ArrayList();
        
        String maxStr = "";
        String name = "";
        int start = 0;
        int end = 0;
        
        int size;
        try {
            File file = new File(inputFile).getAbsoluteFile();
            PrintWriter writer = new PrintWriter("OutputIS.txt","UTF-8");
            Scanner in = new Scanner(file);
            //Reads input file, and adds job objects to an array list
            while(in.hasNext()) {   
                name = in.next();
                start = in.nextInt();
                end = in.nextInt();
                job job = new job(name,start,end);
                list.add(job);                
            }
            
            int curr;
            //String of arrays that represent the subset of compatible jobs
            String[] max = new String[list.size()];
            //Greedy algorithm, that selects the subsets of compatible jobs
            for(int i = 0; i < list.size(); i++) {
                
                max[i] = list.get(i).getName();
                curr = i;
                    for(int j = 1; j < list.size(); j++) {
                        if(list.get(curr).getEnd() <= list.get(j).getStart()){
                            max[i] = max[i] + list.get(j).getName();
                            curr = j;
                        }  
                    }                   
                }
            int ma = max[0].length();
            //Finds max length of all subsets
            for(int i = 1; i < max.length;i++) {
                if(max[i].length() > ma) maxStr = max[i];
            }
            //Writes the max subset to a file
            for(int i=0; i < maxStr.length();i++) {
                for(int j=0; j<list.size();j++) {
                    if(Character.toString(maxStr.charAt(i)).endsWith(list.get(j).getName())) {
                        writer.println(list.get(j).getName() + " " + list.get(j).start + " " + list.get(j).end);
                       
                        
                    }
                        
                }
            }

            in.close();
            writer.close();
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    //Job class that takes the name, start time, and end time of a job
    public static class job {
        private String name;
        private int start;
        private int end;
        
        public job (String name,int start,int end) {
            this.name = name;
            this.start = start;
            this.end = end;                   
        }
        
        public String getName() {
            return this.name;
        }
        
        public int getStart() {
            return this.start;
        }
        
        public int getEnd() {
            return this.end;
        }
    }
}
