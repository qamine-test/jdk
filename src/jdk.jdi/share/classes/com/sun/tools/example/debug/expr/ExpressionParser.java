/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* Generbted By:JbvbCC: Do not edit this line. ExpressionPbrser.jbvb */
pbckbge com.sun.tools.exbmple.debug.expr;

import com.sun.jdi.*;
import jbvb.util.Stbck;
import jbvb.util.List;
import jbvb.util.ArrbyList;

public clbss ExpressionPbrser implements ExpressionPbrserConstbnts {

  Stbck<LVblue> stbck = new Stbck<>();
  VirtublMbchine vm = null;
  GetFrbme frbmeGetter = null;
  privbte stbtic GetFrbme lbstFrbmeGetter;
  privbte stbtic LVblue lbstLVblue;

  LVblue peek() {
    return stbck.peek();
  }

  LVblue pop() {
    return stbck.pop();
  }

  void push(LVblue lvbl) {
    stbck.push(lvbl);
  }

  public stbtic Vblue getMbssbgedVblue() throws PbrseException {
        return lbstLVblue.getMbssbgedVblue(lbstFrbmeGetter);
  }

  public interfbce GetFrbme {
        StbckFrbme get() throws IncompbtibleThrebdStbteException;
  }

  public stbtic Vblue evblubte(String expr, VirtublMbchine vm,
                               GetFrbme frbmeGetter) throws PbrseException,
                                            InvocbtionException,
                                            InvblidTypeException,
                                            ClbssNotLobdedException,
                                            IncompbtibleThrebdStbteException {
        // TODO StringBufferInputStrebm is deprecbted.
        jbvb.io.InputStrebm in = new jbvb.io.StringBufferInputStrebm(expr);
        ExpressionPbrser pbrser = new ExpressionPbrser(in);
        pbrser.vm = vm;
        pbrser.frbmeGetter = frbmeGetter;
        Vblue vblue = null;
        pbrser.Expression();
        lbstFrbmeGetter = frbmeGetter;
        lbstLVblue = pbrser.pop();
        return lbstLVblue.getVblue();
  }

  public stbtic void mbin(String brgs[]) {
    ExpressionPbrser pbrser;
    System.out.print("Jbvb Expression Pbrser:  ");
    if (brgs.length == 0) {
      System.out.println("Rebding from stbndbrd input . . .");
      pbrser = new ExpressionPbrser(System.in);
    } else if (brgs.length == 1) {
      System.out.println("Rebding from file " + brgs[0] + " . . .");
      try {
        pbrser = new ExpressionPbrser(new jbvb.io.FileInputStrebm(brgs[0]));
      } cbtch (jbvb.io.FileNotFoundException e) {
        System.out.println("Jbvb Pbrser Version 1.0.2:  File " +
                           brgs[0] + " not found.");
        return;
      }
    } else {
      System.out.println("Usbge is one of:");
      System.out.println("         jbvb ExpressionPbrser < inputfile");
      System.out.println("OR");
      System.out.println("         jbvb ExpressionPbrser inputfile");
      return;
    }
    try {
        pbrser.Expression();
        System.out.print("Jbvb Expression Pbrser:  ");
        System.out.println("Jbvb progrbm pbrsed successfully.");
    } cbtch (PbrseException e) {
        System.out.print("Jbvb Expression Pbrser:  ");
        System.out.println("Encountered errors during pbrse.");
    }
  }

/*****************************************
 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
 *****************************************/

/*
 * Type, nbme bnd expression syntbx follows.
 */
  finbl public void Type() throws PbrseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse BOOLEAN:
    cbse BYTE:
    cbse CHAR:
    cbse DOUBLE:
    cbse FLOAT:
    cbse INT:
    cbse LONG:
    cbse SHORT:
      PrimitiveType();
      brebk;
    cbse IDENTIFIER:
      Nbme();
      brebk;
    defbult:
      jj_lb1[0] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
    lbbel_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LBRACKET:
        ;
        brebk;
      defbult:
        jj_lb1[1] = jj_gen;
        brebk lbbel_1;
      }
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    }
  }

  finbl public void PrimitiveType() throws PbrseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse BOOLEAN:
      jj_consume_token(BOOLEAN);
      brebk;
    cbse CHAR:
      jj_consume_token(CHAR);
      brebk;
    cbse BYTE:
      jj_consume_token(BYTE);
      brebk;
    cbse SHORT:
      jj_consume_token(SHORT);
      brebk;
    cbse INT:
      jj_consume_token(INT);
      brebk;
    cbse LONG:
      jj_consume_token(LONG);
      brebk;
    cbse FLOAT:
      jj_consume_token(FLOAT);
      brebk;
    cbse DOUBLE:
      jj_consume_token(DOUBLE);
      brebk;
    defbult:
      jj_lb1[2] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public String Nbme() throws PbrseException {
    StringBuilder sb = new StringBuilder();
    jj_consume_token(IDENTIFIER);
                 sb.bppend(token);
    lbbel_2:
    while (true) {
      if (jj_2_1(2)) {
        ;
      } else {
        brebk lbbel_2;
      }
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                                    sb.bppend('.'); sb.bppend(token);
    }
          {if (true) return sb.toString();}
    throw new Error("Missing return stbtement in function");
  }

  finbl public void NbmeList() throws PbrseException {
    Nbme();
    lbbel_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse COMMA:
        ;
        brebk;
      defbult:
        jj_lb1[3] = jj_gen;
        brebk lbbel_3;
      }
      jj_consume_token(COMMA);
      Nbme();
    }
  }

/*
 * Expression syntbx follows.
 */
  finbl public void Expression() throws PbrseException {
    if (jj_2_2(2147483647)) {
      Assignment();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse FALSE:
      cbse NEW:
      cbse NULL:
      cbse SUPER:
      cbse THIS:
      cbse TRUE:
      cbse INTEGER_LITERAL:
      cbse FLOATING_POINT_LITERAL:
      cbse CHARACTER_LITERAL:
      cbse STRING_LITERAL:
      cbse IDENTIFIER:
      cbse LPAREN:
      cbse BANG:
      cbse TILDE:
      cbse INCR:
      cbse DECR:
      cbse PLUS:
      cbse MINUS:
        ConditionblExpression();
        brebk;
      defbult:
        jj_lb1[4] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    }
  }

  finbl public void Assignment() throws PbrseException {
    PrimbryExpression();
    AssignmentOperbtor();
    Expression();
          LVblue exprVbl = pop(); pop().setVblue(exprVbl); push(exprVbl);
  }

  finbl public void AssignmentOperbtor() throws PbrseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse ASSIGN:
      jj_consume_token(ASSIGN);
      brebk;
    cbse STARASSIGN:
      jj_consume_token(STARASSIGN);
      brebk;
    cbse SLASHASSIGN:
      jj_consume_token(SLASHASSIGN);
      brebk;
    cbse REMASSIGN:
      jj_consume_token(REMASSIGN);
      brebk;
    cbse PLUSASSIGN:
      jj_consume_token(PLUSASSIGN);
      brebk;
    cbse MINUSASSIGN:
      jj_consume_token(MINUSASSIGN);
      brebk;
    cbse LSHIFTASSIGN:
      jj_consume_token(LSHIFTASSIGN);
      brebk;
    cbse RSIGNEDSHIFTASSIGN:
      jj_consume_token(RSIGNEDSHIFTASSIGN);
      brebk;
    cbse RUNSIGNEDSHIFTASSIGN:
      jj_consume_token(RUNSIGNEDSHIFTASSIGN);
      brebk;
    cbse ANDASSIGN:
      jj_consume_token(ANDASSIGN);
      brebk;
    cbse XORASSIGN:
      jj_consume_token(XORASSIGN);
      brebk;
    cbse ORASSIGN:
      jj_consume_token(ORASSIGN);
      brebk;
    defbult:
      jj_lb1[5] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void ConditionblExpression() throws PbrseException {
    ConditionblOrExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse HOOK:
      jj_consume_token(HOOK);
      Expression();
      jj_consume_token(COLON);
      ConditionblExpression();
                  LVblue fblseBrbnch = pop(); LVblue trueBrbnch = pop();
                  Vblue cond = pop().interiorGetVblue();
                  if (cond instbnceof BoolebnVblue) {
                        push(((BoolebnVblue)cond).boolebnVblue()?
                                        trueBrbnch : fblseBrbnch);
                  } else {
                        {if (true) throw new PbrseException("Condition must be boolebn");}
                  }
      brebk;
    defbult:
      jj_lb1[6] = jj_gen;
      ;
    }
  }

  finbl public void ConditionblOrExpression() throws PbrseException {
    ConditionblAndExpression();
    lbbel_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse SC_OR:
        ;
        brebk;
      defbult:
        jj_lb1[7] = jj_gen;
        brebk lbbel_4;
      }
      jj_consume_token(SC_OR);
      ConditionblAndExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void ConditionblAndExpression() throws PbrseException {
    InclusiveOrExpression();
    lbbel_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse SC_AND:
        ;
        brebk;
      defbult:
        jj_lb1[8] = jj_gen;
        brebk lbbel_5;
      }
      jj_consume_token(SC_AND);
      InclusiveOrExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void InclusiveOrExpression() throws PbrseException {
    ExclusiveOrExpression();
    lbbel_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse BIT_OR:
        ;
        brebk;
      defbult:
        jj_lb1[9] = jj_gen;
        brebk lbbel_6;
      }
      jj_consume_token(BIT_OR);
      ExclusiveOrExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void ExclusiveOrExpression() throws PbrseException {
    AndExpression();
    lbbel_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse XOR:
        ;
        brebk;
      defbult:
        jj_lb1[10] = jj_gen;
        brebk lbbel_7;
      }
      jj_consume_token(XOR);
      AndExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void AndExpression() throws PbrseException {
    EqublityExpression();
    lbbel_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse BIT_AND:
        ;
        brebk;
      defbult:
        jj_lb1[11] = jj_gen;
        brebk lbbel_8;
      }
      jj_consume_token(BIT_AND);
      EqublityExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void EqublityExpression() throws PbrseException {
 Token tok;
    InstbnceOfExpression();
    lbbel_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse EQ:
      cbse NE:
        ;
        brebk;
      defbult:
        jj_lb1[12] = jj_gen;
        brebk lbbel_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse EQ:
        tok = jj_consume_token(EQ);
        brebk;
      cbse NE:
        tok = jj_consume_token(NE);
        brebk;
      defbult:
        jj_lb1[13] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      InstbnceOfExpression();
                  LVblue left = pop();
                  push( LVblue.boolebnOperbtion(vm, tok, pop(), left) );
    }
  }

  finbl public void InstbnceOfExpression() throws PbrseException {
    RelbtionblExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse INSTANCEOF:
      jj_consume_token(INSTANCEOF);
      Type();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
      brebk;
    defbult:
      jj_lb1[14] = jj_gen;
      ;
    }
  }

  finbl public void RelbtionblExpression() throws PbrseException {
 Token tok;
    ShiftExpression();
    lbbel_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse GT:
      cbse LT:
      cbse LE:
      cbse GE:
        ;
        brebk;
      defbult:
        jj_lb1[15] = jj_gen;
        brebk lbbel_10;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LT:
        tok = jj_consume_token(LT);
        brebk;
      cbse GT:
        tok = jj_consume_token(GT);
        brebk;
      cbse LE:
        tok = jj_consume_token(LE);
        brebk;
      cbse GE:
        tok = jj_consume_token(GE);
        brebk;
      defbult:
        jj_lb1[16] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      ShiftExpression();
                  LVblue left = pop();
                  push( LVblue.boolebnOperbtion(vm, tok, pop(), left) );
    }
  }

  finbl public void ShiftExpression() throws PbrseException {
    AdditiveExpression();
    lbbel_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LSHIFT:
      cbse RSIGNEDSHIFT:
      cbse RUNSIGNEDSHIFT:
        ;
        brebk;
      defbult:
        jj_lb1[17] = jj_gen;
        brebk lbbel_11;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LSHIFT:
        jj_consume_token(LSHIFT);
        brebk;
      cbse RSIGNEDSHIFT:
        jj_consume_token(RSIGNEDSHIFT);
        brebk;
      cbse RUNSIGNEDSHIFT:
        jj_consume_token(RUNSIGNEDSHIFT);
        brebk;
      defbult:
        jj_lb1[18] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      AdditiveExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
    }
  }

  finbl public void AdditiveExpression() throws PbrseException {
 Token tok;
    MultiplicbtiveExpression();
    lbbel_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse PLUS:
      cbse MINUS:
        ;
        brebk;
      defbult:
        jj_lb1[19] = jj_gen;
        brebk lbbel_12;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse PLUS:
        tok = jj_consume_token(PLUS);
        brebk;
      cbse MINUS:
        tok = jj_consume_token(MINUS);
        brebk;
      defbult:
        jj_lb1[20] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      MultiplicbtiveExpression();
                  LVblue left = pop();
                  push( LVblue.operbtion(vm, tok, pop(), left, frbmeGetter) );
    }
  }

  finbl public void MultiplicbtiveExpression() throws PbrseException {
 Token tok;
    UnbryExpression();
    lbbel_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse STAR:
      cbse SLASH:
      cbse REM:
        ;
        brebk;
      defbult:
        jj_lb1[21] = jj_gen;
        brebk lbbel_13;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse STAR:
        tok = jj_consume_token(STAR);
        brebk;
      cbse SLASH:
        tok = jj_consume_token(SLASH);
        brebk;
      cbse REM:
        tok = jj_consume_token(REM);
        brebk;
      defbult:
        jj_lb1[22] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      UnbryExpression();
                  LVblue left = pop();
                  push( LVblue.operbtion(vm, tok, pop(), left, frbmeGetter) );
    }
  }

  finbl public void UnbryExpression() throws PbrseException {
 Token tok;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse PLUS:
    cbse MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse PLUS:
        tok = jj_consume_token(PLUS);
        brebk;
      cbse MINUS:
        tok = jj_consume_token(MINUS);
        brebk;
      defbult:
        jj_lb1[23] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      UnbryExpression();
                  push( LVblue.operbtion(vm, tok, pop(), frbmeGetter) );
      brebk;
    cbse INCR:
      PreIncrementExpression();
      brebk;
    cbse DECR:
      PreDecrementExpression();
      brebk;
    cbse FALSE:
    cbse NEW:
    cbse NULL:
    cbse SUPER:
    cbse THIS:
    cbse TRUE:
    cbse INTEGER_LITERAL:
    cbse FLOATING_POINT_LITERAL:
    cbse CHARACTER_LITERAL:
    cbse STRING_LITERAL:
    cbse IDENTIFIER:
    cbse LPAREN:
    cbse BANG:
    cbse TILDE:
      UnbryExpressionNotPlusMinus();
      brebk;
    defbult:
      jj_lb1[24] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void PreIncrementExpression() throws PbrseException {
    jj_consume_token(INCR);
    PrimbryExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
  }

  finbl public void PreDecrementExpression() throws PbrseException {
    jj_consume_token(DECR);
    PrimbryExpression();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
  }

  finbl public void UnbryExpressionNotPlusMinus() throws PbrseException {
 Token tok;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse BANG:
    cbse TILDE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse TILDE:
        tok = jj_consume_token(TILDE);
        brebk;
      cbse BANG:
        tok = jj_consume_token(BANG);
        brebk;
      defbult:
        jj_lb1[25] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      UnbryExpression();
                  push( LVblue.operbtion(vm, tok, pop(), frbmeGetter) );
      brebk;
    defbult:
      jj_lb1[26] = jj_gen;
      if (jj_2_3(2147483647)) {
        CbstExpression();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse FALSE:
        cbse NEW:
        cbse NULL:
        cbse SUPER:
        cbse THIS:
        cbse TRUE:
        cbse INTEGER_LITERAL:
        cbse FLOATING_POINT_LITERAL:
        cbse CHARACTER_LITERAL:
        cbse STRING_LITERAL:
        cbse IDENTIFIER:
        cbse LPAREN:
          PostfixExpression();
          brebk;
        defbult:
          jj_lb1[27] = jj_gen;
          jj_consume_token(-1);
          throw new PbrseException();
        }
      }
    }
  }

// This production is to determine lookbhebd only.  The LOOKAHEAD specificbtions
// below bre not used, but they bre there just to indicbte thbt we know bbout
// this.
  finbl public void CbstLookbhebd() throws PbrseException {
    if (jj_2_4(2)) {
      jj_consume_token(LPAREN);
      PrimitiveType();
    } else if (jj_2_5(2147483647)) {
      jj_consume_token(LPAREN);
      Nbme();
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LPAREN:
        jj_consume_token(LPAREN);
        Nbme();
        jj_consume_token(RPAREN);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse TILDE:
          jj_consume_token(TILDE);
          brebk;
        cbse BANG:
          jj_consume_token(BANG);
          brebk;
        cbse LPAREN:
          jj_consume_token(LPAREN);
          brebk;
        cbse IDENTIFIER:
          jj_consume_token(IDENTIFIER);
          brebk;
        cbse THIS:
          jj_consume_token(THIS);
          brebk;
        cbse SUPER:
          jj_consume_token(SUPER);
          brebk;
        cbse NEW:
          jj_consume_token(NEW);
          brebk;
        cbse FALSE:
        cbse NULL:
        cbse TRUE:
        cbse INTEGER_LITERAL:
        cbse FLOATING_POINT_LITERAL:
        cbse CHARACTER_LITERAL:
        cbse STRING_LITERAL:
          Literbl();
          brebk;
        defbult:
          jj_lb1[28] = jj_gen;
          jj_consume_token(-1);
          throw new PbrseException();
        }
        brebk;
      defbult:
        jj_lb1[29] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    }
  }

  finbl public void PostfixExpression() throws PbrseException {
    PrimbryExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse INCR:
    cbse DECR:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse INCR:
        jj_consume_token(INCR);
        brebk;
      cbse DECR:
        jj_consume_token(DECR);
                          {if (true) throw new PbrseException("operbtion not yet supported");}
        brebk;
      defbult:
        jj_lb1[30] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
      brebk;
    defbult:
      jj_lb1[31] = jj_gen;
      ;
    }
  }

  finbl public void CbstExpression() throws PbrseException {
    if (jj_2_6(2)) {
      jj_consume_token(LPAREN);
      PrimitiveType();
      lbbel_14:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LBRACKET:
          ;
          brebk;
        defbult:
          jj_lb1[32] = jj_gen;
          brebk lbbel_14;
        }
        jj_consume_token(LBRACKET);
        jj_consume_token(RBRACKET);
      }
      jj_consume_token(RPAREN);
      UnbryExpression();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LPAREN:
        jj_consume_token(LPAREN);
        Nbme();
        lbbel_15:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          cbse LBRACKET:
            ;
            brebk;
          defbult:
            jj_lb1[33] = jj_gen;
            brebk lbbel_15;
          }
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
        }
        jj_consume_token(RPAREN);
        UnbryExpressionNotPlusMinus();
        brebk;
      defbult:
        jj_lb1[34] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    }
  }

  finbl public void PrimbryExpression() throws PbrseException {
    PrimbryPrefix();
    lbbel_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse LPAREN:
      cbse LBRACKET:
      cbse DOT:
        ;
        brebk;
      defbult:
        jj_lb1[35] = jj_gen;
        brebk lbbel_16;
      }
      PrimbrySuffix();
    }
  }

  finbl public void PrimbryPrefix() throws PbrseException {
 String nbme;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse FALSE:
    cbse NULL:
    cbse TRUE:
    cbse INTEGER_LITERAL:
    cbse FLOATING_POINT_LITERAL:
    cbse CHARACTER_LITERAL:
    cbse STRING_LITERAL:
      Literbl();
      brebk;
    cbse IDENTIFIER:
      nbme = Nbme();
                          push(LVblue.mbkeNbme(vm, frbmeGetter, nbme));
      brebk;
    cbse THIS:
      jj_consume_token(THIS);
                          push(LVblue.mbkeThisObject(vm, frbmeGetter, token));
      brebk;
    cbse SUPER:
      jj_consume_token(SUPER);
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                          {if (true) throw new PbrseException("operbtion not yet supported");}
      brebk;
    cbse LPAREN:
      jj_consume_token(LPAREN);
      Expression();
      jj_consume_token(RPAREN);
      brebk;
    cbse NEW:
      AllocbtionExpression();
      brebk;
    defbult:
      jj_lb1[36] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void PrimbrySuffix() throws PbrseException {
 List<Vblue> brgList;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse LBRACKET:
      jj_consume_token(LBRACKET);
      Expression();
      jj_consume_token(RBRACKET);
                          LVblue index = pop();
                          push(pop().brrbyElementLVblue(index));
      brebk;
    cbse DOT:
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
                          push(pop().memberLVblue(frbmeGetter, token.imbge));
      brebk;
    cbse LPAREN:
      brgList = Arguments();
                          peek().invokeWith(brgList);
      brebk;
    defbult:
      jj_lb1[37] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void Literbl() throws PbrseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse INTEGER_LITERAL:
      jj_consume_token(INTEGER_LITERAL);
                          push(LVblue.mbkeInteger(vm, token));
      brebk;
    cbse FLOATING_POINT_LITERAL:
      jj_consume_token(FLOATING_POINT_LITERAL);
                          push(LVblue.mbkeFlobt(vm, token));
      brebk;
    cbse CHARACTER_LITERAL:
      jj_consume_token(CHARACTER_LITERAL);
                          push(LVblue.mbkeChbrbcter(vm, token));
      brebk;
    cbse STRING_LITERAL:
      jj_consume_token(STRING_LITERAL);
                          push(LVblue.mbkeString(vm, token));
      brebk;
    cbse FALSE:
    cbse TRUE:
      BoolebnLiterbl();
                          push(LVblue.mbkeBoolebn(vm, token));
      brebk;
    cbse NULL:
      NullLiterbl();
                          push(LVblue.mbkeNull(vm, token));
      brebk;
    defbult:
      jj_lb1[38] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void BoolebnLiterbl() throws PbrseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse TRUE:
      jj_consume_token(TRUE);
      brebk;
    cbse FALSE:
      jj_consume_token(FALSE);
      brebk;
    defbult:
      jj_lb1[39] = jj_gen;
      jj_consume_token(-1);
      throw new PbrseException();
    }
  }

  finbl public void NullLiterbl() throws PbrseException {
    jj_consume_token(NULL);
  }

  finbl public List<Vblue> Arguments() throws PbrseException {
 List<Vblue> brgList = new ArrbyList<>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    cbse FALSE:
    cbse NEW:
    cbse NULL:
    cbse SUPER:
    cbse THIS:
    cbse TRUE:
    cbse INTEGER_LITERAL:
    cbse FLOATING_POINT_LITERAL:
    cbse CHARACTER_LITERAL:
    cbse STRING_LITERAL:
    cbse IDENTIFIER:
    cbse LPAREN:
    cbse BANG:
    cbse TILDE:
    cbse INCR:
    cbse DECR:
    cbse PLUS:
    cbse MINUS:
      ArgumentList(brgList);
      brebk;
    defbult:
      jj_lb1[40] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return brgList;}
    throw new Error("Missing return stbtement in function");
  }

  finbl public void ArgumentList(List<Vblue> brgList) throws PbrseException {
    Expression();
                brgList.bdd(pop().interiorGetVblue());
    lbbel_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse COMMA:
        ;
        brebk;
      defbult:
        jj_lb1[41] = jj_gen;
        brebk lbbel_17;
      }
      jj_consume_token(COMMA);
      Expression();
                      brgList.bdd(pop().interiorGetVblue());
    }
  }

  finbl public void AllocbtionExpression() throws PbrseException {
 List<Vblue> brgList; String clbssNbme;
    if (jj_2_7(2)) {
      jj_consume_token(NEW);
      PrimitiveType();
      ArrbyDimensions();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      cbse NEW:
        jj_consume_token(NEW);
        clbssNbme = Nbme();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        cbse LPAREN:
          brgList = Arguments();
                          push(LVblue.mbkeNewObject(vm, frbmeGetter, clbssNbme, brgList));
          brebk;
        cbse LBRACKET:
          ArrbyDimensions();
                          {if (true) throw new PbrseException("operbtion not yet supported");}
          brebk;
        defbult:
          jj_lb1[42] = jj_gen;
          jj_consume_token(-1);
          throw new PbrseException();
        }
        brebk;
      defbult:
        jj_lb1[43] = jj_gen;
        jj_consume_token(-1);
        throw new PbrseException();
      }
    }
  }

/*
 * The second LOOKAHEAD specificbtion below is to pbrse to PrimbrySuffix
 * if there is bn expression between the "[...]".
 */
  finbl public void ArrbyDimensions() throws PbrseException {
    lbbel_18:
    while (true) {
      jj_consume_token(LBRACKET);
      Expression();
      jj_consume_token(RBRACKET);
      if (jj_2_8(2)) {
        ;
      } else {
        brebk lbbel_18;
      }
    }
    lbbel_19:
    while (true) {
      if (jj_2_9(2)) {
        ;
      } else {
        brebk lbbel_19;
      }
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    }
  }

  privbte boolebn jj_2_1(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_1(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(0, xlb); }
  }

  privbte boolebn jj_2_2(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_2(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(1, xlb); }
  }

  privbte boolebn jj_2_3(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_3(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(2, xlb); }
  }

  privbte boolebn jj_2_4(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_4(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(3, xlb); }
  }

  privbte boolebn jj_2_5(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_5(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(4, xlb); }
  }

  privbte boolebn jj_2_6(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_6(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(5, xlb); }
  }

  privbte boolebn jj_2_7(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_7(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(6, xlb); }
  }

  privbte boolebn jj_2_8(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_8(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(7, xlb); }
  }

  privbte boolebn jj_2_9(int xlb) {
    jj_lb = xlb; jj_lbstpos = jj_scbnpos = token;
    try { return !jj_3_9(); }
    cbtch(LookbhebdSuccess ls) { return true; }
    finblly { jj_sbve(8, xlb); }
  }

  privbte boolebn jj_3R_94() {
    if (jj_scbn_token(DECR)) return true;
    if (jj_3R_20()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_86() {
    if (jj_3R_24()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_93() {
    if (jj_scbn_token(INCR)) return true;
    if (jj_3R_20()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_91() {
    if (jj_3R_95()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_23() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(10)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(15)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(12)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(45)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(34)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(36)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(27)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(21)) return true;
    }
    }
    }
    }
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_90() {
    if (jj_3R_94()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_89() {
    if (jj_3R_93()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_85() {
    if (jj_3R_23()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_60() {
    if (jj_3R_58()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_88() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(94)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(95)) return true;
    }
    if (jj_3R_83()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_83() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_88()) {
    jj_scbnpos = xsp;
    if (jj_3R_89()) {
    jj_scbnpos = xsp;
    if (jj_3R_90()) {
    jj_scbnpos = xsp;
    if (jj_3R_91()) return true;
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_82() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_85()) {
    jj_scbnpos = xsp;
    if (jj_3R_86()) return true;
    }
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_87()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_59() {
    if (jj_3R_55()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_96() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(96)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(97)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(101)) return true;
    }
    }
    if (jj_3R_83()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_80() {
    if (jj_3R_83()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_96()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_92() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(94)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(95)) return true;
    }
    if (jj_3R_80()) return true;
    return fblse;
  }

  privbte boolebn jj_3_8() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_3R_25()) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_58() {
    Token xsp;
    if (jj_3_8()) return true;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3_8()) { jj_scbnpos = xsp; brebk; }
    }
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3_9()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_84() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(102)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(103)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(104)) return true;
    }
    }
    if (jj_3R_78()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_78() {
    if (jj_3R_80()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_92()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_54() {
    if (jj_scbn_token(NEW)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_59()) {
    jj_scbnpos = xsp;
    if (jj_3R_60()) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3R_76() {
    if (jj_3R_78()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_84()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_81() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(81)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(80)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(87)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(88)) return true;
    }
    }
    }
    if (jj_3R_76()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_43() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3_7()) {
    jj_scbnpos = xsp;
    if (jj_3R_54()) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3_7() {
    if (jj_scbn_token(NEW)) return true;
    if (jj_3R_23()) return true;
    if (jj_3R_58()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_67() {
    if (jj_scbn_token(COMMA)) return true;
    if (jj_3R_25()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_64() {
    if (jj_3R_25()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_67()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_61() {
    if (jj_3R_64()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_79() {
    if (jj_scbn_token(INSTANCEOF)) return true;
    if (jj_3R_82()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_74() {
    if (jj_3R_76()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_81()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_55() {
    if (jj_scbn_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_61()) jj_scbnpos = xsp;
    if (jj_scbn_token(RPAREN)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_72() {
    if (jj_3R_74()) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_79()) jj_scbnpos = xsp;
    return fblse;
  }

  privbte boolebn jj_3R_77() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(86)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(89)) return true;
    }
    if (jj_3R_72()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_75() {
    if (jj_scbn_token(BIT_AND)) return true;
    if (jj_3R_70()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_70() {
    if (jj_3R_72()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_77()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_57() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(54)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(24)) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3R_53() {
    if (jj_scbn_token(39)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_39() {
    if (jj_3R_42()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_68() {
    if (jj_3R_70()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_75()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_73() {
    if (jj_scbn_token(XOR)) return true;
    if (jj_3R_68()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_52() {
    if (jj_3R_57()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_51() {
    if (jj_scbn_token(STRING_LITERAL)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_50() {
    if (jj_scbn_token(CHARACTER_LITERAL)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_65() {
    if (jj_3R_68()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_73()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_71() {
    if (jj_scbn_token(BIT_OR)) return true;
    if (jj_3R_65()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_49() {
    if (jj_scbn_token(FLOATING_POINT_LITERAL)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_42() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_48()) {
    jj_scbnpos = xsp;
    if (jj_3R_49()) {
    jj_scbnpos = xsp;
    if (jj_3R_50()) {
    jj_scbnpos = xsp;
    if (jj_3R_51()) {
    jj_scbnpos = xsp;
    if (jj_3R_52()) {
    jj_scbnpos = xsp;
    if (jj_3R_53()) return true;
    }
    }
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_48() {
    if (jj_scbn_token(INTEGER_LITERAL)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_62() {
    if (jj_3R_65()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_71()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_69() {
    if (jj_scbn_token(SC_AND)) return true;
    if (jj_3R_62()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_46() {
    if (jj_3R_55()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_45() {
    if (jj_scbn_token(DOT)) return true;
    if (jj_scbn_token(IDENTIFIER)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_56() {
    if (jj_3R_62()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_69()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_66() {
    if (jj_scbn_token(SC_OR)) return true;
    if (jj_3R_56()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_44() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_3R_25()) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_38() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_44()) {
    jj_scbnpos = xsp;
    if (jj_3R_45()) {
    jj_scbnpos = xsp;
    if (jj_3R_46()) return true;
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_37() {
    if (jj_3R_43()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_27() {
    if (jj_3R_38()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_36() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_25()) return true;
    if (jj_scbn_token(RPAREN)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_47() {
    if (jj_3R_56()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_66()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_104() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_35() {
    if (jj_scbn_token(SUPER)) return true;
    if (jj_scbn_token(DOT)) return true;
    if (jj_scbn_token(IDENTIFIER)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_34() {
    if (jj_scbn_token(THIS)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_63() {
    if (jj_scbn_token(HOOK)) return true;
    if (jj_3R_25()) return true;
    if (jj_scbn_token(COLON)) return true;
    if (jj_3R_41()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_33() {
    if (jj_3R_24()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_105() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_32() {
    if (jj_3R_42()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_26() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_32()) {
    jj_scbnpos = xsp;
    if (jj_3R_33()) {
    jj_scbnpos = xsp;
    if (jj_3R_34()) {
    jj_scbnpos = xsp;
    if (jj_3R_35()) {
    jj_scbnpos = xsp;
    if (jj_3R_36()) {
    jj_scbnpos = xsp;
    if (jj_3R_37()) return true;
    }
    }
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_20() {
    if (jj_3R_26()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_27()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  privbte boolebn jj_3R_41() {
    if (jj_3R_47()) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_63()) jj_scbnpos = xsp;
    return fblse;
  }

  privbte boolebn jj_3R_106() {
    if (jj_scbn_token(DECR)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_102() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_105()) { jj_scbnpos = xsp; brebk; }
    }
    if (jj_scbn_token(RPAREN)) return true;
    if (jj_3R_95()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_21() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(79)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(107)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(108)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(112)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(105)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(106)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(113)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(114)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(115)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(109)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(111)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(110)) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_103() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(92)) {
    jj_scbnpos = xsp;
    if (jj_3R_106()) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3R_100() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3_6()) {
    jj_scbnpos = xsp;
    if (jj_3R_102()) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3_6() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_23()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3R_104()) { jj_scbnpos = xsp; brebk; }
    }
    if (jj_scbn_token(RPAREN)) return true;
    if (jj_3R_83()) return true;
    return fblse;
  }

  privbte boolebn jj_3_2() {
    if (jj_3R_20()) return true;
    if (jj_3R_21()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_40() {
    if (jj_3R_20()) return true;
    if (jj_3R_21()) return true;
    if (jj_3R_25()) return true;
    return fblse;
  }

  privbte boolebn jj_3_5() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scbn_token(LBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_101() {
    if (jj_3R_20()) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_103()) jj_scbnpos = xsp;
    return fblse;
  }

  privbte boolebn jj_3R_31() {
    if (jj_3R_41()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_30() {
    if (jj_3R_40()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_25() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_30()) {
    jj_scbnpos = xsp;
    if (jj_3R_31()) return true;
    }
    return fblse;
  }

  privbte boolebn jj_3R_29() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scbn_token(RPAREN)) return true;
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(83)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(82)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(70)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(67)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(50)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(47)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(38)) {
    jj_scbnpos = xsp;
    if (jj_3R_39()) return true;
    }
    }
    }
    }
    }
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_28() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3_4() {
    if (jj_scbn_token(LPAREN)) return true;
    if (jj_3R_23()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_22() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3_4()) {
    jj_scbnpos = xsp;
    if (jj_3R_28()) {
    jj_scbnpos = xsp;
    if (jj_3R_29()) return true;
    }
    }
    return fblse;
  }

  privbte boolebn jj_3_3() {
    if (jj_3R_22()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_99() {
    if (jj_3R_101()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_87() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_98() {
    if (jj_3R_100()) return true;
    return fblse;
  }

  privbte boolebn jj_3_1() {
    if (jj_scbn_token(DOT)) return true;
    if (jj_scbn_token(IDENTIFIER)) return true;
    return fblse;
  }

  privbte boolebn jj_3_9() {
    if (jj_scbn_token(LBRACKET)) return true;
    if (jj_scbn_token(RBRACKET)) return true;
    return fblse;
  }

  privbte boolebn jj_3R_97() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_scbn_token(83)) {
    jj_scbnpos = xsp;
    if (jj_scbn_token(82)) return true;
    }
    if (jj_3R_83()) return true;
    return fblse;
  }

  privbte boolebn jj_3R_95() {
    Token xsp;
    xsp = jj_scbnpos;
    if (jj_3R_97()) {
    jj_scbnpos = xsp;
    if (jj_3R_98()) {
    jj_scbnpos = xsp;
    if (jj_3R_99()) return true;
    }
    }
    return fblse;
  }

  privbte boolebn jj_3R_24() {
    if (jj_scbn_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scbnpos;
      if (jj_3_1()) { jj_scbnpos = xsp; brebk; }
    }
    return fblse;
  }

  /** Generbted Token Mbnbger. */
  public ExpressionPbrserTokenMbnbger token_source;
  JbvbChbrStrebm jj_input_strebm;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  privbte int jj_ntk;
  privbte Token jj_scbnpos, jj_lbstpos;
  privbte int jj_lb;
  privbte int jj_gen;
  finbl privbte int[] jj_lb1 = new int[44];
  stbtic privbte int[] jj_lb1_0;
  stbtic privbte int[] jj_lb1_1;
  stbtic privbte int[] jj_lb1_2;
  stbtic privbte int[] jj_lb1_3;
  stbtic {
      jj_lb1_init_0();
      jj_lb1_init_1();
      jj_lb1_init_2();
      jj_lb1_init_3();
   }
   privbte stbtic void jj_lb1_init_0() {
      jj_lb1_0 = new int[] {0x8209400,0x0,0x8209400,0x0,0x1000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1000000,0x0,0x0,0x1000000,0x1000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1000000,0x0,0x1000000,0x1000000,0x1000000,0x0,0x0,0x0,};
   }
   privbte stbtic void jj_lb1_init_1() {
      jj_lb1_1 = new int[] {0x2014,0x0,0x2014,0x0,0x884480c0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x884480c0,0x0,0x0,0x884480c0,0x884480c0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x884480c0,0x0,0x88400080,0x400000,0x884480c0,0x0,0x0,0x40,};
   }
   privbte stbtic void jj_lb1_init_2() {
      jj_lb1_2 = new int[] {0x8,0x400,0x0,0x2000,0xf00c004e,0x8000,0x100000,0x4000000,0x8000000,0x0,0x0,0x0,0x2400000,0x2400000,0x0,0x1830000,0x1830000,0x0,0x0,0xc0000000,0xc0000000,0x0,0x0,0xc0000000,0xf00c004e,0xc0000,0xc0000,0x4e,0xc004e,0x40,0x30000000,0x30000000,0x400,0x400,0x40,0x4440,0x4e,0x4440,0x6,0x0,0xf00c004e,0x2000,0x440,0x0,};
   }
   privbte stbtic void jj_lb1_init_3() {
      jj_lb1_3 = new int[] {0x0,0x0,0x0,0x0,0x0,0xffe00,0x0,0x0,0x0,0x8,0x10,0x4,0x0,0x0,0x0,0x0,0x0,0x1c0,0x1c0,0x0,0x0,0x23,0x23,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  finbl privbte JJCblls[] jj_2_rtns = new JJCblls[9];
  privbte boolebn jj_rescbn = fblse;
  privbte int jj_gc = 0;

  /** Constructor with InputStrebm. */
  public ExpressionPbrser(jbvb.io.InputStrebm strebm) {
     this(strebm, null);
  }
  /** Constructor with InputStrebm bnd supplied encoding */
  public ExpressionPbrser(jbvb.io.InputStrebm strebm, String encoding) {
    try { jj_input_strebm = new JbvbChbrStrebm(strebm, encoding, 1, 1); } cbtch(jbvb.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ExpressionPbrserTokenMbnbger(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  /** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm strebm) {
     ReInit(strebm, null);
  }
  /** Reinitiblise. */
  public void ReInit(jbvb.io.InputStrebm strebm, String encoding) {
    try { jj_input_strebm.ReInit(strebm, encoding, 1, 1); } cbtch(jbvb.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  /** Constructor. */
  public ExpressionPbrser(jbvb.io.Rebder strebm) {
    jj_input_strebm = new JbvbChbrStrebm(strebm, 1, 1);
    token_source = new ExpressionPbrserTokenMbnbger(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  /** Reinitiblise. */
  public void ReInit(jbvb.io.Rebder strebm) {
    jj_input_strebm.ReInit(strebm, 1, 1);
    token_source.ReInit(jj_input_strebm);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  /** Constructor with generbted Token Mbnbger. */
  public ExpressionPbrser(ExpressionPbrserTokenMbnbger tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  /** Reinitiblise. */
  public void ReInit(ExpressionPbrserTokenMbnbger tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_lb1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCblls();
  }

  privbte Token jj_consume_token(int kind) throws PbrseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCblls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generbtePbrseException();
  }

  @SuppressWbrnings("seribl") // JDK implementbtion clbss
  stbtic privbte finbl clbss LookbhebdSuccess extends jbvb.lbng.Error { }
  finbl privbte LookbhebdSuccess jj_ls = new LookbhebdSuccess();
  privbte boolebn jj_scbn_token(int kind) {
    if (jj_scbnpos == jj_lbstpos) {
      jj_lb--;
      if (jj_scbnpos.next == null) {
        jj_lbstpos = jj_scbnpos = jj_scbnpos.next = token_source.getNextToken();
      } else {
        jj_lbstpos = jj_scbnpos = jj_scbnpos.next;
      }
    } else {
      jj_scbnpos = jj_scbnpos.next;
    }
    if (jj_rescbn) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scbnpos) { i++; tok = tok.next; }
      if (tok != null) jj_bdd_error_token(kind, i);
    }
    if (jj_scbnpos.kind != kind) return true;
    if (jj_lb == 0 && jj_scbnpos == jj_lbstpos) throw jj_ls;
    return fblse;
  }


/** Get the next Token. */
  finbl public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  finbl public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  privbte int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  privbte jbvb.util.List<int[]> jj_expentries = new jbvb.util.ArrbyList<int[]>();
  privbte int[] jj_expentry;
  privbte int jj_kind = -1;
  privbte int[] jj_lbsttokens = new int[100];
  privbte int jj_endpos;

  privbte void jj_bdd_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lbsttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lbsttokens[i];
      }
      jj_entries_loop: for (jbvb.util.Iterbtor<?> it = jj_expentries.iterbtor(); it.hbsNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.bdd(jj_expentry);
          brebk jj_entries_loop;
        }
      }
      if (pos != 0) jj_lbsttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generbte PbrseException. */
  public PbrseException generbtePbrseException() {
    jj_expentries.clebr();
    boolebn[] lb1tokens = new boolebn[116];
    if (jj_kind >= 0) {
      lb1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 44; i++) {
      if (jj_lb1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_lb1_0[i] & (1<<j)) != 0) {
            lb1tokens[j] = true;
          }
          if ((jj_lb1_1[i] & (1<<j)) != 0) {
            lb1tokens[32+j] = true;
          }
          if ((jj_lb1_2[i] & (1<<j)) != 0) {
            lb1tokens[64+j] = true;
          }
          if ((jj_lb1_3[i] & (1<<j)) != 0) {
            lb1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 116; i++) {
      if (lb1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.bdd(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescbn_token();
    jj_bdd_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new PbrseException(token, exptokseq, tokenImbge);
  }

  /** Enbble trbcing. */
  finbl public void enbble_trbcing() {
  }

  /** Disbble trbcing. */
  finbl public void disbble_trbcing() {
  }

  privbte void jj_rescbn_token() {
    jj_rescbn = true;
    for (int i = 0; i < 9; i++) {
    try {
      JJCblls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_lb = p.brg; jj_lbstpos = jj_scbnpos = p.first;
          switch (i) {
            cbse 0: jj_3_1(); brebk;
            cbse 1: jj_3_2(); brebk;
            cbse 2: jj_3_3(); brebk;
            cbse 3: jj_3_4(); brebk;
            cbse 4: jj_3_5(); brebk;
            cbse 5: jj_3_6(); brebk;
            cbse 6: jj_3_7(); brebk;
            cbse 7: jj_3_8(); brebk;
            cbse 8: jj_3_9(); brebk;
          }
        }
        p = p.next;
      } while (p != null);
      } cbtch(LookbhebdSuccess ls) { }
    }
    jj_rescbn = fblse;
  }

  privbte void jj_sbve(int index, int xlb) {
    JJCblls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCblls(); brebk; }
      p = p.next;
    }
    p.gen = jj_gen + xlb - jj_lb; p.first = token; p.brg = xlb;
  }

  stbtic finbl clbss JJCblls {
    int gen;
    Token first;
    int brg;
    JJCblls next;
  }

}
