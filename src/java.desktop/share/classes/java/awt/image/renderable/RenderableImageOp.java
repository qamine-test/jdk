/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* ********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf jbvb.bwt.imbgf.rfndfrbblf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.RfndfringHints;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;

/**
 * Tiis dlbss ibndlfs tif rfndfrbblf bspfdts of bn opfrbtion witi iflp
 * from its bssodibtfd instbndf of b ContfxtublRfndfrfdImbgfFbdtory.
 */
publid dlbss RfndfrbblfImbgfOp implfmfnts RfndfrbblfImbgf {

    /** A PbrbmftfrBlodk dontbining sourdf bnd pbrbmftfrs. */
    PbrbmftfrBlodk pbrbmBlodk;

    /** Tif bssodibtfd ContfxtublRfndfrfdImbgfFbdtory. */
    ContfxtublRfndfrfdImbgfFbdtory myCRIF;

    /** Tif bounding box of tif rfsults of tiis RfndfrbblfImbgfOp. */
    Rfdtbnglf2D boundingBox;


    /**
     * Construdts b RfndfrfdImbgfOp givfn b
     * ContfxtublRfndfrfdImbgfFbdtory objfdt, bnd
     * b PbrbmftfrBlodk dontbining RfndfrbblfImbgf sourdfs bnd otifr
     * pbrbmftfrs.  Any RfndfrfdImbgf sourdfs rfffrfndfd by tif
     * PbrbmftfrBlodk will bf ignorfd.
     *
     * @pbrbm CRIF b ContfxtublRfndfrfdImbgfFbdtory objfdt
     * @pbrbm pbrbmBlodk b PbrbmftfrBlodk dontbining tiis opfrbtion's sourdf
     *        imbgfs bnd otifr pbrbmftfrs nfdfssbry for tif opfrbtion
     *        to run.
     */
    publid RfndfrbblfImbgfOp(ContfxtublRfndfrfdImbgfFbdtory CRIF,
                             PbrbmftfrBlodk pbrbmBlodk) {
        tiis.myCRIF = CRIF;
        tiis.pbrbmBlodk = (PbrbmftfrBlodk) pbrbmBlodk.dlonf();
    }

    /**
     * Rfturns b vfdtor of RfndfrbblfImbgfs tibt brf tif sourdfs of
     * imbgf dbtb for tiis RfndfrbblfImbgf. Notf tibt tiis mftiod mby
     * rfturn bn fmpty vfdtor, to indidbtf tibt tif imbgf ibs no sourdfs,
     * or null, to indidbtf tibt no informbtion is bvbilbblf.
     *
     * @rfturn b (possibly fmpty) Vfdtor of RfndfrbblfImbgfs, or null.
     */
    publid Vfdtor<RfndfrbblfImbgf> gftSourdfs() {
        rfturn gftRfndfrbblfSourdfs();
    }

    privbtf Vfdtor<RfndfrbblfImbgf> gftRfndfrbblfSourdfs() {
        Vfdtor<RfndfrbblfImbgf> sourdfs = null;

        if (pbrbmBlodk.gftNumSourdfs() > 0) {
            sourdfs = nfw Vfdtor<>();
            int i = 0;
            wiilf (i < pbrbmBlodk.gftNumSourdfs()) {
                Objfdt o = pbrbmBlodk.gftSourdf(i);
                if (o instbndfof RfndfrbblfImbgf) {
                    sourdfs.bdd((RfndfrbblfImbgf)o);
                    i++;
                } flsf {
                    brfbk;
                }
            }
        }
        rfturn sourdfs;
    }

    /**
     * Gfts b propfrty from tif propfrty sft of tiis imbgf.
     * If tif propfrty nbmf is not rfdognizfd, jbvb.bwt.Imbgf.UndffinfdPropfrty
     * will bf rfturnfd.
     *
     * @pbrbm nbmf tif nbmf of tif propfrty to gft, bs b String.
     * @rfturn b rfffrfndf to tif propfrty Objfdt, or tif vbluf
     *         jbvb.bwt.Imbgf.UndffinfdPropfrty.
     */
    publid Objfdt gftPropfrty(String nbmf) {
        rfturn myCRIF.gftPropfrty(pbrbmBlodk, nbmf);
    }

    /**
     * Rfturn b list of nbmfs rfdognizfd by gftPropfrty.
     * @rfturn b list of propfrty nbmfs.
     */
    publid String[] gftPropfrtyNbmfs() {
        rfturn myCRIF.gftPropfrtyNbmfs();
    }

    /**
     * Rfturns truf if suddfssivf rfndfrings (tibt is, dblls to
     * drfbtfRfndfring() or drfbtfSdblfdRfndfring()) witi tif sbmf brgumfnts
     * mby produdf difffrfnt rfsults.  Tiis mftiod mby bf usfd to
     * dftfrminf wiftifr bn fxisting rfndfring mby bf dbdifd bnd
     * rfusfd.  Tif CRIF's isDynbmid mftiod will bf dbllfd.
     * @rfturn <dodf>truf</dodf> if suddfssivf rfndfrings witi tif
     *         sbmf brgumfnts migit produdf difffrfnt rfsults;
     *         <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isDynbmid() {
        rfturn myCRIF.isDynbmid();
    }

    /**
     * Gfts tif widti in usfr doordinbtf spbdf.  By donvfntion, tif
     * usubl widti of b RfndfrbblfImbgf is fqubl to tif imbgf's bspfdt
     * rbtio (widti dividfd by ifigit).
     *
     * @rfturn tif widti of tif imbgf in usfr doordinbtfs.
     */
    publid flobt gftWidti() {
        if (boundingBox == null) {
            boundingBox = myCRIF.gftBounds2D(pbrbmBlodk);
        }
        rfturn (flobt)boundingBox.gftWidti();
    }

    /**
     * Gfts tif ifigit in usfr doordinbtf spbdf.  By donvfntion, tif
     * usubl ifigit of b RfndfrfdImbgf is fqubl to 1.0F.
     *
     * @rfturn tif ifigit of tif imbgf in usfr doordinbtfs.
     */
    publid flobt gftHfigit() {
        if (boundingBox == null) {
            boundingBox = myCRIF.gftBounds2D(pbrbmBlodk);
        }
        rfturn (flobt)boundingBox.gftHfigit();
    }

    /**
     * Gfts tif minimum X doordinbtf of tif rfndfring-indfpfndfnt imbgf dbtb.
     */
    publid flobt gftMinX() {
        if (boundingBox == null) {
            boundingBox = myCRIF.gftBounds2D(pbrbmBlodk);
        }
        rfturn (flobt)boundingBox.gftMinX();
    }

    /**
     * Gfts tif minimum Y doordinbtf of tif rfndfring-indfpfndfnt imbgf dbtb.
     */
    publid flobt gftMinY() {
        if (boundingBox == null) {
            boundingBox = myCRIF.gftBounds2D(pbrbmBlodk);
        }
        rfturn (flobt)boundingBox.gftMinY();
    }

    /**
     * Cibngf tif durrfnt PbrbmftfrBlodk of tif opfrbtion, bllowing
     * fditing of imbgf rfndfring dibins.  Tif ffffdts of sudi b
     * dibngf will bf visiblf wifn b nfw rfndfring is drfbtfd from
     * tiis RfndfrbblfImbgfOp or bny dfpfndfnt RfndfrbblfImbgfOp.
     *
     * @pbrbm pbrbmBlodk tif nfw PbrbmftfrBlodk.
     * @rfturn tif old PbrbmftfrBlodk.
     * @sff #gftPbrbmftfrBlodk
     */
    publid PbrbmftfrBlodk sftPbrbmftfrBlodk(PbrbmftfrBlodk pbrbmBlodk) {
        PbrbmftfrBlodk oldPbrbmBlodk = tiis.pbrbmBlodk;
        tiis.pbrbmBlodk = (PbrbmftfrBlodk)pbrbmBlodk.dlonf();
        rfturn oldPbrbmBlodk;
    }

    /**
     * Rfturns b rfffrfndf to tif durrfnt pbrbmftfr blodk.
     * @rfturn tif <dodf>PbrbmftfrBlodk</dodf> of tiis
     *         <dodf>RfndfrbblfImbgfOp</dodf>.
     * @sff #sftPbrbmftfrBlodk(PbrbmftfrBlodk)
     */
    publid PbrbmftfrBlodk gftPbrbmftfrBlodk() {
        rfturn pbrbmBlodk;
    }

    /**
     * Crfbtfs b RfndfrfdImbgf instbndf of tiis imbgf witi widti w, bnd
     * ifigit i in pixfls.  Tif RfndfrContfxt is built butombtidblly
     * witi bn bppropribtf usr2dfv trbnsform bnd bn brfb of intfrfst
     * of tif full imbgf.  All tif rfndfring iints domf from iints
     * pbssfd in.
     *
     * <p> If w == 0, it will bf tbkfn to fqubl
     * Mbti.round(i*(gftWidti()/gftHfigit())).
     * Similbrly, if i == 0, it will bf tbkfn to fqubl
     * Mbti.round(w*(gftHfigit()/gftWidti())).  Onf of
     * w or i must bf non-zfro or flsf bn IllfgblArgumfntExdfption
     * will bf tirown.
     *
     * <p> Tif drfbtfd RfndfrfdImbgf mby ibvf b propfrty idfntififd
     * by tif String HINTS_OBSERVED to indidbtf wiidi RfndfringHints
     * wfrf usfd to drfbtf tif imbgf.  In bddition bny RfndfrfdImbgfs
     * tibt brf obtbinfd vib tif gftSourdfs() mftiod on tif drfbtfd
     * RfndfrfdImbgf mby ibvf sudi b propfrty.
     *
     * @pbrbm w tif widti of rfndfrfd imbgf in pixfls, or 0.
     * @pbrbm i tif ifigit of rfndfrfd imbgf in pixfls, or 0.
     * @pbrbm iints b RfndfringHints objfdt dontbining iints.
     * @rfturn b RfndfrfdImbgf dontbining tif rfndfrfd dbtb.
     */
    publid RfndfrfdImbgf drfbtfSdblfdRfndfring(int w, int i,
                                               RfndfringHints iints) {
        // DSR -- dodf to try to gft b unit sdblf
        doublf sx = (doublf)w/gftWidti();
        doublf sy = (doublf)i/gftHfigit();
        if (Mbti.bbs(sx/sy - 1.0) < 0.01) {
            sx = sy;
        }
        AffinfTrbnsform usr2dfv = AffinfTrbnsform.gftSdblfInstbndf(sx, sy);
        RfndfrContfxt nfwRC = nfw RfndfrContfxt(usr2dfv, iints);
        rfturn drfbtfRfndfring(nfwRC);
    }

    /**
     * Gfts b RfndfrfdImbgf instbndf of tiis imbgf witi b dffbult
     * widti bnd ifigit in pixfls.  Tif RfndfrContfxt is built
     * butombtidblly witi bn bppropribtf usr2dfv trbnsform bnd bn brfb
     * of intfrfst of tif full imbgf.  All tif rfndfring iints domf
     * from iints pbssfd in.  Implfmfntors of tiis intfrfbdf must bf
     * surf tibt tifrf is b dffinfd dffbult widti bnd ifigit.
     *
     * @rfturn b RfndfrfdImbgf dontbining tif rfndfrfd dbtb.
     */
    publid RfndfrfdImbgf drfbtfDffbultRfndfring() {
        AffinfTrbnsform usr2dfv = nfw AffinfTrbnsform(); // Idfntity
        RfndfrContfxt nfwRC = nfw RfndfrContfxt(usr2dfv);
        rfturn drfbtfRfndfring(nfwRC);
    }

    /**
     * Crfbtfs b RfndfrfdImbgf wiidi rfprfsfnts tiis
     * RfndfrbblfImbgfOp (indluding its Rfndfrbblf sourdfs) rfndfrfd
     * bddording to tif givfn RfndfrContfxt.
     *
     * <p> Tiis mftiod supports dibining of fitifr Rfndfrbblf or
     * RfndfrfdImbgf opfrbtions.  If sourdfs in
     * tif PbrbmftfrBlodk usfd to donstrudt tif RfndfrbblfImbgfOp brf
     * RfndfrbblfImbgfs, tifn b tirff stfp prodfss is followfd:
     *
     * <ol>
     * <li> mbpRfndfrContfxt() is dbllfd on tif bssodibtfd CRIF for
     * fbdi RfndfrbblfImbgf sourdf;
     * <li> drfbtfRfndfring() is dbllfd on fbdi of tif RfndfrbblfImbgf sourdfs
     * using tif bbdkwbrds-mbppfd RfndfrContfxts obtbinfd in stfp 1,
     * rfsulting in b rfndfring of fbdi sourdf;
     * <li> ContfxtublRfndfrfdImbgfFbdtory.drfbtf() is dbllfd
     * witi b nfw PbrbmftfrBlodk dontbining tif pbrbmftfrs of
     * tif RfndfrbblfImbgfOp bnd tif RfndfrfdImbgfs tibt wfrf drfbtfd by tif
     * drfbtfRfndfring() dblls.
     * </ol>
     *
     * <p> If tif flfmfnts of tif sourdf Vfdtor of
     * tif PbrbmftfrBlodk usfd to donstrudt tif RfndfrbblfImbgfOp brf
     * instbndfs of RfndfrfdImbgf, tifn tif CRIF.drfbtf() mftiod is
     * dbllfd immfdibtfly using tif originbl PbrbmftfrBlodk.
     * Tiis providfs b bbsis dbsf for tif rfdursion.
     *
     * <p> Tif drfbtfd RfndfrfdImbgf mby ibvf b propfrty idfntififd
     * by tif String HINTS_OBSERVED to indidbtf wiidi RfndfringHints
     * (from tif RfndfrContfxt) wfrf usfd to drfbtf tif imbgf.
     * In bddition bny RfndfrfdImbgfs
     * tibt brf obtbinfd vib tif gftSourdfs() mftiod on tif drfbtfd
     * RfndfrfdImbgf mby ibvf sudi b propfrty.
     *
     * @pbrbm rfndfrContfxt Tif RfndfrContfxt to usf to pfrform tif rfndfring.
     * @rfturn b RfndfrfdImbgf dontbining tif dfsirfd output imbgf.
     */
    publid RfndfrfdImbgf drfbtfRfndfring(RfndfrContfxt rfndfrContfxt) {
        RfndfrfdImbgf imbgf = null;
        RfndfrContfxt rdOut = null;

        // Clonf tif originbl PbrbmftfrBlodk; if tif PbrbmftfrBlodk
        // dontbins RfndfrbblfImbgf sourdfs, tify will bf rfplbdfd by
        // RfndfrfdImbgfs.
        PbrbmftfrBlodk rfndfrfdPbrbmBlodk = (PbrbmftfrBlodk)pbrbmBlodk.dlonf();
        Vfdtor<? fxtfnds Objfdt> sourdfs = gftRfndfrbblfSourdfs();

        try {
            // Tiis bssumfs tibt if tifrf is no rfndfrbblf sourdf, tibt tifrf
            // is b rfndfrfd sourdf in pbrbmBlodk

            if (sourdfs != null) {
                Vfdtor<Objfdt> rfndfrfdSourdfs = nfw Vfdtor<>();
                for (int i = 0; i < sourdfs.sizf(); i++) {
                    rdOut = myCRIF.mbpRfndfrContfxt(i, rfndfrContfxt,
                                                    pbrbmBlodk, tiis);
                    RfndfrfdImbgf rdrdImbgf =
                        ((RfndfrbblfImbgf)sourdfs.flfmfntAt(i)).drfbtfRfndfring(rdOut);
                    if (rdrdImbgf == null) {
                        rfturn null;
                    }

                    // Add tiis rfndfrfd imbgf to tif PbrbmftfrBlodk's
                    // list of RfndfrfdImbgfs.
                    rfndfrfdSourdfs.bddElfmfnt(rdrdImbgf);
                }

                if (rfndfrfdSourdfs.sizf() > 0) {
                    rfndfrfdPbrbmBlodk.sftSourdfs(rfndfrfdSourdfs);
                }
            }

            rfturn myCRIF.drfbtf(rfndfrContfxt, rfndfrfdPbrbmBlodk);
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            // Tiis siould nfvfr ibppfn
            rfturn null;
        }
    }
}
