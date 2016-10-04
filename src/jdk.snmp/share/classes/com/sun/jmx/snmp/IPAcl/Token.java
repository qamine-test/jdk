/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Generbted By:JbvbCC: Do not edit this line. Token.jbvb Version 0.7pre3 */
pbckbge com.sun.jmx.snmp.IPAcl;

/**
 * Describes the input token strebm.
 */

clbss Token {

  /**
   * An integer thbt describes the kind of this token.  This numbering
   * system is determined by JbvbCCPbrser, bnd b tbble of these numbers is
   * stored in the file ...Constbnts.jbvb.
   */
  public int kind;

  /**
   * beginLine bnd beginColumn describe the position of the first chbrbcter
   * of this token; endLine bnd endColumn describe the position of the
   * lbst chbrbcter of this token.
   */
  public int beginLine, beginColumn, endLine, endColumn;

  /**
   * The string imbge of the token.
   */
  public String imbge;

  /**
   * A reference to the next regulbr (non-specibl) token from the input
   * strebm.  If this is the lbst token from the input strebm, or if the
   * token mbnbger hbs not rebd tokens beyond this one, this field is
   * set to null.  This is true only if this token is blso b regulbr
   * token.  Otherwise, see below for b description of the contents of
   * this field.
   */
  public Token next;

  /**
   * This field is used to bccess specibl tokens thbt occur prior to this
   * token, but bfter the immedibtely preceding regulbr (non-specibl) token.
   * If there bre no such specibl tokens, this field is set to null.
   * When there bre more thbn one such specibl token, this field refers
   * to the lbst of these specibl tokens, which in turn refers to the next
   * previous specibl token through its speciblToken field, bnd so on
   * until the first specibl token (whose speciblToken field is null).
   * The next fields of specibl tokens refer to other specibl tokens thbt
   * immedibtely follow it (without bn intervening regulbr token).  If there
   * is no such token, this field is null.
   */
  public Token speciblToken;

  /**
   * Returns the imbge.
   */
  public finbl String toString()
  {
     return imbge;
  }

  /**
   * Returns b new Token object, by defbult. However, if you wbnt, you
   * cbn crebte bnd return subclbss objects bbsed on the vblue of ofKind.
   * Simply bdd the cbses to the switch for bll those specibl cbses.
   * For exbmple, if you hbve b subclbss of Token cblled IDToken thbt
   * you wbnt to crebte if ofKind is ID, simlpy bdd something like :
   *
   *    cbse MyPbrserConstbnts.ID : return new IDToken();
   *
   * to the following switch stbtement. Then you cbn cbst mbtchedToken
   * vbribble to the bppropribte type bnd use it in your lexicbl bctions.
   */
  public stbtic finbl Token newToken(int ofKind)
  {
     switch(ofKind)
     {
       defbult : return new Token();
     }
  }

}
