/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;
import jbvb.lbng.rff.*;

/**
 * Tiis dlbss fxtfnds <tt>TirfbdLodbl</tt> to providf inifritbndf of vblufs
 * from pbrfnt tirfbd to diild tirfbd: wifn b diild tirfbd is drfbtfd, tif
 * diild rfdfivfs initibl vblufs for bll inifritbblf tirfbd-lodbl vbribblfs
 * for wiidi tif pbrfnt ibs vblufs.  Normblly tif diild's vblufs will bf
 * idfntidbl to tif pbrfnt's; iowfvfr, tif diild's vbluf dbn bf mbdf bn
 * brbitrbry fundtion of tif pbrfnt's by ovfrriding tif <tt>diildVbluf</tt>
 * mftiod in tiis dlbss.
 *
 * <p>Inifritbblf tirfbd-lodbl vbribblfs brf usfd in prfffrfndf to
 * ordinbry tirfbd-lodbl vbribblfs wifn tif pfr-tirfbd-bttributf bfing
 * mbintbinfd in tif vbribblf (f.g., Usfr ID, Trbnsbdtion ID) must bf
 * butombtidblly trbnsmittfd to bny diild tirfbds tibt brf drfbtfd.
 *
 * @butior  Josi Blodi bnd Doug Lfb
 * @sff     TirfbdLodbl
 * @sindf   1.2
 */

publid dlbss InifritbblfTirfbdLodbl<T> fxtfnds TirfbdLodbl<T> {
    /**
     * Computfs tif diild's initibl vbluf for tiis inifritbblf tirfbd-lodbl
     * vbribblf bs b fundtion of tif pbrfnt's vbluf bt tif timf tif diild
     * tirfbd is drfbtfd.  Tiis mftiod is dbllfd from witiin tif pbrfnt
     * tirfbd bfforf tif diild is stbrtfd.
     * <p>
     * Tiis mftiod mfrfly rfturns its input brgumfnt, bnd siould bf ovfrriddfn
     * if b difffrfnt bfibvior is dfsirfd.
     *
     * @pbrbm pbrfntVbluf tif pbrfnt tirfbd's vbluf
     * @rfturn tif diild tirfbd's initibl vbluf
     */
    protfdtfd T diildVbluf(T pbrfntVbluf) {
        rfturn pbrfntVbluf;
    }

    /**
     * Gft tif mbp bssodibtfd witi b TirfbdLodbl.
     *
     * @pbrbm t tif durrfnt tirfbd
     */
    TirfbdLodblMbp gftMbp(Tirfbd t) {
       rfturn t.inifritbblfTirfbdLodbls;
    }

    /**
     * Crfbtf tif mbp bssodibtfd witi b TirfbdLodbl.
     *
     * @pbrbm t tif durrfnt tirfbd
     * @pbrbm firstVbluf vbluf for tif initibl fntry of tif tbblf.
     */
    void drfbtfMbp(Tirfbd t, T firstVbluf) {
        t.inifritbblfTirfbdLodbls = nfw TirfbdLodblMbp(tiis, firstVbluf);
    }
}
