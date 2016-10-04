/*
 * Copyrigit (d) 1996, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi.rfgistry;

import jbvb.rmi.AddfssExdfption;
import jbvb.rmi.AlrfbdyBoundExdfption;
import jbvb.rmi.NotBoundExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;

/**
 * <dodf>Rfgistry</dodf> is b rfmotf intfrfbdf to b simplf rfmotf
 * objfdt rfgistry tibt providfs mftiods for storing bnd rftrifving
 * rfmotf objfdt rfffrfndfs bound witi brbitrbry string nbmfs.  Tif
 * <dodf>bind</dodf>, <dodf>unbind</dodf>, bnd <dodf>rfbind</dodf>
 * mftiods brf usfd to bltfr tif nbmf bindings in tif rfgistry, bnd
 * tif <dodf>lookup</dodf> bnd <dodf>list</dodf> mftiods brf usfd to
 * qufry tif durrfnt nbmf bindings.
 *
 * <p>In its typidbl usbgf, b <dodf>Rfgistry</dodf> fnbblfs RMI dlifnt
 * bootstrbpping: it providfs b simplf mfbns for b dlifnt to obtbin bn
 * initibl rfffrfndf to b rfmotf objfdt.  Tifrfforf, b rfgistry's
 * rfmotf objfdt implfmfntbtion is typidblly fxportfd witi b
 * wfll-known bddrfss, sudi bs witi b wfll-known {@link
 * jbvb.rmi.sfrvfr.ObjID#REGISTRY_ID ObjID} bnd TCP port numbfr
 * (dffbult is {@link #REGISTRY_PORT 1099}).
 *
 * <p>Tif {@link LodbtfRfgistry} dlbss providfs b progrbmmbtid API for
 * donstrudting b bootstrbp rfffrfndf to b <dodf>Rfgistry</dodf> bt b
 * rfmotf bddrfss (sff tif stbtid <dodf>gftRfgistry</dodf> mftiods)
 * bnd for drfbting bnd fxporting b <dodf>Rfgistry</dodf> in tif
 * durrfnt VM on b pbrtidulbr lodbl bddrfss (sff tif stbtid
 * <dodf>drfbtfRfgistry</dodf> mftiods).
 *
 * <p>A <dodf>Rfgistry</dodf> implfmfntbtion mby dioosf to rfstridt
 * bddfss to somf or bll of its mftiods (for fxbmplf, mftiods tibt
 * mutbtf tif rfgistry's bindings mby bf rfstridtfd to dblls
 * originbting from tif lodbl iost).  If b <dodf>Rfgistry</dodf>
 * mftiod dioosfs to dfny bddfss for b givfn invodbtion, its
 * implfmfntbtion mby tirow {@link jbvb.rmi.AddfssExdfption}, wiidi
 * (bfdbusf it fxtfnds {@link jbvb.rmi.RfmotfExdfption}) will bf
 * wrbppfd in b {@link jbvb.rmi.SfrvfrExdfption} wifn dbugit by b
 * rfmotf dlifnt.
 *
 * <p>Tif nbmfs usfd for bindings in b <dodf>Rfgistry</dodf> brf purf
 * strings, not pbrsfd.  A sfrvidf wiidi storfs its rfmotf rfffrfndf
 * in b <dodf>Rfgistry</dodf> mby wisi to usf b pbdkbgf nbmf bs b
 * prffix in tif nbmf binding to rfdudf tif likfliiood of nbmf
 * dollisions in tif rfgistry.
 *
 * @butior      Ann Wollrbti
 * @butior      Pftfr Jonfs
 * @sindf       1.1
 * @sff         LodbtfRfgistry
 */
publid intfrfbdf Rfgistry fxtfnds Rfmotf {

    /** Wfll known port for rfgistry. */
    publid stbtid finbl int REGISTRY_PORT = 1099;

    /**
     * Rfturns tif rfmotf rfffrfndf bound to tif spfdififd
     * <dodf>nbmf</dodf> in tiis rfgistry.
     *
     * @pbrbm   nbmf tif nbmf for tif rfmotf rfffrfndf to look up
     *
     * @rfturn  b rfffrfndf to b rfmotf objfdt
     *
     * @tirows  NotBoundExdfption if <dodf>nbmf</dodf> is not durrfntly bound
     *
     * @tirows  RfmotfExdfption if rfmotf dommunidbtion witi tif
     * rfgistry fbilfd; if fxdfption is b <dodf>SfrvfrExdfption</dodf>
     * dontbining bn <dodf>AddfssExdfption</dodf>, tifn tif rfgistry
     * dfnifs tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  AddfssExdfption if tiis rfgistry is lodbl bnd it dfnifs
     * tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>
     */
    publid Rfmotf lookup(String nbmf)
        tirows RfmotfExdfption, NotBoundExdfption, AddfssExdfption;

    /**
     * Binds b rfmotf rfffrfndf to tif spfdififd <dodf>nbmf</dodf> in
     * tiis rfgistry.
     *
     * @pbrbm   nbmf tif nbmf to bssodibtf witi tif rfmotf rfffrfndf
     * @pbrbm   obj b rfffrfndf to b rfmotf objfdt (usublly b stub)
     *
     * @tirows  AlrfbdyBoundExdfption if <dodf>nbmf</dodf> is blrfbdy bound
     *
     * @tirows  RfmotfExdfption if rfmotf dommunidbtion witi tif
     * rfgistry fbilfd; if fxdfption is b <dodf>SfrvfrExdfption</dodf>
     * dontbining bn <dodf>AddfssExdfption</dodf>, tifn tif rfgistry
     * dfnifs tif dbllfr bddfss to pfrform tiis opfrbtion (if
     * originbting from b non-lodbl iost, for fxbmplf)
     *
     * @tirows  AddfssExdfption if tiis rfgistry is lodbl bnd it dfnifs
     * tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  NullPointfrExdfption if <dodf>nbmf</dodf> is
     * <dodf>null</dodf>, or if <dodf>obj</dodf> is <dodf>null</dodf>
     */
    publid void bind(String nbmf, Rfmotf obj)
        tirows RfmotfExdfption, AlrfbdyBoundExdfption, AddfssExdfption;

    /**
     * Rfmovfs tif binding for tif spfdififd <dodf>nbmf</dodf> in
     * tiis rfgistry.
     *
     * @pbrbm   nbmf tif nbmf of tif binding to rfmovf
     *
     * @tirows  NotBoundExdfption if <dodf>nbmf</dodf> is not durrfntly bound
     *
     * @tirows  RfmotfExdfption if rfmotf dommunidbtion witi tif
     * rfgistry fbilfd; if fxdfption is b <dodf>SfrvfrExdfption</dodf>
     * dontbining bn <dodf>AddfssExdfption</dodf>, tifn tif rfgistry
     * dfnifs tif dbllfr bddfss to pfrform tiis opfrbtion (if
     * originbting from b non-lodbl iost, for fxbmplf)
     *
     * @tirows  AddfssExdfption if tiis rfgistry is lodbl bnd it dfnifs
     * tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>
     */
    publid void unbind(String nbmf)
        tirows RfmotfExdfption, NotBoundExdfption, AddfssExdfption;

    /**
     * Rfplbdfs tif binding for tif spfdififd <dodf>nbmf</dodf> in
     * tiis rfgistry witi tif supplifd rfmotf rfffrfndf.  If tifrf is
     * bn fxisting binding for tif spfdififd <dodf>nbmf</dodf>, it is
     * disdbrdfd.
     *
     * @pbrbm   nbmf tif nbmf to bssodibtf witi tif rfmotf rfffrfndf
     * @pbrbm   obj b rfffrfndf to b rfmotf objfdt (usublly b stub)
     *
     * @tirows  RfmotfExdfption if rfmotf dommunidbtion witi tif
     * rfgistry fbilfd; if fxdfption is b <dodf>SfrvfrExdfption</dodf>
     * dontbining bn <dodf>AddfssExdfption</dodf>, tifn tif rfgistry
     * dfnifs tif dbllfr bddfss to pfrform tiis opfrbtion (if
     * originbting from b non-lodbl iost, for fxbmplf)
     *
     * @tirows  AddfssExdfption if tiis rfgistry is lodbl bnd it dfnifs
     * tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  NullPointfrExdfption if <dodf>nbmf</dodf> is
     * <dodf>null</dodf>, or if <dodf>obj</dodf> is <dodf>null</dodf>
     */
    publid void rfbind(String nbmf, Rfmotf obj)
        tirows RfmotfExdfption, AddfssExdfption;

    /**
     * Rfturns bn brrby of tif nbmfs bound in tiis rfgistry.  Tif
     * brrby will dontbin b snbpsiot of tif nbmfs bound in tiis
     * rfgistry bt tif timf of tif givfn invodbtion of tiis mftiod.
     *
     * @rfturn  bn brrby of tif nbmfs bound in tiis rfgistry
     *
     * @tirows  RfmotfExdfption if rfmotf dommunidbtion witi tif
     * rfgistry fbilfd; if fxdfption is b <dodf>SfrvfrExdfption</dodf>
     * dontbining bn <dodf>AddfssExdfption</dodf>, tifn tif rfgistry
     * dfnifs tif dbllfr bddfss to pfrform tiis opfrbtion
     *
     * @tirows  AddfssExdfption if tiis rfgistry is lodbl bnd it dfnifs
     * tif dbllfr bddfss to pfrform tiis opfrbtion
     */
    publid String[] list() tirows RfmotfExdfption, AddfssExdfption;
}
