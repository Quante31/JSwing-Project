package jswing.app;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Fibonnaci {
	static int[] fibonnaci = {0 ,1 ,1 ,2 ,3 ,5 ,8 ,13 ,21 ,34 ,55 ,89 ,144 ,233 ,377 ,610 ,987 ,1597 ,2584 ,4181 };
	static int Fibonnachi(int n) {	
		if (n == 0 || n == 1) {
			return n;
		}
			
		int twoBehind = 0;
		int oneBehind = 1;
		int result = 0;
		for (int i = 1; i < n; i++){
			result = twoBehind + oneBehind;
			twoBehind = oneBehind;
			oneBehind = result;
		}
		return result;
	}
	
	static Shape createFibonnaciSequence(int length) {
		Path2D.Double path2D = new Path2D.Double();
		path2D.moveTo(200, 0);
		path2D.lineTo(200, 700);
		path2D.lineTo(880, 700);
		path2D.moveTo(200, 700);
		for (int i = 1; i < length;i++) {
			path2D.lineTo(200 + i*10, 700 - (i*fibonnaci[i]));
		}
		return path2D;
	}
	
}
