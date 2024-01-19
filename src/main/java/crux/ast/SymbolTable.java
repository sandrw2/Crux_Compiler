package crux.ast;

import crux.ast.Position;
import crux.ast.types.*;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Symbol table will map each symbol from Crux source code to its declaration or appearance in the
 * source. The symbol table is made up of scopes, Each scope is a map which maps an identifier to
 * it's symbol. Scopes are inserted to the table starting from the first scope (Global Scope). The
 * Global scope is the first scope in each Crux program and it contains all the built in functions
 * and names. The symbol table is an ArrayList of scops.
 */
public final class SymbolTable {

  /**
   * Symbol is used to record the name and type of names in the code. Names include function names,
   * global variables, global arrays, and local variables.
   */
  static public final class Symbol implements java.io.Serializable {
    static final long serialVersionUID = 12022L;
    private final String name;
    private final Type type;
    private final String error;

    /**
     *
     * @param name String
     * @param type the Type
     */
    private Symbol(String name, Type type) {
      this.name = name;
      this.type = type;
      this.error = null;
    }

    private Symbol(String name, String error) {
      this.name = name;
      this.type = null;
      this.error = error;
    }

    /**
     *
     * @return String the name
     */
    public String getName() {
      return name;
    }

    /**
     *
     * @return the type
     */
    public Type getType() {
      return type;
    }

    @Override
    public String toString() {
      if (error != null) {
        return String.format("Symbol(%s:%s)", name, error);
      }
      return String.format("Symbol(%s:%s)", name, type);
    }

    public String toString(boolean includeType) {
      if (error != null) {
        return toString();
      }
      return includeType ? toString() : String.format("Symbol(%s)", name);
    }
  }

  private final PrintStream err;
  private final ArrayList<Map<String, Symbol>> symbolScopes = new ArrayList<>();

  private boolean encounteredError = false;

  SymbolTable(PrintStream err) {
    this.err = err;
    //create new hashmap and add into ArrayList
    HashMap<String, Symbol> hm = new HashMap<String, Symbol>();
    symbolScopes.add(hm);
    //Add built-in functions into global scope
    //TypeList(List<Type> types)
    //FuncType(TypeList args, Type ReturnType)
    //Symbol(String name, Type type) --> Symbol(name, FuncType)

    //Int readInt()
    FuncType readIntFuncType = new FuncType(new TypeList(), new IntType());
    Symbol readintSymbol = new Symbol("readInt", readIntFuncType);
    symbolScopes.get(0).put("readInt", readintSymbol);

    //Int readChar()
    FuncType readCharFuncType = new FuncType(new TypeList(), new IntType());
    Symbol readCharSymbol = new Symbol("readChar", readCharFuncType);
    symbolScopes.get(0).put("readChar", readCharSymbol);

    //void printBool(bool arg)
    TypeList printBoolTypeList = new TypeList();
    printBoolTypeList.append(new BoolType());
    FuncType printBoolFuncType = new FuncType(printBoolTypeList, new VoidType());
    Symbol printBoolSymbol = new Symbol("printBool", printBoolFuncType);
    symbolScopes.get(0).put("printBool", printBoolSymbol);

    //void printInt(int arg)
    TypeList printIntTypeList = new TypeList();
    printIntTypeList.append(new IntType());
    FuncType printIntFuncType = new FuncType(printIntTypeList, new VoidType());
    Symbol printIntSymbol = new Symbol("printInt", printIntFuncType);
    symbolScopes.get(0).put("printInt", printIntSymbol);

    //void printChar(int arg)
    TypeList printCharTypeList = new TypeList();
    printCharTypeList.append(new IntType());
    FuncType printCharFuncType = new FuncType(printCharTypeList, new VoidType());
    Symbol printCharSymbol = new Symbol("printChar", printCharFuncType);
    symbolScopes.get(0).put("printChar", printCharSymbol);

    //void println
    FuncType printlnFuncType = new FuncType(new TypeList(), new VoidType());
    Symbol printlnSymbol = new Symbol("println", printlnFuncType);
    symbolScopes.get(0).put("println", printlnSymbol);
  }

  boolean hasEncounteredError() {
    return encounteredError;
  }

  /**
   * Called to tell symbol table we entered a new scope.
   */

  void enter() {
    Map<String,Symbol> newScope = new HashMap<String,Symbol>();
    symbolScopes.add(0, newScope);
  }

  /**
   * Called to tell symbol table we are exiting a scope.
   */

  void exit() {
    symbolScopes.remove(0);
  }

  /**
   * Insert a symbol to the table at the most recent scope. if the name already exists in the
   * current scope that's a declareation error.
   */
  Symbol add(Position pos, String name, Type type) {
    if(symbolScopes.get(0).containsKey(name)){
      err.printf("DeclareSymbolError%s[%s already exists.]%n", pos, name);
      encounteredError = true;
      return new Symbol(name, "DeclareSymbolError");
    } else {
      Symbol newSymbol = new Symbol(name, type);
      symbolScopes.get(0).put(name, newSymbol);
      return newSymbol;
    }
  }

  /**
   * lookup a name in the SymbolTable, if the name not found in the table it shouold encounter an
   * error and return a symbol with ResolveSymbolError error. if the symbol is found then return it.
   */
  Symbol lookup(Position pos, String name) {
    var symbol = find(name);
    if (symbol == null) {
      err.printf("ResolveSymbolError%s[Could not find %s.]%n", pos, name);
      encounteredError = true;
      return new Symbol(name, "ResolveSymbolError");
    } else {
      return symbol;
    }
  }

  /**
   * Try to find a symbol in the table starting form the most recent scope.
   */
  private Symbol find(String name) {
    for(Map<String, Symbol> scope : symbolScopes){
      if(scope.containsKey(name)){
        return scope.get(name);
      }
    }
    return null;
  }
}
