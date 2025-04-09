package ps2.crudmusica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import static java.lang.System.out;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MusicaRepo repositorio;

    private Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        boolean sair = false;
        while (!sair) {
            out.println("\n## GERENCIADOR DE MÚSICAS ##");
            out.println("(1) Criar música");
            out.println("(2) Ler músicas");
            out.println("(3) Alterar música");
            out.println("(4) Apagar música");
            out.println("(0) Sair");
            out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch(opcao) {
				case 1: criar(); break; 
				case 2: lerTodos(); break; 
				case 3: alterar(); break;
				case 4: apagar(); break;
				case 0: sair = true; break;
				default:
					out.println("## Opção inválida! ##");
            }
        }
    }

    private void criar() {
        out.print("Título: ");
        String titulo = entrada.nextLine();
        out.print("Compositor: ");
        String compositor = entrada.nextLine();
        out.print("Ano: ");
        int ano = Integer.parseInt(entrada.nextLine());

        Musica m = new Musica(titulo, compositor, ano);
        repositorio.save(m);
        out.println("Música criada!");
    }

    private void lerTodos() {
        var musicas = repositorio.findAll();
        if (musicas.isEmpty()) {
            out.println("Nenhuma música cadastrada.");
        } else {
            musicas.forEach(System.out::println);
        }
    }

    private void alterar() {
        out.print("ID da música a alterar: ");
        Long id = Long.parseLong(entrada.nextLine());
        var musica = repositorio.findById(id);
        if (musica.isPresent()) {
            Musica m = musica.get();
            out.print("Novo título: ");
            m.setTitulo(entrada.nextLine());
            out.print("Novo compositor: ");
            m.setCompositor(entrada.nextLine());
            out.print("Novo ano: ");
            m.setAno(Integer.parseInt(entrada.nextLine()));
            repositorio.save(m);
            out.println("Música atualizada.");
        } else {
            out.println("Música não encontrada.");
        }
    }

    private void apagar() {
        out.print("ID da música a apagar: ");
        Long id = Long.parseLong(entrada.nextLine());
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            out.println("Música apagada.");
        } else {
            out.println("Música não encontrada.");
        }
    }
}