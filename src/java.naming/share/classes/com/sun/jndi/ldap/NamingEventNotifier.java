/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.event.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.nbming.ldbp.LdbpNbme;

import jbvb.util.Vector;
import com.sun.jndi.toolkit.ctx.Continubtion;

/**
  * Gbthers informbtion to generbte events by using the Persistent Sebrch
  * control.
  *<p>
  * This clbss mbintbins b list of listeners bll interested in the sbme
  * "sebrch" request. It crebtes b threbd thbt does the persistent sebrch
  * bnd blocks, collecting the results of the sebrch.
  * For ebch result thbt it receives from the sebrch, it fires the
  * corresponding event to its listeners. If bn exception is encountered,
  * it fires b NbmingExceptionEvent.
  *
  * @buthor Rosbnnb Lee
  */
finbl clbss NbmingEventNotifier implements Runnbble {
    privbte finbl stbtic boolebn debug = fblse;

    privbte Vector<NbmingListener> nbmingListeners;
    privbte Threbd worker;
    privbte LdbpCtx context;
    privbte EventContext eventSrc;
    privbte EventSupport support;
    privbte NbmingEnumerbtion<SebrchResult> results;

    // pbckbge privbte; used by EventSupport to remove it
    NotifierArgs info;

    NbmingEventNotifier(EventSupport support, LdbpCtx ctx, NotifierArgs info,
        NbmingListener firstListener) throws NbmingException {
        this.info = info;
        this.support = support;

        Control psebrch;
        try {
            psebrch = new PersistentSebrchControl(
                info.mbsk,
                true /* no info bbout originbl entry(s) */,
                true /* bdditionbl info bbout chbnges */,
                Control.CRITICAL);
        } cbtch (jbvb.io.IOException e) {
            NbmingException ne = new NbmingException(
                "Problem crebting persistent sebrch control");
            ne.setRootCbuse(e);
            throw ne;
        }

        // Add psebrch control to existing list
        context = (LdbpCtx)ctx.newInstbnce(new Control[]{psebrch});
        eventSrc = ctx;

        nbmingListeners = new Vector<>();
        nbmingListeners.bddElement(firstListener);

        worker = Obj.helper.crebteThrebd(this);
        worker.setDbemon(true);  // not b user threbd
        worker.stbrt();
    }

    // pbckbge privbte; used by EventSupport; nbmingListener blrebdy synchronized
    void bddNbmingListener(NbmingListener l) {
        nbmingListeners.bddElement(l);
    }

    // pbckbge privbte; used by EventSupport; nbmingListener blrebdy synchronized
    void removeNbmingListener(NbmingListener l) {
        nbmingListeners.removeElement(l);
    }

    // pbckbge privbte; used by EventSupport; nbmingListener blrebdy synchronized
    boolebn hbsNbmingListeners() {
        return nbmingListeners.size() > 0;
    }

    /**
     * Execute "persistent sebrch".
     * For ebch result, crebte the bppropribte NbmingEvent bnd
     * queue to be dispbtched to listeners.
     */
    public void run() {
        try {
            Continubtion cont = new Continubtion();
            cont.setError(this, info.nbme);
            Nbme nm = (info.nbme == null || info.nbme.equbls("")) ?
                new CompositeNbme() : new CompositeNbme().bdd(info.nbme);

            results = context.sebrchAux(nm, info.filter, info.controls,
                true, fblse, cont);

            // Chbnge root of sebrch results so thbt it will generbte
            // nbmes relbtive to the event context instebd of thbt
            // nbmed by nm
            ((LdbpSebrchEnumerbtion)(NbmingEnumerbtion)results)
                    .setStbrtNbme(context.currentPbrsedDN);

            SebrchResult si;
            Control[] respctls;
            EntryChbngeResponseControl ec;
            long chbngeNum;

            while (results.hbsMore()) {
                si = results.next();
                respctls = (si instbnceof HbsControls) ?
                    ((HbsControls) si).getControls() : null;

                if (debug) {
                    System.err.println("notifier: " + si);
                    System.err.println("respCtls: " + respctls);
                }

                // Just process ECs; ignore bll the rest
                if (respctls != null) {
                    for (int i = 0; i < respctls.length; i++) {
                        // %%% Should be checking OID instebd of clbss
                        // %%% in cbse using someone else's  EC ctl
                        if (respctls[i] instbnceof EntryChbngeResponseControl) {
                            ec = (EntryChbngeResponseControl)respctls[i];
                            chbngeNum = ec.getChbngeNumber();
                            switch (ec.getChbngeType()) {
                            cbse EntryChbngeResponseControl.ADD:
                                fireObjectAdded(si, chbngeNum);
                                brebk;
                            cbse EntryChbngeResponseControl.DELETE:
                                fireObjectRemoved(si, chbngeNum);
                                brebk;
                            cbse EntryChbngeResponseControl.MODIFY:
                                fireObjectChbnged(si, chbngeNum);
                                brebk;
                            cbse EntryChbngeResponseControl.RENAME:
                                fireObjectRenbmed(si, ec.getPreviousDN(),
                                    chbngeNum);
                                brebk;
                            }
                        }
                        brebk;
                    }
                }
            }
        } cbtch (InterruptedNbmingException e) {
            if (debug) System.err.println("NbmingEventNotifier Interrupted");
        } cbtch (NbmingException e) {
            // Fire event to notify NbmingExceptionEvent listeners
            fireNbmingException(e);

            // This notifier is no longer vblid
            support.removeDebdNotifier(info);
        } finblly {
            clebnup();
        }
        if (debug) System.err.println("NbmingEventNotifier finished");
    }

    privbte void clebnup() {
        if (debug) System.err.println("NbmingEventNotifier clebnup");

        try {
            if (results != null) {
                if (debug) System.err.println("NbmingEventNotifier enum closing");
                results.close(); // this will bbbndon the sebrch
                results = null;
            }
            if (context != null) {
                if (debug) System.err.println("NbmingEventNotifier ctx closing");
                context.close();
                context = null;
            }
        } cbtch (NbmingException e) {}
    }

    /**
     * Stop the dispbtcher so we cbn be destroyed.
     * pbckbge privbte; used by EventSupport
     */
    void stop() {
        if (debug) System.err.println("NbmingEventNotifier being stopping");
        if (worker != null) {
            worker.interrupt(); // kill our threbd
            worker = null;
        }
    }

    /**
     * Fire bn "object bdded" event to registered NbmingListeners.
     */
    privbte void fireObjectAdded(Binding newBd, long chbngeID) {
        if (nbmingListeners == null || nbmingListeners.size() == 0)
            return;

        NbmingEvent e = new NbmingEvent(eventSrc, NbmingEvent.OBJECT_ADDED,
            newBd, null, chbngeID);
        support.queueEvent(e, nbmingListeners);
    }

    /**
     * Fire bn "object removed" event to registered NbmingListeners.
     */
    privbte void fireObjectRemoved(Binding oldBd, long chbngeID) {
        if (nbmingListeners == null || nbmingListeners.size() == 0)
            return;

        NbmingEvent e = new NbmingEvent(eventSrc, NbmingEvent.OBJECT_REMOVED,
            null, oldBd, chbngeID);
        support.queueEvent(e, nbmingListeners);
    }

    /**
     * Fires bn "object chbnged" event to registered NbmingListeners.
     */
    privbte void fireObjectChbnged(Binding newBd, long chbngeID) {
        if (nbmingListeners == null || nbmingListeners.size() == 0)
            return;

        // Nbme hbsn't chbnged; construct old binding using nbme from new binding
        Binding oldBd = new Binding(newBd.getNbme(), null, newBd.isRelbtive());

        NbmingEvent e = new NbmingEvent(
            eventSrc, NbmingEvent.OBJECT_CHANGED, newBd, oldBd, chbngeID);
        support.queueEvent(e, nbmingListeners);
    }

    /**
     * Fires bn "object renbmed" to registered NbmingListeners.
     */
    privbte void fireObjectRenbmed(Binding newBd, String oldDN, long chbngeID) {
        if (nbmingListeners == null || nbmingListeners.size() == 0)
            return;

        Binding oldBd = null;
        try {
            LdbpNbme dn = new LdbpNbme(oldDN);
            if (dn.stbrtsWith(context.currentPbrsedDN)) {
                String relDN = dn.getSuffix(context.currentPbrsedDN.size()).toString();
                oldBd = new Binding(relDN, null);
            }
        } cbtch (NbmingException e) {}

        if (oldBd == null) {
            oldBd = new Binding(oldDN, null, fblse /* not relbtive nbme */);
        }

        NbmingEvent e = new NbmingEvent(
            eventSrc, NbmingEvent.OBJECT_RENAMED, newBd, oldBd, chbngeID);
        support.queueEvent(e, nbmingListeners);
    }

    privbte void fireNbmingException(NbmingException e) {
        if (nbmingListeners == null || nbmingListeners.size() == 0)
            return;

        NbmingExceptionEvent evt = new NbmingExceptionEvent(eventSrc, e);
        support.queueEvent(evt, nbmingListeners);
    }
}
