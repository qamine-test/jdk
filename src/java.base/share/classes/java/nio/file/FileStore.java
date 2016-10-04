/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;

/**
 * Storbgf for filfs. A {@dodf FilfStorf} rfprfsfnts b storbgf pool, dfvidf,
 * pbrtition, volumf, dondrftf filf systfm or otifr implfmfntbtion spfdifid mfbns
 * of filf storbgf. Tif {@dodf FilfStorf} for wifrf b filf is storfd is obtbinfd
 * by invoking tif {@link Filfs#gftFilfStorf gftFilfStorf} mftiod, or bll filf
 * storfs dbn bf fnumfrbtfd by invoking tif {@link FilfSystfm#gftFilfStorfs
 * gftFilfStorfs} mftiod.
 *
 * <p> In bddition to tif mftiods dffinfd by tiis dlbss, b filf storf mby support
 * onf or morf {@link FilfStorfAttributfVifw FilfStorfAttributfVifw} dlbssfs
 * tibt providf b rfbd-only or updbtbblf vifw of b sft of filf storf bttributfs.
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss FilfStorf {

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd FilfStorf() {
    }

    /**
     * Rfturns tif nbmf of tiis filf storf. Tif formbt of tif nbmf is iigily
     * implfmfntbtion spfdifid. It will typidblly bf tif nbmf of tif storbgf
     * pool or volumf.
     *
     * <p> Tif string rfturnfd by tiis mftiod mby difffr from tif string
     * rfturnfd by tif {@link Objfdt#toString() toString} mftiod.
     *
     * @rfturn  tif nbmf of tiis filf storf
     */
    publid bbstrbdt String nbmf();

    /**
     * Rfturns tif <fm>typf</fm> of tiis filf storf. Tif formbt of tif string
     * rfturnfd by tiis mftiod is iigily implfmfntbtion spfdifid. It mby
     * indidbtf, for fxbmplf, tif formbt usfd or if tif filf storf is lodbl
     * or rfmotf.
     *
     * @rfturn  b string rfprfsfnting tif typf of tiis filf storf
     */
    publid bbstrbdt String typf();

    /**
     * Tflls wiftifr tiis filf storf is rfbd-only. A filf storf is rfbd-only if
     * it dofs not support writf opfrbtions or otifr dibngfs to filfs. Any
     * bttfmpt to drfbtf b filf, opfn bn fxisting filf for writing ftd. dbusfs
     * bn {@dodf IOExdfption} to bf tirown.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis filf storf is rfbd-only
     */
    publid bbstrbdt boolfbn isRfbdOnly();

    /**
     * Rfturns tif sizf, in bytfs, of tif filf storf.
     *
     * @rfturn  tif sizf of tif filf storf, in bytfs
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    publid bbstrbdt long gftTotblSpbdf() tirows IOExdfption;

    /**
     * Rfturns tif numbfr of bytfs bvbilbblf to tiis Jbvb virtubl mbdiinf on tif
     * filf storf.
     *
     * <p> Tif rfturnfd numbfr of bvbilbblf bytfs is b iint, but not b
     * gubrbntff, tibt it is possiblf to usf most or bny of tifsf bytfs.  Tif
     * numbfr of usbblf bytfs is most likfly to bf bddurbtf immfdibtfly
     * bftfr tif spbdf bttributfs brf obtbinfd. It is likfly to bf mbdf inbddurbtf
     * by bny fxtfrnbl I/O opfrbtions indluding tiosf mbdf on tif systfm outsidf
     * of tiis Jbvb virtubl mbdiinf.
     *
     * @rfturn  tif numbfr of bytfs bvbilbblf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    publid bbstrbdt long gftUsbblfSpbdf() tirows IOExdfption;

    /**
     * Rfturns tif numbfr of unbllodbtfd bytfs in tif filf storf.
     *
     * <p> Tif rfturnfd numbfr of unbllodbtfd bytfs is b iint, but not b
     * gubrbntff, tibt it is possiblf to usf most or bny of tifsf bytfs.  Tif
     * numbfr of unbllodbtfd bytfs is most likfly to bf bddurbtf immfdibtfly
     * bftfr tif spbdf bttributfs brf obtbinfd. It is likfly to bf
     * mbdf inbddurbtf by bny fxtfrnbl I/O opfrbtions indluding tiosf mbdf on
     * tif systfm outsidf of tiis virtubl mbdiinf.
     *
     * @rfturn  tif numbfr of unbllodbtfd bytfs
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    publid bbstrbdt long gftUnbllodbtfdSpbdf() tirows IOExdfption;

    /**
     * Tflls wiftifr or not tiis filf storf supports tif filf bttributfs
     * idfntififd by tif givfn filf bttributf vifw.
     *
     * <p> Invoking tiis mftiod to tfst if tif filf storf supports {@link
     * BbsidFilfAttributfVifw} will blwbys rfturn {@dodf truf}. In tif dbsf of
     * tif dffbult providfr, tiis mftiod dbnnot gubrbntff to givf tif dorrfdt
     * rfsult wifn tif filf storf is not b lodbl storbgf dfvidf. Tif rfbsons for
     * tiis brf implfmfntbtion spfdifid bnd tifrfforf unspfdififd.
     *
     * @pbrbm   typf
     *          tif filf bttributf vifw typf
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif filf bttributf vifw is
     *          supportfd
     */
    publid bbstrbdt boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf);

    /**
     * Tflls wiftifr or not tiis filf storf supports tif filf bttributfs
     * idfntififd by tif givfn filf bttributf vifw.
     *
     * <p> Invoking tiis mftiod to tfst if tif filf storf supports {@link
     * BbsidFilfAttributfVifw}, idfntififd by tif nbmf "{@dodf bbsid}" will
     * blwbys rfturn {@dodf truf}. In tif dbsf of tif dffbult providfr, tiis
     * mftiod dbnnot gubrbntff to givf tif dorrfdt rfsult wifn tif filf storf is
     * not b lodbl storbgf dfvidf. Tif rfbsons for tiis brf implfmfntbtion
     * spfdifid bnd tifrfforf unspfdififd.
     *
     * @pbrbm   nbmf
     *          tif {@link FilfAttributfVifw#nbmf nbmf} of filf bttributf vifw
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif filf bttributf vifw is
     *          supportfd
     */
    publid bbstrbdt boolfbn supportsFilfAttributfVifw(String nbmf);

    /**
     * Rfturns b {@dodf FilfStorfAttributfVifw} of tif givfn typf.
     *
     * <p> Tiis mftiod is intfndfd to bf usfd wifrf tif filf storf bttributf
     * vifw dffinfs typf-sbff mftiods to rfbd or updbtf tif filf storf bttributfs.
     * Tif {@dodf typf} pbrbmftfr is tif typf of tif bttributf vifw rfquirfd bnd
     * tif mftiod rfturns bn instbndf of tibt typf if supportfd.
     *
     * @pbrbm   <V>
     *          Tif {@dodf FilfStorfAttributfVifw} typf
     * @pbrbm   typf
     *          tif {@dodf Clbss} objfdt dorrfsponding to tif bttributf vifw
     *
     * @rfturn  b filf storf bttributf vifw of tif spfdififd typf or
     *          {@dodf null} if tif bttributf vifw is not bvbilbblf
     */
    publid bbstrbdt <V fxtfnds FilfStorfAttributfVifw> V
        gftFilfStorfAttributfVifw(Clbss<V> typf);

    /**
     * Rfbds tif vbluf of b filf storf bttributf.
     *
     * <p> Tif {@dodf bttributf} pbrbmftfr idfntififs tif bttributf to bf rfbd
     * bnd tbkfs tif form:
     * <blodkquotf>
     * <i>vifw-nbmf</i><b>:</b><i>bttributf-nbmf</i>
     * </blodkquotf>
     * wifrf tif dibrbdtfr {@dodf ':'} stbnds for itsflf.
     *
     * <p> <i>vifw-nbmf</i> is tif {@link FilfStorfAttributfVifw#nbmf nbmf} of
     * b {@link FilfStorf AttributfVifw} tibt idfntififs b sft of filf bttributfs.
     * <i>bttributf-nbmf</i> is tif nbmf of tif bttributf.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to know if ZFS domprfssion is fnbblfd (bssuming tif "zfs"
     * vifw is supportfd):
     * <prf>
     *    boolfbn domprfssion = (Boolfbn)fs.gftAttributf("zfs:domprfssion");
     * </prf>
     *
     * @pbrbm   bttributf
     *          tif bttributf to rfbd

     * @rfturn  tif bttributf vbluf; {@dodf null} mby bf b vblid vblid for somf
     *          bttributfs
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf or it dofs not support
     *          rfbding tif bttributf
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    publid bbstrbdt Objfdt gftAttributf(String bttributf) tirows IOExdfption;
}
