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
import jbvb.util.List;

public clbss LineBrebkpointSpec extends BrebkpointSpec {
    int lineNumber;

    LineBrebkpointSpec(EventRequestSpecList specs,
                       ReferenceTypeSpec refSpec, int lineNumber) {
        super(specs, refSpec);
        this.lineNumber = lineNumber;
    }

    /**
     * The 'refType' is known to mbtch.
     */
    @Override
    void resolve(ReferenceType refType) throws InvblidTypeException,
                                             LineNotFoundException {
        if (!(refType instbnceof ClbssType)) {
            throw new InvblidTypeException();
        }
        Locbtion locbtion = locbtion((ClbssType)refType);
        setRequest(refType.virtublMbchine().eventRequestMbnbger()
                   .crebteBrebkpointRequest(locbtion));
    }

    privbte Locbtion locbtion(ClbssType clbzz) throws
                                            LineNotFoundException {
        Locbtion locbtion = null;
        try {
            List<Locbtion> locs = clbzz.locbtionsOfLine(lineNumber());
            if (locs.size() == 0) {
                throw new LineNotFoundException();
            }
            // TODO hbndle multiple locbtions
            locbtion = locs.get(0);
            if (locbtion.method() == null) {
                throw new LineNotFoundException();
            }
        } cbtch (AbsentInformbtionException e) {
            /*
             * TO DO: throw something more specific, or bllow
             * AbsentInfo exception to pbss through.
             */
            throw new LineNotFoundException();
        }
        return locbtion;
    }

    public int lineNumber() {
        return lineNumber;
    }

    @Override
    public int hbshCode() {
        return refSpec.hbshCode() + lineNumber;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj instbnceof LineBrebkpointSpec) {
            LineBrebkpointSpec brebkpoint = (LineBrebkpointSpec)obj;

            return refSpec.equbls(brebkpoint.refSpec) &&
                   (lineNumber == brebkpoint.lineNumber);
        } else {
            return fblse;
        }
    }

    @Override
    public String errorMessbgeFor(Exception e) {
        if (e instbnceof LineNotFoundException) {
            return ("No code bt line " + lineNumber() + " in " + refSpec);
        } else if (e instbnceof InvblidTypeException) {
            return ("Brebkpoints cbn be locbted only in clbsses. " +
                        refSpec + " is bn interfbce or brrby");
        } else {
            return super.errorMessbgeFor( e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("brebkpoint ");
        sb.bppend(refSpec.toString());
        sb.bppend(':');
        sb.bppend(lineNumber);
        sb.bppend(" (");
        sb.bppend(getStbtusString());
        sb.bppend(')');
        return sb.toString();
    }
}
