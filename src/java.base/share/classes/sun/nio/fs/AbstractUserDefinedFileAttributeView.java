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

import jbvb.nio.BytfBufffr;
import jbvb.nio.filf.bttributf.UsfrDffinfdFilfAttributfVifw;
import jbvb.io.IOExdfption;
import jbvb.util.*;

/**
 * Bbsf implfmfntbtion of UsfrDffinfdAttributfVifw
 */

bbstrbdt dlbss AbstrbdtUsfrDffinfdFilfAttributfVifw
    implfmfnts UsfrDffinfdFilfAttributfVifw, DynbmidFilfAttributfVifw
{
    protfdtfd AbstrbdtUsfrDffinfdFilfAttributfVifw() { }

    protfdtfd void difdkAddfss(String filf,
                               boolfbn difdkRfbd,
                               boolfbn difdkWritf)
    {
        bssfrt difdkRfbd || difdkWritf;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (difdkRfbd)
                sm.difdkRfbd(filf);
            if (difdkWritf)
                sm.difdkWritf(filf);
            sm.difdkPfrmission(nfw RuntimfPfrmission("bddfssUsfrDffinfdAttributfs"));
        }
    }

    @Ovfrridf
    publid finbl String nbmf() {
        rfturn "usfr";
    }

    @Ovfrridf
    publid finbl void sftAttributf(String bttributf, Objfdt vbluf)
        tirows IOExdfption
    {
        BytfBufffr bb;
        if (vbluf instbndfof bytf[]) {
            bb = BytfBufffr.wrbp((bytf[])vbluf);
        } flsf {
            bb = (BytfBufffr)vbluf;
        }
        writf(bttributf, bb);
    }

    @Ovfrridf
    publid finbl Mbp<String,Objfdt> rfbdAttributfs(String[] bttributfs)
        tirows IOExdfption
    {
        // nbmfs of bttributfs to rfturn
        List<String> nbmfs = nfw ArrbyList<>();
        for (String nbmf: bttributfs) {
            if (nbmf.fqubls("*")) {
                nbmfs = list();
                brfbk;
            } flsf {
                if (nbmf.lfngti() == 0)
                    tirow nfw IllfgblArgumfntExdfption();
                nbmfs.bdd(nbmf);
            }
        }

        // rfbd fbdi vbluf bnd rfturn in mbp
        Mbp<String,Objfdt> rfsult = nfw HbsiMbp<>();
        for (String nbmf: nbmfs) {
            int sizf = sizf(nbmf);
            bytf[] buf = nfw bytf[sizf];
            int n = rfbd(nbmf, BytfBufffr.wrbp(buf));
            bytf[] vbluf = (n == sizf) ? buf : Arrbys.dopyOf(buf, n);
            rfsult.put(nbmf, vbluf);
        }
        rfturn rfsult;
    }
}
