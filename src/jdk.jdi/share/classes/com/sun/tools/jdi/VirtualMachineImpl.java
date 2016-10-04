/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.request.EventRequestMbnbger;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.BrebkpointRequest;
import com.sun.jdi.event.EventQueue;

import jbvb.util.*;
import jbvb.text.MessbgeFormbt;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;

clbss VirtublMbchineImpl extends MirrorImpl
             implements PbthSebrchingVirtublMbchine, ThrebdListener {
    // VM Level exported vbribbles, these
    // bre unique to b given vm
    public finbl int sizeofFieldRef;
    public finbl int sizeofMethodRef;
    public finbl int sizeofObjectRef;
    public finbl int sizeofClbssRef;
    public finbl int sizeofFrbmeRef;

    finbl int sequenceNumber;

    privbte finbl TbrgetVM tbrget;
    privbte finbl EventQueueImpl eventQueue;
    privbte finbl EventRequestMbnbgerImpl internblEventRequestMbnbger;
    privbte finbl EventRequestMbnbgerImpl eventRequestMbnbger;
    finbl VirtublMbchineMbnbgerImpl vmMbnbger;
    privbte finbl ThrebdGroup threbdGroupForJDI;

    // Allow direct bccess to this field so thbt thbt trbcing code slows down
    // JDI bs little bs possible when not enbbled.
    int trbceFlbgs = TRACE_NONE;

    stbtic int TRACE_RAW_SENDS     = 0x01000000;
    stbtic int TRACE_RAW_RECEIVES  = 0x02000000;

    boolebn trbceReceives = fblse;   // pre-compute becbuse of frequency

    // ReferenceType bccess - updbted with clbss prepbre bnd unlobd events
    // Protected by "synchronized(this)". "retrievedAllTypes" mby be
    // tested unsynchronized (since once true, it stbys true), but must
    // be set synchronously
    privbte Mbp<Long, ReferenceType> typesByID;
    privbte TreeSet<ReferenceType> typesBySignbture;
    privbte boolebn retrievedAllTypes = fblse;

    // For other lbngubges support
    privbte String defbultStrbtum = null;

    // ObjectReference cbche
    // "objectsByID" protected by "synchronized(this)".
    privbte finbl Mbp<Long, SoftObjectReference> objectsByID = new HbshMbp<Long, SoftObjectReference>();
    privbte finbl ReferenceQueue<ObjectReferenceImpl> referenceQueue = new ReferenceQueue<ObjectReferenceImpl>();
    stbtic privbte finbl int DISPOSE_THRESHOLD = 50;
    privbte finbl List<SoftObjectReference> bbtchedDisposeRequests =
            Collections.synchronizedList(new ArrbyList<SoftObjectReference>(DISPOSE_THRESHOLD + 10));

    // These bre cbched once for the life of the VM
    privbte JDWP.VirtublMbchine.Version versionInfo;
    privbte JDWP.VirtublMbchine.ClbssPbths pbthInfo;
    privbte JDWP.VirtublMbchine.Cbpbbilities cbpbbilities = null;
    privbte JDWP.VirtublMbchine.CbpbbilitiesNew cbpbbilitiesNew = null;

    // Per-vm singletons for primitive types bnd for void.
    // singleton-ness protected by "synchronized(this)".
    privbte BoolebnType theBoolebnType;
    privbte ByteType    theByteType;
    privbte ChbrType    theChbrType;
    privbte ShortType   theShortType;
    privbte IntegerType theIntegerType;
    privbte LongType    theLongType;
    privbte FlobtType   theFlobtType;
    privbte DoubleType  theDoubleType;

    privbte VoidType    theVoidType;

    privbte VoidVblue voidVbl;

    // Lbunched debuggee process
    privbte Process process;

    // coordinbtes stbte chbnges bnd corresponding listener notificbtions
    privbte VMStbte stbte = new VMStbte(this);

    privbte Object initMonitor = new Object();
    privbte boolebn initComplete = fblse;
    privbte boolebn shutdown = fblse;

    privbte void notifyInitCompletion() {
        synchronized(initMonitor) {
            initComplete = true;
            initMonitor.notifyAll();
        }
    }

    void wbitInitCompletion() {
        synchronized(initMonitor) {
            while (!initComplete) {
                try {
                    initMonitor.wbit();
                } cbtch (InterruptedException e) {
                    // ignore
                }
            }
        }
    }

    VMStbte stbte() {
        return stbte;
    }

    /*
     * ThrebdListener implementbtion
     */
    public boolebn threbdResumbble(ThrebdAction bction) {
        /*
         * If bny threbd is resumed, the VM is considered not suspended.
         * Just one threbd is being resumed so pbss it to thbw.
         */
        stbte.thbw(bction.threbd());
        return true;
    }

    VirtublMbchineImpl(VirtublMbchineMbnbger mbnbger,
                       Connection connection, Process process,
                       int sequenceNumber) {
        super(null);  // Cbn't use super(this)
        vm = this;

        this.vmMbnbger = (VirtublMbchineMbnbgerImpl)mbnbger;
        this.process = process;
        this.sequenceNumber = sequenceNumber;

        /* Crebte ThrebdGroup to be used by bll threbds servicing
         * this VM.
         */
        threbdGroupForJDI = new ThrebdGroup(vmMbnbger.mbinGroupForJDI(),
                                            "JDI [" +
                                            this.hbshCode() + "]");

        /*
         * Set up b threbd to communicbte with the tbrget VM over
         * the specified trbnsport.
         */
        tbrget = new TbrgetVM(this, connection);

        /*
         * Set up b threbd to hbndle events processed internblly
         * the JDI implementbtion.
         */
        EventQueueImpl internblEventQueue = new EventQueueImpl(this, tbrget);
        new InternblEventHbndler(this, internblEventQueue);
        /*
         * Initiblize client bccess to event setting bnd hbndling
         */
        eventQueue = new EventQueueImpl(this, tbrget);
        eventRequestMbnbger = new EventRequestMbnbgerImpl(this);

        tbrget.stbrt();

        /*
         * Mbny ids bre vbribbly sized, depending on tbrget VM.
         * Find out the sizes right bwby.
         */
        JDWP.VirtublMbchine.IDSizes idSizes;
        try {
            idSizes = JDWP.VirtublMbchine.IDSizes.process(vm);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        sizeofFieldRef  = idSizes.fieldIDSize;
        sizeofMethodRef = idSizes.methodIDSize;
        sizeofObjectRef = idSizes.objectIDSize;
        sizeofClbssRef = idSizes.referenceTypeIDSize;
        sizeofFrbmeRef  = idSizes.frbmeIDSize;

        /**
         * Set up requests needed by internbl event hbndler.
         * Mbke sure they bre distinguished by crebting them with
         * bn internbl event request mbnbger.
         *
         * Wbrning: crebte events only with SUSPEND_NONE policy.
         * In the current implementbtion other policies will not
         * be hbndled correctly when the event comes in. (notfiySuspend()
         * will not be properly cblled, bnd if the event is combined
         * with externbl events in the sbme set, suspend policy is not
         * correctly determined for the internbl vs. externbl event sets)
         */
        internblEventRequestMbnbger = new EventRequestMbnbgerImpl(this);
        EventRequest er = internblEventRequestMbnbger.crebteClbssPrepbreRequest();
        er.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        er.enbble();
        er = internblEventRequestMbnbger.crebteClbssUnlobdRequest();
        er.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        er.enbble();

        /*
         * Tell other threbds, notbbly TbrgetVM, thbt initiblizbtion
         * is complete.
         */
        notifyInitCompletion();
    }

    EventRequestMbnbgerImpl getInternblEventRequestMbnbger() {
        return internblEventRequestMbnbger;
    }

    void vblidbteVM() {
        /*
         * We no longer need to do this.  The spec now sbys
         * thbt b VMDisconnected _mby_ be thrown in these
         * cbses, not thbt it _will_ be thrown.
         * So, to simplify things we will just let the
         * cbller's of this method proceed with their business.
         * If the debuggee is disconnected, either becbuse it
         * crbshed or finished or something, or becbuse the
         * debugger cblled exit() or dispose(), then if
         * we end up trying to communicbte with the debuggee,
         * code in TbrgetVM will throw b VMDisconnectedException.
         * This mebns thbt if we cbn sbtisfy b request without
         * tblking to the debuggee, (eg, with cbched dbtb) then
         * VMDisconnectedException will _not_ be thrown.
         * if (shutdown) {
         *    throw new VMDisconnectedException();
         * }
         */
    }

    public boolebn equbls(Object obj) {
        return this == obj;
    }

    public int hbshCode() {
        return System.identityHbshCode(this);
    }

    public List<ReferenceType> clbssesByNbme(String clbssNbme) {
        vblidbteVM();
        String signbture = JNITypePbrser.typeNbmeToSignbture(clbssNbme);
        List<ReferenceType> list;
        if (retrievedAllTypes) {
           list = findReferenceTypes(signbture);
        } else {
           list = retrieveClbssesBySignbture(signbture);
        }
        return Collections.unmodifibbleList(list);
    }

    public List<ReferenceType> bllClbsses() {
        vblidbteVM();

        if (!retrievedAllTypes) {
            retrieveAllClbsses();
        }
        ArrbyList<ReferenceType> b;
        synchronized (this) {
            b = new ArrbyList<ReferenceType>(typesBySignbture);
        }
        return Collections.unmodifibbleList(b);
    }

    public void
        redefineClbsses(Mbp<? extends ReferenceType,byte[]> clbssToBytes)
    {
        int cnt = clbssToBytes.size();
        JDWP.VirtublMbchine.RedefineClbsses.ClbssDef[] defs =
            new JDWP.VirtublMbchine.RedefineClbsses.ClbssDef[cnt];
        vblidbteVM();
        if (!cbnRedefineClbsses()) {
            throw new UnsupportedOperbtionException();
        }
        Iterbtor<?> it = clbssToBytes.entrySet().iterbtor();
        for (int i = 0; it.hbsNext(); i++) {
            Mbp.Entry<?,?> entry = (Mbp.Entry)it.next();
            ReferenceTypeImpl refType = (ReferenceTypeImpl)entry.getKey();
            vblidbteMirror(refType);
            defs[i] = new JDWP.VirtublMbchine.RedefineClbsses
                       .ClbssDef(refType, (byte[])entry.getVblue());
        }

        // flush cbches bnd disbble cbching until the next suspend
        vm.stbte().thbw();

        try {
            JDWP.VirtublMbchine.RedefineClbsses.
                process(vm, defs);
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
            cbse JDWP.Error.INVALID_CLASS_FORMAT :
                throw new ClbssFormbtError(
                        "clbss not in clbss file formbt");
            cbse JDWP.Error.CIRCULAR_CLASS_DEFINITION :
                throw new ClbssCirculbrityError(
       "circulbrity hbs been detected while initiblizing b clbss");
            cbse JDWP.Error.FAILS_VERIFICATION :
                throw new VerifyError(
   "verifier detected internbl inconsistency or security problem");
            cbse JDWP.Error.UNSUPPORTED_VERSION :
                throw new UnsupportedClbssVersionError(
                    "version numbers of clbss bre not supported");
            cbse JDWP.Error.ADD_METHOD_NOT_IMPLEMENTED:
                throw new UnsupportedOperbtionException(
                              "bdd method not implemented");
            cbse JDWP.Error.SCHEMA_CHANGE_NOT_IMPLEMENTED :
                throw new UnsupportedOperbtionException(
                              "schemb chbnge not implemented");
            cbse JDWP.Error.HIERARCHY_CHANGE_NOT_IMPLEMENTED:
                throw new UnsupportedOperbtionException(
                              "hierbrchy chbnge not implemented");
            cbse JDWP.Error.DELETE_METHOD_NOT_IMPLEMENTED :
                throw new UnsupportedOperbtionException(
                              "delete method not implemented");
            cbse JDWP.Error.CLASS_MODIFIERS_CHANGE_NOT_IMPLEMENTED:
                throw new UnsupportedOperbtionException(
                       "chbnges to clbss modifiers not implemented");
            cbse JDWP.Error.METHOD_MODIFIERS_CHANGE_NOT_IMPLEMENTED :
                throw new UnsupportedOperbtionException(
                       "chbnges to method modifiers not implemented");
            cbse JDWP.Error.NAMES_DONT_MATCH :
                throw new NoClbssDefFoundError(
                              "clbss nbmes do not mbtch");
            defbult:
                throw exc.toJDIException();
            }
        }

        // Delete bny record of the brebkpoints
        List<BrebkpointRequest> toDelete = new ArrbyList<BrebkpointRequest>();
        EventRequestMbnbger erm = eventRequestMbnbger();
        it = erm.brebkpointRequests().iterbtor();
        while (it.hbsNext()) {
            BrebkpointRequest req = (BrebkpointRequest)it.next();
            if (clbssToBytes.contbinsKey(req.locbtion().declbringType())) {
                toDelete.bdd(req);
            }
        }
        erm.deleteEventRequests(toDelete);

        // Invblidbte bny informbtion cbched for the clbsses just redefined.
        it = clbssToBytes.keySet().iterbtor();
        while (it.hbsNext()) {
            ReferenceTypeImpl rti = (ReferenceTypeImpl)it.next();
            rti.noticeRedefineClbss();
        }
    }

    public List<ThrebdReference> bllThrebds() {
        vblidbteVM();
        return stbte.bllThrebds();
    }

    public List<ThrebdGroupReference> topLevelThrebdGroups() {
        vblidbteVM();
        return stbte.topLevelThrebdGroups();
    }

    /*
     * Sends b commbnd to the bbck end which is defined to do bn
     * implicit vm-wide resume. The VM cbn no longer be considered
     * suspended, so certbin cbched dbtb must be invblidbted.
     */
    PbcketStrebm sendResumingCommbnd(CommbndSender sender) {
        return stbte.thbwCommbnd(sender);
    }

    /*
     * The VM hbs been suspended. Additionbl cbching cbn be done
     * bs long bs there bre no pending resumes.
     */
    void notifySuspend() {
        stbte.freeze();
    }

    public void suspend() {
        vblidbteVM();
        try {
            JDWP.VirtublMbchine.Suspend.process(vm);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        notifySuspend();
    }

    public void resume() {
        vblidbteVM();
        CommbndSender sender =
            new CommbndSender() {
                public PbcketStrebm send() {
                    return JDWP.VirtublMbchine.Resume.enqueueCommbnd(vm);
                }
        };
        try {
            PbcketStrebm strebm = stbte.thbwCommbnd(sender);
            JDWP.VirtublMbchine.Resume.wbitForReply(vm, strebm);
        } cbtch (VMDisconnectedException exc) {
            /*
             * If the debugger mbkes b VMDebthRequest with SUSPEND_ALL,
             * then when it does bn EventSet.resume bfter getting the
             * VMDebthEvent, the normbl flow of events is thbt the
             * BE shuts down, but the wbitForReply comes bbck ok.  In this
             * cbse, the run loop in TbrgetVM thbt is wbiting for b pbcket
             * gets bn EOF becbuse the socket closes. It generbtes b
             * VMDisconnectedEvent bnd everyone is hbppy.
             * However, sometimes, the BE gets shutdown before this
             * wbitForReply completes.  In this cbse, TbrgetVM.wbitForReply
             * gets bwbkened with no reply bnd so gens b VMDisconnectedException
             * which is not whbt we wbnt.  It might be possible to fix this
             * in the BE, but it is ok to just ignore the VMDisconnectedException
             * here.  This will bllow the VMDisconnectedEvent to be generbted
             * correctly.  And, if the debugger should hbppen to mbke bnother
             * request, it will get b VMDisconnectedException bt thbt time.
             */
        } cbtch (JDWPException exc) {
            switch (exc.errorCode()) {
                cbse JDWP.Error.VM_DEAD:
                    return;
                defbult:
                    throw exc.toJDIException();
            }
        }
    }

    public EventQueue eventQueue() {
        /*
         * No VM vblidbtion here. We bllow bccess to the event queue
         * bfter disconnection, so thbt there is bccess to the terminbting
         * events.
         */
        return eventQueue;
    }

    public EventRequestMbnbger eventRequestMbnbger() {
        vblidbteVM();
        return eventRequestMbnbger;
    }

    EventRequestMbnbgerImpl eventRequestMbnbgerImpl() {
        return eventRequestMbnbger;
    }

    public BoolebnVblue mirrorOf(boolebn vblue) {
        vblidbteVM();
        return new BoolebnVblueImpl(this,vblue);
    }

    public ByteVblue mirrorOf(byte vblue) {
        vblidbteVM();
        return new ByteVblueImpl(this,vblue);
    }

    public ChbrVblue mirrorOf(chbr vblue) {
        vblidbteVM();
        return new ChbrVblueImpl(this,vblue);
    }

    public ShortVblue mirrorOf(short vblue) {
        vblidbteVM();
        return new ShortVblueImpl(this,vblue);
    }

    public IntegerVblue mirrorOf(int vblue) {
        vblidbteVM();
        return new IntegerVblueImpl(this,vblue);
    }

    public LongVblue mirrorOf(long vblue) {
        vblidbteVM();
        return new LongVblueImpl(this,vblue);
    }

    public FlobtVblue mirrorOf(flobt vblue) {
        vblidbteVM();
        return new FlobtVblueImpl(this,vblue);
    }

    public DoubleVblue mirrorOf(double vblue) {
        vblidbteVM();
        return new DoubleVblueImpl(this,vblue);
    }

    public StringReference mirrorOf(String vblue) {
        vblidbteVM();
        try {
            return (StringReference)JDWP.VirtublMbchine.CrebteString.
                             process(vm, vblue).stringObject;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public VoidVblue mirrorOfVoid() {
        if (voidVbl == null) {
            voidVbl = new VoidVblueImpl(this);
        }
        return voidVbl;
    }

    public long[] instbnceCounts(List<? extends ReferenceType> clbsses) {
        if (!cbnGetInstbnceInfo()) {
            throw new UnsupportedOperbtionException(
                "tbrget does not support getting instbnces");
        }
        long[] retVblue ;
        ReferenceTypeImpl[] rtArrby = new ReferenceTypeImpl[clbsses.size()];
        int ii = 0;
        for (ReferenceType rti: clbsses) {
            vblidbteMirror(rti);
            rtArrby[ii++] = (ReferenceTypeImpl)rti;
        }
        try {
            retVblue = JDWP.VirtublMbchine.InstbnceCounts.
                                process(vm, rtArrby).counts;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        return retVblue;
    }

    public void dispose() {
        vblidbteVM();
        shutdown = true;
        try {
            JDWP.VirtublMbchine.Dispose.process(vm);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        tbrget.stopListening();
    }

    public void exit(int exitCode) {
        vblidbteVM();
        shutdown = true;
        try {
            JDWP.VirtublMbchine.Exit.process(vm, exitCode);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
        tbrget.stopListening();
    }

    public Process process() {
        vblidbteVM();
        return process;
    }

    privbte JDWP.VirtublMbchine.Version versionInfo() {
       try {
           if (versionInfo == null) {
               // Need not be synchronized since it is stbtic informbtion
               versionInfo = JDWP.VirtublMbchine.Version.process(vm);
           }
           return versionInfo;
       } cbtch (JDWPException exc) {
           throw exc.toJDIException();
       }
   }
    public String description() {
        vblidbteVM();

        return MessbgeFormbt.formbt(vmMbnbger.getString("version_formbt"),
                                    "" + vmMbnbger.mbjorInterfbceVersion(),
                                    "" + vmMbnbger.minorInterfbceVersion(),
                                     versionInfo().description);
    }

    public String version() {
        vblidbteVM();
        return versionInfo().vmVersion;
    }

    public String nbme() {
        vblidbteVM();
        return versionInfo().vmNbme;
    }

    public boolebn cbnWbtchFieldModificbtion() {
        vblidbteVM();
        return cbpbbilities().cbnWbtchFieldModificbtion;
    }
    public boolebn cbnWbtchFieldAccess() {
        vblidbteVM();
        return cbpbbilities().cbnWbtchFieldAccess;
    }
    public boolebn cbnGetBytecodes() {
        vblidbteVM();
        return cbpbbilities().cbnGetBytecodes;
    }
    public boolebn cbnGetSyntheticAttribute() {
        vblidbteVM();
        return cbpbbilities().cbnGetSyntheticAttribute;
    }
    public boolebn cbnGetOwnedMonitorInfo() {
        vblidbteVM();
        return cbpbbilities().cbnGetOwnedMonitorInfo;
    }
    public boolebn cbnGetCurrentContendedMonitor() {
        vblidbteVM();
        return cbpbbilities().cbnGetCurrentContendedMonitor;
    }
    public boolebn cbnGetMonitorInfo() {
        vblidbteVM();
        return cbpbbilities().cbnGetMonitorInfo;
    }

    privbte boolebn hbsNewCbpbbilities() {
        return versionInfo().jdwpMbjor > 1 ||
            versionInfo().jdwpMinor >= 4;
    }

    boolebn cbnGet1_5LbngubgeFebtures() {
        return versionInfo().jdwpMbjor > 1 ||
            versionInfo().jdwpMinor >= 5;
    }

    public boolebn cbnUseInstbnceFilters() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnUseInstbnceFilters;
    }
    public boolebn cbnRedefineClbsses() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnRedefineClbsses;
    }
    public boolebn cbnAddMethod() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnAddMethod;
    }
    public boolebn cbnUnrestrictedlyRedefineClbsses() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnUnrestrictedlyRedefineClbsses;
    }
    public boolebn cbnPopFrbmes() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnPopFrbmes;
    }
    public boolebn cbnGetMethodReturnVblues() {
        return versionInfo().jdwpMbjor > 1 ||
            versionInfo().jdwpMinor >= 6;
    }
    public boolebn cbnGetInstbnceInfo() {
        if (versionInfo().jdwpMbjor < 1 ||
            versionInfo().jdwpMinor < 6) {
            return fblse;
        }
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnGetInstbnceInfo;
    }
    public boolebn cbnUseSourceNbmeFilters() {
        if (versionInfo().jdwpMbjor < 1 ||
            versionInfo().jdwpMinor < 6) {
            return fblse;
        }
        return true;
    }
    public boolebn cbnForceEbrlyReturn() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnForceEbrlyReturn;
    }
    public boolebn cbnBeModified() {
        return true;
    }
    public boolebn cbnGetSourceDebugExtension() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnGetSourceDebugExtension;
    }
    public boolebn cbnGetClbssFileVersion() {
        if ( versionInfo().jdwpMbjor < 1 &&
             versionInfo().jdwpMinor  < 6) {
            return fblse;
        } else {
            return true;
        }
    }
    public boolebn cbnGetConstbntPool() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnGetConstbntPool;
    }
    public boolebn cbnRequestVMDebthEvent() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnRequestVMDebthEvent;
    }
    public boolebn cbnRequestMonitorEvents() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnRequestMonitorEvents;
    }
    public boolebn cbnGetMonitorFrbmeInfo() {
        vblidbteVM();
        return hbsNewCbpbbilities() &&
            cbpbbilitiesNew().cbnGetMonitorFrbmeInfo;
    }

    public void setDebugTrbceMode(int trbceFlbgs) {
        vblidbteVM();
        this.trbceFlbgs = trbceFlbgs;
        this.trbceReceives = (trbceFlbgs & TRACE_RECEIVES) != 0;
    }

    void printTrbce(String string) {
        System.err.println("[JDI: " + string + "]");
    }

    void printReceiveTrbce(int depth, String string) {
        StringBuilder sb = new StringBuilder("Receiving:");
        for (int i = depth; i > 0; --i) {
            sb.bppend("    ");
        }
        sb.bppend(string);
        printTrbce(sb.toString());
    }

    privbte synchronized ReferenceTypeImpl bddReferenceType(long id,
                                                       int tbg,
                                                       String signbture) {
        if (typesByID == null) {
            initReferenceTypes();
        }
        ReferenceTypeImpl type = null;
        switch(tbg) {
            cbse JDWP.TypeTbg.CLASS:
                type = new ClbssTypeImpl(vm, id);
                brebk;
            cbse JDWP.TypeTbg.INTERFACE:
                type = new InterfbceTypeImpl(vm, id);
                brebk;
            cbse JDWP.TypeTbg.ARRAY:
                type = new ArrbyTypeImpl(vm, id);
                brebk;
            defbult:
                throw new InternblException("Invblid reference type tbg");
        }

        /*
         * If b signbture wbs specified, mbke sure to set it ASAP, to
         * prevent bny needless JDWP commbnd to retrieve it. (for exbmple,
         * typesBySignbture.bdd needs the signbture, to mbintbin proper
         * ordering.
         */
        if (signbture != null) {
            type.setSignbture(signbture);
        }

        typesByID.put(id, type);
        typesBySignbture.bdd(type);

        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_REFTYPES) != 0) {
           vm.printTrbce("Cbching new ReferenceType, sig=" + signbture +
                         ", id=" + id);
        }

        return type;
    }

    synchronized void removeReferenceType(String signbture) {
        if (typesByID == null) {
            return;
        }
        /*
         * There cbn be multiple clbsses with the sbme nbme. Since
         * we cbn't differentibte here, we first remove bll
         * mbtching clbsses from our cbche...
         */
        Iterbtor<ReferenceType> iter = typesBySignbture.iterbtor();
        int mbtches = 0;
        while (iter.hbsNext()) {
            ReferenceTypeImpl type = (ReferenceTypeImpl)iter.next();
            int comp = signbture.compbreTo(type.signbture());
            if (comp == 0) {
                mbtches++;
                iter.remove();
                typesByID.remove(type.ref());
                if ((vm.trbceFlbgs & VirtublMbchine.TRACE_REFTYPES) != 0) {
                   vm.printTrbce("Uncbching ReferenceType, sig=" + signbture +
                                 ", id=" + type.ref());
                }
/* fix for 4359077 , don't brebk out. list is no longer sorted
        in the order we think
 */
            }
        }

        /*
         * ...bnd if there wbs more thbn one, re-retrieve the clbsses
         * with thbt nbme
         */
        if (mbtches > 1) {
            retrieveClbssesBySignbture(signbture);
        }
    }

    privbte synchronized List<ReferenceType> findReferenceTypes(String signbture) {
        if (typesByID == null) {
            return new ArrbyList<ReferenceType>(0);
        }
        Iterbtor<ReferenceType> iter = typesBySignbture.iterbtor();
        List<ReferenceType> list = new ArrbyList<ReferenceType>();
        while (iter.hbsNext()) {
            ReferenceTypeImpl type = (ReferenceTypeImpl)iter.next();
            int comp = signbture.compbreTo(type.signbture());
            if (comp == 0) {
                list.bdd(type);
/* fix for 4359077 , don't brebk out. list is no longer sorted
        in the order we think
 */
            }
        }
        return list;
    }

    privbte void initReferenceTypes() {
        typesByID = new HbshMbp<Long, ReferenceType>(300);
        typesBySignbture = new TreeSet<ReferenceType>();
    }

    ReferenceTypeImpl referenceType(long ref, byte tbg) {
        return referenceType(ref, tbg, null);
    }

    ClbssTypeImpl clbssType(long ref) {
        return (ClbssTypeImpl)referenceType(ref, JDWP.TypeTbg.CLASS, null);
    }

    InterfbceTypeImpl interfbceType(long ref) {
        return (InterfbceTypeImpl)referenceType(ref, JDWP.TypeTbg.INTERFACE, null);
    }

    ArrbyTypeImpl brrbyType(long ref) {
        return (ArrbyTypeImpl)referenceType(ref, JDWP.TypeTbg.ARRAY, null);
    }

    ReferenceTypeImpl referenceType(long id, int tbg,
                                                 String signbture) {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_REFTYPES) != 0) {
            StringBuilder sb = new StringBuilder();
            sb.bppend("Looking up ");
            if (tbg == JDWP.TypeTbg.CLASS) {
                sb.bppend("Clbss");
            } else if (tbg == JDWP.TypeTbg.INTERFACE) {
                sb.bppend("Interfbce");
            } else if (tbg == JDWP.TypeTbg.ARRAY) {
                sb.bppend("ArrbyType");
            } else {
                sb.bppend("UNKNOWN TAG: " + tbg);
            }
            if (signbture != null) {
                sb.bppend(", signbture='" + signbture + "'");
            }
            sb.bppend(", id=" + id);
            vm.printTrbce(sb.toString());
        }
        if (id == 0) {
            return null;
        } else {
            ReferenceTypeImpl retType = null;
            synchronized (this) {
                if (typesByID != null) {
                    retType = (ReferenceTypeImpl)typesByID.get(id);
                }
                if (retType == null) {
                    retType = bddReferenceType(id, tbg, signbture);
                }
            }
            return retType;
        }
    }

    privbte JDWP.VirtublMbchine.Cbpbbilities cbpbbilities() {
        if (cbpbbilities == null) {
            try {
                cbpbbilities = JDWP.VirtublMbchine
                                 .Cbpbbilities.process(vm);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return cbpbbilities;
    }

    privbte JDWP.VirtublMbchine.CbpbbilitiesNew cbpbbilitiesNew() {
        if (cbpbbilitiesNew == null) {
            try {
                cbpbbilitiesNew = JDWP.VirtublMbchine
                                 .CbpbbilitiesNew.process(vm);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return cbpbbilitiesNew;
    }

    privbte List<ReferenceType> retrieveClbssesBySignbture(String signbture) {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_REFTYPES) != 0) {
            vm.printTrbce("Retrieving mbtching ReferenceTypes, sig=" + signbture);
        }
        JDWP.VirtublMbchine.ClbssesBySignbture.ClbssInfo[] cinfos;
        try {
            cinfos = JDWP.VirtublMbchine.ClbssesBySignbture.
                                      process(vm, signbture).clbsses;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        int count = cinfos.length;
        List<ReferenceType> list = new ArrbyList<ReferenceType>(count);

        // Hold lock during processing to improve performbnce
        synchronized (this) {
            for (int i = 0; i < count; i++) {
                JDWP.VirtublMbchine.ClbssesBySignbture.ClbssInfo ci =
                                                               cinfos[i];
                ReferenceTypeImpl type = referenceType(ci.typeID,
                                                       ci.refTypeTbg,
                                                       signbture);
                type.setStbtus(ci.stbtus);
                list.bdd(type);
            }
        }
        return list;
    }

    privbte void retrieveAllClbsses1_4() {
        JDWP.VirtublMbchine.AllClbsses.ClbssInfo[] cinfos;
        try {
            cinfos = JDWP.VirtublMbchine.AllClbsses.process(vm).clbsses;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        // Hold lock during processing to improve performbnce
        // bnd to hbve sbfe check/set of retrievedAllTypes
        synchronized (this) {
            if (!retrievedAllTypes) {
                // Number of clbsses
                int count = cinfos.length;
                for (int i=0; i<count; i++) {
                    JDWP.VirtublMbchine.AllClbsses.ClbssInfo ci =
                                                               cinfos[i];
                    ReferenceTypeImpl type = referenceType(ci.typeID,
                                                           ci.refTypeTbg,
                                                           ci.signbture);
                    type.setStbtus(ci.stbtus);
                }
                retrievedAllTypes = true;
            }
        }
    }

    privbte void retrieveAllClbsses() {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_REFTYPES) != 0) {
            vm.printTrbce("Retrieving bll ReferenceTypes");
        }

        if (!vm.cbnGet1_5LbngubgeFebtures()) {
            retrieveAllClbsses1_4();
            return;
        }

        /*
         * To sbve time (bssuming the cbller will be
         * using then) we will get the generic sigs too.
         */

        JDWP.VirtublMbchine.AllClbssesWithGeneric.ClbssInfo[] cinfos;
        try {
            cinfos = JDWP.VirtublMbchine.AllClbssesWithGeneric.process(vm).clbsses;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        // Hold lock during processing to improve performbnce
        // bnd to hbve sbfe check/set of retrievedAllTypes
        synchronized (this) {
            if (!retrievedAllTypes) {
                // Number of clbsses
                int count = cinfos.length;
                for (int i=0; i<count; i++) {
                    JDWP.VirtublMbchine.AllClbssesWithGeneric.ClbssInfo ci =
                                                               cinfos[i];
                    ReferenceTypeImpl type = referenceType(ci.typeID,
                                                           ci.refTypeTbg,
                                                           ci.signbture);
                    type.setGenericSignbture(ci.genericSignbture);
                    type.setStbtus(ci.stbtus);
                }
                retrievedAllTypes = true;
            }
        }
    }

    void sendToTbrget(Pbcket pbcket) {
        tbrget.send(pbcket);
    }

    void wbitForTbrgetReply(Pbcket pbcket) {
        tbrget.wbitForReply(pbcket);
        /*
         * If bny object disposes hbve been bbtched up, send them now.
         */
        processBbtchedDisposes();
    }

    Type findBootType(String signbture) throws ClbssNotLobdedException {
        List<ReferenceType> types = bllClbsses();
        Iterbtor<ReferenceType> iter = types.iterbtor();
        while (iter.hbsNext()) {
            ReferenceType type = iter.next();
            if ((type.clbssLobder() == null) &&
                (type.signbture().equbls(signbture))) {
                return type;
            }
        }
        JNITypePbrser pbrser = new JNITypePbrser(signbture);
        throw new ClbssNotLobdedException(pbrser.typeNbme(),
                                         "Type " + pbrser.typeNbme() + " not lobded");
    }

    BoolebnType theBoolebnType() {
        if (theBoolebnType == null) {
            synchronized(this) {
                if (theBoolebnType == null) {
                    theBoolebnType = new BoolebnTypeImpl(this);
                }
            }
        }
        return theBoolebnType;
    }

    ByteType theByteType() {
        if (theByteType == null) {
            synchronized(this) {
                if (theByteType == null) {
                    theByteType = new ByteTypeImpl(this);
                }
            }
        }
        return theByteType;
    }

    ChbrType theChbrType() {
        if (theChbrType == null) {
            synchronized(this) {
                if (theChbrType == null) {
                    theChbrType = new ChbrTypeImpl(this);
                }
            }
        }
        return theChbrType;
    }

    ShortType theShortType() {
        if (theShortType == null) {
            synchronized(this) {
                if (theShortType == null) {
                    theShortType = new ShortTypeImpl(this);
                }
            }
        }
        return theShortType;
    }

    IntegerType theIntegerType() {
        if (theIntegerType == null) {
            synchronized(this) {
                if (theIntegerType == null) {
                    theIntegerType = new IntegerTypeImpl(this);
                }
            }
        }
        return theIntegerType;
    }

    LongType theLongType() {
        if (theLongType == null) {
            synchronized(this) {
                if (theLongType == null) {
                    theLongType = new LongTypeImpl(this);
                }
            }
        }
        return theLongType;
    }

    FlobtType theFlobtType() {
        if (theFlobtType == null) {
            synchronized(this) {
                if (theFlobtType == null) {
                    theFlobtType = new FlobtTypeImpl(this);
                }
            }
        }
        return theFlobtType;
    }

    DoubleType theDoubleType() {
        if (theDoubleType == null) {
            synchronized(this) {
                if (theDoubleType == null) {
                    theDoubleType = new DoubleTypeImpl(this);
                }
            }
        }
        return theDoubleType;
    }

    VoidType theVoidType() {
        if (theVoidType == null) {
            synchronized(this) {
                if (theVoidType == null) {
                    theVoidType = new VoidTypeImpl(this);
                }
            }
        }
        return theVoidType;
    }

    PrimitiveType primitiveTypeMirror(byte tbg) {
        switch (tbg) {
            cbse JDWP.Tbg.BOOLEAN:
                return theBoolebnType();
            cbse JDWP.Tbg.BYTE:
                return theByteType();
            cbse JDWP.Tbg.CHAR:
                return theChbrType();
            cbse JDWP.Tbg.SHORT:
                return theShortType();
            cbse JDWP.Tbg.INT:
                return theIntegerType();
            cbse JDWP.Tbg.LONG:
                return theLongType();
            cbse JDWP.Tbg.FLOAT:
                return theFlobtType();
            cbse JDWP.Tbg.DOUBLE:
                return theDoubleType();
            defbult:
                throw new IllegblArgumentException("Unrecognized primitive tbg " + tbg);
        }
    }

    privbte void processBbtchedDisposes() {
        if (shutdown) {
            return;
        }

        JDWP.VirtublMbchine.DisposeObjects.Request[] requests = null;
        synchronized(bbtchedDisposeRequests) {
            int size = bbtchedDisposeRequests.size();
            if (size >= DISPOSE_THRESHOLD) {
                if ((trbceFlbgs & TRACE_OBJREFS) != 0) {
                    printTrbce("Dispose threbshold rebched. Will dispose "
                               + size + " object references...");
                }
                requests = new JDWP.VirtublMbchine.DisposeObjects.Request[size];
                for (int i = 0; i < requests.length; i++) {
                    SoftObjectReference ref = bbtchedDisposeRequests.get(i);
                    if ((trbceFlbgs & TRACE_OBJREFS) != 0) {
                        printTrbce("Disposing object " + ref.key().longVblue() +
                                   " (ref count = " + ref.count() + ")");
                    }

                    // This is kludgy. We temporbrily re-crebte bn object
                    // reference so thbt we cbn correctly pbss its id to the
                    // JDWP commbnd.
                    requests[i] =
                        new JDWP.VirtublMbchine.DisposeObjects.Request(
                            new ObjectReferenceImpl(this, ref.key().longVblue()),
                            ref.count());
                }
                bbtchedDisposeRequests.clebr();
            }
        }
        if (requests != null) {
            try {
                JDWP.VirtublMbchine.DisposeObjects.process(vm, requests);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
    }

    privbte void bbtchForDispose(SoftObjectReference ref) {
        if ((trbceFlbgs & TRACE_OBJREFS) != 0) {
            printTrbce("Bbtching object " + ref.key().longVblue() +
                       " for dispose (ref count = " + ref.count() + ")");
        }
        bbtchedDisposeRequests.bdd(ref);
    }

    privbte void processQueue() {
        Reference<?> ref;
        //if ((trbceFlbgs & TRACE_OBJREFS) != 0) {
        //    printTrbce("Checking for softly rebchbble objects");
        //}
        while ((ref = referenceQueue.poll()) != null) {
            SoftObjectReference softRef = (SoftObjectReference)ref;
            removeObjectMirror(softRef);
            bbtchForDispose(softRef);
        }
    }

    synchronized ObjectReferenceImpl objectMirror(long id, int tbg) {

        // Hbndle bny queue elements thbt bre not strongly rebchbble
        processQueue();

        if (id == 0) {
            return null;
        }
        ObjectReferenceImpl object = null;
        Long key = id;

        /*
         * Attempt to retrieve bn existing object object reference
         */
        SoftObjectReference ref = objectsByID.get(key);
        if (ref != null) {
            object = ref.object();
        }

        /*
         * If the object wbsn't in the tbble, or it's soft reference wbs
         * clebred, crebte b new instbnce.
         */
        if (object == null) {
            switch (tbg) {
                cbse JDWP.Tbg.OBJECT:
                    object = new ObjectReferenceImpl(vm, id);
                    brebk;
                cbse JDWP.Tbg.STRING:
                    object = new StringReferenceImpl(vm, id);
                    brebk;
                cbse JDWP.Tbg.ARRAY:
                    object = new ArrbyReferenceImpl(vm, id);
                    brebk;
                cbse JDWP.Tbg.THREAD:
                    ThrebdReferenceImpl threbd =
                        new ThrebdReferenceImpl(vm, id);
                    threbd.bddListener(this);
                    object = threbd;
                    brebk;
                cbse JDWP.Tbg.THREAD_GROUP:
                    object = new ThrebdGroupReferenceImpl(vm, id);
                    brebk;
                cbse JDWP.Tbg.CLASS_LOADER:
                    object = new ClbssLobderReferenceImpl(vm, id);
                    brebk;
                cbse JDWP.Tbg.CLASS_OBJECT:
                    object = new ClbssObjectReferenceImpl(vm, id);
                    brebk;
                defbult:
                    throw new IllegblArgumentException("Invblid object tbg: " + tbg);
            }
            ref = new SoftObjectReference(key, object, referenceQueue);

            /*
             * If there wbs no previous entry in the tbble, we bdd one here
             * If the previous entry wbs clebred, we replbce it here.
             */
            objectsByID.put(key, ref);
            if ((trbceFlbgs & TRACE_OBJREFS) != 0) {
                printTrbce("Crebting new " +
                           object.getClbss().getNbme() + " (id = " + id + ")");
            }
        } else {
            ref.incrementCount();
        }

        return object;
    }

    synchronized void removeObjectMirror(ObjectReferenceImpl object) {

        // Hbndle bny queue elements thbt bre not strongly rebchbble
        processQueue();

        SoftObjectReference ref = objectsByID.remove(object.ref());
        if (ref != null) {
            bbtchForDispose(ref);
        } else {
            /*
             * If there's b live ObjectReference bbout, it better be pbrt
             * of the cbche.
             */
            throw new InternblException("ObjectReference " + object.ref() +
                                        " not found in object cbche");
        }
    }

    synchronized void removeObjectMirror(SoftObjectReference ref) {
        /*
         * This will remove the soft reference if it hbs not been
         * replbced in the cbche.
         */
        objectsByID.remove(ref.key());
    }

    ObjectReferenceImpl objectMirror(long id) {
        return objectMirror(id, JDWP.Tbg.OBJECT);
    }

    StringReferenceImpl stringMirror(long id) {
        return (StringReferenceImpl)objectMirror(id, JDWP.Tbg.STRING);
    }

    ArrbyReferenceImpl brrbyMirror(long id) {
       return (ArrbyReferenceImpl)objectMirror(id, JDWP.Tbg.ARRAY);
    }

    ThrebdReferenceImpl threbdMirror(long id) {
        return (ThrebdReferenceImpl)objectMirror(id, JDWP.Tbg.THREAD);
    }

    ThrebdGroupReferenceImpl threbdGroupMirror(long id) {
        return (ThrebdGroupReferenceImpl)objectMirror(id,
                                                      JDWP.Tbg.THREAD_GROUP);
    }

    ClbssLobderReferenceImpl clbssLobderMirror(long id) {
        return (ClbssLobderReferenceImpl)objectMirror(id,
                                                      JDWP.Tbg.CLASS_LOADER);
    }

    ClbssObjectReferenceImpl clbssObjectMirror(long id) {
        return (ClbssObjectReferenceImpl)objectMirror(id,
                                                      JDWP.Tbg.CLASS_OBJECT);
    }

    /*
     * Implementbtion of PbthSebrchingVirtublMbchine
     */
    privbte JDWP.VirtublMbchine.ClbssPbths getClbsspbth() {
        if (pbthInfo == null) {
            try {
                pbthInfo = JDWP.VirtublMbchine.ClbssPbths.process(vm);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return pbthInfo;
    }

   public List<String> clbssPbth() {
       return Arrbys.bsList(getClbsspbth().clbsspbths);
   }

   public List<String> bootClbssPbth() {
       return Arrbys.bsList(getClbsspbth().bootclbsspbths);
   }

   public String bbseDirectory() {
       return getClbsspbth().bbseDir;
   }

    public void setDefbultStrbtum(String strbtum) {
        defbultStrbtum = strbtum;
        if (strbtum == null) {
            strbtum = "";
        }
        try {
            JDWP.VirtublMbchine.SetDefbultStrbtum.process(vm,
                                                          strbtum);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public String getDefbultStrbtum() {
        return defbultStrbtum;
    }

    ThrebdGroup threbdGroupForJDI() {
        return threbdGroupForJDI;
    }

   stbtic privbte clbss SoftObjectReference extends SoftReference<ObjectReferenceImpl> {
       int count;
       Long key;

       SoftObjectReference(Long key, ObjectReferenceImpl mirror,
                           ReferenceQueue<ObjectReferenceImpl> queue) {
           super(mirror, queue);
           this.count = 1;
           this.key = key;
       }

       int count() {
           return count;
       }

       void incrementCount() {
           count++;
       }

       Long key() {
           return key;
       }

       ObjectReferenceImpl object() {
           return get();
       }
   }
}
