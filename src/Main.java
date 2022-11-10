import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine();
        while (!entrada.equals("FIM")) {
            Pilha pilha = new Pilha();
            String entradaSeparada[] = entrada.split("");
            String res = "";
            boolean parar = false;
            for (int i = 0; ((i < entradaSeparada.length) && !parar); i++) {
                char ref =  entrada.charAt(i);
                if (isValid(ref)) {
                    if (abrindo(ref)) {
                        pilha.empilhar(ref);
                    } else {
                        try {
                            char ultimo = pilha.desempilhar();
                            if (getType(ultimo) == getType(ref)) {
                                res = "correto";
                            } else {
                                res = "incorreto";
                                parar = true;
                            }
                        } catch (Exception e) {
                            res = "incorreto";
                            parar = true;
                            //System.err.println(e.getMessage());
                        }
                    }
                }
            }
            if (!pilha.pilhaVazia()) {
                res = "incorreto";
            }
            System.out.println(res);
            entrada = scanner.nextLine();
        }
    }

    public static boolean isValid(char carac) {
        if (carac == '[' || carac == '(' || carac == ']' || carac == ')') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean abrindo(char carac) {
        if (carac == '[' || carac == '(') {
            return true;
        }
        return false;
    }

    public static int getType(char carac) {
        /*
        * 1 = [
        * 2 = (
        * */
        if (carac == '[' || carac == ']') {
            return 1;
        }
        if (carac == '(' || carac == ')') {
            return 2;
        }
        return -1;
    }
}

class Celula {

    private char item;
    private Celula proximo;

    public Celula(char novo) {
        item = novo;
        proximo = null;
    }

    public Celula() {

        item = new Character(' ');
        proximo = null;
    }

    public char getItem() {
        return item;
    }
    public void setItem(char item) {
        this.item = item;
    }

    public Celula getProximo() {
        return proximo;
    }
    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}

class Pilha {

    private Celula fundo;
    private Celula topo;

    public Pilha() {

        Celula sentinela;

        sentinela = new Celula();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean pilhaVazia() {

        boolean resp;

        if (topo == fundo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void empilhar(char novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);
        novaCelula.setProximo(topo);
        topo = novaCelula;
    }

    public char desempilhar() throws Exception {

        Celula desempilhado;

        if (! pilhaVazia()) {
            desempilhado = topo;
            topo = topo.getProximo();
            desempilhado.setProximo(null);
            return (desempilhado.getItem());
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public char consultarTopo() throws Exception {

        if (! pilhaVazia()) {
            return(topo.getItem());
        } else
            throw new Exception("Não foi possível consultar o topo da pilha: a pilha está vazia!");
    }
}