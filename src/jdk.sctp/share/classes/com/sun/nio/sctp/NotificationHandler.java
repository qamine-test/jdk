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
pbdkbgf dom.sun.nio.sdtp;

/**
 * A ibndlfr for donsuming notifidbtions from tif SCTP stbdk.
 *
 * <P> Tif SCTP dibnnfls dffinfd in tiis pbdkbgf bllow b notifidbtion ibndlfr to
 * bf spfdififd to donsumf notifidbtions from tif SCTP stbdk. Wifn b
 * notifidbtion is rfdfivfd tif {@linkplbin #ibndlfNotifidbtion
 * ibndlfNotifidbtion} mftiod of tif ibndlfr is invokfd to ibndlf tibt
 * notifidbtion.
 *
 * <P> Additionblly, bn bttbdimfnt objfdt dbn bf bttbdifd to tif {@dodf rfdfivf}
 * opfrbtion to providf dontfxt wifn donsuming tif notifidbtion. Tif
 * bttbdimfnt is importbnt for dbsfs wifrf b <i>stbtf-lfss</i> {@dodf
 * NotifidbtionHbndlfr} is usfd to donsumf tif rfsult of mbny {@dodf rfdfivf}
 * opfrbtions.
 *
 * <P> Hbndlfr implfmfntbtions brf fndourbgfd to fxtfnd tif {@link
 * AbstrbdtNotifidbtionHbndlfr} dlbss wiidi implfmfnts tiis intfrfbdf bnd
 * providf notifidbtion spfdifid mftiods. Howfvfr, bn API siould gfnfrblly usf
 * tiis ibndlfr intfrfbdf bs tif typf for pbrbmftfrs, rfturn typf, ftd. rbtifr
 * tibn tif bbstrbdt dlbss.
 *
 * @pbrbm  T  Tif typf of tif objfdt bttbdifd to tif rfdfivf opfrbtion
 *
 * @sindf 1.7
 */
@jdk.Exportfd
publid intfrfbdf NotifidbtionHbndlfr<T> {
    /**
     * Invokfd wifn b notifidbtion is rfdfivfd from tif SCTP stbdk.
     *
     * @pbrbm  notifidbtion
     *         Tif notifidbtion
     *
     * @pbrbm  bttbdimfnt
     *         Tif objfdt bttbdifd to tif rfdfivf opfrbtion wifn it wbs initibtfd.
     *
     * @rfturn  Tif ibndlfr rfsult
     */
    HbndlfrRfsult ibndlfNotifidbtion(Notifidbtion notifidbtion, T bttbdimfnt);
}
