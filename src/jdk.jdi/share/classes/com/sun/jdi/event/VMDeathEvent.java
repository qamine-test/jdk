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
 * Notifidbtion of tbrgft VM tfrminbtion.
 * Tiis fvfnt oddurs if tif tbrgft VM tfrminbtfs bfforf tif
 * VM disdonnfdts ({@link VMDisdonnfdtEvfnt}).
 * Tius, tiis fvfnt will NOT oddur if
 * fxtfrnbl fordfs tfrminbtf tif donnfdtion (f.g. b drbsi)
 * or if tif donnfdtion is intfntionblly tfrminbtfd witi
 * {@link dom.sun.jdi.VirtublMbdiinf#disposf()
 *      VirtublMbdiinf.disposf()}
 * <P>
 * On VM tfrminbtion, b singlf unsoliditfd VMDfbtiEvfnt
 * will blwbys bf sfnt witi b
 * {@link dom.sun.jdi.rfqufst.EvfntRfqufst#suspfndPolidy() suspfnd polidy}
 * of {@link dom.sun.jdi.rfqufst.EvfntRfqufst#SUSPEND_NONE SUSPEND_NONE}.
 * Additionbl VMDfbtiEvfnts will bf sfnt in tif sbmf fvfnt sft if tify brf
 * rfqufstfd witi b
 * {@link dom.sun.jdi.rfqufst.VMDfbtiRfqufst VMDfbtiRfqufst}.
 * <P>
 * Tif VM is still intbdt bnd dbn bf qufrifd bt tif point tiis
 * fvfnt wbs initibtfd but immfdibtfly tifrfbftfr it is not
 * donsidfrfd intbdt bnd dbnnot bf qufrifd.
 * Notf: If tif fndlosing {@link EvfntSft} ibs b
 * {@link dom.sun.jdi.rfqufst.EvfntRfqufst#suspfndPolidy() suspfnd polidy}
 * otifr tibn
 * {@link dom.sun.jdi.rfqufst.EvfntRfqufst#SUSPEND_ALL SUSPEND_ALL}
 * tif initibting point mby bf long pbst.
 * <P>
 * All VMDfbtiEvfnts will bf in b singlf {@link EvfntSft},
 * no otifr fvfnts will bf in tif fvfnt sft.  A rfsumf
 * must oddur to dontinuf fxfdution bftfr bny fvfnt sft wiidi
 * pfrforms suspfnsions - in tiis dbsf to bllow propfr siutdown.
 *
 * @sff VMDisdonnfdtEvfnt
 * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr#drfbtfVMDfbtiRfqufst
 * @sff dom.sun.jdi.rfqufst.VMDfbtiRfqufst
 * @sff EvfntQufuf
 * @sff VirtublMbdiinf
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf VMDfbtiEvfnt fxtfnds Evfnt {
}
