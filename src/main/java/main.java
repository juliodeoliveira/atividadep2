import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Agenda agenda = new Agenda();
        System.out.println(agenda.toString());

        int op=0;
        String nomeContato;
        String telContato;
        String email;
        ArrayList<Contato> result;

        do {
            System.out.print("1-Criar contatos\n2-Ler todos contatos\n3-Buscar contato\n4-Apagar contato\n5-Salvar\n6-Sair\nEscolha uma opção: ");

            op = ler.nextInt();
            ler.nextLine();
            switch (op) {
                case 1:
                    Contato contato = new Contato();
                    System.out.print("Digite o nome do contato: ");
                    nomeContato = ler.nextLine();
                    contato.setNome(nomeContato);

                    System.out.print("Digite o telefone do contato: ");
                    telContato = ler.nextLine();
                    contato.setTelefone(telContato);

                    System.out.print("Digite o email do contato: ");
                    email = ler.nextLine();
                    contato.setEmail(email);

                    agenda.adicionarContato(contato);

                    break;
                case 2:
                    System.out.println("Segue a lista de contatos:");
                    System.out.println("----------------------------------------------------");

                    ArrayList<Contato> allContacts = agenda.allContacts();
                    if (allContacts.isEmpty()) {
                        System.out.println("Nenhum contato encontrado!");
                    } else {
                        for(Contato c: allContacts){
                            System.out.print(c.getId() + " ~ ");
                            System.out.print(c.getNome() + " ~ ");
                            System.out.print(c.getTelefone() + " ~ ");
                            System.out.print(c.getEmail() + "\n");
                        }
                    }

                    System.out.println("----------------------------------------------------");
                    break;
                case 3:

                    /* * Sugestao para mudar os loops de menu, exclusivamente, estou mantendo dessa forma para seguir o padrao 
                     * Dessa maneira ele requer que algumas variaveis estejam "vivas" durante o projeto sendo que serao inuteis
                     * usar o break previne isso, só colocar a condicao de parada
                    */
                        
                    int userOption;
                    String action;
                    do {
                        System.out.print("1-Nome\n2-Telefone\n3-Email\n4-Cancelar\n");
    
                        System.out.println("Pelo que voce deseja pesquisar? ");
                        userOption = ler.nextInt();
                        ler.nextLine();
    
                        action = "searchforall";


                        switch (userOption) {
                            case 1:
                                action = "name";
                                break;
                            case 2: 
                                action = "phone";
                                break;
                            case 3: 
                                action = "email";
                                break;
                        }
                        if (userOption >= 1 && userOption <= 3) {
                            break;
                        }

                    } while (userOption != 4);

                    System.out.print("Digite o nome, numero ou email do contato: ");
                    nomeContato = ler.nextLine();
                    result = agenda.lerContato(nomeContato, action);
                    

                    if(result.isEmpty()){
                        System.out.println("Nenhum contato encontrado!");
                    }else{
                        System.out.println("\nContatos encontrados: ");
                        System.out.println("----------------------------------------------------");
                        for(Contato c: result){
                            
                            System.out.print(c.getId() + " ~ ");
                            System.out.print(c.getNome() + " ~ ");
                            System.out.print(c.getTelefone() + " ~ ");
                            System.out.print(c.getEmail() + "\n");
                        }
                    }

                    System.out.println("----------------------------------------------------");
                    break;
                case 4:
                    System.out.print("Digite o nome ou numero do contato: ");
                    nomeContato = ler.nextLine();

//                    if (listaPessoas.contains(pessoaParaVerificar2)) {
//                        System.out.println("A pessoa Pedro existe na lista.");
//                    } else {
//                        System.out.println("A pessoa Pedro não existe na lista.");
//                    }

                    result = agenda.lerContato(nomeContato, "searchforall");
                    if(result.isEmpty()){
                        System.out.println("----------------------------------------------------");
                        System.out.println("Nenhum contato encontrado!");
                        System.out.println("----------------------------------------------------");
                    }
                    else {
                        System.out.println("\nContatos encontrados: ");
                        System.out.println("----------------------------------------------------");

                        ArrayList<Integer> ids = new ArrayList<>();
                        for (Contato c : result) {
                            System.out.print(c.getId() + " ~ ");
                            ids.add(c.getId());
                            System.out.print(c.getNome() + " ~ ");
                            System.out.print(c.getTelefone() + " ~ ");
                            System.out.print(c.getEmail() + "\n");
                        }
                        System.out.println("----------------------------------------------------");

                        while (true) {
                            System.out.print("Digite o ID do contato que quer apagar (999 - cancela): ");
                            int user = ler.nextInt();
                            System.out.println();
                            if (ids.contains(user)) {
                                agenda.apagarContato(user);
                                break;
                            } else if (user == 999) {
                                break;
                            }

                            System.out.println("ID inválido!");
                        }

                    }

                    break;
                case 5:
                    agenda.save();
                    System.out.println();
                    break;
            }
        }while (op != 6);



//
//        while(true) {
//            l = ler.nextLine();
//            if (l.equals("fim")) {
//                break;
//            }
//
//            if (l.equals("bananao")) {
//                continue;
//            }
//
//            total += (l + "\n");
//            arq.escreverArquivo(total);
//        }
    }
}
