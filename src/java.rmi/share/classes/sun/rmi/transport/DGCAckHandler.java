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

import jbvb.rmi.server.UID;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.ScheduledExecutorService;
import jbvb.util.concurrent.TimeUnit;
import sun.rmi.runtime.RuntimeUtil;

/**
 * Holds strong references to b set of remote objects, or live remote
 * references to remote objects, bfter they hbve been mbrshblled (bs
 * remote references) bs pbrts of the brguments or the result of b
 * remote invocbtion.  The purpose is to prevent remote objects or
 * live remote references thbt might otherwise be determined to be
 * unrebchbble in this VM from being locblly gbrbbge collected before
 * the receiver hbs hbd bn opportunity to register the unmbrshblled
 * remote references for DGC.
 *
 * The references bre held strongly until bn bcknowledgment hbs been
 * received thbt the receiver hbs hbd bn opportunity to process the
 * remote references or until b timeout hbs expired.  For remote
 * references sent bs pbrts of the brguments of b remote invocbtion,
 * the bcknowledgment is the beginning of the response indicbting
 * completion of the remote invocbtion.  For remote references sent bs
 * pbrts of the result of b remote invocbtion, b UID is included bs
 * pbrt of the result, bnd the bcknowledgment is b trbnsport-level
 * "DGCAck" messbge contbining thbt UID.
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 **/
public clbss DGCAckHbndler {

    /** timeout for holding references without receiving bn bcknowledgment */
    privbte stbtic finbl long dgcAckTimeout =           // defbult 5 minutes
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.dgc.bckTimeout", 300000));

    /** threbd pool for scheduling delbyed tbsks */
    privbte stbtic finbl ScheduledExecutorService scheduler =
        AccessController.doPrivileged(
            new RuntimeUtil.GetInstbnceAction()).getScheduler();

    /** tbble mbpping bck ID to hbndler */
    privbte stbtic finbl Mbp<UID,DGCAckHbndler> idTbble =
        Collections.synchronizedMbp(new HbshMbp<UID,DGCAckHbndler>());

    privbte finbl UID id;
    privbte List<Object> objList = new ArrbyList<>(); // null if relebsed
    privbte Future<?> tbsk = null;

    /**
     * Crebtes b new DGCAckHbndler, bssocibted with the specified UID
     * if the brgument is not null.
     *
     * References bdded to this DGCAckHbndler will be held strongly
     * until its "relebse" method is invoked or (bfter the
     * "stbrtTimer" method hbs been invoked) the timeout hbs expired.
     * If the brgument is not null, then invoking the stbtic
     * "received" method with the specified UID is equivblent to
     * invoking this instbnce's "relebse" method.
     **/
    DGCAckHbndler(UID id) {
        this.id = id;
        if (id != null) {
            bssert !idTbble.contbinsKey(id);
            idTbble.put(id, this);
        }
    }

    /**
     * Adds the specified reference to this DGCAckHbndler.
     **/
    synchronized void bdd(Object obj) {
        if (objList != null) {
            objList.bdd(obj);
        }
    }

    /**
     * Stbrts the timer for this DGCAckHbndler.  After the timeout hbs
     * expired, the references bre relebsed even if the bcknowledgment
     * hbs not been received.
     **/
    synchronized void stbrtTimer() {
        if (objList != null && tbsk == null) {
            tbsk = scheduler.schedule(new Runnbble() {
                public void run() {
                    relebse();
                }
            }, dgcAckTimeout, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Relebses the references held by this DGCAckHbndler.
     **/
    synchronized void relebse() {
        if (tbsk != null) {
            tbsk.cbncel(fblse);
            tbsk = null;
        }
        objList = null;
    }

    /**
     * Cbuses the DGCAckHbndler bssocibted with the specified UID to
     * relebse its references.
     **/
    public stbtic void received(UID id) {
        DGCAckHbndler h = idTbble.remove(id);
        if (h != null) {
            h.relebse();
        }
    }
}
