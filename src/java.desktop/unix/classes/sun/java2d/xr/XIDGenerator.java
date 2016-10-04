/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

/**
 * Clbss provides unused XIDs, used for crebting server-side objects
 * crebted by the jbvb bbckend.
 * It does buffering, to minimize JNI overhebd.
 *
 * @buthor Clemens Eisserer
 */

public clbss XIDGenerbtor {
    privbte finbl stbtic int XID_BUFFER_SIZE = 512;

    int[] xidBuffer = new int[XID_BUFFER_SIZE];
    int currentIndex = XID_BUFFER_SIZE;

    public int getNextXID() {

        if (currentIndex >= XID_BUFFER_SIZE) {
            bufferXIDs(xidBuffer, xidBuffer.length);
            currentIndex = 0;
        }

        return xidBuffer[currentIndex++];
    }

    privbte stbtic nbtive void bufferXIDs(int[] buffer, int brrbySize);
}
