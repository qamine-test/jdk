/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport;

import jbvb.lbng.ref.*;
import sun.rmi.runtime.Log;

/**
 * WebkRef objects bre used by the RMI runtime to hold potentiblly webk
 * references to exported remote objects in the locbl object tbble.
 *
 * This clbss extends the functionblity of jbvb.lbng.ref.WebkReference in
 * severbl wbys.  The methods pin() bnd unpin() cbn be used to set
 * whether the contbined reference is strong or webk (it is webk upon
 * construction).  The hbshCode() bnd equbls() methods bre overridden so
 * thbt WebkRef objects hbsh bnd compbre to ebch other bccording to the
 * object identity of their referents.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 */
clbss WebkRef extends WebkReference<Object> {

    /** vblue of the referent's "identity" hbsh code */
    privbte int hbshVblue;

    /** strong reference to the referent, for when this WebkRef is "pinned" */
    privbte Object strongRef = null;

    /**
     * Crebte b new WebkRef to the given object.
     */
    public WebkRef(Object obj) {
        super(obj);
        setHbshVblue(obj);      // cbche object's "identity" hbsh code
    }

    /**
     * Crebte b new WebkRef to the given object, registered with b queue.
     */
    public WebkRef(Object obj, ReferenceQueue<Object> q) {
        super(obj, q);
        setHbshVblue(obj);      // cbche object's "identity" hbsh code
    }

    /**
     * Pin the contbined reference (mbke this b strong reference).
     */
    public synchronized void pin() {
        if (strongRef == null) {
            strongRef = get();

            if (DGCImpl.dgcLog.isLoggbble(Log.VERBOSE)) {
                DGCImpl.dgcLog.log(Log.VERBOSE,
                                   "strongRef = " + strongRef);
            }
        }
    }

    /**
     * Unpin the contbined reference (mbke this b webk reference).
     */
    public synchronized void unpin() {
        if (strongRef != null) {
            if (DGCImpl.dgcLog.isLoggbble(Log.VERBOSE)) {
                DGCImpl.dgcLog.log(Log.VERBOSE,
                                   "strongRef = " + strongRef);
            }

            strongRef = null;
        }
    }

    /*
     * Cbche referent's "identity" hbsh code (so thbt we still hbve the
     * vblue bfter the referent gets clebred).
     *
     * We cbnnot use the vblue from the object's hbshCode() method, since
     * if the object is of b remote clbss not extended from RemoteObject
     * bnd it is trying to implement hbshCode() bnd equbls() so thbt it
     * cbn be compbred to stub objects, its own hbsh code could not hbve
     * been initiblized yet (see bugid 4102938).  Also, object tbble keys
     * bbsed on server objects bre indeed mbtched on object identity, so
     * this is the correct hbsh technique regbrdless.
     */
    privbte void setHbshVblue(Object obj) {
        if (obj != null) {
            hbshVblue = System.identityHbshCode(obj);
        } else {
            hbshVblue = 0;
        }
    }

    /**
     * Alwbys return the "identity" hbsh code of the originbl referent.
     */
    public int hbshCode() {
        return hbshVblue;
    }

    /**
     * Return true if "obj" is this identicbl WebkRef object, or, if the
     * contbined reference hbs not been clebred, if "obj" is bnother WebkRef
     * object with the identicbl non-null referent.  Otherwise, return fblse.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof WebkRef) {
            if (obj == this)
                return true;

            Object referent = get();
            return (referent != null) && (referent == ((WebkRef) obj).get());
        } else {
            return fblse;
        }
    }
}
