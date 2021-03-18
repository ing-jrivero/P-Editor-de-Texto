package Editor_de_texto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.print.attribute.AttributeSet;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.TextArea;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.KeyAdapter;

public class editor_texto extends JFrame {
	//declarando el objeto JtextPane de la interfaz grafica
	JTextPane miarea;
	//Declarando objetos para darle formato a las letras en el JTextPane
	SimpleAttributeSet attrs_A ; //azul
	SimpleAttributeSet attrs_N ; //negro
	SimpleAttributeSet attrs_V ; //verde
	SimpleAttributeSet attrs_R ; //rojo
	
	//Declarando variables de control de funciones
	//en este caso para la revision del texto y saber si seguiamos recibiendo parametros de una variable,una condicional o un predicado.
	boolean variable;
	boolean condicional;
	boolean predicado;
	
	JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editor_texto frame = new editor_texto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public editor_texto() {
		
        //en la siguiente linea de codigo configuramos el icono de la interfaz grafica
		setIconImage(Toolkit.getDefaultToolkit().getImage(editor_texto.class.getResource("/Editor_de_texto/page_edit.png")));
		setTitle("Editor de Texto");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		//creamos un objeto JMenuBar
		JMenuBar menuBar = new JMenuBar();
		//creamos los objetos JMenu
		JMenu abrir=new JMenu("Abrir");
		JMenu guardar=new JMenu("Guardar");
		JMenu formato=new JMenu("Formato");
		JMenu cerrar=new JMenu("Cerrar");
		//creamos los objetos JMenuItem
		JMenuItem abrir_aut=new JMenuItem("Abrir Local");
		abrir.add(abrir_aut);
		JMenuItem abrir_file=new JMenuItem("Buscar Archivo");
		abrir.add(abrir_file);
		JMenuItem guardar_aut=new JMenuItem("Guardar Local");
		guardar.add(guardar_aut);
		JMenuItem guardar_file=new JMenuItem("Guardar Archivo");
		guardar.add(guardar_file);
		JMenuItem tamaño=new JMenuItem("Tamaño");
		formato.add(tamaño);
		JMenuItem cerrar_file=new JMenuItem("Cerrar Programa");
		cerrar.add(cerrar_file);
		
		//asignamos un evento para los objetos JMenuItem
		GestionMenu_a1 abrir_local=new GestionMenu_a1();
		abrir_aut.addActionListener(abrir_local);
		GestionMenu_a abrir_ruta=new GestionMenu_a();
		abrir_file.addActionListener(abrir_ruta);
		GestionMenu_g1 guardar_local=new GestionMenu_g1();
		guardar_aut.addActionListener(guardar_local);
		GestionMenu_g guardar_arc=new GestionMenu_g();
		guardar_file.addActionListener(guardar_arc);
		GestionMenu_f formato_tam=new GestionMenu_f();
		tamaño.addActionListener(formato_tam);
		GestionMenu_c cerrar_ventana=new GestionMenu_c();
		cerrar_file.addActionListener(cerrar_ventana);
		
		//agregamos los objetos JMenu al objeto JMenuBar
		menuBar.add(abrir);
		menuBar.add(guardar);
		menuBar.add(formato);
		menuBar.add(cerrar);
		//agrgamos el objeto JMenuBar al panel para que asi pueda ser visible en la interfaz grafica	
		panel.add(menuBar);
		
		//inicializamos el JTextPane
		miarea=new JTextPane();
		
		
		//creamos un metodo para detectar las teclas presionadas en el JtextPane
		miarea.addKeyListener(new KeyAdapter() {
			/*en un principio pensabamos utilizar este metodo para llevar el control de los datos introducidos en el JTextPane pero luego nos dimos cuenta
			que habia otro metodo mejor para la funcion de este caso.*/
			@Override
			public void keyPressed(KeyEvent e) {
				//esta parte del codigo no se utiliza en la ejecucion en este momento pero no quicimos eliminarla por que puede ser util para funciones posteriores
				String datos;
				datos=miarea.getText();
			
				if(e.getKeyCode()==KeyEvent.VK_ENTER) 
				{
				//este if colocaba un punto al final de cada sentencia si es que no lo tenia puesto
				/*	if(datos.charAt(datos.length()-1)!='.') 
					{
						try {
							miarea.getStyledDocument().insertString(datos.length()-1,".", attrs1);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}*/
				}
			
                
			}
			
			//Creando metodos para detectar las teclas liberadas 
			@Override
			public void keyReleased(KeyEvent e) {
				//aqui creamos una variables tipo string la cual captura todos los datos introducidos hasta el momento en el que se libera una tecla
				String datos;
				datos=miarea.getText();
				
				//inicializamos los objetos que nos ayudaran a cambiar el color de las letras segun sea el caso
				attrs_A = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_A, Color.BLUE);
				
				attrs_N = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_N, Color.BLACK);
				
				attrs_V = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_V, Color.GREEN);
				
				attrs_R = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_R, Color.RED);
				
				
				/*Este primer if checa si la ultima tecla registrada es un '(' o si la variable predicado esta true
				 * la logica que usamos en este if fue si ya se habia registrado un '(' entonces debia cambiar a true la variable predicado, asi cual quier tecla 
				 * introducida luego se cambiaria al color rojo hasta que la variable predicado volviera a ser false*/
				if(datos.charAt(datos.length()-1)=='('||predicado==true) 
				 {
				
					 predicado=true;
					 /*al ser verdadera la sentencia if, en esta linea declaramos el cambio de estilo del formato de las letra en el JTextPane y le indicamos que cambie el estilo
					 desde la ultima tecla regitrada*/
 					 miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_R, true);
					/*En el dado caso que se fuera a terminar el predicado que se estaba introduciendo en el JTextPane, se creo este if que verifica si la antepenultima
					 * tecla registrada era un ')' en ese caso predicado se declara false y se regresa el formato de color negro */	
 					 if(datos.charAt(datos.length()-2)==')') 
						{
							predicado=false;
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
						}
				
				 }
				/*En este if checa si las teclas liberadas coenciden con ese margen de numeros del codigo ASCII
				 * ya que todos los numeros que entran en ese margen se supone que son puras letras mayusculas pero luego no servia del todo bien
				 * asi que decidi agregar otro if para comprobar si la letra esra mayuscula o minuscula */
				else if(e.getKeyCode()>=65&&e.getKeyCode()<=90||variable==true){//inicio con letras mayusculas
					 String letra=""+e.getKeyChar();//este string se creo solo para poder comparar un string con un char
					 /*lo que se hace aqui es tener un string con la tecla liberada y se comprara con la misma tecla pero aplicando un metodo para comvertirla
					  * en mayuscula si son iguales la letra original deberia de ser mayuscula*/
					 if(letra.equals(letra.toUpperCase())||variable==true) 
					 {
						/*en este if checamos si la antepenultima tecla liberada es un '_' ya que si este es el caso por la sintaxis podria ser una variable */	
						 if(datos.charAt(datos.length()-2)=='_'||variable==true) 
						 {
							/*en este if controlamos cuando finaliza una varible ya que al dar un espaco o salto de linea ya no entra en la sintaxis de una variable*/ 
							 if(datos.charAt(datos.length()-1)==' '||datos.charAt(datos.length()-1)==KeyEvent.VK_ENTER) 
							 {
								 //como ya no estamos buscando una vartiable declaramos false a variable
								 variable=false;
							 }else 
							 {
								 /*en el caso de que no se introduzcan espacion ni saltos de linea declaramos en true a variable para comenzar a buscar letra que puedan ser variables
								  y cambiaoms el color de letra de verde*/
								 variable=true;
		    					
							   	miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-2, miarea.getStyledDocument().getLength(), attrs_V, true);
													
							 }
								
						 }
									
										
						 
					 }
					 
						
						}
				 
				/*Este if revisa si se ha liberado la tecla '-' y si es asi revisa si la antepenultima telca liberada es ':' asi podremos saber si se forma la sintaxis de la condicional*/
				else if(e.getKeyCode()==45||condicional==true){//guion
					if(datos.charAt(datos.length()-2)==':'||condicional==true)//la parte del "||condicional" en ambos if por el momento no se utilizan
					{
					//	condicional=true;
						//aqui solo cambiamos el color a azul del texto
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-2, miarea.getStyledDocument().getLength(), attrs_A, true);
								
							}
						}
					
					//en este if checamos si se ha liberado una coma
				else if(e.getKeyCode()==KeyEvent.VK_COMMA){//coma
					//si es asi se cambia el color a azul
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_A, true);
						}
				//en este if se revisa si se a liberado un punto
						else if(e.getKeyCode()==KeyEvent.VK_PERIOD){//punto
							//si es asi se cambia el color a azul
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_A, true);
							}
				/*en este if checamos si se ha liberado la tecla de espacio esto lo ocupamos para poder terminar ciertas condiciones de las reglas de la sintaxis
				y asi poder volver a poner el texto en color negro*/
				else if(datos.charAt(datos.length()-1)==' ') {
                    //en este if checamos si variable es true, si es asi la volvermos false y cambiamos el color a negro				
					if(variable==true){ 
						
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
							variable=false;
					}
					//en este if checamos si condicional es true, si es asi lo volvemos false y cambiamos el color a negro
					if(condicional==true) 
					{
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
						condicional=false;
				
					}
					
				}
				//por ultimo en este else si se liber cualquier otra tecla diferente de las antes mencionadas cambia el color a negro
				else 
				{	
			miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
			}
		//		System.out.println(datos);
		//		System.out.println("Ultimo dato:"+datos.charAt(datos.length()-1));
				
			//	System.out.println(miarea.getStyledDocument().getLength());
		
			}
		});
		JScrollPane sp = new JScrollPane(miarea);
		
		getContentPane().add(sp,BorderLayout.CENTER);
		
		
	}
	
	/*en este programa se crearon varias clases para definir los eventos de los objetos del programa*/
	//en esta clase se define como va a ser la funcion Abrir Local
private class GestionMenu_a1 implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		String ruta="prueba.txt";
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			linea = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		miarea.setText(linea);
		
	}
}
    //en esta clase se define la funcion Abrir Archivo
	private class GestionMenu_a implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String ruta="";
		
		ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Abrir Fichero",JOptionPane.INFORMATION_MESSAGE);
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			linea = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		miarea.setText(linea);
		
	}
}
	//en esta clase se define la funcion Guardar Local
	private class GestionMenu_g1 implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=miarea.getText();
		//	System.out.println(archivo);
		        try
		        {
		            fichero = new FileWriter("prueba.txt");
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion Guardar Archivo
	private class GestionMenu_g implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=miarea.getText();
		//	System.out.println(archivo);
			String ruta="";
			
			ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Guardar Fichero",JOptionPane.INFORMATION_MESSAGE);
			
		        try
		        {
		            fichero = new FileWriter(ruta);
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion tamaño
	private class GestionMenu_f implements ActionListener {
		
        
		public void actionPerformed(ActionEvent e) {
			int tamaño;
			tamaño=Integer.parseInt(JOptionPane.showInputDialog(null,"Integre Tamaño de Letra","Tamaño de Letra",JOptionPane.INFORMATION_MESSAGE));
			miarea.setFont(new Font("Arial",Font.PLAIN,tamaño));
				
	}
	}
	//en esta clase se define la funcion cerrar
	private class GestionMenu_c implements ActionListener {
		

	@Override
	public void actionPerformed(ActionEvent e) {
        
        if(JOptionPane.showConfirmDialog (null, "Deseas Cerrar El Programa?","Cerrar",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }else {
              
            }
          }
		
		
	}
	
	
	
}

