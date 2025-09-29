import java.util.ArrayList;
import java.util.regex.Pattern;

public class Agenda {

    private Arquivo arquivo;
    private ArrayList<Contato> contatos = new ArrayList<>();

    public Agenda() {
        this.arquivo = new Arquivo("contacts.txt");
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
        if (!telefone.matches("[\\d\\s\\-()]+")) {
            return "ERRO: Números de telefone não podem conter letras ou símbolos inválidos.";
        }

        String somenteNumeros = telefone.replaceAll("\\D", "");

        if (somenteNumeros.length() < 8) {
            return "ERRO: Número inválido. Mínimo de 8 dígitos.";
        }

        if (somenteNumeros.length() >= 10) {
            String ddd = somenteNumeros.substring(0, 2);
            String numero = somenteNumeros.substring(2);

            if (somenteNumeros.length() == 12 && somenteNumeros.startsWith("0")) {
                somenteNumeros = somenteNumeros.substring(1);
                ddd = somenteNumeros.substring(0, 2);
                numero = somenteNumeros.substring(2);
            }

            if (numero.startsWith("9")) {
                return String.format(
                    "+55 (%s) %s %s-%s",
                    ddd,
                    numero.substring(0, 1),
                    numero.substring(1, 5),
                    numero.substring(5)
                );
            }
        }

        int tamanho = somenteNumeros.length();
        return "+55 " + somenteNumeros.substring(0, tamanho - 4) 
            + "-" + somenteNumeros.substring(tamanho - 4);
    }

    public String validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(email).matches()) {
            return "ERRO: email inválido.";        
        } 
            
        return email;
    }

}
