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

    private void AumentarIdentacion(){
        identacion+=tabulacion;
    }

    private void DecrementarIdentacion(){
        identacion-=tabulacion;
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

    public void enterBloq_instr(Anasint.Bloq_instrContext ctx){
        String res=ctx.getText();
        if(res.contains("asignacion")){
            Anasint.AsignacionContext aa=(Anasint.AsignacionContext)ctx.getChild(0);
            enterAsignacion(aa);
        }else if(res.contains("condicional")){
            Anasint.CondicionalContext ac=(Anasint.CondicionalContext)ctx.getChild(0);
            enterCondicional(ac);
        }else if(res.contains("iteracion")){
            enterIteracion(ctx);
        }else if(res.contains("RUPTURA")){
            //hacer ruptura
        }else if(res.contains("llamada")){
            enterLlamada(ctx);
        }else if(res.contains("aserto")){
            enterAserto(ctx);
        }else{
            enterDevolucion(ctx);
        }
    }

    @Override
    public void enterAsignacion(Anasint.AsignacionContext ctx) {
        List<String> variables = new ArrayList<>();
        List<String> expresiones = new ArrayList<>();
        escribirCodigo(ctx.getText()+"\n");
        String parteNom_var=ctx.getText().substring(0,ctx.getText().indexOf('='));
        String parteDev_expr=ctx.getText().substring(ctx.getText().indexOf('=')+1,ctx.getText().length()-1);

        if(!parteNom_var.contains(",")){
            variables.add(parteNom_var);
        }else{
            variables.add(parteNom_var.substring(0,parteNom_var.indexOf(',')));
            variables.add(parteNom_var.substring(parteNom_var.indexOf(',')+1,parteNom_var.length()));
            if(parteDev_expr.contains(",")){
                expresiones.add(parteDev_expr.substring(0,parteDev_expr.indexOf(',')));
                expresiones.add(parteDev_expr.substring(parteDev_expr.indexOf(',')+1,parteDev_expr.length()));
            }else{
                expresiones.add(parteDev_expr);
            }
        }
        String num="";
        for (String s: variables) {
            if(tipoAlmacen(s)=="Integer"){
                //Falta añadir valores a las variables asignadas, ¿asignarlo en expresion?
                if(expresiones.size()==1){
                    almacen_enteros.put(s,traduceExpresionInteger(expresiones.get(0)));
                }else{
                    almacen_enteros.put(s,traduceExpresionInteger(expresiones.get(variables.indexOf(s))));
                }

                System.out.println(almacen_enteros);
            }else if(tipoAlmacen(s)=="Boolean"){
                if(expresiones.size()==1){
                    almacen_boleanos.put(s,traduceExpresionBoolean(expresiones.get(0)));
                }else{
                    almacen_boleanos.put(s,traduceExpresionBoolean(expresiones.get(variables.indexOf(s))));
                }
                System.out.println(almacen_boleanos);
            }else if(tipoAlmacen(s)=="List<Integer>"){
                if(expresiones.size()==1){
                    almacen_lenteros.put(s,traduceExpresionLInteger(expresiones.get(0)));
                }else{
                    almacen_lenteros.put(s,traduceExpresionLInteger(expresiones.get(variables.indexOf(s))));
                }
                System.out.println(almacen_lenteros);//put de test
            }else if(tipoAlmacen(s)=="List<Boolean>"){
                if(expresiones.size()==1){
                    almacen_lboleanos.put(s,traduceExpresionLBoolean(expresiones.get(0)));
                }else{
                    almacen_lboleanos.put(s,traduceExpresionLBoolean(expresiones.get(variables.indexOf(s))));
                }
                System.out.println(almacen_lboleanos);//put de test
            }else {     //else si se decide hacer algo cuando la variable no esté declarada
                System.out.println("La variable "+s+" no esta definida");
            }

        }
    }

    private Integer traduceExpresionInteger (String expr){

        return null;
    }

    private Boolean traduceExpresionBoolean (String expr){

        return null;
    }

    private List<Integer> traduceExpresionLInteger (String expr){

        return null;
    }

    private List<Boolean> traduceExpresionLBoolean (String expr){

        return null;
    }


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