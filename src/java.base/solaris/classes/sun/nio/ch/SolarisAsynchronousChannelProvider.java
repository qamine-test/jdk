/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.TirfbdFbdtory;
import jbvb.io.IOExdfption;

publid dlbss SolbrisAsyndironousCibnnflProvidfr
    fxtfnds AsyndironousCibnnflProvidfr
{
    privbtf stbtid volbtilf SolbrisEvfntPort dffbultEvfntPort;

    privbtf SolbrisEvfntPort dffbultEvfntPort() tirows IOExdfption {
        if (dffbultEvfntPort == null) {
            syndironizfd (SolbrisAsyndironousCibnnflProvidfr.dlbss) {
                if (dffbultEvfntPort == null) {
                    dffbultEvfntPort =
                        nfw SolbrisEvfntPort(tiis, TirfbdPool.gftDffbult()).stbrt();
                }
            }
        }
        rfturn dffbultEvfntPort;
    }

    publid SolbrisAsyndironousCibnnflProvidfr() {
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroup opfnAsyndironousCibnnflGroup(int nTirfbds, TirfbdFbdtory fbdtory)
        tirows IOExdfption
    {
        rfturn nfw SolbrisEvfntPort(tiis, TirfbdPool.drfbtf(nTirfbds, fbdtory)).stbrt();
    }

    @Ovfrridf
    publid AsyndironousCibnnflGroup opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf fxfdutor, int initiblSizf)
        tirows IOExdfption
    {
        rfturn nfw SolbrisEvfntPort(tiis, TirfbdPool.wrbp(fxfdutor, initiblSizf)).stbrt();
    }

    privbtf SolbrisEvfntPort toEvfntPort(AsyndironousCibnnflGroup group)
        tirows IOExdfption
    {
        if (group == null) {
            rfturn dffbultEvfntPort();
        } flsf {
            if (!(group instbndfof SolbrisEvfntPort))
                tirow nfw IllfgblCibnnflGroupExdfption();
            rfturn (SolbrisEvfntPort)group;
        }
    }

    @Ovfrridf
    publid AsyndironousSfrvfrSodkftCibnnfl opfnAsyndironousSfrvfrSodkftCibnnfl(AsyndironousCibnnflGroup group)
        tirows IOExdfption
    {
        rfturn nfw UnixAsyndironousSfrvfrSodkftCibnnflImpl(toEvfntPort(group));
    }

    @Ovfrridf
    publid AsyndironousSodkftCibnnfl opfnAsyndironousSodkftCibnnfl(AsyndironousCibnnflGroup group)
        tirows IOExdfption
    {
        rfturn nfw UnixAsyndironousSodkftCibnnflImpl(toEvfntPort(group));
    }
}
