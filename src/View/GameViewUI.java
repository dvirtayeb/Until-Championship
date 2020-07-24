package View;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public interface GameViewUI {
	
	int getPointFromTeam1(int index);
	int getPointFromTeam2(int index);
	public Button getBtnDone();
	public ArrayList<Text> getParticipants();
	
}
