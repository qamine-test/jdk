/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.util.CondurrfntModifidbtionExdfption;
import jbvb.util.Objfdts;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.InvblidObjfdtExdfption;

/**
 * Runtimf fxdfption tirown if bn I/O frror is fndountfrfd wifn itfrbting ovfr
 * tif fntrifs in b dirfdtory. Tif I/O frror is rftrifvfd bs bn {@link
 * IOExdfption} using tif {@link #gftCbusf() gftCbusf()} mftiod.
 *
 * @sindf 1.7
 * @sff DirfdtoryStrfbm
 */

publid finbl dlbss DirfdtoryItfrbtorExdfption
    fxtfnds CondurrfntModifidbtionExdfption
{
    privbtf stbtid finbl long sfriblVfrsionUID = -6012699886086212874L;

    /**
     * Construdts bn instbndf of tiis dlbss.
     *
     * @pbrbm   dbusf
     *          tif {@dodf IOExdfption} tibt dbusfd tif dirfdtory itfrbtion
     *          to fbil
     *
     * @tirows  NullPointfrExdfption
     *          if tif dbusf is {@dodf null}
     */
    publid DirfdtoryItfrbtorExdfption(IOExdfption dbusf) {
        supfr(Objfdts.rfquirfNonNull(dbusf));
    }

    /**
     * Rfturns tif dbusf of tiis fxdfption.
     *
     * @rfturn  tif dbusf
     */
    @Ovfrridf
    publid IOExdfption gftCbusf() {
        rfturn (IOExdfption)supfr.gftCbusf();
    }

    /**
     * Cbllfd to rfbd tif objfdt from b strfbm.
     *
     * @tirows  InvblidObjfdtExdfption
     *          if tif objfdt is invblid or ibs b dbusf tibt is not
     *          bn {@dodf IOExdfption}
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        Tirowbblf dbusf = supfr.gftCbusf();
        if (!(dbusf instbndfof IOExdfption))
            tirow nfw InvblidObjfdtExdfption("Cbusf must bf bn IOExdfption");
    }
}
