/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti.dbllbbdk;

/**
 * <p> Undfrlying sfdurity sfrvidfs instbntibtf bnd pbss b
 * {@dodf TfxtOutputCbllbbdk} to tif {@dodf ibndlf}
 * mftiod of b {@dodf CbllbbdkHbndlfr} to displby informbtion mfssbgfs,
 * wbrning mfssbgfs bnd frror mfssbgfs.
 *
 * @sff jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr
 */
publid dlbss TfxtOutputCbllbbdk implfmfnts Cbllbbdk, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 1689502495511663102L;

    /** Informbtion mfssbgf. */
    publid stbtid finbl int INFORMATION         = 0;
    /** Wbrning mfssbgf. */
    publid stbtid finbl int WARNING             = 1;
    /** Error mfssbgf. */
    publid stbtid finbl int ERROR               = 2;

    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf int mfssbgfTypf;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String mfssbgf;

    /**
     * Construdt b TfxtOutputCbllbbdk witi b mfssbgf typf bnd mfssbgf
     * to bf displbyfd.
     *
     * <p>
     *
     * @pbrbm mfssbgfTypf tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}). <p>
     *
     * @pbrbm mfssbgf tif mfssbgf to bf displbyfd. <p>
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf mfssbgfTypf}
     *                  is not fitifr {@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR},
     *                  if {@dodf mfssbgf} is null,
     *                  or if {@dodf mfssbgf} ibs b lfngti of 0.
     */
    publid TfxtOutputCbllbbdk(int mfssbgfTypf, String mfssbgf) {
        if ((mfssbgfTypf != INFORMATION &&
                mfssbgfTypf != WARNING && mfssbgfTypf != ERROR) ||
            mfssbgf == null || mfssbgf.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption();

        tiis.mfssbgfTypf = mfssbgfTypf;
        tiis.mfssbgf = mfssbgf;
    }

    /**
     * Gft tif mfssbgf typf.
     *
     * <p>
     *
     * @rfturn tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}).
     */
    publid int gftMfssbgfTypf() {
        rfturn mfssbgfTypf;
    }

    /**
     * Gft tif mfssbgf to bf displbyfd.
     *
     * <p>
     *
     * @rfturn tif mfssbgf to bf displbyfd.
     */
    publid String gftMfssbgf() {
        rfturn mfssbgf;
    }
}
