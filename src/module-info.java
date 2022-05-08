module BillManagmentSystem {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.mail;
	requires activation;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
