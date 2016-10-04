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

import jbvb.bwt.pffr.TfxtFifldPffr;
import jbvb.bwt.fvfnt.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvbx.bddfssibility.*;


/**
 * A <dodf>TfxtFifld</dodf> objfdt is b tfxt domponfnt
 * tibt bllows for tif fditing of b singlf linf of tfxt.
 * <p>
 * For fxbmplf, tif following imbgf dfpidts b frbmf witi four
 * tfxt fiflds of vbrying widtis. Two of tifsf tfxt fiflds
 * displby tif prfdffinfd tfxt <dodf>"Hfllo"</dodf>.
 * <p>
 * <img srd="dod-filfs/TfxtFifld-1.gif" blt="Tif prfdfding tfxt dfsdribfs tiis imbgf."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Hfrf is tif dodf tibt produdfs tifsf four tfxt fiflds:
 *
 * <ir><blodkquotf><prf>
 * TfxtFifld tf1, tf2, tf3, tf4;
 * // b blbnk tfxt fifld
 * tf1 = nfw TfxtFifld();
 * // blbnk fifld of 20 dolumns
 * tf2 = nfw TfxtFifld("", 20);
 * // prfdffinfd tfxt displbyfd
 * tf3 = nfw TfxtFifld("Hfllo!");
 * // prfdffinfd tfxt in 30 dolumns
 * tf4 = nfw TfxtFifld("Hfllo", 30);
 * </prf></blodkquotf><ir>
 * <p>
 * Evfry timf tif usfr typfs b kfy in tif tfxt fifld, onf or
 * morf kfy fvfnts brf sfnt to tif tfxt fifld.  A <dodf>KfyEvfnt</dodf>
 * mby bf onf of tirff typfs: kfyPrfssfd, kfyRflfbsfd, or kfyTypfd.
 * Tif propfrtifs of b kfy fvfnt indidbtf wiidi of tifsf typfs
 * it is, bs wfll bs bdditionbl informbtion bbout tif fvfnt,
 * sudi bs wibt modififrs brf bpplifd to tif kfy fvfnt bnd tif
 * timf bt wiidi tif fvfnt oddurrfd.
 * <p>
 * Tif kfy fvfnt is pbssfd to fvfry <dodf>KfyListfnfr</dodf>
 * or <dodf>KfyAdbptfr</dodf> objfdt wiidi rfgistfrfd to rfdfivf sudi
 * fvfnts using tif domponfnt's <dodf>bddKfyListfnfr</dodf> mftiod.
 * (<dodf>KfyAdbptfr</dodf> objfdts implfmfnt tif
 * <dodf>KfyListfnfr</dodf> intfrfbdf.)
 * <p>
 * It is blso possiblf to firf bn <dodf>AdtionEvfnt</dodf>.
 * If bdtion fvfnts brf fnbblfd for tif tfxt fifld, tify mby
 * bf firfd by prfssing tif <dodf>Rfturn</dodf> kfy.
 * <p>
 * Tif <dodf>TfxtFifld</dodf> dlbss's <dodf>prodfssEvfnt</dodf>
 * mftiod fxbminfs tif bdtion fvfnt bnd pbssfs it blong to
 * <dodf>prodfssAdtionEvfnt</dodf>. Tif lbttfr mftiod rfdirfdts tif
 * fvfnt to bny <dodf>AdtionListfnfr</dodf> objfdts tibt ibvf
 * rfgistfrfd to rfdfivf bdtion fvfnts gfnfrbtfd by tiis
 * tfxt fifld.
 *
 * @butior      Sbmi Sibio
 * @sff         jbvb.bwt.fvfnt.KfyEvfnt
 * @sff         jbvb.bwt.fvfnt.KfyAdbptfr
 * @sff         jbvb.bwt.fvfnt.KfyListfnfr
 * @sff         jbvb.bwt.fvfnt.AdtionEvfnt
 * @sff         jbvb.bwt.Componfnt#bddKfyListfnfr
 * @sff         jbvb.bwt.TfxtFifld#prodfssEvfnt
 * @sff         jbvb.bwt.TfxtFifld#prodfssAdtionEvfnt
 * @sff         jbvb.bwt.TfxtFifld#bddAdtionListfnfr
 * @sindf       1.0
 */
publid dlbss TfxtFifld fxtfnds TfxtComponfnt {

    /**
     * Tif numbfr of dolumns in tif tfxt fifld.
     * A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.
     * Gubrbntffd to bf non-nfgbtivf.
     *
     * @sfribl
     * @sff #sftColumns(int)
     * @sff #gftColumns()
     */
    int dolumns;

    /**
     * Tif fdio dibrbdtfr, wiidi is usfd wifn
     * tif usfr wisifs to disguisf tif dibrbdtfrs
     * typfd into tif tfxt fifld.
     * Tif disguisfs brf rfmovfd if fdioCibr = <dodf>0</dodf>.
     *
     * @sfribl
     * @sff #gftEdioCibr()
     * @sff #sftEdioCibr(dibr)
     * @sff #fdioCibrIsSft()
     */
    dibr fdioCibr;

    trbnsifnt AdtionListfnfr bdtionListfnfr;

    privbtf stbtid finbl String bbsf = "tfxtfifld";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -2966288784432217853L;

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Construdts b nfw tfxt fifld.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid TfxtFifld() tirows HfbdlfssExdfption {
        tiis("", 0);
    }

    /**
     * Construdts b nfw tfxt fifld initiblizfd witi tif spfdififd tfxt.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd. If
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid TfxtFifld(String tfxt) tirows HfbdlfssExdfption {
        tiis(tfxt, (tfxt != null) ? tfxt.lfngti() : 0);
    }

    /**
     * Construdts b nfw fmpty tfxt fifld witi tif spfdififd numbfr
     * of dolumns.  A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.
     * @pbrbm      dolumns     tif numbfr of dolumns.  If
     *             <dodf>dolumns</dodf> is lfss tibn <dodf>0</dodf>,
     *             <dodf>dolumns</dodf> is sft to <dodf>0</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid TfxtFifld(int dolumns) tirows HfbdlfssExdfption {
        tiis("", dolumns);
    }

    /**
     * Construdts b nfw tfxt fifld initiblizfd witi tif spfdififd tfxt
     * to bf displbyfd, bnd widf fnougi to iold tif spfdififd
     * numbfr of dolumns. A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd. If
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd.
     * @pbrbm      dolumns     tif numbfr of dolumns.  If
     *             <dodf>dolumns</dodf> is lfss tibn <dodf>0</dodf>,
     *             <dodf>dolumns</dodf> is sft to <dodf>0</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid TfxtFifld(String tfxt, int dolumns) tirows HfbdlfssExdfption {
        supfr(tfxt);
        tiis.dolumns = (dolumns >= 0) ? dolumns : 0;
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (TfxtFifld.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif TfxtFifld's pffr.  Tif pffr bllows us to modify tif
     * bppfbrbndf of tif TfxtFifld witiout dibnging its fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfTfxtFifld(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Gfts tif dibrbdtfr tibt is to bf usfd for fdioing.
     * <p>
     * An fdio dibrbdtfr is usfful for tfxt fiflds wifrf
     * usfr input siould not bf fdiofd to tif sdrffn, bs in
     * tif dbsf of b tfxt fifld for fntfring b pbssword.
     * If <dodf>fdioCibr</dodf> = <dodf>0</dodf>, usfr
     * input is fdiofd to tif sdrffn undibngfd.
     * <p>
     * A Jbvb plbtform implfmfntbtion mby support only b limitfd,
     * non-fmpty sft of fdio dibrbdtfrs. Tiis fundtion rfturns tif
     * fdio dibrbdtfr originblly rfqufstfd vib sftEdioCibr(). Tif fdio
     * dibrbdtfr bdtublly usfd by tif TfxtFifld implfmfntbtion migit bf
     * difffrfnt.
     * @rfturn      tif fdio dibrbdtfr for tiis tfxt fifld.
     * @sff         jbvb.bwt.TfxtFifld#fdioCibrIsSft
     * @sff         jbvb.bwt.TfxtFifld#sftEdioCibr
     */
    publid dibr gftEdioCibr() {
        rfturn fdioCibr;
    }

    /**
     * Sfts tif fdio dibrbdtfr for tiis tfxt fifld.
     * <p>
     * An fdio dibrbdtfr is usfful for tfxt fiflds wifrf
     * usfr input siould not bf fdiofd to tif sdrffn, bs in
     * tif dbsf of b tfxt fifld for fntfring b pbssword.
     * Sftting <dodf>fdioCibr</dodf> = <dodf>0</dodf> bllows
     * usfr input to bf fdiofd to tif sdrffn bgbin.
     * <p>
     * A Jbvb plbtform implfmfntbtion mby support only b limitfd,
     * non-fmpty sft of fdio dibrbdtfrs. Attfmpts to sft bn
     * unsupportfd fdio dibrbdtfr will dbusf tif dffbult fdio
     * dibrbdtfr to bf usfd instfbd. Subsfqufnt dblls to gftEdioCibr()
     * will rfturn tif fdio dibrbdtfr originblly rfqufstfd. Tiis migit
     * or migit not bf idfntidbl to tif fdio dibrbdtfr bdtublly
     * usfd by tif TfxtFifld implfmfntbtion.
     * @pbrbm       d   tif fdio dibrbdtfr for tiis tfxt fifld.
     * @sff         jbvb.bwt.TfxtFifld#fdioCibrIsSft
     * @sff         jbvb.bwt.TfxtFifld#gftEdioCibr
     * @sindf       1.1
     */
    publid void sftEdioCibr(dibr d) {
        sftEdioCibrbdtfr(d);
    }

    /**
     * Sfts tif dibrbdtfr to bf fdiofd wifn protfdtfd input is displbyfd.
     *
     *  @pbrbm  d tif fdio dibrbdtfr for tiis tfxt fifld
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftEdioCibr(dibr)</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void sftEdioCibrbdtfr(dibr d) {
        if (fdioCibr != d) {
            fdioCibr = d;
            TfxtFifldPffr pffr = (TfxtFifldPffr)tiis.pffr;
            if (pffr != null) {
                pffr.sftEdioCibr(d);
            }
        }
    }

    /**
     * Sfts tif tfxt tibt is prfsfntfd by tiis
     * tfxt domponfnt to bf tif spfdififd tfxt.
     * @pbrbm       t   tif nfw tfxt.
     * @sff         jbvb.bwt.TfxtComponfnt#gftTfxt
     */
    publid void sftTfxt(String t) {
        supfr.sftTfxt(t);

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        invblidbtfIfVblid();
    }

    /**
     * Indidbtfs wiftifr or not tiis tfxt fifld ibs b
     * dibrbdtfr sft for fdioing.
     * <p>
     * An fdio dibrbdtfr is usfful for tfxt fiflds wifrf
     * usfr input siould not bf fdiofd to tif sdrffn, bs in
     * tif dbsf of b tfxt fifld for fntfring b pbssword.
     * @rfturn     <dodf>truf</dodf> if tiis tfxt fifld ibs
     *                 b dibrbdtfr sft for fdioing;
     *                 <dodf>fblsf</dodf> otifrwisf.
     * @sff        jbvb.bwt.TfxtFifld#sftEdioCibr
     * @sff        jbvb.bwt.TfxtFifld#gftEdioCibr
     */
    publid boolfbn fdioCibrIsSft() {
        rfturn fdioCibr != 0;
    }

    /**
     * Gfts tif numbfr of dolumns in tiis tfxt fifld. A dolumn is bn
     * bpproximbtf bvfrbgf dibrbdtfr widti tibt is plbtform-dfpfndfnt.
     * @rfturn     tif numbfr of dolumns.
     * @sff        jbvb.bwt.TfxtFifld#sftColumns
     * @sindf      1.1
     */
    publid int gftColumns() {
        rfturn dolumns;
    }

    /**
     * Sfts tif numbfr of dolumns in tiis tfxt fifld. A dolumn is bn
     * bpproximbtf bvfrbgf dibrbdtfr widti tibt is plbtform-dfpfndfnt.
     * @pbrbm      dolumns   tif numbfr of dolumns.
     * @sff        jbvb.bwt.TfxtFifld#gftColumns
     * @fxdfption  IllfgblArgumfntExdfption   if tif vbluf
     *                 supplifd for <dodf>dolumns</dodf>
     *                 is lfss tibn <dodf>0</dodf>.
     * @sindf      1.1
     */
    publid void sftColumns(int dolumns) {
        int oldVbl;
        syndironizfd (tiis) {
            oldVbl = tiis.dolumns;
            if (dolumns < 0) {
                tirow nfw IllfgblArgumfntExdfption("dolumns lfss tibn zfro.");
            }
            if (dolumns != oldVbl) {
                tiis.dolumns = dolumns;
            }
        }

        if (dolumns != oldVbl) {
            invblidbtf();
        }
    }

    /**
     * Gfts tif prfffrrfd sizf of tiis tfxt fifld
     * witi tif spfdififd numbfr of dolumns.
     * @pbrbm     dolumns tif numbfr of dolumns
     *                 in tiis tfxt fifld.
     * @rfturn    tif prfffrrfd dimfnsions for
     *                 displbying tiis tfxt fifld.
     * @sindf     1.1
     */
    publid Dimfnsion gftPrfffrrfdSizf(int dolumns) {
        rfturn prfffrrfdSizf(dolumns);
    }

    /**
     * Rfturns tif prfffrrfd sizf for tiis tfxt fifld
     * witi tif spfdififd numbfr of dolumns.
     *
     * @pbrbm  dolumns tif numbfr of dolumns
     * @rfturn tif prfffrrfd sizf for tif tfxt fifld
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf(int)</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf(int dolumns) {
        syndironizfd (gftTrffLodk()) {
            TfxtFifldPffr pffr = (TfxtFifldPffr)tiis.pffr;
            rfturn (pffr != null) ?
                       pffr.gftPrfffrrfdSizf(dolumns) :
                       supfr.prfffrrfdSizf();
        }
    }

    /**
     * Gfts tif prfffrrfd sizf of tiis tfxt fifld.
     * @rfturn     tif prfffrrfd dimfnsions for
     *                         displbying tiis tfxt fifld.
     * @sindf      1.1
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn prfffrrfdSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf() {
        syndironizfd (gftTrffLodk()) {
            rfturn (dolumns > 0) ?
                       prfffrrfdSizf(dolumns) :
                       supfr.prfffrrfdSizf();
        }
    }

    /**
     * Gfts tif minimum dimfnsions for b tfxt fifld witi
     * tif spfdififd numbfr of dolumns.
     * @pbrbm  dolumns tif numbfr of dolumns in
     *         tiis tfxt fifld.
     * @rfturn tif minimum sizf for tiis tfxt fifld
     * @sindf    1.1
     */
    publid Dimfnsion gftMinimumSizf(int dolumns) {
        rfturn minimumSizf(dolumns);
    }

    /**
     * Rfturns tif minimum dimfnsions for b tfxt fifld witi
     * tif spfdififd numbfr of dolumns.
     *
     * @pbrbm  dolumns tif numbfr of dolumns
     * @rfturn tif minimum sizf for tiis tfxt fifld
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf(int)</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf(int dolumns) {
        syndironizfd (gftTrffLodk()) {
            TfxtFifldPffr pffr = (TfxtFifldPffr)tiis.pffr;
            rfturn (pffr != null) ?
                       pffr.gftMinimumSizf(dolumns) :
                       supfr.minimumSizf();
        }
    }

    /**
     * Gfts tif minimum dimfnsions for tiis tfxt fifld.
     * @rfturn     tif minimum dimfnsions for
     *                  displbying tiis tfxt fifld.
     * @sindf      1.1
     */
    publid Dimfnsion gftMinimumSizf() {
        rfturn minimumSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf() {
        syndironizfd (gftTrffLodk()) {
            rfturn (dolumns > 0) ?
                       minimumSizf(dolumns) :
                       supfr.minimumSizf();
        }
    }

    /**
     * Adds tif spfdififd bdtion listfnfr to rfdfivf
     * bdtion fvfnts from tiis tfxt fifld.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm      l tif bdtion listfnfr.
     * @sff        #rfmovfAdtionListfnfr
     * @sff        #gftAdtionListfnfrs
     * @sff        jbvb.bwt.fvfnt.AdtionListfnfr
     * @sindf      1.1
     */
    publid syndironizfd void bddAdtionListfnfr(AdtionListfnfr l) {
        if (l == null) {
            rfturn;
        }
        bdtionListfnfr = AWTEvfntMultidbstfr.bdd(bdtionListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd bdtion listfnfr so tibt it no longfr
     * rfdfivfs bdtion fvfnts from tiis tfxt fifld.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm           l tif bdtion listfnfr.
     * @sff             #bddAdtionListfnfr
     * @sff             #gftAdtionListfnfrs
     * @sff             jbvb.bwt.fvfnt.AdtionListfnfr
     * @sindf           1.1
     */
    publid syndironizfd void rfmovfAdtionListfnfr(AdtionListfnfr l) {
        if (l == null) {
            rfturn;
        }
        bdtionListfnfr = AWTEvfntMultidbstfr.rfmovf(bdtionListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif bdtion listfnfrs
     * rfgistfrfd on tiis tfxtfifld.
     *
     * @rfturn bll of tiis tfxtfifld's <dodf>AdtionListfnfr</dodf>s
     *         or bn fmpty brrby if no bdtion
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddAdtionListfnfr
     * @sff #rfmovfAdtionListfnfr
     * @sff jbvb.bwt.fvfnt.AdtionListfnfr
     * @sindf 1.4
     */
    publid syndironizfd AdtionListfnfr[] gftAdtionListfnfrs() {
        rfturn gftListfnfrs(AdtionListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>TfxtFifld</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>TfxtFifld</dodf> <dodf>t</dodf>
     * for its bdtion listfnfrs witi tif following dodf:
     *
     * <prf>AdtionListfnfr[] bls = (AdtionListfnfr[])(t.gftListfnfrs(AdtionListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis tfxtfifld,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftAdtionListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == AdtionListfnfr.dlbss) {
            l = bdtionListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        if (f.id == AdtionEvfnt.ACTION_PERFORMED) {
            if ((fvfntMbsk & AWTEvfnt.ACTION_EVENT_MASK) != 0 ||
                bdtionListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis tfxt fifld. If tif fvfnt
     * is bn instbndf of <dodf>AdtionEvfnt</dodf>,
     * it invokfs tif <dodf>prodfssAdtionEvfnt</dodf>
     * mftiod. Otifrwisf, it invokfs <dodf>prodfssEvfnt</dodf>
     * on tif supfrdlbss.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm      f tif fvfnt
     * @sff        jbvb.bwt.fvfnt.AdtionEvfnt
     * @sff        jbvb.bwt.TfxtFifld#prodfssAdtionEvfnt
     * @sindf      1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof AdtionEvfnt) {
            prodfssAdtionEvfnt((AdtionEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs bdtion fvfnts oddurring on tiis tfxt fifld by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>AdtionListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss bdtion fvfnts brf
     * fnbblfd for tiis domponfnt. Adtion fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>AdtionListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddAdtionListfnfr</dodf>.
     * <li>Adtion fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif bdtion fvfnt
     * @sff         jbvb.bwt.fvfnt.AdtionListfnfr
     * @sff         jbvb.bwt.TfxtFifld#bddAdtionListfnfr
     * @sff         jbvb.bwt.Componfnt#fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssAdtionEvfnt(AdtionEvfnt f) {
        AdtionListfnfr listfnfr = bdtionListfnfr;
        if (listfnfr != null) {
            listfnfr.bdtionPfrformfd(f);
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>TfxtFifld</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn      tif pbrbmftfr string of tiis tfxt fifld
     */
    protfdtfd String pbrbmString() {
        String str = supfr.pbrbmString();
        if (fdioCibr != 0) {
            str += ",fdio=" + fdioCibr;
        }
        rfturn str;
    }


    /*
     * Sfriblizbtion support.
     */
    /**
     * Tif tfxtFifld Sfriblizfd Dbtb Vfrsion.
     *
     * @sfribl
     */
    privbtf int tfxtFifldSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf AdtionListfnfr(s) bs optionbl dbtb.
     * Tif non-sfriblizbblf AdtionListfnfr(s) brf dftfdtfd bnd
     * no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @sfriblDbtb Null tfrminbtfd sfqufndf of zfro or morf pbirs.
     *             A pbir donsists of b String bnd Objfdt.
     *             Tif String indidbtfs tif typf of objfdt bnd
     *             is onf of tif following :
     *             AdtionListfnfrK indidbting bnd AdtionListfnfr objfdt.
     *
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff jbvb.bwt.Componfnt#bdtionListfnfrK
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
      tirows IOExdfption
    {
        s.dffbultWritfObjfdt();

        AWTEvfntMultidbstfr.sbvf(s, bdtionListfnfrK, bdtionListfnfr);
        s.writfObjfdt(null);
    }

    /**
     * Rfbd tif ObjfdtInputStrfbm bnd if it isn't null,
     * bdd b listfnfr to rfdfivf bdtion fvfnts firfd by tif
     * TfxtFifld.  Unrfdognizfd kfys or vblufs will bf
     * ignorfd.
     *
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     * <dodf>truf</dodf>
     * @sff #rfmovfAdtionListfnfr(AdtionListfnfr)
     * @sff #bddAdtionListfnfr(AdtionListfnfr)
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
        // HfbdlfssExdfption will bf tirown by TfxtComponfnt's rfbdObjfdt
        s.dffbultRfbdObjfdt();

        // Mbkf surf tif stbtf wf just rfbd in for dolumns ibs lfgbl vblufs
        if (dolumns < 0) {
            dolumns = 0;
        }

        // Rfbd in listfnfrs, if bny
        Objfdt kfyOrNull;
        wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (bdtionListfnfrK == kfy) {
                bddAdtionListfnfr((AdtionListfnfr)(s.rfbdObjfdt()));
            } flsf {
                // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();
            }
        }
    }


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis TfxtFifld.
     * For tfxt fiflds, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTTfxtFifld.
     * A nfw AddfssiblfAWTTfxtFifld instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTTfxtFifld tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis TfxtFifld
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTTfxtFifld();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>TfxtFifld</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tfxt fifld usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTTfxtFifld fxtfnds AddfssiblfAWTTfxtComponfnt
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 6219164359235943158L;

        /**
         * Gfts tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dfsdribing tif stbtfs
         * of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            stbtfs.bdd(AddfssiblfStbtf.SINGLE_LINE);
            rfturn stbtfs;
        }
    }

}
