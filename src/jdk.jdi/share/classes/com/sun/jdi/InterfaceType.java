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
 * A mirror of bn intfrfbdf in tif tbrgft VM. An IntfrfbdfTypf is
 * b rffinfmfnt of {@link RfffrfndfTypf} tibt bpplifs to truf intfrfbdfs
 * in tif JLS  sfnsf of tif dffinition (not b dlbss, not bn brrby typf).
 * An intfrfbdf typf will nfvfr bf rfturnfd by
 * {@link ObjfdtRfffrfndf#rfffrfndfTypf}, but it mby bf in tif list
 * of implfmfntfd intfrfbdfs for b {@link ClbssTypf} tibt is rfturnfd
 * by tibt mftiod.
 *
 * @sff ObjfdtRfffrfndf
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf IntfrfbdfTypf fxtfnds RfffrfndfTypf {
    /**
     * Gfts tif intfrfbdfs dirfdtly fxtfndfd by tiis intfrfbdf.
     * Tif rfturnfd list dontbins only tiosf intfrfbdfs tiis
     * intfrfbdf ibs dfdlbrfd to bf fxtfndfd.
     *
     * @rfturn b List of {@link IntfrfbdfTypf} objfdts fbdi mirroring
     * bn intfrfbdf fxtfndfd by tiis intfrfbdf.
     * If nonf fxist, rfturns b zfro lfngti List.
     * @tirows ClbssNotPrfpbrfdExdfption if tiis dlbss not yft bffn
     * prfpbrfd.
     */
    List<IntfrfbdfTypf> supfrintfrfbdfs();

    /**
     * Gfts tif durrfntly prfpbrfd intfrfbdfs wiidi dirfdtly fxtfnd tiis
     * intfrfbdf. Tif rfturnfd list dontbins only tiosf intfrfbdfs tibt
     * dfdlbrfd tiis intfrfbdf in tifir "fxtfnds" dlbusf.
     *
     * @rfturn b List of {@link IntfrfbdfTypf} objfdts fbdi mirroring
     * bn intfrfbdf fxtfnding tiis intfrfbdf.
     * If nonf fxist, rfturns b zfro lfngti List.
     */
    List<IntfrfbdfTypf> subintfrfbdfs();

    /**
     * Gfts tif durrfntly prfpbrfd dlbssfs wiidi dirfdtly implfmfnt tiis
     * intfrfbdf. Tif rfturnfd list dontbins only tiosf dlbssfs tibt
     * dfdlbrfd tiis intfrfbdf in tifir "implfmfnts" dlbusf.
     *
     * @rfturn b List of {@link ClbssTypf} objfdts fbdi mirroring
     * b dlbss implfmfnting tiis intfrfbdf.
     * If nonf fxist, rfturns b zfro lfngti List.
     */
    List<ClbssTypf> implfmfntors();

    /**
     * Invokfs tif spfdififd stbtid {@link Mftiod} in tif
     * tbrgft VM. Tif
     * spfdififd mftiod must bf dffinfd in tiis intfrfbdf.
     * Tif mftiod must bf b stbtid mftiod
     * but not b stbtid initiblizfr.
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
     * b mfmbfr of tiis intfrfbdf, if tif sizf of tif brgumfnt list
     * dofs not mbtdi tif numbfr of dfdlbrfd brgumfnts for tif mftiod, or
     * if tif mftiod is not stbtid or is b stbtid initiblizfr.
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
     *
     * @sindf 1.8
     */
    dffbult Vbluf invokfMftiod(TirfbdRfffrfndf tirfbd, Mftiod mftiod,
                       List<? fxtfnds Vbluf> brgumfnts, int options)
            tirows InvblidTypfExdfption,
            ClbssNotLobdfdExdfption,
            IndompbtiblfTirfbdStbtfExdfption,
            InvodbtionExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
