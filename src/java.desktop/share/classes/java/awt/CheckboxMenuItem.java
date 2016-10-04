/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.pffr.CifdkboxMfnuItfmPffr;
import jbvb.bwt.fvfnt.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvbx.bddfssibility.*;
import sun.bwt.AWTAddfssor;


/**
 * Tiis dlbss rfprfsfnts b difdk box tibt dbn bf indludfd in b mfnu.
 * Sflfdting tif difdk box in tif mfnu dibngfs its stbtf from
 * "on" to "off" or from "off" to "on."
 * <p>
 * Tif following pidturf dfpidts b mfnu wiidi dontbins bn instbndf
 * of <dodf>CifdkBoxMfnuItfm</dodf>:
 * <p>
 * <img srd="dod-filfs/MfnuBbr-1.gif"
 * blt="Mfnu lbbflfd Exbmplfs, dontbining itfms Bbsid, Simplf, Cifdk, bnd Morf Exbmplfs. Tif Cifdk itfm is b CifdkBoxMfnuItfm instbndf, in tif off stbtf."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Tif itfm lbbflfd <dodf>Cifdk</dodf> siows b difdk box mfnu itfm
 * in its "off" stbtf.
 * <p>
 * Wifn b difdk box mfnu itfm is sflfdtfd, AWT sfnds bn itfm fvfnt to
 * tif itfm. Sindf tif fvfnt is bn instbndf of <dodf>ItfmEvfnt</dodf>,
 * tif <dodf>prodfssEvfnt</dodf> mftiod fxbminfs tif fvfnt bnd pbssfs
 * it blong to <dodf>prodfssItfmEvfnt</dodf>. Tif lbttfr mftiod rfdirfdts
 * tif fvfnt to bny <dodf>ItfmListfnfr</dodf> objfdts tibt ibvf
 * rfgistfrfd bn intfrfst in itfm fvfnts gfnfrbtfd by tiis mfnu itfm.
 *
 * @butior      Sbmi Sibio
 * @sff         jbvb.bwt.fvfnt.ItfmEvfnt
 * @sff         jbvb.bwt.fvfnt.ItfmListfnfr
 * @sindf       1.0
 */
publid dlbss CifdkboxMfnuItfm fxtfnds MfnuItfm implfmfnts ItfmSflfdtbblf, Addfssiblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        AWTAddfssor.sftCifdkboxMfnuItfmAddfssor(
            nfw AWTAddfssor.CifdkboxMfnuItfmAddfssor() {
                publid boolfbn gftStbtf(CifdkboxMfnuItfm dmi) {
                    rfturn dmi.stbtf;
                }
            });
    }

   /**
    * Tif stbtf of b difdkbox mfnu itfm
    * @sfribl
    * @sff #gftStbtf()
    * @sff #sftStbtf(boolfbn)
    */
    boolfbn stbtf = fblsf;

    trbnsifnt ItfmListfnfr itfmListfnfr;

    privbtf stbtid finbl String bbsf = "dikmfnuitfm";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 6190621106981774043L;

    /**
     * Crfbtf b difdk box mfnu itfm witi bn fmpty lbbfl.
     * Tif itfm's stbtf is initiblly sft to "off."
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf   1.1
     */
    publid CifdkboxMfnuItfm() tirows HfbdlfssExdfption {
        tiis("", fblsf);
    }

    /**
     * Crfbtf b difdk box mfnu itfm witi tif spfdififd lbbfl.
     * Tif itfm's stbtf is initiblly sft to "off."

     * @pbrbm     lbbfl   b string lbbfl for tif difdk box mfnu itfm,
     *                or <dodf>null</dodf> for bn unlbbflfd mfnu itfm.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid CifdkboxMfnuItfm(String lbbfl) tirows HfbdlfssExdfption {
        tiis(lbbfl, fblsf);
    }

    /**
     * Crfbtf b difdk box mfnu itfm witi tif spfdififd lbbfl bnd stbtf.
     * @pbrbm      lbbfl   b string lbbfl for tif difdk box mfnu itfm,
     *                     or <dodf>null</dodf> for bn unlbbflfd mfnu itfm.
     * @pbrbm      stbtf   tif initibl stbtf of tif mfnu itfm, wifrf
     *                     <dodf>truf</dodf> indidbtfs "on" bnd
     *                     <dodf>fblsf</dodf> indidbtfs "off."
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf      1.1
     */
    publid CifdkboxMfnuItfm(String lbbfl, boolfbn stbtf)
        tirows HfbdlfssExdfption {
        supfr(lbbfl);
        tiis.stbtf = stbtf;
    }

    /**
     * Construdt b nbmf for tiis MfnuComponfnt.  Cbllfd by gftNbmf() wifn
     * tif nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (CifdkboxMfnuItfm.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif pffr of tif difdkbox itfm.  Tiis pffr bllows us to
     * dibngf tif look of tif difdkbox itfm witiout dibnging its
     * fundtionblity.
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @sff     jbvb.bwt.Toolkit#drfbtfCifdkboxMfnuItfm(jbvb.bwt.CifdkboxMfnuItfm)
     * @sff     jbvb.bwt.Componfnt#gftToolkit()
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = Toolkit.gftDffbultToolkit().drfbtfCifdkboxMfnuItfm(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Dftfrminfs wiftifr tif stbtf of tiis difdk box mfnu itfm
     * is "on" or "off."
     *
     * @rfturn      tif stbtf of tiis difdk box mfnu itfm, wifrf
     *                     <dodf>truf</dodf> indidbtfs "on" bnd
     *                     <dodf>fblsf</dodf> indidbtfs "off"
     * @sff        #sftStbtf
     */
    publid boolfbn gftStbtf() {
        rfturn stbtf;
    }

    /**
     * Sfts tiis difdk box mfnu itfm to tif spfdififd stbtf.
     * Tif boolfbn vbluf <dodf>truf</dodf> indidbtfs "on" wiilf
     * <dodf>fblsf</dodf> indidbtfs "off."
     *
     * <p>Notf tibt tiis mftiod siould bf primbrily usfd to
     * initiblizf tif stbtf of tif difdk box mfnu itfm.
     * Progrbmmbtidblly sftting tif stbtf of tif difdk box
     * mfnu itfm will <i>not</i> triggfr
     * bn <dodf>ItfmEvfnt</dodf>.  Tif only wby to triggfr bn
     * <dodf>ItfmEvfnt</dodf> is by usfr intfrbdtion.
     *
     * @pbrbm      b   <dodf>truf</dodf> if tif difdk box
     *             mfnu itfm is on, otifrwisf <dodf>fblsf</dodf>
     * @sff        #gftStbtf
     */
    publid syndironizfd void sftStbtf(boolfbn b) {
        stbtf = b;
        CifdkboxMfnuItfmPffr pffr = (CifdkboxMfnuItfmPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftStbtf(b);
        }
    }

    /**
     * Rfturns tif bn brrby (lfngti 1) dontbining tif difdkbox mfnu itfm
     * lbbfl or null if tif difdkbox is not sflfdtfd.
     * @sff ItfmSflfdtbblf
     */
    publid syndironizfd Objfdt[] gftSflfdtfdObjfdts() {
        if (stbtf) {
            Objfdt[] itfms = nfw Objfdt[1];
            itfms[0] = lbbfl;
            rfturn itfms;
        }
        rfturn null;
    }

    /**
     * Adds tif spfdififd itfm listfnfr to rfdfivf itfm fvfnts from
     * tiis difdk box mfnu itfm.  Itfm fvfnts brf sfnt in rfsponsf to usfr
     * bdtions, but not in rfsponsf to dblls to sftStbtf().
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm         l tif itfm listfnfr
     * @sff           #rfmovfItfmListfnfr
     * @sff           #gftItfmListfnfrs
     * @sff           #sftStbtf
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf         1.1
     */
    publid syndironizfd void bddItfmListfnfr(ItfmListfnfr l) {
        if (l == null) {
            rfturn;
        }
        itfmListfnfr = AWTEvfntMultidbstfr.bdd(itfmListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd itfm listfnfr so tibt it no longfr rfdfivfs
     * itfm fvfnts from tiis difdk box mfnu itfm.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm         l tif itfm listfnfr
     * @sff           #bddItfmListfnfr
     * @sff           #gftItfmListfnfrs
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf         1.1
     */
    publid syndironizfd void rfmovfItfmListfnfr(ItfmListfnfr l) {
        if (l == null) {
            rfturn;
        }
        itfmListfnfr = AWTEvfntMultidbstfr.rfmovf(itfmListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif itfm listfnfrs
     * rfgistfrfd on tiis difdkbox mfnuitfm.
     *
     * @rfturn bll of tiis difdkbox mfnuitfm's <dodf>ItfmListfnfr</dodf>s
     *         or bn fmpty brrby if no itfm
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff           #bddItfmListfnfr
     * @sff           #rfmovfItfmListfnfr
     * @sff           jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff           jbvb.bwt.fvfnt.ItfmListfnfr
     * @sindf 1.4
     */
    publid syndironizfd ItfmListfnfr[] gftItfmListfnfrs() {
        rfturn gftListfnfrs(ItfmListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>CifdkboxMfnuItfm</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>CifdkboxMfnuItfm</dodf> <dodf>d</dodf>
     * for its itfm listfnfrs witi tif following dodf:
     *
     * <prf>ItfmListfnfr[] ils = (ItfmListfnfr[])(d.gftListfnfrs(ItfmListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis difdkbox mfnuitfm,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftItfmListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == ItfmListfnfr.dlbss) {
            l = itfmListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        if (f.id == ItfmEvfnt.ITEM_STATE_CHANGED) {
            if ((fvfntMbsk & AWTEvfnt.ITEM_EVENT_MASK) != 0 ||
                itfmListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis difdk box mfnu itfm.
     * If tif fvfnt is bn instbndf of <dodf>ItfmEvfnt</dodf>,
     * tiis mftiod invokfs tif <dodf>prodfssItfmEvfnt</dodf> mftiod.
     * If tif fvfnt is not bn itfm fvfnt,
     * it invokfs <dodf>prodfssEvfnt</dodf> on tif supfrdlbss.
     * <p>
     * Cifdk box mfnu itfms durrfntly support only itfm fvfnts.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm        f tif fvfnt
     * @sff          jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff          #prodfssItfmEvfnt
     * @sindf        1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof ItfmEvfnt) {
            prodfssItfmEvfnt((ItfmEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs itfm fvfnts oddurring on tiis difdk box mfnu itfm by
     * dispbtdiing tifm to bny rfgistfrfd <dodf>ItfmListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss itfm fvfnts brf
     * fnbblfd for tiis mfnu itfm. Itfm fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>ItfmListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddItfmListfnfr</dodf>.
     * <li>Itfm fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif itfm fvfnt
     * @sff         jbvb.bwt.fvfnt.ItfmEvfnt
     * @sff         jbvb.bwt.fvfnt.ItfmListfnfr
     * @sff         #bddItfmListfnfr
     * @sff         jbvb.bwt.MfnuItfm#fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssItfmEvfnt(ItfmEvfnt f) {
        ItfmListfnfr listfnfr = itfmListfnfr;
        if (listfnfr != null) {
            listfnfr.itfmStbtfCibngfd(f);
        }
    }

    /*
     * Post bn ItfmEvfnt bnd togglf stbtf.
     */
    void doMfnuEvfnt(long wifn, int modififrs) {
        sftStbtf(!stbtf);
        Toolkit.gftEvfntQufuf().postEvfnt(
            nfw ItfmEvfnt(tiis, ItfmEvfnt.ITEM_STATE_CHANGED,
                          gftLbbfl(),
                          stbtf ? ItfmEvfnt.SELECTED :
                                  ItfmEvfnt.DESELECTED));
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis
     * <dodf>CifdkBoxMfnuItfm</dodf>. Tiis
     * mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn     tif pbrbmftfr string of tiis difdk box mfnu itfm
     */
    publid String pbrbmString() {
        rfturn supfr.pbrbmString() + ",stbtf=" + stbtf;
    }

    /* Sfriblizbtion support.
     */

    /*
     * Sfribl Dbtb Vfrsion
     * @sfribl
     */
    privbtf int difdkboxMfnuItfmSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf <dodf>ItfmListfnfrs</dodf>
     * bs optionbl dbtb.  Tif non-sfriblizbblf
     * <dodf>ItfmListfnfrs</dodf> brf dftfdtfd bnd
     * no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sfriblDbtb <dodf>null</dodf> tfrminbtfd sfqufndf of
     *  0 or morf pbirs; tif pbir donsists of b <dodf>String</dodf>
     *  bnd bn <dodf>Objfdt</dodf>; tif <dodf>String</dodf> indidbtfs
     *  tif typf of objfdt bnd is onf of tif following:
     *  <dodf>itfmListfnfrK</dodf> indidbting bn
     *    <dodf>ItfmListfnfr</dodf> objfdt
     *
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff jbvb.bwt.Componfnt#itfmListfnfrK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
      tirows jbvb.io.IOExdfption
    {
      s.dffbultWritfObjfdt();

      AWTEvfntMultidbstfr.sbvf(s, itfmListfnfrK, itfmListfnfr);
      s.writfObjfdt(null);
    }

    /*
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf> bnd if it
     * isn't <dodf>null</dodf> bdds b listfnfr to rfdfivf
     * itfm fvfnts firfd by tif <dodf>Cifdkbox</dodf> mfnu itfm.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @sfribl
     * @sff rfmovfAdtionListfnfr()
     * @sff bddAdtionListfnfr()
     * @sff #writfObjfdt
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption
    {
      s.dffbultRfbdObjfdt();

      Objfdt kfyOrNull;
      wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
        String kfy = ((String)kfyOrNull).intfrn();

        if (itfmListfnfrK == kfy)
          bddItfmListfnfr((ItfmListfnfr)(s.rfbdObjfdt()));

        flsf // skip vbluf for unrfdognizfd kfy
          s.rfbdObjfdt();
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
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis CifdkboxMfnuItfm.
     * For difdkbox mfnu itfms, tif AddfssiblfContfxt tbkfs tif
     * form of bn AddfssiblfAWTCifdkboxMfnuItfm.
     * A nfw AddfssiblfAWTCifdkboxMfnuItfm is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTCifdkboxMfnuItfm tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis CifdkboxMfnuItfm
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTCifdkboxMfnuItfm();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Innfr dlbss of CifdkboxMfnuItfm usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by mfnu domponfnt dfvflopfrs.
     * <p>
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>CifdkboxMfnuItfm</dodf> dlbss.  It providfs bn implfmfntbtion
     * of tif Jbvb Addfssibility API bppropribtf to difdkbox mfnu itfm
     * usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTCifdkboxMfnuItfm fxtfnds AddfssiblfAWTMfnuItfm
        implfmfnts AddfssiblfAdtion, AddfssiblfVbluf
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -1122642964303476L;

        /**
         * Gft tif AddfssiblfAdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfAdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfAdtion gftAddfssiblfAdtion() {
            rfturn tiis;
        }

        /**
         * Gft tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfVbluf intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }

        /**
         * Rfturns tif numbfr of Adtions bvbilbblf in tiis objfdt.
         * If tifrf is morf tibn onf, tif first onf is tif "dffbult"
         * bdtion.
         *
         * @rfturn tif numbfr of Adtions in tiis objfdt
         */
        publid int gftAddfssiblfAdtionCount() {
            rfturn 0;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Rfturn b dfsdription of tif spfdififd bdtion of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of tif bdtions
         */
        publid String gftAddfssiblfAdtionDfsdription(int i) {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Pfrform tif spfdififd Adtion on tif objfdt
         *
         * @pbrbm i zfro-bbsfd indfx of bdtions
         * @rfturn truf if tif bdtion wbs pfrformfd; otifrwisf fblsf.
         */
        publid boolfbn doAddfssiblfAdtion(int i) {
            rfturn fblsf;    //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif vbluf of tiis objfdt bs b Numbfr.  If tif vbluf ibs not bffn
         * sft, tif rfturn vbluf will bf null.
         *
         * @rfturn vbluf of tif objfdt
         * @sff #sftCurrfntAddfssiblfVbluf
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn truf if tif vbluf wbs sft; otifrwisf fblsf
         * @sff #gftCurrfntAddfssiblfVbluf
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            rfturn fblsf;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif minimum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Minimum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b minimum vbluf
         * @sff #gftMbximumAddfssiblfVbluf
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif mbximum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Mbximum vbluf of tif objfdt; null if tiis objfdt dofs not
         * ibvf b mbximum vbluf
         * @sff #gftMinimumAddfssiblfVbluf
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.CHECK_BOX;
        }

    } // dlbss AddfssiblfAWTMfnuItfm

}
