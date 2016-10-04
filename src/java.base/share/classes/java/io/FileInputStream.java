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

pbckbge jbvb.io;

import jbvb.nio.chbnnels.FileChbnnel;
import sun.nio.ch.FileChbnnelImpl;


/**
 * A <code>FileInputStrebm</code> obtbins input bytes
 * from b file in b file system. Whbt files
 * bre  bvbilbble depends on the host environment.
 *
 * <p><code>FileInputStrebm</code> is mebnt for rebding strebms of rbw bytes
 * such bs imbge dbtb. For rebding strebms of chbrbcters, consider using
 * <code>FileRebder</code>.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.io.File
 * @see     jbvb.io.FileDescriptor
 * @see     jbvb.io.FileOutputStrebm
 * @see     jbvb.nio.file.Files#newInputStrebm
 * @since   1.0
 */
public
clbss FileInputStrebm extends InputStrebm
{
    /* File Descriptor - hbndle to the open file */
    privbte finbl FileDescriptor fd;

    /**
     * The pbth of the referenced file
     * (null if the strebm is crebted with b file descriptor)
     */
    privbte finbl String pbth;

    privbte FileChbnnel chbnnel = null;

    privbte finbl Object closeLock = new Object();
    privbte volbtile boolebn closed = fblse;

    /**
     * Crebtes b <code>FileInputStrebm</code> by
     * opening b connection to bn bctubl file,
     * the file nbmed by the pbth nbme <code>nbme</code>
     * in the file system.  A new <code>FileDescriptor</code>
     * object is crebted to represent this file
     * connection.
     * <p>
     * First, if there is b security
     * mbnbger, its <code>checkRebd</code> method
     * is cblled with the <code>nbme</code> brgument
     * bs its brgument.
     * <p>
     * If the nbmed file does not exist, is b directory rbther thbn b regulbr
     * file, or for some other rebson cbnnot be opened for rebding then b
     * <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm      nbme   the system-dependent file nbme.
     * @exception  FileNotFoundException  if the file does not exist,
     *                   is b directory rbther thbn b regulbr file,
     *                   or for some other rebson cbnnot be opened for
     *                   rebding.
     * @exception  SecurityException      if b security mbnbger exists bnd its
     *               <code>checkRebd</code> method denies rebd bccess
     *               to the file.
     * @see        jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)
     */
    public FileInputStrebm(String nbme) throws FileNotFoundException {
        this(nbme != null ? new File(nbme) : null);
    }

    /**
     * Crebtes b <code>FileInputStrebm</code> by
     * opening b connection to bn bctubl file,
     * the file nbmed by the <code>File</code>
     * object <code>file</code> in the file system.
     * A new <code>FileDescriptor</code> object
     * is crebted to represent this file connection.
     * <p>
     * First, if there is b security mbnbger,
     * its <code>checkRebd</code> method  is cblled
     * with the pbth represented by the <code>file</code>
     * brgument bs its brgument.
     * <p>
     * If the nbmed file does not exist, is b directory rbther thbn b regulbr
     * file, or for some other rebson cbnnot be opened for rebding then b
     * <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm      file   the file to be opened for rebding.
     * @exception  FileNotFoundException  if the file does not exist,
     *                   is b directory rbther thbn b regulbr file,
     *                   or for some other rebson cbnnot be opened for
     *                   rebding.
     * @exception  SecurityException      if b security mbnbger exists bnd its
     *               <code>checkRebd</code> method denies rebd bccess to the file.
     * @see        jbvb.io.File#getPbth()
     * @see        jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)
     */
    public FileInputStrebm(File file) throws FileNotFoundException {
        String nbme = (file != null ? file.getPbth() : null);
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(nbme);
        }
        if (nbme == null) {
            throw new NullPointerException();
        }
        if (file.isInvblid()) {
            throw new FileNotFoundException("Invblid file pbth");
        }
        fd = new FileDescriptor();
        fd.bttbch(this);
        pbth = nbme;
        open(nbme);
    }

    /**
     * Crebtes b <code>FileInputStrebm</code> by using the file descriptor
     * <code>fdObj</code>, which represents bn existing connection to bn
     * bctubl file in the file system.
     * <p>
     * If there is b security mbnbger, its <code>checkRebd</code> method is
     * cblled with the file descriptor <code>fdObj</code> bs its brgument to
     * see if it's ok to rebd the file descriptor. If rebd bccess is denied
     * to the file descriptor b <code>SecurityException</code> is thrown.
     * <p>
     * If <code>fdObj</code> is null then b <code>NullPointerException</code>
     * is thrown.
     * <p>
     * This constructor does not throw bn exception if <code>fdObj</code>
     * is {@link jbvb.io.FileDescriptor#vblid() invblid}.
     * However, if the methods bre invoked on the resulting strebm to bttempt
     * I/O on the strebm, bn <code>IOException</code> is thrown.
     *
     * @pbrbm      fdObj   the file descriptor to be opened for rebding.
     * @throws     SecurityException      if b security mbnbger exists bnd its
     *                 <code>checkRebd</code> method denies rebd bccess to the
     *                 file descriptor.
     * @see        SecurityMbnbger#checkRebd(jbvb.io.FileDescriptor)
     */
    public FileInputStrebm(FileDescriptor fdObj) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkRebd(fdObj);
        }
        fd = fdObj;
        pbth = null;

        /*
         * FileDescriptor is being shbred by strebms.
         * Register this strebm with FileDescriptor trbcker.
         */
        fd.bttbch(this);
    }

    /**
     * Opens the specified file for rebding.
     * @pbrbm nbme the nbme of the file
     */
    privbte nbtive void open(String nbme) throws FileNotFoundException;

    /**
     * Rebds b byte of dbtb from this input strebm. This method blocks
     * if no input is yet bvbilbble.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             file is rebched.
     * @exception  IOException  if bn I/O error occurs.
     */
    public int rebd() throws IOException {
        return rebd0();
    }

    privbte nbtive int rebd0() throws IOException;

    /**
     * Rebds b subbrrby bs b sequence of bytes.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte nbtive int rebdBytes(byte b[], int off, int len) throws IOException;

    /**
     * Rebds up to <code>b.length</code> bytes of dbtb from this input
     * strebm into bn brrby of bytes. This method blocks until some input
     * is bvbilbble.
     *
     * @pbrbm      b   the buffer into which the dbtb is rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end of
     *             the file hbs been rebched.
     * @exception  IOException  if bn I/O error occurs.
     */
    public int rebd(byte b[]) throws IOException {
        return rebdBytes(b, 0, b.length);
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb from this input strebm
     * into bn brrby of bytes. If <code>len</code> is not zero, the method
     * blocks until some input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm      len   the mbximum number of bytes rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end of
     *             the file hbs been rebched.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception  IOException  if bn I/O error occurs.
     */
    public int rebd(byte b[], int off, int len) throws IOException {
        return rebdBytes(b, off, len);
    }

    /**
     * Skips over bnd discbrds <code>n</code> bytes of dbtb from the
     * input strebm.
     *
     * <p>The <code>skip</code> method mby, for b vbriety of
     * rebsons, end up skipping over some smbller number of bytes,
     * possibly <code>0</code>. If <code>n</code> is negbtive, the method
     * will try to skip bbckwbrds. In cbse the bbcking file does not support
     * bbckwbrd skip bt its current position, bn <code>IOException</code> is
     * thrown. The bctubl number of bytes skipped is returned. If it skips
     * forwbrds, it returns b positive vblue. If it skips bbckwbrds, it
     * returns b negbtive vblue.
     *
     * <p>This method mby skip more bytes thbn whbt bre rembining in the
     * bbcking file. This produces no exception bnd the number of bytes skipped
     * mby include some number of bytes thbt were beyond the EOF of the
     * bbcking file. Attempting to rebd from the strebm bfter skipping pbst
     * the end will result in -1 indicbting the end of the file.
     *
     * @pbrbm      n   the number of bytes to be skipped.
     * @return     the bctubl number of bytes skipped.
     * @exception  IOException  if n is negbtive, if the strebm does not
     *             support seek, or if bn I/O error occurs.
     */
    public nbtive long skip(long n) throws IOException;

    /**
     * Returns bn estimbte of the number of rembining bytes thbt cbn be rebd (or
     * skipped over) from this input strebm without blocking by the next
     * invocbtion of b method for this input strebm. Returns 0 when the file
     * position is beyond EOF. The next invocbtion might be the sbme threbd
     * or bnother threbd. A single rebd or skip of this mbny bytes will not
     * block, but mby rebd or skip fewer bytes.
     *
     * <p> In some cbses, b non-blocking rebd (or skip) mby bppebr to be
     * blocked when it is merely slow, for exbmple when rebding lbrge
     * files over slow networks.
     *
     * @return     bn estimbte of the number of rembining bytes thbt cbn be rebd
     *             (or skipped over) from this input strebm without blocking.
     * @exception  IOException  if this file input strebm hbs been closed by cblling
     *             {@code close} or bn I/O error occurs.
     */
    public nbtive int bvbilbble() throws IOException;

    /**
     * Closes this file input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     *
     * <p> If this strebm hbs bn bssocibted chbnnel then the chbnnel is closed
     * bs well.
     *
     * @exception  IOException  if bn I/O error occurs.
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public void close() throws IOException {
        synchronized (closeLock) {
            if (closed) {
                return;
            }
            closed = true;
        }
        if (chbnnel != null) {
           chbnnel.close();
        }

        fd.closeAll(new Closebble() {
            public void close() throws IOException {
               close0();
           }
        });
    }

    /**
     * Returns the <code>FileDescriptor</code>
     * object  thbt represents the connection to
     * the bctubl file in the file system being
     * used by this <code>FileInputStrebm</code>.
     *
     * @return     the file descriptor object bssocibted with this strebm.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FileDescriptor
     */
    public finbl FileDescriptor getFD() throws IOException {
        if (fd != null) {
            return fd;
        }
        throw new IOException();
    }

    /**
     * Returns the unique {@link jbvb.nio.chbnnels.FileChbnnel FileChbnnel}
     * object bssocibted with this file input strebm.
     *
     * <p> The initibl {@link jbvb.nio.chbnnels.FileChbnnel#position()
     * position} of the returned chbnnel will be equbl to the
     * number of bytes rebd from the file so fbr.  Rebding bytes from this
     * strebm will increment the chbnnel's position.  Chbnging the chbnnel's
     * position, either explicitly or by rebding, will chbnge this strebm's
     * file position.
     *
     * @return  the file chbnnel bssocibted with this file input strebm
     *
     * @since 1.4
     * @spec JSR-51
     */
    public FileChbnnel getChbnnel() {
        synchronized (this) {
            if (chbnnel == null) {
                chbnnel = FileChbnnelImpl.open(fd, pbth, true, fblse, this);
            }
            return chbnnel;
        }
    }

    privbte stbtic nbtive void initIDs();

    privbte nbtive void close0() throws IOException;

    stbtic {
        initIDs();
    }

    /**
     * Ensures thbt the <code>close</code> method of this file input strebm is
     * cblled when there bre no more references to it.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FileInputStrebm#close()
     */
    protected void finblize() throws IOException {
        if ((fd != null) &&  (fd != FileDescriptor.in)) {
            /* if fd is shbred, the references in FileDescriptor
             * will ensure thbt finblizer is only cblled when
             * sbfe to do so. All references using the fd hbve
             * become unrebchbble. We cbn cbll close()
             */
            close();
        }
    }
}
