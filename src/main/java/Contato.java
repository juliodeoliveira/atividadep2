public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    public Contato(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome){
       this.nome = nome;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getId() {
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getTelefone(){
        return  this.telefone;
    }

}
