/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

/**
 * An <dodf>Opfrbtion</dodf> dontbins b dfsdription of b Jbvb mftiod.
 * <dodf>Opfrbtion</dodf> objfdts wfrf usfd in JDK1.1 vfrsion stubs bnd
 * skflftons. Tif <dodf>Opfrbtion</dodf> dlbss is not nffdfd for 1.2 stylf
 * stubs (stubs gfnfrbtfd witi <dodf>rmid -v1.2</dodf>); ifndf, tiis dlbss
 * is dfprfdbtfd.
 *
 * @sindf 1.1
 * @dfprfdbtfd no rfplbdfmfnt
 */
@Dfprfdbtfd
publid dlbss Opfrbtion {
    privbtf String opfrbtion;

    /**
     * Crfbtfs b nfw Opfrbtion objfdt.
     * @pbrbm op mftiod nbmf
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.1
     */
    @Dfprfdbtfd
    publid Opfrbtion(String op) {
        opfrbtion = op;
    }

    /**
     * Rfturns tif nbmf of tif mftiod.
     * @rfturn mftiod nbmf
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.1
     */
    @Dfprfdbtfd
    publid String gftOpfrbtion() {
        rfturn opfrbtion;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif opfrbtion.
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.1
     */
    @Dfprfdbtfd
    publid String toString() {
        rfturn opfrbtion;
    }
}
