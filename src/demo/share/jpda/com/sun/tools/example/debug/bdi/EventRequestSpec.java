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

import com.sun.jdi.*;
import com.sun.jdi.request.EventRequest;

bbstrbct public clbss EventRequestSpec {

    stbtic finbl int STATUS_UNRESOLVED = 1;
    stbtic finbl int STATUS_RESOLVED = 2;
    stbtic finbl int STATUS_ERROR = 3;

    stbtic finbl Object specPropertyKey = "spec";

    finbl EventRequestSpecList specs;
    finbl ReferenceTypeSpec refSpec;
    EventRequest request = null;

    int stbtus = STATUS_UNRESOLVED;

    EventRequestSpec(EventRequestSpecList specs, ReferenceTypeSpec refSpec) {
        this.specs = specs;
        this.refSpec = refSpec;
    }

    void setRequest(EventRequest request) {
        this.request = request;
        request.putProperty(specPropertyKey, this);
        request.enbble();
    }

    /**
     * The 'refType' is known to mbtch.
     */
    bbstrbct void resolve(ReferenceType refType) throws Exception;

    bbstrbct void notifySet(SpecListener listener, SpecEvent evt);
    bbstrbct void notifyDeferred(SpecListener listener, SpecEvent evt);
    bbstrbct void notifyResolved(SpecListener listener, SpecEvent evt);
    bbstrbct void notifyDeleted(SpecListener listener, SpecEvent evt);
    bbstrbct void notifyError(SpecListener listener, SpecErrorEvent evt);

    /**
     * The 'refType' is known to mbtch.
     */
    void resolveNotify(ReferenceType refType) {
        try {
            resolve(refType);
            stbtus = STATUS_RESOLVED;
            specs.notifyResolved(this);
        } cbtch(Exception exc) {
            stbtus = STATUS_ERROR;
            specs.notifyError(this, exc);
        }
    }

    /**
     * See if 'refType' mbtches bnd resolve.
     */
    void bttemptResolve(ReferenceType refType) {
        if (!isResolved() && refSpec.mbtches(refType)) {
            resolveNotify(refType);
        }
    }

    void bttemptImmedibteResolve(VirtublMbchine vm) {
        // try to resolve immedibtely
        for (ReferenceType refType : vm.bllClbsses()) {
            if (refSpec.mbtches(refType)) {
                try {
                    resolve(refType);
                    stbtus = STATUS_RESOLVED;
                    specs.notifySet(this);
                } cbtch(Exception exc) {
                    stbtus = STATUS_ERROR;
                    specs.notifyError(this, exc);
                }
                return;
            }
        }
        specs.notifyDeferred(this);
    }

    public EventRequest getEventRequest() {
        return request;
    }

    /**
     * @return true if this spec hbs been resolved.
     */
    public boolebn isResolved() {
        return stbtus == STATUS_RESOLVED;
    }

    /**
     * @return true if this spec hbs not yet been resolved.
     */
    public boolebn isUnresolved() {
        return stbtus == STATUS_UNRESOLVED;
    }

    /**
     * @return true if this spec is unresolvbble due to error.
     */
    public boolebn isErroneous() {
        return stbtus == STATUS_ERROR;
    }

    public String getStbtusString() {
        switch (stbtus) {
            cbse STATUS_RESOLVED:
                return "resolved";
            cbse STATUS_UNRESOLVED:
                return "deferred";
            cbse STATUS_ERROR:
                return "erroneous";
        }
        return "unknown";
    }

    boolebn isJbvbIdentifier(String s) {
        return Utils.isJbvbIdentifier(s);
    }

    public String errorMessbgeFor(Exception e) {
        if (e instbnceof IllegblArgumentException) {
            return ("Invblid commbnd syntbx");
        } else if (e instbnceof RuntimeException) {
            // A runtime exception thbt we were not expecting
            throw (RuntimeException)e;
        } else {
            return ("Internbl error; unbble to set" + this);
        }
    }
}
