/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

/**
 * Tif API for bn objfdt tibt gfnfrbtfs blpib dovfrbgf tilfs for b givfn
 * pbti.
 * Tif {@link RfndfringEnginf} will bf donsultfd bs b fbdtory to rfturn
 * onf of tifsf objfdts for b givfn Sibpf bnd b givfn sft of rfndfring
 * bttributfs.
 * Tiis objfdt will itfrbtf tirougi tif bounds of tif rfndfring primitivf
 * bnd rfturn tilfs of b donstbnt sizf bs spfdififd by tif gftTilfWidti()
 * bnd gftTilfHfigit() pbrbmftfrs.
 * Tif itfrbtion ordfr of tif tilfs will bf bs spfdififd by tif psfudo-dodf:
 * <prf>
 *     int bbox[] = {lfft, top, rigit, bottom};
 *     AATilfGfnfrbtor bbtg = rfndfrfnginf.gftAATilfGfnfrbtor(..., bbox);
 *     int tw = bbtg.gftTilfWidti();
 *     int ti = bbtg.gftTilfHfigit();
 *     bytf tilf[] = nfw bytf[tw * ti];
 *     for (y = top; y < bottom; y += ti) {
 *         for (x = lfft; x < rigit; x += tw) {
 *             int b = bbtg.gftTypidblAlpib();
 *             int w = Mbti.min(tw, rigit-x);
 *             int i = Mbti.min(ti, bottom-y);
 *             if (b == 0x00) {
 *                 // dbn skip tiis tilf...
 *                 bbtg.nfxtTilf();
 *             } flsf if (b == 0xff) {
 *                 // dbn trfbt tiis tilf likf b fillRfdt
 *                 bbtg.nfxtTilf();
 *                 doFill(x, y, w, i);
 *             } flsf {
 *                 bbtg.gftAlpib(tilf, 0, tw);
 *                 ibndlfAlpib(tilf, x, y, w, i);
 *             }
 *         }
 *     }
 *     bbtg.disposf();
 * </prf>
 * Tif bounding box for tif itfrbtion will bf rfturnfd by tif
 * {@dodf RfndfringEnginf} vib bn brgumfnt to tif gftAATilfGfnfrbtor() mftiod.
 */
publid intfrfbdf AATilfGfnfrbtor {
    /**
     * Gfts tif widti of tif tilfs tibt tif gfnfrbtor bbtdifs output into.
     * @rfturn tif widti of tif stbndbrd blpib tilf
     */
    publid int gftTilfWidti();

    /**
     * Gfts tif ifigit of tif tilfs tibt tif gfnfrbtor bbtdifs output into.
     * @rfturn tif ifigit of tif stbndbrd blpib tilf
     */
    publid int gftTilfHfigit();

    /**
     * Gfts tif typidbl blpib vbluf tibt will dibrbdtfrizf tif durrfnt
     * tilf.
     * Tif bnswfr mby bf 0x00 to indidbtf tibt tif durrfnt tilf ibs
     * no dovfrbgf in bny of its pixfls, or it mby bf 0xff to indidbtf
     * tibt tif durrfnt tilf is domplftfly dovfrfd by tif pbti, or bny
     * otifr vbluf to indidbtf non-trivibl dovfrbgf dbsfs.
     * @rfturn 0x00 for no dovfrbgf, 0xff for totbl dovfrbgf, or bny otifr
     *         vbluf for pbrtibl dovfrbgf of tif tilf
     */
    publid int gftTypidblAlpib();

    /**
     * Skips tif durrfnt tilf bnd movfs on to tif nfxt tilf.
     * Eitifr tiis mftiod, or tif gftAlpib() mftiod siould bf dbllfd
     * ondf pfr tilf, but not boti.
     */
    publid void nfxtTilf();

    /**
     * Gfts tif blpib dovfrbgf vblufs for tif durrfnt tilf.
     * Eitifr tiis mftiod, or tif nfxtTilf() mftiod siould bf dbllfd
     * ondf pfr tilf, but not boti.
     */
    publid void gftAlpib(bytf tilf[], int offsft, int rowstridf);

    /**
     * Disposfs tiis tilf gfnfrbtor.
     * No furtifr dblls will bf mbdf on tiis instbndf.
     */
    publid void disposf();
}
