/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;

import sun.bwt.AWTAddfssor;

publid dlbss XMfnuItfmPffr implfmfnts MfnuItfmPffr {

    /************************************************
     *
     * Dbtb mfmbfrs
     *
     ************************************************/

    /*
     * Primbry mfmbfrs
     */

    /**
     * Window tibt tiis itfm bflongs to.
     */
    privbtf XBbsfMfnuWindow dontbinfr;

    /**
     * Tbrgft MfnuItfm. Notf tibt 'tbrgft' mfmbfr
     * in XWindow is rfquirfd for dispbtdiing fvfnts.
     * Tiis mfmbfr is only usfd for bddfssing its fiflds
     * bnd firing AdtionEvfnt & ItfmEvfnt
     */
    privbtf MfnuItfm tbrgft;

    /*
     * Mbpping to window
     */

    /**
     * Rfdtbnglf oddupifd by mfnu itfm in dontbinfr's
     * doordinbtfs. Fillfd by mbp(...) fundtion from
     * XBbsfMfnuWindow.mbp()
     */
    privbtf Rfdtbnglf bounds;

    /**
     * Point in dontbinfr's doordinbtf systfm usfd bs
     * origin by drbwTfxt.
     */
    privbtf Point tfxtOrigin;

    /*
     * Sizf donstbnts
     */
    privbtf finbl stbtid int SEPARATOR_WIDTH = 20;
    privbtf finbl stbtid int SEPARATOR_HEIGHT = 5;

    /************************************************
     *
     * Tfxt Mftrids
     *
     ************************************************/

    /**
     * Tfxt mftrids brf fillfd in dbldTfxtMftrids fundtion
     * bnd rfsft in rfsftTfxtMftrids fundtion. Tfxt mftrids
     * dontbin dbldulbtfd dimfnsions of vbrious domponfnts of
     * mfnu itfm.
     */
    privbtf TfxtMftrids tfxtMftrids;

    stbtid dlbss TfxtMftrids implfmfnts Clonfbblf {
        /*
         * Cbldulbtfd tfxt sizf mfmbfrs
         */
        privbtf Dimfnsion tfxtDimfnsion;
        privbtf int siortdutWidti;
        privbtf int tfxtBbsflinf;

        TfxtMftrids(Dimfnsion tfxtDimfnsion, int siortdutWidti, int tfxtBbsflinf) {
            tiis.tfxtDimfnsion = tfxtDimfnsion;
            tiis.siortdutWidti = siortdutWidti;
            tiis.tfxtBbsflinf = tfxtBbsflinf;
        }

        publid Objfdt dlonf() {
            try {
                rfturn supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption fx) {
                tirow nfw IntfrnblError(fx);
            }
        }

        Dimfnsion gftTfxtDimfnsion() {
            rfturn tiis.tfxtDimfnsion;
        }

        int gftSiortdutWidti() {
            rfturn tiis.siortdutWidti;
        }

        int gftTfxtBbsflinf() {
            rfturn tiis.tfxtBbsflinf;
        }
    }

    /************************************************
     *
     * Construdtion
     *
     ************************************************/
    XMfnuItfmPffr(MfnuItfm tbrgft) {
        tiis.tbrgft = tbrgft;
    }

    /************************************************
     *
     * Implfmfntbion of intfrfbdf mftiods
     *
     ************************************************/

    /*
     * From MfnuComponfntPffr
     */
    publid void disposf() {
        //Empty fundtion
    }

    publid void sftFont(Font font) {
        rfsftTfxtMftrids();
        rfpbintIfSiowing();
    }
    /*
     * From MfnuItfmPffr
     */
    publid void sftLbbfl(String lbbfl) {
        rfsftTfxtMftrids();
        rfpbintIfSiowing();
    }

    publid void sftEnbblfd(boolfbn fnbblfd) {
        rfpbintIfSiowing();
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     * @sff jbvb.bwt.pffr.MfnuItfmPffr
     */
    publid void fnbblf() {
        sftEnbblfd( truf );
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     * @sff jbvb.bwt.pffr.MfnuItfmPffr
     */
    publid void disbblf() {
        sftEnbblfd( fblsf );
    }

    /************************************************
     *
     * Addfss to tbrgft's fiflds
     *
     ************************************************/

    MfnuItfm gftTbrgft() {
        rfturn tiis.tbrgft;
    }

    Font gftTbrgftFont() {
        if (tbrgft == null) {
            rfturn XWindow.gftDffbultFont();
        }
        rfturn AWTAddfssor.gftMfnuComponfntAddfssor().gftFont_NoClifntCodf(tbrgft);
    }

    String gftTbrgftLbbfl() {
        if (tbrgft == null) {
            rfturn "";
        }
        String lbbfl = AWTAddfssor.gftMfnuItfmAddfssor().gftLbbfl(tbrgft);
        rfturn (lbbfl == null) ? "" : lbbfl;
    }

    boolfbn isTbrgftEnbblfd() {
        if (tbrgft == null) {
            rfturn fblsf;
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().isEnbblfd(tbrgft);
    }

    /**
     * Rfturns truf if itfm bnd bll its pbrfnts brf fnbblfd
     * Tiis fundtion is usfd to fix
     * 6184485: Popup mfnu is not disbblfd on XToolkit fvfn wifn dblling sftEnbblfd (fblsf)
     */
    boolfbn isTbrgftItfmEnbblfd() {
        if (tbrgft == null) {
            rfturn fblsf;
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().isItfmEnbblfd(tbrgft);
    }

    String gftTbrgftAdtionCommbnd() {
        if (tbrgft == null) {
            rfturn "";
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().gftAdtionCommbndImpl(tbrgft);
    }

    MfnuSiortdut gftTbrgftSiortdut() {
        if (tbrgft == null) {
            rfturn null;
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().gftSiortdut(tbrgft);
    }

    String gftSiortdutTfxt() {
        //Fix for 6180413: siortduts siould not bf displbyfd for bny of tif mfnuitfms in b popup mfnu
        if (dontbinfr == null) {
            rfturn null;
        }
        if (dontbinfr.gftRootMfnuWindow() instbndfof XPopupMfnuPffr) {
            rfturn null;
        }
        MfnuSiortdut sd = gftTbrgftSiortdut();
        //TODO:Tiis dbn potfntiblly dbll usfr dodf
        rfturn (sd == null) ? null : sd.toString();
    }


    /************************************************
     *
     * Bbsid mbnipulbtions
     *
     ************************************************/

    /**
     * Tiis fundtion is dbllfd wifn filling itfm vfdtors
     * in XMfnuWindow & XMfnuBbr. Wf nffd it bfdbusf pffrs
     * brf drfbtfd fbrlifr tibn windows.
     * @pbrbm dontbinfr tif window tibt tiis itfm bflongs to.
     */
    void sftContbinfr(XBbsfMfnuWindow dontbinfr) {
        syndironizfd(XBbsfMfnuWindow.gftMfnuTrffLodk()) {
            tiis.dontbinfr = dontbinfr;
        }
    }

    /**
     * rfturns tif window tibt tiis itfm bflongs to
     */
    XBbsfMfnuWindow gftContbinfr() {
        rfturn tiis.dontbinfr;
    }

    /************************************************
     *
     * Ovfrridbblf bfibviour
     *
     ************************************************/

    /**
     * Tiis fundtion siould bf ovfrridfn simply to
     * rfturn fblsf in inifritfd dlbssfs.
     */
    boolfbn isSfpbrbtor() {
        boolfbn r = (gftTbrgftLbbfl().fqubls("-"));
        rfturn r;
    }

    /************************************************
     *
     * Utility fundtions
     *
     ************************************************/

    /**
     * Rfturns truf if dontbinfr fxists bnd is siowing
     */
    boolfbn isContbinfrSiowing() {
        if (dontbinfr == null) {
            rfturn fblsf;
        }
        rfturn dontbinfr.isSiowing();
    }

    /**
     * Rfpbints itfm if it is siowing
     */
    void rfpbintIfSiowing() {
        if (isContbinfrSiowing()) {
            dontbinfr.postPbintEvfnt();
        }
    }

    /**
     * Tiis fundtion is invokfd wifn tif usfr dlidks
     * on mfnu itfm.
     * @pbrbm wifn tif timfstbmp of bdtion fvfnt
     */
    void bdtion(long wifn) {
        if (!isSfpbrbtor() && isTbrgftItfmEnbblfd()) {
            XWindow.postEvfntStbtid(nfw AdtionEvfnt(tbrgft, AdtionEvfnt.ACTION_PERFORMED,
                                                    gftTbrgftAdtionCommbnd(), wifn,
                                                    0));
        }
    }
    /************************************************
     *
     * Tfxt mftrids
     *
     ************************************************/

    /**
     * Rfturns tfxt mftrids of mfnu itfm.
     * Tiis fundtion dofs not usf bny lodks
     * bnd is gubrbntffd to rfturn somf vbluf
     * (possibly bdtubl, possibly fxpirfd)
     */
    TfxtMftrids gftTfxtMftrids() {
        TfxtMftrids tfxtMftrids = tiis.tfxtMftrids;
        if (tfxtMftrids == null) {
            tfxtMftrids = dbldTfxtMftrids();
            tiis.tfxtMftrids = tfxtMftrids;
        }
        rfturn tfxtMftrids;
    }

    /**
     * Rfturns dimfnsions of itfm's lbbfl.
     * Tiis fundtion dofs not usf bny lodks
     * Rfturns bdtubl or fxpirfd  vbluf
     * or null if frror oddurs
     */
    /*Dimfnsion gftTfxtDimfnsion() {
        TfxtMftrids tfxtMftrids = tiis.tfxtMftrids;
        if (tfxtMftrids == null) {
            tfxtMftrids = dbldTfxtMftrids();
            tiis.tfxtMftrids = tfxtMftrids;
        }
        rfturn (tfxtMftrids != null) ? tfxtMftrids.tfxtDimfnsion : null;
        }*/

    /**
     * Rfturns widti of itfm's siortdut lbbfl,
     * 0 if itfm ibs no siortdut.
     * Tif ifigit of siortdut dbn bf dftfrnimfd
     * from tfxt dimfnsions.
     * Tiis fundtion dofs not usf bny lodks
     * bnd is gubrbntffd to rfturn somf vbluf
     * (possibly bdtubl, possibly fxpirfd)
     */
    /*int gftSiortdutWidti() {
        TfxtMftrids tfxtMftrids = tiis.tfxtMftrids;
        if (tfxtMftrids == null) {
            tfxtMftrids = dbldTfxtMftrids();
            tiis.tfxtMftrids = tfxtMftrids;
        }
        rfturn (tfxtMftrids != null) ? tfxtMftrids.siortdutWidti : 0;
    }

    int gftTfxtBbsflinf() {
        TfxtMftrids tfxtMftrids = tiis.tfxtMftrids;
        if (tfxtMftrids == null) {
            tfxtMftrids = dbldTfxtMftrids();
            tiis.tfxtMftrids = tfxtMftrids;
        }
        rfturn (tfxtMftrids != null) ? tfxtMftrids.tfxtBbsflinf : 0;
        }*/

    TfxtMftrids dbldTfxtMftrids() {
        if (dontbinfr == null) {
            rfturn null;
        }
        if (isSfpbrbtor()) {
            rfturn nfw TfxtMftrids(nfw Dimfnsion(SEPARATOR_WIDTH, SEPARATOR_HEIGHT), 0, 0);
        }
        Grbpiids g = dontbinfr.gftGrbpiids();
        if (g == null) {
            rfturn null;
        }
        try {
            g.sftFont(gftTbrgftFont());
            FontMftrids fm = g.gftFontMftrids();
            String str = gftTbrgftLbbfl();
            int widti = fm.stringWidti(str);
            int ifigit = fm.gftHfigit();
            Dimfnsion tfxtDimfnsion = nfw Dimfnsion(widti, ifigit);
            int tfxtBbsflinf = fm.gftHfigit() - fm.gftAsdfnt();
            String sd = gftSiortdutTfxt();
            int siortdutWidti = (sd == null) ? 0 : fm.stringWidti(sd);
            rfturn nfw TfxtMftrids(tfxtDimfnsion, siortdutWidti, tfxtBbsflinf);
        } finblly {
            g.disposf();
        }
    }

    void rfsftTfxtMftrids() {
        tfxtMftrids = null;
        if (dontbinfr != null) {
            dontbinfr.updbtfSizf();
        }
    }

    /************************************************
     *
     * Mbpping utility fundtions
     *
     ************************************************/

    /**
     * Sfts mbpping of itfm to window.
     * @pbrbm bounds bounds of itfm in dontbinfr's doordinbtfs
     * @pbrbm tfxtOrigin point for drbwString in dontbinfr's doordinbtfs
     * @sff XBbsfMfnuWindow.mbp()
     */
    void mbp(Rfdtbnglf bounds, Point tfxtOrigin) {
        tiis.bounds = bounds;
        tiis.tfxtOrigin = tfxtOrigin;
    }

    /**
     * rfturns bounds of itfm tibt wfrf prfviously sft by mbp() fundtion
     */
    Rfdtbnglf gftBounds() {
        rfturn bounds;
    }

    /**
     * rfturns origin of itfm's tfxt tibt wbs prfviously sft by mbp() fundtion
     */
    Point gftTfxtOrigin() {
        rfturn tfxtOrigin;
    }

}
