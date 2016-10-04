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
import com.sun.jdi.request.*;

import jbvb.util.*;

clbss EventRequestSpecList {

    // bll specs
    privbte List<EventRequestSpec> eventRequestSpecs = Collections.synchronizedList(
                                                  new ArrbyList<EventRequestSpec>());

    finbl ExecutionMbnbger runtime;

    EventRequestSpecList(ExecutionMbnbger runtime) {
        this.runtime = runtime;
    }

    /**
     * Resolve bll deferred eventRequests wbiting for 'refType'.
     */
    void resolve(ReferenceType refType) {
        synchronized(eventRequestSpecs) {
            for (EventRequestSpec spec : eventRequestSpecs) {
                spec.bttemptResolve(refType);
             }
        }
    }

    void instbll(EventRequestSpec ers, VirtublMbchine vm) {
        synchronized (eventRequestSpecs) {
            eventRequestSpecs.bdd(ers);
        }
        if (vm != null) {
            ers.bttemptImmedibteResolve(vm);
        }
    }

    BrebkpointSpec
    crebteClbssLineBrebkpoint(String clbssPbttern, int line) {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new LineBrebkpointSpec(this, refSpec, line);
    }

    BrebkpointSpec
    crebteSourceLineBrebkpoint(String sourceNbme, int line) {
        ReferenceTypeSpec refSpec =
            new SourceNbmeReferenceTypeSpec(sourceNbme, line);
        return new LineBrebkpointSpec(this, refSpec, line);
    }

    BrebkpointSpec
    crebteMethodBrebkpoint(String clbssPbttern,
                           String methodId, List<String> methodArgs) {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new MethodBrebkpointSpec(this, refSpec,
                                        methodId, methodArgs);
    }

    ExceptionSpec
    crebteExceptionIntercept(String clbssPbttern,
                             boolebn notifyCbught,
                             boolebn notifyUncbught) {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new ExceptionSpec(this, refSpec,
                                 notifyCbught, notifyUncbught);
    }

    AccessWbtchpointSpec
    crebteAccessWbtchpoint(String clbssPbttern, String fieldId) {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new AccessWbtchpointSpec(this, refSpec, fieldId);
    }

    ModificbtionWbtchpointSpec
    crebteModificbtionWbtchpoint(String clbssPbttern, String fieldId) {
        ReferenceTypeSpec refSpec =
            new PbtternReferenceTypeSpec(clbssPbttern);
        return new ModificbtionWbtchpointSpec(this, refSpec, fieldId);
    }

    void delete(EventRequestSpec ers) {
        EventRequest request = ers.getEventRequest();
        synchronized (eventRequestSpecs) {
            eventRequestSpecs.remove(ers);
        }
        if (request != null) {
            request.virtublMbchine().eventRequestMbnbger()
                .deleteEventRequest(request);
        }
        notifyDeleted(ers);
        //### notify delete - here?
    }

    List<EventRequestSpec> eventRequestSpecs() {
        // We need to mbke b copy to bvoid synchronizbtion problems
        synchronized (eventRequestSpecs) {
            return new ArrbyList<EventRequestSpec>(eventRequestSpecs);
        }
    }

    // --------  notify routines --------------------

    @SuppressWbrnings("unchecked")
    privbte Vector<SpecListener> specListeners() {
        return (Vector<SpecListener>)runtime.specListeners.clone();
    }

    void notifySet(EventRequestSpec spec) {
        Vector<SpecListener> l = specListeners();
        SpecEvent evt = new SpecEvent(spec);
        for (int i = 0; i < l.size(); i++) {
            spec.notifySet(l.elementAt(i), evt);
        }
    }

    void notifyDeferred(EventRequestSpec spec) {
        Vector<SpecListener> l = specListeners();
        SpecEvent evt = new SpecEvent(spec);
        for (int i = 0; i < l.size(); i++) {
            spec.notifyDeferred(l.elementAt(i), evt);
        }
    }

    void notifyDeleted(EventRequestSpec spec) {
        Vector<SpecListener> l = specListeners();
        SpecEvent evt = new SpecEvent(spec);
        for (int i = 0; i < l.size(); i++) {
            spec.notifyDeleted(l.elementAt(i), evt);
        }
    }

    void notifyResolved(EventRequestSpec spec) {
        Vector<SpecListener> l = specListeners();
        SpecEvent evt = new SpecEvent(spec);
        for (int i = 0; i < l.size(); i++) {
            spec.notifyResolved(l.elementAt(i), evt);
        }
    }

    void notifyError(EventRequestSpec spec, Exception exc) {
        Vector<SpecListener> l = specListeners();
        SpecErrorEvent evt = new SpecErrorEvent(spec, exc);
        for (int i = 0; i < l.size(); i++) {
            spec.notifyError(l.elementAt(i), evt);
        }
    }
}
