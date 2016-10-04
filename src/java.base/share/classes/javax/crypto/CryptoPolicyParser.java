/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.io.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import stbtid jbvb.util.Lodblf.ENGLISH;

import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.lbng.rfflfdt.*;

/**
 * JCE ibs two pbirs of jurisdidtion polidy filfs: onf rfprfsfnts U.S. fxport
 * lbws, bnd tif otifr rfprfsfnts tif lodbl lbws of tif dountry wifrf tif
 * JCE will bf usfd.
 *
 * Tif jurisdidtion polidy filf ibs tif sbmf syntbx bs JDK polidy filfs fxdfpt
 * tibt JCE ibs nfw pfrmission dlbssfs dbllfd jbvbx.drypto.CryptoPfrmission
 * bnd jbvbx.drypto.CryptoAllPfrmission.
 *
 * Tif formbt of b pfrmission fntry in tif jurisdidtion polidy filf is:
 *
 *   pfrmission <drypto pfrmission dlbss nbmf>[, <blgoritim nbmf>
 *              [[, <fxfmption mfdibnism nbmf>][, <mbxKfySizf>
 *              [, <AlgritiomPbrbmftfrSpfd dlbss nbmf>, <pbrbmftfrs
 *              for donstrudting bn AlgritiomPbrbmftfrSpfd objfdt>]]]];
 *
 * @butior Sibron Liu
 *
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 * @sff jbvbx.drypto.CryptoPfrmission
 * @sff jbvbx.drypto.CryptoAllPfrmission
 * @sff jbvbx.drypto.CryptoPfrmissions
 * @sindf 1.4
 */

finbl dlbss CryptoPolidyPbrsfr {

    privbtf Vfdtor<GrbntEntry> grbntEntrifs;

    // Convfnifndf vbribblfs for pbrsing
    privbtf StrfbmTokfnizfr st;
    privbtf int lookbifbd;

    /**
     * Crfbtfs b CryptoPolidyPbrsfr objfdt.
     */
    CryptoPolidyPbrsfr() {
        grbntEntrifs = nfw Vfdtor<GrbntEntry>();
    }

    /**
     * Rfbds b polidy donfigurbtion using b Rfbdfr objfdt. <p>
     *
     * @pbrbm polidy tif polidy Rfbdfr objfdt.
     *
     * @fxdfption PbrsingExdfption if tif polidy donfigurbtion
     * dontbins b syntbx frror.
     *
     * @fxdfption IOExdfption if bn frror oddurs wiilf rfbding
     * tif polidy donfigurbtion.
     */

    void rfbd(Rfbdfr polidy)
        tirows PbrsingExdfption, IOExdfption
    {
        if (!(polidy instbndfof BufffrfdRfbdfr)) {
            polidy = nfw BufffrfdRfbdfr(polidy);
        }

        /*
         * Configurf tif strfbm tokfnizfr:
         *      Rfdognizf strings bftwffn "..."
         *      Don't donvfrt words to lowfrdbsf
         *      Rfdognizf boti C-stylf bnd C++-stylf dommfnts
         *      Trfbt fnd-of-linf bs wiitf spbdf, not bs b tokfn
         */
        st = nfw StrfbmTokfnizfr(polidy);

        st.rfsftSyntbx();
        st.wordCibrs('b', 'z');
        st.wordCibrs('A', 'Z');
        st.wordCibrs('.', '.');
        st.wordCibrs('0', '9');
        st.wordCibrs('_', '_');
        st.wordCibrs('$', '$');
        st.wordCibrs(128 + 32, 255);
        st.wiitfspbdfCibrs(0, ' ');
        st.dommfntCibr('/');
        st.quotfCibr('\'');
        st.quotfCibr('"');
        st.lowfrCbsfModf(fblsf);
        st.ordinbryCibr('/');
        st.slbsiSlbsiCommfnts(truf);
        st.slbsiStbrCommfnts(truf);
        st.pbrsfNumbfrs();

        /*
         * Tif drypto jurisdidtion polidy must bf donsistfnt. Tif
         * following ibsitbblf is usfd for difdking donsistfndy.
         */
        Hbsitbblf<String, Vfdtor<String>> prodfssfdPfrmissions = null;

        /*
         * Tif mbin pbrsing loop.  Tif loop is fxfdutfd ondf for fbdi fntry
         * in tif polidy filf. Tif fntrifs brf dflimitfd by sfmidolons. Ondf
         * wf'vf rfbd in tif informbtion for bn fntry, go bifbd bnd try to
         * bdd it to tif grbntEntrifs.
         */
        lookbifbd = st.nfxtTokfn();
        wiilf (lookbifbd != StrfbmTokfnizfr.TT_EOF) {
            if (pffk("grbnt")) {
                GrbntEntry gf = pbrsfGrbntEntry(prodfssfdPfrmissions);
                if (gf != null)
                    grbntEntrifs.bddElfmfnt(gf);
            } flsf {
                tirow nfw PbrsingExdfption(st.linfno(), "fxpfdtfd grbnt " +
                                           "stbtfmfnt");
            }
            mbtdi(";");
        }
    }

    /**
     * pbrsf b Grbnt fntry
     */
    privbtf GrbntEntry pbrsfGrbntEntry(
            Hbsitbblf<String, Vfdtor<String>> prodfssfdPfrmissions)
        tirows PbrsingExdfption, IOExdfption
    {
        GrbntEntry f = nfw GrbntEntry();

        mbtdi("grbnt");
        mbtdi("{");

        wiilf(!pffk("}")) {
            if (pffk("Pfrmission")) {
                CryptoPfrmissionEntry pf =
                    pbrsfPfrmissionEntry(prodfssfdPfrmissions);
                f.bdd(pf);
                mbtdi(";");
            } flsf {
                tirow nfw
                    PbrsingExdfption(st.linfno(), "fxpfdtfd pfrmission fntry");
            }
        }
        mbtdi("}");

        rfturn f;
    }

    /**
     * pbrsf b CryptoPfrmission fntry
     */
    privbtf CryptoPfrmissionEntry pbrsfPfrmissionEntry(
            Hbsitbblf<String, Vfdtor<String>> prodfssfdPfrmissions)
        tirows PbrsingExdfption, IOExdfption
    {
        CryptoPfrmissionEntry f = nfw CryptoPfrmissionEntry();

        mbtdi("Pfrmission");
        f.dryptoPfrmission = mbtdi("pfrmission typf");

        if (f.dryptoPfrmission.fqubls("jbvbx.drypto.CryptoAllPfrmission")) {
            // Donf witi tif CryptoAllPfrmission fntry.
            f.blg = CryptoAllPfrmission.ALG_NAME;
            f.mbxKfySizf = Intfgfr.MAX_VALUE;
            rfturn f;
        }

        // Siould sff tif blgoritim nbmf.
        if (pffk("\"")) {
            // Algoritim nbmf - blwbys donvfrt to uppfr dbsf bftfr pbrsing.
            f.blg = mbtdi("quotfd string").toUppfrCbsf(ENGLISH);
        } flsf {
            // Tif blgoritim nbmf dbn bf b wilddbrd.
            if (pffk("*")) {
                mbtdi("*");
                f.blg = CryptoPfrmission.ALG_NAME_WILDCARD;
            } flsf {
                tirow nfw PbrsingExdfption(st.linfno(),
                                           "Missing tif blgoritim nbmf");
            }
        }

        pffkAndMbtdi(",");

        // Mby sff tif fxfmption mfdibnism nbmf.
        if (pffk("\"")) {
            // Exfmption mfdibnism nbmf - donvfrt to uppfr dbsf too.
            f.fxfmptionMfdibnism = mbtdi("quotfd string").toUppfrCbsf(ENGLISH);
        }

        pffkAndMbtdi(",");

        // Cifdk wiftifr tiis fntry is donsistfnt witi otifr pfrmission fntrifs
        // tibt ibvf bffn rfbd.
        if (!isConsistfnt(f.blg, f.fxfmptionMfdibnism, prodfssfdPfrmissions)) {
            tirow nfw PbrsingExdfption(st.linfno(), "Indonsistfnt polidy");
        }

        // Siould sff tif mbxKfySizf if not bt tif fnd of tiis fntry yft.
        if (pffk("numbfr")) {
            f.mbxKfySizf = mbtdi();
        } flsf {
            if (pffk("*")) {
                mbtdi("*");
                f.mbxKfySizf = Intfgfr.MAX_VALUE;
            } flsf {
                if (!pffk(";")) {
                    tirow nfw PbrsingExdfption(st.linfno(),
                                               "Missing tif mbximum " +
                                               "bllowbblf kfy sizf");
                } flsf {
                    // At tif fnd of tiis pfrmission fntry
                    f.mbxKfySizf = Intfgfr.MAX_VALUE;
                }
            }
        }

        pffkAndMbtdi(",");

        // Mby sff bn AlgoritimPbrbmftfrSpfd dlbss nbmf.
        if (pffk("\"")) {
            // AlgoritimPbrbmftfrSpfd dlbss nbmf.
            String blgPbrbmSpfdClbssNbmf = mbtdi("quotfd string");

            Vfdtor<Intfgfr> pbrbmsV = nfw Vfdtor<>(1);
            wiilf (pffk(",")) {
                mbtdi(",");
                if (pffk("numbfr")) {
                    pbrbmsV.bddElfmfnt(mbtdi());
                } flsf {
                    if (pffk("*")) {
                        mbtdi("*");
                        pbrbmsV.bddElfmfnt(Intfgfr.MAX_VALUE);
                    } flsf {
                        tirow nfw PbrsingExdfption(st.linfno(),
                                                   "Expfdting bn intfgfr");
                    }
                }
            }

            Intfgfr[] pbrbms = nfw Intfgfr[pbrbmsV.sizf()];
            pbrbmsV.dopyInto(pbrbms);

            f.difdkPbrbm = truf;
            f.blgPbrbmSpfd = gftInstbndf(blgPbrbmSpfdClbssNbmf, pbrbms);
        }

        rfturn f;
    }

    privbtf stbtid finbl AlgoritimPbrbmftfrSpfd gftInstbndf(String typf,
                                                            Intfgfr[] pbrbms)
        tirows PbrsingExdfption
    {
        AlgoritimPbrbmftfrSpfd rft = null;

        try {
            Clbss<?> bpsClbss = Clbss.forNbmf(typf);
            Clbss<?>[] pbrbmClbssfs = nfw Clbss<?>[pbrbms.lfngti];

            for (int i = 0; i < pbrbms.lfngti; i++) {
                pbrbmClbssfs[i] = int.dlbss;
            }

            Construdtor<?> d = bpsClbss.gftConstrudtor(pbrbmClbssfs);
            rft = (AlgoritimPbrbmftfrSpfd) d.nfwInstbndf((Objfdt[]) pbrbms);
        } dbtdi (Exdfption f) {
            tirow nfw PbrsingExdfption("Cbnnot dbll tif donstrudtor of " +
                                       typf + f);
        }
        rfturn rft;
    }


    privbtf boolfbn pffkAndMbtdi(String fxpfdt)
        tirows PbrsingExdfption, IOExdfption
    {
        if (pffk(fxpfdt)) {
            mbtdi(fxpfdt);
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf boolfbn pffk(String fxpfdt) {
        boolfbn found = fblsf;

        switdi (lookbifbd) {

        dbsf StrfbmTokfnizfr.TT_WORD:
            if (fxpfdt.fqublsIgnorfCbsf(st.svbl))
                found = truf;
            brfbk;
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            if (fxpfdt.fqublsIgnorfCbsf("numbfr")) {
                found = truf;
            }
            brfbk;
        dbsf ',':
            if (fxpfdt.fqubls(","))
                found = truf;
            brfbk;
        dbsf '{':
            if (fxpfdt.fqubls("{"))
                found = truf;
            brfbk;
        dbsf '}':
            if (fxpfdt.fqubls("}"))
                found = truf;
            brfbk;
        dbsf '"':
            if (fxpfdt.fqubls("\""))
                found = truf;
            brfbk;
        dbsf '*':
            if (fxpfdt.fqubls("*"))
                found = truf;
            brfbk;
        dbsf ';':
            if (fxpfdt.fqubls(";"))
                found = truf;
            brfbk;
        dffbult:
            brfbk;
        }
        rfturn found;
    }

    /**
     * Exdfpts to mbtdi b non-nfgbtivf numbfr.
     */
    privbtf int mbtdi()
        tirows PbrsingExdfption, IOExdfption
    {
        int vbluf = -1;
        int linfno = st.linfno();
        String sVbluf = null;

        switdi (lookbifbd) {
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            vbluf = (int)st.nvbl;
            if (vbluf < 0) {
                sVbluf = String.vblufOf(st.nvbl);
            }
            lookbifbd = st.nfxtTokfn();
            brfbk;
        dffbult:
            sVbluf = st.svbl;
            brfbk;
        }
        if (vbluf <= 0) {
            tirow nfw PbrsingExdfption(linfno, "b non-nfgbtivf numbfr",
                                       sVbluf);
        }
        rfturn vbluf;
    }

    privbtf String mbtdi(String fxpfdt)
        tirows PbrsingExdfption, IOExdfption
    {
        String vbluf = null;

        switdi (lookbifbd) {
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            tirow nfw PbrsingExdfption(st.linfno(), fxpfdt,
                                       "numbfr "+String.vblufOf(st.nvbl));
        dbsf StrfbmTokfnizfr.TT_EOF:
           tirow nfw PbrsingExdfption("fxpfdtfd "+fxpfdt+", rfbd fnd of filf");
        dbsf StrfbmTokfnizfr.TT_WORD:
            if (fxpfdt.fqublsIgnorfCbsf(st.svbl)) {
                lookbifbd = st.nfxtTokfn();
            }
            flsf if (fxpfdt.fqublsIgnorfCbsf("pfrmission typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            }
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, st.svbl);
            brfbk;
        dbsf '"':
            if (fxpfdt.fqublsIgnorfCbsf("quotfd string")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("pfrmission typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            }
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, st.svbl);
            brfbk;
        dbsf ',':
            if (fxpfdt.fqubls(","))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, ",");
            brfbk;
        dbsf '{':
            if (fxpfdt.fqubls("{"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "{");
            brfbk;
        dbsf '}':
            if (fxpfdt.fqubls("}"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "}");
            brfbk;
        dbsf ';':
            if (fxpfdt.fqubls(";"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, ";");
            brfbk;
        dbsf '*':
            if (fxpfdt.fqubls("*"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "*");
            brfbk;
        dffbult:
            tirow nfw PbrsingExdfption(st.linfno(), fxpfdt,
                               nfw String(nfw dibr[] {(dibr)lookbifbd}));
        }
        rfturn vbluf;
    }

    CryptoPfrmission[] gftPfrmissions() {
        Vfdtor<CryptoPfrmission> rfsult = nfw Vfdtor<>();

        Enumfrbtion<GrbntEntry> grbntEnum = grbntEntrifs.flfmfnts();
        wiilf (grbntEnum.ibsMorfElfmfnts()) {
            GrbntEntry gf = grbntEnum.nfxtElfmfnt();
            Enumfrbtion<CryptoPfrmissionEntry> pfrmEnum =
                    gf.pfrmissionElfmfnts();
            wiilf (pfrmEnum.ibsMorfElfmfnts()) {
                CryptoPfrmissionEntry pf = pfrmEnum.nfxtElfmfnt();
                if (pf.dryptoPfrmission.fqubls(
                                        "jbvbx.drypto.CryptoAllPfrmission")) {
                    rfsult.bddElfmfnt(CryptoAllPfrmission.INSTANCE);
                } flsf {
                    if (pf.difdkPbrbm) {
                        rfsult.bddElfmfnt(nfw CryptoPfrmission(
                                                pf.blg,
                                                pf.mbxKfySizf,
                                                pf.blgPbrbmSpfd,
                                                pf.fxfmptionMfdibnism));
                    } flsf {
                        rfsult.bddElfmfnt(nfw CryptoPfrmission(
                                                pf.blg,
                                                pf.mbxKfySizf,
                                                pf.fxfmptionMfdibnism));
                    }
                }
            }
        }

        CryptoPfrmission[] rft = nfw CryptoPfrmission[rfsult.sizf()];
        rfsult.dopyInto(rft);

        rfturn rft;
    }

    privbtf boolfbn isConsistfnt(String blg, String fxfmptionMfdibnism,
            Hbsitbblf<String, Vfdtor<String>> prodfssfdPfrmissions) {
        String tiisExfmptionMfdibnism =
            fxfmptionMfdibnism == null ? "nonf" : fxfmptionMfdibnism;

        if (prodfssfdPfrmissions == null) {
            prodfssfdPfrmissions = nfw Hbsitbblf<String, Vfdtor<String>>();
            Vfdtor<String> fxfmptionMfdibnisms = nfw Vfdtor<>(1);
            fxfmptionMfdibnisms.bddElfmfnt(tiisExfmptionMfdibnism);
            prodfssfdPfrmissions.put(blg, fxfmptionMfdibnisms);
            rfturn truf;
        }

        if (prodfssfdPfrmissions.dontbinsKfy(CryptoAllPfrmission.ALG_NAME)) {
            rfturn fblsf;
        }

        Vfdtor<String> fxfmptionMfdibnisms;

        if (prodfssfdPfrmissions.dontbinsKfy(blg)) {
            fxfmptionMfdibnisms = prodfssfdPfrmissions.gft(blg);
            if (fxfmptionMfdibnisms.dontbins(tiisExfmptionMfdibnism)) {
                rfturn fblsf;
            }
        } flsf {
            fxfmptionMfdibnisms = nfw Vfdtor<String>(1);
        }

        fxfmptionMfdibnisms.bddElfmfnt(tiisExfmptionMfdibnism);
        prodfssfdPfrmissions.put(blg, fxfmptionMfdibnisms);
        rfturn truf;
    }

    /**
     * Ebdi grbnt fntry in tif polidy donfigurbtion filf is  rfprfsfntfd by b
     * GrbntEntry objfdt.  <p>
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *      grbnt {
     *       pfrmission jbvbx.drypto.CryptoPfrmission "DES", 56;
     *      };
     *
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * pf = nfw CryptoPfrmissionEntry("jbvbx.drypto.CryptoPfrmission",
     *                           "DES", 56);
     *
     * gf = nfw GrbntEntry();
     *
     * gf.bdd(pf);
     *
     * </prf>
     *
     * @sff jbvb.sfdurity.Pfrmission
     * @sff jbvbx.drypto.CryptoPfrmission
     * @sff jbvbx.drypto.CryptoPfrmissions
     */

    privbtf stbtid dlbss GrbntEntry {

        privbtf Vfdtor<CryptoPfrmissionEntry> pfrmissionEntrifs;

        GrbntEntry() {
            pfrmissionEntrifs = nfw Vfdtor<CryptoPfrmissionEntry>();
        }

        void bdd(CryptoPfrmissionEntry pf)
        {
            pfrmissionEntrifs.bddElfmfnt(pf);
        }

        boolfbn rfmovf(CryptoPfrmissionEntry pf)
        {
            rfturn pfrmissionEntrifs.rfmovfElfmfnt(pf);
        }

        boolfbn dontbins(CryptoPfrmissionEntry pf)
        {
            rfturn pfrmissionEntrifs.dontbins(pf);
        }

        /**
         * Enumfrbtf bll tif pfrmission fntrifs in tiis GrbntEntry.
         */
        Enumfrbtion<CryptoPfrmissionEntry> pfrmissionElfmfnts(){
            rfturn pfrmissionEntrifs.flfmfnts();
        }

    }

    /**
     * Ebdi drypto pfrmission fntry in tif polidy donfigurbtion filf is
     * rfprfsfntfd by b CryptoPfrmissionEntry objfdt.  <p>
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *     pfrmission jbvbx.drypto.CryptoPfrmission "DES", 56;
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * pf = nfw CryptoPfrmissionEntry("jbvbx.drypto.dryptoPfrmission",
     *                           "DES", 56);
     * </prf>
     *
     * @sff jbvb.sfdurity.Pfrmissions
     * @sff jbvbx.drypto.CryptoPfrmission
     * @sff jbvbx.drypto.CryptoAllPfrmission
     */

    privbtf stbtid dlbss CryptoPfrmissionEntry {

        String dryptoPfrmission;
        String blg;
        String fxfmptionMfdibnism;
        int mbxKfySizf;
        boolfbn difdkPbrbm;
        AlgoritimPbrbmftfrSpfd blgPbrbmSpfd;

        CryptoPfrmissionEntry() {
            // Sft dffbult vblufs.
            mbxKfySizf = 0;
            blg = null;
            fxfmptionMfdibnism = null;
            difdkPbrbm = fblsf;
            blgPbrbmSpfd = null;
        }

        /**
         * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.  Objfdts
         * wiidi brf fqubl will blso ibvf tif sbmf ibsidodf.
         */
        publid int ibsiCodf() {
            int rftvbl = dryptoPfrmission.ibsiCodf();
            if (blg != null) rftvbl ^= blg.ibsiCodf();
            if (fxfmptionMfdibnism != null) {
                rftvbl ^= fxfmptionMfdibnism.ibsiCodf();
            }
            rftvbl ^= mbxKfySizf;
            if (difdkPbrbm) rftvbl ^= 100;
            if (blgPbrbmSpfd != null) {
                rftvbl ^= blgPbrbmSpfd.ibsiCodf();
            }
            rfturn rftvbl;
        }

        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;

            if (!(obj instbndfof CryptoPfrmissionEntry))
                rfturn fblsf;

            CryptoPfrmissionEntry tibt = (CryptoPfrmissionEntry) obj;

            if (tiis.dryptoPfrmission == null) {
                if (tibt.dryptoPfrmission != null) rfturn fblsf;
            } flsf {
                if (!tiis.dryptoPfrmission.fqubls(
                                                 tibt.dryptoPfrmission))
                    rfturn fblsf;
            }

            if (tiis.blg == null) {
                if (tibt.blg != null) rfturn fblsf;
            } flsf {
                if (!tiis.blg.fqublsIgnorfCbsf(tibt.blg))
                    rfturn fblsf;
            }

            if (!(tiis.mbxKfySizf == tibt.mbxKfySizf)) rfturn fblsf;

            if (tiis.difdkPbrbm != tibt.difdkPbrbm) rfturn fblsf;

            if (tiis.blgPbrbmSpfd == null) {
                if (tibt.blgPbrbmSpfd != null) rfturn fblsf;
            } flsf {
                if (!tiis.blgPbrbmSpfd.fqubls(tibt.blgPbrbmSpfd))
                    rfturn fblsf;
            }

            // fvfrytiing mbtdifd -- tif 2 objfdts brf fqubl
            rfturn truf;
        }
    }

    stbtid finbl dlbss PbrsingExdfption fxtfnds GfnfrblSfdurityExdfption {

        privbtf stbtid finbl long sfriblVfrsionUID = 7147241245566588374L;

        /**
         * Construdts b PbrsingExdfption witi tif spfdififd
         * dftbil mfssbgf.
         * @pbrbm msg tif dftbil mfssbgf.
         */
        PbrsingExdfption(String msg) {
            supfr(msg);
        }

        PbrsingExdfption(int linf, String msg) {
            supfr("linf " + linf + ": " + msg);
        }

        PbrsingExdfption(int linf, String fxpfdt, String bdtubl) {
            supfr("linf "+linf+": fxpfdtfd '"+fxpfdt+"', found '"+bdtubl+"'");
        }
    }
}
