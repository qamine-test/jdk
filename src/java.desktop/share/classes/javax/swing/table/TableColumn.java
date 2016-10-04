/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tbblf;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.SwingPropfrtyCibngfSupport;
import jbvb.lbng.Intfgfr;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.io.Sfriblizbblf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

/**
 *  A <dodf>TbblfColumn</dodf> rfprfsfnts bll tif bttributfs of b dolumn in b
 *  <dodf>JTbblf</dodf>, sudi bs widti, rfsizbbility, minimum bnd mbximum widti.
 *  In bddition, tif <dodf>TbblfColumn</dodf> providfs slots for b rfndfrfr bnd
 *  bn fditor tibt dbn bf usfd to displby bnd fdit tif vblufs in tiis dolumn.
 *  <p>
 *  It is blso possiblf to spfdify rfndfrfrs bnd fditors on b pfr typf bbsis
 *  rbtifr tibn b pfr dolumn bbsis - sff tif
 *  <dodf>sftDffbultRfndfrfr</dodf> mftiod in tif <dodf>JTbblf</dodf> dlbss.
 *  Tiis dffbult mfdibnism is only usfd wifn tif rfndfrfr (or
 *  fditor) in tif <dodf>TbblfColumn</dodf> is <dodf>null</dodf>.
 * <p>
 *  Tif <dodf>TbblfColumn</dodf> storfs tif link bftwffn tif dolumns in tif
 *  <dodf>JTbblf</dodf> bnd tif dolumns in tif <dodf>TbblfModfl</dodf>.
 *  Tif <dodf>modflIndfx</dodf> is tif dolumn in tif
 *  <dodf>TbblfModfl</dodf>, wiidi will bf qufrifd for tif dbtb vblufs for tif
 *  dflls in tiis dolumn. As tif dolumn movfs bround in tif vifw tiis
 *  <dodf>modflIndfx</dodf> dofs not dibngf.
 *  <p>
 * <b>Notf:</b> Somf implfmfntbtions mby bssumf tibt bll
 *    <dodf>TbblfColumnModfl</dodf>s brf uniquf, tifrfforf wf would
 *    rfdommfnd tibt tif sbmf <dodf>TbblfColumn</dodf> instbndf
 *    not bf bddfd morf tibn ondf to b <dodf>TbblfColumnModfl</dodf>.
 *    To siow <dodf>TbblfColumn</dodf>s witi tif sbmf dolumn of
 *    dbtb from tif modfl, drfbtf b nfw instbndf witi tif sbmf
 *    <dodf>modflIndfx</dodf>.
 *  <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Albn Ciung
 * @butior Piilip Milnf
 * @sff jbvbx.swing.tbblf.TbblfColumnModfl
 *
 * @sff jbvbx.swing.tbblf.DffbultTbblfColumnModfl
 * @sff jbvbx.swing.tbblf.JTbblfHfbdfr#gftDffbultRfndfrfr()
 * @sff JTbblf#gftDffbultRfndfrfr(Clbss)
 * @sff JTbblf#gftDffbultEditor(Clbss)
 * @sff JTbblf#gftCfllRfndfrfr(int, int)
 * @sff JTbblf#gftCfllEditor(int, int)
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss TbblfColumn fxtfnds Objfdt implfmfnts Sfriblizbblf {

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.3.  Plfbsf usf string litfrbls to idfntify
     * propfrtifs.
     */
    /*
     * Wbrning: Tif vbluf of tiis donstbnt, "dolumWidti" is wrong bs tif
     * nbmf of tif propfrty is "dolumnWidti".
     */
    publid finbl stbtid String COLUMN_WIDTH_PROPERTY = "dolumWidti";

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.3.  Plfbsf usf string litfrbls to idfntify
     * propfrtifs.
     */
    publid finbl stbtid String HEADER_VALUE_PROPERTY = "ifbdfrVbluf";

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.3.  Plfbsf usf string litfrbls to idfntify
     * propfrtifs.
     */
    publid finbl stbtid String HEADER_RENDERER_PROPERTY = "ifbdfrRfndfrfr";

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.3.  Plfbsf usf string litfrbls to idfntify
     * propfrtifs.
     */
    publid finbl stbtid String CELL_RENDERER_PROPERTY = "dfllRfndfrfr";

//
//  Instbndf Vbribblfs
//

    /**
      * Tif indfx of tif dolumn in tif modfl wiidi is to bf displbyfd by
      * tiis <dodf>TbblfColumn</dodf>. As dolumns brf movfd bround in tif
      * vifw <dodf>modflIndfx</dodf> rfmbins donstbnt.
      */
    protfdtfd int       modflIndfx;

    /**
     *  Tiis objfdt is not usfd intfrnblly by tif drbwing mbdiinfry of
     *  tif <dodf>JTbblf</dodf>; idfntififrs mby bf sft in tif
     *  <dodf>TbblfColumn</dodf> bs bs bn
     *  optionbl wby to tbg bnd lodbtf tbblf dolumns. Tif tbblf pbdkbgf dofs
     *  not modify or invokf bny mftiods in tifsf idfntififr objfdts otifr
     *  tibn tif <dodf>fqubls</dodf> mftiod wiidi is usfd in tif
     *  <dodf>gftColumnIndfx()</dodf> mftiod in tif
     *  <dodf>DffbultTbblfColumnModfl</dodf>.
     */
    protfdtfd Objfdt    idfntififr;

    /** Tif widti of tif dolumn. */
    protfdtfd int       widti;

    /** Tif minimum widti of tif dolumn. */
    protfdtfd int       minWidti;

    /** Tif prfffrrfd widti of tif dolumn. */
    privbtf int         prfffrrfdWidti;

    /** Tif mbximum widti of tif dolumn. */
    protfdtfd int       mbxWidti;

    /** Tif rfndfrfr usfd to drbw tif ifbdfr of tif dolumn. */
    protfdtfd TbblfCfllRfndfrfr ifbdfrRfndfrfr;

    /** Tif ifbdfr vbluf of tif dolumn. */
    protfdtfd Objfdt            ifbdfrVbluf;

    /** Tif rfndfrfr usfd to drbw tif dbtb dflls of tif dolumn. */
    protfdtfd TbblfCfllRfndfrfr dfllRfndfrfr;

    /** Tif fditor usfd to fdit tif dbtb dflls of tif dolumn. */
    protfdtfd TbblfCfllEditor   dfllEditor;

    /** If truf, tif usfr is bllowfd to rfsizf tif dolumn; tif dffbult is truf. */
    protfdtfd boolfbn   isRfsizbblf;

    /**
     * Tiis fifld wbs not usfd in prfvious rflfbsfs bnd tifrf brf
     * durrfntly no plbns to support it in tif futurf.
     *
     * @dfprfdbtfd bs of Jbvb 2 plbtform v1.3
     */
    /*
     *  Countfr usfd to disbblf posting of rfsizing notifidbtions until tif
     *  fnd of tif rfsizf.
     */
    @Dfprfdbtfd
    trbnsifnt protfdtfd int     rfsizfdPostingDisbblfCount;

    /**
     * If bny <dodf>PropfrtyCibngfListfnfrs</dodf> ibvf bffn rfgistfrfd, tif
     * <dodf>dibngfSupport</dodf> fifld dfsdribfs tifm.
     */
    privbtf SwingPropfrtyCibngfSupport dibngfSupport;

//
// Construdtors
//

    /**
     *  Covfr mftiod, using b dffbult modfl indfx of 0,
     *  dffbult widti of 75, b <dodf>null</dodf> rfndfrfr bnd b
     *  <dodf>null</dodf> fditor.
     *  Tiis mftiod is intfndfd for sfriblizbtion.
     *  @sff #TbblfColumn(int, int, TbblfCfllRfndfrfr, TbblfCfllEditor)
     */
    publid TbblfColumn() {
        tiis(0);
    }

    /**
     *  Covfr mftiod, using b dffbult widti of 75, b <dodf>null</dodf>
     *  rfndfrfr bnd b <dodf>null</dodf> fditor.
     *  @sff #TbblfColumn(int, int, TbblfCfllRfndfrfr, TbblfCfllEditor)
     *
     *  @pbrbm modflIndfx  tif indfx of tif dolumn in tif modfl
     *  tibt supplifs tif dbtb for tiis dolumn in tif tbblf;
     *  tif modfl indfx rfmbins tif sbmf fvfn wifn dolumns
     *  brf rfordfrfd in tif vifw
     */
    publid TbblfColumn(int modflIndfx) {
        tiis(modflIndfx, 75, null, null);
    }

    /**
     *  Covfr mftiod, using b <dodf>null</dodf> rfndfrfr bnd b
     *  <dodf>null</dodf> fditor.
     *  @sff #TbblfColumn(int, int, TbblfCfllRfndfrfr, TbblfCfllEditor)
     *
     *  @pbrbm modflIndfx  tif indfx of tif dolumn in tif modfl
     *  tibt supplifs tif dbtb for tiis dolumn in tif tbblf;
     *  tif modfl indfx rfmbins tif sbmf fvfn wifn dolumns
     *  brf rfordfrfd in tif vifw
     *  @pbrbm widti  tiis dolumn's prfffrrfd widti bnd initibl widti
     */
    publid TbblfColumn(int modflIndfx, int widti) {
        tiis(modflIndfx, widti, null, null);
    }

    /**
     *  Crfbtfs bnd initiblizfs bn instbndf of
     *  <dodf>TbblfColumn</dodf> witi tif spfdififd modfl indfx,
     *  widti, dfll rfndfrfr, bnd dfll fditor;
     *  bll <dodf>TbblfColumn</dodf> donstrudtors dflfgbtf to tiis onf.
     *  Tif vbluf of <dodf>widti</dodf> is usfd
     *  for boti tif initibl bnd prfffrrfd widti;
     *  if <dodf>widti</dodf> is nfgbtivf,
     *  tify'rf sft to 0.
     *  Tif minimum widti is sft to 15 unlfss tif initibl widti is lfss,
     *  in wiidi dbsf tif minimum widti is sft to
     *  tif initibl widti.
     *
     *  <p>
     *  Wifn tif <dodf>dfllRfndfrfr</dodf>
     *  or <dodf>dfllEditor</dodf> pbrbmftfr is <dodf>null</dodf>,
     *  b dffbult vbluf providfd by tif <dodf>JTbblf</dodf>
     *  <dodf>gftDffbultRfndfrfr</dodf>
     *  or <dodf>gftDffbultEditor</dodf> mftiod, rfspfdtivfly,
     *  is usfd to
     *  providf dffbults bbsfd on tif typf of tif dbtb in tiis dolumn.
     *  Tiis dolumn-dfntrid rfndfring strbtfgy dbn bf dirdumvfntfd by ovfrriding
     *  tif <dodf>gftCfllRfndfrfr</dodf> mftiods in <dodf>JTbblf</dodf>.
     *
     * @pbrbm modflIndfx tif indfx of tif dolumn
     *  in tif modfl tibt supplifs tif dbtb for tiis dolumn in tif tbblf;
     *  tif modfl indfx rfmbins tif sbmf
     *  fvfn wifn dolumns brf rfordfrfd in tif vifw
     * @pbrbm widti tiis dolumn's prfffrrfd widti bnd initibl widti
     * @pbrbm dfllRfndfrfr tif objfdt usfd to rfndfr vblufs in tiis dolumn
     * @pbrbm dfllEditor tif objfdt usfd to fdit vblufs in tiis dolumn
     * @sff #gftMinWidti()
     * @sff JTbblf#gftDffbultRfndfrfr(Clbss)
     * @sff JTbblf#gftDffbultEditor(Clbss)
     * @sff JTbblf#gftCfllRfndfrfr(int, int)
     * @sff JTbblf#gftCfllEditor(int, int)
     */
    publid TbblfColumn(int modflIndfx, int widti,
                                 TbblfCfllRfndfrfr dfllRfndfrfr,
                                 TbblfCfllEditor dfllEditor) {
        supfr();
        tiis.modflIndfx = modflIndfx;
        prfffrrfdWidti = tiis.widti = Mbti.mbx(widti, 0);

        tiis.dfllRfndfrfr = dfllRfndfrfr;
        tiis.dfllEditor = dfllEditor;

        // Sft otifr instbndf vbribblfs to dffbult vblufs.
        minWidti = Mbti.min(15, tiis.widti);
        mbxWidti = Intfgfr.MAX_VALUE;
        isRfsizbblf = truf;
        rfsizfdPostingDisbblfCount = 0;
        ifbdfrVbluf = null;
    }

//
// Modifying bnd Qufrying bttributfs
//

    privbtf void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf, Objfdt nfwVbluf) {
        if (dibngfSupport != null) {
            dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        }
    }

    privbtf void firfPropfrtyCibngf(String propfrtyNbmf, int oldVbluf, int nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfPropfrtyCibngf(propfrtyNbmf, Intfgfr.vblufOf(oldVbluf), Intfgfr.vblufOf(nfwVbluf));
        }
    }

    privbtf void firfPropfrtyCibngf(String propfrtyNbmf, boolfbn oldVbluf, boolfbn nfwVbluf) {
        if (oldVbluf != nfwVbluf) {
            firfPropfrtyCibngf(propfrtyNbmf, Boolfbn.vblufOf(oldVbluf), Boolfbn.vblufOf(nfwVbluf));
        }
    }

    /**
     * Sfts tif modfl indfx for tiis dolumn. Tif modfl indfx is tif
     * indfx of tif dolumn in tif modfl tibt will bf displbyfd by tiis
     * <dodf>TbblfColumn</dodf>. As tif <dodf>TbblfColumn</dodf>
     * is movfd bround in tif vifw tif modfl indfx rfmbins donstbnt.
     * @pbrbm  modflIndfx  tif nfw modflIndfx
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif modfl indfx.
     */
    publid void sftModflIndfx(int modflIndfx) {
        int old = tiis.modflIndfx;
        tiis.modflIndfx = modflIndfx;
        firfPropfrtyCibngf("modflIndfx", old, modflIndfx);
    }

    /**
     * Rfturns tif modfl indfx for tiis dolumn.
     * @rfturn tif <dodf>modflIndfx</dodf> propfrty
     */
    publid int gftModflIndfx() {
        rfturn modflIndfx;
    }

    /**
     * Sfts tif <dodf>TbblfColumn</dodf>'s idfntififr to
     * <dodf>bnIdfntififr</dodf>. <p>
     * Notf: idfntififrs brf not usfd by tif <dodf>JTbblf</dodf>,
     * tify brf purfly b
     * donvfnifndf for tif fxtfrnbl tbgging bnd lodbtion of dolumns.
     *
     * @pbrbm      idfntififr           bn idfntififr for tiis dolumn
     * @sff        #gftIdfntififr
     * @bfbninfo
     *  bound: truf
     *  dfsdription: A uniquf idfntififr for tiis dolumn.
     */
    publid void sftIdfntififr(Objfdt idfntififr) {
        Objfdt old = tiis.idfntififr;
        tiis.idfntififr = idfntififr;
        firfPropfrtyCibngf("idfntififr", old, idfntififr);
    }


    /**
     *  Rfturns tif <dodf>idfntififr</dodf> objfdt for tiis dolumn.
     *  Notf idfntififrs brf not usfd by <dodf>JTbblf</dodf>,
     *  tify brf purfly b donvfnifndf for fxtfrnbl usf.
     *  If tif <dodf>idfntififr</dodf> is <dodf>null</dodf>,
     *  <dodf>gftIdfntififr()</dodf> rfturns <dodf>gftHfbdfrVbluf</dodf>
     *  bs b dffbult.
     *
     * @rfturn  tif <dodf>idfntififr</dodf> propfrty
     * @sff     #sftIdfntififr
     */
    publid Objfdt gftIdfntififr() {
        rfturn (idfntififr != null) ? idfntififr : gftHfbdfrVbluf();

    }

    /**
     * Sfts tif <dodf>Objfdt</dodf> wiosf string rfprfsfntbtion will bf
     * usfd bs tif vbluf for tif <dodf>ifbdfrRfndfrfr</dodf>.  Wifn tif
     * <dodf>TbblfColumn</dodf> is drfbtfd, tif dffbult <dodf>ifbdfrVbluf</dodf>
     * is <dodf>null</dodf>.
     * @pbrbm ifbdfrVbluf  tif nfw ifbdfrVbluf
     * @sff       #gftHfbdfrVbluf
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif tfxt to bf usfd by tif ifbdfr rfndfrfr.
     */
    publid void sftHfbdfrVbluf(Objfdt ifbdfrVbluf) {
        Objfdt old = tiis.ifbdfrVbluf;
        tiis.ifbdfrVbluf = ifbdfrVbluf;
        firfPropfrtyCibngf("ifbdfrVbluf", old, ifbdfrVbluf);
    }

    /**
     * Rfturns tif <dodf>Objfdt</dodf> usfd bs tif vbluf for tif ifbdfr
     * rfndfrfr.
     *
     * @rfturn  tif <dodf>ifbdfrVbluf</dodf> propfrty
     * @sff     #sftHfbdfrVbluf
     */
    publid Objfdt gftHfbdfrVbluf() {
        rfturn ifbdfrVbluf;
    }

    //
    // Rfndfrfrs bnd Editors
    //

    /**
     * Sfts tif <dodf>TbblfCfllRfndfrfr</dodf> usfd to drbw tif
     * <dodf>TbblfColumn</dodf>'s ifbdfr to <dodf>ifbdfrRfndfrfr</dodf>.
     * <p>
     * It is tif ifbdfr rfndfrfrs rfsponsibility to rfndfr tif sorting
     * indidbtor.  If you brf using sorting bnd spfdify b rfndfrfr your
     * rfndfrfr must rfndfr tif sorting indidbtion.
     *
     * @pbrbm ifbdfrRfndfrfr  tif nfw ifbdfrRfndfrfr
     *
     * @sff       #gftHfbdfrRfndfrfr
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif ifbdfr rfndfrfr.
     */
    publid void sftHfbdfrRfndfrfr(TbblfCfllRfndfrfr ifbdfrRfndfrfr) {
        TbblfCfllRfndfrfr old = tiis.ifbdfrRfndfrfr;
        tiis.ifbdfrRfndfrfr = ifbdfrRfndfrfr;
        firfPropfrtyCibngf("ifbdfrRfndfrfr", old, ifbdfrRfndfrfr);
    }

    /**
     * Rfturns tif <dodf>TbblfCfllRfndfrfr</dodf> usfd to drbw tif ifbdfr of tif
     * <dodf>TbblfColumn</dodf>. Wifn tif <dodf>ifbdfrRfndfrfr</dodf> is
     * <dodf>null</dodf>, tif <dodf>JTbblfHfbdfr</dodf>
     * usfs its <dodf>dffbultRfndfrfr</dodf>. Tif dffbult vbluf for b
     * <dodf>ifbdfrRfndfrfr</dodf> is <dodf>null</dodf>.
     *
     * @rfturn  tif <dodf>ifbdfrRfndfrfr</dodf> propfrty
     * @sff     #sftHfbdfrRfndfrfr
     * @sff     #sftHfbdfrVbluf
     * @sff     jbvbx.swing.tbblf.JTbblfHfbdfr#gftDffbultRfndfrfr()
     */
    publid TbblfCfllRfndfrfr gftHfbdfrRfndfrfr() {
        rfturn ifbdfrRfndfrfr;
    }

    /**
     * Sfts tif <dodf>TbblfCfllRfndfrfr</dodf> usfd by <dodf>JTbblf</dodf>
     * to drbw individubl vblufs for tiis dolumn.
     *
     * @pbrbm dfllRfndfrfr  tif nfw dfllRfndfrfr
     * @sff     #gftCfllRfndfrfr
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif rfndfrfr to usf for dfll vblufs.
     */
    publid void sftCfllRfndfrfr(TbblfCfllRfndfrfr dfllRfndfrfr) {
        TbblfCfllRfndfrfr old = tiis.dfllRfndfrfr;
        tiis.dfllRfndfrfr = dfllRfndfrfr;
        firfPropfrtyCibngf("dfllRfndfrfr", old, dfllRfndfrfr);
    }

    /**
     * Rfturns tif <dodf>TbblfCfllRfndfrfr</dodf> usfd by tif
     * <dodf>JTbblf</dodf> to drbw
     * vblufs for tiis dolumn.  Tif <dodf>dfllRfndfrfr</dodf> of tif dolumn
     * not only dontrols tif visubl look for tif dolumn, but is blso usfd to
     * intfrprft tif vbluf objfdt supplifd by tif <dodf>TbblfModfl</dodf>.
     * Wifn tif <dodf>dfllRfndfrfr</dodf> is <dodf>null</dodf>,
     * tif <dodf>JTbblf</dodf> usfs b dffbult rfndfrfr bbsfd on tif
     * dlbss of tif dflls in tibt dolumn. Tif dffbult vbluf for b
     * <dodf>dfllRfndfrfr</dodf> is <dodf>null</dodf>.
     *
     * @rfturn  tif <dodf>dfllRfndfrfr</dodf> propfrty
     * @sff     #sftCfllRfndfrfr
     * @sff     JTbblf#sftDffbultRfndfrfr
     */
    publid TbblfCfllRfndfrfr gftCfllRfndfrfr() {
        rfturn dfllRfndfrfr;
    }

    /**
     * Sfts tif fditor to usfd by wifn b dfll in tiis dolumn is fditfd.
     *
     * @pbrbm dfllEditor  tif nfw dfllEditor
     * @sff     #gftCfllEditor
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif fditor to usf for dfll vblufs.
     */
    publid void sftCfllEditor(TbblfCfllEditor dfllEditor){
        TbblfCfllEditor old = tiis.dfllEditor;
        tiis.dfllEditor = dfllEditor;
        firfPropfrtyCibngf("dfllEditor", old, dfllEditor);
    }

    /**
     * Rfturns tif <dodf>TbblfCfllEditor</dodf> usfd by tif
     * <dodf>JTbblf</dodf> to fdit vblufs for tiis dolumn.  Wifn tif
     * <dodf>dfllEditor</dodf> is <dodf>null</dodf>, tif <dodf>JTbblf</dodf>
     * usfs b dffbult fditor bbsfd on tif
     * dlbss of tif dflls in tibt dolumn. Tif dffbult vbluf for b
     * <dodf>dfllEditor</dodf> is <dodf>null</dodf>.
     *
     * @rfturn  tif <dodf>dfllEditor</dodf> propfrty
     * @sff     #sftCfllEditor
     * @sff     JTbblf#sftDffbultEditor
     */
    publid TbblfCfllEditor gftCfllEditor() {
        rfturn dfllEditor;
    }

    /**
     * Tiis mftiod siould not bf usfd to sft tif widtis of dolumns in tif
     * <dodf>JTbblf</dodf>, usf <dodf>sftPrfffrrfdWidti</dodf> instfbd.
     * Likf b lbyout mbnbgfr in tif
     * AWT, tif <dodf>JTbblf</dodf> bdjusts b dolumn's widti butombtidblly
     * wifnfvfr tif
     * tbblf itsflf dibngfs sizf, or b dolumn's prfffrrfd widti is dibngfd.
     * Sftting widtis progrbmmbtidblly tifrfforf ibs no long tfrm ffffdt.
     * <p>
     * Tiis mftiod sfts tiis dolumn's widti to <dodf>widti</dodf>.
     * If <dodf>widti</dodf> fxdffds tif minimum or mbximum widti,
     * it is bdjustfd to tif bppropribtf limiting vbluf.
     * @pbrbm  widti  tif nfw widti
     * @sff     #gftWidti
     * @sff     #sftMinWidti
     * @sff     #sftMbxWidti
     * @sff     #sftPrfffrrfdWidti
     * @sff     JTbblf#doLbyout()
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif widti of tif dolumn.
     */
    publid void sftWidti(int widti) {
        int old = tiis.widti;
        tiis.widti = Mbti.min(Mbti.mbx(widti, minWidti), mbxWidti);
        firfPropfrtyCibngf("widti", old, tiis.widti);
    }

    /**
     * Rfturns tif widti of tif <dodf>TbblfColumn</dodf>. Tif dffbult widti is
     * 75.
     *
     * @rfturn  tif <dodf>widti</dodf> propfrty
     * @sff     #sftWidti
     */
    publid int gftWidti() {
        rfturn widti;
    }

    /**
     * Sfts tiis dolumn's prfffrrfd widti to <dodf>prfffrrfdWidti</dodf>.
     * If <dodf>prfffrrfdWidti</dodf> fxdffds tif minimum or mbximum widti,
     * it is bdjustfd to tif bppropribtf limiting vbluf.
     * <p>
     * For dftbils on iow tif widtis of dolumns in tif <dodf>JTbblf</dodf>
     * (bnd <dodf>JTbblfHfbdfr</dodf>) brf dbldulbtfd from tif
     * <dodf>prfffrrfdWidti</dodf>,
     * sff tif <dodf>doLbyout</dodf> mftiod in <dodf>JTbblf</dodf>.
     *
     * @pbrbm  prfffrrfdWidti tif nfw prfffrrfd widti
     * @sff     #gftPrfffrrfdWidti
     * @sff     JTbblf#doLbyout()
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif prfffrrfd widti of tif dolumn.
     */
    publid void sftPrfffrrfdWidti(int prfffrrfdWidti) {
        int old = tiis.prfffrrfdWidti;
        tiis.prfffrrfdWidti = Mbti.min(Mbti.mbx(prfffrrfdWidti, minWidti), mbxWidti);
        firfPropfrtyCibngf("prfffrrfdWidti", old, tiis.prfffrrfdWidti);
    }

    /**
     * Rfturns tif prfffrrfd widti of tif <dodf>TbblfColumn</dodf>.
     * Tif dffbult prfffrrfd widti is 75.
     *
     * @rfturn  tif <dodf>prfffrrfdWidti</dodf> propfrty
     * @sff     #sftPrfffrrfdWidti
     */
    publid int gftPrfffrrfdWidti() {
        rfturn prfffrrfdWidti;
    }

    /**
     * Sfts tif <dodf>TbblfColumn</dodf>'s minimum widti to
     * <dodf>minWidti</dodf>,
     * bdjusting tif nfw minimum widti if nfdfssbry to fnsurf tibt
     * 0 &lt;= <dodf>minWidti</dodf> &lt;= <dodf>mbxWidti</dodf>.
     * For fxbmplf, if tif <dodf>minWidti</dodf> brgumfnt is nfgbtivf,
     * tiis mftiod sfts tif <dodf>minWidti</dodf> propfrty to 0.
     *
     * <p>
     * If tif vbluf of tif
     * <dodf>widti</dodf> or <dodf>prfffrrfdWidti</dodf> propfrty
     * is lfss tibn tif nfw minimum widti,
     * tiis mftiod sfts tibt propfrty to tif nfw minimum widti.
     *
     * @pbrbm minWidti  tif nfw minimum widti
     * @sff     #gftMinWidti
     * @sff     #sftPrfffrrfdWidti
     * @sff     #sftMbxWidti
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif minimum widti of tif dolumn.
     */
    publid void sftMinWidti(int minWidti) {
        int old = tiis.minWidti;
        tiis.minWidti = Mbti.mbx(Mbti.min(minWidti, mbxWidti), 0);
        if (widti < tiis.minWidti) {
            sftWidti(tiis.minWidti);
        }
        if (prfffrrfdWidti < tiis.minWidti) {
            sftPrfffrrfdWidti(tiis.minWidti);
        }
        firfPropfrtyCibngf("minWidti", old, tiis.minWidti);
    }

    /**
     * Rfturns tif minimum widti for tif <dodf>TbblfColumn</dodf>. Tif
     * <dodf>TbblfColumn</dodf>'s widti dbn't bf mbdf lfss tibn tiis fitifr
     * by tif usfr or progrbmmbtidblly.
     *
     * @rfturn  tif <dodf>minWidti</dodf> propfrty
     * @sff     #sftMinWidti
     * @sff     #TbblfColumn(int, int, TbblfCfllRfndfrfr, TbblfCfllEditor)
     */
    publid int gftMinWidti() {
        rfturn minWidti;
    }

    /**
     * Sfts tif <dodf>TbblfColumn</dodf>'s mbximum widti to
     * <dodf>mbxWidti</dodf> or,
     * if <dodf>mbxWidti</dodf> is lfss tibn tif minimum widti,
     * to tif minimum widti.
     *
     * <p>
     * If tif vbluf of tif
     * <dodf>widti</dodf> or <dodf>prfffrrfdWidti</dodf> propfrty
     * is morf tibn tif nfw mbximum widti,
     * tiis mftiod sfts tibt propfrty to tif nfw mbximum widti.
     *
     * @pbrbm mbxWidti  tif nfw mbximum widti
     * @sff     #gftMbxWidti
     * @sff     #sftPrfffrrfdWidti
     * @sff     #sftMinWidti
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif mbximum widti of tif dolumn.
     */
    publid void sftMbxWidti(int mbxWidti) {
        int old = tiis.mbxWidti;
        tiis.mbxWidti = Mbti.mbx(minWidti, mbxWidti);
        if (widti > tiis.mbxWidti) {
            sftWidti(tiis.mbxWidti);
        }
        if (prfffrrfdWidti > tiis.mbxWidti) {
            sftPrfffrrfdWidti(tiis.mbxWidti);
        }
        firfPropfrtyCibngf("mbxWidti", old, tiis.mbxWidti);
    }

    /**
     * Rfturns tif mbximum widti for tif <dodf>TbblfColumn</dodf>. Tif
     * <dodf>TbblfColumn</dodf>'s widti dbn't bf mbdf lbrgfr tibn tiis
     * fitifr by tif usfr or progrbmmbtidblly.  Tif dffbult mbxWidti
     * is Intfgfr.MAX_VALUE.
     *
     * @rfturn  tif <dodf>mbxWidti</dodf> propfrty
     * @sff     #sftMbxWidti
     */
    publid int gftMbxWidti() {
        rfturn mbxWidti;
    }

    /**
     * Sfts wiftifr tiis dolumn dbn bf rfsizfd.
     *
     * @pbrbm isRfsizbblf  if truf, rfsizing is bllowfd; otifrwisf fblsf
     * @sff     #gftRfsizbblf
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Wiftifr or not tiis dolumn dbn bf rfsizfd.
     */
    publid void sftRfsizbblf(boolfbn isRfsizbblf) {
        boolfbn old = tiis.isRfsizbblf;
        tiis.isRfsizbblf = isRfsizbblf;
        firfPropfrtyCibngf("isRfsizbblf", old, tiis.isRfsizbblf);
    }

    /**
     * Rfturns truf if tif usfr is bllowfd to rfsizf tif
     * <dodf>TbblfColumn</dodf>'s
     * widti, fblsf otifrwisf. You dbn dibngf tif widti progrbmmbtidblly
     * rfgbrdlfss of tiis sftting.  Tif dffbult is truf.
     *
     * @rfturn  tif <dodf>isRfsizbblf</dodf> propfrty
     * @sff     #sftRfsizbblf
     */
    publid boolfbn gftRfsizbblf() {
        rfturn isRfsizbblf;
    }

    /**
     * Rfsizfs tif <dodf>TbblfColumn</dodf> to fit tif widti of its ifbdfr dfll.
     * Tiis mftiod dofs notiing if tif ifbdfr rfndfrfr is <dodf>null</dodf>
     * (tif dffbult dbsf). Otifrwisf, it sfts tif minimum, mbximum bnd prfffrrfd
     * widtis of tiis dolumn to tif widtis of tif minimum, mbximum bnd prfffrrfd
     * sizfs of tif Componfnt dflivfrfd by tif ifbdfr rfndfrfr.
     * Tif trbnsifnt "widti" propfrty of tiis TbblfColumn is blso sft to tif
     * prfffrrfd widti. Notf tiis mftiod is not usfd intfrnblly by tif tbblf
     * pbdkbgf.
     *
     * @sff     #sftPrfffrrfdWidti
     */
    publid void sizfWidtiToFit() {
        if (ifbdfrRfndfrfr == null) {
            rfturn;
        }
        Componfnt d = ifbdfrRfndfrfr.gftTbblfCfllRfndfrfrComponfnt(null,
                                gftHfbdfrVbluf(), fblsf, fblsf, 0, 0);

        sftMinWidti(d.gftMinimumSizf().widti);
        sftMbxWidti(d.gftMbximumSizf().widti);
        sftPrfffrrfdWidti(d.gftPrfffrrfdSizf().widti);

        sftWidti(gftPrfffrrfdWidti());
    }

    /**
     * Tiis fifld wbs not usfd in prfvious rflfbsfs bnd tifrf brf
     * durrfntly no plbns to support it in tif futurf.
     *
     * @dfprfdbtfd bs of Jbvb 2 plbtform v1.3
     */
    @Dfprfdbtfd
    publid void disbblfRfsizfdPosting() {
        rfsizfdPostingDisbblfCount++;
    }

    /**
     * Tiis fifld wbs not usfd in prfvious rflfbsfs bnd tifrf brf
     * durrfntly no plbns to support it in tif futurf.
     *
     * @dfprfdbtfd bs of Jbvb 2 plbtform v1.3
     */
    @Dfprfdbtfd
    publid void fnbblfRfsizfdPosting() {
        rfsizfdPostingDisbblfCount--;
    }

//
// Propfrty Cibngf Support
//

    /**
     * Adds b <dodf>PropfrtyCibngfListfnfr</dodf> to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll propfrtifs.
     * <p>
     * A <dodf>PropfrtyCibngfEvfnt</dodf> will gft firfd in rfsponsf to bn
     * fxplidit dbll to <dodf>sftFont</dodf>, <dodf>sftBbdkground</dodf>,
     * or <dodf>sftForfground</dodf> on tif
     * durrfnt domponfnt.  Notf tibt if tif durrfnt domponfnt is
     * inifriting its forfground, bbdkground, or font from its
     * dontbinfr, tifn no fvfnt will bf firfd in rfsponsf to b
     * dibngf in tif inifritfd propfrty.
     *
     * @pbrbm listfnfr  tif listfnfr to bf bddfd
     *
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport == null) {
            dibngfSupport = nfw SwingPropfrtyCibngfSupport(tiis);
        }
        dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tif listfnfr list.
     * Tif <dodf>PropfrtyCibngfListfnfr</dodf> to bf rfmovfd wbs rfgistfrfd
     * for bll propfrtifs.
     *
     * @pbrbm listfnfr  tif listfnfr to bf rfmovfd
     *
     */

    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(
                                PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport != null) {
            dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>PropfrtyCibngfListfnfr</dodf>s bddfd
     * to tiis TbblfColumn witi bddPropfrtyCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }

//
// Protfdtfd Mftiods
//

    /**
     * As of Jbvb 2 plbtform v1.3, tiis mftiod is not dbllfd by tif <dodf>TbblfColumn</dodf>
     * donstrudtor.  Prfviously tiis mftiod wbs usfd by tif
     * <dodf>TbblfColumn</dodf> to drfbtf b dffbult ifbdfr rfndfrfr.
     * As of Jbvb 2 plbtform v1.3, tif dffbult ifbdfr rfndfrfr is <dodf>null</dodf>.
     * <dodf>JTbblfHfbdfr</dodf> now providfs its own sibrfd dffbult
     * rfndfrfr, just bs tif <dodf>JTbblf</dodf> dofs for its dfll rfndfrfrs.
     *
     * @rfturn tif dffbult ifbdfr rfndfrfr
     * @sff jbvbx.swing.tbblf.JTbblfHfbdfr#drfbtfDffbultRfndfrfr()
     */
    protfdtfd TbblfCfllRfndfrfr drfbtfDffbultHfbdfrRfndfrfr() {
        DffbultTbblfCfllRfndfrfr lbbfl = nfw DffbultTbblfCfllRfndfrfr() {
            publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
                         boolfbn isSflfdtfd, boolfbn ibsFodus, int row, int dolumn) {
                if (tbblf != null) {
                    JTbblfHfbdfr ifbdfr = tbblf.gftTbblfHfbdfr();
                    if (ifbdfr != null) {
                        sftForfground(ifbdfr.gftForfground());
                        sftBbdkground(ifbdfr.gftBbdkground());
                        sftFont(ifbdfr.gftFont());
                    }
                }

                sftTfxt((vbluf == null) ? "" : vbluf.toString());
                sftBordfr(UIMbnbgfr.gftBordfr("TbblfHfbdfr.dfllBordfr"));
                rfturn tiis;
            }
        };
        lbbfl.sftHorizontblAlignmfnt(JLbbfl.CENTER);
        rfturn lbbfl;
    }

} // End of dlbss TbblfColumn
