package Helpers;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Helpers {
	
	// Método para concatenar los elementos de un array con un delimitador
    public static String implode(String[] array, String delimitador) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            resultado.append(array[i]);
            if (i < array.length - 1) {
                resultado.append(delimitador);
            }
        }
        return resultado.toString();
    }
    
    
    //Método para eliminar componentes todos los componentes de un GridBagLayout, menos el que nosotros indiquemos (la cabecera del layout)
    public static void removeAllComponents(JPanel panel, int row, int column) {
    	
	    // Obtener el diseño del panel
	    GridBagLayout layout = (GridBagLayout) panel.getLayout();
	    
	    // Obtener la información de restricciones del componente en la posición especificada
	    Component[] components = panel.getComponents();
	    for (Component component : components) {
	        GridBagConstraints gbc = layout.getConstraints(component);
	        
	        System.out.println(gbc.gridx+"//"+gbc.gridy);
	        
	        if ((gbc.gridx!=column)||(gbc.gridy != row)) {
	        	
	        	System.out.println(component);	        	
	            panel.remove(component); // Eliminar el componente
	           
	           
	        }
	    }
	    
	    panel.revalidate(); // Actualizar el diseño del panel
        panel.repaint(); // Repintar el panel
	}
   
    
    // Método para obtener todos los componentes de un tipo específico en un GridBagLayout
    public static <T extends Component> ArrayList<T> getAllComponentsOfType(JPanel panel, Class<T> type) {
        ArrayList<T> componentsOfType = new ArrayList<>();
        
        // Obtener el diseño del panel
        GridBagLayout layout = (GridBagLayout) panel.getLayout();
        
        // Obtener todos los componentes del panel
        Component[] components = panel.getComponents();
        for (Component component : components) {
            // Verificar si el componente es del tipo especificado
            if (type.isInstance(component)) {
                componentsOfType.add(type.cast(component));
            }
        }
        
        return componentsOfType;
    }

}
