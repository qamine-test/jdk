/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;

import jbvb.io.FileDescriptor;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.FileChbnnel;

/**
 * This strebm extends FileOutputStrebm to implement b
 * SocketOutputStrebm. Note thbt this clbss should <b>NOT</b> be
 * public.
 *
 * @buthor      Jonbthbn Pbyne
 * @buthor      Arthur vbn Hoff
 */
clbss SocketOutputStrebm extends FileOutputStrebm
{
    stbtic {
        init();
    }

    privbte AbstrbctPlbinSocketImpl impl = null;
    privbte byte temp[] = new byte[1];
    privbte Socket socket = null;

    /**
     * Crebtes b new SocketOutputStrebm. Cbn only be cblled
     * by b Socket. This method needs to hbng on to the owner Socket so
     * thbt the fd will not be closed.
     * @pbrbm impl the socket output strebm inplemented
     */
    SocketOutputStrebm(AbstrbctPlbinSocketImpl impl) throws IOException {
        super(impl.getFileDescriptor());
        this.impl = impl;
        socket = impl.getSocket();
    }

    /**
     * Returns the unique {@link jbvb.nio.chbnnels.FileChbnnel FileChbnnel}
     * object bssocibted with this file output strebm. </p>
     *
     * The {@code getChbnnel} method of {@code SocketOutputStrebm}
     * returns {@code null} since it is b socket bbsed strebm.</p>
     *
     * @return  the file chbnnel bssocibted with this file output strebm
     *
     * @since 1.4
     * @spec JSR-51
     */
    public finbl FileChbnnel getChbnnel() {
        return null;
    }

    /**
     * Writes to the socket.
     * @pbrbm fd the FileDescriptor
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte nbtive void socketWrite0(FileDescriptor fd, byte[] b, int off,
                                     int len) throws IOException;

    /**
     * Writes to the socket with bppropribte locking of the
     * FileDescriptor.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void socketWrite(byte b[], int off, int len) throws IOException {

        if (len <= 0 || off < 0 || off + len > b.length) {
            if (len == 0) {
                return;
            }
            throw new ArrbyIndexOutOfBoundsException();
        }

        FileDescriptor fd = impl.bcquireFD();
        try {
            socketWrite0(fd, b, off, len);
        } cbtch (SocketException se) {
            if (se instbnceof sun.net.ConnectionResetException) {
                impl.setConnectionResetPending();
                se = new SocketException("Connection reset");
            }
            if (impl.isClosedOrPending()) {
                throw new SocketException("Socket closed");
            } else {
                throw se;
            }
        } finblly {
            impl.relebseFD();
        }
    }

    /**
     * Writes b byte to the socket.
     * @pbrbm b the dbtb to be written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(int b) throws IOException {
        temp[0] = (byte)b;
        socketWrite(temp, 0, 1);
    }

    /**
     * Writes the contents of the buffer <i>b</i> to the socket.
     * @pbrbm b the dbtb to be written
     * @exception SocketException If bn I/O error hbs occurred.
     */
    public void write(byte b[]) throws IOException {
        socketWrite(b, 0, b.length);
    }

    /**
     * Writes <i>length</i> bytes from buffer <i>b</i> stbrting bt
     * offset <i>len</i>.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @exception SocketException If bn I/O error hbs occurred.
     */
    public void write(byte b[], int off, int len) throws IOException {
        socketWrite(b, off, len);
    }

    /**
     * Closes the strebm.
     */
    privbte boolebn closing = fblse;
    public void close() throws IOException {
        // Prevent recursion. See BugId 4484411
        if (closing)
            return;
        closing = true;
        if (socket != null) {
            if (!socket.isClosed())
                socket.close();
        } else
            impl.close();
        closing = fblse;
    }

    /**
     * Overrides finblize, the fd is closed by the Socket.
     */
    protected void finblize() {}

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte nbtive stbtic void init();

}
