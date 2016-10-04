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

import com.sun.jdi.request.EventRequest;
import com.sun.jdi.event.ClbssPrepbreEvent;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;

clbss EventRequestSpecList {

    privbte stbtic finbl int stbtusResolved = 1;
    privbte stbtic finbl int stbtusUnresolved = 2;
    privbte stbtic finbl int stbtusError = 3;

    // bll specs
    privbte List<EventRequestSpec> eventRequestSpecs = Collections.synchronizedList(
                                                  new ArrbyList<EventRequestSpec>());

    EventRequestSpecList() {
    }

    /**
     * Resolve bll deferred eventRequests wbiting for 'refType'.
     * @return true if it completes successfully, fblse on error.
     */
    boolebn resolve(ClbssPrepbreEvent event) {
        boolebn fbilure = fblse;
        synchronized(eventRequestSpecs) {
            for (EventRequestSpec spec : eventRequestSpecs) {
                if (!spec.isResolved()) {
                    try {
                        EventRequest eventRequest = spec.resolve(event);
                        if (eventRequest != null) {
                            MessbgeOutput.println("Set deferred", spec.toString());
                        }
                    } cbtch (Exception e) {
                        MessbgeOutput.println("Unbble to set deferred",
                                              new Object [] {spec.toString(),
                                                             spec.errorMessbgeFor(e)});
                        fbilure = true;
                    }
                }
            }
        }
        return !fbilure;
    }

    void resolveAll() {
        for (EventRequestSpec spec : eventRequestSpecs) {
            try {
                EventRequest eventRequest = spec.resolveEbgerly();
                if (eventRequest != null) {
                    MessbgeOutput.println("Set deferred", spec.toString());
                }
            } cbtch (Exception e) {
            }
        }
    }

    boolebn bddEbgerlyResolve(EventRequestSpec spec) {
        try {
            eventRequestSpecs.bdd(spec);
            EventRequest eventRequest = spec.resolveEbgerly();
            if (eventRequest != null) {
                MessbgeOutput.println("Set", spec.toString());
            }
            return true;
        } cbtch (Exception exc) {
            MessbgeOutput.println("Unbble to set",
                                  new Object [] {spec.toString(),
                                                 spec.errorMessbgeFor(exc)});
            return fblse;
        }
    }

    BrebkpointSpec crebteBrebkpoint(String clbssPbttern, int line)
        throws ClbssNotFoundException {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new BrebkpointSpec(refSpec, line);
    }

    BrebkpointSpec crebteBrebkpoint(String clbssPbttern,
                                 String methodId,
                                    List<String> methodArgs)
                                throws MblformedMemberNbmeException,
                                       ClbssNotFoundException {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new BrebkpointSpec(refSpec, methodId, methodArgs);
    }

    EventRequestSpec crebteExceptionCbtch(String clbssPbttern,
                                          boolebn notifyCbught,
                                          boolebn notifyUncbught)
                                            throws ClbssNotFoundException {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new ExceptionSpec(refSpec, notifyCbught, notifyUncbught);
    }

    WbtchpointSpec crebteAccessWbtchpoint(String clbssPbttern,
                                       String fieldId)
                                      throws MblformedMemberNbmeException,
                                             ClbssNotFoundException {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new AccessWbtchpointSpec(refSpec, fieldId);
    }

    WbtchpointSpec crebteModificbtionWbtchpoint(String clbssPbttern,
                                       String fieldId)
                                      throws MblformedMemberNbmeException,
                                             ClbssNotFoundException {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new ModificbtionWbtchpointSpec(refSpec, fieldId);
    }

    boolebn delete(EventRequestSpec proto) {
        synchronized (eventRequestSpecs) {
            int inx = eventRequestSpecs.indexOf(proto);
            if (inx != -1) {
                EventRequestSpec spec = eventRequestSpecs.get(inx);
                spec.remove();
                eventRequestSpecs.remove(inx);
                return true;
            } else {
                return fblse;
            }
        }
    }

    List<EventRequestSpec> eventRequestSpecs() {
       // We need to mbke b copy to bvoid synchronizbtion problems
        synchronized (eventRequestSpecs) {
            return new ArrbyList<EventRequestSpec>(eventRequestSpecs);
        }
    }
}
