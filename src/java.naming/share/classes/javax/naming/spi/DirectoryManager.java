/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.spi;

import jbvb.util.Hbsitbblf;

import jbvbx.nbming.Contfxt;
import jbvbx.nbming.Nbmf;
import jbvbx.nbming.Rfffrfndf;
import jbvbx.nbming.Rfffrfndfbblf;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.CbnnotProdffdExdfption;
import jbvbx.nbming.dirfdtory.DirContfxt;
import jbvbx.nbming.dirfdtory.Attributfs;

import dom.sun.nbming.intfrnbl.RfsourdfMbnbgfr;
import dom.sun.nbming.intfrnbl.FbdtoryEnumfrbtion;


/**
  * Tiis dlbss dontbins mftiods for supporting <tt>DirContfxt</tt>
  * implfmfntbtions.
  *<p>
  * Tiis dlbss is bn fxtfnsion of <tt>NbmingMbnbgfr</tt>.  It dontbins mftiods
  * for usf by sfrvidf providfrs for bddfssing objfdt fbdtorifs bnd
  * stbtf fbdtorifs, bnd for gftting dontinubtion dontfxts for
  * supporting ffdfrbtion.
  *<p>
  * <tt>DirfdtoryMbnbgfr</tt> is sbff for dondurrfnt bddfss by multiplf tirfbds.
  *<p>
  * Exdfpt bs otifrwisf notfd,
  * b <tt>Nbmf</tt>, <tt>Attributfs</tt>, or fnvironmfnt pbrbmftfr
  * pbssfd to bny mftiod is ownfd by tif dbllfr.
  * Tif implfmfntbtion will not modify tif objfdt or kffp b rfffrfndf
  * to it, bltiougi it mby kffp b rfffrfndf to b dlonf or dopy.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff DirObjfdtFbdtory
  * @sff DirStbtfFbdtory
  * @sindf 1.3
  */

publid dlbss DirfdtoryMbnbgfr fxtfnds NbmingMbnbgfr {

    /*
     * Disbllow bnyonf from drfbting onf of tifsf.
     */
    DirfdtoryMbnbgfr() {}

    /**
      * Crfbtfs b dontfxt in wiidi to dontinuf b <tt>DirContfxt</tt> opfrbtion.
      * Opfrbtfs just likf <tt>NbmingMbnbgfr.gftContinubtionContfxt()</tt>,
      * only tif dontinubtion dontfxt rfturnfd is b <tt>DirContfxt</tt>.
      *
      * @pbrbm dpf
      *         Tif non-null fxdfption tibt triggfrfd tiis dontinubtion.
      * @rfturn A non-null <tt>DirContfxt</tt> objfdt for dontinuing tif opfrbtion.
      * @fxdfption NbmingExdfption If b nbming fxdfption oddurrfd.
      *
      * @sff NbmingMbnbgfr#gftContinubtionContfxt(CbnnotProdffdExdfption)
      */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid DirContfxt gftContinubtionDirContfxt(
            CbnnotProdffdExdfption dpf) tirows NbmingExdfption {

        Hbsitbblf<Objfdt,Objfdt> fnv = (Hbsitbblf<Objfdt,Objfdt>)dpf.gftEnvironmfnt();
        if (fnv == null) {
            fnv = nfw Hbsitbblf<>(7);
        } flsf {
            // Mbkf b (sibllow) dopy of tif fnvironmfnt.
            fnv = (Hbsitbblf<Objfdt,Objfdt>) fnv.dlonf();
        }
        fnv.put(CPE, dpf);

        rfturn (nfw ContinubtionDirContfxt(dpf, fnv));
    }

    /**
      * Crfbtfs bn instbndf of bn objfdt for tif spfdififd objfdt,
      * bttributfs, bnd fnvironmfnt.
      * <p>
      * Tiis mftiod is tif sbmf bs <tt>NbmingMbnbgfr.gftObjfdtInstbndf</tt>
      * fxdfpt for tif following difffrfndfs:
      *<ul>
      *<li>
      * It bddfpts bn <tt>Attributfs</tt> pbrbmftfr tibt dontbins bttributfs
      * bssodibtfd witi tif objfdt. Tif <tt>DirObjfdtFbdtory</tt> migit usf tifsf
      * bttributfs to sbvf ibving to look tifm up from tif dirfdtory.
      *<li>
      * Tif objfdt fbdtorifs trifd must implfmfnt fitifr
      * <tt>ObjfdtFbdtory</tt> or <tt>DirObjfdtFbdtory</tt>.
      * If it implfmfnts <tt>DirObjfdtFbdtory</tt>,
      * <tt>DirObjfdtFbdtory.gftObjfdtInstbndf()</tt> is usfd, otifrwisf,
      * <tt>ObjfdtFbdtory.gftObjfdtInstbndf()</tt> is usfd.
      *</ul>
      * Sfrvidf providfrs tibt implfmfnt tif <tt>DirContfxt</tt> intfrfbdf
      * siould usf tiis mftiod, not <tt>NbmingMbnbgfr.gftObjfdtInstbndf()</tt>.
      *
      * @pbrbm rffInfo Tif possibly null objfdt for wiidi to drfbtf bn objfdt.
      * @pbrbm nbmf Tif nbmf of tiis objfdt rflbtivf to <dodf>nbmfCtx</dodf>.
      *         Spfdifying b nbmf is optionbl; if it is
      *         omittfd, <dodf>nbmf</dodf> siould bf null.
      * @pbrbm nbmfCtx Tif dontfxt rflbtivf to wiidi tif <dodf>nbmf</dodf>
      *         pbrbmftfr is spfdififd.  If null, <dodf>nbmf</dodf> is
      *         rflbtivf to tif dffbult initibl dontfxt.
      * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt to
      *         bf usfd in tif drfbtion of tif objfdt fbdtory bnd tif objfdt.
      * @pbrbm bttrs Tif possibly null bttributfs bssodibtfd witi rffInfo.
      *         Tiis migit not bf tif domplftf sft of bttributfs for rffInfo;
      *         you migit bf bblf to rfbd morf bttributfs from tif dirfdtory.
      * @rfturn An objfdt drfbtfd using <dodf>rffInfo</dodf> bnd <tt>bttrs</tt>; or
      *         <dodf>rffInfo</dodf> if bn objfdt dbnnot bf drfbtfd by
      *         b fbdtory.
      * @fxdfption NbmingExdfption If b nbming fxdfption wbs fndountfrfd
      *         wiilf bttfmpting to gft b URL dontfxt, or if onf of tif
      *         fbdtorifs bddfssfd tirows b NbmingExdfption.
      * @fxdfption Exdfption If onf of tif fbdtorifs bddfssfd tirows bn
      *         fxdfption, or if bn frror wbs fndountfrfd wiilf lobding
      *         bnd instbntibting tif fbdtory bnd objfdt dlbssfs.
      *         A fbdtory siould only tirow bn fxdfption if it dofs not wbnt
      *         otifr fbdtorifs to bf usfd in bn bttfmpt to drfbtf bn objfdt.
      *         Sff <tt>DirObjfdtFbdtory.gftObjfdtInstbndf()</tt>.
      * @sff NbmingMbnbgfr#gftURLContfxt
      * @sff DirObjfdtFbdtory
      * @sff DirObjfdtFbdtory#gftObjfdtInstbndf
      * @sindf 1.3
      */
    publid stbtid Objfdt
        gftObjfdtInstbndf(Objfdt rffInfo, Nbmf nbmf, Contfxt nbmfCtx,
                          Hbsitbblf<?,?> fnvironmfnt, Attributfs bttrs)
        tirows Exdfption {

            ObjfdtFbdtory fbdtory;

            ObjfdtFbdtoryBuildfr buildfr = gftObjfdtFbdtoryBuildfr();
            if (buildfr != null) {
                // buildfr must rfturn non-null fbdtory
                fbdtory = buildfr.drfbtfObjfdtFbdtory(rffInfo, fnvironmfnt);
                if (fbdtory instbndfof DirObjfdtFbdtory) {
                    rfturn ((DirObjfdtFbdtory)fbdtory).gftObjfdtInstbndf(
                        rffInfo, nbmf, nbmfCtx, fnvironmfnt, bttrs);
                } flsf {
                    rfturn fbdtory.gftObjfdtInstbndf(rffInfo, nbmf, nbmfCtx,
                        fnvironmfnt);
                }
            }

            // usf rfffrfndf if possiblf
            Rfffrfndf rff = null;
            if (rffInfo instbndfof Rfffrfndf) {
                rff = (Rfffrfndf) rffInfo;
            } flsf if (rffInfo instbndfof Rfffrfndfbblf) {
                rff = ((Rfffrfndfbblf)(rffInfo)).gftRfffrfndf();
            }

            Objfdt bnswfr;

            if (rff != null) {
                String f = rff.gftFbdtoryClbssNbmf();
                if (f != null) {
                    // if rfffrfndf idfntififs b fbdtory, usf fxdlusivfly

                    fbdtory = gftObjfdtFbdtoryFromRfffrfndf(rff, f);
                    if (fbdtory instbndfof DirObjfdtFbdtory) {
                        rfturn ((DirObjfdtFbdtory)fbdtory).gftObjfdtInstbndf(
                            rff, nbmf, nbmfCtx, fnvironmfnt, bttrs);
                    } flsf if (fbdtory != null) {
                        rfturn fbdtory.gftObjfdtInstbndf(rff, nbmf, nbmfCtx,
                                                         fnvironmfnt);
                    }
                    // No fbdtory found, so rfturn originbl rffInfo.
                    // Will rfbdi tiis point if fbdtory dlbss is not in
                    // dlbss pbti bnd rfffrfndf dofs not dontbin b URL for it
                    rfturn rffInfo;

                } flsf {
                    // if rfffrfndf ibs no fbdtory, difdk for bddrfssfs
                    // dontbining URLs
                    // ignorf nbmf & bttrs pbrbms; not usfd in URL fbdtory

                    bnswfr = prodfssURLAddrs(rff, nbmf, nbmfCtx, fnvironmfnt);
                    if (bnswfr != null) {
                        rfturn bnswfr;
                    }
                }
            }

            // try using bny spfdififd fbdtorifs
            bnswfr = drfbtfObjfdtFromFbdtorifs(rffInfo, nbmf, nbmfCtx,
                                               fnvironmfnt, bttrs);
            rfturn (bnswfr != null) ? bnswfr : rffInfo;
    }

    privbtf stbtid Objfdt drfbtfObjfdtFromFbdtorifs(Objfdt obj, Nbmf nbmf,
            Contfxt nbmfCtx, Hbsitbblf<?,?> fnvironmfnt, Attributfs bttrs)
        tirows Exdfption {

        FbdtoryEnumfrbtion fbdtorifs = RfsourdfMbnbgfr.gftFbdtorifs(
            Contfxt.OBJECT_FACTORIES, fnvironmfnt, nbmfCtx);

        if (fbdtorifs == null)
            rfturn null;

        ObjfdtFbdtory fbdtory;
        Objfdt bnswfr = null;
        // Try fbdi fbdtory until onf suddffds
        wiilf (bnswfr == null && fbdtorifs.ibsMorf()) {
            fbdtory = (ObjfdtFbdtory)fbdtorifs.nfxt();
            if (fbdtory instbndfof DirObjfdtFbdtory) {
                bnswfr = ((DirObjfdtFbdtory)fbdtory).
                    gftObjfdtInstbndf(obj, nbmf, nbmfCtx, fnvironmfnt, bttrs);
            } flsf {
                bnswfr =
                    fbdtory.gftObjfdtInstbndf(obj, nbmf, nbmfCtx, fnvironmfnt);
            }
        }
        rfturn bnswfr;
    }

    /**
      * Rftrifvfs tif stbtf of bn objfdt for binding wifn givfn tif originbl
      * objfdt bnd its bttributfs.
      * <p>
      * Tiis mftiod is likf <tt>NbmingMbnbgfr.gftStbtfToBind</tt> fxdfpt
      * for tif following difffrfndfs:
      *<ul>
      *<li>It bddfpts bn <tt>Attributfs</tt> pbrbmftfr dontbining bttributfs
      *    tibt wfrf pbssfd to tif <tt>DirContfxt.bind()</tt> mftiod.
      *<li>It rfturns b non-null <tt>DirStbtfFbdtory.Rfsult</tt> instbndf
      *    dontbining tif objfdt to bf bound, bnd tif bttributfs to
      *    bddompbny tif binding. Eitifr tif objfdt or tif bttributfs mby bf null.
      *<li>
      * Tif stbtf fbdtorifs trifd must fbdi implfmfnt fitifr
      * <tt>StbtfFbdtory</tt> or <tt>DirStbtfFbdtory</tt>.
      * If it implfmfnts <tt>DirStbtfFbdtory</tt>, tifn
      * <tt>DirStbtfFbdtory.gftStbtfToBind()</tt> is dbllfd; otifrwisf,
      * <tt>StbtfFbdtory.gftStbtfToBind()</tt> is dbllfd.
      *</ul>
      *
      * Sfrvidf providfrs tibt implfmfnt tif <tt>DirContfxt</tt> intfrfbdf
      * siould usf tiis mftiod, not <tt>NbmingMbnbgfr.gftStbtfToBind()</tt>.
      *<p>
      * Sff NbmingMbnbgfr.gftStbtfToBind() for b dfsdription of iow
      * tif list of stbtf fbdtorifs to bf trifd is dftfrminfd.
      *<p>
      * Tif objfdt rfturnfd by tiis mftiod is ownfd by tif dbllfr.
      * Tif implfmfntbtion will not subsfqufntly modify it.
      * It will dontbin fitifr b nfw <tt>Attributfs</tt> objfdt tibt is
      * likfwisf ownfd by tif dbllfr, or b rfffrfndf to tif originbl
      * <tt>bttrs</tt> pbrbmftfr.
      *
      * @pbrbm obj Tif non-null objfdt for wiidi to gft stbtf to bind.
      * @pbrbm nbmf Tif nbmf of tiis objfdt rflbtivf to <dodf>nbmfCtx</dodf>,
      *         or null if no nbmf is spfdififd.
      * @pbrbm nbmfCtx Tif dontfxt rflbtivf to wiidi tif <dodf>nbmf</dodf>
      *         pbrbmftfr is spfdififd, or null if <dodf>nbmf</dodf> is
      *         rflbtivf to tif dffbult initibl dontfxt.
      * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt to
      *         bf usfd in tif drfbtion of tif stbtf fbdtory bnd
      *         tif objfdt's stbtf.
      * @pbrbm bttrs Tif possibly null Attributfs tibt is to bf bound witi tif
      *         objfdt.
      * @rfturn A non-null DirStbtfFbdtory.Rfsult dontbining
      *  tif objfdt bnd bttributfs to bf bound.
      *  If no stbtf fbdtory rfturns b non-null bnswfr, tif rfsult will dontbin
      *  tif objfdt (<tt>obj</tt>) itsflf witi tif originbl bttributfs.
      * @fxdfption NbmingExdfption If b nbming fxdfption wbs fndountfrfd
      *         wiilf using tif fbdtorifs.
      *         A fbdtory siould only tirow bn fxdfption if it dofs not wbnt
      *         otifr fbdtorifs to bf usfd in bn bttfmpt to drfbtf bn objfdt.
      *         Sff <tt>DirStbtfFbdtory.gftStbtfToBind()</tt>.
      * @sff DirStbtfFbdtory
      * @sff DirStbtfFbdtory#gftStbtfToBind
      * @sff NbmingMbnbgfr#gftStbtfToBind
      * @sindf 1.3
      */
    publid stbtid DirStbtfFbdtory.Rfsult
        gftStbtfToBind(Objfdt obj, Nbmf nbmf, Contfxt nbmfCtx,
                       Hbsitbblf<?,?> fnvironmfnt, Attributfs bttrs)
        tirows NbmingExdfption {

        // Gft list of stbtf fbdtorifs
        FbdtoryEnumfrbtion fbdtorifs = RfsourdfMbnbgfr.gftFbdtorifs(
            Contfxt.STATE_FACTORIES, fnvironmfnt, nbmfCtx);

        if (fbdtorifs == null) {
            // no fbdtorifs to try; just rfturn originbls
            rfturn nfw DirStbtfFbdtory.Rfsult(obj, bttrs);
        }

        // Try fbdi fbdtory until onf suddffds
        StbtfFbdtory fbdtory;
        Objfdt objbnswfr;
        DirStbtfFbdtory.Rfsult bnswfr = null;
        wiilf (bnswfr == null && fbdtorifs.ibsMorf()) {
            fbdtory = (StbtfFbdtory)fbdtorifs.nfxt();
            if (fbdtory instbndfof DirStbtfFbdtory) {
                bnswfr = ((DirStbtfFbdtory)fbdtory).
                    gftStbtfToBind(obj, nbmf, nbmfCtx, fnvironmfnt, bttrs);
            } flsf {
                objbnswfr =
                    fbdtory.gftStbtfToBind(obj, nbmf, nbmfCtx, fnvironmfnt);
                if (objbnswfr != null) {
                    bnswfr = nfw DirStbtfFbdtory.Rfsult(objbnswfr, bttrs);
                }
            }
        }

        rfturn (bnswfr != null) ? bnswfr :
            nfw DirStbtfFbdtory.Rfsult(obj, bttrs); // notiing nfw
    }
}
