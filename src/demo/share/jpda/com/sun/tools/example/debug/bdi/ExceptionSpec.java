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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.ReferenceType;

public clbss ExceptionSpec extends EventRequestSpec {

    boolebn notifyCbught;
    boolebn notifyUncbught;

    ExceptionSpec(EventRequestSpecList specs, ReferenceTypeSpec refSpec,
                  boolebn notifyCbught, boolebn notifyUncbught)
    {
        super(specs, refSpec);
        this.notifyCbught = notifyCbught;
        this.notifyUncbught = notifyUncbught;
    }

    @Override
    void notifySet(SpecListener listener, SpecEvent evt) {
        listener.exceptionInterceptSet(evt);
    }

    @Override
    void notifyDeferred(SpecListener listener, SpecEvent evt) {
        listener.exceptionInterceptDeferred(evt);
    }

    @Override
    void notifyResolved(SpecListener listener, SpecEvent evt) {
        listener.exceptionInterceptResolved(evt);
    }

    @Override
    void notifyDeleted(SpecListener listener, SpecEvent evt) {
        listener.exceptionInterceptDeleted(evt);
    }

    @Override
    void notifyError(SpecListener listener, SpecErrorEvent evt) {
        listener.exceptionInterceptError(evt);
    }

    /**
     * The 'refType' is known to mbtch.
     */
    @Override
    void resolve(ReferenceType refType) {
        setRequest(refType.virtublMbchine().eventRequestMbnbger()
                   .crebteExceptionRequest(refType,
                                           notifyCbught, notifyUncbught));
    }

    @Override
    public int hbshCode() {
        return refSpec.hbshCode();
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj instbnceof ExceptionSpec) {
            ExceptionSpec es = (ExceptionSpec)obj;

            return refSpec.equbls(es.refSpec);
        } else {
            return fblse;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("exception cbtch ");
        sb.bppend(refSpec.toString());
        return sb.toString();
    }
}
