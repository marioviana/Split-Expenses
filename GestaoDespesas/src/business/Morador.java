package business;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Morador {
  // Atributos
  
  private String mail;
  private String nome;
  private String password;
  private HashSet<Integer> listaDespesas;
  private HashMap<String,Float> Lista_Dividas; 
  private float balanco;
  private int iban;
  private boolean admin=false;

  // Construtores

  public Morador (String nome, String mail, String password, int iban) {
    this.mail = mail;
    this.nome=nome;
    this.password = password;
    this.iban=iban;
    this.listaDespesas = new HashSet<>();
    this.Lista_Dividas = new HashMap<>();
    this.balanco = 0;
  }

  public Morador (String nome, String mail, String password, int iban, HashSet<Integer> listaDespesas) {
      this.iban=iban;
    this.mail = mail;
    this.nome=nome;
    this.password = password;
    HashSet<Integer> novo = new HashSet<>();
    for(Integer i : listaDespesas){
        novo.add(i);
    }
    this.listaDespesas = novo;
    this.Lista_Dividas = new HashMap<>();
    this.balanco = 0;
  }
  
    public Morador (String nome, String mail, String password, int iban, float t,boolean admin) {
        this.iban= iban;
    this.mail = mail;
    this.nome=nome;
    this.password = password;
    this.listaDespesas = new HashSet<>();
    this.Lista_Dividas = new HashMap<>();
    this.balanco = t;
    this.admin=admin;
  }
    public Morador (String nome, String mail, String password, int iban, float t) {
        this.iban= iban;
    this.mail = mail;
    this.nome=nome;
    this.password = password;
    this.listaDespesas = new HashSet<>();
    this.Lista_Dividas = new HashMap<>();
    this.balanco = t;
  }
    
    public Morador(){
    this.iban= 0;
    this.mail = "";
    this.nome="";
    this.password = "";
    this.listaDespesas = new HashSet<>();
    this.Lista_Dividas = new HashMap<>();
    this.balanco = 0;
    }

    public Morador(Morador a){
    this.iban= a.getIban();
    this.mail = a.getMail();
    this.nome=a.getNome();
    this.password = a.getPassword();
    this.listaDespesas = a.getListaDespesas();
    this.Lista_Dividas = a.getLista_Dividas();
    this.balanco = a.getBalanco();
    this.admin = a.getAdmin();
    }
  // Getters e Setters
    
 public int getIban(){
     return this.iban;
 }

  public String getMail() {
    return this.mail;
  }

  public String getPassword() {
    return this.password;
  }

  public String getNome(){
      return nome;
  }
  public HashSet<Integer> getListaDespesas() {
    return this.listaDespesas;
  }
  
  public HashMap<String,Float> getLista_Dividas(){
      return this.Lista_Dividas;
  }

  public float getBalanco() {
    return this.balanco;
  }

  public boolean getAdmin() {
    return this.admin;
  }
  
  public void setIban(int iban){
      this.iban=iban;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }
  
  public void setNome(String nome){
      this.nome=nome;
  }

  public void setPassword(String passw) {
    this.password = passw;
  }

  public void setListaDespesas(HashSet<Integer> lista) {
    HashSet<Integer> novo = new HashSet<>();
    for(Integer i : lista){
        novo.add(i);
    }
    this.listaDespesas = novo;
  }

  public void setLista_Dividas(HashMap<String,Float> lista){
      HashMap<String,Float> novo = new HashMap<>();
      for(Map.Entry<String,Float> i : lista.entrySet()){
          novo.put(i.getKey(), i.getValue());
      }
      this.Lista_Dividas=novo;
  }
  
  public void setBalanco(float balanco) {
    this.balanco = balanco;
  }

  public void setAdmin(boolean b) {
    this.admin = b;
  }

  // Métodos

  public void addBalanco(float balanco) {
    this.balanco+=balanco;
  }

  public void subBalanco(float balanco) {
    this.balanco-=balanco;
  }
  
  public void addDespesa(Integer i) {
      this.listaDespesas.add(i);
  }
  
  public int getDespesasSize() {
      return this.listaDespesas.size();
  }
  
  public void actualizaDividas(String nome, float valor){
      if(this.Lista_Dividas.get(nome) == valor) this.Lista_Dividas.remove(nome);
      else {
          float a = this.Lista_Dividas.get(nome);
          this.Lista_Dividas.remove(nome);
          this.Lista_Dividas.put(nome, a-valor);
          
      }
  }
  
  public Morador clone(){
      return new Morador(this);
  }
  
}
