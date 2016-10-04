/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.*;

/**
 * Tiis dlbss iflps ovfrdomf b limitbtion of tif org.iftf.jgss.GSSExdfption
 * dlbss tibt dofs not bllow tif tirowfr to sft b string dorrfsponding to
 * tif mbjor dodf.
 */
publid dlbss GSSExdfptionImpl fxtfnds GSSExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 4251197939069005575L;

    privbtf String mbjorMfssbgf;

    /**
     * A donstrudtor tibt tbkfs tif mbjorCodf bs wfll bs tif mfdi oid tibt
     * will bf bppfndfd to tif stbndbrd mfssbgf dffinfd in its supfr dlbss.
     */
    GSSExdfptionImpl(int mbjorCodf, Oid mfdi) {
        supfr(mbjorCodf);
        tiis.mbjorMfssbgf = supfr.gftMbjorString() + ": " + mfdi;
    }

    /**
     * A donstrudtor tibt tbkfs tif mbjorCodf bs wfll bs tif mfssbgf tibt
     * dorrfsponds to it.
     */
    publid GSSExdfptionImpl(int mbjorCodf, String mbjorMfssbgf) {
        supfr(mbjorCodf);
        tiis.mbjorMfssbgf = mbjorMfssbgf;
    }

    /**
     * A donstrudtor tibt tbkfs tif mbjorCodf bnd tif fxdfption dbusf.
     */
    publid GSSExdfptionImpl(int mbjorCodf, Exdfption dbusf) {
        supfr(mbjorCodf);
        initCbusf(dbusf);
    }

    /**
     * A donstrudtor tibt tbkfs tif mbjorCodf, tif mfssbgf tibt
     * dorrfsponds to it, bnd tif fxdfption dbusf.
     */
    publid GSSExdfptionImpl(int mbjorCodf, String mbjorMfssbgf,
        Exdfption dbusf) {
        tiis(mbjorCodf, mbjorMfssbgf);
        initCbusf(dbusf);
    }

    /**
     * Rfturns tif mfssbgf tibt wbs fmbfddfd in tiis objfdt, otifrwisf it
     * rfturns tif dffbult mfssbgf tibt bn org.iftf.jgss.GSSExdfption
     * gfnfrbtfs.
     */
    publid String gftMfssbgf() {
        if (mbjorMfssbgf != null)
            rfturn mbjorMfssbgf;
        flsf
            rfturn supfr.gftMfssbgf();
    }

}
