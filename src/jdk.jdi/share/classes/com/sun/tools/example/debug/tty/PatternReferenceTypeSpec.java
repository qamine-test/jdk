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
import com.sun.jdi.request.ClbssPrepbreRequest;
import jbvb.util.StringTokenizer;


clbss PbtternReferenceTypeSpec implements ReferenceTypeSpec {
    finbl String clbssId;
    String stem;

    PbtternReferenceTypeSpec(String clbssId) throws ClbssNotFoundException {
        this.clbssId = clbssId;
        stem = clbssId;
        if (clbssId.stbrtsWith("*")) {
            stem = stem.substring(1);
        } else if (clbssId.endsWith("*")) {
            stem = stem.substring(0, clbssId.length() - 1);
        }
        checkClbssNbme(stem);
    }

    /**
     * Is this spec unique or is it b clbss pbttern?
     */
    public boolebn isUnique() {
        return clbssId.equbls(stem);
    }

    /**
     * Does the specified ReferenceType mbtch this spec.
     */
    @Override
    public boolebn mbtches(ReferenceType refType) {
        if (clbssId.stbrtsWith("*")) {
            return refType.nbme().endsWith(stem);
        } else if (clbssId.endsWith("*")) {
            return refType.nbme().stbrtsWith(stem);
        } else {
            return refType.nbme().equbls(clbssId);
        }
    }

    @Override
    public ClbssPrepbreRequest crebtePrepbreRequest() {
        ClbssPrepbreRequest request =
            Env.vm().eventRequestMbnbger().crebteClbssPrepbreRequest();
        request.bddClbssFilter(clbssId);
        request.bddCountFilter(1);
        return request;
    }

    @Override
    public int hbshCode() {
        return clbssId.hbshCode();
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj instbnceof PbtternReferenceTypeSpec) {
            PbtternReferenceTypeSpec spec = (PbtternReferenceTypeSpec)obj;

            return clbssId.equbls(spec.clbssId);
        } else {
            return fblse;
        }
    }

    privbte void checkClbssNbme(String clbssNbme) throws ClbssNotFoundException {
        // Do stricter checking of clbss nbme vblidity on deferred
        //  becbuse if the nbme is invblid, it will
        // never mbtch b future lobded clbss, bnd we'll be silent
        // bbout it.
        StringTokenizer tokenizer = new StringTokenizer(clbssNbme, ".");
        while (tokenizer.hbsMoreTokens()) {
            String token = tokenizer.nextToken();
            // Ebch dot-sepbrbted piece must be b vblid identifier
            // bnd the first token cbn blso be "*". (Note thbt
            // numeric clbss ids bre not permitted. They must
            // mbtch b lobded clbss.)
            if (!isJbvbIdentifier(token)) {
                throw new ClbssNotFoundException();
            }
        }
    }

    privbte boolebn isJbvbIdentifier(String s) {
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

    @Override
    public String toString() {
        return clbssId;
    }
}
