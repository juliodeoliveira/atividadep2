import java.util.ArrayList;
import java.util.stream.Collectors;

public class Agenda {
    private int Id;
    private String nome;
    private Arquivo arquivo;
    private ArrayList<Contato> contatos = new ArrayList<>();

    public Agenda() {
        this.arquivo = new Arquivo("teste.txt");
        this.arquivo.criarArquivo();

        ArrayList<String> conteudo = this.arquivo.readFileArray();
        if (conteudo.isEmpty()) {
            return;
        }

        int idCount = 0;
        for (String conte: conteudo) {
            String[] part = conte.split("\\|");

            String name = part[0].trim();
            String number = part[1].trim();
            String email = part[2].trim();

            Contato contact = new Contato(name,number,email);
            contact.setId(idCount);


            idCount += 1;
            this.contatos.add(contact);
        }
    }

    public void adicionarContato(Contato contact) {
        contact.setId(this.contatos.size());
        this.contatos.add(contact);
    }

//    public int verificarUltimoId(){
//        ArrayList<String> conteudo = this.arquivo.readFileArray();
//
//        if(conteudo.isEmpty()){
//           return 0;
//        }
//        String UltimaLinha = conteudo.getLast();
//        return Integer.parseInt(UltimaLinha.split(" | ")[0])+1;
//       // String[] arrayContent = conteudo.split("\\|");
//
//
//    }

    public ArrayList<Contato> lerContato(String valor, String field){
        ArrayList<Contato> contatos = new ArrayList<>();

        String informationToSearch = "";

        switch (field) {
            case "name":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getNome().contains(valor)) {
                        contatos.add(c);
                    }
                }

                break;
                
            case "phone":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getTelefone().contains(valor)) {
                        contatos.add(c);
                    }
                }

            case "email":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getEmail().contains(valor)) {
                        contatos.add(c);
                    }
                }
        
            case "searchforall":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getNome().contains(valor) || c.getTelefone().contains(valor) || c.getEmail().contains(valor)) {
                        contatos.add(c);
                    }
                }
                break;
        }


        return contatos;
    }

    public void apagarContato(int Id){
        this.contatos.remove(Id);

        int idCount = 0;
        for (Contato c: this.contatos) {
            c.setId(idCount);
            idCount++;
        }
    }

    // TODO: Deve ser toString
    public ArrayList<Contato> allContacts() {
        return this.contatos;
    }

    @Override
    public String toString() {
        String converts = "";

        for (Contato c : this.contatos) {
            converts += "Nome: "+ c.getNome() + " , " + "Telefone: " + c.getTelefone() + "," +"Email: "+ c.getEmail() + "\n";
        }

        return "Contatos: " + converts;
    }

    public void save() {
        String content = "";
        for (Contato c : this.contatos) {
            content += c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail() + "\n";
        }

        this.arquivo.escreverArquivo(content);

    }

    public void editarContato(String valor, String field, int Id){

        switch (field) {
            case "name":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getId() == Id) {
                        c.setNome(valor);
                    }
                }break;

            case "phone":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getId() == Id) {
                        c.setTelefone(valor);
                    }
                }break;

            case "email":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getId() == Id) {
                        c.setEmail(valor);
                    }
                }break;



        }

    }
}
