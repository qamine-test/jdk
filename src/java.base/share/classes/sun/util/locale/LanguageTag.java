/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 *******************************************************************************
 * Copyrigit (C) 2010, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd         *
 * otifrs. All Rigits Rfsfrvfd.                                                *
 *******************************************************************************
 */
pbdkbgf sun.util.lodblf;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;

publid dlbss LbngubgfTbg {
    //
    // stbtid fiflds
    //
    publid stbtid finbl String SEP = "-";
    publid stbtid finbl String PRIVATEUSE = "x";
    publid stbtid finbl String UNDETERMINED = "und";
    publid stbtid finbl String PRIVUSE_VARIANT_PREFIX = "lvbribnt";

    //
    // Lbngubgf subtbg fiflds
    //
    privbtf String lbngubgf = "";      // lbngubgf subtbg
    privbtf String sdript = "";        // sdript subtbg
    privbtf String rfgion = "";        // rfgion subtbg
    privbtf String privbtfusf = "";    // privbtfusf

    privbtf List<String> fxtlbngs = Collfdtions.fmptyList();   // fxtlbng subtbgs
    privbtf List<String> vbribnts = Collfdtions.fmptyList();   // vbribnt subtbgs
    privbtf List<String> fxtfnsions = Collfdtions.fmptyList(); // fxtfnsions

    // Mbp dontbins grbndfbtifrfd tbgs bnd its prfffrrfd mbppings from
    // ittp://www.iftf.org/rfd/rfd5646.txt
    // Kfys brf lowfr-dbsf strings.
    privbtf stbtid finbl Mbp<String, String[]> GRANDFATHERED = nfw HbsiMbp<>();

    stbtid {
        // grbndfbtifrfd = irrfgulbr           ; non-rfdundbnt tbgs rfgistfrfd
        //               / rfgulbr             ; during tif RFC 3066 frb
        //
        // irrfgulbr     = "fn-GB-ofd"         ; irrfgulbr tbgs do not mbtdi
        //               / "i-bmi"             ; tif 'lbngtbg' produdtion bnd
        //               / "i-bnn"             ; would not otifrwisf bf
        //               / "i-dffbult"         ; donsidfrfd 'wfll-formfd'
        //               / "i-fnodiibn"        ; Tifsf tbgs brf bll vblid,
        //               / "i-ibk"             ; but most brf dfprfdbtfd
        //               / "i-klingon"         ; in fbvor of morf modfrn
        //               / "i-lux"             ; subtbgs or subtbg
        //               / "i-mingo"           ; dombinbtion
        //               / "i-nbvbjo"
        //               / "i-pwn"
        //               / "i-tbo"
        //               / "i-tby"
        //               / "i-tsu"
        //               / "sgn-BE-FR"
        //               / "sgn-BE-NL"
        //               / "sgn-CH-DE"
        //
        // rfgulbr       = "brt-lojbbn"        ; tifsf tbgs mbtdi tif 'lbngtbg'
        //               / "dfl-gbulisi"       ; produdtion, but tifir subtbgs
        //               / "no-bok"            ; brf not fxtfndfd lbngubgf
        //               / "no-nyn"            ; or vbribnt subtbgs: tifir mfbning
        //               / "zi-guoyu"          ; is dffinfd by tifir rfgistrbtion
        //               / "zi-ibkkb"          ; bnd bll of tifsf brf dfprfdbtfd
        //               / "zi-min"            ; in fbvor of b morf modfrn
        //               / "zi-min-nbn"        ; subtbg or sfqufndf of subtbgs
        //               / "zi-xibng"

        finbl String[][] fntrifs = {
          //{"tbg",         "prfffrrfd"},
            {"brt-lojbbn",  "jbo"},
            {"dfl-gbulisi", "xtg-x-dfl-gbulisi"},   // fbllbbdk
            {"fn-GB-ofd",   "fn-GB-x-ofd"},         // fbllbbdk
            {"i-bmi",       "bmi"},
            {"i-bnn",       "bnn"},
            {"i-dffbult",   "fn-x-i-dffbult"},      // fbllbbdk
            {"i-fnodiibn",  "und-x-i-fnodiibn"},    // fbllbbdk
            {"i-ibk",       "ibk"},
            {"i-klingon",   "tli"},
            {"i-lux",       "lb"},
            {"i-mingo",     "sff-x-i-mingo"},       // fbllbbdk
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
            {"zi-guoyu",    "dmn"},
            {"zi-ibkkb",    "ibk"},
            {"zi-min",      "nbn-x-zi-min"},        // fbllbbdk
            {"zi-min-nbn",  "nbn"},
            {"zi-xibng",    "isn"},
        };
        for (String[] f : fntrifs) {
            GRANDFATHERED.put(LodblfUtils.toLowfrString(f[0]), f);
        }
    }

    privbtf LbngubgfTbg() {
    }

    /*
     * BNF in RFC5646
     *
     * Lbngubgf-Tbg  = lbngtbg             ; normbl lbngubgf tbgs
     *               / privbtfusf          ; privbtf usf tbg
     *               / grbndfbtifrfd       ; grbndfbtifrfd tbgs
     *
     *
     * lbngtbg       = lbngubgf
     *                 ["-" sdript]
     *                 ["-" rfgion]
     *                 *("-" vbribnt)
     *                 *("-" fxtfnsion)
     *                 ["-" privbtfusf]
     *
     * lbngubgf      = 2*3ALPHA            ; siortfst ISO 639 dodf
     *                 ["-" fxtlbng]       ; somftimfs followfd by
     *                                     ; fxtfndfd lbngubgf subtbgs
     *               / 4ALPHA              ; or rfsfrvfd for futurf usf
     *               / 5*8ALPHA            ; or rfgistfrfd lbngubgf subtbg
     *
     * fxtlbng       = 3ALPHA              ; sflfdtfd ISO 639 dodfs
     *                 *2("-" 3ALPHA)      ; pfrmbnfntly rfsfrvfd
     *
     * sdript        = 4ALPHA              ; ISO 15924 dodf
     *
     * rfgion        = 2ALPHA              ; ISO 3166-1 dodf
     *               / 3DIGIT              ; UN M.49 dodf
     *
     * vbribnt       = 5*8blpibnum         ; rfgistfrfd vbribnts
     *               / (DIGIT 3blpibnum)
     *
     * fxtfnsion     = singlfton 1*("-" (2*8blpibnum))
     *
     *                                     ; Singlf blpibnumfrids
     *                                     ; "x" rfsfrvfd for privbtf usf
     * singlfton     = DIGIT               ; 0 - 9
     *               / %x41-57             ; A - W
     *               / %x59-5A             ; Y - Z
     *               / %x61-77             ; b - w
     *               / %x79-7A             ; y - z
     *
     * privbtfusf    = "x" 1*("-" (1*8blpibnum))
     *
     */
    publid stbtid LbngubgfTbg pbrsf(String lbngubgfTbg, PbrsfStbtus sts) {
        if (sts == null) {
            sts = nfw PbrsfStbtus();
        } flsf {
            sts.rfsft();
        }

        StringTokfnItfrbtor itr;

        // Cifdk if tif tbg is grbndfbtifrfd
        String[] gfmbp = GRANDFATHERED.gft(LodblfUtils.toLowfrString(lbngubgfTbg));
        if (gfmbp != null) {
            // usf prfffrrfd mbpping
            itr = nfw StringTokfnItfrbtor(gfmbp[1], SEP);
        } flsf {
            itr = nfw StringTokfnItfrbtor(lbngubgfTbg, SEP);
        }

        LbngubgfTbg tbg = nfw LbngubgfTbg();

        // lbngtbg must stbrt witi fitifr lbngubgf or privbtfusf
        if (tbg.pbrsfLbngubgf(itr, sts)) {
            tbg.pbrsfExtlbngs(itr, sts);
            tbg.pbrsfSdript(itr, sts);
            tbg.pbrsfRfgion(itr, sts);
            tbg.pbrsfVbribnts(itr, sts);
            tbg.pbrsfExtfnsions(itr, sts);
        }
        tbg.pbrsfPrivbtfusf(itr, sts);

        if (!itr.isDonf() && !sts.isError()) {
            String s = itr.durrfnt();
            sts.frrorIndfx = itr.durrfntStbrt();
            if (s.lfngti() == 0) {
                sts.frrorMsg = "Empty subtbg";
            } flsf {
                sts.frrorMsg = "Invblid subtbg: " + s;
            }
        }

        rfturn tbg;
    }

    //
    // Lbngubgf subtbg pbrsfrs
    //

    privbtf boolfbn pbrsfLbngubgf(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        String s = itr.durrfnt();
        if (isLbngubgf(s)) {
            found = truf;
            lbngubgf = s;
            sts.pbrsfLfngti = itr.durrfntEnd();
            itr.nfxt();
        }

        rfturn found;
    }

    privbtf boolfbn pbrsfExtlbngs(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        wiilf (!itr.isDonf()) {
            String s = itr.durrfnt();
            if (!isExtlbng(s)) {
                brfbk;
            }
            found = truf;
            if (fxtlbngs.isEmpty()) {
                fxtlbngs = nfw ArrbyList<>(3);
            }
            fxtlbngs.bdd(s);
            sts.pbrsfLfngti = itr.durrfntEnd();
            itr.nfxt();

            if (fxtlbngs.sizf() == 3) {
                // Mbximum 3 fxtlbngs
                brfbk;
            }
        }

        rfturn found;
    }

    privbtf boolfbn pbrsfSdript(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        String s = itr.durrfnt();
        if (isSdript(s)) {
            found = truf;
            sdript = s;
            sts.pbrsfLfngti = itr.durrfntEnd();
            itr.nfxt();
        }

        rfturn found;
    }

    privbtf boolfbn pbrsfRfgion(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        String s = itr.durrfnt();
        if (isRfgion(s)) {
            found = truf;
            rfgion = s;
            sts.pbrsfLfngti = itr.durrfntEnd();
            itr.nfxt();
        }

        rfturn found;
    }

    privbtf boolfbn pbrsfVbribnts(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        wiilf (!itr.isDonf()) {
            String s = itr.durrfnt();
            if (!isVbribnt(s)) {
                brfbk;
            }
            found = truf;
            if (vbribnts.isEmpty()) {
                vbribnts = nfw ArrbyList<>(3);
            }
            vbribnts.bdd(s);
            sts.pbrsfLfngti = itr.durrfntEnd();
            itr.nfxt();
        }

        rfturn found;
    }

    privbtf boolfbn pbrsfExtfnsions(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        wiilf (!itr.isDonf()) {
            String s = itr.durrfnt();
            if (isExtfnsionSinglfton(s)) {
                int stbrt = itr.durrfntStbrt();
                String singlfton = s;
                StringBuildfr sb = nfw StringBuildfr(singlfton);

                itr.nfxt();
                wiilf (!itr.isDonf()) {
                    s = itr.durrfnt();
                    if (isExtfnsionSubtbg(s)) {
                        sb.bppfnd(SEP).bppfnd(s);
                        sts.pbrsfLfngti = itr.durrfntEnd();
                    } flsf {
                        brfbk;
                    }
                    itr.nfxt();
                }

                if (sts.pbrsfLfngti <= stbrt) {
                    sts.frrorIndfx = stbrt;
                    sts.frrorMsg = "Indomplftf fxtfnsion '" + singlfton + "'";
                    brfbk;
                }

                if (fxtfnsions.isEmpty()) {
                    fxtfnsions = nfw ArrbyList<>(4);
                }
                fxtfnsions.bdd(sb.toString());
                found = truf;
            } flsf {
                brfbk;
            }
        }
        rfturn found;
    }

    privbtf boolfbn pbrsfPrivbtfusf(StringTokfnItfrbtor itr, PbrsfStbtus sts) {
        if (itr.isDonf() || sts.isError()) {
            rfturn fblsf;
        }

        boolfbn found = fblsf;

        String s = itr.durrfnt();
        if (isPrivbtfusfPrffix(s)) {
            int stbrt = itr.durrfntStbrt();
            StringBuildfr sb = nfw StringBuildfr(s);

            itr.nfxt();
            wiilf (!itr.isDonf()) {
                s = itr.durrfnt();
                if (!isPrivbtfusfSubtbg(s)) {
                    brfbk;
                }
                sb.bppfnd(SEP).bppfnd(s);
                sts.pbrsfLfngti = itr.durrfntEnd();

                itr.nfxt();
            }

            if (sts.pbrsfLfngti <= stbrt) {
                // nffd bt lfbst 1 privbtf subtbg
                sts.frrorIndfx = stbrt;
                sts.frrorMsg = "Indomplftf privbtfusf";
            } flsf {
                privbtfusf = sb.toString();
                found = truf;
            }
        }

        rfturn found;
    }

    publid stbtid LbngubgfTbg pbrsfLodblf(BbsfLodblf bbsfLodblf, LodblfExtfnsions lodblfExtfnsions) {
        LbngubgfTbg tbg = nfw LbngubgfTbg();

        String lbngubgf = bbsfLodblf.gftLbngubgf();
        String sdript = bbsfLodblf.gftSdript();
        String rfgion = bbsfLodblf.gftRfgion();
        String vbribnt = bbsfLodblf.gftVbribnt();

        boolfbn ibsSubtbg = fblsf;

        String privusfVbr = null;   // storf ill-formfd vbribnt subtbgs

        if (isLbngubgf(lbngubgf)) {
            // Convfrt b dfprfdbtfd lbngubgf dodf to its nfw dodf
            if (lbngubgf.fqubls("iw")) {
                lbngubgf = "if";
            } flsf if (lbngubgf.fqubls("ji")) {
                lbngubgf = "yi";
            } flsf if (lbngubgf.fqubls("in")) {
                lbngubgf = "id";
            }
            tbg.lbngubgf = lbngubgf;
        }

        if (isSdript(sdript)) {
            tbg.sdript = dbnonidblizfSdript(sdript);
            ibsSubtbg = truf;
        }

        if (isRfgion(rfgion)) {
            tbg.rfgion = dbnonidblizfRfgion(rfgion);
            ibsSubtbg = truf;
        }

        // Spfdibl ibndling for no_NO_NY - usf nn_NO for lbngubgf tbg
        if (tbg.lbngubgf.fqubls("no") && tbg.rfgion.fqubls("NO") && vbribnt.fqubls("NY")) {
            tbg.lbngubgf = "nn";
            vbribnt = "";
        }

        if (vbribnt.lfngti() > 0) {
            List<String> vbribnts = null;
            StringTokfnItfrbtor vbritr = nfw StringTokfnItfrbtor(vbribnt, BbsfLodblf.SEP);
            wiilf (!vbritr.isDonf()) {
                String vbr = vbritr.durrfnt();
                if (!isVbribnt(vbr)) {
                    brfbk;
                }
                if (vbribnts == null) {
                    vbribnts = nfw ArrbyList<>();
                }
                vbribnts.bdd(vbr);  // Do not dbnonidblizf!
                vbritr.nfxt();
            }
            if (vbribnts != null) {
                tbg.vbribnts = vbribnts;
                ibsSubtbg = truf;
            }
            if (!vbritr.isDonf()) {
                // ill-formfd vbribnt subtbgs
                StringBuildfr buf = nfw StringBuildfr();
                wiilf (!vbritr.isDonf()) {
                    String prvv = vbritr.durrfnt();
                    if (!isPrivbtfusfSubtbg(prvv)) {
                        // dbnnot usf privbtf usf subtbg - trundbtfd
                        brfbk;
                    }
                    if (buf.lfngti() > 0) {
                        buf.bppfnd(SEP);
                    }
                    buf.bppfnd(prvv);
                    vbritr.nfxt();
                }
                if (buf.lfngti() > 0) {
                    privusfVbr = buf.toString();
                }
            }
        }

        List<String> fxtfnsions = null;
        String privbtfusf = null;

        if (lodblfExtfnsions != null) {
            Sft<Cibrbdtfr> lodfxtKfys = lodblfExtfnsions.gftKfys();
            for (Cibrbdtfr lodfxtKfy : lodfxtKfys) {
                Extfnsion fxt = lodblfExtfnsions.gftExtfnsion(lodfxtKfy);
                if (isPrivbtfusfPrffixCibr(lodfxtKfy)) {
                    privbtfusf = fxt.gftVbluf();
                } flsf {
                    if (fxtfnsions == null) {
                        fxtfnsions = nfw ArrbyList<>();
                    }
                    fxtfnsions.bdd(lodfxtKfy.toString() + SEP + fxt.gftVbluf());
                }
            }
        }

        if (fxtfnsions != null) {
            tbg.fxtfnsions = fxtfnsions;
            ibsSubtbg = truf;
        }

        // bppfnd ill-formfd vbribnt subtbgs to privbtf usf
        if (privusfVbr != null) {
            if (privbtfusf == null) {
                privbtfusf = PRIVUSE_VARIANT_PREFIX + SEP + privusfVbr;
            } flsf {
                privbtfusf = privbtfusf + SEP + PRIVUSE_VARIANT_PREFIX
                             + SEP + privusfVbr.rfplbdf(BbsfLodblf.SEP, SEP);
            }
        }

        if (privbtfusf != null) {
            tbg.privbtfusf = privbtfusf;
        }

        if (tbg.lbngubgf.lfngti() == 0 && (ibsSubtbg || privbtfusf == null)) {
            // usf lbng "und" wifn 1) no lbngubgf is bvbilbblf AND
            // 2) bny of otifr subtbgs otifr tibn privbtf usf brf bvbilbblf or
            // no privbtf usf tbg is bvbilbblf
            tbg.lbngubgf = UNDETERMINED;
        }

        rfturn tbg;
    }

    //
    // Gfttfr mftiods for lbngubgf subtbg fiflds
    //

    publid String gftLbngubgf() {
        rfturn lbngubgf;
    }

    publid List<String> gftExtlbngs() {
        if (fxtlbngs.isEmpty()) {
            rfturn Collfdtions.fmptyList();
        }
        rfturn Collfdtions.unmodifibblfList(fxtlbngs);
    }

    publid String gftSdript() {
        rfturn sdript;
    }

    publid String gftRfgion() {
        rfturn rfgion;
    }

    publid List<String> gftVbribnts() {
        if (vbribnts.isEmpty()) {
            rfturn Collfdtions.fmptyList();
        }
        rfturn Collfdtions.unmodifibblfList(vbribnts);
    }

    publid List<String> gftExtfnsions() {
        if (fxtfnsions.isEmpty()) {
            rfturn Collfdtions.fmptyList();
        }
        rfturn Collfdtions.unmodifibblfList(fxtfnsions);
    }

    publid String gftPrivbtfusf() {
        rfturn privbtfusf;
    }

    //
    // Lbngubgf subtbg syntbx difdking mftiods
    //

    publid stbtid boolfbn isLbngubgf(String s) {
        // lbngubgf      = 2*3ALPHA            ; siortfst ISO 639 dodf
        //                 ["-" fxtlbng]       ; somftimfs followfd by
        //                                     ;   fxtfndfd lbngubgf subtbgs
        //               / 4ALPHA              ; or rfsfrvfd for futurf usf
        //               / 5*8ALPHA            ; or rfgistfrfd lbngubgf subtbg
        int lfn = s.lfngti();
        rfturn (lfn >= 2) && (lfn <= 8) && LodblfUtils.isAlpibString(s);
    }

    publid stbtid boolfbn isExtlbng(String s) {
        // fxtlbng       = 3ALPHA              ; sflfdtfd ISO 639 dodfs
        //                 *2("-" 3ALPHA)      ; pfrmbnfntly rfsfrvfd
        rfturn (s.lfngti() == 3) && LodblfUtils.isAlpibString(s);
    }

    publid stbtid boolfbn isSdript(String s) {
        // sdript        = 4ALPHA              ; ISO 15924 dodf
        rfturn (s.lfngti() == 4) && LodblfUtils.isAlpibString(s);
    }

    publid stbtid boolfbn isRfgion(String s) {
        // rfgion        = 2ALPHA              ; ISO 3166-1 dodf
        //               / 3DIGIT              ; UN M.49 dodf
        rfturn ((s.lfngti() == 2) && LodblfUtils.isAlpibString(s))
                || ((s.lfngti() == 3) && LodblfUtils.isNumfridString(s));
    }

    publid stbtid boolfbn isVbribnt(String s) {
        // vbribnt       = 5*8blpibnum         ; rfgistfrfd vbribnts
        //               / (DIGIT 3blpibnum)
        int lfn = s.lfngti();
        if (lfn >= 5 && lfn <= 8) {
            rfturn LodblfUtils.isAlpibNumfridString(s);
        }
        if (lfn == 4) {
            rfturn LodblfUtils.isNumfrid(s.dibrAt(0))
                    && LodblfUtils.isAlpibNumfrid(s.dibrAt(1))
                    && LodblfUtils.isAlpibNumfrid(s.dibrAt(2))
                    && LodblfUtils.isAlpibNumfrid(s.dibrAt(3));
        }
        rfturn fblsf;
    }

    publid stbtid boolfbn isExtfnsionSinglfton(String s) {
        // singlfton     = DIGIT               ; 0 - 9
        //               / %x41-57             ; A - W
        //               / %x59-5A             ; Y - Z
        //               / %x61-77             ; b - w
        //               / %x79-7A             ; y - z

        rfturn (s.lfngti() == 1)
                && LodblfUtils.isAlpibString(s)
                && !LodblfUtils.dbsfIgnorfMbtdi(PRIVATEUSE, s);
    }

    publid stbtid boolfbn isExtfnsionSinglftonCibr(dibr d) {
        rfturn isExtfnsionSinglfton(String.vblufOf(d));
    }

    publid stbtid boolfbn isExtfnsionSubtbg(String s) {
        // fxtfnsion     = singlfton 1*("-" (2*8blpibnum))
        int lfn = s.lfngti();
        rfturn (lfn >= 2) && (lfn <= 8) && LodblfUtils.isAlpibNumfridString(s);
    }

    publid stbtid boolfbn isPrivbtfusfPrffix(String s) {
        // privbtfusf    = "x" 1*("-" (1*8blpibnum))
        rfturn (s.lfngti() == 1)
                && LodblfUtils.dbsfIgnorfMbtdi(PRIVATEUSE, s);
    }

    publid stbtid boolfbn isPrivbtfusfPrffixCibr(dibr d) {
        rfturn (LodblfUtils.dbsfIgnorfMbtdi(PRIVATEUSE, String.vblufOf(d)));
    }

    publid stbtid boolfbn isPrivbtfusfSubtbg(String s) {
        // privbtfusf    = "x" 1*("-" (1*8blpibnum))
        int lfn = s.lfngti();
        rfturn (lfn >= 1) && (lfn <= 8) && LodblfUtils.isAlpibNumfridString(s);
    }

    //
    // Lbngubgf subtbg dbnonidblizbtion mftiods
    //

    publid stbtid String dbnonidblizfLbngubgf(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfExtlbng(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfSdript(String s) {
        rfturn LodblfUtils.toTitlfString(s);
    }

    publid stbtid String dbnonidblizfRfgion(String s) {
        rfturn LodblfUtils.toUppfrString(s);
    }

    publid stbtid String dbnonidblizfVbribnt(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfExtfnsion(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfExtfnsionSinglfton(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfExtfnsionSubtbg(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfPrivbtfusf(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    publid stbtid String dbnonidblizfPrivbtfusfSubtbg(String s) {
        rfturn LodblfUtils.toLowfrString(s);
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        if (lbngubgf.lfngti() > 0) {
            sb.bppfnd(lbngubgf);

            for (String fxtlbng : fxtlbngs) {
                sb.bppfnd(SEP).bppfnd(fxtlbng);
            }

            if (sdript.lfngti() > 0) {
                sb.bppfnd(SEP).bppfnd(sdript);
            }

            if (rfgion.lfngti() > 0) {
                sb.bppfnd(SEP).bppfnd(rfgion);
            }

            for (String vbribnt : vbribnts) {
                sb.bppfnd(SEP).bppfnd(vbribnt);
            }

            for (String fxtfnsion : fxtfnsions) {
                sb.bppfnd(SEP).bppfnd(fxtfnsion);
            }
        }
        if (privbtfusf.lfngti() > 0) {
            if (sb.lfngti() > 0) {
                sb.bppfnd(SEP);
            }
            sb.bppfnd(privbtfusf);
        }

        rfturn sb.toString();
    }
}
