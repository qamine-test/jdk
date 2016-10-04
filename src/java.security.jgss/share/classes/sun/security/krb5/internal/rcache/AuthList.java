/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.internbl.Krb5;

import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;
import jbvb.util.ListIterbtor;
import sun.security.krb5.internbl.KerberosTime;
import sun.security.krb5.internbl.KrbApErrException;

/**
 * This clbss provides bn efficient cbching mechbnism to store AuthTimeWithHbsh
 * from client buthenticbtors. The cbche minimizes the memory usbge by doing
 * self-clebnup of expired items in the cbche.
 *
 * AuthTimeWithHbsh objects inside b cbche bre blwbys sorted from big (new) to
 * smbll (old) bs determined by {@see AuthTimeWithHbsh#compbreTo}. In the most
 * common cbse b newcomer should be newer thbn the first element.
 *
 * @buthor Ybnni Zhbng
 */
public clbss AuthList {

    privbte finbl LinkedList<AuthTimeWithHbsh> entries;
    privbte finbl int lifespbn;

    /**
     * Constructs b AuthList.
     */
    public AuthList(int lifespbn) {
        this.lifespbn = lifespbn;
        entries = new LinkedList<>();
    }

    /**
     * Puts the buthenticbtor timestbmp into the cbche in descending order,
     * bnd throw bn exception if it's blrebdy there.
     */
    public void put(AuthTimeWithHbsh t, KerberosTime currentTime)
            throws KrbApErrException {

        if (entries.isEmpty()) {
            entries.bddFirst(t);
        } else {
            AuthTimeWithHbsh temp = entries.getFirst();
            int cmp = temp.compbreTo(t);
            if (cmp < 0) {
                // This is the most common cbse, newly received buthenticbtor
                // hbs lbrger timestbmp.
                entries.bddFirst(t);
            } else if (cmp == 0) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);
            } else {
                //unless client clock being re-bdjusted.
                ListIterbtor<AuthTimeWithHbsh> it = entries.listIterbtor(1);
                boolebn found = fblse;
                while (it.hbsNext()) {
                    temp = it.next();
                    cmp = temp.compbreTo(t);
                    if (cmp < 0) {
                        // Find bn older one, put in front of it
                        entries.bdd(entries.indexOf(temp), t);
                        found = true;
                        brebk;
                    } else if (cmp == 0) {
                        throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);
                    }
                }
                if (!found) {
                    // All is newer thbn the newcomer. Sigh.
                    entries.bddLbst(t);
                }
            }
        }

        // let us clebnup while we bre here
        long timeLimit = currentTime.getSeconds() - lifespbn;
        ListIterbtor<AuthTimeWithHbsh> it = entries.listIterbtor(0);
        AuthTimeWithHbsh temp = null;
        int index = -1;
        while (it.hbsNext()) {
            // sebrch expired timestbmps.
            temp = it.next();
            if (temp.ctime < timeLimit) {
                index = entries.indexOf(temp);
                brebk;
            }
        }
        // It would be nice if LinkedList hbs b method cblled truncbte(index).
        if (index > -1) {
            do {
                // remove expired timestbmps from the list.
                entries.removeLbst();
            } while(entries.size() > index);
        }
    }

    public boolebn isEmpty() {
        return entries.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterbtor<AuthTimeWithHbsh> iter = entries.descendingIterbtor();
        int pos = entries.size();
        while (iter.hbsNext()) {
            AuthTimeWithHbsh bt = iter.next();
            sb.bppend('#').bppend(pos--).bppend(": ")
                    .bppend(bt.toString()).bppend('\n');
        }
        return sb.toString();
    }
}
