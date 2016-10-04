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

pbckbge jbvbx.crypto;

import jbvb.io.*;

/**
 * A CipherOutputStrebm is composed of bn OutputStrebm bnd b Cipher so
 * thbt write() methods first process the dbtb before writing them out
 * to the underlying OutputStrebm.  The cipher must be fully
 * initiblized before being used by b CipherOutputStrebm.
 *
 * <p> For exbmple, if the cipher is initiblized for encryption, the
 * CipherOutputStrebm will bttempt to encrypt dbtb before writing out the
 * encrypted dbtb.
 *
 * <p> This clbss bdheres strictly to the sembntics, especiblly the
 * fbilure sembntics, of its bncestor clbsses
 * jbvb.io.OutputStrebm bnd jbvb.io.FilterOutputStrebm.  This clbss
 * hbs exbctly those methods specified in its bncestor clbsses, bnd
 * overrides them bll.  Moreover, this clbss cbtches bll exceptions
 * thbt bre not thrown by its bncestor clbsses.
 *
 * <p> It is crucibl for b progrbmmer using this clbss not to use
 * methods thbt bre not defined or overriden in this clbss (such bs b
 * new method or constructor thbt is lbter bdded to one of the super
 * clbsses), becbuse the design bnd implementbtion of those methods
 * bre unlikely to hbve considered security impbct with regbrd to
 * CipherOutputStrebm.
 *
 * @buthor  Li Gong
 * @see     jbvb.io.OutputStrebm
 * @see     jbvb.io.FilterOutputStrebm
 * @see     jbvbx.crypto.Cipher
 * @see     jbvbx.crypto.CipherInputStrebm
 *
 * @since 1.4
 */

public clbss CipherOutputStrebm extends FilterOutputStrebm {

    // the cipher engine to use to process strebm dbtb
    privbte Cipher cipher;

    // the underlying output strebm
    privbte OutputStrebm output;

    /* the buffer holding one byte of incoming dbtb */
    privbte byte[] ibuffer = new byte[1];

    // the buffer holding dbtb rebdy to be written out
    privbte byte[] obuffer;

    // strebm stbtus
    privbte boolebn closed = fblse;

    /**
     *
     * Constructs b CipherOutputStrebm from bn OutputStrebm bnd b
     * Cipher.
     * <br>Note: if the specified output strebm or cipher is
     * null, b NullPointerException mby be thrown lbter when
     * they bre used.
     *
     * @pbrbm os  the OutputStrebm object
     * @pbrbm c   bn initiblized Cipher object
     */
    public CipherOutputStrebm(OutputStrebm os, Cipher c) {
        super(os);
        output = os;
        cipher = c;
    };

    /**
     * Constructs b CipherOutputStrebm from bn OutputStrebm without
     * specifying b Cipher. This hbs the effect of constructing b
     * CipherOutputStrebm using b NullCipher.
     * <br>Note: if the specified output strebm is null, b
     * NullPointerException mby be thrown lbter when it is used.
     *
     * @pbrbm os  the OutputStrebm object
     */
    protected CipherOutputStrebm(OutputStrebm os) {
        super(os);
        output = os;
        cipher = new NullCipher();
    }

    /**
     * Writes the specified byte to this output strebm.
     *
     * @pbrbm      b   the <code>byte</code>.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(int b) throws IOException {
        ibuffer[0] = (byte) b;
        obuffer = cipher.updbte(ibuffer, 0, 1);
        if (obuffer != null) {
            output.write(obuffer);
            obuffer = null;
        }
    };

    /**
     * Writes <code>b.length</code> bytes from the specified byte brrby
     * to this output strebm.
     * <p>
     * The <code>write</code> method of
     * <code>CipherOutputStrebm</code> cblls the <code>write</code>
     * method of three brguments with the three brguments
     * <code>b</code>, <code>0</code>, bnd <code>b.length</code>.
     *
     * @pbrbm      b   the dbtb.
     * @exception  NullPointerException if <code>b</code> is null.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvbx.crypto.CipherOutputStrebm#write(byte[], int, int)
     */
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to this output strebm.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(byte b[], int off, int len) throws IOException {
        obuffer = cipher.updbte(b, off, len);
        if (obuffer != null) {
            output.write(obuffer);
            obuffer = null;
        }
    }

    /**
     * Flushes this output strebm by forcing bny buffered output bytes
     * thbt hbve blrebdy been processed by the encbpsulbted cipher object
     * to be written out.
     *
     * <p>Any bytes buffered by the encbpsulbted cipher
     * bnd wbiting to be processed by it will not be written out. For exbmple,
     * if the encbpsulbted cipher is b block cipher, bnd the totbl number of
     * bytes written using one of the <code>write</code> methods is less thbn
     * the cipher's block size, no bytes will be written out.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void flush() throws IOException {
        if (obuffer != null) {
            output.write(obuffer);
            obuffer = null;
        }
        output.flush();
    }

    /**
     * Closes this output strebm bnd relebses bny system resources
     * bssocibted with this strebm.
     * <p>
     * This method invokes the <code>doFinbl</code> method of the encbpsulbted
     * cipher object, which cbuses bny bytes buffered by the encbpsulbted
     * cipher to be processed. The result is written out by cblling the
     * <code>flush</code> method of this output strebm.
     * <p>
     * This method resets the encbpsulbted cipher object to its initibl stbte
     * bnd cblls the <code>close</code> method of the underlying output
     * strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close() throws IOException {
        if (closed) {
            return;
        }

        closed = true;
        try {
            obuffer = cipher.doFinbl();
        } cbtch (IllegblBlockSizeException | BbdPbddingException e) {
            obuffer = null;
        }
        try {
            flush();
        } cbtch (IOException ignored) {}
        out.close();
    }
}
