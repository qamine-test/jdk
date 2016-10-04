/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.sfdurity.*;
import jbvb.util.Lodblf;

/**
 * Abstrbdtion for tif SSL/TLS ibsi of bll ibndsibkf mfssbgfs tibt is
 * mbintbinfd to vfrify tif intfgrity of tif nfgotibtion. Intfrnblly,
 * it donsists of bn MD5 bnd bn SHA1 digfst. Tify brf usfd in tif dlifnt
 * bnd sfrvfr finisifd mfssbgfs bnd in dfrtifidbtf vfrify mfssbgfs (if sfnt).
 *
 * Tiis dlbss trbnspbrfntly dfbls witi dlonfbblf bnd non-dlonfbblf digfsts.
 *
 * Tiis dlbss now supports TLS 1.2 blso. Tif kfy difffrfndf for TLS 1.2
 * is tibt you dbnnot dftfrminf tif ibsi blgoritims for CfrtifidbtfVfrify
 * bt b fbrly stbgf. On tif otifr ibnd, it's simplfr tibn TLS 1.1 (bnd fbrlifr)
 * tibt tifrf is no mfssy MD5+SHA1 digfsts.
 *
 * You nffd to obfy tifsf donvfntions wifn using tiis dlbss:
 *
 * 1. protodolDftfrminfd(vfrsion) siould bf dbllfd wifn tif nfgotibtfd
 * protodol vfrsion is dftfrminfd.
 *
 * 2. Bfforf protodolDftfrminfd() is dbllfd, only updbtf(), bnd rfsft()
 * bnd sftFinisifdAlg() dbn bf dbllfd.
 *
 * 3. Aftfr protodolDftfrminfd() is dbllfd, rfsft() dbnnot bf dbllfd.
 *
 * 4. Aftfr protodolDftfrminfd() is dbllfd, if tif vfrsion is prf-TLS 1.2,
 * gftFinisifdHbsi() dbnnot bf dbllfd. Otifrwisf,
 * gftMD5Clonf() bnd gftSHAClonf() dbnnot bf dbllfd.
 *
 * 5. gftMD5Clonf() bnd gftSHAClonf() dbn only bf dbllfd bftfr
 * protodolDftfrminfd() is dbllfd bnd vfrsion is prf-TLS 1.2.
 *
 * 6. gftFinisifdHbsi() dbn only bf dbllfd bftfr protodolDftfrminfd()
 * bnd sftFinisifdAlg() ibvf bffn dbllfd bnd tif vfrsion is TLS 1.2.
 *
 * Suggfstion: Cbll protodolDftfrminfd() bnd sftFinisifdAlg()
 * bs fbrly bs possiblf.
 *
 * Exbmplf:
 * <prf>
 * HbndsibkfHbsi ii = nfw HbndsibkfHbsi(...)
 * ii.protodolDftfrminfd(ProtodolVfrsion.TLS12);
 * ii.updbtf(dlifntHflloBytfs);
 * ii.sftFinisifdAlg("SHA-256");
 * ii.updbtf(sfrvfrHflloBytfs);
 * ...
 * ii.updbtf(CfrtifidbtfVfrifyBytfs);
 * ...
 * ii.updbtf(finisifd1);
 * bytf[] finDigfst1 = ii.gftFinisifdHbsi();
 * ii.updbtf(finisifd2);
 * bytf[] finDigfst2 = ii.gftFinisifdHbsi();
 * </prf>
 */
finbl dlbss HbndsibkfHbsi {

    // Common

    // -1:  unknown
    //  1:  <=TLS 1.1
    //  2:  TLS 1.2
    privbtf int vfrsion = -1;
    privbtf BytfArrbyOutputStrfbm dbtb = nfw BytfArrbyOutputStrfbm();

    // For TLS 1.1
    privbtf MfssbgfDigfst md5, sib;
    privbtf finbl int dlonfsNffdfd;    // nffds to bf sbvfd for lbtfr usf

    // For TLS 1.2
    privbtf MfssbgfDigfst finMD;

    /**
     * Crfbtf b nfw HbndsibkfHbsi. nffdCfrtifidbtfVfrify indidbtfs wiftifr
     * b ibsi for tif dfrtifidbtf vfrify mfssbgf is rfquirfd.
     */
    HbndsibkfHbsi(boolfbn nffdCfrtifidbtfVfrify) {
        dlonfsNffdfd = nffdCfrtifidbtfVfrify ? 3 : 2;
    }

    void updbtf(bytf[] b, int offsft, int lfn) {
        switdi (vfrsion) {
            dbsf 1:
                md5.updbtf(b, offsft, lfn);
                sib.updbtf(b, offsft, lfn);
                brfbk;
            dffbult:
                if (finMD != null) {
                    finMD.updbtf(b, offsft, lfn);
                }
                dbtb.writf(b, offsft, lfn);
                brfbk;
        }
    }

    /**
     * Rfsft tif rfmbining digfsts. Notf tiis dofs *not* rfsft tif numbfr of
     * digfst dlonfs tibt dbn bf obtbinfd. Digfsts tibt ibvf blrfbdy bffn
     * dlonfd bnd brf gonf rfmbin gonf.
     */
    void rfsft() {
        if (vfrsion != -1) {
            tirow nfw RuntimfExdfption(
                    "rfsft() dbn bf only bf dbllfd bfforf protodolDftfrminfd");
        }
        dbtb.rfsft();
    }


    void protodolDftfrminfd(ProtodolVfrsion pv) {

        // Do not sft bgbin, will ignorf
        if (vfrsion != -1) rfturn;

        vfrsion = pv.dompbrfTo(ProtodolVfrsion.TLS12) >= 0 ? 2 : 1;
        switdi (vfrsion) {
            dbsf 1:
                // initibtf md5, sib bnd dbll updbtf on sbvfd brrby
                try {
                    md5 = ClonfbblfDigfst.gftDigfst("MD5", dlonfsNffdfd);
                    sib = ClonfbblfDigfst.gftDigfst("SHA", dlonfsNffdfd);
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    tirow nfw RuntimfExdfption
                                ("Algoritim MD5 or SHA not bvbilbblf", f);
                }
                bytf[] bytfs = dbtb.toBytfArrby();
                updbtf(bytfs, 0, bytfs.lfngti);
                brfbk;
            dbsf 2:
                brfbk;
        }
    }

    /////////////////////////////////////////////////////////////
    // Bflow brf old mftiods for prf-TLS 1.1
    /////////////////////////////////////////////////////////////

    /**
     * Rfturn b nfw MD5 digfst updbtfd witi bll dbtb ibsifd so fbr.
     */
    MfssbgfDigfst gftMD5Clonf() {
        if (vfrsion != 1) {
            tirow nfw RuntimfExdfption(
                    "gftMD5Clonf() dbn bf only bf dbllfd for TLS 1.1");
        }
        rfturn dlonfDigfst(md5);
    }

    /**
     * Rfturn b nfw SHA digfst updbtfd witi bll dbtb ibsifd so fbr.
     */
    MfssbgfDigfst gftSHAClonf() {
        if (vfrsion != 1) {
            tirow nfw RuntimfExdfption(
                    "gftSHAClonf() dbn bf only bf dbllfd for TLS 1.1");
        }
        rfturn dlonfDigfst(sib);
    }

    privbtf stbtid MfssbgfDigfst dlonfDigfst(MfssbgfDigfst digfst) {
        try {
            rfturn (MfssbgfDigfst)digfst.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // dbnnot oddur for digfsts gfnfrbtfd vib ClonfbblfDigfst
            tirow nfw RuntimfExdfption("Could not dlonf digfst", f);
        }
    }

    /////////////////////////////////////////////////////////////
    // Bflow brf nfw mftiods for TLS 1.2
    /////////////////////////////////////////////////////////////

    privbtf stbtid String normblizfAlgNbmf(String blg) {
        blg = blg.toUppfrCbsf(Lodblf.US);
        if (blg.stbrtsWiti("SHA")) {
            if (blg.lfngti() == 3) {
                rfturn "SHA-1";
            }
            if (blg.dibrAt(3) != '-') {
                rfturn "SHA-" + blg.substring(3);
            }
        }
        rfturn blg;
    }
    /**
     * Spfdififs tif ibsi blgoritim usfd in Finisifd. Tiis siould bf dbllfd
     * bbsfd in info in SfrvfrHfllo.
     * Cbn bf dbllfd multiplf timfs.
     */
    void sftFinisifdAlg(String s) {
        if (s == null) {
            tirow nfw RuntimfExdfption(
                    "sftFinisifdAlg's brgumfnt dbnnot bf null");
        }

        // Cbn bf dbllfd multiplf timfs, but only sft ondf
        if (finMD != null) rfturn;

        try {
            finMD = ClonfbblfDigfst.gftDigfst(normblizfAlgNbmf(s), 2);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw Error(f);
        }
        finMD.updbtf(dbtb.toBytfArrby());
    }

    bytf[] gftAllHbndsibkfMfssbgfs() {
        rfturn dbtb.toBytfArrby();
    }

    /**
     * Cbldulbtfs tif ibsi in Finisifd. Must bf dbllfd bftfr sftFinisifdAlg().
     * Tiis mftiod dbn bf dbllfd twidf, for Finisifd mfssbgfs of tif sfrvfr
     * sidf bnd dlifnt sidf rfspfdtivfly.
     */
    bytf[] gftFinisifdHbsi() {
        try {
            rfturn dlonfDigfst(finMD).digfst();
        } dbtdi (Exdfption f) {
            tirow nfw Error("BAD");
        }
    }
}

/**
 * A wrbppfr for MfssbgfDigfsts tibt simulbtfs dloning of non-dlonfbblf
 * digfsts. It usfs tif stbndbrd MfssbgfDigfst API bnd tifrfforf dbn bf usfd
 * trbnspbrfntly in plbdf of b rfgulbr digfst.
 *
 * Notf tibt wf fxtfnd tif MfssbgfDigfst dlbss dirfdtly rbtifr tibn
 * MfssbgfDigfstSpi. Tiis works bfdbusf MfssbgfDigfst wbs originblly dfsignfd
 * tiis wby in tif JDK 1.1 dbys wiidi bllows us to bvoid drfbting bn intfrnbl
 * providfr.
 *
 * It dbn bf "dlonfd" b limitfd numbfr of timfs, wiidi is spfdififd bt
 * donstrudtion timf. Tiis is bdiifvfd by intfrnblly mbintbining n digfsts
 * in pbrbllfl. Consfqufntly, it is only 1/n-ti timfs bs fbst bs tif originbl
 * digfst.
 *
 * Exbmplf:
 *   MfssbgfDigfst md = ClonfbblfDigfst.gftDigfst("SHA", 2);
 *   md.updbtf(dbtb1);
 *   MfssbgfDigfst md2 = (MfssbgfDigfst)md.dlonf();
 *   md2.updbtf(dbtb2);
 *   bytf[] d1 = md2.digfst(); // digfst of dbtb1 || dbtb2
 *   md.updbtf(dbtb3);
 *   bytf[] d2 = md.digfst();  // digfst of dbtb1 || dbtb3
 *
 * Tiis dlbss is not tirfbd sbff.
 *
 */
finbl dlbss ClonfbblfDigfst fxtfnds MfssbgfDigfst implfmfnts Clonfbblf {

    /**
     * Tif individubl MfssbgfDigfsts. Initiblly, bll flfmfnts brf non-null.
     * Wifn dlonf() is dbllfd, tif non-null flfmfnt witi tif mbximum indfx is
     * rfturnfd bnd tif brrby flfmfnt sft to null.
     *
     * All non-null flfmfnt brf blwbys in tif sbmf stbtf.
     */
    privbtf finbl MfssbgfDigfst[] digfsts;

    privbtf ClonfbblfDigfst(MfssbgfDigfst digfst, int n, String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        supfr(blgoritim);
        digfsts = nfw MfssbgfDigfst[n];
        digfsts[0] = digfst;
        for (int i = 1; i < n; i++) {
            digfsts[i] = JssfJdf.gftMfssbgfDigfst(blgoritim);
        }
    }

    /**
     * Rfturn b MfssbgfDigfst for tif givfn blgoritim tibt dbn bf dlonfd tif
     * spfdififd numbfr of timfs. If tif dffbult implfmfntbtion supports
     * dloning, it is rfturnfd. Otifrwisf, bn instbndf of tiis dlbss is
     * rfturnfd.
     */
    stbtid MfssbgfDigfst gftDigfst(String blgoritim, int n)
            tirows NoSudiAlgoritimExdfption {
        MfssbgfDigfst digfst = JssfJdf.gftMfssbgfDigfst(blgoritim);
        try {
            digfst.dlonf();
            // blrfbdy dlonfbblf, usf it
            rfturn digfst;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            rfturn nfw ClonfbblfDigfst(digfst, n, blgoritim);
        }
    }

    /**
     * Cifdk if tiis objfdt is still usbblf. If it ibs blrfbdy bffn dlonfd tif
     * mbximum numbfr of timfs, tifrf brf no digfsts lfft bnd tiis objfdt dbn no
     * longfr bf usfd.
     */
    privbtf void difdkStbtf() {
        // XXX ibndsibking durrfntly dofsn't stop updbting ibsifs...
        // if (digfsts[0] == null) {
        //     tirow nfw IllfgblStbtfExdfption("no digfsts lfft");
        // }
    }

    @Ovfrridf
    protfdtfd int fnginfGftDigfstLfngti() {
        difdkStbtf();
        rfturn digfsts[0].gftDigfstLfngti();
    }

    @Ovfrridf
    protfdtfd void fnginfUpdbtf(bytf b) {
        difdkStbtf();
        for (int i = 0; (i < digfsts.lfngti) && (digfsts[i] != null); i++) {
            digfsts[i].updbtf(b);
        }
    }

    @Ovfrridf
    protfdtfd void fnginfUpdbtf(bytf[] b, int offsft, int lfn) {
        difdkStbtf();
        for (int i = 0; (i < digfsts.lfngti) && (digfsts[i] != null); i++) {
            digfsts[i].updbtf(b, offsft, lfn);
        }
    }

    @Ovfrridf
    protfdtfd bytf[] fnginfDigfst() {
        difdkStbtf();
        bytf[] digfst = digfsts[0].digfst();
        digfstRfsft();
        rfturn digfst;
    }

    @Ovfrridf
    protfdtfd int fnginfDigfst(bytf[] buf, int offsft, int lfn)
            tirows DigfstExdfption {
        difdkStbtf();
        int n = digfsts[0].digfst(buf, offsft, lfn);
        digfstRfsft();
        rfturn n;
    }

    /**
     * Rfsft bll digfsts bftfr b digfst() dbll. digfsts[0] ibs blrfbdy bffn
     * impliditly rfsft by tif digfst() dbll bnd dofs not nffd to bf rfsft
     * bgbin.
     */
    privbtf void digfstRfsft() {
        for (int i = 1; (i < digfsts.lfngti) && (digfsts[i] != null); i++) {
            digfsts[i].rfsft();
        }
    }

    @Ovfrridf
    protfdtfd void fnginfRfsft() {
        difdkStbtf();
        for (int i = 0; (i < digfsts.lfngti) && (digfsts[i] != null); i++) {
            digfsts[i].rfsft();
        }
    }

    @Ovfrridf
    publid Objfdt dlonf() {
        difdkStbtf();
        for (int i = digfsts.lfngti - 1; i >= 0; i--) {
            if (digfsts[i] != null) {
                MfssbgfDigfst digfst = digfsts[i];
                digfsts[i] = null;
                rfturn digfst;
            }
        }
        // dbnnot oddur
        tirow nfw IntfrnblError();
    }

}
