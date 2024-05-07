package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BusinessLayer.Booking;
import BusinessLayer.Registration;
import BusinessLayer.admin;
import DatabaseHandler.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class FrontController implements Initializable{
    
	//////////////////////////////////   MAIN MENU ???????????????? ///////////////////////////////////////
	@FXML
	private Button AdminLoginButton;

	@FXML
	private Button CustomerReg;
    
    @FXML
    void LogtheAdmin(ActionEvent event) throws IOException {
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         stage.show();
    }
   
    @FXML
    void GoToMainPage(ActionEvent event) throws IOException {
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         stage.show();
    }
    @FXML
    void GoToCustomerMenu(ActionEvent event) throws IOException {
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         stage.show();
    }
    @FXML
    void GoToAdminMenu(ActionEvent event) throws IOException {
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         stage.show();
    }
    
    @FXML
    void RegisterCustomer(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void RegisterCustomer1(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Complaints(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("HandleComplaints.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void Success(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SuccessfulPrompt.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void ChkFare(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CheckFare.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
public Boolean ValidateAdmin(String username, String passcode) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "PASSWORD-123");
    	
    	PreparedStatement ps = con.prepareStatement("select * from admin");
    	ResultSet rs = ps.executeQuery();
    	
    	while(rs.next())
    	{
    		String dbusername = rs.getString("username");
    		String dbpass = rs.getString("password");
    		
    		if(username.contentEquals(dbusername) && passcode.contentEquals(dbpass))
    		{
    			return true;
    		}
    	}
    	
    	String Errors = "Wrong username or password";
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Login Unsuccessful");
		errorAlert.setContentText(Errors);
		errorAlert.show(); 
		
		admin ad = new admin(username,passcode);
    	return false;
	}
    ////////////////////////////////////  ADMIN LOGIN PAGE ???????? /////////////////////////////////////
    
    @FXML
    private Button butLog1;

    @FXML
    private TextField NameOfUser;

    @FXML
    private TextField passcode;

    String adminUsername = null;
    String adminPassword = null;
    
    
    
    @FXML
    void Admin_Button_Submit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    	
    	adminUsername = NameOfUser.getText();
    	adminPassword = passcode.getText();
    	
//    	DBHandler dbHandler = DBHandler.getInstance();
    	boolean validateAdmin = ValidateAdmin(adminUsername, adminPassword);
    	
    	System.out.println(validateAdmin);
    	
    	if(validateAdmin)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}
    }
//////////////////////////Customer Login Page///////////////////////////////////////////////
  

    @FXML
    private Button loginButton;

   
    @FXML
    public void validateCnic() {
        String cnicText = cnic.getText().trim();

        // Enable the login button only if the CNIC is exactly 13 digits
        loginButton.setDisable(cnicText.length() != 13 || !cnicText.matches("\\d+"));
        }
public Boolean ValidateCustomer(String username, String passcode) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "PASSWORD-123");
    	
    	PreparedStatement ps = con.prepareStatement("select * from customer");
    	ResultSet rs = ps.executeQuery();
    	
    	while(rs.next())
    	{
    		String dbusername = rs.getString("username");
    		String dbpass = rs.getString("password");
    		
    		if(username.contentEquals(dbusername) && passcode.contentEquals(dbpass))
    		{
    			return true;
    		}
    	}
    	
    	String Errors = "Wrong username or password";
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Login Unsuccessful");
		errorAlert.setContentText(Errors);
		errorAlert.show(); 
		
		admin ad = new admin(username,passcode);
    	return false;
	}
@FXML
private Button cusLog1;

@FXML
private TextField NameOfCustomer;

@FXML
private TextField passwcode;

String customerUsername = null;
String customerPassword = null;


@FXML
void Customer_Button_Submit(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
	
	customerUsername = NameOfCustomer.getText();
	customerPassword = passwcode.getText();
	
//	DBHandler dbHandler = DBHandler.getInstance();
	boolean validateCustomer = ValidateCustomer(customerUsername, customerPassword);
	
	System.out.println(validateCustomer);
	
	if(validateCustomer)
	{
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
    
    
    /////////////////////////////////  CUSTOMER REGISTRATION PAGE ???????? ///////////////////////////////
    public Boolean ValidateCustomer(String CustomerName, String CustomerCnic, String address, String phoneNo) throws SQLException, ClassNotFoundException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railwayschema", "root", "PASSWORD-123");

	    // Validate CNIC (should be 13 digits)
	    if (CustomerCnic.length() != 13 || !CustomerCnic.matches("\\d+")) {
	        String Errors = "Invalid CNIC format.Enter 13-digit CNIC.";
	        showAlert("Registration Unsuccessful", Errors, AlertType.ERROR);
	        return false;
	    }

	    // Validate phone number (should be 10 digits)
	    if (phoneNo.length() != 10 || !phoneNo.matches("\\d+")) {
	        String Errors = "Invalid format.Enter 10-digit phone number.";
	        showAlert("Registration Unsuccessful", Errors, AlertType.ERROR);
	        return false;
	    }

	    PreparedStatement ps = con.prepareStatement("select * from customer where customer.cnic='" + CustomerCnic + "';");
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        String cnicOfCustomer = rs.getString("cnic");

	        if (CustomerCnic.contentEquals(cnicOfCustomer)) {
	            String Errors = "Cannot Register due to invalid or registered CNIC.";
	            showAlert("Registration Unsuccessful", Errors, AlertType.ERROR);
	            return false;
	        }
	    }

	    PreparedStatement pstmt = con.prepareStatement("INSERT INTO customer(customerName, cnic, customerAddress, phoneNum) VALUES (?,?,?,?);");
	    pstmt.setString(1, CustomerName);
	    pstmt.setString(2, CustomerCnic);
	    pstmt.setString(3, address);
	    pstmt.setString(4, phoneNo);
	    pstmt.executeUpdate();

	    Registration reg = new Registration(CustomerName, CustomerCnic, address, phoneNo);
	    return true;
	}

	// Helper method to show an alert
	private void showAlert(String title, String content, AlertType alertType) {
	    Alert errorAlert = new Alert(alertType);
	    errorAlert.setTitle(title);
	    errorAlert.setContentText(content);
	    errorAlert.show();
	}
    
    
    
    
    
    
    
    @FXML
    private TextField address;

    @FXML
    private TextField cnic;

    @FXML
    private TextField fullName;

    @FXML
    private TextField phoneNum;

    @FXML
    private Button registerButton;
    
    String custName = "";
    String custCnic = "";
    String custAddr = "";
    String custNumb = "";
    		

    @FXML
    void CustomerRegistered(ActionEvent event)  throws IOException, ClassNotFoundException, SQLException{
    	
    	custName = fullName.getText();
    	custCnic = cnic.getText();
    	custAddr = address.getText();
    	custNumb = phoneNum.getText();
    	
    	// DBHandler dbHandler = DBHandler.getInstance();
    	boolean validateCustomer = ValidateCustomer(custName, custCnic, custAddr, custNumb);
    	z
    	System.out.println(validateCustomer);
    	
    	if(validateCustomer)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
    	}
    
    }
    
    
    
    //////////////////////////////////////  CUSTOMER MENU PAGE ???????????????????? ////////////////////////////
    
    @FXML
    private Button bookTicketButton;

    @FXML
    private Button cancelTicketButton;

    @FXML
    private Button seatChangeButton;

    @FXML
    void ProceedToSeatChange(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("RequestSeatChange.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProceedToTicketBooking(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("TicketBooking.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProceedToTicketCancellation(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("TicketCancellation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    //////////////////////// RESERVE TICKET ????????????? //////////////////////
     
    @FXML
    private TextField ArrivalText;

    @FXML
    private TextField DestinationText;

    @FXML
    private TextField NumberOfSeatsText;

    @FXML
    private Button ReserveBookingButton;

    @FXML
    private TextField TrainNameText;

    @FXML
    private TextField cnicText;
    
    
    String booking_Cnic="";
    String booking_train="";
    String booking_arrival="";
    String booking_dest="";
    String booking_NoOfSeats="";

    
    
    @FXML
    
    void BookingReserved(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	
    	booking_Cnic = cnicText.getText();
    	booking_train = TrainNameText.getText();
    	booking_arrival = ArrivalText.getText();
    	booking_dest = DestinationText.getText();
    	booking_NoOfSeats = NumberOfSeatsText.getText();
    	
    	int seats = Integer.parseInt(booking_NoOfSeats);
    	
    	Booking booking = new Booking(booking_Cnic, booking_train, booking_arrival, booking_dest, seats);
    	
    	boolean reserveTicket = booking.saveToDB();
    	System.out.println(reserveTicket);
    	
    	if(reserveTicket)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Payment.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}
    }
    
       
    ////////////////////////  SUCCESSFULL PROMPT ????????????? .///////////////////////////////
    
    @FXML
    private Button homeButton;

    @FXML
    private Button previousTabButton;

    @FXML
    void ProceedToHome(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProceedToPreviousTab(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    /////////////////////////////  CANCEL BOOKING CUSTOMER ??????????????///////////////////////
    
    @FXML
    private TextField CNICTextInCancelTicket;

    @FXML
    private Button cancelButtonforTicket;
    
    String cnic_CancelBooking="";
    
    
    @FXML
    void CancelledTicketPrompt(ActionEvent event)  throws IOException, ClassNotFoundException, SQLException{
    	
    	cnic_CancelBooking = CNICTextInCancelTicket.getText();
    	
    	DBHandler dbHandler = DBHandler.getInstance();
    	boolean cancelBooking = dbHandler.ValidateCustomerForCancelBooking(cnic_CancelBooking);
    	
    	System.out.println(cancelBooking);
    	
    	if(cancelBooking)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SuccessfulPrompt.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}
    }
    
    
    
    /////////////////////////////  REQUEST SEAT CHANGE TICKET PROMPT PAGE ??????????????///////////////////////
    @FXML
    private Button FinalSeatButton;

    @FXML
    private TextField NewSeatNumber;

    @FXML
    private TextField TicketNumSeat;

    @FXML
    private TextField seatNumOriginal;

    @FXML
    void SeatChangedPrompt(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SuccessfulPrompt.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    
    
    /////////////////////////   ADMIN MENU PAGE ??????????????????/////////////////////////////
    @FXML
    private Button buttonBooking;

    @FXML
    private Button buttonLuggage;

    @FXML
    private Button buttonStation;

    @FXML
    private Button buttonTrains;

    @FXML
    void BookingManager(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ManageBooking.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void LuggageManager(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ManageLuggage.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void StationManager(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ManageStation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void TrainManager(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ManageTrains.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    
    ////////////////////////// MANAGE BOOKING ADMIN PAGE ????????????????///////////////////////////////////
    @FXML
    private Button AddBookingAdminButton;

    @FXML
    private Button DeleteBookingAdminButton;

    @FXML
    private Button UpdateBookingAdminButton;
    

    @FXML
    void ProceedtoAddBooking(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AddBooking.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedtoDeleteBooking(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("deleteBooking.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    @FXML
    void ProceedtoUpdateBooking(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("updateBooking.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    /////////////////////////  ADD BOOKING ADMIN PAGE ?????????/////////////////////////
    @FXML
    private TextField ArrivalAdminText;

    @FXML
    private TextField DestinationAdminText;

    @FXML
    private TextField NumberOfSeatsAdminText;

    @FXML
    private TextField TrainNameAdminText;

    @FXML
    private Button addBookingAdmin;

    @FXML
    private TextField cnicAdminText;

    String Adminbooking_Cnic="";
    String Adminbooking_train="";
    String Adminbooking_arrival="";
    String Adminbooking_dest="";
    String Adminbooking_NoOfSeats="";
    
    //Admin Booking
    public Boolean AdminBookingConfirmed(String cnic, String nameofTrain, String arrival, String destination, int no_of_Seats) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railwayschema", "root", "PASSWORD-123");
		
		PreparedStatement ps = con.prepareStatement("select * from booking where booking.CnicOfCustomer ='"+cnic+"';");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			String cnicOfCustomer = rs.getString("CnicOfCustomer");
			
			if(!cnic.contentEquals(cnicOfCustomer))
			{
				String Errors = "Cannot book the ticket due to invalid or registered cnic";
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Booking Unsuccessful");
				errorAlert.setContentText(Errors);
				errorAlert.show();
				return false;
			}
		}
		
		PreparedStatement ps1 = con.prepareStatement("select * from train where train.NameOfTr='"+nameofTrain+"'AND train.trainfrom='"+arrival+"' AND train.trainto='"
		+destination+"';");
		
		ResultSet rs1 = ps1.executeQuery();
//		
//		while(rs1.next())
//		{
//			String train_Name = rs1.getString("NameOfTr");
//			String train_arrival = rs1.getString("trainfrom");
//			String train_destination = rs1.getNString("trainto");
//			
//			if(!(train_Name.contentEquals(nameofTrain)) && (train_arrival.contentEquals(arrival)) && (train_destination.contentEquals(destination))) 
//			{
//				String Errors = "Train does not exist! Please enter correct Train Name";
//				Alert errorAlert = new Alert(AlertType.ERROR);
//				errorAlert.setTitle("Booking Unsuccessful");
//				errorAlert.setContentText(Errors);
//				errorAlert.show();
//				return false;
//			}
			
//			if(!train_arrival.contentEquals(arrival))
//			{
//				String Errors = "This specific train does not go from the entered Place!";
//				Alert errorAlert = new Alert(AlertType.ERROR);
//				errorAlert.setTitle("Booking Unsuccessful");
//				errorAlert.setContentText(Errors);
//				errorAlert.show();
//				return false;
//			}
//				
//			if(!train_destination.contentEquals(destination))
//			{
//				String Errors = "This specific train does not go to the entered Place!";
//				Alert errorAlert = new Alert(AlertType.ERROR);
//				errorAlert.setTitle("Booking Unsuccessful");
//				errorAlert.setContentText(Errors);
//				errorAlert.show();
//				return false;
//			}
//		}
		
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO booking VALUES (?,?,?,?,?);");
		pstmt.setString(1, cnic);
		pstmt.setString(2, nameofTrain);
		pstmt.setString(3, arrival);
		pstmt.setString(4, destination);
		pstmt.setInt(5, no_of_Seats);
		pstmt.executeUpdate();
		
		int fare = no_of_Seats*1000;
		
		String fare_amount = "Calculated fare is "+ fare;
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fare Amount");
        alert.setContentText(fare_amount);
        alert.show();
        
		Booking bookTicket = new Booking(cnic,nameofTrain,arrival,destination,no_of_Seats);	
		
		return true;
	}
    @FXML
    
    void AddedBooking(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
    	
    	Adminbooking_Cnic = cnicAdminText.getText();
    	Adminbooking_train = TrainNameAdminText.getText();
    	Adminbooking_arrival = ArrivalAdminText.getText();
    	Adminbooking_dest = DestinationAdminText.getText();
    	Adminbooking_NoOfSeats = NumberOfSeatsAdminText.getText();
    	
    	int adminSeats = Integer.parseInt(Adminbooking_NoOfSeats);
    	
    	DBHandler dbHandler = DBHandler.getInstance();
    	boolean AdminValidateBooking = AdminBookingConfirmed(Adminbooking_Cnic, Adminbooking_train, Adminbooking_arrival, Adminbooking_dest, adminSeats);
    	
    	System.out.println(AdminValidateBooking);
    	
    	if(AdminValidateBooking)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}

    }
    
    ///////////////////////////// 	DELETE BOOKING ADMIN PAGE ????????????/////////////////////////////////
    @FXML
    private TextField CNICTextInAdminPanel;

    @FXML
    private Button cancelButtonforAdmin;

    String cnicAdminuse ="";
    
    @FXML
    void adminCancelledBooking(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
    	
    	cnicAdminuse = CNICTextInAdminPanel.getText();
    	
    	DBHandler dbHandler = DBHandler.getInstance();
    	boolean adminvalidatebookingcancellation = dbHandler.ValidateCustomerForCancelBooking(cnicAdminuse);
    	
    	System.out.println(adminvalidatebookingcancellation);
    	
    	if(adminvalidatebookingcancellation)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}

    }
    
    ///////////////////////////////// UPDATE BOOKING ADMIN PAGE ??????????????????/////////////////////////
    String updateBooking_Cnic="";
    String updateBooking_TrainName="";
    String updateBooking_destination="";
    String updateBooking_arrival="";
    int updateBooking_noOfSeats;
    
    
    @FXML
    private TextField ArrivalupdateText;

    @FXML
    private TextField DestinationupdateText;

    @FXML
    private TextField NumberOfSeatsupdateText;

    @FXML
    private TextField TrainNameUpdateText;

    @FXML
    private TextField cnicUpdateText;

    @FXML
    private Button updateBookingAdmin;

    @FXML
    private Button findOtherInfoButton;
    
    @FXML
    void foundDetails(ActionEvent event) throws ClassNotFoundException, SQLException {
    	updateBooking_Cnic=cnicUpdateText.getText();
    	DBHandler db=DBHandler.getInstance();
    	ResultSet rs=db.BookingCnicExists(updateBooking_Cnic);
    	
    	if(!rs.isBeforeFirst()) {

			String Errors = "Cannot add the train due to invalid or already used train name";
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("add Train Unsuccessful");
			errorAlert.setContentText(Errors);
			errorAlert.show();
    	}
    	while(rs.next()) {
    	TrainNameUpdateText.setText(rs.getString("trainName"));
    	ArrivalupdateText.setText(rs.getString("arrival"));
    	DestinationupdateText.setText(rs.getString("dest"));
    	NumberOfSeatsupdateText.setText(rs.getString("numbOfSeats"));
    	}
    }
    
    @FXML
    void updatedBooking(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	
         updateBooking_TrainName=TrainNameUpdateText.getText();
         updateBooking_destination=DestinationupdateText.getText();
         updateBooking_arrival=ArrivalupdateText.getText();
         updateBooking_noOfSeats=Integer.parseInt(NumberOfSeatsupdateText.getText());
         
         DBHandler db=DBHandler.getInstance();
         db.updateBooking(updateBooking_Cnic,updateBooking_TrainName,updateBooking_arrival,updateBooking_destination,updateBooking_noOfSeats);
    	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    //////////////////////////     MANAGE TRAINS ADMIN PAGE ????????????????///////////////////////////////////
    @FXML
    private Button addTrainAdminButton;
    
    @FXML
    private Button deleteTrainAdminButton;

    @FXML
    private Button updateTrainButton;

    @FXML
    void ProceedToAddTrain(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("addTrain.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToDeleteTrain(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("deleteTrain.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToUpdateTrain(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("updateTrain.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
    
    ////////////////////////////////////// ADD TRAIN ADMIN PAGE ?????????????????////////////////////////////
    
    @FXML
    private TextField addArrivalText;

    @FXML
    private TextField addDestinationText;

    @FXML
    private TextField addTrainNameText;

    @FXML
    private Button addTrainbyAdmin;

    
    String nameofTrainAdmin ="";
    String arrivalAdmin=""; 
    String destinationAdmin="";
    @FXML
    void AddedTrainbyAdmin(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
    	
    	nameofTrainAdmin = addTrainNameText.getText();
    	arrivalAdmin = addArrivalText.getText();
    	destinationAdmin = addDestinationText.getText();
    	
    	DBHandler dbHandler = DBHandler.getInstance();
    	boolean validateAddTrain = dbHandler.addTrainConfirmed(nameofTrainAdmin, arrivalAdmin, destinationAdmin);
    	
    	System.out.println(validateAddTrain);
    	
    	if(validateAddTrain)
    	{
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    	}

    } 

    /////////////////////////////	 DELETE TRAIN ADMIN PAGE?????????????????///////////////////////////////
    
    @FXML
    private TextField deleteArrivalText;

    @FXML
    private TextField deleteDestinationText;

    @FXML
    private Button deleteTrainButton;

    @FXML
    private TextField deleteTrainText;

    String deleteTrain_Name ="";
    String deleteTrain_Arrival ="";
    String deleteTrain_Dest ="";
    @FXML
    
    void deletedTrain(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        	
    	deleteTrain_Name = deleteTrainText.getText();
    	deleteTrain_Arrival = deleteArrivalText.getText();
    	deleteTrain_Dest = deleteDestinationText.getText();
        	
        DBHandler dbHandler = DBHandler.getInstance();
        boolean del_Train = dbHandler.validateTrainDeletion(deleteTrain_Name);
        	
        System.out.println(del_Train);
        	
        if(del_Train)
        {
        	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    
    ///////////////////////////////// UPDATE TRAIN ADMIN PAGE ??????????????????/////////////////////////
    
    
    @FXML
    private TextField updateArrivalText;

    @FXML
    private TextField updateDestinationText;

    @FXML
    private Button button_updateTrain;
    
    @FXML
    private TextField updateTrainText;
    
    String updateTrain_TrainName="";
    String updateTrain_dest="";
    String updateTrain_arrival="";
    
    @FXML
    void updatedTrain(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
    	
    	updateTrain_TrainName = updateTrainText.getText();
    	updateTrain_arrival = updateArrivalText.getText();
    	updateTrain_dest = updateDestinationText.getText();
    	
    	DBHandler db = DBHandler.getInstance();
    	db.train_UpdateValidate(updateTrain_TrainName, updateTrain_arrival, updateTrain_dest);
    	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    	
    
    
    
    //////////////////////////     MANAGE STATION ADMIN PAGE ????????????????///////////////////////////////////
    String stationName="";
    @FXML
    private TextField StationNameText;

    @FXML
    private TextField StationNumText;

    @FXML
    private Button addStationButton;

    @FXML
    private Button deleteStationButton;

    @FXML
    private Button updateStationButton;

    @FXML
    void ProceedToDeleteStation(ActionEvent event) throws IOException{
    	
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToUpdateStation(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToaddStation(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
    	
    	stationName=StationNameText.getText();
    	
    	DBHandler dbb = DBHandler.getInstance();
    	dbb.addTrainStation(stationName);
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
    
    //////////////////////////     MANAGE LUGGAGE ADMIN PAGE ????????????????///////////////////////////////////
    @FXML
    private TextField ArrivalForLuggage;

    @FXML
    private TextField DestForLuggage;

    @FXML
    private TextField LuggageNum;

    @FXML
    private TextField LuggageWeight;

    @FXML
    private TextField TicketNumForLuggage;

    @FXML
    private TextField TrainNumForLuggage;

    @FXML
    private Button addLuggage;

    @FXML
    private Button deleteLuggage;

    @FXML
    private Button updateLuggage;

    @FXML
    void ProceedToAddLuggage(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToDeleteLuggage(ActionEvent event)throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ProceedToUpdateLuggage(ActionEvent event)throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminPromptSuccessful.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    
    ////////////////////////////  ADMIN SUCCESSFUL PROMPT PAGE ??????????????////////////////
    @FXML
    private Button AdminHomeButton;

    @FXML
    private Button BackToAdminMenuButton;

    @FXML
    void FromAdminControlToHome(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ProceedToAdminMenu(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Reporter(ActionEvent event) throws IOException{
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("report.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TableView<TrainInfo> tableView;
    
    @FXML
    private TableColumn<TrainInfo, String> trainNameColumn;

    @FXML
    private TableColumn<TrainInfo, String> arrivalColumn;

    @FXML
    private TableColumn<TrainInfo, String> destinationColumn;

    @FXML
    private TableColumn<TrainInfo, String> fareColumn;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		   TrainInfo sampleTrain = new TrainInfo("ABC", "ISB", "RWL", "600");

	        // Create an ObservableList to hold the data
	        ObservableList<TrainInfo> trainList = FXCollections.observableArrayList(sampleTrain);

	        // Set the data to the TableView
	        if(tableView!=null) {
	        	System.out.println("Hi");
	        	tableView.setItems(trainList);
	        	
	        	// Set cell value factories for each column
	        	trainNameColumn.setCellValueFactory(cellData -> cellData.getValue().trainNameProperty());
	        	arrivalColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalProperty());
	        	destinationColumn.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
	        	fareColumn.setCellValueFactory(cellData -> cellData.getValue().fareProperty());
	        }
	 
	}
	/////manage luggage/////////////////
	
	    @FXML
	    private void ProceedToAddLuggage1(ActionEvent event) {
	        // Retrieve the entered luggage weight
	        String weightText = LuggageWeight.getText();

	        try {
	            // Convert the weight to a numeric value
	            double weight = Double.parseDouble(weightText);

	            // Check if the weight is less than 45kg
	            if (weight < 45) {
	                // Add the luggage or perform other actions
	                // Add your logic here
	                System.out.println("Luggage weight is less than 45kg. Adding luggage...");
	            } else {
	                // Display an error message or perform other actions
	                // Add your logic here
	                System.out.println("Luggage weight is 45kg or more. Cannot add luggage.");
	            }
	        } catch (NumberFormatException e) {
	            // Handle the case where the entered weight is not a valid number
	            e.printStackTrace();
	            // You may want to show an error message to the user
	        }
	    }
//////////////////COMPLAINTS//////////////////////////////////
	   
	    @FXML
	    private TextField cnicField;

	    @FXML
	    private TextArea complaintTextArea;

	    @FXML
	    private Button proceedButton;

	    // JDBC database URL, username, and password of MySQL server
	    private static final String URL = "jdbc:mysql://localhost:3306/railwayschema";
	    private static final String USER = "root";
	    private static final String PASSWORD = "PASSWORD-123";

	    @FXML
	    private void initialize() {
	        // Disable the button initially
	        proceedButton.setDisable(true);
	    }

	    @FXML
	    private void checkFields() {
	        // Enable the button only if CNIC and Complaint are filled
	        boolean allFieldsFilled = !cnicField.getText().isEmpty() &&
	                !complaintTextArea.getText().isEmpty();

	        proceedButton.setDisable(!allFieldsFilled);
	    }

	    @FXML
	    private void submitComplaint(MouseEvent event) {
	        String cnic = cnicField.getText();
	        String complaint = complaintTextArea.getText();

	        // Insert the complaint into the database
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            String query = "INSERT INTO complaints (cnic, complaint_details) VALUES (?, ?)";
	            try (PreparedStatement pst = con.prepareStatement(query)) {
	                pst.setString(1, cnic);
	                pst.setString(2, complaint);
	                pst.executeUpdate();
	            }

	            // Reset fields after successful submission
	            cnicField.clear();
	            complaintTextArea.clear();

	            // Display a success message or perform any other actions as needed
	            System.out.println("Complaint submitted successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle database connection or query execution errors
	        }
	    }

	    @FXML
	    private void goToCustomerMenu(MouseEvent event) {
	        // Implement your go to customer menu action here
	        System.out.println("Going to customer menu...");
	    }
	}

