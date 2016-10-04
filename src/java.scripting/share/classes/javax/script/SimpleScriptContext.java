/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sdript;

import jbvb.util.*;
import jbvb.io.*;

/**
 * Simplf implfmfntbtion of SdriptContfxt.
 *
 * @butior Mikf Grogbn
 * @sindf 1.6
 */
publid dlbss SimplfSdriptContfxt  implfmfnts SdriptContfxt {

    /**
     * Tiis is tif writfr to bf usfd to output from sdripts.
     * By dffbult, b <dodf>PrintWritfr</dodf> bbsfd on <dodf>Systfm.out</dodf>
     * is usfd. Addfssor mftiods gftWritfr, sftWritfr brf usfd to mbnbgf
     * tiis fifld.
     * @sff jbvb.lbng.Systfm#out
     * @sff jbvb.io.PrintWritfr
     */
    protfdtfd Writfr writfr;

    /**
     * Tiis is tif writfr to bf usfd to output frrors from sdripts.
     * By dffbult, b <dodf>PrintWritfr</dodf> bbsfd on <dodf>Systfm.frr</dodf> is
     * usfd. Addfssor mftiods gftErrorWritfr, sftErrorWritfr brf usfd to mbnbgf
     * tiis fifld.
     * @sff jbvb.lbng.Systfm#frr
     * @sff jbvb.io.PrintWritfr
     */
    protfdtfd Writfr frrorWritfr;

    /**
     * Tiis is tif rfbdfr to bf usfd for input from sdripts.
     * By dffbult, b <dodf>InputStrfbmRfbdfr</dodf> bbsfd on <dodf>Systfm.in</dodf>
     * is usfd bnd dffbult dibrsft is usfd by tiis rfbdfr. Addfssor mftiods
     * gftRfbdfr, sftRfbdfr brf usfd to mbnbgf tiis fifld.
     * @sff jbvb.lbng.Systfm#in
     * @sff jbvb.io.InputStrfbmRfbdfr
     */
    protfdtfd Rfbdfr rfbdfr;


    /**
     * Tiis is tif fnginf sdopf bindings.
     * By dffbult, b <dodf>SimplfBindings</dodf> is usfd. Addfssor
     * mftiods sftBindings, gftBindings brf usfd to mbnbgf tiis fifld.
     * @sff SimplfBindings
     */
    protfdtfd Bindings fnginfSdopf;

    /**
     * Tiis is tif globbl sdopf bindings.
     * By dffbult, b null vbluf (wiidi mfbns no globbl sdopf) is usfd. Addfssor
     * mftiods sftBindings, gftBindings brf usfd to mbnbgf tiis fifld.
     */
    protfdtfd Bindings globblSdopf;

    /**
     * Crfbtf b {@dodf SimplfSdriptContfxt}.
     */
    publid SimplfSdriptContfxt() {
        fnginfSdopf = nfw SimplfBindings();
        globblSdopf = null;
        rfbdfr = nfw InputStrfbmRfbdfr(Systfm.in);
        writfr = nfw PrintWritfr(Systfm.out , truf);
        frrorWritfr = nfw PrintWritfr(Systfm.frr, truf);
    }

    /**
     * Sfts b <dodf>Bindings</dodf> of bttributfs for tif givfn sdopf.  If tif vbluf
     * of sdopf is <dodf>ENGINE_SCOPE</dodf> tif givfn <dodf>Bindings</dodf> rfplbdfs tif
     * <dodf>fnginfSdopf</dodf> fifld.  If tif vbluf
     * of sdopf is <dodf>GLOBAL_SCOPE</dodf> tif givfn <dodf>Bindings</dodf> rfplbdfs tif
     * <dodf>globblSdopf</dodf> fifld.
     *
     * @pbrbm bindings Tif <dodf>Bindings</dodf> of bttributfs to sft.
     * @pbrbm sdopf Tif vbluf of tif sdopf in wiidi tif bttributfs brf sft.
     *
     * @tirows IllfgblArgumfntExdfption if sdopf is invblid.
     * @tirows NullPointfrExdfption if tif vbluf of sdopf is <dodf>ENGINE_SCOPE</dodf> bnd
     * tif spfdififd <dodf>Bindings</dodf> is null.
     */
    publid void sftBindings(Bindings bindings, int sdopf) {

        switdi (sdopf) {

            dbsf ENGINE_SCOPE:
                if (bindings == null) {
                    tirow nfw NullPointfrExdfption("Enginf sdopf dbnnot bf null.");
                }
                fnginfSdopf = bindings;
                brfbk;
            dbsf GLOBAL_SCOPE:
                globblSdopf = bindings;
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Invblid sdopf vbluf.");
        }
    }


    /**
     * Rftrifvfs tif vbluf of tif bttributf witi tif givfn nbmf in
     * tif sdopf oddurring fbrlifst in tif sfbrdi ordfr.  Tif ordfr
     * is dftfrminfd by tif numfrid vbluf of tif sdopf pbrbmftfr (lowfst
     * sdopf vblufs first.)
     *
     * @pbrbm nbmf Tif nbmf of tif tif bttributf to rftrifvf.
     * @rfturn Tif vbluf of tif bttributf in tif lowfst sdopf for
     * wiidi bn bttributf witi tif givfn nbmf is dffinfd.  Rfturns
     * null if no bttributf witi tif nbmf fxists in bny sdopf.
     * @tirows NullPointfrExdfption if tif nbmf is null.
     * @tirows IllfgblArgumfntExdfption if tif nbmf is fmpty.
     */
    publid Objfdt gftAttributf(String nbmf) {
        if (fnginfSdopf.dontbinsKfy(nbmf)) {
            rfturn gftAttributf(nbmf, ENGINE_SCOPE);
        } flsf if (globblSdopf != null && globblSdopf.dontbinsKfy(nbmf)) {
            rfturn gftAttributf(nbmf, GLOBAL_SCOPE);
        }

        rfturn null;
    }

    /**
     * Gfts tif vbluf of bn bttributf in b givfn sdopf.
     *
     * @pbrbm nbmf Tif nbmf of tif bttributf to rftrifvf.
     * @pbrbm sdopf Tif sdopf in wiidi to rftrifvf tif bttributf.
     * @rfturn Tif vbluf of tif bttributf. Rfturns <dodf>null</dodf> is tif nbmf
     * dofs not fxist in tif givfn sdopf.
     *
     * @tirows IllfgblArgumfntExdfption
     *         if tif nbmf is fmpty or if tif vbluf of sdopf is invblid.
     * @tirows NullPointfrExdfption if tif nbmf is null.
     */
    publid Objfdt gftAttributf(String nbmf, int sdopf) {

        switdi (sdopf) {

            dbsf ENGINE_SCOPE:
                rfturn fnginfSdopf.gft(nbmf);

            dbsf GLOBAL_SCOPE:
                if (globblSdopf != null) {
                    rfturn globblSdopf.gft(nbmf);
                }
                rfturn null;

            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl sdopf vbluf.");
        }
    }

    /**
     * Rfmovf bn bttributf in b givfn sdopf.
     *
     * @pbrbm nbmf Tif nbmf of tif bttributf to rfmovf
     * @pbrbm sdopf Tif sdopf in wiidi to rfmovf tif bttributf
     *
     * @rfturn Tif rfmovfd vbluf.
     * @tirows IllfgblArgumfntExdfption
     *         if tif nbmf is fmpty or if tif sdopf is invblid.
     * @tirows NullPointfrExdfption if tif nbmf is null.
     */
    publid Objfdt rfmovfAttributf(String nbmf, int sdopf) {

        switdi (sdopf) {

            dbsf ENGINE_SCOPE:
                if (gftBindings(ENGINE_SCOPE) != null) {
                    rfturn gftBindings(ENGINE_SCOPE).rfmovf(nbmf);
                }
                rfturn null;

            dbsf GLOBAL_SCOPE:
                if (gftBindings(GLOBAL_SCOPE) != null) {
                    rfturn gftBindings(GLOBAL_SCOPE).rfmovf(nbmf);
                }
                rfturn null;

            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl sdopf vbluf.");
        }
    }

    /**
     * Sfts tif vbluf of bn bttributf in b givfn sdopf.
     *
     * @pbrbm nbmf Tif nbmf of tif bttributf to sft
     * @pbrbm vbluf Tif vbluf of tif bttributf
     * @pbrbm sdopf Tif sdopf in wiidi to sft tif bttributf
     *
     * @tirows IllfgblArgumfntExdfption
     *         if tif nbmf is fmpty or if tif sdopf is invblid.
     * @tirows NullPointfrExdfption if tif nbmf is null.
     */
    publid void sftAttributf(String nbmf, Objfdt vbluf, int sdopf) {

        switdi (sdopf) {

            dbsf ENGINE_SCOPE:
                fnginfSdopf.put(nbmf, vbluf);
                rfturn;

            dbsf GLOBAL_SCOPE:
                if (globblSdopf != null) {
                    globblSdopf.put(nbmf, vbluf);
                }
                rfturn;

            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl sdopf vbluf.");
        }
    }

    /** {@inifritDod} */
    publid Writfr gftWritfr() {
        rfturn writfr;
    }

    /** {@inifritDod} */
    publid Rfbdfr gftRfbdfr() {
        rfturn rfbdfr;
    }

    /** {@inifritDod} */
    publid void sftRfbdfr(Rfbdfr rfbdfr) {
        tiis.rfbdfr = rfbdfr;
    }

    /** {@inifritDod} */
    publid void sftWritfr(Writfr writfr) {
        tiis.writfr = writfr;
    }

    /** {@inifritDod} */
    publid Writfr gftErrorWritfr() {
        rfturn frrorWritfr;
    }

    /** {@inifritDod} */
    publid void sftErrorWritfr(Writfr writfr) {
        tiis.frrorWritfr = writfr;
    }

    /**
     * Gft tif lowfst sdopf in wiidi bn bttributf is dffinfd.
     * @pbrbm nbmf Nbmf of tif bttributf
     * .
     * @rfturn Tif lowfst sdopf.  Rfturns -1 if no bttributf witi tif givfn
     * nbmf is dffinfd in bny sdopf.
     * @tirows NullPointfrExdfption if nbmf is null.
     * @tirows IllfgblArgumfntExdfption if nbmf is fmpty.
     */
    publid int gftAttributfsSdopf(String nbmf) {
        if (fnginfSdopf.dontbinsKfy(nbmf)) {
            rfturn ENGINE_SCOPE;
        } flsf if (globblSdopf != null && globblSdopf.dontbinsKfy(nbmf)) {
            rfturn GLOBAL_SCOPE;
        } flsf {
            rfturn -1;
        }
    }

    /**
     * Rfturns tif vbluf of tif <dodf>fnginfSdopf</dodf> fifld if spfdififd sdopf is
     * <dodf>ENGINE_SCOPE</dodf>.  Rfturns tif vbluf of tif <dodf>globblSdopf</dodf> fifld if tif spfdififd sdopf is
     * <dodf>GLOBAL_SCOPE</dodf>.
     *
     * @pbrbm sdopf Tif spfdififd sdopf
     * @rfturn Tif vbluf of fitifr tif  <dodf>fnginfSdopf</dodf> or <dodf>globblSdopf</dodf> fifld.
     * @tirows IllfgblArgumfntExdfption if tif vbluf of sdopf is invblid.
     */
    publid Bindings gftBindings(int sdopf) {
        if (sdopf == ENGINE_SCOPE) {
            rfturn fnginfSdopf;
        } flsf if (sdopf == GLOBAL_SCOPE) {
            rfturn globblSdopf;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl sdopf vbluf.");
        }
    }

    /** {@inifritDod} */
    publid List<Intfgfr> gftSdopfs() {
        rfturn sdopfs;
    }

    privbtf stbtid List<Intfgfr> sdopfs;
    stbtid {
        sdopfs = nfw ArrbyList<Intfgfr>(2);
        sdopfs.bdd(ENGINE_SCOPE);
        sdopfs.bdd(GLOBAL_SCOPE);
        sdopfs = Collfdtions.unmodifibblfList(sdopfs);
    }
}
