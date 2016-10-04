/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.BufffrCbpbbilitifs.FlipContfnts;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.List;
import jbvb.io.*;

import sun.bwt.CbusfdFodusEvfnt.Cbusf;
import sun.bwt.AWTAddfssor;
import sun.jbvb2d.pipf.Rfgion;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;

dlbss CFilfDiblog implfmfnts FilfDiblogPffr {

    privbtf dlbss Tbsk implfmfnts Runnbblf {

        @Ovfrridf
        publid void run() {
            try {
                boolfbn nbvigbtfApps = !AddfssControllfr.doPrivilfgfd(
                        nfw GftBoolfbnAdtion("bpplf.bwt.usf-filf-diblog-pbdkbgfs"));
                boolfbn dioosfDirfdtorifs = AddfssControllfr.doPrivilfgfd(
                        nfw GftBoolfbnAdtion("bpplf.bwt.filfDiblogForDirfdtorifs"));

                int diblogModf = tbrgft.gftModf();
                String titlf = tbrgft.gftTitlf();
                if (titlf == null) {
                    titlf = " ";
                }

                String[] usfrFilfNbmfs = nbtivfRunFilfDiblog(titlf,
                        diblogModf,
                        tbrgft.isMultiplfModf(),
                        nbvigbtfApps,
                        dioosfDirfdtorifs,
                        tbrgft.gftFilfnbmfFiltfr() != null,
                        tbrgft.gftDirfdtory(),
                        tbrgft.gftFilf());

                String dirfdtory = null;
                String filf = null;
                Filf[] filfs = null;

                if (usfrFilfNbmfs != null) {
                    // tif diblog wbsn't dbndfllfd
                    int filfsNumbfr = usfrFilfNbmfs.lfngti;
                    filfs = nfw Filf[filfsNumbfr];
                    for (int i = 0; i < filfsNumbfr; i++) {
                        filfs[i] = nfw Filf(usfrFilfNbmfs[i]);
                    }

                    dirfdtory = filfs[0].gftPbrfnt();
                    // mbkf surf dirfdtory blwbys fnds in '/'
                    if (!dirfdtory.fndsWiti(Filf.sfpbrbtor)) {
                        dirfdtory = dirfdtory + Filf.sfpbrbtor;
                    }

                    filf = filfs[0].gftNbmf(); // pidk bny filf
                }

                // storf rfsults bbdk in domponfnt
                AWTAddfssor.FilfDiblogAddfssor bddfssor = AWTAddfssor.gftFilfDiblogAddfssor();
                bddfssor.sftDirfdtory(tbrgft, dirfdtory);
                bddfssor.sftFilf(tbrgft, filf);
                bddfssor.sftFilfs(tbrgft, filfs);
            } finblly {
                // Jbvb2 Diblog wbits for iidf to lft siow() rfturn
                tbrgft.disposf();
            }
        }
    }

    // Tif tbrgft FilfDiblog
    privbtf finbl FilfDiblog tbrgft;

    CFilfDiblog(FilfDiblog tbrgft) {
        tiis.tbrgft = tbrgft;
    }

    @Ovfrridf
    publid void disposf() {
        LWCToolkit.tbrgftDisposfdPffr(tbrgft, tiis);
        // Unlikf otifr pffrs, wf do not ibvf b nbtivf modfl pointfr to
        // disposf of bfdbusf tif sbvf bnd opfn pbnfls brf nfvfr rflfbsfd by
        // bn bpplidbtion.
    }

    @Ovfrridf
    publid void sftVisiblf(boolfbn visiblf) {
        if (visiblf) {
            // Jbvb2 Diblog dlbss rfquirfs pffr to run dodf in b sfpbrbtf tirfbd
            // bnd ibndlfs kffping tif dbll modbl
            nfw Tirfbd(nfw Tbsk()).stbrt(); // invokfs my 'run' mftiod, bflow...
        }
        // Wf iidf oursflf bfforf "siow" rfturns - sftVisiblf(fblsf)
        // dofsn't bpply
    }

    /**
     * A dbllbbdk mftiod.
     * If tif filf diblog ibs b filf filtfr, bsk it if inFilfnbmf is bddfptbblf.
     * If tif diblog dofsn't ibvf b filf filtfr rfturn truf.
     */
    privbtf boolfbn qufryFilfnbmfFiltfr(finbl String inFilfnbmf) {
        boolfbn rft = fblsf;

        finbl FilfnbmfFiltfr ff = tbrgft.gftFilfnbmfFiltfr();
        Filf filfObj = nfw Filf(inFilfnbmf);

        // Dirfdtorifs brf nfvfr filtfrfd by tif FilfDiblog.
        if (!filfObj.isDirfdtory()) {
            Filf dirfdtoryObj = nfw Filf(filfObj.gftPbrfnt());
            String nbmfOnly = filfObj.gftNbmf();
            rft = ff.bddfpt(dirfdtoryObj, nbmfOnly);
        }
        rfturn rft;
    }

    privbtf nbtivf String[] nbtivfRunFilfDiblog(String titlf, int modf,
            boolfbn multiplfModf, boolfbn siouldNbvigbtfApps,
            boolfbn dbnCioosfDirfdtorifs, boolfbn ibsFilfnbmfFiltfr,
            String dirfdtory, String filf);

    @Ovfrridf
    publid void sftDirfdtory(String dir) {
    }

    @Ovfrridf
    publid void sftFilf(String filf) {
    }

    @Ovfrridf
    publid void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr) {
    }

    @Ovfrridf
    publid void blodkWindows(List<Window> windows) {
    }

    @Ovfrridf
    publid void sftRfsizbblf(boolfbn rfsizfbblf) {
    }

    @Ovfrridf
    publid void sftTitlf(String titlf) {
    }

    @Ovfrridf
    publid void rfpositionSfdurityWbrning() {
    }

    @Ovfrridf
    publid void updbtfAlwbysOnTopStbtf() {
    }

    @Ovfrridf
    publid void sftModblBlodkfd(Diblog blodkfr, boolfbn blodkfd) {
    }

    @Ovfrridf
    publid void sftOpbdity(flobt opbdity) {
    }

    @Ovfrridf
    publid void sftOpbquf(boolfbn isOpbquf) {
    }

    @Ovfrridf
    publid void toBbdk() {
    }

    @Ovfrridf
    publid void toFront() {
    }

    @Ovfrridf
    publid void updbtfFodusbblfWindowStbtf() {
    }

    @Ovfrridf
    publid void updbtfIdonImbgfs() {
    }

    @Ovfrridf
    publid void updbtfMinimumSizf() {
    }

    @Ovfrridf
    publid void updbtfWindow() {
    }

    @Ovfrridf
    publid void bfginLbyout() {
    }

    @Ovfrridf
    publid void bfginVblidbtf() {
    }

    @Ovfrridf
    publid void fndLbyout() {
    }

    @Ovfrridf
    publid void fndVblidbtf() {
    }

    @Ovfrridf
    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    @Ovfrridf
    publid void bpplySibpf(Rfgion sibpf) {
    }

    @Ovfrridf
    publid boolfbn dbnDftfrminfObsdurity() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid int difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn 0;
    }

    @Ovfrridf
    publid void doblfsdfPbintEvfnt(PbintEvfnt f) {
    }

    @Ovfrridf
    publid void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
            tirows AWTExdfption {
    }

    @Ovfrridf
    publid Imbgf drfbtfImbgf(ImbgfProdudfr produdfr) {
        rfturn null;
    }

    @Ovfrridf
    publid Imbgf drfbtfImbgf(int widti, int ifigit) {
        rfturn null;
    }

    @Ovfrridf
    publid VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit) {
        rfturn null;
    }

    @Ovfrridf
    publid void dfstroyBufffrs() {
    }

    @Ovfrridf
    publid void flip(int x1, int y1, int x2, int y2, FlipContfnts flipAdtion) {
    }

    @Ovfrridf
    publid Imbgf gftBbdkBufffr() {
        rfturn null;
    }

    @Ovfrridf
    publid ColorModfl gftColorModfl() {
        rfturn null;
    }

    @Ovfrridf
    publid FontMftrids gftFontMftrids(Font font) {
        rfturn null;
    }

    @Ovfrridf
    publid Grbpiids gftGrbpiids() {
        rfturn null;
    }

    @Ovfrridf
    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        rfturn null;
    }

    @Ovfrridf
    publid Point gftLodbtionOnSdrffn() {
        rfturn null;
    }

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf() {
        rfturn tbrgft.gftSizf();
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn gftMinimumSizf();
    }

    @Ovfrridf
    publid void ibndlfEvfnt(AWTEvfnt f) {
    }

    @Ovfrridf
    publid boolfbn ibndlfsWifflSdrolling() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn isObsdurfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn isRfpbrfntSupportfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid void lbyout() {
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
    }

    @Ovfrridf
    publid boolfbn prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn fblsf;
    }

    @Ovfrridf
    publid void print(Grbpiids g) {
    }

    @Ovfrridf
    publid void rfpbrfnt(ContbinfrPffr nfwContbinfr) {
    }

    @Ovfrridf
    publid boolfbn rfqufstFodus(Componfnt ligitwfigitCiild, boolfbn tfmporbry,
            boolfbn fodusfdWindowCibngfAllowfd, long timf, Cbusf dbusf) {
        rfturn fblsf;
    }

    @Ovfrridf
    publid void sftBbdkground(Color d) {
    }

    @Ovfrridf
    publid void sftForfground(Color d) {
    }

    @Ovfrridf
    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
    }

    @Ovfrridf
    publid void sftEnbblfd(boolfbn f) {
    }

    @Ovfrridf
    publid void sftFont(Font f) {
    }

    @Ovfrridf
    publid void sftZOrdfr(ComponfntPffr bbovf) {
    }

    @Ovfrridf
    publid void updbtfCursorImmfdibtfly() {
    }

    @Ovfrridf
    publid boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        rfturn fblsf;
    }
}
