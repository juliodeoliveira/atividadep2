import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Agenda agenda = new Agenda();
        System.out.println(agenda.toString());

        int op = 0;
        String nomeContato;
        String telContato;
        String email;
        ArrayList<Contato> result = new ArrayList<>();

        do {
            System.out.println("----------------------------------------------------");

            System.out.println("====================================================");
            System.out.println("                   MENU PRINCIPAL");
            System.out.println("====================================================");

            System.out.print("1-Criar contatos\n2-Ler todos contatos\n3-Buscar contato\n4-Editar contato\n5-Apagar contato\n6-Salvar\n7-Sair\nEscolha uma opção: ");

            op = ler.nextInt();
            ler.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Digite o nome do contato: ");
                    nomeContato = ler.nextLine();

                    while (true) {
                        System.out.print("Digite o telefone do contato: +55 ");

                        telContato = ler.nextLine();
                        
                        String validation = agenda.validatePhone(telContato);

                        if (!validation.startsWith("ERRO:")) {
                            telContato = validation;
                            break;
                        }

                        System.out.println(validation);
                    }


                    while (true) {
                        System.out.print("Digite o email do contato: ");
                        email = ler.nextLine();

                        String validation = agenda.validateEmail(email);

                        if (!validation.startsWith("ERRO:")) {
                            break;
                        }

                        System.out.println(validation); // inválido → mostra mensagem
                    }

                    Contato contato = new Contato(nomeContato, telContato, email);

                    agenda.adicionarContato(contato);
                    System.out.println("Contato criado com sucesso!");

                    break;
                case 2:
                    System.out.println("Segue a lista de contatos:");
                    System.out.println("----------------------------------------------------");

                    ArrayList<Contato> allContacts = agenda.allContacts();
                    if (allContacts.isEmpty()) {
                        System.out.println("Nenhum contato encontrado!");
                    } else {
                        for (Contato c : allContacts) {
                            System.out.print(c.getId() + " ~ ");
                            System.out.print(c.getNome() + " ~ ");
                            System.out.print(c.getTelefone() + " ~ ");
                            System.out.print(c.getEmail() + "\n");
                        }
                    }

                    break;
                case 3:

                    int userOption;
                    String action;
                    do {
                        System.out.print("1-Nome\n2-Telefone\n3-Email\n4-Cancelar\n");

                        System.out.print("Pelo que voce deseja pesquisar? ");
                        userOption = ler.nextInt();
                        ler.nextLine();

                        if (userOption == 4) break;
                        switch (userOption) {
                            case 1:
                                action = "name";
                                System.out.print("Digite o nome do contato: ");
                                nomeContato = ler.nextLine();
                                result = agenda.lerContato(nomeContato, action);
                                break;
                            case 2:
                                action = "phone";
                                System.out.print("Digite o numero do contato: +55 ");
                                String numeroContato = ler.nextLine();
                                
                                String validatedNumber = agenda.validatePhone(numeroContato);

                                if (validatedNumber.startsWith("ERRO:")) {
                                    validatedNumber = numeroContato;
                                }

                                result = agenda.lerContato(validatedNumber, action);
                                break;
                            case 3:
                                action = "email";
                                System.out.print("Digite o email do contato: ");
                                String emailContato = ler.nextLine();

                                result = agenda.lerContato(emailContato, action);
                                break;

                        }


                        if (result.isEmpty()) {
                            System.out.println("Nenhum contato encontrado!");
                        } else {
                            System.out.println("\nContatos encontrados: ");
                            System.out.println("----------------------------------------------------");
                            for (Contato c : result) {

                                System.out.print(c.getId() + " ~ ");
                                System.out.print(c.getNome() + " ~ ");
                                System.out.print(c.getTelefone() + " ~ ");
                                System.out.print(c.getEmail() + "\n");
                            }
                        }

                        System.out.println("----------------------------------------------------");

                    } while (true);

                    break;

                case 4:
                    System.out.print("Digite o nome ou numero do contato: ");
                    nomeContato = ler.nextLine();

                    result = agenda.lerContato(nomeContato, "searchforall");
                    if (result.isEmpty()) {
                        System.out.println("----------------------------------------------------");
                        System.out.println("Nenhum contato encontrado!");
                        System.out.println("----------------------------------------------------");
                    } else {
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
                            System.out.print("Digite o ID do contato que quer editar: ");
                            int user = ler.nextInt();

                            int editOp;
                            if (!(ids.contains(user))) {
                                System.out.println("ID digitado não foi encontrado!");
                                continue;
                            }

                            Contato contatoSelecionado = null;
                            for (Contato c : result) {
                                if (c.getId() == user) {
                                    contatoSelecionado = c;
                                    break;
                                }
                            }

                            do {
                                System.out.printf("O que você quer editar do contato \"%s\":\n", contatoSelecionado.getNome());                                System.out.print("1-Nome\n2-Telefone\n3-Email\n4-Cancelar\nEscolha a opção: ");
                                editOp = ler.nextInt();
                                ler.nextLine();
                                switch (editOp) {
                                    case 1:
                                        System.out.print("Digite o novo nome do contato: ");
                                        agenda.editarContato(ler.nextLine(), "name", user);

                                        System.out.println("----------------------------------------------------");
                                        System.out.println("Contato editado com sucesso!");
                                        break;
                                    case 2:

                                        while (true) {
                                            System.out.print("Digite o telefone do contato: +55 ");
                                            telContato = ler.nextLine();

                                            String validation = agenda.validatePhone(telContato);

                                            if (!validation.startsWith("ERRO:")) {
                                                agenda.editarContato(validation, "phone", user);
                                                break;
                                            }

                                            System.out.println(validation);
                                        }
                                        System.out.println("----------------------------------------------------");
                                        System.out.println("Contato editado com sucesso!");

                                        break;
                                    case 3:
                                        while (true) {
                                            System.out.print("Digite o email do contato: ");
                                            email = ler.nextLine();

                                            String validation = agenda.validateEmail(email);

                                            if (!validation.startsWith("ERRO:")) {
                                                agenda.editarContato(validation, "email", user);
                                                break;
                                            }

                                            System.out.println(validation);
                                        }

                                        System.out.println("----------------------------------------------------");
                                        System.out.println("Contato editado com sucesso!");

                                        break;
                                }
                            } while (editOp != 4);

                            if (editOp == 4) {
                                break;
                            }

                        }

                    }
                   
                    break;

                case 5:
                    System.out.print("Digite o nome, número ou email do contato: ");
                    nomeContato = ler.nextLine();
                    result = agenda.lerContato(nomeContato, "searchforall");

                    if (result.isEmpty()) {
                        System.out.println("----------------------------------------------------");
                        System.out.println("Nenhum contato encontrado!");
                        System.out.println("----------------------------------------------------");
                    } else {
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

                            if (user == 999) {
                                break;
                            }

                            System.out.println();
                            if (ids.contains(user)) {
                                agenda.apagarContato(user);
                                break;
                            }

                            System.out.println("ID inválido!");
                        }

                    }

                    break;
                case 6:
                    agenda.save();
                    System.out.println("Agenda salva com sucesso!");
                    break;
            }
        } while (op != 7);
        agenda.save();
    }
}
