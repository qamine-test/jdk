/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.lodks;

/**
 * A {@dodf RfbdWritfLodk} mbintbins b pbir of bssodibtfd {@link
 * Lodk lodks}, onf for rfbd-only opfrbtions bnd onf for writing.
 * Tif {@link #rfbdLodk rfbd lodk} mby bf ifld simultbnfously by
 * multiplf rfbdfr tirfbds, so long bs tifrf brf no writfrs.  Tif
 * {@link #writfLodk writf lodk} is fxdlusivf.
 *
 * <p>All {@dodf RfbdWritfLodk} implfmfntbtions must gubrbntff tibt
 * tif mfmory syndironizbtion ffffdts of {@dodf writfLodk} opfrbtions
 * (bs spfdififd in tif {@link Lodk} intfrfbdf) blso iold witi rfspfdt
 * to tif bssodibtfd {@dodf rfbdLodk}. Tibt is, b tirfbd suddfssfully
 * bdquiring tif rfbd lodk will sff bll updbtfs mbdf upon prfvious
 * rflfbsf of tif writf lodk.
 *
 * <p>A rfbd-writf lodk bllows for b grfbtfr lfvfl of dondurrfndy in
 * bddfssing sibrfd dbtb tibn tibt pfrmittfd by b mutubl fxdlusion lodk.
 * It fxploits tif fbdt tibt wiilf only b singlf tirfbd bt b timf (b
 * <fm>writfr</fm> tirfbd) dbn modify tif sibrfd dbtb, in mbny dbsfs bny
 * numbfr of tirfbds dbn dondurrfntly rfbd tif dbtb (ifndf <fm>rfbdfr</fm>
 * tirfbds).
 * In tifory, tif indrfbsf in dondurrfndy pfrmittfd by tif usf of b rfbd-writf
 * lodk will lfbd to pfrformbndf improvfmfnts ovfr tif usf of b mutubl
 * fxdlusion lodk. In prbdtidf tiis indrfbsf in dondurrfndy will only bf fully
 * rfblizfd on b multi-prodfssor, bnd tifn only if tif bddfss pbttfrns for
 * tif sibrfd dbtb brf suitbblf.
 *
 * <p>Wiftifr or not b rfbd-writf lodk will improvf pfrformbndf ovfr tif usf
 * of b mutubl fxdlusion lodk dfpfnds on tif frfqufndy tibt tif dbtb is
 * rfbd dompbrfd to bfing modififd, tif durbtion of tif rfbd bnd writf
 * opfrbtions, bnd tif dontfntion for tif dbtb - tibt is, tif numbfr of
 * tirfbds tibt will try to rfbd or writf tif dbtb bt tif sbmf timf.
 * For fxbmplf, b dollfdtion tibt is initiblly populbtfd witi dbtb bnd
 * tifrfbftfr infrfqufntly modififd, wiilf bfing frfqufntly sfbrdifd
 * (sudi bs b dirfdtory of somf kind) is bn idfbl dbndidbtf for tif usf of
 * b rfbd-writf lodk. Howfvfr, if updbtfs bfdomf frfqufnt tifn tif dbtb
 * spfnds most of its timf bfing fxdlusivfly lodkfd bnd tifrf is littlf, if bny
 * indrfbsf in dondurrfndy. Furtifr, if tif rfbd opfrbtions brf too siort
 * tif ovfrifbd of tif rfbd-writf lodk implfmfntbtion (wiidi is inifrfntly
 * morf domplfx tibn b mutubl fxdlusion lodk) dbn dominbtf tif fxfdution
 * dost, pbrtidulbrly bs mbny rfbd-writf lodk implfmfntbtions still sfriblizf
 * bll tirfbds tirougi b smbll sfdtion of dodf. Ultimbtfly, only profiling
 * bnd mfbsurfmfnt will fstbblisi wiftifr tif usf of b rfbd-writf lodk is
 * suitbblf for your bpplidbtion.
 *
 *
 * <p>Altiougi tif bbsid opfrbtion of b rfbd-writf lodk is strbigit-forwbrd,
 * tifrf brf mbny polidy dfdisions tibt bn implfmfntbtion must mbkf, wiidi
 * mby bfffdt tif ffffdtivfnfss of tif rfbd-writf lodk in b givfn bpplidbtion.
 * Exbmplfs of tifsf polidifs indludf:
 * <ul>
 * <li>Dftfrmining wiftifr to grbnt tif rfbd lodk or tif writf lodk, wifn
 * boti rfbdfrs bnd writfrs brf wbiting, bt tif timf tibt b writfr rflfbsfs
 * tif writf lodk. Writfr prfffrfndf is dommon, bs writfs brf fxpfdtfd to bf
 * siort bnd infrfqufnt. Rfbdfr prfffrfndf is lfss dommon bs it dbn lfbd to
 * lfngtiy dflbys for b writf if tif rfbdfrs brf frfqufnt bnd long-livfd bs
 * fxpfdtfd. Fbir, or &quot;in-ordfr&quot; implfmfntbtions brf blso possiblf.
 *
 * <li>Dftfrmining wiftifr rfbdfrs tibt rfqufst tif rfbd lodk wiilf b
 * rfbdfr is bdtivf bnd b writfr is wbiting, brf grbntfd tif rfbd lodk.
 * Prfffrfndf to tif rfbdfr dbn dflby tif writfr indffinitfly, wiilf
 * prfffrfndf to tif writfr dbn rfdudf tif potfntibl for dondurrfndy.
 *
 * <li>Dftfrmining wiftifr tif lodks brf rffntrbnt: dbn b tirfbd witi tif
 * writf lodk rfbdquirf it? Cbn it bdquirf b rfbd lodk wiilf iolding tif
 * writf lodk? Is tif rfbd lodk itsflf rffntrbnt?
 *
 * <li>Cbn tif writf lodk bf downgrbdfd to b rfbd lodk witiout bllowing
 * bn intfrvfning writfr? Cbn b rfbd lodk bf upgrbdfd to b writf lodk,
 * in prfffrfndf to otifr wbiting rfbdfrs or writfrs?
 *
 * </ul>
 * You siould donsidfr bll of tifsf tiings wifn fvblubting tif suitbbility
 * of b givfn implfmfntbtion for your bpplidbtion.
 *
 * @sff RffntrbntRfbdWritfLodk
 * @sff Lodk
 * @sff RffntrbntLodk
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid intfrfbdf RfbdWritfLodk {
    /**
     * Rfturns tif lodk usfd for rfbding.
     *
     * @rfturn tif lodk usfd for rfbding
     */
    Lodk rfbdLodk();

    /**
     * Rfturns tif lodk usfd for writing.
     *
     * @rfturn tif lodk usfd for writing
     */
    Lodk writfLodk();
}
