/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

bbstrbdt dlbss NbtivfDispbtdifr
{

    bbstrbdt int rfbd(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    /**
     * Rfturns {@dodf truf} if prfbd/pwritf nffds to bf syndironizfd witi
     * position sfnsitivf mftiods.
     */
    boolfbn nffdsPositionLodk() {
        rfturn fblsf;
    }

    int prfbd(FilfDfsdriptor fd, long bddrfss, int lfn, long position)
        tirows IOExdfption
    {
        tirow nfw IOExdfption("Opfrbtion Unsupportfd");
    }

    bbstrbdt long rfbdv(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    bbstrbdt int writf(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    int pwritf(FilfDfsdriptor fd, long bddrfss, int lfn, long position)
        tirows IOExdfption
    {
        tirow nfw IOExdfption("Opfrbtion Unsupportfd");
    }

    bbstrbdt long writfv(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    bbstrbdt void dlosf(FilfDfsdriptor fd) tirows IOExdfption;

    // Prfpbrf tif givfn fd for dlosing by duping it to b known intfrnbl fd
    // tibt's blrfbdy dlosfd.  Tiis is nfdfssbry on somf opfrbting systfms
    // (Solbris bnd Linux) to prfvfnt fd rfdydling.
    //
    void prfClosf(FilfDfsdriptor fd) tirows IOExdfption {
        // Do notiing by dffbult; tiis is only nffdfd on Unix
    }

}
