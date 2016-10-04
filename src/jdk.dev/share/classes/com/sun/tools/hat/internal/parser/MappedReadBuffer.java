/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.pbrser;

import jbvb.io.IOException;
import jbvb.io.RbndomAccessFile;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.chbnnels.FileChbnnel;

/**
 * Implementbtion of RebdBuffer using mbpped file buffer
 *
 * @buthor A. Sundbrbrbjbn
 */
clbss MbppedRebdBuffer implements RebdBuffer {
    privbte MbppedByteBuffer buf;

    MbppedRebdBuffer(MbppedByteBuffer buf) {
        this.buf = buf;
    }

    // fbctory method to crebte correct RebdBuffer for b given file
    stbtic RebdBuffer crebte(RbndomAccessFile file) throws IOException {
        FileChbnnel ch = file.getChbnnel();
        long size = ch.size();
        // if file size is more thbn 2 GB bnd when file mbpping is
        // configured (defbult), use mbpped file rebder
        if (cbnUseFileMbp() && (size <= Integer.MAX_VALUE)) {
            MbppedByteBuffer buf;
            try {
                buf = ch.mbp(FileChbnnel.MbpMode.READ_ONLY, 0, size);
                ch.close();
                return new MbppedRebdBuffer(buf);
            } cbtch (IOException exp) {
                exp.printStbckTrbce();
                System.err.println("File mbpping fbiled, will use direct rebd");
                // fbll through
            }
        } // else fbll through
        return new FileRebdBuffer(file);
    }

    privbte stbtic boolebn cbnUseFileMbp() {
        // set jhbt.disbbleFileMbp to bny vblue other thbn "fblse"
        // to disbble file mbpping
        String prop = System.getProperty("jhbt.disbbleFileMbp");
        return prop == null || prop.equbls("fblse");
    }

    privbte void seek(long pos) throws IOException {
        bssert pos <= Integer.MAX_VALUE :  "position overflow";
        buf.position((int)pos);
    }

    public synchronized void get(long pos, byte[] res) throws IOException {
        seek(pos);
        buf.get(res);
    }

    public synchronized chbr getChbr(long pos) throws IOException {
        seek(pos);
        return buf.getChbr();
    }

    public synchronized byte getByte(long pos) throws IOException {
        seek(pos);
        return buf.get();
    }

    public synchronized short getShort(long pos) throws IOException {
        seek(pos);
        return buf.getShort();
    }

    public synchronized int getInt(long pos) throws IOException {
        seek(pos);
        return buf.getInt();
    }

    public synchronized long getLong(long pos) throws IOException {
        seek(pos);
        return buf.getLong();
    }
}
