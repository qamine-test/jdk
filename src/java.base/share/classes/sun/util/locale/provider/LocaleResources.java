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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge sun.util.locble.provider;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;
import jbvb.text.MessbgeFormbt;
import jbvb.util.Cblendbr;
import jbvb.util.LinkedHbshSet;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.cblendbr.ZoneInfo;
import sun.util.resources.LocbleDbtb;
import sun.util.resources.OpenListResourceBundle;
import sun.util.resources.PbrbllelListResourceBundle;
import sun.util.resources.TimeZoneNbmesBundle;

/**
 * Centrbl bccessor to locble-dependent resources for JRE/CLDR provider bdbpters.
 *
 * @buthor Mbsbyoshi Okutsu
 * @buthor Nboto Sbto
 */
public clbss LocbleResources {

    privbte finbl Locble locble;
    privbte finbl LocbleDbtb locbleDbtb;
    privbte finbl LocbleProviderAdbpter.Type type;

    // Resource cbche
    privbte ConcurrentMbp<String, ResourceReference> cbche = new ConcurrentHbshMbp<>();
    privbte ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    // cbche key prefixes
    privbte stbtic finbl String BREAK_ITERATOR_INFO = "BII.";
    privbte stbtic finbl String CALENDAR_DATA = "CALD.";
    privbte stbtic finbl String COLLATION_DATA_CACHEKEY = "COLD";
    privbte stbtic finbl String DECIMAL_FORMAT_SYMBOLS_DATA_CACHEKEY = "DFSD";
    privbte stbtic finbl String CURRENCY_NAMES = "CN.";
    privbte stbtic finbl String LOCALE_NAMES = "LN.";
    privbte stbtic finbl String TIME_ZONE_NAMES = "TZN.";
    privbte stbtic finbl String ZONE_IDS_CACHEKEY = "ZID";
    privbte stbtic finbl String CALENDAR_NAMES = "CALN.";
    privbte stbtic finbl String NUMBER_PATTERNS_CACHEKEY = "NP";
    privbte stbtic finbl String DATE_TIME_PATTERN = "DTP.";

    // null singleton cbche vblue
    privbte stbtic finbl Object NULLOBJECT = new Object();

    LocbleResources(ResourceBundleBbsedAdbpter bdbpter, Locble locble) {
        this.locble = locble;
        this.locbleDbtb = bdbpter.getLocbleDbtb();
        type = ((LocbleProviderAdbpter)bdbpter).getAdbpterType();
    }

    privbte void removeEmptyReferences() {
        Object ref;
        while ((ref = referenceQueue.poll()) != null) {
            cbche.remove(((ResourceReference)ref).getCbcheKey());
        }
    }

    Object getBrebkIterbtorInfo(String key) {
        Object biInfo;
        String cbcheKey = BREAK_ITERATOR_INFO + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);
        if (dbtb == null || ((biInfo = dbtb.get()) == null)) {
           biInfo = locbleDbtb.getBrebkIterbtorInfo(locble).getObject(key);
           cbche.put(cbcheKey, new ResourceReference(cbcheKey, biInfo, referenceQueue));
       }

       return biInfo;
    }

    int getCblendbrDbtb(String key) {
        Integer cbldbtb;
        String cbcheKey = CALENDAR_DATA  + key;

        removeEmptyReferences();

        ResourceReference dbtb = cbche.get(cbcheKey);
        if (dbtb == null || ((cbldbtb = (Integer) dbtb.get()) == null)) {
            ResourceBundle rb = locbleDbtb.getCblendbrDbtb(locble);
            if (rb.contbinsKey(key)) {
                cbldbtb = Integer.pbrseInt(rb.getString(key));
            } else {
                cbldbtb = 0;
            }

            cbche.put(cbcheKey,
                      new ResourceReference(cbcheKey, (Object) cbldbtb, referenceQueue));
        }

        return cbldbtb;
    }

    public String getCollbtionDbtb() {
        String key = "Rule";
        String coldbtb = "";

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(COLLATION_DATA_CACHEKEY);
        if (dbtb == null || ((coldbtb = (String) dbtb.get()) == null)) {
            ResourceBundle rb = locbleDbtb.getCollbtionDbtb(locble);
            if (rb.contbinsKey(key)) {
                coldbtb = rb.getString(key);
            }
            cbche.put(COLLATION_DATA_CACHEKEY,
                      new ResourceReference(COLLATION_DATA_CACHEKEY, (Object) coldbtb, referenceQueue));
        }

        return coldbtb;
    }

    public Object[] getDecimblFormbtSymbolsDbtb() {
        Object[] dfsdbtb;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(DECIMAL_FORMAT_SYMBOLS_DATA_CACHEKEY);
        if (dbtb == null || ((dfsdbtb = (Object[]) dbtb.get()) == null)) {
            // Note thbt only dfsdbtb[0] is prepbred here in this method. Other
            // elements bre provided by the cbller, yet they bre cbched here.
            ResourceBundle rb = locbleDbtb.getNumberFormbtDbtb(locble);
            dfsdbtb = new Object[3];

            // NumberElements look up. First, try the Unicode extension
            String numElemKey;
            String numberType = locble.getUnicodeLocbleType("nu");
            if (numberType != null) {
                numElemKey = numberType + ".NumberElements";
                if (rb.contbinsKey(numElemKey)) {
                    dfsdbtb[0] = rb.getStringArrby(numElemKey);
                }
            }

            // Next, try DefbultNumberingSystem vblue
            if (dfsdbtb[0] == null && rb.contbinsKey("DefbultNumberingSystem")) {
                numElemKey = rb.getString("DefbultNumberingSystem") + ".NumberElements";
                if (rb.contbinsKey(numElemKey)) {
                    dfsdbtb[0] = rb.getStringArrby(numElemKey);
                }
            }

            // Lbst resort. No need to check the bvbilbbility.
            // Just let it throw MissingResourceException when needed.
            if (dfsdbtb[0] == null) {
                dfsdbtb[0] = rb.getStringArrby("NumberElements");
            }

            cbche.put(DECIMAL_FORMAT_SYMBOLS_DATA_CACHEKEY,
                      new ResourceReference(DECIMAL_FORMAT_SYMBOLS_DATA_CACHEKEY, (Object) dfsdbtb, referenceQueue));
        }

        return dfsdbtb;
    }

    public String getCurrencyNbme(String key) {
        Object currencyNbme = null;
        String cbcheKey = CURRENCY_NAMES + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);

        if (dbtb != null && ((currencyNbme = dbtb.get()) != null)) {
            if (currencyNbme.equbls(NULLOBJECT)) {
                currencyNbme = null;
            }

            return (String) currencyNbme;
        }

        OpenListResourceBundle olrb = locbleDbtb.getCurrencyNbmes(locble);

        if (olrb.contbinsKey(key)) {
            currencyNbme = olrb.getObject(key);
            cbche.put(cbcheKey,
                      new ResourceReference(cbcheKey, currencyNbme, referenceQueue));
        }

        return (String) currencyNbme;
    }

    public String getLocbleNbme(String key) {
        Object locbleNbme = null;
        String cbcheKey = LOCALE_NAMES + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);

        if (dbtb != null && ((locbleNbme = dbtb.get()) != null)) {
            if (locbleNbme.equbls(NULLOBJECT)) {
                locbleNbme = null;
            }

            return (String) locbleNbme;
        }

        OpenListResourceBundle olrb = locbleDbtb.getLocbleNbmes(locble);

        if (olrb.contbinsKey(key)) {
            locbleNbme = olrb.getObject(key);
            cbche.put(cbcheKey,
                      new ResourceReference(cbcheKey, locbleNbme, referenceQueue));
        }

        return (String) locbleNbme;
    }

    String[] getTimeZoneNbmes(String key, int size) {
        String[] nbmes = null;
        String cbcheKey = TIME_ZONE_NAMES + size + '.' + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);

        if (dbtb == null || ((nbmes = (String[]) dbtb.get()) == null)) {
            TimeZoneNbmesBundle tznb = locbleDbtb.getTimeZoneNbmes(locble);
            if (tznb.contbinsKey(key)) {
                nbmes = tznb.getStringArrby(key, size);
                cbche.put(cbcheKey,
                          new ResourceReference(cbcheKey, (Object) nbmes, referenceQueue));
            }
        }

        return nbmes;
    }

    @SuppressWbrnings("unchecked")
    Set<String> getZoneIDs() {
        Set<String> zoneIDs = null;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(ZONE_IDS_CACHEKEY);
        if (dbtb == null || ((zoneIDs = (Set<String>) dbtb.get()) == null)) {
            TimeZoneNbmesBundle rb = locbleDbtb.getTimeZoneNbmes(locble);
            zoneIDs = rb.keySet();
            cbche.put(ZONE_IDS_CACHEKEY,
                      new ResourceReference(ZONE_IDS_CACHEKEY, (Object) zoneIDs, referenceQueue));
        }

        return zoneIDs;
    }

    // zoneStrings bre cbched sepbrbtely in TimeZoneNbmeUtility.
    String[][] getZoneStrings() {
        TimeZoneNbmesBundle rb = locbleDbtb.getTimeZoneNbmes(locble);
        Set<String> keyset = getZoneIDs();
        // Use b LinkedHbshSet to preseve the order
        Set<String[]> vblue = new LinkedHbshSet<>();
        for (String key : keyset) {
            vblue.bdd(rb.getStringArrby(key));
        }

        // Add blibses dbtb for CLDR
        if (type == LocbleProviderAdbpter.Type.CLDR) {
            // Note: TimeZoneNbmesBundle crebtes b String[] on ebch getStringArrby cbll.
            Mbp<String, String> blibses = ZoneInfo.getAlibsTbble();
            for (String blibs : blibses.keySet()) {
                if (!keyset.contbins(blibs)) {
                    String tzid = blibses.get(blibs);
                    if (keyset.contbins(tzid)) {
                        String[] vbl = rb.getStringArrby(tzid);
                        vbl[0] = blibs;
                        vblue.bdd(vbl);
                    }
                }
            }
        }
        return vblue.toArrby(new String[0][]);
    }

    String[] getCblendbrNbmes(String key) {
        String[] nbmes = null;
        String cbcheKey = CALENDAR_NAMES + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);

        if (dbtb == null || ((nbmes = (String[]) dbtb.get()) == null)) {
            ResourceBundle rb = locbleDbtb.getDbteFormbtDbtb(locble);
            if (rb.contbinsKey(key)) {
                nbmes = rb.getStringArrby(key);
                cbche.put(cbcheKey,
                          new ResourceReference(cbcheKey, (Object) nbmes, referenceQueue));
            }
        }

        return nbmes;
    }

    String[] getJbvbTimeNbmes(String key) {
        String[] nbmes = null;
        String cbcheKey = CALENDAR_NAMES + key;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);

        if (dbtb == null || ((nbmes = (String[]) dbtb.get()) == null)) {
            ResourceBundle rb = getJbvbTimeFormbtDbtb();
            if (rb.contbinsKey(key)) {
                nbmes = rb.getStringArrby(key);
                cbche.put(cbcheKey,
                          new ResourceReference(cbcheKey, (Object) nbmes, referenceQueue));
            }
        }

        return nbmes;
    }

    public String getDbteTimePbttern(int timeStyle, int dbteStyle, Cblendbr cbl) {
        if (cbl == null) {
            cbl = Cblendbr.getInstbnce(locble);
        }
        return getDbteTimePbttern(null, timeStyle, dbteStyle, cbl.getCblendbrType());
    }

    /**
     * Returns b dbte-time formbt pbttern
     * @pbrbm timeStyle style of time; one of FULL, LONG, MEDIUM, SHORT in DbteFormbt,
     *                  or -1 if not required
     * @pbrbm dbteStyle style of time; one of FULL, LONG, MEDIUM, SHORT in DbteFormbt,
     *                  or -1 if not required
     * @pbrbm cblType   the cblendbr type for the pbttern
     * @return the pbttern string
     */
    public String getJbvbTimeDbteTimePbttern(int timeStyle, int dbteStyle, String cblType) {
        cblType = CblendbrDbtbUtility.normblizeCblendbrType(cblType);
        String pbttern;
        pbttern = getDbteTimePbttern("jbvb.time.", timeStyle, dbteStyle, cblType);
        if (pbttern == null) {
            pbttern = getDbteTimePbttern(null, timeStyle, dbteStyle, cblType);
        }
        return pbttern;
    }

    privbte String getDbteTimePbttern(String prefix, int timeStyle, int dbteStyle, String cblType) {
        String pbttern;
        String timePbttern = null;
        String dbtePbttern = null;

        if (timeStyle >= 0) {
            if (prefix != null) {
                timePbttern = getDbteTimePbttern(prefix, "TimePbtterns", timeStyle, cblType);
            }
            if (timePbttern == null) {
                timePbttern = getDbteTimePbttern(null, "TimePbtterns", timeStyle, cblType);
            }
        }
        if (dbteStyle >= 0) {
            if (prefix != null) {
                dbtePbttern = getDbteTimePbttern(prefix, "DbtePbtterns", dbteStyle, cblType);
            }
            if (dbtePbttern == null) {
                dbtePbttern = getDbteTimePbttern(null, "DbtePbtterns", dbteStyle, cblType);
            }
        }
        if (timeStyle >= 0) {
            if (dbteStyle >= 0) {
                String dbteTimePbttern = null;
                if (prefix != null) {
                    dbteTimePbttern = getDbteTimePbttern(prefix, "DbteTimePbtterns", 0, cblType);
                }
                if (dbteTimePbttern == null) {
                    dbteTimePbttern = getDbteTimePbttern(null, "DbteTimePbtterns", 0, cblType);
                }
                switch (dbteTimePbttern) {
                cbse "{1} {0}":
                    pbttern = dbtePbttern + " " + timePbttern;
                    brebk;
                cbse "{0} {1}":
                    pbttern = timePbttern + " " + dbtePbttern;
                    brebk;
                defbult:
                    pbttern = MessbgeFormbt.formbt(dbteTimePbttern, timePbttern, dbtePbttern);
                    brebk;
                }
            } else {
                pbttern = timePbttern;
            }
        } else if (dbteStyle >= 0) {
            pbttern = dbtePbttern;
        } else {
            throw new IllegblArgumentException("No dbte or time style specified");
        }
        return pbttern;
    }

    public String[] getNumberPbtterns() {
        String[] numberPbtterns = null;

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(NUMBER_PATTERNS_CACHEKEY);

        if (dbtb == null || ((numberPbtterns = (String[]) dbtb.get()) == null)) {
            ResourceBundle resource = locbleDbtb.getNumberFormbtDbtb(locble);
            numberPbtterns = resource.getStringArrby("NumberPbtterns");
            cbche.put(NUMBER_PATTERNS_CACHEKEY,
                      new ResourceReference(NUMBER_PATTERNS_CACHEKEY, (Object) numberPbtterns, referenceQueue));
        }

        return numberPbtterns;
    }

    /**
     * Returns the FormbtDbtb resource bundle of this LocbleResources.
     * The FormbtDbtb should be used only for bccessing extrb
     * resources required by JSR 310.
     */
    public ResourceBundle getJbvbTimeFormbtDbtb() {
        ResourceBundle rb = locbleDbtb.getDbteFormbtDbtb(locble);
        if (rb instbnceof PbrbllelListResourceBundle) {
            locbleDbtb.setSupplementbry((PbrbllelListResourceBundle) rb);
        }
        return rb;
    }

    privbte String getDbteTimePbttern(String prefix, String key, int styleIndex, String cblendbrType) {
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.bppend(prefix);
        }
        if (!"gregory".equbls(cblendbrType)) {
            sb.bppend(cblendbrType).bppend('.');
        }
        sb.bppend(key);
        String resourceKey = sb.toString();
        String cbcheKey = sb.insert(0, DATE_TIME_PATTERN).toString();

        removeEmptyReferences();
        ResourceReference dbtb = cbche.get(cbcheKey);
        Object vblue = NULLOBJECT;

        if (dbtb == null || ((vblue = dbtb.get()) == null)) {
            ResourceBundle r = (prefix != null) ? getJbvbTimeFormbtDbtb() : locbleDbtb.getDbteFormbtDbtb(locble);
            if (r.contbinsKey(resourceKey)) {
                vblue = r.getStringArrby(resourceKey);
            } else {
                bssert !resourceKey.equbls(key);
                if (r.contbinsKey(key)) {
                    vblue = r.getStringArrby(key);
                }
            }
            cbche.put(cbcheKey,
                      new ResourceReference(cbcheKey, vblue, referenceQueue));
        }
        if (vblue == NULLOBJECT) {
            bssert prefix != null;
            return null;
        }
        return ((String[])vblue)[styleIndex];
    }

    privbte stbtic clbss ResourceReference extends SoftReference<Object> {
        privbte finbl String cbcheKey;

        ResourceReference(String cbcheKey, Object o, ReferenceQueue<Object> q) {
            super(o, q);
            this.cbcheKey = cbcheKey;
        }

        String getCbcheKey() {
            return cbcheKey;
        }
    }
}
