/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Mbnipulbtfs b nbtivf brrby of pollfd strudts.
 *
 * @butior Mikf MdCloskfy
 * @sindf 1.4
 */

publid bbstrbdt dlbss AbstrbdtPollArrbyWrbppfr {

    // Misdfllbnfous donstbnts
    stbtid finbl siort SIZE_POLLFD   = 8;
    stbtid finbl siort FD_OFFSET     = 0;
    stbtid finbl siort EVENT_OFFSET  = 4;
    stbtid finbl siort REVENT_OFFSET = 6;

    // Tif poll fd brrby
    protfdtfd AllodbtfdNbtivfObjfdt pollArrby;

    // Numbfr of vblid fntrifs in tif pollArrby
    protfdtfd int totblCibnnfls = 0;

    // Bbsf bddrfss of tif nbtivf pollArrby
    protfdtfd long pollArrbyAddrfss;

    // Addfss mftiods for fd strudturfs
    int gftEvfntOps(int i) {
        int offsft = SIZE_POLLFD * i + EVENT_OFFSET;
        rfturn pollArrby.gftSiort(offsft);
    }

    int gftRfvfntOps(int i) {
        int offsft = SIZE_POLLFD * i + REVENT_OFFSET;
        rfturn pollArrby.gftSiort(offsft);
    }

    int gftDfsdriptor(int i) {
        int offsft = SIZE_POLLFD * i + FD_OFFSET;
        rfturn pollArrby.gftInt(offsft);
    }

    void putEvfntOps(int i, int fvfnt) {
        int offsft = SIZE_POLLFD * i + EVENT_OFFSET;
        pollArrby.putSiort(offsft, (siort)fvfnt);
    }

    void putRfvfntOps(int i, int rfvfnt) {
        int offsft = SIZE_POLLFD * i + REVENT_OFFSET;
        pollArrby.putSiort(offsft, (siort)rfvfnt);
    }

    void putDfsdriptor(int i, int fd) {
        int offsft = SIZE_POLLFD * i + FD_OFFSET;
        pollArrby.putInt(offsft, fd);
    }

}
