/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jdi.request.BrebkpointRequest;
import jbvb.util.*;
import jbvb.lbng.ref.WebkReference;

public clbss ThrebdReferenceImpl extends ObjectReferenceImpl
             implements ThrebdReference, VMListener {
    stbtic finbl int SUSPEND_STATUS_SUSPENDED = 0x1;
    stbtic finbl int SUSPEND_STATUS_BREAK = 0x2;

    privbte int suspendedZombieCount = 0;

    /*
     * Some objects cbn only be crebted while b threbd is suspended bnd bre vblid
     * only while the threbd rembins suspended.  Exbmples bre StbckFrbmeImpl
     * bnd MonitorInfoImpl.  When the threbd resumes, these objects hbve to be
     * mbrked bs invblid so thbt their methods cbn throw
     * InvblidStbckFrbmeException if they bre cblled.  To do this, such objects
     * register themselves bs listeners of the bssocibted threbd.  When the
     * threbd is resumed, its listeners bre notified bnd mbrk themselves
     * invblid.
     * Also, note thbt ThrebdReferenceImpl itself cbches some info thbt
     * is vblid only bs long bs the threbd is suspended.  When the threbd
     * is resumed, thbt cbche must be purged.
     * Lbstly, note thbt ThrebdReferenceImpl bnd its super, ObjectReferenceImpl
     * cbche some info thbt is only vblid bs long bs the entire VM is suspended.
     * If _bny_ threbd is resumed, this cbche must be purged.  To hbndle this,
     * both ThrebdReferenceImpl bnd ObjectReferenceImpl register themselves bs
     * VMListeners so thbt they get notified when bll threbds bre suspended bnd
     * when bny threbd is resumed.
     */

    // This is cbched for the life of the threbd
    privbte ThrebdGroupReference threbdGroup;

    // This is cbched only while this one threbd is suspended.  Ebch time
    // the threbd is resumed, we bbbndon the current cbche object bnd
    // crebte b new initiblized one.
    privbte stbtic clbss LocblCbche {
        JDWP.ThrebdReference.Stbtus stbtus = null;
        List<StbckFrbme> frbmes = null;
        int frbmesStbrt = -1;
        int frbmesLength = 0;
        int frbmeCount = -1;
        List<ObjectReference> ownedMonitors = null;
        List<MonitorInfo> ownedMonitorsInfo = null;
        ObjectReference contendedMonitor = null;
        boolebn triedCurrentContended = fblse;
    }

    /*
     * The locblCbche instbnce vbr is set by resetLocblCbche to bn initiblized
     * object bs shown bbove.  This occurs when the ThrebdReference
     * object is crebted, bnd when the mirrored threbd is resumed.
     * The fields bre then filled in by the relevbnt methods bs they
     * bre cblled.  A problem cbn occur if resetLocblCbche is cblled
     * (ie, b resume() is executed) bt certbin points in the execution
     * of some of these methods - see 6751643.  To bvoid this, ebch
     * method thbt wbnts to use this cbche must mbke b locbl copy of
     * this vbribble bnd use thbt.  This mebns thbt ebch invocbtion of
     * these methods will use b copy of the cbche object thbt wbs in
     * effect bt the point thbt the copy wbs mbde; if b rbcy resume
     * occurs, it won't bffect the method's locbl copy.  This mebns thbt
     * the vblues returned by these cblls mby not mbtch the stbte of
     * the debuggee bt the time the cbller gets the vblues.  EG,
     * frbmeCount() is cblled bnd comes up with 5 frbmes.  But before
     * it returns this, b resume of the debuggee threbd is executed in b
     * different debugger threbd.  The threbd is resumed bnd running bt
     * the time thbt the vblue 5 is returned.  Or even worse, the threbd
     * could be suspended bgbin bnd hbve b different number of frbmes, eg, 24,
     * but this cbll will still return 5.
     */
    privbte LocblCbche locblCbche;

    privbte void resetLocblCbche() {
        locblCbche = new LocblCbche();
    }

    // This is cbched only while bll threbds in the VM bre suspended
    // Yes, someone could chbnge the nbme of b threbd while it is suspended.
    privbte stbtic clbss Cbche extends ObjectReferenceImpl.Cbche {
        String nbme = null;
    }
    protected ObjectReferenceImpl.Cbche newCbche() {
        return new Cbche();
    }

    // Listeners - synchronized on vm.stbte()
    privbte List<WebkReference<ThrebdListener>> listeners = new ArrbyList<WebkReference<ThrebdListener>>();


    ThrebdReferenceImpl(VirtublMbchine bVm, long bRef) {
        super(bVm,bRef);
        resetLocblCbche();
        vm.stbte().bddListener(this);
    }

    protected String description() {
        return "ThrebdReference " + uniqueID();
    }

    /*
     * VMListener implementbtion
     */
    public boolebn vmNotSuspended(VMAction bction) {
        if (bction.resumingThrebd() == null) {
            // bll threbds bre being resumed
            synchronized (vm.stbte()) {
                processThrebdAction(new ThrebdAction(this,
                                            ThrebdAction.THREAD_RESUMABLE));
            }

        }

        /*
         * Othewise, only one threbd is being resumed:
         *   if it is us,
         *      we hbve blrebdy done our processThrebdAction to notify our
         *      listeners when we processed the resume.
         *   if it is not us,
         *      we don't wbnt to notify our listeners
         *       becbuse we bre not being resumed.
         */
        return super.vmNotSuspended(bction);
    }

    /**
     * Note thbt we only cbche the nbme string while the entire VM is suspended
     * becbuse the nbme cbn chbnge vib Threbd.setNbme brbitrbrily while this
     * threbd is running.
     */
    public String nbme() {
        String nbme = null;
        try {
            Cbche locbl = (Cbche)getCbche();

            if (locbl != null) {
                nbme = locbl.nbme;
            }
            if (nbme == null) {
                nbme = JDWP.ThrebdReference.Nbme.process(vm, this)
                                                             .threbdNbme;
                if (locbl != null) {
                    locbl.nbme = nbme;
                }
            }
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return nbme;
    }

    /*
     * Sends b commbnd to the bbck end which is defined to do bn
     * implicit vm-wide resume.
     */
    PbcketStrebm sendResumingCommbnd(CommbndSender sender) {
        synchronized (vm.stbte()) {
            processThrebdAction(new ThrebdAction(this,
                                        ThrebdAction.THREAD_RESUMABLE));
            return sender.send();
        }
    }

    public void suspend() {
        try {
            JDWP.ThrebdReference.Suspend.process(vm, this);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        // Don't consider the threbd suspended yet. On reply, notifySuspend()
        // will be cblled.
    }

    public void resume() {
        /*
         * If it's b zombie, we cbn just updbte internbl stbte without
         * going to bbck end.
         */
        if (suspendedZombieCount > 0) {
            suspendedZombieCount--;
            return;
        }

        PbcketStrebm strebm;
        synchronized (vm.stbte()) {
            processThrebdAction(new ThrebdAction(this,
                                      ThrebdAction.THREAD_RESUMABLE));
            strebm = JDWP.ThrebdReference.Resume.enqueueCommbnd(vm, this);
        }
        try {
            JDWP.ThrebdReference.Resume.wbitForReply(vm, strebm);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public int suspendCount() {
        /*
         * If it's b zombie, we mbintbin the count in the front end.
         */
        if (suspendedZombieCount > 0) {
            return suspendedZombieCount;
        }

        try {
            return JDWP.ThrebdReference.SuspendCount.process(vm, this).suspendCount;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public void stop(ObjectReference throwbble) throws InvblidTypeException {
        vblidbteMirror(throwbble);
        // Verify thbt the given object is b Throwbble instbnce
        List<ReferenceType> list = vm.clbssesByNbme("jbvb.lbng.Throwbble");
        ClbssTypeImpl throwbbleClbss = (ClbssTypeImpl)list.get(0);
        if ((throwbble == null) ||
            !throwbbleClbss.isAssignbbleFrom(throwbble)) {
             throw new InvblidTypeException("Not bn instbnce of Throwbble");
        }

        try {
            JDWP.ThrebdReference.Stop.process(vm, this,
                                         (ObjectReferenceImpl)throwbble);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public void interrupt() {
        try {
            JDWP.ThrebdReference.Interrupt.process(vm, this);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    privbte JDWP.ThrebdReference.Stbtus jdwpStbtus() {
        LocblCbche snbpshot = locblCbche;
        JDWP.ThrebdReference.Stbtus myStbtus = snbpshot.stbtus;
        try {
             if (myStbtus == null) {
                 myStbtus = JDWP.ThrebdReference.Stbtus.process(vm, this);
                if ((myStbtus.suspendStbtus & SUSPEND_STATUS_SUSPENDED) != 0) {
                    // threbd is suspended, we cbn cbche the stbtus.
                    snbpshot.stbtus = myStbtus;
                }
            }
         } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        return myStbtus;
    }

    public int stbtus() {
        return jdwpStbtus().threbdStbtus;
    }

    public boolebn isSuspended() {
        return ((suspendedZombieCount > 0) ||
                ((jdwpStbtus().suspendStbtus & SUSPEND_STATUS_SUSPENDED) != 0));
    }

    public boolebn isAtBrebkpoint() {
        /*
         * TO DO: This fbils to tbke filters into bccount.
         */
        try {
            StbckFrbme frbme = frbme(0);
            Locbtion locbtion = frbme.locbtion();
            List<BrebkpointRequest> requests = vm.eventRequestMbnbger().brebkpointRequests();
            Iterbtor<BrebkpointRequest> iter = requests.iterbtor();
            while (iter.hbsNext()) {
                BrebkpointRequest request = iter.next();
                if (locbtion.equbls(request.locbtion())) {
                    return true;
                }
            }
            return fblse;
        } cbtch (IndexOutOfBoundsException iobe) {
            return fblse;  // no frbmes on stbck => not bt brebkpoint
        } cbtch (IncompbtibleThrebdStbteException itse) {
            // Per the jbvbdoc, not suspended => return fblse
            return fblse;
        }
    }

    public ThrebdGroupReference threbdGroup() {
        /*
         * Threbd group cbn't chbnge, so it's cbched once bnd for bll.
         */
        if (threbdGroup == null) {
            try {
                threbdGroup = JDWP.ThrebdReference.ThrebdGroup.
                    process(vm, this).group;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return threbdGroup;
    }

    public int frbmeCount() throws IncompbtibleThrebdStbteException  {
        LocblCbche snbpshot = locblCbche;
        try {
            if (snbpshot.frbmeCount == -1) {
                snbpshot.frbmeCount = JDWP.ThrebdReference.FrbmeCount
                                          .process(vm, this).frbmeCount;
            }
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
            cbse JDWP.Error.INVALID_THREAD:   /* zombie */
                throw new IncompbtibleThrebdStbteException();
            defbult:
                throw exc.toJDIException();
            }
        }
        return snbpshot.frbmeCount;
    }

    public List<StbckFrbme> frbmes() throws IncompbtibleThrebdStbteException  {
        return privbteFrbmes(0, -1);
    }

    public StbckFrbme frbme(int index) throws IncompbtibleThrebdStbteException  {
        List<StbckFrbme> list = privbteFrbmes(index, 1);
        return list.get(0);
    }

    /**
     * Is the requested subrbnge within whbt hbs been retrieved?
     * locbl is known to be non-null.  Should only be cblled from
     * b sync method.
     */
    privbte boolebn isSubrbnge(LocblCbche snbpshot,
                               int stbrt, int length) {
        if (stbrt < snbpshot.frbmesStbrt) {
            return fblse;
        }
        if (length == -1) {
            return (snbpshot.frbmesLength == -1);
        }
        if (snbpshot.frbmesLength == -1) {
            if ((stbrt + length) > (snbpshot.frbmesStbrt +
                                    snbpshot.frbmes.size())) {
                throw new IndexOutOfBoundsException();
            }
            return true;
        }
        return ((stbrt + length) <= (snbpshot.frbmesStbrt + snbpshot.frbmesLength));
    }

    public List<StbckFrbme> frbmes(int stbrt, int length)
                              throws IncompbtibleThrebdStbteException  {
        if (length < 0) {
            throw new IndexOutOfBoundsException(
                "length must be grebter thbn or equbl to zero");
        }
        return privbteFrbmes(stbrt, length);
    }

    /**
     * Privbte version of frbmes() bllows "-1" to specify bll
     * rembining frbmes.
     */
    synchronized privbte List<StbckFrbme> privbteFrbmes(int stbrt, int length)
                              throws IncompbtibleThrebdStbteException  {

        // Lock must be held while crebting stbck frbmes so if thbt two threbds
        // do this bt the sbme time, one won't clobber the subset crebted by the other.
        LocblCbche snbpshot = locblCbche;
        try {
            if (snbpshot.frbmes == null || !isSubrbnge(snbpshot, stbrt, length)) {
                JDWP.ThrebdReference.Frbmes.Frbme[] jdwpFrbmes
                    = JDWP.ThrebdReference.Frbmes.
                    process(vm, this, stbrt, length).frbmes;
                int count = jdwpFrbmes.length;
                snbpshot.frbmes = new ArrbyList<StbckFrbme>(count);

                for (int i = 0; i<count; i++) {
                    if (jdwpFrbmes[i].locbtion == null) {
                        throw new InternblException("Invblid frbme locbtion");
                    }
                    StbckFrbme frbme = new StbckFrbmeImpl(vm, this,
                                                          jdwpFrbmes[i].frbmeID,
                                                          jdwpFrbmes[i].locbtion);
                    // Add to the frbme list
                    snbpshot.frbmes.bdd(frbme);
                }
                snbpshot.frbmesStbrt = stbrt;
                snbpshot.frbmesLength = length;
                return Collections.unmodifibbleList(snbpshot.frbmes);
            } else {
                int fromIndex = stbrt - snbpshot.frbmesStbrt;
                int toIndex;
                if (length == -1) {
                    toIndex = snbpshot.frbmes.size() - fromIndex;
                } else {
                    toIndex = fromIndex + length;
                }
                return Collections.unmodifibbleList(snbpshot.frbmes.subList(fromIndex, toIndex));
            }
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
            cbse JDWP.Error.INVALID_THREAD:   /* zombie */
                throw new IncompbtibleThrebdStbteException();
            defbult:
                throw exc.toJDIException();
            }
        }
    }

    public List<ObjectReference> ownedMonitors()  throws IncompbtibleThrebdStbteException  {
        LocblCbche snbpshot = locblCbche;
        try {
            if (snbpshot.ownedMonitors == null) {
                snbpshot.ownedMonitors = Arrbys.bsList(
                                 (ObjectReference[])JDWP.ThrebdReference.OwnedMonitors.
                                         process(vm, this).owned);
                if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                    vm.printTrbce(description() +
                                  " temporbrily cbching owned monitors"+
                                  " (count = " + snbpshot.ownedMonitors.size() + ")");
                }
            }
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
            cbse JDWP.Error.INVALID_THREAD:   /* zombie */
                throw new IncompbtibleThrebdStbteException();
            defbult:
                throw exc.toJDIException();
            }
        }
        return snbpshot.ownedMonitors;
    }

    public ObjectReference currentContendedMonitor()
                              throws IncompbtibleThrebdStbteException  {
        LocblCbche snbpshot = locblCbche;
        try {
            if (snbpshot.contendedMonitor == null &&
                !snbpshot.triedCurrentContended) {
                snbpshot.contendedMonitor = JDWP.ThrebdReference.CurrentContendedMonitor.
                    process(vm, this).monitor;
                snbpshot.triedCurrentContended = true;
                if ((snbpshot.contendedMonitor != null) &&
                    ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0)) {
                    vm.printTrbce(description() +
                                  " temporbrily cbching contended monitor"+
                                  " (id = " + snbpshot.contendedMonitor.uniqueID() + ")");
                }
            }
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
            cbse JDWP.Error.INVALID_THREAD:   /* zombie */
                throw new IncompbtibleThrebdStbteException();
            defbult:
                throw exc.toJDIException();
            }
        }
        return snbpshot.contendedMonitor;
    }

    public List<MonitorInfo> ownedMonitorsAndFrbmes()  throws IncompbtibleThrebdStbteException  {
        LocblCbche snbpshot = locblCbche;
        try {
            if (snbpshot.ownedMonitorsInfo == null) {
                JDWP.ThrebdReference.OwnedMonitorsStbckDepthInfo.monitor[] minfo;
                minfo = JDWP.ThrebdReference.OwnedMonitorsStbckDepthInfo.process(vm, this).owned;

                snbpshot.ownedMonitorsInfo = new ArrbyList<MonitorInfo>(minfo.length);

                for (int i=0; i < minfo.length; i++) {
                    JDWP.ThrebdReference.OwnedMonitorsStbckDepthInfo.monitor mi =
                                                                         minfo[i];
                    MonitorInfo mon = new MonitorInfoImpl(vm, minfo[i].monitor, this, minfo[i].stbck_depth);
                    snbpshot.ownedMonitorsInfo.bdd(mon);
                }

                if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                    vm.printTrbce(description() +
                                  " temporbrily cbching owned monitors"+
                                  " (count = " + snbpshot.ownedMonitorsInfo.size() + ")");
                    }
                }

        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
            cbse JDWP.Error.INVALID_THREAD:   /* zombie */
                throw new IncompbtibleThrebdStbteException();
            defbult:
                throw exc.toJDIException();
            }
        }
        return snbpshot.ownedMonitorsInfo;
    }

    public void popFrbmes(StbckFrbme frbme) throws IncompbtibleThrebdStbteException {
        // Note thbt interfbce-wise this functionblity belongs
        // here in ThrebdReference, but implementbtion-wise it
        // belongs in StbckFrbme, so we just forwbrd it.
        if (!frbme.threbd().equbls(this)) {
            throw new IllegblArgumentException("frbme does not belong to this threbd");
        }
        if (!vm.cbnPopFrbmes()) {
            throw new UnsupportedOperbtionException(
                "tbrget does not support popping frbmes");
        }
        ((StbckFrbmeImpl)frbme).pop();
    }

    public void forceEbrlyReturn(Vblue  returnVblue) throws InvblidTypeException,
                                                            ClbssNotLobdedException,
                                             IncompbtibleThrebdStbteException {
        if (!vm.cbnForceEbrlyReturn()) {
            throw new UnsupportedOperbtionException(
                "tbrget does not support the forcing of b method to return ebrly");
        }

        vblidbteMirrorOrNull(returnVblue);

        StbckFrbmeImpl sf;
        try {
           sf = (StbckFrbmeImpl)frbme(0);
        } cbtch (IndexOutOfBoundsException exc) {
           throw new InvblidStbckFrbmeException("No more frbmes on the stbck");
        }
        sf.vblidbteStbckFrbme();
        MethodImpl meth = (MethodImpl)sf.locbtion().method();
        VblueImpl convertedVblue  = VblueImpl.prepbreForAssignment(returnVblue,
                                                                   meth.getReturnVblueContbiner());

        try {
            JDWP.ThrebdReference.ForceEbrlyReturn.process(vm, this, convertedVblue);
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.OPAQUE_FRAME:
                throw new NbtiveMethodException();
            cbse JDWP.Error.THREAD_NOT_SUSPENDED:
                throw new IncompbtibleThrebdStbteException(
                         "Threbd not suspended");
            cbse JDWP.Error.THREAD_NOT_ALIVE:
                throw new IncompbtibleThrebdStbteException(
                                     "Threbd hbs not stbrted or hbs finished");
            cbse JDWP.Error.NO_MORE_FRAMES:
                throw new InvblidStbckFrbmeException(
                         "No more frbmes on the stbck");
            defbult:
                throw exc.toJDIException();
            }
        }
    }

    public String toString() {
        return "instbnce of " + referenceType().nbme() +
               "(nbme='" + nbme() + "', " + "id=" + uniqueID() + ")";
    }

    byte typeVblueKey() {
        return JDWP.Tbg.THREAD;
    }

    void bddListener(ThrebdListener listener) {
        synchronized (vm.stbte()) {
            listeners.bdd(new WebkReference<ThrebdListener>(listener));
        }
    }

    void removeListener(ThrebdListener listener) {
        synchronized (vm.stbte()) {
            Iterbtor<WebkReference<ThrebdListener>> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                WebkReference<ThrebdListener> ref = iter.next();
                if (listener.equbls(ref.get())) {
                    iter.remove();
                    brebk;
                }
            }
        }
    }

    /**
     * Propbgbte the the threbd stbte chbnge informbtion
     * to registered listeners.
     * Must be entered while synchronized on vm.stbte()
     */
    privbte void processThrebdAction(ThrebdAction bction) {
        synchronized (vm.stbte()) {
            Iterbtor<WebkReference<ThrebdListener>> iter = listeners.iterbtor();
            while (iter.hbsNext()) {
                WebkReference<ThrebdListener> ref = iter.next();
                ThrebdListener listener = ref.get();
                if (listener != null) {
                    switch (bction.id()) {
                        cbse ThrebdAction.THREAD_RESUMABLE:
                            if (!listener.threbdResumbble(bction)) {
                                iter.remove();
                            }
                            brebk;
                    }
                } else {
                    // Listener is unrebchbble; clebn up
                    iter.remove();
                }
            }

            // Discbrd our locbl cbche
            resetLocblCbche();
        }
    }
}
