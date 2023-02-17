package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main extends Application {
	static ArrayList<Process> memory = new ArrayList<>();// to store what i read from file if procees time equal arraival time remove frome memory
	static ArrayList<Process> memoryA = new ArrayList<>();//to store what i read from file (don't remove proceese)
	static ArrayList<Process> queue1 = new ArrayList<>();
	static ArrayList<Process> queue2 = new ArrayList<>();
	static ArrayList<Process> queue3 = new ArrayList<>();
	static ArrayList<Process> queue4 = new ArrayList<>();
	static ArrayList<Process> waiting = new ArrayList<>();//for queue 1
	static ArrayList<Process> waiting1 = new ArrayList<>();//for queue 2
	static ArrayList<Process> waiting2 = new ArrayList<>();//for queue 3
	static ArrayList<Process> waiting3 = new ArrayList<>();//for queue 4
	static ArrayList<String> gantProcesses = new ArrayList<>();
	static ArrayList<String> recordings = new ArrayList<>();// when user enter the time to show the procese
	static ArrayList<String> comp = new ArrayList<>();// when proces finsh all cpu burst and i/o to show the sequnce
	static ArrayList<Integer> timeChartF = new ArrayList<>();
	static ArrayList<Integer> timeChartI = new ArrayList<>();
	static String path = "C:\\Users\\Lenovo\\eclipse-workspace\\mahaaaa1\\src";
	static int time;
	static int NumProcess;
	static int maxArrival;
	static int MaxCPUBurst;
	static int minIO;
	static int maxIO;
	static int minCPU;
	static int maxCPU;
	static String fileName;
	static int q1;
	static int q2;
	static double alpha;
	static int gueusValue = 50;

	@Override
	public void start(Stage stage) {
		DropShadow shadow = new DropShadow();
		VBox pane = new VBox();
		Image image4 = new Image(path+"\\hello.png");
		ImageView imagestart = new ImageView(image4);
		imagestart.setFitHeight(100);
		imagestart.setFitWidth(100);
		Label L = new Label("Welcom for our Sechdular Simulation\n", imagestart);
		L.setFont(Font.font(L.getText(), FontWeight.BOLD, 40));
		HBox H = new HBox();
		Image image5 = new Image(path+"\\surprise-box.png");
		ImageView imageRun = new ImageView(image5);
		imageRun.setFitHeight(100);
		imageRun.setFitWidth(100);
		Button random = new Button("randomly workload", imageRun);
		random.setContentDisplay(ContentDisplay.TOP);
		random.setFont(Font.font(random.getText(), FontWeight.BOLD, 30));
		random.setStyle("-fx-background-color:lightpink");
		;
		random.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			random.setEffect(shadow);
		});
		random.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			random.setEffect(null);
		});
		Image image6 = new Image(path+"\\choice.png");
		ImageView imageSelect = new ImageView(image6);
		imageSelect.setFitHeight(100);
		imageSelect.setFitWidth(100);
		Button select = new Button("select workload", imageSelect);
		select.setContentDisplay(ContentDisplay.TOP);
		select.setFont(Font.font(select.getText(), FontWeight.BOLD, 30));
		select.setStyle("-fx-background-color:lightpink");
		select.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			select.setEffect(shadow);
		});
		select.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			select.setEffect(null);
		});
		H.getChildren().addAll(random, select);
		H.setSpacing(200);
		H.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(L, H);
		pane.setSpacing(200);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane, 1000, 650);

		stage.setFullScreen(true);

		random.setOnAction(e -> {
			randomlyFile(stage, scene);
			fileName = "output.txt";
		});

		select.setOnAction(c -> {
			VBox Pane = new VBox();
			Image image8 = new Image(path+"\\writing.png");
			ImageView imagewrite = new ImageView(image8);
			imagewrite.setFitHeight(130);
			imagewrite.setFitWidth(130);
			Label L1 = new Label(" Please enter a file name\n", imagewrite);
			L1.setFont(Font.font(L.getText(), FontWeight.BOLD, 40));
			HBox H1 = new HBox();
			Image image7 = new Image(path+"\\start.png");
			ImageView imageStart = new ImageView(image7);
			imageStart.setFitHeight(60);
			imageStart.setFitWidth(60);
			Button start = new Button("start", imageStart);
			start.setFont(Font.font(start.getText(), FontWeight.BOLD, 30));
			start.setStyle("-fx-background-color:white");
			;
			start.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				start.setEffect(shadow);
			});
			start.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				start.setEffect(null);
			});
			TextField T1 = new TextField();
			T1.setFont(Font.font(T1.getText(), FontWeight.BOLD, 22));
			H1.getChildren().addAll(L1, T1);
			H1.setSpacing(100);
			H1.setAlignment(Pos.CENTER);
			Pane.getChildren().addAll(H1, start);
			Pane.setSpacing(150);
			Pane.setAlignment(Pos.CENTER);
			scene.setRoot(Pane);
			Pane.setStyle("-fx-background-color:lightblue;");

			start.setOnAction(b -> {
				if (T1.getText().isEmpty())
					error(" Fill the text first");
				else {
					fileName = T1.getText();
					try {
						File myObj = new File(fileName);
						Scanner myReader = new Scanner(myObj);
						if (myObj.exists()) {
							readFile();
							tokensFromUser(stage, scene);
						}
						myReader.close();
					} catch (FileNotFoundException e) {
						error("This file does not exist");
					}
				}
			});

			stage.setScene(scene);
			stage.show();

		});

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void randomlyFile(Stage stage, Scene scene) {// this method to take input from user and store them in file 
		Label header = new Label("Welcome To Our Randomly Workload");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		Image image4 = new Image(path+"\\Add.png");
		ImageView ba = new ImageView(image4);
		ba.setFitHeight(40);
		ba.setFitWidth(40);
		Button save = new Button("Save", ba);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		Image image5 = new Image(path+"\\start.png");
		ImageView imageRun = new ImageView(image5);
		imageRun.setFitHeight(40);
		imageRun.setFitWidth(40);
		Button start = new Button("start", imageRun);
		start.setFont(Font.font(start.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, start);
		Label pL = new Label("Number of processes ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label aL = new Label("Max arrival Time ");
		aL.setFont(Font.font(aL.getText(), FontWeight.BOLD, 22));
		TextField aT = new TextField();
		aT.setFont(Font.font(aT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("Max number of CPU burst ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label rIOLm = new Label("Min rang for i/o burst ");
		rIOLm.setFont(Font.font(rIOLm.getText(), FontWeight.BOLD, 22));
		TextField rIOTm = new TextField();
		rIOTm.setFont(Font.font(rIOTm.getText(), FontWeight.BOLD, 22));
		Label rIOLmx = new Label("Max rang for i/o burst ");
		rIOLmx.setFont(Font.font(rIOLmx.getText(), FontWeight.BOLD, 22));
		TextField rIOTmx = new TextField();
		rIOTmx.setFont(Font.font(rIOTmx.getText(), FontWeight.BOLD, 22));
		Label rcLm = new Label("Min rang for CPU burst ");
		rcLm.setFont(Font.font(rcLm.getText(), FontWeight.BOLD, 22));
		TextField rcTm = new TextField();
		rcTm.setFont(Font.font(rcTm.getText(), FontWeight.BOLD, 22));
		Label rcLmx = new Label("Max rang for CPU burst ");
		rcLmx.setFont(Font.font(rcLmx.getText(), FontWeight.BOLD, 22));
		TextField rcTmx = new TextField();
		rcTmx.setFont(Font.font(rcTmx.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(aL, 0, 1);
		root.add(aT, 1, 1);
		root.add(cL, 0, 2);
		root.add(cT, 1, 2);
		root.add(rIOLm, 0, 3);
		root.add(rIOTm, 1, 3);
		root.add(rIOLmx, 0, 4);
		root.add(rIOTmx, 1, 4);
		root.add(rcLm, 0, 5);
		root.add(rcTm, 1, 5);
		root.add(rcLmx, 0, 6);
		root.add(rcTmx, 1, 6);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(30);
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(
				new BackgroundImage(new Image(path+"\\backgraund.png"),
						null, null, null, null)));

		save.setOnAction(e -> {
			if (pT.getText().isEmpty() || aT.getText().isBlank() || cT.getText().isEmpty() || rIOTm.getText().isEmpty()
					|| rIOTmx.getText().isEmpty() || rcTm.getText().isEmpty() || rcTmx.getText().isEmpty())
				error("Please fill all text fields with numbers\\n");
			else {
				NumProcess = Integer.parseInt(pT.getText());
				maxArrival = Integer.parseInt(aT.getText());
				MaxCPUBurst = Integer.parseInt(cT.getText());
				minIO = Integer.parseInt(rIOTm.getText());
				maxIO = Integer.parseInt(rIOTmx.getText());
				minCPU = Integer.parseInt(rcTm.getText());
				maxCPU = Integer.parseInt(rcTmx.getText());

				creatFile();
			}
		});

		start.setOnAction(b -> {
			readFile();
			tokensFromUser(stage, scene);
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}

	public static void error(String s) {// this method to notice the user when forget enter any field
		Stage st = new Stage();
		HBox h = new HBox();
		ImageView image = new ImageView(path+"\\warning.png");
		image.setFitWidth(50);
		image.setFitHeight(50);
		Label l = new Label(s, image);
		l.setFont(Font.font(30));
		h.setAlignment(Pos.CENTER);
		h.getChildren().add(l);
		h.setStyle("-fx-background-color:white;");
		Scene se = new Scene(h, 600, 200);
		st.setScene(se);
		st.show();
	}

	public static void creatFile() {// to create the file to store user input 
		try {
			FileWriter out = new FileWriter("output.txt");
			for (int i = 0; i < NumProcess; i++) {
				int cpuBursts = (int) (Math.random() * (MaxCPUBurst) + 1);
				String PID = "P" + i;
				int arrive = (int) (Math.random() * (maxArrival + 1));
				String s1 = PID + "\t" + arrive + "\t";
				String s2 = "";
				if (cpuBursts != 1) {
					for (int j = 0; j < cpuBursts - 1; j++) {
						int cburst = (int) ((Math.random() * (maxCPU - minCPU + 1)) + minCPU);
						int IOburst = (int) ((Math.random() * (maxIO - minIO + 1)) + minIO);
						s2 += cburst + "\t" + IOburst + "\t";
					}
				}
				s2 += (int) ((Math.random() * (maxCPU - minCPU + 1)) + minCPU);
				out.write(s1 + s2 + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile() {//to read the file 
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data[] = myReader.nextLine().split("\t");
				ArrayList<Integer> CPU_burst = new ArrayList<Integer>();
				ArrayList<Integer> IO_burst = new ArrayList<Integer>();
				if (data.length == 3) {
					CPU_burst.add(Integer.parseInt(data[2]));
				} else {
					for (int i = 2; i < data.length - 2; i++) {
						CPU_burst.add(Integer.parseInt(data[i]));
						// System.out.println(CPU_burst);
						IO_burst.add(Integer.parseInt(data[i + 1]));
						i++;
					}
					CPU_burst.add(Integer.parseInt(data[data.length - 1]));
				}
				Process p = new Process(data[0], CPU_burst, Integer.parseInt(data[1]), IO_burst);
				memory.add(p);
				memoryA.add(p);
				// System.out.println(memory);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		Collections.sort(memory);
	}

	public static void tokensFromUser(Stage stage, Scene scene) {// to take input from user
		Label header = new Label("Please fill the following texts");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		Image image4 = new Image(path+"\\Add.png");
		ImageView ba = new ImageView(image4);
		ba.setFitHeight(40);
		ba.setFitWidth(40);
		Button save = new Button("Save", ba);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		Image image5 = new Image(path+"\\start.png");
		ImageView imageRun = new ImageView(image5);
		imageRun.setFitHeight(40);
		imageRun.setFitWidth(40);
		Button start = new Button("start", imageRun);
		start.setFont(Font.font(start.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, start);
		Label L1 = new Label("quantum for queue 1 ");
		L1.setFont(Font.font(L1.getText(), FontWeight.BOLD, 22));
		TextField T1 = new TextField();
		T1.setFont(Font.font(T1.getText(), FontWeight.BOLD, 22));
		Label L2 = new Label("quantum for queue 2 ");
		L2.setFont(Font.font(L2.getText(), FontWeight.BOLD, 22));
		TextField T2 = new TextField();
		T2.setFont(Font.font(T2.getText(), FontWeight.BOLD, 22));
		Label L3 = new Label("the value of alpha for queue 3 ");
		L3.setFont(Font.font(L3.getText(), FontWeight.BOLD, 22));
		TextField T3 = new TextField();
		T3.setFont(Font.font(T3.getText(), FontWeight.BOLD, 22));
		root.add(L1, 0, 0);
		root.add(T1, 1, 0);
		root.add(L2, 0, 1);
		root.add(T2, 1, 1);
		root.add(L3, 0, 2);
		root.add(T3, 1, 2);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(30);
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(new BackgroundImage(new Image(path+"\\backgraund.png"),
				null, null, null, null)));

		save.setOnAction(e -> {
			if (T1.getText().isEmpty() || T2.getText().isBlank() || T3.getText().isEmpty())
				error("Please fill all text fields with numbers");
			else {
				q1 = Integer.parseInt(T1.getText());
				q2 = Integer.parseInt(T2.getText());
				alpha = Double.parseDouble(T3.getText());
			}
		});

		start.setOnAction(b -> {
			execution(stage, scene);
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();

	}

	public static void execution(Stage stage, Scene scene) {
		time = 0;
		timeChartF.add(time);
		while (queue1.size() != 0 || memory.size() != 0 || waiting.size() != 0 || queue2.size() != 0
				|| queue3.size() != 0 || queue4.size() != 0 || waiting1.size() != 0 || waiting2.size() != 0
				|| waiting3.size() != 0) {
			isExistAtTime();
			isExistAtTime1();
			isExistAtTime2();
			isExistAtTime3();
			if (queue1.size() == 0 && queue2.size() == 0 && queue3.size() == 0 && queue4.size() == 0) {
				while(queue1.size() == 0 && queue2.size() == 0 && queue3.size() == 0 && queue4.size() == 0) {
					printInfo("The CPU IDL");
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
				}
				gantProcesses.add("IDL");
				timeChartF.add(time);
			}
			queue1Algo();
			queue2Algo();
			queue3Algo();
			queue4Algo();
		}
		System.out.println("\n\nThe system finished");
		System.out.println("The sequnce in gant chartis\n" + gantProcesses);
		System.out.println("the sequnce in completion is " + comp);
		System.out.println("last Time = " + time);
		for (int i = 0; i < memoryA.size(); i++) {
			System.out.println(memoryA.get(i).getPID() + " -> " + memoryA.get(i).getCompletion_time());// completion time for each process
		}
		choose(stage, scene);
	}

	public static void queue1Algo() {// algorithm for first queue Round Robin 
		isExistAtTime();
		isExistAtTime1();
		isExistAtTime2();
		isExistAtTime3();
		int i = 0;
		while (queue1.size() != 0) {
			int j = 0;
			int flag = 0;
			while (flag == 0) {
				if (queue1.get(i).getCPU_burst().get(j) == 0) {
					j++;
				} else
					flag = 1;
			}
			enterCPU(i, j);
			isExistAtTime();
		}
	}

	public static void isExistAtTime() {//check if there process in memory & waiting  less then time we reach 
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i).getArrival_time() <= time) {
				queue1.add(memory.get(i));
				memory.remove(i);
				i--;
			}
		}
		if (waiting.size() != 0) {
			for (int i = 0; i < waiting.size(); i++) {
				if (waiting.get(i).getArrival_time() <= time) {
					queue1.add(waiting.get(i));
					waiting.remove(i);
					i--;
				}
			}
		}
	}

	public static void isExistAtTime1() {//check if there process waiting 1 
		for (int i = 0; i < waiting1.size(); i++) {
			if (waiting1.get(i).getArrival_time() <= time) {
				queue2.add(waiting1.get(i));
				waiting1.remove(i);
				i--;
			}
		}
	}

	public static void isExistAtTime2() {//check if there process waiting 2 
		for (int i = 0; i < waiting2.size(); i++) {
			if (waiting2.get(i).getArrival_time() <= time) {
				queue3.add(waiting2.get(i));
				nextBurstEstimated(queue3.size()-1);
				waiting2.remove(i);
				i--;
			}
		}
	}

	public static void isExistAtTime3() {//check if there process waiting 1 
		for (int i = 0; i < waiting3.size(); i++) {
			if (waiting3.get(i).getArrival_time() <= time) {
				queue4.add(waiting3.get(i));
				waiting3.remove(i);
				i--;
			}
		}
	}

	public static void enterCPU(int pNum, int cpuNum) {// for queue1  to check quanta and move process to the queue
		for (int k = 0; k < q1; k++) {
			if (cpuNum == (queue1.get(pNum).getCPU_burst().size() - 1)) {//if reach last cpu burst(no i/o after it)
				if (queue1.get(pNum).getCPU_burst().get(cpuNum) > 0) {// last index no i/o after it print info 
					printInfo(queue1.get(pNum).getPID());
					queue1.get(pNum).decrementCPU(cpuNum);
					queue1.get(pNum).count++;
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue1.get(pNum).count == 10 * q1 && queue1.get(pNum).getCPU_burst().get(cpuNum) > 0) {
						queue1.get(pNum).count = 0;
						gantProcesses.add(queue1.get(pNum).getPID());
						timeChartF.add(time);
						queue2.add(queue1.get(pNum));
						queue1.remove(pNum);
						return;
					} else if (queue1.get(pNum).count == 2 * q1 && queue1.get(pNum).getCPU_burst().get(cpuNum) == 0)
						queue1.get(pNum).count = 0;
				}
				if (queue1.get(pNum).getCPU_burst().get(cpuNum) == 0) {
					queue1.get(pNum).setCompletion_time(time);
					comp.add(queue1.get(pNum).getPID());
					gantProcesses.add(queue1.get(pNum).getPID());
					timeChartF.add(time);
					queue1.remove(pNum);
					return;
				}
			} else {
				if (queue1.get(pNum).getCPU_burst().get(cpuNum) > 0) {
					printInfo(queue1.get(pNum).getPID());
					queue1.get(pNum).decrementCPU(cpuNum);
					queue1.get(pNum).count++;
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
				}
				if (queue1.get(pNum).count == 10 * q1 && queue1.get(pNum).getCPU_burst().get(cpuNum) > 0) {
					queue1.get(pNum).count = 0;
					gantProcesses.add(queue1.get(pNum).getPID());
					timeChartF.add(time);
					queue2.add(queue1.get(pNum));
					queue1.remove(pNum);
					return;
				} else if (queue1.get(pNum).count == 2 * q1 && queue1.get(pNum).getCPU_burst().get(cpuNum) == 0)
					queue1.get(pNum).count = 0;
				if (queue1.get(pNum).getCPU_burst().get(cpuNum) == 0) {
					waiting.add(queue1.get(pNum));
					queue1.get(pNum).setArrival_time(time + queue1.get(pNum).getIO_burst().get(cpuNum));
					queue1.get(pNum).getIO_burst().set(cpuNum, 0);
					gantProcesses.add(queue1.get(pNum).getPID());
					timeChartF.add(time);
					queue1.remove(pNum);
					return;
				}
			}
		}
		if (queue1.get(pNum).getCPU_burst().get(cpuNum) > 0) {
			gantProcesses.add(queue1.get(pNum).getPID());
			timeChartF.add(time);
			queue1.add(queue1.get(pNum));
			queue1.remove(pNum);
		}

	}

	public static void queue2Algo() {// algorithm for second  queue Round Robin
		isExistAtTime();
		isExistAtTime1();
		isExistAtTime2();
		isExistAtTime3();
		int i = 0;
		while (queue1.size() == 0 && queue2.size() != 0) {
			int j = 0;
			int flag = 0;
			while (flag == 0) {
				if (queue2.get(i).getCPU_burst().get(j) == 0) {
					j++;
				} else
					flag = 1;
			}
			enterCPU2(i, j);
		}
	}

	public static void enterCPU2(int i, int j) {// for queue2  to check quanta and move process to the queue
		for (int k = 0; k < q2; k++) {
			if (j == (queue2.get(i).getCPU_burst().size() - 1)) {
				if (queue2.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue2.get(i).getPID());
					queue2.get(i).decrementCPU(j);
					queue2.get(i).count++;
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue2.get(i).count == 10 * q2 && queue2.get(i).getCPU_burst().get(j) > 0) {
						queue2.get(i).count = 0;
						queue3.add(queue2.get(i));
						nextBurstEstimated(queue3.size()-1);
						gantProcesses.add(queue2.get(i).getPID());
						timeChartF.add(time);
						queue2.remove(i);
						return;
					} else if (queue2.get(i).count == 2 * q2 && queue2.get(i).getCPU_burst().get(j) == 0)
						queue2.get(i).count = 0;
				}
				if (queue2.get(i).getCPU_burst().get(j) == 0) {
					queue2.get(i).setCompletion_time(time);
					gantProcesses.add(queue2.get(i).getPID());
					timeChartF.add(time);
					comp.add(queue2.get(i).getPID());
					queue2.remove(i);
					return;
				}
				if (queue1.size() != 0) {
					gantProcesses.add(queue2.get(i).getPID());
					timeChartF.add(time);
					return;
				}
			} else {
				if (queue2.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue2.get(i).getPID());
					queue2.get(i).decrementCPU(j);
					queue2.get(i).count++;
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue2.get(i).count == 10 * q2 && queue2.get(i).getCPU_burst().get(j) > 0) {
						queue2.get(i).count = 0;
						queue3.add(queue2.get(i));
						nextBurstEstimated(queue3.size()-1);
						gantProcesses.add(queue2.get(i).getPID());
						timeChartF.add(time);
						queue2.remove(i);
						return;
					} else if (queue2.get(i).count == 2 * q2 && queue2.get(i).getCPU_burst().get(j) == 0)
						queue2.get(i).count = 0;
				}
				if (queue2.get(i).getCPU_burst().get(j) == 0) {
					waiting1.add(queue2.get(i));
					queue2.get(i).setArrival_time(time + queue2.get(i).getIO_burst().get(j));
					queue2.get(i).getIO_burst().set(j, 0);
					gantProcesses.add(queue2.get(i).getPID());
					timeChartF.add(time);
					queue2.remove(i);
					return;
				}
				if (queue1.size() != 0) {
					gantProcesses.add(queue2.get(i).getPID());
					timeChartF.add(time);
					return;
				}
			}
		}
		if (queue2.get(i).getCPU_burst().get(j) > 0) {
			gantProcesses.add(queue2.get(i).getPID());
			timeChartF.add(time);
			queue2.add(queue2.get(i));
			queue2.remove(i);
		}
	}

	public static void queue3Algo() {// algorithm for third queue Shortest-remaining-time first.
		isExistAtTime();
		isExistAtTime1();
		isExistAtTime2();
		isExistAtTime3();
		while (queue1.size() == 0 && queue2.size() == 0 && queue3.size() != 0) {
			int i = getMin();
			int j = 0;
			int flag = 0;
			while (flag == 0) {
				if (queue3.get(i).getCPU_burst().get(j) == 0) {
					j++;
				} else
					flag = 1;
			}
			if (j == (queue3.get(i).getCPU_burst().size() - 1)) {
				int n = 0;
				if (queue3.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue3.get(i).getPID());
					queue3.get(i).decrementCPU(j);
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue1.size() != 0) {
						queue3.get(i).count++;
						timeChartF.add(time);
						gantProcesses.add(queue3.get(i).getPID());
						break;
					}
					if (queue2.size() != 0) {
						queue3.get(i).count++;
						timeChartF.add(time);
						gantProcesses.add(queue3.get(i).getPID());
						break;
					}
					if (queue3.get(i).count == 3 && queue3.get(i).getCPU_burst().get(j) > 0) {
						queue4.add(queue3.get(i));
						queue3.remove(i);
						continue;
					} else if (queue3.get(i).count == 3 && queue3.get(i).getCPU_burst().get(j) == 0)
						queue3.get(i).count = 0;
				}
				if (queue3.get(i).getCPU_burst().get(j) == 0) {
					queue3.get(i).setCompletion_time(time);
					comp.add(queue3.get(i).getPID());
					timeChartF.add(time);
					gantProcesses.add(queue3.get(i).getPID());
					queue3.remove(i);
					continue;
				}
				n = getMin();
				if (n != i) {
					queue3.get(i).count++;
					timeChartF.add(time);
					gantProcesses.add(queue3.get(i).getPID());
					continue;
				}
			} else {
				int n = 0;
				if (queue3.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue3.get(i).getPID());
					queue3.get(i).decrementCPU(j);
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue1.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue3.get(i).getPID());
						queue3.get(i).count++;
						break;
					}
					if (queue2.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue3.get(i).getPID());
						queue3.get(i).count++;
						break;
					}
					if (queue3.get(i).count == 3 && queue3.get(i).getCPU_burst().get(j) > 0) {
						queue4.add(queue3.get(i));
						queue3.remove(i);
						continue;
					} else if (queue3.get(i).count == 3 && queue3.get(i).getCPU_burst().get(j) == 0)
						queue3.get(i).count = 0;
				}
				if (queue3.get(i).getCPU_burst().get(j) == 0) {
					waiting2.add(queue3.get(i));
					queue3.get(i).setArrival_time(time + queue3.get(i).getIO_burst().get(j));
					queue3.get(i).getIO_burst().set(j, 0);
					timeChartF.add(time);
					gantProcesses.add(queue3.get(i).getPID());
					queue3.remove(i);
					continue;
				}
				n = getMin();
				if (n != i) {
					queue3.get(i).count++;
					timeChartF.add(time);
					gantProcesses.add(queue3.get(i).getPID());
					continue;
				}
			}
		}

	}

	public static void nextBurstEstimated(int i) {// this for queue 3 short remining first 
		int j = 0;
		int flag = 0;
		while (flag == 0) {
			if (queue3.get(i).getCPU_burst().get(j) == 0) {
				j++;
			} else
				flag = 1;
		}
		if (i == 0) {
			double value = (alpha * queue3.get(i).getCPU_burst().get(j));
			double value2 = (1 - alpha) * gueusValue;
			queue3.get(i).getCPU_burst().set(j, (int )(value + value2));
			queue3.get(i).PreEstimated = queue3.get(i).getCPU_burst().get(j);
		} else {
			int k = 0;
			int flag1 = 0;
			while (flag1 == 0) {
				if (queue3.get(i).getCPU_burst().get(k) == 0) {
					k++;
				} else
					flag1 = 1;
			}
			double value1 = alpha * queue3.get(i).getCPU_burst().get(k);
			double value2 = (1 - alpha) * queue3.get(i - 1).PreEstimated;
			queue3.get(i).getCPU_burst().set(k, (int) (value1 + value2));
			queue3.get(i).PreEstimated = queue3.get(i).getCPU_burst().get(k);
		}
	}

	public static int getMin() {// get the index of process which have  the smallest cpu burst
		int i = 0;
		int j = 0;
		int flag = 0;
		while (flag == 0) {
			if (queue3.get(i).getCPU_burst().get(j) == 0) {
				j++;
			} else
				flag = 1;
		}
		int min = queue3.get(i).getCPU_burst().get(j);
		int indexMin = 0;
		for (int k = 0; k < queue3.size(); k++) {
			j = 0;
			flag = 0;
			while (flag == 0) {
				if (queue3.get(k).getCPU_burst().get(j) == 0) {
					j++;
				} else
					flag = 1;
			}
			if (queue3.get(k).getCPU_burst().get(j) < min) {
				min = queue3.get(k).getCPU_burst().get(j);
				indexMin = k;
			}
		}
		return indexMin;
	}

	public static void queue4Algo() {//// algorithm to four queue FCFS
		isExistAtTime();
		isExistAtTime1();
		isExistAtTime2();
		isExistAtTime3();
		while (queue1.size() == 0 && queue2.size() == 0 && queue3.size() == 0 && queue4.size() != 0) {
			int i = 0;
			int j = 0;
			int flag = 0;
			while (flag == 0) {
				if (queue4.get(i).getCPU_burst().get(j) == 0) {
					j++;
				} else
					flag = 1;
			}
			if (j == (queue4.get(i).getCPU_burst().size() - 1)) {
				if (queue4.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue4.get(i).getPID());
					queue4.get(i).decrementCPU(j);
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue1.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
					if (queue2.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
					if (queue3.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
				}
				if (queue4.get(i).getCPU_burst().get(j) == 0) {
					queue4.get(i).setCompletion_time(time);
					comp.add(queue4.get(i).getPID());
					timeChartF.add(time);
					gantProcesses.add(queue4.get(i).getPID());
					queue4.remove(i);
					continue;
				}
			} else {
				if (queue4.get(i).getCPU_burst().get(j) > 0) {
					printInfo(queue4.get(i).getPID());
					queue4.get(i).decrementCPU(j);
					time++;
					isExistAtTime();
					isExistAtTime1();
					isExistAtTime2();
					isExistAtTime3();
					if (queue1.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
					if (queue2.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
					if (queue3.size() != 0) {
						timeChartF.add(time);
						gantProcesses.add(queue4.get(i).getPID());
						break;
					}
				}
				if (queue4.get(i).getCPU_burst().get(j) == 0) {
					waiting3.add(queue4.get(i));
					queue4.get(i).setArrival_time(time + queue4.get(i).getIO_burst().get(j));
					queue4.get(i).getIO_burst().set(j, 0);
					timeChartF.add(time);
					gantProcesses.add(queue4.get(i).getPID());
					queue4.remove(i);
					continue;
				}
			}
		}
	}

	public static void choose(Stage stage, Scene scene) {// this method to chosse plot GantChart or show thr procese in time
		DropShadow shadow = new DropShadow();
		Image image7 = new Image(path+"\\finished.png");
		ImageView imageheader = new ImageView(image7);
		imageheader.setFitHeight(100);
		imageheader.setFitWidth(100);
		Label header = new Label("Dear user, the execution has been fininshed",imageheader);
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Image image5 = new Image(path+"\\data.png");
		ImageView imageRun = new ImageView(image5);
		imageRun.setFitHeight(100);
		imageRun.setFitWidth(100);
		Button save = new Button("Show gant chart, Avarage waiting\ntime and CPU utilization", imageRun);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		save.setContentDisplay(ContentDisplay.TOP);
		save.setStyle("-fx-background-color:pink");
		;
		save.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			save.setEffect(shadow);
		});
		save.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			save.setEffect(null);
		});
		Image image6 = new Image(path+"\\read-more.png");
		ImageView imageStart = new ImageView(image6);
		imageStart.setFitHeight(100);
		imageStart.setFitWidth(100);
		Button start = new Button("Show contents of\nqueues at any time", imageStart);
		start.setFont(Font.font(start.getText(), FontWeight.BOLD, 30));
		start.setContentDisplay(ContentDisplay.TOP);
		start.setStyle("-fx-background-color:pink");
		;
		start.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			start.setEffect(shadow);
		});
		start.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			start.setEffect(null);
		});
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, start);
		pane.getChildren().addAll(header, buttons);
		pane.setSpacing(130);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:lightgrey");

		scene.setRoot(pane);

		save.setOnAction(e -> {
			plotGantChart(stage, scene, pane);
		});

		start.setOnAction(b -> {
			showInfoAtTime(stage, scene, pane);
		});

		stage.setScene(scene);
		stage.show();
	}

	public static void plotGantChart(Stage stage, Scene scene, VBox pane2) {//the method to plot GantChart
		Label header = new Label("Final Gant Chart");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 50));
		VBox pane = new VBox();
		ArrayList<TextField> Ls = new ArrayList<>();
		for (int j = 0; j < timeChartF.size() - 1; j++) {
			TextField l2 = new TextField(timeChartF.get(j) + "");
			l2.setStyle("-fx-background-color:pink;");
			l2.setMaxWidth(55);
			l2.setFont(Font.font(l2.getText(), FontWeight.BOLD, 14));
			Ls.add(l2);
		}
		TextField l2 = new TextField(timeChartF.get(timeChartF.size() - 1) + "");
		l2.setMaxWidth(55);
		l2.setFont(Font.font(l2.getText(), FontWeight.BOLD, 14));
		l2.setStyle("-fx-background-color:pink;");
		Ls.add(l2);
		HBox h = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();
		HBox h5 = new HBox();
		for (int n = 0; n < Ls.size(); n++) {
			if (n > 72) {
				Ls.get(n).setText("  " + Ls.get(n).getText());
				h5.getChildren().addAll(Ls.get(n));
			} else if (n > 54) {
				Ls.get(n).setText("  " + Ls.get(n).getText());
				h4.getChildren().addAll(Ls.get(n));
			} else if (n > 36) {
				Ls.get(n).setText("  " + Ls.get(n).getText());
				h3.getChildren().addAll(Ls.get(n));
			} else if (n > 18) {
				Ls.get(n).setText("  " + Ls.get(n).getText());
				h2.getChildren().addAll(Ls.get(n));
			} else
				h.getChildren().addAll(Ls.get(n));
		}
		h.setSpacing(15);
		h2.setSpacing(19);
		h3.setSpacing(20);
		h4.setSpacing(21);
		h5.setSpacing(22);
		ArrayList<TextField> tx = new ArrayList<>();
		for (int g = 0; g < gantProcesses.size(); g++) {
			TextField t = new TextField();
			t.setMaxWidth(55);
			t.setFont(Font.font(t.getText(), FontWeight.BOLD, 14));
			t.setText("   " + gantProcesses.get(g));
			tx.add(t);
		}
		HBox texts = new HBox();
		HBox texts2 = new HBox();
		HBox texts3 = new HBox();
		HBox texts4 = new HBox();
		HBox texts5 = new HBox();
		for (int k = 0; k < tx.size(); k++) {
			if (k >= 72)
				texts5.getChildren().addAll(tx.get(k));
			else if (k >= 54)
				texts4.getChildren().addAll(tx.get(k));
			else if (k >= 36)
				texts3.getChildren().addAll(tx.get(k));
			else if (k >= 18)
				texts2.getChildren().addAll(tx.get(k));
			else
				texts.getChildren().addAll(tx.get(k));
		}
		texts.setSpacing(16);
		texts2.setSpacing(16);
		texts3.setSpacing(16);
		texts4.setSpacing(16);
		texts5.setSpacing(16);
		VBox v = new VBox();
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(h, texts, h2, texts2, h3, texts3, h4, texts4, h5, texts5);
		v.setSpacing(10);
		int waitings = 0;
		for (int y = 0; y < memoryA.size(); y++) {
			waitings += memoryA.get(y).waitingTime();
		}
		double finalAv = ((double) waitings / memoryA.size());
		Label avgW = new Label("Average waiting time\nis " + finalAv + "");
		avgW.setFont(Font.font(avgW.getText(), FontWeight.BOLD, 30));
		Label utilization = new Label("CPU utilization\nis " + CPU_utilization() + "%");
		utilization.setFont(Font.font(utilization.getText(), FontWeight.BOLD, 30));

		Image image4 = new Image(path+"\\back.png");
		ImageView ba = new ImageView(image4);
		ba.setFitHeight(40);
		ba.setFitWidth(40);
		Button back = new Button("Back", ba);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));

		HBox AvUt = new HBox();
		AvUt.setAlignment(Pos.CENTER);
		AvUt.setSpacing(300);
		AvUt.getChildren().addAll(avgW, utilization);
		pane.getChildren().addAll(header, v, AvUt, back);
		pane.setSpacing(35);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-background-color:pink;");
		scene.setRoot(pane);

		back.setOnAction(e -> {
			scene.setRoot(pane2);
		});

		stage.setFullScreen(true);
		stage.setScene(scene);
		stage.show();
	}

	public static void printInfo(String PID) {// to print information of process in  the time 
		String s = "";
		s += "Time = " + time + "\n";
		s += "\n\nCurrently runing process is -> " + PID + "\n";
		if (queue1.size() != 0) {
			s += "\nset of processes in queue 1:\n";
			for (int i = 0; i < queue1.size(); i++) {
				s += queue1.get(i).getPID() + " ";
			}
			s += "\n";
		} else
			s += "queue 1 is empty\n";
		if (queue2.size() != 0) {
			s += "set of processes in queue 2:\n";
			for (int i = 0; i < queue2.size(); i++) {
				s += queue2.get(i).getPID() + " ";
			}
			s += "\n";
		} else
			s += "queue 2 is empty\n";
		if (queue3.size() != 0) {
			s += "set of processes in queue 3:\n";
			for (int i = 0; i < queue3.size(); i++) {
				s += queue3.get(i).getPID() + " ";
			}
			s += "\n";
		} else
			s += "queue 3 is empty\n";
		if (queue4.size() != 0) {
			s += "set of processes in queue 4:\n";
			for (int i = 0; i < queue4.size(); i++) {
				s += queue4.get(i).getPID() + " ";
			}
			s += "\n";
		} else
			s += "queue 4 is empty\n";
		if (waiting.size() == 0 && waiting1.size() == 0 && waiting2.size() == 0 && waiting3.size() == 0)
			s += "waiting queue is empty\n";
		else {
			s += "set of processes in the waiting queue :\n";
			for (int i = 0; i < waiting.size(); i++) {
				s += waiting.get(i).getPID() + " ";
			}
			for (int i = 0; i < waiting1.size(); i++) {
				s += waiting1.get(i).getPID() + " ";
			}
			for (int i = 0; i < waiting2.size(); i++) {
				s += waiting2.get(i).getPID() + " ";
			}
			for (int i = 0; i < waiting3.size(); i++) {
				s += waiting3.get(i).getPID() + " ";
			}
			s += "\n";
		}
		recordings.add(s);
	}

	public static void showInfoAtTime(Stage stage, Scene scene, VBox pane2) {// show the information of procesees at time user enter it 
		Image image5 = new Image(path+"\\hourglass.png");
		ImageView imageL = new ImageView(image5);
		imageL.setFitHeight(80);
		imageL.setFitWidth(80);
		Label L = new Label("Time = ",imageL);
		L.setFont(Font.font(L.getText(), FontWeight.BOLD, 30));
		TextField T = new TextField();
		T.setFont(Font.font(T.getText(), FontWeight.BOLD, 30));
		HBox h = new HBox();
		h.setAlignment(Pos.CENTER);
		h.setSpacing(60);
		h.getChildren().addAll(L, T);
		HBox h2 = new HBox();
		h2.setAlignment(Pos.CENTER);
		h2.setSpacing(100);
		Image image3 = new Image(path+"\\search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(40);
		search.setFitWidth(40);
		Button b = new Button("Show", search);
		b.setFont(Font.font(b.getText(), FontWeight.BOLD, 30));
		Image image4 = new Image(path+"\\back.png");
		ImageView ba = new ImageView(image4);
		ba.setFitHeight(40);
		ba.setFitWidth(40);
		Button back = new Button("Back", ba);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		h2.getChildren().addAll(b, back);
		VBox v = new VBox();
		v.setAlignment(Pos.CENTER);
		v.setSpacing(120);
		v.getChildren().addAll(h, h2);
		
		scene.setRoot(v);
		b.setOnAction(e -> {
			if (T.getText().isEmpty())
				error("Fill the text first");
			else if (Integer.parseInt(T.getText()) < recordings.size() && Integer.parseInt(T.getText()) >= 0) {
				Stage st = new Stage();
				VBox v2 = new VBox();
				v2.setAlignment(Pos.CENTER);
				Label l = new Label(recordings.get(Integer.parseInt(T.getText())));
				l.setFont(Font.font(l.getText(), FontWeight.BOLD, 30));
				v2.getChildren().add(l);

				Scene s2 = new Scene(v2, 800, 550);
				st.setScene(s2);
				st.show();
			} else
				error("Out of rang, choose between 0 and " + (recordings.size() - 1));
		});

		back.setOnAction(e -> {
			scene.setRoot(pane2);
		});

		stage.setScene(scene);
		stage.show();
	}

	public static double CPU_utilization() {// this methood to find cpu utilization
		int sum = 0;
		for (int i = 0; i < timeChartF.size() - 1; i++) {
			if (!gantProcesses.get(i).equals("IDL")) {
				sum += (timeChartF.get(i + 1) - timeChartF.get(i));
			}
		}
		return (((double) sum) / (time)) * 100;
	}

}
