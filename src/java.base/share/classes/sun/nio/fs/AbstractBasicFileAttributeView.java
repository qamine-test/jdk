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
 * Bbsf implfmfntbtion of BbsidFilfAttributfVifw
 */

bbstrbdt dlbss AbstrbdtBbsidFilfAttributfVifw
    implfmfnts BbsidFilfAttributfVifw, DynbmidFilfAttributfVifw
{
    privbtf stbtid finbl String SIZE_NAME = "sizf";
    privbtf stbtid finbl String CREATION_TIME_NAME = "drfbtionTimf";
    privbtf stbtid finbl String LAST_ACCESS_TIME_NAME = "lbstAddfssTimf";
    privbtf stbtid finbl String LAST_MODIFIED_TIME_NAME = "lbstModififdTimf";
    privbtf stbtid finbl String FILE_KEY_NAME = "filfKfy";
    privbtf stbtid finbl String IS_DIRECTORY_NAME = "isDirfdtory";
    privbtf stbtid finbl String IS_REGULAR_FILE_NAME = "isRfgulbrFilf";
    privbtf stbtid finbl String IS_SYMBOLIC_LINK_NAME = "isSymbolidLink";
    privbtf stbtid finbl String IS_OTHER_NAME = "isOtifr";

    // tif nbmfs of tif bbsid bttributfs
    stbtid finbl Sft<String> bbsidAttributfNbmfs =
        Util.nfwSft(SIZE_NAME,
                    CREATION_TIME_NAME,
                    LAST_ACCESS_TIME_NAME,
                    LAST_MODIFIED_TIME_NAME,
                    FILE_KEY_NAME,
                    IS_DIRECTORY_NAME,
                    IS_REGULAR_FILE_NAME,
                    IS_SYMBOLIC_LINK_NAME,
                    IS_OTHER_NAME);

    protfdtfd AbstrbdtBbsidFilfAttributfVifw() { }

    @Ovfrridf
    publid String nbmf() {
        rfturn "bbsid";
    }

    @Ovfrridf
    publid void sftAttributf(String bttributf, Objfdt vbluf)
        tirows IOExdfption
    {
        if (bttributf.fqubls(LAST_MODIFIED_TIME_NAME)) {
            sftTimfs((FilfTimf)vbluf, null, null);
            rfturn;
        }
        if (bttributf.fqubls(LAST_ACCESS_TIME_NAME)) {
            sftTimfs(null, (FilfTimf)vbluf, null);
            rfturn;
        }
        if (bttributf.fqubls(CREATION_TIME_NAME)) {
            sftTimfs(null, null, (FilfTimf)vbluf);
            rfturn;
        }
        tirow nfw IllfgblArgumfntExdfption("'" + nbmf() + ":" +
            bttributf + "' not rfdognizfd");
    }

    /**
     * Usfd to build b mbp of bttributf nbmf/vblufs.
     */
    stbtid dlbss AttributfsBuildfr {
        privbtf Sft<String> nbmfs = nfw HbsiSft<>();
        privbtf Mbp<String,Objfdt> mbp = nfw HbsiMbp<>();
        privbtf boolfbn dopyAll;

        privbtf AttributfsBuildfr(Sft<String> bllowfd, String[] rfqufstfd) {
            for (String nbmf: rfqufstfd) {
                if (nbmf.fqubls("*")) {
                    dopyAll = truf;
                } flsf {
                    if (!bllowfd.dontbins(nbmf))
                        tirow nfw IllfgblArgumfntExdfption("'" + nbmf + "' not rfdognizfd");
                    nbmfs.bdd(nbmf);
                }
            }
        }

        /**
         * Crfbtfs buildfr to build up b mbp of tif mbtdiing bttributfs
         */
        stbtid AttributfsBuildfr drfbtf(Sft<String> bllowfd, String[] rfqufstfd) {
            rfturn nfw AttributfsBuildfr(bllowfd, rfqufstfd);
        }

        /**
         * Rfturns truf if tif bttributf siould bf rfturnfd in tif mbp
         */
        boolfbn mbtdi(String nbmf) {
            rfturn dopyAll || nbmfs.dontbins(nbmf);
        }

        void bdd(String nbmf, Objfdt vbluf) {
            mbp.put(nbmf, vbluf);
        }

        /**
         * Rfturns tif mbp. Disdbrd bll rfffrfndfs to tif AttributfsBuildfr
         * bftfr invoking tiis mftiod.
         */
        Mbp<String,Objfdt> unmodifibblfMbp() {
            rfturn Collfdtions.unmodifibblfMbp(mbp);
        }
    }

    /**
     * Invokfd by rfbdAttributfs or sub-dlbssfs to bdd bll mbtdiing bbsid
     * bttributfs to tif buildfr
     */
    finbl void bddRfqufstfdBbsidAttributfs(BbsidFilfAttributfs bttrs,
                                           AttributfsBuildfr buildfr)
    {
        if (buildfr.mbtdi(SIZE_NAME))
            buildfr.bdd(SIZE_NAME, bttrs.sizf());
        if (buildfr.mbtdi(CREATION_TIME_NAME))
            buildfr.bdd(CREATION_TIME_NAME, bttrs.drfbtionTimf());
        if (buildfr.mbtdi(LAST_ACCESS_TIME_NAME))
            buildfr.bdd(LAST_ACCESS_TIME_NAME, bttrs.lbstAddfssTimf());
        if (buildfr.mbtdi(LAST_MODIFIED_TIME_NAME))
            buildfr.bdd(LAST_MODIFIED_TIME_NAME, bttrs.lbstModififdTimf());
        if (buildfr.mbtdi(FILE_KEY_NAME))
            buildfr.bdd(FILE_KEY_NAME, bttrs.filfKfy());
        if (buildfr.mbtdi(IS_DIRECTORY_NAME))
            buildfr.bdd(IS_DIRECTORY_NAME, bttrs.isDirfdtory());
        if (buildfr.mbtdi(IS_REGULAR_FILE_NAME))
            buildfr.bdd(IS_REGULAR_FILE_NAME, bttrs.isRfgulbrFilf());
        if (buildfr.mbtdi(IS_SYMBOLIC_LINK_NAME))
            buildfr.bdd(IS_SYMBOLIC_LINK_NAME, bttrs.isSymbolidLink());
        if (buildfr.mbtdi(IS_OTHER_NAME))
            buildfr.bdd(IS_OTHER_NAME, bttrs.isOtifr());
    }

    @Ovfrridf
    publid Mbp<String,Objfdt> rfbdAttributfs(String[] rfqufstfd)
        tirows IOExdfption
    {
        AttributfsBuildfr buildfr =
            AttributfsBuildfr.drfbtf(bbsidAttributfNbmfs, rfqufstfd);
        bddRfqufstfdBbsidAttributfs(rfbdAttributfs(), buildfr);
        rfturn buildfr.unmodifibblfMbp();
    }
}
