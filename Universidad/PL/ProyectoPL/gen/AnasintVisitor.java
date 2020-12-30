// Generated from C:/Users/JMPere/IdeaProjects/ProyectoPL/src\Anasint.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Anasint}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnasintVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Anasint#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(Anasint.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariables(Anasint.VariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#vars_decla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars_decla(Anasint.Vars_declaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#nom_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNom_var(Anasint.Nom_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(Anasint.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#tipo2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo2(Anasint.Tipo2Context ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#subprogramas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogramas(Anasint.SubprogramasContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprograma(Anasint.SubprogramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#funciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunciones(Anasint.FuncionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#def_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_funcion(Anasint.Def_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#v_entrada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitV_entrada(Anasint.V_entradaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#v_salida}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitV_salida(Anasint.V_salidaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#procedimientos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedimientos(Anasint.ProcedimientosContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#def_procedimiento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_procedimiento(Anasint.Def_procedimientoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucciones(Anasint.InstruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloq_instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloq_instr(Anasint.Bloq_instrContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(Anasint.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#devolucion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDevolucion(Anasint.DevolucionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#dev_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev_expr(Anasint.Dev_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(Anasint.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(Anasint.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#expresion_entera}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_entera(Anasint.Expresion_enteraContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#expresion_lista}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_lista(Anasint.Expresion_listaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#expresion_booleana}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_booleana(Anasint.Expresion_booleanaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicional(Anasint.CondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion(Anasint.CondicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#comparador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparador(Anasint.ComparadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#iteracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIteracion(Anasint.IteracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#funcion_avance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion_avance(Anasint.Funcion_avanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#aserto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAserto(Anasint.AsertoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#cuantif_universal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuantif_universal(Anasint.Cuantif_universalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#cuantif_exist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuantif_exist(Anasint.Cuantif_existContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#llamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada(Anasint.LlamadaContext ctx);
}