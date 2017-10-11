//***************************************************************************************************************************************************
//
// Copyright (C) 2016 Selim Temizer.
//
// This file is part of CourseScheduler.
//
// CourseScheduler is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
//
// CourseScheduler is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License along with CourseScheduler. If not, see <http://www.gnu.org/licenses/>.
//
//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

import java.util.List         ;
import java.util.ArrayList    ;
import java.io.FileReader     ;
import java.io.BufferedReader ;

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class SchedulingProblem
{
  //=================================================================================================================================================

  // Course and room capacities

  public static final int SMALL  = 1 ;
  public static final int MEDIUM = 2 ;
  public static final int LARGE  = 3 ;

  // Course lecture hour arrangements

  public static final int A3     = 1 ;  /* Course is to be scheduled as 3 consecutive hours on a single day */
  public static final int A21    = 2 ;  /* Course is to be scheduled as 2 + 1 hours on two different days   */

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  public static String capacityToString ( int capacity )
  {
    switch ( capacity )
    {
      case SMALL  : return "Small"                  ;
      case MEDIUM : return "Medium"                 ;
      case LARGE  : return "Large"                  ;
      default     : return "Invalid capacity value" ;
    }
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  public static int stringToCapacity ( String text )
  {
    switch ( text )
    {
      case "Small"  : return SMALL  ;
      case "Medium" : return MEDIUM ;
      case "Large"  : return LARGE  ;
      default       : return -1     ;
    }
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  public static String arrangementToString ( int arrangement )
  {
    switch ( arrangement )
    {
      case A3  : return "A3"                        ;
      case A21 : return "A21"                       ;
      default  : return "Invalid arrangement value" ;
    }
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  public static int stringToArrangement ( String text )
  {
    switch ( text )
    {
      case "A3"  : return A3  ;
      case "A21" : return A21 ;
      default    : return -1  ;
    }
  }

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  public static boolean fits ( int courseCapacity , int roomCapacity )
  {
    return ( roomCapacity >= courseCapacity ) ;
  }

  //=================================================================================================================================================

  public Slot       [] slots       ;  // Note: Entry in index 0 is null
  public Room       [] rooms       ;  // Note: Entry in index 0 is null
  public Instructor [] instructors ;  // Note: Entry in index 0 is null
  public Course     [] courses     ;  // Note: Entry in index 0 is null

  //=================================================================================================================================================

  public SchedulingProblem ( String fileName , char commentStarter ) throws Exception
  {
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    List< Slot       > listSlots       = new ArrayList<>() ;
    List< Room       > listRooms       = new ArrayList<>() ;
    List< Instructor > listInstructors = new ArrayList<>() ;
    List< Course     > listCourses     = new ArrayList<>() ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    BufferedReader file = new BufferedReader( new FileReader( fileName ) ) ;
    String         line                                                    ;

    while ( ( line = getNextParsableLine( file , commentStarter ) ) != null )
    {
      String [] parts = line.split( "=" ) ;

      switch ( parts[0].trim() )
      {
        case "Slot"       : listSlots      .add( new Slot      ( parts[1].trim() ) ) ;  break ;
        case "Room"       : listRooms      .add( new Room      ( parts[1].trim() ) ) ;  break ;
        case "Instructor" : listInstructors.add( new Instructor( parts[1].trim() ,45) ) ;  break ;
        case "Course"     : listCourses    .add( new Course    ( parts[1].trim() ) ) ;  break ;
        default           : System.err.println ( "Invalid line in input file!"     ) ;  break ;
      }
    }

    file.close() ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    slots       = new Slot       [ listSlots      .size() + 1 ] ;
    rooms       = new Room       [ listRooms      .size() + 1 ] ;
    instructors = new Instructor [ listInstructors.size() + 1 ] ;
    courses     = new Course     [ listCourses    .size() + 1 ] ;

    for ( Slot       s : listSlots       )  { slots      [ s.index ] = s ; }
    for ( Room       r : listRooms       )  { rooms      [ r.index ] = r ; }
    for ( Instructor i : listInstructors )  { instructors[ i.index ] = i ; }
    for ( Course     c : listCourses     )  { courses    [ c.index ] = c ; }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
  }

  //=================================================================================================================================================

  private String getNextParsableLine ( BufferedReader file , char commentStarter ) throws Exception
  {
    while ( true )
    {
      String line = file.readLine() ;

      if ( line == null )  { return null ; }

      line = line.trim() ;

      if ( line.equals( "" )                   )  { continue ; }
      if ( line.charAt( 0  ) == commentStarter )  { continue ; }

      return line ;
    }
  }

  //=================================================================================================================================================

  public void print ()
  {
    System.out.println( "Slots"       + "\n" ) ;  for ( Slot       s : slots       )  { System.out.println( s ) ; }  System.out.println( "\n" ) ;
    System.out.println( "Rooms"       + "\n" ) ;  for ( Room       r : rooms       )  { System.out.println( r ) ; }  System.out.println( "\n" ) ;
    System.out.println( "Instructors" + "\n" ) ;  for ( Instructor i : instructors )  { System.out.println( i ) ; }  System.out.println( "\n" ) ;
    System.out.println( "Courses"     + "\n" ) ;  for ( Course     c : courses     )  { System.out.println( c ) ; }  System.out.println( "\n" ) ;
  }

  //=================================================================================================================================================

  public static void main ( String [] args ) throws Exception
  {
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    String fileName       = "Input.txt" ;
    char   commentStarter = '#'         ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    if ( args.length > 0 )  { fileName       = args[0].trim()             ; }
    if ( args.length > 1 )  { commentStarter = args[1].trim().charAt( 0 ) ; }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    SchedulingProblem problem = new SchedulingProblem( fileName , commentStarter ) ;

    problem.print() ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

