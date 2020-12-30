/*
 * Christian Taborda
 * 1632081-3743
 */

package luces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LucesGui extends JFrame{
	private JLabel titulo;
	private Container contenedor;
	private static final int GRIDSIZE = 3;
	private LuminosButton[][] botones;
	private JPanel marco;
	
	public void initGUI(){
		contenedor = this.getContentPane();
		titulo = new JLabel("Marco de Luces");
		contenedor.add(titulo,BorderLayout.PAGE_START);
		botones = new LuminosButton[GRIDSIZE][GRIDSIZE];
		marco = new JPanel();
		marco.setLayout(new GridLayout(3,3));
		
		EscuchaEventosAccion escucha = new EscuchaEventosAccion();
		
		for(int x=0; x<GRIDSIZE; x++){
			for(int y=0; y<GRIDSIZE; y++){
				botones[x][y] = new LuminosButton(x,y);
				botones[x][y].addActionListener(escucha);
				marco.add(botones[x][y]);
			}
		}
		
		contenedor.add(marco,BorderLayout.CENTER);
	}
	
	public LucesGui(){
		initGUI();
		
		setTitle("Cambia las luces");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	public void cambiarLuces(int fila, int columna){
		botones[fila][columna].cambiarOnOff();
		
		if(fila == 0 && columna == 0){
			botones[0][1].cambiarOnOff();
			botones[1][0].cambiarOnOff();
			botones[1][1].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 0 && columna == 2){
			botones[0][1].cambiarOnOff();
			botones[1][1].cambiarOnOff();
			botones[1][2].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 2 && columna == 0){
			botones[1][0].cambiarOnOff();
			botones[2][1].cambiarOnOff();
			botones[1][1].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 2 && columna == 2){
			botones[1][1].cambiarOnOff();
			botones[2][1].cambiarOnOff();
			botones[1][2].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 1 && columna == 0){
			botones[0][0].cambiarOnOff();
			botones[2][0].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 0 && columna == 1){
			botones[0][0].cambiarOnOff();
			botones[0][2].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 1 && columna == 2){
			botones[0][2].cambiarOnOff();
			botones[2][2].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 2 && columna == 1){
			botones[2][2].cambiarOnOff();
			botones[2][0].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
		if(fila == 1 && columna == 1){
			botones[0][1].cambiarOnOff();
			botones[2][1].cambiarOnOff();
			botones[1][0].cambiarOnOff();
			botones[1][2].cambiarOnOff();
			if(juzgar()){
				JOptionPane.showMessageDialog(null,"Ganaste!","Resultado",JOptionPane.PLAIN_MESSAGE);
				reinicio();
			}
		}
	}
	
	public Boolean juzgar(){
		Boolean salida = true;
		if(!botones[1][1].isOn()){
			for(int x=0; x<GRIDSIZE; x++){
				if(!botones[0][x].isOn()){
					salida = false;
				}
			}
			for(int x=0; x<GRIDSIZE; x++){
				if(!botones[2][x].isOn()){
					salida = false;
				}
			}
			if(!botones[1][0].isOn()){
				salida = false;
			}
			if(!botones[1][2].isOn()){
				salida = false;
			}
		}
		else{
			salida = false;
		}
		return salida;
	}
	
	public void reinicio(){
		int decision = JOptionPane.showConfirmDialog(null, "Quieres volver a jugar?", "Reinicio", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(decision == 0){
			for(int x=0; x<GRIDSIZE; x++){
				for(int y=0; y<GRIDSIZE; y++){
					botones[x][y].apagar();
				}
			}
		}
		else{
			System.exit(-1);
		}
	}
	
	private class EscuchaEventosAccion implements ActionListener{
		public void actionPerformed(ActionEvent evento){
			LuminosButton boton = (LuminosButton)evento.getSource();
			int fila = boton.getFila();
			int columna = boton.getColumna();
			cambiarLuces(fila,columna);
		}
	}
}
