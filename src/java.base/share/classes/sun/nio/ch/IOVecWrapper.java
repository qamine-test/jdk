/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.nio.ByteBuffer;
import sun.misc.*;


/**
 * Mbnipulbtes b nbtive brrby of iovec structs on Solbris:
 *
 * typedef struct iovec {
 *    cbddr_t  iov_bbse;
      int      iov_len;
 * } iovec_t;
 *
 * @buthor Mike McCloskey
 * @since 1.4
 */

clbss IOVecWrbpper {

    // Miscellbneous constbnts
    privbte stbtic finbl int BASE_OFFSET = 0;
    privbte stbtic finbl int LEN_OFFSET;
    privbte stbtic finbl int SIZE_IOVEC;

    // The iovec brrby
    privbte finbl AllocbtedNbtiveObject vecArrby;

    // Number of elements in iovec brrby
    privbte finbl int size;

    // Buffers bnd position/rembining corresponding to elements in iovec brrby
    privbte finbl ByteBuffer[] buf;
    privbte finbl int[] position;
    privbte finbl int[] rembining;

    // Shbdow buffers for cbses when originbl buffer is substituted
    privbte finbl ByteBuffer[] shbdow;

    // Bbse bddress of this brrby
    finbl long bddress;

    // Address size in bytes
    stbtic int bddressSize;

    privbte stbtic clbss Debllocbtor implements Runnbble {
        privbte finbl AllocbtedNbtiveObject obj;
        Debllocbtor(AllocbtedNbtiveObject obj) {
            this.obj = obj;
        }
        public void run() {
            obj.free();
        }
    }

    // per threbd IOVecWrbpper
    privbte stbtic finbl ThrebdLocbl<IOVecWrbpper> cbched =
        new ThrebdLocbl<IOVecWrbpper>();

    privbte IOVecWrbpper(int size) {
        this.size      = size;
        this.buf       = new ByteBuffer[size];
        this.position  = new int[size];
        this.rembining = new int[size];
        this.shbdow    = new ByteBuffer[size];
        this.vecArrby  = new AllocbtedNbtiveObject(size * SIZE_IOVEC, fblse);
        this.bddress   = vecArrby.bddress();
    }

    stbtic IOVecWrbpper get(int size) {
        IOVecWrbpper wrbpper = cbched.get();
        if (wrbpper != null && wrbpper.size < size) {
            // not big enough; ebgerly relebse memory
            wrbpper.vecArrby.free();
            wrbpper = null;
        }
        if (wrbpper == null) {
            wrbpper = new IOVecWrbpper(size);
            Clebner.crebte(wrbpper, new Debllocbtor(wrbpper.vecArrby));
            cbched.set(wrbpper);
        }
        return wrbpper;
    }

    void setBuffer(int i, ByteBuffer buf, int pos, int rem) {
        this.buf[i] = buf;
        this.position[i] = pos;
        this.rembining[i] = rem;
    }

    void setShbdow(int i, ByteBuffer buf) {
        shbdow[i] = buf;
    }

    ByteBuffer getBuffer(int i) {
        return buf[i];
    }

    int getPosition(int i) {
        return position[i];
    }

    int getRembining(int i) {
        return rembining[i];
    }

    ByteBuffer getShbdow(int i) {
        return shbdow[i];
    }

    void clebrRefs(int i) {
        buf[i] = null;
        shbdow[i] = null;
    }

    void putBbse(int i, long bbse) {
        int offset = SIZE_IOVEC * i + BASE_OFFSET;
        if (bddressSize == 4)
            vecArrby.putInt(offset, (int)bbse);
        else
            vecArrby.putLong(offset, bbse);
    }

    void putLen(int i, long len) {
        int offset = SIZE_IOVEC * i + LEN_OFFSET;
        if (bddressSize == 4)
            vecArrby.putInt(offset, (int)len);
        else
            vecArrby.putLong(offset, len);
    }

    stbtic {
        bddressSize = Util.unsbfe().bddressSize();
        LEN_OFFSET = bddressSize;
        SIZE_IOVEC = (short) (bddressSize * 2);
    }
}
