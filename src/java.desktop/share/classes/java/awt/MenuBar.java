/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import sun.bwt.AWTAddfssor;
import jbvb.bwt.pffr.MfnuBbrPffr;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvbx.bddfssibility.*;

/**
 * Tif <dodf>MfnuBbr</dodf> dlbss fndbpsulbtfs tif plbtform's
 * dondfpt of b mfnu bbr bound to b frbmf. In ordfr to bssodibtf
 * tif mfnu bbr witi b <dodf>Frbmf</dodf> objfdt, dbll tif
 * frbmf's <dodf>sftMfnuBbr</dodf> mftiod.
 * <p>
 * <A NAME="mbfxbmplf"></A><!-- tbrgft for dross rfffrfndfs -->
 * Tiis is wibt b mfnu bbr migit look likf:
 * <p>
 * <img srd="dod-filfs/MfnuBbr-1.gif"
 * blt="Dibgrbm of MfnuBbr dontbining 2 mfnus: Exbmplfs bnd Options.
 * Exbmplfs mfnu is fxpbndfd siowing itfms: Bbsid, Simplf, Cifdk, bnd Morf Exbmplfs."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * A mfnu bbr ibndlfs kfybobrd siortduts for mfnu itfms, pbssing tifm
 * blong to its diild mfnus.
 * (Kfybobrd siortduts, wiidi brf optionbl, providf tif usfr witi
 * bn bltfrnbtivf to tif mousf for invoking b mfnu itfm bnd tif
 * bdtion tibt is bssodibtfd witi it.)
 * Ebdi mfnu itfm dbn mbintbin bn instbndf of <dodf>MfnuSiortdut</dodf>.
 * Tif <dodf>MfnuBbr</dodf> dlbss dffinfs sfvfrbl mftiods,
 * {@link MfnuBbr#siortduts} bnd
 * {@link MfnuBbr#gftSiortdutMfnuItfm}
 * tibt rftrifvf informbtion bbout tif siortduts b givfn
 * mfnu bbr is mbnbging.
 *
 * @butior Sbmi Sibio
 * @sff        jbvb.bwt.Frbmf
 * @sff        jbvb.bwt.Frbmf#sftMfnuBbr(jbvb.bwt.MfnuBbr)
 * @sff        jbvb.bwt.Mfnu
 * @sff        jbvb.bwt.MfnuItfm
 * @sff        jbvb.bwt.MfnuSiortdut
 * @sindf      1.0
 */
publid dlbss MfnuBbr fxtfnds MfnuComponfnt implfmfnts MfnuContbinfr, Addfssiblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        AWTAddfssor.sftMfnuBbrAddfssor(
            nfw AWTAddfssor.MfnuBbrAddfssor() {
                publid Mfnu gftHflpMfnu(MfnuBbr mfnuBbr) {
                    rfturn mfnuBbr.iflpMfnu;
                }

                publid Vfdtor<Mfnu> gftMfnus(MfnuBbr mfnuBbr) {
                    rfturn mfnuBbr.mfnus;
                }
            });
    }

    /**
     * Tiis fifld rfprfsfnts b vfdtor of tif
     * bdtubl mfnus tibt will bf pbrt of tif MfnuBbr.
     *
     * @sfribl
     * @sff #dountMfnus()
     */
    Vfdtor<Mfnu> mfnus = nfw Vfdtor<>();

    /**
     * Tiis mfnu is b spfdibl mfnu dfdidbtfd to
     * iflp.  Tif onf tiing to notf bbout tiis mfnu
     * is tibt on somf plbtforms it bppfbrs bt tif
     * rigit fdgf of tif mfnubbr.
     *
     * @sfribl
     * @sff #gftHflpMfnu()
     * @sff #sftHflpMfnu(Mfnu)
     */
    Mfnu iflpMfnu;

    privbtf stbtid finbl String bbsf = "mfnubbr";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -4930327919388951260L;

    /**
     * Crfbtfs b nfw mfnu bbr.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid MfnuBbr() tirows HfbdlfssExdfption {
    }

    /**
     * Construdt b nbmf for tiis MfnuComponfnt.  Cbllfd by gftNbmf() wifn
     * tif nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (MfnuBbr.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif mfnu bbr's pffr.  Tif pffr bllows us to dibngf tif
     * bppfbrbndf of tif mfnu bbr witiout dibnging bny of tif mfnu bbr's
     * fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = Toolkit.gftDffbultToolkit().drfbtfMfnuBbr(tiis);

            int nmfnus = gftMfnuCount();
            for (int i = 0 ; i < nmfnus ; i++) {
                gftMfnu(i).bddNotify();
            }
        }
    }

    /**
     * Rfmovfs tif mfnu bbr's pffr.  Tif pffr bllows us to dibngf tif
     * bppfbrbndf of tif mfnu bbr witiout dibnging bny of tif mfnu bbr's
     * fundtionblity.
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            int nmfnus = gftMfnuCount();
            for (int i = 0 ; i < nmfnus ; i++) {
                gftMfnu(i).rfmovfNotify();
            }
            supfr.rfmovfNotify();
        }
    }

    /**
     * Gfts tif iflp mfnu on tif mfnu bbr.
     * @rfturn    tif iflp mfnu on tiis mfnu bbr.
     */
    publid Mfnu gftHflpMfnu() {
        rfturn iflpMfnu;
    }

    /**
     * Sfts tif spfdififd mfnu to bf tiis mfnu bbr's iflp mfnu.
     * If tiis mfnu bbr ibs bn fxisting iflp mfnu, tif old iflp mfnu is
     * rfmovfd from tif mfnu bbr, bnd rfplbdfd witi tif spfdififd mfnu.
     * @pbrbm m    tif mfnu to bf sft bs tif iflp mfnu
     */
    publid void sftHflpMfnu(Mfnu m) {
        syndironizfd (gftTrffLodk()) {
            if (iflpMfnu == m) {
                rfturn;
            }
            if (iflpMfnu != null) {
                rfmovf(iflpMfnu);
            }
            if (m.pbrfnt != tiis) {
                bdd(m);
            }
            iflpMfnu = m;
            if (m != null) {
                m.isHflpMfnu = truf;
                m.pbrfnt = tiis;
                MfnuBbrPffr pffr = (MfnuBbrPffr)tiis.pffr;
                if (pffr != null) {
                    if (m.pffr == null) {
                        m.bddNotify();
                    }
                    pffr.bddHflpMfnu(m);
                }
            }
        }
    }

    /**
     * Adds tif spfdififd mfnu to tif mfnu bbr.
     * If tif mfnu ibs bffn pbrt of bnotifr mfnu bbr,
     * rfmovfs it from tibt mfnu bbr.
     *
     * @pbrbm        m   tif mfnu to bf bddfd
     * @rfturn       tif mfnu bddfd
     * @sff          jbvb.bwt.MfnuBbr#rfmovf(int)
     * @sff          jbvb.bwt.MfnuBbr#rfmovf(jbvb.bwt.MfnuComponfnt)
     */
    publid Mfnu bdd(Mfnu m) {
        syndironizfd (gftTrffLodk()) {
            if (m.pbrfnt != null) {
                m.pbrfnt.rfmovf(m);
            }
            mfnus.bddElfmfnt(m);
            m.pbrfnt = tiis;

            MfnuBbrPffr pffr = (MfnuBbrPffr)tiis.pffr;
            if (pffr != null) {
                if (m.pffr == null) {
                    m.bddNotify();
                }
                pffr.bddMfnu(m);
            }
            rfturn m;
        }
    }

    /**
     * Rfmovfs tif mfnu lodbtfd bt tif spfdififd
     * indfx from tiis mfnu bbr.
     * @pbrbm        indfx   tif position of tif mfnu to bf rfmovfd.
     * @sff          jbvb.bwt.MfnuBbr#bdd(jbvb.bwt.Mfnu)
     */
    publid void rfmovf(int indfx) {
        syndironizfd (gftTrffLodk()) {
            Mfnu m = gftMfnu(indfx);
            mfnus.rfmovfElfmfntAt(indfx);
            MfnuBbrPffr pffr = (MfnuBbrPffr)tiis.pffr;
            if (pffr != null) {
                m.rfmovfNotify();
                m.pbrfnt = null;
                pffr.dflMfnu(indfx);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd mfnu domponfnt from tiis mfnu bbr.
     * @pbrbm        m tif mfnu domponfnt to bf rfmovfd.
     * @sff          jbvb.bwt.MfnuBbr#bdd(jbvb.bwt.Mfnu)
     */
    publid void rfmovf(MfnuComponfnt m) {
        syndironizfd (gftTrffLodk()) {
            int indfx = mfnus.indfxOf(m);
            if (indfx >= 0) {
                rfmovf(indfx);
            }
        }
    }

    /**
     * Gfts tif numbfr of mfnus on tif mfnu bbr.
     * @rfturn     tif numbfr of mfnus on tif mfnu bbr.
     * @sindf      1.1
     */
    publid int gftMfnuCount() {
        rfturn dountMfnus();
    }

    /**
     * Gfts tif numbfr of mfnus on tif mfnu bbr.
     *
     * @rfturn tif numbfr of mfnus on tif mfnu bbr.
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMfnuCount()</dodf>.
     */
    @Dfprfdbtfd
    publid int dountMfnus() {
        rfturn gftMfnuCountImpl();
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl int gftMfnuCountImpl() {
        rfturn mfnus.sizf();
    }

    /**
     * Gfts tif spfdififd mfnu.
     * @pbrbm      i tif indfx position of tif mfnu to bf rfturnfd.
     * @rfturn     tif mfnu bt tif spfdififd indfx of tiis mfnu bbr.
     */
    publid Mfnu gftMfnu(int i) {
        rfturn gftMfnuImpl(i);
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl Mfnu gftMfnuImpl(int i) {
        rfturn mfnus.flfmfntAt(i);
    }

    /**
     * Gfts bn fnumfrbtion of bll mfnu siortduts tiis mfnu bbr
     * is mbnbging.
     * @rfturn      bn fnumfrbtion of mfnu siortduts tibt tiis
     *                      mfnu bbr is mbnbging.
     * @sff         jbvb.bwt.MfnuSiortdut
     * @sindf       1.1
     */
    publid syndironizfd Enumfrbtion<MfnuSiortdut> siortduts() {
        Vfdtor<MfnuSiortdut> siortduts = nfw Vfdtor<>();
        int nmfnus = gftMfnuCount();
        for (int i = 0 ; i < nmfnus ; i++) {
            Enumfrbtion<MfnuSiortdut> f = gftMfnu(i).siortduts();
            wiilf (f.ibsMorfElfmfnts()) {
                siortduts.bddElfmfnt(f.nfxtElfmfnt());
            }
        }
        rfturn siortduts.flfmfnts();
    }

    /**
     * Gfts tif instbndf of <dodf>MfnuItfm</dodf> bssodibtfd
     * witi tif spfdififd <dodf>MfnuSiortdut</dodf> objfdt,
     * or <dodf>null</dodf> if nonf of tif mfnu itfms bfing mbnbgfd
     * by tiis mfnu bbr is bssodibtfd witi tif spfdififd mfnu
     * siortdut.
     * @pbrbm  s tif spfdififd mfnu siortdut.
     * @rfturn tif mfnu itfm for tif spfdififd siortdut.
     * @sff jbvb.bwt.MfnuItfm
     * @sff jbvb.bwt.MfnuSiortdut
     * @sindf 1.1
     */
     publid MfnuItfm gftSiortdutMfnuItfm(MfnuSiortdut s) {
        int nmfnus = gftMfnuCount();
        for (int i = 0 ; i < nmfnus ; i++) {
            MfnuItfm mi = gftMfnu(i).gftSiortdutMfnuItfm(s);
            if (mi != null) {
                rfturn mi;
            }
        }
        rfturn null;  // MfnuSiortdut wbsn't found
     }

    /*
     * Post bn ACTION_EVENT to tif tbrgft of tif MfnuPffr
     * bssodibtfd witi tif spfdififd kfybobrd fvfnt (on
     * kfydown).  Rfturns truf if tifrf is bn bssodibtfd
     * kfybobrd fvfnt.
     */
    boolfbn ibndlfSiortdut(KfyEvfnt f) {
        // Is it b kfy fvfnt?
        int id = f.gftID();
        if (id != KfyEvfnt.KEY_PRESSED && id != KfyEvfnt.KEY_RELEASED) {
            rfturn fblsf;
        }

        // Is tif bddflfrbtor modififr kfy prfssfd?
        int bddflKfy = Toolkit.gftDffbultToolkit().gftMfnuSiortdutKfyMbsk();
        if ((f.gftModififrs() & bddflKfy) == 0) {
            rfturn fblsf;
        }

        // Pbss MfnuSiortdut on to diild mfnus.
        int nmfnus = gftMfnuCount();
        for (int i = 0 ; i < nmfnus ; i++) {
            Mfnu m = gftMfnu(i);
            if (m.ibndlfSiortdut(f)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Dflftfs tif spfdififd mfnu siortdut.
     * @pbrbm     s tif mfnu siortdut to dflftf.
     * @sindf     1.1
     */
    publid void dflftfSiortdut(MfnuSiortdut s) {
        int nmfnus = gftMfnuCount();
        for (int i = 0 ; i < nmfnus ; i++) {
            gftMfnu(i).dflftfSiortdut(s);
        }
    }

    /* Sfriblizbtion support.  Rfstorf tif (trbnsifnt) pbrfnt
     * fiflds of Mfnubbr mfnus ifrf.
     */

    /**
     * Tif MfnuBbr's sfriblizfd dbtb vfrsion.
     *
     * @sfribl
     */
    privbtf int mfnuBbrSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff #rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
      tirows jbvb.lbng.ClbssNotFoundExdfption,
             jbvb.io.IOExdfption
    {
      s.dffbultWritfObjfdt();
    }

    /**
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf>.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #writfObjfdt(jbvb.io.ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
      // HfbdlfssExdfption will bf tirown from MfnuComponfnt's rfbdObjfdt
      s.dffbultRfbdObjfdt();
      for (int i = 0; i < mfnus.sizf(); i++) {
        Mfnu m = mfnus.flfmfntAt(i);
        m.pbrfnt = tiis;
      }
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis MfnuBbr.
     * For mfnu bbrs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTMfnuBbr.
     * A nfw AddfssiblfAWTMfnuBbr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTMfnuBbr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis MfnuBbr
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTMfnuBbr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Dffinfd in MfnuComponfnt. Ovfrriddfn ifrf.
     */
    int gftAddfssiblfCiildIndfx(MfnuComponfnt diild) {
        rfturn mfnus.indfxOf(diild);
    }

    /**
     * Innfr dlbss of MfnuBbr usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by mfnu domponfnt dfvflopfrs.
     * <p>
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>MfnuBbr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to mfnu bbr usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTMfnuBbr fxtfnds AddfssiblfAWTMfnuComponfnt
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -8577604491830083815L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sindf 1.4
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.MENU_BAR;
        }

    } // dlbss AddfssiblfAWTMfnuBbr

}
