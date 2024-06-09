package jswing.app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShapeGenerator extends JFrame implements ActionListener{
	JPanel buttonsPanel;
	JPanel infoPanel;
	JTextField degreeField;
	JButton button;
	JButton fillButton;
	JButton turnButton;
	JButton submitButton;
	JButton fibonnaciButton;
	JColorChooser colorChooser;
	JLabel widthLabel;
	JLabel heightLabel;
	JLabel xLabel;
	JLabel yLabel;
	JLabel mouseLabel;
	Color color;
	
	Shape shape;
	ShapeObject shapeObj;
	int angle = 90;
	
	public ShapeGenerator() {
		this.setTitle("MyFrame");
		this.setSize(1280,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBackground(Color.decode("#F9F6EE"));
		ImageIcon icon = new ImageIcon("favicon.png");
		this.setIconImage(icon.getImage());
		//panel = new MyPanel();
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(null);
		buttonsPanel.setBounds(0, 0, 180,1000);
		
		button = new JButton("Create a shape");
		button.setFont(new Font("Comic Sans",Font.PLAIN,14));
		button.setFocusable(false);
		button.setBounds(0,0,180,100);		
		button.setBackground(Color.white);
		button.addActionListener(this);
	
		fillButton =new JButton("Color the shape");
		fillButton.setFont(new Font("Comic Sans",Font.PLAIN,14));
		fillButton.setFocusable(false);
		fillButton.setBounds(0,110,180,100);
		fillButton.setBackground(Color.white);
		fillButton.addActionListener(this);
		
		turnButton = new JButton("Rotate the shape");
		turnButton.setFont(new Font("Comic Sans",Font.PLAIN,14));
		turnButton.setFocusable(false);
		turnButton.setBounds(0,220,180,100);
		turnButton.setBackground(Color.white);
		turnButton.addActionListener(this);
		
		degreeField = new JTextField();
		degreeField.setPreferredSize(new Dimension(60,25));
		degreeField.setBounds(45,330,60,25);
		
		submitButton = new JButton();
		submitButton.addActionListener(new SubmitButtonListener());
		submitButton.setPreferredSize(new Dimension(25,25));
		submitButton.setBounds(110,330,25,25);
		
		colorChooser = new JColorChooser();
		colorChooser.setBounds(0, 365, 180, 100);
		colorChooser.getSelectionModel().addChangeListener(new ColorChooserListener());
		colorChooser.setSize(new Dimension(180,100));
		colorChooser.setVisible(true);
		colorChooser.setPreviewPanel(new JPanel());
		

		fibonnaciButton = new JButton("Plot fibonnaci");
		fibonnaciButton.setBounds(0,475,180,100);
		fibonnaciButton.setBackground(Color.white);
		fibonnaciButton.addActionListener(this);
		
		buttonsPanel.add(button);
		buttonsPanel.add(fillButton);
		buttonsPanel.add(turnButton);
		buttonsPanel.add(colorChooser);
		buttonsPanel.add(degreeField);
		buttonsPanel.add(submitButton);
		buttonsPanel.add(fibonnaciButton);
		buttonsPanel.setVisible(true);
		
		infoPanel = new JPanel();
		infoPanel.setSize(new Dimension(100,230));
		infoPanel.setLayout(null);
		infoPanel.setBounds(this.getBounds().width - 100, this.getBounds().height - 230, 100,230);
		
		widthLabel = new JLabel("w: 0");
		widthLabel.setFont(new Font(null,Font.PLAIN,12));
		widthLabel.setBounds(0, 0, 100, 100); 
		
		
		heightLabel = new JLabel("h: 0");
		heightLabel.setFont(new Font(null,Font.PLAIN,12));
		heightLabel.setBounds(0, 100, 100, 100);
		
		xLabel = new JLabel("x: 0");
		xLabel.setFont(new Font(null,Font.PLAIN,12));
		xLabel.setBounds(0, 37, 100, 100); 
		
		yLabel = new JLabel("y: 0");
		yLabel.setFont(new Font(null,Font.PLAIN,12));
		yLabel.setBounds(0, 75, 100, 100);
		
		mouseLabel = new JLabel();
		mouseLabel.setFont(new Font(null,Font.PLAIN,9));
		mouseLabel.setBounds(0,0,70,20);
		
		infoPanel.add(widthLabel);
		infoPanel.add(heightLabel);
		infoPanel.add(xLabel);
		infoPanel.add(yLabel);
		infoPanel.setVisible(true);

		
		this.add(buttonsPanel);
		this.add(infoPanel);
		this.add(mouseLabel);
		this.setVisible(true);
		
		
		this.doLayout();
		
		MouseMotionEventDemo mouseMotionEventDemo = new MouseMotionEventDemo(this);
	}
	

	
	public void fill(Graphics g) {
		if (color == null) {
			return;
		}
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(color);
		g2D.fill(shape);
		shapeObj.color = color;
	}
	public void rotate(Graphics g) {
		this.paint(g);
		Graphics2D g2D = (Graphics2D)g;
		Rectangle2D bounds = shape.getBounds2D();
		double maxDim = Math.max(bounds.getWidth(), bounds.getHeight());
	    Rectangle2D extendedBounds = new Rectangle2D.Double(bounds.getX() - maxDim, bounds.getY() - maxDim,bounds.getWidth() + 2 * maxDim, bounds.getHeight() + 2 * maxDim);
	    g2D.setClip(extendedBounds);
		shapeObj.r += angle;
		//shape = shapeObj.createRotatedShape();
		double cx = bounds.getCenterX();
		double cy = bounds.getCenterY();
		g2D.rotate(Math.toRadians(shapeObj.r),cx, cy);
		g2D.setStroke(new BasicStroke(2));
		//g2D.clearRect(0, 0, shapeObj.w, shapeObj.h);
		g2D.draw(shape);	
		if (shapeObj.color != null) {
			g2D.setColor(color);
			g2D.fill(shape);
		}
		g2D.setClip(null);	
		
	}
	void getNewCoordinates(int x, int y,int cx, int cy, int r) {
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Graphics g = this.getGraphics();
		if (e.getSource() == button) {
			
			shapeObj = new ShapeObject(Main.random.nextInt(180,800),Main.random.nextInt(200,800),Main.random.nextInt(400),Main.random.nextInt(400),Main.random.nextInt(400),ShapeType.values()[Main.random.nextInt(3)]);
			shape = shapeObj.createShape();
			Graphics2D g2D = (Graphics2D)g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.addRenderingHints(rh);
			g2D.setPaint(Color.black);
			g2D.setStroke(new BasicStroke(2));
			g2D.draw(shape);		
			widthLabel.setText("w: " + shapeObj.w);
			heightLabel.setText("h: " + shapeObj.h);
			xLabel.setText("x: " + shapeObj.x);
			yLabel.setText("y: " + shapeObj.y);
			
		}
		if (e.getSource() == fibonnaciButton) {
			shape = Fibonnaci.createFibonnaciSequence(15);
			Graphics2D g2D = (Graphics2D)g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setPaint(Color.black);
			g2D.addRenderingHints(rh);
			g2D.setStroke(new BasicStroke(2));
			g2D.draw(shape);	
		}
		if (shape == null) {
			return;
		}
		
		if (e.getSource() == fillButton) {
			this.fill(g);
		}
		if (e.getSource() == turnButton) {
			this.rotate(g);
		}
	}
	

	

	class ColorChooserListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
		    Color newColor = colorChooser.getColor();
		    color = newColor;
		}
	}
	
	class SubmitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitButton) {			
				angle = Integer.parseInt(degreeField.getText().trim());			
			}
		}
	}
	public class MouseMotionEventDemo extends JPanel implements MouseMotionListener {	
		public MouseMotionEventDemo(JFrame frame){
			frame.addMouseMotionListener(this);
	        addMouseMotionListener(this);
		}
		public void mouseMoved(MouseEvent e) {
			display(e);
		}
		
		public void mouseDragged(MouseEvent e) {
			display(e);
		}
		
		void display(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			mouseLabel.setLocation(x, y);
			mouseLabel.setText("x: " + Integer.toString(x) + " y: " + Integer.toString(y));
		}
	}

	

}
