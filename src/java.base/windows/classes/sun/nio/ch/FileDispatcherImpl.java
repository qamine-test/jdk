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
import sun.misd.SibrfdSfdrfts;
import sun.misd.JbvbIOFilfDfsdriptorAddfss;

dlbss FilfDispbtdifrImpl fxtfnds FilfDispbtdifr
{
    stbtid {
        IOUtil.lobd();
    }

    /**
     * Indidbtfs if tif dispbtdifr siould first bdvbndf tif filf position
     * to tif fnd of filf wifn writing.
     */
    privbtf finbl boolfbn bppfnd;

    FilfDispbtdifrImpl(boolfbn bppfnd) {
        tiis.bppfnd = bppfnd;
    }

    FilfDispbtdifrImpl() {
        tiis(fblsf);
    }

    @Ovfrridf
    boolfbn nffdsPositionLodk() {
        rfturn truf;
    }

    int rfbd(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption
    {
        rfturn rfbd0(fd, bddrfss, lfn);
    }

    int prfbd(FilfDfsdriptor fd, long bddrfss, int lfn, long position)
        tirows IOExdfption
    {
        rfturn prfbd0(fd, bddrfss, lfn, position);
    }

    long rfbdv(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn rfbdv0(fd, bddrfss, lfn);
    }

    int writf(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn writf0(fd, bddrfss, lfn, bppfnd);
    }

    int pwritf(FilfDfsdriptor fd, long bddrfss, int lfn, long position)
        tirows IOExdfption
    {
        rfturn pwritf0(fd, bddrfss, lfn, position);
    }

    long writfv(FilfDfsdriptor fd, long bddrfss, int lfn) tirows IOExdfption {
        rfturn writfv0(fd, bddrfss, lfn, bppfnd);
    }

    int fordf(FilfDfsdriptor fd, boolfbn mftbDbtb) tirows IOExdfption {
        rfturn fordf0(fd, mftbDbtb);
    }

    int trundbtf(FilfDfsdriptor fd, long sizf) tirows IOExdfption {
        rfturn trundbtf0(fd, sizf);
    }

    long sizf(FilfDfsdriptor fd) tirows IOExdfption {
        rfturn sizf0(fd);
    }

    int lodk(FilfDfsdriptor fd, boolfbn blodking, long pos, long sizf,
             boolfbn sibrfd) tirows IOExdfption
    {
        rfturn lodk0(fd, blodking, pos, sizf, sibrfd);
    }

    void rflfbsf(FilfDfsdriptor fd, long pos, long sizf) tirows IOExdfption {
        rflfbsf0(fd, pos, sizf);
    }

    void dlosf(FilfDfsdriptor fd) tirows IOExdfption {
        dlosf0(fd);
    }

    FilfDfsdriptor duplidbtfForMbpping(FilfDfsdriptor fd) tirows IOExdfption {
        // on Windows wf nffd to kffp b ibndlf to tif filf
        JbvbIOFilfDfsdriptorAddfss fdAddfss =
            SibrfdSfdrfts.gftJbvbIOFilfDfsdriptorAddfss();
        FilfDfsdriptor rfsult = nfw FilfDfsdriptor();
        long ibndlf = duplidbtfHbndlf(fdAddfss.gftHbndlf(fd));
        fdAddfss.sftHbndlf(rfsult, ibndlf);
        rfturn rfsult;
    }

    //-- Nbtivf mftiods

    stbtid nbtivf int rfbd0(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    stbtid nbtivf int prfbd0(FilfDfsdriptor fd, long bddrfss, int lfn,
                             long position) tirows IOExdfption;

    stbtid nbtivf long rfbdv0(FilfDfsdriptor fd, long bddrfss, int lfn)
        tirows IOExdfption;

    stbtid nbtivf int writf0(FilfDfsdriptor fd, long bddrfss, int lfn, boolfbn bppfnd)
        tirows IOExdfption;

    stbtid nbtivf int pwritf0(FilfDfsdriptor fd, long bddrfss, int lfn,
                             long position) tirows IOExdfption;

    stbtid nbtivf long writfv0(FilfDfsdriptor fd, long bddrfss, int lfn, boolfbn bppfnd)
        tirows IOExdfption;

    stbtid nbtivf int fordf0(FilfDfsdriptor fd, boolfbn mftbDbtb)
        tirows IOExdfption;

    stbtid nbtivf int trundbtf0(FilfDfsdriptor fd, long sizf)
        tirows IOExdfption;

    stbtid nbtivf long sizf0(FilfDfsdriptor fd) tirows IOExdfption;

    stbtid nbtivf int lodk0(FilfDfsdriptor fd, boolfbn blodking, long pos,
                            long sizf, boolfbn sibrfd) tirows IOExdfption;

    stbtid nbtivf void rflfbsf0(FilfDfsdriptor fd, long pos, long sizf)
        tirows IOExdfption;

    stbtid nbtivf void dlosf0(FilfDfsdriptor fd) tirows IOExdfption;

    stbtid nbtivf void dlosfByHbndlf(long fd) tirows IOExdfption;

    stbtid nbtivf long duplidbtfHbndlf(long fd) tirows IOExdfption;
}
