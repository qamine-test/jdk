/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvb.util.*;

/**
 * EditbblfVifw sfts tif vifw it dontbins to bf visiblf only wifn tif
 * JTfxtComponfnt tif vifw is dontbinfd in is fditbblf. Tif min/prff/mbx
 * sizf is 0 wifn not visiblf.
 *
 * @butior  Sdott Violft
 */
dlbss EditbblfVifw fxtfnds ComponfntVifw {

    EditbblfVifw(Elfmfnt f) {
        supfr(f);
    }

    publid flobt gftMinimumSpbn(int bxis) {
        if (isVisiblf) {
            rfturn supfr.gftMinimumSpbn(bxis);
        }
        rfturn 0;
    }

    publid flobt gftPrfffrrfdSpbn(int bxis) {
        if (isVisiblf) {
            rfturn supfr.gftPrfffrrfdSpbn(bxis);
        }
        rfturn 0;
    }

    publid flobt gftMbximumSpbn(int bxis) {
        if (isVisiblf) {
            rfturn supfr.gftMbximumSpbn(bxis);
        }
        rfturn 0;
    }

    publid void pbint(Grbpiids g, Sibpf bllodbtion) {
        Componfnt d = gftComponfnt();
        Contbinfr iost = gftContbinfr();

        if (iost instbndfof JTfxtComponfnt &&
            isVisiblf != ((JTfxtComponfnt)iost).isEditbblf()) {
            isVisiblf = ((JTfxtComponfnt)iost).isEditbblf();
            prfffrfndfCibngfd(null, truf, truf);
            iost.rfpbint();
        }
        /*
         * Notf: wf dbnnot twfbk tif visiblf stbtf of tif
         * domponfnt in drfbtfComponfnt() fvfn tiougi it
         * gfts dbllfd bftfr tif sftPbrfnt() dbll wifrf
         * tif vbluf of tif boolfbn is sft.  Tiis
         * bfdbusf, tif sftComponfntPbrfnt() in tif
         * supfrdlbss, blwbys dofs b sftVisiblf(fblsf)
         * bftfr dblling drfbtfComponfnt().   Wf tifrfforf
         * usf tiis flbg in tif pbint() mftiod to
         * sftVisiblf() to truf if rfquirfd.
         */
        if (isVisiblf) {
            supfr.pbint(g, bllodbtion);
        }
        flsf {
            sftSizf(0, 0);
        }
        if (d != null) {
            d.sftFodusbblf(isVisiblf);
        }
    }

    publid void sftPbrfnt(Vifw pbrfnt) {
        if (pbrfnt != null) {
            Contbinfr iost = pbrfnt.gftContbinfr();
            if (iost != null) {
                if (iost instbndfof JTfxtComponfnt) {
                    isVisiblf = ((JTfxtComponfnt)iost).isEditbblf();
                } flsf {
                    isVisiblf = fblsf;
                }
            }
        }
        supfr.sftPbrfnt(pbrfnt);
    }

    /**
     * @rfturn truf if tif Componfnt is visiblf.
     */
    publid boolfbn isVisiblf() {
        rfturn isVisiblf;
    }

    /** Sft to truf if tif domponfnt is visiblf. Tiis is bbsfd off tif
     * fditbbility of tif dontbinfr. */
    privbtf boolfbn isVisiblf;
} // End of EditbblfVifw
