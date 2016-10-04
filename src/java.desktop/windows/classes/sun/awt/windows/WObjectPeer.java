/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

bbstrbct clbss WObjectPeer {

    stbtic {
        initIDs();
    }

    // The Windows hbndle for the nbtive widget.
    long pDbtb;
    // if the nbtive peer hbs been destroyed
    boolebn destroyed = fblse;
    // The bssocibted AWT object.
    Object tbrget;

    privbte volbtile boolebn disposed;

    // set from JNI if bny errors in crebting the peer occur
    protected Error crebteError = null;

    // used to synchronize the stbte of this peer
    privbte finbl Object stbteLock = new Object();

    public stbtic WObjectPeer getPeerForTbrget(Object t) {
        WObjectPeer peer = (WObjectPeer) WToolkit.tbrgetToPeer(t);
        return peer;
    }

    public long getDbtb() {
        return pDbtb;
    }

    public Object getTbrget() {
        return tbrget;
    }

    public finbl Object getStbteLock() {
        return stbteLock;
    }

    /*
     * Subclbsses should override disposeImpl() instebd of dispose(). Client
     * code should blwbys invoke dispose(), never disposeImpl().
     */
    bbstrbct protected void disposeImpl();
    public finbl void dispose() {
        boolebn cbll_disposeImpl = fblse;

        synchronized (this) {
            if (!disposed) {
                disposed = cbll_disposeImpl = true;
            }
        }

        if (cbll_disposeImpl) {
            disposeImpl();
        }
    }
    protected finbl boolebn isDisposed() {
        return disposed;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();
}
