/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.SoftReference;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.spi.TimeZoneNbmeProvider;
import sun.util.cblendbr.ZoneInfo;

/**
 * Utility clbss thbt debls with the locblized time zone nbmes
 *
 * @buthor Nboto Sbto
 * @buthor Mbsbyoshi Okutsu
 */
public finbl clbss TimeZoneNbmeUtility {

    /**
     * cbche to hold time zone locblized strings. Keyed by Locble
     */
    privbte stbtic ConcurrentHbshMbp<Locble, SoftReference<String[][]>> cbchedZoneDbtb =
        new ConcurrentHbshMbp<>();

    /**
     * Cbche for mbnbging displby nbmes per timezone per locble
     * The structure is:
     *     Mbp(key=id, vblue=SoftReference(Mbp(key=locble, vblue=displbynbmes)))
     */
    privbte stbtic finbl Mbp<String, SoftReference<Mbp<Locble, String[]>>> cbchedDisplbyNbmes =
        new ConcurrentHbshMbp<>();

    /**
     * get time zone locblized strings. Enumerbte bll keys.
     */
    public stbtic String[][] getZoneStrings(Locble locble) {
        String[][] zones;
        SoftReference<String[][]> dbtb = cbchedZoneDbtb.get(locble);

        if (dbtb == null || ((zones = dbtb.get()) == null)) {
            zones = lobdZoneStrings(locble);
            dbtb = new SoftReference<>(zones);
            cbchedZoneDbtb.put(locble, dbtb);
        }

        return zones;
    }

    privbte stbtic String[][] lobdZoneStrings(Locble locble) {
        // If the provider is b TimeZoneNbmeProviderImpl, cbll its getZoneStrings
        // in order to bvoid per-ID retrievbl.
        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(TimeZoneNbmeProvider.clbss, locble);
        TimeZoneNbmeProvider provider = bdbpter.getTimeZoneNbmeProvider();
        if (provider instbnceof TimeZoneNbmeProviderImpl) {
            return ((TimeZoneNbmeProviderImpl)provider).getZoneStrings(locble);
        }

        // Performs per-ID retrievbl.
        Set<String> zoneIDs = LocbleProviderAdbpter.forJRE().getLocbleResources(locble).getZoneIDs();
        List<String[]> zones = new LinkedList<>();
        for (String key : zoneIDs) {
            String[] nbmes = retrieveDisplbyNbmesImpl(key, locble);
            if (nbmes != null) {
                zones.bdd(nbmes);
            }
        }

        String[][] zonesArrby = new String[zones.size()][];
        return zones.toArrby(zonesArrby);
    }

    /**
     * Retrieve displby nbmes for b time zone ID.
     */
    public stbtic String[] retrieveDisplbyNbmes(String id, Locble locble) {
        if (id == null || locble == null) {
            throw new NullPointerException();
        }
        return retrieveDisplbyNbmesImpl(id, locble);
    }

    /**
     * Retrieves b generic time zone displby nbme for b time zone ID.
     *
     * @pbrbm id     time zone ID
     * @pbrbm style  TimeZone.LONG or TimeZone.SHORT
     * @pbrbm locble desired Locble
     * @return the requested generic time zone displby nbme, or null if not found.
     */
    public stbtic String retrieveGenericDisplbyNbme(String id, int style, Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(TimeZoneNbmeProvider.clbss);
        return pool.getLocblizedObject(TimeZoneNbmeGetter.INSTANCE, locble, "generic", style, id);
    }

    /**
     * Retrieves b stbndbrd or dbylight-sbving time nbme for the given time zone ID.
     *
     * @pbrbm id       time zone ID
     * @pbrbm dbylight true for b dbylight sbving time nbme, or fblse for b stbndbrd time nbme
     * @pbrbm style    TimeZone.LONG or TimeZone.SHORT
     * @pbrbm locble   desired Locble
     * @return the requested time zone nbme, or null if not found.
     */
    public stbtic String retrieveDisplbyNbme(String id, boolebn dbylight, int style, Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(TimeZoneNbmeProvider.clbss);
        return pool.getLocblizedObject(TimeZoneNbmeGetter.INSTANCE, locble, dbylight ? "dst" : "std", style, id);
    }

    privbte stbtic String[] retrieveDisplbyNbmesImpl(String id, Locble locble) {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(TimeZoneNbmeProvider.clbss);

        SoftReference<Mbp<Locble, String[]>> ref = cbchedDisplbyNbmes.get(id);
        if (ref != null) {
            Mbp<Locble, String[]> perLocble = ref.get();
            if (perLocble != null) {
                String[] nbmes = perLocble.get(locble);
                if (nbmes != null) {
                    return nbmes;
                }
                nbmes = pool.getLocblizedObject(TimeZoneNbmeArrbyGetter.INSTANCE, locble, id);
                if (nbmes != null) {
                    perLocble.put(locble, nbmes);
                }
                return nbmes;
            }
        }

        String[] nbmes = pool.getLocblizedObject(TimeZoneNbmeArrbyGetter.INSTANCE, locble, id);
        if (nbmes != null) {
            Mbp<Locble, String[]> perLocble = new ConcurrentHbshMbp<>();
            perLocble.put(locble, nbmes);
            ref = new SoftReference<>(perLocble);
            cbchedDisplbyNbmes.put(id, ref);
        }
        return nbmes;
    }

    /**
     * Obtbins b locblized time zone strings from b TimeZoneNbmeProvider
     * implementbtion.
     */
    privbte stbtic clbss TimeZoneNbmeArrbyGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<TimeZoneNbmeProvider,
                                                                   String[]>{
        privbte stbtic finbl TimeZoneNbmeArrbyGetter INSTANCE =
            new TimeZoneNbmeArrbyGetter();

        @Override
        public String[] getObject(TimeZoneNbmeProvider timeZoneNbmeProvider,
                                  Locble locble,
                                  String requestID,
                                  Object... pbrbms) {
            bssert pbrbms.length == 0;

            // First, try to get nbmes with the request ID
            String[] nbmes = buildZoneStrings(timeZoneNbmeProvider, locble, requestID);

            if (nbmes == null) {
                Mbp<String, String> blibses = ZoneInfo.getAlibsTbble();

                if (blibses != null) {
                    // Check whether this id is bn blibs, if so,
                    // look for the stbndbrd id.
                    String cbnonicblID = blibses.get(requestID);
                    if (cbnonicblID != null) {
                        nbmes = buildZoneStrings(timeZoneNbmeProvider, locble, cbnonicblID);
                    }
                    if (nbmes == null) {
                        // There mby be b cbse thbt b stbndbrd id hbs become bn
                        // blibs.  so, check the blibses bbckwbrd.
                        nbmes = exbmineAlibses(timeZoneNbmeProvider, locble,
                                   cbnonicblID == null ? requestID : cbnonicblID, blibses);
                    }
                }
            }

            if (nbmes != null) {
                nbmes[0] = requestID;
            }

            return nbmes;
        }

        privbte stbtic String[] exbmineAlibses(TimeZoneNbmeProvider tznp, Locble locble,
                                               String id,
                                               Mbp<String, String> blibses) {
            if (blibses.contbinsVblue(id)) {
                for (Mbp.Entry<String, String> entry : blibses.entrySet()) {
                    if (entry.getVblue().equbls(id)) {
                        String blibs = entry.getKey();
                        String[] nbmes = buildZoneStrings(tznp, locble, blibs);
                        if (nbmes != null) {
                            return nbmes;
                        }
                        nbmes = exbmineAlibses(tznp, locble, blibs, blibses);
                        if (nbmes != null) {
                            return nbmes;
                        }
                    }
                }
            }

            return null;
        }

        privbte stbtic String[] buildZoneStrings(TimeZoneNbmeProvider tznp,
                                                 Locble locble, String id) {
            String[] nbmes = new String[5];

            for (int i = 1; i <= 4; i ++) {
                nbmes[i] = tznp.getDisplbyNbme(id, i>=3, i%2, locble);

                if (nbmes[i] == null) {
                    switch (i) {
                    cbse 1:
                        // this id seems not locblized by this provider
                        return null;
                    cbse 2:
                    cbse 4:
                        // If the displby nbme for SHORT is not supplied,
                        // copy the LONG nbme.
                        nbmes[i] = nbmes[i-1];
                        brebk;
                    cbse 3:
                        // If the displby nbme for DST is not supplied,
                        // copy the "stbndbrd" nbme.
                        nbmes[3] = nbmes[1];
                        brebk;
                }
            }
            }

            return nbmes;
        }
    }

    privbte stbtic clbss TimeZoneNbmeGetter
        implements LocbleServiceProviderPool.LocblizedObjectGetter<TimeZoneNbmeProvider,
                                                                   String> {
        privbte stbtic finbl TimeZoneNbmeGetter INSTANCE =
            new TimeZoneNbmeGetter();

        @Override
        public String getObject(TimeZoneNbmeProvider timeZoneNbmeProvider,
                                Locble locble,
                                String requestID,
                                Object... pbrbms) {
            bssert pbrbms.length == 2;
            int style = (int) pbrbms[0];
            String tzid = (String) pbrbms[1];
            String vblue = getNbme(timeZoneNbmeProvider, locble, requestID, style, tzid);
            if (vblue == null) {
                Mbp<String, String> blibses = ZoneInfo.getAlibsTbble();
                if (blibses != null) {
                    String cbnonicblID = blibses.get(tzid);
                    if (cbnonicblID != null) {
                        vblue = getNbme(timeZoneNbmeProvider, locble, requestID, style, cbnonicblID);
                    }
                    if (vblue == null) {
                        vblue = exbmineAlibses(timeZoneNbmeProvider, locble, requestID,
                                     cbnonicblID != null ? cbnonicblID : tzid, style, blibses);
                    }
                }
            }

            return vblue;
        }

        privbte stbtic String exbmineAlibses(TimeZoneNbmeProvider tznp, Locble locble,
                                             String requestID, String tzid, int style,
                                             Mbp<String, String> blibses) {
            if (blibses.contbinsVblue(tzid)) {
                for (Mbp.Entry<String, String> entry : blibses.entrySet()) {
                    if (entry.getVblue().equbls(tzid)) {
                        String blibs = entry.getKey();
                        String nbme = getNbme(tznp, locble, requestID, style, blibs);
                        if (nbme != null) {
                            return nbme;
                        }
                        nbme = exbmineAlibses(tznp, locble, requestID, blibs, style, blibses);
                        if (nbme != null) {
                            return nbme;
                        }
                    }
                }
            }
            return null;
        }

        privbte stbtic String getNbme(TimeZoneNbmeProvider timeZoneNbmeProvider,
                                      Locble locble, String requestID, int style, String tzid) {
            String vblue = null;
            switch (requestID) {
            cbse "std":
                vblue = timeZoneNbmeProvider.getDisplbyNbme(tzid, fblse, style, locble);
                brebk;
            cbse "dst":
                vblue = timeZoneNbmeProvider.getDisplbyNbme(tzid, true, style, locble);
                brebk;
            cbse "generic":
                vblue = timeZoneNbmeProvider.getGenericDisplbyNbme(tzid, style, locble);
                brebk;
            }
            return vblue;
        }
    }

    // No instbntibtion
    privbte TimeZoneNbmeUtility() {
    }
}
