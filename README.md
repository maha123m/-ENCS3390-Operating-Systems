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

### 1.Multilevel Feedback Queue
     Implements a multilevel feedback queue scheduling algorithm.
     Utilizes four queues with varying priorities: RR with different time quanta, SRTF, and FCFS.
   
### 2.Queue Characteristics
      Round Robin (RR) queues with customizable time quanta.
      Shortest-Remaining-Time First (SRTF) for efficient CPU burst handling.
      First-Come-First-Serve (FCFS) for the lowest priority queue.
      
### 3.User-Configurable Parameters
      Allows users to set time quanta for RR queues.
      Incorporates 𝛼 parameter for predicting the duration of the next CPU burst in SRTF.

## Results 

<div>
  <img src ="https://github.com/maha123m/ENCS4370-Computer-Architecture-Project-2/assets/99613493/7c3af2c7-2ec9-4fd9-87a3-7d98ca228c70" width="800" height="400"> 
  

  <img src ="https://github.com/maha123m/ENCS4370-Computer-Architecture-Project-2/assets/99613493/1fc65742-42df-4594-adb6-6b4d71c7e3b3" width="900" height="400"> 
  

  <img src ="https://github.com/maha123m/ENCS4370-Computer-Architecture-Project-2/assets/99613493/e983dae5-e5f2-415f-8d7c-bb905b6f0ab0" width="900" height="400">  
  

  <img src ="https://github.com/maha123m/ENCS4370-Computer-Architecture-Project-2/assets/99613493/6ef57ad1-e730-4f42-bdab-79b6135e0278" width="900" height="400"> 

  
  <img src ="https://github.com/maha123m/ENCS4370-Computer-Architecture-Project-2/assets/99613493/997c3e40-4e63-45d9-b991-ba628c588077" width="900" height="400"> 

   
</div>





## Partner 
    Lama Naser 1200190 

  
