package sample.banco;

public class Main {

  public static void main(String[] args) throws Exception {
    Class.forName("org.h2.Driver");
    System.out.println("Driver registrado!");

    Operations ops = new Operations();
    ops.salva("Pessoa "+System.currentTimeMillis());
    ops.lista();

  }
}