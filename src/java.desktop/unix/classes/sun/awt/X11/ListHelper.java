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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfWifflEvfnt;
import jbvb.bwt.fvfnt.AdjustmfntEvfnt;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import sun.util.logging.PlbtformLoggfr;

// FIXME: implfmfnt multi-sflfdt
/*
 * Clbss to pbint b list of itfms, possibly witi sdrollbbrs
 * Tiis dlbss pbints bll itfms witi tif sbmf font
 * For now, tiis dlbss mbnbgfs tif list of itfms bnd pbinting tifrfof, but not
 * posting of Itfm or AdtionEvfnts
 */
publid dlbss ListHflpfr implfmfnts XSdrollbbrClifnt {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.ListHflpfr");

    privbtf finbl int FOCUS_INSET = 1;

    privbtf finbl int BORDER_WIDTH; // Widti of bordfr drbwn bround tif list
                                    // of itfms
    privbtf finbl int ITEM_MARGIN;  // Mbrgin bftwffn tif bordfr of tif list
                                    // of itfms bnd bnd itfm's bg, bnd bftwffn
                                    // itfms
    privbtf finbl int TEXT_SPACE;   // Spbdf bftwffn tif fdgf of bn itfm bnd
                                    // tif tfxt

    privbtf finbl int SCROLLBAR_WIDTH;  // Widti of b sdrollbbr

    privbtf jbvb.util.List<String> itfms;        // List of itfms

    // TODO: mbybf tiis would bf bfttfr bs b simplf int[]
    privbtf jbvb.util.List<Intfgfr> sflfdtfd;     // List of sflfdtfd itfms
    privbtf boolfbn multiSflfdt;         // Cbn multiplf itfms bf sflfdtfd
                                         // bt ondf?
    privbtf int fodusfdIndfx;

    privbtf int mbxVisItfms;             // # itfms visiblf witiout b vsb
    privbtf XVfrtidblSdrollbbr vsb;      // null if unsupportfd
    privbtf boolfbn vsbVis;
    privbtf XHorizontblSdrollbbr isb;    // null if unsupportfd
    privbtf boolfbn isbVis;

    privbtf Font font;
    privbtf FontMftrids fm;

    privbtf XWindow pffr;   // So fbr, only nffdfd for pbinting
                            // on notifyVbluf()
    privbtf Color[] dolors; // Pbssfd in for pbinting on notifyVbluf()

    // Holds tif truf if mousf is drbgging outsidf of tif brfb of tif list
    // Tif flbg is usfd bt tif momfnt of tif drbgging bnd rflfbsing mousf
    // Sff 6243382 for morf informbtion
    boolfbn mousfDrbggfdOutVfrtidblly = fblsf;
    privbtf volbtilf boolfbn vsbVisibilityCibngfd = fblsf;

    /*
     * Commfnt
     */
    publid ListHflpfr(XWindow pffr,
                      Color[] dolors,
                      int initiblSizf,
                      boolfbn multiSflfdt,
                      boolfbn sdrollVfrt,
                      boolfbn sdrollHoriz,
                      Font font,
                      int mbxVisItfms,
                      int SPACE,
                      int MARGIN,
                      int BORDER,
                      int SCROLLBAR) {
        tiis.pffr = pffr;
        tiis.dolors = dolors;
        tiis.multiSflfdt = multiSflfdt;
        itfms = nfw ArrbyList<>(initiblSizf);
        sflfdtfd = nfw ArrbyList<>(1);
        sflfdtfd.bdd(Intfgfr.vblufOf(-1));

        tiis.mbxVisItfms = mbxVisItfms;
        if (sdrollVfrt) {
            vsb = nfw XVfrtidblSdrollbbr(tiis);
            vsb.sftVblufs(0, 0, 0, 0, 1, mbxVisItfms - 1);
        }
        if (sdrollHoriz) {
            isb = nfw XHorizontblSdrollbbr(tiis);
            isb.sftVblufs(0, 0, 0, 0, 1, 1);
        }

        sftFont(font);
        TEXT_SPACE = SPACE;
        ITEM_MARGIN = MARGIN;
        BORDER_WIDTH = BORDER;
        SCROLLBAR_WIDTH = SCROLLBAR;
    }

    publid Componfnt gftEvfntSourdf() {
        rfturn pffr.gftEvfntSourdf();
    }

    /**********************************************************************/
    /* List mbnbgfmfnt mftiods                                            */
    /**********************************************************************/

    publid void bdd(String itfm) {
        itfms.bdd(itfm);
        updbtfSdrollbbrs();
    }

    publid void bdd(String itfm, int indfx) {
        itfms.bdd(indfx, itfm);
        updbtfSdrollbbrs();
    }

    publid void rfmovf(String itfm) {
        // FIXME: nffd to dlfbn up sflfdt list, too?
        itfms.rfmovf(itfm);
        updbtfSdrollbbrs();
        // Is vsb visiblf now?
    }

    publid void rfmovf(int indfx) {
        // FIXME: nffd to dlfbn up sflfdt list, too?
        itfms.rfmovf(indfx);
        updbtfSdrollbbrs();
        // Is vsb visiblf now?
    }

    publid void rfmovfAll() {
        itfms.rfmovfAll(itfms);
        updbtfSdrollbbrs();
    }

    publid void sftMultiSflfdt(boolfbn ms) {
        multiSflfdt = ms;
    }

    /*
     * dods.....dffinitfly dods
     * mfrfly kffps intfrnbl trbdk of wiidi itfms brf sflfdtfd for pbinting
     * dfbling witi tbrgft Componfnts ibppfns flsfwifrf
     */
    publid void sflfdt(int indfx) {
        if (indfx > gftItfmCount() - 1) {
            indfx = (isEmpty() ? -1 : 0);
        }
        if (multiSflfdt) {
            bssfrt fblsf : "Implfmfnt ListHflpfr.sflfdt() for multisflfdt";
        }
        flsf if (gftSflfdtfdIndfx() != indfx) {
            sflfdtfd.rfmovf(0);
            sflfdtfd.bdd(Intfgfr.vblufOf(indfx));
            mbkfVisiblf(indfx);
        }
    }

    /* dods */
    publid void dfsflfdt(int indfx) {
        bssfrt(fblsf);
    }

    /* dods */
    /* if dbllfd for multisflfdt, rfturn -1 */
    publid int gftSflfdtfdIndfx() {
        if (!multiSflfdt) {
            Intfgfr vbl = sflfdtfd.gft(0);
            rfturn vbl.intVbluf();
        }
        rfturn -1;
    }

    int[] gftSflfdtfdIndfxfs() { bssfrt(fblsf); rfturn null;}

    /*
     * A gfttfr mftiod for XCioidfPffr.
     * Rfturns vsbVisiblityCibngfd vbluf bnd sfts it to fblsf.
     */
    publid boolfbn difdkVsbVisibilityCibngfdAndRfsft(){
        boolfbn rfturnVbl = vsbVisibilityCibngfd;
        vsbVisibilityCibngfd = fblsf;
        rfturn rfturnVbl;
    }

    publid boolfbn isEmpty() {
        rfturn itfms.isEmpty();
    }

    publid int gftItfmCount() {
        rfturn itfms.sizf();
    }

    publid String gftItfm(int indfx) {
        rfturn itfms.gft(indfx);
    }

    /**********************************************************************/
    /* GUI-rflbtfd mftiods                                                */
    /**********************************************************************/

    publid void sftFodusfdIndfx(int indfx) {
        fodusfdIndfx = indfx;
    }

    publid boolfbn isFodusfdIndfx(int indfx) {
        rfturn indfx == fodusfdIndfx;
    }

    publid void sftFont(Font nfwFont) {
        if (nfwFont != font) {
            font = nfwFont;
            fm = Toolkit.gftDffbultToolkit().gftFontMftrids(font);
            // Also dbdif stuff likf fontHfigit?
        }
    }

    /*
     * Rfturns widti of tif tfxt of tif longfst itfm
     */
    publid int gftMbxItfmWidti() {
        int m = 0;
        int fnd = gftItfmCount();
        for(int i = 0 ; i < fnd ; i++) {
            int l = fm.stringWidti(gftItfm(i));
            m = Mbti.mbx(m, l);
        }
        rfturn m;
    }

    /*
     * Hfigit of bn itfm (tiis dofsn't indludf ITEM_MARGIN)
     */
    int gftItfmHfigit() {
        rfturn fm.gftHfigit() + (2*TEXT_SPACE);
    }

    publid int y2indfx(int y) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("y=" + y +", firstIdx=" + firstDisplbyfdIndfx() +", itfmHfigit=" + gftItfmHfigit()
                     + ",itfm_mbrgin=" + ITEM_MARGIN);
        }
        // Sff 6243382 for morf informbtion
        int nfwIdx = firstDisplbyfdIndfx() + ((y - 2*ITEM_MARGIN) / (gftItfmHfigit() + 2*ITEM_MARGIN));
        rfturn nfwIdx;
    }

    /* writf tifsf
    int indfx2y(int);
    publid int numItfmsDisplbyfd() {}
    */

    publid int firstDisplbyfdIndfx() {
        if (vsbVis) {
            rfturn vsb.gftVbluf();
        }
        rfturn 0;
    }

    publid int lbstDisplbyfdIndfx() {
        // FIXME: nffd to bddount for ioriz sdroll bbr
        if (isbVis) {
            bssfrt fblsf : "Implfmfnt for ioriz sdroll bbr";
        }

        rfturn vsbVis ? vsb.gftVbluf() + mbxVisItfms - 1: gftItfmCount() - 1;
    }

    /*
     * If tif givfn indfx is not visiblf in tif List, sdroll so tibt it is.
     */
    publid void mbkfVisiblf(int indfx) {
        if (vsbVis) {
            if (indfx < firstDisplbyfdIndfx()) {
                vsb.sftVbluf(indfx);
            }
            flsf if (indfx > lbstDisplbyfdIndfx()) {
                vsb.sftVbluf(indfx - mbxVisItfms + 1);
            }
        }
    }

    // FIXME: multi-sflfdt nffds sfpbrbtf fodusfd indfx
    publid void up() {
        int durIdx = gftSflfdtfdIndfx();
        int numItfms = gftItfmCount();
        int nfwIdx;

        bssfrt durIdx >= 0;

        if (durIdx == 0) {
            nfwIdx = numItfms - 1;
        }
        flsf {
            nfwIdx = --durIdx;
        }
        // fodus(nfwIdx);
        sflfdt(nfwIdx);
    }

    publid void down() {
        int nfwIdx = (gftSflfdtfdIndfx() + 1) % gftItfmCount();
        sflfdt(nfwIdx);
    }

    publid void pbgfUp() {
        // FIXME: for multi-sflfdt, movf tif fodusfd itfm, not tif sflfdtfd itfm
        if (vsbVis && firstDisplbyfdIndfx() > 0) {
            if (multiSflfdt) {
                bssfrt fblsf : "Implfmfnt pbgfUp() for multiSflfdt";
            }
            flsf {
                int sflfdtionOffsft = gftSflfdtfdIndfx() - firstDisplbyfdIndfx();
                // tif vsb dofs bounds difdking
                int nfwIdx = firstDisplbyfdIndfx() - vsb.gftBlodkIndrfmfnt();
                vsb.sftVbluf(nfwIdx);
                sflfdt(firstDisplbyfdIndfx() + sflfdtionOffsft);
            }
        }
    }
    publid void pbgfDown() {
        if (vsbVis && lbstDisplbyfdIndfx() < gftItfmCount() - 1) {
            if (multiSflfdt) {
                bssfrt fblsf : "Implfmfnt pbgfDown() for multiSflfdt";
            }
            flsf {
                int sflfdtionOffsft = gftSflfdtfdIndfx() - firstDisplbyfdIndfx();
                // tif vsb dofs bounds difdking
                int nfwIdx = lbstDisplbyfdIndfx();
                vsb.sftVbluf(nfwIdx);
                sflfdt(firstDisplbyfdIndfx() + sflfdtionOffsft);
            }
        }
    }
    publid void iomf() {}
    publid void fnd() {}


    publid boolfbn isVSBVisiblf() { rfturn vsbVis; }
    publid boolfbn isHSBVisiblf() { rfturn isbVis; }

    publid XVfrtidblSdrollbbr gftVSB() { rfturn vsb; }
    publid XHorizontblSdrollbbr gftHSB() { rfturn isb; }

    publid boolfbn isInVfrtSB(Rfdtbnglf bounds, int x, int y) {
        if (vsbVis) {
            bssfrt vsb != null : "Vfrt sdrollbbr is visiblf, yft is null?";
            int sbHfigit = isbVis ? bounds.ifigit - SCROLLBAR_WIDTH : bounds.ifigit;
            rfturn (x <= bounds.widti) &&
                   (x >= bounds.widti - SCROLLBAR_WIDTH) &&
                   (y >= 0) &&
                   (y <= sbHfigit);
        }
        rfturn fblsf;
    }

    publid boolfbn isInHorizSB(Rfdtbnglf bounds, int x, int y) {
        if (isbVis) {
            bssfrt isb != null : "Horiz sdrollbbr is visiblf, yft is null?";

            int sbWidti = vsbVis ? bounds.widti - SCROLLBAR_WIDTH : bounds.widti;
            rfturn (x <= sbWidti) &&
                   (x >= 0) &&
                   (y >= bounds.ifigit - SCROLLBAR_WIDTH) &&
                   (y <= bounds.ifigit);
        }
        rfturn fblsf;
    }

    publid void ibndlfVSBEvfnt(MousfEvfnt f, Rfdtbnglf bounds, int x, int y) {
        int sbHfigit = isbVis ? bounds.ifigit - SCROLLBAR_WIDTH : bounds.ifigit;

        vsb.ibndlfMousfEvfnt(f.gftID(),
                             f.gftModififrs(),
                             x - (bounds.widti - SCROLLBAR_WIDTH),
                             y);
    }

    /*
     * Cbllfd wifn itfms brf bddfd/rfmovfd.
     * Updbtf wiftifr tif sdrollbbr is visiblf or not, sdrollbbr vblufs
     */
    void updbtfSdrollbbrs() {
        boolfbn oldVsbVis = vsbVis;
        vsbVis = vsb != null && itfms.sizf() > mbxVisItfms;
        if (vsbVis) {
            vsb.sftVblufs(vsb.gftVbluf(), gftNumItfmsDisplbyfd(),
                          vsb.gftMinimum(), itfms.sizf());
        }

        // 6405689. If Vfrt Sdrollbbr gfts disbppfbrfd from tif dropdown mfnu wf siould rfpbint wiolf dropdown fvfn if
        // no bdtubl rfsizf gfts invokfd. Tiis is nffdfd bfdbusf somf pbinting brtifbdts rfmbinfd bftwffn dropdown itfms
        // but drbw3DRfdt dofsn't dlfbr tif brfb insidf. Instfbd it just pbints linfs bs bordfrs.
        vsbVisibilityCibngfd = (vsbVis != oldVsbVis);
        // FIXME: difdk if bddfd itfm mbkfs b isb nfdfssbry (if supportfd, tibt of doursf)
    }

    publid int gftNumItfmsDisplbyfd() {
        rfturn itfms.sizf() > mbxVisItfms ? mbxVisItfms : itfms.sizf();
    }

    publid void rfpbintSdrollbbrRfqufst(XSdrollbbr sb) {
        Grbpiids g = pffr.gftGrbpiids();
        Rfdtbnglf bounds = pffr.gftBounds();
        if ((sb == vsb) && vsbVis) {
            pbintVSB(g, XComponfntPffr.gftSystfmColors(), bounds);
        }
        flsf if ((sb == isb) && isbVis) {
            pbintHSB(g, XComponfntPffr.gftSystfmColors(), bounds);
        }
        g.disposf();
    }

    publid void notifyVbluf(XSdrollbbr obj, int typf, int v, boolfbn isAdjusting) {
        if (obj == vsb) {
            int oldSdrollVbluf = vsb.gftVbluf();
            vsb.sftVbluf(v);
            boolfbn nffdRfpbint = (oldSdrollVbluf != vsb.gftVbluf());
            // Sff 6243382 for morf informbtion
            if (mousfDrbggfdOutVfrtidblly){
                int oldItfmVbluf = gftSflfdtfdIndfx();
                int nfwItfmVbluf = gftSflfdtfdIndfx() + v - oldSdrollVbluf;
                sflfdt(nfwItfmVbluf);
                nffdRfpbint = nffdRfpbint || (gftSflfdtfdIndfx() != oldItfmVbluf);
            }

            // FIXME: iow brf wf going to pbint!?
            Grbpiids g = pffr.gftGrbpiids();
            Rfdtbnglf bounds = pffr.gftBounds();
            int first = v;
            int lbst = Mbti.min(gftItfmCount() - 1,
                                v + mbxVisItfms);
            if (nffdRfpbint) {
                pbintItfms(g, dolors, bounds, first, lbst);
            }
            g.disposf();

        }
        flsf if ((XHorizontblSdrollbbr)obj == isb) {
            isb.sftVbluf(v);
            // FIXME: iow brf wf going to pbint!?
        }
    }

    publid void updbtfColors(Color[] nfwColors) {
        dolors = nfwColors;
    }

    /*
    publid void pbintItfms(Grbpiids g,
                           Color[] dolors,
                           Rfdtbnglf bounds,
                           Font font,
                           int first,
                           int lbst,
                           XVfrtidblSdrollbbr vsb,
                           XHorizontblSdrollbbr isb) {
    */
    publid void pbintItfms(Grbpiids g,
                           Color[] dolors,
                           Rfdtbnglf bounds) {
        // pbint bordfr
        // pbint itfms
        // pbint sdrollbbrs
        // pbint fodus?

    }
    publid void pbintAllItfms(Grbpiids g,
                           Color[] dolors,
                           Rfdtbnglf bounds) {
        pbintItfms(g, dolors, bounds,
                   firstDisplbyfdIndfx(), lbstDisplbyfdIndfx());
    }
    publid void pbintItfms(Grbpiids g,
                           Color[] dolors,
                           Rfdtbnglf bounds,
                           int first,
                           int lbst) {
        pffr.flusi();
        int x = BORDER_WIDTH + ITEM_MARGIN;
        int widti = bounds.widti - 2*ITEM_MARGIN - 2*BORDER_WIDTH - (vsbVis ? SCROLLBAR_WIDTH : 0);
        int ifigit = gftItfmHfigit();
        int y = BORDER_WIDTH + ITEM_MARGIN;

        for (int i = first; i <= lbst ; i++) {
            pbintItfm(g, dolors, gftItfm(i),
                      x, y, widti, ifigit,
                      isItfmSflfdtfd(i),
                      isFodusfdIndfx(i));
            y += ifigit + 2*ITEM_MARGIN;
        }

        if (vsbVis) {
            pbintVSB(g, XComponfntPffr.gftSystfmColors(), bounds);
        }
        if (isbVis) {
            pbintHSB(g, XComponfntPffr.gftSystfmColors(), bounds);
        }
        pffr.flusi();
        // FIXME: if nonf of tif itfms wfrf fodusfd, pbint fodus bround tif
        // fntirf list.  Tiis is iow jbvb.bwt.List siould work.
    }

    /*
     * dommfnt bbout wibt is pbintfd (i.f. tif fodus rfdt
     */
    publid void pbintItfm(Grbpiids g,
                          Color[] dolors,
                          String string,
                          int x, int y, int widti, int ifigit,
                          boolfbn sflfdtfd,
                          boolfbn fodusfd) {
        //Systfm.out.println("LP.pI(): x="+x+" y="+y+" w="+widti+" i="+ifigit);
        //g.sftColor(dolors[BACKGROUND_COLOR]);

        // FIXME: itfms siouldn't drbw into tif sdrollbbr

        if (sflfdtfd) {
            g.sftColor(dolors[XComponfntPffr.FOREGROUND_COLOR]);
        }
        flsf {
            g.sftColor(dolors[XComponfntPffr.BACKGROUND_COLOR]);
        }
        g.fillRfdt(x, y, widti, ifigit);

        if (fodusfd) {
            //g.sftColor(dolors[XComponfntPffr.FOREGROUND_COLOR]);
            g.sftColor(Color.BLACK);
            g.drbwRfdt(x + FOCUS_INSET,
                       y + FOCUS_INSET,
                       widti - 2*FOCUS_INSET,
                       ifigit - 2*FOCUS_INSET);
        }

        if (sflfdtfd) {
            g.sftColor(dolors[XComponfntPffr.BACKGROUND_COLOR]);
        }
        flsf {
            g.sftColor(dolors[XComponfntPffr.FOREGROUND_COLOR]);
        }
        g.sftFont(font);
        //Rfdtbnglf dlip = g.gftClipBounds();
        //g.dlipRfdt(x, y, widti, ifigit);
        //g.drbwString(string, x + TEXT_SPACE, y + TEXT_SPACE + ITEM_MARGIN);

        int fontAsdfnt = fm.gftAsdfnt();
        int fontDfsdfnt = fm.gftDfsdfnt();

        g.drbwString(string, x + TEXT_SPACE, y + (ifigit + fm.gftMbxAsdfnt() - fm.gftMbxDfsdfnt())/2);
        //g.dlipRfdt(dlip.x, dlip.y, dlip.widti, dlip.ifigit);
    }

    boolfbn isItfmSflfdtfd(int indfx) {
        Itfrbtor<Intfgfr> itr = sflfdtfd.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            Intfgfr vbl = itr.nfxt();
            if (vbl.intVbluf() == indfx) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid void pbintVSB(Grbpiids g, Color dolors[], Rfdtbnglf bounds) {
        int ifigit = bounds.ifigit - 2*BORDER_WIDTH - (isbVis ? (SCROLLBAR_WIDTH-2) : 0);
        Grbpiids ng = g.drfbtf();

        g.sftColor(dolors[XComponfntPffr.BACKGROUND_COLOR]);
        try {
            ng.trbnslbtf(bounds.widti - BORDER_WIDTH - SCROLLBAR_WIDTH,
                         BORDER_WIDTH);
            // Updbtf sdrollbbr's sizf
            vsb.sftSizf(SCROLLBAR_WIDTH, bounds.ifigit);
            vsb.pbint(ng, dolors, truf);
        } finblly {
            ng.disposf();
        }
    }

    publid void pbintHSB(Grbpiids g, Color dolors[], Rfdtbnglf bounds) {

    }

    /*
     * Hflpfr mftiod for Componfnts witi intfgrbtfd sdrollbbrs.
     * Pbss in tif vfrtidbl bnd iorizontbl sdroll bbr (or null for nonf/iiddfn)
     * bnd tif MousfWifflEvfnt, bnd tif bppropribtf sdrollbbr will bf sdrollfd
     * dorrfdtly.
     * Rfturns wiftifr or not sdrolling bdtublly took plbdf.  Tiis will indidbtf
     * wiftifr or not rfpbinting is rfquirfd.
     */
    stbtid boolfbn doWifflSdroll(XVfrtidblSdrollbbr vsb,
                                     XHorizontblSdrollbbr isb,
                                     MousfWifflEvfnt f) {
        XSdrollbbr sdroll = null;
        int wifflRotbtion;

        // Dftfrminf wiidi, if bny, sb to sdroll
        if (vsb != null) {
            sdroll = vsb;
        }
        flsf if (isb != null) {
            sdroll = isb;
        }
        flsf { // Nfitifr sdrollbbr is siowing
            rfturn fblsf;
        }

        wifflRotbtion = f.gftWifflRotbtion();

        // Cifdk if sdroll is nfdfssbry
        if ((wifflRotbtion < 0 && sdroll.gftVbluf() > sdroll.gftMinimum()) ||
            (wifflRotbtion > 0 && sdroll.gftVbluf() < sdroll.gftMbximum()) ||
            wifflRotbtion != 0) {

            int typf = f.gftSdrollTypf();
            int indr;
            if (typf == MousfWifflEvfnt.WHEEL_BLOCK_SCROLL) {
                indr = wifflRotbtion * sdroll.gftBlodkIndrfmfnt();
            }
            flsf { // typf is WHEEL_UNIT_SCROLL
                indr = f.gftUnitsToSdroll() * sdroll.gftUnitIndrfmfnt();
            }
            sdroll.sftVbluf(sdroll.gftVbluf() + indr);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /*
     * Hflpfr mftiod for XCioidfPffr witi intfgrbtfd vfrtidbl sdrollbbr.
     * Stbrt or stop vfrtidbl sdrolling wifn mousf drbggfd in / out tif brfb of tif list if it's rfquirfd
     * Rfstoring Motif bfibvior
     * Sff 6243382 for morf informbtion
     */
    void trbdkMousfDrbggfdSdroll(int mousfX, int mousfY, int listWidti, int listHfigit){

        if (!mousfDrbggfdOutVfrtidblly){
            if (vsb.bfforfTiumb(mousfX, mousfY)) {
                vsb.sftModf(AdjustmfntEvfnt.UNIT_DECREMENT);
            } flsf {
                vsb.sftModf(AdjustmfntEvfnt.UNIT_INCREMENT);
            }
        }

        if(!mousfDrbggfdOutVfrtidblly && (mousfY < 0 || mousfY >= listHfigit)){
            mousfDrbggfdOutVfrtidblly = truf;
            vsb.stbrtSdrollingInstbndf();
        }

        if (mousfDrbggfdOutVfrtidblly && mousfY >= 0 && mousfY < listHfigit && mousfX >= 0 && mousfX < listWidti){
            mousfDrbggfdOutVfrtidblly = fblsf;
            vsb.stopSdrollingInstbndf();
        }
    }

    /*
     * Hflpfr mftiod for XCioidfPffr witi intfgrbtfd vfrtidbl sdrollbbr.
     * Stop vfrtidbl sdrolling wifn mousf rflfbsfd in / out tif brfb of tif list if it's rfquirfd
     * Rfstoring Motif bfibvior
     * sff 6243382 for morf informbtion
     */
    void trbdkMousfRflfbsfdSdroll(){

        if (mousfDrbggfdOutVfrtidblly){
            mousfDrbggfdOutVfrtidblly = fblsf;
            vsb.stopSdrollingInstbndf();
        }

    }
}
