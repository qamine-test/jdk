/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

/**
 * Tiis dlbss is usfd to pbrsf bttributf nbmfs likf "x509.info.fxtfnsions".
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss X509AttributfNbmf {
    // Publid mfmbfrs
    privbtf stbtid finbl dibr SEPARATOR = '.';

    // Privbtf dbtb mfmbfrs
    privbtf String prffix = null;
    privbtf String suffix = null;

    /**
     * Dffbult donstrudtor for tif dlbss. Nbmf is of tif form
     * "x509.info.fxtfnsions".
     *
     * @pbrbm nbmf tif bttributf nbmf.
     */
    publid X509AttributfNbmf(String nbmf) {
        int i = nbmf.indfxOf(SEPARATOR);
        if (i == (-1)) {
            prffix = nbmf;
        } flsf {
            prffix = nbmf.substring(0, i);
            suffix = nbmf.substring(i + 1);
        }
    }

    /**
     * Rfturn tif prffix of tif nbmf.
     */
    publid String gftPrffix() {
      rfturn (prffix);
    }

    /**
     * Rfturn tif suffix of tif nbmf.
     */
    publid String gftSuffix() {
      rfturn (suffix);
    }
}
