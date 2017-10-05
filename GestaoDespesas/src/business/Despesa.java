package business;

import java.util.Date;
import java.util.HashSet;

public class Despesa {
  private int id;
  private float valor;
  private Date prazo;
  private Pagamento pagamento;
  private String nome;
  private String descricao;
  private int estado;
  private String tipo;
  private Date data_recorrente;
  
  private int entidade;
  private int referencia;


  public Despesa (int id, float valor, Date prazo, String nome, String descricao, int estado, String tipo, Date data_rec, int a, int b) {
    this.id = id;
    this.valor = valor;
    this.prazo = prazo;
    this.pagamento = null;
    this.nome = nome;
    this.descricao = descricao;
  
    this.estado = estado;
    this.tipo = tipo;
    this.data_recorrente = data_rec;
    this.entidade = a;
    this.referencia  =b;
  }
  
    public Despesa (int id, float valor, Date prazo, String nome, String descricao, int estado, String tipo, Date data_rec) {
    this.id = id;
    this.valor = valor;
    this.prazo = prazo;
    this.pagamento = null;
    this.nome = nome;
    this.descricao = descricao;
    this.estado = estado;
    this.tipo = tipo;
    this.data_recorrente = data_rec;
    this.entidade = 0;
    this.referencia  =0;
  }
 
    public Despesa(Despesa a){
        this.id = a.getId();
    this.valor = a.getValor();
    this.prazo = a.getPrazo();
    this.pagamento = a.getPagamento();
    this.nome = a.getNome();
    this.descricao = a.getDescricao();
    
    this.estado = a.getEstado();
    this.tipo = a.getTipo();
    this.data_recorrente = a.getData_Recorrente();
    this.entidade = a.getEntidade();
    this.referencia  =a.getReferencia(); 
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public int getReferencia() {
        return referencia;
    }

    public int getEntidade(){
        return entidade;
    }
    

  
    public String getTipo() {
        return tipo;
    }
    
    public int getEstado() {
        return estado;
    }
    
    public Date getData_Recorrente() {
        return data_recorrente;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public void setData_Recorrente(Date data_recorrente) {
        this.data_recorrente = data_recorrente;
    }
  
    public void setReferencia(int referencia){
        this.referencia = referencia;
    }

    public void setEntidade(int entidade){
            this.entidade = entidade;
    }
        
 // abstract public int getEntidade();

  //abstract public int getReferencia();

    public Despesa clone(){
        return new Despesa(this);
    }
}
