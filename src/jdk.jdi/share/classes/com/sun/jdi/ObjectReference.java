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
import jbvb.util.Mbp;

/**
 * An objfdt tibt durrfntly fxists in tif tbrgft VM. An ObjfdtRfffrfndf
 * mirrors only tif objfdt itsflf bnd is not spfdifid to bny
 * {@link Fifld} or {@link LodblVbribblf} to wiidi it is durrfntly
 * bssignfd. An ObjfdtRfffrfndf dbn
 * ibvf 0 or morf rfffrfndfs from fifld(s) bnd/or vbribblf(s).
 * <p>
 * Any mftiod on <dodf>ObjfdtRfffrfndf</dodf> wiidi dirfdtly or
 * indirfdtly tbkfs <dodf>ObjfdtRfffrfndf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMDisdonnfdtfdExdfption} if tif tbrgft VM is
 * disdonnfdtfd bnd tif {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt} ibs bffn or is
 * bvbilbblf to bf rfbd from tif {@link dom.sun.jdi.fvfnt.EvfntQufuf}.
 * <p>
 * Any mftiod on <dodf>ObjfdtRfffrfndf</dodf> wiidi dirfdtly or
 * indirfdtly tbkfs <dodf>ObjfdtRfffrfndf</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMOutOfMfmoryExdfption} if tif tbrgft VM ibs run out of mfmory.
 * <p>
 * Any mftiod on <dodf>ObjfdtRfffrfndf</dodf> or wiidi dirfdtly or indirfdtly tbkfs
 * <dodf>ObjfdtRfffrfndf</dodf> bs pbrbmftfr mby tirow
 * {@link dom.sun.jdi.ObjfdtCollfdtfdExdfption} if tif mirrorfd objfdt ibs bffn
 * gbrbbgf dollfdtfd.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ObjfdtRfffrfndf fxtfnds Vbluf {

    /**
     * Gfts tif {@link RfffrfndfTypf} tibt mirrors tif typf
     * of tiis objfdt. Tif typf mby bf b subdlbss or implfmfntor of tif
     * dfdlbrfd typf of bny fifld or vbribblf wiidi durrfntly iolds it.
     * For fxbmplf, rigit bftfr tif following stbtfmfnt.
     * <p>
     * <dodf>Objfdt obj = nfw String("Hfllo, world!");</dodf>
     * <p>
     * Tif RfffrfndfTypf of obj will mirror jbvb.lbng.String bnd not
     * jbvb.lbng.Objfdt.
     * <p>
     * Tif typf of bn objfdt nfvfr dibngfs, so tiis mftiod will
     * blwbys rfturn tif sbmf RfffrfndfTypf ovfr tif lifftimf of tif
     * mirrorfd objfdt.
     * <p>
     * Tif rfturnfd RfffrfndfTypf will bf b {@link ClbssTypf} or
     * {@link ArrbyTypf} bnd nfvfr bn {@link IntfrfbdfTypf}.
     *
     * @rfturn tif {@link RfffrfndfTypf} for tiis objfdt.
     */
    RfffrfndfTypf rfffrfndfTypf();

    /**
     * Gfts tif vbluf of b givfn instbndf or stbtid fifld in tiis objfdt.
     * Tif Fifld must bf vblid for tiis ObjfdtRfffrfndf;
     * tibt is, it must bf from
     * tif mirrorfd objfdt's dlbss or b supfrdlbss of tibt dlbss.
     *
     * @pbrbm sig tif fifld dontbining tif rfqufstfd vbluf
     * @rfturn tif {@link Vbluf} of tif instbndf fifld.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif fifld is not vblid for
     * tiis objfdt's dlbss.
     */
    Vbluf gftVbluf(Fifld sig);

    /**
     * Gfts tif vbluf of multiplf instbndf bnd/or stbtid fiflds in tiis objfdt.
     * Tif Fiflds must bf vblid for tiis ObjfdtRfffrfndf;
     * tibt is, tify must bf from
     * tif mirrorfd objfdt's dlbss or b supfrdlbss of tibt dlbss.
     *
     * @pbrbm fiflds b list of {@link Fifld} objfdts dontbining tif
     * rfqufstfd vblufs.
     * @rfturn b Mbp of tif rfqufstfd {@link Fifld} objfdts witi
     * tifir {@link Vbluf}.
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if bny fifld is not vblid for
     * tiis objfdt's dlbss.
     */
    Mbp<Fifld,Vbluf> gftVblufs(List<? fxtfnds Fifld> fiflds);

    /**
     * Sfts tif vbluf of b givfn instbndf or stbtid fifld in tiis objfdt.
     * Tif {@link Fifld} must bf vblid for tiis ObjfdtRfffrfndf; tibt is,
     * it must bf from tif mirrorfd objfdt's dlbss or b supfrdlbss of tibt dlbss.
     * If stbtid, tif fifld must not bf finbl.
     * <p>
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif fifld typf
     * (Tiis implifs tibt tif fifld typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif fifld typf or must bf
     * donvfrtiblf to tif fifld typf witiout loss of informbtion.
     * Sff sfdtion 5.2 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
     * for morf informbtion on bssignmfnt
     * dompbtibility.
     *
     * @pbrbm fifld tif fifld dontbining tif rfqufstfd vbluf
     * @pbrbm vbluf tif nfw vbluf to bssign
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if tif fifld is not vblid for
     * tiis objfdt's dlbss.
     * @tirows InvblidTypfExdfption if tif vbluf's typf dofs not mbtdi
     * tif fifld's typf.
     * @tirows ClbssNotLobdfdExdfption if 'vbluf' is not null, bnd tif fifld
     * typf ibs not yft bffn lobdfd tirougi tif bppropribtf dlbss lobdfr.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void sftVbluf(Fifld fifld, Vbluf vbluf)
        tirows InvblidTypfExdfption, ClbssNotLobdfdExdfption;

    /** Pfrform mftiod invodbtion witi only tif invoking tirfbd rfsumfd */
    stbtid finbl int INVOKE_SINGLE_THREADED = 0x1;
    /** Pfrform non-virtubl mftiod invodbtion */
    stbtid finbl int INVOKE_NONVIRTUAL      = 0x2;

    /**
     * Invokfs tif spfdififd {@link Mftiod} on tiis objfdt in tif
     * tbrgft VM. Tif
     * spfdififd mftiod dbn bf dffinfd in tiis objfdt's dlbss,
     * in b supfrdlbss of tiis objfdt's dlbss, or in bn intfrfbdf
     * implfmfntfd by tiis objfdt. Tif mftiod mby bf b stbtid mftiod
     * or bn instbndf mftiod, but not b stbtid initiblizfr or donstrudtor.
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
     * If tif invokfd mftiod tirows bn fxdfption, tiis mftiod
     * will tirow bn {@link InvodbtionExdfption} wiidi dontbins
     * b mirror to tif fxdfption objfdt tirown.
     * <p>
     * Objfdt brgumfnts must bf bssignmfnt dompbtiblf witi tif brgumfnt typf
     * (Tiis implifs tibt tif brgumfnt typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf brgumfnts must bf
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
     * Sff sfdtion 5.2 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
     * for morf informbtion on bssignmfnt dompbtibility.
     * <p>
     * By dffbult, tif mftiod is invokfd using dynbmid lookup bs
     * dodumfntfd in sfdtion 15.12.4.4 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>
     * in pbrtidulbr, ovfrriding bbsfd on tif runtimf typf of tif objfdt
     * mirrorfd by tiis {@link ObjfdtRfffrfndf} will oddur. Tiis
     * bfibvior dbn bf dibngfd by spfdifying tif
     * {@link #INVOKE_NONVIRTUAL} bit flbg in tif <dodf>options</dodf>
     * brgumfnt. If tiis flbg is sft, tif spfdififd mftiod is invokfd
     * wiftifr or not it is ovfrriddfn for tiis objfdt's runtimf typf.
     * Tif mftiod, in tiis dbsf, must ibvf bn implfmfntbtion, fitifr in b dlbss
     * or bn intfrfbdf. Tiis option is usfful for pfrforming mftiod invodbtions
     * likf tiosf donf witi tif <dodf>supfr</dodf> kfyword in tif Jbvb progrbmming
     * lbngubgf.
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
     * b mfmbfr of tiis objfdt's dlbss, if tif sizf of tif brgumfnt list
     * dofs not mbtdi tif numbfr of dfdlbrfd brgumfnts for tif mftiod,
     * if tif mftiod is b donstrudtor or stbtid intiblizfr, or
     * if {@link #INVOKE_NONVIRTUAL} is spfdififd bnd tif mftiod is
     * fitifr bbstrbdt or b non-dffbult intfrfbdf mfmbfr.
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
     *         lobdfd tirougi tif fndlosing dlbss's dlbss lobdfr.
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
     * Prfvfnts gbrbbgf dollfdtion for tiis objfdt. By dffbult bll
     * {@link ObjfdtRfffrfndf} vblufs rfturnfd by JDI mby bf dollfdtfd
     * bt bny timf tif tbrgft VM is running. A dbll to tiis mftiod
     * gubrbntffs tibt tif objfdt will not bf dollfdtfd.
     * {@link #fnbblfCollfdtion} dbn bf usfd to bllow dollfdtion ondf
     * bgbin.
     * <p>
     * Cblls to tiis mftiod brf dountfd. Evfry dbll to tiis mftiod
     * rfquirfs b dorrfsponding dbll to {@link #fnbblfCollfdtion} bfforf
     * gbrbbgf dollfdtion is rf-fnbblfd.
     * <p>
     * Notf tibt wiilf tif tbrgft VM is suspfndfd, no gbrbbgf dollfdtion
     * will oddur bfdbusf bll tirfbds brf suspfndfd. Tif typidbl
     * fxbminbtion of vbribblfs, fiflds, bnd brrbys during tif suspfnsion
     * is sbff witiout fxpliditly disbbling gbrbbgf dollfdtion.
     * <p>
     * Tiis mftiod siould bf usfd spbringly, bs it bltfrs tif
     * pbttfrn of gbrbbgf dollfdtion in tif tbrgft VM bnd,
     * donsfqufntly, mby rfsult in bpplidbtion bfibvior undfr tif
     * dfbuggfr tibt difffrs from its non-dfbuggfd bfibvior.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * -sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void disbblfCollfdtion();

    /**
     * Pfrmits gbrbbgf dollfdtion for tiis objfdt. By dffbult bll
     * {@link ObjfdtRfffrfndf} vblufs rfturnfd by JDI mby bf dollfdtfd
     * bt bny timf tif tbrgft VM is running. A dbll to tiis mftiod
     * is nfdfssbry only if gbrbbgf dollfdtion wbs prfviously disbblfd
     * witi {@link #disbblfCollfdtion}.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * -sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void fnbblfCollfdtion();

    /**
     * Dftfrminfs if tiis objfdt ibs bffn gbrbbgf dollfdtfd in tif tbrgft
     * VM.
     *
     * @rfturn <dodf>truf</dodf> if tiis {@link ObjfdtRfffrfndf} ibs bffn dollfdtfd;
     * <dodf>fblsf</dodf> otifrwisf.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only
     * -sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    boolfbn isCollfdtfd();

    /**
     * Rfturns b uniquf idfntififr for tiis ObjfdtRfffrfndf.
     * It is gubrbntffd to bf uniquf bmong bll
     * ObjfdtRfffrfndfs from tif sbmf VM tibt ibvf not yft bffn disposfd.
     * Tif gubrbntff bpplifs bs long
     * bs tiis ObjfdtRfffrfndf ibs not yft bffn disposfd.
     *
     * @rfturn b long uniquf ID
     */
    long uniqufID();

    /**
     * Rfturns b List dontbining b {@link TirfbdRfffrfndf} for
     * fbdi tirfbd durrfntly wbiting for tiis objfdt's monitor.
     * Sff {@link TirfbdRfffrfndf#durrfntContfndfdMonitor} for
     * informbtion bbout wifn b tirfbd is donsidfrfd to bf wbiting
     * for b monitor.
     * <p>
     * Not bll tbrgft VMs support tiis opfrbtion. Sff
     * VirtublMbdiinf#dbnGftMonitorInfo to dftfrminf if tif
     * opfrbtion is supportfd.
     *
     * @rfturn b List of {@link TirfbdRfffrfndf} objfdts. Tif list
     * ibs zfro lfngti if no tirfbds brf wbiting for tif monitor.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif
     * tbrgft VM dofs not support tiis opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if bny
     * wbiting tirfbd is not suspfndfd
     * in tif tbrgft VM
     */
    List<TirfbdRfffrfndf> wbitingTirfbds()
        tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns bn {@link TirfbdRfffrfndf} for tif tirfbd, if bny,
     * wiidi durrfntly owns tiis objfdt's monitor.
     * Sff {@link TirfbdRfffrfndf#ownfdMonitors} for b dffinition
     * of ownfrsiip.
     * <p>
     * Not bll tbrgft VMs support tiis opfrbtion. Sff
     * VirtublMbdiinf#dbnGftMonitorInfo to dftfrminf if tif
     * opfrbtion is supportfd.
     *
     * @rfturn tif {@link TirfbdRfffrfndf} wiidi durrfntly owns tif
     * monitor, or null if it is unownfd.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif
     * tbrgft VM dofs not support tiis opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif owning tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    TirfbdRfffrfndf owningTirfbd() tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns tif numbfr timfs tiis objfdt's monitor ibs bffn
     * fntfrfd by tif durrfnt owning tirfbd.
     * Sff {@link TirfbdRfffrfndf#ownfdMonitors} for b dffinition
     * of ownfrsiip.
     * <p>
     * Not bll tbrgft VMs support tiis opfrbtion. Sff
     * VirtublMbdiinf#dbnGftMonitorInfo to dftfrminf if tif
     * opfrbtion is supportfd.
     *
     * @sff #owningTirfbd
     * @rfturn tif intfgfr dount of tif numbfr of fntrifs.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif
     * tbrgft VM dofs not support tiis opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif owning tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    int fntryCount() tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns objfdts tibt dirfdtly rfffrfndf tiis objfdt.
     * Only objfdts tibt brf rfbdibblf for tif purposfs of gbrbbgf dollfdtion
     * brf rfturnfd.  Notf tibt bn objfdt dbn blso bf rfffrfndfd in otifr wbys,
     * sudi bs from b lodbl vbribblf in b stbdk frbmf, or from b JNI globbl
     * rfffrfndf.  Sudi non-objfdt rfffrrfrs brf not rfturnfd by tiis mftiod.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnGftInstbndfInfo()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @sff VirtublMbdiinf#instbndfCounts(List)
     * @sff RfffrfndfTypf#instbndfs(long)

     * @pbrbm mbxRfffrrfrs  Tif mbximum numbfr of rfffrring objfdts to rfturn.
     *                      Must bf non-nfgbtivf.  If zfro, bll rfffrring
     *                      objfdts brf rfturnfd.
     * @rfturn b of List of {@link ObjfdtRfffrfndf} objfdts. If tifrf brf
     *  no objfdts tibt rfffrfndf tiis objfdt, b zfro-lfngti list is rfturnfd..
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion - sff
     * {@link VirtublMbdiinf#dbnGftInstbndfInfo() dbnGftInstbndfInfo()}
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if mbxRfffrrfrs is lfss
     *         tibn zfro.
     * @sindf 1.6
     */
    List<ObjfdtRfffrfndf> rfffrringObjfdts(long mbxRfffrrfrs);


    /**
     * Compbrfs tif spfdififd Objfdt witi tiis ObjfdtRfffrfndf for fqublity.
     *
     * @rfturn  truf if tif Objfdt is bn ObjfdtRfffrfndf, if tif
     * ObjfdtRfffrfndfs bflong to tif sbmf VM, bnd if bpplying tif
     * "==" opfrbtor on tif mirrorfd objfdts in tibt VM fvblubtfs to truf.
     */
    boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis ObjfdtRfffrfndf.
     *
     * @rfturn tif intfgfr ibsi dodf
     */
    int ibsiCodf();
}
