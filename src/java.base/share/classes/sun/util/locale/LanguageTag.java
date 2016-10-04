/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Copyright (C) 2010, Internbtionbl Business Mbchines Corporbtion bnd         *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

public clbss LbngubgeTbg {
    //
    // stbtic fields
    //
    public stbtic finbl String SEP = "-";
    public stbtic finbl String PRIVATEUSE = "x";
    public stbtic finbl String UNDETERMINED = "und";
    public stbtic finbl String PRIVUSE_VARIANT_PREFIX = "lvbribnt";

    //
    // Lbngubge subtbg fields
    //
    privbte String lbngubge = "";      // lbngubge subtbg
    privbte String script = "";        // script subtbg
    privbte String region = "";        // region subtbg
    privbte String privbteuse = "";    // privbteuse

    privbte List<String> extlbngs = Collections.emptyList();   // extlbng subtbgs
    privbte List<String> vbribnts = Collections.emptyList();   // vbribnt subtbgs
    privbte List<String> extensions = Collections.emptyList(); // extensions

    // Mbp contbins grbndfbthered tbgs bnd its preferred mbppings from
    // http://www.ietf.org/rfc/rfc5646.txt
    // Keys bre lower-cbse strings.
    privbte stbtic finbl Mbp<String, String[]> GRANDFATHERED = new HbshMbp<>();

    stbtic {
        // grbndfbthered = irregulbr           ; non-redundbnt tbgs registered
        //               / regulbr             ; during the RFC 3066 erb
        //
        // irregulbr     = "en-GB-oed"         ; irregulbr tbgs do not mbtch
        //               / "i-bmi"             ; the 'lbngtbg' production bnd
        //               / "i-bnn"             ; would not otherwise be
        //               / "i-defbult"         ; considered 'well-formed'
        //               / "i-enochibn"        ; These tbgs bre bll vblid,
        //               / "i-hbk"             ; but most bre deprecbted
        //               / "i-klingon"         ; in fbvor of more modern
        //               / "i-lux"             ; subtbgs or subtbg
        //               / "i-mingo"           ; combinbtion
        //               / "i-nbvbjo"
        //               / "i-pwn"
        //               / "i-tbo"
        //               / "i-tby"
        //               / "i-tsu"
        //               / "sgn-BE-FR"
        //               / "sgn-BE-NL"
        //               / "sgn-CH-DE"
        //
        // regulbr       = "brt-lojbbn"        ; these tbgs mbtch the 'lbngtbg'
        //               / "cel-gbulish"       ; production, but their subtbgs
        //               / "no-bok"            ; bre not extended lbngubge
        //               / "no-nyn"            ; or vbribnt subtbgs: their mebning
        //               / "zh-guoyu"          ; is defined by their registrbtion
        //               / "zh-hbkkb"          ; bnd bll of these bre deprecbted
        //               / "zh-min"            ; in fbvor of b more modern
        //               / "zh-min-nbn"        ; subtbg or sequence of subtbgs
        //               / "zh-xibng"

        finbl String[][] entries = {
          //{"tbg",         "preferred"},
            {"brt-lojbbn",  "jbo"},
            {"cel-gbulish", "xtg-x-cel-gbulish"},   // fbllbbck
            {"en-GB-oed",   "en-GB-x-oed"},         // fbllbbck
            {"i-bmi",       "bmi"},
            {"i-bnn",       "bnn"},
            {"i-defbult",   "en-x-i-defbult"},      // fbllbbck
            {"i-enochibn",  "und-x-i-enochibn"},    // fbllbbck
            {"i-hbk",       "hbk"},
            {"i-klingon",   "tlh"},
            {"i-lux",       "lb"},
            {"i-mingo",     "see-x-i-mingo"},       // fbllbbck
            {"i-nbvbjo",    "nv"},
            {"i-pwn",       "pwn"},
            {"i-tbo",       "tbo"},
            {"i-tby",       "tby"},
            {"i-tsu",       "tsu"},
            {"no-bok",      "nb"},
            {"no-nyn",      "nn"},
            {"sgn-BE-FR",   "sfb"},
            {"sgn-BE-NL",   "vgt"},
            {"sgn-CH-DE",   "sgg"},
            {"zh-guoyu",    "cmn"},
            {"zh-hbkkb",    "hbk"},
            {"zh-min",      "nbn-x-zh-min"},        // fbllbbck
            {"zh-min-nbn",  "nbn"},
            {"zh-xibng",    "hsn"},
        };
        for (String[] e : entries) {
            GRANDFATHERED.put(LocbleUtils.toLowerString(e[0]), e);
        }
    }

    privbte LbngubgeTbg() {
    }

    /*
     * BNF in RFC5646
     *
     * Lbngubge-Tbg  = lbngtbg             ; normbl lbngubge tbgs
     *               / privbteuse          ; privbte use tbg
     *               / grbndfbthered       ; grbndfbthered tbgs
     *
     *
     * lbngtbg       = lbngubge
     *                 ["-" script]
     *                 ["-" region]
     *                 *("-" vbribnt)
     *                 *("-" extension)
     *                 ["-" privbteuse]
     *
     * lbngubge      = 2*3ALPHA            ; shortest ISO 639 code
     *                 ["-" extlbng]       ; sometimes followed by
     *                                     ; extended lbngubge subtbgs
     *               / 4ALPHA              ; or reserved for future use
     *               / 5*8ALPHA            ; or registered lbngubge subtbg
     *
     * extlbng       = 3ALPHA              ; selected ISO 639 codes
     *                 *2("-" 3ALPHA)      ; permbnently reserved
     *
     * script        = 4ALPHA              ; ISO 15924 code
     *
     * region        = 2ALPHA              ; ISO 3166-1 code
     *               / 3DIGIT              ; UN M.49 code
     *
     * vbribnt       = 5*8blphbnum         ; registered vbribnts
     *               / (DIGIT 3blphbnum)
     *
     * extension     = singleton 1*("-" (2*8blphbnum))
     *
     *                                     ; Single blphbnumerics
     *                                     ; "x" reserved for privbte use
     * singleton     = DIGIT               ; 0 - 9
     *               / %x41-57             ; A - W
     *               / %x59-5A             ; Y - Z
     *               / %x61-77             ; b - w
     *               / %x79-7A             ; y - z
     *
     * privbteuse    = "x" 1*("-" (1*8blphbnum))
     *
     */
    public stbtic LbngubgeTbg pbrse(String lbngubgeTbg, PbrseStbtus sts) {
        if (sts == null) {
            sts = new PbrseStbtus();
        } else {
            sts.reset();
        }

        StringTokenIterbtor itr;

        // Check if the tbg is grbndfbthered
        String[] gfmbp = GRANDFATHERED.get(LocbleUtils.toLowerString(lbngubgeTbg));
        if (gfmbp != null) {
            // use preferred mbpping
            itr = new StringTokenIterbtor(gfmbp[1], SEP);
        } else {
            itr = new StringTokenIterbtor(lbngubgeTbg, SEP);
        }

        LbngubgeTbg tbg = new LbngubgeTbg();

        // lbngtbg must stbrt with either lbngubge or privbteuse
        if (tbg.pbrseLbngubge(itr, sts)) {
            tbg.pbrseExtlbngs(itr, sts);
            tbg.pbrseScript(itr, sts);
            tbg.pbrseRegion(itr, sts);
            tbg.pbrseVbribnts(itr, sts);
            tbg.pbrseExtensions(itr, sts);
        }
        tbg.pbrsePrivbteuse(itr, sts);

        if (!itr.isDone() && !sts.isError()) {
            String s = itr.current();
            sts.errorIndex = itr.currentStbrt();
            if (s.length() == 0) {
                sts.errorMsg = "Empty subtbg";
            } else {
                sts.errorMsg = "Invblid subtbg: " + s;
            }
        }

        return tbg;
    }

    //
    // Lbngubge subtbg pbrsers
    //

    privbte boolebn pbrseLbngubge(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        String s = itr.current();
        if (isLbngubge(s)) {
            found = true;
            lbngubge = s;
            sts.pbrseLength = itr.currentEnd();
            itr.next();
        }

        return found;
    }

    privbte boolebn pbrseExtlbngs(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        while (!itr.isDone()) {
            String s = itr.current();
            if (!isExtlbng(s)) {
                brebk;
            }
            found = true;
            if (extlbngs.isEmpty()) {
                extlbngs = new ArrbyList<>(3);
            }
            extlbngs.bdd(s);
            sts.pbrseLength = itr.currentEnd();
            itr.next();

            if (extlbngs.size() == 3) {
                // Mbximum 3 extlbngs
                brebk;
            }
        }

        return found;
    }

    privbte boolebn pbrseScript(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        String s = itr.current();
        if (isScript(s)) {
            found = true;
            script = s;
            sts.pbrseLength = itr.currentEnd();
            itr.next();
        }

        return found;
    }

    privbte boolebn pbrseRegion(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        String s = itr.current();
        if (isRegion(s)) {
            found = true;
            region = s;
            sts.pbrseLength = itr.currentEnd();
            itr.next();
        }

        return found;
    }

    privbte boolebn pbrseVbribnts(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        while (!itr.isDone()) {
            String s = itr.current();
            if (!isVbribnt(s)) {
                brebk;
            }
            found = true;
            if (vbribnts.isEmpty()) {
                vbribnts = new ArrbyList<>(3);
            }
            vbribnts.bdd(s);
            sts.pbrseLength = itr.currentEnd();
            itr.next();
        }

        return found;
    }

    privbte boolebn pbrseExtensions(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        while (!itr.isDone()) {
            String s = itr.current();
            if (isExtensionSingleton(s)) {
                int stbrt = itr.currentStbrt();
                String singleton = s;
                StringBuilder sb = new StringBuilder(singleton);

                itr.next();
                while (!itr.isDone()) {
                    s = itr.current();
                    if (isExtensionSubtbg(s)) {
                        sb.bppend(SEP).bppend(s);
                        sts.pbrseLength = itr.currentEnd();
                    } else {
                        brebk;
                    }
                    itr.next();
                }

                if (sts.pbrseLength <= stbrt) {
                    sts.errorIndex = stbrt;
                    sts.errorMsg = "Incomplete extension '" + singleton + "'";
                    brebk;
                }

                if (extensions.isEmpty()) {
                    extensions = new ArrbyList<>(4);
                }
                extensions.bdd(sb.toString());
                found = true;
            } else {
                brebk;
            }
        }
        return found;
    }

    privbte boolebn pbrsePrivbteuse(StringTokenIterbtor itr, PbrseStbtus sts) {
        if (itr.isDone() || sts.isError()) {
            return fblse;
        }

        boolebn found = fblse;

        String s = itr.current();
        if (isPrivbteusePrefix(s)) {
            int stbrt = itr.currentStbrt();
            StringBuilder sb = new StringBuilder(s);

            itr.next();
            while (!itr.isDone()) {
                s = itr.current();
                if (!isPrivbteuseSubtbg(s)) {
                    brebk;
                }
                sb.bppend(SEP).bppend(s);
                sts.pbrseLength = itr.currentEnd();

                itr.next();
            }

            if (sts.pbrseLength <= stbrt) {
                // need bt lebst 1 privbte subtbg
                sts.errorIndex = stbrt;
                sts.errorMsg = "Incomplete privbteuse";
            } else {
                privbteuse = sb.toString();
                found = true;
            }
        }

        return found;
    }

    public stbtic LbngubgeTbg pbrseLocble(BbseLocble bbseLocble, LocbleExtensions locbleExtensions) {
        LbngubgeTbg tbg = new LbngubgeTbg();

        String lbngubge = bbseLocble.getLbngubge();
        String script = bbseLocble.getScript();
        String region = bbseLocble.getRegion();
        String vbribnt = bbseLocble.getVbribnt();

        boolebn hbsSubtbg = fblse;

        String privuseVbr = null;   // store ill-formed vbribnt subtbgs

        if (isLbngubge(lbngubge)) {
            // Convert b deprecbted lbngubge code to its new code
            if (lbngubge.equbls("iw")) {
                lbngubge = "he";
            } else if (lbngubge.equbls("ji")) {
                lbngubge = "yi";
            } else if (lbngubge.equbls("in")) {
                lbngubge = "id";
            }
            tbg.lbngubge = lbngubge;
        }

        if (isScript(script)) {
            tbg.script = cbnonicblizeScript(script);
            hbsSubtbg = true;
        }

        if (isRegion(region)) {
            tbg.region = cbnonicblizeRegion(region);
            hbsSubtbg = true;
        }

        // Specibl hbndling for no_NO_NY - use nn_NO for lbngubge tbg
        if (tbg.lbngubge.equbls("no") && tbg.region.equbls("NO") && vbribnt.equbls("NY")) {
            tbg.lbngubge = "nn";
            vbribnt = "";
        }

        if (vbribnt.length() > 0) {
            List<String> vbribnts = null;
            StringTokenIterbtor vbritr = new StringTokenIterbtor(vbribnt, BbseLocble.SEP);
            while (!vbritr.isDone()) {
                String vbr = vbritr.current();
                if (!isVbribnt(vbr)) {
                    brebk;
                }
                if (vbribnts == null) {
                    vbribnts = new ArrbyList<>();
                }
                vbribnts.bdd(vbr);  // Do not cbnonicblize!
                vbritr.next();
            }
            if (vbribnts != null) {
                tbg.vbribnts = vbribnts;
                hbsSubtbg = true;
            }
            if (!vbritr.isDone()) {
                // ill-formed vbribnt subtbgs
                StringBuilder buf = new StringBuilder();
                while (!vbritr.isDone()) {
                    String prvv = vbritr.current();
                    if (!isPrivbteuseSubtbg(prvv)) {
                        // cbnnot use privbte use subtbg - truncbted
                        brebk;
                    }
                    if (buf.length() > 0) {
                        buf.bppend(SEP);
                    }
                    buf.bppend(prvv);
                    vbritr.next();
                }
                if (buf.length() > 0) {
                    privuseVbr = buf.toString();
                }
            }
        }

        List<String> extensions = null;
        String privbteuse = null;

        if (locbleExtensions != null) {
            Set<Chbrbcter> locextKeys = locbleExtensions.getKeys();
            for (Chbrbcter locextKey : locextKeys) {
                Extension ext = locbleExtensions.getExtension(locextKey);
                if (isPrivbteusePrefixChbr(locextKey)) {
                    privbteuse = ext.getVblue();
                } else {
                    if (extensions == null) {
                        extensions = new ArrbyList<>();
                    }
                    extensions.bdd(locextKey.toString() + SEP + ext.getVblue());
                }
            }
        }

        if (extensions != null) {
            tbg.extensions = extensions;
            hbsSubtbg = true;
        }

        // bppend ill-formed vbribnt subtbgs to privbte use
        if (privuseVbr != null) {
            if (privbteuse == null) {
                privbteuse = PRIVUSE_VARIANT_PREFIX + SEP + privuseVbr;
            } else {
                privbteuse = privbteuse + SEP + PRIVUSE_VARIANT_PREFIX
                             + SEP + privuseVbr.replbce(BbseLocble.SEP, SEP);
            }
        }

        if (privbteuse != null) {
            tbg.privbteuse = privbteuse;
        }

        if (tbg.lbngubge.length() == 0 && (hbsSubtbg || privbteuse == null)) {
            // use lbng "und" when 1) no lbngubge is bvbilbble AND
            // 2) bny of other subtbgs other thbn privbte use bre bvbilbble or
            // no privbte use tbg is bvbilbble
            tbg.lbngubge = UNDETERMINED;
        }

        return tbg;
    }

    //
    // Getter methods for lbngubge subtbg fields
    //

    public String getLbngubge() {
        return lbngubge;
    }

    public List<String> getExtlbngs() {
        if (extlbngs.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifibbleList(extlbngs);
    }

    public String getScript() {
        return script;
    }

    public String getRegion() {
        return region;
    }

    public List<String> getVbribnts() {
        if (vbribnts.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifibbleList(vbribnts);
    }

    public List<String> getExtensions() {
        if (extensions.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifibbleList(extensions);
    }

    public String getPrivbteuse() {
        return privbteuse;
    }

    //
    // Lbngubge subtbg syntbx checking methods
    //

    public stbtic boolebn isLbngubge(String s) {
        // lbngubge      = 2*3ALPHA            ; shortest ISO 639 code
        //                 ["-" extlbng]       ; sometimes followed by
        //                                     ;   extended lbngubge subtbgs
        //               / 4ALPHA              ; or reserved for future use
        //               / 5*8ALPHA            ; or registered lbngubge subtbg
        int len = s.length();
        return (len >= 2) && (len <= 8) && LocbleUtils.isAlphbString(s);
    }

    public stbtic boolebn isExtlbng(String s) {
        // extlbng       = 3ALPHA              ; selected ISO 639 codes
        //                 *2("-" 3ALPHA)      ; permbnently reserved
        return (s.length() == 3) && LocbleUtils.isAlphbString(s);
    }

    public stbtic boolebn isScript(String s) {
        // script        = 4ALPHA              ; ISO 15924 code
        return (s.length() == 4) && LocbleUtils.isAlphbString(s);
    }

    public stbtic boolebn isRegion(String s) {
        // region        = 2ALPHA              ; ISO 3166-1 code
        //               / 3DIGIT              ; UN M.49 code
        return ((s.length() == 2) && LocbleUtils.isAlphbString(s))
                || ((s.length() == 3) && LocbleUtils.isNumericString(s));
    }

    public stbtic boolebn isVbribnt(String s) {
        // vbribnt       = 5*8blphbnum         ; registered vbribnts
        //               / (DIGIT 3blphbnum)
        int len = s.length();
        if (len >= 5 && len <= 8) {
            return LocbleUtils.isAlphbNumericString(s);
        }
        if (len == 4) {
            return LocbleUtils.isNumeric(s.chbrAt(0))
                    && LocbleUtils.isAlphbNumeric(s.chbrAt(1))
                    && LocbleUtils.isAlphbNumeric(s.chbrAt(2))
                    && LocbleUtils.isAlphbNumeric(s.chbrAt(3));
        }
        return fblse;
    }

    public stbtic boolebn isExtensionSingleton(String s) {
        // singleton     = DIGIT               ; 0 - 9
        //               / %x41-57             ; A - W
        //               / %x59-5A             ; Y - Z
        //               / %x61-77             ; b - w
        //               / %x79-7A             ; y - z

        return (s.length() == 1)
                && LocbleUtils.isAlphbString(s)
                && !LocbleUtils.cbseIgnoreMbtch(PRIVATEUSE, s);
    }

    public stbtic boolebn isExtensionSingletonChbr(chbr c) {
        return isExtensionSingleton(String.vblueOf(c));
    }

    public stbtic boolebn isExtensionSubtbg(String s) {
        // extension     = singleton 1*("-" (2*8blphbnum))
        int len = s.length();
        return (len >= 2) && (len <= 8) && LocbleUtils.isAlphbNumericString(s);
    }

    public stbtic boolebn isPrivbteusePrefix(String s) {
        // privbteuse    = "x" 1*("-" (1*8blphbnum))
        return (s.length() == 1)
                && LocbleUtils.cbseIgnoreMbtch(PRIVATEUSE, s);
    }

    public stbtic boolebn isPrivbteusePrefixChbr(chbr c) {
        return (LocbleUtils.cbseIgnoreMbtch(PRIVATEUSE, String.vblueOf(c)));
    }

    public stbtic boolebn isPrivbteuseSubtbg(String s) {
        // privbteuse    = "x" 1*("-" (1*8blphbnum))
        int len = s.length();
        return (len >= 1) && (len <= 8) && LocbleUtils.isAlphbNumericString(s);
    }

    //
    // Lbngubge subtbg cbnonicblizbtion methods
    //

    public stbtic String cbnonicblizeLbngubge(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizeExtlbng(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizeScript(String s) {
        return LocbleUtils.toTitleString(s);
    }

    public stbtic String cbnonicblizeRegion(String s) {
        return LocbleUtils.toUpperString(s);
    }

    public stbtic String cbnonicblizeVbribnt(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizeExtension(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizeExtensionSingleton(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizeExtensionSubtbg(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizePrivbteuse(String s) {
        return LocbleUtils.toLowerString(s);
    }

    public stbtic String cbnonicblizePrivbteuseSubtbg(String s) {
        return LocbleUtils.toLowerString(s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (lbngubge.length() > 0) {
            sb.bppend(lbngubge);

            for (String extlbng : extlbngs) {
                sb.bppend(SEP).bppend(extlbng);
            }

            if (script.length() > 0) {
                sb.bppend(SEP).bppend(script);
            }

            if (region.length() > 0) {
                sb.bppend(SEP).bppend(region);
            }

            for (String vbribnt : vbribnts) {
                sb.bppend(SEP).bppend(vbribnt);
            }

            for (String extension : extensions) {
                sb.bppend(SEP).bppend(extension);
            }
        }
        if (privbteuse.length() > 0) {
            if (sb.length() > 0) {
                sb.bppend(SEP);
            }
            sb.bppend(privbteuse);
        }

        return sb.toString();
    }
}
