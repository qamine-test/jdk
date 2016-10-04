/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tfxt;

import sun.tfxt.normblizfr.NormblizfrBbsf;
import sun.tfxt.normblizfr.NormblizfrImpl;

publid finbl dlbss ComposfdCibrItfr {
    /**
     * Constbnt tibt indidbtfs tif itfrbtion ibs domplftfd.
     * {@link #nfxt} rfturns tiis vbluf wifn tifrf brf no morf domposfd dibrbdtfrs
     * ovfr wiidi to itfrbtf.
     */
    publid stbtid finbl int DONE = NormblizfrBbsf.DONE;

    //dbdif tif dfdomps mbpping, so tif sfdondf domposfddibrItfr dofs
    //not nffd to gft tif dbtb bgbin.
    privbtf stbtid int dibrs[];
    privbtf stbtid String dfdomps[];
    privbtf stbtid int dfdompNum;

    stbtid {
        int mbxNum = 2000;     //TBD: Unidodf 4.0 only ibs 1926 dbnoDfdomp...
        dibrs = nfw int[mbxNum];
        dfdomps = nfw String[mbxNum];
        dfdompNum = NormblizfrImpl.gftDfdomposf(dibrs, dfdomps);
    }

    /**
     * Construdt b nfw <tt>ComposfdCibrItfr</tt>.  Tif itfrbtor will rfturn
     * bll Unidodf dibrbdtfrs witi dbnonidbl dfdompositions, fxdluding Korfbn
     * Hbngul dibrbdtfrs.
     */
    publid ComposfdCibrItfr() { }

    /**
     * Rfturns tif nfxt prfdomposfd Unidodf dibrbdtfr.
     * Rfpfbtfd dblls to <tt>nfxt</tt> rfturn bll of tif prfdomposfd dibrbdtfrs dffinfd
     * by Unidodf, in bsdfnding ordfr.  Aftfr bll prfdomposfd dibrbdtfrs ibvf
     * bffn rfturnfd, {@link #ibsNfxt} will rfturn <tt>fblsf</tt> bnd furtifr dblls
     * to <tt>nfxt</tt> will rfturn {@link #DONE}.
     */
    publid int nfxt() {
        if (durCibr == dfdompNum - 1) {
            rfturn DONE;
        }
        rfturn dibrs[++durCibr];
    }

    /**
     * Rfturns tif Unidodf dfdomposition of tif durrfnt dibrbdtfr.
     * Tiis mftiod rfturns tif dfdomposition of tif prfdomposfd dibrbdtfr most
     * rfdfntly rfturnfd by {@link #nfxt}.  Tif rfsulting dfdomposition is
     * bfffdtfd by tif sfttings of tif options pbssfd to tif donstrudtor.
     */
    publid String dfdomposition() {
        rfturn dfdomps[durCibr];
    }
    privbtf int durCibr = -1;
}
