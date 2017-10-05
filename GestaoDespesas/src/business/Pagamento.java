package business;

import java.util.Date;
import java.util.HashSet;

public class Pagamento {
  private Date data;
 

  public Pagamento (Date data) {
    this.data = data;

  }

    public Pagamento() {
        data = new Date();
    }

  
  
  public Date getData() {
    return this.data;
  }



  public void setData(Date dat) {
    this.data = dat;
  }

}
