
<html>
<body>
<h1> Course Scheduler using Genetic Algorithm   </h1>

<p> Java project which solves the course scheduling problem with genetic algorithm.  <br>

<h2> The problem   </h2>
<p> The problem has some constraints: <br>

<ul style="list-style-type:disc">
<li>An instructor cannot offer more than one course at a time slot.</li>
<li>In a room, more than one course is not allowed at the same time</li>
<li>The rooms have limited capacities with three levels : small,medium large. The courses cannot be take place in a room which has lower level capacity than the course's capacity.  </li>
<li>The instructors have unwanted time slots, in these slots the instructor cannot have a course</li>
<li>The instructors have unpreferred time slots, in these slots it is better not to assing the instructor a course (soft constraint)</li>
</ul>

<p> see input files to better understand constraints. <br>

<h2> The solution </h2>

<p>The courses are randomly placed into schedule at first. Then, using genetic algorithm, the schedule is evolved to a valid one. <br>
<p> In the algorithm, the fitness is incremented by 1 for each violated hard-constraint and 0.001 for each violated soft constraint.
Then the algorithm tries to mininmize the fitness. It is assumed that the total number of soft constraint is at most 999 in order that the soft constraints cannot surpass the hard constraints.<br>

<h2>Compile and Run </h2>
<p> Use  “javac *.java”  to compile all sources <br>
<p>Use ScheduleSolver executable by providing the path of the input file as follows :<br>
<p>java ScheduleSolver path-to-input<br>


</html>
</body>
