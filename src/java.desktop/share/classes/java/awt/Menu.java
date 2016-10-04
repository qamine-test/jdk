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
import jbvb.bwt.pffr.MfnuPffr;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvbx.bddfssibility.*;
import sun.bwt.AWTAddfssor;

/**
 * A <dodf>Mfnu</dodf> objfdt is b pull-down mfnu domponfnt
 * tibt is dfployfd from b mfnu bbr.
 * <p>
 * A mfnu dbn optionblly bf b <i>tfbr-off</i> mfnu. A tfbr-off mfnu
 * dbn bf opfnfd bnd drbggfd bwby from its pbrfnt mfnu bbr or mfnu.
 * It rfmbins on tif sdrffn bftfr tif mousf button ibs bffn rflfbsfd.
 * Tif mfdibnism for tfbring off b mfnu is plbtform dfpfndfnt, sindf
 * tif look bnd fffl of tif tfbr-off mfnu is dftfrminfd by its pffr.
 * On plbtforms tibt do not support tfbr-off mfnus, tif tfbr-off
 * propfrty is ignorfd.
 * <p>
 * Ebdi itfm in b mfnu must bflong to tif <dodf>MfnuItfm</dodf>
 * dlbss. It dbn bf bn instbndf of <dodf>MfnuItfm</dodf>, b submfnu
 * (bn instbndf of <dodf>Mfnu</dodf>), or b difdk box (bn instbndf of
 * <dodf>CifdkboxMfnuItfm</dodf>).
 *
 * @butior Sbmi Sibio
 * @sff     jbvb.bwt.MfnuItfm
 * @sff     jbvb.bwt.CifdkboxMfnuItfm
 * @sindf   1.0
 */
publid dlbss Mfnu fxtfnds MfnuItfm implfmfnts MfnuContbinfr, Addfssiblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        AWTAddfssor.sftMfnuAddfssor(
            nfw AWTAddfssor.MfnuAddfssor() {
                publid Vfdtor<MfnuItfm> gftItfms(Mfnu mfnu) {
                    rfturn mfnu.itfms;
                }
            });
    }

    /**
     * A vfdtor of tif itfms tibt will bf pbrt of tif Mfnu.
     *
     * @sfribl
     * @sff #dountItfms()
     */
    Vfdtor<MfnuItfm> itfms = nfw Vfdtor<>();

    /**
     * Tiis fifld indidbtfs wiftifr tif mfnu ibs tif
     * tfbr of propfrty or not.  It will bf sft to
     * <dodf>truf</dodf> if tif mfnu ibs tif tfbr off
     * propfrty bnd it will bf sft to <dodf>fblsf</dodf>
     * if it dofs not.
     * A torn off mfnu dbn bf dflftfd by b usfr wifn
     * it is no longfr nffdfd.
     *
     * @sfribl
     * @sff #isTfbrOff()
     */
    boolfbn             tfbrOff;

    /**
     * Tiis fifld will bf sft to <dodf>truf</dodf>
     * if tif Mfnu in qufstion is bdtublly b iflp
     * mfnu.  Otifrwisf it will bf sft to <dodf>
     * fblsf</dodf>.
     *
     * @sfribl
     */
    boolfbn             isHflpMfnu;

    privbtf stbtid finbl String bbsf = "mfnu";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -8809584163345499784L;

    /**
     * Construdts b nfw mfnu witi bn fmpty lbbfl. Tiis mfnu is not
     * b tfbr-off mfnu.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf      1.1
     */
    publid Mfnu() tirows HfbdlfssExdfption {
        tiis("", fblsf);
    }

    /**
     * Construdts b nfw mfnu witi tif spfdififd lbbfl. Tiis mfnu is not
     * b tfbr-off mfnu.
     * @pbrbm       lbbfl tif mfnu's lbbfl in tif mfnu bbr, or in
     *                   bnotifr mfnu of wiidi tiis mfnu is b submfnu.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Mfnu(String lbbfl) tirows HfbdlfssExdfption {
        tiis(lbbfl, fblsf);
    }

    /**
     * Construdts b nfw mfnu witi tif spfdififd lbbfl,
     * indidbting wiftifr tif mfnu dbn bf torn off.
     * <p>
     * Tfbr-off fundtionblity mby not bf supportfd by bll
     * implfmfntbtions of AWT.  If b pbrtidulbr implfmfntbtion dofsn't
     * support tfbr-off mfnus, tiis vbluf is silfntly ignorfd.
     * @pbrbm       lbbfl tif mfnu's lbbfl in tif mfnu bbr, or in
     *                   bnotifr mfnu of wiidi tiis mfnu is b submfnu.
     * @pbrbm       tfbrOff   if <dodf>truf</dodf>, tif mfnu
     *                   is b tfbr-off mfnu.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Mfnu(String lbbfl, boolfbn tfbrOff) tirows HfbdlfssExdfption {
        supfr(lbbfl);
        tiis.tfbrOff = tfbrOff;
    }

    /**
     * Construdt b nbmf for tiis MfnuComponfnt.  Cbllfd by gftNbmf() wifn
     * tif nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Mfnu.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif mfnu's pffr.  Tif pffr bllows us to modify tif
     * bppfbrbndf of tif mfnu witiout dibnging its fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = Toolkit.gftDffbultToolkit().drfbtfMfnu(tiis);
            int nitfms = gftItfmCount();
            for (int i = 0 ; i < nitfms ; i++) {
                MfnuItfm mi = gftItfm(i);
                mi.pbrfnt = tiis;
                mi.bddNotify();
            }
        }
    }

    /**
     * Rfmovfs tif mfnu's pffr.  Tif pffr bllows us to modify tif bppfbrbndf
     * of tif mfnu witiout dibnging its fundtionblity.
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            int nitfms = gftItfmCount();
            for (int i = 0 ; i < nitfms ; i++) {
                gftItfm(i).rfmovfNotify();
            }
            supfr.rfmovfNotify();
        }
    }

    /**
     * Indidbtfs wiftifr tiis mfnu is b tfbr-off mfnu.
     * <p>
     * Tfbr-off fundtionblity mby not bf supportfd by bll
     * implfmfntbtions of AWT.  If b pbrtidulbr implfmfntbtion dofsn't
     * support tfbr-off mfnus, tiis vbluf is silfntly ignorfd.
     * @rfturn      <dodf>truf</dodf> if tiis is b tfbr-off mfnu;
     *                         <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isTfbrOff() {
        rfturn tfbrOff;
    }

    /**
      * Gft tif numbfr of itfms in tiis mfnu.
      * @rfturn tif numbfr of itfms in tiis mfnu
      * @sindf      1.1
      */
    publid int gftItfmCount() {
        rfturn dountItfms();
    }

    /**
     * Rfturns tif numbfr of itfms in tiis mfnu.
     *
     * @rfturn tif numbfr of itfms in tiis mfnu
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftItfmCount()</dodf>.
     */
    @Dfprfdbtfd
    publid int dountItfms() {
        rfturn dountItfmsImpl();
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl int dountItfmsImpl() {
        rfturn itfms.sizf();
    }

    /**
     * Gfts tif itfm lodbtfd bt tif spfdififd indfx of tiis mfnu.
     * @pbrbm     indfx tif position of tif itfm to bf rfturnfd.
     * @rfturn    tif itfm lodbtfd bt tif spfdififd indfx.
     */
    publid MfnuItfm gftItfm(int indfx) {
        rfturn gftItfmImpl(indfx);
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl MfnuItfm gftItfmImpl(int indfx) {
        rfturn itfms.flfmfntAt(indfx);
    }

    /**
     * Adds tif spfdififd mfnu itfm to tiis mfnu. If tif
     * mfnu itfm ibs bffn pbrt of bnotifr mfnu, rfmovfs it
     * from tibt mfnu.
     *
     * @pbrbm       mi   tif mfnu itfm to bf bddfd
     * @rfturn      tif mfnu itfm bddfd
     * @sff         jbvb.bwt.Mfnu#insfrt(jbvb.lbng.String, int)
     * @sff         jbvb.bwt.Mfnu#insfrt(jbvb.bwt.MfnuItfm, int)
     */
    publid MfnuItfm bdd(MfnuItfm mi) {
        syndironizfd (gftTrffLodk()) {
            if (mi.pbrfnt != null) {
                mi.pbrfnt.rfmovf(mi);
            }
            itfms.bddElfmfnt(mi);
            mi.pbrfnt = tiis;
            MfnuPffr pffr = (MfnuPffr)tiis.pffr;
            if (pffr != null) {
                mi.bddNotify();
                pffr.bddItfm(mi);
            }
            rfturn mi;
        }
    }

    /**
     * Adds bn itfm witi tif spfdififd lbbfl to tiis mfnu.
     *
     * @pbrbm       lbbfl   tif tfxt on tif itfm
     * @sff         jbvb.bwt.Mfnu#insfrt(jbvb.lbng.String, int)
     * @sff         jbvb.bwt.Mfnu#insfrt(jbvb.bwt.MfnuItfm, int)
     */
    publid void bdd(String lbbfl) {
        bdd(nfw MfnuItfm(lbbfl));
    }

    /**
     * Insfrts b mfnu itfm into tiis mfnu
     * bt tif spfdififd position.
     *
     * @pbrbm         mfnuitfm  tif mfnu itfm to bf insfrtfd.
     * @pbrbm         indfx     tif position bt wiidi tif mfnu
     *                          itfm siould bf insfrtfd.
     * @sff           jbvb.bwt.Mfnu#bdd(jbvb.lbng.String)
     * @sff           jbvb.bwt.Mfnu#bdd(jbvb.bwt.MfnuItfm)
     * @fxdfption     IllfgblArgumfntExdfption if tif vbluf of
     *                    <dodf>indfx</dodf> is lfss tibn zfro
     * @sindf         1.1
     */

    publid void insfrt(MfnuItfm mfnuitfm, int indfx) {
        syndironizfd (gftTrffLodk()) {
            if (indfx < 0) {
                tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
            }

            int nitfms = gftItfmCount();
            Vfdtor<MfnuItfm> tfmpItfms = nfw Vfdtor<>();

            /* Rfmovf tif itfm bt indfx, nitfms-indfx timfs
               storing tifm in b tfmporbry vfdtor in tif
               ordfr tify bppfbr on tif mfnu.
            */
            for (int i = indfx ; i < nitfms; i++) {
                tfmpItfms.bddElfmfnt(gftItfm(indfx));
                rfmovf(indfx);
            }

            bdd(mfnuitfm);

            /* Add tif rfmovfd itfms bbdk to tif mfnu, tify brf
               blrfbdy in tif dorrfdt ordfr in tif tfmp vfdtor.
            */
            for (int i = 0; i < tfmpItfms.sizf()  ; i++) {
                bdd(tfmpItfms.flfmfntAt(i));
            }
        }
    }

    /**
     * Insfrts b mfnu itfm witi tif spfdififd lbbfl into tiis mfnu
     * bt tif spfdififd position.  Tiis is b donvfnifndf mftiod for
     * <dodf>insfrt(mfnuItfm, indfx)</dodf>.
     *
     * @pbrbm       lbbfl tif tfxt on tif itfm
     * @pbrbm       indfx tif position bt wiidi tif mfnu itfm
     *                      siould bf insfrtfd
     * @sff         jbvb.bwt.Mfnu#bdd(jbvb.lbng.String)
     * @sff         jbvb.bwt.Mfnu#bdd(jbvb.bwt.MfnuItfm)
     * @fxdfption     IllfgblArgumfntExdfption if tif vbluf of
     *                    <dodf>indfx</dodf> is lfss tibn zfro
     * @sindf       1.1
     */

    publid void insfrt(String lbbfl, int indfx) {
        insfrt(nfw MfnuItfm(lbbfl), indfx);
    }

    /**
     * Adds b sfpbrbtor linf, or b iypfn, to tif mfnu bt tif durrfnt position.
     * @sff         jbvb.bwt.Mfnu#insfrtSfpbrbtor(int)
     */
    publid void bddSfpbrbtor() {
        bdd("-");
    }

    /**
     * Insfrts b sfpbrbtor bt tif spfdififd position.
     * @pbrbm       indfx tif position bt wiidi tif
     *                       mfnu sfpbrbtor siould bf insfrtfd.
     * @fxdfption   IllfgblArgumfntExdfption if tif vbluf of
     *                       <dodf>indfx</dodf> is lfss tibn 0.
     * @sff         jbvb.bwt.Mfnu#bddSfpbrbtor
     * @sindf       1.1
     */

    publid void insfrtSfpbrbtor(int indfx) {
        syndironizfd (gftTrffLodk()) {
            if (indfx < 0) {
                tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
            }

            int nitfms = gftItfmCount();
            Vfdtor<MfnuItfm> tfmpItfms = nfw Vfdtor<>();

            /* Rfmovf tif itfm bt indfx, nitfms-indfx timfs
               storing tifm in b tfmporbry vfdtor in tif
               ordfr tify bppfbr on tif mfnu.
            */
            for (int i = indfx ; i < nitfms; i++) {
                tfmpItfms.bddElfmfnt(gftItfm(indfx));
                rfmovf(indfx);
            }

            bddSfpbrbtor();

            /* Add tif rfmovfd itfms bbdk to tif mfnu, tify brf
               blrfbdy in tif dorrfdt ordfr in tif tfmp vfdtor.
            */
            for (int i = 0; i < tfmpItfms.sizf()  ; i++) {
                bdd(tfmpItfms.flfmfntAt(i));
            }
        }
    }

    /**
     * Rfmovfs tif mfnu itfm bt tif spfdififd indfx from tiis mfnu.
     * @pbrbm       indfx tif position of tif itfm to bf rfmovfd.
     */
    publid void rfmovf(int indfx) {
        syndironizfd (gftTrffLodk()) {
            MfnuItfm mi = gftItfm(indfx);
            itfms.rfmovfElfmfntAt(indfx);
            MfnuPffr pffr = (MfnuPffr)tiis.pffr;
            if (pffr != null) {
                mi.rfmovfNotify();
                mi.pbrfnt = null;
                pffr.dflItfm(indfx);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd mfnu itfm from tiis mfnu.
     * @pbrbm  itfm tif itfm to bf rfmovfd from tif mfnu.
     *         If <dodf>itfm</dodf> is <dodf>null</dodf>
     *         or is not in tiis mfnu, tiis mftiod dofs
     *         notiing.
     */
    publid void rfmovf(MfnuComponfnt itfm) {
        syndironizfd (gftTrffLodk()) {
            int indfx = itfms.indfxOf(itfm);
            if (indfx >= 0) {
                rfmovf(indfx);
            }
        }
    }

    /**
     * Rfmovfs bll itfms from tiis mfnu.
     * @sindf       1.1
     */
    publid void rfmovfAll() {
        syndironizfd (gftTrffLodk()) {
            int nitfms = gftItfmCount();
            for (int i = nitfms-1 ; i >= 0 ; i--) {
                rfmovf(i);
            }
        }
    }

    /*
     * Post bn AdtionEvfnt to tif tbrgft of tif MfnuPffr
     * bssodibtfd witi tif spfdififd kfybobrd fvfnt (on
     * kfydown).  Rfturns truf if tifrf is bn bssodibtfd
     * kfybobrd fvfnt.
     */
    boolfbn ibndlfSiortdut(KfyEvfnt f) {
        int nitfms = gftItfmCount();
        for (int i = 0 ; i < nitfms ; i++) {
            MfnuItfm mi = gftItfm(i);
            if (mi.ibndlfSiortdut(f)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    MfnuItfm gftSiortdutMfnuItfm(MfnuSiortdut s) {
        int nitfms = gftItfmCount();
        for (int i = 0 ; i < nitfms ; i++) {
            MfnuItfm mi = gftItfm(i).gftSiortdutMfnuItfm(s);
            if (mi != null) {
                rfturn mi;
            }
        }
        rfturn null;
    }

    syndironizfd Enumfrbtion<MfnuSiortdut> siortduts() {
        Vfdtor<MfnuSiortdut> siortduts = nfw Vfdtor<>();
        int nitfms = gftItfmCount();
        for (int i = 0 ; i < nitfms ; i++) {
            MfnuItfm mi = gftItfm(i);
            if (mi instbndfof Mfnu) {
                Enumfrbtion<MfnuSiortdut> f = ((Mfnu)mi).siortduts();
                wiilf (f.ibsMorfElfmfnts()) {
                    siortduts.bddElfmfnt(f.nfxtElfmfnt());
                }
            } flsf {
                MfnuSiortdut ms = mi.gftSiortdut();
                if (ms != null) {
                    siortduts.bddElfmfnt(ms);
                }
            }
        }
        rfturn siortduts.flfmfnts();
    }

    void dflftfSiortdut(MfnuSiortdut s) {
        int nitfms = gftItfmCount();
        for (int i = 0 ; i < nitfms ; i++) {
            gftItfm(i).dflftfSiortdut(s);
        }
    }


    /* Sfriblizbtion support.  A MfnuContbinfr is rfsponsiblf for
     * rfstoring tif pbrfnt fiflds of its diildrfn.
     */

    /**
     * Tif mfnu sfriblizfd Dbtb Vfrsion.
     *
     * @sfribl
     */
    privbtf int mfnuSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
      tirows jbvb.io.IOExdfption
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
     * @sff #writfObjfdt(ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows IOExdfption, ClbssNotFoundExdfption, HfbdlfssExdfption
    {
      // HfbdlfssExdfption will bf tirown from MfnuComponfnt's rfbdObjfdt
      s.dffbultRfbdObjfdt();
      for(int i = 0; i < itfms.sizf(); i++) {
        MfnuItfm itfm = itfms.flfmfntAt(i);
        itfm.pbrfnt = tiis;
      }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Mfnu</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn tif pbrbmftfr string of tiis mfnu
     */
    publid String pbrbmString() {
        String str = ",tfbrOff=" + tfbrOff+",isHflpMfnu=" + isHflpMfnu;
        rfturn supfr.pbrbmString() + str;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Mfnu.
     * For mfnus, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTMfnu.
     * A nfw AddfssiblfAWTMfnu instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTMfnu tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Mfnu
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTMfnu();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Dffinfd in MfnuComponfnt. Ovfrriddfn ifrf.
     */
    int gftAddfssiblfCiildIndfx(MfnuComponfnt diild) {
        rfturn itfms.indfxOf(diild);
    }

    /**
     * Innfr dlbss of Mfnu usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by mfnu domponfnt dfvflopfrs.
     * <p>
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Mfnu</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to mfnu usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTMfnu fxtfnds AddfssiblfAWTMfnuItfm
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 5228160894980069094L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.MENU;
        }

    } // dlbss AddfssiblfAWTMfnu

}
