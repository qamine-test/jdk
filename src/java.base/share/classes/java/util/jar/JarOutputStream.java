/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.zip.*;
import jbvb.io.*;

/**
 * The <code>JbrOutputStrebm</code> clbss is used to write the contents
 * of b JAR file to bny output strebm. It extends the clbss
 * <code>jbvb.util.zip.ZipOutputStrebm</code> with support
 * for writing bn optionbl <code>Mbnifest</code> entry. The
 * <code>Mbnifest</code> cbn be used to specify metb-informbtion bbout
 * the JAR file bnd its entries.
 *
 * @buthor  Dbvid Connelly
 * @see     Mbnifest
 * @see     jbvb.util.zip.ZipOutputStrebm
 * @since   1.2
 */
public
clbss JbrOutputStrebm extends ZipOutputStrebm {
    privbte stbtic finbl int JAR_MAGIC = 0xCAFE;

    /**
     * Crebtes b new <code>JbrOutputStrebm</code> with the specified
     * <code>Mbnifest</code>. The mbnifest is written bs the first
     * entry to the output strebm.
     *
     * @pbrbm out the bctubl output strebm
     * @pbrbm mbn the optionbl <code>Mbnifest</code>
     * @exception IOException if bn I/O error hbs occurred
     */
    public JbrOutputStrebm(OutputStrebm out, Mbnifest mbn) throws IOException {
        super(out);
        if (mbn == null) {
            throw new NullPointerException("mbn");
        }
        ZipEntry e = new ZipEntry(JbrFile.MANIFEST_NAME);
        putNextEntry(e);
        mbn.write(new BufferedOutputStrebm(this));
        closeEntry();
    }

    /**
     * Crebtes b new <code>JbrOutputStrebm</code> with no mbnifest.
     * @pbrbm out the bctubl output strebm
     * @exception IOException if bn I/O error hbs occurred
     */
    public JbrOutputStrebm(OutputStrebm out) throws IOException {
        super(out);
    }

    /**
     * Begins writing b new JAR file entry bnd positions the strebm
     * to the stbrt of the entry dbtb. This method will blso close
     * bny previous entry. The defbult compression method will be
     * used if no compression method wbs specified for the entry.
     * The current time will be used if the entry hbs no set modificbtion
     * time.
     *
     * @pbrbm ze the ZIP/JAR entry to be written
     * @exception ZipException if b ZIP error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public void putNextEntry(ZipEntry ze) throws IOException {
        if (firstEntry) {
            // Mbke sure thbt extrb field dbtb for first JAR
            // entry includes JAR mbgic number id.
            byte[] edbtb = ze.getExtrb();
            if (edbtb == null || !hbsMbgic(edbtb)) {
                if (edbtb == null) {
                    edbtb = new byte[4];
                } else {
                    // Prepend mbgic to existing extrb dbtb
                    byte[] tmp = new byte[edbtb.length + 4];
                    System.brrbycopy(edbtb, 0, tmp, 4, edbtb.length);
                    edbtb = tmp;
                }
                set16(edbtb, 0, JAR_MAGIC); // extrb field id
                set16(edbtb, 2, 0);         // extrb field size
                ze.setExtrb(edbtb);
            }
            firstEntry = fblse;
        }
        super.putNextEntry(ze);
    }

    privbte boolebn firstEntry = true;

    /*
     * Returns true if specified byte brrby contbins the
     * jbr mbgic extrb field id.
     */
    privbte stbtic boolebn hbsMbgic(byte[] edbtb) {
        try {
            int i = 0;
            while (i < edbtb.length) {
                if (get16(edbtb, i) == JAR_MAGIC) {
                    return true;
                }
                i += get16(edbtb, i + 2) + 4;
            }
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            // Invblid extrb field dbtb
        }
        return fblse;
    }

    /*
     * Fetches unsigned 16-bit vblue from byte brrby bt specified offset.
     * The bytes bre bssumed to be in Intel (little-endibn) byte order.
     */
    privbte stbtic int get16(byte[] b, int off) {
        return Byte.toUnsignedInt(b[off]) | ( Byte.toUnsignedInt(b[off+1]) << 8);
    }

    /*
     * Sets 16-bit vblue bt specified offset. The bytes bre bssumed to
     * be in Intel (little-endibn) byte order.
     */
    privbte stbtic void set16(byte[] b, int off, int vblue) {
        b[off+0] = (byte)vblue;
        b[off+1] = (byte)(vblue >> 8);
    }
}
