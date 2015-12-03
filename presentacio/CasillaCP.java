package presentacio;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author arnau.zapata.i
 *
 */
@SuppressWarnings("serial")
public class CasillaCP extends JPanel{
	int x;int y;
	Graphics g;
	
	boolean error;
	int tamany;
	int valor=0;
	JButton b= new JButton();
	boolean[] candidats;
	JLabel labelOperacio= new JLabel();
	JLabel labelCandidats=new JLabel();
	JLabel labelValor = new JLabel();
	JLabel labelObjectiu = new JLabel();
	private Point p = new Point();
	Color colorOriginal =new Color(0,0,255);
	Color color1 = new Color(255,0,0);
	Color color2= new Color(0,255,0);
	Color color3= new Color(255,255,0);
	Color color4= new Color(255,0,255);
	Color color5= new Color(0,255,255);
	
	public CasillaCP(int tamany, int i1, int j1){
		x=i1;y=j1;
		Dimension d= this.getSize();
		d.setSize(d.height, d.height);
		this.setSize(d);
		this.tamany=tamany;
		candidats= new boolean[tamany];
		for(int i =0;i<tamany;i++)candidats[i]=false;
		writeCandidats();
		this.setBackground(colorOriginal);
		this.setLayout(new BorderLayout());
		this.add(labelOperacio,BorderLayout.WEST);
		this.add(labelCandidats,BorderLayout.SOUTH);
		this.add(labelValor,BorderLayout.EAST);
		this.add(labelObjectiu,BorderLayout.NORTH);
		
		
	}
	/*public Color getColor(){
		return c;
	}*/
	
	public void paintComponent(Graphics g) {
         int i=this.getLocation().x;
         int j=this.getLocation().y; 
         //System.out.println(i+" "+j);
	     super.paintComponent(g); 
	     int L=50;
	    for(int x=0;x<tamany;x++)for(int y=0;y<tamany;y++){
	    	int x1=27*x;
	    	int y1=42*y;
	    	g.drawLine(x1*i, y1*j, x1*i+2*L, y1*j);
	    	g.drawLine(x1*i, y1*j, x1*i, y1*j+3*L);
	    }
	     
 
	 }
	 
	 
	
	public void setColor(Color c2) {
		this.setBackground(c2);
	}
	public void setColorOriginal(Color c2){
		colorOriginal=c2;
		this.setColor(c2);
		labelValor.setText("");
		labelOperacio.setText("");
		labelObjectiu.setText("");

	}
	public void ReturnColorOriginal(){
		this.setBackground(colorOriginal);
	}
	public Color getColorOriginal() {
		return colorOriginal;
	}
	public String getValor() {
		String s=this.labelValor.getText();
		return s;		
	}
	public void setValor(String valor) {
		if(!valor.equals("CAP") && !valor.equals("0")){
			this.valor=Integer.valueOf(valor);
			labelValor.setText(valor);
		}
		else{
			this.valor=0;
			labelValor.setText("");
		}
	}
	public String getOperacio(){
		return labelOperacio.getText();
	}
	public String getObjectiu(){
		return labelObjectiu.getText();
	}
	public void setOperacio(String o){
		if(o.equals("eligeix l'operacio")){}
		else if(o.equals("CAP")){labelOperacio.setText(" ");}
		else labelOperacio.setText(o);
	}
	public boolean[] getCandidats(){
		return candidats;
	}
	
	public void eraseCandidat(String valor){
		int i=Integer.valueOf(valor);
		candidats[i-1]=false;
		writeCandidats();
	}
	private void writeCandidats() {
		String aux ="";
		for(int i=0;i<candidats.length;i++){
			if(candidats[i]==false)aux+="  ";
			else aux+=String.valueOf(i+1);
		}
		labelCandidats.setText(aux);
	}

	public void addCandidat(String valor){
		int i=Integer.valueOf(valor);
		candidats[i-1]=true;
		writeCandidats();
	}
	public Point getXY(){
		p.x=x;
		p.y=y;
		return p;
	}

	public void AfegirRegio(boolean[] aux) {
		int i=1;
		for(i=1;i<aux.length;i++){
			if(aux[i]==false){setColorOriginal(ChooseColor(i));break;}
		}

		if(i==aux.length)setColor(ChooseColor(0));
	}
	private Color ChooseColor(int i) {
		switch(i){
			case 1: colorOriginal=color1;return color1;
			case 2: colorOriginal=color2;return color2;
			case 3: colorOriginal=color3;return color3;
			case 4: colorOriginal=color4;return color4;
			case 5: colorOriginal=color5;return color5;
			default: return colorOriginal; 
		}
	}

	public boolean setObjectiu(String valor) {
		boolean b =toInt(valor);
		valor=valor.replaceAll(" ","");
		if(b){
			labelObjectiu.setText(valor);	
		}
		return b;
	}

	private boolean toInt(String valor) {
		valor.replaceAll(" ", "");
		int i= valor.length();
		for(int j=0;j<i;j++){
			char c=valor.charAt(j);
			if(c==' '){}
			if(c>='a' && c<='z'){return false;}
			if(c>='A' && c<='Z'){return false;}
		}
		return true;
	}
	public Color getColor() {
		return this.getBackground();
	}

	public void borrarValor() {
		this.setValor("CAP");
		
	}

	public int getValorInt() {
		return valor;
	}
	
}
