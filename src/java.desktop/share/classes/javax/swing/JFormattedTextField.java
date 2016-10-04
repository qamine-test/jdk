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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.im.InputContfxt;
import jbvb.io.*;
import jbvb.tfxt.*;
import jbvb.util.*;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.*;

/**
 * <dodf>JFormbttfdTfxtFifld</dodf> fxtfnds <dodf>JTfxtFifld</dodf> bdding
 * support for formbtting brbitrbry vblufs, bs wfll bs rftrifving b pbrtidulbr
 * objfdt ondf tif usfr ibs fditfd tif tfxt. Tif following illustrbtfs
 * donfiguring b <dodf>JFormbttfdTfxtFifld</dodf> to fdit dbtfs:
 * <prf>
 *   JFormbttfdTfxtFifld ftf = nfw JFormbttfdTfxtFifld();
 *   ftf.sftVbluf(nfw Dbtf());
 * </prf>
 * <p>
 * Ondf b <dodf>JFormbttfdTfxtFifld</dodf> ibs bffn drfbtfd, you dbn
 * listfn for fditing dibngfs by wby of bdding
 * b <dodf>PropfrtyCibngfListfnfr</dodf> bnd listfning for
 * <dodf>PropfrtyCibngfEvfnt</dodf>s witi tif propfrty nbmf <dodf>vbluf</dodf>.
 * <p>
 * <dodf>JFormbttfdTfxtFifld</dodf> bllows
 * donfiguring wibt bdtion siould bf tbkfn wifn fodus is lost. Tif possiblf
 * donfigurbtions brf:
 * <tbblf summbry="Possiblf JFormbttfdTfxtFifld donfigurbtions bnd tifir dfsdriptions">
 * <tr><ti><p stylf="tfxt-blign:lfft">Vbluf</p></ti><ti><p stylf="tfxt-blign:lfft">Dfsdription</p></ti></tr>
 * <tr><td>JFormbttfdTfxtFifld.REVERT
 *            <td>Rfvfrt tif displby to mbtdi tibt of <dodf>gftVbluf</dodf>,
 *                possibly losing tif durrfnt fdit.
 *        <tr><td>JFormbttfdTfxtFifld.COMMIT
 *            <td>Commits tif durrfnt vbluf. If tif vbluf bfing fditfd
 *                isn't donsidfrfd b lfgbl vbluf by tif
 *                <dodf>AbstrbdtFormbttfr</dodf> tibt is, b
 *                <dodf>PbrsfExdfption</dodf> is tirown, tifn tif vbluf
 *                will not dibngf, bnd tifn fditfd vbluf will pfrsist.
 *        <tr><td>JFormbttfdTfxtFifld.COMMIT_OR_REVERT
 *            <td>Similbr to <dodf>COMMIT</dodf>, but if tif vbluf isn't
 *                lfgbl, bfibvf likf <dodf>REVERT</dodf>.
 *        <tr><td>JFormbttfdTfxtFifld.PERSIST
 *            <td>Do notiing, don't obtbin b nfw
 *                <dodf>AbstrbdtFormbttfr</dodf>, bnd don't updbtf tif vbluf.
 * </tbblf>
 * Tif dffbult is <dodf>JFormbttfdTfxtFifld.COMMIT_OR_REVERT</dodf>,
 * rfffr to {@link #sftFodusLostBfibvior} for morf informbtion on tiis.
 * <p>
 * <dodf>JFormbttfdTfxtFifld</dodf> bllows tif fodus to lfbvf, fvfn if
 * tif durrfntly fditfd vbluf is invblid. To lodk tif fodus down wiilf tif
 * <dodf>JFormbttfdTfxtFifld</dodf> is bn invblid fdit stbtf
 * you dbn bttbdi bn <dodf>InputVfrififr</dodf>. Tif following dodf snippft
 * siows b potfntibl implfmfntbtion of sudi bn <dodf>InputVfrififr</dodf>:
 * <prf>
 * publid dlbss FormbttfdTfxtFifldVfrififr fxtfnds InputVfrififr {
 *     publid boolfbn vfrify(JComponfnt input) {
 *         if (input instbndfof JFormbttfdTfxtFifld) {
 *             JFormbttfdTfxtFifld ftf = (JFormbttfdTfxtFifld)input;
 *             AbstrbdtFormbttfr formbttfr = ftf.gftFormbttfr();
 *             if (formbttfr != null) {
 *                 String tfxt = ftf.gftTfxt();
 *                 try {
 *                      formbttfr.stringToVbluf(tfxt);
 *                      rfturn truf;
 *                  } dbtdi (PbrsfExdfption pf) {
 *                      rfturn fblsf;
 *                  }
 *              }
 *          }
 *          rfturn truf;
 *      }
 *      publid boolfbn siouldYifldFodus(JComponfnt input) {
 *          rfturn vfrify(input);
 *      }
 *  }
 * </prf>
 * <p>
 * Altfrnbtivfly, you dould invokf <dodf>dommitEdit</dodf>, wiidi would blso
 * dommit tif vbluf.
 * <p>
 * <dodf>JFormbttfdTfxtFifld</dodf> dofs not do tif formbtting it sflf,
 * rbtifr formbtting is donf tirougi bn instbndf of
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> wiidi is obtbinfd from
 * bn instbndf of <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfrFbdtory</dodf>.
 * Instbndfs of <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> brf
 * notififd wifn tify bfdomf bdtivf by wby of tif
 * <dodf>instbll</dodf> mftiod, bt wiidi point tif
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf> dbn instbll wibtfvfr
 * it nffds to, typidblly b <dodf>DodumfntFiltfr</dodf>. Similbrly wifn
 * <dodf>JFormbttfdTfxtFifld</dodf> no longfr
 * nffds tif <dodf>AbstrbdtFormbttfr</dodf>, it will invokf
 * <dodf>uninstbll</dodf>.
 * <p>
 * <dodf>JFormbttfdTfxtFifld</dodf> typidblly
 * qufrifs tif <dodf>AbstrbdtFormbttfrFbdtory</dodf> for bn
 * <dodf>AbstrbdtFormbt</dodf> wifn it gbins or losfs fodus. Altiougi tiis
 * dbn dibngf bbsfd on tif fodus lost polidy. If tif fodus lost
 * polidy is <dodf>JFormbttfdTfxtFifld.PERSIST</dodf>
 * bnd tif <dodf>JFormbttfdTfxtFifld</dodf> ibs bffn fditfd, tif
 * <dodf>AbstrbdtFormbttfrFbdtory</dodf> will not bf qufrifd until tif
 * vbluf ibs bffn dommittfd. Similbrly if tif fodus lost polidy is
 * <dodf>JFormbttfdTfxtFifld.COMMIT</dodf> bnd bn fxdfption
 * is tirown from <dodf>stringToVbluf</dodf>, tif
 * <dodf>AbstrbdtFormbttfrFbdtory</dodf> will not bf qufrifd wifn fodus is
 * lost or gbinfd.
 * <p>
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>
 * is blso rfsponsiblf for dftfrmining wifn vblufs brf dommittfd to
 * tif <dodf>JFormbttfdTfxtFifld</dodf>. Somf
 * <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>s will mbkf nfw vblufs
 * bvbilbblf on fvfry fdit, bnd otifrs will nfvfr dommit tif vbluf. You dbn
 * fordf tif durrfnt vbluf to bf obtbinfd
 * from tif durrfnt <dodf>JFormbttfdTfxtFifld.AbstrbdtFormbttfr</dodf>
 * by wby of invoking <dodf>dommitEdit</dodf>. <dodf>dommitEdit</dodf> will
 * bf invokfd wifnfvfr rfturn is prfssfd in tif
 * <dodf>JFormbttfdTfxtFifld</dodf>.
 * <p>
 * If bn <dodf>AbstrbdtFormbttfrFbdtory</dodf> ibs not bffn fxpliditly
 * sft, onf will bf sft bbsfd on tif <dodf>Clbss</dodf> of tif vbluf typf bftfr
 * <dodf>sftVbluf</dodf> ibs bffn invokfd (bssuming vbluf is non-null).
 * For fxbmplf, in tif following dodf bn bppropribtf
 * <dodf>AbstrbdtFormbttfrFbdtory</dodf> bnd <dodf>AbstrbdtFormbttfr</dodf>
 * will bf drfbtfd to ibndlf formbtting of numbfrs:
 * <prf>
 *   JFormbttfdTfxtFifld tf = nfw JFormbttfdTfxtFifld();
 *   tf.sftVbluf(nfw Numbfr(100));
 * </prf>
 * <p>
 * <strong>Wbrning:</strong> As tif <dodf>AbstrbdtFormbttfr</dodf> will
 * typidblly instbll b <dodf>DodumfntFiltfr</dodf> on tif
 * <dodf>Dodumfnt</dodf>, bnd b <dodf>NbvigbtionFiltfr</dodf> on tif
 * <dodf>JFormbttfdTfxtFifld</dodf> you siould not instbll your own. If you do,
 * you brf likfly to sff odd bfibvior in tibt tif fditing polidy of tif
 * <dodf>AbstrbdtFormbttfr</dodf> will not bf fnfordfd.
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JFormbttfdTfxtFifld fxtfnds JTfxtFifld {
    privbtf stbtid finbl String uiClbssID = "FormbttfdTfxtFifldUI";
    privbtf stbtid finbl Adtion[] dffbultAdtions =
            { nfw CommitAdtion(), nfw CbndflAdtion() };

    /**
     * Constbnt idfntifying tibt wifn fodus is lost,
     * <dodf>dommitEdit</dodf> siould bf invokfd. If in dommitting tif
     * nfw vbluf b <dodf>PbrsfExdfption</dodf> is tirown, tif invblid
     * vbluf will rfmbin.
     *
     * @sff #sftFodusLostBfibvior
     */
    publid stbtid finbl int COMMIT = 0;

    /**
     * Constbnt idfntifying tibt wifn fodus is lost,
     * <dodf>dommitEdit</dodf> siould bf invokfd. If in dommitting tif nfw
     * vbluf b <dodf>PbrsfExdfption</dodf> is tirown, tif vbluf will bf
     * rfvfrtfd.
     *
     * @sff #sftFodusLostBfibvior
     */
    publid stbtid finbl int COMMIT_OR_REVERT = 1;

    /**
     * Constbnt idfntifying tibt wifn fodus is lost, fditing vbluf siould
     * bf rfvfrtfd to durrfnt vbluf sft on tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     *
     * @sff #sftFodusLostBfibvior
     */
    publid stbtid finbl int REVERT = 2;

    /**
     * Constbnt idfntifying tibt wifn fodus is lost, tif fditfd vbluf
     * siould bf lfft.
     *
     * @sff #sftFodusLostBfibvior
     */
    publid stbtid finbl int PERSIST = 3;


    /**
     * Fbdtory usfd to obtbin bn instbndf of AbstrbdtFormbttfr.
     */
    privbtf AbstrbdtFormbttfrFbdtory fbdtory;
    /**
     * Objfdt rfsponsiblf for formbtting tif durrfnt vbluf.
     */
    privbtf AbstrbdtFormbttfr formbt;
    /**
     * Lbst vblid vbluf.
     */
    privbtf Objfdt vbluf;
    /**
     * Truf wiilf tif vbluf bfing fditfd is vblid.
     */
    privbtf boolfbn fditVblid;
    /**
     * Bfibvior wifn fodus is lost.
     */
    privbtf int fodusLostBfibvior;
    /**
     * Indidbtfs tif durrfnt vbluf ibs bffn fditfd.
     */
    privbtf boolfbn fditfd;
    /**
     * Usfd to sft tif dirty stbtf.
     */
    privbtf DodumfntListfnfr dodumfntListfnfr;
    /**
     * Mbskfd usfd to sft tif AbstrbdtFormbttfrFbdtory.
     */
    privbtf Objfdt mbsk;
    /**
     * AdtionMbp tibt tif TfxtFormbttfr Adtions brf bddfd to.
     */
    privbtf AdtionMbp tfxtFormbttfrAdtionMbp;
    /**
     * Indidbtfs tif input mftiod domposfd tfxt is in tif dodumfnt
     */
    privbtf boolfbn domposfdTfxtExists = fblsf;
    /**
     * A ibndlfr for FOCUS_LOST fvfnt
     */
    privbtf FodusLostHbndlfr fodusLostHbndlfr;


    /**
     * Crfbtfs b <dodf>JFormbttfdTfxtFifld</dodf> witi no
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf>. Usf <dodf>sftMbsk</dodf> or
     * <dodf>sftFormbttfrFbdtory</dodf> to donfigurf tif
     * <dodf>JFormbttfdTfxtFifld</dodf> to fdit b pbrtidulbr typf of
     * vbluf.
     */
    publid JFormbttfdTfxtFifld() {
        supfr();
        fnbblfEvfnts(AWTEvfnt.FOCUS_EVENT_MASK);
        sftFodusLostBfibvior(COMMIT_OR_REVERT);
    }

    /**
     * Crfbtfs b JFormbttfdTfxtFifld witi tif spfdififd vbluf. Tiis will
     * drfbtf bn <dodf>AbstrbdtFormbttfrFbdtory</dodf> bbsfd on tif
     * typf of <dodf>vbluf</dodf>.
     *
     * @pbrbm vbluf Initibl vbluf for tif JFormbttfdTfxtFifld
     */
    publid JFormbttfdTfxtFifld(Objfdt vbluf) {
        tiis();
        sftVbluf(vbluf);
    }

    /**
     * Crfbtfs b <dodf>JFormbttfdTfxtFifld</dodf>. <dodf>formbt</dodf> is
     * wrbppfd in bn bppropribtf <dodf>AbstrbdtFormbttfr</dodf> wiidi is
     * tifn wrbppfd in bn <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     *
     * @pbrbm formbt Formbt usfd to look up bn AbstrbdtFormbttfr
     */
    publid JFormbttfdTfxtFifld(jbvb.tfxt.Formbt formbt) {
        tiis();
        sftFormbttfrFbdtory(gftDffbultFormbttfrFbdtory(formbt));
    }

    /**
     * Crfbtfs b <dodf>JFormbttfdTfxtFifld</dodf> witi tif spfdififd
     * <dodf>AbstrbdtFormbttfr</dodf>. Tif <dodf>AbstrbdtFormbttfr</dodf>
     * is plbdfd in bn <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     *
     * @pbrbm formbttfr AbstrbdtFormbttfr to usf for formbtting.
     */
    publid JFormbttfdTfxtFifld(AbstrbdtFormbttfr formbttfr) {
        tiis(nfw DffbultFormbttfrFbdtory(formbttfr));
    }

    /**
     * Crfbtfs b <dodf>JFormbttfdTfxtFifld</dodf> witi tif spfdififd
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     *
     * @pbrbm fbdtory AbstrbdtFormbttfrFbdtory usfd for formbtting.
     */
    publid JFormbttfdTfxtFifld(AbstrbdtFormbttfrFbdtory fbdtory) {
        tiis();
        sftFormbttfrFbdtory(fbdtory);
    }

    /**
     * Crfbtfs b <dodf>JFormbttfdTfxtFifld</dodf> witi tif spfdififd
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> bnd initibl vbluf.
     *
     * @pbrbm fbdtory <dodf>AbstrbdtFormbttfrFbdtory</dodf> usfd for
     *        formbtting.
     * @pbrbm durrfntVbluf Initibl vbluf to usf
     */
    publid JFormbttfdTfxtFifld(AbstrbdtFormbttfrFbdtory fbdtory,
                               Objfdt durrfntVbluf) {
        tiis(durrfntVbluf);
        sftFormbttfrFbdtory(fbdtory);
    }

    /**
     * Sfts tif bfibvior wifn fodus is lost. Tiis will bf onf of
     * <dodf>JFormbttfdTfxtFifld.COMMIT_OR_REVERT</dodf>,
     * <dodf>JFormbttfdTfxtFifld.REVERT</dodf>,
     * <dodf>JFormbttfdTfxtFifld.COMMIT</dodf> or
     * <dodf>JFormbttfdTfxtFifld.PERSIST</dodf>
     * Notf tibt somf <dodf>AbstrbdtFormbttfr</dodf>s mby pusi dibngfs bs
     * tify oddur, so tibt tif vbluf of tiis will ibvf no ffffdt.
     * <p>
     * Tiis will tirow bn <dodf>IllfgblArgumfntExdfption</dodf> if tif objfdt
     * pbssfd in is not onf of tif bforf mfntionfd vblufs.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is
     * <dodf>JFormbttfdTfxtFifld.COMMIT_OR_REVERT</dodf>.
     *
     * @pbrbm bfibvior Idfntififs bfibvior wifn fodus is lost
     * @tirows IllfgblArgumfntExdfption if bfibvior is not onf of tif known
     *         vblufs
     * @bfbninfo
     *  fnum: COMMIT         JFormbttfdTfxtFifld.COMMIT
     *        COMMIT_OR_REVERT JFormbttfdTfxtFifld.COMMIT_OR_REVERT
     *        REVERT         JFormbttfdTfxtFifld.REVERT
     *        PERSIST        JFormbttfdTfxtFifld.PERSIST
     *  dfsdription: Bfibvior wifn domponfnt losfs fodus
     */
    publid void sftFodusLostBfibvior(int bfibvior) {
        if (bfibvior != COMMIT && bfibvior != COMMIT_OR_REVERT &&
            bfibvior != PERSIST && bfibvior != REVERT) {
            tirow nfw IllfgblArgumfntExdfption("sftFodusLostBfibvior must bf onf of: JFormbttfdTfxtFifld.COMMIT, JFormbttfdTfxtFifld.COMMIT_OR_REVERT, JFormbttfdTfxtFifld.PERSIST or JFormbttfdTfxtFifld.REVERT");
        }
        fodusLostBfibvior = bfibvior;
    }

    /**
     * Rfturns tif bfibvior wifn fodus is lost. Tiis will bf onf of
     * <dodf>COMMIT_OR_REVERT</dodf>,
     * <dodf>COMMIT</dodf>,
     * <dodf>REVERT</dodf> or
     * <dodf>PERSIST</dodf>
     * Notf tibt somf <dodf>AbstrbdtFormbttfr</dodf>s mby pusi dibngfs bs
     * tify oddur, so tibt tif vbluf of tiis will ibvf no ffffdt.
     *
     * @rfturn rfturns bfibvior wifn fodus is lost
     */
    publid int gftFodusLostBfibvior() {
        rfturn fodusLostBfibvior;
    }

    /**
     * Sfts tif <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> is
     * bblf to rfturn bn instbndf of <dodf>AbstrbdtFormbttfr</dodf> tibt is
     * usfd to formbt b vbluf for displby, bs wfll bn fnfording bn fditing
     * polidy.
     * <p>
     * If you ibvf not fxpliditly sft bn <dodf>AbstrbdtFormbttfrFbdtory</dodf>
     * by wby of tiis mftiod (or b donstrudtor) bn
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> bnd donsfqufntly bn
     * <dodf>AbstrbdtFormbttfr</dodf> will bf usfd bbsfd on tif
     * <dodf>Clbss</dodf> of tif vbluf. <dodf>NumbfrFormbttfr</dodf> will
     * bf usfd for <dodf>Numbfr</dodf>s, <dodf>DbtfFormbttfr</dodf> will
     * bf usfd for <dodf>Dbtfs</dodf>, otifrwisf <dodf>DffbultFormbttfr</dodf>
     * will bf usfd.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm tf <dodf>AbstrbdtFormbttfrFbdtory</dodf> usfd to lookup
     *          instbndfs of <dodf>AbstrbdtFormbttfr</dodf>
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: AbstrbdtFormbttfrFbdtory, rfsponsiblf for rfturning bn
     *              AbstrbdtFormbttfr tibt dbn formbt tif durrfnt vbluf.
     */
    publid void sftFormbttfrFbdtory(AbstrbdtFormbttfrFbdtory tf) {
        AbstrbdtFormbttfrFbdtory oldFbdtory = fbdtory;

        fbdtory = tf;
        firfPropfrtyCibngf("formbttfrFbdtory", oldFbdtory, tf);
        sftVbluf(gftVbluf(), truf, fblsf);
    }

    /**
     * Rfturns tif durrfnt <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     *
     * @sff #sftFormbttfrFbdtory
     * @rfturn <dodf>AbstrbdtFormbttfrFbdtory</dodf> usfd to dftfrminf
     *         <dodf>AbstrbdtFormbttfr</dodf>s
     */
    publid AbstrbdtFormbttfrFbdtory gftFormbttfrFbdtory() {
        rfturn fbdtory;
    }

    /**
     * Sfts tif durrfnt <dodf>AbstrbdtFormbttfr</dodf>.
     * <p>
     * You siould not normblly invokf tiis, instfbd sft tif
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> or sft tif vbluf.
     * <dodf>JFormbttfdTfxtFifld</dodf> will
     * invokf tiis bs tif stbtf of tif <dodf>JFormbttfdTfxtFifld</dodf>
     * dibngfs bnd rfquirfs tif vbluf to bf rfsft.
     * <dodf>JFormbttfdTfxtFifld</dodf> pbssfs in tif
     * <dodf>AbstrbdtFormbttfr</dodf> obtbinfd from tif
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf>.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @sff #sftFormbttfrFbdtory
     * @pbrbm formbt AbstrbdtFormbttfr to usf for formbtting
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: TfxtFormbttfr, rfsponsiblf for formbtting tif durrfnt vbluf
     */
    protfdtfd void sftFormbttfr(AbstrbdtFormbttfr formbt) {
        AbstrbdtFormbttfr oldFormbt = tiis.formbt;

        if (oldFormbt != null) {
            oldFormbt.uninstbll();
        }
        sftEditVblid(truf);
        tiis.formbt = formbt;
        if (formbt != null) {
            formbt.instbll(tiis);
        }
        sftEditfd(fblsf);
        firfPropfrtyCibngf("tfxtFormbttfr", oldFormbt, formbt);
    }

    /**
     * Rfturns tif <dodf>AbstrbdtFormbttfr</dodf> tibt is usfd to formbt bnd
     * pbrsf tif durrfnt vbluf.
     *
     * @rfturn AbstrbdtFormbttfr usfd for formbtting
     */
    publid AbstrbdtFormbttfr gftFormbttfr() {
        rfturn formbt;
    }

    /**
     * Sfts tif vbluf tibt will bf formbttfd by bn
     * <dodf>AbstrbdtFormbttfr</dodf> obtbinfd from tif durrfnt
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf>. If no
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> ibs bffn spfdififd, tiis will
     * bttfmpt to drfbtf onf bbsfd on tif typf of <dodf>vbluf</dodf>.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is null.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm vbluf Currfnt vbluf to displby
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif vbluf to bf formbttfd.
     */
    publid void sftVbluf(Objfdt vbluf) {
        if (vbluf != null && gftFormbttfrFbdtory() == null) {
            sftFormbttfrFbdtory(gftDffbultFormbttfrFbdtory(vbluf));
        }
        sftVbluf(vbluf, truf, truf);
    }

    /**
     * Rfturns tif lbst vblid vbluf. Bbsfd on tif fditing polidy of
     * tif <dodf>AbstrbdtFormbttfr</dodf> tiis mby not rfturn tif durrfnt
     * vbluf. Tif durrfntly fditfd vbluf dbn bf obtbinfd by invoking
     * <dodf>dommitEdit</dodf> followfd by <dodf>gftVbluf</dodf>.
     *
     * @rfturn Lbst vblid vbluf
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Fordfs tif durrfnt vbluf to bf tbkfn from tif
     * <dodf>AbstrbdtFormbttfr</dodf> bnd sft bs tif durrfnt vbluf.
     * Tiis ibs no ffffdt if tifrf is no durrfnt
     * <dodf>AbstrbdtFormbttfr</dodf> instbllfd.
     *
     * @tirows PbrsfExdfption if tif <dodf>AbstrbdtFormbttfr</dodf> is not bblf
     *         to formbt tif durrfnt vbluf
     */
    publid void dommitEdit() tirows PbrsfExdfption {
        AbstrbdtFormbttfr formbt = gftFormbttfr();

        if (formbt != null) {
            sftVbluf(formbt.stringToVbluf(gftTfxt()), fblsf, truf);
        }
    }

    /**
     * Sfts tif vblidity of tif fdit on tif rfdfivfr. You siould not normblly
     * invokf tiis. Tiis will bf invokfd by tif
     * <dodf>AbstrbdtFormbttfr</dodf> bs tif usfr fdits tif vbluf.
     * <p>
     * Not bll formbttfrs will bllow tif domponfnt to gft into bn invblid
     * stbtf, bnd tius tiis mby nfvfr bf invokfd.
     * <p>
     * Bbsfd on tif look bnd fffl tiis mby visublly dibngf tif stbtf of
     * tif rfdfivfr.
     *
     * @pbrbm isVblid boolfbn indidbting if tif durrfntly fditfd vbluf is
     *        vblid.
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Truf indidbtfs tif fditfd vbluf is vblid
     */
    privbtf void sftEditVblid(boolfbn isVblid) {
        if (isVblid != fditVblid) {
            fditVblid = isVblid;
            firfPropfrtyCibngf("fditVblid", Boolfbn.vblufOf(!isVblid),
                               Boolfbn.vblufOf(isVblid));
        }
    }

    /**
     * Rfturns truf if tif durrfnt vbluf bfing fditfd is vblid. Tif vbluf of
     * tiis is mbnbgfd by tif durrfnt <dodf>AbstrbdtFormbttfr</dodf>, bs sudi
     * tifrf is no publid sfttfr for it.
     *
     * @rfturn truf if tif durrfnt vbluf bfing fditfd is vblid.
     */
    publid boolfbn isEditVblid() {
        rfturn fditVblid;
    }

    /**
     * Invokfd wifn tif usfr inputs bn invblid vbluf. Tiis givfs tif
     * domponfnt b dibndf to providf fffdbbdk. Tif dffbult
     * implfmfntbtion bffps.
     */
    protfdtfd void invblidEdit() {
        UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JFormbttfdTfxtFifld.tiis);
    }

    /**
     * Prodfssfs bny input mftiod fvfnts, sudi bs
     * <dodf>InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED</dodf> or
     * <dodf>InputMftiodEvfnt.CARET_POSITION_CHANGED</dodf>.
     *
     * @pbrbm f tif <dodf>InputMftiodEvfnt</dodf>
     * @sff InputMftiodEvfnt
     */
    protfdtfd void prodfssInputMftiodEvfnt(InputMftiodEvfnt f) {
        AttributfdCibrbdtfrItfrbtor tfxt = f.gftTfxt();
        int dommitCount = f.gftCommittfdCibrbdtfrCount();

        // Kffp trbdk of tif domposfd tfxt
        if (tfxt != null) {
            int bfgin = tfxt.gftBfginIndfx();
            int fnd = tfxt.gftEndIndfx();
            domposfdTfxtExists = ((fnd - bfgin) > dommitCount);
        } flsf {
            domposfdTfxtExists = fblsf;
        }

        supfr.prodfssInputMftiodEvfnt(f);
    }

    /**
     * Prodfssfs bny fodus fvfnts, sudi bs
     * <dodf>FodusEvfnt.FOCUS_GAINED</dodf> or
     * <dodf>FodusEvfnt.FOCUS_LOST</dodf>.
     *
     * @pbrbm f tif <dodf>FodusEvfnt</dodf>
     * @sff FodusEvfnt
     */
    protfdtfd void prodfssFodusEvfnt(FodusEvfnt f) {
        supfr.prodfssFodusEvfnt(f);

        // ignorf tfmporbry fodus fvfnt
        if (f.isTfmporbry()) {
            rfturn;
        }

        if (isEditfd() && f.gftID() == FodusEvfnt.FOCUS_LOST) {
            InputContfxt id = gftInputContfxt();
            if (fodusLostHbndlfr == null) {
                fodusLostHbndlfr = nfw FodusLostHbndlfr();
            }

            // if tifrf is b domposfd tfxt, prodfss it first
            if ((id != null) && domposfdTfxtExists) {
                id.fndComposition();
                EvfntQufuf.invokfLbtfr(fodusLostHbndlfr);
            } flsf {
                fodusLostHbndlfr.run();
            }
        }
        flsf if (!isEditfd()) {
            // rfformbt
            sftVbluf(gftVbluf(), truf, truf);
        }
    }

    /**
     * FOCUS_LOST bfibvior implfmfntbtion
     */
    privbtf dlbss FodusLostHbndlfr implfmfnts Runnbblf, Sfriblizbblf {
        publid void run() {
            int fb = JFormbttfdTfxtFifld.tiis.gftFodusLostBfibvior();
            if (fb == JFormbttfdTfxtFifld.COMMIT ||
                fb == JFormbttfdTfxtFifld.COMMIT_OR_REVERT) {
                try {
                    JFormbttfdTfxtFifld.tiis.dommitEdit();
                    // Givf it b dibndf to rfformbt.
                    JFormbttfdTfxtFifld.tiis.sftVbluf(
                        JFormbttfdTfxtFifld.tiis.gftVbluf(), truf, truf);
                } dbtdi (PbrsfExdfption pf) {
                    if (fb == JFormbttfdTfxtFifld.COMMIT_OR_REVERT) {
                        JFormbttfdTfxtFifld.tiis.sftVbluf(
                            JFormbttfdTfxtFifld.tiis.gftVbluf(), truf, truf);
                    }
                }
            }
            flsf if (fb == JFormbttfdTfxtFifld.REVERT) {
                JFormbttfdTfxtFifld.tiis.sftVbluf(
                    JFormbttfdTfxtFifld.tiis.gftVbluf(), truf, truf);
            }
        }
    }

    /**
     * Fftdifs tif dommbnd list for tif fditor.  Tiis is
     * tif list of dommbnds supportfd by tif pluggfd-in UI
     * bugmfntfd by tif dollfdtion of dommbnds tibt tif
     * fditor itsflf supports.  Tifsf brf usfful for binding
     * to fvfnts, sudi bs in b kfymbp.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn TfxtAdtion.bugmfntList(supfr.gftAdtions(), dffbultAdtions);
    }

    /**
     * Gfts tif dlbss ID for b UI.
     *
     * @rfturn tif string "FormbttfdTfxtFifldUI"
     * @sff JComponfnt#gftUIClbssID
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Assodibtfs tif fditor witi b tfxt dodumfnt.
     * Tif durrfntly rfgistfrfd fbdtory is usfd to build b vifw for
     * tif dodumfnt, wiidi gfts displbyfd by tif fditor bftfr rfvblidbtion.
     * A PropfrtyCibngf fvfnt ("dodumfnt") is propbgbtfd to fbdi listfnfr.
     *
     * @pbrbm dod  tif dodumfnt to displby/fdit
     * @sff #gftDodumfnt
     * @bfbninfo
     *  dfsdription: tif tfxt dodumfnt modfl
     *        bound: truf
     *       fxpfrt: truf
     */
    publid void sftDodumfnt(Dodumfnt dod) {
        if (dodumfntListfnfr != null && gftDodumfnt() != null) {
            gftDodumfnt().rfmovfDodumfntListfnfr(dodumfntListfnfr);
        }
        supfr.sftDodumfnt(dod);
        if (dodumfntListfnfr == null) {
            dodumfntListfnfr = nfw DodumfntHbndlfr();
        }
        dod.bddDodumfntListfnfr(dodumfntListfnfr);
    }

    /*
     * Sff rfbdObjfdt bnd writfObjfdt in JComponfnt for morf
     * informbtion bbout sfriblizbtion in Swing.
     *
     * @pbrbm s Strfbm to writf to
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }

    /**
     * Rfsfts tif Adtions tibt domf from tif TfxtFormbttfr to
     * <dodf>bdtions</dodf>.
     */
    privbtf void sftFormbttfrAdtions(Adtion[] bdtions) {
        if (bdtions == null) {
            if (tfxtFormbttfrAdtionMbp != null) {
                tfxtFormbttfrAdtionMbp.dlfbr();
            }
        }
        flsf {
            if (tfxtFormbttfrAdtionMbp == null) {
                AdtionMbp mbp = gftAdtionMbp();

                tfxtFormbttfrAdtionMbp = nfw AdtionMbp();
                wiilf (mbp != null) {
                    AdtionMbp pbrfnt = mbp.gftPbrfnt();

                    if (pbrfnt instbndfof UIRfsourdf || pbrfnt == null) {
                        mbp.sftPbrfnt(tfxtFormbttfrAdtionMbp);
                        tfxtFormbttfrAdtionMbp.sftPbrfnt(pbrfnt);
                        brfbk;
                    }
                    mbp = pbrfnt;
                }
            }
            for (int dountfr = bdtions.lfngti - 1; dountfr >= 0;
                 dountfr--) {
                Objfdt kfy = bdtions[dountfr].gftVbluf(Adtion.NAME);

                if (kfy != null) {
                    tfxtFormbttfrAdtionMbp.put(kfy, bdtions[dountfr]);
                }
            }
        }
    }

    /**
     * Dofs tif sftting of tif vbluf. If <dodf>drfbtfFormbt</dodf> is truf,
     * tiis will blso obtbin b nfw <dodf>AbstrbdtFormbttfr</dodf> from tif
     * durrfnt fbdtory. Tif propfrty dibngf fvfnt will bf firfd if
     * <dodf>firfPC</dodf> is truf.
     */
    privbtf void sftVbluf(Objfdt vbluf, boolfbn drfbtfFormbt, boolfbn firfPC) {
        Objfdt oldVbluf = tiis.vbluf;

        tiis.vbluf = vbluf;

        if (drfbtfFormbt) {
            AbstrbdtFormbttfrFbdtory fbdtory = gftFormbttfrFbdtory();
            AbstrbdtFormbttfr btf;

            if (fbdtory != null) {
                btf = fbdtory.gftFormbttfr(tiis);
            }
            flsf {
                btf = null;
            }
            sftFormbttfr(btf);
        }
        flsf {
            // Assumfd to bf vblid
            sftEditVblid(truf);
        }

        sftEditfd(fblsf);

        if (firfPC) {
            firfPropfrtyCibngf("vbluf", oldVbluf, vbluf);
        }
    }

    /**
     * Sfts tif fditfd stbtf of tif rfdfivfr.
     */
    privbtf void sftEditfd(boolfbn fditfd) {
        tiis.fditfd = fditfd;
    }

    /**
     * Rfturns truf if tif rfdfivfr ibs bffn fditfd.
     */
    privbtf boolfbn isEditfd() {
        rfturn fditfd;
    }

    /**
     * Rfturns bn AbstrbdtFormbttfrFbdtory suitbblf for tif pbssfd in
     * Objfdt typf.
     */
    privbtf AbstrbdtFormbttfrFbdtory gftDffbultFormbttfrFbdtory(Objfdt typf) {
        if (typf instbndfof DbtfFormbt) {
            rfturn nfw DffbultFormbttfrFbdtory(nfw DbtfFormbttfr
                                               ((DbtfFormbt)typf));
        }
        if (typf instbndfof NumbfrFormbt) {
            rfturn nfw DffbultFormbttfrFbdtory(nfw NumbfrFormbttfr(
                                               (NumbfrFormbt)typf));
        }
        if (typf instbndfof Formbt) {
            rfturn nfw DffbultFormbttfrFbdtory(nfw IntfrnbtionblFormbttfr(
                                               (Formbt)typf));
        }
        if (typf instbndfof Dbtf) {
            rfturn nfw DffbultFormbttfrFbdtory(nfw DbtfFormbttfr());
        }
        if (typf instbndfof Numbfr) {
            AbstrbdtFormbttfr displbyFormbttfr = nfw NumbfrFormbttfr();
            ((NumbfrFormbttfr)displbyFormbttfr).sftVblufClbss(typf.gftClbss());
            AbstrbdtFormbttfr fditFormbttfr = nfw NumbfrFormbttfr(
                                  nfw DfdimblFormbt("#.#"));
            ((NumbfrFormbttfr)fditFormbttfr).sftVblufClbss(typf.gftClbss());

            rfturn nfw DffbultFormbttfrFbdtory(displbyFormbttfr,
                                               displbyFormbttfr,fditFormbttfr);
        }
        rfturn nfw DffbultFormbttfrFbdtory(nfw DffbultFormbttfr());
    }


    /**
     * Instbndfs of <dodf>AbstrbdtFormbttfrFbdtory</dodf> brf usfd by
     * <dodf>JFormbttfdTfxtFifld</dodf> to obtbin instbndfs of
     * <dodf>AbstrbdtFormbttfr</dodf> wiidi in turn brf usfd to formbt vblufs.
     * <dodf>AbstrbdtFormbttfrFbdtory</dodf> dbn rfturn difffrfnt
     * <dodf>AbstrbdtFormbttfr</dodf>s bbsfd on tif stbtf of tif
     * <dodf>JFormbttfdTfxtFifld</dodf>, pfribps rfturning difffrfnt
     * <dodf>AbstrbdtFormbttfr</dodf>s wifn tif
     * <dodf>JFormbttfdTfxtFifld</dodf> ibs fodus vs wifn it
     * dofsn't ibvf fodus.
     * @sindf 1.4
     */
    publid stbtid bbstrbdt dlbss AbstrbdtFormbttfrFbdtory {
        /**
         * Rfturns bn <dodf>AbstrbdtFormbttfr</dodf> tibt dbn ibndlf formbtting
         * of tif pbssfd in <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm tf JFormbttfdTfxtFifld rfqufsting AbstrbdtFormbttfr
         * @rfturn AbstrbdtFormbttfr to ibndlf formbtting dutifs, b null
         *         rfturn vbluf implifs tif JFormbttfdTfxtFifld siould bfibvf
         *         likf b normbl JTfxtFifld
         */
        publid bbstrbdt AbstrbdtFormbttfr gftFormbttfr(JFormbttfdTfxtFifld tf);
    }


    /**
     * Instbndfs of <dodf>AbstrbdtFormbttfr</dodf> brf usfd by
     * <dodf>JFormbttfdTfxtFifld</dodf> to ibndlf tif donvfrsion boti
     * from bn Objfdt to b String, bnd bbdk from b String to bn Objfdt.
     * <dodf>AbstrbdtFormbttfr</dodf>s dbn blso fnfordf fditing polidifs,
     * or nbvigbtion polidifs, or mbnipulbtf tif
     * <dodf>JFormbttfdTfxtFifld</dodf> in bny wby it sffs fit to
     * fnfordf tif dfsirfd polidy.
     * <p>
     * An <dodf>AbstrbdtFormbttfr</dodf> dbn only bf bdtivf in
     * onf <dodf>JFormbttfdTfxtFifld</dodf> bt b timf.
     * <dodf>JFormbttfdTfxtFifld</dodf> invokfs
     * <dodf>instbll</dodf> wifn it is rfbdy to usf it followfd
     * by <dodf>uninstbll</dodf> wifn donf. Subdlbssfs
     * tibt wisi to instbll bdditionbl stbtf siould ovfrridf
     * <dodf>instbll</dodf> bnd mfssbgf supfr bppropribtfly.
     * <p>
     * Subdlbssfs must ovfrridf tif donvfrsion mftiods
     * <dodf>stringToVbluf</dodf> bnd <dodf>vblufToString</dodf>. Optionblly
     * tify dbn ovfrridf <dodf>gftAdtions</dodf>,
     * <dodf>gftNbvigbtionFiltfr</dodf> bnd <dodf>gftDodumfntFiltfr</dodf>
     * to rfstridt tif <dodf>JFormbttfdTfxtFifld</dodf> in b pbrtidulbr
     * wby.
     * <p>
     * Subdlbssfs tibt bllow tif <dodf>JFormbttfdTfxtFifld</dodf> to bf in
     * b tfmporbrily invblid stbtf siould invokf <dodf>sftEditVblid</dodf>
     * bt tif bppropribtf timfs.
     * @sindf 1.4
     */
    publid stbtid bbstrbdt dlbss AbstrbdtFormbttfr implfmfnts Sfriblizbblf {
        privbtf JFormbttfdTfxtFifld ftf;

        /**
         * Instblls tif <dodf>AbstrbdtFormbttfr</dodf> onto b pbrtidulbr
         * <dodf>JFormbttfdTfxtFifld</dodf>.
         * Tiis will invokf <dodf>vblufToString</dodf> to donvfrt tif
         * durrfnt vbluf from tif <dodf>JFormbttfdTfxtFifld</dodf> to
         * b String. Tiis will tifn instbll tif <dodf>Adtion</dodf>s from
         * <dodf>gftAdtions</dodf>, tif <dodf>DodumfntFiltfr</dodf>
         * rfturnfd from <dodf>gftDodumfntFiltfr</dodf> bnd tif
         * <dodf>NbvigbtionFiltfr</dodf> rfturnfd from
         * <dodf>gftNbvigbtionFiltfr</dodf> onto tif
         * <dodf>JFormbttfdTfxtFifld</dodf>.
         * <p>
         * Subdlbssfs will typidblly only nffd to ovfrridf tiis if tify
         * wisi to instbll bdditionbl listfnfrs on tif
         * <dodf>JFormbttfdTfxtFifld</dodf>.
         * <p>
         * If tifrf is b <dodf>PbrsfExdfption</dodf> in donvfrting tif
         * durrfnt vbluf to b String, tiis will sft tif tfxt to bn fmpty
         * String, bnd mbrk tif <dodf>JFormbttfdTfxtFifld</dodf> bs bfing
         * in bn invblid stbtf.
         * <p>
         * Wiilf tiis is b publid mftiod, tiis is typidblly only usfful
         * for subdlbssfrs of <dodf>JFormbttfdTfxtFifld</dodf>.
         * <dodf>JFormbttfdTfxtFifld</dodf> will invokf tiis mftiod bt
         * tif bppropribtf timfs wifn tif vbluf dibngfs, or its intfrnbl
         * stbtf dibngfs.  You will only nffd to invokf tiis yoursflf if
         * you brf subdlbssing <dodf>JFormbttfdTfxtFifld</dodf> bnd
         * instblling/uninstblling <dodf>AbstrbdtFormbttfr</dodf> bt b
         * difffrfnt timf tibn <dodf>JFormbttfdTfxtFifld</dodf> dofs.
         *
         * @pbrbm ftf JFormbttfdTfxtFifld to formbt for, mby bf null indidbting
         *            uninstbll from durrfnt JFormbttfdTfxtFifld.
         */
        publid void instbll(JFormbttfdTfxtFifld ftf) {
            if (tiis.ftf != null) {
                uninstbll();
            }
            tiis.ftf = ftf;
            if (ftf != null) {
                try {
                    ftf.sftTfxt(vblufToString(ftf.gftVbluf()));
                } dbtdi (PbrsfExdfption pf) {
                    ftf.sftTfxt("");
                    sftEditVblid(fblsf);
                }
                instbllDodumfntFiltfr(gftDodumfntFiltfr());
                ftf.sftNbvigbtionFiltfr(gftNbvigbtionFiltfr());
                ftf.sftFormbttfrAdtions(gftAdtions());
            }
        }

        /**
         * Uninstblls bny stbtf tif <dodf>AbstrbdtFormbttfr</dodf> mby ibvf
         * instbllfd on tif <dodf>JFormbttfdTfxtFifld</dodf>. Tiis rfsfts tif
         * <dodf>DodumfntFiltfr</dodf>, <dodf>NbvigbtionFiltfr</dodf>
         * bnd bdditionbl <dodf>Adtion</dodf>s instbllfd on tif
         * <dodf>JFormbttfdTfxtFifld</dodf>.
         */
        publid void uninstbll() {
            if (tiis.ftf != null) {
                instbllDodumfntFiltfr(null);
                tiis.ftf.sftNbvigbtionFiltfr(null);
                tiis.ftf.sftFormbttfrAdtions(null);
            }
        }

        /**
         * Pbrsfs <dodf>tfxt</dodf> rfturning bn brbitrbry Objfdt. Somf
         * formbttfrs mby rfturn null.
         *
         * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
         * @pbrbm tfxt String to donvfrt
         * @rfturn Objfdt rfprfsfntbtion of tfxt
         */
        publid bbstrbdt Objfdt stringToVbluf(String tfxt) tirows
                                     PbrsfExdfption;

        /**
         * Rfturns tif string vbluf to displby for <dodf>vbluf</dodf>.
         *
         * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
         * @pbrbm vbluf Vbluf to donvfrt
         * @rfturn String rfprfsfntbtion of vbluf
         */
        publid bbstrbdt String vblufToString(Objfdt vbluf) tirows
                        PbrsfExdfption;

        /**
         * Rfturns tif durrfnt <dodf>JFormbttfdTfxtFifld</dodf> tif
         * <dodf>AbstrbdtFormbttfr</dodf> is instbllfd on.
         *
         * @rfturn JFormbttfdTfxtFifld formbtting for.
         */
        protfdtfd JFormbttfdTfxtFifld gftFormbttfdTfxtFifld() {
            rfturn ftf;
        }

        /**
         * Tiis siould bf invokfd wifn tif usfr typfs bn invblid dibrbdtfr.
         * Tiis forwbrds tif dbll to tif durrfnt JFormbttfdTfxtFifld.
         */
        protfdtfd void invblidEdit() {
            JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();

            if (ftf != null) {
                ftf.invblidEdit();
            }
        }

        /**
         * Invokf tiis to updbtf tif <dodf>fditVblid</dodf> propfrty of tif
         * <dodf>JFormbttfdTfxtFifld</dodf>. If you bn fnfordf b polidy
         * sudi tibt tif <dodf>JFormbttfdTfxtFifld</dodf> is blwbys in b
         * vblid stbtf, you will nfvfr nffd to invokf tiis.
         *
         * @pbrbm vblid Vblid stbtf of tif JFormbttfdTfxtFifld
         */
        protfdtfd void sftEditVblid(boolfbn vblid) {
            JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();

            if (ftf != null) {
                ftf.sftEditVblid(vblid);
            }
        }

        /**
         * Subdlbss bnd ovfrridf if you wisi to providf b dustom sft of
         * <dodf>Adtion</dodf>s. <dodf>instbll</dodf> will instbll tifsf
         * on tif <dodf>JFormbttfdTfxtFifld</dodf>'s <dodf>AdtionMbp</dodf>.
         *
         * @rfturn Arrby of Adtions to instbll on JFormbttfdTfxtFifld
         */
        protfdtfd Adtion[] gftAdtions() {
            rfturn null;
        }

        /**
         * Subdlbss bnd ovfrridf if you wisi to providf b
         * <dodf>DodumfntFiltfr</dodf> to rfstridt wibt dbn bf input.
         * <dodf>instbll</dodf> will instbll tif rfturnfd vbluf onto
         * tif <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @rfturn DodumfntFiltfr to rfstridt fdits
         */
        protfdtfd DodumfntFiltfr gftDodumfntFiltfr() {
            rfturn null;
        }

        /**
         * Subdlbss bnd ovfrridf if you wisi to providf b filtfr to rfstridt
         * wifrf tif usfr dbn nbvigbtf to.
         * <dodf>instbll</dodf> will instbll tif rfturnfd vbluf onto
         * tif <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @rfturn NbvigbtionFiltfr to rfstridt nbvigbtion
         */
        protfdtfd NbvigbtionFiltfr gftNbvigbtionFiltfr() {
            rfturn null;
        }

        /**
         * Clonfs tif <dodf>AbstrbdtFormbttfr</dodf>. Tif rfturnfd instbndf
         * is not bssodibtfd witi b <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @rfturn Copy of tif AbstrbdtFormbttfr
         */
        protfdtfd Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
            AbstrbdtFormbttfr formbttfr = (AbstrbdtFormbttfr)supfr.dlonf();

            formbttfr.ftf = null;
            rfturn formbttfr;
        }

        /**
         * Instblls tif <dodf>DodumfntFiltfr</dodf> <dodf>filtfr</dodf>
         * onto tif durrfnt <dodf>JFormbttfdTfxtFifld</dodf>.
         *
         * @pbrbm filtfr DodumfntFiltfr to instbll on tif Dodumfnt.
         */
        privbtf void instbllDodumfntFiltfr(DodumfntFiltfr filtfr) {
            JFormbttfdTfxtFifld ftf = gftFormbttfdTfxtFifld();

            if (ftf != null) {
                Dodumfnt dod = ftf.gftDodumfnt();

                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).sftDodumfntFiltfr(filtfr);
                }
                dod.putPropfrty(DodumfntFiltfr.dlbss, null);
            }
        }
    }


    /**
     * Usfd to dommit tif fdit. Tiis fxtfnds JTfxtFifld.NotifyAdtion
     * so tibt <dodf>isEnbblfd</dodf> is truf wiilf b JFormbttfdTfxtFifld
     * ibs fodus, bnd fxtfnds <dodf>bdtionPfrformfd</dodf> to invokf
     * dommitEdit.
     */
    stbtid dlbss CommitAdtion fxtfnds JTfxtFifld.NotifyAdtion {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();

            if (tbrgft instbndfof JFormbttfdTfxtFifld) {
                // Attfmpt to dommit tif vbluf
                try {
                    ((JFormbttfdTfxtFifld)tbrgft).dommitEdit();
                } dbtdi (PbrsfExdfption pf) {
                    ((JFormbttfdTfxtFifld)tbrgft).invblidEdit();
                    // vbluf not dommittfd, don't notify AdtionListfnfrs
                    rfturn;
                }
            }
            // Supfr bfibvior.
            supfr.bdtionPfrformfd(f);
        }

        publid boolfbn isEnbblfd() {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();
            if (tbrgft instbndfof JFormbttfdTfxtFifld) {
                JFormbttfdTfxtFifld ftf = (JFormbttfdTfxtFifld)tbrgft;
                if (!ftf.isEditfd()) {
                    rfturn fblsf;
                }
                rfturn truf;
            }
            rfturn supfr.isEnbblfd();
        }
    }


    /**
     * CbndflAdtion will rfsft tif vbluf in tif JFormbttfdTfxtFifld wifn
     * <dodf>bdtionPfrformfd</dodf> is invokfd. It will only bf
     * fnbblfd if tif fodusfd domponfnt is bn instbndf of
     * JFormbttfdTfxtFifld.
     */
    privbtf stbtid dlbss CbndflAdtion fxtfnds TfxtAdtion {
        publid CbndflAdtion() {
            supfr("rfsft-fifld-fdit");
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();

            if (tbrgft instbndfof JFormbttfdTfxtFifld) {
                JFormbttfdTfxtFifld ftf = (JFormbttfdTfxtFifld)tbrgft;
                ftf.sftVbluf(ftf.gftVbluf());
            }
        }

        publid boolfbn isEnbblfd() {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();
            if (tbrgft instbndfof JFormbttfdTfxtFifld) {
                JFormbttfdTfxtFifld ftf = (JFormbttfdTfxtFifld)tbrgft;
                if (!ftf.isEditfd()) {
                    rfturn fblsf;
                }
                rfturn truf;
            }
            rfturn supfr.isEnbblfd();
        }
    }


    /**
     * Sfts tif dirty stbtf bs tif dodumfnt dibngfs.
     */
    privbtf dlbss DodumfntHbndlfr implfmfnts DodumfntListfnfr, Sfriblizbblf {
        publid void insfrtUpdbtf(DodumfntEvfnt f) {
            sftEditfd(truf);
        }
        publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            sftEditfd(truf);
        }
        publid void dibngfdUpdbtf(DodumfntEvfnt f) {}
    }
}
