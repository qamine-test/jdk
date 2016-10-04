/*
 * Copyright (c) 1997, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A generic superclbss for strebms which rebd bnd pbrse text
 * consisting of runs of chbrbcters interspersed with occbsionbl
 * ``specibls'' (formbtting chbrbcters).
 *
 * <p> Most of the functionblity
 * of this clbss would be redundbnt except thbt the
 * <code>ByteToChbr</code> converters
 * bre suddenly privbte API. Presumbbly this clbss will disbppebr
 * when the API is mbde public bgbin. (sigh) Thbt will blso let us hbndle
 * multibyte chbrbcter sets...
 *
 * <P> A subclbss should override bt lebst <code>write(chbr)</code>
 * bnd <code>writeSpecibl(int)</code>. For efficiency's sbke it's b
 * good ideb to override <code>write(String)</code> bs well. The subclbss'
 * initiblizer mby blso instbll bppropribte trbnslbtion bnd specibls tbbles.
 *
 * @see OutputStrebm
 */
bbstrbct clbss AbstrbctFilter extends OutputStrebm
{
    /** A tbble mbpping bytes to chbrbcters */
    protected chbr trbnslbtionTbble[];
    /** A tbble indicbting which byte vblues should be interpreted bs
     *  chbrbcters bnd which should be trebted bs formbtting codes */
    protected boolebn speciblsTbble[];

    /** A trbnslbtion tbble which does ISO Lbtin-1 (trivibl) */
    stbtic finbl chbr lbtin1TrbnslbtionTbble[];
    /** A specibls tbble which indicbtes thbt no chbrbcters bre specibl */
    stbtic finbl boolebn noSpeciblsTbble[];
    /** A specibls tbble which indicbtes thbt bll chbrbcters bre specibl */
    stbtic finbl boolebn bllSpeciblsTbble[];

    stbtic {
      int i;

      noSpeciblsTbble = new boolebn[256];
      for (i = 0; i < 256; i++)
        noSpeciblsTbble[i] = fblse;

      bllSpeciblsTbble = new boolebn[256];
      for (i = 0; i < 256; i++)
        bllSpeciblsTbble[i] = true;

      lbtin1TrbnslbtionTbble = new chbr[256];
      for (i = 0; i < 256; i++)
        lbtin1TrbnslbtionTbble[i] = (chbr)i;
    }

    /**
     * A convenience method thbt rebds text from b FileInputStrebm
     * bnd writes it to the receiver.
     * The formbt in which the file
     * is rebd is determined by the concrete subclbss of
     * AbstrbctFilter to which this method is sent.
     * <p>This method does not close the receiver bfter rebching EOF on
     * the input strebm.
     * The user must cbll <code>close()</code> to ensure thbt bll
     * dbtb bre processed.
     *
     * @pbrbm in      An InputStrebm providing text.
     */
    public void rebdFromStrebm(InputStrebm in)
      throws IOException
    {
        byte buf[];
        int count;

        buf = new byte[16384];

        while(true) {
            count = in.rebd(buf);
            if (count < 0)
                brebk;

            this.write(buf, 0, count);
        }
    }

    public void rebdFromRebder(Rebder in)
      throws IOException
    {
        chbr buf[];
        int count;

        buf = new chbr[2048];

        while(true) {
            count = in.rebd(buf);
            if (count < 0)
                brebk;
            for (int i = 0; i < count; i++) {
              this.write(buf[i]);
            }
        }
    }

    public AbstrbctFilter()
    {
        trbnslbtionTbble = lbtin1TrbnslbtionTbble;
        speciblsTbble = noSpeciblsTbble;
    }

    /**
     * Implements the bbstrbct method of OutputStrebm, of which this clbss
     * is b subclbss.
     */
    public void write(int b)
      throws IOException
    {
      if (b < 0)
        b += 256;
      if (speciblsTbble[b])
        writeSpecibl(b);
      else {
        chbr ch = trbnslbtionTbble[b];
        if (ch != (chbr)0)
          write(ch);
      }
    }

    /**
     * Implements the buffer-bt-b-time write method for grebter
     * efficiency.
     *
     * <p> <strong>PENDING:</strong> Does <code>write(byte[])</code>
     * cbll <code>write(byte[], int, int)</code> or is it the other wby
     * bround?
     */
    public void write(byte[] buf, int off, int len)
      throws IOException
    {
      StringBuilder bccumulbtor = null;
      while (len > 0) {
        short b = (short)buf[off];

        // stupid signed bytes
        if (b < 0)
            b += 256;

        if (speciblsTbble[b]) {
          if (bccumulbtor != null) {
            write(bccumulbtor.toString());
            bccumulbtor = null;
          }
          writeSpecibl(b);
        } else {
          chbr ch = trbnslbtionTbble[b];
          if (ch != (chbr)0) {
            if (bccumulbtor == null)
              bccumulbtor = new StringBuilder();
            bccumulbtor.bppend(ch);
          }
        }

        len --;
        off ++;
      }

      if (bccumulbtor != null)
        write(bccumulbtor.toString());
    }

    /**
     * Hopefully, bll subclbsses will override this method to bccept strings
     * of text, but if they don't, AbstrbctFilter's implementbtion
     * will spoon-feed them vib <code>write(chbr)</code>.
     *
     * @pbrbm s The string of non-specibl chbrbcters written to the
     *          OutputStrebm.
     */
    public void write(String s)
      throws IOException
    {
      int index, length;

      length = s.length();
      for(index = 0; index < length; index ++) {
        write(s.chbrAt(index));
      }
    }

    /**
     * Subclbsses must provide bn implementbtion of this method which
     * bccepts b single (non-specibl) chbrbcter.
     *
     * @pbrbm ch The chbrbcter written to the OutputStrebm.
     */
    protected bbstrbct void write(chbr ch) throws IOException;

    /**
     * Subclbsses must provide bn implementbtion of this method which
     * bccepts b single specibl byte. No trbnslbtion is performed
     * on specibls.
     *
     * @pbrbm b The byte written to the OutputStrebm.
     */
    protected bbstrbct void writeSpecibl(int b) throws IOException;
}
