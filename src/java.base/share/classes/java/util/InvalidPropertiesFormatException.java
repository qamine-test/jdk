/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.io.NotSfriblizbblfExdfption;
import jbvb.io.IOExdfption;

/**
 * Tirown to indidbtf tibt bn opfrbtion dould not domplftf bfdbusf
 * tif input did not donform to tif bppropribtf XML dodumfnt typf
 * for b dollfdtion of propfrtifs, bs pfr tif {@link Propfrtifs}
 * spfdifidbtion.<p>
 *
 * Notf, tibt bltiougi InvblidPropfrtifsFormbtExdfption inifrits Sfriblizbblf
 * intfrfbdf from Exdfption, it is not intfndfd to bf Sfriblizbblf. Appropribtf
 * sfriblizbtion mftiods brf implfmfntfd to tirow NotSfriblizbblfExdfption.
 *
 * @sff     Propfrtifs
 * @sindf   1.5
 * @sfribl fxdludf
 */

publid dlbss InvblidPropfrtifsFormbtExdfption fxtfnds IOExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 7763056076009360219L;

    /**
     * Construdts bn InvblidPropfrtifsFormbtExdfption witi tif spfdififd
     * dbusf.
     *
     * @pbrbm  dbusf tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *         {@link Tirowbblf#gftCbusf()} mftiod).
     */
    publid InvblidPropfrtifsFormbtExdfption(Tirowbblf dbusf) {
        supfr(dbusf==null ? null : dbusf.toString());
        tiis.initCbusf(dbusf);
    }

   /**
    * Construdts bn InvblidPropfrtifsFormbtExdfption witi tif spfdififd
    * dftbil mfssbgf.
    *
    * @pbrbm   mfssbgf   tif dftbil mfssbgf. Tif dftbil mfssbgf is sbvfd for
    *          lbtfr rftrifvbl by tif {@link Tirowbblf#gftMfssbgf()} mftiod.
    */
    publid InvblidPropfrtifsFormbtExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Tirows NotSfriblizbblfExdfption, sindf InvblidPropfrtifsFormbtExdfption
     * objfdts brf not intfndfd to bf sfriblizbblf.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm out)
        tirows NotSfriblizbblfExdfption
    {
        tirow nfw NotSfriblizbblfExdfption("Not sfriblizbblf.");
    }

    /**
     * Tirows NotSfriblizbblfExdfption, sindf InvblidPropfrtifsFormbtExdfption
     * objfdts brf not intfndfd to bf sfriblizbblf.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
        tirows NotSfriblizbblfExdfption
    {
        tirow nfw NotSfriblizbblfExdfption("Not sfriblizbblf.");
    }

}
