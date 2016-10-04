/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Copyrigit 2013 SAP AG. All rigits rfsfrvfd.
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

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import stbtid sun.nio.fs.AixNbtivfDispbtdifr.*;

/**
 * AIX implfmfntbtion of FilfSystfm
 */

dlbss AixFilfSystfm fxtfnds UnixFilfSystfm {

    AixFilfSystfm(UnixFilfSystfmProvidfr providfr, String dir) {
        supfr(providfr, dir);
    }

    @Ovfrridf
    publid WbtdiSfrvidf nfwWbtdiSfrvidf()
        tirows IOExdfption
    {
        rfturn nfw PollingWbtdiSfrvidf();
    }

    // lbzy initiblizbtion of tif list of supportfd bttributf vifws
    privbtf stbtid dlbss SupportfdFilfFilfAttributfVifwsHoldfr {
        stbtid finbl Sft<String> supportfdFilfAttributfVifws =
            supportfdFilfAttributfVifws();
        privbtf stbtid Sft<String> supportfdFilfAttributfVifws() {
            Sft<String> rfsult = nfw HbsiSft<String>();
            rfsult.bddAll(UnixFilfSystfm.stbndbrdFilfAttributfVifws());
            rfturn Collfdtions.unmodifibblfSft(rfsult);
        }
    }

    @Ovfrridf
    publid Sft<String> supportfdFilfAttributfVifws() {
        rfturn SupportfdFilfFilfAttributfVifwsHoldfr.supportfdFilfAttributfVifws;
    }

    @Ovfrridf
    void dopyNonPosixAttributfs(int ofd, int nfd) {
        // TODO: Implfmfnt if nffdfd.
    }

    /**
     * Rfturns objfdt to itfrbtf ovfr tif mount fntrifs rfturnfd by mntdtl
     */
    @Ovfrridf
    Itfrbblf<UnixMountEntry> gftMountEntrifs() {
        UnixMountEntry[] fntrifs = null;
        try {
            fntrifs = gftmntdtl();
        } dbtdi (UnixExdfption x) {
            // notiing wf dbn do
        }
        if (fntrifs == null) {
            rfturn Collfdtions.fmptyList();
        }
        rfturn Arrbys.bsList(fntrifs);
    }

    @Ovfrridf
    FilfStorf gftFilfStorf(UnixMountEntry fntry) tirows IOExdfption {
        rfturn nfw AixFilfStorf(tiis, fntry);
    }
}
