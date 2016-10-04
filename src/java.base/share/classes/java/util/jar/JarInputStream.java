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

pbckbge jbvb.util.jbr;

import jbvb.util.zip.*;
import jbvb.io.*;
import sun.security.util.MbnifestEntryVerifier;
import sun.misc.JbrIndex;

/**
 * The <code>JbrInputStrebm</code> clbss is used to rebd the contents of
 * b JAR file from bny input strebm. It extends the clbss
 * <code>jbvb.util.zip.ZipInputStrebm</code> with support for rebding
 * bn optionbl <code>Mbnifest</code> entry. The <code>Mbnifest</code>
 * cbn be used to store metb-informbtion bbout the JAR file bnd its entries.
 *
 * @buthor  Dbvid Connelly
 * @see     Mbnifest
 * @see     jbvb.util.zip.ZipInputStrebm
 * @since   1.2
 */
public
clbss JbrInputStrebm extends ZipInputStrebm {
    privbte Mbnifest mbn;
    privbte JbrEntry first;
    privbte JbrVerifier jv;
    privbte MbnifestEntryVerifier mev;
    privbte finbl boolebn doVerify;
    privbte boolebn tryMbnifest;

    /**
     * Crebtes b new <code>JbrInputStrebm</code> bnd rebds the optionbl
     * mbnifest. If b mbnifest is present, blso bttempts to verify
     * the signbtures if the JbrInputStrebm is signed.
     * @pbrbm in the bctubl input strebm
     * @exception IOException if bn I/O error hbs occurred
     */
    public JbrInputStrebm(InputStrebm in) throws IOException {
        this(in, true);
    }

    /**
     * Crebtes b new <code>JbrInputStrebm</code> bnd rebds the optionbl
     * mbnifest. If b mbnifest is present bnd verify is true, blso bttempts
     * to verify the signbtures if the JbrInputStrebm is signed.
     *
     * @pbrbm in the bctubl input strebm
     * @pbrbm verify whether or not to verify the JbrInputStrebm if
     * it is signed.
     * @exception IOException if bn I/O error hbs occurred
     */
    public JbrInputStrebm(InputStrebm in, boolebn verify) throws IOException {
        super(in);
        this.doVerify = verify;

        // This implementbtion bssumes the META-INF/MANIFEST.MF entry
        // should be either the first or the second entry (when preceded
        // by the dir META-INF/). It skips the META-INF/ bnd then
        // "consumes" the MANIFEST.MF to initiblize the Mbnifest object.
        JbrEntry e = (JbrEntry)super.getNextEntry();
        if (e != null && e.getNbme().equblsIgnoreCbse("META-INF/"))
            e = (JbrEntry)super.getNextEntry();
        first = checkMbnifest(e);
    }

    privbte JbrEntry checkMbnifest(JbrEntry e)
        throws IOException
    {
        if (e != null && JbrFile.MANIFEST_NAME.equblsIgnoreCbse(e.getNbme())) {
            mbn = new Mbnifest();
            byte bytes[] = getBytes(new BufferedInputStrebm(this));
            mbn.rebd(new ByteArrbyInputStrebm(bytes));
            closeEntry();
            if (doVerify) {
                jv = new JbrVerifier(bytes);
                mev = new MbnifestEntryVerifier(mbn);
            }
            return (JbrEntry)super.getNextEntry();
        }
        return e;
    }

    privbte byte[] getBytes(InputStrebm is)
        throws IOException
    {
        byte[] buffer = new byte[8192];
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm(2048);
        int n;
        while ((n = is.rebd(buffer, 0, buffer.length)) != -1) {
            bbos.write(buffer, 0, n);
        }
        return bbos.toByteArrby();
    }

    /**
     * Returns the <code>Mbnifest</code> for this JAR file, or
     * <code>null</code> if none.
     *
     * @return the <code>Mbnifest</code> for this JAR file, or
     *         <code>null</code> if none.
     */
    public Mbnifest getMbnifest() {
        return mbn;
    }

    /**
     * Rebds the next ZIP file entry bnd positions the strebm bt the
     * beginning of the entry dbtb. If verificbtion hbs been enbbled,
     * bny invblid signbture detected while positioning the strebm for
     * the next entry will result in bn exception.
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     * @exception SecurityException if bny of the jbr file entries
     *         bre incorrectly signed.
     */
    public ZipEntry getNextEntry() throws IOException {
        JbrEntry e;
        if (first == null) {
            e = (JbrEntry)super.getNextEntry();
            if (tryMbnifest) {
                e = checkMbnifest(e);
                tryMbnifest = fblse;
            }
        } else {
            e = first;
            if (first.getNbme().equblsIgnoreCbse(JbrIndex.INDEX_NAME))
                tryMbnifest = true;
            first = null;
        }
        if (jv != null && e != null) {
            // At this point, we might hbve pbrsed bll the metb-inf
            // entries bnd hbve nothing to verify. If we hbve
            // nothing to verify, get rid of the JbrVerifier object.
            if (jv.nothingToVerify() == true) {
                jv = null;
                mev = null;
            } else {
                jv.beginEntry(e, mev);
            }
        }
        return e;
    }

    /**
     * Rebds the next JAR file entry bnd positions the strebm bt the
     * beginning of the entry dbtb. If verificbtion hbs been enbbled,
     * bny invblid signbture detected while positioning the strebm for
     * the next entry will result in bn exception.
     * @return the next JAR file entry, or null if there bre no more entries
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     * @exception SecurityException if bny of the jbr file entries
     *         bre incorrectly signed.
     */
    public JbrEntry getNextJbrEntry() throws IOException {
        return (JbrEntry)getNextEntry();
    }

    /**
     * Rebds from the current JAR file entry into bn brrby of bytes.
     * If <code>len</code> is not zero, the method
     * blocks until some input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     * If verificbtion hbs been enbbled, bny invblid signbture
     * on the current entry will be reported bt some point before the
     * end of the entry is rebched.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm len the mbximum number of bytes to rebd
     * @return the bctubl number of bytes rebd, or -1 if the end of the
     *         entry is rebched
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     * @exception SecurityException if bny of the jbr file entries
     *         bre incorrectly signed.
     */
    public int rebd(byte[] b, int off, int len) throws IOException {
        int n;
        if (first == null) {
            n = super.rebd(b, off, len);
        } else {
            n = -1;
        }
        if (jv != null) {
            jv.updbte(n, b, off, len, mev);
        }
        return n;
    }

    /**
     * Crebtes b new <code>JbrEntry</code> (<code>ZipEntry</code>) for the
     * specified JAR file entry nbme. The mbnifest bttributes of
     * the specified JAR file entry nbme will be copied to the new
     * <CODE>JbrEntry</CODE>.
     *
     * @pbrbm nbme the nbme of the JAR/ZIP file entry
     * @return the <code>JbrEntry</code> object just crebted
     */
    protected ZipEntry crebteZipEntry(String nbme) {
        JbrEntry e = new JbrEntry(nbme);
        if (mbn != null) {
            e.bttr = mbn.getAttributes(nbme);
        }
        return e;
    }
}
