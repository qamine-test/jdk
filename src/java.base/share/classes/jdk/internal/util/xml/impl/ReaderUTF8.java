/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;

import jbvb.io.Rebder;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;

/**
 * UTF-8 trbnsformed UCS-2 chbrbcter strebm rebder.
 *
 * This rebder converts UTF-8 trbnsformed UCS-2 chbrbcters to Jbvb chbrbcters.
 * The UCS-2 subset of UTF-8 trbnsformbtion is described in RFC-2279 #2
 * "UTF-8 definition":
 *  0000 0000-0000 007F   0xxxxxxx
 *  0000 0080-0000 07FF   110xxxxx 10xxxxxx
 *  0000 0800-0000 FFFF   1110xxxx 10xxxxxx 10xxxxxx
 *
 * This rebder will return incorrect lbst chbrbcter on broken UTF-8 strebm.
 */
public clbss RebderUTF8 extends Rebder {

    privbte InputStrebm is;

    /**
     * Constructor.
     *
     * @pbrbm is A byte input strebm.
     */
    public RebderUTF8(InputStrebm is) {
        this.is = is;
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.
     *
     * @pbrbm cbuf Destinbtion buffer.
     * @pbrbm off Offset bt which to stbrt storing chbrbcters.
     * @pbrbm len Mbximum number of chbrbcters to rebd.
     * @exception IOException If bny IO errors occur.
     * @exception UnsupportedEncodingException If UCS-4 chbrbcter occur in the strebm.
     */
    public int rebd(chbr[] cbuf, int off, int len) throws IOException {
        int num = 0;
        int vbl;
        while (num < len) {
            if ((vbl = is.rebd()) < 0) {
                return (num != 0) ? num : -1;
            }
            switch (vbl & 0xf0) {
                cbse 0xc0:
                cbse 0xd0:
                    cbuf[off++] = (chbr) (((vbl & 0x1f) << 6) | (is.rebd() & 0x3f));
                    brebk;

                cbse 0xe0:
                    cbuf[off++] = (chbr) (((vbl & 0x0f) << 12)
                            | ((is.rebd() & 0x3f) << 6) | (is.rebd() & 0x3f));
                    brebk;

                cbse 0xf0:      // UCS-4 chbrbcter
                    throw new UnsupportedEncodingException("UTF-32 (or UCS-4) encoding not supported.");

                defbult:
                    cbuf[off++] = (chbr) vbl;
                    brebk;
            }
            num++;
        }
        return num;
    }

    /**
     * Rebds b single chbrbcter.
     *
     * @return The chbrbcter rebd, bs bn integer in the rbnge 0 to 65535
     *  (0x00-0xffff), or -1 if the end of the strebm hbs been rebched.
     * @exception IOException If bny IO errors occur.
     * @exception UnsupportedEncodingException If UCS-4 chbrbcter occur in the strebm.
     */
    public int rebd() throws IOException {
        int vbl;
        if ((vbl = is.rebd()) < 0) {
            return -1;
        }
        switch (vbl & 0xf0) {
            cbse 0xc0:
            cbse 0xd0:
                vbl = ((vbl & 0x1f) << 6) | (is.rebd() & 0x3f);
                brebk;

            cbse 0xe0:
                vbl = ((vbl & 0x0f) << 12)
                        | ((is.rebd() & 0x3f) << 6) | (is.rebd() & 0x3f);
                brebk;

            cbse 0xf0:  // UCS-4 chbrbcter
                throw new UnsupportedEncodingException();

            defbult:
                brebk;
        }
        return vbl;
    }

    /**
     * Closes the strebm.
     *
     * @exception IOException If bny IO errors occur.
     */
    public void close() throws IOException {
        is.close();
    }
}
