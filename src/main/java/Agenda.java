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

            Contato contact = new Contato();
            contact.setId(idCount);
            contact.setNome(name);
            contact.setTelefone(number);
            contact.setEmail(email);

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

    public ArrayList<Contato> lerContato(String valor){
        ArrayList<Contato> contatos = new ArrayList<>();

        for (Contato c : this.contatos) {
            // TODo o usuario tem que informar pelo o que ele quer pesquisar
            if (c.getNome().contains(valor) || c.getTelefone().contains(valor) || c.getEmail().contains(valor)) {
                contatos.add(c);
            }
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
            converts += c.getNome() + " ~ " + c.getTelefone() + " ~ " + c.getEmail() + "\n";
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
}
