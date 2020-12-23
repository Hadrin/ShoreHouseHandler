import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

public class apiHandler {
	private static final JsonFactory jsf = new JacksonFactory().getDefaultInstance();
	private static final String targetedCalendar = "f8jv1pqoabmh4td3hgasu4q9ko@group.calendar.google.com";
	private static Calendar calendar;
	
	public apiHandler() throws IOException{
		//Authentification
		//Creates Http Transport object for upcoming authentification
		HttpTransport http = null;
		try {
			http = GoogleNetHttpTransport.newTrustedTransport();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Retrieves credentials from file and requests read/write/edit scope from calendar API
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("credentials.json"));
		if(credentials.createScopedRequired()) {
			credentials = credentials.createScoped("https://www.googleapis.com/auth/calendar");
		}
		credentials.refreshIfExpired();
		AccessToken token = credentials.getAccessToken();
		//For testing purposes
		//System.out.println(token);
		
		//Takes scoped credentials and prepares to authenticate to google API
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
		//Authenticates and creates calendar object to interact with google API
		calendar = new Calendar.Builder(http, jsf, requestInitializer).build();
	}
	
	/**
	 * Creates an event in the calendar specified by define targetedCalendar
	 * @param calendar Calendar object used to interact with API
	 * @param startDate date of event start in format YYYY MM DD
	 * @param endDate date of event end in format YYYY MM DD
	 * @param title Name of event to be created
	 * @return boolean - returns whether the event was created successfully or not
	 */
	public boolean createEvent(String startDate, String endDate, String title) {
		try {
			Event event = new Event();
			long startDateEpoch, endDateEpoch;
			startDateEpoch = convertBeginTime(startDate);
			endDateEpoch = convertEndTime(endDate);
			event.setStart(new EventDateTime().setDateTime(new DateTime(new Date(startDateEpoch))));
			event.setEnd(new EventDateTime().setDateTime(new DateTime(new Date(endDateEpoch))));
			event.setSummary(title);
			event.setVisibility("public");
			calendar.events().insert(targetedCalendar, event).execute();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	/**
	 * Creates an event in the calendar specified by define targetedCalendar
	 * @param calendar Calendar object used to interact with API
	 * @param startDate date of event start in format YYYY MM DD
	 * @param endDate date of event end in format YYYY MM DD
	 * @param title Name of event to be created
	 * @param description description of event
	 * @return boolean - returns whether the event was created successfully or not
	 */
	public boolean createEvent(String startDate, String endDate, String title, String description) {
		try {
			Event event = new Event();
			long startDateEpoch, endDateEpoch;
			startDateEpoch = convertBeginTime(startDate);
			endDateEpoch = convertEndTime(endDate);
			event.setStart(new EventDateTime().setDateTime(new DateTime(new Date(startDateEpoch))));
			event.setEnd(new EventDateTime().setDateTime(new DateTime(new Date(endDateEpoch))));
			event.setSummary(title);
			event.setDescription(description);
			event.setVisibility("public");
			calendar.events().insert(targetedCalendar, event).execute();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Gets a list of all events on the selected calendar regardless of date. 
	 * @return a list of events from given calendar
	 */
	public List<Event> getEvents() {
		try {
			Events events = calendar.events().list(targetedCalendar).execute();
			List<Event> eventList = events.getItems();
			return eventList;
		}catch(Exception e) {
			System.out.println("Failed to get events from calendar");
			return null;
		}
	}
	
	/**
	 * Gets a list of all events on the selected calendar that interact with the given month
	 * @param month month to be checked
	 * @param year year to be checked
	 * @return a list of events from given calendar
	 */
	public List<Event> getEvents(int month, int year) {
		try {
			Date date = new Date();
			Events events = calendar.events().list(targetedCalendar).execute();
			List<Event> eventList = events.getItems();
			Iterator<Event> li = eventList.iterator();
			while(li.hasNext()) {
				Event event = li.next();
				int eventStartMonth, eventStartYear, eventEndMonth, eventEndYear = 0; 
				date.setTime(event.getStart().getDate().getValue());
				eventStartMonth = date.getMonth();
				eventStartYear = date.getYear();
				date.setTime(event.getEnd().getDate().getValue());
				eventEndMonth = date.getMonth();
				eventEndYear = date.getYear();
				if(eventEndYear < year) {
					li.remove();
				} else if(eventStartYear > year){
					li.remove();
				} else if(!((eventStartMonth == month) || (month - eventStartMonth == 1))) {
					li.remove();
				}
			}
			System.out.println(eventList);
			return eventList;
		}catch(Exception e) {
			System.out.println("Failed to get events from calendar");
			return null;
		}
	}
	
	/**
	 * Converts a string in the format of YYYY MM DD to its value in epoch time at 2PM
	 * @param startDate date to be converted
	 * @return long value of date in epoch time at PM
	 * @throws ParseException
	 */
	private static long convertBeginTime(String startDate) throws ParseException {
		long epoch;
		String startDateVal = startDate.concat(" 14:00:00");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy LL dd HH:mm:ss");
		Date date = dateFormat.parse(startDateVal);
		epoch = date.getTime();
		return epoch;
	}
	
	/**
	 * Converts a string in the format of YYYY MM DD to its value in epoch time at 8AM
	 * @param endDate date to be converted
	 * @return long value of date in epoch time at 8AM
	 * @throws ParseException
	 */
	private static long convertEndTime(String endDate) throws ParseException {
		long epoch;
		String endDateVal = endDate.concat(" 08:00:00");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy LL dd HH:mm:ss");
		Date date = dateFormat.parse(endDateVal);
		epoch = date.getTime();
		return epoch;
	}
	
	public long convertIntoEpochTime(String date) throws ParseException{
		long epoch;
		String dateVal = date.concat(" 12:00:00");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy LL dd HH:mm:ss");
		Date newDate = dateFormat.parse(dateVal);
		epoch = newDate.getTime();
		return epoch;
	}
	
	public int convertMonthFromEpoch(long epoch){
		Date date = new Date(epoch);
		return date.getMonth();
	}
	
	public int convertYearFromEpoch(long epoch){
		Date date = new Date(epoch);
		return date.getYear();
	}
	
	public int convertDayFromEpoch(long epoch){
		Date date = new Date(epoch);
		return date.getDate();
	}
}
