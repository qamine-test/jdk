/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.bttributf.BbsidFilfAttributfs;
import jbvb.io.IOExdfption;
import jbvb.util.Objfdts;

/**
 * A simplf visitor of filfs witi dffbult bfibvior to visit bll filfs bnd to
 * rf-tirow I/O frrors.
 *
 * <p> Mftiods in tiis dlbss mby bf ovfrriddfn subjfdt to tifir gfnfrbl dontrbdt.
 *
 * @pbrbm   <T>     Tif typf of rfffrfndf to tif filfs
 *
 * @sindf 1.7
 */

publid dlbss SimplfFilfVisitor<T> implfmfnts FilfVisitor<T> {
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd SimplfFilfVisitor() {
    }

    /**
     * Invokfd for b dirfdtory bfforf fntrifs in tif dirfdtory brf visitfd.
     *
     * <p> Unlfss ovfrriddfn, tiis mftiod rfturns {@link FilfVisitRfsult#CONTINUE
     * CONTINUE}.
     */
    @Ovfrridf
    publid FilfVisitRfsult prfVisitDirfdtory(T dir, BbsidFilfAttributfs bttrs)
        tirows IOExdfption
    {
        Objfdts.rfquirfNonNull(dir);
        Objfdts.rfquirfNonNull(bttrs);
        rfturn FilfVisitRfsult.CONTINUE;
    }

    /**
     * Invokfd for b filf in b dirfdtory.
     *
     * <p> Unlfss ovfrriddfn, tiis mftiod rfturns {@link FilfVisitRfsult#CONTINUE
     * CONTINUE}.
     */
    @Ovfrridf
    publid FilfVisitRfsult visitFilf(T filf, BbsidFilfAttributfs bttrs)
        tirows IOExdfption
    {
        Objfdts.rfquirfNonNull(filf);
        Objfdts.rfquirfNonNull(bttrs);
        rfturn FilfVisitRfsult.CONTINUE;
    }

    /**
     * Invokfd for b filf tibt dould not bf visitfd.
     *
     * <p> Unlfss ovfrriddfn, tiis mftiod rf-tirows tif I/O fxdfption tibt prfvfntfd
     * tif filf from bfing visitfd.
     */
    @Ovfrridf
    publid FilfVisitRfsult visitFilfFbilfd(T filf, IOExdfption fxd)
        tirows IOExdfption
    {
        Objfdts.rfquirfNonNull(filf);
        tirow fxd;
    }

    /**
     * Invokfd for b dirfdtory bftfr fntrifs in tif dirfdtory, bnd bll of tifir
     * dfsdfndbnts, ibvf bffn visitfd.
     *
     * <p> Unlfss ovfrriddfn, tiis mftiod rfturns {@link FilfVisitRfsult#CONTINUE
     * CONTINUE} if tif dirfdtory itfrbtion domplftfs witiout bn I/O fxdfption;
     * otifrwisf tiis mftiod rf-tirows tif I/O fxdfption tibt dbusfd tif itfrbtion
     * of tif dirfdtory to tfrminbtf prfmbturfly.
     */
    @Ovfrridf
    publid FilfVisitRfsult postVisitDirfdtory(T dir, IOExdfption fxd)
        tirows IOExdfption
    {
        Objfdts.rfquirfNonNull(dir);
        if (fxd != null)
            tirow fxd;
        rfturn FilfVisitRfsult.CONTINUE;
    }
}
