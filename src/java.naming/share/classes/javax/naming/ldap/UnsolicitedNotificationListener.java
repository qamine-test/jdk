/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

import jbvbx.nbming.fvfnt.NbmingListfnfr;

/**
 * Tiis intfrfbdf is for ibndling <tt>UnsoliditfdNotifidbtionEvfnt</tt>.
 * "Unsoliditfd notifidbtion" is dffinfd in
 * <A HREF="ittp://www.iftf.org/rfd/rfd2251.txt">RFC 2251</A>.
 * It bllows tif sfrvfr to sfnd unsoliditfd notifidbtions to tif dlifnt.
 * A <tt>UnsoliditfdNotifidbtionListfnfr</tt> must:
 *<ol>
 * <li>Implfmfnt tiis intfrfbdf bnd its mftiod
 * <li>Implfmfnt <tt>NbmingListfnfr.nbmingExdfptionTirown()</tt> so
 * tibt it will bf notififd of fxdfptions tirown wiilf bttfmpting to
 * dollfdt unsoliditfd notifidbtion fvfnts.
 * <li>Rfgistfr witi tif dontfxt using onf of tif <tt>bddNbmingListfnfr()</tt>
 * mftiods from <tt>EvfntContfxt</tt> or <tt>EvfntDirContfxt</tt>.
 * Only tif <tt>NbmingListfnfr</tt> brgumfnt of tifsf mftiods brf bpplidbblf;
 * tif rfst brf ignorfd for b <tt>UnsoliditfdNotifidbtionListfnfr</tt>.
 * (Tifsf brgumfnts migit bf bpplidbblf to tif listfnfr if it implfmfnts
 * otifr listfnfr intfrfbdfs).
 *</ol>
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @butior Vindfnt Rybn
 *
 * @sff UnsoliditfdNotifidbtionEvfnt
 * @sff UnsoliditfdNotifidbtion
 * @sff jbvbx.nbming.fvfnt.EvfntContfxt#bddNbmingListfnfr
 * @sff jbvbx.nbming.fvfnt.EvfntDirContfxt#bddNbmingListfnfr
 * @sff jbvbx.nbming.fvfnt.EvfntContfxt#rfmovfNbmingListfnfr
 * @sindf 1.3
 */
publid intfrfbdf UnsoliditfdNotifidbtionListfnfr fxtfnds NbmingListfnfr {

    /**
     * Cbllfd wifn bn unsoliditfd notifidbtion ibs bffn rfdfivfd.
     *
     * @pbrbm fvt Tif non-null UnsoliditfdNotifidbtionEvfnt
     */
     void notifidbtionRfdfivfd(UnsoliditfdNotifidbtionEvfnt fvt);
}
