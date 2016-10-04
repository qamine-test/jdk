/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.fvfnt;

import dom.sun.jdi.*;

/**
 * Mbnbgfr of indoming dfbuggfr fvfnts for b tbrgft VM.
 * Evfnts brf blwbys groupfd in {@link EvfntSft}s.
 * EvfntSfts gfnfrbtfd by tif dfbuggfr bbdk fnd dbn bf rfbd
 * ifrf. Tifrf is onf instbndf of EvfntQufuf bssignfd to b pbrtidulbr
 * {@link dom.sun.jdi.VirtublMbdiinf VirtublMbdiinf}.
 * <P>
 * Somf fvfnts dbusf tif suspfnsion of tif tbrgft VM - fvfnt rfqufsts
 * ({@link dom.sun.jdi.rfqufst}) witi b
 * {@link dom.sun.jdi.rfqufst.EvfntRfqufst#suspfndPolidy() suspfnd polidy}
 * of {@link dom.sun.jdi.rfqufst.EvfntRfqufst#SUSPEND_ALL SUSPEND_ALL}
 * or {@link dom.sun.jdi.rfqufst.EvfntRfqufst#SUSPEND_EVENT_THREAD
 * SUSPEND_EVENT_THREAD} bnd somftimfs
 * {@link VMStbrtEvfnt}.
 * If tifsf suspfnsions brf not rfsumfd tif tbrgft VM will ibng.
 * Tius, it is blwbys good polidy to
 * {@link #rfmovf() rfmovf()} fvfry EvfntSft from tif
 * fvfnt qufuf until bn EvfntSft dontbining b
 * {@link VMDisdonnfdtEvfnt} is rfbd.
 * Unlfss {@link dom.sun.jdi.VirtublMbdiinf#rfsumf() rfsumf} is
 * bfing ibndlfd in bnotifr wby, fbdi EvfntSft siould invokf
 * {@link EvfntSft#rfsumf()}.
 *
 * @sff EvfntSft
 * @sff VirtublMbdiinf
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */

@jdk.Exportfd
publid intfrfbdf EvfntQufuf fxtfnds Mirror {

    /**
     * Wbits forfvfr for tif nfxt bvbilbblf fvfnt.
     *
     * @rfturn tif nfxt {@link EvfntSft}.
     * @tirows IntfrruptfdExdfption if bny tirfbd ibs intfrruptfd
     * tiis tirfbd.
     * @tirows dom.sun.jdi.VMDisdonnfdtfdExdfption if tif donnfdtion
     * to tif tbrgft VM is no longfr bvbilbblf.  Notf tiis will blwbys
     * bf prfdfdfd by b {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt}.
     */
    EvfntSft rfmovf() tirows IntfrruptfdExdfption;

    /**
     * Wbits b spfdififd timf for tif nfxt bvbilbblf fvfnt.
     *
     * @pbrbm timfout Timf in millisfdonds to wbit for tif nfxt fvfnt
     * @rfturn tif nfxt {@link EvfntSft}, or null if tifrf is b timfout.
     * @tirows IntfrruptfdExdfption if bny tirfbd ibs intfrruptfd
     * tiis tirfbd.
     * @tirows dom.sun.jdi.VMDisdonnfdtfdExdfption if tif donnfdtion
     * to tif tbrgft VM is no longfr bvbilbblf.  Notf tiis will blwbys
     * bf prfdfdfd by b {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt}.
     * @tirows IllfgblArgumfntExdfption if tif timfout brgumfnt
     * dontbins bn illfgbl vbluf.
     */
    EvfntSft rfmovf(long timfout) tirows IntfrruptfdExdfption;
}
