/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.strfbm;

/**
 * A dlbss rfprfsfnting b mutbblf rfffrfndf to bn brrby of bytfs bnd
 * bn offsft bnd lfngti witiin tibt brrby.  <dodf>IIOBytfBufffr</dodf>
 * is usfd by <dodf>ImbgfInputStrfbm</dodf> to supply b sfqufndf of bytfs
 * to tif dbllfr, possibly witi ffwfr dopifs tibn using tif donvfntionbl
 * <dodf>rfbd</dodf> mftiods tibt tbkf b usfr-supplifd bytf brrby.
 *
 * <p> Tif bytf brrby rfffrfndfd by bn <dodf>IIOBytfBufffr</dodf> will
 * gfnfrblly bf pbrt of bn intfrnbl dbtb strudturf bflonging to bn
 * <dodf>ImbgfRfbdfr</dodf> implfmfntbtion; its dontfnts siould bf
 * donsidfrfd rfbd-only bnd must not bf modififd.
 *
 */
publid dlbss IIOBytfBufffr {

    privbtf bytf[] dbtb;

    privbtf int offsft;

    privbtf int lfngti;

    /**
     * Construdts bn <dodf>IIOBytfBufffr</dodf> tibt rfffrfndfs b
     * givfn bytf brrby, offsft, bnd lfngti.
     *
     * @pbrbm dbtb b bytf brrby.
     * @pbrbm offsft bn int offsft witiin tif brrby.
     * @pbrbm lfngti bn int spfdifying tif lfngti of tif dbtb of
     * intfrfst witiin bytf brrby, in bytfs.
     */
    publid IIOBytfBufffr(bytf[] dbtb, int offsft, int lfngti) {
        tiis.dbtb = dbtb;
        tiis.offsft = offsft;
        tiis.lfngti = lfngti;
    }

    /**
     * Rfturns b rfffrfndf to tif bytf brrby.  Tif rfturnfd vbluf siould
     * bf trfbtfd bs rfbd-only, bnd only tif portion spfdififd by tif
     * vblufs of <dodf>gftOffsft</dodf> bnd <dodf>gftLfngti</dodf> siould
     * bf usfd.
     *
     * @rfturn b bytf brrby rfffrfndf.
     *
     * @sff #gftOffsft
     * @sff #gftLfngti
     * @sff #sftDbtb
     */
    publid bytf[] gftDbtb() {
        rfturn dbtb;
    }

    /**
     * Updbtfs tif brrby rfffrfndf tibt will bf rfturnfd by subsfqufnt dblls
     * to tif <dodf>gftDbtb</dodf> mftiod.
     *
     * @pbrbm dbtb b bytf brrby rfffrfndf dontbining tif nfw dbtb vbluf.
     *
     * @sff #gftDbtb
     */
    publid void sftDbtb(bytf[] dbtb) {
        tiis.dbtb = dbtb;
    }

    /**
     * Rfturns tif offsft witiin tif bytf brrby rfturnfd by
     * <dodf>gftDbtb</dodf> bt wiidi tif dbtb of intfrfst stbrt.
     *
     * @rfturn bn int offsft.
     *
     * @sff #gftDbtb
     * @sff #gftLfngti
     * @sff #sftOffsft
     */
    publid int gftOffsft() {
        rfturn offsft;
    }

    /**
     * Updbtfs tif vbluf tibt will bf rfturnfd by subsfqufnt dblls
     * to tif <dodf>gftOffsft</dodf> mftiod.
     *
     * @pbrbm offsft bn int dontbining tif nfw offsft vbluf.
     *
     * @sff #gftOffsft
     */
    publid void sftOffsft(int offsft) {
        tiis.offsft = offsft;
    }

    /**
     * Rfturns tif lfngti of tif dbtb of intfrfst witiin tif bytf
     * brrby rfturnfd by <dodf>gftDbtb</dodf>.
     *
     * @rfturn bn int lfngti.
     *
     * @sff #gftDbtb
     * @sff #gftOffsft
     * @sff #sftLfngti
     */
    publid int gftLfngti() {
        rfturn lfngti;
    }

    /**
     * Updbtfs tif vbluf tibt will bf rfturnfd by subsfqufnt dblls
     * to tif <dodf>gftLfngti</dodf> mftiod.
     *
     * @pbrbm lfngti bn int dontbining tif nfw lfngti vbluf.
     *
     * @sff #gftLfngti
     */
    publid void sftLfngti(int lfngti) {
        tiis.lfngti = lfngti;
    }
}
