/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Point;
import jbvbx.swing.Adtion;
import jbvbx.swing.fvfnt.CibngfListfnfr;

/**
 * A plbdf witiin b dodumfnt vifw tibt rfprfsfnts wifrf
 * tiings dbn bf insfrtfd into tif dodumfnt modfl.  A dbrft
 * ibs b position in tif dodumfnt rfffrrfd to bs b dot.
 * Tif dot is wifrf tif dbrft is durrfntly lodbtfd in tif
 * modfl.  Tifrf is
 * b sfdond position mbintbinfd by tif dbrft tibt rfprfsfnts
 * tif otifr fnd of b sflfdtion dbllfd mbrk.  If tifrf is
 * no sflfdtion tif dot bnd mbrk will bf fqubl.  If b sflfdtion
 * fxists, tif two vblufs will bf difffrfnt.
 * <p>
 * Tif dot dbn bf plbdfd by fitifr dblling
 * <dodf>sftDot</dodf> or <dodf>movfDot</dodf>.  Sftting
 * tif dot ibs tif ffffdt of rfmoving bny sflfdtion tibt mby
 * ibvf prfviously fxistfd.  Tif dot bnd mbrk will bf fqubl.
 * Moving tif dot ibs tif ffffdt of drfbting b sflfdtion bs
 * tif mbrk is lfft bt wibtfvfr position it prfviously ibd.
 *
 * @butior  Timotiy Prinzing
 */
publid intfrfbdf Cbrft {

    /**
     * Cbllfd wifn tif UI is bfing instbllfd into tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis dbn bf usfd
     * to gbin bddfss to tif modfl tibt is bfing nbvigbtfd
     * by tif implfmfntbtion of tiis intfrfbdf.
     *
     * @pbrbm d tif JTfxtComponfnt
     */
    publid void instbll(JTfxtComponfnt d);

    /**
     * Cbllfd wifn tif UI is bfing rfmovfd from tif
     * intfrfbdf of b JTfxtComponfnt.  Tiis is usfd to
     * unrfgistfr bny listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif JTfxtComponfnt
     */
    publid void dfinstbll(JTfxtComponfnt d);

    /**
     * Rfndfrs tif dbrft. Tiis mftiod is dbllfd by UI dlbssfs.
     *
     * @pbrbm g tif grbpiids dontfxt
     */
    publid void pbint(Grbpiids g);

    /**
     * Adds b listfnfr to trbdk wifnfvfr tif dbrft position
     * ibs bffn dibngfd.
     *
     * @pbrbm l tif dibngf listfnfr
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l);

    /**
     * Rfmovfs b listfnfr tibt wbs trbdking dbrft position dibngfs.
     *
     * @pbrbm l tif dibngf listfnfr
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l);

    /**
     * Dftfrminfs if tif dbrft is durrfntly visiblf.
     *
     * @rfturn truf if tif dbrft is visiblf flsf fblsf
     */
    publid boolfbn isVisiblf();

    /**
     * Sfts tif visibility of tif dbrft.
     *
     * @pbrbm v  truf if tif dbrft siould bf siown,
     *  bnd fblsf if tif dbrft siould bf iiddfn
     */
    publid void sftVisiblf(boolfbn v);

    /**
     * Dftfrminfs if tif sflfdtion is durrfntly visiblf.
     *
     * @rfturn truf if tif dbrft is visiblf flsf fblsf
     */
    publid boolfbn isSflfdtionVisiblf();

    /**
     * Sfts tif visibility of tif sflfdtion
     *
     * @pbrbm v  truf if tif dbrft siould bf siown,
     *  bnd fblsf if tif dbrft siould bf iiddfn
     */
    publid void sftSflfdtionVisiblf(boolfbn v);

    /**
     * Sft tif durrfnt dbrft visubl lodbtion.  Tiis dbn bf usfd wifn
     * moving bftwffn linfs tibt ibvf unfvfn fnd positions (sudi bs
     * wifn dbrft up or down bdtions oddur).  If tfxt flows
     * lfft-to-rigit or rigit-to-lfft tif x-doordinbtf will indidbtf
     * tif dfsirfd nbvigbtion lodbtion for vfrtidbl movfmfnt.  If
     * tif tfxt flow is top-to-bottom, tif y-doordinbtf will indidbtf
     * tif dfsirfd nbvigbtion lodbtion for iorizontbl movfmfnt.
     *
     * @pbrbm p  tif Point to usf for tif sbvfd position.  Tiis
     *   dbn bf null to indidbtf tifrf is no visubl lodbtion.
     */
    publid void sftMbgidCbrftPosition(Point p);

    /**
     * Gfts tif durrfnt dbrft visubl lodbtion.
     *
     * @rfturn tif visubl position.
     * @sff #sftMbgidCbrftPosition
     */
    publid Point gftMbgidCbrftPosition();

    /**
     * Sfts tif blink rbtf of tif dbrft.  Tiis dftfrminfs if
     * bnd iow fbst tif dbrft blinks, dommonly usfd bs onf
     * wby to bttrbdt bttfntion to tif dbrft.
     *
     * @pbrbm rbtf  tif dflby in millisfdonds &gt;=0.  If tiis is
     *  zfro tif dbrft will not blink.
     */
    publid void sftBlinkRbtf(int rbtf);

    /**
     * Gfts tif blink rbtf of tif dbrft.  Tiis dftfrminfs if
     * bnd iow fbst tif dbrft blinks, dommonly usfd bs onf
     * wby to bttrbdt bttfntion to tif dbrft.
     *
     * @rfturn tif dflby in millisfdonds &gt;=0.  If tiis is
     *  zfro tif dbrft will not blink.
     */
    publid int gftBlinkRbtf();

    /**
     * Fftdifs tif durrfnt position of tif dbrft.
     *
     * @rfturn tif position &gt;=0
     */
    publid int gftDot();

    /**
     * Fftdifs tif durrfnt position of tif mbrk.  If tifrf
     * is b sflfdtion, tif mbrk will not bf tif sbmf bs
     * tif dot.
     *
     * @rfturn tif position &gt;=0
     */
    publid int gftMbrk();

    /**
     * Sfts tif dbrft position to somf position.  Tiis
     * dbusfs tif mbrk to bfdomf tif sbmf bs tif dot,
     * ffffdtivfly sftting tif sflfdtion rbngf to zfro.
     * <p>
     * If tif pbrbmftfr is nfgbtivf or bfyond tif lfngti of tif dodumfnt,
     * tif dbrft is plbdfd bt tif bfginning or bt tif fnd, rfspfdtivfly.
     *
     * @pbrbm dot  tif nfw position to sft tif dbrft to
     */
    publid void sftDot(int dot);

    /**
     * Movfs tif dbrft position (dot) to somf otifr position,
     * lfbving bfiind tif mbrk.  Tiis is usfful for
     * mbking sflfdtions.
     *
     * @pbrbm dot  tif nfw position to movf tif dbrft to &gt;=0
     */
    publid void movfDot(int dot);

};
