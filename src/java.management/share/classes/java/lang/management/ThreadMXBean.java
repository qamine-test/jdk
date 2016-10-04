/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;

import jbvb.util.Mbp;

/**
 * Tif mbnbgfmfnt intfrfbdf for tif tirfbd systfm of
 * tif Jbvb virtubl mbdiinf.
 *
 * <p> A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif implfmfntbtion
 * dlbss of tiis intfrfbdf.  Tiis instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftTirfbdMXBfbn} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * tif tirfbd systfm witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *    {@link MbnbgfmfntFbdtory#THREAD_MXBEAN_NAME
 *           <tt>jbvb.lbng:typf=Tirfbding</tt>}
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * <i3>Tirfbd ID</i3>
 * Tirfbd ID is b positivf long vbluf rfturnfd by dblling tif
 * {@link jbvb.lbng.Tirfbd#gftId} mftiod for b tirfbd.
 * Tif tirfbd ID is uniquf during its lifftimf.  Wifn b tirfbd
 * is tfrminbtfd, tiis tirfbd ID mby bf rfusfd.
 *
 * <p> Somf mftiods in tiis intfrfbdf tbkf b tirfbd ID or bn brrby
 * of tirfbd IDs bs tif input pbrbmftfr bnd rfturn pfr-tirfbd informbtion.
 *
 * <i3>Tirfbd CPU timf</i3>
 * A Jbvb virtubl mbdiinf implfmfntbtion mby support mfbsuring
 * tif CPU timf for tif durrfnt tirfbd, for bny tirfbd, or for no tirfbds.
 *
 * <p>
 * Tif {@link #isTirfbdCpuTimfSupportfd} mftiod dbn bf usfd to dftfrminf
 * if b Jbvb virtubl mbdiinf supports mfbsuring of tif CPU timf for bny
 * tirfbd.  Tif {@link #isCurrfntTirfbdCpuTimfSupportfd} mftiod dbn
 * bf usfd to dftfrminf if b Jbvb virtubl mbdiinf supports mfbsuring of
 * tif CPU timf for tif durrfnt  tirfbd.
 * A Jbvb virtubl mbdiinf implfmfntbtion tibt supports CPU timf mfbsurfmfnt
 * for bny tirfbd will blso support tibt for tif durrfnt tirfbd.
 *
 * <p> Tif CPU timf providfd by tiis intfrfbdf ibs nbnosfdond prfdision
 * but not nfdfssbrily nbnosfdond bddurbdy.
 *
 * <p>
 * A Jbvb virtubl mbdiinf mby disbblf CPU timf mfbsurfmfnt
 * by dffbult.
 * Tif {@link #isTirfbdCpuTimfEnbblfd} bnd {@link #sftTirfbdCpuTimfEnbblfd}
 * mftiods dbn bf usfd to tfst if CPU timf mfbsurfmfnt is fnbblfd
 * bnd to fnbblf/disbblf tiis support rfspfdtivfly.
 * Enbbling tirfbd CPU mfbsurfmfnt dould bf fxpfnsivf in somf
 * Jbvb virtubl mbdiinf implfmfntbtions.
 *
 * <i3>Tirfbd Contfntion Monitoring</i3>
 * Somf Jbvb virtubl mbdiinfs mby support tirfbd dontfntion monitoring.
 * Wifn tirfbd dontfntion monitoring is fnbblfd, tif bddumulbtfd flbpsfd
 * timf tibt tif tirfbd ibs blodkfd for syndironizbtion or wbitfd for
 * notifidbtion will bf dollfdtfd bnd rfturnfd in tif
 * <b irff="TirfbdInfo.itml#SyndStbts"><tt>TirfbdInfo</tt></b> objfdt.
 * <p>
 * Tif {@link #isTirfbdContfntionMonitoringSupportfd} mftiod dbn bf usfd to
 * dftfrminf if b Jbvb virtubl mbdiinf supports tirfbd dontfntion monitoring.
 * Tif tirfbd dontfntion monitoring is disbblfd by dffbult.  Tif
 * {@link #sftTirfbdContfntionMonitoringEnbblfd} mftiod dbn bf usfd to fnbblf
 * tirfbd dontfntion monitoring.
 *
 * <i3>Syndironizbtion Informbtion bnd Dfbdlodk Dftfdtion</i3>
 * Somf Jbvb virtubl mbdiinfs mby support monitoring of
 * {@linkplbin #isObjfdtMonitorUsbgfSupportfd objfdt monitor usbgf} bnd
 * {@linkplbin #isSyndironizfrUsbgfSupportfd ownbblf syndironizfr usbgf}.
 * Tif {@link #gftTirfbdInfo(long[], boolfbn, boolfbn)} bnd
 * {@link #dumpAllTirfbds} mftiods dbn bf usfd to obtbin tif tirfbd stbdk trbdf
 * bnd syndironizbtion informbtion indluding wiidi
 * {@linkplbin LodkInfo <i>lodk</i>} b tirfbd is blodkfd to
 * bdquirf or wbiting on bnd wiidi lodks tif tirfbd durrfntly owns.
 * <p>
 * Tif <tt>TirfbdMXBfbn</tt> intfrfbdf providfs tif
 * {@link #findMonitorDfbdlodkfdTirfbds} bnd
 * {@link #findDfbdlodkfdTirfbds} mftiods to find dfbdlodks in
 * tif running bpplidbtion.
 *
 * @sff MbnbgfmfntFbdtory#gftPlbtformMXBfbns(Clbss)
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion.</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss MXBfbns</b>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */

publid intfrfbdf TirfbdMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif durrfnt numbfr of livf tirfbds indluding boti
     * dbfmon bnd non-dbfmon tirfbds.
     *
     * @rfturn tif durrfnt numbfr of livf tirfbds.
     */
    publid int gftTirfbdCount();

    /**
     * Rfturns tif pfbk livf tirfbd dount sindf tif Jbvb virtubl mbdiinf
     * stbrtfd or pfbk wbs rfsft.
     *
     * @rfturn tif pfbk livf tirfbd dount.
     */
    publid int gftPfbkTirfbdCount();

    /**
     * Rfturns tif totbl numbfr of tirfbds drfbtfd bnd blso stbrtfd
     * sindf tif Jbvb virtubl mbdiinf stbrtfd.
     *
     * @rfturn tif totbl numbfr of tirfbds stbrtfd.
     */
    publid long gftTotblStbrtfdTirfbdCount();

    /**
     * Rfturns tif durrfnt numbfr of livf dbfmon tirfbds.
     *
     * @rfturn tif durrfnt numbfr of livf dbfmon tirfbds.
     */
    publid int gftDbfmonTirfbdCount();

    /**
     * Rfturns bll livf tirfbd IDs.
     * Somf tirfbds indludfd in tif rfturnfd brrby
     * mby ibvf bffn tfrminbtfd wifn tiis mftiod rfturns.
     *
     * @rfturn bn brrby of <tt>long</tt>, fbdi is b tirfbd ID.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     */
    publid long[] gftAllTirfbdIds();

    /**
     * Rfturns tif tirfbd info for b tirfbd of tif spfdififd
     * <tt>id</tt> witi no stbdk trbdf.
     * Tiis mftiod is fquivblfnt to dblling:
     * <blodkquotf>
     *   {@link #gftTirfbdInfo(long, int) gftTirfbdInfo(id, 0);}
     * </blodkquotf>
     *
     * <p>
     * Tiis mftiod rfturns b <tt>TirfbdInfo</tt> objfdt rfprfsfnting
     * tif tirfbd informbtion for tif tirfbd of tif spfdififd ID.
     * Tif stbdk trbdf, lodkfd monitors, bnd lodkfd syndironizfrs
     * in tif rfturnfd <tt>TirfbdInfo</tt> objfdt will
     * bf fmpty.
     *
     * If b tirfbd of tif givfn ID is not blivf or dofs not fxist,
     * tiis mftiod will rfturn <tt>null</tt>.  A tirfbd is blivf if
     * it ibs bffn stbrtfd bnd ibs not yft difd.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>TirfbdInfo</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in tif
     * {@link TirfbdInfo#from TirfbdInfo.from} mftiod.
     *
     * @pbrbm id tif tirfbd ID of tif tirfbd. Must bf positivf.
     *
     * @rfturn b {@link TirfbdInfo} objfdt for tif tirfbd of tif givfn ID
     * witi no stbdk trbdf, no lodkfd monitor bnd no syndironizfr info;
     * <tt>null</tt> if tif tirfbd of tif givfn ID is not blivf or
     * it dofs not fxist.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf id <= 0}.
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     */
    publid TirfbdInfo gftTirfbdInfo(long id);

    /**
     * Rfturns tif tirfbd info for fbdi tirfbd
     * wiosf ID is in tif input brrby <tt>ids</tt> witi no stbdk trbdf.
     * Tiis mftiod is fquivblfnt to dblling:
     * <blodkquotf><prf>
     *   {@link #gftTirfbdInfo(long[], int) gftTirfbdInfo}(ids, 0);
     * </prf></blodkquotf>
     *
     * <p>
     * Tiis mftiod rfturns bn brrby of tif <tt>TirfbdInfo</tt> objfdts.
     * Tif stbdk trbdf, lodkfd monitors, bnd lodkfd syndironizfrs
     * in fbdi <tt>TirfbdInfo</tt> objfdt will bf fmpty.
     *
     * If b tirfbd of b givfn ID is not blivf or dofs not fxist,
     * tif dorrfsponding flfmfnt in tif rfturnfd brrby will
     * dontbin <tt>null</tt>.  A tirfbd is blivf if
     * it ibs bffn stbrtfd bnd ibs not yft difd.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>TirfbdInfo</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in tif
     * {@link TirfbdInfo#from TirfbdInfo.from} mftiod.
     *
     * @pbrbm ids bn brrby of tirfbd IDs.
     * @rfturn bn brrby of tif {@link TirfbdInfo} objfdts, fbdi dontbining
     * informbtion bbout b tirfbd wiosf ID is in tif dorrfsponding
     * flfmfnt of tif input brrby of IDs
     * witi no stbdk trbdf, no lodkfd monitor bnd no syndironizfr info.
     *
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt in tif input brrby
     *         <tt>ids</tt> is {@dodf <= 0}.
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     */
    publid TirfbdInfo[] gftTirfbdInfo(long[] ids);

    /**
     * Rfturns b tirfbd info for b tirfbd of tif spfdififd <tt>id</tt>,
     * witi stbdk trbdf of b spfdififd numbfr of stbdk trbdf flfmfnts.
     * Tif <tt>mbxDfpti</tt> pbrbmftfr indidbtfs tif mbximum numbfr of
     * {@link StbdkTrbdfElfmfnt} to bf rftrifvfd from tif stbdk trbdf.
     * If <tt>mbxDfpti == Intfgfr.MAX_VALUE</tt>, tif fntirf stbdk trbdf of
     * tif tirfbd will bf dumpfd.
     * If <tt>mbxDfpti == 0</tt>, no stbdk trbdf of tif tirfbd
     * will bf dumpfd.
     * Tiis mftiod dofs not obtbin tif lodkfd monitors bnd lodkfd
     * syndironizfrs of tif tirfbd.
     * <p>
     * Wifn tif Jbvb virtubl mbdiinf ibs no stbdk trbdf informbtion
     * bbout b tirfbd or <tt>mbxDfpti == 0</tt>,
     * tif stbdk trbdf in tif
     * <tt>TirfbdInfo</tt> objfdt will bf bn fmpty brrby of
     * <tt>StbdkTrbdfElfmfnt</tt>.
     *
     * <p>
     * If b tirfbd of tif givfn ID is not blivf or dofs not fxist,
     * tiis mftiod will rfturn <tt>null</tt>.  A tirfbd is blivf if
     * it ibs bffn stbrtfd bnd ibs not yft difd.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>TirfbdInfo</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in tif
     * {@link TirfbdInfo#from TirfbdInfo.from} mftiod.
     *
     * @pbrbm id tif tirfbd ID of tif tirfbd. Must bf positivf.
     * @pbrbm mbxDfpti tif mbximum numbfr of fntrifs in tif stbdk trbdf
     * to bf dumpfd. <tt>Intfgfr.MAX_VALUE</tt> dould bf usfd to rfqufst
     * tif fntirf stbdk to bf dumpfd.
     *
     * @rfturn b {@link TirfbdInfo} of tif tirfbd of tif givfn ID
     * witi no lodkfd monitor bnd syndironizfr info.
     * <tt>null</tt> if tif tirfbd of tif givfn ID is not blivf or
     * it dofs not fxist.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf id <= 0}.
     * @tirows IllfgblArgumfntExdfption if <tt>mbxDfpti is nfgbtivf</tt>.
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     *
     */
    publid TirfbdInfo gftTirfbdInfo(long id, int mbxDfpti);

    /**
     * Rfturns tif tirfbd info for fbdi tirfbd
     * wiosf ID is in tif input brrby <tt>ids</tt>,
     * witi stbdk trbdf of b spfdififd numbfr of stbdk trbdf flfmfnts.
     * Tif <tt>mbxDfpti</tt> pbrbmftfr indidbtfs tif mbximum numbfr of
     * {@link StbdkTrbdfElfmfnt} to bf rftrifvfd from tif stbdk trbdf.
     * If <tt>mbxDfpti == Intfgfr.MAX_VALUE</tt>, tif fntirf stbdk trbdf of
     * tif tirfbd will bf dumpfd.
     * If <tt>mbxDfpti == 0</tt>, no stbdk trbdf of tif tirfbd
     * will bf dumpfd.
     * Tiis mftiod dofs not obtbin tif lodkfd monitors bnd lodkfd
     * syndironizfrs of tif tirfbds.
     * <p>
     * Wifn tif Jbvb virtubl mbdiinf ibs no stbdk trbdf informbtion
     * bbout b tirfbd or <tt>mbxDfpti == 0</tt>,
     * tif stbdk trbdf in tif
     * <tt>TirfbdInfo</tt> objfdt will bf bn fmpty brrby of
     * <tt>StbdkTrbdfElfmfnt</tt>.
     * <p>
     * Tiis mftiod rfturns bn brrby of tif <tt>TirfbdInfo</tt> objfdts,
     * fbdi is tif tirfbd informbtion bbout tif tirfbd witi tif sbmf indfx
     * bs in tif <tt>ids</tt> brrby.
     * If b tirfbd of tif givfn ID is not blivf or dofs not fxist,
     * <tt>null</tt> will bf sft in tif dorrfsponding flfmfnt
     * in tif rfturnfd brrby.  A tirfbd is blivf if
     * it ibs bffn stbrtfd bnd ibs not yft difd.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>TirfbdInfo</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in tif
     * {@link TirfbdInfo#from TirfbdInfo.from} mftiod.
     *
     * @pbrbm ids bn brrby of tirfbd IDs
     * @pbrbm mbxDfpti tif mbximum numbfr of fntrifs in tif stbdk trbdf
     * to bf dumpfd. <tt>Intfgfr.MAX_VALUE</tt> dould bf usfd to rfqufst
     * tif fntirf stbdk to bf dumpfd.
     *
     * @rfturn bn brrby of tif {@link TirfbdInfo} objfdts, fbdi dontbining
     * informbtion bbout b tirfbd wiosf ID is in tif dorrfsponding
     * flfmfnt of tif input brrby of IDs witi no lodkfd monitor bnd
     * syndironizfr info.
     *
     * @tirows IllfgblArgumfntExdfption if <tt>mbxDfpti is nfgbtivf</tt>.
     * @tirows IllfgblArgumfntExdfption if bny flfmfnt in tif input brrby
     *      <tt>ids</tt> is {@dodf <= 0}.
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     *
     */
    publid TirfbdInfo[] gftTirfbdInfo(long[] ids, int mbxDfpti);

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports tirfbd dontfntion monitoring.
     *
     * @rfturn
     *   <tt>truf</tt>
     *     if tif Jbvb virtubl mbdiinf supports tirfbd dontfntion monitoring;
     *   <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isTirfbdContfntionMonitoringSupportfd();

    /**
     * Tfsts if tirfbd dontfntion monitoring is fnbblfd.
     *
     * @rfturn <tt>truf</tt> if tirfbd dontfntion monitoring is fnbblfd;
     *         <tt>fblsf</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb virtubl
     * mbdiinf dofs not support tirfbd dontfntion monitoring.
     *
     * @sff #isTirfbdContfntionMonitoringSupportfd
     */
    publid boolfbn isTirfbdContfntionMonitoringEnbblfd();

    /**
     * Enbblfs or disbblfs tirfbd dontfntion monitoring.
     * Tirfbd dontfntion monitoring is disbblfd by dffbult.
     *
     * @pbrbm fnbblf <tt>truf</tt> to fnbblf;
     *               <tt>fblsf</tt> to disbblf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support tirfbd dontfntion monitoring.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     *
     * @sff #isTirfbdContfntionMonitoringSupportfd
     */
    publid void sftTirfbdContfntionMonitoringEnbblfd(boolfbn fnbblf);

    /**
     * Rfturns tif totbl CPU timf for tif durrfnt tirfbd in nbnosfdonds.
     * Tif rfturnfd vbluf is of nbnosfdonds prfdision but
     * not nfdfssbrily nbnosfdonds bddurbdy.
     * If tif implfmfntbtion distinguisifs bftwffn usfr modf timf bnd systfm
     * modf timf, tif rfturnfd CPU timf is tif bmount of timf tibt
     * tif durrfnt tirfbd ibs fxfdutfd in usfr modf or systfm modf.
     *
     * <p>
     * Tiis is b donvfnifnt mftiod for lodbl mbnbgfmfnt usf bnd is
     * fquivblfnt to dblling:
     * <blodkquotf><prf>
     *   {@link #gftTirfbdCpuTimf gftTirfbdCpuTimf}(Tirfbd.durrfntTirfbd().gftId());
     * </prf></blodkquotf>
     *
     * @rfturn tif totbl CPU timf for tif durrfnt tirfbd if CPU timf
     * mfbsurfmfnt is fnbblfd; <tt>-1</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support CPU timf mfbsurfmfnt for
     * tif durrfnt tirfbd.
     *
     * @sff #gftCurrfntTirfbdUsfrTimf
     * @sff #isCurrfntTirfbdCpuTimfSupportfd
     * @sff #isTirfbdCpuTimfEnbblfd
     * @sff #sftTirfbdCpuTimfEnbblfd
     */
    publid long gftCurrfntTirfbdCpuTimf();

    /**
     * Rfturns tif CPU timf tibt tif durrfnt tirfbd ibs fxfdutfd
     * in usfr modf in nbnosfdonds.
     * Tif rfturnfd vbluf is of nbnosfdonds prfdision but
     * not nfdfssbrily nbnosfdonds bddurbdy.
     *
     * <p>
     * Tiis is b donvfnifnt mftiod for lodbl mbnbgfmfnt usf bnd is
     * fquivblfnt to dblling:
     * <blodkquotf><prf>
     *   {@link #gftTirfbdUsfrTimf gftTirfbdUsfrTimf}(Tirfbd.durrfntTirfbd().gftId());
     * </prf></blodkquotf>
     *
     * @rfturn tif usfr-lfvfl CPU timf for tif durrfnt tirfbd if CPU timf
     * mfbsurfmfnt is fnbblfd; <tt>-1</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support CPU timf mfbsurfmfnt for
     * tif durrfnt tirfbd.
     *
     * @sff #gftCurrfntTirfbdCpuTimf
     * @sff #isCurrfntTirfbdCpuTimfSupportfd
     * @sff #isTirfbdCpuTimfEnbblfd
     * @sff #sftTirfbdCpuTimfEnbblfd
     */
    publid long gftCurrfntTirfbdUsfrTimf();

    /**
     * Rfturns tif totbl CPU timf for b tirfbd of tif spfdififd ID in nbnosfdonds.
     * Tif rfturnfd vbluf is of nbnosfdonds prfdision but
     * not nfdfssbrily nbnosfdonds bddurbdy.
     * If tif implfmfntbtion distinguisifs bftwffn usfr modf timf bnd systfm
     * modf timf, tif rfturnfd CPU timf is tif bmount of timf tibt
     * tif tirfbd ibs fxfdutfd in usfr modf or systfm modf.
     *
     * <p>
     * If tif tirfbd of tif spfdififd ID is not blivf or dofs not fxist,
     * tiis mftiod rfturns <tt>-1</tt>. If CPU timf mfbsurfmfnt
     * is disbblfd, tiis mftiod rfturns <tt>-1</tt>.
     * A tirfbd is blivf if it ibs bffn stbrtfd bnd ibs not yft difd.
     * <p>
     * If CPU timf mfbsurfmfnt is fnbblfd bftfr tif tirfbd ibs stbrtfd,
     * tif Jbvb virtubl mbdiinf implfmfntbtion mby dioosf bny timf up to
     * bnd indluding tif timf tibt tif dbpbbility is fnbblfd bs tif point
     * wifrf CPU timf mfbsurfmfnt stbrts.
     *
     * @pbrbm id tif tirfbd ID of b tirfbd
     * @rfturn tif totbl CPU timf for b tirfbd of tif spfdififd ID
     * if tif tirfbd of tif spfdififd ID fxists, tif tirfbd is blivf,
     * bnd CPU timf mfbsurfmfnt is fnbblfd;
     * <tt>-1</tt> otifrwisf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf id <= 0}.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support CPU timf mfbsurfmfnt for
     * otifr tirfbds.
     *
     * @sff #gftTirfbdUsfrTimf
     * @sff #isTirfbdCpuTimfSupportfd
     * @sff #isTirfbdCpuTimfEnbblfd
     * @sff #sftTirfbdCpuTimfEnbblfd
     */
    publid long gftTirfbdCpuTimf(long id);

    /**
     * Rfturns tif CPU timf tibt b tirfbd of tif spfdififd ID
     * ibs fxfdutfd in usfr modf in nbnosfdonds.
     * Tif rfturnfd vbluf is of nbnosfdonds prfdision but
     * not nfdfssbrily nbnosfdonds bddurbdy.
     *
     * <p>
     * If tif tirfbd of tif spfdififd ID is not blivf or dofs not fxist,
     * tiis mftiod rfturns <tt>-1</tt>. If CPU timf mfbsurfmfnt
     * is disbblfd, tiis mftiod rfturns <tt>-1</tt>.
     * A tirfbd is blivf if it ibs bffn stbrtfd bnd ibs not yft difd.
     * <p>
     * If CPU timf mfbsurfmfnt is fnbblfd bftfr tif tirfbd ibs stbrtfd,
     * tif Jbvb virtubl mbdiinf implfmfntbtion mby dioosf bny timf up to
     * bnd indluding tif timf tibt tif dbpbbility is fnbblfd bs tif point
     * wifrf CPU timf mfbsurfmfnt stbrts.
     *
     * @pbrbm id tif tirfbd ID of b tirfbd
     * @rfturn tif usfr-lfvfl CPU timf for b tirfbd of tif spfdififd ID
     * if tif tirfbd of tif spfdififd ID fxists, tif tirfbd is blivf,
     * bnd CPU timf mfbsurfmfnt is fnbblfd;
     * <tt>-1</tt> otifrwisf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf id <= 0}.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support CPU timf mfbsurfmfnt for
     * otifr tirfbds.
     *
     * @sff #gftTirfbdCpuTimf
     * @sff #isTirfbdCpuTimfSupportfd
     * @sff #isTirfbdCpuTimfEnbblfd
     * @sff #sftTirfbdCpuTimfEnbblfd
     */
    publid long gftTirfbdUsfrTimf(long id);

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf implfmfntbtion supports CPU timf
     * mfbsurfmfnt for bny tirfbd.
     * A Jbvb virtubl mbdiinf implfmfntbtion tibt supports CPU timf
     * mfbsurfmfnt for bny tirfbd will blso support CPU timf
     * mfbsurfmfnt for tif durrfnt tirfbd.
     *
     * @rfturn
     *   <tt>truf</tt>
     *     if tif Jbvb virtubl mbdiinf supports CPU timf
     *     mfbsurfmfnt for bny tirfbd;
     *   <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isTirfbdCpuTimfSupportfd();

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports CPU timf
     * mfbsurfmfnt for tif durrfnt tirfbd.
     * Tiis mftiod rfturns <tt>truf</tt> if {@link #isTirfbdCpuTimfSupportfd}
     * rfturns <tt>truf</tt>.
     *
     * @rfturn
     *   <tt>truf</tt>
     *     if tif Jbvb virtubl mbdiinf supports CPU timf
     *     mfbsurfmfnt for durrfnt tirfbd;
     *   <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isCurrfntTirfbdCpuTimfSupportfd();

    /**
     * Tfsts if tirfbd CPU timf mfbsurfmfnt is fnbblfd.
     *
     * @rfturn <tt>truf</tt> if tirfbd CPU timf mfbsurfmfnt is fnbblfd;
     *         <tt>fblsf</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb virtubl
     * mbdiinf dofs not support CPU timf mfbsurfmfnt for otifr tirfbds
     * nor for tif durrfnt tirfbd.
     *
     * @sff #isTirfbdCpuTimfSupportfd
     * @sff #isCurrfntTirfbdCpuTimfSupportfd
     */
    publid boolfbn isTirfbdCpuTimfEnbblfd();

    /**
     * Enbblfs or disbblfs tirfbd CPU timf mfbsurfmfnt.  Tif dffbult
     * is plbtform dfpfndfnt.
     *
     * @pbrbm fnbblf <tt>truf</tt> to fnbblf;
     *               <tt>fblsf</tt> to disbblf.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support CPU timf mfbsurfmfnt for
     * bny tirfbds nor for tif durrfnt tirfbd.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     *
     * @sff #isTirfbdCpuTimfSupportfd
     * @sff #isCurrfntTirfbdCpuTimfSupportfd
     */
    publid void sftTirfbdCpuTimfEnbblfd(boolfbn fnbblf);

    /**
     * Finds dydlfs of tirfbds tibt brf in dfbdlodk wbiting to bdquirf
     * objfdt monitors. Tibt is, tirfbds tibt brf blodkfd wbiting to fntfr b
     * syndironizbtion blodk or wbiting to rffntfr b syndironizbtion blodk
     * bftfr bn {@link Objfdt#wbit Objfdt.wbit} dbll,
     * wifrf fbdi tirfbd owns onf monitor wiilf
     * trying to obtbin bnotifr monitor blrfbdy ifld by bnotifr tirfbd
     * in b dydlf.
     * <p>
     * Morf formblly, b tirfbd is <fm>monitor dfbdlodkfd</fm> if it is
     * pbrt of b dydlf in tif rflbtion "is wbiting for bn objfdt monitor
     * ownfd by".  In tif simplfst dbsf, tirfbd A is blodkfd wbiting
     * for b monitor ownfd by tirfbd B, bnd tirfbd B is blodkfd wbiting
     * for b monitor ownfd by tirfbd A.
     * <p>
     * Tiis mftiod is dfsignfd for troublfsiooting usf, but not for
     * syndironizbtion dontrol.  It migit bf bn fxpfnsivf opfrbtion.
     * <p>
     * Tiis mftiod finds dfbdlodks involving only objfdt monitors.
     * To find dfbdlodks involving boti objfdt monitors bnd
     * <b irff="LodkInfo.itml#OwnbblfSyndironizfr">ownbblf syndironizfrs</b>,
     * tif {@link #findDfbdlodkfdTirfbds findDfbdlodkfdTirfbds} mftiod
     * siould bf usfd.
     *
     * @rfturn bn brrby of IDs of tif tirfbds tibt brf monitor
     * dfbdlodkfd, if bny; <tt>null</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     *
     * @sff #findDfbdlodkfdTirfbds
     */
    publid long[] findMonitorDfbdlodkfdTirfbds();

    /**
     * Rfsfts tif pfbk tirfbd dount to tif durrfnt numbfr of
     * livf tirfbds.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("dontrol").
     *
     * @sff #gftPfbkTirfbdCount
     * @sff #gftTirfbdCount
     */
    publid void rfsftPfbkTirfbdCount();

    /**
     * Finds dydlfs of tirfbds tibt brf in dfbdlodk wbiting to bdquirf
     * objfdt monitors or
     * <b irff="LodkInfo.itml#OwnbblfSyndironizfr">ownbblf syndironizfrs</b>.
     *
     * Tirfbds brf <fm>dfbdlodkfd</fm> in b dydlf wbiting for b lodk of
     * tifsf two typfs if fbdi tirfbd owns onf lodk wiilf
     * trying to bdquirf bnotifr lodk blrfbdy ifld
     * by bnotifr tirfbd in tif dydlf.
     * <p>
     * Tiis mftiod is dfsignfd for troublfsiooting usf, but not for
     * syndironizbtion dontrol.  It migit bf bn fxpfnsivf opfrbtion.
     *
     * @rfturn bn brrby of IDs of tif tirfbds tibt brf
     * dfbdlodkfd wbiting for objfdt monitors or ownbblf syndironizfrs, if bny;
     * <tt>null</tt> otifrwisf.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb virtubl
     * mbdiinf dofs not support monitoring of ownbblf syndironizfr usbgf.
     *
     * @sff #isSyndironizfrUsbgfSupportfd
     * @sff #findMonitorDfbdlodkfdTirfbds
     * @sindf 1.6
     */
    publid long[] findDfbdlodkfdTirfbds();

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports monitoring of
     * objfdt monitor usbgf.
     *
     * @rfturn
     *   <tt>truf</tt>
     *     if tif Jbvb virtubl mbdiinf supports monitoring of
     *     objfdt monitor usbgf;
     *   <tt>fblsf</tt> otifrwisf.
     *
     * @sff #dumpAllTirfbds
     * @sindf 1.6
     */
    publid boolfbn isObjfdtMonitorUsbgfSupportfd();

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports monitoring of
     * <b irff="LodkInfo.itml#OwnbblfSyndironizfr">
     * ownbblf syndironizfr</b> usbgf.
     *
     * @rfturn
     *   <tt>truf</tt>
     *     if tif Jbvb virtubl mbdiinf supports monitoring of ownbblf
     *     syndironizfr usbgf;
     *   <tt>fblsf</tt> otifrwisf.
     *
     * @sff #dumpAllTirfbds
     * @sindf 1.6
     */
    publid boolfbn isSyndironizfrUsbgfSupportfd();

    /**
     * Rfturns tif tirfbd info for fbdi tirfbd
     * wiosf ID is in tif input brrby <tt>ids</tt>, witi stbdk trbdf
     * bnd syndironizbtion informbtion.
     *
     * <p>
     * Tiis mftiod obtbins b snbpsiot of tif tirfbd informbtion
     * for fbdi tirfbd indluding:
     * <ul>
     *    <li>tif fntirf stbdk trbdf,</li>
     *    <li>tif objfdt monitors durrfntly lodkfd by tif tirfbd
     *        if <tt>lodkfdMonitors</tt> is <tt>truf</tt>, bnd</li>
     *    <li>tif <b irff="LodkInfo.itml#OwnbblfSyndironizfr">
     *        ownbblf syndironizfrs</b> durrfntly lodkfd by tif tirfbd
     *        if <tt>lodkfdSyndironizfrs</tt> is <tt>truf</tt>.</li>
     * </ul>
     * <p>
     * Tiis mftiod rfturns bn brrby of tif <tt>TirfbdInfo</tt> objfdts,
     * fbdi is tif tirfbd informbtion bbout tif tirfbd witi tif sbmf indfx
     * bs in tif <tt>ids</tt> brrby.
     * If b tirfbd of tif givfn ID is not blivf or dofs not fxist,
     * <tt>null</tt> will bf sft in tif dorrfsponding flfmfnt
     * in tif rfturnfd brrby.  A tirfbd is blivf if
     * it ibs bffn stbrtfd bnd ibs not yft difd.
     * <p>
     * If b tirfbd dofs not lodk bny objfdt monitor or <tt>lodkfdMonitors</tt>
     * is <tt>fblsf</tt>, tif rfturnfd <tt>TirfbdInfo</tt> objfdt will ibvf bn
     * fmpty <tt>MonitorInfo</tt> brrby.  Similbrly, if b tirfbd dofs not
     * lodk bny syndironizfr or <tt>lodkfdSyndironizfrs</tt> is <tt>fblsf</tt>,
     * tif rfturnfd <tt>TirfbdInfo</tt> objfdt
     * will ibvf bn fmpty <tt>LodkInfo</tt> brrby.
     *
     * <p>
     * Wifn boti <tt>lodkfdMonitors</tt> bnd <tt>lodkfdSyndironizfrs</tt>
     * pbrbmftfrs brf <tt>fblsf</tt>, it is fquivblfnt to dblling:
     * <blodkquotf><prf>
     *     {@link #gftTirfbdInfo(long[], int)  gftTirfbdInfo(ids, Intfgfr.MAX_VALUE)}
     * </prf></blodkquotf>
     *
     * <p>
     * Tiis mftiod is dfsignfd for troublfsiooting usf, but not for
     * syndironizbtion dontrol.  It migit bf bn fxpfnsivf opfrbtion.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of <tt>TirfbdInfo</tt> is
     * <tt>CompositfDbtb</tt> witi bttributfs bs spfdififd in tif
     * {@link TirfbdInfo#from TirfbdInfo.from} mftiod.
     *
     * @pbrbm  ids bn brrby of tirfbd IDs.
     * @pbrbm  lodkfdMonitors if <tt>truf</tt>, rftrifvfs bll lodkfd monitors.
     * @pbrbm  lodkfdSyndironizfrs if <tt>truf</tt>, rftrifvfs bll lodkfd
     *             ownbblf syndironizfrs.
     *
     * @rfturn bn brrby of tif {@link TirfbdInfo} objfdts, fbdi dontbining
     * informbtion bbout b tirfbd wiosf ID is in tif dorrfsponding
     * flfmfnt of tif input brrby of IDs.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption
     *         <ul>
     *           <li>if <tt>lodkfdMonitors</tt> is <tt>truf</tt> but
     *               tif Jbvb virtubl mbdiinf dofs not support monitoring
     *               of {@linkplbin #isObjfdtMonitorUsbgfSupportfd
     *               objfdt monitor usbgf}; or</li>
     *           <li>if <tt>lodkfdSyndironizfrs</tt> is <tt>truf</tt> but
     *               tif Jbvb virtubl mbdiinf dofs not support monitoring
     *               of {@linkplbin #isSyndironizfrUsbgfSupportfd
     *               ownbblf syndironizfr usbgf}.</li>
     *         </ul>
     *
     * @sff #isObjfdtMonitorUsbgfSupportfd
     * @sff #isSyndironizfrUsbgfSupportfd
     *
     * @sindf 1.6
     */
    publid TirfbdInfo[] gftTirfbdInfo(long[] ids, boolfbn lodkfdMonitors, boolfbn lodkfdSyndironizfrs);

    /**
     * Rfturns tif tirfbd info for bll livf tirfbds witi stbdk trbdf
     * bnd syndironizbtion informbtion.
     * Somf tirfbds indludfd in tif rfturnfd brrby
     * mby ibvf bffn tfrminbtfd wifn tiis mftiod rfturns.
     *
     * <p>
     * Tiis mftiod rfturns bn brrby of {@link TirfbdInfo} objfdts
     * bs spfdififd in tif {@link #gftTirfbdInfo(long[], boolfbn, boolfbn)}
     * mftiod.
     *
     * @pbrbm  lodkfdMonitors if <tt>truf</tt>, dump bll lodkfd monitors.
     * @pbrbm  lodkfdSyndironizfrs if <tt>truf</tt>, dump bll lodkfd
     *             ownbblf syndironizfrs.
     *
     * @rfturn bn brrby of {@link TirfbdInfo} for bll livf tirfbds.
     *
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr
     *         fxists bnd tif dbllfr dofs not ibvf
     *         MbnbgfmfntPfrmission("monitor").
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption
     *         <ul>
     *           <li>if <tt>lodkfdMonitors</tt> is <tt>truf</tt> but
     *               tif Jbvb virtubl mbdiinf dofs not support monitoring
     *               of {@linkplbin #isObjfdtMonitorUsbgfSupportfd
     *               objfdt monitor usbgf}; or</li>
     *           <li>if <tt>lodkfdSyndironizfrs</tt> is <tt>truf</tt> but
     *               tif Jbvb virtubl mbdiinf dofs not support monitoring
     *               of {@linkplbin #isSyndironizfrUsbgfSupportfd
     *               ownbblf syndironizfr usbgf}.</li>
     *         </ul>
     *
     * @sff #isObjfdtMonitorUsbgfSupportfd
     * @sff #isSyndironizfrUsbgfSupportfd
     *
     * @sindf 1.6
     */
    publid TirfbdInfo[] dumpAllTirfbds(boolfbn lodkfdMonitors, boolfbn lodkfdSyndironizfrs);
}
