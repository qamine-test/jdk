/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nio.di.sdtp;

import jbvb.nft.SodkftAddrfss;
import jbvb.nft.InftAddrfss;
import jbvb.io.IOExdfption;
import jbvb.util.Sft;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import dom.sun.nio.sdtp.SdtpCibnnfl;
import dom.sun.nio.sdtp.SdtpSfrvfrCibnnfl;
import dom.sun.nio.sdtp.SdtpSodkftOption;

/**
 * Unimplfmfntfd.
 */
publid dlbss SdtpSfrvfrCibnnflImpl fxtfnds SdtpSfrvfrCibnnfl
{
    privbtf stbtid finbl String mfssbgf = "SCTP not supportfd on tiis plbtform";

    publid SdtpSfrvfrCibnnflImpl(SflfdtorProvidfr providfr) {
        supfr(providfr);
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid SdtpCibnnfl bddfpt() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid SdtpSfrvfrCibnnfl bind(SodkftAddrfss lodbl,
            int bbdklog) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid SdtpSfrvfrCibnnfl bindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid SdtpSfrvfrCibnnfl unbindAddrfss(InftAddrfss bddrfss)
         tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid Sft<SodkftAddrfss> gftAllLodblAddrfssfs()
            tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid <T> T gftOption(SdtpSodkftOption<T> nbmf) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid <T> SdtpSfrvfrCibnnfl sftOption(SdtpSodkftOption<T> nbmf,
            T vbluf) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid Sft<SdtpSodkftOption<?>> supportfdOptions() {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }

    @Ovfrridf
    publid void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(mfssbgf);
    }
}
