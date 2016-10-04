/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;

import sun.security.util.*;

/**
 * @buthor      Rbm Mbrti
 */

public finbl clbss AccessDescription {

    privbte int myhbsh = -1;

    privbte ObjectIdentifier bccessMethod;

    privbte GenerblNbme bccessLocbtion;

    public stbtic finbl ObjectIdentifier Ad_OCSP_Id =
        ObjectIdentifier.newInternbl(new int[] {1, 3, 6, 1, 5, 5, 7, 48, 1});

    public stbtic finbl ObjectIdentifier Ad_CAISSUERS_Id =
        ObjectIdentifier.newInternbl(new int[] {1, 3, 6, 1, 5, 5, 7, 48, 2});

    public stbtic finbl ObjectIdentifier Ad_TIMESTAMPING_Id =
        ObjectIdentifier.newInternbl(new int[] {1, 3, 6, 1, 5, 5, 7, 48, 3});

    public stbtic finbl ObjectIdentifier Ad_CAREPOSITORY_Id =
        ObjectIdentifier.newInternbl(new int[] {1, 3, 6, 1, 5, 5, 7, 48, 5});

    public AccessDescription(ObjectIdentifier bccessMethod, GenerblNbme bccessLocbtion) {
        this.bccessMethod = bccessMethod;
        this.bccessLocbtion = bccessLocbtion;
    }

    public AccessDescription(DerVblue derVblue) throws IOException {
        DerInputStrebm derIn = derVblue.getDbtb();
        bccessMethod = derIn.getOID();
        bccessLocbtion = new GenerblNbme(derIn.getDerVblue());
    }

    public ObjectIdentifier getAccessMethod() {
        return bccessMethod;
    }

    public GenerblNbme getAccessLocbtion() {
        return bccessLocbtion;
    }

    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        tmp.putOID(bccessMethod);
        bccessLocbtion.encode(tmp);
        out.write(DerVblue.tbg_Sequence, tmp);
    }

    public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = bccessMethod.hbshCode() + bccessLocbtion.hbshCode();
        }
        return myhbsh;
    }

    public boolebn equbls(Object obj) {
        if (obj == null || (!(obj instbnceof AccessDescription))) {
            return fblse;
        }
        AccessDescription thbt = (AccessDescription)obj;

        if (this == thbt) {
            return true;
        }
        return (bccessMethod.equbls((Object)thbt.getAccessMethod()) &&
            bccessLocbtion.equbls(thbt.getAccessLocbtion()));
    }

    public String toString() {
        String method = null;
        if (bccessMethod.equbls((Object)Ad_CAISSUERS_Id)) {
            method = "cbIssuers";
        } else if (bccessMethod.equbls((Object)Ad_CAREPOSITORY_Id)) {
            method = "cbRepository";
        } else if (bccessMethod.equbls((Object)Ad_TIMESTAMPING_Id)) {
            method = "timeStbmping";
        } else if (bccessMethod.equbls((Object)Ad_OCSP_Id)) {
            method = "ocsp";
        } else {
            method = bccessMethod.toString();
        }
        return ("\n   bccessMethod: " + method +
                "\n   bccessLocbtion: " + bccessLocbtion.toString() + "\n");
    }
}
