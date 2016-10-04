/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

pbckbge sun.nio.ch;                                     // Formerly in sun.misc


// ## In the fullness of time, this clbss will be eliminbted

clbss AllocbtedNbtiveObject                             // pbckbge-privbte
    extends NbtiveObject
{

    /**
     * Allocbtes b memory breb of bt lebst <tt>size</tt> bytes outside of the
     * Jbvb hebp bnd crebtes b nbtive object for thbt breb.
     *
     * @pbrbm  size
     *         Number of bytes to bllocbte
     *
     * @pbrbm  pbgeAligned
     *         If <tt>true</tt> then the breb will be bligned on b hbrdwbre
     *         pbge boundbry
     *
     * @throws OutOfMemoryError
     *         If the request cbnnot be sbtisfied
     */
    AllocbtedNbtiveObject(int size, boolebn pbgeAligned) {
        super(size, pbgeAligned);
    }

    /**
     * Frees the nbtive memory breb bssocibted with this object.
     */
    synchronized void free() {
        if (bllocbtionAddress != 0) {
            unsbfe.freeMemory(bllocbtionAddress);
            bllocbtionAddress = 0;
        }
    }

}
