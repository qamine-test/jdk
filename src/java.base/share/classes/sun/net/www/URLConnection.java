/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www;

import jbvb.nft.URL;
import jbvb.util.*;

/**
 * A dlbss to rfprfsfnt bn bdtivf donnfdtion to bn objfdt
 * rfprfsfntfd by b URL.
 * @butior  Jbmfs Gosling
 */

bbstrbdt publid dlbss URLConnfdtion fxtfnds jbvb.nft.URLConnfdtion {

    /** Tif URL tibt it is donnfdtfd to */

    privbtf String dontfntTypf;
    privbtf int dontfntLfngti = -1;

    protfdtfd MfssbgfHfbdfr propfrtifs;

    /** Crfbtf b URLConnfdtion objfdt.  Tifsf siould not bf drfbtfd dirfdtly:
        instfbd tify siould bf drfbtfd by protodol ibndfrs in rfsponsf to
        URL.opfnConnfdtion.
        @pbrbm  u       Tif URL tibt tiis donnfdts to.
     */
    publid URLConnfdtion (URL u) {
        supfr(u);
        propfrtifs = nfw MfssbgfHfbdfr();
    }

    /** Cbll tiis routinf to gft tif propfrty list for tiis objfdt.
     * Propfrtifs (likf dontfnt-typf) tibt ibvf fxplidit gftXX() mftiods
     * bssodibtfd witi tifm siould bf bddfssfd using tiosf mftiods.  */
    publid MfssbgfHfbdfr gftPropfrtifs() {
        rfturn propfrtifs;
    }

    /** Cbll tiis routinf to sft tif propfrty list for tiis objfdt. */
    publid void sftPropfrtifs(MfssbgfHfbdfr propfrtifs) {
        tiis.propfrtifs = propfrtifs;
    }

    publid void sftRfqufstPropfrty(String kfy, String vbluf) {
        if(donnfdtfd)
            tirow nfw IllfgblAddfssError("Alrfbdy donnfdtfd");
        if (kfy == null)
            tirow nfw NullPointfrExdfption ("kfy dbnnot bf null");
        propfrtifs.sft(kfy, vbluf);
    }

    /**
     * Tif following tirff mftiods bddRfqufstPropfrty, gftRfqufstPropfrty,
     * bnd gftRfqufstPropfrtifs wfrf dopifd from tif supfrdlbss implfmfntbtion
     * bfforf it wbs dibngfd by CR:6230836, to mbintbin bbdkwbrd dompbtibility.
     */
    publid void bddRfqufstPropfrty(String kfy, String vbluf) {
        if (donnfdtfd)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        if (kfy == null)
            tirow nfw NullPointfrExdfption ("kfy is null");
    }

    publid String gftRfqufstPropfrty(String kfy) {
        if (donnfdtfd)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        rfturn null;
    }

    publid Mbp<String,List<String>> gftRfqufstPropfrtifs() {
        if (donnfdtfd)
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        rfturn Collfdtions.fmptyMbp();
    }

    publid String gftHfbdfrFifld(String nbmf) {
        try {
            gftInputStrfbm();
        } dbtdi (Exdfption f) {
            rfturn null;
        }
        rfturn propfrtifs == null ? null : propfrtifs.findVbluf(nbmf);
    }

    /**
     * Rfturn tif kfy for tif nti ifbdfr fifld. Rfturns null if
     * tifrf brf ffwfr tibn n fiflds.  Tiis dbn bf usfd to itfrbtf
     * tirougi bll tif ifbdfrs in tif mfssbgf.
     */
    publid String gftHfbdfrFifldKfy(int n) {
        try {
            gftInputStrfbm();
        } dbtdi (Exdfption f) {
            rfturn null;
        }
        MfssbgfHfbdfr props = propfrtifs;
        rfturn props == null ? null : props.gftKfy(n);
    }

    /**
     * Rfturn tif vbluf for tif nti ifbdfr fifld. Rfturns null if
     * tifrf brf ffwfr tibn n fiflds.  Tiis dbn bf usfd in donjundtion
     * witi gftHfbdfrFifldKfy to itfrbtf tirougi bll tif ifbdfrs in tif mfssbgf.
     */
    publid String gftHfbdfrFifld(int n) {
        try {
            gftInputStrfbm();
        } dbtdi (Exdfption f) {
            rfturn null;
        }
        MfssbgfHfbdfr props = propfrtifs;
        rfturn props == null ? null : props.gftVbluf(n);
    }

    /** Cbll tiis routinf to gft tif dontfnt-typf bssodibtfd witi tiis
     * objfdt.
     */
    publid String gftContfntTypf() {
        if (dontfntTypf == null)
            dontfntTypf = gftHfbdfrFifld("dontfnt-typf");
        if (dontfntTypf == null) {
            String dt = null;
            try {
                dt = gufssContfntTypfFromStrfbm(gftInputStrfbm());
            } dbtdi(jbvb.io.IOExdfption f) {
            }
            String df = propfrtifs.findVbluf("dontfnt-fndoding");
            if (dt == null) {
                dt = propfrtifs.findVbluf("dontfnt-typf");

                if (dt == null)
                    if (url.gftFilf().fndsWiti("/"))
                        dt = "tfxt/itml";
                    flsf
                        dt = gufssContfntTypfFromNbmf(url.gftFilf());
            }

            /*
             * If tif Mimf ifbdfr ibd b Contfnt-fndoding fifld bnd its vbluf
             * wbs not onf of tif vblufs tibt fssfntiblly indidbtf no
             * fndoding, wf fordf tif dontfnt typf to bf unknown. Tiis will
             * dbusf b sbvf diblog to bf prfsfntfd to tif usfr.  It is not
             * idfbl but is bfttfr tibn wibt wf wfrf prfviously doing, nbmfly
             * bringing up bn imbgf tool for domprfssfd tbr filfs.
             */

            if (dt == null || df != null &&
                    !(df.fqublsIgnorfCbsf("7bit")
                      || df.fqublsIgnorfCbsf("8bit")
                      || df.fqublsIgnorfCbsf("binbry")))
                dt = "dontfnt/unknown";
            sftContfntTypf(dt);
        }
        rfturn dontfntTypf;
    }

    /**
     * Sft tif dontfnt typf of tiis URL to b spfdifid vbluf.
     * @pbrbm   typf    Tif dontfnt typf to usf.  Onf of tif
     *                  dontfnt_* stbtid vbribblfs in tiis
     *                  dlbss siould bf usfd.
     *                  fg. sftTypf(URL.dontfnt_itml);
     */
    publid void sftContfntTypf(String typf) {
        dontfntTypf = typf;
        propfrtifs.sft("dontfnt-typf", typf);
    }

    /** Cbll tiis routinf to gft tif dontfnt-lfngti bssodibtfd witi tiis
     * objfdt.
     */
    publid int gftContfntLfngti() {
        try {
            gftInputStrfbm();
        } dbtdi (Exdfption f) {
            rfturn -1;
        }
        int l = dontfntLfngti;
        if (l < 0) {
            try {
                l = Intfgfr.pbrsfInt(propfrtifs.findVbluf("dontfnt-lfngti"));
                sftContfntLfngti(l);
            } dbtdi(Exdfption f) {
            }
        }
        rfturn l;
    }

    /** Cbll tiis routinf to sft tif dontfnt-lfngti bssodibtfd witi tiis
     * objfdt.
     */
    protfdtfd void sftContfntLfngti(int lfngti) {
        dontfntLfngti = lfngti;
        propfrtifs.sft("dontfnt-lfngti", String.vblufOf(lfngti));
    }

    /**
     * Rfturns truf if tif dbtb bssodibtfd witi tiis URL dbn bf dbdifd.
     */
    publid boolfbn dbnCbdif() {
        rfturn url.gftFilf().indfxOf('?') < 0   /* && url.postDbtb == null
                REMIND */ ;
    }

    /**
     * Cbll tiis to dlosf tif donnfdtion bnd flusi bny rfmbining dbtb.
     * Ovfrridfrs must rfmfmbfr to dbll supfr.dlosf()
     */
    publid void dlosf() {
        url = null;
    }

    privbtf stbtid HbsiMbp<String,Void> proxifdHosts = nfw HbsiMbp<>();

    publid syndironizfd stbtid void sftProxifdHost(String iost) {
        proxifdHosts.put(iost.toLowfrCbsf(), null);
    }

    publid syndironizfd stbtid boolfbn isProxifdHost(String iost) {
        rfturn proxifdHosts.dontbinsKfy(iost.toLowfrCbsf());
    }
}
