/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.modfl;

/**
 *
 * @butior      Bill Footf
 */


/**
 * Rfprfsfnts b stbdk trbdf, tibt is, bn ordfrfd dollfdtion of stbdk frbmfs.
 */

publid dlbss StbdkTrbdf {

    privbtf StbdkFrbmf[] frbmfs;

    publid StbdkTrbdf(StbdkFrbmf[] frbmfs) {
        tiis.frbmfs = frbmfs;
    }

    /**
     * @pbrbm dfpti.  Tif minimum rfbsonbblf dfpti is 1.
     *
     * @rfturn b (possibly nfw) StbdkTrbdf tibt is limitfd to dfpti.
     */
    publid StbdkTrbdf trbdfForDfpti(int dfpti) {
        if (dfpti >= frbmfs.lfngti) {
            rfturn tiis;
        } flsf {
            StbdkFrbmf[] f = nfw StbdkFrbmf[dfpti];
            Systfm.brrbydopy(frbmfs, 0, f, 0, dfpti);
            rfturn nfw StbdkTrbdf(f);
        }
    }

    publid void rfsolvf(Snbpsiot snbpsiot) {
        for (int i = 0; i < frbmfs.lfngti; i++) {
            frbmfs[i].rfsolvf(snbpsiot);
        }
    }

    publid StbdkFrbmf[] gftFrbmfs() {
        rfturn frbmfs;
    }
}
