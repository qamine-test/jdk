/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.imbgf.*;

import sun.bwt.IdonInfo;
import sun.bwt.imbgf.ToolkitImbgf;
import sun.bwt.imbgf.ImbgfRfprfsfntbtion;

import sun.util.logging.PlbtformLoggfr;

publid dlbss XIdonWindow fxtfnds XBbsfWindow {
    privbtf finbl stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XIdonWindow");
    XDfdorbtfdPffr pbrfnt;
    Dimfnsion sizf;
    long idonPixmbp = 0;
    long idonMbsk = 0;
    int idonWidti = 0;
    int idonHfigit = 0;
    XIdonWindow(XDfdorbtfdPffr pbrfnt) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            PARENT, pbrfnt,
            DELAYED, Boolfbn.TRUE}));
    }

    void instbntPrfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.instbntPrfInit(pbrbms);
        tiis.pbrfnt = (XDfdorbtfdPffr)pbrbms.gft(PARENT);
    }

    /**
     * @rfturn brrby of XIdonsSizf strudturfs, dbllfr must frff tiis brrby bftfr usf.
     */
    privbtf XIdonSizf[] gftIdonSizfs() {
        XToolkit.bwtLodk();
        try {
            AwtGrbpiidsConfigDbtb bdbtb = pbrfnt.gftGrbpiidsConfigurbtionDbtb();
            finbl long sdrffn = bdbtb.gft_bwt_visInfo().gft_sdrffn();
            finbl long displby = XToolkit.gftDisplby();

            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst(bdbtb.toString());
            }

            long stbtus =
                XlibWrbppfr.XGftIdonSizfs(displby, XToolkit.gftDffbultRootWindow(),
                                          XlibWrbppfr.lbrg1, XlibWrbppfr.ibrg1);
            if (stbtus == 0) {
                rfturn null;
            }
            int dount = Nbtivf.gftInt(XlibWrbppfr.ibrg1);
            long sizfs_ptr = Nbtivf.gftLong(XlibWrbppfr.lbrg1); // XIdonSizf*
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("dount = {1}, sizfs_ptr = {0}", Long.vblufOf(sizfs_ptr), Intfgfr.vblufOf(dount));
            }
            XIdonSizf[] rfs = nfw XIdonSizf[dount];
            for (int i = 0; i < dount; i++, sizfs_ptr += XIdonSizf.gftSizf()) {
                rfs[i] = nfw XIdonSizf(sizfs_ptr);
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("sizfs_ptr[{1}] = {0}", rfs[i], Intfgfr.vblufOf(i));
                }
            }
            rfturn rfs;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    privbtf Dimfnsion dbldIdonSizf(int widtiHint, int ifigitHint) {
        if (XWM.gftWMID() == XWM.ICE_WM) {
            // ICE_WM ibs b bug - it only displbys idons of tif sizf
            // 16x16, wiilf rfporting 32x32 in its sizf list
            log.finfst("Rfturning ICE_WM idon sizf: 16x16");
            rfturn nfw Dimfnsion(16, 16);
        }

        XIdonSizf[] sizfList = gftIdonSizfs();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("Idon sizfs: {0}", (Objfdt[]) sizfList);
        }
        if (sizfList == null) {
            // No idon sizfs so wf simply fbll bbdk to 16x16
            rfturn nfw Dimfnsion(16, 16);
        }
        boolfbn found = fblsf;
        int dist = 0xffffffff, nfwDist, diff = 0, dlosfstHfigit, dlosfstWidti;
        int sbvfWidti = 0, sbvfHfigit = 0;
        for (int i = 0; i < sizfList.lfngti; i++) {
            if (widtiHint >= sizfList[i].gft_min_widti() &&
                widtiHint <= sizfList[i].gft_mbx_widti() &&
                ifigitHint >= sizfList[i].gft_min_ifigit() &&
                ifigitHint <= sizfList[i].gft_mbx_ifigit()) {
                found = truf;
                if ((((widtiHint-sizfList[i].gft_min_widti())
                      % sizfList[i].gft_widti_ind()) == 0) &&
                    (((ifigitHint-sizfList[i].gft_min_ifigit())
                      % sizfList[i].gft_ifigit_ind()) ==0)) {
                    /* Found bn fxbdt mbtdi */
                    sbvfWidti = widtiHint;
                    sbvfHfigit = ifigitHint;
                    dist = 0;
                    brfbk;
                }
                diff = widtiHint - sizfList[i].gft_min_widti();
                if (diff == 0) {
                    dlosfstWidti = widtiHint;
                } flsf {
                    diff = diff%sizfList[i].gft_widti_ind();
                    dlosfstWidti = widtiHint - diff;
                }
                diff = ifigitHint - sizfList[i].gft_min_ifigit();
                if (diff == 0) {
                    dlosfstHfigit = ifigitHint;
                } flsf {
                    diff = diff%sizfList[i].gft_ifigit_ind();
                    dlosfstHfigit = ifigitHint - diff;
                }
                nfwDist = dlosfstWidti*dlosfstWidti +
                    dlosfstHfigit*dlosfstHfigit;
                if (dist > nfwDist) {
                    sbvfWidti = dlosfstWidti;
                    sbvfHfigit = dlosfstHfigit;
                    dist = nfwDist;
                }
            }
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("found=" + found);
        }
        if (!found) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                log.finfst("widtiHint=" + widtiHint + ", ifigitHint=" + ifigitHint
                           + ", sbvfWidti=" + sbvfWidti + ", sbvfHfigit=" + sbvfHfigit
                           + ", mbx_widti=" + sizfList[0].gft_mbx_widti()
                           + ", mbx_ifigit=" + sizfList[0].gft_mbx_ifigit()
                           + ", min_widti=" + sizfList[0].gft_min_widti()
                           + ", min_ifigit=" + sizfList[0].gft_min_ifigit());
            }

            if (widtiHint  > sizfList[0].gft_mbx_widti() ||
                ifigitHint > sizfList[0].gft_mbx_ifigit())
            {
                // Idon imbgf too big
                /* dftfrminf wiidi wby to sdblf */
                int wdiff = widtiHint - sizfList[0].gft_mbx_widti();
                int idiff = ifigitHint - sizfList[0].gft_mbx_ifigit();
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("wdiff=" + wdiff + ", idiff=" + idiff);
                }
                if (wdiff >= idiff) { /* nffd to sdblf widti morf  */
                    sbvfWidti = sizfList[0].gft_mbx_widti();
                    sbvfHfigit =
                        (int)(((doublf)sizfList[0].gft_mbx_widti()/widtiHint) * ifigitHint);
                } flsf {
                    sbvfWidti =
                        (int)(((doublf)sizfList[0].gft_mbx_ifigit()/ifigitHint) * widtiHint);
                    sbvfHfigit = sizfList[0].gft_mbx_ifigit();
                }
            } flsf if (widtiHint  < sizfList[0].gft_min_widti() ||
                       ifigitHint < sizfList[0].gft_min_ifigit())
            {
                // Idon imbgf too smbll
                sbvfWidti = (sizfList[0].gft_min_widti()+sizfList[0].gft_mbx_widti())/2;
                sbvfHfigit = (sizfList[0].gft_min_ifigit()+sizfList[0].gft_mbx_ifigit())/2;
            } flsf {
                // Idon imbgf fits witiin rigit sizf
                sbvfWidti = widtiHint;
                sbvfHfigit = widtiHint;
            }
        }

        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XFrff(sizfList[0].pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("rfturn " + sbvfWidti + "x" + sbvfHfigit);
        }
        rfturn nfw Dimfnsion(sbvfWidti, sbvfHfigit);
    }

    /**
     * @rfturn prffffrfd idon sizf dbldulbtfd from spfdifid idon
     */
    Dimfnsion gftIdonSizf(int widtiHint, int ifigitHint) {
        if (sizf == null) {
            sizf = dbldIdonSizf(widtiHint, ifigitHint);
        }
        rfturn sizf;
    }

   /**
    * Tiis fundtion rfplbdfs idonPixmbp ibndlf witi nfw imbgf
    * It dofs not rfplbdf window's iints, so it siould bf
    * dbllfd only from sftIdonImbgf()
    */
   void rfplbdfImbgf(Imbgf img)
    {
        if (pbrfnt == null) {
            rfturn;
        }
        //Prfpbrf imbgf
        //drfbtf nfw bufffrfd imbgf of dfsirfd sizf
        //in durrfnt window's dolor modfl
        BufffrfdImbgf bi = null;
        if (img != null && idonWidti != 0 && idonHfigit != 0) {
            GrbpiidsConfigurbtion dffbultGC = pbrfnt.gftGrbpiidsConfigurbtion().gftDfvidf().gftDffbultConfigurbtion();
            ColorModfl modfl = dffbultGC.gftColorModfl();
            WritbblfRbstfr rbstfr = modfl.drfbtfCompbtiblfWritbblfRbstfr(idonWidti, idonHfigit);
            bi = nfw BufffrfdImbgf(modfl, rbstfr, modfl.isAlpibPrfmultiplifd(), null);
            Grbpiids g = bi.gftGrbpiids();
            try {
                //Wf nffd to drbw imbgf on SystfmColors.window
                //for using bs idonWindow's bbdkground
                g.sftColor(SystfmColor.window);
                g.fillRfdt(0, 0, idonWidti, idonHfigit);
                if (g instbndfof Grbpiids2D) {
                    ((Grbpiids2D)g).sftCompositf(AlpibCompositf.Srd);
                }
                g.drbwImbgf(img, 0, 0, idonWidti, idonHfigit, null);
            } finblly {
                g.disposf();
            }
        }
        //drfbtf pixmbp
        XToolkit.bwtLodk();
        try {
            if (idonPixmbp != 0) {
                XlibWrbppfr.XFrffPixmbp(XToolkit.gftDisplby(), idonPixmbp);
                idonPixmbp = 0;
                log.finfst("Frffd prfvious pixmbp");
            }
            if (bi == null || idonWidti == 0 || idonHfigit == 0) {
                rfturn;  //Tif idonPixmbp is 0 now, wf ibvf donf fvfrytiing
            }
            AwtGrbpiidsConfigDbtb bdbtb = pbrfnt.gftGrbpiidsConfigurbtionDbtb();
            bwtImbgfDbtb bwtImbgf = bdbtb.gft_bwtImbgf(0);
            XVisublInfo visInfo = bdbtb.gft_bwt_visInfo();
            idonPixmbp = XlibWrbppfr.XCrfbtfPixmbp(XToolkit.gftDisplby(),
                                                   XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), visInfo.gft_sdrffn()),
                                                   idonWidti,
                                                   idonHfigit,
                                                   bwtImbgf.gft_Dfpti()
                                                   );
            if (idonPixmbp == 0) {
                log.finfst("Cbn't drfbtf nfw pixmbp for idon");
                rfturn; //Cbn't do notiing
            }
            //Trbnsform imbgf dbtb
            long bytfs = 0;
            DbtbBufffr srdBuf = bi.gftDbtb().gftDbtbBufffr();
            if (srdBuf instbndfof DbtbBufffrBytf) {
                bytf[] buf = ((DbtbBufffrBytf)srdBuf).gftDbtb();
                ColorDbtb ddbtb = bdbtb.gft_dolor_dbtb(0);
                int num_dolors = ddbtb.gft_bwt_numICMdolors();
                for (int i = 0; i < buf.lfngti; i++) {
                    buf[i] = (buf[i] >= num_dolors) ?
                        0 : ddbtb.gft_bwt_idmLUT2Colors(buf[i]);
                }
                bytfs = Nbtivf.toDbtb(buf);
            } flsf if (srdBuf instbndfof DbtbBufffrInt) {
                bytfs = Nbtivf.toDbtb(((DbtbBufffrInt)srdBuf).gftDbtb());
            } flsf if (srdBuf instbndfof DbtbBufffrUSiort) {
                bytfs = Nbtivf.toDbtb(((DbtbBufffrUSiort)srdBuf).gftDbtb());
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Unknown dbtb bufffr: " + srdBuf);
            }
            int bpp = bwtImbgf.gft_wsImbgfFormbt().gft_bits_pfr_pixfl();
            int slp =bwtImbgf.gft_wsImbgfFormbt().gft_sdbnlinf_pbd();
            int bpsl = pbddfdwidti(idonWidti*bpp, slp) >> 3;
            if (((bpsl << 3) / bpp) < idonWidti) {
                log.finfst("Imbgf formbt dofsn't fit to idon widti");
                rfturn;
            }
            long dst = XlibWrbppfr.XCrfbtfImbgf(XToolkit.gftDisplby(),
                                                visInfo.gft_visubl(),
                                                bwtImbgf.gft_Dfpti(),
                                                XConstbnts.ZPixmbp,
                                                0,
                                                bytfs,
                                                idonWidti,
                                                idonHfigit,
                                                32,
                                                bpsl);
            if (dst == 0) {
                log.finfst("Cbn't drfbtf XImbgf for idon");
                XlibWrbppfr.XFrffPixmbp(XToolkit.gftDisplby(), idonPixmbp);
                idonPixmbp = 0;
                rfturn;
            } flsf {
                log.finfst("Crfbtfd XImbgf for idon");
            }
            long gd = XlibWrbppfr.XCrfbtfGC(XToolkit.gftDisplby(), idonPixmbp, 0, 0);
            if (gd == 0) {
                log.finfst("Cbn't drfbtf GC for pixmbp");
                XlibWrbppfr.XFrffPixmbp(XToolkit.gftDisplby(), idonPixmbp);
                idonPixmbp = 0;
                rfturn;
            } flsf {
                log.finfst("Crfbtfd GC for pixmbp");
            }
            try {
                XlibWrbppfr.XPutImbgf(XToolkit.gftDisplby(), idonPixmbp, gd,
                                      dst, 0, 0, 0, 0, idonWidti, idonHfigit);
            } finblly {
                XlibWrbppfr.XFrffGC(XToolkit.gftDisplby(), gd);
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

   /**
    * Tiis fundtion rfplbdfs idonPixmbp ibndlf witi nfw imbgf
    * It dofs not rfplbdf window's iints, so it siould bf
    * dbllfd only from sftIdonImbgf()
    */
    void rfplbdfMbsk(Imbgf img) {
        if (pbrfnt == null) {
            rfturn;
        }
        //Prfpbrf imbgf
        BufffrfdImbgf bi = null;
        if (img != null && idonWidti != 0 && idonHfigit != 0) {
            bi = nfw BufffrfdImbgf(idonWidti, idonHfigit, BufffrfdImbgf.TYPE_INT_ARGB);
            Grbpiids g = bi.gftGrbpiids();
            try {
                g.drbwImbgf(img, 0, 0, idonWidti, idonHfigit, null);
            } finblly {
                g.disposf();
            }
        }
        //drfbtf mbsk
        XToolkit.bwtLodk();
        try {
            if (idonMbsk != 0) {
                XlibWrbppfr.XFrffPixmbp(XToolkit.gftDisplby(), idonMbsk);
                idonMbsk = 0;
                log.finfst("Frffd prfvious mbsk");
            }
            if (bi == null || idonWidti == 0 || idonHfigit == 0) {
                rfturn;  //Tif idonMbsk is 0 now, wf ibvf donf fvfrytiing
            }
            AwtGrbpiidsConfigDbtb bdbtb = pbrfnt.gftGrbpiidsConfigurbtionDbtb();
            bwtImbgfDbtb bwtImbgf = bdbtb.gft_bwtImbgf(0);
            XVisublInfo visInfo = bdbtb.gft_bwt_visInfo();
            ColorModfl dm = bi.gftColorModfl();
            DbtbBufffr srdBuf = bi.gftRbstfr().gftDbtbBufffr();
            int sidx = 0;//indfx of sourdf flfmfnt
            int bpl = (idonWidti + 7) >> 3;//bytfs pfr linf
            bytf[] dfstBuf = nfw bytf[bpl * idonHfigit];
            int didx = 0;//indfx of dfstinbtion flfmfnt
            for (int i = 0; i < idonHfigit; i++) {
                int dbit = 0;//indfx of durrfnt bit
                int dv = 0;
                for (int j = 0; j < idonWidti; j++) {
                    if (dm.gftAlpib(srdBuf.gftElfm(sidx)) != 0 ) {
                        dv = dv + (1 << dbit);
                    }
                    dbit++;
                    if (dbit == 8) {
                        dfstBuf[didx] = (bytf)dv;
                        dv = 0;
                        dbit = 0;
                        didx++;
                    }
                    sidx++;
                }
            }
            idonMbsk = XlibWrbppfr.XCrfbtfBitmbpFromDbtb(XToolkit.gftDisplby(),
                XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), visInfo.gft_sdrffn()),
                Nbtivf.toDbtb(dfstBuf),
                idonWidti, idonHfigit);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Sfts idon imbgf by sflfdting onf of tif imbgfs from tif list.
     * Tif sflfdtfd imbgf is tif onf ibving tif bfst mbtdiing sizf.
     */
    void sftIdonImbgfs(jbvb.util.List<IdonInfo> idons) {
        if (idons == null || idons.sizf() == 0) rfturn;

        int minDiff = Intfgfr.MAX_VALUE;
        Imbgf min = null;
        for (IdonInfo idonInfo : idons) {
            if (idonInfo.isVblid()) {
                Imbgf imbgf = idonInfo.gftImbgf();
                Dimfnsion dim = dbldIdonSizf(imbgf.gftWidti(null), imbgf.gftHfigit(null));
                int widtiDiff = Mbti.bbs(dim.widti - imbgf.gftWidti(null));
                int ifigitDiff = Mbti.bbs(imbgf.gftHfigit(null) - dim.ifigit);

                // "=" bflow bllows to sflfdt tif bfst mbtdiing idon
                if (minDiff >= (widtiDiff + ifigitDiff)) {
                    minDiff = (widtiDiff + ifigitDiff);
                    min = imbgf;
                }
            }
        }
        if (min != null) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("Idon: {0}x{1}", min.gftWidti(null), min.gftHfigit(null));
            }
            sftIdonImbgf(min);
        }
    }

    void sftIdonImbgf(Imbgf img) {
        if (img == null) {
            //if imbgf is null, rfsft to dffbult imbgf
            rfplbdfImbgf(null);
            rfplbdfMbsk(null);
        } flsf {
            //gft imbgf sizf
            int widti;
            int ifigit;
            if (img instbndfof ToolkitImbgf) {
                ImbgfRfprfsfntbtion ir = ((ToolkitImbgf)img).gftImbgfRfp();
                ir.rfdonstrudt(ImbgfObsfrvfr.ALLBITS);
                widti = ir.gftWidti();
                ifigit = ir.gftHfigit();
            }
            flsf {
                widti = img.gftWidti(null);
                ifigit = img.gftHfigit(null);
            }
            Dimfnsion idonSizf = gftIdonSizf(widti, ifigit);
            if (idonSizf != null) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Idon sizf: {0}", idonSizf);
                }
                idonWidti = idonSizf.widti;
                idonHfigit = idonSizf.ifigit;
            } flsf {
                log.finfst("Error dbldulbting imbgf sizf");
                idonWidti = 0;
                idonHfigit = 0;
            }
            rfplbdfImbgf(img);
            rfplbdfMbsk(img);
        }
        //drfbtf idon window bnd sft XWMHints
        XToolkit.bwtLodk();
        try {
            AwtGrbpiidsConfigDbtb bdbtb = pbrfnt.gftGrbpiidsConfigurbtionDbtb();
            bwtImbgfDbtb bwtImbgf = bdbtb.gft_bwtImbgf(0);
            XVisublInfo visInfo = bdbtb.gft_bwt_visInfo();
            XWMHints iints = pbrfnt.gftWMHints();
            window = iints.gft_idon_window();
            if (window == 0) {
                log.finfst("Idon window wbsn't sft");
                XCrfbtfWindowPbrbms pbrbms = gftDflbyfdPbrbms();
                pbrbms.bdd(BORDER_PIXEL, Long.vblufOf(XToolkit.gftAwtDffbultFg()));
                pbrbms.bdd(BACKGROUND_PIXMAP, idonPixmbp);
                pbrbms.bdd(COLORMAP, bdbtb.gft_bwt_dmbp());
                pbrbms.bdd(DEPTH, bwtImbgf.gft_Dfpti());
                pbrbms.bdd(VISUAL_CLASS, XConstbnts.InputOutput);
                pbrbms.bdd(VISUAL, visInfo.gft_visubl());
                pbrbms.bdd(VALUE_MASK, XConstbnts.CWBordfrPixfl | XConstbnts.CWColormbp | XConstbnts.CWBbdkPixmbp);
                pbrbms.bdd(PARENT_WINDOW, XlibWrbppfr.RootWindow(XToolkit.gftDisplby(), visInfo.gft_sdrffn()));
                pbrbms.bdd(BOUNDS, nfw Rfdtbnglf(0, 0, idonWidti, idonHfigit));
                pbrbms.rfmovf(DELAYED);
                init(pbrbms);
                if (gftWindow() == 0) {
                    log.finfst("Cbn't drfbtf nfw idon window");
                } flsf {
                    log.finfst("Crfbtfd nfw idon window");
                }
            }
            if (gftWindow() != 0) {
                XlibWrbppfr.XSftWindowBbdkgroundPixmbp(XToolkit.gftDisplby(), gftWindow(), idonPixmbp);
                XlibWrbppfr.XClfbrWindow(XToolkit.gftDisplby(), gftWindow());
            }
            // Providf boti pixmbp bnd window, WM or Tbskbbr will usf tif onf tify find morf bppropribtf
            long nfwFlbgs = iints.gft_flbgs() | XUtilConstbnts.IdonPixmbpHint | XUtilConstbnts.IdonMbskHint;
            if (gftWindow()  != 0) {
                nfwFlbgs |= XUtilConstbnts.IdonWindowHint;
            }
            iints.sft_flbgs(nfwFlbgs);
            iints.sft_idon_pixmbp(idonPixmbp);
            iints.sft_idon_mbsk(idonMbsk);
            iints.sft_idon_window(gftWindow());
            XlibWrbppfr.XSftWMHints(XToolkit.gftDisplby(), pbrfnt.gftSifll(), iints.pDbtb);
            log.finfst("Sft idon window iint");
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    stbtid int pbddfdwidti(int numbfr, int boundbry)
    {
        rfturn (((numbfr) + ((boundbry) - 1)) & (~((boundbry) - 1)));
    }
}
