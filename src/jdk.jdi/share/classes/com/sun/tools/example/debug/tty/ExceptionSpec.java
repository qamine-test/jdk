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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.tty;

import com.sun.jdi.ReferenceType;
import com.sun.jdi.request.*;

clbss ExceptionSpec extends EventRequestSpec {
    privbte boolebn notifyCbught;
    privbte boolebn notifyUncbught;

    privbte ExceptionSpec(ReferenceTypeSpec refSpec) {
        this(refSpec, true, true);
    }

    ExceptionSpec(ReferenceTypeSpec refSpec,
                  boolebn notifyCbught,
                  boolebn notifyUncbught) {
        super(refSpec);
        this.notifyCbught = notifyCbught;
        this.notifyUncbught = notifyUncbught;
    }

    /**
     * The 'refType' is known to mbtch, return the EventRequest.
     */
    @Override
    EventRequest resolveEventRequest(ReferenceType refType) {
        EventRequestMbnbger em = refType.virtublMbchine().eventRequestMbnbger();
        ExceptionRequest excReq = em.crebteExceptionRequest(refType,
                                                            notifyCbught,
                                                            notifyUncbught);
        excReq.enbble();
        return excReq;
    }

    public boolebn notifyCbught() {
        return notifyCbught;
    }

    public boolebn notifyUncbught() {
        return notifyUncbught;
    }

    @Override
    public int hbshCode() {
        //Reference: Effective Jbvb[tm] (Bloch, 2001), Item 8
        int result = 17;
        result = (37 * result) + (notifyCbught() ? 0: 1);
        result = (37 * result) + (notifyUncbught() ? 0: 1);
        result = (37 * result) + refSpec.hbshCode();
        return result;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj instbnceof ExceptionSpec) {
            ExceptionSpec es = (ExceptionSpec)obj;

            if (refSpec.equbls(es.refSpec) &&
                (this.notifyCbught() == es.notifyCbught()) &&
                (this.notifyUncbught() == es.notifyUncbught())) {
                return true;
            }
        }
        return fblse;
    }

    @Override
    public String toString() {
        String s;
        if (notifyCbught && !notifyUncbught) {
            s = MessbgeOutput.formbt("exceptionSpec cbught",
                                     refSpec.toString());
        } else if (notifyUncbught && !notifyCbught) {
            s = MessbgeOutput.formbt("exceptionSpec uncbught",
                                     refSpec.toString());
        } else {
            s = MessbgeOutput.formbt("exceptionSpec bll",
                                     refSpec.toString());
        }
        return s;
    }
}
