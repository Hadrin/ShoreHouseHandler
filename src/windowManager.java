import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
public class windowManager implements ActionListener {
	private int m = 0;
	private int year = 0;
	
	public windowManager(String type){
		try {
			apiHandler api = new apiHandler();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		String input = type.toLowerCase();
		switch(input) {
		case "menu":
			createMenu();
			break;
		case "calendar":
			createCalendar(15);
			break;
		case "requests":
			//TODO
			break;
		default:
			System.out.println("Malformed call to windowManager: " + input);
		}
	}
	
	private void createMenu() {
		JFrame menu = new JFrame();
		FlowLayout layout = new FlowLayout();
		menu.setLayout(layout);
		JLabel menuTextA = new JLabel("Shore House");
		JLabel menuTextB = new JLabel("Scheduling Manager");
		JLabel spacerA = new JLabel("                          ");
		JLabel spacerB = new JLabel("                          ");
		JButton bCalendar = new JButton("View Calendar");
		bCalendar.setBounds(50, 100, 150, 75);
		bCalendar.addActionListener(this);
		bCalendar.setActionCommand("calendar");
		JButton bRequests = new JButton("View Requests");
		bRequests.setBounds(50, 200, 150, 75);
		bRequests.addActionListener(this);
		bRequests.setActionCommand("requests");
		menu.setBounds(100, 100, 200, 300);
		menu.add(menuTextA);
		menu.add(menuTextB);
		menu.add(new JSeparator(SwingConstants.HORIZONTAL));
		menu.add(spacerA);
		menu.add(bCalendar);
		menu.add(spacerB);
		menu.add(bRequests);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menu.setVisible(true);
	}
	
	private void createCalendar(int monthArg) {
		JFrame calendarWindow = new JFrame();
		JPanel calendarPanel = new JPanel(); 
		JPanel controlPanel = new JPanel();
		
		//Layout must be null so the calendar buttons properly align
		calendarPanel.setLayout(null);
		
		//Creates buttons for calendar view and aligns them
		JButton[] calendarDay = new JButton[35];
		int x, y = 0;
		for(int i = 0; i < calendarDay.length; i++) {
			x = 25 + (50 * (i % 7));
			y = 25 + (50 * (i / 7));
			calendarDay[i] = new JButton();
			calendarDay[i].setBounds(new Rectangle(x, y, 50, 50));
			calendarPanel.add(calendarDay[i]);
		}
		
		//Begin assembling window
		calendarWindow.add(calendarPanel);
		calendarWindow.setBounds(100, 100, 500, 500);
		calendarWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//Creates calendar view for desired month and creates calendar object for datekeeping purposes
		Calendar cal = Calendar.getInstance();
		Date currentDate = cal.getTime();
		if(monthArg == 15) {
			m = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		} else {
			m = monthArg;
			cal.set(Calendar.MONTH, m);
			cal.set(Calendar.YEAR, year);
		}
		
		//Array to keep track of how many days are in each month. Does not account for leap years but since each month is rooted
		//to its own beginning and no-one wants to rent in February it should be OK
		int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String[] nameOfMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int startPoint = cal.get(Calendar.DAY_OF_WEEK);
		startPoint--;
		int pointer = 0;
		for(int i = 0; i < 35; i++) {
			if(i < startPoint) {
				calendarDay[i].setEnabled(false);
			} else if(pointer >= daysInMonth[m]) {
				calendarDay[i].setEnabled(false);
			} else {
				pointer++;
				calendarDay[i].setText(Integer.toString(pointer));
			}
		}
		
		JButton monthBack = new JButton("<");
		monthBack.addActionListener(e -> {
			m--;
			if(m == -1) {
				m = 11;
				year--;
			}
			createCalendar(m);
			calendarWindow.dispose();
		});
		monthBack.setBounds(new Rectangle(0, 400, 250, 50));
		calendarPanel.add(monthBack);
		JButton monthForward = new JButton(">");
		monthForward.addActionListener(e -> {
			m++;
			if(m == 12) {
				m = 0;
				year++;
			}
			createCalendar(m);
			calendarWindow.dispose();
		});
		monthForward.setBounds(new Rectangle(250, 400, 250, 50));
		calendarPanel.add(monthForward);
		
		JLabel currentMonthText = new JLabel(nameOfMonth[m] + " " + Integer.toString(year));
		currentMonthText.setBounds(new Rectangle(200, 350, 100, 50));
		calendarPanel.add(currentMonthText);
		
		calendarWindow.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input;
		input = e.getActionCommand();
		switch(input) {
		case "calendar":
			new windowManager("calendar");
			break;
		case "requests":
			new windowManager("requests");
			break;
		}
	}

}
