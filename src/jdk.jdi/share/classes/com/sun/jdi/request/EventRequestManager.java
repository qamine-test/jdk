/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.rfqufst;

import dom.sun.jdi.*;

import jbvb.util.List;

/**
 * Mbnbgfs tif drfbtion bnd dflftion of {@link EvfntRfqufst}s. A singlf
 * implfmfntor of tiis intfrfbdf fxists in b pbrtidubr VM bnd
 * is bddfssfd tirougi {@link VirtublMbdiinf#fvfntRfqufstMbnbgfr()}
 *
 * @sff EvfntRfqufst
 * @sff dom.sun.jdi.fvfnt.Evfnt
 * @sff BrfbkpointRfqufst
 * @sff dom.sun.jdi.fvfnt.BrfbkpointEvfnt
 * @sff VirtublMbdiinf
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */

@jdk.Exportfd
publid intfrfbdf EvfntRfqufstMbnbgfr fxtfnds Mirror {

    /**
     * Crfbtfs b nfw disbblfd {@link ClbssPrfpbrfRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link ClbssPrfpbrfRfqufst}
     */
    ClbssPrfpbrfRfqufst drfbtfClbssPrfpbrfRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link ClbssUnlobdRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link ClbssUnlobdRfqufst}
     */
    ClbssUnlobdRfqufst drfbtfClbssUnlobdRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link TirfbdStbrtRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link TirfbdStbrtRfqufst}
     */
    TirfbdStbrtRfqufst drfbtfTirfbdStbrtRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link TirfbdDfbtiRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link TirfbdDfbtiRfqufst}
     */
    TirfbdDfbtiRfqufst drfbtfTirfbdDfbtiRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link ExdfptionRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     * <P>
     * A spfdifid fxdfption typf bnd its subdlbssfs dbn bf sflfdtfd
     * for fxdfption fvfnts. Cbugit fxdfptions,  undbugit fxdfptions,
     * or boti dbn bf sflfdtfd. Notf, iowfvfr, tibt
     * bt tif timf bn fxdfption is tirown, it is not blwbys
     * possiblf to dftfrminf wiftifr it is truly dbugit. Sff
     * {@link dom.sun.jdi.fvfnt.ExdfptionEvfnt#dbtdiLodbtion} for
     * dftbils.
     * @pbrbm rffTypf If non-null, spfdififs tibt fxdfptions wiidi brf
     *                instbndfs of rffTypf will bf rfportfd. Notf: tiis
     *                will indludf instbndfs of sub-typfs.  If null,
     *                bll instbndfs will bf rfportfd
     * @pbrbm notifyCbugit If truf, dbugit fxdfptions will bf rfportfd.
     * @pbrbm notifyUndbugit If truf, undbugit fxdfptions will bf rfportfd.
     *
     * @rfturn tif drfbtfd {@link ExdfptionRfqufst}
     */
    ExdfptionRfqufst drfbtfExdfptionRfqufst(RfffrfndfTypf rffTypf,
                                            boolfbn notifyCbugit,
                                            boolfbn notifyUndbugit);

    /**
     * Crfbtfs b nfw disbblfd {@link MftiodEntryRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link MftiodEntryRfqufst}
     */
    MftiodEntryRfqufst drfbtfMftiodEntryRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link MftiodExitRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @rfturn tif drfbtfd {@link MftiodExitRfqufst}
     */
    MftiodExitRfqufst drfbtfMftiodExitRfqufst();

     /**
     * Crfbtfs b nfw disbblfd {@link MonitorContfndfdEntfrRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnRfqufstMonitorEvfnts()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif drfbtfd {@link MonitorContfndfdEntfrRfqufst}
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft VM dofs not support tiis
     * opfrbtion.
     *
     * @sindf 1.6
     */
    MonitorContfndfdEntfrRfqufst drfbtfMonitorContfndfdEntfrRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link MonitorContfndfdEntfrfdRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnRfqufstMonitorEvfnts()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif drfbtfd {@link MonitorContfndfdEntfrfdRfqufst}
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft VM dofs not support tiis
     * opfrbtion.
     *
     * @sindf 1.6
     */

    MonitorContfndfdEntfrfdRfqufst drfbtfMonitorContfndfdEntfrfdRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link MonitorWbitRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnRfqufstMonitorEvfnts()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif drfbtfd {@link MonitorWbitRfqufst}
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft VM dofs not support tiis
     * opfrbtion.
     *
     * @sindf 1.6
     */
    MonitorWbitRfqufst drfbtfMonitorWbitRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link MonitorWbitfdRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnRfqufstMonitorEvfnts()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif drfbtfd {@link MonitorWbitfdRfqufst}
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft VM dofs not support tiis
     * opfrbtion.
     *
     * @sindf 1.6
     */
    MonitorWbitfdRfqufst drfbtfMonitorWbitfdRfqufst();

    /**
     * Crfbtfs b nfw disbblfd {@link StfpRfqufst}.
     * Tif nfw fvfnt rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     * <p>
     * Tif rfturnfd rfqufst will dontrol stfpping only in tif spfdififd
     * <dodf>tirfbd</dodf>; bll otifr tirfbds will bf unbfffdtfd.
     * A <dodf>sizf</dodf>vbluf of {@link dom.sun.jdi.rfqufst.StfpRfqufst#STEP_MIN} will gfnfrbtf b
     * stfp fvfnt fbdi timf tif dodf indfx dibngfs. It rfprfsfnts tif
     * smbllfst stfp sizf bvbilbblf bnd oftfn mbps to tif instrudtion
     * lfvfl.
     * A <dodf>sizf</dodf> vbluf of {@link dom.sun.jdi.rfqufst.StfpRfqufst#STEP_LINE} will gfnfrbtf b
     * stfp fvfnt fbdi timf tif sourdf linf dibngfs unlfss linf numbfr informbtion is not bvbilbblf,
     * in wiidi dbsf b STEP_MIN will bf donf instfbd.  For fxbmplf, no linf numbfr informbtion is
     * bvbilbblf during tif fxfdution of b mftiod tibt ibs bffn rfndfrfd obsolftf by
     * by b {@link dom.sun.jdi.VirtublMbdiinf#rfdffinfClbssfs} opfrbtion.
     * A <dodf>dfpti</dodf> vbluf of {@link dom.sun.jdi.rfqufst.StfpRfqufst#STEP_INTO} will gfnfrbtf
     * stfp fvfnts in bny dbllfd mftiods.  A <dodf>dfpti</dodf> vbluf
     * of {@link dom.sun.jdi.rfqufst.StfpRfqufst#STEP_OVER} rfstridts stfp fvfnts to tif durrfnt frbmf
     * or dbllfr frbmfs. A <dodf>dfpti</dodf> vbluf of {@link dom.sun.jdi.rfqufst.StfpRfqufst#STEP_OUT}
     * rfstridts stfp fvfnts to dbllfr frbmfs only. All dfpti
     * rfstridtions brf rflbtivf to tif dbll stbdk immfdibtfly bfforf tif
     * stfp tbkfs plbdf.
     * <p>
     * Only onf pfnding stfp rfqufst is bllowfd pfr tirfbd.
     * <p>
     * Notf tibt b typidbl dfbuggfr will wbnt to dbndfl stfpping
     * bftfr tif first stfp is dftfdtfd.  Tius b nfxt linf mftiod
     * would do tif following:
     * <dodf>
     * <prf>
     *     EvfntRfqufstMbnbgfr mgr = myVM.{@link VirtublMbdiinf#fvfntRfqufstMbnbgfr fvfntRfqufstMbnbgfr}();
     *     StfpRfqufst rfqufst = mgr.drfbtfStfpRfqufst(myTirfbd,
     *                                                 StfpRfqufst.{@link StfpRfqufst#STEP_LINE STEP_LINE},
     *                                                 StfpRfqufst.{@link StfpRfqufst#STEP_OVER STEP_OVER});
     *     rfqufst.{@link EvfntRfqufst#bddCountFiltfr bddCountFiltfr}(1);  // nfxt stfp only
     *     rfqufst.fnbblf();
     *     myVM.{@link VirtublMbdiinf#rfsumf rfsumf}();
     * </prf>
     * </dodf>
     *
     * @pbrbm tirfbd tif tirfbd in wiidi to stfp
     * @pbrbm dfpti tif stfp dfpti
     * @pbrbm sizf tif stfp sizf
     * @rfturn tif drfbtfd {@link StfpRfqufst}
     * @tirows DuplidbtfRfqufstExdfption if tifrf is blrfbdy b pfnding
     * stfp rfqufst for tif spfdififd tirfbd.
     * @tirows IllfgblArgumfntExdfption if tif sizf or dfpti brgumfnts
     * dontbin illfgbl vblufs.
     */
    StfpRfqufst drfbtfStfpRfqufst(TirfbdRfffrfndf tirfbd,
                                  int sizf,
                                  int dfpti);

    /**
     * Crfbtfs b nfw disbblfd {@link BrfbkpointRfqufst}.
     * Tif givfn {@link Lodbtion} must ibvf b vblid
     * (tibt is, non-nfgbtivf) dodf indfx. Tif nfw
     * brfbkpoint is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Multiplf brfbkpoints bt tif
     * sbmf lodbtion brf pfrmittfd. Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     *
     * @pbrbm lodbtion tif lodbtion of tif nfw brfbkpoint.
     * @rfturn tif drfbtfd {@link BrfbkpointRfqufst}
     * @tirows NbtivfMftiodExdfption if lodbtion is witiin b nbtivf mftiod.
     */
    BrfbkpointRfqufst drfbtfBrfbkpointRfqufst(Lodbtion lodbtion);

    /**
     * Crfbtfs b nfw disbblfd wbtdipoint wiidi wbtdifs bddfssfs to tif
     * spfdififd fifld. Tif nfw
     * wbtdipoint is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Multiplf wbtdipoints on tif
     * sbmf fifld brf pfrmittfd.
     * Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     * <P>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnWbtdiFifldAddfss()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @pbrbm fifld tif fifld to wbtdi
     * @rfturn tif drfbtfd wbtdipoint
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     */
    AddfssWbtdipointRfqufst drfbtfAddfssWbtdipointRfqufst(Fifld fifld);

    /**
     * Crfbtfs b nfw disbblfd wbtdipoint wiidi wbtdifs bddfssfs to tif
     * spfdififd fifld. Tif nfw
     * wbtdipoint is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Multiplf wbtdipoints on tif
     * sbmf fifld brf pfrmittfd.
     * Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     * <P>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnWbtdiFifldModifidbtion()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @pbrbm fifld tif fifld to wbtdi
     * @rfturn tif drfbtfd wbtdipoint
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     */
    ModifidbtionWbtdipointRfqufst drfbtfModifidbtionWbtdipointRfqufst(Fifld fifld);

    /**
     * Crfbtfs b nfw disbblfd {@link VMDfbtiRfqufst}.
     * Tif nfw rfqufst is bddfd to tif list mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr.
     * Usf {@link EvfntRfqufst#fnbblf()} to
     * bdtivbtf tiis fvfnt rfqufst.
     * <P>
     * Tiis rfqufst (if fnbblfd) will dbusf b
     * {@link dom.sun.jdi.fvfnt.VMDfbtiEvfnt}
     * to bf sfnt on tfrminbtion of tif tbrgft VM.
     * <P>
     * A VMDfbtiRfqufst witi b suspfnd polidy of
     * {@link EvfntRfqufst#SUSPEND_ALL SUSPEND_ALL}
     * dbn bf usfd to bssurf prodfssing of indoming
     * {@link EvfntRfqufst#SUSPEND_NONE SUSPEND_NONE} or
     * {@link EvfntRfqufst#SUSPEND_EVENT_THREAD SUSPEND_EVENT_THREAD}
     * fvfnts bfforf VM dfbti.  If bll fvfnt prodfssing is bfing
     * donf in tif sbmf tirfbd bs fvfnt sfts brf bfing rfbd,
     * fnbbling tif rfqufst is bll tibt is nffdfd sindf tif VM
     * will bf suspfndfd until tif {@link dom.sun.jdi.fvfnt.EvfntSft}
     * dontbining tif {@link dom.sun.jdi.fvfnt.VMDfbtiEvfnt}
     * is rfsumfd.
     * <P>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnRfqufstVMDfbtiEvfnt()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif drfbtfd rfqufst
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft VM dofs not support tiis
     * opfrbtion.
     *
     * @sindf 1.4
     */
    VMDfbtiRfqufst drfbtfVMDfbtiRfqufst();

    /**
     * Rfmovfs bn fvfntRfqufst. Tif fvfntRfqufst is disbblfd bnd
     * tif rfmovfd from tif rfqufsts mbnbgfd by tiis
     * EvfntRfqufstMbnbgfr. Ondf tif fvfntRfqufst is dflftfd, no
     * opfrbtions (for fxbmplf, {@link EvfntRfqufst#sftEnbblfd})
     * brf pfrmittfd - bttfmpts to do so will gfnfrblly dbusf bn
     * {@link InvblidRfqufstStbtfExdfption}.
     * No otifr fvfntRfqufsts brf ffffdtfd.
     * <P>
     * Bfdbusf tiis mftiod dibngfs tif undfrlying lists of fvfnt
     * rfqufsts, bttfmpting to dirfdtly dflftf from b list rfturnfd
     * by b rfqufst bddfssor (f.g. bflow):
     * <PRE>
     *   Itfrbtor itfr = rfqufstMbnbgfr.stfpRfqufsts().itfrbtor();
     *   wiilf (itfr.ibsNfxt()) {
     *      rfqufstMbnbgfr.dflftfEvfntRfqufst(itfr.nfxt());
     *  }
     * </PRE>
     * mby dbusf b {@link jbvb.util.CondurrfntModifidbtionExdfption}.
     * Instfbd usf
     * {@link #dflftfEvfntRfqufsts(List) dflftfEvfntRfqufsts(List)}
     * or dopy tif list bfforf itfrbting.
     *
     * @pbrbm fvfntRfqufst tif fvfntRfqufst to rfmovf
     */
    void dflftfEvfntRfqufst(EvfntRfqufst fvfntRfqufst);

    /**
     * Rfmovfs b list of {@link EvfntRfqufst}s.
     *
     * @sff #dflftfEvfntRfqufst(EvfntRfqufst)
     *
     * @pbrbm fvfntRfqufsts tif list of fvfntRfqufsts to rfmovf
     */
    void dflftfEvfntRfqufsts(List<? fxtfnds EvfntRfqufst> fvfntRfqufsts);

    /**
     * Rfmovf bll brfbkpoints mbnbgfd by tiis EvfntRfqufstMbnbgfr.
     *
     * @sff #dflftfEvfntRfqufst(EvfntRfqufst)
     */
    void dflftfAllBrfbkpoints();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd stfp rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link StfpRfqufst} objfdts.
     */
    List<StfpRfqufst> stfpRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd dlbss prfpbrf rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link ClbssPrfpbrfRfqufst} objfdts.
     */
    List<ClbssPrfpbrfRfqufst> dlbssPrfpbrfRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd dlbss unlobd rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link ClbssUnlobdRfqufst} objfdts.
     */
    List<ClbssUnlobdRfqufst> dlbssUnlobdRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd tirfbd stbrt rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link TirfbdStbrtRfqufst} objfdts.
     */
    List<TirfbdStbrtRfqufst> tirfbdStbrtRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd tirfbd dfbti rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link TirfbdDfbtiRfqufst} objfdts.
     */
    List<TirfbdDfbtiRfqufst> tirfbdDfbtiRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd fxdfption rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link ExdfptionRfqufst} objfdts.
     */
    List<ExdfptionRfqufst> fxdfptionRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd brfbkpoint rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link BrfbkpointRfqufst} objfdts.
     */
    List<BrfbkpointRfqufst> brfbkpointRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd bddfss
     * wbtdipoint rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link AddfssWbtdipointRfqufst} objfdts.
     */
    List<AddfssWbtdipointRfqufst> bddfssWbtdipointRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd modifidbtion
     * wbtdipoint rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif bll {@link ModifidbtionWbtdipointRfqufst} objfdts.
     */
    List<ModifidbtionWbtdipointRfqufst> modifidbtionWbtdipointRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd mftiod fntry rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MftiodEntryRfqufst} objfdts.
     */
    List<MftiodEntryRfqufst> mftiodEntryRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd mftiod fxit rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MftiodExitRfqufst} objfdts.
     */
    List<MftiodExitRfqufst> mftiodExitRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd monitor dontfndfd fntfr rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MonitorContfndfdEntfrRfqufst} objfdts.
     *
     * @sindf 1.6
     */
    List<MonitorContfndfdEntfrRfqufst> monitorContfndfdEntfrRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd monitor dontfndfd fntfrfd rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MonitorContfndfdEntfrfdRfqufst} objfdts.
     *
     * @sindf 1.6
     */
    List<MonitorContfndfdEntfrfdRfqufst> monitorContfndfdEntfrfdRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd monitor wbit rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MonitorWbitRfqufst} objfdts.
     *
     * @sindf 1.6
     */
    List<MonitorWbitRfqufst> monitorWbitRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd monitor wbitfd rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * @rfturn tif list of bll {@link MonitorWbitfdRfqufst} objfdts.
     *
     * @sindf 1.6
     */
    List<MonitorWbitfdRfqufst> monitorWbitfdRfqufsts();

    /**
     * Rfturn bn unmodifibblf list of tif fnbblfd bnd disbblfd VM dfbti rfqufsts.
     * Tiis list is b livf vifw of tifsf rfqufsts bnd tius dibngfs bs rfqufsts
     * brf bddfd bnd dflftfd.
     * Notf: tif unsoliditfd VMDfbtiEvfnt dofs not ibvf b
     * dorrfsponding rfqufst.
     * @rfturn tif list of bll {@link VMDfbtiRfqufst} objfdts.
     *
     * @sindf 1.4
     */
    List<VMDfbtiRfqufst> vmDfbtiRfqufsts();
}
