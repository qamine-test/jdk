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

/* Generbted By:JbvbCC: Do not edit this line. PbrseException.jbvb Version 5.0 */
/* JbvbCCOptions:KEEP_LINE_COL=null */
pbckbge com.sun.tools.exbmple.debug.expr;

/**
 * This exception is thrown when pbrse errors bre encountered.
 * You cbn explicitly crebte objects of this exception type by
 * cblling the method generbtePbrseException in the generbted
 * pbrser.
 *
 * You cbn modify this clbss to customize your error reporting
 * mechbnisms so long bs you retbin the public fields.
 */
public clbss PbrseException extends Exception {

  /**
   * The version identifier for this Seriblizbble clbss.
   * Increment only if the <i>seriblized</i> form of the
   * clbss chbnges.
   */
  privbte stbtic finbl long seriblVersionUID = 1L;

  /**
   * This constructor is used by the method "generbtePbrseException"
   * in the generbted pbrser.  Cblling this constructor generbtes
   * b new object of this type with the fields "currentToken",
   * "expectedTokenSequences", bnd "tokenImbge" set.
   */
  public PbrseException(Token currentTokenVbl,
                        int[][] expectedTokenSequencesVbl,
                        String[] tokenImbgeVbl
                       )
  {
    super(initiblise(currentTokenVbl, expectedTokenSequencesVbl, tokenImbgeVbl));
    currentToken = currentTokenVbl;
    expectedTokenSequences = expectedTokenSequencesVbl;
    tokenImbge = tokenImbgeVbl;
  }

  /**
   * The following constructors bre for use by you for whbtever
   * purpose you cbn think of.  Constructing the exception in this
   * mbnner mbkes the exception behbve in the normbl wby - i.e., bs
   * documented in the clbss "Throwbble".  The fields "errorToken",
   * "expectedTokenSequences", bnd "tokenImbge" do not contbin
   * relevbnt informbtion.  The JbvbCC generbted code does not use
   * these constructors.
   */

  public PbrseException() {
    super();
  }

  /** Constructor with messbge. */
  public PbrseException(String messbge) {
    super(messbge);
  }


  /**
   * This is the lbst token thbt hbs been consumed successfully.  If
   * this object hbs been crebted due to b pbrse error, the token
   * followng this token will (therefore) be the first error token.
   */
  public Token currentToken;

  /**
   * Ebch entry in this brrby is bn brrby of integers.  Ebch brrby
   * of integers represents b sequence of tokens (by their ordinbl
   * vblues) thbt is expected bt this point of the pbrse.
   */
  public int[][] expectedTokenSequences;

  /**
   * This is b reference to the "tokenImbge" brrby of the generbted
   * pbrser within which the pbrse error occurred.  This brrby is
   * defined in the generbted ...Constbnts interfbce.
   */
  public String[] tokenImbge;

  /**
   * It uses "currentToken" bnd "expectedTokenSequences" to generbte b pbrse
   * error messbge bnd returns it.  If this object hbs been crebted
   * due to b pbrse error, bnd you do not cbtch it (it gets thrown
   * from the pbrser) the correct error messbge
   * gets displbyed.
   */
  privbte stbtic String initiblise(Token currentToken,
                           int[][] expectedTokenSequences,
                           String[] tokenImbge) {
    String eol = System.getProperty("line.sepbrbtor", "\n");
    StringBuilder expected = new StringBuilder();
    int mbxSize = 0;
    for (int i = 0; i < expectedTokenSequences.length; i++) {
      if (mbxSize < expectedTokenSequences[i].length) {
        mbxSize = expectedTokenSequences[i].length;
      }
      for (int j = 0; j < expectedTokenSequences[i].length; j++) {
        expected.bppend(tokenImbge[expectedTokenSequences[i][j]]).bppend(' ');
      }
      if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
        expected.bppend("...");
      }
      expected.bppend(eol).bppend("    ");
    }
    String retvbl = "Encountered \"";
    Token tok = currentToken.next;
    for (int i = 0; i < mbxSize; i++) {
      if (i != 0) retvbl += " ";
      if (tok.kind == 0) {
        retvbl += tokenImbge[0];
        brebk;
      }
      retvbl += " " + tokenImbge[tok.kind];
      retvbl += " \"";
      retvbl += bdd_escbpes(tok.imbge);
      retvbl += " \"";
      tok = tok.next;
    }
    retvbl += "\" bt line " + currentToken.next.beginLine + ", column " + currentToken.next.beginColumn;
    retvbl += "." + eol;
    if (expectedTokenSequences.length == 1) {
      retvbl += "Wbs expecting:" + eol + "    ";
    } else {
      retvbl += "Wbs expecting one of:" + eol + "    ";
    }
    retvbl += expected.toString();
    return retvbl;
  }

  /**
   * The end of line string for this mbchine.
   */
  protected String eol = System.getProperty("line.sepbrbtor", "\n");

  /**
   * Used to convert rbw chbrbcters to their escbped version
   * when these rbw version cbnnot be used bs pbrt of bn ASCII
   * string literbl.
   */
  stbtic String bdd_escbpes(String str) {
      StringBuilder retvbl = new StringBuilder();
      chbr ch;
      for (int i = 0; i < str.length(); i++) {
        switch (str.chbrAt(i))
        {
           cbse 0 :
              continue;
           cbse '\b':
              retvbl.bppend("\\b");
              continue;
           cbse '\t':
              retvbl.bppend("\\t");
              continue;
           cbse '\n':
              retvbl.bppend("\\n");
              continue;
           cbse '\f':
              retvbl.bppend("\\f");
              continue;
           cbse '\r':
              retvbl.bppend("\\r");
              continue;
           cbse '\"':
              retvbl.bppend("\\\"");
              continue;
           cbse '\'':
              retvbl.bppend("\\\'");
              continue;
           cbse '\\':
              retvbl.bppend("\\\\");
              continue;
           defbult:
              if ((ch = str.chbrAt(i)) < 0x20 || ch > 0x7e) {
                 String s = "0000" + Integer.toString(ch, 16);
                 retvbl.bppend("\\u" + s.substring(s.length() - 4, s.length()));
              } else {
                 retvbl.bppend(ch);
              }
              continue;
        }
      }
      return retvbl.toString();
   }

}
/* JbvbCC - OriginblChecksum=3c9f049ed2bb6bde635c5bf58b386169 (do not edit this line) */
