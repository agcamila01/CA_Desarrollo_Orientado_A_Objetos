
public class ProductoFisico extends Producto {
    private int costoEnvio;

    public ProductoFisico(String nombre, int precioBase, int stock, int costoEnvio){
        super(nombre, precioBase, stock);
        this.costoEnvio = costoEnvio;
    }

    public int getCostoEnvio() {
        return this.costoEnvio;
    }

    @Override
    public int calcularPrecioFinal() {
        return this.getPrecioBase() + this.getCostoEnvio();
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + " Costo de envío: " + this.costoEnvio;
    }
}