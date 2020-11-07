package sample.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface TheOp {
  void run(Connection con) throws Exception;
}

public class Operations {

  private static final String JDBC_URL = "jdbc:h2:file:~/projeto-banco";
  private static final String CREATE_TABLE = "create table if not exists pessoa(id integer not null "
      + "primary key auto_increment, nome varchar(255) not null)";

  public Operations() throws Exception {
    opera(con -> {
      con.prepareStatement(CREATE_TABLE).executeUpdate();
      con.commit();
    });
  }

  private void opera(TheOp op) throws Exception {
    try (Connection con = DriverManager.getConnection(JDBC_URL, "sa", "")) {
      op.run(con);
    }
  }

  public void salva(String pessoa) throws Exception {
    opera(con -> {
      PreparedStatement prep = con.prepareStatement("insert into pessoa(nome) values (?)");
      prep.setString(1, pessoa);
      prep.executeUpdate();
      con.commit();
    });
  }

  public void lista() throws Exception {
    opera(con -> {
      ResultSet res = con.prepareStatement("select * from pessoa").executeQuery();
      while(res.next()) System.out.println(res.getString("nome"));
    });
  }

}
