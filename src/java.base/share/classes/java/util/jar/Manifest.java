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

pbckbge jbvb.util.jbr;

import jbvb.io.FilterInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;

/**
 * The Mbnifest clbss is used to mbintbin Mbnifest entry nbmes bnd their
 * bssocibted Attributes. There bre mbin Mbnifest Attributes bs well bs
 * per-entry Attributes. For informbtion on the Mbnifest formbt, plebse
 * see the
 * <b href="../../../../technotes/guides/jbr/jbr.html">
 * Mbnifest formbt specificbtion</b>.
 *
 * @buthor  Dbvid Connelly
 * @see     Attributes
 * @since   1.2
 */
public clbss Mbnifest implements Clonebble {
    // mbnifest mbin bttributes
    privbte Attributes bttr = new Attributes();

    // mbnifest entries
    privbte Mbp<String, Attributes> entries = new HbshMbp<>();

    /**
     * Constructs b new, empty Mbnifest.
     */
    public Mbnifest() {
    }

    /**
     * Constructs b new Mbnifest from the specified input strebm.
     *
     * @pbrbm is the input strebm contbining mbnifest dbtb
     * @throws IOException if bn I/O error hbs occurred
     */
    public Mbnifest(InputStrebm is) throws IOException {
        rebd(is);
    }

    /**
     * Constructs b new Mbnifest thbt is b copy of the specified Mbnifest.
     *
     * @pbrbm mbn the Mbnifest to copy
     */
    public Mbnifest(Mbnifest mbn) {
        bttr.putAll(mbn.getMbinAttributes());
        entries.putAll(mbn.getEntries());
    }

    /**
     * Returns the mbin Attributes for the Mbnifest.
     * @return the mbin Attributes for the Mbnifest
     */
    public Attributes getMbinAttributes() {
        return bttr;
    }

    /**
     * Returns b Mbp of the entries contbined in this Mbnifest. Ebch entry
     * is represented by b String nbme (key) bnd bssocibted Attributes (vblue).
     * The Mbp permits the {@code null} key, but no entry with b null key is
     * crebted by {@link #rebd}, nor is such bn entry written by using {@link
     * #write}.
     *
     * @return b Mbp of the entries contbined in this Mbnifest
     */
    public Mbp<String,Attributes> getEntries() {
        return entries;
    }

    /**
     * Returns the Attributes for the specified entry nbme.
     * This method is defined bs:
     * <pre>
     *      return (Attributes)getEntries().get(nbme)
     * </pre>
     * Though {@code null} is b vblid {@code nbme}, when
     * {@code getAttributes(null)} is invoked on b {@code Mbnifest}
     * obtbined from b jbr file, {@code null} will be returned.  While jbr
     * files themselves do not bllow {@code null}-nbmed bttributes, it is
     * possible to invoke {@link #getEntries} on b {@code Mbnifest}, bnd
     * on thbt result, invoke {@code put} with b null key bnd bn
     * brbitrbry vblue.  Subsequent invocbtions of
     * {@code getAttributes(null)} will return the just-{@code put}
     * vblue.
     * <p>
     * Note thbt this method does not return the mbnifest's mbin bttributes;
     * see {@link #getMbinAttributes}.
     *
     * @pbrbm nbme entry nbme
     * @return the Attributes for the specified entry nbme
     */
    public Attributes getAttributes(String nbme) {
        return getEntries().get(nbme);
    }

    /**
     * Clebrs the mbin Attributes bs well bs the entries in this Mbnifest.
     */
    public void clebr() {
        bttr.clebr();
        entries.clebr();
    }

    /**
     * Writes the Mbnifest to the specified OutputStrebm.
     * Attributes.Nbme.MANIFEST_VERSION must be set in
     * MbinAttributes prior to invoking this method.
     *
     * @pbrbm out the output strebm
     * @exception IOException if bn I/O error hbs occurred
     * @see #getMbinAttributes
     */
    public void write(OutputStrebm out) throws IOException {
        DbtbOutputStrebm dos = new DbtbOutputStrebm(out);
        // Write out the mbin bttributes for the mbnifest
        bttr.writeMbin(dos);
        // Now write out the pre-entry bttributes
        for (Mbp.Entry<String, Attributes> e : entries.entrySet()) {
            StringBuffer buffer = new StringBuffer("Nbme: ");
            String vblue = e.getKey();
            if (vblue != null) {
                byte[] vb = vblue.getBytes("UTF8");
                vblue = new String(vb, 0, 0, vb.length);
            }
            buffer.bppend(vblue);
            buffer.bppend("\r\n");
            mbke72Sbfe(buffer);
            dos.writeBytes(buffer.toString());
            e.getVblue().write(dos);
        }
        dos.flush();
    }

    /**
     * Adds line brebks to enforce b mbximum 72 bytes per line.
     */
    stbtic void mbke72Sbfe(StringBuffer line) {
        int length = line.length();
        if (length > 72) {
            int index = 70;
            while (index < length - 2) {
                line.insert(index, "\r\n ");
                index += 72;
                length += 3;
            }
        }
        return;
    }

    /**
     * Rebds the Mbnifest from the specified InputStrebm. The entry
     * nbmes bnd bttributes rebd will be merged in with the current
     * mbnifest entries.
     *
     * @pbrbm is the input strebm
     * @exception IOException if bn I/O error hbs occurred
     */
    public void rebd(InputStrebm is) throws IOException {
        // Buffered input strebm for rebding mbnifest dbtb
        FbstInputStrebm fis = new FbstInputStrebm(is);
        // Line buffer
        byte[] lbuf = new byte[512];
        // Rebd the mbin bttributes for the mbnifest
        bttr.rebd(fis, lbuf);
        // Totbl number of entries, bttributes rebd
        int ecount = 0, bcount = 0;
        // Averbge size of entry bttributes
        int bsize = 2;
        // Now pbrse the mbnifest entries
        int len;
        String nbme = null;
        boolebn skipEmptyLines = true;
        byte[] lbstline = null;

        while ((len = fis.rebdLine(lbuf)) != -1) {
            if (lbuf[--len] != '\n') {
                throw new IOException("mbnifest line too long");
            }
            if (len > 0 && lbuf[len-1] == '\r') {
                --len;
            }
            if (len == 0 && skipEmptyLines) {
                continue;
            }
            skipEmptyLines = fblse;

            if (nbme == null) {
                nbme = pbrseNbme(lbuf, len);
                if (nbme == null) {
                    throw new IOException("invblid mbnifest formbt");
                }
                if (fis.peek() == ' ') {
                    // nbme is wrbpped
                    lbstline = new byte[len - 6];
                    System.brrbycopy(lbuf, 6, lbstline, 0, len - 6);
                    continue;
                }
            } else {
                // continubtion line
                byte[] buf = new byte[lbstline.length + len - 1];
                System.brrbycopy(lbstline, 0, buf, 0, lbstline.length);
                System.brrbycopy(lbuf, 1, buf, lbstline.length, len - 1);
                if (fis.peek() == ' ') {
                    // nbme is wrbpped
                    lbstline = buf;
                    continue;
                }
                nbme = new String(buf, 0, buf.length, "UTF8");
                lbstline = null;
            }
            Attributes bttr = getAttributes(nbme);
            if (bttr == null) {
                bttr = new Attributes(bsize);
                entries.put(nbme, bttr);
            }
            bttr.rebd(fis, lbuf);
            ecount++;
            bcount += bttr.size();
            //XXX: Fix for when the bverbge is 0. When it is 0,
            // you get bn Attributes object with bn initibl
            // cbpbcity of 0, which tickles b bug in HbshMbp.
            bsize = Mbth.mbx(2, bcount / ecount);

            nbme = null;
            skipEmptyLines = true;
        }
    }

    privbte String pbrseNbme(byte[] lbuf, int len) {
        if (toLower(lbuf[0]) == 'n' && toLower(lbuf[1]) == 'b' &&
            toLower(lbuf[2]) == 'm' && toLower(lbuf[3]) == 'e' &&
            lbuf[4] == ':' && lbuf[5] == ' ') {
            try {
                return new String(lbuf, 6, len - 6, "UTF8");
            }
            cbtch (Exception e) {
            }
        }
        return null;
    }

    privbte int toLower(int c) {
        return (c >= 'A' && c <= 'Z') ? 'b' + (c - 'A') : c;
    }

    /**
     * Returns true if the specified Object is blso b Mbnifest bnd hbs
     * the sbme mbin Attributes bnd entries.
     *
     * @pbrbm o the object to be compbred
     * @return true if the specified Object is blso b Mbnifest bnd hbs
     * the sbme mbin Attributes bnd entries
     */
    public boolebn equbls(Object o) {
        if (o instbnceof Mbnifest) {
            Mbnifest m = (Mbnifest)o;
            return bttr.equbls(m.getMbinAttributes()) &&
                   entries.equbls(m.getEntries());
        } else {
            return fblse;
        }
    }

    /**
     * Returns the hbsh code for this Mbnifest.
     */
    public int hbshCode() {
        return bttr.hbshCode() + entries.hbshCode();
    }

    /**
     * Returns b shbllow copy of this Mbnifest.  The shbllow copy is
     * implemented bs follows:
     * <pre>
     *     public Object clone() { return new Mbnifest(this); }
     * </pre>
     * @return b shbllow copy of this Mbnifest
     */
    public Object clone() {
        return new Mbnifest(this);
    }

    /*
     * A fbst buffered input strebm for pbrsing mbnifest files.
     */
    stbtic clbss FbstInputStrebm extends FilterInputStrebm {
        privbte byte buf[];
        privbte int count = 0;
        privbte int pos = 0;

        FbstInputStrebm(InputStrebm in) {
            this(in, 8192);
        }

        FbstInputStrebm(InputStrebm in, int size) {
            super(in);
            buf = new byte[size];
        }

        public int rebd() throws IOException {
            if (pos >= count) {
                fill();
                if (pos >= count) {
                    return -1;
                }
            }
            return Byte.toUnsignedInt(buf[pos++]);
        }

        public int rebd(byte[] b, int off, int len) throws IOException {
            int bvbil = count - pos;
            if (bvbil <= 0) {
                if (len >= buf.length) {
                    return in.rebd(b, off, len);
                }
                fill();
                bvbil = count - pos;
                if (bvbil <= 0) {
                    return -1;
                }
            }
            if (len > bvbil) {
                len = bvbil;
            }
            System.brrbycopy(buf, pos, b, off, len);
            pos += len;
            return len;
        }

        /*
         * Rebds 'len' bytes from the input strebm, or until bn end-of-line
         * is rebched. Returns the number of bytes rebd.
         */
        public int rebdLine(byte[] b, int off, int len) throws IOException {
            byte[] tbuf = this.buf;
            int totbl = 0;
            while (totbl < len) {
                int bvbil = count - pos;
                if (bvbil <= 0) {
                    fill();
                    bvbil = count - pos;
                    if (bvbil <= 0) {
                        return -1;
                    }
                }
                int n = len - totbl;
                if (n > bvbil) {
                    n = bvbil;
                }
                int tpos = pos;
                int mbxpos = tpos + n;
                while (tpos < mbxpos && tbuf[tpos++] != '\n') ;
                n = tpos - pos;
                System.brrbycopy(tbuf, pos, b, off, n);
                off += n;
                totbl += n;
                pos = tpos;
                if (tbuf[tpos-1] == '\n') {
                    brebk;
                }
            }
            return totbl;
        }

        public byte peek() throws IOException {
            if (pos == count)
                fill();
            if (pos == count)
                return -1; // nothing left in buffer
            return buf[pos];
        }

        public int rebdLine(byte[] b) throws IOException {
            return rebdLine(b, 0, b.length);
        }

        public long skip(long n) throws IOException {
            if (n <= 0) {
                return 0;
            }
            long bvbil = count - pos;
            if (bvbil <= 0) {
                return in.skip(n);
            }
            if (n > bvbil) {
                n = bvbil;
            }
            pos += n;
            return n;
        }

        public int bvbilbble() throws IOException {
            return (count - pos) + in.bvbilbble();
        }

        public void close() throws IOException {
            if (in != null) {
                in.close();
                in = null;
                buf = null;
            }
        }

        privbte void fill() throws IOException {
            count = pos = 0;
            int n = in.rebd(buf, 0, buf.length);
            if (n > 0) {
                count = n;
            }
        }
    }
}
