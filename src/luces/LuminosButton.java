/*
 * Christian Taborda
 * 1632081-3743
 */

package luces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class LuminosButton extends JButton{
	private int fila = 0, columna = 0;
	private static final int MAXSIZE = 150;
	
	public LuminosButton(int fila, int columna){
		this.fila = fila;
		this.columna = columna;
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		Dimension size = new Dimension(MAXSIZE,MAXSIZE);
		this.setPreferredSize(size);
	}
	
	public int getFila(){
		return fila;
	}
	
	public int getColumna(){
		return columna;
	}
	
	public void encender(){
		this.setBackground(Color.RED);
	}
	
	public void apagar(){
		this.setBackground(Color.BLACK);
	}
	
	public Boolean isOn(){
		Boolean estado;
		estado = this.getBackground().equals(Color.RED);
		return estado;
	}
	
	public void cambiarOnOff(){
		if(isOn()){
			apagar();
		}
		else{
			encender();
		}
	}
}
