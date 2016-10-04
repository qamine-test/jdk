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

pbdkbgf dom.sun.jdi;

import jbvb.util.List;

/**
 * A mirror of b dlbss in tif tbrgft VM. A ClbssTypf is b rffinfmfnt
 * of {@link RfffrfndfTypf} tibt bpplifs to truf dlbssfs in tif JLS
 * sfnsf of tif dffinition (not bn intfrfbdf, not bn brrby typf). Any
 * {@link ObjfdtRfffrfndf} tibt mirrors bn instbndf of sudi b dlbss
 * will ibvf b ClbssTypf bs its typf.
 *
 * @sff ObjfdtRfffrfndf
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ClbssTypf fxtfnds RfffrfndfTypf {
    /**
     * Gfts tif supfrdlbss of tiis dlbss.
     *
     * @rfturn b {@link ClbssTypf} tibt mirrors tif supfrdlbss
     * of tiis dlbss in tif tbrgft VM. If no sudi dlbss fxists,
     * rfturns null
     */
    ClbssTypf supfrdlbss();

    /**
     * Gfts tif intfrfbdfs dirfdtly implfmfntfd by tiis dlbss.
     * Only tif intfrfbdfs tibt brf dfdlbrfd witi tif "implfmfnts"
     * kfyword in tiis dlbss brf indludfd.
     *
     * @rfturn b List of {@link IntfrfbdfTypf} objfdts fbdi mirroring
     * b dirfdt intfrfbdf tiis ClbssTypf in tif tbrgft VM.
     * If nonf fxist, rfturns b zfro lfngti List.
     * @tirows ClbssNotPrfpbrfdExdfption if tiis dlbss not yft bffn
     * prfpbrfd.
     */
    List<IntfrfbdfTypf> intfrfbdfs();

    /**
     * Gfts tif intfrfbdfs dirfdtly bnd indirfdtly implfmfntfd
     * by tiis dlbss. Intfrfbdfs rfturnfd by {@link ClbssTypf#intfrfbdfs}
     * brf rfturnfd bs wfll bll supfrintfrfbdfs.
     *
     * @rfturn b List of {@link IntfrfbdfTypf} objfdts fbdi mirroring
     * bn intfrfbdf of tiis ClbssTypf in tif tbrgft VM.
     * If nonf fxist, rfturns b zfro lfngti List.
     * @tirows ClbssNotPrfpbrfdExdfption if tiis dlbss not yft bffn
     * prfpbrfd.
     */
    List<IntfrfbdfTypf> bllIntfrfbdfs();

    /**
     * Gfts tif durrfntly lobdfd, dirfdt subdlbssfs of tiis dlbss.
     * No ordfring of tiis list is gubrbntffd.
     *
     * @rfturn b List of {@link ClbssTypf} objfdts fbdi mirroring b lobdfd
     * subdlbss of tiis dlbss in tif tbrgft VM. If no sudi dlbssfs
     * fxist, tiis mftiod rfturns b zfro-lfngti list.
     */
    List<ClbssTypf> subdlbssfs();

    /**
     * Dftfrminf if tiis dlbss wbs dfdlbrfd bs bn fnum.
     * @rfturn <dodf>truf</dodf> if tiis dlbss wbs dfdlbrfd bs bn fnum; fblsf
     * otifrwisf.
     */
    boolfbn isEnum();

    /**
     * Assigns b vbluf to b stbtid fifld.
     * Tif {@link Fifld} must bf vblid for tiis ClbssTypf; tibt is,
     * it must bf from tif mirrorfd objfdt's dlbss or b supfrdlbss of tibt dlbss.
     * Tif fifld must not bf finbl.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif fifld typf
     * (Tiis implifs tibt tif fifld typf must bf lobdfd tirougi tif
     * fndlosing dlbss' dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif fifld typf or must bf
     * donvfrtiblf to tif fifld typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm fifld tif fifld to sft.
     * @pbrbm vbluf tif vbluf to bf bssignfd.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif fifld is
     * not stbtid, tif fifld is finbl, or tif fifld dofs not fxist
     * in tiis dlbss.
     * @tirows ClbssNotLobdfdExdfption if tif fifld typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows InvblidTypfExdfption if tif vbluf's typf dofs not mbtdi
     * tif fifld's dfdlbrfd typf.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void sftVbluf(Fifld fifld, Vbluf vbluf)
        tirows InvblidTypfExdfption, ClbssNotLobdfdExdfption;

    /** Pfrform mftiod invodbtion witi only tif invoking tirfbd rfsumfd */
    stbtid finbl int INVOKE_SINGLE_THREADED = 0x1;

    /**
     * Invokfs tif spfdififd stbtid {@link Mftiod} in tif
     * tbrgft VM. Tif
     * spfdififd mftiod dbn bf dffinfd in tiis dlbss,
     * or in b supfrdlbss.
     * Tif mftiod must bf b stbtid mftiod
     * but not b stbtid initiblizfr.
     * Usf {@link ClbssTypf#nfwInstbndf} to drfbtf b nfw objfdt bnd
     * run its donstrudtor.
     * <p>
     * Tif mftiod invodbtion will oddur in tif spfdififd tirfbd.
     * Mftiod invodbtion dbn oddur only if tif spfdififd tirfbd
     * ibs bffn suspfndfd by bn fvfnt wiidi oddurrfd in tibt tirfbd.
     * Mftiod invodbtion is not supportfd
     * wifn tif tbrgft VM ibs bffn suspfndfd tirougi
     * {@link VirtublMbdiinf#suspfnd} or wifn tif spfdififd tirfbd
     * is suspfndfd tirougi {@link TirfbdRfffrfndf#suspfnd}.
     * <p>
     * Tif spfdififd mftiod is invokfd witi tif brgumfnts in tif spfdififd
     * brgumfnt list.  Tif mftiod invodbtion is syndironous; tiis mftiod
     * dofs not rfturn until tif invokfd mftiod rfturns in tif tbrgft VM.
     * If tif invokfd mftiod tirows bn fxdfption, tiis mftiod will tirow
     * bn {@link InvodbtionExdfption} wiidi dontbins b mirror to tif fxdfption
     * objfdt tirown.
     * <p>
     * Objfdt brgumfnts must bf bssignmfnt dompbtiblf witi tif brgumfnt typf
     * (Tiis implifs tibt tif brgumfnt typf must bf lobdfd tirougi tif
     * fndlosing dlbss' dlbss lobdfr). Primitivf brgumfnts must bf
     * fitifr bssignmfnt dompbtiblf witi tif brgumfnt typf or must bf
     * donvfrtiblf to tif brgumfnt typf witiout loss of informbtion.
     * If tif mftiod bfing dbllfd bddfpts b vbribblf numbfr of brgumfnts,
     * tifn tif lbst brgumfnt typf is bn brrby of somf domponfnt typf.
     * Tif brgumfnt in tif mbtdiing position dbn bf omittfd, or dbn bf null,
     * bn brrby of tif sbmf domponfnt typf, or bn brgumfnt of tif
     * domponfnt typf followfd by bny numbfr of otifr brgumfnts of tif sbmf
     * typf. If tif brgumfnt is omittfd, tifn b 0 lfngti brrby of tif
     * domponfnt typf is pbssfd.  Tif domponfnt typf dbn bf b primitivf typf.
     * Autoboxing is not supportfd.
     *
     * Sff Sfdtion 5.2 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
     * for morf informbtion on bssignmfnt dompbtibility.
     * <p>
     * By dffbult, bll tirfbds in tif tbrgft VM brf rfsumfd wiilf
     * tif mftiod is bfing invokfd if tify wfrf prfviously
     * suspfndfd by bn fvfnt or by {@link VirtublMbdiinf#suspfnd} or
     * {@link TirfbdRfffrfndf#suspfnd}. Tiis is donf to prfvfnt tif dfbdlodks
     * tibt will oddur if bny of tif tirfbds own monitors
     * tibt will bf nffdfd by tif invokfd mftiod.
     * Notf, iowfvfr, tibt tiis implidit rfsumf bdts fxbdtly likf
     * {@link TirfbdRfffrfndf#rfsumf}, so if tif tirfbd's suspfnd
     * dount is grfbtfr tibn 1, it will rfmbin in b suspfndfd stbtf
     * during tif invodbtion bnd tius b dfbdlodk dould still oddur.
     * By dffbult, wifn tif invodbtion domplftfs,
     * bll tirfbds in tif tbrgft VM brf suspfndfd, rfgbrdlfss tifir stbtf
     * bfforf tif invodbtion.
     * It is possiblf tibt
     * brfbkpoints or otifr fvfnts migit oddur during tif invodbtion.
     * Tiis dbn dbusf dfbdlodks bs dfsdribfd bbovf. It dbn blso dbusf b dfbdlodk
     * if invokfMftiod is dbllfd from tif dlifnt's fvfnt ibndlfr tirfbd.  In tiis
     * dbsf, tiis tirfbd will bf wbiting for tif invokfMftiod to domplftf bnd
     * won't rfbd tif EvfntSft tibt domfs in for tif nfw fvfnt.  If tiis
     * nfw EvfntSft is SUSPEND_ALL, tifn b dfbdlodk will oddur bfdbusf no
     * onf will rfsumf tif EvfntSft.  To bvoid tiis, bll EvfntRfqufsts siould
     * bf disbblfd bfforf doing tif invokfMftiod, or tif invokfMftiod siould
     * not bf donf from tif dlifnt's fvfnt ibndlfr tirfbd.
     * <p>
     * Tif rfsumption of otifr tirfbds during tif invodbtion dbn bf prfvfntfd
     * by spfdifying tif {@link #INVOKE_SINGLE_THREADED}
     * bit flbg in tif <dodf>options</dodf> brgumfnt; iowfvfr,
     * tifrf is no protfdtion bgbinst or rfdovfry from tif dfbdlodks
     * dfsdribfd bbovf, so tiis option siould bf usfd witi grfbt dbution.
     * Only tif spfdififd tirfbd will bf rfsumfd (bs dfsdribfd for bll
     * tirfbds bbovf). Upon domplftion of b singlf tirfbdfd invokf, tif invoking tirfbd
     * will bf suspfndfd ondf bgbin. Notf tibt bny tirfbds stbrtfd during
     * tif singlf tirfbdfd invodbtion will not bf suspfndfd wifn tif
     * invodbtion domplftfs.
     * <p>
     * If tif tbrgft VM is disdonnfdtfd during tif invokf (for fxbmplf, tirougi
     * {@link VirtublMbdiinf#disposf}) tif mftiod invodbtion dontinufs.
     *
     * @pbrbm tirfbd tif tirfbd in wiidi to invokf.
     * @pbrbm mftiod tif {@link Mftiod} to invokf.
     * @pbrbm brgumfnts tif list of {@link Vbluf} brgumfnts bound to tif
     * invokfd mftiod. Vblufs from tif list brf bssignfd to brgumfnts
     * in tif ordfr tify bppfbr in tif mftiod signbturf.
     * @pbrbm options tif intfgfr bit flbg options.
     * @rfturn b {@link Vbluf} mirror of tif invokfd mftiod's rfturn vbluf.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif mftiod is not
     * b mfmbfr of tiis dlbss or b supfrdlbss, if tif sizf of tif brgumfnt list
     * dofs not mbtdi tif numbfr of dfdlbrfd brgumfnts for tif mftiod, or
     * if tif mftiod is bn initiblizfr, donstrudtor or stbtid intiblizfr.
     * @tirows {@link InvblidTypfExdfption} if bny brgumfnt in tif
     * brgumfnt list is not bssignbblf to tif dorrfsponding mftiod brgumfnt
     * typf.
     * @tirows ClbssNotLobdfdExdfption if bny brgumfnt typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif spfdififd tirfbd ibs not
     * bffn suspfndfd by bn fvfnt.
     * @tirows InvodbtionExdfption if tif mftiod invodbtion rfsultfd in
     * bn fxdfption in tif tbrgft VM.
     * @tirows InvblidTypfExdfption If tif brgumfnts do not mfft tiis rfquirfmfnt --
     *         Objfdt brgumfnts must bf bssignmfnt dompbtiblf witi tif brgumfnt
     *         typf.  Tiis implifs tibt tif brgumfnt typf must bf
     *         lobdfd tirougi tif fndlosing dlbss' dlbss lobdfr.
     *         Primitivf brgumfnts must bf fitifr bssignmfnt dompbtiblf witi tif
     *         brgumfnt typf or must bf donvfrtiblf to tif brgumfnt typf witiout loss
     *         of informbtion. Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     *         dompbtibility.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    Vbluf invokfMftiod(TirfbdRfffrfndf tirfbd, Mftiod mftiod,
                       List<? fxtfnds Vbluf> brgumfnts, int options)
                                   tirows InvblidTypfExdfption,
                                          ClbssNotLobdfdExdfption,
                                          IndompbtiblfTirfbdStbtfExdfption,
                                          InvodbtionExdfption;

    /**
     * Construdts b nfw instbndf of tiis typf, using
     * tif givfn donstrudtor {@link Mftiod} in tif
     * tbrgft VM. Tif
     * spfdififd donstrudtor must bf dffinfd in tiis dlbss.
     * <p>
     * Instbndf drfbtion will oddur in tif spfdififd tirfbd.
     * Instbndf drfbtion dbn oddur only if tif spfdififd tirfbd
     * ibs bffn suspfndfd by bn fvfnt wiidi oddurrfd in tibt tirfbd.
     * Instbndf drfbtion is not supportfd
     * wifn tif tbrgft VM ibs bffn suspfndfd tirougi
     * {@link VirtublMbdiinf#suspfnd} or wifn tif spfdififd tirfbd
     * is suspfndfd tirougi {@link TirfbdRfffrfndf#suspfnd}.
     * <p>
     * Tif spfdififd donstrudtor is invokfd witi tif brgumfnts in tif spfdififd
     * brgumfnt list.  Tif invodbtion is syndironous; tiis mftiod
     * dofs not rfturn until tif donstrudtor rfturns in tif tbrgft VM.
     * If tif invokfd mftiod tirows bn
     * fxdfption, tiis mftiod will tirow bn {@link InvodbtionExdfption}
     * wiidi dontbins b mirror to tif fxdfption objfdt tirown.
     * <p>
     * Objfdt brgumfnts must bf bssignmfnt dompbtiblf witi tif brgumfnt typf
     * (Tiis implifs tibt tif brgumfnt typf must bf lobdfd tirougi tif
     * fndlosing dlbss' dlbss lobdfr). Primitivf brgumfnts must bf
     * fitifr bssignmfnt dompbtiblf witi tif brgumfnt typf or must bf
     * donvfrtiblf to tif brgumfnt typf witiout loss of informbtion.
     * If tif mftiod bfing dbllfd bddfpts b vbribblf numbfr of brgumfnts,
     * tifn tif lbst brgumfnt typf is bn brrby of somf domponfnt typf.
     * Tif brgumfnt in tif mbtdiing position dbn bf omittfd, or dbn bf null,
     * bn brrby of tif sbmf domponfnt typf, or bn brgumfnt of tif
     * domponfnt typf, followfd by bny numbfr of otifr brgumfnts of tif sbmf
     * typf. If tif brgumfnt is omittfd, tifn b 0 lfngti brrby of tif
     * domponfnt typf is pbssfd.  Tif domponfnt typf dbn bf b primitivf typf.
     * Autoboxing is not supportfd.
     *
     * Sff sfdtion 5.2 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
     * for morf informbtion on bssignmfnt dompbtibility.
     * <p>
     * By dffbult, bll tirfbds in tif tbrgft VM brf rfsumfd wiilf
     * tif mftiod is bfing invokfd if tify wfrf prfviously
     * suspfndfd by bn fvfnt or by {@link VirtublMbdiinf#suspfnd} or
     * {@link TirfbdRfffrfndf#suspfnd}. Tiis is donf to prfvfnt tif dfbdlodks
     * tibt will oddur if bny of tif tirfbds own monitors
     * tibt will bf nffdfd by tif invokfd mftiod. It is possiblf tibt
     * brfbkpoints or otifr fvfnts migit oddur during tif invodbtion.
     * Notf, iowfvfr, tibt tiis implidit rfsumf bdts fxbdtly likf
     * {@link TirfbdRfffrfndf#rfsumf}, so if tif tirfbd's suspfnd
     * dount is grfbtfr tibn 1, it will rfmbin in b suspfndfd stbtf
     * during tif invodbtion. By dffbult, wifn tif invodbtion domplftfs,
     * bll tirfbds in tif tbrgft VM brf suspfndfd, rfgbrdlfss tifir stbtf
     * bfforf tif invodbtion.
     * <p>
     * Tif rfsumption of otifr tirfbds during tif invodbtion dbn bf prfvfntfd
     * by spfdifying tif {@link #INVOKE_SINGLE_THREADED}
     * bit flbg in tif <dodf>options</dodf> brgumfnt; iowfvfr,
     * tifrf is no protfdtion bgbinst or rfdovfry from tif dfbdlodks
     * dfsdribfd bbovf, so tiis option siould bf usfd witi grfbt dbution.
     * Only tif spfdififd tirfbd will bf rfsumfd (bs dfsdribfd for bll
     * tirfbds bbovf). Upon domplftion of b singlf tirfbdfd invokf, tif invoking tirfbd
     * will bf suspfndfd ondf bgbin. Notf tibt bny tirfbds stbrtfd during
     * tif singlf tirfbdfd invodbtion will not bf suspfndfd wifn tif
     * invodbtion domplftfs.
     * <p>
     * If tif tbrgft VM is disdonnfdtfd during tif invokf (for fxbmplf, tirougi
     * {@link VirtublMbdiinf#disposf}) tif mftiod invodbtion dontinufs.
     *
     * @pbrbm tirfbd tif tirfbd in wiidi to invokf.
     * @pbrbm mftiod tif donstrudtor {@link Mftiod} to invokf.
     * @pbrbm brgumfnts tif list of {@link Vbluf} brgumfnts bound to tif
     * invokfd donstrudtor. Vblufs from tif list brf bssignfd to brgumfnts
     * in tif ordfr tify bppfbr in tif donstrudtor signbturf.
     * @pbrbm options tif intfgfr bit flbg options.
     * @rfturn bn {@link ObjfdtRfffrfndf} mirror of tif nfwly drfbtfd
     * objfdt.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif mftiod is not
     * b mfmbfr of tiis dlbss, if tif sizf of tif brgumfnt list
     * dofs not mbtdi tif numbfr of dfdlbrfd brgumfnts for tif donstrudtor,
     * or if tif mftiod is not b donstrudtor.
     * @tirows {@link InvblidTypfExdfption} if bny brgumfnt in tif
     * brgumfnt list is not bssignbblf to tif dorrfsponding mftiod brgumfnt
     * typf.
     * @tirows ClbssNotLobdfdExdfption if bny brgumfnt typf ibs not yft bffn lobdfd
     * tirougi tif bppropribtf dlbss lobdfr.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif spfdififd tirfbd ibs not
     * bffn suspfndfd by bn fvfnt.
     * @tirows InvodbtionExdfption if tif mftiod invodbtion rfsultfd in
     * bn fxdfption in tif tbrgft VM.
     * @tirows InvblidTypfExdfption If tif brgumfnts do not mfft tiis rfquirfmfnt --
     *         Objfdt brgumfnts must bf bssignmfnt dompbtiblf witi tif brgumfnt
     *         typf.  Tiis implifs tibt tif brgumfnt typf must bf
     *         lobdfd tirougi tif fndlosing dlbss' dlbss lobdfr.
     *         Primitivf brgumfnts must bf fitifr bssignmfnt dompbtiblf witi tif
     *         brgumfnt typf or must bf donvfrtiblf to tif brgumfnt typf witiout loss
     *         of informbtion. Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     *         dompbtibility.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    ObjfdtRfffrfndf nfwInstbndf(TirfbdRfffrfndf tirfbd, Mftiod mftiod,
                                List<? fxtfnds Vbluf> brgumfnts, int options)
                                   tirows InvblidTypfExdfption,
                                          ClbssNotLobdfdExdfption,
                                          IndompbtiblfTirfbdStbtfExdfption,
                                          InvodbtionExdfption;

    /**
     * Rfturns b tif singlf non-bbstrbdt {@link Mftiod} visiblf from
     * tiis dlbss tibt ibs tif givfn nbmf bnd signbturf.
     * Sff {@link RfffrfndfTypf#mftiodsByNbmf(jbvb.lbng.String, jbvb.lbng.String)}
     * for informbtion on signbturf formbt.
     * <p>
     * Tif rfturnfd mftiod (if non-null) is b domponfnt of
     * {@link ClbssTypf}.
     *
     * @sff RfffrfndfTypf#visiblfMftiods
     * @sff RfffrfndfTypf#mftiodsByNbmf(jbvb.lbng.String nbmf)
     * @sff RfffrfndfTypf#mftiodsByNbmf(jbvb.lbng.String nbmf, jbvb.lbng.String signbturf)
     * @pbrbm nbmf tif nbmf of tif mftiod to find.
     * @pbrbm signbturf tif signbturf of tif mftiod to find
     * @rfturn tif {@link Mftiod} tibt mbtdifs tif givfn
     * nbmf bnd signbturf or <dodf>null</dodf> if tifrf is no mbtdi.
     * @tirows ClbssNotPrfpbrfdExdfption if mftiods brf not yft bvbilbblf
     * bfdbusf tif dlbss ibs not yft bffn prfpbrfd.
     */
    Mftiod dondrftfMftiodByNbmf(String nbmf, String signbturf);
}
