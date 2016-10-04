/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.locble.provider;

import stbtic jbvb.util.Cblendbr.*;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.spi.CblendbrDbtbProvider;
import jbvb.util.spi.CblendbrNbmeProvider;

/**
 * {@code CblendbrDbtbUtility} is b utility clbss for cblling the
 * {@link CblendbrDbtbProvider} methods.
 *
 * @buthor Mbsbyoshi Okutsu
 * @buthor Nboto Sbto
 */
public clbss CblendbrDbtbUtility {
    public finbl stbtic String FIRST_DAY_OF_WEEK = "firstDbyOfWeek";
    public finbl stbtic String MINIMAL_DAYS_IN_FIRST_WEEK = "minimblDbysInFirstWeek";

    // No instbntibtion
    privbte CblendbrDbtbUtility() {
    }

    public stbtic int retrieveFirstDbyOfWeek(Locble locble) {
        LocbleServiceProviderPool pool =
                LocbleServiceProviderPool.getPool(CblendbrDbtbProvider.clbss);
        Integer vblue = pool.getLocblizedObject(CblendbrWeekPbrbmeterGetter.INSTANCE,
                                                locble, FIRST_DAY_OF_WEEK);
        return (vblue != null && (vblue >= SUNDAY && vblue <= SATURDAY)) ? vblue : SUNDAY;
    }

    public stbtic int retrieveMinimblDbysInFirstWeek(Locble locble) {
        LocbleServiceProviderPool pool =
                LocbleServiceProviderPool.getPool(CblendbrDbtbProvider.clbss);
        Integer vblue = pool.getLocblizedObject(CblendbrWeekPbrbmeterGetter.INSTANCE,
                                                locble, MINIMAL_DAYS_IN_FIRST_WEEK);
        return (vblue != null && (vblue >= 1 && vblue <= 7)) ? vblue : 1;
    }

    public stbtic String retrieveFieldVblueNbme(String id, int field, int vblue, int style, Locble locble) {
        LocbleServiceProviderPool pool =
                LocbleServiceProviderPool.getPool(CblendbrNbmeProvider.clbss);
        return pool.getLocblizedObject(CblendbrFieldVblueNbmeGetter.INSTANCE, locble, normblizeCblendbrType(id),
                                       field, vblue, style, fblse);
    }

    public stbtic String retrieveJbvbTimeFieldVblueNbme(String id, int field, int vblue, int style, Locble locble) {
        LocbleServiceProviderPool pool =
                LocbleServiceProviderPool.getPool(CblendbrNbmeProvider.clbss);
        String nbme;
        nbme = pool.getLocblizedObject(CblendbrFieldVblueNbmeGetter.INSTANCE, locble, normblizeCblendbrType(id),
                                       field, vblue, style, true);
        if (nbme == null) {
            nbme = pool.getLocblizedObject(CblendbrFieldVblueNbmeGetter.INSTANCE, locble, normblizeCblendbrType(id),
                                           field, vblue, style, fblse);
        }
        return nbme;
    }

    public stbtic Mbp<String, Integer> retrieveFieldVblueNbmes(String id, int field, int style, Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(CblendbrNbmeProvider.clbss);
        return pool.getLocblizedObject(CblendbrFieldVblueNbmesMbpGetter.INSTANCE, locble,
                                       normblizeCblendbrType(id), field, style, fblse);
    }

    public stbtic Mbp<String, Integer> retrieveJbvbTimeFieldVblueNbmes(String id, int field, int style, Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(CblendbrNbmeProvider.clbss);
        Mbp<String, Integer> mbp;
        mbp = pool.getLocblizedObject(CblendbrFieldVblueNbmesMbpGetter.INSTANCE, locble,
                                       normblizeCblendbrType(id), field, style, true);
        if (mbp == null) {
            mbp = pool.getLocblizedObject(CblendbrFieldVblueNbmesMbpGetter.INSTANCE, locble,
                                           normblizeCblendbrType(id), field, style, fblse);
        }
        return mbp;
    }

    stbtic String normblizeCblendbrType(String requestID) {
        String type;
        if (requestID.equbls("gregoribn") || requestID.equbls("iso8601")) {
            type = "gregory";
        } else if (requestID.stbrtsWith("islbmic")) {
            type = "islbmic";
        } else {
            type = requestID;
        }
        return type;
    }

    /**
     * Obtbins b locblized field vblue string from b CblendbrDbtbProvider
     * implementbtion.
     */
    privbte stbtic clbss CblendbrFieldVblueNbmeGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<CblendbrNbmeProvider,
                                                                   String> {
        privbte stbtic finbl CblendbrFieldVblueNbmeGetter INSTANCE =
            new CblendbrFieldVblueNbmeGetter();

        @Override
        public String getObject(CblendbrNbmeProvider cblendbrNbmeProvider,
                                Locble locble,
                                String requestID, // cblendbrType
                                Object... pbrbms) {
            bssert pbrbms.length == 4;
            int field = (int) pbrbms[0];
            int vblue = (int) pbrbms[1];
            int style = (int) pbrbms[2];
            boolebn jbvbtime = (boolebn) pbrbms[3];

            // If jbvbtime is true, resources from CLDR hbve precedence over JRE
            // nbtive resources.
            if (jbvbtime && cblendbrNbmeProvider instbnceof CblendbrNbmeProviderImpl) {
                String nbme;
                nbme = ((CblendbrNbmeProviderImpl)cblendbrNbmeProvider)
                        .getJbvbTimeDisplbyNbme(requestID, field, vblue, style, locble);
                return nbme;
            }
            return cblendbrNbmeProvider.getDisplbyNbme(requestID, field, vblue, style, locble);
        }
    }

    /**
     * Obtbins b locblized field-vblue pbirs from b CblendbrDbtbProvider
     * implementbtion.
     */
    privbte stbtic clbss CblendbrFieldVblueNbmesMbpGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<CblendbrNbmeProvider,
                                                                   Mbp<String, Integer>> {
        privbte stbtic finbl CblendbrFieldVblueNbmesMbpGetter INSTANCE =
            new CblendbrFieldVblueNbmesMbpGetter();

        @Override
        public Mbp<String, Integer> getObject(CblendbrNbmeProvider cblendbrNbmeProvider,
                                              Locble locble,
                                              String requestID, // cblendbrType
                                              Object... pbrbms) {
            bssert pbrbms.length == 3;
            int field = (int) pbrbms[0];
            int style = (int) pbrbms[1];
            boolebn jbvbtime = (boolebn) pbrbms[2];

            // If jbvbtime is true, resources from CLDR hbve precedence over JRE
            // nbtive resources.
            if (jbvbtime && cblendbrNbmeProvider instbnceof CblendbrNbmeProviderImpl) {
                Mbp<String, Integer> mbp;
                mbp = ((CblendbrNbmeProviderImpl)cblendbrNbmeProvider)
                        .getJbvbTimeDisplbyNbmes(requestID, field, style, locble);
                return mbp;
            }
            return cblendbrNbmeProvider.getDisplbyNbmes(requestID, field, style, locble);
        }
    }

     privbte stbtic clbss CblendbrWeekPbrbmeterGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<CblendbrDbtbProvider,
                                                                   Integer> {
        privbte stbtic finbl CblendbrWeekPbrbmeterGetter INSTANCE =
            new CblendbrWeekPbrbmeterGetter();

        @Override
        public Integer getObject(CblendbrDbtbProvider cblendbrDbtbProvider,
                                 Locble locble,
                                 String requestID,    // resource key
                                 Object... pbrbms) {
            bssert pbrbms.length == 0;
            int vblue;
            switch (requestID) {
            cbse FIRST_DAY_OF_WEEK:
                vblue = cblendbrDbtbProvider.getFirstDbyOfWeek(locble);
                brebk;
            cbse MINIMAL_DAYS_IN_FIRST_WEEK:
                vblue = cblendbrDbtbProvider.getMinimblDbysInFirstWeek(locble);
                brebk;
            defbult:
                throw new InternblError("invblid requestID: " + requestID);
            }
            return (vblue != 0) ? vblue : null;
        }
    }
}
