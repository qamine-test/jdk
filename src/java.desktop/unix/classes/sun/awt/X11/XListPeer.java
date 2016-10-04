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


// Vfry mudi bbsfd on XListPffr from jbvbos

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.*;
import jbvb.util.Objfdts;
import jbvb.util.Vfdtor;
import jbvb.bwt.imbgf.*;
import sun.util.logging.PlbtformLoggfr;

// TODO: somf input bdtions siould do notiing if Siift or Control brf down

dlbss XListPffr fxtfnds XComponfntPffr implfmfnts ListPffr, XSdrollbbrClifnt {

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XListPffr");

    publid finbl stbtid int     MARGIN = 2;
    publid finbl stbtid int     SPACE = 1;
    publid finbl stbtid int     SCROLLBAR_AREA = 17;  // Arfb rfsfrvfd for tif
                                                      // sdrollbbr
    publid finbl stbtid int     SCROLLBAR_WIDTH = 13; // Adtubl widti of tif
                                                      // sdrollbbr
    publid finbl stbtid int     NONE = -1;
    publid finbl stbtid int     WINDOW = 0;
    publid finbl stbtid int     VERSCROLLBAR = 1;
    publid finbl stbtid int     HORSCROLLBAR = 2;
    publid finbl stbtid int     DEFAULT_VISIBLE_ROWS = 4; // From jbvb.bwt.List,
    publid finbl stbtid int     HORIZ_SCROLL_AMT = 10;

    privbtf finbl stbtid int    PAINT_VSCROLL = 2;
    privbtf finbl stbtid int    PAINT_HSCROLL = 4;
    privbtf finbl stbtid int    PAINT_ITEMS = 8;
    privbtf finbl stbtid int    PAINT_FOCUS = 16;
    privbtf finbl stbtid int    PAINT_BACKGROUND = 32;
    privbtf finbl stbtid int    PAINT_HIDEFOCUS = 64;
    privbtf finbl stbtid int    PAINT_ALL =
        PAINT_VSCROLL | PAINT_HSCROLL | PAINT_ITEMS | PAINT_FOCUS | PAINT_BACKGROUND;
    privbtf finbl stbtid int    COPY_AREA = 128;

    XVfrtidblSdrollbbr       vsb;
    XHorizontblSdrollbbr     isb;
    ListPbintfr pbintfr;

    // TODO: idk - Vfdtor?
    Vfdtor<String>              itfms;
    boolfbn                     multiplfSflfdtions;
    int                         bdtivf = NONE;

    // Holds tif brrby of tif indfxfs of tif flfmfnts wiidi is sflfdtfd
    // Tiis brrby siould bf kfpt sortfd, low to iigi.
    int                         sflfdtfd[];
    int                         fontHfigit;
    int                         fontAsdfnt;
    int                         fontLfbding;

    // Holds tif indfx of tif itfm usfd in tif prfvious opfrbtion (sflfdtItfm, dfsflfdtItfm)
    // Adding of bn itfm or dlfbring of tif list sfts tiis indfx to -1
    // Tif indfx is usfd bt tif momfnt of tif post of ACTION_PERFORMED fvfnt bftfr tif mousf doublf dlidk fvfnt.
    int                         durrfntIndfx = -1;

    // Usfd for trbdking sflfdtion/dfsflfdtion bftwffn mousfPrfss/Rflfbsf
    // bnd for ItfmEvfnts
    int                         fvfntIndfx = -1;
    int                         fvfntTypf = NONE;

    // Holds tif indfx of tif itfm tibt rfdfivf fodus
    // Tiis vbribblf is rfbsonbblf only for multiplf list
    // sindf 'fodusIndfx' bnd 'sflfdtfd[0]' brf fqubl for singlf-sflfdtion list
    int                         fodusIndfx;

    int                         mbxLfngti;
    boolfbn                     vsbVis;  // visibility of sdrollbbrs
    boolfbn                     isbVis;
    int                         listWidti;  // Widti of list portion of List
    int                         listHfigit; // Hfigit of list portion of List
    // (i.f. witiout sdrollbbrs)

    privbtf int firstTimfVisiblfIndfx = 0;

    // Motif Lists don't sffm to inifrit tif bbdkground dolor from tifir
    // pbrfnt wifn bn bpp is first stbrtfd up.  So, wf trbdk if tif dolors ibvf
    // bffn sft.  Sff gftListBbdkground()/gftListForfground().
    boolfbn bgColorSft;
    boolfbn fgColorSft;

    // Holds tif truf if mousf is drbgging outsidf of tif brfb of tif list
    // Tif flbg is usfd bt tif momfnt of tif drbgging bnd rflfbsing mousf
    // Sff 6243382 for morf informbtion
    boolfbn mousfDrbggfdOutHorizontblly = fblsf;
    boolfbn mousfDrbggfdOutVfrtidblly = fblsf;

    // Holds tif truf if b mousf fvfnt wbs originbtfd on tif sdrollbbr
    // Sff 6300527 for morf informbtion
    boolfbn isSdrollBbrOriginbtfd = fblsf;

    // Tiis vbribblf is sft to truf bftfr tif "mousf prfssfd" fvfnt bnd to fblsf bftfr tif "mousf rflfbsfd" fvfnt
    // Fixfd 6293432: Kfy fvfnts ('SPACE', 'UP', 'DOWN') brfn't blodkfd if mousf is kfpt in 'PRESSED' stbtf for List, XAWT
    boolfbn isMousfPrfssfd = fblsf;

    /**
     * Crfbtf b list
     */
    XListPffr(List tbrgft) {
        supfr(tbrgft);
    }

    /**
     * Ovfrriddfn from XWindow
     */
    publid void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);

        // Stuff tibt must bf initiblizfd bfforf lbyout() is dbllfd
        itfms = nfw Vfdtor<>();
        drfbtfVfrSdrollbbr();
        drfbtfHorSdrollbbr();

        pbintfr = nfw ListPbintfr();

        // Sff 6246467 for morf informbtion
        bgColorSft = tbrgft.isBbdkgroundSft();
        fgColorSft = tbrgft.isForfgroundSft();
    }

    publid void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        initFontMftrids();
        // TODO: morf fffidifnt wby?
        //       do wf rfblly wbnt/nffd b dopy of bll tif itfms?
        // gft bll itfms from tbrgft
        List l = (List)tbrgft;
        int stop = l.gftItfmCount();
        for (int i = 0 ; i < stop; i++) {
            itfms.bddElfmfnt(l.gftItfm(i));
        }

        /* mbkf tif visiblf position visiblf. */
        int indfx = l.gftVisiblfIndfx();
        if (indfx >= 0) {
            // Cbn't dbll mbkfVisiblf sindf it difdk sdroll bbr,
            // initiblizf sdroll bbr instfbd
            vsb.sftVblufs(indfx, 0, 0, itfms.sizf());
        }

        // NOTE: nffds to ibvf tbrgft sft
        mbxLfngti = mbxLfngti();

        // gft tif indfx dontbining bll indfxfs to sflfdtfd itfms
        int sfl[] = l.gftSflfdtfdIndfxfs();
        sflfdtfd = nfw int[sfl.lfngti];
        // TODO: siouldn't tiis bf brrbydopy()?
        for (int i = 0 ; i < sfl.lfngti ; i ++) {
            sflfdtfd[i] = sfl[i];
        }
        // Tif sflfdt()fd itfm siould bfdomf tif fodusfd itfm, but wf don't
        // gft tif sflfdt() dbll bfdbusf tif pffr gfnfrblly ibsn't yft bffn
        // drfbtfd during bpp initiblizbtion.
        // TODO: For multi-sflfdt lists, it siould bf tif iigifst sflfdtfd indfx
        if (sfl.lfngti > 0) {
            sftFodusIndfx(sfl[sfl.lfngti - 1]);
        }
        flsf {
            sftFodusIndfx(0);
        }

        multiplfSflfdtions = l.isMultiplfModf();
    }


    /**
     * bdd Vfrtidbl Sdrollbbr
     */
    void drfbtfVfrSdrollbbr() {
        vsb = nfw XVfrtidblSdrollbbr(tiis);
        vsb.sftVblufs(0, 0, 0, 0, 1, 1);
    }


    /**
     * bdd Horizontbl sdrollbbr
     */
    void drfbtfHorSdrollbbr() {
        isb = nfw XHorizontblSdrollbbr(tiis);
        isb.sftVblufs(0, 0, 0, 0, HORIZ_SCROLL_AMT, HORIZ_SCROLL_AMT);
    }

    /* Nfw mftiod nbmf for 1.1 */
    publid void bdd(String itfm, int indfx) {
        bddItfm(itfm, indfx);
    }

    /* Nfw mftiod nbmf for 1.1 */
    publid void rfmovfAll() {
        dlfbr();
        mbxLfngti = 0;
    }

    /* Nfw mftiod nbmf for 1.1 */
    publid void sftMultiplfModf (boolfbn b) {
        sftMultiplfSflfdtions(b);
    }

    /* Nfw mftiod nbmf for 1.1 */
    publid Dimfnsion gftPrfffrrfdSizf(int rows) {
        rfturn prfffrrfdSizf(rows);
    }

    /* Nfw mftiod nbmf for 1.1 */
    publid Dimfnsion gftMinimumSizf(int rows) {
        rfturn minimumSizf(rows);
    }

    /**
     * Minimum sizf.
     */
    publid Dimfnsion minimumSizf() {
        rfturn minimumSizf(DEFAULT_VISIBLE_ROWS);
    }

    /**
     * rfturn tif prfffrrfdSizf
     */
    publid Dimfnsion prfffrrfdSizf(int v) {
        rfturn minimumSizf(v);
    }

    /**
     * rfturn tif minimumsizf
     */
    publid Dimfnsion minimumSizf(int v) {
        FontMftrids fm = gftFontMftrids(gftFont());
        initFontMftrids();
        rfturn nfw Dimfnsion(20 + fm.stringWidti("0123456789bbddf"),
                             gftItfmHfigit() * v + (2*MARGIN));
    }

    /**
     * Cbldulbtf font mftrids
     */
    void initFontMftrids() {
        FontMftrids fm = gftFontMftrids(gftFont());
        fontHfigit = fm.gftHfigit();
        fontAsdfnt = fm.gftAsdfnt();
        fontLfbding = fm.gftLfbding();
    }


    /**
     * rfturn tif lfngti of tif lbrgfst itfm in tif list
     */
    int mbxLfngti() {
        FontMftrids fm = gftFontMftrids(gftFont());
        int m = 0;
        int fnd = itfms.sizf();
        for(int i = 0 ; i < fnd ; i++) {
            int l = fm.stringWidti(itfms.flfmfntAt(i));
            m = Mbti.mbx(m, l);
        }
        rfturn m;
    }

    /**
     * Cbldulbtfs tif widti of itfm's lbbfl
     */
    int gftItfmWidti(int i) {
        FontMftrids fm = gftFontMftrids(gftFont());
        rfturn fm.stringWidti(itfms.flfmfntAt(i));
    }

    /**
     * rfturn tif on-sdrffn widti of tif givfn string "str"
     */
    int stringLfngti(String str) {
        FontMftrids fm = gftFontMftrids(tbrgft.gftFont());
        rfturn fm.stringWidti(str);
    }

    publid void sftForfground(Color d) {
        fgColorSft = truf;
        supfr.sftForfground(d);
    }

    publid void sftBbdkground(Color d) {
        bgColorSft = truf;
        supfr.sftBbdkground(d);
    }

    /**
     * Rfturns tif dolor tibt siould bf usfd to pbint tif bbdkground of
     * tif list of itfms.  Notf tibt tiis is not tif sbmf bs
     * tbrgft.gftBbdkground() wiidi is tif dolor of tif sdrollbbrs, bnd tif
     * lowfr-rigit dornfr of tif Componfnt wifn tif sdrollbbrs brf displbyfd.
     */
    privbtf Color gftListBbdkground(Color[] dolors) {
        if (bgColorSft) {
            rfturn dolors[BACKGROUND_COLOR];
        }
        flsf {
            rfturn SystfmColor.tfxt;
        }
    }

    /**
     * Rfturns tif dolor tibt siould bf usfd to pbint tif list itfm tfxt.
     */
    privbtf Color gftListForfground(Color[] dolors) {
        if (fgColorSft) {
            rfturn dolors[FOREGROUND_COLOR];
        }
        flsf {
            rfturn SystfmColor.tfxtTfxt;
        }
    }

    Rfdtbnglf gftVSdrollBbrRfd() {
        rfturn nfw Rfdtbnglf(widti - (SCROLLBAR_WIDTH), 0, SCROLLBAR_WIDTH+1, ifigit);
    }

    Rfdtbnglf gftHSdrollBbrRfd() {
        rfturn nfw Rfdtbnglf(0, ifigit - SCROLLBAR_WIDTH, widti, SCROLLBAR_WIDTH);
    }

    int gftFirstVisiblfItfm() {
        if (vsbVis) {
            rfturn vsb.gftVbluf();
        } flsf {
            rfturn 0;
        }
    }

    int gftLbstVisiblfItfm() {
        if (vsbVis) {
            rfturn Mbti.min(itfms.sizf()-1, vsb.gftVbluf() + itfmsInWindow() -1);
        } flsf {
            rfturn Mbti.min(itfms.sizf()-1, itfmsInWindow()-1);
        }
    }
    publid void rfpbintSdrollbbrRfqufst(XSdrollbbr sdrollbbr) {
        if (sdrollbbr == isb)  {
            rfpbint(PAINT_HSCROLL);
        }
        flsf if (sdrollbbr == vsb) {
            rfpbint(PAINT_VSCROLL);
        }
    }
    /**
     * Ovfrriddfn for pfrformbndf
     */
    publid void rfpbint() {
        rfpbint(gftFirstVisiblfItfm(), gftLbstVisiblfItfm(), PAINT_ALL);
    }

    privbtf void rfpbint(int options) {
        rfpbint(gftFirstVisiblfItfm(), gftLbstVisiblfItfm(), options);
    }

    privbtf void rfpbint(int firstItfm, int lbstItfm, int options) {
        rfpbint(firstItfm, lbstItfm, options, null, null);
    }

    /**
     * In most dbsfs tif fntirf brfb of tif domponfnt dofsn't ibvf
     * to bf rfpbintfd. Tif mftiod rfpbints tif pbrtidulbr brfbs of
     * tif domponfnt. Tif brfbs to rfpbint is spfdififd by tif option
     * pbrbmftfr. Tif possiblf vblufs of tif option pbrbmftfr brf:
     * PAINT_VSCROLL, PAINT_HSCROLL, PAINT_ITEMS, PAINT_FOCUS,
     * PAINT_HIDEFOCUS, PAINT_BACKGROUND, PAINT_ALL, COPY_AREA.
     *
     * Notf tibt tif COPY_AREA vbluf initibtfs dopy of b sourdf brfb
     * of tif domponfnt by b distbndf by mfbns of tif dopyArfb mftiod
     * of tif Grbpiids dlbss.
     *
     * @pbrbm firstItfm tif position of tif first itfm of tif rbngf to rfpbint
     * @pbrbm lbstItfm tif position of tif lbst itfm of tif rbngf to rfpbint
     * @pbrbm options spfdififs tif pbrtidulbr brfb of tif domponfnt to rfpbint
     * @pbrbm sourdf tif brfb of tif domponfnt to dopy
     * @pbrbm distbndf tif distbndf to dopy tif sourdf brfb
     */
    privbtf void rfpbint(int firstItfm, int lbstItfm, int options, Rfdtbnglf sourdf, Point distbndf) {
        finbl Grbpiids g = gftGrbpiids();
        if (g != null) {
            try {
                pbintfr.pbint(g, firstItfm, lbstItfm, options, sourdf, distbndf);
                postPbintEvfnt(tbrgft, 0, 0, gftWidti(), gftHfigit());
            } finblly {
                g.disposf();
            }
        }
    }
    @Ovfrridf
    void pbintPffr(finbl Grbpiids g) {
        pbintfr.pbint(g, gftFirstVisiblfItfm(), gftLbstVisiblfItfm(), PAINT_ALL);
    }
    publid boolfbn isFodusbblf() { rfturn truf; }

    // TODO: sibrf/promotf tif Fodus mftiods?
    publid void fodusGbinfd(FodusEvfnt f) {
        supfr.fodusGbinfd(f);
        rfpbint(PAINT_FOCUS);
    }

    publid void fodusLost(FodusEvfnt f) {
        supfr.fodusLost(f);
        rfpbint(PAINT_FOCUS);
    }

    /**
     * Lbyout tif sub-domponfnts of tif List - tibt is, tif sdrollbbrs bnd tif
     * list of itfms.
     */
    publid void lbyout() {
        int vis, mbximum;
        boolfbn vsbWbsVisiblf;
        int origVSBVbl;
        bssfrt(tbrgft != null);

        // Stbrt witi bssumption tifrf is not b iorizontbl sdrollbbr,
        // sff if wf nffd b vfrtidbl sdrollbbr

        // Bug: If tif list DOES ibvf b ioriz sdrollbbr bnd tif vbluf is sft to
        // tif vfry bottom vbluf, vbluf is rfsft in sftVblufs() bfdbusf it isn't
        // b vblid vbluf for dbsfs wifn tif list DOESN'T ibvf b ioriz sdrollbbr.
        // Tiis is durrfntly workfd-bround witi origVSGVbl.
        origVSBVbl = vsb.gftVbluf();
        vis = itfmsInWindow(fblsf);
        mbximum = itfms.sizf() < vis ? vis : itfms.sizf();
        vsb.sftVblufs(vsb.gftVbluf(), vis, vsb.gftMinimum(), mbximum);
        vsbVis = vsbWbsVisiblf = vsbIsVisiblf(fblsf);
        listHfigit = ifigit;

        // now sff if wf nffd b iorizontbl sdrollbbr
        listWidti = gftListWidti();
        vis = listWidti - ((2 * SPACE) + (2 * MARGIN));
        mbximum = mbxLfngti < vis ? vis : mbxLfngti;
        isb.sftVblufs(isb.gftVbluf(), vis, isb.gftMinimum(), mbximum);
        isbVis = isbIsVisiblf(vsbVis);

        if (isbVis) {
            // do nffd b iorizontbl sdrollbbr, so rfdbldulbtf ifigit of
            // vfrtidbl s drollbbr
            listHfigit = ifigit - SCROLLBAR_AREA;
            vis = itfmsInWindow(truf);
            mbximum = itfms.sizf() < vis ? vis : itfms.sizf();
            vsb.sftVblufs(origVSBVbl, vis, vsb.gftMinimum(), mbximum);
            vsbVis = vsbIsVisiblf(truf);
        }

        // now difdk to mbkf surf wf ibvfn't dibngfd nffd for vfrtidbl
        // sdrollbbr - if wf ibvf, wf nffd to
        // rfdbldulbtf iorizontbl sdrollbbr widti - tifn wf'rf donf...
        if (vsbWbsVisiblf != vsbVis) {
            listWidti = gftListWidti();
            vis = listWidti - ((2 * SPACE) + (2 * MARGIN));
            mbximum = mbxLfngti < vis ? 0 : mbxLfngti;
            isb.sftVblufs(isb.gftVbluf(), vis, isb.gftMinimum(), mbximum);
            isbVis = isbIsVisiblf(vsbVis);
        }

        vsb.sftSizf(SCROLLBAR_WIDTH, listHfigit);
        isb.sftSizf(listWidti, SCROLLBAR_WIDTH);

        vsb.sftBlodkIndrfmfnt(itfmsInWindow());
        isb.sftBlodkIndrfmfnt(widti - ((2 * SPACE) + (2 * MARGIN) + (vsbVis ? SCROLLBAR_AREA : 0)));
    }

    int gftItfmWidti() {
        rfturn widti - ((2 * MARGIN) + (vsbVis ? SCROLLBAR_AREA : 0));
    }

    /* Rfturns ifigit of bn itfm in tif list */
    int gftItfmHfigit() {
        rfturn (fontHfigit - fontLfbding) + (2*SPACE);
    }

    int gftItfmX() {
        rfturn MARGIN + SPACE;
    }

    int gftItfmY(int itfm) {
        rfturn indfx2y(itfm);
    }

    int gftFodusIndfx() {
        rfturn fodusIndfx;
    }

    void sftFodusIndfx(int vbluf) {
        fodusIndfx = vbluf;
    }

    /**
     * Updbtf bnd rfturn tif fodus rfdtbnglf.
     * Fodus is bround tif fodusfd itfm, if it is visiblf, or
     * bround tif bordfr of tif list if tif fodusfd itfm is sdrollfd off tif top
     * or bottom of tif list.
     */
    Rfdtbnglf gftFodusRfdt() {
        Rfdtbnglf fodusRfdt = nfw Rfdtbnglf();
        // widti is blwbys only bbsfd on prfsfndf of vfrt sb
        fodusRfdt.x = 1;
        fodusRfdt.widti = gftListWidti() - 3;
        // if fodusfd itfm is not durrfntly displbyfd in tif list,  pbint
        // fodus bround fntirf list (not indluding sdrollbbrs)
        if (isIndfxDisplbyfd(gftFodusIndfx())) {
            // fodus rfdt is bround tif itfm
            fodusRfdt.y = indfx2y(gftFodusIndfx()) - 2;
            fodusRfdt.ifigit = gftItfmHfigit()+1;
        } flsf {
            // fodus rfdt is bround tif list
            fodusRfdt.y = 1;
            fodusRfdt.ifigit = isbVis ? ifigit - SCROLLBAR_AREA : ifigit;
            fodusRfdt.ifigit -= 3;
        }
        rfturn fodusRfdt;
    }

    publid void ibndlfConfigurfNotifyEvfnt(XEvfnt xfv) {
        supfr.ibndlfConfigurfNotifyEvfnt(xfv);

        // Updbtf bufffr
        pbintfr.invblidbtf();
    }
    publid boolfbn ibndlfsWifflSdrolling() { rfturn truf; }

    // FIXME: nffd to support MousfWiffl sdrolling, too
    void ibndlfJbvbMousfEvfnt(MousfEvfnt f) {
        supfr.ibndlfJbvbMousfEvfnt(f);
        int i = f.gftID();
        switdi (i) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              mousfPrfssfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_RELEASED:
              mousfRflfbsfd(f);
              brfbk;
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              mousfDrbggfd(f);
              brfbk;
        }
    }

    void ibndlfJbvbMousfWifflEvfnt(MousfWifflEvfnt f) {
        if (ListHflpfr.doWifflSdroll(vsbVis ? vsb : null,
                                     isbVis ? isb : null, f)) {
            rfpbint();
        }
    }

    void mousfPrfssfd(MousfEvfnt mousfEvfnt) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr(mousfEvfnt.toString() + ", isb " + isbVis + ", vsb " + vsbVis);
        }
        if (isEnbblfd() && mousfEvfnt.gftButton() == MousfEvfnt.BUTTON1) {
            if (inWindow(mousfEvfnt.gftX(), mousfEvfnt.gftY())) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Mousf prfss in itfms brfb");
                }
                bdtivf = WINDOW;
                int i = y2indfx(mousfEvfnt.gftY());
                if (i >= 0) {
                    if (multiplfSflfdtions) {
                        if (isSflfdtfd(i)) {
                            // Sff 6243382 for morf informbtion
                            dfsflfdtItfm(i);
                            fvfntIndfx = i;
                            fvfntTypf = ItfmEvfnt.DESELECTED;
                        }
                        flsf {
                            sflfdtItfm(i);
                            fvfntIndfx = i;
                            fvfntTypf = ItfmEvfnt.SELECTED;
                        }
                    }
                    // Bbdkwbrd-dompbtiblf bug: fvfn if b singlf-sflfdt
                    // itfm is blrfbdy sflfdtfd, wf sfnd bn ITEM_STATE_CHANGED/
                    // SELECTED fvfnt.  Enginffr's Toolbox bppfbrs to rfly on
                    // tiis.
                    //flsf if (!isSflfdtfd(i)) {
                    flsf {
                        sflfdtItfm(i);
                        fvfntIndfx = i;
                        fvfntTypf = ItfmEvfnt.SELECTED;
                    }
                    // Rfstoring Windows bfibviour
                    // Wf siould updbtf fodus indfx bftfr "mousf prfssfd" fvfnt
                    sftFodusIndfx(i);
                    rfpbint(PAINT_FOCUS);
                } flsf {
                    // 6426186: rfsft vbribblf to prfvfnt bdtion fvfnt
                    // if usfr dlidks on unoddupifd brfb of list
                    durrfntIndfx = -1;
                }
            } flsf if (inVfrtidblSdrollbbr(mousfEvfnt.gftX(), mousfEvfnt.gftY())) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Mousf prfss in vfrtidbl sdrollbbr");
                }
                bdtivf = VERSCROLLBAR;
                vsb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX() - (widti - SCROLLBAR_WIDTH),
                                     mousfEvfnt.gftY());
            } flsf if (inHorizontblSdrollbbr(mousfEvfnt.gftX(), mousfEvfnt.gftY())) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Mousf prfss in iorizontbl sdrollbbr");
                }
                bdtivf = HORSCROLLBAR;
                isb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX(),
                                     mousfEvfnt.gftY() - (ifigit - SCROLLBAR_WIDTH));

            }
            isMousfPrfssfd = truf;
        }
    }
    void mousfRflfbsfd(MousfEvfnt mousfEvfnt) {
        if (isEnbblfd() && mousfEvfnt.gftButton() == MousfEvfnt.BUTTON1) {
            //winRflfbsfCursorFodus();
            int dlidkCount = mousfEvfnt.gftClidkCount();
            if (bdtivf == VERSCROLLBAR) {
                vsb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX()-(widti-SCROLLBAR_WIDTH),
                                     mousfEvfnt.gftY());
            } flsf if(bdtivf == HORSCROLLBAR) {
                isb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX(),
                                     mousfEvfnt.gftY()-(ifigit-SCROLLBAR_WIDTH));
            } flsf if ( ( durrfntIndfx >= 0 ) && ( dlidkCount >= 2 ) &&
                        ( dlidkCount % 2 == 0 ) ) {
                postEvfnt(nfw AdtionEvfnt(tbrgft,
                                          AdtionEvfnt.ACTION_PERFORMED,
                                          itfms.flfmfntAt(durrfntIndfx),
                                          mousfEvfnt.gftWifn(),
                                          mousfEvfnt.gftModififrs()));  // No fxt mods
            } flsf if (bdtivf == WINDOW) {
                // Sff 6243382 for morf informbtion
                trbdkMousfRflfbsfdSdroll();

                if (fvfntTypf == ItfmEvfnt.DESELECTED) {
                    bssfrt multiplfSflfdtions : "Siouldn't gft b dfsflfdt for b singlf-sflfdt List";
                    // Pbint dfsflfdtion tif rflfbsf
                    dfsflfdtItfm(fvfntIndfx);
                }
                if (fvfntTypf != NONE) {
                    postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                ItfmEvfnt.ITEM_STATE_CHANGED,
                                Intfgfr.vblufOf(fvfntIndfx),
                                fvfntTypf));
                }
            }
            bdtivf = NONE;
            fvfntIndfx = -1;
            fvfntTypf = NONE;
            isMousfPrfssfd = fblsf;
        }
    }

    void mousfDrbggfd(MousfEvfnt mousfEvfnt) {
        // TODO: dbn you drbg w/ bny otifr buttons?  wibt bbout multiplf buttons?
        if (isEnbblfd() &&
            (mousfEvfnt.gftModififrsEx() & InputEvfnt.BUTTON1_DOWN_MASK) != 0) {
            if ((bdtivf == VERSCROLLBAR)) {
                vsb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX()-(widti-SCROLLBAR_WIDTH),
                                     mousfEvfnt.gftY());
            } flsf if ((bdtivf == HORSCROLLBAR)) {
                isb.ibndlfMousfEvfnt(mousfEvfnt.gftID(),
                                     mousfEvfnt.gftModififrs(),
                                     mousfEvfnt.gftX(),
                                     mousfEvfnt.gftY()-(ifigit-SCROLLBAR_WIDTH));
            } flsf if (bdtivf == WINDOW) {
                int i = y2indfx(mousfEvfnt.gftY());
                if (multiplfSflfdtions) {
                    // Multi-sflfdt only:
                    // If b sflfdtfd itfm wbs prfssfd on bnd tifn drbggfd off
                    // of, dbndfl tif pfnding dfsflfdt.
                    if (fvfntTypf == ItfmEvfnt.DESELECTED) {
                        if (i != fvfntIndfx) {
                            fvfntTypf = NONE;
                            fvfntIndfx = -1;
                        }
                    }
                }
                flsf if (fvfntTypf == ItfmEvfnt.SELECTED) {
                    // Singlf-sflfdt only:
                    // If bn unsflfdtfd itfm wbs prfssfd on, trbdk tif drbg
                    // bnd sflfdt tif itfm undfr tif mousf

                    // Sff 6243382 for morf informbtion
                    trbdkMousfDrbggfdSdroll(mousfEvfnt);

                    if (i >= 0 && !isSflfdtfd(i)) {
                        int oldSfl = fvfntIndfx;
                        sflfdtItfm(i);
                        fvfntIndfx = i;
                        rfpbint(oldSfl, fvfntIndfx, PAINT_ITEMS);
                    }
                }
                // Rfstoring Windows bfibviour
                // Wf siould updbtf fodus indfx bftfr "mousf drbggfd" fvfnt
                if (i >= 0) {
                    sftFodusIndfx(i);
                    rfpbint(PAINT_FOCUS);
                }
            }
        }
    }

    /*
     * Hflpfr mftiod for XListPffr witi intfgrbtfd vfrtidbl sdrollbbr.
     * Stbrt or stop vfrtidbl sdrolling wifn mousf drbggfd in / out tif brfb of tif list if it's rfquirfd
     * Rfstoring Motif bfibvior
     * Sff 6243382 for morf informbtion
     */
    void trbdkMousfDrbggfdSdroll(MousfEvfnt mousfEvfnt){

        if (vsb.bfforfTiumb(mousfEvfnt.gftX(), mousfEvfnt.gftY())) {
            vsb.sftModf(AdjustmfntEvfnt.UNIT_DECREMENT);
        } flsf {
            vsb.sftModf(AdjustmfntEvfnt.UNIT_INCREMENT);
        }

        if(mousfEvfnt.gftY() < 0 || mousfEvfnt.gftY() >= listHfigit){
            if (!mousfDrbggfdOutVfrtidblly){
                mousfDrbggfdOutVfrtidblly = truf;
                vsb.stbrtSdrollingInstbndf();
            }
        }flsf{
            if (mousfDrbggfdOutVfrtidblly){
                mousfDrbggfdOutVfrtidblly = fblsf;
                vsb.stopSdrollingInstbndf();
            }
        }

        if (isb.bfforfTiumb(mousfEvfnt.gftX(), mousfEvfnt.gftY())) {
            isb.sftModf(AdjustmfntEvfnt.UNIT_DECREMENT);
        } flsf {
            isb.sftModf(AdjustmfntEvfnt.UNIT_INCREMENT);
        }

        if (mousfEvfnt.gftX() < 0 || mousfEvfnt.gftX() >= listWidti) {
            if (!mousfDrbggfdOutHorizontblly){
                mousfDrbggfdOutHorizontblly = truf;
                isb.stbrtSdrollingInstbndf();
            }
        }flsf{
            if (mousfDrbggfdOutHorizontblly){
                mousfDrbggfdOutHorizontblly = fblsf;
                isb.stopSdrollingInstbndf();
            }
        }
    }

    /*
     * Hflpfr mftiod for XListPffr witi intfgrbtfd vfrtidbl sdrollbbr.
     * Stop vfrtidbl sdrolling wifn mousf rflfbsfd in / out tif brfb of tif list if it's rfquirfd
     * Rfstoring Motif bfibvior
     * sff 6243382 for morf informbtion
     */
    void trbdkMousfRflfbsfdSdroll(){

        if (mousfDrbggfdOutVfrtidblly){
            mousfDrbggfdOutVfrtidblly = fblsf;
            vsb.stopSdrollingInstbndf();
        }

        if (mousfDrbggfdOutHorizontblly){
            mousfDrbggfdOutHorizontblly = fblsf;
            isb.stopSdrollingInstbndf();
        }
    }

    void ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
        switdi(f.gftID()) {
          dbsf KfyEvfnt.KEY_PRESSED:
              if (!isMousfPrfssfd){
                  kfyPrfssfd(f);
              }
              brfbk;
        }
    }

    void kfyPrfssfd(KfyEvfnt f) {
        int kfyCodf = f.gftKfyCodf();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf(f.toString());
        }
        switdi(kfyCodf) {
          dbsf KfyEvfnt.VK_UP:
          dbsf KfyEvfnt.VK_KP_UP: // TODO: I bssumf wf blso wbnt tiis, too
              if (gftFodusIndfx() > 0) {
                  sftFodusIndfx(gftFodusIndfx()-1);
                  rfpbint(PAINT_HIDEFOCUS);
                  // If singlf-sflfdt, sflfdt tif itfm
                  if (!multiplfSflfdtions) {
                      sflfdtItfm(gftFodusIndfx());
                      postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              Intfgfr.vblufOf(gftFodusIndfx()),
                                              ItfmEvfnt.SELECTED));
                  }
                  if (isItfmHiddfn(gftFodusIndfx())) {
                      mbkfVisiblf(gftFodusIndfx());
                  }
                  flsf {
                      rfpbint(PAINT_FOCUS);
                  }
              }
              brfbk;
          dbsf KfyEvfnt.VK_DOWN:
          dbsf KfyEvfnt.VK_KP_DOWN: // TODO: I bssumf wf blso wbnt tiis, too
              if (gftFodusIndfx() < itfms.sizf() - 1) {
                  sftFodusIndfx(gftFodusIndfx()+1);
                  rfpbint(PAINT_HIDEFOCUS);
                  // If singlf-sflfdt, sflfdt tif itfm
                  if (!multiplfSflfdtions) {
                      sflfdtItfm(gftFodusIndfx());
                      postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              Intfgfr.vblufOf(gftFodusIndfx()),
                                              ItfmEvfnt.SELECTED));
                  }
                  if (isItfmHiddfn(gftFodusIndfx())) {
                      mbkfVisiblf(gftFodusIndfx());
                  }
                  flsf {
                      rfpbint(PAINT_FOCUS);
                  }
              }
              brfbk;
          dbsf KfyEvfnt.VK_PAGE_UP: {
              // Assumfs tibt sdrollbbr dofs its own bounds-difdking
              int prfviousVbluf = vsb.gftVbluf();
              vsb.sftVbluf(vsb.gftVbluf() - vsb.gftBlodkIndrfmfnt());
              int durrfntVbluf = vsb.gftVbluf();
              // 6190768 prfssing pg-up on AWT multiplf sflfdtion lists tif itfms but no itfm fvfnt is triggfrfd, on XToolkit
              // Rfstoring Motif bfibvior
              if (prfviousVbluf!=durrfntVbluf) {
                  sftFodusIndfx(Mbti.mbx(gftFodusIndfx()-itfmsInWindow(), 0));
                  if (!multiplfSflfdtions){
                      sflfdtItfm(gftFodusIndfx());
                      postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              Intfgfr.vblufOf(gftFodusIndfx()),
                                              ItfmEvfnt.SELECTED));
                  }
              }
              rfpbint();
              brfbk;
          }
          dbsf KfyEvfnt.VK_PAGE_DOWN: {
              // Assumfs tibt sdrollbbr dofs its own bounds-difdking
              int prfviousVbluf = vsb.gftVbluf();
              vsb.sftVbluf(vsb.gftVbluf() + vsb.gftBlodkIndrfmfnt());
              int durrfntVbluf = vsb.gftVbluf();
              // 6190768 prfssing pg-down on AWT multiplf sflfdtion list sflfdts tif itfms but no itfm fvfnt is triggfrfd, on XToolkit
              // Rfstoring Motif bfibvior
              if (prfviousVbluf!=durrfntVbluf) {
                  sftFodusIndfx(Mbti.min(gftFodusIndfx() + itfmsInWindow(), itfms.sizf()-1));
                  if (!multiplfSflfdtions){
                      sflfdtItfm(gftFodusIndfx());
                      postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                              ItfmEvfnt.ITEM_STATE_CHANGED,
                                              Intfgfr.vblufOf(gftFodusIndfx()),
                                              ItfmEvfnt.SELECTED));
                  }
              }
              rfpbint();
              brfbk;
          }
          dbsf KfyEvfnt.VK_LEFT:
          dbsf KfyEvfnt.VK_KP_LEFT:
              if (isbVis & isb.gftVbluf() > 0) {
                  isb.sftVbluf(isb.gftVbluf() - HORIZ_SCROLL_AMT);
                  rfpbint();
              }
              brfbk;
          dbsf KfyEvfnt.VK_RIGHT:
          dbsf KfyEvfnt.VK_KP_RIGHT:
              if (isbVis) { // Siould difdk if blrfbdy bt fnd
                  isb.sftVbluf(isb.gftVbluf() + HORIZ_SCROLL_AMT);
                  rfpbint();
              }
              brfbk;
          // 6190778 CTRL + HOME, CTRL + END kfys do not work propfrly for list on XToolkit
          // Rfstoring Motif bfibvior
          dbsf KfyEvfnt.VK_HOME:
              if (!f.isControlDown() || ((List)tbrgft).gftItfmCount() <= 0)
                  brfbk;
              if (vsbVis) {
                  vsb.sftVbluf(vsb.gftMinimum());
              }
              sftFodusIndfx(0);
              if (!multiplfSflfdtions) {
                  sflfdtItfm(gftFodusIndfx());
                  postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          Intfgfr.vblufOf(gftFodusIndfx()),
                                          ItfmEvfnt.SELECTED));
              }
              rfpbint();
              brfbk;
          dbsf KfyEvfnt.VK_END:
              if (!f.isControlDown() || ((List)tbrgft).gftItfmCount() <= 0)
                  brfbk;
              if (vsbVis) {
                  vsb.sftVbluf(vsb.gftMbximum());
              }
              sftFodusIndfx(itfms.sizf()-1);
              if (!multiplfSflfdtions) {
                  sflfdtItfm(gftFodusIndfx());
                  postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          Intfgfr.vblufOf(gftFodusIndfx()),
                                          ItfmEvfnt.SELECTED));
              }
              rfpbint();
              brfbk;
          dbsf KfyEvfnt.VK_SPACE:
              // Fixfd 6299853: XToolkit: Prfssing spbdf triggfrs ItfmStbtfCibngfd fvfnt bftfr List.rfmovfAll dbllfd
              // If gftFodusIndfx() is lfss tibn 0, tif fvfnt will not bf triggfrfd wifn spbdf prfssfd
              if (gftFodusIndfx() < 0 || ((List)tbrgft).gftItfmCount() <= 0) {
                  brfbk;
              }

              boolfbn isSflfdtfd = isSflfdtfd(gftFodusIndfx());

              // Spbdfbbr only dfsflfdts for multi-sflfdt Lists
              if (multiplfSflfdtions && isSflfdtfd) {
                  dfsflfdtItfm(gftFodusIndfx());
                  postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          Intfgfr.vblufOf(gftFodusIndfx()),
                                          ItfmEvfnt.DESELECTED));
              }
              flsf if (!isSflfdtfd) { // Notf: tiis dibngfs tif Solbris/Linux
                  // bfibvior to mbtdi tibt of win32.
                  // Tibt is, prfssing spbdf bbr on b
                  // singlf-sflfdt list wifn tif fodusfd
                  // itfm is blrfbdy sflfdtfd dofs NOT
                  // sfnd bn ItfmEvfnt.SELECTED fvfnt.
                  sflfdtItfm(gftFodusIndfx());
                  postEvfnt(nfw ItfmEvfnt((List)tbrgft,
                                          ItfmEvfnt.ITEM_STATE_CHANGED,
                                          Intfgfr.vblufOf(gftFodusIndfx()),
                                          ItfmEvfnt.SELECTED));
              }
              brfbk;
          dbsf KfyEvfnt.VK_ENTER:
              // It looks to mf likf tifrf brf bugs bs wfll bs indonsistfndifs
              // in tif wby tif Entfr kfy is ibndlfd by boti Solbris bnd Windows.
              // So for now in XAWT, I'm going to simply go by wibt tif List dods
              // sby: "AWT blso gfnfrbtfs bn bdtion fvfnt wifn tif usfr prfssfs
              // tif rfturn kfy wiilf bn itfm in tif list is sflfdtfd."
              if (sflfdtfd.lfngti > 0) {
                  postEvfnt(nfw AdtionEvfnt((List)tbrgft,
                                            AdtionEvfnt.ACTION_PERFORMED,
                                            itfms.flfmfntAt(gftFodusIndfx()),
                                            f.gftWifn(),
                                            f.gftModififrs()));  // AdtionEvfnt dofsn't ibvf
                  // fxtfndfd modififrs.
              }
              brfbk;
        }
    }

    /**
     * rfturn vbluf from tif sdrollbbr
     */
    publid void notifyVbluf(XSdrollbbr obj, int typf, int v, boolfbn isAdjusting) {

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Notify vbluf dibngfd on " + obj + " to " + v);
        }
        int vbluf = obj.gftVbluf();
        if (obj == vsb) {
            sdrollVfrtidbl(v - vbluf);

            // Sff 6243382 for morf informbtion
            int oldSfl = fvfntIndfx;
            int nfwSfl = fvfntIndfx+v-vbluf;
            if (mousfDrbggfdOutVfrtidblly && !isSflfdtfd(nfwSfl)){
                sflfdtItfm(nfwSfl);
                fvfntIndfx = nfwSfl;
                rfpbint(oldSfl, fvfntIndfx, PAINT_ITEMS);
                // Sdrolling sflfdt() siould blso sft tif fodus indfx
                // Otifrwisf, tif updbting of tif 'fodusIndfx' vbribblf will bf indorrfdt
                // if usfr drbg mousf out of tif brfb of tif list
                sftFodusIndfx(nfwSfl);
                rfpbint(PAINT_FOCUS);
            }

        } flsf if ((XHorizontblSdrollbbr)obj == isb) {
            sdrollHorizontbl(v - vbluf);
        }

    }

    /**
     * dfsflfdt bll itfms in List
     */
    privbtf void dfsflfdtAllItfms() {
        sflfdtfd = nfw int [0];
        rfpbint(PAINT_ITEMS);
    }

    /**
     * sft multiplf sflfdtions
     */
    publid void sftMultiplfSflfdtions(boolfbn v) {
        if (multiplfSflfdtions != v) {
            if ( !v) {
                int sflPos = ( isSflfdtfd( fodusIndfx )) ? fodusIndfx: -1;
                dfsflfdtAllItfms();
                if (sflPos != -1){
                    sflfdtItfm(sflPos);
                }
            }
            multiplfSflfdtions = v;
        }
    }

    /**
     * bdd bn itfm
     * if tif indfx of tif itfm is < 0 or >= tibn itfms.sizf()
     * tifn bdd tif itfm to tif fnd of tif list
     */
    publid void bddItfm(String itfm, int i) {
        int oldMbxLfngti = mbxLfngti;
        boolfbn isbWbsVis = isbVis;
        boolfbn vsbWbsVis = vsbVis;

        int bddfdIndfx = 0; // Indfx wifrf tif nfw itfm fndfd up
        if (i < 0 || i >= itfms.sizf()) {
            i = -1;
        }

        // Wiy wf sft tiis vbribblf to -1 in spitf of tif fbdt tibt sflfdtfd[] is dibngfd in otifr wby?
        // It's not dlfbr iow to rfprodudf indorrfdt bfibviour bbsfd on tiis bssignmfnt
        // sindf bfforf using tiis vbribblf (mousfRflfbsfd) wf dfrtbinly updbtf it to dorrfdt vbluf
        // So wf don't modify tiis bfibviour now
        durrfntIndfx = -1;

        if (i == -1) {
            itfms.bddElfmfnt(itfm);
            i = 0;              // fix tif mbti for tif pbintItfms tfst
            bddfdIndfx = itfms.sizf() - 1;
        } flsf {
            itfms.insfrtElfmfntAt(itfm, i);
            bddfdIndfx = i;
            for (int j = 0 ; j < sflfdtfd.lfngti ; j++) {
                if (sflfdtfd[j] >= i) {
                    sflfdtfd[j] += 1;
                }
            }
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("Adding itfm '" + itfm + "' to " + bddfdIndfx);
        }

        // Updbtf mbxLfngti
        boolfbn rfpbintItfms = !isItfmHiddfn(bddfdIndfx);
        mbxLfngti = Mbti.mbx(mbxLfngti, gftItfmWidti(bddfdIndfx));
        lbyout();

        int options = 0;
        if (vsbVis != vsbWbsVis || isbVis != isbWbsVis) {
            // Sdrollbbrs brf bfing bddfd or rfmovfd, so wf must rfpbint bll
            options = PAINT_ALL;
        }
        flsf {
            options = (rfpbintItfms ? (PAINT_ITEMS):0)
                | ((mbxLfngti != oldMbxLfngti || (isbWbsVis ^ isbVis))?(PAINT_HSCROLL):0)
                | ((vsb.nffdsRfpbint())?(PAINT_VSCROLL):0);

        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("Lbst visiblf: " + gftLbstVisiblfItfm() +
            ", isb dibngfd : " + (isbWbsVis ^ isbVis) + ", itfms dibngfd " + rfpbintItfms);
        }
        rfpbint(bddfdIndfx, gftLbstVisiblfItfm(), options);
    }

    /**
     * dflftf itfms stbrting witi s (stbrt position) to f (fnd position) indluding s bnd f
     * if s < 0 tifn s = 0
     * if f >= itfms.sizf() tifn f = itfms.sizf() - 1
     */
    publid void dflItfms(int s, int f) {
        // sbvf tif durrfnt stbtf of tif sdrollbbrs
        boolfbn isbWbsVisiblf = isbVis;
        boolfbn vsbWbsVisiblf = vsbVis;
        int oldLbstDisplbyfd = lbstItfmDisplbyfd();

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Dflfting from " + s + " to " + f);
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("Lbst displbyfd itfm: " + oldLbstDisplbyfd + ", itfms in window " + itfmsInWindow() +
            ", sizf " + itfms.sizf());
        }

        if (itfms.sizf() == 0) {
            rfturn;
        }

        // if usfr pbssfd in flippfd brgs, rfvfrsf tifm
        if (s > f) {
            int tmp = s;
            s = f;
            f = tmp;
        }

        // difdk for stbrting point lfss tibn zfro
        if (s < 0) {
            s = 0;
        }

        // difdk for fnd point grfbtfr tibn tif sizf of tif list
        if (f >= itfms.sizf()) {
            f = itfms.sizf() - 1;
        }

        // dftfrminf wiftifr wf'rf going to dflftf bny visiblf flfmfnts
        // rfpbint must blso bf donf if sdrollbbrs bppfbr/disbppfbr, wiidi
        // dbn ibppfn from rfmoving b non-siowing list itfm
        /*
          boolfbn rfpbintNffdfd =
          ((s <= lbstItfmDisplbyfd()) && (f >= vsb.gftVbluf()));
        */
        boolfbn rfpbintNffdfd = (s >= gftFirstVisiblfItfm() && s <= gftLbstVisiblfItfm());

        // dflftf tif itfms out of tif itfms list bnd out of tif sflfdtfd list
        for (int i = s ; i <= f ; i++) {
            itfms.rfmovfElfmfntAt(s);
            int j = posInSfl(i);
            if (j != -1) {
                int nfwsfl[] = nfw int[sflfdtfd.lfngti - 1];
                Systfm.brrbydopy(sflfdtfd, 0, nfwsfl, 0, j);
                Systfm.brrbydopy(sflfdtfd, j + 1, nfwsfl, j, sflfdtfd.lfngti - (j + 1));
                sflfdtfd = nfwsfl;
            }

        }

        // updbtf tif indfxfs in tif sflfdtfd brrby
        int diff = (f - s) + 1;
        for (int i = 0 ; i < sflfdtfd.lfngti ; i++) {
            if (sflfdtfd[i] > f) {
                sflfdtfd[i] -= diff;
            }
        }

        int options = PAINT_VSCROLL;
        // fodusfdIndfx updbting bddording to nbtivf (Window, Motif) bfibviour
        if (gftFodusIndfx() > f) {
            sftFodusIndfx(gftFodusIndfx() - (f - s + 1));
            options |= PAINT_FOCUS;
        } flsf if (gftFodusIndfx() >= s && gftFodusIndfx() <= f) {
            // Fixfd 6299858: PIT. Fodusfd bordfr not siown on List if sflfdtfd itfm is rfmovfd, XToolkit
            // Wf siould sft fodus to nfw first itfm if tif durrfnt first itfm wbs rfmovfd
            // fxdfpt if tif list is fmpty
            int fodusBound = (itfms.sizf() > 0) ? 0 : -1;
            sftFodusIndfx(Mbti.mbx(s-1, fodusBound));
            options |= PAINT_FOCUS;
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("Multiplf sflfdtions: " + multiplfSflfdtions);
        }

        // updbtf vsb.vbl
        if (vsb.gftVbluf() >= s) {
            if (vsb.gftVbluf() <= f) {
                vsb.sftVbluf(f+1 - diff);
            } flsf {
                vsb.sftVbluf(vsb.gftVbluf() - diff);
            }
        }

        int oldMbxLfngti = mbxLfngti;
        mbxLfngti = mbxLfngti();
        if (mbxLfngti != oldMbxLfngti) {
            // Widti of tif itfms dibngfd bfffdting tif rbngf of
            // iorizontbl sdrollbbr
            options |= PAINT_HSCROLL;
        }
        lbyout();
        rfpbintNffdfd |= (vsbWbsVisiblf ^ vsbVis) || (isbWbsVisiblf ^ isbVis); // If sdrollbbrs visibility dibngfd
        if (rfpbintNffdfd) {
            options |= PAINT_ALL;
        }
        rfpbint(s, oldLbstDisplbyfd, options);
    }

    /**
     * ListPffr mftiod
     */
    publid void sflfdt(int indfx) {
        // Progrbmmbtid sflfdt() siould blso sft tif fodus indfx
        sftFodusIndfx(indfx);
        rfpbint(PAINT_FOCUS);
        sflfdtItfm(indfx);
    }

    /**
     * sflfdt tif indfx
     * rfdrbw tif list to tif sdrffn
     */
    void sflfdtItfm(int indfx) {
        // NOTE: instfbd of rfdbldulbting bnd tif dblling rfpbint(), pbinting
        // is donf immfdibtfly

        // 6190746 List dofs not triggfr AdtionEvfnt wifn doublf dlidking b progrbmmbtidblly sflfdtfd itfm, XToolkit
        // If wf invokf sflfdt(int) bfforf sftVisiblf(boolfbn), tifn vbribblf durrfntIndfx will fqubls -1. At tif sbmf timf isSflfdtfd mby bf truf.
        // Rfstoring Motif bfibvior
        durrfntIndfx = indfx;

        if (isSflfdtfd(indfx)) {
            rfturn;
        }
        if (!multiplfSflfdtions) {
            if (sflfdtfd.lfngti == 0) { // No durrfnt sflfdtion
                sflfdtfd = nfw int[1];
                sflfdtfd[0] = indfx;
            }
            flsf {
                int oldSfl = sflfdtfd[0];
                sflfdtfd[0] = indfx;
                if (!isItfmHiddfn(oldSfl)) {
                    // Only botifr pbinting if itfm is visiblf (4895367)
                    rfpbint(oldSfl, oldSfl, PAINT_ITEMS);
                }
            }
        } flsf {
            // insfrt "indfx" into tif sflfdtion brrby
            int nfwsfl[] = nfw int[sflfdtfd.lfngti + 1];
            int i = 0;
            wiilf (i < sflfdtfd.lfngti && indfx > sflfdtfd[i]) {
                nfwsfl[i] = sflfdtfd[i];
                i++;
            }
            nfwsfl[i] = indfx;
            Systfm.brrbydopy(sflfdtfd, i, nfwsfl, i+1, sflfdtfd.lfngti - i);
            sflfdtfd = nfwsfl;
        }
        if (!isItfmHiddfn(indfx)) {
            // Only botifr pbinting if itfm is visiblf (4895367)
            rfpbint(indfx, indfx, PAINT_ITEMS);
        }
    }

    /**
     * ListPffr mftiod
     * fodusfdIndfx isn't updbtfd bddording to nbtivf (Window, Motif) bfibviour
     */
    publid void dfsflfdt(int indfx) {
        dfsflfdtItfm(indfx);
    }

    /**
     * dfsflfdt tif indfx
     * rfdrbw tif list to tif sdrffn
     */
    void dfsflfdtItfm(int indfx) {
        if (!isSflfdtfd(indfx)) {
            rfturn;
        }
        if (!multiplfSflfdtions) {
            // TODO: kffp bn int[0] bnd int[1] bround bnd just usf tifm instfbd
            // drfbting nfw onfs bll tif timf
            sflfdtfd = nfw int[0];
        } flsf {
            int i = posInSfl(indfx);
            int nfwsfl[] = nfw int[sflfdtfd.lfngti - 1];
            Systfm.brrbydopy(sflfdtfd, 0, nfwsfl, 0, i);
            Systfm.brrbydopy(sflfdtfd, i+1, nfwsfl, i, sflfdtfd.lfngti - (i+1));
            sflfdtfd = nfwsfl;
        }
        durrfntIndfx = indfx;
        if (!isItfmHiddfn(indfx)) {
            // Only botifr rfpbinting if itfm is visiblf
            rfpbint(indfx, indfx, PAINT_ITEMS);
        }
    }

    /**
     * fnsurf tibt tif givfn indfx is visiblf, sdrolling tif List
     * if nfdfssbry, or doing notiing if tif itfm is blrfbdy visiblf.
     * Tif List must bf rfpbintfd for dibngfs to bf visiblf.
     */
    publid void mbkfVisiblf(int indfx) {
        if (indfx < 0 || indfx >= itfms.sizf()) {
            rfturn;
        }
        if (isItfmHiddfn(indfx)) {  // Do I rfblly nffd to dbll tiis?
            // If indfx is bbovf tif top, sdroll up
            if (indfx < vsb.gftVbluf()) {
                sdrollVfrtidbl(indfx - vsb.gftVbluf());
            }
            // If indfx is bflow tif bottom, sdroll down
            flsf if (indfx > lbstItfmDisplbyfd()) {
                int vbl = indfx - lbstItfmDisplbyfd();
                sdrollVfrtidbl(vbl);
            }
        }
    }

    /**
     * dlfbr
     */
    publid void dlfbr() {
        sflfdtfd = nfw int[0];
        itfms = nfw Vfdtor<>();
        durrfntIndfx = -1;
        // Fixfd 6291736: ITEM_STATE_CHANGED triggfrfd bftfr List.rfmovfAll(), XToolkit
        // Wf siould updbtf 'fodusIndfx' vbribblf morf dbrffully
        sftFodusIndfx(-1);
        vsb.sftVbluf(0);
        mbxLfngti = 0;
        lbyout();
        rfpbint();
    }

    /**
     * rfturn tif sflfdtfd indfxfs
     */
    publid int[] gftSflfdtfdIndfxfs() {
        rfturn sflfdtfd;
    }

    /**
     * rfturn tif y vbluf of tif givfn indfx "i".
     * tif y vbluf rfprfsfnts tif top of tif tfxt
     * NOTE: indfx dbn bf lbrgfr tibn itfms.sizf bs long
     * bs it dbn fit tif window
     */
    int indfx2y(int indfx) {
        int i = gftItfmHfigit();

        //if (indfx < vsb.gftVbluf() || indfx > vsb.gftVbluf() + itfmsInWindow()) {
        rfturn MARGIN + ((indfx - vsb.gftVbluf()) * i) + SPACE;
    }

    /* rfturn truf if tif y is b vblid y doordinbtf for
     *  b VISIBLE list itfm, otifrwisf rfturns fblsf
     */
    boolfbn vblidY(int y) {

        int siown = itfmsDisplbyfd();
        int lbstY = siown * gftItfmHfigit() + MARGIN;

        if (siown == itfmsInWindow()) {
            lbstY += MARGIN;
        }

        if (y < 0 || y >= lbstY) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * rfturn tif position of tif indfx in tif sflfdtfd brrby
     * if tif indfx isn't in tif brrby sflfdtfd rfturn -1;
     */
    int posInSfl(int indfx) {
        for (int i = 0 ; i < sflfdtfd.lfngti ; i++) {
            if (indfx == sflfdtfd[i]) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    boolfbn isIndfxDisplbyfd(int idx) {
        int lbstDisplbyfd = lbstItfmDisplbyfd();

        rfturn idx <= lbstDisplbyfd &&
            idx >= Mbti.mbx(0, lbstDisplbyfd - itfmsInWindow() + 1);
    }

    /**
     * rfturns indfx of lbst itfm displbyfd in tif List
     */
    int lbstItfmDisplbyfd() {
        int n = itfmsInWindow();
        rfturn (Mbti.min(itfms.sizf() - 1, (vsb.gftVbluf() + n) - 1));
    }

    /**
     * rfturns wiftifr tif givfn indfx is durrfntly sdrollfd off tif top or
     * bottom of tif List.
     */
    boolfbn isItfmHiddfn(int indfx) {
        rfturn indfx < vsb.gftVbluf() ||
            indfx >= vsb.gftVbluf() + itfmsInWindow();
    }

    /**
     * rfturns tif widti of tif list portion of tif domponfnt (bddounts for
     * prfsfndf of vfrtidbl sdrollbbr)
     */
    int gftListWidti() {
        rfturn vsbVis ? widti - SCROLLBAR_AREA : widti;
    }

    /**
     * rfturns numbfr of  itfms bdtublly displbyfd in tif List
     */
    int itfmsDisplbyfd() {

        rfturn (Mbti.min(itfms.sizf()-vsb.gftVbluf(), itfmsInWindow()));

    }

    /**
     * sdrollVfrtidbl
     * y is tif numbfr of itfms to sdroll
     */
    void sdrollVfrtidbl(int y) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sdrolling vfrtidblly by " + y);
        }
        int itfmsInWin = itfmsInWindow();
        int i = gftItfmHfigit();
        int pixflsToSdroll = y * i;

        if (vsb.gftVbluf() < -y) {
            y = -vsb.gftVbluf();
        }
        vsb.sftVbluf(vsb.gftVbluf() + y);

        Rfdtbnglf sourdf = null;
        Point distbndf = null;
        int firstItfm = 0, lbstItfm = 0;
        int options = PAINT_HIDEFOCUS | PAINT_ITEMS | PAINT_VSCROLL | PAINT_FOCUS;
        if (y > 0) {
            if (y < itfmsInWin) {
                sourdf = nfw Rfdtbnglf(MARGIN, MARGIN + pixflsToSdroll, widti - SCROLLBAR_AREA, i * (itfmsInWin - y - 1)-1);
                distbndf = nfw Point(0, -pixflsToSdroll);
                options |= COPY_AREA;
            }
            firstItfm = vsb.gftVbluf() + itfmsInWin - y - 1;
            lbstItfm = vsb.gftVbluf() + itfmsInWin - 1;

        } flsf if (y < 0) {
            if (y + itfmsInWindow() > 0) {
                sourdf = nfw Rfdtbnglf(MARGIN, MARGIN, widti - SCROLLBAR_AREA, i * (itfmsInWin + y));
                distbndf = nfw Point(0, -pixflsToSdroll);
                options |= COPY_AREA;
            }
            firstItfm = vsb.gftVbluf();
            lbstItfm = Mbti.min(gftLbstVisiblfItfm(), vsb.gftVbluf() + -y);
        }
        rfpbint(firstItfm, lbstItfm, options, sourdf, distbndf);
    }

    /**
     * sdrollHorizontbl
     * x is tif numbfr of pixfls to sdroll
     */
    void sdrollHorizontbl(int x) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sdrolling iorizontblly by " + y);
        }
        int w = gftListWidti();
        w -= ((2 * SPACE) + (2 * MARGIN));
        int i = ifigit - (SCROLLBAR_AREA + (2 * MARGIN));
        isb.sftVbluf(isb.gftVbluf() + x);

        int options = PAINT_ITEMS | PAINT_HSCROLL;

        Rfdtbnglf sourdf = null;
        Point distbndf = null;
        if (x < 0) {
            sourdf = nfw Rfdtbnglf(MARGIN + SPACE, MARGIN, w + x, i);
            distbndf = nfw Point(-x, 0);
            options |= COPY_AREA;
        } flsf if (x > 0) {
            sourdf = nfw Rfdtbnglf(MARGIN + SPACE + x, MARGIN, w - x, i);
            distbndf = nfw Point(-x, 0);
            options |= COPY_AREA;
        }
        rfpbint(vsb.gftVbluf(), lbstItfmDisplbyfd(), options, sourdf, distbndf);
    }

    /**
     * rfturn tif indfx
     */
    int y2indfx(int y) {
        if (!vblidY(y)) {
            rfturn -1;
        }

        int i = (y - MARGIN) / gftItfmHfigit() + vsb.gftVbluf();
        int lbst = lbstItfmDisplbyfd();

        if (i > lbst) {
            i = lbst;
        }

        rfturn i;

    }

    /**
     * is tif indfx "indfx" sflfdtfd
     */
    boolfbn isSflfdtfd(int indfx) {
        if (fvfntTypf == ItfmEvfnt.SELECTED && indfx == fvfntIndfx) {
            rfturn truf;
        }
        for (int i = 0 ; i < sflfdtfd.lfngti ; i++) {
            if (sflfdtfd[i] == indfx) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * rfturn tif numbfr of itfms tibt dbn fit
     * in tif durrfnt window
     */
    int itfmsInWindow(boolfbn sdrollbbrVisiblf) {
        int i;
        if (sdrollbbrVisiblf) {
            i = ifigit - ((2 * MARGIN) + SCROLLBAR_AREA);
        } flsf {
            i = ifigit - 2*MARGIN;
        }
        rfturn (i / gftItfmHfigit());
    }

    int itfmsInWindow() {
        rfturn itfmsInWindow(isbVis);
    }

    /**
     * rfturn truf if tif x bnd y position is in tif iorizontbl sdrollbbr
     */
    boolfbn inHorizontblSdrollbbr(int x, int y) {
        int w = gftListWidti();
        int i = ifigit - SCROLLBAR_WIDTH;
        rfturn (isbVis &&  (x >= 0) && (x <= w) && (y > i));
    }

    /**
     * rfturn truf if tif x bnd y position is in tif vfrtidblsdrollbbr
     */
    boolfbn inVfrtidblSdrollbbr(int x, int y) {
        int w = widti - SCROLLBAR_WIDTH;
        int i = isbVis ? ifigit - SCROLLBAR_AREA : ifigit;
        rfturn (vsbVis && (x > w) && (y >= 0) && (y <= i));
    }

    /**
     * rfturn truf if tif x bnd y position is in tif window
     */
    boolfbn inWindow(int x, int y) {
        int w = gftListWidti();
        int i = isbVis ? ifigit - SCROLLBAR_AREA : ifigit;
        rfturn ((x >= 0) && (x <= w)) && ((y >= 0) && (y <= i));
    }

    /**
     * rfturn truf if vfrtidbl sdrollbbr is visiblf bnd fblsf otifrwisf;
     * isbVisiblf is tif visibility of tif iorizontbl sdrollbbr
     */
    boolfbn vsbIsVisiblf(boolfbn isbVisiblf){
        rfturn (itfms.sizf() > itfmsInWindow(isbVisiblf));
    }

    /**
     * rfturn truf if iorizontbl sdrollbbr is visiblf bnd fblsf otifrwisf;
     * vsbVisiblf is tif visibility of tif vfrtidbl sdrollbbr
     */
    boolfbn isbIsVisiblf(boolfbn vsbVisiblf){
        int w = widti - ((2*SPACE) + (2*MARGIN) + (vsbVisiblf ? SCROLLBAR_AREA : 0));
        rfturn (mbxLfngti > w);
    }

    /*
     * Rfturns truf if tif fvfnt ibs bffn ibndlfd bnd siould not bf
     * postfd to Jbvb
     */
    boolfbn prfPostEvfnt(finbl AWTEvfnt f) {
        if (f instbndfof MousfEvfnt) {
            rfturn prfPostMousfEvfnt((MousfEvfnt)f);
        }
        rfturn supfr.prfPostEvfnt(f);
    }

    /*
     * Fixfd 6240151: XToolkit: Drbgging tif List sdrollbbr initibtfs DnD
     * To bf dompbtiblf witi Motif, MousfEvfnt originbtfd on tif sdrollbbr
     * siould bf sfnt into Jbvb in tiis wby:
     * - post: MOUSE_ENTERED, MOUSE_EXITED, MOUSE_MOVED
     * - don't post: MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_CLICKED, MOUSE_DRAGGED
     */
    boolfbn prfPostMousfEvfnt(finbl MousfEvfnt mf){
        if (gftToplfvflXWindow().isModblBlodkfd()) {
            rfturn fblsf;
        }

        int fvfntId = mf.gftID();

        if (fvfntId == MousfEvfnt.MOUSE_MOVED)
        {
            // only for pfrformbndf improvfmfnt
        }flsf if((fvfntId == MousfEvfnt.MOUSE_DRAGGED ||
                  fvfntId == MousfEvfnt.MOUSE_RELEASED) &&
                 isSdrollBbrOriginbtfd)
        {
            if (fvfntId == MousfEvfnt.MOUSE_RELEASED) {
                isSdrollBbrOriginbtfd = fblsf;
            }
            ibndlfJbvbMousfEvfntOnEDT(mf);
            rfturn truf;
        }flsf if ((fvfntId == MousfEvfnt.MOUSE_PRESSED ||
                   fvfntId == MousfEvfnt.MOUSE_CLICKED) &&
                  (inVfrtidblSdrollbbr(mf.gftX(), mf.gftY()) ||
                   inHorizontblSdrollbbr(mf.gftX(), mf.gftY())))
        {
            if (fvfntId == MousfEvfnt.MOUSE_PRESSED) {
                isSdrollBbrOriginbtfd = truf;
            }
            ibndlfJbvbMousfEvfntOnEDT(mf);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /*
     * Do ibndlfJbvbMousfEvfnt on EDT
     */
    void ibndlfJbvbMousfEvfntOnEDT(finbl MousfEvfnt mf){
        InvodbtionEvfnt fv = nfw InvodbtionEvfnt(tbrgft, nfw Runnbblf() {
            publid void run() {
                ibndlfJbvbMousfEvfnt(mf);
            }
        });
        postEvfnt(fv);
    }

    /*
     * Fixfd 5010944: List's rows ovfrlbp onf bnotifr
     * Tif bug is duf to indorrfnt dbdiing of tif list itfm sizf
     * So wf siould rfdbldulbtf font mftrids on sftFont
     */
    publid void sftFont(Font f) {
        if (!Objfdts.fqubls(gftFont(), f)) {
            supfr.sftFont(f);
            initFontMftrids();
            lbyout();
            rfpbint();
        }
    }

    /**
     * Somftimfs pbintfr is dbllfd on Toolkit tirfbd, so tif lodk sfqufndf is:
     *     bwtLodk -> Pbintfr -> bwtLodk
     * Somftimfs it is dbllfd on otifr tirfbds:
     *     Pbintfr -> bwtLodk
     * Sindf wf dbn't gubrbntff tif sfqufndf, usf bwtLodk.
     */
    dlbss ListPbintfr {
        VolbtilfImbgf bufffr;
        Color[] dolors;

        privbtf Color gftListForfground() {
            if (fgColorSft) {
                rfturn dolors[FOREGROUND_COLOR];
            }
            flsf {
            rfturn SystfmColor.tfxtTfxt;
            }
        }
        privbtf Color gftListBbdkground() {
            if (bgColorSft) {
                rfturn dolors[BACKGROUND_COLOR];
            }
            flsf {
                rfturn SystfmColor.tfxt;
            }
        }

        privbtf Color gftDisbblfdColor() {
            Color bbdkgroundColor = gftListBbdkground();
            Color forfgroundColor = gftListForfground();
            rfturn (bbdkgroundColor.fqubls(Color.BLACK)) ? forfgroundColor.dbrkfr() : bbdkgroundColor.dbrkfr();
        }

        privbtf boolfbn drfbtfBufffr() {
            VolbtilfImbgf lodblBufffr = null;
            XToolkit.bwtLodk();
            try {
                lodblBufffr = bufffr;
            } finblly {
                XToolkit.bwtUnlodk();
            }

            if (lodblBufffr == null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Crfbting bufffr " + widti + "x" + ifigit);
                }
                // usf GrbpiidsConfig.dCVI() instfbd of Componfnt.dVI(),
                // bfdbusf tif lbttfr mby dbusf b dfbdlodk witi tif trff lodk
                lodblBufffr =
                    grbpiidsConfig.drfbtfCompbtiblfVolbtilfImbgf(widti+1,
                                                                 ifigit+1);
            }
            XToolkit.bwtLodk();
            try {
                if (bufffr == null) {
                    bufffr = lodblBufffr;
                    rfturn truf;
                }
            } finblly {
                XToolkit.bwtUnlodk();
            }
            rfturn fblsf;
        }

        publid void invblidbtf() {
            XToolkit.bwtLodk();
            try {
                if (bufffr != null) {
                    bufffr.flusi();
                }
                bufffr = null;
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }

        privbtf void pbint(Grbpiids listG, int firstItfm, int lbstItfm, int options) {
            pbint(listG, firstItfm, lbstItfm, options, null, null);
        }

        privbtf void pbint(Grbpiids listG, int firstItfm, int lbstItfm, int options,
                           Rfdtbnglf sourdf, Point distbndf) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Rfpbint from " + firstItfm + " to " + lbstItfm + " options " + options);
            }
            if (firstItfm > lbstItfm) {
                int t = lbstItfm;
                lbstItfm = firstItfm;
                firstItfm = t;
            }
            if (firstItfm < 0) {
                firstItfm = 0;
            }
            dolors = gftGUIdolors();
            VolbtilfImbgf lodblBufffr = null;
            do {
                XToolkit.bwtLodk();
                try {
                    if (drfbtfBufffr()) {
                        // First timf drfbtfd bufffr siould bf pbintfd ovfr bt full.
                        options = PAINT_ALL;
                    }
                    lodblBufffr = bufffr;
                } finblly {
                    XToolkit.bwtUnlodk();
                }
                switdi (lodblBufffr.vblidbtf(gftGrbpiidsConfigurbtion())) {
                  dbsf VolbtilfImbgf.IMAGE_INCOMPATIBLE:
                      invblidbtf();
                      options = PAINT_ALL;
                      dontinuf;
                  dbsf VolbtilfImbgf.IMAGE_RESTORED:
                      options = PAINT_ALL;
                }
                Grbpiids g = lodblBufffr.drfbtfGrbpiids();

                // Notf tibt tif ordfr of tif following pbinting opfrbtions
                // siould not bf modififd
                try {
                    g.sftFont(gftFont());

                    // iiding tif fodus rfdtbnglf must bf donf prior to dopying
                    // brfb bnd so tiis is tif first bdtion to bf pfrformfd
                    if ((options & (PAINT_HIDEFOCUS)) != 0) {
                        pbintFodus(g, PAINT_HIDEFOCUS);
                    }
                    /*
                     * Tif siift of tif domponfnt dontfnts oddurs wiilf somfonf
                     * sdrolls tif domponfnt, tif only purposf of tif siift is to
                     * indrfbsf tif pbinting pfrformbndf. Tif siift siould bf donf
                     * prior to pbinting bny brfb (fxdfpt iiding fodus) bnd bdtublly
                     * it siould nfvfr bf donf jointly witi frbsf bbdkground.
                     */
                    if ((options & COPY_AREA) != 0) {
                        g.dopyArfb(sourdf.x, sourdf.y, sourdf.widti, sourdf.ifigit,
                            distbndf.x, distbndf.y);
                    }
                    if ((options & PAINT_BACKGROUND) != 0) {
                        pbintBbdkground(g);
                        // Sindf wf mbdf full frbsf updbtf itfms
                        firstItfm = gftFirstVisiblfItfm();
                        lbstItfm = gftLbstVisiblfItfm();
                    }
                    if ((options & PAINT_ITEMS) != 0) {
                        pbintItfms(g, firstItfm, lbstItfm, options);
                    }
                    if ((options & PAINT_VSCROLL) != 0 && vsbVis) {
                        g.sftClip(gftVSdrollBbrRfd());
                        pbintVfrSdrollbbr(g, truf);
                    }
                    if ((options & PAINT_HSCROLL) != 0 && isbVis) {
                        g.sftClip(gftHSdrollBbrRfd());
                        pbintHorSdrollbbr(g, truf);
                    }
                    if ((options & (PAINT_FOCUS)) != 0) {
                        pbintFodus(g, PAINT_FOCUS);
                    }
                } finblly {
                    g.disposf();
                }
            } wiilf (lodblBufffr.dontfntsLost());
            listG.drbwImbgf(lodblBufffr, 0, 0, null);
        }

        privbtf void pbintBbdkground(Grbpiids g) {
            g.sftColor(SystfmColor.window);
            g.fillRfdt(0, 0, widti, ifigit);
            g.sftColor(gftListBbdkground());
            g.fillRfdt(0, 0, listWidti, listHfigit);
            drbw3DRfdt(g, gftSystfmColors(), 0, 0, listWidti - 1, listHfigit - 1, fblsf);
        }

        privbtf void pbintItfms(Grbpiids g, int firstItfm, int lbstItfm, int options) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Pbinting itfms from " + firstItfm + " to " + lbstItfm + ", fodusfd " + fodusIndfx + ", first " + gftFirstVisiblfItfm() + ", lbst " + gftLbstVisiblfItfm());
            }

            firstItfm = Mbti.mbx(gftFirstVisiblfItfm(), firstItfm);
            if (firstItfm > lbstItfm) {
                int t = lbstItfm;
                lbstItfm = firstItfm;
                firstItfm = t;
            }
            firstItfm = Mbti.mbx(gftFirstVisiblfItfm(), firstItfm);
            lbstItfm = Mbti.min(lbstItfm, itfms.sizf()-1);

            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Adtublly pbinting itfms from " + firstItfm + " to " + lbstItfm +
                          ", itfms in window " + itfmsInWindow());
            }
            for (int i = firstItfm; i <= lbstItfm; i++) {
                pbintItfm(g, i);
            }
        }

        privbtf void pbintItfm(Grbpiids g, int indfx) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("Pbinting itfm " + indfx);
            }
            // 4895367 - only pbint itfms wiidi brf visiblf
            if (!isItfmHiddfn(indfx)) {
                Sibpf dlip = g.gftClip();
                int w = gftItfmWidti();
                int i = gftItfmHfigit();
                int y = gftItfmY(indfx);
                int x = gftItfmX();
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Sftting dlip " + nfw Rfdtbnglf(x, y, w - (SPACE*2), i-(SPACE*2)));
                }
                g.sftClip(x, y, w - (SPACE*2), i-(SPACE*2));

                // Alwbys pbint tif bbdkground so tibt fodus is unpbintfd in
                // multisflfdt modf
                if (isSflfdtfd(indfx)) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        log.finfst("Pbintfd itfm is sflfdtfd");
                    }
                    g.sftColor(gftListForfground());
                } flsf {
                    g.sftColor(gftListBbdkground());
                }
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Filling " + nfw Rfdtbnglf(x, y, w, i));
                }
                g.fillRfdt(x, y, w, i);

                if (indfx <= gftLbstVisiblfItfm() && indfx < itfms.sizf()) {
                    if (!isEnbblfd()){
                        g.sftColor(gftDisbblfdColor());
                    } flsf if (isSflfdtfd(indfx)) {
                        g.sftColor(gftListBbdkground());
                    } flsf {
                        g.sftColor(gftListForfground());
                    }
                    String str = itfms.flfmfntAt(indfx);
                    g.drbwString(str, x - isb.gftVbluf(), y + fontAsdfnt);
                } flsf {
                    // Clfbr tif rfmbining brfb bround tif itfm - fodus brfb bnd tif rfst of bordfr
                    g.sftClip(x, y, listWidti, i);
                    g.sftColor(gftListBbdkground());
                    g.fillRfdt(x, y, listWidti, i);
                }
                g.sftClip(dlip);
            }
        }

        void pbintSdrollBbr(XSdrollbbr sdr, Grbpiids g, int x, int y, int widti, int ifigit, boolfbn pbintAll) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("Pbinting sdrollbbr " + sdr + " widti " +
                widti + " ifigit " + ifigit + ", pbintAll " + pbintAll);
            }
            g.trbnslbtf(x, y);
            sdr.pbint(g, gftSystfmColors(), pbintAll);
            g.trbnslbtf(-x, -y);
        }

        /**
         * Pbint tif iorizontbl sdrollbbr to tif sdrffn
         *
         * @pbrbm g tif grbpiids dontfxt to drbw into
         * @pbrbm dolors tif dolors usfd to drbw tif sdrollbbr
         * @pbrbm pbintAll pbint tif wiolf sdrollbbr if truf, just tif tiumb if fblsf
         */
        void pbintHorSdrollbbr(Grbpiids g, boolfbn pbintAll) {
            int w = gftListWidti();
            pbintSdrollBbr(isb, g, 0, ifigit - (SCROLLBAR_WIDTH), w, SCROLLBAR_WIDTH, pbintAll);
        }

        /**
         * Pbint tif vfrtidbl sdrollbbr to tif sdrffn
         *
         * @pbrbm g tif grbpiids dontfxt to drbw into
         * @pbrbm dolors tif dolors usfd to drbw tif sdrollbbr
         * @pbrbm pbintAll pbint tif wiolf sdrollbbr if truf, just tif tiumb if fblsf
         */
        void pbintVfrSdrollbbr(Grbpiids g, boolfbn pbintAll) {
            int i = ifigit - (isbVis ? (SCROLLBAR_AREA-2) : 0);
            pbintSdrollBbr(vsb, g, widti - SCROLLBAR_WIDTH, 0, SCROLLBAR_WIDTH - 2, i, pbintAll);
        }


        privbtf Rfdtbnglf prfvFodusRfdt;
        privbtf void pbintFodus(Grbpiids g, int options) {
            boolfbn pbintFodus = (options & PAINT_FOCUS) != 0;
            if (pbintFodus && !ibsFodus()) {
                pbintFodus = fblsf;
            }
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("Pbinting fodus, fodus indfx " + gftFodusIndfx() + ", fodus is " +
                         (isItfmHiddfn(gftFodusIndfx())?("invisiblf"):("visiblf")) + ", pbint fodus is " + pbintFodus);
            }
            Sibpf dlip = g.gftClip();
            g.sftClip(0, 0, listWidti, listHfigit);
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("Sftting fodus dlip " + nfw Rfdtbnglf(0, 0, listWidti, listHfigit));
            }
            Rfdtbnglf rfdt = gftFodusRfdt();
            if (prfvFodusRfdt != null) {
                // Erbsf fodus rfdt
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Erbsing prfvious fodus rfdt " + prfvFodusRfdt);
                }
                g.sftColor(gftListBbdkground());
                g.drbwRfdt(prfvFodusRfdt.x, prfvFodusRfdt.y, prfvFodusRfdt.widti, prfvFodusRfdt.ifigit);
                prfvFodusRfdt = null;
            }
            if (pbintFodus) {
                // Pbint nfw
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Pbinting fodus rfdt " + rfdt);
                }
                g.sftColor(gftListForfground());  // Fodus dolor is blwbys blbdk on Linux
                g.drbwRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
                prfvFodusRfdt = rfdt;
            }
            g.sftClip(dlip);
        }
    }
}
