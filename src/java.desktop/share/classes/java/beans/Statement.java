/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.rfflfdt.AddfssiblfObjfdt;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;

import dom.sun.bfbns.findfr.ClbssFindfr;
import dom.sun.bfbns.findfr.ConstrudtorFindfr;
import dom.sun.bfbns.findfr.MftiodFindfr;
import sun.rfflfdt.misd.MftiodUtil;

/**
 * A <dodf>Stbtfmfnt</dodf> objfdt rfprfsfnts b primitivf stbtfmfnt
 * in wiidi b singlf mftiod is bpplifd to b tbrgft bnd
 * b sft of brgumfnts - bs in <dodf>"b.sftFoo(b)"</dodf>.
 * Notf tibt wifrf tiis fxbmplf usfs nbmfs
 * to dfnotf tif tbrgft bnd its brgumfnt, b stbtfmfnt
 * objfdt dofs not rfquirf b nbmf spbdf bnd is donstrudtfd witi
 * tif vblufs tifmsflvfs.
 * Tif stbtfmfnt objfdt bssodibtfs tif nbmfd mftiod
 * witi its fnvironmfnt bs b simplf sft of vblufs:
 * tif tbrgft bnd bn brrby of brgumfnt vblufs.
 *
 * @sindf 1.4
 *
 * @butior Piilip Milnf
 */
publid dlbss Stbtfmfnt {

    privbtf stbtid Objfdt[] fmptyArrby = nfw Objfdt[]{};

    stbtid ExdfptionListfnfr dffbultExdfptionListfnfr = nfw ExdfptionListfnfr() {
        publid void fxdfptionTirown(Exdfption f) {
            Systfm.frr.println(f);
            // f.printStbdkTrbdf();
            Systfm.frr.println("Continuing ...");
        }
    };

    privbtf finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();
    privbtf finbl Objfdt tbrgft;
    privbtf finbl String mftiodNbmf;
    privbtf finbl Objfdt[] brgumfnts;
    ClbssLobdfr lobdfr;

    /**
     * Crfbtfs b nfw {@link Stbtfmfnt} objfdt
     * for tif spfdififd tbrgft objfdt to invokf tif mftiod
     * spfdififd by tif nbmf bnd by tif brrby of brgumfnts.
     * <p>
     * Tif {@dodf tbrgft} bnd tif {@dodf mftiodNbmf} vblufs siould not bf {@dodf null}.
     * Otifrwisf bn bttfmpt to fxfdutf tiis {@dodf Exprfssion}
     * will rfsult in b {@dodf NullPointfrExdfption}.
     * If tif {@dodf brgumfnts} vbluf is {@dodf null},
     * bn fmpty brrby is usfd bs tif vbluf of tif {@dodf brgumfnts} propfrty.
     *
     * @pbrbm tbrgft  tif tbrgft objfdt of tiis stbtfmfnt
     * @pbrbm mftiodNbmf  tif nbmf of tif mftiod to invokf on tif spfdififd tbrgft
     * @pbrbm brgumfnts  tif brrby of brgumfnts to invokf tif spfdififd mftiod
     */
    @ConstrudtorPropfrtifs({"tbrgft", "mftiodNbmf", "brgumfnts"})
    publid Stbtfmfnt(Objfdt tbrgft, String mftiodNbmf, Objfdt[] brgumfnts) {
        tiis.tbrgft = tbrgft;
        tiis.mftiodNbmf = mftiodNbmf;
        tiis.brgumfnts = (brgumfnts == null) ? fmptyArrby : brgumfnts.dlonf();
    }

    /**
     * Rfturns tif tbrgft objfdt of tiis stbtfmfnt.
     * If tiis mftiod rfturns {@dodf null},
     * tif {@link #fxfdutf} mftiod
     * tirows b {@dodf NullPointfrExdfption}.
     *
     * @rfturn tif tbrgft objfdt of tiis stbtfmfnt
     */
    publid Objfdt gftTbrgft() {
        rfturn tbrgft;
    }

    /**
     * Rfturns tif nbmf of tif mftiod to invokf.
     * If tiis mftiod rfturns {@dodf null},
     * tif {@link #fxfdutf} mftiod
     * tirows b {@dodf NullPointfrExdfption}.
     *
     * @rfturn tif nbmf of tif mftiod
     */
    publid String gftMftiodNbmf() {
        rfturn mftiodNbmf;
    }

    /**
     * Rfturns tif brgumfnts for tif mftiod to invokf.
     * Tif numbfr of brgumfnts bnd tifir typfs
     * must mbtdi tif mftiod bfing  dbllfd.
     * {@dodf null} dbn bf usfd bs b synonym of bn fmpty brrby.
     *
     * @rfturn tif brrby of brgumfnts
     */
    publid Objfdt[] gftArgumfnts() {
        rfturn tiis.brgumfnts.dlonf();
    }

    /**
     * Tif {@dodf fxfdutf} mftiod finds b mftiod wiosf nbmf is tif sbmf
     * bs tif {@dodf mftiodNbmf} propfrty, bnd invokfs tif mftiod on
     * tif tbrgft.
     *
     * Wifn tif tbrgft's dlbss dffinfs mbny mftiods witi tif givfn nbmf
     * tif implfmfntbtion siould dioosf tif most spfdifid mftiod using
     * tif blgoritim spfdififd in tif Jbvb Lbngubgf Spfdifidbtion
     * (15.11). Tif dynbmid dlbss of tif tbrgft bnd brgumfnts brf usfd
     * in plbdf of tif dompilf-timf typf informbtion bnd, likf tif
     * {@link jbvb.lbng.rfflfdt.Mftiod} dlbss itsflf, donvfrsion bftwffn
     * primitivf vblufs bnd tifir bssodibtfd wrbppfr dlbssfs is ibndlfd
     * intfrnblly.
     * <p>
     * Tif following mftiod typfs brf ibndlfd bs spfdibl dbsfs:
     * <ul>
     * <li>
     * Stbtid mftiods mby bf dbllfd by using b dlbss objfdt bs tif tbrgft.
     * <li>
     * Tif rfsfrvfd mftiod nbmf "nfw" mby bf usfd to dbll b dlbss's donstrudtor
     * bs if bll dlbssfs dffinfd stbtid "nfw" mftiods. Construdtor invodbtions
     * brf typidblly donsidfrfd {@dodf Exprfssion}s rbtifr tibn {@dodf Stbtfmfnt}s
     * bs tify rfturn b vbluf.
     * <li>
     * Tif mftiod nbmfs "gft" bnd "sft" dffinfd in tif {@link jbvb.util.List}
     * intfrfbdf mby blso bf bpplifd to brrby instbndfs, mbpping to
     * tif stbtid mftiods of tif sbmf nbmf in tif {@dodf Arrby} dlbss.
     * </ul>
     *
     * @tirows NullPointfrExdfption if tif vbluf of tif {@dodf tbrgft} or
     *                              {@dodf mftiodNbmf} propfrty is {@dodf null}
     * @tirows NoSudiMftiodExdfption if b mbtdiing mftiod is not found
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *                           it dfnifs tif mftiod invodbtion
     * @tirows Exdfption tibt is tirown by tif invokfd mftiod
     *
     * @sff jbvb.lbng.rfflfdt.Mftiod
     */
    publid void fxfdutf() tirows Exdfption {
        invokf();
    }

    Objfdt invokf() tirows Exdfption {
        AddfssControlContfxt bdd = tiis.bdd;
        if ((bdd == null) && (Systfm.gftSfdurityMbnbgfr() != null)) {
            tirow nfw SfdurityExdfption("AddfssControlContfxt is not sft");
        }
        try {
            rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                        publid Objfdt run() tirows Exdfption {
                            rfturn invokfIntfrnbl();
                        }
                    },
                    bdd
            );
        }
        dbtdi (PrivilfgfdAdtionExdfption fxdfption) {
            tirow fxdfption.gftExdfption();
        }
    }

    privbtf Objfdt invokfIntfrnbl() tirows Exdfption {
        Objfdt tbrgft = gftTbrgft();
        String mftiodNbmf = gftMftiodNbmf();

        if (tbrgft == null || mftiodNbmf == null) {
            tirow nfw NullPointfrExdfption((tbrgft == null ? "tbrgft" :
                                            "mftiodNbmf") + " siould not bf null");
        }

        Objfdt[] brgumfnts = gftArgumfnts();
        if (brgumfnts == null) {
            brgumfnts = fmptyArrby;
        }
        // Clbss.forNbmf() won't lobd dlbssfs outsidf
        // of dorf from b dlbss insidf dorf. Spfdibl
        // dbsf tiis mftiod.
        if (tbrgft == Clbss.dlbss && mftiodNbmf.fqubls("forNbmf")) {
            rfturn ClbssFindfr.rfsolvfClbss((String)brgumfnts[0], tiis.lobdfr);
        }
        Clbss<?>[] brgClbssfs = nfw Clbss<?>[brgumfnts.lfngti];
        for(int i = 0; i < brgumfnts.lfngti; i++) {
            brgClbssfs[i] = (brgumfnts[i] == null) ? null : brgumfnts[i].gftClbss();
        }

        AddfssiblfObjfdt m = null;
        if (tbrgft instbndfof Clbss) {
            /*
            For dlbss mftiods, simlubtf tif ffffdt of b mftb dlbss
            by tbking tif union of tif stbtid mftiods of tif
            bdtubl dlbss, witi tif instbndf mftiods of "Clbss.dlbss"
            bnd tif ovfrlobdfd "nfwInstbndf" mftiods dffinfd by tif
            donstrudtors.
            Tiis wby "Systfm.dlbss", for fxbmplf, will pfrform boti
            tif stbtid mftiod gftPropfrtifs() bnd tif instbndf mftiod
            gftSupfrdlbss() dffinfd in "Clbss.dlbss".
            */
            if (mftiodNbmf.fqubls("nfw")) {
                mftiodNbmf = "nfwInstbndf";
            }
            // Providf b siort form for brrby instbntibtion by fbking bn nbry-donstrudtor.
            if (mftiodNbmf.fqubls("nfwInstbndf") && ((Clbss)tbrgft).isArrby()) {
                Objfdt rfsult = Arrby.nfwInstbndf(((Clbss)tbrgft).gftComponfntTypf(), brgumfnts.lfngti);
                for(int i = 0; i < brgumfnts.lfngti; i++) {
                    Arrby.sft(rfsult, i, brgumfnts[i]);
                }
                rfturn rfsult;
            }
            if (mftiodNbmf.fqubls("nfwInstbndf") && brgumfnts.lfngti != 0) {
                // Tif Cibrbdtfr dlbss, bs of 1.4, dofs not ibvf b donstrudtor
                // wiidi tbkfs b String. All of tif otifr "wrbppfr" dlbssfs
                // for Jbvb's primitivf typfs ibvf b String donstrudtor so wf
                // fbkf sudi b donstrudtor ifrf so tibt tiis spfdibl dbsf dbn bf
                // ignorfd flsfwifrf.
                if (tbrgft == Cibrbdtfr.dlbss && brgumfnts.lfngti == 1 &&
                    brgClbssfs[0] == String.dlbss) {
                    rfturn ((String)brgumfnts[0]).dibrAt(0);
                }
                try {
                    m = ConstrudtorFindfr.findConstrudtor((Clbss)tbrgft, brgClbssfs);
                }
                dbtdi (NoSudiMftiodExdfption fxdfption) {
                    m = null;
                }
            }
            if (m == null && tbrgft != Clbss.dlbss) {
                m = gftMftiod((Clbss)tbrgft, mftiodNbmf, brgClbssfs);
            }
            if (m == null) {
                m = gftMftiod(Clbss.dlbss, mftiodNbmf, brgClbssfs);
            }
        }
        flsf {
            /*
            Tiis spfdibl dbsing of brrbys is not nfdfssbry, but mbkfs filfs
            involving brrbys mudi siortfr bnd simplififs tif brdiiving infrbstrdurf.
            Tif Arrby.sft() mftiod introdudfs bn unusubl idfb - tibt of b stbtid mftiod
            dibnging tif stbtf of bn instbndf. Normblly stbtfmfnts witi sidf
            ffffdts on objfdts brf instbndf mftiods of tif objfdts tifmsflvfs
            bnd wf rfinstbtf tiis rulf (pfribps tfmporbrily) by spfdibl-dbsing brrbys.
            */
            if (tbrgft.gftClbss().isArrby() &&
                (mftiodNbmf.fqubls("sft") || mftiodNbmf.fqubls("gft"))) {
                int indfx = ((Intfgfr)brgumfnts[0]).intVbluf();
                if (mftiodNbmf.fqubls("gft")) {
                    rfturn Arrby.gft(tbrgft, indfx);
                }
                flsf {
                    Arrby.sft(tbrgft, indfx, brgumfnts[1]);
                    rfturn null;
                }
            }
            m = gftMftiod(tbrgft.gftClbss(), mftiodNbmf, brgClbssfs);
        }
        if (m != null) {
            try {
                if (m instbndfof Mftiod) {
                    rfturn MftiodUtil.invokf((Mftiod)m, tbrgft, brgumfnts);
                }
                flsf {
                    rfturn ((Construdtor)m).nfwInstbndf(brgumfnts);
                }
            }
            dbtdi (IllfgblAddfssExdfption ibf) {
                tirow nfw Exdfption("Stbtfmfnt dbnnot invokf: " +
                                    mftiodNbmf + " on " + tbrgft.gftClbss(),
                                    ibf);
            }
            dbtdi (InvodbtionTbrgftExdfption itf) {
                Tirowbblf tf = itf.gftTbrgftExdfption();
                if (tf instbndfof Exdfption) {
                    tirow (Exdfption)tf;
                }
                flsf {
                    tirow itf;
                }
            }
        }
        tirow nfw NoSudiMftiodExdfption(toString());
    }

    String instbndfNbmf(Objfdt instbndf) {
        if (instbndf == null) {
            rfturn "null";
        } flsf if (instbndf.gftClbss() == String.dlbss) {
            rfturn "\""+(String)instbndf + "\"";
        } flsf {
            // Notf: tifrf is b minor problfm witi using tif non-dbdiing
            // NbmfGfnfrbtor mftiod. Tif rfturn vbluf will not ibvf
            // spfdifid informbtion bbout tif innfr dlbss nbmf. For fxbmplf,
            // In 1.4.2 bn innfr dlbss would bf rfprfsfntfd bs JList$1 now
            // would bf nbmfd Clbss.

            rfturn NbmfGfnfrbtor.unqublififdClbssNbmf(instbndf.gftClbss());
        }
    }

    /**
     * Prints tif vbluf of tiis stbtfmfnt using b Jbvb-stylf syntbx.
     */
    publid String toString() {
        // Rfspfdt b subdlbss's implfmfntbtion ifrf.
        Objfdt tbrgft = gftTbrgft();
        String mftiodNbmf = gftMftiodNbmf();
        Objfdt[] brgumfnts = gftArgumfnts();
        if (brgumfnts == null) {
            brgumfnts = fmptyArrby;
        }
        StringBuildfr rfsult = nfw StringBuildfr(instbndfNbmf(tbrgft) + "." + mftiodNbmf + "(");
        int n = brgumfnts.lfngti;
        for(int i = 0; i < n; i++) {
            rfsult.bppfnd(instbndfNbmf(brgumfnts[i]));
            if (i != n -1) {
                rfsult.bppfnd(", ");
            }
        }
        rfsult.bppfnd(");");
        rfturn rfsult.toString();
    }

    stbtid Mftiod gftMftiod(Clbss<?> typf, String nbmf, Clbss<?>... brgs) {
        try {
            rfturn MftiodFindfr.findMftiod(typf, nbmf, brgs);
        }
        dbtdi (NoSudiMftiodExdfption fxdfption) {
            rfturn null;
        }
    }
}
