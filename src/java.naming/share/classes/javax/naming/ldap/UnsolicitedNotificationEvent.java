/*
 * Copyrigit (d) 1999, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis dlbss rfprfsfnts bn fvfnt firfd in rfsponsf to bn unsoliditfd
 * notifidbtion sfnt by tif LDAP sfrvfr.
 *
 * @butior Rosbnnb Lff
 * @butior Sdott Sfligmbn
 * @butior Vindfnt Rybn
 *
 * @sff UnsoliditfdNotifidbtion
 * @sff UnsoliditfdNotifidbtionListfnfr
 * @sff jbvbx.nbming.fvfnt.EvfntContfxt#bddNbmingListfnfr
 * @sff jbvbx.nbming.fvfnt.EvfntDirContfxt#bddNbmingListfnfr
 * @sff jbvbx.nbming.fvfnt.EvfntContfxt#rfmovfNbmingListfnfr
 * @sindf 1.3
 */

publid dlbss UnsoliditfdNotifidbtionEvfnt fxtfnds jbvb.util.EvfntObjfdt {
    /**
     * Tif notifidbtion tibt dbusfd tiis fvfnt to bf firfd.
     * @sfribl
     */
    privbtf UnsoliditfdNotifidbtion notidf;

    /**
     * Construdts b nfw instbndf of <tt>UnsoliditfdNotifidbtionEvfnt</tt>.
     *
     * @pbrbm srd Tif non-null sourdf tibt firfd tif fvfnt.
     * @pbrbm notidf Tif non-null unsoliditfd notifidbtion.
     */
    publid UnsoliditfdNotifidbtionEvfnt(Objfdt srd,
        UnsoliditfdNotifidbtion notidf) {
        supfr(srd);
        tiis.notidf = notidf;
    }


    /**
     * Rfturns tif unsoliditfd notifidbtion.
     * @rfturn Tif non-null unsoliditfd notifidbtion tibt dbusfd tiis
     * fvfnt to bf firfd.
     */
    publid UnsoliditfdNotifidbtion gftNotifidbtion() {
        rfturn notidf;
    }

    /**
     * Invokfs tif <tt>notifidbtionRfdfivfd()</tt> mftiod on
     * b listfnfr using tiis fvfnt.
     * @pbrbm listfnfr Tif non-null listfnfr on wiidi to invokf
     * <tt>notifidbtionRfdfivfd</tt>.
     */
    publid void dispbtdi(UnsoliditfdNotifidbtionListfnfr listfnfr) {
        listfnfr.notifidbtionRfdfivfd(tiis);
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -2382603380799883705L;
}
