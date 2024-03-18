class Automovil {
    private String Marca;
    private int Modelo;
    private String Linea;
    private String Placa;
    private double Precio;

    public Automovil(String marca, int modelo, String linea, String placa, double precio) {
        Marca = marca;
        Modelo = modelo;
        Linea = linea;
        Placa = placa;
        Precio = precio;
    }

    @Override
    public String toString() {
        return "Automovil [Marca=" + Marca + ", Modelo=" + Modelo + ", Linea=" + Linea + ", Placa=" + Placa
                + ", Precio=" + Precio + "]";
    }

}