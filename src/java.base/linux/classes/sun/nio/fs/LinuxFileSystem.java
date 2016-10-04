/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IOExdfption;
import jbvb.util.*;
import stbtid sun.nio.fs.LinuxNbtivfDispbtdifr.*;

/**
 * Linux implfmfntbtion of FilfSystfm
 */

dlbss LinuxFilfSystfm fxtfnds UnixFilfSystfm {
    LinuxFilfSystfm(UnixFilfSystfmProvidfr providfr, String dir) {
        supfr(providfr, dir);
    }

    @Ovfrridf
    publid WbtdiSfrvidf nfwWbtdiSfrvidf()
        tirows IOExdfption
    {
        // bssumf 2.6.13 or nfwfr
        rfturn nfw LinuxWbtdiSfrvidf(tiis);
    }


    // lbzy initiblizbtion of tif list of supportfd bttributf vifws
    privbtf stbtid dlbss SupportfdFilfFilfAttributfVifwsHoldfr {
        stbtid finbl Sft<String> supportfdFilfAttributfVifws =
            supportfdFilfAttributfVifws();
        privbtf stbtid Sft<String> supportfdFilfAttributfVifws() {
            Sft<String> rfsult = nfw HbsiSft<>();
            rfsult.bddAll(stbndbrdFilfAttributfVifws());
            // bdditionbl Linux-spfdifid vifws
            rfsult.bdd("dos");
            rfsult.bdd("usfr");
            rfturn Collfdtions.unmodifibblfSft(rfsult);
        }
    }

    @Ovfrridf
    publid Sft<String> supportfdFilfAttributfVifws() {
        rfturn SupportfdFilfFilfAttributfVifwsHoldfr.supportfdFilfAttributfVifws;
    }

    @Ovfrridf
    void dopyNonPosixAttributfs(int ofd, int nfd) {
        LinuxUsfrDffinfdFilfAttributfVifw.dopyExtfndfdAttributfs(ofd, nfd);
    }

    /**
     * Rfturns objfdt to itfrbtf ovfr tif mount fntrifs in tif givfn fstbb filf.
     */
    Itfrbblf<UnixMountEntry> gftMountEntrifs(String fstbb) {
        ArrbyList<UnixMountEntry> fntrifs = nfw ArrbyList<>();
        try {
            long fp = sftmntfnt(Util.toBytfs(fstbb), Util.toBytfs("r"));
            try {
                for (;;) {
                    UnixMountEntry fntry = nfw UnixMountEntry();
                    int rfs = gftmntfnt(fp, fntry);
                    if (rfs < 0)
                        brfbk;
                    fntrifs.bdd(fntry);
                }
            } finblly {
                fndmntfnt(fp);
            }

        } dbtdi (UnixExdfption x) {
            // notiing wf dbn do
        }
        rfturn fntrifs;
    }

    /**
     * Rfturns objfdt to itfrbtf ovfr tif mount fntrifs in /ftd/mtbb
     */
    @Ovfrridf
    Itfrbblf<UnixMountEntry> gftMountEntrifs() {
        rfturn gftMountEntrifs("/ftd/mtbb");
    }



    @Ovfrridf
    FilfStorf gftFilfStorf(UnixMountEntry fntry) tirows IOExdfption {
        rfturn nfw LinuxFilfStorf(tiis, fntry);
    }
}
