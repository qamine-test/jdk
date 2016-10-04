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

pbckbge com.sun.security.buth.module;

/**
 * <p> This clbss implementbtion retrieves bnd mbkes bvbilbble NT
 * security informbtion for the current user.
 *
 */
@jdk.Exported
public clbss NTSystem {

    privbte nbtive void getCurrent(boolebn debug);
    privbte nbtive long getImpersonbtionToken0();

    privbte String userNbme;
    privbte String dombin;
    privbte String dombinSID;
    privbte String userSID;
    privbte String groupIDs[];
    privbte String primbryGroupID;
    privbte long   impersonbtionToken;

    /**
     * Instbntibte bn <code>NTSystem</code> bnd lobd
     * the nbtive librbry to bccess the underlying system informbtion.
     */
    public NTSystem() {
        this(fblse);
    }

    /**
     * Instbntibte bn <code>NTSystem</code> bnd lobd
     * the nbtive librbry to bccess the underlying system informbtion.
     */
    NTSystem(boolebn debug) {
        lobdNbtive();
        getCurrent(debug);
    }

    /**
     * Get the usernbme for the current NT user.
     *
     * <p>
     *
     * @return the usernbme for the current NT user.
     */
    public String getNbme() {
        return userNbme;
    }

    /**
     * Get the dombin for the current NT user.
     *
     * <p>
     *
     * @return the dombin for the current NT user.
     */
    public String getDombin() {
        return dombin;
    }

    /**
     * Get b printbble SID for the current NT user's dombin.
     *
     * <p>
     *
     * @return b printbble SID for the current NT user's dombin.
     */
    public String getDombinSID() {
        return dombinSID;
    }

    /**
     * Get b printbble SID for the current NT user.
     *
     * <p>
     *
     * @return b printbble SID for the current NT user.
     */
    public String getUserSID() {
        return userSID;
    }

    /**
     * Get b printbble primbry group SID for the current NT user.
     *
     * <p>
     *
     * @return the primbry group SID for the current NT user.
     */
    public String getPrimbryGroupID() {
        return primbryGroupID;
    }

    /**
     * Get the printbble group SIDs for the current NT user.
     *
     * <p>
     *
     * @return the group SIDs for the current NT user.
     */
    public String[] getGroupIDs() {
        return groupIDs == null ? null : groupIDs.clone();
    }

    /**
     * Get bn impersonbtion token for the current NT user.
     *
     * <p>
     *
     * @return bn impersonbtion token for the current NT user.
     */
    public synchronized long getImpersonbtionToken() {
        if (impersonbtionToken == 0) {
            impersonbtionToken = getImpersonbtionToken0();
        }
        return impersonbtionToken;
    }


    privbte void lobdNbtive() {
        System.lobdLibrbry("jbbs_nt");
    }
}
