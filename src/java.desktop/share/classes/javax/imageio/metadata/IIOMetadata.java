/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.mftbdbtb;

import org.w3d.dom.Nodf;
import jbvb.lbng.rfflfdt.Mftiod;

/**
 * An bbstrbdt dlbss to bf fxtfndfd by objfdts tibt rfprfsfnt mftbdbtb
 * (non-imbgf dbtb) bssodibtfd witi imbgfs bnd strfbms.  Plug-ins
 * rfprfsfnt mftbdbtb using opbquf, plug-in spfdifid objfdts.  Tifsf
 * objfdts, iowfvfr, providf tif bbility to bddfss tifir intfrnbl
 * informbtion bs b trff of <dodf>IIOMftbdbtbNodf</dodf> objfdts tibt
 * support tif XML DOM intfrfbdfs bs wfll bs bdditionbl intfrfbdfs for
 * storing non-tfxtubl dbtb bnd rftrifving informbtion bbout lfgbl
 * dbtb vblufs.  Tif formbt of sudi trffs is plug-in dfpfndfnt, but
 * plug-ins mby dioosf to support b plug-in nfutrbl formbt dfsdribfd
 * bflow.  A singlf plug-in mby support multiplf mftbdbtb formbts,
 * wiosf nbmfs mbybf dftfrminfd by dblling
 * <dodf>gftMftbdbtbFormbtNbmfs</dodf>.  Tif plug-in mby blso support
 * b singlf spfdibl formbt, rfffrrfd to bs tif "nbtivf" formbt, wiidi
 * is dfsignfd to fndodf its mftbdbtb losslfssly.  Tiis formbt will
 * typidblly bf dfsignfd spfdifidblly to work witi b spfdifid filf
 * formbt, so tibt imbgfs mby bf lobdfd bnd sbvfd in tif sbmf formbt
 * witi no loss of mftbdbtb, but mby bf lfss usfful for trbnsffrring
 * mftbdbtb bftwffn bn <dodf>ImbgfRfbdfr</dodf> bnd bn
 * <dodf>ImbgfWritfr</dodf> for difffrfnt imbgf formbts.  To donvfrt
 * bftwffn two nbtivf formbts bs losslfssly bs tif imbgf filf formbts
 * will bllow, bn <dodf>ImbgfTrbnsdodfr</dodf> objfdt must bf usfd.
 *
 * @sff jbvbx.imbgfio.ImbgfRfbdfr#gftImbgfMftbdbtb
 * @sff jbvbx.imbgfio.ImbgfRfbdfr#gftStrfbmMftbdbtb
 * @sff jbvbx.imbgfio.ImbgfRfbdfr#rfbdAll
 * @sff jbvbx.imbgfio.ImbgfWritfr#gftDffbultStrfbmMftbdbtb
 * @sff jbvbx.imbgfio.ImbgfWritfr#gftDffbultImbgfMftbdbtb
 * @sff jbvbx.imbgfio.ImbgfWritfr#writf
 * @sff jbvbx.imbgfio.ImbgfWritfr#donvfrtImbgfMftbdbtb
 * @sff jbvbx.imbgfio.ImbgfWritfr#donvfrtStrfbmMftbdbtb
 * @sff jbvbx.imbgfio.IIOImbgf
 * @sff jbvbx.imbgfio.ImbgfTrbnsdodfr
 *
 */
publid bbstrbdt dlbss IIOMftbdbtb {

    /**
     * A boolfbn indidbting wiftifr tif dondrftf subdlbss supports tif
     * stbndbrd mftbdbtb formbt, sft vib tif donstrudtor.
     */
    protfdtfd boolfbn stbndbrdFormbtSupportfd;

    /**
     * Tif nbmf of tif nbtivf mftbdbtb formbt for tiis objfdt,
     * initiblizfd to <dodf>null</dodf> bnd sft vib tif donstrudtor.
     */
    protfdtfd String nbtivfMftbdbtbFormbtNbmf = null;

    /**
     * Tif nbmf of tif dlbss implfmfnting <dodf>IIOMftbdbtbFormbt</dodf>
     * bnd rfprfsfnting tif nbtivf mftbdbtb formbt, initiblizfd to
     * <dodf>null</dodf> bnd sft vib tif donstrudtor.
     */
    protfdtfd String nbtivfMftbdbtbFormbtClbssNbmf = null;

    /**
     * An brrby of nbmfs of formbts, otifr tibn tif stbndbrd bnd
     * nbtivf formbts, tibt brf supportfd by tiis plug-in,
     * initiblizfd to <dodf>null</dodf> bnd sft vib tif donstrudtor.
     */
    protfdtfd String[] fxtrbMftbdbtbFormbtNbmfs = null;

    /**
     * An brrby of nbmfs of dlbssfs implfmfnting <dodf>IIOMftbdbtbFormbt</dodf>
     * bnd rfprfsfnting tif mftbdbtb formbts, otifr tibn tif stbndbrd bnd
     * nbtivf formbts, tibt brf supportfd by tiis plug-in,
     * initiblizfd to <dodf>null</dodf> bnd sft vib tif donstrudtor.
     */
    protfdtfd String[] fxtrbMftbdbtbFormbtClbssNbmfs = null;

    /**
     * An <dodf>IIOMftbdbtbControllfr</dodf> tibt is suggfstfd for usf
     * bs tif dontrollfr for tiis <dodf>IIOMftbdbtb</dodf> objfdt.  It
     * mby bf rftrifvfd vib <dodf>gftDffbultControllfr</dodf>.  To
     * instbll tif dffbult dontrollfr, dbll
     * <dodf>sftControllfr(gftDffbultControllfr())</dodf>.  Tiis
     * instbndf vbribblf siould bf sft by subdlbssfs tibt dioosf to
     * providf tifir own dffbult dontrollfr, usublly b GUI, for
     * sftting pbrbmftfrs.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #gftDffbultControllfr
     */
    protfdtfd IIOMftbdbtbControllfr dffbultControllfr = null;

    /**
     * Tif <dodf>IIOMftbdbtbControllfr</dodf> tibt will bf
     * usfd to providf sfttings for tiis <dodf>IIOMftbdbtb</dodf>
     * objfdt wifn tif <dodf>bdtivbtfControllfr</dodf> mftiod
     * is dbllfd.  Tiis vbluf ovfrridfs bny dffbult dontrollfr,
     * fvfn wifn <dodf>null</dodf>.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #sftControllfr(IIOMftbdbtbControllfr)
     * @sff #ibsControllfr()
     * @sff #bdtivbtfControllfr()
     */
    protfdtfd IIOMftbdbtbControllfr dontrollfr = null;

    /**
     * Construdts bn fmpty <dodf>IIOMftbdbtb</dodf> objfdt.  Tif
     * subdlbss is rfsponsiblf for supplying vblufs for bll protfdtfd
     * instbndf vbribblfs tibt will bllow bny non-ovfrriddfn dffbult
     * implfmfntbtions of mftiods to sbtisfy tifir dontrbdts.  For fxbmplf,
     * <dodf>fxtrbMftbdbtbFormbtNbmfs</dodf> siould not ibvf lfngti 0.
     */
    protfdtfd IIOMftbdbtb() {}

    /**
     * Construdts bn <dodf>IIOMftbdbtb</dodf> objfdt witi tif givfn
     * formbt nbmfs bnd formbt dlbss nbmfs, bs wfll bs b boolfbn
     * indidbting wiftifr tif stbndbrd formbt is supportfd.
     *
     * <p> Tiis donstrudtor dofs not bttfmpt to difdk tif dlbss nbmfs
     * for vblidity.  Invblid dlbss nbmfs mby dbusf fxdfptions in
     * subsfqufnt dblls to <dodf>gftMftbdbtbFormbt</dodf>.
     *
     * @pbrbm stbndbrdMftbdbtbFormbtSupportfd <dodf>truf</dodf> if
     * tiis objfdt dbn rfturn or bddfpt b DOM trff using tif stbndbrd
     * mftbdbtb formbt.
     * @pbrbm nbtivfMftbdbtbFormbtNbmf tif nbmf of tif nbtivf mftbdbtb
     * formbt, bs b <dodf>String</dodf>, or <dodf>null</dodf> if tifrf
     * is no nbtivf formbt.
     * @pbrbm nbtivfMftbdbtbFormbtClbssNbmf tif nbmf of tif dlbss of
     * tif nbtivf mftbdbtb formbt, or <dodf>null</dodf> if tifrf is
     * no nbtivf formbt.
     * @pbrbm fxtrbMftbdbtbFormbtNbmfs bn brrby of <dodf>String</dodf>s
     * indidbting bdditionbl formbts supportfd by tiis objfdt, or
     * <dodf>null</dodf> if tifrf brf nonf.
     * @pbrbm fxtrbMftbdbtbFormbtClbssNbmfs bn brrby of <dodf>String</dodf>s
     * indidbting tif dlbss nbmfs of bny bdditionbl formbts supportfd by
     * tiis objfdt, or <dodf>null</dodf> if tifrf brf nonf.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fxtrbMftbdbtbFormbtNbmfs</dodf> ibs lfngti 0.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>fxtrbMftbdbtbFormbtNbmfs</dodf> bnd
     * <dodf>fxtrbMftbdbtbFormbtClbssNbmfs</dodf> brf nfitifr boti
     * <dodf>null</dodf>, nor of tif sbmf lfngti.
     */
    protfdtfd IIOMftbdbtb(boolfbn stbndbrdMftbdbtbFormbtSupportfd,
                          String nbtivfMftbdbtbFormbtNbmf,
                          String nbtivfMftbdbtbFormbtClbssNbmf,
                          String[] fxtrbMftbdbtbFormbtNbmfs,
                          String[] fxtrbMftbdbtbFormbtClbssNbmfs) {
        tiis.stbndbrdFormbtSupportfd = stbndbrdMftbdbtbFormbtSupportfd;
        tiis.nbtivfMftbdbtbFormbtNbmf = nbtivfMftbdbtbFormbtNbmf;
        tiis.nbtivfMftbdbtbFormbtClbssNbmf = nbtivfMftbdbtbFormbtClbssNbmf;
        if (fxtrbMftbdbtbFormbtNbmfs != null) {
            if (fxtrbMftbdbtbFormbtNbmfs.lfngti == 0) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fxtrbMftbdbtbFormbtNbmfs.lfngti == 0!");
            }
            if (fxtrbMftbdbtbFormbtClbssNbmfs == null) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fxtrbMftbdbtbFormbtNbmfs != null && fxtrbMftbdbtbFormbtClbssNbmfs == null!");
            }
            if (fxtrbMftbdbtbFormbtClbssNbmfs.lfngti !=
                fxtrbMftbdbtbFormbtNbmfs.lfngti) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fxtrbMftbdbtbFormbtClbssNbmfs.lfngti != fxtrbMftbdbtbFormbtNbmfs.lfngti!");
            }
            tiis.fxtrbMftbdbtbFormbtNbmfs = fxtrbMftbdbtbFormbtNbmfs.dlonf();
            tiis.fxtrbMftbdbtbFormbtClbssNbmfs = fxtrbMftbdbtbFormbtClbssNbmfs.dlonf();
        } flsf {
            if (fxtrbMftbdbtbFormbtClbssNbmfs != null) {
                tirow nfw IllfgblArgumfntExdfption
                    ("fxtrbMftbdbtbFormbtNbmfs == null && fxtrbMftbdbtbFormbtClbssNbmfs != null!");
            }
        }
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif stbndbrd mftbdbtb formbt is
     * supportfd by <dodf>gftMftbdbtbFormbt</dodf>,
     * <dodf>gftAsTrff</dodf>, <dodf>sftFromTrff</dodf>, bnd
     * <dodf>mfrgfTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns tif vbluf of tif
     * <dodf>stbndbrdFormbtSupportfd</dodf> instbndf vbribblf.
     *
     * @rfturn <dodf>truf</dodf> if tif stbndbrd mftbdbtb formbt
     * is supportfd.
     *
     * @sff #gftAsTrff
     * @sff #sftFromTrff
     * @sff #mfrgfTrff
     * @sff #gftMftbdbtbFormbt
     */
    publid boolfbn isStbndbrdMftbdbtbFormbtSupportfd() {
        rfturn stbndbrdFormbtSupportfd;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis objfdt dofs not support tif
     * <dodf>mfrgfTrff</dodf>, <dodf>sftFromTrff</dodf>, bnd
     * <dodf>rfsft</dodf> mftiods.
     *
     * @rfturn truf if tiis <dodf>IIOMftbdbtb</dodf> objfdt dbnnot bf
     * modififd.
     */
    publid bbstrbdt boolfbn isRfbdOnly();

    /**
     * Rfturns tif nbmf of tif "nbtivf" mftbdbtb formbt for tiis
     * plug-in, wiidi typidblly bllows for losslfss fndoding bnd
     * trbnsmission of tif mftbdbtb storfd in tif formbt ibndlfd by
     * tiis plug-in.  If no sudi formbt is supportfd,
     * <dodf>null</dodf>will bf rfturnfd.
     *
     * <p> Tif strudturf bnd dontfnts of tif "nbtivf" mftbdbtb formbt
     * brf dffinfd by tif plug-in tibt drfbtfd tiis
     * <dodf>IIOMftbdbtb</dodf> objfdt.  Plug-ins for simplf formbts
     * will usublly drfbtf b dummy nodf for tif root, bnd tifn b
     * sfrifs of diild nodfs rfprfsfnting individubl tbgs, diunks, or
     * kfyword/vbluf pbirs.  A plug-in mby dioosf wiftifr or not to
     * dodumfnt its nbtivf formbt.
     *
     * <p> Tif dffbult implfmfntbtion rfturns tif vbluf of tif
     * <dodf>nbtivfMftbdbtbFormbtNbmf</dodf> instbndf vbribblf.
     *
     * @rfturn tif nbmf of tif nbtivf formbt, or <dodf>null</dodf>.
     *
     * @sff #gftExtrbMftbdbtbFormbtNbmfs
     * @sff #gftMftbdbtbFormbtNbmfs
     */
    publid String gftNbtivfMftbdbtbFormbtNbmf() {
        rfturn nbtivfMftbdbtbFormbtNbmf;
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s dontbining tif nbmfs
     * of bdditionbl mftbdbtb formbts, otifr tibn tif nbtivf bnd stbndbrd
     * formbts, rfdognizfd by tiis plug-in's
     * <dodf>gftAsTrff</dodf>, <dodf>sftFromTrff</dodf>, bnd
     * <dodf>mfrgfTrff</dodf> mftiods.  If tifrf brf no sudi bdditionbl
     * formbts, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif dffbult implfmfntbtion rfturns b dlonf of tif
     * <dodf>fxtrbMftbdbtbFormbtNbmfs</dodf> instbndf vbribblf.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s witi lfngti bt lfbst
     * 1, or <dodf>null</dodf>.
     *
     * @sff #gftAsTrff
     * @sff #sftFromTrff
     * @sff #mfrgfTrff
     * @sff #gftNbtivfMftbdbtbFormbtNbmf
     * @sff #gftMftbdbtbFormbtNbmfs
     */
    publid String[] gftExtrbMftbdbtbFormbtNbmfs() {
        if (fxtrbMftbdbtbFormbtNbmfs == null) {
            rfturn null;
        }
        rfturn fxtrbMftbdbtbFormbtNbmfs.dlonf();
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s dontbining tif nbmfs
     * of bll mftbdbtb formbts, indluding tif nbtivf bnd stbndbrd
     * formbts, rfdognizfd by tiis plug-in's <dodf>gftAsTrff</dodf>,
     * <dodf>sftFromTrff</dodf>, bnd <dodf>mfrgfTrff</dodf> mftiods.
     * If tifrf brf no sudi formbts, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif dffbult implfmfntbtion dblls
     * <dodf>gftNbtivfMftbdbtbFormbtNbmf</dodf>,
     * <dodf>isStbndbrdMftbdbtbFormbtSupportfd</dodf>, bnd
     * <dodf>gftExtrbMftbdbtbFormbtNbmfs</dodf> bnd rfturns tif
     * dombinfd rfsults.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     *
     * @sff #gftNbtivfMftbdbtbFormbtNbmf
     * @sff #isStbndbrdMftbdbtbFormbtSupportfd
     * @sff #gftExtrbMftbdbtbFormbtNbmfs
     */
    publid String[] gftMftbdbtbFormbtNbmfs() {
        String nbtivfNbmf = gftNbtivfMftbdbtbFormbtNbmf();
        String stbndbrdNbmf = isStbndbrdMftbdbtbFormbtSupportfd() ?
            IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf : null;
        String[] fxtrbNbmfs = gftExtrbMftbdbtbFormbtNbmfs();

        int numFormbts = 0;
        if (nbtivfNbmf != null) {
            ++numFormbts;
        }
        if (stbndbrdNbmf != null) {
            ++numFormbts;
        }
        if (fxtrbNbmfs != null) {
            numFormbts += fxtrbNbmfs.lfngti;
        }
        if (numFormbts == 0) {
            rfturn null;
        }

        String[] formbts = nfw String[numFormbts];
        int indfx = 0;
        if (nbtivfNbmf != null) {
            formbts[indfx++] = nbtivfNbmf;
        }
        if (stbndbrdNbmf != null) {
            formbts[indfx++] = stbndbrdNbmf;
        }
        if (fxtrbNbmfs != null) {
            for (int i = 0; i < fxtrbNbmfs.lfngti; i++) {
                formbts[indfx++] = fxtrbNbmfs[i];
            }
        }

        rfturn formbts;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbFormbt</dodf> objfdt dfsdribing tif
     * givfn mftbdbtb formbt, or <dodf>null</dodf> if no dfsdription
     * is bvbilbblf.  Tif supplifd nbmf must bf onf of tiosf rfturnfd
     * by <dodf>gftMftbdbtbFormbtNbmfs</dodf> (<i>i.f.</i>, fitifr tif
     * nbtivf formbt nbmf, tif stbndbrd formbt nbmf, or onf of tiosf
     * rfturnfd by <dodf>gftExtrbMftbdbtbFormbtNbmfs</dodf>).
     *
     * <p> Tif dffbult implfmfntbtion difdks tif nbmf bgbinst tif
     * globbl stbndbrd mftbdbtb formbt nbmf, bnd rfturns tibt formbt
     * if it is supportfd.  Otifrwisf, it difdks bgbinst tif nbtivf
     * formbt nbmfs followfd by bny bdditionbl formbt nbmfs.  If b
     * mbtdi is found, it rftrifvfs tif nbmf of tif
     * <dodf>IIOMftbdbtbFormbt</dodf> dlbss from
     * <dodf>nbtivfMftbdbtbFormbtClbssNbmf</dodf> or
     * <dodf>fxtrbMftbdbtbFormbtClbssNbmfs</dodf> bs bppropribtf, bnd
     * donstrudts bn instbndf of tibt dlbss using its
     * <dodf>gftInstbndf</dodf> mftiod.
     *
     * @pbrbm formbtNbmf tif dfsirfd mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbFormbt</dodf> objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf> or is not onf of tif nbmfs rfdognizfd by
     * tif plug-in.
     * @fxdfption IllfgblStbtfExdfption if tif dlbss dorrfsponding to
     * tif formbt nbmf dbnnot bf lobdfd.
     */
    publid IIOMftbdbtbFormbt gftMftbdbtbFormbt(String formbtNbmf) {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }
        if (stbndbrdFormbtSupportfd
            && formbtNbmf.fqubls
                (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            rfturn IIOMftbdbtbFormbtImpl.gftStbndbrdFormbtInstbndf();
        }
        String formbtClbssNbmf = null;
        if (formbtNbmf.fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            formbtClbssNbmf = nbtivfMftbdbtbFormbtClbssNbmf;
        } flsf if (fxtrbMftbdbtbFormbtNbmfs != null) {
            for (int i = 0; i < fxtrbMftbdbtbFormbtNbmfs.lfngti; i++) {
                if (formbtNbmf.fqubls(fxtrbMftbdbtbFormbtNbmfs[i])) {
                    formbtClbssNbmf = fxtrbMftbdbtbFormbtClbssNbmfs[i];
                    brfbk;  // out of for
                }
            }
        }
        if (formbtClbssNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd formbt nbmf");
        }
        try {
            Clbss<?> dls = null;
            finbl Objfdt o = tiis;

            // firstly wf try to usf dlbsslobdfr usfd for lobding
            // tif IIOMftbdbtb implfmbntbtion for tiis plugin.
            ClbssLobdfr lobdfr =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
                            publid ClbssLobdfr run() {
                                rfturn o.gftClbss().gftClbssLobdfr();
                            }
                        });

            try {
                dls = Clbss.forNbmf(formbtClbssNbmf, truf,
                                    lobdfr);
            } dbtdi (ClbssNotFoundExdfption f) {
                // wf fbilfd to lobd IIOMftbdbtbFormbt dlbss by
                // using IIOMftbdbtb dlbsslobdfr.Nfxt try is to
                // usf tirfbd dontfxt dlbsslobdfr.
                lobdfr =
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
                                publid ClbssLobdfr run() {
                                    rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                                }
                        });
                try {
                    dls = Clbss.forNbmf(formbtClbssNbmf, truf,
                                        lobdfr);
                } dbtdi (ClbssNotFoundExdfption f1) {
                    // finblly wf try to usf systfm dlbsslobdfr in dbsf
                    // if wf fbilfd to lobd IIOMftbdbtbFormbt implfmfntbtion
                    // dlbss bbovf.
                    dls = Clbss.forNbmf(formbtClbssNbmf, truf,
                                        ClbssLobdfr.gftSystfmClbssLobdfr());
                }
            }

            Mftiod mfti = dls.gftMftiod("gftInstbndf");
            rfturn (IIOMftbdbtbFormbt) mfti.invokf(null);
        } dbtdi (Exdfption f) {
            RuntimfExdfption fx =
                nfw IllfgblStbtfExdfption ("Cbn't obtbin formbt");
            fx.initCbusf(f);
            tirow fx;
        }

    }

    /**
     * Rfturns bn XML DOM <dodf>Nodf</dodf> objfdt tibt rfprfsfnts tif
     * root of b trff of mftbdbtb dontbinfd witiin tiis objfdt
     * bddording to tif donvfntions dffinfd by b givfn mftbdbtb
     * formbt.
     *
     * <p> Tif nbmfs of tif bvbilbblf mftbdbtb formbts mby bf qufrifd
     * using tif <dodf>gftMftbdbtbFormbtNbmfs</dodf> mftiod.
     *
     * @pbrbm formbtNbmf tif dfsirfd mftbdbtb formbt.
     *
     * @rfturn bn XML DOM <dodf>Nodf</dodf> objfdt forming tif
     * root of b trff.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf> or is not onf of tif nbmfs rfturnfd by
     * <dodf>gftMftbdbtbFormbtNbmfs</dodf>.
     *
     * @sff #gftMftbdbtbFormbtNbmfs
     * @sff #sftFromTrff
     * @sff #mfrgfTrff
     */
    publid bbstrbdt Nodf gftAsTrff(String formbtNbmf);

    /**
     * Altfrs tif intfrnbl stbtf of tiis <dodf>IIOMftbdbtb</dodf>
     * objfdt from b trff of XML DOM <dodf>Nodf</dodf>s wiosf syntbx
     * is dffinfd by tif givfn mftbdbtb formbt.  Tif prfvious stbtf is
     * bltfrfd only bs nfdfssbry to bddommodbtf tif nodfs tibt brf
     * prfsfnt in tif givfn trff.  If tif trff strudturf or dontfnts
     * brf invblid, bn <dodf>IIOInvblidTrffExdfption</dodf> will bf
     * tirown.
     *
     * <p> As tif sfmbntids of iow b trff or subtrff mby bf mfrgfd witi
     * bnotifr trff brf domplftfly formbt-spfdifid, plug-in butiors mby
     * implfmfnt tiis mftiod in wibtfvfr mbnnfr is most bppropribtf for
     * tif formbt, indluding simply rfplbding bll fxisting stbtf witi tif
     * dontfnts of tif givfn trff.
     *
     * @pbrbm formbtNbmf tif dfsirfd mftbdbtb formbt.
     * @pbrbm root bn XML DOM <dodf>Nodf</dodf> objfdt forming tif
     * root of b trff.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis objfdt is rfbd-only.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf> or is not onf of tif nbmfs rfturnfd by
     * <dodf>gftMftbdbtbFormbtNbmfs</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>root</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IIOInvblidTrffExdfption if tif trff dbnnot bf pbrsfd
     * suddfssfully using tif rulfs of tif givfn formbt.
     *
     * @sff #gftMftbdbtbFormbtNbmfs
     * @sff #gftAsTrff
     * @sff #sftFromTrff
     */
    publid bbstrbdt void mfrgfTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption;

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif diromb
     * informbtion of tif stbndbrd <dodf>jbvbx_imbgfio_1.0</dodf>
     * mftbdbtb formbt, or <dodf>null</dodf> if no sudi informbtion is
     * bvbilbblf.  Tiis mftiod is intfndfd to bf dbllfd by tif utility
     * routinf <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdCirombNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif
     * domprfssion informbtion of tif stbndbrd
     * <dodf>jbvbx_imbgfio_1.0</dodf> mftbdbtb formbt, or
     * <dodf>null</dodf> if no sudi informbtion is bvbilbblf.  Tiis
     * mftiod is intfndfd to bf dbllfd by tif utility routinf
     * <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdComprfssionNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif dbtb
     * formbt informbtion of tif stbndbrd
     * <dodf>jbvbx_imbgfio_1.0</dodf> mftbdbtb formbt, or
     * <dodf>null</dodf> if no sudi informbtion is bvbilbblf.  Tiis
     * mftiod is intfndfd to bf dbllfd by tif utility routinf
     * <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdDbtbNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif
     * dimfnsion informbtion of tif stbndbrd
     * <dodf>jbvbx_imbgfio_1.0</dodf> mftbdbtb formbt, or
     * <dodf>null</dodf> if no sudi informbtion is bvbilbblf.  Tiis
     * mftiod is intfndfd to bf dbllfd by tif utility routinf
     * <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdDimfnsionNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif dodumfnt
     * informbtion of tif stbndbrd <dodf>jbvbx_imbgfio_1.0</dodf>
     * mftbdbtb formbt, or <dodf>null</dodf> if no sudi informbtion is
     * bvbilbblf.  Tiis mftiod is intfndfd to bf dbllfd by tif utility
     * routinf <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdDodumfntNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif tfxtubl
     * informbtion of tif stbndbrd <dodf>jbvbx_imbgfio_1.0</dodf>
     * mftbdbtb formbt, or <dodf>null</dodf> if no sudi informbtion is
     * bvbilbblf.  Tiis mftiod is intfndfd to bf dbllfd by tif utility
     * routinf <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdTfxtNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif tiling
     * informbtion of tif stbndbrd <dodf>jbvbx_imbgfio_1.0</dodf>
     * mftbdbtb formbt, or <dodf>null</dodf> if no sudi informbtion is
     * bvbilbblf.  Tiis mftiod is intfndfd to bf dbllfd by tif utility
     * routinf <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #gftStbndbrdTrff
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdTilfNodf() {
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif
     * trbnspbrfndy informbtion of tif stbndbrd
     * <dodf>jbvbx_imbgfio_1.0</dodf> mftbdbtb formbt, or
     * <dodf>null</dodf> if no sudi informbtion is bvbilbblf.  Tiis
     * mftiod is intfndfd to bf dbllfd by tif utility routinf
     * <dodf>gftStbndbrdTrff</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>null</dodf>.
     *
     * <p> Subdlbssfs siould ovfrridf tiis mftiod to produdf bn
     * bppropribtf subtrff if tify wisi to support tif stbndbrd
     * mftbdbtb formbt.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf>, or <dodf>null</dodf>.
     */
    protfdtfd IIOMftbdbtbNodf gftStbndbrdTrbnspbrfndyNodf() {
        rfturn null;
    }

    /**
     * Appfnds b nfw nodf to bn fxisting nodf, if tif nfw nodf is
     * non-<dodf>null</dodf>.
     */
    privbtf void bppfnd(IIOMftbdbtbNodf root, IIOMftbdbtbNodf nodf) {
        if (nodf != null) {
            root.bppfndCiild(nodf);
        }
    }

    /**
     * A utility mftiod to rfturn b trff of
     * <dodf>IIOMftbdbtbNodf</dodf>s rfprfsfnting tif mftbdbtb
     * dontbinfd witiin tiis objfdt bddording to tif donvfntions of
     * tif stbndbrd <dodf>jbvbx_imbgfio_1.0</dodf> mftbdbtb formbt.
     *
     * <p> Tiis mftiod dblls tif vbrious <dodf>gftStbndbrd*Nodf</dodf>
     * mftiods to supply fbdi of tif subtrffs rootfd bt tif diildrfn
     * of tif root nodf.  If bny of tiosf mftiods rfturns
     * <dodf>null</dodf>, tif dorrfsponding subtrff will bf omittfd.
     * If bll of tifm rfturn <dodf>null</dodf>, b trff donsisting of b
     * singlf root nodf will bf rfturnfd.
     *
     * @rfturn bn <dodf>IIOMftbdbtbNodf</dodf> rfprfsfnting tif root
     * of b mftbdbtb trff in tif <dodf>jbvbx_imbgfio_1.0</dodf>
     * formbt.
     *
     * @sff #gftStbndbrdCirombNodf
     * @sff #gftStbndbrdComprfssionNodf
     * @sff #gftStbndbrdDbtbNodf
     * @sff #gftStbndbrdDimfnsionNodf
     * @sff #gftStbndbrdDodumfntNodf
     * @sff #gftStbndbrdTfxtNodf
     * @sff #gftStbndbrdTilfNodf
     * @sff #gftStbndbrdTrbnspbrfndyNodf
     */
    protfdtfd finbl IIOMftbdbtbNodf gftStbndbrdTrff() {
        IIOMftbdbtbNodf root = nfw IIOMftbdbtbNodf
                (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf);
        bppfnd(root, gftStbndbrdCirombNodf());
        bppfnd(root, gftStbndbrdComprfssionNodf());
        bppfnd(root, gftStbndbrdDbtbNodf());
        bppfnd(root, gftStbndbrdDimfnsionNodf());
        bppfnd(root, gftStbndbrdDodumfntNodf());
        bppfnd(root, gftStbndbrdTfxtNodf());
        bppfnd(root, gftStbndbrdTilfNodf());
        bppfnd(root, gftStbndbrdTrbnspbrfndyNodf());
        rfturn root;
    }

    /**
     * Sfts tif intfrnbl stbtf of tiis <dodf>IIOMftbdbtb</dodf> objfdt
     * from b trff of XML DOM <dodf>Nodf</dodf>s wiosf syntbx is
     * dffinfd by tif givfn mftbdbtb formbt.  Tif prfvious stbtf is
     * disdbrdfd.  If tif trff's strudturf or dontfnts brf invblid, bn
     * <dodf>IIOInvblidTrffExdfption</dodf> will bf tirown.
     *
     * <p> Tif dffbult implfmfntbtion dblls <dodf>rfsft</dodf>
     * followfd by <dodf>mfrgfTrff(formbtNbmf, root)</dodf>.
     *
     * @pbrbm formbtNbmf tif dfsirfd mftbdbtb formbt.
     * @pbrbm root bn XML DOM <dodf>Nodf</dodf> objfdt forming tif
     * root of b trff.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis objfdt is rfbd-only.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf> or is not onf of tif nbmfs rfturnfd by
     * <dodf>gftMftbdbtbFormbtNbmfs</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>root</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IIOInvblidTrffExdfption if tif trff dbnnot bf pbrsfd
     * suddfssfully using tif rulfs of tif givfn formbt.
     *
     * @sff #gftMftbdbtbFormbtNbmfs
     * @sff #gftAsTrff
     * @sff #mfrgfTrff
     */
    publid void sftFromTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption {
        rfsft();
        mfrgfTrff(formbtNbmf, root);
    }

    /**
     * Rfsfts bll tif dbtb storfd in tiis objfdt to dffbult vblufs,
     * usublly to tif stbtf tiis objfdt wbs in immfdibtfly bftfr
     * donstrudtion, tiougi tif prfdisf sfmbntids brf plug-in spfdifid.
     * Notf tibt tifrf brf mbny possiblf dffbult vblufs, dfpfnding on
     * iow tif objfdt wbs drfbtfd.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis objfdt is rfbd-only.
     *
     * @sff jbvbx.imbgfio.ImbgfRfbdfr#gftStrfbmMftbdbtb
     * @sff jbvbx.imbgfio.ImbgfRfbdfr#gftImbgfMftbdbtb
     * @sff jbvbx.imbgfio.ImbgfWritfr#gftDffbultStrfbmMftbdbtb
     * @sff jbvbx.imbgfio.ImbgfWritfr#gftDffbultImbgfMftbdbtb
     */
    publid bbstrbdt void rfsft();

    /**
     * Sfts tif <dodf>IIOMftbdbtbControllfr</dodf> to bf usfd
     * to providf sfttings for tiis <dodf>IIOMftbdbtb</dodf>
     * objfdt wifn tif <dodf>bdtivbtfControllfr</dodf> mftiod
     * is dbllfd, ovfrriding bny dffbult dontrollfr.  If tif
     * brgumfnt is <dodf>null</dodf>, no dontrollfr will bf
     * usfd, indluding bny dffbult.  To rfstorf tif dffbult, usf
     * <dodf>sftControllfr(gftDffbultControllfr())</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif <dodf>dontrollfr</dodf>
     * instbndf vbribblf to tif supplifd vbluf.
     *
     * @pbrbm dontrollfr An bppropribtf
     * <dodf>IIOMftbdbtbControllfr</dodf>, or <dodf>null</dodf>.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid void sftControllfr(IIOMftbdbtbControllfr dontrollfr) {
        tiis.dontrollfr = dontrollfr;
    }

    /**
     * Rfturns wibtfvfr <dodf>IIOMftbdbtbControllfr</dodf> is durrfntly
     * instbllfd.  Tiis dould bf tif dffbult if tifrf is onf,
     * <dodf>null</dodf>, or tif brgumfnt of tif most rfdfnt dbll
     * to <dodf>sftControllfr</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns tif vbluf of tif
     * <dodf>dontrollfr</dodf> instbndf vbribblf.
     *
     * @rfturn tif durrfntly instbllfd
     * <dodf>IIOMftbdbtbControllfr</dodf>, or <dodf>null</dodf>.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #sftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid IIOMftbdbtbControllfr gftControllfr() {
        rfturn dontrollfr;
    }

    /**
     * Rfturns tif dffbult <dodf>IIOMftbdbtbControllfr</dodf>, if tifrf
     * is onf, rfgbrdlfss of tif durrfntly instbllfd dontrollfr.  If
     * tifrf is no dffbult dontrollfr, rfturns <dodf>null</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns tif vbluf of tif
     * <dodf>dffbultControllfr</dodf> instbndf vbribblf.
     *
     * @rfturn tif dffbult <dodf>IIOMftbdbtbControllfr</dodf>, or
     * <dodf>null</dodf>.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #sftControllfr(IIOMftbdbtbControllfr)
     * @sff #gftControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid IIOMftbdbtbControllfr gftDffbultControllfr() {
        rfturn dffbultControllfr;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tifrf is b dontrollfr instbllfd
     * for tiis <dodf>IIOMftbdbtb</dodf> objfdt.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>truf</dodf> if tif
     * <dodf>gftControllfr</dodf> mftiod rfturns b
     * non-<dodf>null</dodf> vbluf.
     *
     * @rfturn <dodf>truf</dodf> if b dontrollfr is instbllfd.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #sftControllfr(IIOMftbdbtbControllfr)
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid boolfbn ibsControllfr() {
        rfturn (gftControllfr() != null);
    }

    /**
     * Adtivbtfs tif instbllfd <dodf>IIOMftbdbtbControllfr</dodf> for
     * tiis <dodf>IIOMftbdbtb</dodf> objfdt bnd rfturns tif rfsulting
     * vbluf.  Wifn tiis mftiod rfturns <dodf>truf</dodf>, bll vblufs for tiis
     * <dodf>IIOMftbdbtb</dodf> objfdt will bf rfbdy for tif nfxt writf
     * opfrbtion.  If <dodf>fblsf</dodf> is
     * rfturnfd, no sfttings in tiis objfdt will ibvf bffn disturbfd
     * (<i>i.f.</i>, tif usfr dbndflfd tif opfrbtion).
     *
     * <p> Ordinbrily, tif dontrollfr will bf b GUI providing b usfr
     * intfrfbdf for b subdlbss of <dodf>IIOMftbdbtb</dodf> for b
     * pbrtidulbr plug-in.  Controllfrs nffd not bf GUIs, iowfvfr.
     *
     * <p> Tif dffbult implfmfntbtion dblls <dodf>gftControllfr</dodf>
     * bnd tif dblls <dodf>bdtivbtf</dodf> on tif rfturnfd objfdt if
     * <dodf>ibsControllfr</dodf> rfturns <dodf>truf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif dontrollfr domplftfd normblly.
     *
     * @fxdfption IllfgblStbtfExdfption if tifrf is no dontrollfr
     * durrfntly instbllfd.
     *
     * @sff IIOMftbdbtbControllfr
     * @sff #sftControllfr(IIOMftbdbtbControllfr)
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     */
    publid boolfbn bdtivbtfControllfr() {
        if (!ibsControllfr()) {
            tirow nfw IllfgblStbtfExdfption("ibsControllfr() == fblsf!");
        }
        rfturn gftControllfr().bdtivbtf(tiis);
    }
}
