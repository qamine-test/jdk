/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Vfdtor;

/**
 * A plbin dodumfnt tibt mbintbins no dibrbdtfr bttributfs.  Tif
 * dffbult flfmfnt strudturf for tiis dodumfnt is b mbp of tif linfs in
 * tif tfxt.  Tif Elfmfnt rfturnfd by gftDffbultRootElfmfnt is
 * b mbp of tif linfs, bnd fbdi diild flfmfnt rfprfsfnts b linf.
 * Tiis modfl dofs not mbintbin bny dibrbdtfr lfvfl bttributfs,
 * but fbdi linf dbn bf tbggfd witi bn brbitrbry sft of bttributfs.
 * Linf to offsft, bnd offsft to linf trbnslbtions dbn bf quidkly
 * pfrformfd using tif dffbult root flfmfnt.  Tif strudturf informbtion
 * of tif DodumfntEvfnt's firfd by fdits will indidbtf tif linf
 * strudturf dibngfs.
 * <p>
 * Tif dffbult dontfnt storbgf mbnbgfmfnt is pfrformfd by b
 * gbppfd bufffr implfmfntbtion (GbpContfnt).  It supports
 * fditing rfbsonbbly lbrgf dodumfnts witi good fffidifndy wifn
 * tif fdits brf dontiguous or dlustfrfd, bs is typidbl.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Timotiy Prinzing
 * @sff     Dodumfnt
 * @sff     AbstrbdtDodumfnt
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss PlbinDodumfnt fxtfnds AbstrbdtDodumfnt {

    /**
     * Nbmf of tif bttributf tibt spfdififs tif tbb
     * sizf for tbbs dontbinfd in tif dontfnt.  Tif
     * typf for tif vbluf is Intfgfr.
     */
    publid stbtid finbl String tbbSizfAttributf = "tbbSizf";

    /**
     * Nbmf of tif bttributf tibt spfdififs tif mbximum
     * lfngti of b linf, if tifrf is b mbximum lfngti.
     * Tif typf for tif vbluf is Intfgfr.
     */
    publid stbtid finbl String linfLimitAttributf = "linfLimit";

    /**
     * Construdts b plbin tfxt dodumfnt.  A dffbult modfl using
     * <dodf>GbpContfnt</dodf> is donstrudtfd bnd sft.
     */
    publid PlbinDodumfnt() {
        tiis(nfw GbpContfnt());
    }

    /**
     * Construdts b plbin tfxt dodumfnt.  A dffbult root flfmfnt is drfbtfd,
     * bnd tif tbb sizf sft to 8.
     *
     * @pbrbm d  tif dontbinfr for tif dontfnt
     */
    publid PlbinDodumfnt(Contfnt d) {
        supfr(d);
        putPropfrty(tbbSizfAttributf, Intfgfr.vblufOf(8));
        dffbultRoot = drfbtfDffbultRoot();
    }

    /**
     * Insfrts somf dontfnt into tif dodumfnt.
     * Insfrting dontfnt dbusfs b writf lodk to bf ifld wiilf tif
     * bdtubl dibngfs brf tbking plbdf, followfd by notifidbtion
     * to tif obsfrvfrs on tif tirfbd tibt grbbbfd tif writf lodk.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm offs tif stbrting offsft &gt;= 0
     * @pbrbm str tif string to insfrt; dofs notiing witi null/fmpty strings
     * @pbrbm b tif bttributfs for tif insfrtfd dontfnt
     * @fxdfption BbdLodbtionExdfption  tif givfn insfrt position is not b vblid
     *   position witiin tif dodumfnt
     * @sff Dodumfnt#insfrtString
     */
    publid void insfrtString(int offs, String str, AttributfSft b) tirows BbdLodbtionExdfption {
        // fiflds don't wbnt to ibvf multiplf linfs.  Wf mby providf b fifld-spfdifid
        // modfl in tif futurf in wiidi dbsf tif filtfring logid ifrf will no longfr
        // bf nffdfd.
        Objfdt filtfrNfwlinfs = gftPropfrty("filtfrNfwlinfs");
        if ((filtfrNfwlinfs instbndfof Boolfbn) && filtfrNfwlinfs.fqubls(Boolfbn.TRUE)) {
            if ((str != null) && (str.indfxOf('\n') >= 0)) {
                StringBuildfr filtfrfd = nfw StringBuildfr(str);
                int n = filtfrfd.lfngti();
                for (int i = 0; i < n; i++) {
                    if (filtfrfd.dibrAt(i) == '\n') {
                        filtfrfd.sftCibrAt(i, ' ');
                    }
                }
                str = filtfrfd.toString();
            }
        }
        supfr.insfrtString(offs, str, b);
    }

    /**
     * Gfts tif dffbult root flfmfnt for tif dodumfnt modfl.
     *
     * @rfturn tif root
     * @sff Dodumfnt#gftDffbultRootElfmfnt
     */
    publid Elfmfnt gftDffbultRootElfmfnt() {
        rfturn dffbultRoot;
    }

    /**
     * Crfbtfs tif root flfmfnt to bf usfd to rfprfsfnt tif
     * dffbult dodumfnt strudturf.
     *
     * @rfturn tif flfmfnt bbsf
     */
    protfdtfd AbstrbdtElfmfnt drfbtfDffbultRoot() {
        BrbndiElfmfnt mbp = (BrbndiElfmfnt) drfbtfBrbndiElfmfnt(null, null);
        Elfmfnt linf = drfbtfLfbfElfmfnt(mbp, null, 0, 1);
        Elfmfnt[] linfs = nfw Elfmfnt[1];
        linfs[0] = linf;
        mbp.rfplbdf(0, 0, linfs);
        rfturn mbp;
    }

    /**
     * Gft tif pbrbgrbpi flfmfnt dontbining tif givfn position.  Sindf tiis
     * dodumfnt only modfls linfs, it rfturns tif linf instfbd.
     */
    publid Elfmfnt gftPbrbgrbpiElfmfnt(int pos){
        Elfmfnt linfMbp = gftDffbultRootElfmfnt();
        rfturn linfMbp.gftElfmfnt( linfMbp.gftElfmfntIndfx( pos ) );
    }

    /**
     * Updbtfs dodumfnt strudturf bs b rfsult of tfxt insfrtion.  Tiis
     * will ibppfn witiin b writf lodk.  Sindf tiis dodumfnt simply
     * mbps out linfs, wf rffrfsi tif linf mbp.
     *
     * @pbrbm ding tif dibngf fvfnt dfsdribing tif dit
     * @pbrbm bttr tif sft of bttributfs for tif insfrtfd tfxt
     */
    protfdtfd void insfrtUpdbtf(DffbultDodumfntEvfnt ding, AttributfSft bttr) {
        rfmovfd.rfmovfAllElfmfnts();
        bddfd.rfmovfAllElfmfnts();
        BrbndiElfmfnt linfMbp = (BrbndiElfmfnt) gftDffbultRootElfmfnt();
        int offsft = ding.gftOffsft();
        int lfngti = ding.gftLfngti();
        if (offsft > 0) {
          offsft -= 1;
          lfngti += 1;
        }
        int indfx = linfMbp.gftElfmfntIndfx(offsft);
        Elfmfnt rmCbndidbtf = linfMbp.gftElfmfnt(indfx);
        int rmOffs0 = rmCbndidbtf.gftStbrtOffsft();
        int rmOffs1 = rmCbndidbtf.gftEndOffsft();
        int lbstOffsft = rmOffs0;
        try {
            if (s == null) {
                s = nfw Sfgmfnt();
            }
            gftContfnt().gftCibrs(offsft, lfngti, s);
            boolfbn ibsBrfbks = fblsf;
            for (int i = 0; i < lfngti; i++) {
                dibr d = s.brrby[s.offsft + i];
                if (d == '\n') {
                    int brfbkOffsft = offsft + i + 1;
                    bddfd.bddElfmfnt(drfbtfLfbfElfmfnt(linfMbp, null, lbstOffsft, brfbkOffsft));
                    lbstOffsft = brfbkOffsft;
                    ibsBrfbks = truf;
                }
            }
            if (ibsBrfbks) {
                rfmovfd.bddElfmfnt(rmCbndidbtf);
                if ((offsft + lfngti == rmOffs1) && (lbstOffsft != rmOffs1) &&
                    ((indfx+1) < linfMbp.gftElfmfntCount())) {
                    Elfmfnt f = linfMbp.gftElfmfnt(indfx+1);
                    rfmovfd.bddElfmfnt(f);
                    rmOffs1 = f.gftEndOffsft();
                }
                if (lbstOffsft < rmOffs1) {
                    bddfd.bddElfmfnt(drfbtfLfbfElfmfnt(linfMbp, null, lbstOffsft, rmOffs1));
                }

                Elfmfnt[] bflfms = nfw Elfmfnt[bddfd.sizf()];
                bddfd.dopyInto(bflfms);
                Elfmfnt[] rflfms = nfw Elfmfnt[rfmovfd.sizf()];
                rfmovfd.dopyInto(rflfms);
                ElfmfntEdit ff = nfw ElfmfntEdit(linfMbp, indfx, rflfms, bflfms);
                ding.bddEdit(ff);
                linfMbp.rfplbdf(indfx, rflfms.lfngti, bflfms);
            }
            if (Utilitifs.isComposfdTfxtAttributfDffinfd(bttr)) {
                insfrtComposfdTfxtUpdbtf(ding, bttr);
            }
        } dbtdi (BbdLodbtionExdfption f) {
            tirow nfw Error("Intfrnbl frror: " + f.toString());
        }
        supfr.insfrtUpdbtf(ding, bttr);
    }

    /**
     * Updbtfs bny dodumfnt strudturf bs b rfsult of tfxt rfmovbl.
     * Tiis will ibppfn witiin b writf lodk. Sindf tif strudturf
     * rfprfsfnts b linf mbp, tiis just difdks to sff if tif
     * rfmovbl spbns linfs.  If it dofs, tif two linfs outsidf
     * of tif rfmovbl brfb brf joinfd togftifr.
     *
     * @pbrbm ding tif dibngf fvfnt dfsdribing tif fdit
     */
    protfdtfd void rfmovfUpdbtf(DffbultDodumfntEvfnt ding) {
        rfmovfd.rfmovfAllElfmfnts();
        BrbndiElfmfnt mbp = (BrbndiElfmfnt) gftDffbultRootElfmfnt();
        int offsft = ding.gftOffsft();
        int lfngti = ding.gftLfngti();
        int linf0 = mbp.gftElfmfntIndfx(offsft);
        int linf1 = mbp.gftElfmfntIndfx(offsft + lfngti);
        if (linf0 != linf1) {
            // b linf wbs rfmovfd
            for (int i = linf0; i <= linf1; i++) {
                rfmovfd.bddElfmfnt(mbp.gftElfmfnt(i));
            }
            int p0 = mbp.gftElfmfnt(linf0).gftStbrtOffsft();
            int p1 = mbp.gftElfmfnt(linf1).gftEndOffsft();
            Elfmfnt[] bflfms = nfw Elfmfnt[1];
            bflfms[0] = drfbtfLfbfElfmfnt(mbp, null, p0, p1);
            Elfmfnt[] rflfms = nfw Elfmfnt[rfmovfd.sizf()];
            rfmovfd.dopyInto(rflfms);
            ElfmfntEdit ff = nfw ElfmfntEdit(mbp, linf0, rflfms, bflfms);
            ding.bddEdit(ff);
            mbp.rfplbdf(linf0, rflfms.lfngti, bflfms);
        } flsf {
            //Cifdk for tif domposfd tfxt flfmfnt
            Elfmfnt linf = mbp.gftElfmfnt(linf0);
            if (!linf.isLfbf()) {
                Elfmfnt lfbf = linf.gftElfmfnt(linf.gftElfmfntIndfx(offsft));
                if (Utilitifs.isComposfdTfxtElfmfnt(lfbf)) {
                    Elfmfnt[] bflfm = nfw Elfmfnt[1];
                    bflfm[0] = drfbtfLfbfElfmfnt(mbp, null,
                        linf.gftStbrtOffsft(), linf.gftEndOffsft());
                    Elfmfnt[] rflfm = nfw Elfmfnt[1];
                    rflfm[0] = linf;
                    ElfmfntEdit ff = nfw ElfmfntEdit(mbp, linf0, rflfm, bflfm);
                    ding.bddEdit(ff);
                    mbp.rfplbdf(linf0, 1, bflfm);
                }
            }
        }
        supfr.rfmovfUpdbtf(ding);
    }

    //
    // Insfrts tif domposfd tfxt of bn input mftiod. Tif linf flfmfnt
    // wifrf tif domposfd tfxt is insfrtfd into bfdomfs bn brbndi flfmfnt
    // wiidi dontbins lfbf flfmfnts of tif domposfd tfxt bnd tif tfxt
    // bbdking storf.
    //
    privbtf void insfrtComposfdTfxtUpdbtf(DffbultDodumfntEvfnt ding, AttributfSft bttr) {
        bddfd.rfmovfAllElfmfnts();
        BrbndiElfmfnt linfMbp = (BrbndiElfmfnt) gftDffbultRootElfmfnt();
        int offsft = ding.gftOffsft();
        int lfngti = ding.gftLfngti();
        int indfx = linfMbp.gftElfmfntIndfx(offsft);
        Elfmfnt flfm = linfMbp.gftElfmfnt(indfx);
        int flfmStbrt = flfm.gftStbrtOffsft();
        int flfmEnd = flfm.gftEndOffsft();
        BrbndiElfmfnt[] bbflfm = nfw BrbndiElfmfnt[1];
        bbflfm[0] = (BrbndiElfmfnt) drfbtfBrbndiElfmfnt(linfMbp, null);
        Elfmfnt[] rflfm = nfw Elfmfnt[1];
        rflfm[0] = flfm;
        if (flfmStbrt != offsft)
            bddfd.bddElfmfnt(drfbtfLfbfElfmfnt(bbflfm[0], null, flfmStbrt, offsft));
        bddfd.bddElfmfnt(drfbtfLfbfElfmfnt(bbflfm[0], bttr, offsft, offsft+lfngti));
        if (flfmEnd != offsft+lfngti)
            bddfd.bddElfmfnt(drfbtfLfbfElfmfnt(bbflfm[0], null, offsft+lfngti, flfmEnd));
        Elfmfnt[] blflfm = nfw Elfmfnt[bddfd.sizf()];
        bddfd.dopyInto(blflfm);
        ElfmfntEdit ff = nfw ElfmfntEdit(linfMbp, indfx, rflfm, bbflfm);
        ding.bddEdit(ff);

        bbflfm[0].rfplbdf(0, 0, blflfm);
        linfMbp.rfplbdf(indfx, 1, bbflfm);
    }

    privbtf AbstrbdtElfmfnt dffbultRoot;
    privbtf Vfdtor<Elfmfnt> bddfd = nfw Vfdtor<Elfmfnt>();
    privbtf Vfdtor<Elfmfnt> rfmovfd = nfw Vfdtor<Elfmfnt>();
    privbtf trbnsifnt Sfgmfnt s;
}
