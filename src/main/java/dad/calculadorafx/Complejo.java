package dad.calculadorafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

	public class Complejo {
	    private final DoubleProperty real = new SimpleDoubleProperty();
	    private final DoubleProperty imaginario = new SimpleDoubleProperty();

	    public Complejo() {
	    }

	    public Complejo(double real, double imaginario) {
	        this.real.set(real);
	        this.imaginario.set(imaginario);
	    }

	    public double getReal() {
	        return real.get();
	    }

	    public DoubleProperty realProperty() {
	        return real;
	    }

	    public void setReal(double real) {
	        this.real.set(real);
	    }

	    public double getImaginario() {
	        return imaginario.get();
	    }

	    public DoubleProperty imaginarioProperty() {
	        return imaginario;
	    }

	    public void setImaginario(double imaginario) {
	        this.imaginario.set(imaginario);
	    }
	}
