/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import Services.Add;
import datojava.jcalendar.DJFechasEspInv;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import medical.Medico;
import Services.Leer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Yanir
 */
public class FrameCita extends javax.swing.JFrame  implements ActionListener{

    /**
     * Creates new form Cita
     */
     
    
    //////////////Lecturas y escrituras de la BD
    Leer rutasLeer;
    Add rutasAdd;
    
    ////////////// ELEMENTOS DEL PANEL DETALLE
    Font font;
    Color colorTextFields;
    Color colorBackGround;
    Color colorDelPapa;
    Color colorBotones;
    Color colorActivo;
    JPanel Botones;
    JPanel BotonAtras;
    JPanel BotonBuscar;
    
    //Labels - Fijos, no se modifican
    JLabel fechaL;
    JLabel horaL;
    JLabel nombreL;
    JLabel apellidoL;
    JLabel cedulaL;
    JLabel direccionL;
    JLabel motivosL;
    JLabel telefonoCasaL;
    JLabel telefonoCelularL;
    JLabel correoL;
    JLabel horaCitas;//
      
    //JTextFields - seran para ingreso o impresion de datos segun sea el caso
    JTextField fechaJ;
    JTextField horaJ;
    JTextField nombreJ;
    JTextField apellidoJ;
    JTextField cedulaJ;
    JTextField direccionJ;
    JTextField telefonoCasaJ;
    JTextField telefonoCelularJ;
    JTextField correoJ;
    
    
    //JTextArea - para ingreso o impresion de motivos
    JTextArea motivosTA;
    
    //Botones - Se activaran segun accion necesaria
    JButton modificarB;
    JButton eliminarB;
    JButton agregarB;
    JButton atrasB;
    JButton buscarB;
    
    
    
    JScrollPane scrollPanelCita; //Scroll del panel Cita
    Leer testl;
    Add addu;
    
    //Medico en sesion
    Medico medico;
    Citas citas[];
    //Para los minutos y horas
    int min;
    int hora;
    
    
    ////////////// ELEMENTOS DEL PANEL DETALLE
    
    
    public FrameCita() throws ClientProtocolException, IOException, JSONException, ParseException, java.text.ParseException {
        
        initComponents();
        this.getContentPane().setLayout(new GridBagLayout());
        rutasLeer = new Leer();
        rutasAdd = new Add();
        medico = new Medico(2,0,3,30); //MEDICO EN SESIOOON
        citas = new Citas [medico.cantidadDeCitasxDia(10, 30)];
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Sertig.otf"));
            font  = font.deriveFont(Font.BOLD, 11);
        } catch (FontFormatException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setTitle("Citas");
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(PanelCalendar, gbc);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        this.getContentPane().add(PanelCita, gbc);
        
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.getContentPane().add(PanelDetalle, gbc); 
        
              
        
        ////////////////// PANEL DETALLES
        
        //Colores
        colorBackGround=new Color(hex("8FA8F6"));//Color de los BackGround 
        colorTextFields = new Color (hex("CDCBA6"));//Color de los JTextFields
        colorDelPapa = new Color (hex("2980b9"));//Color del backgorud del papa
        colorBotones = new Color (hex ("2C3E50"));//Color d elos botonte
        colorActivo = new Color (hex ("81CFE0"));//Color de los jTextFild cuando los activan
        //Colores

        
        //Inicializaciones
        
        this.setBackground(colorDelPapa);//Color del backgroud
        //BOTONES INICIALIZACION
        modificarB = new JButton("Modificar");
        eliminarB = new JButton("Eliminar");
        agregarB = new JButton("Agregar");
        atrasB= new JButton("Atras");
        buscarB = new JButton ("Buscar");
        Botones = new JPanel();  //PAnel donde van los botones Agregar, Modificar y Eliminar
        BotonAtras = new JPanel();  //PAnel donde va el boton atras
        BotonBuscar = new JPanel();  //PAnel donde va el boton buscar
        agregarB.addActionListener(this);
        modificarB.addActionListener(this);
        eliminarB.addActionListener(this);
        atrasB.addActionListener(this);
        buscarB.addActionListener(this);
        // FIN BOTONES INICIALIZACION
        
        PanelCita.setLayout(new GridBagLayout());
        
        disenoBotones(modificarB);
        disenoBotones(eliminarB);
        disenoBotones(agregarB);
        disenoBotones(atrasB);
        disenoBotones(buscarB);
        PanelDetalle.setLayout(new GridBagLayout());          //Panel Papá (PanelDetalle)
        cambiarColorPanel(PanelCalendar,colorDelPapa);
        cambiarColorPanel(PanelCita,colorDelPapa);
        cambiarColorPanel(PanelDetalle,colorDelPapa);
        
        
        //LABELS
        horaCitas = new JLabel();
        fechaL=new JLabel("<HTML> Fecha &nbsp</HTML>");
        horaL=new JLabel("<HTML> Hora &nbsp</HTML>");
        nombreL=new JLabel("<HTML> Nombre &nbsp &nbsp </HTML>");
        apellidoL=new JLabel("<HTML> Apellido &nbsp &nbsp &nbsp</HTML>");
        cedulaL=new JLabel("<HTML> Cedula &nbsp &nbsp &nbsp &nbsp &nbsp</HTML>");
        direccionL=new JLabel("<HTML> Direccion &nbsp &nbsp</HTML>");
        motivosL=new JLabel("<HTML> Motivos &nbsp &nbsp &nbsp &nbsp</HTML>");
        telefonoCasaL=new JLabel("<HTML> Tlfn Casa&nbsp </HTML>");
        telefonoCelularL=new JLabel("<HTML> Tlfn Celular</HTML>");
        correoL=new JLabel("<HTML> Correo &nbsp &nbsp &nbsp</HTML>");
        
        font=font  = font.deriveFont(Font.BOLD, 11);
        disenoLabel(horaCitas);
        disenoLabel(fechaL);
        disenoLabel(horaL);
        disenoLabel(nombreL);
        disenoLabel(apellidoL);
        disenoLabel(cedulaL);
        disenoLabel(direccionL);
        disenoLabel(motivosL);
        disenoLabel(telefonoCasaL);
        disenoLabel(telefonoCelularL);
        disenoLabel(correoL);
        
        
        //JTEXTFIELDS y TEXAREA
        
        fechaJ=new JTextField("");
        horaJ=new JTextField("");
        nombreJ=new JTextField("");
        apellidoJ=new JTextField("");
        cedulaJ=new JTextField("");
        direccionJ=new JTextField();
        motivosTA=new JTextArea("");
        telefonoCasaJ=new JTextField("");
        telefonoCelularJ=new JTextField("");
        correoJ=new JTextField("");
        //COLORES
        /*fechaJ.setBackground(colorTextFields);
        horaJ.setBackground(colorTextFields);
        nombreJ.setBackground(colorTextFields);
        apellidoJ.setBackground(colorTextFields);
        cedulaJ.setBackground(colorTextFields);
        direccionJ.setBackground(colorTextFields);
        motivosTA .setBackground(colorTextFields);
        telefonoCasaJ.setBackground(colorTextFields);
        telefonoCelularJ.setBackground(colorTextFields);
        correoJ.setBackground(colorTextFields);
*/
       //Añadir componentes al PanelDetalle
       GridBagConstraints constraints = new GridBagConstraints();
       
       //Caracteristicas globales del grid
       constraints.fill = GridBagConstraints.BOTH;
       constraints.weighty = 1.0;
       constraints.insets.set(5, 0, 5,10 );
       //Fin Caracteristicas Globales del grid
       
       constraints.gridx = 0;
       constraints.gridy = 0; 
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (fechaL, constraints);
       
       
       constraints.gridx = 1; //Necesita estirarse
       constraints.gridy = 0;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (fechaJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 1; 
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (horaL, constraints);
       
       
       constraints.gridx = 1; //Necesita estirarse
       constraints.gridy = 1;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (horaJ, constraints);
       constraints.weightx = 0.0;
       
       
       constraints.gridx = 0;
       constraints.gridy = 2;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (nombreL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 2;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (nombreJ, constraints);
       constraints.weightx = 0.0;
         
       constraints.gridx = 0;
       constraints.gridy = 3;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (apellidoL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 3;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (apellidoJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 4;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (cedulaL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 4;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (cedulaJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 5;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (direccionL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 5;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (direccionJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 6;
       constraints.gridwidth = 1;
       constraints.gridheight = 2;
       constraints.weighty = 1.0;
       PanelDetalle.add (motivosL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 6;
       constraints.gridwidth = 1;
       constraints.gridheight = 2;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       PanelDetalle.add (motivosTA, constraints);
       constraints.weightx = 0.0;
       
       
       constraints.gridx = 0;
       constraints.gridy = 8;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (telefonoCasaL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 8;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (telefonoCasaJ, constraints);
       constraints.weightx = 0.0;
        
              constraints.gridx = 0;
       constraints.gridy = 9;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (telefonoCelularL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 9;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (telefonoCelularJ, constraints);
       constraints.weightx = 0.0;
       
              constraints.gridx = 0;
       constraints.gridy = 10;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (correoL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 10;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (correoJ, constraints);
       constraints.weightx = 0.0;
       
       //PANEL DE LOS BOTONES
       
       constraints.insets.set(30, 0, 10,10 );
       
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 11;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 0.0;
        FlowLayout flowLayout1=new FlowLayout();
	Botones.setLayout(flowLayout1);
        Botones.add(agregarB);
        Botones.add(modificarB);
        Botones.add(eliminarB);
        PanelDetalle.add(Botones, constraints);
       
       
       
       //Boton Buscar
       constraints.anchor=GridBagConstraints.NORTH;
       constraints.insets.set(10, 0, 0,0 );
       constraints.gridx = 2;//Necesita estirarse
       constraints.gridy = 4;
       constraints.gridwidth = 1;
       constraints.gridheight = 0;
       constraints.weightx = 0.0;
	BotonBuscar.setLayout(flowLayout1);
        BotonBuscar.add(buscarB);
        BotonBuscar.setBackground(colorBackGround);
       PanelDetalle.add(BotonBuscar, constraints);
        constraints.insets.set(30, 0, 10,10 );
       
     
      
       //BOTON ATRAS
       
       

       
       constraints.insets.set(30, 0, 10,10 );
       constraints.gridx = 2;//Necesita estirarse
       constraints.gridy = 11;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 0.0;
       
	BotonAtras.setLayout(flowLayout1);
        BotonAtras.add(atrasB);
        
        PanelDetalle.add(BotonAtras, constraints);
        
        cambiarColorPanel(Botones,colorDelPapa);
        cambiarColorPanel(BotonBuscar,colorDelPapa);
        cambiarColorPanel(BotonAtras,colorDelPapa);
        
        PanelDetalle.setVisible(true);
        
        //////////////////////ULTIMAS MOTIFICACIONES PRIMER INCREMENTO
        atrasB.setEnabled(false);
        buscarB.setEnabled(false);
        modificarB.setEnabled(false);
        agregarB.setEnabled(false);
        eliminarB.setEnabled(false);
        fechaJ.setEditable(false);
        horaJ.setEditable(false);
        nombreJ.setEditable(false);
        apellidoJ.setEditable(false);
        cedulaJ.setEditable(false);
        direccionJ.setEditable(false);
        motivosTA.setEditable(false);
        telefonoCasaJ.setEditable(false);
        telefonoCelularJ.setEditable(false);
        correoJ.setEditable(false);
        
        /////////////////////FIN ULTIMAS MOTIFICACIONES PRIMER INCREMENTO
        
        
        /////////////////Para enero
        
  
        
        setCitas();
        dibujarPanelCita(medico);//Dibuja la "libreta" de las citas
  
        
        /////// fin para ENERO
        
  
        //jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo
        jCalendar1.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    try {
                        setCitas();
                    } catch (ClientProtocolException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.text.ParseException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    
    private void disenoLabel(JLabel actual){
        actual.setFont(font);
    }
    
    
    
       private void disenoBotones(JButton actual){
       actual.setBackground(colorBotones);
       font  = font.deriveFont(Font.TYPE1_FONT, 13);
       actual.setFont(font);
       actual.setBorderPainted(false);
       actual.setFocusPainted(false);
        //actual.setContentAreaFilled(false);
        
       actual.setOpaque(false);
   }
    
    public void setCitas() throws IOException, ClientProtocolException, JSONException, ParseException, java.text.ParseException{
        
        JSONArray citasporfecha;
        JSONArray pacienteporid;
        JSONObject paciente;
        jCalendar1.setTodayButtonVisible(false);  
        jCalendar1.setForeground(Color.BLUE);//Pinta todas las fechas en azul, las que estan ocupadas se pintaran de rojo abajo
        jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo
        BorrarTextFields();
        PanelCita.removeAll();
        PanelCita.revalidate();
        PanelCita.repaint();
        PanelCita.setLayout(new GridBagLayout());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        FechaLbl.setText(formato.format(jCalendar1.getDate()));
         dibujarPanelCita(medico);//Dibuja la "libreta" de las citas
               
        
        font=font  = font.deriveFont(Font.BOLD, 17);
        disenoLabel(FechaLbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        PanelCita.add(FechaLbl,gbc);
        //Aqui se busca esta fecha (jCalendar1.getDate()) en la base de datos y se traen las citas 
        citasporfecha = rutasLeer.leer("http://localhost/API_Citas/public/Citas/porFecha/"+formato.format(jCalendar1.getDate()));
        
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.BOTH;
            int total = 0;   
            for (int i = 0;i<citasporfecha.length();i++) {  
                JSONObject obj = (JSONObject)citasporfecha.get(i);
                System.out.println("ENTRE EN EL FOR " + i + ": "+ obj.toString());
                
                for (int j = 0 ; j < citas.length ; j++){
                   
                    if(citas[j].getHora().equals(obj.get("hora"))){
                        pacienteporid = rutasLeer.leer("http://localhost/API_Citas/public/Pacientes/porId/"+obj.get("paciente"));
                        paciente = (JSONObject) pacienteporid.get(0);
                        citas[j].setText(citas[j].getText()+" "+obj.get("paciente")+ " "+ paciente.get("cedula"));
                        total++;
                    }
                }
                //if(citas [i].getHora() == citasporfecha.get("id"))
                System.out.println("voy a agregar las citas");
                //citas [i] = new Citas (i);  
                citas [i].setBorder(BorderFactory.createLineBorder(Color.black));
                citas [i].setOpaque(true); 
                
                citas [i].addMouseListener(new MouseListener() {              
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        Citas seleccion = new Citas();
                        seleccion = (Citas) e.getComponent();
                        System.out.println("Label  clickeado" +  seleccion.getText() );
                        acciones(seleccion);
                    }
                });
                
                
                
                gbc.gridy = i+1;
                PanelCita.add (citas[i],gbc);  


            }  
            if(total==medico.getCitasPorDia()){
                JSONObject fecha = new org.json.JSONObject();
                fecha.put("diasOcupados", formato.format(jCalendar1.getDate()));
                //rutasAdd.add("http://localhost/API_Citas/public/Diasocupados/insertarfecha", fecha);
                jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo 
            }
            jCalendar1.setDate(jCalendar1.getDate());
            jCalendar1.revalidate();
            jCalendar1.repaint();
            PanelCita.revalidate();
            PanelCita.repaint();
    }
    
    
     private void dibujarPanelCita(Medico medico){
        min = medico.getMinutosDeAtencionxPaciente(); 
        int horaInicio;
        int minInicio;
        int horaActual;
        int minActual;
        
        GridBagConstraints gbc = new GridBagConstraints();
       
        horaInicio=medico.getHoraInicio();
        horaActual=medico.getHoraInicio();
        minInicio=medico.getMinInicio();
        minActual=medico.getMinInicio();
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int i = 0;i< medico.getCitasPorDia() ;i++) {
            minActual+=medico.getMinutosDeAtencionxPaciente();
            if(minActual==60){
                horaActual+=1;
                minActual=0;
            }
            gbc.gridy = i+1;
            System.out.print(String.format("%02d",horaInicio));
            citas [i]=new Citas (("" +String.format("%02d",horaInicio)+":" + String.format("%02d",minInicio)  + " - " + String.format("%02d",horaActual) + ":" + String.format("%02d",minActual)),("" +String.format("%02d",horaInicio)+":" + String.format("%02d",minInicio) + ":" +  String.format("%02d",0)));
            citas [i].setBorder(BorderFactory.createLineBorder(Color.black));
            citas [i].setOpaque(true);
            citas [i].addMouseListener(new MouseListener() {              
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        fechaJ.setText(jCalendar1.getDate().toString());
                        fechaJ.setEnabled(true);
                        acciones(null);
                    }
                });
            PanelCita.add (citas[i] ,gbc);
            System.out.print(i);
            horaInicio=horaActual;
            minInicio=minActual;
            }
        
    }
    
    
    

        public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==agregarB) {
            try {
                
                //Cita
                JSONObject cita=new JSONObject();
                cita.put("fecha",fechaJ.getText());
                cita.put("hora",horaJ.getText());
                cita.put("paciente",3);
                cita.put("medicos","1");
                cita.put("tratamiento","tratamiento");
                cita.put("diagnostico","diagnostico");
                cita.put("motivo", motivosTA.getText());
                System.out.print(cita);
                
                //Paciente
                JSONObject paciente=new JSONObject();
                paciente.put("cedula",cedulaJ.getText());
                paciente.put("nombre",nombreJ.getText());
                paciente.put("apellido",apellidoJ.getText());
                paciente.put("direccion",direccionJ.getText());
                paciente.put("correo",correoJ.getText());
                paciente.put("tlfncasa",telefonoCasaJ.getText());
                paciente.put("tlfncelular",telefonoCelularJ.getText());
                System.out.print(paciente);
                //rutasAdd.add("http://localhost/API_Citas/public/Pacientes/insertarPaciente", paciente);
                rutasAdd.add("http://localhost/API_Citas/public/Citas/insertarCita", cita);
                
                setCitas();
            } catch (IOException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
            
        }
        
        if (e.getSource()==modificarB) {
            
            return;
        }
        if (e.getSource()==eliminarB) {
            
            return;
        }
        if (e.getSource()==atrasB) {
           
 
            
            
        }
        if (e.getSource()==buscarB) {
            
            
            
             String[] opciones = {"Aceptar" };
            int opcion = JOptionPane.showOptionDialog(
                               null                             //componente
                             , "Cedula no pertenece a ningun paciente registrado"            // Mensaje
                             , "Paciente no encontrado"         // Titulo en la barra del cuadro
                             , JOptionPane.DEFAULT_OPTION       // Tipo de opciones
                             , JOptionPane.WARNING_MESSAGE  // Tipo de mensaje (icono)
                             , null                             // Icono (ninguno)
                             , opciones                         // Opciones personalizadas
                             , null                             // Opcion por defecto
                           );
            
        }
        
        
        
        
    }
        
        
        
    
        
    private void BorrarTextFields(){
        
            fechaJ.setText("");
            horaJ.setText("");
            nombreJ.setText("");
            apellidoJ.setText("");
            cedulaJ.setText("");
            direccionJ.setText("");
            telefonoCasaJ.setText("");
            telefonoCelularJ.setText("");
            correoJ.setText("");
            motivosTA.setText("");
        
    }
   
     private void acciones(Citas cita){
        
        BorrarTextFields();
        if(cita==null){
            atrasB.setEnabled(true);
            buscarB.setEnabled(true);
            modificarB.setEnabled(false);
            agregarB.setEnabled(true);
            eliminarB.setEnabled(false);
            fechaJ.setEditable(false);
            horaJ.setEditable(false);
            nombreJ.setEditable(true);
            apellidoJ.setEditable(true);
            cedulaJ.setEditable(true);
            direccionJ.setEditable(true);
            telefonoCasaJ.setEditable(true);
            telefonoCelularJ.setEditable(true);
            correoJ.setEditable(true); 
            motivosTA.setEditable(true);
            
        }else{
            atrasB.setEnabled(false);
            buscarB.setEnabled(false);
            modificarB.setEnabled(false);
            agregarB.setEnabled(false);
            eliminarB.setEnabled(false);
            fechaJ.setText(cita.Dia);
            horaJ.setText(cita.Hora);
            nombreJ.setText(cita.Paciente);
            cedulaJ.setText(cita.Cedula);
            fechaJ.setEditable(false);
            horaJ.setEditable(false);
            nombreJ.setEditable(false);
            apellidoJ.setEditable(false);
            cedulaJ.setEditable(false);
            direccionJ.setEditable(false);
            telefonoCasaJ.setEditable(false);
            telefonoCelularJ.setEditable(false);
            correoJ.setEditable(false);  
            motivosTA.setEditable(false);
        }
         
         
         
         
        
    }
    
    
    private void cambiarColorPanel(JPanel panel, Color color){
        
        panel.setBackground(color);
        
    }
            
    
    private int hex( String color_hex )
    {
        return Integer.parseInt(color_hex,  16 );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        PanelCalendar = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        PanelCita = new javax.swing.JPanel();
        FechaLbl = new javax.swing.JLabel();
        PanelDetalle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 500));
        setSize(new java.awt.Dimension(0, 0));

        PanelCalendar.setBorder(javax.swing.BorderFactory.createTitledBorder("Calendario"));
        PanelCalendar.setLayout(new java.awt.BorderLayout());

        jCalendar1.setMaxSelectableDate(new java.util.Date(2524627865000L));
        jCalendar1.setMinSelectableDate(new java.util.Date(1420090265000L));
        jCalendar1.setSundayForeground(new java.awt.Color(164, 0, 6));
        jCalendar1.setTodayButtonText("");
        jCalendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCalendar1MouseClicked(evt);
            }
        });
        PanelCalendar.add(jCalendar1, java.awt.BorderLayout.CENTER);

        PanelCita.setBorder(javax.swing.BorderFactory.createTitledBorder("Citas"));
        PanelCita.setAlignmentX(0.0F);
        PanelCita.setAlignmentY(0.0F);
        PanelCita.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PanelCita.setLayout(new java.awt.GridBagLayout());

        FechaLbl.setBackground(new java.awt.Color(255, 255, 255));
        FechaLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FechaLbl.setForeground(new java.awt.Color(204, 0, 0));
        FechaLbl.setText("08/Diciembre/2015");
        PanelCita.add(FechaLbl, new java.awt.GridBagConstraints());

        PanelDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        javax.swing.GroupLayout PanelDetalleLayout = new javax.swing.GroupLayout(PanelDetalle);
        PanelDetalle.setLayout(PanelDetalleLayout);
        PanelDetalleLayout.setHorizontalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        PanelDetalleLayout.setVerticalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(PanelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(PanelCita, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236)
                .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelCita, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCalendar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCalendar1MouseClicked
      
    }//GEN-LAST:event_jCalendar1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameCita().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FechaLbl;
    private javax.swing.JPanel PanelCalendar;
    private javax.swing.JPanel PanelCita;
    private javax.swing.JPanel PanelDetalle;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JOptionPane jOptionPane1;
    // End of variables declaration//GEN-END:variables
}
