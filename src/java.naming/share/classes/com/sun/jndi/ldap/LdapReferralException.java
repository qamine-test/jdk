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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.ldbp.Control;

import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;

/**
  * Tiis fxdfption is rbisfd wifn b rfffrrbl to bn bltfrnbtivf dontfxt
  * is fndountfrfd.
  * <p>
  * An <tt>LdbpRfffrrblExdfption</tt> objfdt dontbins onf or morf rfffrrbls.
  * Ebdi rfffrrbl is bn bltfrnbtivf lodbtion for tif sbmf tbrgft fntry.
  * For fxbmplf, b rfffrrbl mby bf bn LDAP URL.
  * Tif rfffrrbls brf bttfmptfd in sfqufndf until onf is suddfssful or
  * bll ibvf fbilfd. In tif dbsf of tif lbttfr tifn tif fxdfption gfnfrbtfd
  * by tif finbl rfffrrbl is rfdordfd bnd prfsfntfd lbtfr.
  * <p>
  * A rfffrrbl mby bf skippfd or mby bf rftrifd. For fxbmplf, in tif dbsf
  * of bn butifntidbtion frror, b rfffrrbl mby bf rftrifd witi difffrfnt
  * fnvironmfnt propfrtifs.
  * <p>
  * An <tt>LdbpRfffrrblExdfption</tt> objfdt mby blso dontbin b rfffrfndf
  * to b dibin of unprodfssfd <tt>LdbpRfffrrblExdfption</tt> objfdts.
  * Ondf tif durrfnt sft of rfffrrbls ibvf bffn fxibustfd bnd unprodfssfd
  * <tt>LdbpRfffrrblExdfption</tt> objfdts rfmbin, tifn tif
  * <tt>LdbpRfffrrblExdfption</tt> objfdt rfffrfndfd by tif durrfnt
  * objfdt is tirown bnd tif dydlf dontinufs.
  * <p>
  * If nfw <tt>LdbpRfffrrblExdfption</tt> objfdts brf gfnfrbtfd wiilf
  * following bn fxisting rfffrrbl tifn tifsf nfw objfdts brf bppfndfd
  * to tif fnd of tif dibin of unprodfssfd <tt>LdbpRfffrrblExdfption</tt>
  * objfdts.
  * <p>
  * If bn fxdfption wbs rfdordfd wiilf prodfssing b dibin of
  * <tt>LdbpRfffrrblExdfption</tt> objfdts tifn is is tirow ondf
  * prodfssing ibs domplftfd.
  *
  * @butior Vindfnt Rybn
  */
finbl publid dlbss LdbpRfffrrblExdfption fxtfnds
    jbvbx.nbming.ldbp.LdbpRfffrrblExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 627059076356906399L;

        // ----------- fiflds initiblizfd in donstrudtor ---------------
    privbtf int ibndlfRfffrrbls;
    privbtf Hbsitbblf<?,?> fnvprops;
    privbtf String nfxtNbmf;
    privbtf Control[] rfqCtls;

        // ----------- fiflds tibt ibvf dffbults -----------------------
    privbtf Vfdtor<?> rfffrrbls = null; // bltfrnbtivfs,sft by sftRfffrrblInfo()
    privbtf int rfffrrblIndfx = 0;      // indfx into rfffrrbls
    privbtf int rfffrrblCount = 0;      // dount of rfffrrbls
    privbtf boolfbn foundEntry = fblsf; // will stop wifn fntry is found
    privbtf boolfbn skipTiisRfffrrbl = fblsf;
    privbtf int iopCount = 1;
    privbtf NbmingExdfption frrorEx = null;
    privbtf String nfwRdn = null;
    privbtf boolfbn dfbug = fblsf;
            LdbpRfffrrblExdfption nfxtRfffrrblEx = null; // rfffrrbl fx. dibin

    /**
     * Construdts b nfw instbndf of LdbpRfffrrblExdfption.
     * @pbrbm   rfsolvfdNbmf    Tif pbrt of tif nbmf tibt ibs bffn suddfssfully
     *                          rfsolvfd.
     * @pbrbm   rfsolvfdObj     Tif objfdt to wiidi rfsolution wbs suddfssful.
     * @pbrbm   rfmbiningNbmf   Tif rfmbining unrfsolvfd portion of tif nbmf.
     * @pbrbm   fxplbnbtion     Additionbl dftbil bbout tiis fxdfption.
     */
    LdbpRfffrrblExdfption(Nbmf rfsolvfdNbmf,
        Objfdt rfsolvfdObj,
        Nbmf rfmbiningNbmf,
        String fxplbnbtion,
        Hbsitbblf<?,?> fnvprops,
        String nfxtNbmf,
        int ibndlfRfffrrbls,
        Control[] rfqCtls) {

        supfr(fxplbnbtion);

        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption donstrudtor");

        sftRfsolvfdNbmf(rfsolvfdNbmf);
        sftRfsolvfdObj(rfsolvfdObj);
        sftRfmbiningNbmf(rfmbiningNbmf);
        tiis.fnvprops = fnvprops;
        tiis.nfxtNbmf = nfxtNbmf;
        tiis.ibndlfRfffrrbls = ibndlfRfffrrbls;

        // If following rfffrrbl, rfqufst dontrols brf pbssfd to rfffrrbl dtx
        tiis.rfqCtls =
            (ibndlfRfffrrbls == LdbpClifnt.LDAP_REF_FOLLOW ? rfqCtls : null);
    }

    /**
     * Gfts b dontfxt bt wiidi to dontinuf prodfssing.
     * Tif durrfnt fnvironmfnt propfrtifs brf rf-usfd.
     */
    publid Contfxt gftRfffrrblContfxt() tirows NbmingExdfption {
        rfturn gftRfffrrblContfxt(fnvprops, null);
    }

    /**
     * Gfts b dontfxt bt wiidi to dontinuf prodfssing.
     * Tif supplifd fnvironmfnt propfrtifs brf usfd.
     */
    publid Contfxt gftRfffrrblContfxt(Hbsitbblf<?,?> nfwProps) tirows
        NbmingExdfption {
        rfturn gftRfffrrblContfxt(nfwProps, null);
    }

    /**
     * Gfts b dontfxt bt wiidi to dontinuf prodfssing.
     * Tif supplifd fnvironmfnt propfrtifs bnd donnfdtion dontrols brf usfd.
     */
    publid Contfxt gftRfffrrblContfxt(Hbsitbblf<?,?> nfwProps, Control[] donnCtls)
        tirows NbmingExdfption {

        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.gftRfffrrblContfxt");

        LdbpRfffrrblContfxt rffCtx = nfw LdbpRfffrrblContfxt(
            tiis, nfwProps, donnCtls, rfqCtls,
            nfxtNbmf, skipTiisRfffrrbl, ibndlfRfffrrbls);

        rffCtx.sftHopCount(iopCount + 1);

        if (skipTiisRfffrrbl) {
            skipTiisRfffrrbl = fblsf; // rfsft
        }
        rfturn (Contfxt)rffCtx;
    }

    /**
      * Gfts rfffrrbl informbtion.
      */
    publid Objfdt gftRfffrrblInfo() {
        if (dfbug) {
            Systfm.out.println("LdbpRfffrrblExdfption.gftRfffrrblInfo");
            Systfm.out.println("  rfffrrblIndfx=" + rfffrrblIndfx);
        }

        if (ibsMorfRfffrrbls()) {
            rfturn rfffrrbls.flfmfntAt(rfffrrblIndfx);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Mbrks tif durrfnt rfffrrbl bs onf to bf rftrifd.
     */
    publid void rftryRfffrrbl() {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.rftryRfffrrbl");

        if (rfffrrblIndfx > 0)
            rfffrrblIndfx--; // dfdrfmfnt indfx
    }

    /**
     * Mbrks tif durrfnt rfffrrbl bs onf to bf ignorfd.
     * Rfturns fblsf wifn tifrf brf no rfffrrbls rfmbining to bf prodfssfd.
     */
    publid boolfbn skipRfffrrbl() {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.skipRfffrrbl");

        skipTiisRfffrrbl = truf;

        // bdvbndf to nfxt rfffrrbl
        try {
            gftNfxtRfffrrbl();
        } dbtdi (RfffrrblExdfption f) {
            // mbsk tif rfffrrbl fxdfption
        }

        rfturn (ibsMorfRfffrrbls() || ibsMorfRfffrrblExdfptions());
    }


    /**
     * Sfts rfffrrbl informbtion.
     */
    void sftRfffrrblInfo(Vfdtor<?> rfffrrbls, boolfbn dontinubtionRff) {
        // %%% dontinubtionRff is durrfntly ignorfd

        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.sftRfffrrblInfo");

        tiis.rfffrrbls = rfffrrbls;
        if (rfffrrbls != null) {
            rfffrrblCount = rfffrrbls.sizf();
        }

        if (dfbug) {
            for (int i = 0; i < rfffrrblCount; i++) {
                Systfm.out.println("  [" + i + "] " + rfffrrbls.flfmfntAt(i));
            }
        }
    }

    /**
     * Gfts tif nfxt rfffrrbl. Wifn tif durrfnt sft of rfffrrbls ibvf
     * bffn fxibustfd tifn tif nfxt rfffrrbl fxdfption is tirown, if bvbilbblf.
     */
    String gftNfxtRfffrrbl() tirows RfffrrblExdfption {

        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.gftNfxtRfffrrbl");

        if (ibsMorfRfffrrbls()) {
            rfturn (String)rfffrrbls.flfmfntAt(rfffrrblIndfx++);
        } flsf if (ibsMorfRfffrrblExdfptions()) {
            tirow nfxtRfffrrblEx;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Appfnds tif supplifd (dibin of) rfffrrbl fxdfption onto tif fnd of
     * tif durrfnt (dibin of) rfffrrbl fxdfption. Spfnt rfffrrbl fxdfptions
     * brf trimmfd off.
     */
    LdbpRfffrrblExdfption
        bppfndUnprodfssfdRfffrrbls(LdbpRfffrrblExdfption bbdk) {

        if (dfbug) {
            Systfm.out.println(
                "LdbpRfffrrblExdfption.bppfndUnprodfssfdRfffrrbls");
            dump();
            if (bbdk != null) {
                bbdk.dump();
            }
        }

        LdbpRfffrrblExdfption front = tiis;

        if (! front.ibsMorfRfffrrbls()) {
            front = nfxtRfffrrblEx; // trim

            if ((frrorEx != null) && (front != null)) {
                front.sftNbmingExdfption(frrorEx); //bdvbndf tif sbvfd fxdfption
            }
        }

        // don't bppfnd onto itsflf
        if (tiis == bbdk) {
            rfturn front;
        }

        if ((bbdk != null) && (! bbdk.ibsMorfRfffrrbls())) {
            bbdk = bbdk.nfxtRfffrrblEx; // trim
        }

        if (bbdk == null) {
            rfturn front;
        }

        // Lodbtf tif fnd of tif durrfnt dibin
        LdbpRfffrrblExdfption ptr = front;
        wiilf (ptr.nfxtRfffrrblEx != null) {
            ptr = ptr.nfxtRfffrrblEx;
        }
        ptr.nfxtRfffrrblEx = bbdk; // bppfnd

        rfturn front;
    }

    /**
     * Tfsts if tifrf brf bny rfffrrbls rfmbining to bf prodfssfd.
     * If nbmf rfsolution ibs blrfbdy domplftfd tifn bny rfmbining
     * rfffrrbls (in tif durrfnt rfffrrbl fxdfption) will bf ignorfd.
     */
    boolfbn ibsMorfRfffrrbls() {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.ibsMorfRfffrrbls");

        rfturn (! foundEntry) && (rfffrrblIndfx < rfffrrblCount);
    }

    /**
     * Tfsts if tifrf brf bny rfffrrbl fxdfptions rfmbining to bf prodfssfd.
     */
    boolfbn ibsMorfRfffrrblExdfptions() {
        if (dfbug)
            Systfm.out.println(
                "LdbpRfffrrblExdfption.ibsMorfRfffrrblExdfptions");

        rfturn (nfxtRfffrrblEx != null);
    }

    /**
     * Sfts tif dountfr wiidi rfdords tif numbfr of iops tibt rfsult
     * from following b sfqufndf of rfffrrbls.
     */
    void sftHopCount(int iopCount) {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.sftHopCount");

        tiis.iopCount = iopCount;
    }

    /**
     * Sfts tif flbg to indidbtf tibt tif tbrgft nbmf ibs bffn rfsolvfd.
     */
    void sftNbmfRfsolvfd(boolfbn rfsolvfd) {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.sftNbmfRfsolvfd");

        foundEntry = rfsolvfd;
    }

    /**
     * Sfts tif fxdfption gfnfrbtfd wiilf prodfssing b rfffrrbl.
     * Only tif first fxdfption is rfdordfd.
     */
    void sftNbmingExdfption(NbmingExdfption f) {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.sftNbmingExdfption");

        if (frrorEx == null) {
            f.sftRootCbusf(tiis); //rfdord tif rfffrrbl fxdfption tibt dbusfd it
            frrorEx = f;
        }
    }

    /**
     * Gfts tif nfw RDN nbmf.
     */
    String gftNfwRdn() {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.gftNfwRdn");

        rfturn nfwRdn;
    }

    /**
     * Sfts tif nfw RDN nbmf so tibt tif rfnbmf opfrbtion dbn bf domplftfd
     * (wifn b rfffrrbl is bfing followfd).
     */
    void sftNfwRdn(String nfwRdn) {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.sftNfwRdn");

        tiis.nfwRdn = nfwRdn;
    }

    /**
     * Gfts tif fxdfption gfnfrbtfd wiilf prodfssing b rfffrrbl.
     */
    NbmingExdfption gftNbmingExdfption() {
        if (dfbug)
            Systfm.out.println("LdbpRfffrrblExdfption.gftNbmingExdfption");

        rfturn frrorEx;
    }

    /**
     * Displby tif stbtf of fbdi flfmfnt in b dibin of LdbpRfffrrblExdfption
     * objfdts.
     */
    void dump() {

        Systfm.out.println();
        Systfm.out.println("LdbpRfffrrblExdfption.dump");
        LdbpRfffrrblExdfption ptr = tiis;
        wiilf (ptr != null) {
            ptr.dumpStbtf();
            ptr = ptr.nfxtRfffrrblEx;
        }
    }

    /**
     * Displby tif stbtf of tiis LdbpRfffrrblExdfption objfdt.
     */
    privbtf void dumpStbtf() {
        Systfm.out.println("LdbpRfffrrblExdfption.dumpStbtf");
        Systfm.out.println("  ibsiCodf=" + ibsiCodf());
        Systfm.out.println("  foundEntry=" + foundEntry);
        Systfm.out.println("  skipTiisRfffrrbl=" + skipTiisRfffrrbl);
        Systfm.out.println("  rfffrrblIndfx=" + rfffrrblIndfx);

        if (rfffrrbls != null) {
            Systfm.out.println("  rfffrrbls:");
            for (int i = 0; i < rfffrrblCount; i++) {
                Systfm.out.println("    [" + i + "] " + rfffrrbls.flfmfntAt(i));
            }
        } flsf {
            Systfm.out.println("  rfffrrbls=null");
        }

        Systfm.out.println("  frrorEx=" + frrorEx);

        if (nfxtRfffrrblEx == null) {
            Systfm.out.println("  nfxtRffEx=null");
        } flsf {
            Systfm.out.println("  nfxtRffEx=" + nfxtRfffrrblEx.ibsiCodf());
        }
        Systfm.out.println();
    }
}
