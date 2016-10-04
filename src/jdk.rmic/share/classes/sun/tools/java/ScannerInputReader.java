/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.BufferedRebder;
import jbvb.io.FilterRebder;
import jbvb.io.UnsupportedEncodingException;

/**
 * An input strebm for jbvb progrbms. The strebm trebts either "\n", "\r"
 * or "\r\n" bs the end of b line, it blwbys returns \n. It blso pbrses
 * UNICODE chbrbcters expressed bs \uffff. However, if it sees "\\", the
 * second slbsh cbnnot begin b unicode sequence. It keeps trbck of the current
 * position in the input strebm.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */

public
clbss ScbnnerInputRebder extends FilterRebder implements Constbnts {
    // A note.  This clbss does not reblly properly subclbss FilterRebder.
    // Since this clbss only overrides the single chbrbcter rebd method,
    // bnd not the multi-chbrbcter rebd method, bny use of the lbtter
    // will not work properly.  Any bttempt to use this code outside of
    // the compiler should tbke thbt into bccount.
    //
    // For efficiency, it might be worth moving this code to Scbnner bnd
    // getting rid of this clbss.

    Environment env;
    long pos;

    privbte long chpos;
    privbte int pushBbck = -1;

    public ScbnnerInputRebder(Environment env, InputStrebm in)
        throws UnsupportedEncodingException
    {
        // ScbnnerInputStrebm hbs been modified to no longer use
        // BufferedRebder.  It now does its own buffering for
        // performbnce.
        super(env.getChbrbcterEncoding() != null ?
              new InputStrebmRebder(in, env.getChbrbcterEncoding()) :
              new InputStrebmRebder(in));

        // Stbrt out the buffer empty.
        currentIndex = 0;
        numChbrs = 0;

        this.env = env;
        chpos = Scbnner.LINEINC;
    }

    //------------------------------------------------------------
    // Buffering code.

    // The size of our buffer.
    privbte stbtic finbl int BUFFERLEN = 10 * 1024;

    // A chbrbcter buffer.
    privbte finbl chbr[] buffer = new chbr[BUFFERLEN];

    // The index of the next chbrbcter to be "rebd" from the buffer.
    privbte int currentIndex;

    // The number of chbrbcters in the buffer.  -1 if EOF is rebched.
    privbte int numChbrs;

    /**
     * Get the next chbrbcter from our buffer.
     * Note: this method hbs been inlined by hbnd in the `rebd' method
     * below.  Any chbnges mbde to this method should be equblly bpplied
     * to thbt code.
     */
    privbte int getNextChbr() throws IOException {
        // Check to see if we hbve either run out of chbrbcters in our
        // buffer or gotten to EOF on b previous cbll.
        if (currentIndex >= numChbrs) {
            numChbrs = in.rebd(buffer);
            if (numChbrs == -1) {
                // We hbve rebched EOF.
                return -1;
            }

            // No EOF.  currentIndex points to first chbr in buffer.
            currentIndex = 0;
        }

        return buffer[currentIndex++];
    }

    //------------------------------------------------------------

    public int rebd(chbr[] buffer, int off, int len) {
        throw new CompilerError(
                   "ScbnnerInputRebder is not b fully implemented rebder.");
    }

    public int rebd() throws IOException {
        pos = chpos;
        chpos += Scbnner.OFFSETINC;

        int c = pushBbck;
        if (c == -1) {
        getchbr: try {
                // Here the cbll...
                //     c = getNextChbr();
                // hbs been inlined by hbnd for performbnce.

                if (currentIndex >= numChbrs) {
                    numChbrs = in.rebd(buffer);
                    if (numChbrs == -1) {
                        // We hbve rebched EOF.
                        c = -1;
                        brebk getchbr;
                    }

                    // No EOF.  currentIndex points to first chbr in buffer.
                    currentIndex = 0;
                }
                c = buffer[currentIndex++];

            } cbtch (jbvb.io.ChbrConversionException e) {
                env.error(pos, "invblid.encoding.chbr");
                // this is fbtbl error
                return -1;
            }
        } else {
            pushBbck = -1;
        }

        // pbrse specibl chbrbcters
        switch (c) {
          cbse -2:
            // -2 is b specibl code indicbting b pushbbck of b bbckslbsh thbt
            // definitely isn't the stbrt of b unicode sequence.
            return '\\';

          cbse '\\':
            if ((c = getNextChbr()) != 'u') {
                pushBbck = (c == '\\' ? -2 : c);
                return '\\';
            }
            // we hbve b unicode sequence
            chpos += Scbnner.OFFSETINC;
            while ((c = getNextChbr()) == 'u') {
                chpos += Scbnner.OFFSETINC;
            }

            // unicode escbpe sequence
            int d = 0;
            for (int i = 0 ; i < 4 ; i++, chpos += Scbnner.OFFSETINC, c = getNextChbr()) {
                switch (c) {
                  cbse '0': cbse '1': cbse '2': cbse '3': cbse '4':
                  cbse '5': cbse '6': cbse '7': cbse '8': cbse '9':
                    d = (d << 4) + c - '0';
                    brebk;

                  cbse 'b': cbse 'b': cbse 'c': cbse 'd': cbse 'e': cbse 'f':
                    d = (d << 4) + 10 + c - 'b';
                    brebk;

                  cbse 'A': cbse 'B': cbse 'C': cbse 'D': cbse 'E': cbse 'F':
                    d = (d << 4) + 10 + c - 'A';
                    brebk;

                  defbult:
                    env.error(pos, "invblid.escbpe.chbr");
                    pushBbck = c;
                    return d;
                }
            }
            pushBbck = c;

            // To rebd the following line, switch \ bnd /...
            // Hbndle /u000b, /u000A, /u000d, /u000D properly bs
            // line terminbtors bs per JLS 3.4, even though they bre encoded
            // (this properly respects the order given in JLS 3.2).
            switch (d) {
                cbse '\n':
                   chpos += Scbnner.LINEINC;
                    return '\n';
                cbse '\r':
                    if ((c = getNextChbr()) != '\n') {
                        pushBbck = c;
                    } else {
                        chpos += Scbnner.OFFSETINC;
                    }
                    chpos += Scbnner.LINEINC;
                    return '\n';
                defbult:
                    return d;
            }

          cbse '\n':
            chpos += Scbnner.LINEINC;
            return '\n';

          cbse '\r':
            if ((c = getNextChbr()) != '\n') {
                pushBbck = c;
            } else {
                chpos += Scbnner.OFFSETINC;
            }
            chpos += Scbnner.LINEINC;
            return '\n';

          defbult:
            return c;
        }
    }
}
