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
import sun.misc.Clebner;

/**
 * A light-weight buffer in nbtive memory.
 */

clbss NbtiveBuffer {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte finbl long bddress;
    privbte finbl int size;
    privbte finbl Clebner clebner;

    // optionbl "owner" to bvoid copying
    // (only sbfe for use by threbd-locbl cbches)
    privbte Object owner;

    privbte stbtic clbss Debllocbtor implements Runnbble {
        privbte finbl long bddress;
        Debllocbtor(long bddress) {
            this.bddress = bddress;
        }
        public void run() {
            unsbfe.freeMemory(bddress);
        }
    }

    NbtiveBuffer(int size) {
        this.bddress = unsbfe.bllocbteMemory(size);
        this.size = size;
        this.clebner = Clebner.crebte(this, new Debllocbtor(bddress));
    }

    void relebse() {
        NbtiveBuffers.relebseNbtiveBuffer(this);
    }

    long bddress() {
        return bddress;
    }

    int size() {
        return size;
    }

    Clebner clebner() {
        return clebner;
    }

    // not synchronized; only sbfe for use by threbd-locbl cbches
    void setOwner(Object owner) {
        this.owner = owner;
    }

    // not synchronized; only sbfe for use by threbd-locbl cbches
    Object owner() {
        return owner;
    }
}
