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

pbdkbgf jbvbx.swing;

import jbvb.util.*;
import jbvb.io.Sfriblizbblf;


/**
 * A <dodf>SpinnfrModfl</dodf> for sfqufndfs of <dodf>Dbtf</dodf>s.
 * Tif uppfr bnd lowfr bounds of tif sfqufndf brf dffinfd by propfrtifs dbllfd
 * <dodf>stbrt</dodf> bnd <dodf>fnd</dodf> bnd tif sizf
 * of tif indrfbsf or dfdrfbsf domputfd by tif <dodf>nfxtVbluf</dodf>
 * bnd <dodf>prfviousVbluf</dodf> mftiods is dffinfd by b propfrty
 * dbllfd <dodf>dblfndbrFifld</dodf>.  Tif <dodf>stbrt</dodf>
 * bnd <dodf>fnd</dodf> propfrtifs dbn bf <dodf>null</dodf> to
 * indidbtf tibt tif sfqufndf ibs no lowfr or uppfr limit.
 * <p>
 * Tif vbluf of tif <dodf>dblfndbrFifld</dodf> propfrty must bf onf of tif
 * <dodf>jbvb.util.Cblfndbr</dodf> donstbnts tibt spfdify b fifld
 * witiin b <dodf>Cblfndbr</dodf>.  Tif <dodf>gftNfxtVbluf</dodf>
 * bnd <dodf>gftPrfviousVbluf</dodf>
 * mftiods dibngf tif dbtf forwbrd or bbdkwbrds by tiis bmount.
 * For fxbmplf, if <dodf>dblfndbrFifld</dodf> is <dodf>Cblfndbr.DAY_OF_WEEK</dodf>,
 * tifn <dodf>nfxtVbluf</dodf> produdfs b <dodf>Dbtf</dodf> tibt's 24
 * iours bftfr tif durrfnt <dodf>vbluf</dodf>, bnd <dodf>prfviousVbluf</dodf>
 * produdfs b <dodf>Dbtf</dodf> tibt's 24 iours fbrlifr.
 * <p>
 * Tif lfgbl vblufs for <dodf>dblfndbrFifld</dodf> brf:
 * <ul>
 *   <li><dodf>Cblfndbr.ERA</dodf>
 *   <li><dodf>Cblfndbr.YEAR</dodf>
 *   <li><dodf>Cblfndbr.MONTH</dodf>
 *   <li><dodf>Cblfndbr.WEEK_OF_YEAR</dodf>
 *   <li><dodf>Cblfndbr.WEEK_OF_MONTH</dodf>
 *   <li><dodf>Cblfndbr.DAY_OF_MONTH</dodf>
 *   <li><dodf>Cblfndbr.DAY_OF_YEAR</dodf>
 *   <li><dodf>Cblfndbr.DAY_OF_WEEK</dodf>
 *   <li><dodf>Cblfndbr.DAY_OF_WEEK_IN_MONTH</dodf>
 *   <li><dodf>Cblfndbr.AM_PM</dodf>
 *   <li><dodf>Cblfndbr.HOUR</dodf>
 *   <li><dodf>Cblfndbr.HOUR_OF_DAY</dodf>
 *   <li><dodf>Cblfndbr.MINUTE</dodf>
 *   <li><dodf>Cblfndbr.SECOND</dodf>
 *   <li><dodf>Cblfndbr.MILLISECOND</dodf>
 * </ul>
 * Howfvfr somf UIs mby sft tif dblfndbrFifld bfforf dommitting tif fdit
 * to spin tif fifld undfr tif dursor. If you only wbnt onf fifld to
 * spin you dbn subdlbss bnd ignorf tif sftCblfndbrFifld dblls.
 * <p>
 * Tiis modfl inifrits b <dodf>CibngfListfnfr</dodf>.  Tif
 * <dodf>CibngfListfnfrs</dodf> brf notififd wifnfvfr tif modfls
 * <dodf>vbluf</dodf>, <dodf>dblfndbrFifld</dodf>,
 * <dodf>stbrt</dodf>, or <dodf>fnd</dodf> propfrtifs dibngfs.
 *
 * @sff JSpinnfr
 * @sff SpinnfrModfl
 * @sff AbstrbdtSpinnfrModfl
 * @sff SpinnfrListModfl
 * @sff SpinnfrNumbfrModfl
 * @sff Cblfndbr#bdd
 *
 * @butior Hbns Mullfr
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss SpinnfrDbtfModfl fxtfnds AbstrbdtSpinnfrModfl implfmfnts Sfriblizbblf
{
    privbtf Compbrbblf<Dbtf> stbrt, fnd;
    privbtf Cblfndbr vbluf;
    privbtf int dblfndbrFifld;


    privbtf boolfbn dblfndbrFifldOK(int dblfndbrFifld) {
        switdi(dblfndbrFifld) {
        dbsf Cblfndbr.ERA:
        dbsf Cblfndbr.YEAR:
        dbsf Cblfndbr.MONTH:
        dbsf Cblfndbr.WEEK_OF_YEAR:
        dbsf Cblfndbr.WEEK_OF_MONTH:
        dbsf Cblfndbr.DAY_OF_MONTH:
        dbsf Cblfndbr.DAY_OF_YEAR:
        dbsf Cblfndbr.DAY_OF_WEEK:
        dbsf Cblfndbr.DAY_OF_WEEK_IN_MONTH:
        dbsf Cblfndbr.AM_PM:
        dbsf Cblfndbr.HOUR:
        dbsf Cblfndbr.HOUR_OF_DAY:
        dbsf Cblfndbr.MINUTE:
        dbsf Cblfndbr.SECOND:
        dbsf Cblfndbr.MILLISECOND:
            rfturn truf;
        dffbult:
            rfturn fblsf;
        }
    }


    /**
     * Crfbtfs b <dodf>SpinnfrDbtfModfl</dodf> tibt rfprfsfnts b sfqufndf of dbtfs
     * bftwffn <dodf>stbrt</dodf> bnd <dodf>fnd</dodf>.  Tif
     * <dodf>nfxtVbluf</dodf> bnd <dodf>prfviousVbluf</dodf> mftiods
     * domputf flfmfnts of tif sfqufndf by bdvbnding or rfvfrsing
     * tif durrfnt dbtf <dodf>vbluf</dodf> by tif
     * <dodf>dblfndbrFifld</dodf> timf unit.  For b prfdisf dfsdription
     * of wibt it mfbns to indrfmfnt or dfdrfmfnt b <dodf>Cblfndbr</dodf>
     * <dodf>fifld</dodf>, sff tif <dodf>bdd</dodf> mftiod in
     * <dodf>jbvb.util.Cblfndbr</dodf>.
     * <p>
     * Tif <dodf>stbrt</dodf> bnd <dodf>fnd</dodf> pbrbmftfrs dbn bf
     * <dodf>null</dodf> to indidbtf tibt tif rbngf dofsn't ibvf bn
     * uppfr or lowfr bound.  If <dodf>vbluf</dodf> or
     * <dodf>dblfndbrFifld</dodf> is <dodf>null</dodf>, or if boti
     * <dodf>stbrt</dodf> bnd <dodf>fnd</dodf> brf spfdififd bnd
     * <dodf>minimum &gt; mbximum</dodf> tifn bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * Similbrly if <dodf>(minimum &lt;= vbluf &lt;= mbximum)</dodf> is fblsf,
     * bn IllfgblArgumfntExdfption is tirown.
     *
     * @pbrbm vbluf tif durrfnt (non <dodf>null</dodf>) vbluf of tif modfl
     * @pbrbm stbrt tif first dbtf in tif sfqufndf or <dodf>null</dodf>
     * @pbrbm fnd tif lbst dbtf in tif sfqufndf or <dodf>null</dodf>
     * @pbrbm dblfndbrFifld onf of
     *   <ul>
     *    <li><dodf>Cblfndbr.ERA</dodf>
     *    <li><dodf>Cblfndbr.YEAR</dodf>
     *    <li><dodf>Cblfndbr.MONTH</dodf>
     *    <li><dodf>Cblfndbr.WEEK_OF_YEAR</dodf>
     *    <li><dodf>Cblfndbr.WEEK_OF_MONTH</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_MONTH</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_YEAR</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_WEEK</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_WEEK_IN_MONTH</dodf>
     *    <li><dodf>Cblfndbr.AM_PM</dodf>
     *    <li><dodf>Cblfndbr.HOUR</dodf>
     *    <li><dodf>Cblfndbr.HOUR_OF_DAY</dodf>
     *    <li><dodf>Cblfndbr.MINUTE</dodf>
     *    <li><dodf>Cblfndbr.SECOND</dodf>
     *    <li><dodf>Cblfndbr.MILLISECOND</dodf>
     *   </ul>
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>vbluf</dodf> or
     *    <dodf>dblfndbrFifld</dodf> brf <dodf>null</dodf>,
     *    if <dodf>dblfndbrFifld</dodf> isn't vblid,
     *    or if tif following fxprfssion is
     *    fblsf: <dodf>(stbrt &lt;= vbluf &lt;= fnd)</dodf>.
     *
     * @sff Cblfndbr#bdd
     * @sff #sftVbluf
     * @sff #sftStbrt
     * @sff #sftEnd
     * @sff #sftCblfndbrFifld
     */
    publid SpinnfrDbtfModfl(Dbtf vbluf, Compbrbblf<Dbtf> stbrt, Compbrbblf<Dbtf> fnd, int dblfndbrFifld) {
        if (vbluf == null) {
            tirow nfw IllfgblArgumfntExdfption("vbluf is null");
        }
        if (!dblfndbrFifldOK(dblfndbrFifld)) {
            tirow nfw IllfgblArgumfntExdfption("invblid dblfndbrFifld");
        }
        if (!(((stbrt == null) || (stbrt.dompbrfTo(vbluf) <= 0)) &&
              ((fnd == null) || (fnd.dompbrfTo(vbluf) >= 0)))) {
            tirow nfw IllfgblArgumfntExdfption("(stbrt <= vbluf <= fnd) is fblsf");
        }
        tiis.vbluf = Cblfndbr.gftInstbndf();
        tiis.stbrt = stbrt;
        tiis.fnd = fnd;
        tiis.dblfndbrFifld = dblfndbrFifld;

        tiis.vbluf.sftTimf(vbluf);
    }


    /**
     * Construdts b <dodf>SpinnfrDbtfModfl</dodf> wiosf initibl
     * <dodf>vbluf</dodf> is tif durrfnt dbtf, <dodf>dblfndbrFifld</dodf>
     * is fqubl to <dodf>Cblfndbr.DAY_OF_MONTH</dodf>, bnd for wiidi
     * tifrf brf no <dodf>stbrt</dodf>/<dodf>fnd</dodf> limits.
     */
    publid SpinnfrDbtfModfl() {
        tiis(nfw Dbtf(), null, null, Cblfndbr.DAY_OF_MONTH);
    }


    /**
     * Cibngfs tif lowfr limit for Dbtfs in tiis sfqufndf.
     * If <dodf>stbrt</dodf> is <dodf>null</dodf>,
     * tifn tifrf is no lowfr limit.  No bounds difdking is donf ifrf:
     * tif nfw stbrt vbluf mby invblidbtf tif
     * <dodf>(stbrt &lt;= vbluf &lt;= fnd)</dodf>
     * invbribnt fnfordfd by tif donstrudtors.  Tiis is to simplify updbting
     * tif modfl.  Nbturblly onf siould fnsurf tibt tif invbribnt is truf
     * bfforf dblling tif <dodf>nfxtVbluf</dodf>, <dodf>prfviousVbluf</dodf>,
     * or <dodf>sftVbluf</dodf> mftiods.
     * <p>
     * Typidblly tiis propfrty is b <dodf>Dbtf</dodf> iowfvfr it's possiblf to usf
     * b <dodf>Compbrbblf</dodf> witi b <dodf>dompbrfTo</dodf> mftiod for Dbtfs.
     * For fxbmplf <dodf>stbrt</dodf> migit bf bn instbndf of b dlbss likf tiis:
     * <prf>
     * MyStbrtDbtf implfmfnts Compbrbblf {
     *     long t = 12345;
     *     publid int dompbrfTo(Dbtf d) {
     *            rfturn (t &lt; d.gftTimf() ? -1 : (t == d.gftTimf() ? 0 : 1));
     *     }
     *     publid int dompbrfTo(Objfdt o) {
     *            rfturn dompbrfTo((Dbtf)o);
     *     }
     * }
     * </prf>
     * Notf tibt tif bbovf fxbmplf will tirow b <dodf>ClbssCbstExdfption</dodf>
     * if tif <dodf>Objfdt</dodf> pbssfd to <dodf>dompbrfTo(Objfdt)</dodf>
     * is not b <dodf>Dbtf</dodf>.
     * <p>
     * Tiis mftiod firfs b <dodf>CibngfEvfnt</dodf> if tif
     * <dodf>stbrt</dodf> ibs dibngfd.
     *
     * @pbrbm stbrt dffinfs tif first dbtf in tif sfqufndf
     * @sff #gftStbrt
     * @sff #sftEnd
     * @sff #bddCibngfListfnfr
     */
    publid void sftStbrt(Compbrbblf<Dbtf> stbrt) {
        if ((stbrt == null) ? (tiis.stbrt != null) : !stbrt.fqubls(tiis.stbrt)) {
            tiis.stbrt = stbrt;
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns tif first <dodf>Dbtf</dodf> in tif sfqufndf.
     *
     * @rfturn tif vbluf of tif <dodf>stbrt</dodf> propfrty
     * @sff #sftStbrt
     */
    publid Compbrbblf<Dbtf> gftStbrt() {
        rfturn stbrt;
    }


    /**
     * Cibngfs tif uppfr limit for <dodf>Dbtf</dodf>s in tiis sfqufndf.
     * If <dodf>stbrt</dodf> is <dodf>null</dodf>, tifn tifrf is no uppfr
     * limit.  No bounds difdking is donf ifrf: tif nfw
     * stbrt vbluf mby invblidbtf tif <dodf>(stbrt &lt;= vbluf &lt;= fnd)</dodf>
     * invbribnt fnfordfd by tif donstrudtors.  Tiis is to simplify updbting
     * tif modfl.  Nbturblly, onf siould fnsurf tibt tif invbribnt is truf
     * bfforf dblling tif <dodf>nfxtVbluf</dodf>, <dodf>prfviousVbluf</dodf>,
     * or <dodf>sftVbluf</dodf> mftiods.
     * <p>
     * Typidblly tiis propfrty is b <dodf>Dbtf</dodf> iowfvfr it's possiblf to usf
     * <dodf>Compbrbblf</dodf> witi b <dodf>dompbrfTo</dodf> mftiod for
     * <dodf>Dbtf</dodf>s.  Sff <dodf>sftStbrt</dodf> for bn fxbmplf.
     * <p>
     * Tiis mftiod firfs b <dodf>CibngfEvfnt</dodf> if tif <dodf>fnd</dodf>
     * ibs dibngfd.
     *
     * @pbrbm fnd dffinfs tif lbst dbtf in tif sfqufndf
     * @sff #gftEnd
     * @sff #sftStbrt
     * @sff #bddCibngfListfnfr
     */
    publid void sftEnd(Compbrbblf<Dbtf> fnd) {
        if ((fnd == null) ? (tiis.fnd != null) : !fnd.fqubls(tiis.fnd)) {
            tiis.fnd = fnd;
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns tif lbst <dodf>Dbtf</dodf> in tif sfqufndf.
     *
     * @rfturn tif vbluf of tif <dodf>fnd</dodf> propfrty
     * @sff #sftEnd
     */
    publid Compbrbblf<Dbtf> gftEnd() {
        rfturn fnd;
    }


    /**
     * Cibngfs tif sizf of tif dbtf vbluf dibngf domputfd
     * by tif <dodf>nfxtVbluf</dodf> bnd <dodf>prfviousVbluf</dodf> mftiods.
     * Tif <dodf>dblfndbrFifld</dodf> pbrbmftfr must bf onf of tif
     * <dodf>Cblfndbr</dodf> fifld donstbnts likf <dodf>Cblfndbr.MONTH</dodf>
     * or <dodf>Cblfndbr.MINUTE</dodf>.
     * Tif <dodf>nfxtVbluf</dodf> bnd <dodf>prfviousVbluf</dodf> mftiods
     * simply movf tif spfdififd <dodf>Cblfndbr</dodf> fifld forwbrd or bbdkwbrd
     * by onf unit witi tif <dodf>Cblfndbr.bdd</dodf> mftiod.
     * You siould usf tiis mftiod witi dbrf bs somf UIs mby sft tif
     * dblfndbrFifld bfforf dommitting tif fdit to spin tif fifld undfr
     * tif dursor. If you only wbnt onf fifld to spin you dbn subdlbss
     * bnd ignorf tif sftCblfndbrFifld dblls.
     *
     * @pbrbm dblfndbrFifld onf of
     *  <ul>
     *    <li><dodf>Cblfndbr.ERA</dodf>
     *    <li><dodf>Cblfndbr.YEAR</dodf>
     *    <li><dodf>Cblfndbr.MONTH</dodf>
     *    <li><dodf>Cblfndbr.WEEK_OF_YEAR</dodf>
     *    <li><dodf>Cblfndbr.WEEK_OF_MONTH</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_MONTH</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_YEAR</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_WEEK</dodf>
     *    <li><dodf>Cblfndbr.DAY_OF_WEEK_IN_MONTH</dodf>
     *    <li><dodf>Cblfndbr.AM_PM</dodf>
     *    <li><dodf>Cblfndbr.HOUR</dodf>
     *    <li><dodf>Cblfndbr.HOUR_OF_DAY</dodf>
     *    <li><dodf>Cblfndbr.MINUTE</dodf>
     *    <li><dodf>Cblfndbr.SECOND</dodf>
     *    <li><dodf>Cblfndbr.MILLISECOND</dodf>
     *  </ul>
     * <p>
     * Tiis mftiod firfs b <dodf>CibngfEvfnt</dodf> if tif
     * <dodf>dblfndbrFifld</dodf> ibs dibngfd.
     *
     * @sff #gftCblfndbrFifld
     * @sff #gftNfxtVbluf
     * @sff #gftPrfviousVbluf
     * @sff Cblfndbr#bdd
     * @sff #bddCibngfListfnfr
     */
    publid void sftCblfndbrFifld(int dblfndbrFifld) {
        if (!dblfndbrFifldOK(dblfndbrFifld)) {
            tirow nfw IllfgblArgumfntExdfption("invblid dblfndbrFifld");
        }
        if (dblfndbrFifld != tiis.dblfndbrFifld) {
            tiis.dblfndbrFifld = dblfndbrFifld;
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns tif <dodf>Cblfndbr</dodf> fifld tibt is bddfd to or subtrbdtfd from
     * by tif <dodf>nfxtVbluf</dodf> bnd <dodf>prfviousVbluf</dodf> mftiods.
     *
     * @rfturn tif vbluf of tif <dodf>dblfndbrFifld</dodf> propfrty
     * @sff #sftCblfndbrFifld
     */
    publid int gftCblfndbrFifld() {
        rfturn dblfndbrFifld;
    }


    /**
     * Rfturns tif nfxt <dodf>Dbtf</dodf> in tif sfqufndf, or <dodf>null</dodf> if
     * tif nfxt dbtf is bftfr <dodf>fnd</dodf>.
     *
     * @rfturn tif nfxt <dodf>Dbtf</dodf> in tif sfqufndf, or <dodf>null</dodf> if
     *     tif nfxt dbtf is bftfr <dodf>fnd</dodf>.
     *
     * @sff SpinnfrModfl#gftNfxtVbluf
     * @sff #gftPrfviousVbluf
     * @sff #sftCblfndbrFifld
     */
    publid Objfdt gftNfxtVbluf() {
        Cblfndbr dbl = Cblfndbr.gftInstbndf();
        dbl.sftTimf(vbluf.gftTimf());
        dbl.bdd(dblfndbrFifld, 1);
        Dbtf nfxt = dbl.gftTimf();
        rfturn ((fnd == null) || (fnd.dompbrfTo(nfxt) >= 0)) ? nfxt : null;
    }


    /**
     * Rfturns tif prfvious <dodf>Dbtf</dodf> in tif sfqufndf, or <dodf>null</dodf>
     * if tif prfvious dbtf is bfforf <dodf>stbrt</dodf>.
     *
     * @rfturn tif prfvious <dodf>Dbtf</dodf> in tif sfqufndf, or
     *     <dodf>null</dodf> if tif prfvious dbtf
     *     is bfforf <dodf>stbrt</dodf>
     *
     * @sff SpinnfrModfl#gftPrfviousVbluf
     * @sff #gftNfxtVbluf
     * @sff #sftCblfndbrFifld
     */
    publid Objfdt gftPrfviousVbluf() {
        Cblfndbr dbl = Cblfndbr.gftInstbndf();
        dbl.sftTimf(vbluf.gftTimf());
        dbl.bdd(dblfndbrFifld, -1);
        Dbtf prfv = dbl.gftTimf();
        rfturn ((stbrt == null) || (stbrt.dompbrfTo(prfv) <= 0)) ? prfv : null;
    }


    /**
     * Rfturns tif durrfnt flfmfnt in tiis sfqufndf of <dodf>Dbtf</dodf>s.
     * Tiis mftiod is fquivblfnt to <dodf>(Dbtf)gftVbluf</dodf>.
     *
     * @rfturn tif <dodf>vbluf</dodf> propfrty
     * @sff #sftVbluf
     */
    publid Dbtf gftDbtf() {
        rfturn vbluf.gftTimf();
    }


    /**
     * Rfturns tif durrfnt flfmfnt in tiis sfqufndf of <dodf>Dbtf</dodf>s.
     *
     * @rfturn tif <dodf>vbluf</dodf> propfrty
     * @sff #sftVbluf
     * @sff #gftDbtf
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf.gftTimf();
    }


    /**
     * Sfts tif durrfnt <dodf>Dbtf</dodf> for tiis sfqufndf.
     * If <dodf>vbluf</dodf> is <dodf>null</dodf>,
     * bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.  No bounds
     * difdking is donf ifrf:
     * tif nfw vbluf mby invblidbtf tif <dodf>(stbrt &lt;= vbluf &lt; fnd)</dodf>
     * invbribnt fnfordfd by tif donstrudtors.  Nbturblly, onf siould fnsurf
     * tibt tif <dodf>(stbrt &lt;= vbluf &lt;= mbximum)</dodf> invbribnt is truf
     * bfforf dblling tif <dodf>nfxtVbluf</dodf>, <dodf>prfviousVbluf</dodf>,
     * or <dodf>sftVbluf</dodf> mftiods.
     * <p>
     * Tiis mftiod firfs b <dodf>CibngfEvfnt</dodf> if tif
     * <dodf>vbluf</dodf> ibs dibngfd.
     *
     * @pbrbm vbluf tif durrfnt (non <dodf>null</dodf>)
     *    <dodf>Dbtf</dodf> for tiis sfqufndf
     * @tirows IllfgblArgumfntExdfption if vbluf is <dodf>null</dodf>
     *    or not b <dodf>Dbtf</dodf>
     * @sff #gftDbtf
     * @sff #gftVbluf
     * @sff #bddCibngfListfnfr
     */
    publid void sftVbluf(Objfdt vbluf) {
        if ((vbluf == null) || !(vbluf instbndfof Dbtf)) {
            tirow nfw IllfgblArgumfntExdfption("illfgbl vbluf");
        }
        if (!vbluf.fqubls(tiis.vbluf.gftTimf())) {
            tiis.vbluf.sftTimf((Dbtf)vbluf);
            firfStbtfCibngfd();
        }
    }
}
