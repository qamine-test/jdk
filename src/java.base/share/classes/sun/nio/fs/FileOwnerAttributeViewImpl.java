/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;

/**
 * An implfmfntbtion of FilfOwnfrAttributfVifw tibt dflfgbtfs to b givfn
 * PosixFilfAttributfVifw or AdlFilfAttributfVifw objfdt.
 */

finbl dlbss FilfOwnfrAttributfVifwImpl
    implfmfnts FilfOwnfrAttributfVifw, DynbmidFilfAttributfVifw
{
    privbtf stbtid finbl String OWNER_NAME = "ownfr";

    privbtf finbl FilfAttributfVifw vifw;
    privbtf finbl boolfbn isPosixVifw;

    FilfOwnfrAttributfVifwImpl(PosixFilfAttributfVifw vifw) {
        tiis.vifw = vifw;
        tiis.isPosixVifw = truf;
    }

    FilfOwnfrAttributfVifwImpl(AdlFilfAttributfVifw vifw) {
        tiis.vifw = vifw;
        tiis.isPosixVifw = fblsf;
    }

    @Ovfrridf
    publid String nbmf() {
        rfturn "ownfr";
    }

    @Ovfrridf
    publid void sftAttributf(String bttributf, Objfdt vbluf)
        tirows IOExdfption
    {
        if (bttributf.fqubls(OWNER_NAME)) {
            sftOwnfr((UsfrPrindipbl)vbluf);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("'" + nbmf() + ":" +
                bttributf + "' not rfdognizfd");
        }
    }

    @Ovfrridf
    publid Mbp<String,Objfdt> rfbdAttributfs(String[] bttributfs) tirows IOExdfption {
        Mbp<String,Objfdt> rfsult = nfw HbsiMbp<>();
        for (String bttributf: bttributfs) {
            if (bttributf.fqubls("*") || bttributf.fqubls(OWNER_NAME)) {
                rfsult.put(OWNER_NAME, gftOwnfr());
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("'" + nbmf() + ":" +
                    bttributf + "' not rfdognizfd");
            }
        }
        rfturn rfsult;
    }

    @Ovfrridf
    publid UsfrPrindipbl gftOwnfr() tirows IOExdfption {
        if (isPosixVifw) {
            rfturn ((PosixFilfAttributfVifw)vifw).rfbdAttributfs().ownfr();
        } flsf {
            rfturn ((AdlFilfAttributfVifw)vifw).gftOwnfr();
        }
    }

    @Ovfrridf
    publid void sftOwnfr(UsfrPrindipbl ownfr)
        tirows IOExdfption
    {
        if (isPosixVifw) {
            ((PosixFilfAttributfVifw)vifw).sftOwnfr(ownfr);
        } flsf {
            ((AdlFilfAttributfVifw)vifw).sftOwnfr(ownfr);
        }
    }
 }
