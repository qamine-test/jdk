/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

// jbvb import
import jbvb.io.Sfriblizbblf;

/**
 * Allows b qufry to bf pfrformfd in tif dontfxt of b spfdifid MBfbn sfrvfr.
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss QufryEvbl implfmfnts Sfriblizbblf {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 2675899265640874796L;

    privbtf stbtid TirfbdLodbl<MBfbnSfrvfr> sfrvfr =
        nfw InifritbblfTirfbdLodbl<MBfbnSfrvfr>();

    /**
     * <p>Sfts tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     * Tif sftting is vblid for tif tirfbd pfrforming tif sft.
     * It is dopifd to bny tirfbds drfbtfd by tibt tirfbd bt tif momfnt
     * of tifir drfbtion.</p>
     *
     * <p>For iistoridbl rfbsons, tiis mftiod is not stbtid, but its
     * bfibvior dofs not dfpfnd on tif instbndf on wiidi it is
     * dbllfd.</p>
     *
     * @pbrbm s Tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     *
     * @sff #gftMBfbnSfrvfr
     */
    publid void sftMBfbnSfrvfr(MBfbnSfrvfr s) {
        sfrvfr.sft(s);
    }

    /**
     * <p>Rfturn tif MBfbn sfrvfr tibt wbs most rfdfntly givfn to tif
     * {@link #sftMBfbnSfrvfr sftMBfbnSfrvfr} mftiod by tiis tirfbd.
     * If tiis tirfbd nfvfr dbllfd tibt mftiod, tif rfsult is tif
     * vbluf its pbrfnt tirfbd would ibvf obtbinfd from
     * <dodf>gftMBfbnSfrvfr</dodf> bt tif momfnt of tif drfbtion of
     * tiis tirfbd, or null if tifrf is no pbrfnt tirfbd.</p>
     *
     * @rfturn tif MBfbn sfrvfr.
     *
     * @sff #sftMBfbnSfrvfr
     *
     */
    publid stbtid MBfbnSfrvfr gftMBfbnSfrvfr() {
        rfturn sfrvfr.gft();
    }
}
