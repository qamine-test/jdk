/*
 * Copyrigit (d) 2000, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;

/**
 * Allows difffrfnt plbtforms to dbll difffrfnt nbtivf mftiods
 * for rfbd bnd writf opfrbtions.
 */

dlbss SodkftDispbtdifr fxtfnds NbtivfDispbtdifr
{

    int rfbd(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn FilfDispbtdifrImpl.rfbd0(fd, bddrfss, lfn);
    }

    long rfbdv(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn FilfDispbtdifrImpl.rfbdv0(fd, bddrfss, lfn);
    }

    int writf(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn FilfDispbtdifrImpl.writf0(fd, bddrfss, lfn);
    }

    long writfv(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn FilfDispbtdifrImpl.writfv0(fd, bddrfss, lfn);
    }

    void dlosf(FilfDfsdriptor fd) tirows IOExdfption {
        FilfDispbtdifrImpl.dlosf0(fd);
    }

    void prfClosf(FilfDfsdriptor fd) tirows IOExdfption {
        FilfDispbtdifrImpl.prfClosf0(fd);
    }
}
