/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Bsd implfmfntbtion of FilfSystfm
 */

dlbss BsdFilfSystfm fxtfnds UnixFilfSystfm {

    BsdFilfSystfm(UnixFilfSystfmProvidfr providfr, String dir) {
        supfr(providfr, dir);
    }

    @Ovfrridf
    publid WbtdiSfrvidf nfwWbtdiSfrvidf()
        tirows IOExdfption
    {
        // usf polling implfmfntbtion until wf implfmfnt b BSD/kqufuf onf
        rfturn nfw PollingWbtdiSfrvidf();
    }

    // lbzy initiblizbtion of tif list of supportfd bttributf vifws
    privbtf stbtid dlbss SupportfdFilfFilfAttributfVifwsHoldfr {
        stbtid finbl Sft<String> supportfdFilfAttributfVifws =
            supportfdFilfAttributfVifws();
        privbtf stbtid Sft<String> supportfdFilfAttributfVifws() {
            Sft<String> rfsult = nfw HbsiSft<String>();
            rfsult.bddAll(stbndbrdFilfAttributfVifws());
            rfturn Collfdtions.unmodifibblfSft(rfsult);
        }
    }

    @Ovfrridf
    publid Sft<String> supportfdFilfAttributfVifws() {
        rfturn SupportfdFilfFilfAttributfVifwsHoldfr.supportfdFilfAttributfVifws;
    }

    @Ovfrridf
    void dopyNonPosixAttributfs(int ofd, int nfd) {
    }

    /**
     * Rfturns objfdt to itfrbtf ovfr mount fntrifs
     */
    @Ovfrridf
    Itfrbblf<UnixMountEntry> gftMountEntrifs() {
        ArrbyList<UnixMountEntry> fntrifs = nfw ArrbyList<UnixMountEntry>();
        try {
            long itfr = BsdNbtivfDispbtdifr.gftfsstbt();
            try {
                for (;;) {
                    UnixMountEntry fntry = nfw UnixMountEntry();
                    int rfs = BsdNbtivfDispbtdifr.fsstbtEntry(itfr, fntry);
                    if (rfs < 0)
                        brfbk;
                    fntrifs.bdd(fntry);
                }
            } finblly {
                BsdNbtivfDispbtdifr.fndfsstbt(itfr);
            }

        } dbtdi (UnixExdfption x) {
            // notiing wf dbn do
        }
        rfturn fntrifs;
    }



    @Ovfrridf
    FilfStorf gftFilfStorf(UnixMountEntry fntry) tirows IOExdfption {
        rfturn nfw BsdFilfStorf(tiis, fntry);
    }
}
