
import java.util.Arrays ;
import java.util.Random       ;
import java.util.List         ;
import java.util.ArrayList    ;
public class Schedule{

	

	public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> schedule;	
	private int dayCount ;
	private int hourCount ;
	private int roomCount ;
	private static  Random     rand            ;
	public List< Instructor    > instructors       ;
	public List< Room > rooms             ;
	public List< Course > courses           ;
	public List<Slot> slots;
	public List< String    > days              ;
    public List< String    > hours             ;
	
	public double fitness;
	
	public Schedule(List< Instructor    > instructors,
					List< Room > rooms,
					List< Course > courses,
					List<Slot> slots){
		
		schedule = new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();
		
		
		this.instructors =new ArrayList< Instructor    >( );
		this.rooms = rooms;
		this.courses = new ArrayList< Course >();
		this.slots = slots;
		
		for(Course course : courses){
			
			Course c = new Course(course.toString());
			this.courses.add(c);
			
		}
		
		
		for(Instructor ins : instructors){
			
			Instructor c = new Instructor(ins.toString(),slots.size());
			this.instructors.add(c);
			
		}
		
		days        = new ArrayList<>() ;
    hours       = new ArrayList<>() ;
		
		days        . add( "Monday"                                                                                                                ) ;
		days        . add( "Tuesday"                                                                                                               ) ;
		days        . add( "Wednesday"                                                                                                             ) ;
		days        . add( "Thursday"                                                                                                              ) ;
		days        . add( "Friday"                                                                                                                ) ;
		
		hours       . add(  "8:40"                                                                                                                 ) ;
		hours       . add(  "9:40"                                                                                                                 ) ;
		hours       . add( "10:40"                                                                                                                 ) ;
		hours       . add( "11:40"                                                                                                                 ) ;
		hours       . add( "12:40"                                                                                                                 ) ;
		hours       . add( "13:40"                                                                                                                 ) ;
		hours       . add( "14:40"                                                                                                                 ) ;
		hours       . add( "15:40"                                                                                                                 ) ;
		hours       . add( "16:40"    																											   );
		
		
		
		
		fitness= 0;
		rand      = new Random     () ;
		
		    dayCount = 5;
		    hourCount = 9;
		    roomCount = rooms.size();
		   
		
		for(int i=0;i<dayCount;i++){
			
			
			ArrayList<ArrayList<ArrayList<String>>> temp1 = new ArrayList<ArrayList<ArrayList<String>>>();
			
			for(int j=0;j<roomCount;j++){
				
				ArrayList<ArrayList<String>> temp2 = new ArrayList<ArrayList<String>>();
				
				for(int k=0;k<hourCount;k++){
					
					
					ArrayList<String> temp3 = new ArrayList<String>();	
					temp2.add(temp3);
					
				}
				
				temp1.add(temp2);
				
				
			}
		
			schedule.add(temp1);
		}
		
		
		
		
		
		
	}
	
	
	public void addCourse(Course course, int day, int room, int hour){
		
					course.day = day;
					course.room = room;
					course.hour = hour;
					course.day2 = -1;
					course.room2 = -1;
					course.hour2 = -1;
					
					for(int i=0; i< 3; i++){
						schedule.get(day).get(room).get(hour+i).add((String)course.name);
						int slotIndex = day*9 + hour + i;
						instructors.get(course.instructorIndex-1).slots[slotIndex]++;
					}
				
				
		
	}
	
	
	public void addCourse(Course course, int day, int room, int hour, int day2, int room2, int hour2){
		
		course.day = day;
		course.room = room;
		course.hour = hour;
		
		
		for(int i=0; i< 2; i++){
			
			
			schedule.get(day).get(room).get(hour+i).add((String)course.name);
			
			int slotIndex = day*9 + hour + i;
			instructors.get(course.instructorIndex-1).slots[slotIndex]++;
			
		}
		
		
		course.day2 = day2;
		course.room2= room2;
		course.hour2 = hour2;
		
		schedule.get(day2).get(room2).get(hour2).add((String)course.name);
	    int slotIndex = day2*9 + hour2;
		instructors.get(course.instructorIndex-1).slots[slotIndex]++;
				
				
		
	}
	
	
	
	public void removeCourse(Course s){
		
		
		if(s.arrangement == 2){
			
				int oldDay  = s.day;
				int oldRoom = s.room;
				int oldHour = s.hour;
				
				//System.out.println( s[5] );
				//System.out.println( s[6] );
				//System.out.println( s[7] );
				//System.out.println( s[0] );
			
				for(int j=0;j<2;j++){
					
					//List<String> slot = schedule.get(oldDay).get(oldRoom).get(oldHour+j);
				
					//for(String ss : slot)
						//System.out.println( ss );
					
					int index = schedule.get(oldDay).get(oldRoom).get(oldHour+j).indexOf(s.name);
					if(index != -1)
						schedule.get(oldDay).get(oldRoom).get(oldHour+j).remove(index);
					
					int slotIndex = oldDay*9 + oldHour + j;
					instructors.get(s.instructorIndex-1).slots[slotIndex]--;
				
				}
				
				
				
			
			
				int oldDay2  = s.day2;
				int oldRoom2 = s.room2;
				int oldHour2 = s.hour2;
				
				
				//System.out.println(oldDay);
				//System.out.println(oldRoom);
				//System.out.println(oldHour);
				//System.out.println(oldDay2);
				//System.out.println(oldRoom2);
				//System.out.println(oldHour2);
				
				//List<String> slot = schedule.get(oldDay2).get(oldRoom2).get(oldHour2);
				
					//for(String ss : slot)
						//System.out.println( ss );
					
					int index = schedule.get(oldDay2).get(oldRoom2).get(oldHour2).indexOf(s.name);
					if(index != -1)
						schedule.get(oldDay2).get(oldRoom2).get(oldHour2).remove(index);
					
					int slotIndex = oldDay2*9 + oldHour2;
					instructors.get(s.instructorIndex-1).slots[slotIndex]--;
				
				
			
			}
			
			else {
				
				
				int oldDay  = s.day;
				int oldRoom = s.room;
				int oldHour = s.hour;
				
				//System.out.println(oldDay);
				//System.out.println(oldRoom);
				//System.out.println(oldHour);
				
			
				for(int j=0;j<3;j++){
					
					//List<String> slot = schedule.get(oldDay).get(oldRoom).get(oldHour+j);
				
					//for(String ss : slot)
						//System.out.println( ss );
					
					int index = schedule.get(oldDay).get(oldRoom).get(oldHour+j).indexOf(s.name);
					if(index != -1)
						schedule.get(oldDay).get(oldRoom).get(oldHour+j).remove(index);
					
					int slotIndex = oldDay*9 + oldHour + j;
					instructors.get(s.instructorIndex-1).slots[slotIndex]--;
				
				}
			}
	
	}
				
					
					
	public void generate(){
		
		
		for ( Course course : courses ){
				
				
				
				
				int    arrangement     = course.arrangement  ;
				
				
				if(arrangement == 1){
				
				
					int day = rand.nextInt( dayCount );
					int hour = rand.nextInt( hourCount-2 );
					int room = rand.nextInt( roomCount );
					
					
					addCourse(course,day,room,hour);
				
				}
				
				else{
					
					
					int day = rand.nextInt( dayCount );
					int hour = rand.nextInt( hourCount-1 );
					int room = rand.nextInt( roomCount );
					
					int day2 = day;
					while(day2 == day){
						
						day2 = rand.nextInt( dayCount );
					}
					
					int hour2 = rand.nextInt( hourCount);
					int room2 = rand.nextInt( roomCount );
					
					addCourse(course,day,room,hour,day2,room2,hour2);
					
					
				}
			
			}
	
		calcFitness();
	
	}

	
	
	public void print(){
		
		
		String hourString="      |";
		
		for(String s : hours){
			
			while(s.length() < 12 ){
						
				s += " ";
			}
					
			
			s += "|";
			hourString += s;
		}
		
		
		
		String sep="";
		for(int i=0;i<hourString.length();i++)
			sep += "-";
		
		String sep2="";
		for(int i=0;i<hourString.length();i++)
			sep2 += "*";
		
		
		int c=0;
		for(ArrayList<ArrayList<ArrayList<String>>> day : schedule ){
			
			System.out.println(days.get(c));
			System.out.println(hourString);
			System.out.println(sep2); 
			int r=0;
			for(ArrayList<ArrayList<String>> room : day ){
				
				System.out.print(rooms.get(r).name + " |");
				for(ArrayList<String> hour : room){
					
					String output = "";
					if(hour.size() > 0)
						output = hour.get(0);
					
					//output = " " + output;
					
					while(output.length() < 12 ){
						
						output += " ";
					}
					
					System.out.print(output + "|");
					
				}
				System.out.println();
				System.out.println(sep);
				r++;
			}
				
			
			System.out.println();
			
			c++;
		}
	
	}
	
	
	public void calcFitness(){
		
		
		double fit=0;
	
		for(ArrayList<ArrayList<ArrayList<String>>> day : schedule ){
			
			for(ArrayList<ArrayList<String>> room : day ){
				
				for(ArrayList<String> hour : room){
					
					if(hour.size() > 1)
						fit += hour.size() - 1;
				}
			}
		}
		
		
		
		for ( Course course : courses ){
			
			int    capacity = course.capacity ;
				int room = course.room;
				int roomCapacity = rooms.get(room).capacity;
				
				//if(capacity > roomCapacity)
					fit += Math.abs(capacity - roomCapacity);
			
			if(course.arrangement == 2){
				
				room = course.room2;
				roomCapacity = rooms.get(room).capacity;
				
				//if(capacity > roomCapacity)
					fit += Math.abs(capacity - roomCapacity);
			
			}
		
		}
		
		for(Instructor ins : instructors){
			
			for(int i : ins.slots){
				
				if(i > 1)
						fit += i - 1;
				
				
			}
			
			
			
			for(int i : ins.unwantedSlotIndices){
				
				if(ins.slots[i-1] > 0 )
					fit++;
				
			}
			
			for(int i : ins.unpreferredSlotIndices){
				
				if(ins.slots[i-1] > 0 ){
					
					
					fit += 0.001;
					
				}
					
				
			}
		
		}
		
		fitness = fit;
		
	}
	
	
	
	public void mutate(){
		
		
		int numberOfMutations = 2;
		
		for(int i=0;i<numberOfMutations;i++){
			
			int mutationPoint = rand.nextInt(courses.size());
			
			Course s = 	courses.get(mutationPoint);
			
			
			
			removeCourse(s);
			///System.out.println(s[0]);
			if(s.arrangement == 2){
			
				int day = rand.nextInt( dayCount );
				int hour = rand.nextInt( hourCount-1 );
				int room = rand.nextInt( roomCount );
				
				int day2 = day;
				
				while(day2 == day){
					
					day2 = rand.nextInt( dayCount );
				}
				
				
				int hour2 = rand.nextInt( hourCount);
				int room2 = rand.nextInt( roomCount );
				
				addCourse(s,day,room,hour,day2,room2,hour2);
					
				//System.out.println(day);
				//System.out.println(room);
				//System.out.println(hour);
				//System.out.println(day2);
				//System.out.println(room2);
				//System.out.println(hour2);
				
				
			}
			
			else {

				int day = rand.nextInt( dayCount );
				int hour = rand.nextInt( hourCount-2 );
				int room = rand.nextInt( roomCount );
				
				addCourse(s,day,room,hour);
				
				//System.out.println(day);
				//System.out.println(room);
				//System.out.println(hour);
				
			}
			
		}
		
		calcFitness();
	
	}
	
	
	public void crossover( Schedule in , Schedule out){
		
			
			int courseSize = courses.size();
			int crossoverPointStart = rand.nextInt(courseSize);
			int crossoverPointEnd = crossoverPointStart + rand.nextInt(courseSize - crossoverPointStart);
			
			
			
			
			for(int i = 0; i < crossoverPointStart;i++ ){
				
				Course c = this.courses.get(i);
				if(c.arrangement == 2){
				
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour,c.day2,c.room2,c.hour2);
					
				}
				
				else{
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour);
				}
				
			}
			
			
			for(int i=crossoverPointStart;i<crossoverPointEnd;i++){
				
				
				Course c = in.courses.get(i);
				if(c.arrangement == 2){
				
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour,c.day2,c.room2,c.hour2);
					
				}
				
				else{
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour);
				}
				
			}
			
			
			for(int i = crossoverPointEnd; i < courseSize;i++ ){
				
				Course c = this.courses.get(i);
				if(c.arrangement == 2){
				
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour,c.day2,c.room2,c.hour2);
					
				}
				
				else{
					
					out.addCourse(out.courses.get(i),c.day,c.room,c.hour);
				}
				
			}
		
	
			out.calcFitness();	
			
		
		

	}
	
}