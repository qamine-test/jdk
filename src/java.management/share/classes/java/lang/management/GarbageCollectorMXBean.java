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
 * Tif mbnbgfmfnt intfrfbdf for tif gbrbbgf dollfdtion of
 * tif Jbvb virtubl mbdiinf.  Gbrbbgf dollfdtion is tif prodfss
 * tibt tif Jbvb virtubl mbdiinf usfs to find bnd rfdlbim unrfbdibblf
 * objfdts to frff up mfmory spbdf.  A gbrbbgf dollfdtor is onf typf of
 * {@link MfmoryMbnbgfrMXBfbn mfmory mbnbgfr}.
 *
 * <p> A Jbvb virtubl mbdiinf mby ibvf onf or morf instbndfs of
 * tif implfmfntbtion dlbss of tiis intfrfbdf.
 * An instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftGbrbbgfCollfdtorMXBfbns} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * b gbrbbgf dollfdtor witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *   {@link MbnbgfmfntFbdtory#GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:typf=GbrbbgfCollfdtor</tt>}<tt>,nbmf=</tt><i>dollfdtor's nbmf</i>
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * A plbtform usublly indludfs bdditionbl plbtform-dfpfndfnt informbtion
 * spfdifid to b gbrbbgf dollfdtion blgoritim for monitoring.
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
publid intfrfbdf GbrbbgfCollfdtorMXBfbn fxtfnds MfmoryMbnbgfrMXBfbn {
    /**
     * Rfturns tif totbl numbfr of dollfdtions tibt ibvf oddurrfd.
     * Tiis mftiod rfturns <tt>-1</tt> if tif dollfdtion dount is undffinfd for
     * tiis dollfdtor.
     *
     * @rfturn tif totbl numbfr of dollfdtions tibt ibvf oddurrfd.
     */
    publid long gftCollfdtionCount();

    /**
     * Rfturns tif bpproximbtf bddumulbtfd dollfdtion flbpsfd timf
     * in millisfdonds.  Tiis mftiod rfturns <tt>-1</tt> if tif dollfdtion
     * flbpsfd timf is undffinfd for tiis dollfdtor.
     * <p>
     * Tif Jbvb virtubl mbdiinf implfmfntbtion mby usf b iigi rfsolution
     * timfr to mfbsurf tif flbpsfd timf.  Tiis mftiod mby rfturn tif
     * sbmf vbluf fvfn if tif dollfdtion dount ibs bffn indrfmfntfd
     * if tif dollfdtion flbpsfd timf is vfry siort.
     *
     * @rfturn tif bpproximbtf bddumulbtfd dollfdtion flbpsfd timf
     * in millisfdonds.
     */
    publid long gftCollfdtionTimf();


}
