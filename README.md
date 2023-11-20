# -ENCS3390-Operating-Systems
## Project Overview: Multilevel Feedback Queue Scheduling Simulator

This project involves simulating a multilevel feedback queue scheduling algorithm and reporting performance 
measures for randomly generated workloads. The project is designed for groups of two students and consists 
of two main parts: the Workload Generator and the Simulator.

## Workload Generator
Generates a random workload and saves it to a file with the specified structure.
Workload file structure includes Process ID (PID), Arrival Time, CPU Burst, and IO Burst times.
User-configurable parameters include the number of processes, maximum arrival time, maximum number of CPU bursts, and ranges for IO and CPU burst durations.
The generated workload file serves as input for the simulator. 

## Simulator
  The simulator component of this project offers a dynamic and interactive experience in simulating 
  a multilevel feedback queue scheduling algorithm. Here are the key features:

### 1.Multilevel Feedback Queue:
   -Implements a multilevel feedback queue scheduling algorithm.
   -Utilizes four queues with varying priorities: RR with different time quanta, SRTF, and FCFS.
   
### 2.Queue Characteristics:
  Round Robin (RR) queues with customizable time quanta.
  Shortest-Remaining-Time First (SRTF) for efficient CPU burst handling.
  First-Come-First-Serve (FCFS) for the lowest priority queue.
      
### 3.User-Configurable Parameters:
  ##### Allows users to set time quanta for RR queues.
  ##### Incorporates 𝛼 parameter for predicting the duration of the next CPU burst in SRTF.






## Partner 
  .Lama Naser 1200190

  
