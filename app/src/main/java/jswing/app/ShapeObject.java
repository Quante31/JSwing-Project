package jswing.app;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShapeObject{
	public int w;
	public int h;
	public double x;
	public double y;
	public int k;
	public int r;
	public Color color;
	public ShapeType st;
	
	public ShapeObject() {
		
	}

	public ShapeObject(int x, int y, int w, int h,int k, ShapeType st) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.st = st;
		this.k = k;
	}
	Shape createShape() {
		
		switch(st) {
			case CIRCLE:
				Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, w, h);
				return ellipse;
			case RECTANGLE:
				Path2D.Double rect = new Path2D.Double();
				rect.moveTo(x, y+h);
				rect.lineTo(x, y);
				rect.lineTo(x+w, y);
				rect.lineTo(x+w, y+h);
				rect.closePath();
				return rect;
			case TRIANGLE:
				Path2D.Double triangle = new Path2D.Double();
				triangle.moveTo(x,y + h);
				triangle.lineTo(x + (w/k), y);
				triangle.lineTo(x + w, y + h);
				triangle.closePath();
				return triangle;
			default:
				break;
			
		}
		return null;
	}
	Shape createRotatedShape() {
		double vx = x - (x + w/2);
		double vy = y - (y + h/2);
		int x1 = (int) ((vx*Math.cos(r)) + (vy*Math.sin(r)));
		int y1 = (int) ((-vx*Math.sin(r)) + (vy*Math.cos(r)));
		vx = x - (x + w/2);
		vy = y+h - (y + h/2);
		int x2 = (int) ((vx*Math.cos(r)) + (vy*Math.sin(r)));
		int y2 = (int) ((-vx*Math.sin(r)) + (vy*Math.cos(r)));
		vx = x+w - (x + w/2);
		vy = y+h - (y + h/2);
		int x3 = (int) ((vx*Math.cos(r)) + (vy*Math.sin(r)));
		int y3 = (int) ((-vx*Math.sin(r)) + (vy*Math.cos(r)));
		vx = x+w - (x + w/2);
		vy = y - (y + h/2);
		int x4 = (int) ((vx*Math.cos(r)) + (vy*Math.sin(r)));
		int y4 = (int) ((-vx*Math.sin(r)) + (vy*Math.cos(r)));
		System.out.println(x1 + " " + y1);
		System.out.println(x2 + " " + y2);
		System.out.println(x3 + " " + y3);
		System.out.println(x4 + " " + y4);
		switch(st) {
			case CIRCLE:
				Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, w, h);
				//x_rotated =      ((x - (x + w/2)) * Math.cos(r)) - (((y + h/2) - y) * Math.sin(r)) + (x + w/2)
				//y_rotated = (y + h/2) - (((y + h/2) - y) * Math.cos(r)) + ((x - (x + w/2)) * Math.sin(r))
				return ellipse;
			case RECTANGLE:
				Path2D.Double rect = new Path2D.Double();
				rect.moveTo(x1, y1);
				rect.lineTo(x2, y2);
				rect.lineTo(x3, y3);
				rect.lineTo(x4, y4);
				rect.closePath();
				return rect;
			case TRIANGLE:
				Path2D.Double triangle = new Path2D.Double();
				triangle.moveTo(x,y + h);
				triangle.lineTo(x + (w/k), y);
				triangle.lineTo(x + w, y + h);
				triangle.closePath();
				return triangle;
			default:
				break;
			
		}
		return null;
	}
}

