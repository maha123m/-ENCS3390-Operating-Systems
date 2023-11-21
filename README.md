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
  <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/e0a406ab-f8ac-4637-9124-e508d0ec9a4e" width="900" height="400"> 
  

  <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/be1cc3dc-4d7f-462d-a4f2-b8f97afea249" width="900" height="400"> 
  

  <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/cd838d56-3bc6-466b-884d-32fe34a203db" width="900" height="400">  
  

  <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/24facdfd-e9a2-41e6-aff7-eb9d83dc70f3" width="900" height="400"> 

  
  <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/0e293512-be4e-49d2-92ad-49385e4bd4f1" width="900" height="400"> 
  

   <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/c89d8bd2-00c9-4362-a6fe-2a45dabfbabd" width="900" height="400"> 
   

    <img src ="https://github.com/maha123m/ENCS3390-Operating-Systems/assets/99613493/b15d2000-1018-4f52-a09e-4fa14ccad738" width="900" height="400"> 

  

   
</div>





## Partner 
    Lama Naser 1200190 

  
