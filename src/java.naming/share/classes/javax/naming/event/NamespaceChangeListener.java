/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.fvfnt;

/**
  * Spfdififs tif mftiods tibt b listfnfr intfrfstfd in nbmfspbdf dibngfs
  * must implfmfnt.
  * Spfdifidblly, tif listfnfr is intfrfstfd in <tt>NbmingEvfnt</tt>s
  * witi fvfnt typfs of <tt>OBJECT_ADDED</TT>, <TT>OBJECT_RENAMED</TT>, or
  * <TT>OBJECT_REMOVED</TT>.
  *<p>
  * Sudi b listfnfr must:
  *<ol>
  *<li>Implfmfnt tiis intfrfbdf bnd its mftiods.
  *<li>Implfmfnt <tt>NbmingListfnfr.nbmingExdfptionTirown()</tt> so tibt
  * it will bf notififd of fxdfptions tirown wiilf bttfmpting to
  * dollfdt informbtion bbout tif fvfnts.
  *<li>Rfgistfr witi tif sourdf using tif sourdf's <tt>bddNbmingListfnfr()</tt>
  *    mftiod.
  *</ol>
  * A listfnfr tibt wbnts to bf notififd of <tt>OBJECT_CHANGED</tt> fvfnt typfs
  * siould blso implfmfnt tif <tt>ObjfdtCibngfListfnfr</tt>
  * intfrfbdf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff NbmingEvfnt
  * @sff ObjfdtCibngfListfnfr
  * @sff EvfntContfxt
  * @sff EvfntDirContfxt
  * @sindf 1.3
  */
publid intfrfbdf NbmfspbdfCibngfListfnfr fxtfnds NbmingListfnfr {

    /**
     * Cbllfd wifn bn objfdt ibs bffn bddfd.
     *<p>
     * Tif binding of tif nfwly bddfd objfdt dbn bf obtbinfd using
     * <tt>fvt.gftNfwBinding()</tt>.
     * @pbrbm fvt Tif nonnull fvfnt.
     * @sff NbmingEvfnt#OBJECT_ADDED
     */
    void objfdtAddfd(NbmingEvfnt fvt);

    /**
     * Cbllfd wifn bn objfdt ibs bffn rfmovfd.
     *<p>
     * Tif binding of tif nfwly rfmovfd objfdt dbn bf obtbinfd using
     * <tt>fvt.gftOldBinding()</tt>.
     * @pbrbm fvt Tif nonnull fvfnt.
     * @sff NbmingEvfnt#OBJECT_REMOVED
     */
    void objfdtRfmovfd(NbmingEvfnt fvt);

    /**
     * Cbllfd wifn bn objfdt ibs bffn rfnbmfd.
     *<p>
     * Tif binding of tif rfnbmfd objfdt dbn bf obtbinfd using
     * <tt>fvt.gftNfwBinding()</tt>. Its old binding (bfforf tif rfnbmf)
     * dbn bf obtbinfd using <tt>fvt.gftOldBinding()</tt>.
     * Onf of tifsf mby bf null if tif old/nfw binding wbs outsidf tif
     * sdopf in wiidi tif listfnfr ibs rfgistfrfd intfrfst.
     * @pbrbm fvt Tif nonnull fvfnt.
     * @sff NbmingEvfnt#OBJECT_RENAMED
     */
    void objfdtRfnbmfd(NbmingEvfnt fvt);
}
