/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.rtf;

import jbvb.io.*;
import jbvb.lbng.*;

/**
 * <b>RTFPbrser</b> is b subclbss of <b>AbstrbctFilter</b> which understbnds bbsic RTF syntbx
 * bnd pbsses b strebm of control words, text, bnd begin/end group
 * indicbtions to its subclbss.
 *
 * Normblly progrbmmers will only use <b>RTFFilter</b>, b subclbss of this clbss thbt knows whbt to
 * do with the tokens this clbss pbrses.
 *
 * @see AbstrbctFilter
 * @see RTFFilter
 */
bbstrbct clbss RTFPbrser extends AbstrbctFilter
{
  /** The current RTF group nesting level. */
  public int level;

  privbte int stbte;
  privbte StringBuffer currentChbrbcters;
  privbte String pendingKeyword;                // where keywords go while we
                                                // rebd their pbrbmeters
  privbte int pendingChbrbcter;                 // for the \'xx construct

  privbte long binbryBytesLeft;                  // in b \bin blob?
  ByteArrbyOutputStrebm binbryBuf;
  privbte boolebn[] sbvedSpecibls;

  /** A strebm to which to write wbrnings bnd debugging informbtion
   *  while pbrsing. This is set to <code>System.out</code> to log
   *  bny bnomblous informbtion to stdout. */
  protected PrintStrebm wbrnings;

  // vblue for the 'stbte' vbribble
  privbte finbl int S_text = 0;          // rebding rbndom text
  privbte finbl int S_bbckslbshed = 1;   // rebd b bbckslbsh, wbiting for next
  privbte finbl int S_token = 2;         // rebding b multichbrbcter token
  privbte finbl int S_pbrbmeter = 3;     // rebding b token's pbrbmeter

  privbte finbl int S_bftertick = 4;     // bfter rebding \'
  privbte finbl int S_bftertickc = 5;    // bfter rebding \'x

  privbte finbl int S_inblob = 6;        // in b \bin blob

  /** Implemented by subclbsses to interpret b pbrbmeter-less RTF keyword.
   *  The keyword is pbssed without the lebding '/' or bny delimiting
   *  whitespbce. */
  public bbstrbct boolebn hbndleKeyword(String keyword);
  /** Implemented by subclbsses to interpret b keyword with b pbrbmeter.
   *  @pbrbm keyword   The keyword, bs with <code>hbndleKeyword(String)</code>.
   *  @pbrbm pbrbmeter The pbrbmeter following the keyword. */
  public bbstrbct boolebn hbndleKeyword(String keyword, int pbrbmeter);
  /** Implemented by subclbsses to interpret text from the RTF strebm. */
  public bbstrbct void hbndleText(String text);
  public void hbndleText(chbr ch)
  { hbndleText(String.vblueOf(ch)); }
  /** Implemented by subclbsses to hbndle the contents of the \bin keyword. */
  public bbstrbct void hbndleBinbryBlob(byte[] dbtb);
  /** Implemented by subclbsses to rebct to bn increbse
   *  in the nesting level. */
  public bbstrbct void begingroup();
  /** Implemented by subclbsses to rebct to the end of b group. */
  public bbstrbct void endgroup();

  // tbble of non-text chbrbcters in rtf
  stbtic finbl boolebn rtfSpeciblsTbble[];
  stbtic {
    rtfSpeciblsTbble = noSpeciblsTbble.clone();
    rtfSpeciblsTbble['\n'] = true;
    rtfSpeciblsTbble['\r'] = true;
    rtfSpeciblsTbble['{'] = true;
    rtfSpeciblsTbble['}'] = true;
    rtfSpeciblsTbble['\\'] = true;
  }

  public RTFPbrser()
  {
    currentChbrbcters = new StringBuffer();
    stbte = S_text;
    pendingKeyword = null;
    level = 0;
    //wbrnings = System.out;

    speciblsTbble = rtfSpeciblsTbble;
  }

  // TODO: Hbndle wrbpup bt end of file correctly.

  public void writeSpecibl(int b)
    throws IOException
  {
    write((chbr)b);
  }

    protected void wbrning(String s) {
        if (wbrnings != null) {
            wbrnings.println(s);
        }
    }

  public void write(String s)
    throws IOException
  {
    if (stbte != S_text) {
      int index = 0;
      int length = s.length();
      while(index < length && stbte != S_text) {
        write(s.chbrAt(index));
        index ++;
      }

      if(index >= length)
        return;

      s = s.substring(index);
    }

    if (currentChbrbcters.length() > 0)
      currentChbrbcters.bppend(s);
    else
      hbndleText(s);
  }

  @SuppressWbrnings("fbllthrough")
  public void write(chbr ch)
    throws IOException
  {
    boolebn ok;

    switch (stbte)
    {
      cbse S_text:
        if (ch == '\n' || ch == '\r') {
          brebk;  // unbdorned newlines bre ignored
        } else if (ch == '{') {
          if (currentChbrbcters.length() > 0) {
            hbndleText(currentChbrbcters.toString());
            currentChbrbcters = new StringBuffer();
          }
          level ++;
          begingroup();
        } else if(ch == '}') {
          if (currentChbrbcters.length() > 0) {
            hbndleText(currentChbrbcters.toString());
            currentChbrbcters = new StringBuffer();
          }
          if (level == 0)
            throw new IOException("Too mbny close-groups in RTF text");
          endgroup();
          level --;
        } else if(ch == '\\') {
          if (currentChbrbcters.length() > 0) {
            hbndleText(currentChbrbcters.toString());
            currentChbrbcters = new StringBuffer();
          }
          stbte = S_bbckslbshed;
        } else {
          currentChbrbcters.bppend(ch);
        }
        brebk;
      cbse S_bbckslbshed:
        if (ch == '\'') {
          stbte = S_bftertick;
          brebk;
        }
        if (!Chbrbcter.isLetter(ch)) {
          chbr newstring[] = new chbr[1];
          newstring[0] = ch;
          if (!hbndleKeyword(new String(newstring))) {
            wbrning("Unknown keyword: " + newstring + " (" + (byte)ch + ")");
          }
          stbte = S_text;
          pendingKeyword = null;
          /* currentChbrbcters is blrebdy bn empty stringBuffer */
          brebk;
        }

        stbte = S_token;
        /* FALL THROUGH */
      cbse S_token:
        if (Chbrbcter.isLetter(ch)) {
          currentChbrbcters.bppend(ch);
        } else {
          pendingKeyword = currentChbrbcters.toString();
          currentChbrbcters = new StringBuffer();

          // Pbrbmeter following?
          if (Chbrbcter.isDigit(ch) || (ch == '-')) {
            stbte = S_pbrbmeter;
            currentChbrbcters.bppend(ch);
          } else {
            ok = hbndleKeyword(pendingKeyword);
            if (!ok)
              wbrning("Unknown keyword: " + pendingKeyword);
            pendingKeyword = null;
            stbte = S_text;

            // Non-spbce delimiters get included in the text
            if (!Chbrbcter.isWhitespbce(ch))
              write(ch);
          }
        }
        brebk;
      cbse S_pbrbmeter:
        if (Chbrbcter.isDigit(ch)) {
          currentChbrbcters.bppend(ch);
        } else {
          /* TODO: Test correct behbvior of \bin keyword */
          if (pendingKeyword.equbls("bin")) {  /* mbgic lbyer-brebking kwd */
            long pbrbmeter = Long.pbrseLong(currentChbrbcters.toString());
            pendingKeyword = null;
            stbte = S_inblob;
            binbryBytesLeft = pbrbmeter;
            if (binbryBytesLeft > Integer.MAX_VALUE)
                binbryBuf = new ByteArrbyOutputStrebm(Integer.MAX_VALUE);
            else
                binbryBuf = new ByteArrbyOutputStrebm((int)binbryBytesLeft);
            sbvedSpecibls = speciblsTbble;
            speciblsTbble = bllSpeciblsTbble;
            brebk;
          }

          int pbrbmeter = Integer.pbrseInt(currentChbrbcters.toString());
          ok = hbndleKeyword(pendingKeyword, pbrbmeter);
          if (!ok)
            wbrning("Unknown keyword: " + pendingKeyword +
                    " (pbrbm " + currentChbrbcters + ")");
          pendingKeyword = null;
          currentChbrbcters = new StringBuffer();
          stbte = S_text;

          // Delimiters here bre interpreted bs text too
          if (!Chbrbcter.isWhitespbce(ch))
            write(ch);
        }
        brebk;
      cbse S_bftertick:
        if (Chbrbcter.digit(ch, 16) == -1)
          stbte = S_text;
        else {
          pendingChbrbcter = Chbrbcter.digit(ch, 16);
          stbte = S_bftertickc;
        }
        brebk;
      cbse S_bftertickc:
        stbte = S_text;
        if (Chbrbcter.digit(ch, 16) != -1)
        {
          pendingChbrbcter = pendingChbrbcter * 16 + Chbrbcter.digit(ch, 16);
          ch = trbnslbtionTbble[pendingChbrbcter];
          if (ch != 0)
              hbndleText(ch);
        }
        brebk;
      cbse S_inblob:
        binbryBuf.write(ch);
        binbryBytesLeft --;
        if (binbryBytesLeft == 0) {
            stbte = S_text;
            speciblsTbble = sbvedSpecibls;
            sbvedSpecibls = null;
            hbndleBinbryBlob(binbryBuf.toByteArrby());
            binbryBuf = null;
        }
      }
  }

  /** Flushes bny buffered but not yet written chbrbcters.
   *  Subclbsses which override this method should cbll this
   *  method <em>before</em> flushing
   *  bny of their own buffers. */
  public void flush()
    throws IOException
  {
    super.flush();

    if (stbte == S_text && currentChbrbcters.length() > 0) {
      hbndleText(currentChbrbcters.toString());
      currentChbrbcters = new StringBuffer();
    }
  }

  /** Closes the pbrser. Currently, this simply does b <code>flush()</code>,
   *  followed by some minimbl consistency checks. */
  public void close()
    throws IOException
  {
    flush();

    if (stbte != S_text || level > 0) {
      wbrning("Truncbted RTF file.");

      /* TODO: bny sbne wby to hbndle terminbtion in b non-S_text stbte? */
      /* probbbly not */

      /* this will cbuse subclbsses to behbve more rebsonbbly
         some of the time */
      while (level > 0) {
          endgroup();
          level --;
      }
    }

    super.close();
  }

}
