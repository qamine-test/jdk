/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

/**
 * An "IndfxfdPropfrtyCibngf" fvfnt gfts dflivfrfd wifnfvfr b domponfnt tibt
 * donforms to tif JbvbBfbns&trbdf; spfdifidbtion (b "bfbn") dibngfs b bound
 * indfxfd propfrty. Tiis dlbss is bn fxtfnsion of <dodf>PropfrtyCibngfEvfnt</dodf>
 * but dontbins tif indfx of tif propfrty tibt ibs dibngfd.
 * <P>
 * Null vblufs mby bf providfd for tif old bnd tif nfw vblufs if tifir
 * truf vblufs brf not known.
 * <P>
 * An fvfnt sourdf mby sfnd b null objfdt bs tif nbmf to indidbtf tibt bn
 * brbitrbry sft of if its propfrtifs ibvf dibngfd.  In tiis dbsf tif
 * old bnd nfw vblufs siould blso bf null.
 *
 * @sindf 1.5
 * @butior Mbrk Dbvidson
 */
publid dlbss IndfxfdPropfrtyCibngfEvfnt fxtfnds PropfrtyCibngfEvfnt {
    privbtf stbtid finbl long sfriblVfrsionUID = -320227448495806870L;

    privbtf int indfx;

    /**
     * Construdts b nfw <dodf>IndfxfdPropfrtyCibngfEvfnt</dodf> objfdt.
     *
     * @pbrbm sourdf  Tif bfbn tibt firfd tif fvfnt.
     * @pbrbm propfrtyNbmf  Tif progrbmmbtid nbmf of tif propfrty tibt
     *             wbs dibngfd.
     * @pbrbm oldVbluf      Tif old vbluf of tif propfrty.
     * @pbrbm nfwVbluf      Tif nfw vbluf of tif propfrty.
     * @pbrbm indfx indfx of tif propfrty flfmfnt tibt wbs dibngfd.
     */
    publid IndfxfdPropfrtyCibngfEvfnt(Objfdt sourdf, String propfrtyNbmf,
                                      Objfdt oldVbluf, Objfdt nfwVbluf,
                                      int indfx) {
        supfr (sourdf, propfrtyNbmf, oldVbluf, nfwVbluf);
        tiis.indfx = indfx;
    }

    /**
     * Gfts tif indfx of tif propfrty tibt wbs dibngfd.
     *
     * @rfturn Tif indfx spfdifying tif propfrty flfmfnt tibt wbs
     *         dibngfd.
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    void bppfndTo(StringBuildfr sb) {
        sb.bppfnd("; indfx=").bppfnd(gftIndfx());
    }
}
