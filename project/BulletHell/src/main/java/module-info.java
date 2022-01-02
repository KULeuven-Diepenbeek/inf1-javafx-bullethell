module be.inf1.bullethell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens be.inf1.bullethell to javafx.fxml;
    exports be.inf1.bullethell;
}
