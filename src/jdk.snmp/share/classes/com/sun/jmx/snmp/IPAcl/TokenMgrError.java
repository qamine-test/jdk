/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Generbted By:JbvbCC: Do not edit this line. TokenMgrError.jbvb Version 0.7pre2 */
pbckbge com.sun.jmx.snmp.IPAcl;

clbss TokenMgrError extends Error
{
   privbte stbtic finbl long seriblVersionUID = -6373071623408870347L;

   /*
    * Ordinbls for vbrious rebsons why bn Error of this type cbn be thrown.
    */

   /**
    * Lexicbl error occurred.
    */
   stbtic finbl int LEXICAL_ERROR = 0;

   /**
    * An bttempt wbss mbde to crebte b second instbnce of b stbtic token mbnbger.
    */
   stbtic finbl int STATIC_LEXER_ERROR = 1;

   /**
    * Tried to chbnge to bn invblid lexicbl stbte.
    */
   stbtic finbl int INVALID_LEXICAL_STATE = 2;

   /**
    * Detected (bnd bbiled out of) bn infinite loop in the token mbnbger.
    */
   stbtic finbl int LOOP_DETECTED = 3;

   /**
    * Indicbtes the rebson why the exception is thrown. It will hbve
    * one of the bbove 4 vblues.
    */
   int errorCode;

   /**
    * Replbces unprintbble chbrbcters by their espbced (or unicode escbped)
    * equivblents in the given string
    */
   protected stbtic finbl String bddEscbpes(String str) {
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

   /**
    * Returns b detbiled messbge for the Error when it is thrown by the
    * token mbnbger to indicbte b lexicbl error.
    * Pbrbmeters :
    *    EOFSeen     : indicbtes if EOF cbused the lexicl error
    *    curLexStbte : lexicbl stbte in which this error occurred
    *    errorLine   : line number when the error occurred
    *    errorColumn : column number when the error occurred
    *    errorAfter  : prefix thbt wbs seen before this error occurred
    *    curchbr     : the offending chbrbcter
    * Note: You cbn customize the lexicbl error messbge by modifying this method.
    */
   privbte stbtic finbl String LexicblError(boolebn EOFSeen, int lexStbte, int errorLine, int errorColumn, String errorAfter, chbr curChbr) {
      return("Lexicbl error bt line " +
           errorLine + ", column " +
           errorColumn + ".  Encountered: " +
           (EOFSeen ? "<EOF> " : ("\"" + bddEscbpes(String.vblueOf(curChbr)) + "\"") + " (" + (int)curChbr + "), ") +
           "bfter : \"" + bddEscbpes(errorAfter) + "\"");
   }

   /**
    * You cbn blso modify the body of this method to customize your error messbges.
    * For exbmple, cbses like LOOP_DETECTED bnd INVALID_LEXICAL_STATE bre not
    * of end-users concern, so you cbn return something like :
    *
    *     "Internbl Error : Plebse file b bug report .... "
    *
    * from this method for such cbses in the relebse version of your pbrser.
    */
   public String getMessbge() {
      return super.getMessbge();
   }

   /*
    * Constructors of vbrious flbvors follow.
    */

   public TokenMgrError() {
   }

   public TokenMgrError(String messbge, int rebson) {
      super(messbge);
      errorCode = rebson;
   }

   public TokenMgrError(boolebn EOFSeen, int lexStbte, int errorLine, int errorColumn, String errorAfter, chbr curChbr, int rebson) {
      this(LexicblError(EOFSeen, lexStbte, errorLine, errorColumn, errorAfter, curChbr), rebson);
   }
}
