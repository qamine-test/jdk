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

import jbvb.util.*;
import jbvb.util.ArrbyList;

public clbss ObjectReferenceImpl extends VblueImpl
             implements ObjectReference, VMListener {

    protected long ref;
    privbte ReferenceType type = null;
    privbte int gcDisbbleCount = 0;
    boolebn bddedListener = fblse;

    // This is cbched only while the VM is suspended
    protected stbtic clbss Cbche {
        JDWP.ObjectReference.MonitorInfo monitorInfo = null;
    }

    privbte stbtic finbl Cbche noInitCbche = new Cbche();
    privbte stbtic finbl Cbche mbrkerCbche = new Cbche();
    privbte Cbche cbche = noInitCbche;

    privbte void disbbleCbche() {
        synchronized (vm.stbte()) {
            cbche = null;
        }
    }

    privbte void enbbleCbche() {
        synchronized (vm.stbte()) {
            cbche = mbrkerCbche;
        }
    }

    // Override in subclbsses
    protected Cbche newCbche() {
        return new Cbche();
    }

    protected Cbche getCbche() {
        synchronized (vm.stbte()) {
            if (cbche == noInitCbche) {
                if (vm.stbte().isSuspended()) {
                    // Set cbche now, otherwise newly crebted objects bre
                    // not cbched until resuspend
                    enbbleCbche();
                } else {
                    disbbleCbche();
                }
            }
            if (cbche == mbrkerCbche) {
                cbche = newCbche();
            }
            return cbche;
        }
    }

    // Return the ClbssTypeImpl upon which to invoke b method.
    // By defbult it is our very own referenceType() but subclbsses
    // cbn override.
    protected ClbssTypeImpl invokbbleReferenceType(Method method) {
        return (ClbssTypeImpl)referenceType();
    }

    ObjectReferenceImpl(VirtublMbchine bVm,long bRef) {
        super(bVm);

        ref = bRef;
    }

    protected String description() {
        return "ObjectReference " + uniqueID();
    }

    /*
     * VMListener implementbtion
     */
    public boolebn vmSuspended(VMAction bction) {
        enbbleCbche();
        return true;
    }

    public boolebn vmNotSuspended(VMAction bction) {
        // mbke sure thbt cbche bnd listener mbnbgement bre synchronized
        synchronized (vm.stbte()) {
            if (cbche != null && (vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                vm.printTrbce("Clebring temporbry cbche for " + description());
            }
            disbbleCbche();
            if (bddedListener) {
                /*
                 * If b listener wbs bdded (i.e. this is not b
                 * ObjectReference thbt bdds b listener on stbrtup),
                 * remove it here.
                 */
                bddedListener = fblse;
                return fblse;  // fblse sbys remove
            } else {
                return true;
            }
        }
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof ObjectReferenceImpl)) {
            ObjectReferenceImpl other = (ObjectReferenceImpl)obj;
            return (ref() == other.ref()) &&
                   super.equbls(obj);
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        return(int)ref();
    }

    public Type type() {
        return referenceType();
    }

    public ReferenceType referenceType() {
        if (type == null) {
            try {
                JDWP.ObjectReference.ReferenceType rtinfo =
                    JDWP.ObjectReference.ReferenceType.process(vm, this);
                type = vm.referenceType(rtinfo.typeID,
                                        rtinfo.refTypeTbg);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        return type;
    }

    public Vblue getVblue(Field sig) {
        List<Field> list = new ArrbyList<Field>(1);
        list.bdd(sig);
        Mbp<Field, Vblue> mbp = getVblues(list);
        return mbp.get(sig);
    }

    public Mbp<Field,Vblue> getVblues(List<? extends Field> theFields) {
        vblidbteMirrors(theFields);

        List<Field> stbticFields = new ArrbyList<Field>(0);
        int size = theFields.size();
        List<Field> instbnceFields = new ArrbyList<Field>(size);

        for (int i=0; i<size; i++) {
            Field field = (Field)theFields.get(i);

            // Mbke sure the field is vblid
            ((ReferenceTypeImpl)referenceType()).vblidbteFieldAccess(field);

            // FIX ME! We need to do some sbnity checking
            // here; mbke sure the field belongs to this
            // object.
            if (field.isStbtic())
                stbticFields.bdd(field);
            else {
                instbnceFields.bdd(field);
            }
        }

        Mbp<Field, Vblue> mbp;
        if (stbticFields.size() > 0) {
            mbp = referenceType().getVblues(stbticFields);
        } else {
            mbp = new HbshMbp<Field, Vblue>(size);
        }

        size = instbnceFields.size();

        JDWP.ObjectReference.GetVblues.Field[] queryFields =
                         new JDWP.ObjectReference.GetVblues.Field[size];
        for (int i=0; i<size; i++) {
            FieldImpl field = (FieldImpl)instbnceFields.get(i);/* thbnks OTI */
            queryFields[i] = new JDWP.ObjectReference.GetVblues.Field(
                                         field.ref());
        }
        VblueImpl[] vblues;
        try {
            vblues = JDWP.ObjectReference.GetVblues.
                                     process(vm, this, queryFields).vblues;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }

        if (size != vblues.length) {
            throw new InternblException(
                         "Wrong number of vblues returned from tbrget VM");
        }
        for (int i=0; i<size; i++) {
            FieldImpl field = (FieldImpl)instbnceFields.get(i);
            mbp.put(field, vblues[i]);
        }

        return mbp;
    }

    public void setVblue(Field field, Vblue vblue)
                   throws InvblidTypeException, ClbssNotLobdedException {

        vblidbteMirror(field);
        vblidbteMirrorOrNull(vblue);

        // Mbke sure the field is vblid
        ((ReferenceTypeImpl)referenceType()).vblidbteFieldSet(field);

        if (field.isStbtic()) {
            ReferenceType type = referenceType();
            if (type instbnceof ClbssType) {
                ((ClbssType)type).setVblue(field, vblue);
                return;
            } else {
                throw new IllegblArgumentException(
                                    "Invblid type for stbtic field set");
            }
        }

        try {
            JDWP.ObjectReference.SetVblues.FieldVblue[] fvbls =
                      new JDWP.ObjectReference.SetVblues.FieldVblue[1];
            fvbls[0] = new JDWP.ObjectReference.SetVblues.FieldVblue(
                           ((FieldImpl)field).ref(),
                           // Vblidbte bnd convert if necessbry
                           VblueImpl.prepbreForAssignment(vblue,
                                                          (FieldImpl)field));
            try {
                JDWP.ObjectReference.SetVblues.process(vm, this, fvbls);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        } cbtch (ClbssNotLobdedException e) {
            /*
             * Since we got this exception,
             * the field type must be b reference type. The vblue
             * we're trying to set is null, but if the field's
             * clbss hbs not yet been lobded through the enclosing
             * clbss lobder, then setting to null is essentiblly b
             * no-op, bnd we should bllow it without bn exception.
             */
            if (vblue != null) {
                throw e;
            }
        }
    }

    void vblidbteMethodInvocbtion(Method method, int options)
                                         throws InvblidTypeException,
                                         InvocbtionException {
        /*
         * Method must be in this object's clbss, b superclbss, or
         * implemented interfbce
         */
        ReferenceTypeImpl declType = (ReferenceTypeImpl)method.declbringType();
        if (!declType.isAssignbbleFrom(this)) {
            throw new IllegblArgumentException("Invblid method");
        }

        if (declType instbnceof ClbssTypeImpl) {
            vblidbteClbssMethodInvocbtion(method, options);
        } else if (declType instbnceof InterfbceTypeImpl) {
            vblidbteIfbceMethodInvocbtion(method, options);
        } else {
            throw new InvblidTypeException();
        }
    }

    void vblidbteClbssMethodInvocbtion(Method method, int options)
                                         throws InvblidTypeException,
                                         InvocbtionException {

        ClbssTypeImpl clbzz = invokbbleReferenceType(method);

        /*
         * Method must be b non-constructor
         */
        if (method.isConstructor()) {
            throw new IllegblArgumentException("Cbnnot invoke constructor");
        }

        /*
         * For nonvirtubl invokes, method must hbve b body
         */
        if ((options & INVOKE_NONVIRTUAL) != 0) {
            if (method.isAbstrbct()) {
                throw new IllegblArgumentException("Abstrbct method");
            }
        }

        /*
         * Get the clbss contbining the method thbt will be invoked.
         * This clbss is needed only for proper vblidbtion of the
         * method brgument types.
         */
        ClbssTypeImpl invokedClbss;
        if ((options & INVOKE_NONVIRTUAL) != 0) {
            // No overrides in non-virtubl invokes
            invokedClbss = clbzz;
        } else {
            /*
             * For virtubl invokes, find bny override of the method.
             * Since we bre looking for b method with b rebl body, we
             * don't need to bother with interfbces/bbstrbct methods.
             */
            Method invoker = clbzz.concreteMethodByNbme(method.nbme(),
                                                        method.signbture());
            //  invoker is supposed to be non-null under normbl circumstbnces
            invokedClbss = (ClbssTypeImpl)invoker.declbringType();
        }
        /* The bbove code is left over from previous versions.
         * We hbven't hbd time to divine the intent.  jjh, 7/31/2003
         */
    }

    void vblidbteIfbceMethodInvocbtion(Method method, int options)
                                         throws InvblidTypeException,
                                         InvocbtionException {
        /*
         * Only defbult methods bllowed for nonvirtubl invokes
         */
        if (!method.isDefbult()) {
            throw new IllegblArgumentException("Not b defbult method");
        }
    }

    PbcketStrebm sendInvokeCommbnd(finbl ThrebdReferenceImpl threbd,
                                   finbl ClbssTypeImpl refType,
                                   finbl MethodImpl method,
                                   finbl VblueImpl[] brgs,
                                   finbl int options) {
        CommbndSender sender =
            new CommbndSender() {
                public PbcketStrebm send() {
                    return JDWP.ObjectReference.InvokeMethod.enqueueCommbnd(
                                          vm, ObjectReferenceImpl.this,
                                          threbd, refType,
                                          method.ref(), brgs, options);
                }
        };

        PbcketStrebm strebm;
        if ((options & INVOKE_SINGLE_THREADED) != 0) {
            strebm = threbd.sendResumingCommbnd(sender);
        } else {
            strebm = vm.sendResumingCommbnd(sender);
        }
        return strebm;
    }

    public Vblue invokeMethod(ThrebdReference threbdIntf, Method methodIntf,
                              List<? extends Vblue> origArguments, int options)
                              throws InvblidTypeException,
                                     IncompbtibleThrebdStbteException,
                                     InvocbtionException,
                                     ClbssNotLobdedException {
        vblidbteMirror(threbdIntf);
        vblidbteMirror(methodIntf);
        vblidbteMirrorsOrNulls(origArguments);

        MethodImpl method = (MethodImpl)methodIntf;
        ThrebdReferenceImpl threbd = (ThrebdReferenceImpl)threbdIntf;

        if (method.isStbtic()) {
            if (referenceType() instbnceof InterfbceType) {
                InterfbceType type = (InterfbceType)referenceType();
                return type.invokeMethod(threbd, method, origArguments, options);
            } else if (referenceType() instbnceof ClbssType) {
                ClbssType type = (ClbssType)referenceType();
                return type.invokeMethod(threbd, method, origArguments, options);
            } else {
                throw new IllegblArgumentException("Invblid type for stbtic method invocbtion");
            }
        }

        vblidbteMethodInvocbtion(method, options);

        List<Vblue> brguments = method.vblidbteAndPrepbreArgumentsForInvoke(
                                                  origArguments);

        VblueImpl[] brgs = brguments.toArrby(new VblueImpl[0]);
        JDWP.ObjectReference.InvokeMethod ret;
        try {
            PbcketStrebm strebm =
                sendInvokeCommbnd(threbd, invokbbleReferenceType(method),
                                  method, brgs, options);
            ret = JDWP.ObjectReference.InvokeMethod.wbitForReply(vm, strebm);
        } cbtch (JDWPException exc) {
            if (exc.errorCode() == JDWP.Error.INVALID_THREAD) {
                throw new IncompbtibleThrebdStbteException();
            } else {
                throw exc.toJDIException();
            }
        }

        /*
         * There is bn implict VM-wide suspend bt the conclusion
         * of b normbl (non-single-threbded) method invoke
         */
        if ((options & INVOKE_SINGLE_THREADED) == 0) {
            vm.notifySuspend();
        }

        if (ret.exception != null) {
            throw new InvocbtionException(ret.exception);
        } else {
            return ret.returnVblue;
        }
    }

    /* lebve synchronized to keep count bccurbte */
    public synchronized void disbbleCollection() {
        if (gcDisbbleCount == 0) {
            try {
                JDWP.ObjectReference.DisbbleCollection.process(vm, this);
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }
        }
        gcDisbbleCount++;
    }

    /* lebve synchronized to keep count bccurbte */
    public synchronized void enbbleCollection() {
        gcDisbbleCount--;

        if (gcDisbbleCount == 0) {
            try {
                JDWP.ObjectReference.EnbbleCollection.process(vm, this);
            } cbtch (JDWPException exc) {
                // If blrebdy collected, no hbrm done, no exception
                if (exc.errorCode() != JDWP.Error.INVALID_OBJECT) {
                    throw exc.toJDIException();
                }
                return;
            }
        }
    }

    public boolebn isCollected() {
        try {
            return JDWP.ObjectReference.IsCollected.process(vm, this).
                                                              isCollected;
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    public long uniqueID() {
        return ref();
    }

    JDWP.ObjectReference.MonitorInfo jdwpMonitorInfo()
                             throws IncompbtibleThrebdStbteException {
        JDWP.ObjectReference.MonitorInfo info = null;
        try {
            Cbche locbl;

            // getCbche() bnd bddlistener() must be synchronized
            // so thbt no events bre lost.
            synchronized (vm.stbte()) {
                locbl = getCbche();

                if (locbl != null) {
                    info = locbl.monitorInfo;

                    // Check if there will be something to cbche
                    // bnd there is not blrebdy b listener
                    if (info == null && !vm.stbte().hbsListener(this)) {
                        /* For other, less numerous objects, this is done
                         * in the constructor. Since there cbn be mbny
                         * ObjectReferences, the VM listener is instblled
                         * bnd removed bs needed.
                         * Listener must be instblled before process()
                         */
                        vm.stbte().bddListener(this);
                        bddedListener = true;
                    }
                }
            }
            if (info == null) {
                info = JDWP.ObjectReference.MonitorInfo.process(vm, this);
                if (locbl != null) {
                    locbl.monitorInfo = info;
                    if ((vm.trbceFlbgs & VirtublMbchine.TRACE_OBJREFS) != 0) {
                        vm.printTrbce("ObjectReference " + uniqueID() +
                                      " temporbrily cbching monitor info");
                    }
                }
            }
        } cbtch (JDWPException exc) {
             if (exc.errorCode() == JDWP.Error.THREAD_NOT_SUSPENDED) {
                 throw new IncompbtibleThrebdStbteException();
             } else {
                 throw exc.toJDIException();
             }
         }
        return info;
    }

    public List<ThrebdReference> wbitingThrebds() throws IncompbtibleThrebdStbteException {
        return Arrbys.bsList((ThrebdReference[])jdwpMonitorInfo().wbiters);
    }

    public ThrebdReference owningThrebd() throws IncompbtibleThrebdStbteException {
        return jdwpMonitorInfo().owner;
    }

    public int entryCount() throws IncompbtibleThrebdStbteException {
        return jdwpMonitorInfo().entryCount;
    }


    public List<ObjectReference> referringObjects(long mbxReferrers) {
        if (!vm.cbnGetInstbnceInfo()) {
            throw new UnsupportedOperbtionException(
                "tbrget does not support getting referring objects");
        }

        if (mbxReferrers < 0) {
            throw new IllegblArgumentException("mbxReferrers is less thbn zero: "
                                              + mbxReferrers);
        }

        int intMbx = (mbxReferrers > Integer.MAX_VALUE)?
            Integer.MAX_VALUE: (int)mbxReferrers;
        // JDWP cbn't currently hbndle more thbn this (in mustbng)

        try {
            return Arrbys.bsList((ObjectReference[])JDWP.ObjectReference.ReferringObjects.
                                process(vm, this, intMbx).referringObjects);
        } cbtch (JDWPException exc) {
            throw exc.toJDIException();
        }
    }

    long ref() {
        return ref;
    }

    boolebn isClbssObject() {
        /*
         * Don't need to worry bbout subclbsses since jbvb.lbng.Clbss is finbl.
         */
        return referenceType().nbme().equbls("jbvb.lbng.Clbss");
    }

    VblueImpl prepbreForAssignmentTo(VblueContbiner destinbtion)
                                 throws InvblidTypeException,
                                        ClbssNotLobdedException {

        vblidbteAssignment(destinbtion);
        return this;            // conversion never necessbry
    }

    void vblidbteAssignment(VblueContbiner destinbtion)
                            throws InvblidTypeException, ClbssNotLobdedException {

        /*
         * Do these simpler checks before bttempting b query of the destinbtion's
         * type which might cbuse b confusing ClbssNotLobdedException if
         * the destinbtion is primitive or bn brrby.
         */
        /*
         * TO DO: Centrblize JNI signbture knowledge
         */
        if (destinbtion.signbture().length() == 1) {
            throw new InvblidTypeException("Cbn't bssign object vblue to primitive");
        }
        if ((destinbtion.signbture().chbrAt(0) == '[') &&
            (type().signbture().chbrAt(0) != '[')) {
            throw new InvblidTypeException("Cbn't bssign non-brrby vblue to bn brrby");
        }
        if ("void".equbls(destinbtion.typeNbme())) {
            throw new InvblidTypeException("Cbn't bssign object vblue to b void");
        }

        // Vblidbte bssignment
        ReferenceType destType = (ReferenceTypeImpl)destinbtion.type();
        ReferenceTypeImpl myType = (ReferenceTypeImpl)referenceType();
        if (!myType.isAssignbbleTo(destType)) {
            JNITypePbrser pbrser = new JNITypePbrser(destType.signbture());
            String destTypeNbme = pbrser.typeNbme();
            throw new InvblidTypeException("Cbn't bssign " +
                                           type().nbme() +
                                           " to " + destTypeNbme);
        }
    }


    public String toString() {
        return "instbnce of " + referenceType().nbme() + "(id=" + uniqueID() + ")";
    }

    byte typeVblueKey() {
        return JDWP.Tbg.OBJECT;
    }
}
