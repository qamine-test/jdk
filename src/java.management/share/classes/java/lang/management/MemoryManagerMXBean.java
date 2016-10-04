/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;

/**
 * Tif mbnbgfmfnt intfrfbdf for b mfmory mbnbgfr.
 * A mfmory mbnbgfr mbnbgfs onf or morf mfmory pools of tif
 * Jbvb virtubl mbdiinf.
 *
 * <p> A Jbvb virtubl mbdiinf ibs onf or morf mfmory mbnbgfrs.
 * An instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftMfmoryMbnbgfrMXBfbns} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * b mfmory mbnbgfr witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *   {@link MbnbgfmfntFbdtory#MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:typf=MfmoryMbnbgfr</tt>}<tt>,nbmf=</tt><i>mbnbgfr's nbmf</i>
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * @sff MbnbgfmfntFbdtory#gftPlbtformMXBfbns(Clbss)
 * @sff MfmoryMXBfbn
 *
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion.</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss MXBfbns</b>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
publid intfrfbdf MfmoryMbnbgfrMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif nbmf rfprfsfnting tiis mfmory mbnbgfr.
     *
     * @rfturn tif nbmf of tiis mfmory mbnbgfr.
     */
    publid String gftNbmf();

    /**
     * Tfsts if tiis mfmory mbnbgfr is vblid in tif Jbvb virtubl
     * mbdiinf.  A mfmory mbnbgfr bfdomfs invblid ondf tif Jbvb virtubl
     * mbdiinf rfmovfs it from tif mfmory systfm.
     *
     * @rfturn <tt>truf</tt> if tif mfmory mbnbgfr is vblid in tif
     *               Jbvb virtubl mbdiinf;
     *         <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isVblid();

    /**
     * Rfturns tif nbmf of mfmory pools tibt tiis mfmory mbnbgfr mbnbgfs.
     *
     * @rfturn bn brrby of <tt>String</tt> objfdts, fbdi is
     * tif nbmf of b mfmory pool tibt tiis mfmory mbnbgfr mbnbgfs.
     */
    publid String[] gftMfmoryPoolNbmfs();
}
