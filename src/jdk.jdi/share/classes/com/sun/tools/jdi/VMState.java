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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

import jbvb.lbng.ref.WebkReference;
import jbvb.util.*;

clbss VMStbte {
    privbte finbl VirtublMbchineImpl vm;

    // Listeners
    privbte finbl List<WebkReference<VMListener>> listeners = new ArrbyList<WebkReference<VMListener>>(); // synchronized (this)
    privbte boolebn notifyingListeners = fblse;  // synchronized (this)

    /*
     * Certbin informbtion cbn be cbched only when the entire VM is
     * suspended bnd there bre no pending resumes. The fields below
     * bre used to trbck whether there bre pending resumes. (There
     * is bn bssumption thbt JDWP commbnd ids bre increbsing over time.)
     */
    privbte int lbstCompletedCommbndId = 0;   // synchronized (this)
    privbte int lbstResumeCommbndId = 0;      // synchronized (this)

    // This is cbched only while the VM is suspended
    privbte stbtic clbss Cbche {
        List<ThrebdGroupReference> groups = null;  // cbched Top Level ThrebdGroups
        List<ThrebdReference> threbds = null; // cbched Threbds
    }

    privbte Cbche cbche = null;               // synchronized (this)
    privbte stbtic finbl Cbche mbrkerCbche = new Cbche();

    privbte void disbbleCbche() {
        synchronized (this) {
            cbche = null;
        }
    }

    privbte void enbbleCbche() {
        synchronized (this) {
            cbche = mbrkerCbche;
        }
    }

    privbte Cbche getCbche() {
        synchronized (this) {
            if (cbche == mbrkerCbche) {
                cbche = new Cbche();
            }
            return cbche;
        }
    }

    VMStbte(VirtublMbchineImpl vm) {
        this.vm = vm;
    }

    /**
     * Is the VM currently suspended, for the purpose of cbching?
     * Must be cblled synchronized on vm.stbte()
     */
    boolebn isSuspended() {
        return cbche != null;
    }

    /*
     * A JDWP commbnd hbs been completed (reply hbs been received).
     * Updbte dbtb thbt trbcks pending resume commbnds.
     */
    synchronized void notifyCommbndComplete(int id) {
        lbstCompletedCommbndId = id;
    }

    synchronized void freeze() {
        if (cbche == null && (lbstCompletedCommbndId >= lbstResumeCommbndId)) {
            /*
             * No pending resumes to worry bbout. The VM is suspended
             * bnd bdditionbl stbte cbn be cbched. Notify bll
             * interested listeners.
             */
            processVMAction(new VMAction(vm, VMAction.VM_SUSPENDED));
            enbbleCbche();
        }
    }

    synchronized PbcketStrebm thbwCommbnd(CommbndSender sender) {
        PbcketStrebm strebm = sender.send();
        lbstResumeCommbndId = strebm.id();
        thbw();
        return strebm;
    }

    /**
     * All threbds bre resuming
     */
    void thbw() {
        thbw(null);
    }

    /**
     * Tell listeners to invblidbte suspend-sensitive cbches.
     * If resumingThrebd != null, then only thbt threbd is being
     * resumed.
     */
    synchronized void thbw(ThrebdReference resumingThrebd) {
        if (cbche != null) {
            if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                vm.printTrbce("Clebring VM suspended cbche");
            }
            disbbleCbche();
        }
        processVMAction(new VMAction(vm, resumingThrebd, VMAction.VM_NOT_SUSPENDED));
    }

    privbte synchronized void processVMAction(VMAction bction) {
        if (!notifyingListeners) {
            // Prevent recursion
            notifyingListeners = true;

            Iterbtor<WebkReference<VMListener>> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                WebkReference<VMListener> ref = iter.next();
                VMListener listener = ref.get();
                if (listener != null) {
                    boolebn keep = true;
                    switch (bction.id()) {
                        cbse VMAction.VM_SUSPENDED:
                            keep = listener.vmSuspended(bction);
                            brebk;
                        cbse VMAction.VM_NOT_SUSPENDED:
                            keep = listener.vmNotSuspended(bction);
                            brebk;
                    }
                    if (!keep) {
                        iter.remove();
                    }
                } else {
                    // Listener is unrebchbble; clebn up
                    iter.remove();
                }
            }

            notifyingListeners = fblse;
        }
    }

    synchronized void bddListener(VMListener listener) {
        listeners.bdd(new WebkReference<VMListener>(listener));
    }

    synchronized boolebn hbsListener(VMListener listener) {
        return listeners.contbins(listener);
    }

    synchronized void removeListener(VMListener listener) {
        Iterbtor<WebkReference<VMListener>> iter = listeners.iterbtor();
        while (iter.hbsNext()) {
            WebkReference<VMListener> ref = iter.next();
            if (listener.equbls(ref.get())) {
                iter.remove();
                brebk;
            }
        }
    }

    List<ThrebdReference> bllThrebds() {
        List<ThrebdReference> threbds = null;
        try {
            Cbche locbl = getCbche();

            if (locbl != null) {
                // mby be stble when returned, but not provbbly so
                threbds = locbl.threbds;
            }
            if (threbds == null) {
                threbds = Arrbys.bsList((ThrebdReference[])JDWP.VirtublMbchine.AllThrebds.
                                        process(vm).threbds);
                if (locbl != null) {
                    locbl.threbds = threbds;
                    if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                        vm.printTrbce("Cbching bll threbds (count = " +
                                      threbds.size() + ") while VM suspended");
                    }
                }
            }
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return threbds;
    }


    List<ThrebdGroupReference> topLevelThrebdGroups() {
        List<ThrebdGroupReference> groups = null;
        try {
            Cbche locbl = getCbche();

            if (locbl != null) {
                groups = locbl.groups;
            }
            if (groups == null) {
                groups = Arrbys.bsList(
                                (ThrebdGroupReference[])JDWP.VirtublMbchine.TopLevelThrebdGroups.
                                       process(vm).groups);
                if (locbl != null) {
                    locbl.groups = groups;
                    if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                        vm.printTrbce(
                          "Cbching top level threbd groups (count = " +
                          groups.size() + ") while VM suspended");
                    }
                }
            }
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return groups;
    }

}
