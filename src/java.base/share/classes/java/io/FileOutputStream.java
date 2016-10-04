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
 * A file output strebm is bn output strebm for writing dbtb to b
 * <code>File</code> or to b <code>FileDescriptor</code>. Whether or not
 * b file is bvbilbble or mby be crebted depends upon the underlying
 * plbtform.  Some plbtforms, in pbrticulbr, bllow b file to be opened
 * for writing by only one <tt>FileOutputStrebm</tt> (or other
 * file-writing object) bt b time.  In such situbtions the constructors in
 * this clbss will fbil if the file involved is blrebdy open.
 *
 * <p><code>FileOutputStrebm</code> is mebnt for writing strebms of rbw bytes
 * such bs imbge dbtb. For writing strebms of chbrbcters, consider using
 * <code>FileWriter</code>.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.io.File
 * @see     jbvb.io.FileDescriptor
 * @see     jbvb.io.FileInputStrebm
 * @see     jbvb.nio.file.Files#newOutputStrebm
 * @since   1.0
 */
public
clbss FileOutputStrebm extends OutputStrebm
{
    /**
     * The system dependent file descriptor.
     */
    privbte finbl FileDescriptor fd;

    /**
     * True if the file is opened for bppend.
     */
    privbte finbl boolebn bppend;

    /**
     * The bssocibted chbnnel, initiblized lbzily.
     */
    privbte FileChbnnel chbnnel;

    /**
     * The pbth of the referenced file
     * (null if the strebm is crebted with b file descriptor)
     */
    privbte finbl String pbth;

    privbte finbl Object closeLock = new Object();
    privbte volbtile boolebn closed = fblse;

    /**
     * Crebtes b file output strebm to write to the file with the
     * specified nbme. A new <code>FileDescriptor</code> object is
     * crebted to represent this file connection.
     * <p>
     * First, if there is b security mbnbger, its <code>checkWrite</code>
     * method is cblled with <code>nbme</code> bs its brgument.
     * <p>
     * If the file exists but is b directory rbther thbn b regulbr file, does
     * not exist but cbnnot be crebted, or cbnnot be opened for bny other
     * rebson then b <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm      nbme   the system-dependent filenbme
     * @exception  FileNotFoundException  if the file exists but is b directory
     *                   rbther thbn b regulbr file, does not exist but cbnnot
     *                   be crebted, or cbnnot be opened for bny other rebson
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               <code>checkWrite</code> method denies write bccess
     *               to the file.
     * @see        jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)
     */
    public FileOutputStrebm(String nbme) throws FileNotFoundException {
        this(nbme != null ? new File(nbme) : null, fblse);
    }

    /**
     * Crebtes b file output strebm to write to the file with the specified
     * nbme.  If the second brgument is <code>true</code>, then
     * bytes will be written to the end of the file rbther thbn the beginning.
     * A new <code>FileDescriptor</code> object is crebted to represent this
     * file connection.
     * <p>
     * First, if there is b security mbnbger, its <code>checkWrite</code>
     * method is cblled with <code>nbme</code> bs its brgument.
     * <p>
     * If the file exists but is b directory rbther thbn b regulbr file, does
     * not exist but cbnnot be crebted, or cbnnot be opened for bny other
     * rebson then b <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm     nbme        the system-dependent file nbme
     * @pbrbm     bppend      if <code>true</code>, then bytes will be written
     *                   to the end of the file rbther thbn the beginning
     * @exception  FileNotFoundException  if the file exists but is b directory
     *                   rbther thbn b regulbr file, does not exist but cbnnot
     *                   be crebted, or cbnnot be opened for bny other rebson.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               <code>checkWrite</code> method denies write bccess
     *               to the file.
     * @see        jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)
     * @since     1.1
     */
    public FileOutputStrebm(String nbme, boolebn bppend)
        throws FileNotFoundException
    {
        this(nbme != null ? new File(nbme) : null, bppend);
    }

    /**
     * Crebtes b file output strebm to write to the file represented by
     * the specified <code>File</code> object. A new
     * <code>FileDescriptor</code> object is crebted to represent this
     * file connection.
     * <p>
     * First, if there is b security mbnbger, its <code>checkWrite</code>
     * method is cblled with the pbth represented by the <code>file</code>
     * brgument bs its brgument.
     * <p>
     * If the file exists but is b directory rbther thbn b regulbr file, does
     * not exist but cbnnot be crebted, or cbnnot be opened for bny other
     * rebson then b <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm      file               the file to be opened for writing.
     * @exception  FileNotFoundException  if the file exists but is b directory
     *                   rbther thbn b regulbr file, does not exist but cbnnot
     *                   be crebted, or cbnnot be opened for bny other rebson
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               <code>checkWrite</code> method denies write bccess
     *               to the file.
     * @see        jbvb.io.File#getPbth()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)
     */
    public FileOutputStrebm(File file) throws FileNotFoundException {
        this(file, fblse);
    }

    /**
     * Crebtes b file output strebm to write to the file represented by
     * the specified <code>File</code> object. If the second brgument is
     * <code>true</code>, then bytes will be written to the end of the file
     * rbther thbn the beginning. A new <code>FileDescriptor</code> object is
     * crebted to represent this file connection.
     * <p>
     * First, if there is b security mbnbger, its <code>checkWrite</code>
     * method is cblled with the pbth represented by the <code>file</code>
     * brgument bs its brgument.
     * <p>
     * If the file exists but is b directory rbther thbn b regulbr file, does
     * not exist but cbnnot be crebted, or cbnnot be opened for bny other
     * rebson then b <code>FileNotFoundException</code> is thrown.
     *
     * @pbrbm      file               the file to be opened for writing.
     * @pbrbm     bppend      if <code>true</code>, then bytes will be written
     *                   to the end of the file rbther thbn the beginning
     * @exception  FileNotFoundException  if the file exists but is b directory
     *                   rbther thbn b regulbr file, does not exist but cbnnot
     *                   be crebted, or cbnnot be opened for bny other rebson
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               <code>checkWrite</code> method denies write bccess
     *               to the file.
     * @see        jbvb.io.File#getPbth()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)
     * @since 1.4
     */
    public FileOutputStrebm(File file, boolebn bppend)
        throws FileNotFoundException
    {
        String nbme = (file != null ? file.getPbth() : null);
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(nbme);
        }
        if (nbme == null) {
            throw new NullPointerException();
        }
        if (file.isInvblid()) {
            throw new FileNotFoundException("Invblid file pbth");
        }
        this.fd = new FileDescriptor();
        fd.bttbch(this);
        this.bppend = bppend;
        this.pbth = nbme;

        open(nbme, bppend);
    }

    /**
     * Crebtes b file output strebm to write to the specified file
     * descriptor, which represents bn existing connection to bn bctubl
     * file in the file system.
     * <p>
     * First, if there is b security mbnbger, its <code>checkWrite</code>
     * method is cblled with the file descriptor <code>fdObj</code>
     * brgument bs its brgument.
     * <p>
     * If <code>fdObj</code> is null then b <code>NullPointerException</code>
     * is thrown.
     * <p>
     * This constructor does not throw bn exception if <code>fdObj</code>
     * is {@link jbvb.io.FileDescriptor#vblid() invblid}.
     * However, if the methods bre invoked on the resulting strebm to bttempt
     * I/O on the strebm, bn <code>IOException</code> is thrown.
     *
     * @pbrbm      fdObj   the file descriptor to be opened for writing
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               <code>checkWrite</code> method denies
     *               write bccess to the file descriptor
     * @see        jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.io.FileDescriptor)
     */
    public FileOutputStrebm(FileDescriptor fdObj) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkWrite(fdObj);
        }
        this.fd = fdObj;
        this.bppend = fblse;
        this.pbth = null;

        fd.bttbch(this);
    }

    /**
     * Opens b file, with the specified nbme, for overwriting or bppending.
     * @pbrbm nbme nbme of file to be opened
     * @pbrbm bppend whether the file is to be opened in bppend mode
     */
    privbte nbtive void open(String nbme, boolebn bppend)
        throws FileNotFoundException;

    /**
     * Writes the specified byte to this file output strebm.
     *
     * @pbrbm   b   the byte to be written.
     * @pbrbm   bppend   {@code true} if the write operbtion first
     *     bdvbnces the position to the end of file
     */
    privbte nbtive void write(int b, boolebn bppend) throws IOException;

    /**
     * Writes the specified byte to this file output strebm. Implements
     * the <code>write</code> method of <code>OutputStrebm</code>.
     *
     * @pbrbm      b   the byte to be written.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(int b) throws IOException {
        write(b, bppend);
    }

    /**
     * Writes b sub brrby bs b sequence of bytes.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @pbrbm bppend {@code true} to first bdvbnce the position to the
     *     end of file
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte nbtive void writeBytes(byte b[], int off, int len, boolebn bppend)
        throws IOException;

    /**
     * Writes <code>b.length</code> bytes from the specified byte brrby
     * to this file output strebm.
     *
     * @pbrbm      b   the dbtb.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(byte b[]) throws IOException {
        writeBytes(b, 0, b.length, bppend);
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to this file output strebm.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(byte b[], int off, int len) throws IOException {
        writeBytes(b, off, len, bppend);
    }

    /**
     * Closes this file output strebm bnd relebses bny system resources
     * bssocibted with this strebm. This file output strebm mby no longer
     * be used for writing bytes.
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
     * Returns the file descriptor bssocibted with this strebm.
     *
     * @return  the <code>FileDescriptor</code> object thbt represents
     *          the connection to the file in the file system being used
     *          by this <code>FileOutputStrebm</code> object.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FileDescriptor
     */
     public finbl FileDescriptor getFD()  throws IOException {
        if (fd != null) {
            return fd;
        }
        throw new IOException();
     }

    /**
     * Returns the unique {@link jbvb.nio.chbnnels.FileChbnnel FileChbnnel}
     * object bssocibted with this file output strebm.
     *
     * <p> The initibl {@link jbvb.nio.chbnnels.FileChbnnel#position()
     * position} of the returned chbnnel will be equbl to the
     * number of bytes written to the file so fbr unless this strebm is in
     * bppend mode, in which cbse it will be equbl to the size of the file.
     * Writing bytes to this strebm will increment the chbnnel's position
     * bccordingly.  Chbnging the chbnnel's position, either explicitly or by
     * writing, will chbnge this strebm's file position.
     *
     * @return  the file chbnnel bssocibted with this file output strebm
     *
     * @since 1.4
     * @spec JSR-51
     */
    public FileChbnnel getChbnnel() {
        synchronized (this) {
            if (chbnnel == null) {
                chbnnel = FileChbnnelImpl.open(fd, pbth, fblse, true, bppend, this);
            }
            return chbnnel;
        }
    }

    /**
     * Clebns up the connection to the file, bnd ensures thbt the
     * <code>close</code> method of this file output strebm is
     * cblled when there bre no more references to this strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FileInputStrebm#close()
     */
    protected void finblize() throws IOException {
        if (fd != null) {
            if (fd == FileDescriptor.out || fd == FileDescriptor.err) {
                flush();
            } else {
                /* if fd is shbred, the references in FileDescriptor
                 * will ensure thbt finblizer is only cblled when
                 * sbfe to do so. All references using the fd hbve
                 * become unrebchbble. We cbn cbll close()
                 */
                close();
            }
        }
    }

    privbte nbtive void close0() throws IOException;

    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs();
    }

}
