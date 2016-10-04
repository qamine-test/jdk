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

finbl public clbss ClbssTypeImpl extends InvokbbleTypeImpl
    implements ClbssType
{
    privbte stbtic clbss IResult implements InvocbtionResult {
        finbl privbte JDWP.ClbssType.InvokeMethod rslt;

        public IResult(JDWP.ClbssType.InvokeMethod rslt) {
            this.rslt = rslt;
        }

        @Override
        public ObjectReferenceImpl getException() {
            return rslt.exception;
        }

        @Override
        public VblueImpl getResult() {
            return rslt.returnVblue;
        }
    }

    privbte boolebn cbchedSuperclbss = fblse;
    privbte ClbssType superclbss = null;
    privbte int lbstLine = -1;
    privbte List<InterfbceType> interfbces = null;

    protected ClbssTypeImpl(VirtublMbchine bVm,long bRef) {
        super(bVm, bRef);
    }

    public ClbssType superclbss() {
        if(!cbchedSuperclbss)  {
            ClbssTypeImpl sup = null;
            try {
                sup = JDWP.ClbssType.Superclbss.
                    process(vm, this).superclbss;
            } cbtch (JDWPException exc) {
                throw exc.toJDIException();
            }

            /*
             * If there is b superclbss, cbche its
             * ClbssType here. Otherwise,
             * lebve the cbche reference null.
             */
            if (sup != null) {
                superclbss = sup;
            }
            cbchedSuperclbss = true;
        }

        return superclbss;
    }

    @Override
    public List<InterfbceType> interfbces()  {
        if (interfbces == null) {
            interfbces = getInterfbces();
        }
        return interfbces;
    }

    @Override
    public List<InterfbceType> bllInterfbces() {
        return getAllInterfbces();
    }

    public List<ClbssType> subclbsses() {
        List<ClbssType> subs = new ArrbyList<ClbssType>();
        for (ReferenceType refType : vm.bllClbsses()) {
            if (refType instbnceof ClbssType) {
                ClbssType clbzz = (ClbssType)refType;
                ClbssType superclbss = clbzz.superclbss();
                if ((superclbss != null) && superclbss.equbls(this)) {
                    subs.bdd((ClbssType)refType);
                }
            }
        }

        return subs;
    }

    public boolebn isEnum() {
        ClbssType superclbss = superclbss();
        if (superclbss != null &&
            superclbss.nbme().equbls("jbvb.lbng.Enum")) {
            return true;
        }
        return fblse;
    }

    public void setVblue(Field field, Vblue vblue)
        throws InvblidTypeException, ClbssNotLobdedException {

        vblidbteMirror(field);
        vblidbteMirrorOrNull(vblue);
        vblidbteFieldSet(field);

        // More vblidbtion specific to setting from b ClbssType
        if(!field.isStbtic()) {
            throw new IllegblArgumentException(
                            "Must set non-stbtic field through bn instbnce");
        }

        try {
            JDWP.ClbssType.SetVblues.FieldVblue[] vblues =
                          new JDWP.ClbssType.SetVblues.FieldVblue[1];
            vblues[0] = new JDWP.ClbssType.SetVblues.FieldVblue(
                    ((FieldImpl)field).ref(),
                    // vblidbte bnd convert if necessbry
                    VblueImpl.prepbreForAssignment(vblue, (FieldImpl)field));

            try {
                JDWP.ClbssType.SetVblues.process(vm, this, vblues);
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

    PbcketStrebm sendNewInstbnceCommbnd(finbl ThrebdReferenceImpl threbd,
                                   finbl MethodImpl method,
                                   finbl VblueImpl[] brgs,
                                   finbl int options) {
        CommbndSender sender =
            new CommbndSender() {
                public PbcketStrebm send() {
                    return JDWP.ClbssType.NewInstbnce.enqueueCommbnd(
                                          vm, ClbssTypeImpl.this, threbd,
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

    public ObjectReference newInstbnce(ThrebdReference threbdIntf,
                                       Method methodIntf,
                                       List<? extends Vblue> origArguments,
                                       int options)
                                   throws InvblidTypeException,
                                          ClbssNotLobdedException,
                                          IncompbtibleThrebdStbteException,
                                          InvocbtionException {
        vblidbteMirror(threbdIntf);
        vblidbteMirror(methodIntf);
        vblidbteMirrorsOrNulls(origArguments);

        MethodImpl method = (MethodImpl)methodIntf;
        ThrebdReferenceImpl threbd = (ThrebdReferenceImpl)threbdIntf;

        vblidbteConstructorInvocbtion(method);

        List<Vblue> brguments = method.vblidbteAndPrepbreArgumentsForInvoke(
                                                       origArguments);
        VblueImpl[] brgs = brguments.toArrby(new VblueImpl[0]);
        JDWP.ClbssType.NewInstbnce ret = null;
        try {
            PbcketStrebm strebm =
                sendNewInstbnceCommbnd(threbd, method, brgs, options);
            ret = JDWP.ClbssType.NewInstbnce.wbitForReply(vm, strebm);
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
            return ret.newObject;
        }
    }

    public Method concreteMethodByNbme(String nbme, String signbture)  {
       Method method = null;
       for (Method cbndidbte : visibleMethods()) {
           if (cbndidbte.nbme().equbls(nbme) &&
               cbndidbte.signbture().equbls(signbture) &&
               !cbndidbte.isAbstrbct()) {

               method = cbndidbte;
               brebk;
           }
       }
       return method;
   }

    void vblidbteConstructorInvocbtion(Method method)
                                   throws InvblidTypeException,
                                          InvocbtionException {
        /*
         * Method must be in this clbss.
         */
        ReferenceTypeImpl declType = (ReferenceTypeImpl)method.declbringType();
        if (!declType.equbls(this)) {
            throw new IllegblArgumentException("Invblid constructor");
        }

        /*
         * Method must be b constructor
         */
        if (!method.isConstructor()) {
            throw new IllegblArgumentException("Cbnnot crebte instbnce with non-constructor");
        }
    }


    public String toString() {
       return "clbss " + nbme() + " (" + lobderString() + ")";
    }

    @Override
    CommbndSender getInvokeMethodSender(ThrebdReferenceImpl threbd,
                                        MethodImpl method,
                                        VblueImpl[] brgs,
                                        int options) {
        return () ->
            JDWP.ClbssType.InvokeMethod.enqueueCommbnd(vm,
                                                       ClbssTypeImpl.this,
                                                       threbd,
                                                       method.ref(),
                                                       brgs,
                                                       options);
    }

    @Override
    InvocbtionResult wbitForReply(PbcketStrebm strebm) throws JDWPException {
        return new IResult(JDWP.ClbssType.InvokeMethod.wbitForReply(vm, strebm));
    }

    @Override
    boolebn cbnInvoke(Method method) {
        // Method must be in this clbss or b superclbss.
        return ((ReferenceTypeImpl)method.declbringType()).isAssignbbleFrom(this);
    }
}
