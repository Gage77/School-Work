
import java.awt.*;
import javax.swing.*;

public class piChart extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] angles = new int[10];
	private Font font;
	public piChart(){
		setBackground(Color.white);
		setSize(600,400);
		setLocation(70, 70);
		setVisible(true);
	}
	public void setData(MediaMaker tempMaker){
		int[] mediaMakerNumbers = tempMaker.getMediaMakerNumberInfo();
		int totalCredit = mediaMakerNumbers[0] + mediaMakerNumbers[1] + mediaMakerNumbers[2];
		angles[0] = 0;
		angles[3] = 360;
		int percentActor = (mediaMakerNumbers[0] / totalCredit) * 100;
		if((Integer)percentActor == null){
			percentActor = 0;
		}
		int percentDirect = (mediaMakerNumbers[1] / totalCredit) * 100;
		if((Integer)percentDirect == null){
			percentDirect = 0;
		}
		int percentProduce = (mediaMakerNumbers[2] / totalCredit) * 100;
		if((Integer)percentProduce == null){
			percentProduce = 0;
		}
		int[] data = {percentActor, percentDirect, percentProduce};
		double sum = 0;
		for(int i = 1; i < 3; i++){
			sum += data[i-1];
			angles[i] = (int) (360*sum/totalCredit);
		}
		repaint();
	}
	@Override
	public void paint(Graphics g){
		
		
		font = new Font("Comic Sans", Font.BOLD, 14);
		
		g.setColor(Color.BLUE);
		g.fillArc(110, 80, 300, 300, 0, angles[1]);
		g.drawString("Acting Credits: " + 20 + "%", 420, 80);
				
		g.setColor(Color.RED);
		g.fillArc(110, 80, 300, 300, angles[1], angles[2]);
		g.drawString("Directing Credits: " + 20 + "%", 420, 100);
		
		g.setColor(Color.GREEN);
		g.fillArc(110, 80, 300, 300, angles[2], angles[3]);
		g.drawString("Producing Credits: " + 20 + "%", 420, 120);
		
	}
}
