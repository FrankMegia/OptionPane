package emergente;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Esta clase trata de hacer un simil de la clase JOptionPane de Swing para JavaFX.
 * <p>Sólo estan implementados los métodos showConfirmDialog, showInputDialog, showMessageDialog y showOptionDialog, así como sus sobrecargas.
 * @author FrankMegia
 */
public class OptionPane {

	/**
	 * Muestra una ventana emergente, con el mensaje pasado como parámetro.
	 * @param parentComponent {@link Stage} que llama a la ventana.
	 * @param message Mensaje que se mostrará en la ventana.
	 */
	public static void showMessageDialog(Stage parentComponent, Object message) {
		
		showMessageDialog(parentComponent, message, "Mensaje", OptionPane.INFORMATION_MESSAGE, null);
	}
	
	/**
	 * Muestra una ventana emergente con mensaje, título y tipo de mensaje como parámetros.
	 * @param parentComponent {@link Stage} que llama a la ventana.
	 * @param message Mensaje que se mostrará en la ventana.
	 * @param title Título de la ventana.
	 * @param messageType El tipo de mensaje que va a ser mostrado: ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE o PLAIN_MESSAGE.
	 */
	public static void showMessageDialog(Stage parentComponent, Object message, String title, int messageType) {
		
		showMessageDialog(parentComponent, message, title, messageType, null);
	}
	
	/**
	 * Muestra una ventana emergente con mensaje, título y, tipo de mensaje ó icono, como parámetros.
	 * @param parentComponent {@link Stage} que llama a la ventana.
	 * @param message Mensaje que se mostrará en la ventana.
	 * @param title Título de la ventana.
	 * @param messageType El tipo de mensaje que va a ser mostrado: ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE o PLAIN_MESSAGE.
	 * @param icon Icono que se desea que se muestre en lugar del de tipo de mensaje.
	 */
	public static void showMessageDialog(Stage parentComponent, Object message, String title, int messageType, ImageView icon) {
		
		if(parentComponent != null) {
			hasParent = true;
			parent = parentComponent;
		}else hasParent = false;
		
		crearStage(title);

		Label icono = new Label();
		cargaIcono(icono,icon, messageType);
		
		grid = creaRejilla(icono, message);
		
		Button boton = new Button("Aceptar");
		boton.setOnAction(e->miStage.close());
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(30);
		pane.getChildren().addAll(grid, boton);
		VBox.setMargin(boton, new Insets(0,0,15,0));
			
		Scene miScene = new Scene(pane);
		
		miStage.setScene(miScene);
		miStage.setResizable(false);
		miStage.show();
		double anchoScene = miScene.getWidth();

		miStage.hide();
		
		double anchoTitle = title.length()*8;
		
		if(anchoScene < 300) {
			if(anchoTitle < 300) miStage.setWidth(300);
			else miStage.setWidth(anchoTitle);

		}
		else miStage.sizeToScene();

		if(!hasParent) {
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			double centerX = size.getWidth()/2;
			double centerY = size.getHeight()/2;
			miStage.setX(centerX - (miStage.getWidth()/2));
			miStage.setY(centerY - (miStage.getHeight()/2));
			
		}
		miStage.showAndWait();
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que introduzca un valor.
	 * <p>La ventana aparecerá en el centro de la pantalla.
	 * @param message Mensaje a visualizar
	 * @return {@link String} con el valor introducido. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static String showInputDialog(Object message) {
		
		Object obj = showInputDialog(null,message, null, OptionPane.QUESTION_MESSAGE, null, null, null);
		return (String)obj;
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que introduzca un valor.
	 * <p>La ventana aparecerá en el centro de la pantalla.
	 * @param message Mensaje a visualizar
	 * @param initialSelectionValue Valor sugerido por defecto
	 * @return {@link String} con el valor introducido. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static String showInputDialog(Object message, String initialSelectionValue) {
		
		Object obj = showInputDialog(null,message, null, OptionPane.QUESTION_MESSAGE, null, null, initialSelectionValue);
		return (String)obj;
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que introduzca un valor.
	 * <p>La ventana aparecerá colocada respecto al {@code parentComponent}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje a visualizar
	 * @return {@link String} con el valor introducido. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static String showInputDialog(Stage parentComponent, Object message) {
		
		Object obj = showInputDialog(parentComponent,message, null, OptionPane.QUESTION_MESSAGE, null, null, null);
		return (String)obj;
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que introduzca un valor.
	 * <p>La ventana aparecerá colocada respecto al {@code parentComponent}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje a visualizar
	 * @param initialSelectionValue Valor sugerido por defecto
	 * @return {@link String} con el valor introducido. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static String showInputDialog(Stage parentComponent, Object message, String initialSelectionValue) {
		
		Object obj = showInputDialog(parentComponent,message, null, OptionPane.QUESTION_MESSAGE, null, null, initialSelectionValue);
		return (String)obj;
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que introduzca un valor.
	 * <p>La ventana aparecerá colocada respecto al {@code parentComponent}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje a visualizar
	 * @param title Título que tendrá la ventana
	 * @param messageType Tipo de mensaje a mostrar: ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE
	 * @return {@link String} con el valor introducido. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static String showInputDialog(Stage parentComponent, Object message, String title, int messageType) {
		
		Object obj = showInputDialog(parentComponent,message, title, messageType, null, null, null); 
		return (String)obj;
	}
	
	/**
	 * Muestra una ventana solicitando al usuario que seleccione un valor.
	 * <p>La ventana aparecerá colocada respecto al {@code parentComponent}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje a visualizar
	 * @param title Título que tendrá la ventana
	 * @param messageType Tipo de mensaje a mostrar: ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE
	 * @param icon Icono que será mostrado en la ventana
	 * @param selectionValues Conjunto de posibles opciones a elegir
	 * @param initialSelectionValue Opción a elegir por defecto
	 * @return {@link Object} con el valor seleccionado. Será {@code null} si se cierra o se cancela la ventana
	 */
	public static Object showInputDialog(Stage parentComponent, Object message, String title, int messageType, ImageView icon, Object[] selectionValues, Object initialSelectionValue) {
		
		if(parentComponent != null) {
			hasParent = true;
			parent = parentComponent;
		}else hasParent = false;
		
		iconoTipoMensaje = new Image(OptionPane.class.getResourceAsStream("iconos/question.jpg"));
		
		Label icono = new Label();
		cargaIcono(icono,icon, messageType);
		
		grid = creaRejilla(icono, message);
		
		entrada = new TextField();
		entrada.setMinWidth(230);
		
		entradaCombo = new ComboBox<Object>();
		entradaCombo.setMinWidth(230);
		entradaCombo.setEditable(false);
		
		Button btnAceptar = new Button("Aceptar");
		btnAceptar.setOnAction(e->resultadoEntrada = arrojaResultado());
		
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setOnAction(e->resultadoEntrada = arrojaResultadoNulo());
		
		HBox botonera = new HBox();
		botonera.getChildren().addAll(btnAceptar, btnCancelar);
		botonera.setAlignment(Pos.CENTER);
		botonera.setSpacing(15);
		
		VBox cuerpo = new VBox();
		if(selectionValues != null) {
			
			for(Object obj : selectionValues) {
				if(obj instanceof String) {
					entradaCombo.getItems().add(obj);
				}
				else if(obj instanceof Image) {
					Label label = new Label();
					label.setGraphic(new ImageView((Image)obj));
					entradaCombo.getItems().add(label);
				}
				else if(obj instanceof Node) {
					entradaCombo.getItems().add(obj);
				}
				else {
					entradaCombo.getItems().add(obj.toString());
				}
				
				if(initialSelectionValue != null && obj.equals(initialSelectionValue)) {
					entradaCombo.setValue(initialSelectionValue);
				}
					
			}
			
			cuerpo.getChildren().addAll(grid, entradaCombo);
		}else cuerpo.getChildren().addAll(grid, entrada);
		
		cuerpo.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(grid, new Insets(10,10,2,0));
		VBox.setMargin(entrada, new Insets(0,10,0,0));
		VBox.setMargin(entradaCombo, new Insets(0,10,0,0));
		
		HBox conjunto = new HBox();
		conjunto.getChildren().addAll(icono, cuerpo);
		conjunto.setAlignment(Pos.CENTER_LEFT);
		HBox.setMargin(icono, new Insets(10,10,0,10));
		
		VBox total = new VBox();
		total.getChildren().addAll(conjunto, botonera);
		VBox.setMargin(botonera, new Insets(10,0,10,0));
		
		String titulo = title != null ? title : "Entrada";  
		
		crearStage(titulo);
		
		Scene miScene = new Scene(total);
		
		miStage.setScene(miScene);
		
		miStage.show();
		double anchoScene = miScene.getWidth();
		
		if(initialSelectionValue != null) entrada.setText((String)initialSelectionValue);
		else entrada.setText("");
		
		miStage.hide();
		
		// Cálculos necesarios para que todo aparezca en la ventana, incluido el título por largo que sea
		double anchoTitle = title.length()*8;
		
		if(anchoScene < 310) {

			if(anchoTitle < 310) miStage.setWidth(310);
			else {
				
				miStage.setWidth(anchoTitle);
				entradaCombo.setMinWidth(anchoTitle - 80);
			}
			
		}
		else miStage.sizeToScene();
		
		if(!hasParent) {
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			double centerX = size.getWidth()/2;
			double centerY = size.getHeight()/2;
			miStage.setX(centerX - (miStage.getWidth()/2));
			miStage.setY(centerY - (miStage.getHeight()/2));
		}
		miStage.setOnCloseRequest(e->resultadoEntrada = null);
		miStage.showAndWait();
		
		return resultadoEntrada;
	}

	/**
	 *Muestra una ventana de diálogo, con el icono especificado, donde la elección inicial es determinada por el<br/>
	 *parámetro {@code initValue} y las posibles elecciones son determinadas por el parámentro {@code optionType}.
	 *<p>
	 *Si {@code optionType} es YES_NO_OPTION o YES_NO_CANCEL_OPTION y el parámetro {@code opciones} es {@code null},<br/>
	 *entonces las opciones serán proporcionadas para la apariencia.</p>
	 *<p>
	 *El parámetro {@code messageType} es usado primariamente para proporcionar el icono por defecto a mostrar.</p>
	 * @param parentComponent {@link Stage} que llama a la ventana.
	 * @param message Mensaje que se mostrará en la ventana.
	 * @param title Título que mostrará la ventana.
	 * @param optionType Un entero que designa las opciones disponibles en la ventana: DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION o OK_CANCEL_OPTION
	 * @param messageType Un entero que designa el tipo de mensaje, estableciendo el icono por defecto a mostrar: ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE o PLAIN_MESSAGE
	 * @param icon El icono a mostrar en la ventana.
	 * @param opciones Conjunto de posibles opciones que el usuario puede elegir. Si este parámetro es {@code null}, entonces se atenderá al parámetro {@code optionType}.
	 * @param initValue Representa la elección por defecto cuando se {@code opciones} es usado. Puede ser {@code null}.
	 * @return Un int indicando la opción elegida por el usuario, o CLOSED_OPTION si el usuario cerró la ventana.
	 */
	public static int showOptionDialog(Stage parentComponent, Object message, String title, 
			int optionType, int messageType, ImageView icon, Object[] opciones, Object initValue) {
		
		if(parentComponent != null) {
			hasParent = true;
			parent = parentComponent;
		}else hasParent = false;
		
		if(opciones == null) {
			switch(optionType) {
				case DEFAULT_OPTION:
					botones = new String[]{"Aceptar"};
				break;
				case YES_NO_OPTION:
					botones = new String[]{"Si","No"};
				break;
				case YES_NO_CANCEL_OPTION:
					botones = new String[]{"Si", "No", "Cancelar"};
				break;
				case OK_CANCEL_OPTION:
					botones = new String[]{"Aceptar", "Cancelar"};
				break;
			}
		}
		
		Label icono = new Label();
		cargaIcono(icono,icon, messageType);
		
		grid = creaRejilla(icono, message);
		
		HBox hboxBotones = new HBox();
		hboxBotones.setAlignment(Pos.CENTER);
		hboxBotones.setSpacing(10);
		
		count = 0;
		List<Button> listaBotones = new ArrayList<Button>();
		if(opciones == null) { //No se han pasado opciones y se han tomado las de Look and Feel
			
			for(String s: botones) {
				
				Button boton = new Button();
				boton.setText(s);
				if(s.equals("Cancelar")) count = 2;
				boton.setOnAction(new EventHandler<ActionEvent>() {
					
					private int contadorActual = count;
					
					@Override
					public void handle(ActionEvent event) {
						resultado = contadorActual;
						arrojaResultado(resultado);
					}
				});
				listaBotones.add(boton);
				count++;
			
				hboxBotones.getChildren().add(boton);
			}
		}
		else {
			for(Object obj : opciones) {
				Button boton = new Button();
				
				if(obj instanceof String) boton.setText((String)obj);
				else if(obj instanceof Image) boton.setGraphic(new ImageView((Image)obj));
				else if(obj instanceof Node) boton.setGraphic((Node)obj);
				else boton.setText(obj.toString());
				
				boton.setOnAction(new EventHandler<ActionEvent>() {
					
					private int contadorActual = count;
					
					@Override
					public void handle(ActionEvent event) {
						resultado = contadorActual;
						arrojaResultado(resultado);
					}
				});
				listaBotones.add(boton);
				count++;
			
				hboxBotones.getChildren().add(boton);
			}
		}
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(15);
		pane.getChildren().addAll(grid, hboxBotones);
		VBox.setMargin(grid, new Insets(15,15,0,15));
		VBox.setMargin(hboxBotones, new Insets(0,15,15,15));
		
		crearStage(title);
		
		Scene miScene = new Scene(pane);
		
		miStage.setScene(miScene);
		for(Button b: listaBotones) {
			if(initValue != null && opciones != null && initValue.equals(b.getText().toString())) {
				b.requestFocus(); 
			}
		}
		
		miStage.setResizable(false);
		miStage.show();
		double anchoScene = miScene.getWidth();
		
		/*
		 * Operaciones necesarias para que el Scene de cabida a los botones sin comprimirlos
		 */
		sumaAnchoBotones=0;
		for(Button b : listaBotones) {
			sumaAnchoBotones+=b.getWidth()+10;
		}

		miStage.hide();
		
		double anchoTitle = title.length()*8;
		
		if(anchoScene < sumaAnchoBotones) {
			if(sumaAnchoBotones < anchoTitle) miStage.setWidth(anchoTitle);
			else if(sumaAnchoBotones < 300) miStage.setWidth(300);
			else miStage.setWidth(sumaAnchoBotones);

		}else if(anchoScene < 300) {
			if(anchoTitle < 300) miStage.setWidth(300);
			else miStage.setWidth(anchoTitle);

		}
		else miStage.sizeToScene();
		
		if(!hasParent) {
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			double centerX = size.getWidth()/2;
			double centerY = size.getHeight()/2;
			miStage.setX(centerX - (miStage.getWidth()/2));
			miStage.setY(centerY - (miStage.getHeight()/2));
		}

		miStage.setOnCloseRequest(e->resultado=CLOSED_OPTION);
		miStage.showAndWait();
		
		return resultado;
	}

	/**
	 * Muestra una ventana respecto al {@code parentComponent} con las opciones "Si", "No" y "Cancelar".
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje que aparecera en la ventana
	 * @return Un int indicando la opción seleccionada por el usuario
	 */
	public static int showConfirmDialog(Stage parentComponent, Object message) {
		
		return showConfirmDialog(parentComponent, message, "Seleccione una Opción", YES_NO_CANCEL_OPTION, QUESTION_MESSAGE,null);
	}
	
	/**
	 * Muestra una ventana respecto al {@code parentComponent} con las opciones determinadas por {@code optionType}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje que aparecera en la ventana
	 * @param title Título que tendrá la ventana
	 * @param optionType Un int que designa las posibles opciones: YES_NO_OPTION, YES_NO_CANCEL_OPTION o OK_CANCEL_OPTION
	 * @return Un int indicando la opción seleccionada por el usuario
	 */
	public static int showConfirmDialog(Stage parentComponent, Object message, String title, int optionType) {
		
		return showConfirmDialog(parentComponent, message, title, optionType, QUESTION_MESSAGE,null);
	}
	
	/**
	 * Muestra una ventana respecto al {@code parentComponent} con las opciones determinadas por {@code optionType}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje que aparecera en la ventana
	 * @param title Título que tendrá la ventana
	 * @param optionType Un int que designa las posibles opciones: YES_NO_OPTION, YES_NO_CANCEL_OPTION o OK_CANCEL_OPTION
	 * @param messageType Un int indicando el tipo de mensaje(icono) de la ventana:  ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE o PLAIN_MESSAGE
	 * @return Un int indicando la opción seleccionada por el usuario
	 */
	public static int showConfirmDialog(Stage parentComponent, Object message, String title, int optionType, int messageType) {
		
		return showConfirmDialog(parentComponent, message, title, optionType, messageType ,null);
	}
	
	/**
	 * Muestra una ventana respecto al {@code parentComponent} con las opciones determinadas por {@code optionType}.
	 * @param parentComponent {@link Stage} que llama a la ventana
	 * @param message Mensaje que aparecera en la ventana
	 * @param title Título que tendrá la ventana
	 * @param optionType Un int que designa las posibles opciones: YES_NO_OPTION, YES_NO_CANCEL_OPTION o OK_CANCEL_OPTION
	 * @param messageType Un int indicando el tipo de mensaje(icono) primariamente de la ventana:  ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE o PLAIN_MESSAGE
	 * @param icon Icono que se mostrará en la ventana, reemplazando el proporcionado por {@code messageType}
	 * @return Un int indicando la opción seleccionada por el usuario
	 */
	public static int showConfirmDialog(Stage parentComponent, Object message, String title, int optionType, int messageType, ImageView icon) {
		
		return showOptionDialog(parentComponent, message, title, optionType, messageType,icon,null,null);
	}
	
	private static void cargaIcono(Label icono, ImageView icon, int messageType) {
		if(icon == null) 
		{
			switch(messageType) {
				case ERROR_MESSAGE:
					iconoTipoMensaje = new Image(OptionPane.class.getResourceAsStream("iconos/error.jpg"));
					break;
				case INFORMATION_MESSAGE:
					iconoTipoMensaje = new Image(OptionPane.class.getResourceAsStream("iconos/informacion.png"));
					break;
				case WARNING_MESSAGE:
					iconoTipoMensaje = new Image(OptionPane.class.getResourceAsStream("iconos/warning.jpg"));
					break;
				case QUESTION_MESSAGE:
					iconoTipoMensaje = new Image(OptionPane.class.getResourceAsStream("iconos/question.jpg"));
					break;
				case PLAIN_MESSAGE:
					iconoTipoMensaje = null;
					break;
			}
			
			icono.setGraphic(new ImageView(iconoTipoMensaje));
		}else {
			icono.setGraphic(icon);
		}
		
	}

	private static GridPane creaRejilla(Label icono, Object message) {
		
		GridPane rejilla = new GridPane();
		rejilla.add(icono, 0, 0);
		GridPane.setMargin(icono, new Insets(10,20,10,10));
		GridPane.setValignment(icono, VPos.TOP);
		
		if(message.getClass().isArray()) {
			Object[] matriz = (Object[])message;
			
			VBox caja = new VBox();
			caja.setPadding(new Insets(15,0,0,0));
			
			for(Object obj : matriz) 
			{
				if(obj instanceof String) {
					Label mensaje = new Label((String)obj);
					caja.getChildren().add(mensaje);
				}
				else if(obj instanceof Image) {
					Label mensaje = new Label();
					mensaje.setGraphic(new ImageView((Image)obj));
					caja.getChildren().add(mensaje);
				}
				else if(obj instanceof Pane) {
					caja.getChildren().add((Node)obj);
				}
				else {
					Label mensaje = new Label(obj.toString());
					caja.getChildren().add(mensaje);
				}
			}
			rejilla.add(caja, 1, 0);
		}else {

			if(message instanceof String) {
				Label mensaje = new Label((String)message);
				rejilla.add(mensaje, 1, 0);
			}
			else if(message instanceof Image)
			{
				Label mensaje = new Label();
				mensaje.setGraphic(new ImageView((Image)message));
				rejilla.add(mensaje, 1, 0);
				GridPane.setMargin(mensaje, new Insets(10,20,10,10));
				GridPane.setHalignment(mensaje,HPos.CENTER);
			}
			else if(message instanceof Pane) {
				rejilla.add((Node)message, 1, 0);
				GridPane.setMargin((Node)message, new Insets(10,20,10,10));
				GridPane.setHalignment((Node)message,HPos.CENTER);
			}
			else {
				Label mensaje = new Label(message.toString());
				rejilla.add(mensaje, 1, 0);
			}
		}
		
		return rejilla;
	}
	
	private static int arrojaResultado(int resultado2) {
		miStage.close();
		return resultado2;
		
	}
	
	private static Object arrojaResultado() {
		
		Object valor = null;
		if(entradaCombo.getValue() != null) valor = entradaCombo.getValue();
		else valor = entrada.getText();
		miStage.close();
		return valor;
	}
	
	private static String arrojaResultadoNulo() {
		miStage.close();
		return null;
	}
	
	private static void crearStage(String titulo) {
		miStage = new Stage();
		
		miStage.initModality(Modality.APPLICATION_MODAL);
		miStage.setTitle(titulo);
		miStage.initStyle(StageStyle.UTILITY);
		//miStage.setHeight(140);
		
		if(hasParent) {
			double posX = parent.getX()+20;
			double posY = parent.getY()+50;
			
			miStage.setX(posX);
			miStage.setY(posY);
		}
	}

	private static Stage miStage;
	private static Stage parent;
	private static boolean hasParent;
	private static GridPane grid;
	
	private static Image iconoTipoMensaje;
	private static int resultado = -2;
	private static int count;
	private static String[] botones;
	private static double sumaAnchoBotones;
	
	private static TextField entrada;
	private static ComboBox<Object> entradaCombo;
	private static Object resultadoEntrada;
	
	/**
	 * Usado para mensajes de error.
	 */
	public static final int ERROR_MESSAGE = 0;
	
	/**
	 * Usado para mensajes de información.
	 */
	public static final int INFORMATION_MESSAGE = 1;
	
	/**
	 * Usado para mensajes de advertencia.
	 */
	public static final int WARNING_MESSAGE = 2;
	
	/**
	 * Usado para mensajes de consulta.
	 */
	public static final int QUESTION_MESSAGE = 3;
	
	/**
	 * Ningún icono es usado.
	 */
	public static final int PLAIN_MESSAGE = -1;
	
	/**
	 * Tipo de mensaje que sólo mostrará la opción "Aceptar".
	 */
	public static final int DEFAULT_OPTION = -1;
	
	/**
	 * Tipo de mensaje que mostrará las opciones "Si" y "No".
	 */
	public static final int YES_NO_OPTION = 0;
	
	/**
	 * Tipo de mensaje que mostrará las opciones "Si", "No" y "Cancelar".
	 */
	public static final int YES_NO_CANCEL_OPTION = 1;
	
	/**
	 * Tipo de mensaje que mostrará las opciones "Aceptar" y "Cancelar".
	 */
	public static final int OK_CANCEL_OPTION = 2;
	
	/**
	 * Valor devuelto en caso de que el usuario cierre la ventana.
	 */
	public static final int CLOSED_OPTION = -1;
	
	/**
	 * Valor devuelto para la opción "Aceptar".
	 */
	public static final int OK_OPTION = 0;
	
	/**
	 * Valor devuelto para la opción "Si".
	 */
	public static final int YES_OPTION = 0;
	
	/**
	 * Valor devuelto para la opción "No".
	 */
	public static final int NO_OPTION = 1;
	
	/**
	 * Valor devuelto para la opción "Cancelar".
	 */
	public static final int CANCEL_OPTION = 2;
}
