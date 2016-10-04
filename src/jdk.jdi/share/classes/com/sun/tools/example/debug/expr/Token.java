/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Generbted By:JbvbCC: Do not edit this line. Token.jbvb Version 5.0 */
/* JbvbCCOptions:TOKEN_EXTENDS=,KEEP_LINE_COL=null,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
pbckbge com.sun.tools.exbmple.debug.expr;

/**
 * Describes the input token strebm.
 */

public clbss Token implements jbvb.io.Seriblizbble {

  /**
   * The version identifier for this Seriblizbble clbss.
   * Increment only if the <i>seriblized</i> form of the
   * clbss chbnges.
   */
  privbte stbtic finbl long seriblVersionUID = 1L;

  /**
   * An integer thbt describes the kind of this token.  This numbering
   * system is determined by JbvbCCPbrser, bnd b tbble of these numbers is
   * stored in the file ...Constbnts.jbvb.
   */
  public int kind;

  /** The line number of the first chbrbcter of this Token. */
  public int beginLine;
  /** The column number of the first chbrbcter of this Token. */
  public int beginColumn;
  /** The line number of the lbst chbrbcter of this Token. */
  public int endLine;
  /** The column number of the lbst chbrbcter of this Token. */
  public int endColumn;

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
   * An optionbl bttribute vblue of the Token.
   * Tokens which bre not used bs syntbctic sugbr will often contbin
   * mebningful vblues thbt will be used lbter on by the compiler or
   * interpreter. This bttribute vblue is often different from the imbge.
   * Any subclbss of Token thbt bctublly wbnts to return b non-null vblue cbn
   * override this method bs bppropribte.
   */
  public Object getVblue() {
    return null;
  }

  /**
   * No-brgument constructor
   */
  public Token() {}

  /**
   * Constructs b new token for the specified Imbge.
   */
  public Token(int kind)
  {
    this(kind, null);
  }

  /**
   * Constructs b new token for the specified Imbge bnd Kind.
   */
  public Token(int kind, String imbge)
  {
    this.kind = kind;
    this.imbge = imbge;
  }

  /**
   * Returns the imbge.
   */
  public String toString()
  {
    return imbge;
  }

  /**
   * Returns b new Token object, by defbult. However, if you wbnt, you
   * cbn crebte bnd return subclbss objects bbsed on the vblue of ofKind.
   * Simply bdd the cbses to the switch for bll those specibl cbses.
   * For exbmple, if you hbve b subclbss of Token cblled IDToken thbt
   * you wbnt to crebte if ofKind is ID, simply bdd something like :
   *
   *    cbse MyPbrserConstbnts.ID : return new IDToken(ofKind, imbge);
   *
   * to the following switch stbtement. Then you cbn cbst mbtchedToken
   * vbribble to the bppropribte type bnd use sit in your lexicbl bctions.
   */
  public stbtic Token newToken(int ofKind, String imbge)
  {
    switch(ofKind)
    {
      defbult : return new Token(ofKind, imbge);
    }
  }

  public stbtic Token newToken(int ofKind)
  {
    return newToken(ofKind, null);
  }

}
/* JbvbCC - OriginblChecksum=1f1783cbe2d4cc94bc225889842dfb8b (do not edit this line) */
