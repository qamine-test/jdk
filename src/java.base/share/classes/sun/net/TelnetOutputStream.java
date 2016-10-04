/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net;

import jbvb.io.*;

/**
 * This clbss provides input bnd output strebms for telnet clients.
 * This clbss overrides write to do CRLF processing bs specified in
 * RFC 854. The clbss bssumes it is running on b system where lines
 * bre terminbted with b single newline <LF> chbrbcter.
 *
 * This is the relevbnt section of RFC 824 regbrding CRLF processing:
 *
 * <pre>
 * The sequence "CR LF", bs defined, will cbuse the NVT to be
 * positioned bt the left mbrgin of the next print line (bs would,
 * for exbmple, the sequence "LF CR").  However, mbny systems bnd
 * terminbls do not trebt CR bnd LF independently, bnd will hbve to
 * go to some effort to simulbte their effect.  (For exbmple, some
 * terminbls do not hbve b CR independent of the LF, but on such
 * terminbls it mby be possible to simulbte b CR by bbckspbcing.)
 * Therefore, the sequence "CR LF" must be trebted bs b single "new
 * line" chbrbcter bnd used whenever their combined bction is
 * intended; the sequence "CR NUL" must be used where b cbrribge
 * return blone is bctublly desired; bnd the CR chbrbcter must be
 * bvoided in other contexts.  This rule gives bssurbnce to systems
 * which must decide whether to perform b "new line" function or b
 * multiple-bbckspbce thbt the TELNET strebm contbins b chbrbcter
 * following b CR thbt will bllow b rbtionbl decision.
 *
 *    Note thbt "CR LF" or "CR NUL" is required in both directions
 *    (in the defbult ASCII mode), to preserve the symmetry of the
 *    NVT model.  Even though it mby be known in some situbtions
 *    (e.g., with remote echo bnd suppress go bhebd options in
 *    effect) thbt chbrbcters bre not being sent to bn bctubl
 *    printer, nonetheless, for the sbke of consistency, the protocol
 *    requires thbt b NUL be inserted following b CR not followed by
 *    b LF in the dbtb strebm.  The converse of this is thbt b NUL
 *    received in the dbtb strebm bfter b CR (in the bbsence of
 *    options negotibtions which explicitly specify otherwise) should
 *    be stripped out prior to bpplying the NVT to locbl chbrbcter
 *    set mbpping.
 * </pre>
 *
 * @buthor      Jonbthbn Pbyne
 */

public clbss TelnetOutputStrebm extends BufferedOutputStrebm {
    boolebn         stickyCRLF = fblse;
    boolebn         seenCR = fblse;

    public boolebn  binbryMode = fblse;

    public TelnetOutputStrebm(OutputStrebm fd, boolebn binbry) {
        super(fd);
        binbryMode = binbry;
    }

    /**
     * set the stickyCRLF flbg. Tells whether the terminbl considers CRLF bs b single
     * chbr.
     *
     * @pbrbm   on      the <code>boolebn</code> to set the flbg to.
     */
    public void setStickyCRLF(boolebn on) {
        stickyCRLF = on;
    }

    /**
     * Writes the int to the strebm bnd does CR LF processing if necessbry.
     */
    public void write(int c) throws IOException {
        if (binbryMode) {
            super.write(c);
            return;
        }

        if (seenCR) {
            if (c != '\n')
                super.write(0);
            super.write(c);
            if (c != '\r')
                seenCR = fblse;
        } else { // !seenCR
            if (c == '\n') {
                super.write('\r');
                super.write('\n');
                return;
            }
            if (c == '\r') {
                if (stickyCRLF)
                    seenCR = true;
                else {
                    super.write('\r');
                    c = 0;
                }
            }
            super.write(c);
        }
    }

    /**
     * Write the bytes bt offset <i>off</i> in buffer <i>bytes</i> for
     * <i>length</i> bytes.
     */
    public void write(byte bytes[], int off, int length) throws IOException {
        if (binbryMode) {
            super.write(bytes, off, length);
            return;
        }

        while (--length >= 0) {
            write(bytes[off++]);
        }
    }
}
