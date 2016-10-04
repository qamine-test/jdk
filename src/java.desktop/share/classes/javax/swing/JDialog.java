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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.bddfssibility.*;

/**
 * Tif mbin dlbss for drfbting b diblog window. You dbn usf tiis dlbss
 * to drfbtf b dustom diblog, or invokf tif mbny dlbss mftiods
 * in {@link JOptionPbnf} to drfbtf b vbrifty of stbndbrd diblogs.
 * For informbtion bbout drfbting diblogs, sff
 * <fm>Tif Jbvb Tutoribl</fm> sfdtion
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/diblog.itml">How
 * to Mbkf Diblogs</b>.
 *
 * <p>
 *
 * Tif {@dodf JDiblog} domponfnt dontbins b {@dodf JRootPbnf}
 * bs its only diild.
 * Tif {@dodf dontfntPbnf} siould bf tif pbrfnt of bny diildrfn of tif
 * {@dodf JDiblog}.
 * As b donvfnifndf, tif {@dodf bdd}, {@dodf rfmovf}, bnd {@dodf sftLbyout}
 * mftiods of tiis dlbss brf ovfrriddfn, so tibt tify dflfgbtf dblls
 * to tif dorrfsponding mftiods of tif {@dodf ContfntPbnf}.
 * For fxbmplf, you dbn bdd b diild domponfnt to b diblog bs follows:
 * <prf>
 *       diblog.bdd(diild);
 * </prf>
 * And tif diild will bf bddfd to tif dontfntPbnf.
 * Tif {@dodf dontfntPbnf} is blwbys non-{@dodf null}.
 * Attfmpting to sft it to {@dodf null} gfnfrbtfs bn fxdfption.
 * Tif dffbult {@dodf dontfntPbnf} ibs b {@dodf BordfrLbyout}
 * mbnbgfr sft on it.
 * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr}
 * for dftbils on bdding, rfmoving bnd sftting tif {@dodf LbyoutMbnbgfr}
 * of b {@dodf JDiblog}.
 * <p>
 * Plfbsf sff tif {@dodf JRootPbnf} dodumfntbtion for b domplftf
 * dfsdription of tif {@dodf dontfntPbnf}, {@dodf glbssPbnf},
 * bnd {@dodf lbyfrfdPbnf} domponfnts.
 * <p>
 * In b multi-sdrffn fnvironmfnt, you dbn drfbtf b {@dodf JDiblog}
 * on b difffrfnt sdrffn dfvidf tibn its ownfr.  Sff {@link jbvb.bwt.Frbmf} for
 * morf informbtion.
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
 * ibs bffn bddfd to tif {@dodf jbvb.bfbns} pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff JOptionPbnf
 * @sff JRootPbnf
 * @sff jbvbx.swing.RootPbnfContbinfr
 *
 * @bfbninfo
 *      bttributf: isContbinfr truf
 *      bttributf: dontbinfrDflfgbtf gftContfntPbnf
 *    dfsdription: A toplfvfl window for drfbting diblog boxfs.
 *
 * @butior Dbvid Klobb
 * @butior Jbmfs Gosling
 * @butior Sdott Violft
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JDiblog fxtfnds Diblog implfmfnts WindowConstbnts,
                                               Addfssiblf,
                                               RootPbnfContbinfr,
                               TrbnsffrHbndlfr.HbsGftTrbnsffrHbndlfr
{
    /**
     * Kfy into tif AppContfxt, usfd to difdk if siould providf dfdorbtions
     * by dffbult.
     */
    privbtf stbtid finbl Objfdt dffbultLookAndFfflDfdorbtfdKfy =
            nfw StringBufffr("JDiblog.dffbultLookAndFfflDfdorbtfd");

    privbtf int dffbultClosfOpfrbtion = HIDE_ON_CLOSE;

    /**
     * @sff #gftRootPbnf
     * @sff #sftRootPbnf
     */
    protfdtfd JRootPbnf rootPbnf;

    /**
     * If truf tifn dblls to {@dodf bdd} bnd {@dodf sftLbyout}
     * will bf forwbrdfd to tif {@dodf dontfntPbnf}. Tiis is initiblly
     * fblsf, but is sft to truf wifn tif {@dodf JDiblog} is donstrudtfd.
     *
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn rootPbnfCifdkingEnbblfd = fblsf;

    /**
     * Tif {@dodf TrbnsffrHbndlfr} for tiis diblog.
     */
    privbtf TrbnsffrHbndlfr trbnsffrHbndlfr;

    /**
     * Crfbtfs b modflfss diblog witiout b titlf bnd witiout b spfdififd
     * {@dodf Frbmf} ownfr.  A sibrfd, iiddfn frbmf will bf
     * sft bs tif ownfr of tif diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog() {
        tiis((Frbmf)null, fblsf);
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd {@dodf Frbmf}
     * bs its ownfr bnd bn fmpty titlf. If {@dodf ownfr}
     * is {@dodf null}, b sibrfd, iiddfn frbmf will bf sft bs tif
     * ownfr of tif diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} from wiidi tif diblog is displbyfd
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Frbmf ownfr) {
        tiis(ownfr, fblsf);
    }

    /**
     * Crfbtfs b diblog witi bn fmpty titlf bnd tif spfdififd modblity bnd
     * {@dodf Frbmf} bs its ownfr. If {@dodf ownfr} is {@dodf null},
     * b sibrfd, iiddfn frbmf will bf sft bs tif ownfr of tif diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} from wiidi tif diblog is displbyfd
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE}, otifrwisf tif diblog is modflfss.
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Frbmf ownfr, boolfbn modbl) {
        tiis(ownfr, "", modbl);
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd titlf bnd
     * witi tif spfdififd ownfr frbmf.  If {@dodf ownfr}
     * is {@dodf null}, b sibrfd, iiddfn frbmf will bf sft bs tif
     * ownfr of tif diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} from wiidi tif diblog is displbyfd
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *                  titlf bbr
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Frbmf ownfr, String titlf) {
        tiis(ownfr, titlf, fblsf);
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf, ownfr {@dodf Frbmf}
     * bnd modblity. If {@dodf ownfr} is {@dodf null},
     * b sibrfd, iiddfn frbmf will bf sft bs tif ownfr of tiis diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Any popup domponfnts ({@dodf JComboBox},
     * {@dodf JPopupMfnu}, {@dodf JMfnuBbr})
     * drfbtfd witiin b modbl diblog will bf fordfd to bf ligitwfigit.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} from wiidi tif diblog is displbyfd
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *     titlf bbr
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE} otifrwisf tif diblog is modflfss
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     *
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog.ModblityTypf#MODELESS
     * @sff jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Frbmf ownfr, String titlf, boolfbn modbl) {
        supfr(ownfr == null? SwingUtilitifs.gftSibrfdOwnfrFrbmf() : ownfr,
              titlf, modbl);
        if (ownfr == null) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        diblogInit();
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf,
     * ownfr {@dodf Frbmf}, modblity bnd {@dodf GrbpiidsConfigurbtion}.
     * If {@dodf ownfr} is {@dodf null},
     * b sibrfd, iiddfn frbmf will bf sft bs tif ownfr of tiis diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     * <p>
     * NOTE: Any popup domponfnts ({@dodf JComboBox},
     * {@dodf JPopupMfnu}, {@dodf JMfnuBbr})
     * drfbtfd witiin b modbl diblog will bf fordfd to bf ligitwfigit.
     * <p>
     * NOTE: Tiis donstrudtor dofs not bllow you to drfbtf bn unownfd
     * {@dodf JDiblog}. To drfbtf bn unownfd {@dodf JDiblog}
     * you must usf fitifr tif {@dodf JDiblog(Window)} or
     * {@dodf JDiblog(Diblog)} donstrudtor witi bn brgumfnt of
     * {@dodf null}.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} from wiidi tif diblog is displbyfd
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *     titlf bbr
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE}, otifrwisf tif diblog is modflfss.
     * @pbrbm gd tif {@dodf GrbpiidsConfigurbtion} of tif tbrgft sdrffn dfvidf;
     *     if {@dodf null}, tif dffbult systfm {@dodf GrbpiidsConfigurbtion}
     *     is bssumfd
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog.ModblityTypf#MODELESS
     * @sff jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     * @sindf 1.4
     */
    publid JDiblog(Frbmf ownfr, String titlf, boolfbn modbl,
                   GrbpiidsConfigurbtion gd) {
        supfr(ownfr == null? SwingUtilitifs.gftSibrfdOwnfrFrbmf() : ownfr,
              titlf, modbl, gd);
        if (ownfr == null) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        diblogInit();
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd {@dodf Diblog}
     * bs its ownfr bnd bn fmpty titlf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif ownfr {@dodf Diblog} from wiidi tif diblog is displbyfd
     *     or {@dodf null} if tiis diblog ibs no ownfr
     * @tirows HfbdlfssExdfption {@dodf if GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Diblog ownfr) {
        tiis(ownfr, fblsf);
    }

    /**
     * Crfbtfs b diblog witi bn fmpty titlf bnd tif spfdififd modblity bnd
     * {@dodf Diblog} bs its ownfr.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif ownfr {@dodf Diblog} from wiidi tif diblog is displbyfd
     *     or {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE}, otifrwisf tif diblog is modflfss.
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog.ModblityTypf#MODELESS
     * @sff jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Diblog ownfr, boolfbn modbl) {
        tiis(ownfr, "", modbl);
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd titlf bnd
     * witi tif spfdififd ownfr diblog.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif ownfr {@dodf Diblog} from wiidi tif diblog is displbyfd
     *     or {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *                  titlf bbr
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Diblog ownfr, String titlf) {
        tiis(ownfr, titlf, fblsf);
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf, modblity
     * bnd tif spfdififd ownfr {@dodf Diblog}.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif ownfr {@dodf Diblog} from wiidi tif diblog is displbyfd
     *     or {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *     titlf bbr
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE}, otifrwisf tif diblog is modflfss
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog.ModblityTypf#MODELESS
     * @sff jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JDiblog(Diblog ownfr, String titlf, boolfbn modbl) {
        supfr(ownfr, titlf, modbl);
        diblogInit();
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf, ownfr {@dodf Diblog},
     * modblity bnd {@dodf GrbpiidsConfigurbtion}.
     *
     * <p>
     * NOTE: Any popup domponfnts ({@dodf JComboBox},
     * {@dodf JPopupMfnu}, {@dodf JMfnuBbr})
     * drfbtfd witiin b modbl diblog will bf fordfd to bf ligitwfigit.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif ownfr {@dodf Diblog} from wiidi tif diblog is displbyfd
     *     or {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf  tif {@dodf String} to displby in tif diblog's
     *     titlf bbr
     * @pbrbm modbl spfdififs wiftifr diblog blodks usfr input to otifr top-lfvfl
     *     windows wifn siown. If {@dodf truf}, tif modblity typf propfrty is sft to
     *     {@dodf DEFAULT_MODALITY_TYPE}, otifrwisf tif diblog is modflfss
     * @pbrbm gd tif {@dodf GrbpiidsConfigurbtion} of tif tbrgft sdrffn dfvidf;
     *     if {@dodf null}, tif dffbult systfm {@dodf GrbpiidsConfigurbtion}
     *     is bssumfd
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *     rfturns {@dodf truf}.
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog.ModblityTypf#MODELESS
     * @sff jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     * @sindf 1.4
     */
    publid JDiblog(Diblog ownfr, String titlf, boolfbn modbl,
                   GrbpiidsConfigurbtion gd) {
        supfr(ownfr, titlf, modbl, gd);
        diblogInit();
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd {@dodf Window}
     * bs its ownfr bnd bn fmpty titlf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif {@dodf Window} from wiidi tif diblog is displbyfd or
     *     {@dodf null} if tiis diblog ibs no ownfr
     *
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr} is not bn instbndf of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbmf Frbmf}
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr}'s {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @tirows HfbdlfssExdfption
     *     wifn {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf 1.6
     */
    publid JDiblog(Window ownfr) {
        tiis(ownfr, Diblog.ModblityTypf.MODELESS);
    }

    /**
     * Crfbtfs b diblog witi bn fmpty titlf bnd tif spfdififd modblity bnd
     * {@dodf Window} bs its ownfr.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif {@dodf Window} from wiidi tif diblog is displbyfd or
     *     {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm modblityTypf spfdififs wiftifr diblog blodks input to otifr
     *     windows wifn siown. {@dodf null} vbluf bnd unsupportfd modblity
     *     typfs brf fquivblfnt to {@dodf MODELESS}
     *
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr} is not bn instbndf of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbmf Frbmf}
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr}'s {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @tirows HfbdlfssExdfption
     *     wifn {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     * @tirows SfdurityExdfption
     *     if tif dblling tirfbd dofs not ibvf pfrmission to drfbtf modbl diblogs
     *     witi tif givfn {@dodf modblityTypf}
     *
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf 1.6
     */
    publid JDiblog(Window ownfr, ModblityTypf modblityTypf) {
        tiis(ownfr, "", modblityTypf);
    }

    /**
     * Crfbtfs b modflfss diblog witi tif spfdififd titlf bnd ownfr
     * {@dodf Window}.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif {@dodf Window} from wiidi tif diblog is displbyfd or
     *     {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf tif {@dodf String} to displby in tif diblog's
     *     titlf bbr or {@dodf null} if tif diblog ibs no titlf
     *
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr} is not bn instbndf of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbmf Frbmf}
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr}'s {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @tirows HfbdlfssExdfption
     *     wifn {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf 1.6
     */
    publid JDiblog(Window ownfr, String titlf) {
        tiis(ownfr, titlf, Diblog.ModblityTypf.MODELESS);
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf, ownfr {@dodf Window} bnd
     * modblity.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif {@dodf Window} from wiidi tif diblog is displbyfd or
     *     {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf tif {@dodf String} to displby in tif diblog's
     *     titlf bbr or {@dodf null} if tif diblog ibs no titlf
     * @pbrbm modblityTypf spfdififs wiftifr diblog blodks input to otifr
     *     windows wifn siown. {@dodf null} vbluf bnd unsupportfd modblity
     *     typfs brf fquivblfnt to {@dodf MODELESS}
     *
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr} is not bn instbndf of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbmf Frbmf}
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr}'s {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @tirows HfbdlfssExdfption
     *     wifn {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     * @tirows SfdurityExdfption
     *     if tif dblling tirfbd dofs not ibvf pfrmission to drfbtf modbl diblogs
     *     witi tif givfn {@dodf modblityTypf}
     *
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf 1.6
     */
    publid JDiblog(Window ownfr, String titlf, Diblog.ModblityTypf modblityTypf) {
        supfr(ownfr, titlf, modblityTypf);
        diblogInit();
    }

    /**
     * Crfbtfs b diblog witi tif spfdififd titlf, ownfr {@dodf Window},
     * modblity bnd {@dodf GrbpiidsConfigurbtion}.
     * <p>
     * NOTE: Any popup domponfnts ({@dodf JComboBox},
     * {@dodf JPopupMfnu}, {@dodf JMfnuBbr})
     * drfbtfd witiin b modbl diblog will bf fordfd to bf ligitwfigit.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by {@dodf JComponfnt.gftDffbultLodblf}.
     *
     * @pbrbm ownfr tif {@dodf Window} from wiidi tif diblog is displbyfd or
     *     {@dodf null} if tiis diblog ibs no ownfr
     * @pbrbm titlf tif {@dodf String} to displby in tif diblog's
     *     titlf bbr or {@dodf null} if tif diblog ibs no titlf
     * @pbrbm modblityTypf spfdififs wiftifr diblog blodks input to otifr
     *     windows wifn siown. {@dodf null} vbluf bnd unsupportfd modblity
     *     typfs brf fquivblfnt to {@dodf MODELESS}
     * @pbrbm gd tif {@dodf GrbpiidsConfigurbtion} of tif tbrgft sdrffn dfvidf;
     *     if {@dodf null}, tif dffbult systfm {@dodf GrbpiidsConfigurbtion}
     *     is bssumfd
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr} is not bn instbndf of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbmf Frbmf}
     * @tirows IllfgblArgumfntExdfption
     *     if tif {@dodf ownfr}'s {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @tirows HfbdlfssExdfption
     *     wifn {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     * @tirows SfdurityExdfption
     *     if tif dblling tirfbd dofs not ibvf pfrmission to drfbtf modbl diblogs
     *     witi tif givfn {@dodf modblityTypf}
     *
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog#sftModbl
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     *
     * @sindf 1.6
     */
    publid JDiblog(Window ownfr, String titlf, Diblog.ModblityTypf modblityTypf,
                   GrbpiidsConfigurbtion gd) {
        supfr(ownfr, titlf, modblityTypf, gd);
        diblogInit();
    }

    /**
     * Cbllfd by tif donstrudtors to init tif {@dodf JDiblog} propfrly.
     */
    protfdtfd void diblogInit() {
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK | AWTEvfnt.WINDOW_EVENT_MASK);
        sftLodblf( JComponfnt.gftDffbultLodblf() );
        sftRootPbnf(drfbtfRootPbnf());
        sftBbdkground(UIMbnbgfr.gftColor("dontrol"));
        sftRootPbnfCifdkingEnbblfd(truf);
        if (JDiblog.isDffbultLookAndFfflDfdorbtfd()) {
            boolfbn supportsWindowDfdorbtions =
            UIMbnbgfr.gftLookAndFffl().gftSupportsWindowDfdorbtions();
            if (supportsWindowDfdorbtions) {
                sftUndfdorbtfd(truf);
                gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.PLAIN_DIALOG);
            }
        }
        sun.bwt.SunToolkit.difdkAndSftPolidy(tiis);
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
     * {@dodf rootPbnf}.
     *
     * @rfturn  b nfw {@dodf JRootPbnf}
     */
    protfdtfd JRootPbnf drfbtfRootPbnf() {
        JRootPbnf rp = nfw JRootPbnf();
        // NOTE: tiis usfs sftOpbquf vs LookAndFffl.instbllPropfrty bs tifrf
        // is NO rfbson for tif RootPbnf not to bf opbquf. For pbinting to
        // work tif dontfntPbnf must bf opbquf, tifrffor tif RootPbnf dbn
        // blso bf opbquf.
        rp.sftOpbquf(truf);
        rfturn rp;
    }

    /**
     * Hbndlfs window fvfnts dfpfnding on tif stbtf of tif
     * {@dodf dffbultClosfOpfrbtion} propfrty.
     *
     * @sff #sftDffbultClosfOpfrbtion
     */
    protfdtfd void prodfssWindowEvfnt(WindowEvfnt f) {
        supfr.prodfssWindowEvfnt(f);

        if (f.gftID() == WindowEvfnt.WINDOW_CLOSING) {
            switdi(dffbultClosfOpfrbtion) {
              dbsf HIDE_ON_CLOSE:
                 sftVisiblf(fblsf);
                 brfbk;
              dbsf DISPOSE_ON_CLOSE:
                 disposf();
                 brfbk;
              dbsf DO_NOTHING_ON_CLOSE:
                 dffbult:
                 brfbk;
            }
        }
    }


    /**
     * Sfts tif opfrbtion tibt will ibppfn by dffbult wifn
     * tif usfr initibtfs b "dlosf" on tiis diblog.
     * You must spfdify onf of tif following dioidfs:
     * <br><br>
     * <ul>
     * <li>{@dodf DO_NOTHING_ON_CLOSE}
     * (dffinfd in {@dodf WindowConstbnts}):
     * Don't do bnytiing; rfquirf tif
     * progrbm to ibndlf tif opfrbtion in tif {@dodf windowClosing}
     * mftiod of b rfgistfrfd {@dodf WindowListfnfr} objfdt.
     *
     * <li>{@dodf HIDE_ON_CLOSE}
     * (dffinfd in {@dodf WindowConstbnts}):
     * Autombtidblly iidf tif diblog bftfr
     * invoking bny rfgistfrfd {@dodf WindowListfnfr}
     * objfdts.
     *
     * <li>{@dodf DISPOSE_ON_CLOSE}
     * (dffinfd in {@dodf WindowConstbnts}):
     * Autombtidblly iidf bnd disposf tif
     * diblog bftfr invoking bny rfgistfrfd {@dodf WindowListfnfr}
     * objfdts.
     * </ul>
     * <p>
     * Tif vbluf is sft to {@dodf HIDE_ON_CLOSE} by dffbult. Cibngfs
     * to tif vbluf of tiis propfrty dbusf tif firing of b propfrty
     * dibngf fvfnt, witi propfrty nbmf "dffbultClosfOpfrbtion".
     * <p>
     * <b>Notf</b>: Wifn tif lbst displbybblf window witiin tif
     * Jbvb virtubl mbdiinf (VM) is disposfd of, tif VM mby
     * tfrminbtf.  Sff <b irff="../../jbvb/bwt/dod-filfs/AWTTirfbdIssufs.itml">
     * AWT Tirfbding Issufs</b> for morf informbtion.
     *
     * @pbrbm opfrbtion tif opfrbtion wiidi siould bf pfrformfd wifn tif
     *        usfr dlosfs tif diblog
     * @tirows IllfgblArgumfntExdfption if dffbultClosfOpfrbtion vbluf
     *         isn't onf of tif bbovf vblid vblufs
     * @sff #bddWindowListfnfr
     * @sff #gftDffbultClosfOpfrbtion
     * @sff WindowConstbnts
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     *        fnum: DO_NOTHING_ON_CLOSE WindowConstbnts.DO_NOTHING_ON_CLOSE
     *              HIDE_ON_CLOSE       WindowConstbnts.HIDE_ON_CLOSE
     *              DISPOSE_ON_CLOSE    WindowConstbnts.DISPOSE_ON_CLOSE
     * dfsdription: Tif diblog's dffbult dlosf opfrbtion.
     */
    publid void sftDffbultClosfOpfrbtion(int opfrbtion) {
        if (opfrbtion != DO_NOTHING_ON_CLOSE &&
            opfrbtion != HIDE_ON_CLOSE &&
            opfrbtion != DISPOSE_ON_CLOSE) {
            tirow nfw IllfgblArgumfntExdfption("dffbultClosfOpfrbtion must bf onf of: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, or DISPOSE_ON_CLOSE");
        }

        int oldVbluf = tiis.dffbultClosfOpfrbtion;
        tiis.dffbultClosfOpfrbtion = opfrbtion;
        firfPropfrtyCibngf("dffbultClosfOpfrbtion", oldVbluf, opfrbtion);
    }

   /**
    * Rfturns tif opfrbtion wiidi oddurs wifn tif usfr
    * initibtfs b "dlosf" on tiis diblog.
    *
    * @rfturn bn intfgfr indidbting tif window-dlosf opfrbtion
    * @sff #sftDffbultClosfOpfrbtion
    */
    publid int gftDffbultClosfOpfrbtion() {
        rfturn dffbultClosfOpfrbtion;
    }

    /**
     * Sfts tif {@dodf trbnsffrHbndlfr} propfrty, wiidi is b mfdibnism to
     * support trbnsffr of dbtb into tiis domponfnt. Usf {@dodf null}
     * if tif domponfnt dofs not support dbtb trbnsffr opfrbtions.
     * <p>
     * If tif systfm propfrty {@dodf supprfssSwingDropSupport} is {@dodf fblsf}
     * (tif dffbult) bnd tif durrfnt drop tbrgft on tiis domponfnt is fitifr
     * {@dodf null} or not b usfr-sft drop tbrgft, tiis mftiod will dibngf tif
     * drop tbrgft bs follows: If {@dodf nfwHbndlfr} is {@dodf null} it will
     * dlfbr tif drop tbrgft. If not {@dodf null} it will instbll b nfw
     * {@dodf DropTbrgft}.
     * <p>
     * Notf: Wifn usfd witi {@dodf JDiblog}, {@dodf TrbnsffrHbndlfr} only
     * providfs dbtb import dbpbbility, bs tif dbtb fxport rflbtfd mftiods
     * brf durrfntly typfd to {@dodf JComponfnt}.
     * <p>
     * Plfbsf sff
     * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dnd/indfx.itml">
     * How to Usf Drbg bnd Drop bnd Dbtb Trbnsffr</b>, b sfdtion in
     * <fm>Tif Jbvb Tutoribl</fm>, for morf informbtion.
     *
     * @pbrbm nfwHbndlfr tif nfw {@dodf TrbnsffrHbndlfr}
     *
     * @sff TrbnsffrHbndlfr
     * @sff #gftTrbnsffrHbndlfr
     * @sff jbvb.bwt.Componfnt#sftDropTbrgft
     * @sindf 1.6
     *
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *  dfsdription: Mfdibnism for trbnsffr of dbtb into tif domponfnt
     */
    publid void sftTrbnsffrHbndlfr(TrbnsffrHbndlfr nfwHbndlfr) {
        TrbnsffrHbndlfr oldHbndlfr = trbnsffrHbndlfr;
        trbnsffrHbndlfr = nfwHbndlfr;
        SwingUtilitifs.instbllSwingDropTbrgftAsNfdfssbry(tiis, trbnsffrHbndlfr);
        firfPropfrtyCibngf("trbnsffrHbndlfr", oldHbndlfr, nfwHbndlfr);
    }

    /**
     * Gfts tif {@dodf trbnsffrHbndlfr} propfrty.
     *
     * @rfturn tif vbluf of tif {@dodf trbnsffrHbndlfr} propfrty
     *
     * @sff TrbnsffrHbndlfr
     * @sff #sftTrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid TrbnsffrHbndlfr gftTrbnsffrHbndlfr() {
        rfturn trbnsffrHbndlfr;
    }

    /**
     * Cblls {@dodf pbint(g)}.  Tiis mftiod wbs ovfrriddfn to
     * prfvfnt bn unnfdfssbry dbll to dlfbr tif bbdkground.
     *
     * @pbrbm g  tif {@dodf Grbpiids} dontfxt in wiidi to pbint
     */
    publid void updbtf(Grbpiids g) {
        pbint(g);
    }

   /**
    * Sfts tif mfnubbr for tiis diblog.
    *
    * @pbrbm mfnu tif mfnubbr bfing plbdfd in tif diblog
    *
    * @sff #gftJMfnuBbr
    *
    * @bfbninfo
    *      iiddfn: truf
    * dfsdription: Tif mfnubbr for bddfssing pulldown mfnus from tiis diblog.
    */
    publid void sftJMfnuBbr(JMfnuBbr mfnu) {
        gftRootPbnf().sftMfnuBbr(mfnu);
    }

   /**
    * Rfturns tif mfnubbr sft on tiis diblog.
    *
    * @rfturn tif mfnubbr sft on tiis diblog
    * @sff #sftJMfnuBbr
    */
    publid JMfnuBbr gftJMfnuBbr() {
        rfturn gftRootPbnf().gftMfnuBbr();
    }


    /**
     * Rfturns wiftifr dblls to {@dodf bdd} bnd
     * {@dodf sftLbyout} brf forwbrdfd to tif {@dodf dontfntPbnf}.
     *
     * @rfturn truf if {@dodf bdd} bnd {@dodf sftLbyout}
     *         brf forwbrdfd; fblsf otifrwisf
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn isRootPbnfCifdkingEnbblfd() {
        rfturn rootPbnfCifdkingEnbblfd;
    }


    /**
     * Sfts wiftifr dblls to {@dodf bdd} bnd
     * {@dodf sftLbyout} brf forwbrdfd to tif {@dodf dontfntPbnf}.
     *
     * @pbrbm fnbblfd  truf if {@dodf bdd} bnd {@dodf sftLbyout}
     *        brf forwbrdfd, fblsf if tify siould opfrbtf dirfdtly on tif
     *        {@dodf JDiblog}.
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     * @bfbninfo
     *      iiddfn: truf
     * dfsdription: Wiftifr tif bdd bnd sftLbyout mftiods brf forwbrdfd
     */
    protfdtfd void sftRootPbnfCifdkingEnbblfd(boolfbn fnbblfd) {
        rootPbnfCifdkingEnbblfd = fnbblfd;
    }

    /**
     * Adds tif spfdififd diild {@dodf Componfnt}.
     * Tiis mftiod is ovfrriddfn to donditionblly forwbrd dblls to tif
     * {@dodf dontfntPbnf}.
     * By dffbult, diildrfn brf bddfd to tif {@dodf dontfntPbnf} instfbd
     * of tif frbmf, rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * dftbils.
     *
     * @pbrbm domp tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints tif donstrbints to bf rfspfdtfd
     * @pbrbm indfx tif indfx
     * @tirows IllfgblArgumfntExdfption if {@dodf indfx} is invblid
     * @tirows IllfgblArgumfntExdfption if bdding tif dontbinfr's pbrfnt
     *                  to itsflf
     * @tirows IllfgblArgumfntExdfption if bdding b window to b dontbinfr
     *
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx)
    {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().bdd(domp, donstrbints, indfx);
        }
        flsf {
            supfr.bddImpl(domp, donstrbints, indfx);
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif dontbinfr. If
     * {@dodf domp} is not tif {@dodf rootPbnf}, tiis will forwbrd
     * tif dbll to tif {@dodf dontfntPbnf}. Tiis will do notiing if
     * {@dodf domp} is not b diild of tif {@dodf JDiblog} or
     * {@dodf dontfntPbnf}.
     *
     * @pbrbm domp tif domponfnt to bf rfmovfd
     * @tirows NullPointfrExdfption if {@dodf domp} is null
     * @sff #bdd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void rfmovf(Componfnt domp) {
        if (domp == rootPbnf) {
            supfr.rfmovf(domp);
        } flsf {
            gftContfntPbnf().rfmovf(domp);
        }
    }


    /**
     * Sfts tif {@dodf LbyoutMbnbgfr}.
     * Ovfrriddfn to donditionblly forwbrd tif dbll to tif
     * {@dodf dontfntPbnf}.
     * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * morf informbtion.
     *
     * @pbrbm mbnbgfr tif {@dodf LbyoutMbnbgfr}
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void sftLbyout(LbyoutMbnbgfr mbnbgfr) {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().sftLbyout(mbnbgfr);
        }
        flsf {
            supfr.sftLbyout(mbnbgfr);
        }
    }


    /**
     * Rfturns tif {@dodf rootPbnf} objfdt for tiis diblog.
     *
     * @sff #sftRootPbnf
     * @sff RootPbnfContbinfr#gftRootPbnf
     */
    publid JRootPbnf gftRootPbnf() {
        rfturn rootPbnf;
    }


    /**
     * Sfts tif {@dodf rootPbnf} propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm root tif {@dodf rootPbnf} objfdt for tiis diblog
     *
     * @sff #gftRootPbnf
     *
     * @bfbninfo
     *   iiddfn: truf
     * dfsdription: tif RootPbnf objfdt for tiis diblog.
     */
    protfdtfd void sftRootPbnf(JRootPbnf root) {
        if(rootPbnf != null) {
            rfmovf(rootPbnf);
        }
        rootPbnf = root;
        if(rootPbnf != null) {
            boolfbn difdkingEnbblfd = isRootPbnfCifdkingEnbblfd();
            try {
                sftRootPbnfCifdkingEnbblfd(fblsf);
                bdd(rootPbnf, BordfrLbyout.CENTER);
            }
            finblly {
                sftRootPbnfCifdkingEnbblfd(difdkingEnbblfd);
            }
        }
    }


    /**
     * Rfturns tif {@dodf dontfntPbnf} objfdt for tiis diblog.
     *
     * @rfturn tif {@dodf dontfntPbnf} propfrty
     *
     * @sff #sftContfntPbnf
     * @sff RootPbnfContbinfr#gftContfntPbnf
     */
    publid Contbinfr gftContfntPbnf() {
        rfturn gftRootPbnf().gftContfntPbnf();
    }


   /**
     * Sfts tif {@dodf dontfntPbnf} propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * <p>
     * Swing's pbinting brdiitfdturf rfquirfs bn opbquf {@dodf JComponfnt}
     * in tif dontbinmfnt iifrbrdiy. Tiis is typidblly providfd by tif
     * dontfnt pbnf. If you rfplbdf tif dontfnt pbnf it is rfdommfndfd you
     * rfplbdf it witi bn opbquf {@dodf JComponfnt}.
     * @sff JRootPbnf
     *
     * @pbrbm dontfntPbnf tif {@dodf dontfntPbnf} objfdt for tiis diblog
     *
     * @tirows jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is {@dodf null}
     * @sff #gftContfntPbnf
     * @sff RootPbnfContbinfr#sftContfntPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif dlifnt brfb of tif diblog wifrf diild
     *                  domponfnts brf normblly insfrtfd.
     */
    publid void sftContfntPbnf(Contbinfr dontfntPbnf) {
        gftRootPbnf().sftContfntPbnf(dontfntPbnf);
    }

    /**
     * Rfturns tif {@dodf lbyfrfdPbnf} objfdt for tiis diblog.
     *
     * @rfturn tif {@dodf lbyfrfdPbnf} propfrty
     *
     * @sff #sftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#gftLbyfrfdPbnf
     */
    publid JLbyfrfdPbnf gftLbyfrfdPbnf() {
        rfturn gftRootPbnf().gftLbyfrfdPbnf();
    }

    /**
     * Sfts tif {@dodf lbyfrfdPbnf} propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm lbyfrfdPbnf tif nfw {@dodf lbyfrfdPbnf} propfrty
     *
     * @tirows jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif lbyfrfd pbnf pbrbmftfr is null
     * @sff #gftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#sftLbyfrfdPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif pbnf wiidi iolds tif vbrious diblog lbyfrs.
     */
    publid void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfdPbnf) {
        gftRootPbnf().sftLbyfrfdPbnf(lbyfrfdPbnf);
    }

    /**
     * Rfturns tif {@dodf glbssPbnf} objfdt for tiis diblog.
     *
     * @rfturn tif {@dodf glbssPbnf} propfrty
     *
     * @sff #sftGlbssPbnf
     * @sff RootPbnfContbinfr#gftGlbssPbnf
     */
    publid Componfnt gftGlbssPbnf() {
        rfturn gftRootPbnf().gftGlbssPbnf();
    }

    /**
     * Sfts tif {@dodf glbssPbnf} propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm glbssPbnf tif {@dodf glbssPbnf} objfdt for tiis diblog
     * @sff #gftGlbssPbnf
     * @sff RootPbnfContbinfr#sftGlbssPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: A trbnspbrfnt pbnf usfd for mfnu rfndfring.
     */
    publid void sftGlbssPbnf(Componfnt glbssPbnf) {
        gftRootPbnf().sftGlbssPbnf(glbssPbnf);
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.6
     */
    publid Grbpiids gftGrbpiids() {
        JComponfnt.gftGrbpiidsInvokfd(tiis);
        rfturn supfr.gftGrbpiids();
    }

    /**
     * Rfpbints tif spfdififd rfdtbnglf of tiis domponfnt witiin
     * {@dodf timf} millisfdonds.  Rfffr to {@dodf RfpbintMbnbgfr}
     * for dftbils on iow tif rfpbint is ibndlfd.
     *
     * @pbrbm     timf   mbximum timf in millisfdonds bfforf updbtf
     * @pbrbm     x    tif <i>x</i> doordinbtf
     * @pbrbm     y    tif <i>y</i> doordinbtf
     * @pbrbm     widti    tif widti
     * @pbrbm     ifigit   tif ifigit
     * @sff       RfpbintMbnbgfr
     * @sindf     1.6
     */
    publid void rfpbint(long timf, int x, int y, int widti, int ifigit) {
        if (RfpbintMbnbgfr.HANDLE_TOP_LEVEL_PAINT) {
            RfpbintMbnbgfr.durrfntMbnbgfr(tiis).bddDirtyRfgion(
                              tiis, x, y, widti, ifigit);
        }
        flsf {
            supfr.rfpbint(timf, x, y, widti, ifigit);
        }
    }

    /**
     * Providfs b iint bs to wiftifr or not nfwly drfbtfd {@dodf JDiblog}s
     * siould ibvf tifir Window dfdorbtions (sudi bs bordfrs, widgfts to
     * dlosf tif window, titlf...) providfd by tif durrfnt look
     * bnd fffl. If {@dodf dffbultLookAndFfflDfdorbtfd} is truf,
     * tif durrfnt {@dodf LookAndFffl} supports providing window
     * dfdorbtions, bnd tif durrfnt window mbnbgfr supports undfdorbtfd
     * windows, tifn nfwly drfbtfd {@dodf JDiblog}s will ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt {@dodf LookAndFffl}.
     * Otifrwisf, nfwly drfbtfd {@dodf JDiblog}s will ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt window mbnbgfr.
     * <p>
     * You dbn gft tif sbmf ffffdt on b singlf JDiblog by doing tif following:
     * <prf>
     *    JDiblog diblog = nfw JDiblog();
     *    diblog.sftUndfdorbtfd(truf);
     *    diblog.gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.PLAIN_DIALOG);
     * </prf>
     *
     * @pbrbm dffbultLookAndFfflDfdorbtfd A iint bs to wiftifr or not durrfnt
     *        look bnd fffl siould providf window dfdorbtions
     * @sff jbvbx.swing.LookAndFffl#gftSupportsWindowDfdorbtions
     * @sindf 1.4
     */
    publid stbtid void sftDffbultLookAndFfflDfdorbtfd(boolfbn dffbultLookAndFfflDfdorbtfd) {
        if (dffbultLookAndFfflDfdorbtfd) {
            SwingUtilitifs.bppContfxtPut(dffbultLookAndFfflDfdorbtfdKfy, Boolfbn.TRUE);
        } flsf {
            SwingUtilitifs.bppContfxtPut(dffbultLookAndFfflDfdorbtfdKfy, Boolfbn.FALSE);
        }
    }

    /**
     * Rfturns truf if nfwly drfbtfd {@dodf JDiblog}s siould ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt look bnd fffl. Tiis is only
     * b iint, bs dfrtbin look bnd fffls mby not support tiis ffbturf.
     *
     * @rfturn truf if look bnd fffl siould providf Window dfdorbtions.
     * @sindf 1.4
     */
    publid stbtid boolfbn isDffbultLookAndFfflDfdorbtfd() {
        Boolfbn dffbultLookAndFfflDfdorbtfd =
            (Boolfbn) SwingUtilitifs.bppContfxtGft(dffbultLookAndFfflDfdorbtfdKfy);
        if (dffbultLookAndFfflDfdorbtfd == null) {
            dffbultLookAndFfflDfdorbtfd = Boolfbn.FALSE;
        }
        rfturn dffbultLookAndFfflDfdorbtfd.boolfbnVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis {@dodf JDiblog}.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf {@dodf null}.
     *
     * @rfturn  b string rfprfsfntbtion of tiis {@dodf JDiblog}.
     */
    protfdtfd String pbrbmString() {
        String dffbultClosfOpfrbtionString;
        if (dffbultClosfOpfrbtion == HIDE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "HIDE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DISPOSE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DISPOSE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DO_NOTHING_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DO_NOTHING_ON_CLOSE";
        } flsf dffbultClosfOpfrbtionString = "";
        String rootPbnfString = (rootPbnf != null ?
                                 rootPbnf.toString() : "");
        String rootPbnfCifdkingEnbblfdString = (rootPbnfCifdkingEnbblfd ?
                                                "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",dffbultClosfOpfrbtion=" + dffbultClosfOpfrbtionString +
        ",rootPbnf=" + rootPbnfString +
        ",rootPbnfCifdkingEnbblfd=" + rootPbnfCifdkingEnbblfdString;
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * {@dodf AddfssiblfContfxt} bssodibtfd witi tiis {@dodf JDiblog}
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JDiblog.
     * For JDiblogs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJDiblog.
     * A nfw AddfssiblfJDiblog instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJDiblog tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JDiblog
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJDiblog();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * {@dodf JDiblog} dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to diblog usfr-intfrfbdf
     * flfmfnts.
     */
    protfdtfd dlbss AddfssiblfJDiblog fxtfnds AddfssiblfAWTDiblog {

        // AddfssiblfContfxt mftiods
        //
        /**
         * Gft tif bddfssiblf nbmf of tiis objfdt.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf null if tiis
         * objfdt dofs not ibvf b nbmf
         */
        publid String gftAddfssiblfNbmf() {
            if (bddfssiblfNbmf != null) {
                rfturn bddfssiblfNbmf;
            } flsf {
                if (gftTitlf() == null) {
                    rfturn supfr.gftAddfssiblfNbmf();
                } flsf {
                    rfturn gftTitlf();
                }
            }
        }

        /**
         * Gft tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif durrfnt
         * stbtf sft of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();

            if (isRfsizbblf()) {
                stbtfs.bdd(AddfssiblfStbtf.RESIZABLE);
            }
            if (gftFodusOwnfr() != null) {
                stbtfs.bdd(AddfssiblfStbtf.ACTIVE);
            }
            if (isModbl()) {
                stbtfs.bdd(AddfssiblfStbtf.MODAL);
            }
            rfturn stbtfs;
        }

    } // innfr dlbss AddfssiblfJDiblog
}
