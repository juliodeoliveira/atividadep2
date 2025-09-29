import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Agenda {
    // private int Id;
    // private String nome;

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
                break;

            case "email":
                for (Contato c : this.contatos) {
                    // TODo o usuario tem que informar pelo o que ele quer pesquisar
                    if (c.getEmail().contains(valor)) {
                        contatos.add(c);
                    }
                }
                break;
        
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

    public ArrayList<Contato> allContacts() {
        return this.contatos;
    }

    @Override
    public String toString() {
        String converts = "";

        for (Contato c : this.contatos) {
            converts += "Nome: "+ c.getNome() + " , " + "Telefone: " + c.getTelefone() + ", " +"Email: "+ c.getEmail() + "\n";
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
                    if (c.getId() == Id) {
                        c.setNome(valor);
                    }
                }break;

            case "phone":
                for (Contato c : this.contatos) {
                    if (c.getId() == Id) {
                        c.setTelefone(valor);
                    }
                }break;

            case "email":
                for (Contato c : this.contatos) {
                    if (c.getId() == Id) {
                        c.setEmail(valor);
                    }
                }
                break;
        }

    }

    public String validatePhone(String telefone) {
        String somenteNumeros = telefone.replaceAll("\\D", "");

        String ddd = somenteNumeros.substring(0, 2);
        String numero = somenteNumeros.substring(2);

        if (somenteNumeros.length() < 11) {
            return "ERRO: Verifique a quantidade de algarismos e o DDD.";
        }
        if (somenteNumeros.length() > 12 || !numero.startsWith("9")) {
            return "ERRO: Verifique se o número está no padrão brasileiro.";
        }

        if (somenteNumeros.length() == 12 && somenteNumeros.startsWith("0")) {
            somenteNumeros = somenteNumeros.substring(1);
        }

        return String.format(
            "+55 (%s) %s %s-%s",
            ddd,
            numero.substring(0, 1),
            numero.substring(1, 5),
            numero.substring(5)
        );
    }

    public String validateEmail(String email) {
        // Regex básica para validar e-mails
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(email).matches()) {
            return "ERRO: email inválido.";        
        } 
            
        return email;
    }

}
