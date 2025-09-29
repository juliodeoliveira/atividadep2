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

                    /* * Sugestao para mudar os loops de menu, exclusivamente, estou mantendo dessa forma para seguir o padrao
                     * Dessa maneira ele requer que algumas variaveis estejam "vivas" durante o projeto sendo que serao inuteis
                     * usar o break previne isso, só colocar a condicao de parada
                     */

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
                                System.out.print("Digite o numero do contato: ");
                                nomeContato = ler.nextLine();
                                result = agenda.lerContato(nomeContato, action);
                                break;
                            case 3:
                                action = "email";
                                System.out.print("Digite o email do contato: ");
                                nomeContato = ler.nextLine();
                                result = agenda.lerContato(nomeContato, action);
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

                    // TODO: validar se o id que o usuario digitou aqui pra poder editar corretamente

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


                            do {
                                System.out.println("Da ideia, oque você quer editar:");
                                System.out.print("1-Nome\n2-Telefone\n3-Email\n4-Cancelar\nEscolha a opção: ");
                                editOp = ler.nextInt();
                                ler.nextLine();
                                switch (editOp) {
                                    case 1:
                                        System.out.print("Digite o novo nome do contato: ");
                                        agenda.editarContato(ler.nextLine(), "name", user);
                                        break;
                                    case 2:

                                        // TODO: validacao de numero aqui
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

                                        break;
                                    case 3:
                                        // TODO: validacao de email aqui
                                        while (true) {
                                            System.out.print("Digite o email do contato: ");
                                            email = ler.nextLine();

                                            String validation = agenda.validateEmail(email);

                                            if (!validation.startsWith("ERRO:")) {
                                                agenda.editarContato(validation, "email", user);
                                                break;
                                            }

                                            System.out.println(validation); // inválido → mostra mensagem
                                        }

                                        break;
                                }
                            } while (editOp != 4);

                            if (editOp == 4) {
                                break;
                            }

                        }

                    }
                    System.out.println("----------------------------------------------------");
                    System.out.println("Contato editado com sucesso!");
                    break;

                case 5:
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
