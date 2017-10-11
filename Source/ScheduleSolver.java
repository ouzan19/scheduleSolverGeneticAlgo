
import java.util.Arrays ;
import java.util.Random       ;
import java.util.List         ;
import java.util.ArrayList    ;
import java.util.Collections  ;
import java.util.Comparator   ;
import java.io.*;

class ScheduleSolver
{  

		private static  Random     rand            ;
		 public static List< Schedule    > schedules;  
		
		  public ScheduleSolver (){
	
		  }
		
        public static void main(String args[])
        {
			
			rand      = new Random     () ;
			 List< Instructor    > instructors       ;
			 List< Room > rooms             ;
			 List< Course > courses           ;
			 List<Slot> slots;
				instructors = new ArrayList<Instructor>() ;
				rooms       = new ArrayList<Room>() ;
				courses     = new ArrayList<Course>() ;
				slots     = new ArrayList<Slot>() ;
			 try{
			  
			  FileInputStream fstream = new FileInputStream(args[0]);
			 
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			 
			  while ((strLine = br.readLine()) != null)   {
				  
					
					if(strLine.length() ==0 || strLine.charAt(0) == '#' )
						continue;
					
					if(strLine.charAt(0) == 'S'){
						
						Slot s = new Slot(strLine.substring(6));
						slots.add(s);
						
					}
					
					if(strLine.charAt(0) == 'R'){
						
						Room r = new Room(strLine.substring(6));
						rooms.add(r);
						
					}
					
					else if(strLine.charAt(0) == 'I'){
						
						//System.out.println(strLine.substring(18) );
						Instructor ins = new Instructor(strLine.substring(12),slots.size());
						instructors.add(ins);
						
					}
						
						
					else if(strLine.charAt(0) == 'C'){
						
						
						String courseString = strLine.substring(8) + ",0,0,0,0,0,0";
						Course c = new Course(courseString);
						courses.add(c);
						
					}
			  }
			  
			 in.close();
			 
			}
			catch (Exception e){
				System.err.println("Error: " + e.getMessage());
			}
		 
			  /*
			  for ( Room room : rooms ){
				  
				  
				  System.out.print(room.name);
				  System.out.print(" 	");
				  System.out.println(room.capacity);
				  
			  }
			  
			  for ( Instructor ins : instructors ){
				  
				  
				  System.out.println(ins.index);
				  System.out.println(ins.name);
				  
				  for(int i : ins.unwantedSlotIndices){
					  System.out.print(i);
					  System.out.print(",");
				  }
				  System.out.println();
				  
				  for(int i : ins.unpreferredSlotIndices){
					  System.out.print(i);
					  System.out.print(",");
				  }
				  System.out.println();
				  System.out.println("----------------------------------");
				  
				  
				  
				  
			  }
			  
			  for(Course course : courses){
				  
				  
				  System.out.println(course.index);
				  System.out.println(course.name);
				  System.out.println(course.capacity);
				  System.out.println(course.arrangement);
				  System.out.println(course.instructorIndex);
				  System.out.println(course.day);
				  System.out.println(course.room);
				  System.out.println(course.hour);
				  System.out.println(course.day2);
				  System.out.println(course.room2);
				  System.out.println(course.hour2);
				  
				  System.out.println("----------------------------------");
				  
				  
				  
			  }
			  
			  for(Slot s : slots){
				  
				  System.out.print(s.day);
				  System.out.print("	");
				  System.out.println(s.hour);
				  
				  
			  }
			  */
			  
		
			
			
		  rand = new  Random();
		  schedules        = new ArrayList<>() ;
		  int popsize = 100;
		  int elitSize = 5;
		  int generation = 0;
		   int crossoverCount = 10;
		   int mutationProb = 20;
		   
		   for(int i=0;i<popsize;i++){
		   
				Schedule s = new Schedule(instructors,rooms,courses,slots);
				
				s.generate();
				
				schedules.add(s);
		   }
		   
		   while(true){
		   
				for(int i=0;i<crossoverCount;i++){
					
					Schedule newSchedule = new Schedule(instructors,rooms,courses,slots);
					
					
					int parent1 =rand.nextInt(popsize);
					int parent2 =rand.nextInt(popsize);
					
					schedules.get(parent1).crossover(schedules.get(parent2),newSchedule);
					
					schedules.add(newSchedule);
					
				}
				
				
			
			   Collections.sort(schedules, 
			   
			   
						new Comparator<Schedule>() {
							
							public int compare(Schedule c1, Schedule c2) {
								
								if (c1.fitness > c2.fitness) return 1;
								if (c1.fitness < c2.fitness) return -1;
								return 0;
							
							}
						
						}
						
						
				);
				
				
				
				while(schedules.size() > popsize){
					schedules.remove(schedules.size()-1);
				}
	
			System.out.print(generation);
			System.out.print("	");
			System.out.println(schedules.get(0).fitness);
							
			   if( schedules.get(0).fitness < 1){
				  
				   schedules.get(0).print();
					break;			   
			   }
				   
			   for( int s=elitSize;s<popsize;s++){
				   

				int prob =rand.nextInt(100);
	 
				if(prob < mutationProb)
				 schedules.get(s).mutate();
				   				   
			   }
			   
			   
			generation++; 
		  
		   }
		   
		   
        }
}
