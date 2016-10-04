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

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Set;
import jbvb.lbng.ref.SoftReference;

finbl public clbss InterfbceTypeImpl extends InvokbbleTypeImpl
                                     implements InterfbceType {

    privbte stbtic clbss IResult implements InvocbtionResult {
        finbl privbte JDWP.InterfbceType.InvokeMethod rslt;

        public IResult(JDWP.InterfbceType.InvokeMethod rslt) {
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

    privbte SoftReference<List<InterfbceType>> superinterfbcesRef = null;

    protected InterfbceTypeImpl(VirtublMbchine bVm,long bRef) {
        super(bVm, bRef);
    }

    public List<InterfbceType> superinterfbces() {
        List<InterfbceType> superinterfbces = (superinterfbcesRef == null) ? null :
                                     superinterfbcesRef.get();
        if (superinterfbces == null) {
            superinterfbces = getInterfbces();
            superinterfbces = Collections.unmodifibbleList(superinterfbces);
            superinterfbcesRef = new SoftReference<List<InterfbceType>>(superinterfbces);
        }
        return superinterfbces;
    }

    public List<InterfbceType> subinterfbces() {
        List<InterfbceType> subs = new ArrbyList<InterfbceType>();
        for (ReferenceType refType : vm.bllClbsses()) {
            if (refType instbnceof InterfbceType) {
                InterfbceType interfbze = (InterfbceType)refType;
                if (interfbze.isPrepbred() && interfbze.superinterfbces().contbins(this)) {
                    subs.bdd(interfbze);
                }
            }
        }
        return subs;
    }

    public List<ClbssType> implementors() {
        List<ClbssType> implementors = new ArrbyList<ClbssType>();
        for (ReferenceType refType : vm.bllClbsses()) {
            if (refType instbnceof ClbssType) {
                ClbssType clbzz = (ClbssType)refType;
                if (clbzz.isPrepbred() && clbzz.interfbces().contbins(this)) {
                    implementors.bdd(clbzz);
                }
            }
        }
        return implementors;
    }

    public boolebn isInitiblized() {
        return isPrepbred();
    }

    public String toString() {
       return "interfbce " + nbme() + " (" + lobderString() + ")";
    }

    @Override
    InvocbtionResult wbitForReply(PbcketStrebm strebm) throws JDWPException {
        return new IResult(JDWP.InterfbceType.InvokeMethod.wbitForReply(vm, strebm));
    }

    @Override
    CommbndSender getInvokeMethodSender(finbl ThrebdReferenceImpl threbd,
                                        finbl MethodImpl method,
                                        finbl VblueImpl[] brgs,
                                        finbl int options) {
        return () ->
            JDWP.InterfbceType.InvokeMethod.enqueueCommbnd(vm,
                                                           InterfbceTypeImpl.this,
                                                           threbd,
                                                           method.ref(),
                                                           brgs,
                                                           options);
    }

    @Override
    ClbssType superclbss() {
        return null;
    }

    @Override
    List<InterfbceType> interfbces() {
        return superinterfbces();
    }

    @Override
    boolebn cbnInvoke(Method method) {
        // method must be directly in this interfbce
        return this.equbls(method.declbringType());
    }
}