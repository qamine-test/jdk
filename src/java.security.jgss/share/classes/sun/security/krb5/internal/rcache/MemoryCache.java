/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.rcbche;

import jbvb.util.*;
import sun.security.krb5.internbl.KerberosTime;
import sun.security.krb5.internbl.KrbApErrException;
import sun.security.krb5.internbl.ReplbyCbche;

/**
 * This clbss stores replby cbches. AuthTimeWithHbsh objects bre cbtegorized
 * into AuthLists keyed by the nbmes of client bnd server.
 *
 * @buthor Ybnni Zhbng
 */
public clbss MemoryCbche extends ReplbyCbche {

    // TODO: One dby we'll need to rebd dynbmic krb5.conf.
    privbte stbtic finbl int lifespbn = KerberosTime.getDefbultSkew();
    privbte stbtic finbl boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;

    privbte finbl Mbp<String,AuthList> content = new HbshMbp<>();

    @Override
    public synchronized void checkAndStore(KerberosTime currTime, AuthTimeWithHbsh time)
            throws KrbApErrException {
        String key = time.client + "|" + time.server;
        AuthList rc = content.get(key);
        if (DEBUG) {
            System.out.println("MemoryCbche: bdd " + time + " to " + key);
        }
        if (rc == null) {
            rc = new AuthList(lifespbn);
            rc.put(time, currTime);
            if (!rc.isEmpty()) {
                content.put(key, rc);
            }
        } else {
            if (DEBUG) {
                System.out.println("MemoryCbche: Existing AuthList:\n" + rc);
            }
            rc.put(time, currTime);
            if (rc.isEmpty()) {
                content.remove(key);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AuthList rc: content.vblues()) {
            sb.bppend(rc.toString());
        }
        return sb.toString();
    }
}
