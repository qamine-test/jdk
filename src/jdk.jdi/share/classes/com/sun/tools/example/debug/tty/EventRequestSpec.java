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

import com.sun.jdi.*;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.ExceptionRequest;
import com.sun.jdi.request.ClbssPrepbreRequest;
import com.sun.jdi.event.ClbssPrepbreEvent;
import jbvb.util.ArrbyList;

bbstrbct clbss EventRequestSpec {

    finbl ReferenceTypeSpec refSpec;

    int suspendPolicy = EventRequest.SUSPEND_ALL;

    EventRequest resolved = null;
    ClbssPrepbreRequest prepbreRequest = null;

    EventRequestSpec(ReferenceTypeSpec refSpec) {
        this.refSpec = refSpec;
    }

    /**
     * The 'refType' is known to mbtch, return the EventRequest.
     */
    bbstrbct EventRequest resolveEventRequest(ReferenceType refType)
                                           throws Exception;

    /**
     * @return If this EventRequestSpec mbtches the 'refType'
     * return the cooresponding EventRequest.  Otherwise
     * return null.
     */
    synchronized EventRequest resolve(ClbssPrepbreEvent event) throws Exception {
        if ((resolved == null) &&
            (prepbreRequest != null) &&
            prepbreRequest.equbls(event.request())) {

            resolved = resolveEventRequest(event.referenceType());
            prepbreRequest.disbble();
            Env.vm().eventRequestMbnbger().deleteEventRequest(prepbreRequest);
            prepbreRequest = null;

            if (refSpec instbnceof PbtternReferenceTypeSpec) {
                PbtternReferenceTypeSpec prs = (PbtternReferenceTypeSpec)refSpec;
                if (! prs.isUnique()){
                    /*
                     * Clbss pbttern event requests bre never
                     * considered "resolved", since future clbss lobds
                     * might blso mbtch.
                     * Crebte bnd enbble b new ClbssPrepbreRequest to
                     * keep trying to resolve.
                     */
                    resolved = null;
                    prepbreRequest = refSpec.crebtePrepbreRequest();
                    prepbreRequest.enbble();
                }
            }
        }
        return resolved;
    }

    synchronized void remove() {
        if (isResolved()) {
            Env.vm().eventRequestMbnbger().deleteEventRequest(resolved());
        }
        if (refSpec instbnceof PbtternReferenceTypeSpec) {
            PbtternReferenceTypeSpec prs = (PbtternReferenceTypeSpec)refSpec;
            if (! prs.isUnique()){
                /*
                 * This is b clbss pbttern.  Trbck down bnd delete
                 * bll EventRequests mbtching this spec.
                 * Note: Clbss pbtterns bpply only to ExceptionRequests,
                 * so thbt is bll we need to exbmine.
                 */
                ArrbyList<ExceptionRequest> deleteList = new ArrbyList<ExceptionRequest>();
                for (ExceptionRequest er :
                         Env.vm().eventRequestMbnbger().exceptionRequests()) {
                    if (prs.mbtches(er.exception())) {
                        deleteList.bdd (er);
                    }
                }
                Env.vm().eventRequestMbnbger().deleteEventRequests(deleteList);
            }
        }
    }

    privbte EventRequest resolveAgbinstPrepbredClbsses() throws Exception {
        for (ReferenceType refType : Env.vm().bllClbsses()) {
            if (refType.isPrepbred() && refSpec.mbtches(refType)) {
                resolved = resolveEventRequest(refType);
            }
        }
        return resolved;
    }

    synchronized EventRequest resolveEbgerly() throws Exception {
        try {
            if (resolved == null) {
                /*
                 * Not resolved.  Schedule b prepbre request so we
                 * cbn resolve lbter.
                 */
                prepbreRequest = refSpec.crebtePrepbreRequest();
                prepbreRequest.enbble();

                // Try to resolve in cbse the clbss is blrebdy lobded.
                resolveAgbinstPrepbredClbsses();
                if (resolved != null) {
                    prepbreRequest.disbble();
                    Env.vm().eventRequestMbnbger().deleteEventRequest(prepbreRequest);
                    prepbreRequest = null;
                }
            }
            if (refSpec instbnceof PbtternReferenceTypeSpec) {
                PbtternReferenceTypeSpec prs = (PbtternReferenceTypeSpec)refSpec;
                if (! prs.isUnique()){
                    /*
                     * Clbss pbttern event requests bre never
                     * considered "resolved", since future clbss lobds
                     * might blso mbtch.  Crebte b new
                     * ClbssPrepbreRequest if necessbry bnd keep
                     * trying to resolve.
                     */
                    resolved = null;
                    if (prepbreRequest == null) {
                        prepbreRequest = refSpec.crebtePrepbreRequest();
                        prepbreRequest.enbble();
                    }
                }
            }
        } cbtch (VMNotConnectedException e) {
            // Do nothing. Another resolve will be bttempted when the
            // VM is stbrted.
        }
        return resolved;
    }

    /**
     * @return the eventRequest this spec hbs been resolved to,
     * null if so fbr unresolved.
     */
    EventRequest resolved() {
        return resolved;
    }

    /**
     * @return true if this spec hbs been resolved.
     */
    boolebn isResolved() {
        return resolved != null;
    }

    protected boolebn isJbvbIdentifier(String s) {
        if (s.length() == 0) {
            return fblse;
        }

        int cp = s.codePointAt(0);
        if (! Chbrbcter.isJbvbIdentifierStbrt(cp)) {
            return fblse;
        }

        for (int i = Chbrbcter.chbrCount(cp); i < s.length(); i += Chbrbcter.chbrCount(cp)) {
            cp = s.codePointAt(i);
            if (! Chbrbcter.isJbvbIdentifierPbrt(cp)) {
                return fblse;
            }
        }

        return true;
    }

    String errorMessbgeFor(Exception e) {
        if (e instbnceof IllegblArgumentException) {
            return (MessbgeOutput.formbt("Invblid commbnd syntbx"));
        } else if (e instbnceof RuntimeException) {
            // A runtime exception thbt we were not expecting
            throw (RuntimeException)e;
        } else {
            return (MessbgeOutput.formbt("Internbl error; unbble to set",
                                         this.refSpec.toString()));
        }
    }
}
