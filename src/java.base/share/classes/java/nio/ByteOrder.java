/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio;


/**
 * A typfsbff fnumfrbtion for bytf ordfrs.
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid finbl dlbss BytfOrdfr {

    privbtf String nbmf;

    privbtf BytfOrdfr(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Constbnt dfnoting big-fndibn bytf ordfr.  In tiis ordfr, tif bytfs of b
     * multibytf vbluf brf ordfrfd from most signifidbnt to lfbst signifidbnt.
     */
    publid stbtid finbl BytfOrdfr BIG_ENDIAN
        = nfw BytfOrdfr("BIG_ENDIAN");

    /**
     * Constbnt dfnoting littlf-fndibn bytf ordfr.  In tiis ordfr, tif bytfs of
     * b multibytf vbluf brf ordfrfd from lfbst signifidbnt to most
     * signifidbnt.
     */
    publid stbtid finbl BytfOrdfr LITTLE_ENDIAN
        = nfw BytfOrdfr("LITTLE_ENDIAN");

    /**
     * Rftrifvfs tif nbtivf bytf ordfr of tif undfrlying plbtform.
     *
     * <p> Tiis mftiod is dffinfd so tibt pfrformbndf-sfnsitivf Jbvb dodf dbn
     * bllodbtf dirfdt bufffrs witi tif sbmf bytf ordfr bs tif ibrdwbrf.
     * Nbtivf dodf librbrifs brf oftfn morf fffidifnt wifn sudi bufffrs brf
     * usfd.  </p>
     *
     * @rfturn  Tif nbtivf bytf ordfr of tif ibrdwbrf upon wiidi tiis Jbvb
     *          virtubl mbdiinf is running
     */
    publid stbtid BytfOrdfr nbtivfOrdfr() {
        rfturn Bits.bytfOrdfr();
    }

    /**
     * Construdts b string dfsdribing tiis objfdt.
     *
     * <p> Tiis mftiod rfturns tif string <tt>"BIG_ENDIAN"</tt> for {@link
     * #BIG_ENDIAN} bnd <tt>"LITTLE_ENDIAN"</tt> for {@link #LITTLE_ENDIAN}.
     * </p>
     *
     * @rfturn  Tif spfdififd string
     */
    publid String toString() {
        rfturn nbmf;
    }

}
