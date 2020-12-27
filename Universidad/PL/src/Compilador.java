import org.antlr.v4.runtime.RecognitionException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Compilador extends AnasintBaseListener{

    private String nombreFichero;
    private String cadenaAdicional="_optimizado";
    private String extensionEntrada=".txt";
    private String extensionSalida=".lc";
    private FileWriter fichero;
    private Map<String, Integer>almacen_enteros=new HashMap<>();
    private Map<String, Boolean>almacen_boleanos=new HashMap<>();
    private Map<String, List<Integer>>almacen_lenteros=new HashMap<>();
    private Map<String, List<Boolean>>almacen_lboleanos=new HashMap<>();
    private int identacion=0;
    private int tabulacion=3;


    public void init(String nombre){
        this.nombreFichero=new String(nombre);
    }

    private Integer AumentarIdentacion(){
        return identacion+=tabulacion;
    }

    private Integer DecrementarIdentacion(){
        return identacion-=tabulacion;
    }

    private String construirNombreFichero(){
        return nombreFichero.substring(0,nombreFichero.length()-extensionEntrada.length())+cadenaAdicional+extensionSalida;
    }
    private void abrirFichero(){
        try{
            fichero = new FileWriter("src//"+construirNombreFichero());
        }catch(IOException e)
        {System.out.println("Error al abrir el archivo");}
    }
    private void cerrarFichero(){
        try{
            fichero.close();
        }catch(IOException e)
        {System.out.println("Error al cerrar el archivo");}
    }

    private void escribirCodigo(String codigo){
        try {
            for (int i = 0; i < identacion; i++) {
                fichero.write(" ");
            }
            fichero.write(codigo);
        }catch(IOException e)
        {System.out.println("Error al escribir en el archivo");}
    }

    public void enterPrograma(Anasint.ProgramaContext ctx) {
        abrirFichero();
        escribirCodigo("PROGRAMA\n");
    }
    public void enterVariables(Anasint.VariablesContext ctx) {
        escribirCodigo("VARIABLES\n");
        AumentarIdentacion();
    }

    /*private Object traduceTipo(String s){
        if(s=="NUM"){
            return Integer
        }

        return null;
    }*/

    @Override
    public void enterVars_decla(Anasint.Vars_declaContext ctx) {
        List<String> variables = new ArrayList<>();
        String tipo="";

        escribirCodigo(ctx.getText()+";\n");
        String aux="";
        for(int i=0;i<ctx.getText().length();i++){
            if(ctx.getText().charAt(i)!=','&& ctx.getText().charAt(i)!=':'){
                aux+=ctx.getText().charAt(i);
            }else if(ctx.getText().charAt(i)==','){
                variables.add(aux);
                aux="";
            }else if(ctx.getText().charAt(i)==':'){
                variables.add(aux);
                aux="";
                tipo=ctx.getText().substring(i+1,ctx.getText().length());
                break;
            }
        }

        if(tipo.contains("NUM")&&!tipo.contains("SEQ")){

            for (String s:variables) {
                almacen_enteros.put(s,null);
            }
        }else if(tipo.contains("LOG")&&!tipo.contains("SEQ")){
            for (String s:variables) {
                almacen_boleanos.put(s,null);
            }
        }else if(tipo.contains("NUM")&&tipo.contains("SEQ")){
            for (String s:variables) {
                almacen_lenteros.put(s,null);
            }
        }else if(tipo.contains("LOG")&&tipo.contains("SEQ")){
            for (String s:variables) {
                almacen_lboleanos.put(s,null);
            }
        }
        /*System.out.println("enteros:"+almacen_enteros);
        System.out.println("lenteros: "+almacen_lenteros);
        System.out.println("_____________________________");*/
    }

    @Override
    public void enterNom_var(Anasint.Nom_varContext ctx) {

    }

    @Override
    public void exitPrograma(Anasint.ProgramaContext ctx) {
        cerrarFichero();
    }

    @Override
    public void enterSubprogramas(Anasint.SubprogramasContext ctx) {
        DecrementarIdentacion();
        escribirCodigo("SUBPROGRAMAS\n");
    }

    @Override
    public void enterInstrucciones(Anasint.InstruccionesContext ctx) {
        DecrementarIdentacion();
        escribirCodigo("INSTRUCCIONES\n");
        AumentarIdentacion();
    }

    @Override
    public void enterAsignacion(Anasint.AsignacionContext ctx) {
        Set<String> variables = new HashSet<>();
        escribirCodigo(ctx.getText()+"\n");
        String parteNom_var=ctx.getText().substring(0,ctx.getText().indexOf('='));
        //String parteDev_expr=ctx.getText().substring(ctx.getText().indexOf('=')+1,ctx.getText().length()-1);

        if(!parteNom_var.contains(",")){
            variables.add(parteNom_var);
        }else{
            variables.add(parteNom_var.substring(0,parteNom_var.indexOf(',')));
            variables.add(parteNom_var.substring(parteNom_var.indexOf(',')+1,parteNom_var.length()));
        }
        String num="";
        for (String s: variables) {
            if(tipoAlmacen(s)=="Integer"){
                //Falta añadir valores a las variables asignadas, ¿asignarlo en expresion?

                almacen_enteros.put(s,0); //put de test
                System.out.println(almacen_enteros);
            }else if(tipoAlmacen(s)=="Boolean"){
                almacen_boleanos.put(s,false);//put de test
                System.out.println(almacen_boleanos);
            }else if(tipoAlmacen(s)=="List<Integer>"){
                almacen_lenteros.put(s,new ArrayList<Integer>());
                System.out.println(almacen_lenteros);//put de test
            }else if(tipoAlmacen(s)=="List<Integer>"){
                almacen_lboleanos.put(s,new ArrayList<Boolean>());
                System.out.println(almacen_lboleanos);//put de test
            }else {     //else si se decide hacer algo cuando la variable no esté declarada
                System.out.println("La variable "+s+" no esta definida");
            }

        }
    }

    /*private Integer recolectarNumeros(String op, Integer res, Integer i, String num){
        System.out.println("op:"+op+" res:"+res+" i:"+i+" num:"+num);

        if(i<op.length()){
            if(op.charAt(i)=='+'){
                return recolectarNumeros(op,res+Integer.parseInt(num),i+1,"");
            }else if(op.charAt(i)=='-'){
                if(num.length()==0){
                    num+="-";
                    return recolectarNumeros(op,res,i+1,num);
                }else{
                    return recolectarNumeros(op,res-Integer.parseInt(num),i+1,"");
                }
            }else if(op.charAt(i)=='*'){
                return recolectarNumeros(op,res*Integer.parseInt(num),i+1,"");
            }else{

                return recolectarNumeros(op,res,i+1,num+op.charAt(i));
            }
        }
        if(i==op.length()){
            res+=Integer.parseInt(num);
        }
        System.out.println(res);
        return res;
    }*/

    private String tipoAlmacen(String s) {
        String res="";
        if(almacen_enteros.containsKey(s)){
            res="Integer";
        }
        if(almacen_boleanos.containsKey(s)){
            res="Boolean";
        }
        if(almacen_lenteros.containsKey(s)){
            res="List<Integer>";
        }
        if(almacen_lboleanos.containsKey(s)){
            res="List<Boolean>";
        }
        return res;
    }





}