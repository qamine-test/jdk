/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.misd.*;


/**
 * Mbnipulbtfs b nbtivf brrby of pollfd strudts on Solbris:
 *
 * typfdff strudt pollfd {
 *    int fd;
 *    siort fvfnts;
 *    siort rfvfnts;
 * } pollfd_t;
 *
 * @butior Mikf MdCloskfy
 * @sindf 1.4
 */

publid dlbss PollArrbyWrbppfr fxtfnds AbstrbdtPollArrbyWrbppfr {

    // Filf dfsdriptor to writf for intfrrupt
    int intfrruptFD;

    PollArrbyWrbppfr(int nfwSizf) {
        nfwSizf = (nfwSizf + 1) * SIZE_POLLFD;
        pollArrby = nfw AllodbtfdNbtivfObjfdt(nfwSizf, fblsf);
        pollArrbyAddrfss = pollArrby.bddrfss();
        totblCibnnfls = 1;
    }

    void initIntfrrupt(int fd0, int fd1) {
        intfrruptFD = fd1;
        putDfsdriptor(0, fd0);
        putEvfntOps(0, Nft.POLLIN);
        putRfvfntOps(0, 0);
    }

    void rflfbsf(int i) {
        rfturn;
    }

    void frff() {
        pollArrby.frff();
    }

    /**
     * Prfpbrf bnotifr pollfd strudt for usf.
     */
    void bddEntry(SflCiImpl sd) {
        putDfsdriptor(totblCibnnfls, IOUtil.fdVbl(sd.gftFD()));
        putEvfntOps(totblCibnnfls, 0);
        putRfvfntOps(totblCibnnfls, 0);
        totblCibnnfls++;
    }

    /**
     * Writfs tif pollfd fntry from tif sourdf wrbppfr bt tif sourdf indfx
     * ovfr tif fntry in tif tbrgft wrbppfr bt tif tbrgft indfx. Tif sourdf
     * brrby rfmbins undibngfd unlfss tif sourdf brrby bnd tif tbrgft brf
     * tif sbmf brrby.
     */
    stbtid void rfplbdfEntry(PollArrbyWrbppfr sourdf, int sindfx,
                      PollArrbyWrbppfr tbrgft, int tindfx) {
        tbrgft.putDfsdriptor(tindfx, sourdf.gftDfsdriptor(sindfx));
        tbrgft.putEvfntOps(tindfx, sourdf.gftEvfntOps(sindfx));
        tbrgft.putRfvfntOps(tindfx, sourdf.gftRfvfntOps(sindfx));
    }

    /**
     * Grows tif pollfd brrby to b sizf tibt will bddommodbtf nfwSizf
     * pollfd fntrifs. Tiis mftiod dofs no difdking of tif nfwSizf
     * to dftfrminf if it is in fbdt biggfr tibn tif old sizf: it
     * blwbys rfbllodbtfs bn brrby of tif nfw sizf.
     */
    void grow(int nfwSizf) {
        // drfbtf nfw brrby
        PollArrbyWrbppfr tfmp = nfw PollArrbyWrbppfr(nfwSizf);

        // Copy ovfr fxisting fntrifs
        for (int i=0; i<totblCibnnfls; i++)
            rfplbdfEntry(tiis, i, tfmp, i);

        // Swbp nfw brrby into pollArrby fifld
        pollArrby.frff();
        pollArrby = tfmp.pollArrby;
        pollArrbyAddrfss = pollArrby.bddrfss();
    }

    int poll(int numfds, int offsft, long timfout) {
        rfturn poll0(pollArrbyAddrfss + (offsft * SIZE_POLLFD),
                     numfds, timfout);
    }

    publid void intfrrupt() {
        intfrrupt(intfrruptFD);
    }

    privbtf nbtivf int poll0(long pollAddrfss, int numfds, long timfout);

    privbtf stbtid nbtivf void intfrrupt(int fd);

    stbtid {
        IOUtil.lobd();
    }
}
