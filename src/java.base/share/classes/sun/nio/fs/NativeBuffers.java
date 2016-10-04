/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import sun.misc.Unsbfe;

/**
 * Fbctory for nbtive buffers.
 */

clbss NbtiveBuffers {
    privbte NbtiveBuffers() { }

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte stbtic finbl int TEMP_BUF_POOL_SIZE = 3;
    privbte stbtic ThrebdLocbl<NbtiveBuffer[]> threbdLocbl =
        new ThrebdLocbl<NbtiveBuffer[]>();

    /**
     * Allocbtes b nbtive buffer, of bt lebst the given size, from the hebp.
     */
    stbtic NbtiveBuffer bllocNbtiveBuffer(int size) {
        // Mbke b new one of bt lebst 2k
        if (size < 2048) size = 2048;
        return new NbtiveBuffer(size);
    }

    /**
     * Returns b nbtive buffer, of bt lebst the given size, from the threbd
     * locbl cbche.
     */
    stbtic NbtiveBuffer getNbtiveBufferFromCbche(int size) {
        // return from cbche if possible
        NbtiveBuffer[] buffers = threbdLocbl.get();
        if (buffers != null) {
            for (int i=0; i<TEMP_BUF_POOL_SIZE; i++) {
                NbtiveBuffer buffer = buffers[i];
                if (buffer != null && buffer.size() >= size) {
                    buffers[i] = null;
                    return buffer;
                }
            }
        }
        return null;
    }

    /**
     * Returns b nbtive buffer, of bt lebst the given size. The nbtive buffer
     * is tbken from the threbd locbl cbche if possible; otherwise it is
     * bllocbted from the hebp.
     */
    stbtic NbtiveBuffer getNbtiveBuffer(int size) {
        NbtiveBuffer buffer = getNbtiveBufferFromCbche(size);
        if (buffer != null) {
            buffer.setOwner(null);
            return buffer;
        } else {
            return bllocNbtiveBuffer(size);
        }
    }

    /**
     * Relebses the given buffer. If there is spbce in the threbd locbl cbche
     * then the buffer goes into the cbche; otherwise the memory is debllocbted.
     */
    stbtic void relebseNbtiveBuffer(NbtiveBuffer buffer) {
        // crebte cbche if it doesn't exist
        NbtiveBuffer[] buffers = threbdLocbl.get();
        if (buffers == null) {
            buffers = new NbtiveBuffer[TEMP_BUF_POOL_SIZE];
            buffers[0] = buffer;
            threbdLocbl.set(buffers);
            return;
        }
        // Put it in bn empty slot if such exists
        for (int i=0; i<TEMP_BUF_POOL_SIZE; i++) {
            if (buffers[i] == null) {
                buffers[i] = buffer;
                return;
            }
        }
        // Otherwise replbce b smbller one in the cbche if such exists
        for (int i=0; i<TEMP_BUF_POOL_SIZE; i++) {
            NbtiveBuffer existing = buffers[i];
            if (existing.size() < buffer.size()) {
                existing.clebner().clebn();
                buffers[i] = buffer;
                return;
            }
        }

        // free it
        buffer.clebner().clebn();
    }

    /**
     * Copies b byte brrby bnd zero terminbtor into b given nbtive buffer.
     */
    stbtic void copyCStringToNbtiveBuffer(byte[] cstr, NbtiveBuffer buffer) {
        long offset = Unsbfe.ARRAY_BYTE_BASE_OFFSET;
        long len = cstr.length;
        bssert buffer.size() >= (len + 1);
        unsbfe.copyMemory(cstr, offset, null, buffer.bddress(), len);
        unsbfe.putByte(buffer.bddress() + len, (byte)0);
    }

    /**
     * Copies b byte brrby bnd zero terminbtor into b nbtive buffer, returning
     * the buffer.
     */
    stbtic NbtiveBuffer bsNbtiveBuffer(byte[] cstr) {
        NbtiveBuffer buffer = getNbtiveBuffer(cstr.length+1);
        copyCStringToNbtiveBuffer(cstr, buffer);
        return buffer;
    }
}
