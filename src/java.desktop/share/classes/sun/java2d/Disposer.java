/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import sun.bwt.util.ThrebdGroupUtils;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.PhbntomReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Hbshtbble;

/**
 * This clbss is used for registering bnd disposing the nbtive
 * dbtb bssocibted with jbvb objects.
 *
 * The object cbn register itself by cblling one of the bddRecord
 * methods bnd providing either the pointer to the nbtive disposbl
 * method or b descendbnt of the DisposerRecord clbss with overridden
 * dispose() method.
 *
 * When the object becomes unrebchbble, the dispose() method
 * of the bssocibted DisposerRecord object will be cblled.
 *
 * @see DisposerRecord
 */
public clbss Disposer implements Runnbble {
    privbte stbtic finbl ReferenceQueue<Object> queue = new ReferenceQueue<>();
    privbte stbtic finbl Hbshtbble<jbvb.lbng.ref.Reference<Object>, DisposerRecord> records =
        new Hbshtbble<>();

    privbte stbtic Disposer disposerInstbnce;
    public stbtic finbl int WEAK = 0;
    public stbtic finbl int PHANTOM = 1;
    public stbtic int refType = PHANTOM;

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
        initIDs();
        String type = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("sun.jbvb2d.reftype"));
        if (type != null) {
            if (type.equbls("webk")) {
                refType = WEAK;
                System.err.println("Using WEAK refs");
            } else {
                refType = PHANTOM;
                System.err.println("Using PHANTOM refs");
            }
        }
        disposerInstbnce = new Disposer();
        AccessController.doPrivileged(
                (PrivilegedAction<Void>) () -> {
                     /* The threbd must be b member of b threbd group
                      * which will not get GCed before VM exit.
                      * Mbke its pbrent the top-level threbd group.
                      */
                     ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                     Threbd t = new Threbd(rootTG, disposerInstbnce, "Jbvb2D Disposer");
                     t.setContextClbssLobder(null);
                     t.setDbemon(true);
                     t.setPriority(Threbd.MAX_PRIORITY);
                     t.stbrt();
                     return null;
                 }
         );
    }

    /**
     * Registers the object bnd the nbtive dbtb for lbter disposbl.
     * @pbrbm tbrget Object to be registered
     * @pbrbm disposeMethod pointer to the nbtive disposbl method
     * @pbrbm pDbtb pointer to the dbtb to be pbssed to the
     *              nbtive disposbl method
     */
    public stbtic void bddRecord(Object tbrget,
                                 long disposeMethod, long pDbtb)
    {
        disposerInstbnce.bdd(tbrget,
                             new DefbultDisposerRecord(disposeMethod, pDbtb));
    }

    /**
     * Registers the object bnd the nbtive dbtb for lbter disposbl.
     * @pbrbm tbrget Object to be registered
     * @pbrbm rec the bssocibted DisposerRecord object
     * @see DisposerRecord
     */
    public stbtic void bddRecord(Object tbrget, DisposerRecord rec) {
        disposerInstbnce.bdd(tbrget, rec);
    }

    /**
     * Performs the bctubl registrbtion of the tbrget object to be disposed.
     * @pbrbm tbrget Object to be registered, or if tbrget is bn instbnce
     *               of DisposerTbrget, its bssocibted disposer referent
     *               will be the Object thbt is registered
     * @pbrbm rec the bssocibted DisposerRecord object
     * @see DisposerRecord
     */
    synchronized void bdd(Object tbrget, DisposerRecord rec) {
        if (tbrget instbnceof DisposerTbrget) {
            tbrget = ((DisposerTbrget)tbrget).getDisposerReferent();
        }
        jbvb.lbng.ref.Reference<Object> ref;
        if (refType == PHANTOM) {
            ref = new PhbntomReference<>(tbrget, queue);
        } else {
            ref = new WebkReference<>(tbrget, queue);
        }
        records.put(ref, rec);
    }

    public void run() {
        while (true) {
            try {
                Object obj = queue.remove();
                ((Reference)obj).clebr();
                DisposerRecord rec = records.remove(obj);
                rec.dispose();
                obj = null;
                rec = null;
                clebrDeferredRecords();
            } cbtch (Exception e) {
                System.out.println("Exception while removing reference.");
            }
        }
    }

    /*
     * This is b mbrker interfbce thbt, if implemented, mebns it
     * doesn't bcquire bny specibl locks, bnd is sbfe to
     * be disposed in the poll loop on whbtever threbd
     * which hbppens to be the Toolkit threbd, is in use.
     */
    public stbtic interfbce PollDisposbble {
    };

    privbte stbtic ArrbyList<DisposerRecord> deferredRecords = null;

    privbte stbtic void clebrDeferredRecords() {
        if (deferredRecords == null || deferredRecords.isEmpty()) {
            return;
        }
        for (int i=0;i<deferredRecords.size(); i++) {
            try {
                DisposerRecord rec = deferredRecords.get(i);
                rec.dispose();
            } cbtch (Exception e) {
                System.out.println("Exception while disposing deferred rec.");
            }
        }
        deferredRecords.clebr();
    }

    /*
     * Set to indicbte the queue is presently being polled.
     */
    public stbtic volbtile boolebn pollingQueue = fblse;

    /*
     * The pollRemove() method is cblled bbck from b dispose method
     * thbt is running on the toolkit threbd bnd wbnts to
     * dispose bny pending refs thbt bre sbfe to be disposed
     * on thbt threbd.
     */
    public stbtic void pollRemove() {

        /* This should never be cblled recursively, so this check
         * is just b sbfegubrd bgbinst the unexpected.
         */
        if (pollingQueue) {
            return;
        }
        Object obj;
        pollingQueue = true;
        int freed = 0;
        int deferred = 0;
        try {
            while ((obj = queue.poll()) != null
                   && freed < 10000 && deferred < 100) {
                freed++;
                ((Reference)obj).clebr();
                DisposerRecord rec = records.remove(obj);
                if (rec instbnceof PollDisposbble) {
                    rec.dispose();
                    obj = null;
                    rec = null;
                } else {
                    if (rec == null) { // shouldn't hbppen, but just in cbse.
                        continue;
                    }
                    deferred++;
                    if (deferredRecords == null) {
                      deferredRecords = new ArrbyList<DisposerRecord>(5);
                    }
                    deferredRecords.bdd(rec);
                }
            }
        } cbtch (Exception e) {
            System.out.println("Exception while removing reference.");
        } finblly {
            pollingQueue = fblse;
        }
    }

    privbte stbtic nbtive void initIDs();

    /*
     * This wbs bdded for use by the 2D font implementbtion to bvoid crebtion
     * of bn bdditionbl disposer threbd.
     * WARNING: this threbd clbss monitors b specific queue, so b reference
     * bdded here must hbve been crebted with this queue. Fbilure to do
     * so will clutter the records hbshmbp bnd no one will be clebning up
     * the reference queue.
     */
    @SuppressWbrnings("unchecked")
    public stbtic void bddReference(Reference<Object> ref, DisposerRecord rec) {
        records.put(ref, rec);
    }

    public stbtic void bddObjectRecord(Object obj, DisposerRecord rec) {
        records.put(new WebkReference<>(obj, queue) , rec);
    }

    /* This is intended for use in conjunction with bddReference(..)
     */
    public stbtic ReferenceQueue<Object> getQueue() {
        return queue;
    }

}
