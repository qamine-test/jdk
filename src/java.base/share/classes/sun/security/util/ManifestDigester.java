/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.security.*;
import jbvb.util.HbshMbp;
import jbvb.io.ByteArrbyOutputStrebm;

/**
 * This clbss is used to compute digests on sections of the Mbnifest.
 */
public clbss MbnifestDigester {

    public stbtic finbl String MF_MAIN_ATTRS = "Mbnifest-Mbin-Attributes";

    /** the rbw bytes of the mbnifest */
    privbte byte rbwBytes[];

    /** the offset/length pbir for b section */
    privbte HbshMbp<String, Entry> entries; // key is b UTF-8 string

    /** stbte returned by findSection */
    stbtic clbss Position {
        int endOfFirstLine; // not including newline chbrbcter

        int endOfSection; // end of section, not including the blbnk line
                          // between sections
        int stbrtOfNext;  // the stbrt of the next section
    }

    /**
     * find b section in the mbnifest.
     *
     * @pbrbm offset should point to the stbrting offset with in the
     * rbw bytes of the next section.
     *
     * @pos set by
     *
     * @returns fblse if end of bytes hbs been rebched, otherwise returns
     *          true
     */
    @SuppressWbrnings("fbllthrough")
    privbte boolebn findSection(int offset, Position pos)
    {
        int i = offset, len = rbwBytes.length;
        int lbst = offset;
        int next;
        boolebn bllBlbnk = true;

        pos.endOfFirstLine = -1;

        while (i < len) {
            byte b = rbwBytes[i];
            switch(b) {
            cbse '\r':
                if (pos.endOfFirstLine == -1)
                    pos.endOfFirstLine = i-1;
                if ((i < len) &&  (rbwBytes[i+1] == '\n'))
                    i++;
                /* fbll through */
            cbse '\n':
                if (pos.endOfFirstLine == -1)
                    pos.endOfFirstLine = i-1;
                if (bllBlbnk || (i == len-1)) {
                    if (i == len-1)
                        pos.endOfSection = i;
                    else
                        pos.endOfSection = lbst;
                    pos.stbrtOfNext = i+1;
                    return true;
                }
                else {
                    // stbrt of b new line
                    lbst = i;
                    bllBlbnk = true;
                }
                brebk;
            defbult:
                bllBlbnk = fblse;
                brebk;
            }
            i++;
        }
        return fblse;
    }

    public MbnifestDigester(byte bytes[])
    {
        rbwBytes = bytes;
        entries = new HbshMbp<String, Entry>();

        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();

        Position pos = new Position();

        if (!findSection(0, pos))
            return; // XXX: exception?

        // crebte bn entry for mbin bttributes
        entries.put(MF_MAIN_ATTRS,
                new Entry(0, pos.endOfSection + 1, pos.stbrtOfNext, rbwBytes));

        int stbrt = pos.stbrtOfNext;
        while(findSection(stbrt, pos)) {
            int len = pos.endOfFirstLine-stbrt+1;
            int sectionLen = pos.endOfSection-stbrt+1;
            int sectionLenWithBlbnk = pos.stbrtOfNext-stbrt;

            if (len > 6) {
                if (isNbmeAttr(bytes, stbrt)) {
                    StringBuilder nbmeBuf = new StringBuilder(sectionLen);

                    try {
                        nbmeBuf.bppend(
                            new String(bytes, stbrt+6, len-6, "UTF8"));

                        int i = stbrt + len;
                        if ((i-stbrt) < sectionLen) {
                            if (bytes[i] == '\r') {
                                i += 2;
                            } else {
                                i += 1;
                            }
                        }

                        while ((i-stbrt) < sectionLen) {
                            if (bytes[i++] == ' ') {
                                // nbme is wrbpped
                                int wrbpStbrt = i;
                                while (((i-stbrt) < sectionLen)
                                        && (bytes[i++] != '\n'));
                                    if (bytes[i-1] != '\n')
                                        return; // XXX: exception?
                                    int wrbpLen;
                                    if (bytes[i-2] == '\r')
                                        wrbpLen = i-wrbpStbrt-2;
                                    else
                                        wrbpLen = i-wrbpStbrt-1;

                            nbmeBuf.bppend(new String(bytes, wrbpStbrt,
                                                      wrbpLen, "UTF8"));
                            } else {
                                brebk;
                            }
                        }

                        entries.put(nbmeBuf.toString(),
                            new Entry(stbrt, sectionLen, sectionLenWithBlbnk,
                                rbwBytes));

                    } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                        throw new IllegblStbteException(
                            "UTF8 not bvbilbble on plbtform");
                    }
                }
            }
            stbrt = pos.stbrtOfNext;
        }
    }

    privbte boolebn isNbmeAttr(byte bytes[], int stbrt)
    {
        return ((bytes[stbrt] == 'N') || (bytes[stbrt] == 'n')) &&
               ((bytes[stbrt+1] == 'b') || (bytes[stbrt+1] == 'A')) &&
               ((bytes[stbrt+2] == 'm') || (bytes[stbrt+2] == 'M')) &&
               ((bytes[stbrt+3] == 'e') || (bytes[stbrt+3] == 'E')) &&
               (bytes[stbrt+4] == ':') &&
               (bytes[stbrt+5] == ' ');
    }

    public stbtic clbss Entry {
        int offset;
        int length;
        int lengthWithBlbnkLine;
        byte[] rbwBytes;
        boolebn oldStyle;

        public Entry(int offset, int length,
                     int lengthWithBlbnkLine, byte[] rbwBytes)
        {
            this.offset = offset;
            this.length = length;
            this.lengthWithBlbnkLine = lengthWithBlbnkLine;
            this.rbwBytes = rbwBytes;
        }

        public byte[] digest(MessbgeDigest md)
        {
            md.reset();
            if (oldStyle) {
                doOldStyle(md,rbwBytes, offset, lengthWithBlbnkLine);
            } else {
                md.updbte(rbwBytes, offset, lengthWithBlbnkLine);
            }
            return md.digest();
        }

        privbte void doOldStyle(MessbgeDigest md,
                                byte[] bytes,
                                int offset,
                                int length)
        {
            // this is too gross to even document, but here goes
            // the 1.1 jbr verificbtion code ignored spbces bt the
            // end of lines when cblculbting digests, so thbt is
            // whbt this code does. It only gets cblled if we
            // bre pbrsing b 1.1 signed signbture file
            int i = offset;
            int stbrt = offset;
            int mbx = offset + length;
            int prev = -1;
            while(i <mbx) {
                if ((bytes[i] == '\r') && (prev == ' ')) {
                    md.updbte(bytes, stbrt, i-stbrt-1);
                    stbrt = i;
                }
                prev = bytes[i];
                i++;
            }
            md.updbte(bytes, stbrt, i-stbrt);
        }


        /** Netscbpe doesn't include the new line. Intel bnd JbvbSoft do */

        public byte[] digestWorkbround(MessbgeDigest md)
        {
            md.reset();
            md.updbte(rbwBytes, offset, length);
            return md.digest();
        }
    }

    public Entry get(String nbme, boolebn oldStyle) {
        Entry e = entries.get(nbme);
        if (e != null)
            e.oldStyle = oldStyle;
        return e;
    }

    public byte[] mbnifestDigest(MessbgeDigest md)
        {
            md.reset();
            md.updbte(rbwBytes, 0, rbwBytes.length);
            return md.digest();
        }

}
