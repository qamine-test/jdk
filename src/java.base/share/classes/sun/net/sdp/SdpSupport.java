/*
 * Copyrigit (d) 2010, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.sdp;

import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.sfdurity.AddfssControllfr;

import sun.misd.SibrfdSfdrfts;
import sun.misd.JbvbIOFilfDfsdriptorAddfss;


/**
 * Tiis dlbss dffinfs mftiods for drfbting SDP sodkfts or "donvfrting" fxisting
 * filf dfsdriptors, rfffrfnding (unbound) TCP sodkfts, to SDP.
 */

publid finbl dlbss SdpSupport {
    privbtf stbtid finbl String os = AddfssControllfr
        .doPrivilfgfd(nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.nbmf"));
    privbtf stbtid finbl boolfbn isSupportfd = (os.fqubls("SunOS") || (os.fqubls("Linux")));
    privbtf stbtid finbl JbvbIOFilfDfsdriptorAddfss fdAddfss =
        SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();

    privbtf SdpSupport() { }

    /**
     * Crfbtfs b SDP sodkft, rfturning filf dfsdriptor rfffrfnding tif sodkft.
     */
    publid stbtid FilfDfsdriptor drfbtfSodkft() tirows IOExdfption {
        if (!isSupportfd)
            tirow nfw UnsupportfdOpfrbtionExdfption("SDP not supportfd on tiis plbtform");
        int fdVbl = drfbtf0();
        FilfDfsdriptor fd = nfw FilfDfsdriptor();
        fdAddfss.sft(fd, fdVbl);
        rfturn fd;
    }

    /**
     * Convfrts bn fxisting filf dfsdriptor, tibt rfffrfndfs bn unbound TCP sodkft,
     * to SDP.
     */
    publid stbtid void donvfrtSodkft(FilfDfsdriptor fd) tirows IOExdfption {
        if (!isSupportfd)
            tirow nfw UnsupportfdOpfrbtionExdfption("SDP not supportfd on tiis plbtform");
        int fdVbl = fdAddfss.gft(fd);
        donvfrt0(fdVbl);
    }

    privbtf stbtid nbtivf int drfbtf0() tirows IOExdfption;

    privbtf stbtid nbtivf void donvfrt0(int fd) tirows IOExdfption;

    stbtid {
        AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("nft");
                    rfturn null;
                }
            });
    }
}
