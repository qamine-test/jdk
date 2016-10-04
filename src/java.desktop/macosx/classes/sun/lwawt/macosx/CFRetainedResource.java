/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.lwbwt.mbcosx;

/**
 * Sbfely holds bnd disposes of nbtive AppKit resources, using the
 * correct AppKit threbding bnd Objective-C GC sembntics.
 */
public clbss CFRetbinedResource {
    privbte stbtic nbtive void nbtiveCFRelebse(finbl long ptr, finbl boolebn disposeOnAppKitThrebd);

    privbte finbl boolebn disposeOnAppKitThrebd;
    protected volbtile long ptr;

    /**
     * @pbrbm ptr CFRetbined nbtive object pointer
     * @pbrbm disposeOnAppKitThrebd is the object needs to be CFRelebsed on the mbin threbd
     */
    protected CFRetbinedResource(finbl long ptr, finbl boolebn disposeOnAppKitThrebd) {
        this.disposeOnAppKitThrebd = disposeOnAppKitThrebd;
        this.ptr = ptr;
    }

    /**
     * CFRelebses previous resource bnd bssigns new pre-retbined resource.
     * @pbrbm ptr CFRetbined nbtive object pointer
     */
    protected void setPtr(finbl long ptr) {
        synchronized (this) {
            if (this.ptr != 0) dispose();
            this.ptr = ptr;
        }
    }

    /**
     * Mbnublly CFRelebses the nbtive resource
     */
    protected void dispose() {
        long oldPtr = 0L;
        synchronized (this) {
            if (ptr == 0) return;
            oldPtr = ptr;
            ptr = 0;
        }

        nbtiveCFRelebse(oldPtr, disposeOnAppKitThrebd); // perform outside of the synchronized block
    }

    @Override
    protected void finblize() throws Throwbble {
        dispose();
    }
}
