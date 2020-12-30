parser grammar Anasint;
options{
 tokenVocab=Analex;
}
programa: PROGRAMA variables subprogramas instrucciones;

variables: VARIABLES (vars_decla PyC)+;

vars_decla: nom_var DP tipo;

nom_var: IDENT (COMA IDENT)*;

tipo: TIPO_SEC PA tipo2 PC
    | tipo2
    ;

tipo2: TIPO_NUM
     | TIPO_LOG
     ;

subprogramas: SUBPROGRAMAS (subprograma)*;

subprograma: FUNCION funciones FFUNCION
            | PROCEDIMIENTO procedimientos FPROCEDIMIENTO;

funciones: def_funcion variables instrucciones;

def_funcion: IDENT PA v_entrada PC DEV PA v_salida PC;

v_entrada: (tipo IDENT (COMA tipo IDENT)*)?;

v_salida: tipo IDENT (COMA tipo IDENT)*;

procedimientos: def_procedimiento variables instrucciones;

def_procedimiento: IDENT PA v_entrada PC;

instrucciones:INSTRUCCIONES bloq_instr;

bloq_instr: (instruccion)+;

instruccion: asignacion
          | condicional
          | iteracion
          | RUPTURA
          | llamada
          | aserto
          | devolucion
          ;

devolucion: DEV dev_expr PyC;

dev_expr: expresion (COMA expresion)*;

asignacion: nom_var ASIG dev_expr PyC;

expresion: expresion_entera
         | expresion_lista
         | expresion_booleana
         | NULL
         ;

expresion_entera: expresion_entera (POR|MAS|MENOS) expresion_entera
                | llamada
                | (MENOS)? NUM
                | IDENT
                | IDENT CA (expresion_entera) CC
                | PA expresion_entera PC
                ;

expresion_lista: llamada
               | CA NUM (COMA NUM)+ CC
               | CA LOG (COMA LOG)+ CC
               | IDENT
               | CA CC
               ;

expresion_booleana: llamada
                  | IDENT
                  | LOG
                  ;

condicional: SI PA condicion PC ENTONCES bloq_instr (SINO bloq_instr)? FSI;

condicion: NOT condicion // if not variable
         | condicion (AND|OR) condicion
         | expresion comparador expresion
         | (C_CIERTO|C_FALSO);

comparador:(IGUAL_IGUAL|DISTINTO|MENOR_QUE|MAYOR_QUE|MENOR_IGUAL|MAYOR_IGUAL);

iteracion: MIENTRAS PA condicion PC HACER  (funcion_avance)? bloq_instr FMIENTRAS;

funcion_avance: LLA AVANCE DP llamada LLC;

aserto: LLA (LOG|cuantif_exist|cuantif_universal) LLC;

cuantif_universal: PARATODO PA IDENT DP CA expresion_entera COMA expresion_entera CC COMA condicion PC;

cuantif_exist: EXISTE PA IDENT DP CA expresion_entera COMA expresion_entera CC COMA condicion PC;

llamada: IDENT PA (expresion)? (COMA (expresion))* PC;