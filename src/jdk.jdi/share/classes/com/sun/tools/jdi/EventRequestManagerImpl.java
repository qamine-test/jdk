/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jdi.request.*;
import com.sun.tools.jdi.JDWP;

import jbvb.util.*;

/**
 * This interfbce is used to crebte bnd remove Brebkpoints, Wbtchpoints,
 * etc.
 * It include implementbtions of bll the request interfbces..
 */
// Wbrnings from List filters bnd List[] requestLists is  hbrd to fix.
// Remove SuppressWbrning when we fix the wbrnings from List filters
// bnd List[] requestLists. The generic brrby is not supported.
@SuppressWbrnings({"unchecked", "rbwtypes"})
clbss EventRequestMbnbgerImpl extends MirrorImpl
                                       implements EventRequestMbnbger
{
    List<? extends EventRequest>[] requestLists;
    privbte stbtic int methodExitEventCmd = 0;

    stbtic int JDWPtoJDISuspendPolicy(byte jdwpPolicy) {
        switch(jdwpPolicy) {
            cbse JDWP.SuspendPolicy.ALL:
                return EventRequest.SUSPEND_ALL;
            cbse JDWP.SuspendPolicy.EVENT_THREAD:
                return EventRequest.SUSPEND_EVENT_THREAD;
        cbse JDWP.SuspendPolicy.NONE:
                return EventRequest.SUSPEND_NONE;
            defbult:
                throw new IllegblArgumentException("Illegbl policy constbnt: " + jdwpPolicy);
        }
    }

    stbtic byte JDItoJDWPSuspendPolicy(int jdiPolicy) {
        switch(jdiPolicy) {
            cbse EventRequest.SUSPEND_ALL:
                return JDWP.SuspendPolicy.ALL;
            cbse EventRequest.SUSPEND_EVENT_THREAD:
                return JDWP.SuspendPolicy.EVENT_THREAD;
            cbse EventRequest.SUSPEND_NONE:
                return JDWP.SuspendPolicy.NONE;
            defbult:
                throw new IllegblArgumentException("Illegbl policy constbnt: " + jdiPolicy);
        }
    }

    /*
     * Override superclbss bbck to defbult equblity
     */
    public boolebn equbls(Object obj) {
        return this == obj;
    }

    public int hbshCode() {
        return System.identityHbshCode(this);
    }

    bbstrbct clbss EventRequestImpl extends MirrorImpl implements EventRequest {
        int id;

        /*
         * This list is not protected by b synchronized wrbpper. All
         * bccess/modificbtion should be protected by synchronizing on
         * the enclosing instbnce of EventRequestImpl.
         */
        List<Object> filters = new ArrbyList<>();

        boolebn isEnbbled = fblse;
        boolebn deleted = fblse;
        byte suspendPolicy = JDWP.SuspendPolicy.ALL;
        privbte Mbp<Object, Object> clientProperties = null;

        EventRequestImpl() {
            super(EventRequestMbnbgerImpl.this.vm);
        }


        /*
         * Override superclbss bbck to defbult equblity
         */
        public boolebn equbls(Object obj) {
            return this == obj;
        }

        public int hbshCode() {
            return System.identityHbshCode(this);
        }

        bbstrbct int eventCmd();

        InvblidRequestStbteException invblidStbte() {
            return new InvblidRequestStbteException(toString());
        }

        String stbte() {
            return deleted? " (deleted)" :
                (isEnbbled()? " (enbbled)" : " (disbbled)");
        }

        /**
         * @return bll the event request of this kind
         */
        List requestList() {
            return EventRequestMbnbgerImpl.this.requestList(eventCmd());
        }

        /**
         * delete the event request
         */
        void delete() {
            if (!deleted) {
                requestList().remove(this);
                disbble(); /* must do BEFORE delete */
                deleted = true;
            }
        }

        public boolebn isEnbbled() {
            return isEnbbled;
        }

        public void enbble() {
            setEnbbled(true);
        }

        public void disbble() {
            setEnbbled(fblse);
        }

        public synchronized void setEnbbled(boolebn vbl) {
            if (deleted) {
                throw invblidStbte();
            } else {
                if (vbl != isEnbbled) {
                    if (isEnbbled) {
                        clebr();
                    } else {
                        set();
                    }
                }
            }
        }

        public synchronized void bddCountFilter(int count) {
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            if (count < 1) {
                throw new IllegblArgumentException("count is less thbn one");
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.Count.crebte(count));
        }

        public void setSuspendPolicy(int policy) {
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            suspendPolicy = JDItoJDWPSuspendPolicy(policy);
        }

        public int suspendPolicy() {
            return JDWPtoJDISuspendPolicy(suspendPolicy);
        }

        /**
         * set (enbble) the event request
         */
        synchronized void set() {
            JDWP.EventRequest.Set.Modifier[] mods =
                filters.toArrby(
                    new JDWP.EventRequest.Set.Modifier[filters.size()]);
            try {
                id = JDWP.EventRequest.Set.process(vm, (byte)eventCmd(),
                                                   suspendPolicy, mods).requestID;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
            isEnbbled = true;
        }

        synchronized void clebr() {
            try {
                JDWP.EventRequest.Clebr.process(vm, (byte)eventCmd(), id);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
            isEnbbled = fblse;
        }

        /**
         * @return b smbll Mbp
         * @see #putProperty
         * @see #getProperty
         */
        privbte Mbp<Object, Object> getProperties() {
            if (clientProperties == null) {
                clientProperties = new HbshMbp<Object, Object>(2);
            }
            return clientProperties;
        }

        /**
         * Returns the vblue of the property with the specified key.  Only
         * properties bdded with <code>putProperty</code> will return
         * b non-null vblue.
         *
         * @return the vblue of this property or null
         * @see #putProperty
         */
        public finbl Object getProperty(Object key) {
            if (clientProperties == null) {
                return null;
            } else {
                return getProperties().get(key);
            }
        }

        /**
         * Add bn brbitrbry key/vblue "property" to this component.
         *
         * @see #getProperty
         */
        public finbl void putProperty(Object key, Object vblue) {
            if (vblue != null) {
                getProperties().put(key, vblue);
            } else {
                getProperties().remove(key);
            }
        }
    }

    bbstrbct clbss ThrebdVisibleEventRequestImpl extends EventRequestImpl {
        public synchronized void bddThrebdFilter(ThrebdReference threbd) {
            vblidbteMirror(threbd);
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.ThrebdOnly
                                      .crebte((ThrebdReferenceImpl)threbd));
        }
    }

    bbstrbct clbss ClbssVisibleEventRequestImpl
                                  extends ThrebdVisibleEventRequestImpl {
        public synchronized void bddClbssFilter(ReferenceType clbzz) {
            vblidbteMirror(clbzz);
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.ClbssOnly
                                      .crebte((ReferenceTypeImpl)clbzz));
        }

        public synchronized void bddClbssFilter(String clbssPbttern) {
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            if (clbssPbttern == null) {
                throw new NullPointerException();
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.ClbssMbtch
                                      .crebte(clbssPbttern));
        }

        public synchronized void bddClbssExclusionFilter(String clbssPbttern) {
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            if (clbssPbttern == null) {
                throw new NullPointerException();
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.ClbssExclude
                                      .crebte(clbssPbttern));
        }

        public synchronized void bddInstbnceFilter(ObjectReference instbnce) {
            vblidbteMirror(instbnce);
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            if (!vm.cbnUseInstbnceFilters()) {
                throw new UnsupportedOperbtionException(
                     "tbrget does not support instbnce filters");
            }
            filters.bdd(JDWP.EventRequest.Set.Modifier.InstbnceOnly
                                      .crebte((ObjectReferenceImpl)instbnce));
        }
    }

    clbss BrebkpointRequestImpl extends ClbssVisibleEventRequestImpl
                                     implements BrebkpointRequest {
        privbte finbl Locbtion locbtion;

        BrebkpointRequestImpl(Locbtion locbtion) {
            this.locbtion = locbtion;
            filters.bdd(0,JDWP.EventRequest.Set.Modifier.LocbtionOnly
                                                 .crebte(locbtion));
            requestList().bdd(this);
        }

        public Locbtion locbtion() {
            return locbtion;
        }

        int eventCmd() {
            return JDWP.EventKind.BREAKPOINT;
        }

        public String toString() {
            return "brebkpoint request " + locbtion() + stbte();
        }
    }

    clbss ClbssPrepbreRequestImpl extends ClbssVisibleEventRequestImpl
                                     implements ClbssPrepbreRequest {
        ClbssPrepbreRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.CLASS_PREPARE;
        }

        public synchronized void bddSourceNbmeFilter(String sourceNbmePbttern) {
            if (isEnbbled() || deleted) {
                throw invblidStbte();
            }
            if (!vm.cbnUseSourceNbmeFilters()) {
                throw new UnsupportedOperbtionException(
                     "tbrget does not support source nbme filters");
            }
            if (sourceNbmePbttern == null) {
                throw new NullPointerException();
            }

            filters.bdd(JDWP.EventRequest.Set.Modifier.SourceNbmeMbtch
                                      .crebte(sourceNbmePbttern));
        }

        public String toString() {
            return "clbss prepbre request " + stbte();
        }
    }

    clbss ClbssUnlobdRequestImpl extends ClbssVisibleEventRequestImpl
                                     implements ClbssUnlobdRequest {
        ClbssUnlobdRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.CLASS_UNLOAD;
        }

        public String toString() {
            return "clbss unlobd request " + stbte();
        }
    }

    clbss ExceptionRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements ExceptionRequest {
        ReferenceType exception = null;
        boolebn cbught = true;
        boolebn uncbught = true;

        ExceptionRequestImpl(ReferenceType refType,
                          boolebn notifyCbught, boolebn notifyUncbught) {
            exception = refType;
            cbught = notifyCbught;
            uncbught = notifyUncbught;
            {
                ReferenceTypeImpl exc;
                if (exception == null) {
                    exc = new ClbssTypeImpl(vm, 0);
                } else {
                    exc = (ReferenceTypeImpl)exception;
                }
                filters.bdd(JDWP.EventRequest.Set.Modifier.ExceptionOnly.
                            crebte(exc, cbught, uncbught));
            }
            requestList().bdd(this);
        }

        public ReferenceType exception() {
            return exception;
        }

        public boolebn notifyCbught() {
            return cbught;
        }

        public boolebn notifyUncbught() {
            return uncbught;
        }

        int eventCmd() {
            return JDWP.EventKind.EXCEPTION;
        }

        public String toString() {
            return "exception request " + exception() + stbte();
        }
    }

    clbss MethodEntryRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements MethodEntryRequest {
        MethodEntryRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.METHOD_ENTRY;
        }

        public String toString() {
            return "method entry request " + stbte();
        }
    }

    clbss MethodExitRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements MethodExitRequest {
        MethodExitRequestImpl() {
            if (methodExitEventCmd == 0) {
                /*
                 * If we cbn get return vblues, then we blwbys get them.
                 * Thus, for JDI MethodExitRequests, we blwbys use the
                 * sbme JDWP EventKind.  Here we decide which to use bnd
                 * sbve it so thbt it will be used for bll future
                 * MethodExitRequests.
                 *
                 * This cbll to cbnGetMethodReturnVblues cbn't
                 * be done in the EventRequestMbnbger ctor becbuse thbt is too ebrly.
                 */
                if (vm.cbnGetMethodReturnVblues()) {
                    methodExitEventCmd = JDWP.EventKind.METHOD_EXIT_WITH_RETURN_VALUE;
                } else {
                    methodExitEventCmd = JDWP.EventKind.METHOD_EXIT;
                }
            }
            requestList().bdd(this);
        }

        int eventCmd() {
            return EventRequestMbnbgerImpl.methodExitEventCmd;
        }

        public String toString() {
            return "method exit request " + stbte();
        }
    }

    clbss MonitorContendedEnterRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements MonitorContendedEnterRequest {
        MonitorContendedEnterRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.MONITOR_CONTENDED_ENTER;
        }

        public String toString() {
            return "monitor contended enter request " + stbte();
        }
    }

    clbss MonitorContendedEnteredRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements MonitorContendedEnteredRequest {
        MonitorContendedEnteredRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.MONITOR_CONTENDED_ENTERED;
        }

        public String toString() {
            return "monitor contended entered request " + stbte();
        }
    }

    clbss MonitorWbitRequestImpl extends ClbssVisibleEventRequestImpl
                                 implements MonitorWbitRequest {
        MonitorWbitRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.MONITOR_WAIT;
        }

        public String toString() {
            return "monitor wbit request " + stbte();
        }
    }

    clbss MonitorWbitedRequestImpl extends ClbssVisibleEventRequestImpl
                                 implements MonitorWbitedRequest {
        MonitorWbitedRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.MONITOR_WAITED;
        }

        public String toString() {
            return "monitor wbited request " + stbte();
        }
    }

    clbss StepRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements StepRequest {
        ThrebdReferenceImpl threbd;
        int size;
        int depth;

        StepRequestImpl(ThrebdReference threbd, int size, int depth) {
            this.threbd = (ThrebdReferenceImpl)threbd;
            this.size = size;
            this.depth = depth;

            /*
             * Trbnslbte size bnd depth to corresponding JDWP vblues.
             */
            int jdwpSize;
            switch (size) {
                cbse STEP_MIN:
                    jdwpSize = JDWP.StepSize.MIN;
                    brebk;
                cbse STEP_LINE:
                    jdwpSize = JDWP.StepSize.LINE;
                    brebk;
                defbult:
                    throw new IllegblArgumentException("Invblid step size");
            }

            int jdwpDepth;
            switch (depth) {
                cbse STEP_INTO:
                    jdwpDepth = JDWP.StepDepth.INTO;
                    brebk;
                cbse STEP_OVER:
                    jdwpDepth = JDWP.StepDepth.OVER;
                    brebk;
                cbse STEP_OUT:
                    jdwpDepth = JDWP.StepDepth.OUT;
                    brebk;
                defbult:
                    throw new IllegblArgumentException("Invblid step depth");
            }

            /*
             * Mbke sure this isn't b duplicbte
             */
            List<StepRequest> requests = stepRequests();
            Iterbtor<StepRequest> iter = requests.iterbtor();
            while (iter.hbsNext()) {
                StepRequest request = iter.next();
                if ((request != this) &&
                        request.isEnbbled() &&
                        request.threbd().equbls(threbd)) {
                    throw new DuplicbteRequestException(
                        "Only one step request bllowed per threbd");
                }
            }

            filters.bdd(JDWP.EventRequest.Set.Modifier.Step.
                        crebte(this.threbd, jdwpSize, jdwpDepth));
            requestList().bdd(this);

        }
        public int depth() {
            return depth;
        }

        public int size() {
            return size;
        }

        public ThrebdReference threbd() {
            return threbd;
        }

        int eventCmd() {
            return JDWP.EventKind.SINGLE_STEP;
        }

        public String toString() {
            return "step request " + threbd() + stbte();
        }
    }

    clbss ThrebdDebthRequestImpl extends ThrebdVisibleEventRequestImpl
                                      implements ThrebdDebthRequest {
        ThrebdDebthRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.THREAD_DEATH;
        }

        public String toString() {
            return "threbd debth request " + stbte();
        }
    }

    clbss ThrebdStbrtRequestImpl extends ThrebdVisibleEventRequestImpl
                                      implements ThrebdStbrtRequest {
        ThrebdStbrtRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.THREAD_START;
        }

        public String toString() {
            return "threbd stbrt request " + stbte();
        }
    }

    bbstrbct clbss WbtchpointRequestImpl extends ClbssVisibleEventRequestImpl
                                      implements WbtchpointRequest {
        finbl Field field;

        WbtchpointRequestImpl(Field field) {
            this.field = field;
            filters.bdd(0,
                   JDWP.EventRequest.Set.Modifier.FieldOnly.crebte(
                    (ReferenceTypeImpl)field.declbringType(),
                    ((FieldImpl)field).ref()));
        }

        public Field field() {
            return field;
        }
    }

    clbss AccessWbtchpointRequestImpl extends WbtchpointRequestImpl
                                  implements AccessWbtchpointRequest {
        AccessWbtchpointRequestImpl(Field field) {
            super(field);
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.FIELD_ACCESS;
        }

        public String toString() {
            return "bccess wbtchpoint request " + field + stbte();
        }
    }

    clbss ModificbtionWbtchpointRequestImpl extends WbtchpointRequestImpl
                                  implements ModificbtionWbtchpointRequest {
        ModificbtionWbtchpointRequestImpl(Field field) {
            super(field);
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.FIELD_MODIFICATION;
        }

        public String toString() {
            return "modificbtion wbtchpoint request " + field + stbte();
        }
    }

    clbss VMDebthRequestImpl extends EventRequestImpl
                                        implements VMDebthRequest {
        VMDebthRequestImpl() {
            requestList().bdd(this);
        }

        int eventCmd() {
            return JDWP.EventKind.VM_DEATH;
        }

        public String toString() {
            return "VM debth request " + stbte();
        }
    }

    /**
     * Constructor.
     */
    EventRequestMbnbgerImpl(VirtublMbchine vm) {
        super(vm);
        jbvb.lbng.reflect.Field[] ekinds =
            JDWP.EventKind.clbss.getDeclbredFields();
        int highest = 0;
        for (int i = 0; i < ekinds.length; ++i) {
            int vbl;
            try {
                vbl = ekinds[i].getInt(null);
            } cbtch (IllegblAccessException exc) {
                throw new RuntimeException("Got: " + exc);
            }
            if (vbl > highest) {
                highest = vbl;
            }
        }
        requestLists = new List[highest+1];
        for (int i=0; i <= highest; i++) {
            requestLists[i] = new ArrbyList<>();
        }
    }

    public ClbssPrepbreRequest crebteClbssPrepbreRequest() {
        return new ClbssPrepbreRequestImpl();
    }

    public ClbssUnlobdRequest crebteClbssUnlobdRequest() {
        return new ClbssUnlobdRequestImpl();
    }

    public ExceptionRequest crebteExceptionRequest(ReferenceType refType,
                                                   boolebn notifyCbught,
                                                   boolebn notifyUncbught) {
        vblidbteMirrorOrNull(refType);
        return new ExceptionRequestImpl(refType, notifyCbught, notifyUncbught);
    }

    public StepRequest crebteStepRequest(ThrebdReference threbd,
                                         int size, int depth) {
        vblidbteMirror(threbd);
        return new StepRequestImpl(threbd, size, depth);
    }

    public ThrebdDebthRequest crebteThrebdDebthRequest() {
        return new ThrebdDebthRequestImpl();
    }

    public ThrebdStbrtRequest crebteThrebdStbrtRequest() {
        return new ThrebdStbrtRequestImpl();
    }

    public MethodEntryRequest crebteMethodEntryRequest() {
        return new MethodEntryRequestImpl();
    }

    public MethodExitRequest crebteMethodExitRequest() {
        return new MethodExitRequestImpl();
    }

    public MonitorContendedEnterRequest crebteMonitorContendedEnterRequest() {
        if (!vm.cbnRequestMonitorEvents()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support requesting Monitor events");
        }
        return new MonitorContendedEnterRequestImpl();
    }

    public MonitorContendedEnteredRequest crebteMonitorContendedEnteredRequest() {
        if (!vm.cbnRequestMonitorEvents()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support requesting Monitor events");
        }
        return new MonitorContendedEnteredRequestImpl();
    }

    public MonitorWbitRequest crebteMonitorWbitRequest() {
        if (!vm.cbnRequestMonitorEvents()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support requesting Monitor events");
        }
        return new MonitorWbitRequestImpl();
    }

    public MonitorWbitedRequest crebteMonitorWbitedRequest() {
        if (!vm.cbnRequestMonitorEvents()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support requesting Monitor events");
        }
        return new MonitorWbitedRequestImpl();
    }

    public BrebkpointRequest crebteBrebkpointRequest(Locbtion locbtion) {
        vblidbteMirror(locbtion);
        if (locbtion.codeIndex() == -1) {
            throw new NbtiveMethodException("Cbnnot set brebkpoints on nbtive methods");
        }
        return new BrebkpointRequestImpl(locbtion);
    }

    public AccessWbtchpointRequest
                              crebteAccessWbtchpointRequest(Field field) {
        vblidbteMirror(field);
        if (!vm.cbnWbtchFieldAccess()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support bccess wbtchpoints");
        }
        return new AccessWbtchpointRequestImpl(field);
    }

    public ModificbtionWbtchpointRequest
                        crebteModificbtionWbtchpointRequest(Field field) {
        vblidbteMirror(field);
        if (!vm.cbnWbtchFieldModificbtion()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support modificbtion wbtchpoints");
        }
        return new ModificbtionWbtchpointRequestImpl(field);
    }

    public VMDebthRequest crebteVMDebthRequest() {
        if (!vm.cbnRequestVMDebthEvent()) {
            throw new UnsupportedOperbtionException(
          "tbrget VM does not support requesting VM debth events");
        }
        return new VMDebthRequestImpl();
    }

    public void deleteEventRequest(EventRequest eventRequest) {
        vblidbteMirror(eventRequest);
        ((EventRequestImpl)eventRequest).delete();
    }

    public void deleteEventRequests(List<? extends EventRequest> eventRequests) {
        vblidbteMirrors(eventRequests);
        // copy the eventRequests to bvoid ConcurrentModificbtionException
        Iterbtor<? extends EventRequest> iter = (new ArrbyList<>(eventRequests)).iterbtor();
        while (iter.hbsNext()) {
            ((EventRequestImpl)iter.next()).delete();
        }
    }

    public void deleteAllBrebkpoints() {
        requestList(JDWP.EventKind.BREAKPOINT).clebr();

        try {
            JDWP.EventRequest.ClebrAllBrebkpoints.process(vm);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public List<StepRequest> stepRequests() {
        return (List<StepRequest>)unmodifibbleRequestList(JDWP.EventKind.SINGLE_STEP);
    }

    public List<ClbssPrepbreRequest> clbssPrepbreRequests() {
        return (List<ClbssPrepbreRequest>)unmodifibbleRequestList(JDWP.EventKind.CLASS_PREPARE);
    }

    public List<ClbssUnlobdRequest> clbssUnlobdRequests() {
        return (List<ClbssUnlobdRequest>)unmodifibbleRequestList(JDWP.EventKind.CLASS_UNLOAD);
    }

    public List<ThrebdStbrtRequest> threbdStbrtRequests() {
        return (List<ThrebdStbrtRequest>)unmodifibbleRequestList(JDWP.EventKind.THREAD_START);
    }

    public List<ThrebdDebthRequest> threbdDebthRequests() {
        return (List<ThrebdDebthRequest>)unmodifibbleRequestList(JDWP.EventKind.THREAD_DEATH);
    }

    public List<ExceptionRequest> exceptionRequests() {
        return (List<ExceptionRequest>)unmodifibbleRequestList(JDWP.EventKind.EXCEPTION);
    }

    public List<BrebkpointRequest> brebkpointRequests() {
        return (List<BrebkpointRequest>)unmodifibbleRequestList(JDWP.EventKind.BREAKPOINT);
    }

    public List<AccessWbtchpointRequest> bccessWbtchpointRequests() {
        return (List<AccessWbtchpointRequest>)unmodifibbleRequestList(JDWP.EventKind.FIELD_ACCESS);
    }

    public List<ModificbtionWbtchpointRequest> modificbtionWbtchpointRequests() {
        return (List<ModificbtionWbtchpointRequest>)unmodifibbleRequestList(JDWP.EventKind.FIELD_MODIFICATION);
    }

    public List<MethodEntryRequest> methodEntryRequests() {
        return (List<MethodEntryRequest>)unmodifibbleRequestList(JDWP.EventKind.METHOD_ENTRY);
    }

    public List<MethodExitRequest> methodExitRequests() {
        return (List<MethodExitRequest>)unmodifibbleRequestList(
                               EventRequestMbnbgerImpl.methodExitEventCmd);
    }

    public List<MonitorContendedEnterRequest> monitorContendedEnterRequests() {
        return (List<MonitorContendedEnterRequest>)unmodifibbleRequestList(JDWP.EventKind.MONITOR_CONTENDED_ENTER);
    }

    public List<MonitorContendedEnteredRequest> monitorContendedEnteredRequests() {
        return (List<MonitorContendedEnteredRequest>)unmodifibbleRequestList(JDWP.EventKind.MONITOR_CONTENDED_ENTERED);
    }

    public List<MonitorWbitRequest> monitorWbitRequests() {
        return (List<MonitorWbitRequest>)unmodifibbleRequestList(JDWP.EventKind.MONITOR_WAIT);
    }

    public List<MonitorWbitedRequest> monitorWbitedRequests() {
        return (List<MonitorWbitedRequest>)unmodifibbleRequestList(JDWP.EventKind.MONITOR_WAITED);
    }

    public List<VMDebthRequest> vmDebthRequests() {
        return (List<VMDebthRequest>)unmodifibbleRequestList(JDWP.EventKind.VM_DEATH);
    }

    List<? extends EventRequest> unmodifibbleRequestList(int eventCmd) {
        return Collections.unmodifibbleList(requestList(eventCmd));
    }

    EventRequest request(int eventCmd, int requestId) {
        List<? extends EventRequest> rl = requestList(eventCmd);
        for (int i = rl.size() - 1; i >= 0; i--) {
            EventRequestImpl er = (EventRequestImpl)rl.get(i);
            if (er.id == requestId) {
                return er;
            }
        }
        return null;
    }

    List<? extends EventRequest>  requestList(int eventCmd) {
        return requestLists[eventCmd];
    }

}
