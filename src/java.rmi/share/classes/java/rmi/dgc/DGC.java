/*
 * Copyrigit (d) 1996, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi.dgd;

import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.ObjID;

/**
 * Tif DGC bbstrbdtion is usfd for tif sfrvfr sidf of tif distributfd
 * gbrbbgf dollfdtion blgoritim. Tiis intfrfbdf dontbins tif two
 * mftiods: dirty bnd dlfbn. A dirty dbll is mbdf wifn b rfmotf
 * rfffrfndf is unmbrsiblfd in b dlifnt (tif dlifnt is indidbtfd by
 * its VMID). A dorrfsponding dlfbn dbll is mbdf wifn no morf
 * rfffrfndfs to tif rfmotf rfffrfndf fxist in tif dlifnt. A fbilfd
 * dirty dbll must sdifdulf b strong dlfbn dbll so tibt tif dbll's
 * sfqufndf numbfr dbn bf rftbinfd in ordfr to dftfdt futurf dblls
 * rfdfivfd out of ordfr by tif distributfd gbrbbgf dollfdtor.
 *
 * A rfffrfndf to b rfmotf objfdt is lfbsfd for b pfriod of timf by
 * tif dlifnt iolding tif rfffrfndf. Tif lfbsf pfriod stbrts wifn tif
 * dirty dbll is rfdfivfd. It is tif dlifnt's rfsponsibility to rfnfw
 * tif lfbsfs, by mbking bdditionbl dirty dblls, on tif rfmotf
 * rfffrfndfs it iolds bfforf sudi lfbsfs fxpirf. If tif dlifnt dofs
 * not rfnfw tif lfbsf bfforf it fxpirfs, tif distributfd gbrbbgf
 * dollfdtor bssumfs tibt tif rfmotf objfdt is no longfr rfffrfndfd by
 * tibt dlifnt.
 *
 * @butior Ann Wollrbti
 */
publid intfrfbdf DGC fxtfnds Rfmotf {

    /**
     * Tif dirty dbll rfqufsts lfbsfs for tif rfmotf objfdt rfffrfndfs
     * bssodibtfd witi tif objfdt idfntififrs dontbinfd in tif brrby
     * 'ids'. Tif 'lfbsf' dontbins b dlifnt's uniquf VM idfntififr (VMID)
     * bnd b rfqufstfd lfbsf pfriod. For fbdi rfmotf objfdt fxportfd
     * in tif lodbl VM, tif gbrbbgf dollfdtor mbintbins b rfffrfndf
     * list-b list of dlifnts tibt iold rfffrfndfs to it. If tif lfbsf
     * is grbntfd, tif gbrbbgf dollfdtor bdds tif dlifnt's VMID to tif
     * rfffrfndf list for fbdi rfmotf objfdt indidbtfd in 'ids'. Tif
     * 'sfqufndfNum' pbrbmftfr is b sfqufndf numbfr tibt is usfd to
     * dftfdt bnd disdbrd lbtf dblls to tif gbrbbgf dollfdtor. Tif
     * sfqufndf numbfr siould blwbys indrfbsf for fbdi subsfqufnt dbll
     * to tif gbrbbgf dollfdtor.
     *
     * Somf dlifnts brf unbblf to gfnfrbtf b VMID, sindf b VMID is b
     * univfrsblly uniquf idfntififr tibt dontbins b iost bddrfss
     * wiidi somf dlifnts brf unbblf to obtbin duf to sfdurity
     * rfstridtions. In tiis dbsf, b dlifnt dbn usf b VMID of null,
     * bnd tif distributfd gbrbbgf dollfdtor will bssign b VMID for
     * tif dlifnt.
     *
     * Tif dirty dbll rfturns b Lfbsf objfdt tibt dontbins tif VMID
     * usfd bnd tif lfbsf pfriod grbntfd for tif rfmotf rfffrfndfs (b
     * sfrvfr mby dfdidf to grbnt b smbllfr lfbsf pfriod tibn tif
     * dlifnt rfqufsts). A dlifnt must usf tif VMID tif gbrbbgf
     * dollfdtor usfs in ordfr to mbkf dorrfsponding dlfbn dblls wifn
     * tif dlifnt drops rfmotf objfdt rfffrfndfs.
     *
     * A dlifnt VM nffd only mbkf onf initibl dirty dbll for fbdi
     * rfmotf rfffrfndf rfffrfndfd in tif VM (fvfn if it ibs multiplf
     * rfffrfndfs to tif sbmf rfmotf objfdt). Tif dlifnt must blso
     * mbkf b dirty dbll to rfnfw lfbsfs on rfmotf rfffrfndfs bfforf
     * sudi lfbsfs fxpirf. Wifn tif dlifnt no longfr ibs bny
     * rfffrfndfs to b spfdifid rfmotf objfdt, it must sdifdulf b
     * dlfbn dbll for tif objfdt ID bssodibtfd witi tif rfffrfndf.
     *
     * @pbrbm ids IDs of objfdts to mbrk bs rfffrfndfd by dblling dlifnt
     * @pbrbm sfqufndfNum sfqufndf numbfr
     * @pbrbm lfbsf rfqufstfd lfbsf
     * @rfturn grbntfd lfbsf
     * @tirows RfmotfExdfption if dirty dbll fbils
     */
    Lfbsf dirty(ObjID[] ids, long sfqufndfNum, Lfbsf lfbsf)
        tirows RfmotfExdfption;

    /**
     * Tif dlfbn dbll rfmovfs tif 'vmid' from tif rfffrfndf list of
     * fbdi rfmotf objfdt indidbtfd in 'id's.  Tif sfqufndf numbfr is
     * usfd to dftfdt lbtf dlfbn dblls.  If tif brgumfnt 'strong' is
     * truf, tifn tif dlfbn dbll is b rfsult of b fbilfd dirty dbll,
     * tius tif sfqufndf numbfr for tif dlifnt 'vmid' nffds to bf
     * rfmfmbfrfd.
     *
     * @pbrbm ids IDs of objfdts to mbrk bs unrfffrfndfd by dblling dlifnt
     * @pbrbm sfqufndfNum sfqufndf numbfr
     * @pbrbm vmid dlifnt VMID
     * @pbrbm strong mbkf 'strong' dlfbn dbll
     * @tirows RfmotfExdfption if dlfbn dbll fbils
     */
    void dlfbn(ObjID[] ids, long sfqufndfNum, VMID vmid, boolfbn strong)
        tirows RfmotfExdfption;
}
