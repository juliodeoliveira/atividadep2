import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    private String FilePath;
    public Arquivo(String FilePath){
        this.FilePath = FilePath;
    }

    public void criarArquivo(){
        File arq = new File(this.FilePath);//instancia e define o nome do arquivo
        try{
            if(arq.createNewFile()){
                System.out.println("Arquivo criado: "+arq.getName());
            }else{
                System.out.println("Arquivo já existe");
            }
        }catch (IOException e){
            System.out.println("Um erro ocorreu, verifique as permissões dos arquivos do projeto!");
        }
    }

    public void escreverArquivo(String text){
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(this.FilePath))){
            escritor.write(text);


        }catch (IOException e){
            System.out.println("deu erro na escrita!");
        }
    }

    public ArrayList<String> readFileArray() {
        ArrayList<String> teste = new ArrayList<>();
        try(BufferedReader leitor = new BufferedReader(new FileReader(this.FilePath))){
            String text = "";
            String result = "";

            while(true){
                text = leitor.readLine();
                if(text == null){
                    break;
                }

                teste.add((text+"\n"));
            }

            leitor.close();
            return teste;

        }
        catch (IOException e){
            System.out.println("Deu erro na leitura "+e);
        }

        return teste;
    }

}
