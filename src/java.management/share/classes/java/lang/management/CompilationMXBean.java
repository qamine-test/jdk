/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif mbnbgfmfnt intfrfbdf for tif dompilbtion systfm of
 * tif Jbvb virtubl mbdiinf.
 *
 * <p> A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif implfmfntbtion
 * dlbss of tiis intfrfbdf.  Tiis instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftCompilbtionMXBfbn} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * tif dompilbtion systfm witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *  {@link MbnbgfmfntFbdtory#COMPILATION_MXBEAN_NAME
 *         <tt>jbvb.lbng:typf=Compilbtion</tt>}
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * @sff MbnbgfmfntFbdtory#gftPlbtformMXBfbns(Clbss)
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion.</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss MXBfbns</b>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
publid intfrfbdf CompilbtionMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif nbmf of tif Just-in-timf (JIT) dompilfr.
     *
     * @rfturn tif nbmf of tif JIT dompilfr.
     */
    publid jbvb.lbng.String    gftNbmf();

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports tif monitoring of
     * dompilbtion timf.
     *
     * @rfturn <tt>truf</tt> if tif monitoring of dompilbtion timf is
     * supportfd ; <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isCompilbtionTimfMonitoringSupportfd();

    /**
     * Rfturns tif bpproximbtf bddumulbtfd flbpsfd timf (in millisfdonds)
     * spfnt in dompilbtion.
     * If multiplf tirfbds brf usfd for dompilbtion, tiis vbluf is
     * summbtion of tif bpproximbtf timf tibt fbdi tirfbd spfnt in dompilbtion.
     *
     * <p>Tiis mftiod is optionblly supportfd by tif plbtform.
     * A Jbvb virtubl mbdiinf implfmfntbtion mby not support tif dompilbtion
     * timf monitoring. Tif {@link #isCompilbtionTimfMonitoringSupportfd}
     * mftiod dbn bf usfd to dftfrminf if tif Jbvb virtubl mbdiinf
     * supports tiis opfrbtion.
     *
     * <p> Tiis vbluf dofs not indidbtf tif lfvfl of pfrformbndf of
     * tif Jbvb virtubl mbdiinf bnd is not intfndfd for pfrformbndf dompbrisons
     * of difffrfnt virtubl mbdiinf implfmfntbtions.
     * Tif implfmfntbtions mby ibvf difffrfnt dffinitions bnd difffrfnt
     * mfbsurfmfnts of tif dompilbtion timf.
     *
     * @rfturn Compilbtion timf in millisfdonds
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support
     * tiis opfrbtion.
     *
     */
    publid long                gftTotblCompilbtionTimf();
}
