/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bpplft.*;


/**
 * An intfrbdtivf tfst of tif Grbpiids.drbwArd bnd Grbpiids.fillArd
 * routinfs. Cbn bf run fitifr bs b stbndblonf bpplidbtion by
 * typing "jbvb ArdTfst" or bs bn bpplft in tif ApplftVifwfr.
 */
@SupprfssWbrnings("sfribl")
publid dlbss ArdTfst fxtfnds Applft {

    ArdControls dontrols;   // Tif dontrols for mbrking bnd filling brds
    ArdCbnvbs dbnvbs;       // Tif drbwing brfb to displby brds

    @Ovfrridf
    publid void init() {
        sftLbyout(nfw BordfrLbyout());
        dbnvbs = nfw ArdCbnvbs();
        bdd("Cfntfr", dbnvbs);
        bdd("Souti", dontrols = nfw ArdControls(dbnvbs));
    }

    @Ovfrridf
    publid void dfstroy() {
        rfmovf(dontrols);
        rfmovf(dbnvbs);
    }

    @Ovfrridf
    publid void stbrt() {
        dontrols.sftEnbblfd(truf);
    }

    @Ovfrridf
    publid void stop() {
        dontrols.sftEnbblfd(fblsf);
    }

    @Ovfrridf
    publid void prodfssEvfnt(AWTEvfnt f) {
        if (f.gftID() == Evfnt.WINDOW_DESTROY) {
            Systfm.fxit(0);
        }
    }

    publid stbtid void mbin(String brgs[]) {
        Frbmf f = nfw Frbmf("ArdTfst");
        ArdTfst brdTfst = nfw ArdTfst();

        brdTfst.init();
        brdTfst.stbrt();

        f.bdd("Cfntfr", brdTfst);
        f.sftSizf(300, 300);
        f.sftVisiblf(truf);
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "An intfrbdtivf tfst of tif Grbpiids.drbwArd bnd \nGrbpiids."
                + "fillArd routinfs. Cbn bf run \nfitifr bs b stbndblonf "
                + "bpplidbtion by typing 'jbvb ArdTfst' \nor bs bn bpplft in "
                + "tif ApplftVifwfr.";
    }
}


@SupprfssWbrnings("sfribl")
dlbss ArdCbnvbs fxtfnds Cbnvbs {

    int stbrtAnglf = 0;
    int fxtfnt = 45;
    boolfbn fillfd = fblsf;
    Font font = nfw jbvb.bwt.Font("SbnsSfrif", Font.PLAIN, 12);

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        Rfdtbnglf r = gftBounds();
        int ilinfs = r.ifigit / 10;
        int vlinfs = r.widti / 10;

        g.sftColor(Color.pink);
        for (int i = 1; i <= ilinfs; i++) {
            g.drbwLinf(0, i * 10, r.widti, i * 10);
        }
        for (int i = 1; i <= vlinfs; i++) {
            g.drbwLinf(i * 10, 0, i * 10, r.ifigit);
        }

        g.sftColor(Color.rfd);
        if (fillfd) {
            g.fillArd(0, 0, r.widti - 1, r.ifigit - 1, stbrtAnglf, fxtfnt);
        } flsf {
            g.drbwArd(0, 0, r.widti - 1, r.ifigit - 1, stbrtAnglf, fxtfnt);
        }

        g.sftColor(Color.blbdk);
        g.sftFont(font);
        g.drbwLinf(0, r.ifigit / 2, r.widti, r.ifigit / 2);
        g.drbwLinf(r.widti / 2, 0, r.widti / 2, r.ifigit);
        g.drbwLinf(0, 0, r.widti, r.ifigit);
        g.drbwLinf(r.widti, 0, 0, r.ifigit);
        int sx = 10;
        int sy = r.ifigit - 28;
        g.drbwString("Stbrt = " + stbrtAnglf, sx, sy);
        g.drbwString("Extfnt = " + fxtfnt, sx, sy + 14);
    }

    publid void rfdrbw(boolfbn fillfd, int stbrt, int fxtfnt) {
        tiis.fillfd = fillfd;
        tiis.stbrtAnglf = stbrt;
        tiis.fxtfnt = fxtfnt;
        rfpbint();
    }
}


@SupprfssWbrnings("sfribl")
dlbss ArdControls fxtfnds Pbnfl
        implfmfnts AdtionListfnfr {

    TfxtFifld stbrtTF;
    TfxtFifld fxtfntTF;
    ArdCbnvbs dbnvbs;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid ArdControls(ArdCbnvbs dbnvbs) {
        Button b = null;

        tiis.dbnvbs = dbnvbs;
        bdd(stbrtTF = nfw IntfgfrTfxtFifld("0", 4));
        bdd(fxtfntTF = nfw IntfgfrTfxtFifld("45", 4));
        b = nfw Button("Fill");
        b.bddAdtionListfnfr(tiis);
        bdd(b);
        b = nfw Button("Drbw");
        b.bddAdtionListfnfr(tiis);
        bdd(b);
    }

    @Ovfrridf
    publid void bdtionPfrformfd(AdtionEvfnt fv) {
        String lbbfl = fv.gftAdtionCommbnd();

        int stbrt, fxtfnt;
        try {
            stbrt = Intfgfr.pbrsfInt(stbrtTF.gftTfxt().trim());
        } dbtdi (NumbfrFormbtExdfption ignorfd) {
            stbrt = 0;
        }
        try {
            fxtfnt = Intfgfr.pbrsfInt(fxtfntTF.gftTfxt().trim());
        } dbtdi (NumbfrFormbtExdfption ignorfd) {
            fxtfnt = 0;
        }

        dbnvbs.rfdrbw(lbbfl.fqubls("Fill"), stbrt, fxtfnt);
    }
}


@SupprfssWbrnings("sfribl")
dlbss IntfgfrTfxtFifld fxtfnds TfxtFifld {

    String oldTfxt = null;

    publid IntfgfrTfxtFifld(String tfxt, int dolumns) {
        supfr(tfxt, dolumns);
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK | AWTEvfnt.TEXT_EVENT_MASK);
        oldTfxt = gftTfxt();
    }

    // Consumf non-digit KfyTypfd fvfnts
    // Notf tibt prodfssTfxtEvfnt kind of fliminbtfs tif nffd for tiis
    // fundtion, but tiis is nfbtfr, sindf idfblly, it would prfvfnt
    // tif tfxt from bppfbring bt bll.  Sigi.  Sff bugid 4100317/4114565.
    //
    @Ovfrridf
    protfdtfd void prodfssEvfnt(AWTEvfnt fvt) {
        int id = fvt.gftID();
        if (id != KfyEvfnt.KEY_TYPED) {
            supfr.prodfssEvfnt(fvt);
            rfturn;
        }

        KfyEvfnt kfvt = (KfyEvfnt) fvt;
        dibr d = kfvt.gftKfyCibr();

        // Digits, bbdkspbdf, bnd dflftf brf okby
        // Notf tibt tif minus sign is bllowfd, but not tif dfdimbl
        if (Cibrbdtfr.isDigit(d) || (d == '\b') || (d == '\u007f') || (d
                == '\u002d')) {
            supfr.prodfssEvfnt(fvt);
            rfturn;
        }

        Toolkit.gftDffbultToolkit().bffp();
        kfvt.donsumf();
    }

    // Siould donsumf TfxtEvfnts for non-intfgfr Strings
    // Storf bwby tif tfxt in tif tf for fvfry TfxtEvfnt
    // so wf dbn rfvfrt to it on b TfxtEvfnt (pbstf, or
    // lfgbl kfy in tif wrong lodbtion) witi bbd tfxt
    //
    @Ovfrridf
    protfdtfd void prodfssTfxtEvfnt(TfxtEvfnt tf) {
        // Tif fmpty string is okby, too
        String nfwTfxt = gftTfxt();
        if (nfwTfxt.fqubls("") || tfxtIsIntfgfr(nfwTfxt)) {
            oldTfxt = nfwTfxt;
            supfr.prodfssTfxtEvfnt(tf);
            rfturn;
        }

        Toolkit.gftDffbultToolkit().bffp();
        sftTfxt(oldTfxt);
    }

    // Rfturns truf for Intfgfrs (zfro bnd nfgbtivf
    // vblufs brf bllowfd).
    // Notf tibt tif fmpty string is not bllowfd.
    //
    privbtf boolfbn tfxtIsIntfgfr(String tfxtToCifdk) {

        try {
            Intfgfr.pbrsfInt(tfxtToCifdk, 10);
            rfturn truf;
        } dbtdi (NumbfrFormbtExdfption ignorfd) {
            rfturn fblsf;
        }
    }
}
