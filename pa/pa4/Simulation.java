// Simulation.java
// jandreas
// Juan Andreas
// pa4
// traces and reports Queue process in certain simulations

import java.io.*;
import java.util.Scanner;

public class Simulation{
	
   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }
	
   public static void main(String[] args) throws IOException{
		
      String line = null;
      String numJobs = null;
      Job j = null;
      Queue Q1 = new Queue();
      Queue Q2= new Queue();
      Queue Q3 = new Queue();
      Queue[] processorQueues = null;
      int m;
      int time = 0;
		
      if(args.length < 1){
         System.out.println("Usage: Simulation input_file");
         System.exit(1);
      }
		
      // open input file
      Scanner in = new Scanner(new File(args[0]));
		
      // write files
      PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
      PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
		
      // m = numJobs is the first integer of an input_file
      m = Integer.parseInt(in.nextLine());
		
      while(in.hasNext()){
         j = getJob(in);
         Q1.enqueue(j);
      }
		
      // prints header of report files
      report.println("Report file: " + (args[0] + ".rpt"));
      report.println(m + " Jobs:");
      report.println(Q1.toString());
      report.println();
      report.println("*****************************************************************************");

      // prints header of trace files
      trace.println("Trace file: " + (args[0] + ".trc"));
      trace.println(m + " Jobs:");
      trace.println(Q1.toString());
      trace.println();

      // n = numProcessors
      // loop until m(number of jobs)-1
      for(int n = 1; n <= m-1; n++){
         int tWait = 0;
	 int mWait = 0;
	 double avgWait = 0.00;
	 for(int i = 1; i<Q1.length()+1; i++){
	    j = (Job)Q1.dequeue();
	    j.resetFinishTime();
	    Q2.enqueue(j);
	    Q1.enqueue(j);
	 }
			
	 int processors = n;
	    processorQueues = new Queue[n+2];
	    processorQueues[0] = Q2;
	    processorQueues[n+1] = Q3;
            for(int i = 0; i <= n; i++){
	       processorQueues[i] = new Queue();
            }
			
            // prints the heading of each amount of processors in trace
            trace.println("*****************************");
	    if(n == 1){
	    // singular processor
	       trace.println(n + " processor:");
	    }else{
	    // plural processor
	       trace.println(n + " processors:");
	    }
            trace.println("*****************************");
	    trace.println("time=" + time);
	    trace.println("0: " + Q1.toString());
	    for(int i = 1; i < processors+1; i++){
	       trace.println(i + ": " + processorQueues[i]);
	    }
			
	    // while loops traces all queue processor
	    // 0: should print out the in-queue and finished elements
	    while(Q3.length()!=m){ // while unprocessed jobs remain
	       int compFinal = Integer.MAX_VALUE;
	       int finalIndex = 1;
	       int comp = -1;
	       int length = -1;
	       int finalLength = -1;
	       Job compare = null;
				
	       if(!Q2.isEmpty()){ 
	          compare = (Job)Q2.peek();
		  compFinal = compare.getArrival();
		  finalIndex = 0;
	       }
				
	       for(int i = 1; i < processors+1; i++){
	          if(processorQueues[i].length() != 0){
		  compare = (Job)processorQueues[i].peek();
		  comp = compare.getFinish();
	       }
	       if(comp == -1){
	       }else if(comp < compFinal){
	          compFinal = comp;
	          finalIndex = i;
	       }
	       time = compFinal;
	       }
				
	       if(finalIndex == 0){
	          int tempIndex = 1;
		  finalLength = processorQueues[tempIndex].length();
		  for(int i = 1; i < processors+1; i++){
		     length = processorQueues[i].length();
		     if(length < finalLength){
		     finalLength = length;
		     tempIndex = i;
		     }
	          }
				
		  compare = (Job)Q2.dequeue();
		  processorQueues[tempIndex].enqueue(compare);
		  if(processorQueues[tempIndex].length()==1){
		     compare = (Job)processorQueues[tempIndex].peek();
		     compare.computeFinishTime(time);
		  }
                  }else{
		     compare = (Job)processorQueues[finalIndex].dequeue();
		     Q3.enqueue(compare);
		     int tempWait = compare.getWaitTime();
		     if(tempWait > mWait){
		        mWait = tempWait;
		     }
		     tWait += tempWait;
					
		     if(processorQueues[finalIndex].length() >= 1){
		        compare = (Job)processorQueues[finalIndex].peek();
			compare.computeFinishTime(time);
		     }
		  }
				
                  trace.println();
		  trace.println("time=" + time);
		  trace.println("0: " + Q3.toString()+Q2.toString());
		  for(int i = 1; i < processors+1; i++){
		     trace.println(i + ": " + processorQueues[i]);
		  }
	       }
	       avgWait = ((double)tWait/m);
               avgWait = (double)Math.round(avgWait*100)/100;
               trace.println();
   
	       // prints t,m,avg wait times for each amount of processors
	       if(n == 1){
	          // singular processor
	          report.println(processors + " processor: totalWait="+tWait+
				               ", maxWait="+mWait+", averageWait="+String.format("%.2f", avgWait));
	       }else{
	          // plural processors
	          report.println(processors + " processors: totalWait="+tWait+
				               ", maxWait="+mWait+", averageWait="+String.format("%.2f", avgWait));
	       }
	       time = 0;
	       Q3.dequeueAll();
               
	    }// for loop bracket

            // close files
               in.close();
               report.close();
               trace.close();
   } // main bracket
}
