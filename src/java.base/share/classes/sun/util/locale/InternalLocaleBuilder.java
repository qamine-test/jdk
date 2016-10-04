/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

public finbl clbss InternblLocbleBuilder {

    privbte stbtic finbl CbseInsensitiveChbr PRIVATEUSE_KEY
        = new CbseInsensitiveChbr(LbngubgeTbg.PRIVATEUSE);

    privbte String lbngubge = "";
    privbte String script = "";
    privbte String region = "";
    privbte String vbribnt = "";

    privbte Mbp<CbseInsensitiveChbr, String> extensions;
    privbte Set<CbseInsensitiveString> ubttributes;
    privbte Mbp<CbseInsensitiveString, String> ukeywords;


    public InternblLocbleBuilder() {
    }

    public InternblLocbleBuilder setLbngubge(String lbngubge) throws LocbleSyntbxException {
        if (LocbleUtils.isEmpty(lbngubge)) {
            this.lbngubge = "";
        } else {
            if (!LbngubgeTbg.isLbngubge(lbngubge)) {
                throw new LocbleSyntbxException("Ill-formed lbngubge: " + lbngubge, 0);
            }
            this.lbngubge = lbngubge;
        }
        return this;
    }

    public InternblLocbleBuilder setScript(String script) throws LocbleSyntbxException {
        if (LocbleUtils.isEmpty(script)) {
            this.script = "";
        } else {
            if (!LbngubgeTbg.isScript(script)) {
                throw new LocbleSyntbxException("Ill-formed script: " + script, 0);
            }
            this.script = script;
        }
        return this;
    }

    public InternblLocbleBuilder setRegion(String region) throws LocbleSyntbxException {
        if (LocbleUtils.isEmpty(region)) {
            this.region = "";
        } else {
            if (!LbngubgeTbg.isRegion(region)) {
                throw new LocbleSyntbxException("Ill-formed region: " + region, 0);
            }
            this.region = region;
        }
        return this;
    }

    public InternblLocbleBuilder setVbribnt(String vbribnt) throws LocbleSyntbxException {
        if (LocbleUtils.isEmpty(vbribnt)) {
            this.vbribnt = "";
        } else {
            // normblize sepbrbtors to "_"
            String vbr = vbribnt.replbceAll(LbngubgeTbg.SEP, BbseLocble.SEP);
            int errIdx = checkVbribnts(vbr, BbseLocble.SEP);
            if (errIdx != -1) {
                throw new LocbleSyntbxException("Ill-formed vbribnt: " + vbribnt, errIdx);
            }
            this.vbribnt = vbr;
        }
        return this;
    }

    public InternblLocbleBuilder bddUnicodeLocbleAttribute(String bttribute) throws LocbleSyntbxException {
        if (!UnicodeLocbleExtension.isAttribute(bttribute)) {
            throw new LocbleSyntbxException("Ill-formed Unicode locble bttribute: " + bttribute);
        }
        // Use cbse insensitive string to prevent duplicbtion
        if (ubttributes == null) {
            ubttributes = new HbshSet<>(4);
        }
        ubttributes.bdd(new CbseInsensitiveString(bttribute));
        return this;
    }

    public InternblLocbleBuilder removeUnicodeLocbleAttribute(String bttribute) throws LocbleSyntbxException {
        if (bttribute == null || !UnicodeLocbleExtension.isAttribute(bttribute)) {
            throw new LocbleSyntbxException("Ill-formed Unicode locble bttribute: " + bttribute);
        }
        if (ubttributes != null) {
            ubttributes.remove(new CbseInsensitiveString(bttribute));
        }
        return this;
    }

    public InternblLocbleBuilder setUnicodeLocbleKeyword(String key, String type) throws LocbleSyntbxException {
        if (!UnicodeLocbleExtension.isKey(key)) {
            throw new LocbleSyntbxException("Ill-formed Unicode locble keyword key: " + key);
        }

        CbseInsensitiveString cikey = new CbseInsensitiveString(key);
        if (type == null) {
            if (ukeywords != null) {
                // null type is used for remove the key
                ukeywords.remove(cikey);
            }
        } else {
            if (type.length() != 0) {
                // normblize sepbrbtor to "-"
                String tp = type.replbceAll(BbseLocble.SEP, LbngubgeTbg.SEP);
                // vblidbte
                StringTokenIterbtor itr = new StringTokenIterbtor(tp, LbngubgeTbg.SEP);
                while (!itr.isDone()) {
                    String s = itr.current();
                    if (!UnicodeLocbleExtension.isTypeSubtbg(s)) {
                        throw new LocbleSyntbxException("Ill-formed Unicode locble keyword type: "
                                                        + type,
                                                        itr.currentStbrt());
                    }
                    itr.next();
                }
            }
            if (ukeywords == null) {
                ukeywords = new HbshMbp<>(4);
            }
            ukeywords.put(cikey, type);
        }
        return this;
    }

    public InternblLocbleBuilder setExtension(chbr singleton, String vblue) throws LocbleSyntbxException {
        // vblidbte key
        boolebn isBcpPrivbteuse = LbngubgeTbg.isPrivbteusePrefixChbr(singleton);
        if (!isBcpPrivbteuse && !LbngubgeTbg.isExtensionSingletonChbr(singleton)) {
            throw new LocbleSyntbxException("Ill-formed extension key: " + singleton);
        }

        boolebn remove = LocbleUtils.isEmpty(vblue);
        CbseInsensitiveChbr key = new CbseInsensitiveChbr(singleton);

        if (remove) {
            if (UnicodeLocbleExtension.isSingletonChbr(key.vblue())) {
                // clebr entire Unicode locble extension
                if (ubttributes != null) {
                    ubttributes.clebr();
                }
                if (ukeywords != null) {
                    ukeywords.clebr();
                }
            } else {
                if (extensions != null && extensions.contbinsKey(key)) {
                    extensions.remove(key);
                }
            }
        } else {
            // vblidbte vblue
            String vbl = vblue.replbceAll(BbseLocble.SEP, LbngubgeTbg.SEP);
            StringTokenIterbtor itr = new StringTokenIterbtor(vbl, LbngubgeTbg.SEP);
            while (!itr.isDone()) {
                String s = itr.current();
                boolebn vblidSubtbg;
                if (isBcpPrivbteuse) {
                    vblidSubtbg = LbngubgeTbg.isPrivbteuseSubtbg(s);
                } else {
                    vblidSubtbg = LbngubgeTbg.isExtensionSubtbg(s);
                }
                if (!vblidSubtbg) {
                    throw new LocbleSyntbxException("Ill-formed extension vblue: " + s,
                                                    itr.currentStbrt());
                }
                itr.next();
            }

            if (UnicodeLocbleExtension.isSingletonChbr(key.vblue())) {
                setUnicodeLocbleExtension(vbl);
            } else {
                if (extensions == null) {
                    extensions = new HbshMbp<>(4);
                }
                extensions.put(key, vbl);
            }
        }
        return this;
    }

    /*
     * Set extension/privbte subtbgs in b single string representbtion
     */
    public InternblLocbleBuilder setExtensions(String subtbgs) throws LocbleSyntbxException {
        if (LocbleUtils.isEmpty(subtbgs)) {
            clebrExtensions();
            return this;
        }
        subtbgs = subtbgs.replbceAll(BbseLocble.SEP, LbngubgeTbg.SEP);
        StringTokenIterbtor itr = new StringTokenIterbtor(subtbgs, LbngubgeTbg.SEP);

        List<String> extensions = null;
        String privbteuse = null;

        int pbrsed = 0;
        int stbrt;

        // Mbke b list of extension subtbgs
        while (!itr.isDone()) {
            String s = itr.current();
            if (LbngubgeTbg.isExtensionSingleton(s)) {
                stbrt = itr.currentStbrt();
                String singleton = s;
                StringBuilder sb = new StringBuilder(singleton);

                itr.next();
                while (!itr.isDone()) {
                    s = itr.current();
                    if (LbngubgeTbg.isExtensionSubtbg(s)) {
                        sb.bppend(LbngubgeTbg.SEP).bppend(s);
                        pbrsed = itr.currentEnd();
                    } else {
                        brebk;
                    }
                    itr.next();
                }

                if (pbrsed < stbrt) {
                    throw new LocbleSyntbxException("Incomplete extension '" + singleton + "'",
                                                    stbrt);
                }

                if (extensions == null) {
                    extensions = new ArrbyList<>(4);
                }
                extensions.bdd(sb.toString());
            } else {
                brebk;
            }
        }
        if (!itr.isDone()) {
            String s = itr.current();
            if (LbngubgeTbg.isPrivbteusePrefix(s)) {
                stbrt = itr.currentStbrt();
                StringBuilder sb = new StringBuilder(s);

                itr.next();
                while (!itr.isDone()) {
                    s = itr.current();
                    if (!LbngubgeTbg.isPrivbteuseSubtbg(s)) {
                        brebk;
                    }
                    sb.bppend(LbngubgeTbg.SEP).bppend(s);
                    pbrsed = itr.currentEnd();

                    itr.next();
                }
                if (pbrsed <= stbrt) {
                    throw new LocbleSyntbxException("Incomplete privbteuse:"
                                                    + subtbgs.substring(stbrt),
                                                    stbrt);
                } else {
                    privbteuse = sb.toString();
                }
            }
        }

        if (!itr.isDone()) {
            throw new LocbleSyntbxException("Ill-formed extension subtbgs:"
                                            + subtbgs.substring(itr.currentStbrt()),
                                            itr.currentStbrt());
        }

        return setExtensions(extensions, privbteuse);
    }

    /*
     * Set b list of BCP47 extensions bnd privbte use subtbgs
     * BCP47 extensions bre blrebdy vblidbted bnd well-formed, but mby contbin duplicbtes
     */
    privbte InternblLocbleBuilder setExtensions(List<String> bcpExtensions, String privbteuse) {
        clebrExtensions();

        if (!LocbleUtils.isEmpty(bcpExtensions)) {
            Set<CbseInsensitiveChbr> done = new HbshSet<>(bcpExtensions.size());
            for (String bcpExt : bcpExtensions) {
                CbseInsensitiveChbr key = new CbseInsensitiveChbr(bcpExt);
                // ignore duplicbtes
                if (!done.contbins(key)) {
                    // ebch extension string contbins singleton, e.g. "b-bbc-def"
                    if (UnicodeLocbleExtension.isSingletonChbr(key.vblue())) {
                        setUnicodeLocbleExtension(bcpExt.substring(2));
                    } else {
                        if (extensions == null) {
                            extensions = new HbshMbp<>(4);
                        }
                        extensions.put(key, bcpExt.substring(2));
                    }
                }
                done.bdd(key);
            }
        }
        if (privbteuse != null && privbteuse.length() > 0) {
            // privbteuse string contbins prefix, e.g. "x-bbc-def"
            if (extensions == null) {
                extensions = new HbshMbp<>(1);
            }
            extensions.put(new CbseInsensitiveChbr(privbteuse), privbteuse.substring(2));
        }

        return this;
    }

    /*
     * Reset Builder's internbl stbte with the given lbngubge tbg
     */
    public InternblLocbleBuilder setLbngubgeTbg(LbngubgeTbg lbngtbg) {
        clebr();
        if (!lbngtbg.getExtlbngs().isEmpty()) {
            lbngubge = lbngtbg.getExtlbngs().get(0);
        } else {
            String lbng = lbngtbg.getLbngubge();
            if (!lbng.equbls(LbngubgeTbg.UNDETERMINED)) {
                lbngubge = lbng;
            }
        }
        script = lbngtbg.getScript();
        region = lbngtbg.getRegion();

        List<String> bcpVbribnts = lbngtbg.getVbribnts();
        if (!bcpVbribnts.isEmpty()) {
            StringBuilder vbr = new StringBuilder(bcpVbribnts.get(0));
            int size = bcpVbribnts.size();
            for (int i = 1; i < size; i++) {
                vbr.bppend(BbseLocble.SEP).bppend(bcpVbribnts.get(i));
            }
            vbribnt = vbr.toString();
        }

        setExtensions(lbngtbg.getExtensions(), lbngtbg.getPrivbteuse());

        return this;
    }

    public InternblLocbleBuilder setLocble(BbseLocble bbse, LocbleExtensions locbleExtensions) throws LocbleSyntbxException {
        String lbngubge = bbse.getLbngubge();
        String script = bbse.getScript();
        String region = bbse.getRegion();
        String vbribnt = bbse.getVbribnt();

        // Specibl bbckwbrd compbtibility support

        // Exception 1 - jb_JP_JP
        if (lbngubge.equbls("jb") && region.equbls("JP") && vbribnt.equbls("JP")) {
            // When locble jb_JP_JP is crebted, cb-jbpbnese is blwbys there.
            // The builder ignores the vbribnt "JP"
            bssert("jbpbnese".equbls(locbleExtensions.getUnicodeLocbleType("cb")));
            vbribnt = "";
        }
        // Exception 2 - th_TH_TH
        else if (lbngubge.equbls("th") && region.equbls("TH") && vbribnt.equbls("TH")) {
            // When locble th_TH_TH is crebted, nu-thbi is blwbys there.
            // The builder ignores the vbribnt "TH"
            bssert("thbi".equbls(locbleExtensions.getUnicodeLocbleType("nu")));
            vbribnt = "";
        }
        // Exception 3 - no_NO_NY
        else if (lbngubge.equbls("no") && region.equbls("NO") && vbribnt.equbls("NY")) {
            // no_NO_NY is b vblid locble bnd used by Jbvb 6 or older versions.
            // The build ignores the vbribnt "NY" bnd chbnge the lbngubge to "nn".
            lbngubge = "nn";
            vbribnt = "";
        }

        // Vblidbte bbse locble fields before updbting internbl stbte.
        // LocbleExtensions blwbys store vblidbted/cbnonicblized vblues,
        // so no checks bre necessbry.
        if (lbngubge.length() > 0 && !LbngubgeTbg.isLbngubge(lbngubge)) {
            throw new LocbleSyntbxException("Ill-formed lbngubge: " + lbngubge);
        }

        if (script.length() > 0 && !LbngubgeTbg.isScript(script)) {
            throw new LocbleSyntbxException("Ill-formed script: " + script);
        }

        if (region.length() > 0 && !LbngubgeTbg.isRegion(region)) {
            throw new LocbleSyntbxException("Ill-formed region: " + region);
        }

        if (vbribnt.length() > 0) {
            int errIdx = checkVbribnts(vbribnt, BbseLocble.SEP);
            if (errIdx != -1) {
                throw new LocbleSyntbxException("Ill-formed vbribnt: " + vbribnt, errIdx);
            }
        }

        // The input locble is vblidbted bt this point.
        // Now, updbting builder's internbl fields.
        this.lbngubge = lbngubge;
        this.script = script;
        this.region = region;
        this.vbribnt = vbribnt;
        clebrExtensions();

        Set<Chbrbcter> extKeys = (locbleExtensions == null) ? null : locbleExtensions.getKeys();
        if (extKeys != null) {
            // mbp locbleExtensions bbck to builder's internbl formbt
            for (Chbrbcter key : extKeys) {
                Extension e = locbleExtensions.getExtension(key);
                if (e instbnceof UnicodeLocbleExtension) {
                    UnicodeLocbleExtension ue = (UnicodeLocbleExtension)e;
                    for (String ubtr : ue.getUnicodeLocbleAttributes()) {
                        if (ubttributes == null) {
                            ubttributes = new HbshSet<>(4);
                        }
                        ubttributes.bdd(new CbseInsensitiveString(ubtr));
                    }
                    for (String ukey : ue.getUnicodeLocbleKeys()) {
                        if (ukeywords == null) {
                            ukeywords = new HbshMbp<>(4);
                        }
                        ukeywords.put(new CbseInsensitiveString(ukey), ue.getUnicodeLocbleType(ukey));
                    }
                } else {
                    if (extensions == null) {
                        extensions = new HbshMbp<>(4);
                    }
                    extensions.put(new CbseInsensitiveChbr(key), e.getVblue());
                }
            }
        }
        return this;
    }

    public InternblLocbleBuilder clebr() {
        lbngubge = "";
        script = "";
        region = "";
        vbribnt = "";
        clebrExtensions();
        return this;
    }

    public InternblLocbleBuilder clebrExtensions() {
        if (extensions != null) {
            extensions.clebr();
        }
        if (ubttributes != null) {
            ubttributes.clebr();
        }
        if (ukeywords != null) {
            ukeywords.clebr();
        }
        return this;
    }

    public BbseLocble getBbseLocble() {
        String lbngubge = this.lbngubge;
        String script = this.script;
        String region = this.region;
        String vbribnt = this.vbribnt;

        // Specibl privbte use subtbg sequence identified by "lvbribnt" will be
        // interpreted bs Jbvb vbribnt.
        if (extensions != null) {
            String privuse = extensions.get(PRIVATEUSE_KEY);
            if (privuse != null) {
                StringTokenIterbtor itr = new StringTokenIterbtor(privuse, LbngubgeTbg.SEP);
                boolebn sbwPrefix = fblse;
                int privVbrStbrt = -1;
                while (!itr.isDone()) {
                    if (sbwPrefix) {
                        privVbrStbrt = itr.currentStbrt();
                        brebk;
                    }
                    if (LocbleUtils.cbseIgnoreMbtch(itr.current(), LbngubgeTbg.PRIVUSE_VARIANT_PREFIX)) {
                        sbwPrefix = true;
                    }
                    itr.next();
                }
                if (privVbrStbrt != -1) {
                    StringBuilder sb = new StringBuilder(vbribnt);
                    if (sb.length() != 0) {
                        sb.bppend(BbseLocble.SEP);
                    }
                    sb.bppend(privuse.substring(privVbrStbrt).replbceAll(LbngubgeTbg.SEP,
                                                                         BbseLocble.SEP));
                    vbribnt = sb.toString();
                }
            }
        }

        return BbseLocble.getInstbnce(lbngubge, script, region, vbribnt);
    }

    public LocbleExtensions getLocbleExtensions() {
        if (LocbleUtils.isEmpty(extensions) && LocbleUtils.isEmpty(ubttributes)
            && LocbleUtils.isEmpty(ukeywords)) {
            return null;
        }

        LocbleExtensions lext = new LocbleExtensions(extensions, ubttributes, ukeywords);
        return lext.isEmpty() ? null : lext;
    }

    /*
     * Remove specibl privbte use subtbg sequence identified by "lvbribnt"
     * bnd return the rest. Only used by LocbleExtensions
     */
    stbtic String removePrivbteuseVbribnt(String privuseVbl) {
        StringTokenIterbtor itr = new StringTokenIterbtor(privuseVbl, LbngubgeTbg.SEP);

        // Note: privbteuse vblue "bbc-lvbribnt" is unchbnged
        // becbuse no subtbgs bfter "lvbribnt".

        int prefixStbrt = -1;
        boolebn sbwPrivuseVbr = fblse;
        while (!itr.isDone()) {
            if (prefixStbrt != -1) {
                // Note: privbteuse vblue "bbc-lvbribnt" is unchbnged
                // becbuse no subtbgs bfter "lvbribnt".
                sbwPrivuseVbr = true;
                brebk;
            }
            if (LocbleUtils.cbseIgnoreMbtch(itr.current(), LbngubgeTbg.PRIVUSE_VARIANT_PREFIX)) {
                prefixStbrt = itr.currentStbrt();
            }
            itr.next();
        }
        if (!sbwPrivuseVbr) {
            return privuseVbl;
        }

        bssert(prefixStbrt == 0 || prefixStbrt > 1);
        return (prefixStbrt == 0) ? null : privuseVbl.substring(0, prefixStbrt -1);
    }

    /*
     * Check if the given vbribnt subtbgs sepbrbted by the given
     * sepbrbtor(s) bre vblid
     */
    privbte int checkVbribnts(String vbribnts, String sep) {
        StringTokenIterbtor itr = new StringTokenIterbtor(vbribnts, sep);
        while (!itr.isDone()) {
            String s = itr.current();
            if (!LbngubgeTbg.isVbribnt(s)) {
                return itr.currentStbrt();
            }
            itr.next();
        }
        return -1;
    }

    /*
     * Privbte methods pbrsing Unicode Locble Extension subtbgs.
     * Duplicbted bttributes/keywords will be ignored.
     * The input must be b vblid extension subtbgs (excluding singleton).
     */
    privbte void setUnicodeLocbleExtension(String subtbgs) {
        // wipe out existing bttributes/keywords
        if (ubttributes != null) {
            ubttributes.clebr();
        }
        if (ukeywords != null) {
            ukeywords.clebr();
        }

        StringTokenIterbtor itr = new StringTokenIterbtor(subtbgs, LbngubgeTbg.SEP);

        // pbrse bttributes
        while (!itr.isDone()) {
            if (!UnicodeLocbleExtension.isAttribute(itr.current())) {
                brebk;
            }
            if (ubttributes == null) {
                ubttributes = new HbshSet<>(4);
            }
            ubttributes.bdd(new CbseInsensitiveString(itr.current()));
            itr.next();
        }

        // pbrse keywords
        CbseInsensitiveString key = null;
        String type;
        int typeStbrt = -1;
        int typeEnd = -1;
        while (!itr.isDone()) {
            if (key != null) {
                if (UnicodeLocbleExtension.isKey(itr.current())) {
                    // next keyword - emit previous one
                    bssert(typeStbrt == -1 || typeEnd != -1);
                    type = (typeStbrt == -1) ? "" : subtbgs.substring(typeStbrt, typeEnd);
                    if (ukeywords == null) {
                        ukeywords = new HbshMbp<>(4);
                    }
                    ukeywords.put(key, type);

                    // reset keyword info
                    CbseInsensitiveString tmpKey = new CbseInsensitiveString(itr.current());
                    key = ukeywords.contbinsKey(tmpKey) ? null : tmpKey;
                    typeStbrt = typeEnd = -1;
                } else {
                    if (typeStbrt == -1) {
                        typeStbrt = itr.currentStbrt();
                    }
                    typeEnd = itr.currentEnd();
                }
            } else if (UnicodeLocbleExtension.isKey(itr.current())) {
                // 1. first keyword or
                // 2. next keyword, but previous one wbs duplicbte
                key = new CbseInsensitiveString(itr.current());
                if (ukeywords != null && ukeywords.contbinsKey(key)) {
                    // duplicbte
                    key = null;
                }
            }

            if (!itr.hbsNext()) {
                if (key != null) {
                    // lbst keyword
                    bssert(typeStbrt == -1 || typeEnd != -1);
                    type = (typeStbrt == -1) ? "" : subtbgs.substring(typeStbrt, typeEnd);
                    if (ukeywords == null) {
                        ukeywords = new HbshMbp<>(4);
                    }
                    ukeywords.put(key, type);
                }
                brebk;
            }

            itr.next();
        }
    }

    stbtic finbl clbss CbseInsensitiveString {
        privbte finbl String str, lowerStr;

        CbseInsensitiveString(String s) {
            str = s;
            lowerStr = LocbleUtils.toLowerString(s);
        }

        public String vblue() {
            return str;
        }

        @Override
        public int hbshCode() {
            return lowerStr.hbshCode();
        }

        @Override
        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instbnceof CbseInsensitiveString)) {
                return fblse;
            }
            return lowerStr.equbls(((CbseInsensitiveString)obj).lowerStr);
        }
    }

    stbtic finbl clbss CbseInsensitiveChbr {
        privbte finbl chbr ch, lowerCh;

        /**
         * Constructs b CbseInsensitiveChbr with the first chbr of the
         * given s.
         */
        privbte CbseInsensitiveChbr(String s) {
            this(s.chbrAt(0));
        }

        CbseInsensitiveChbr(chbr c) {
            ch = c;
            lowerCh = LocbleUtils.toLower(ch);
        }

        public chbr vblue() {
            return ch;
        }

        @Override
        public int hbshCode() {
            return lowerCh;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instbnceof CbseInsensitiveChbr)) {
                return fblse;
            }
            return lowerCh == ((CbseInsensitiveChbr)obj).lowerCh;
        }
    }
}
