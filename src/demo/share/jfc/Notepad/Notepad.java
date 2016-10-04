/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.nft.*;
import jbvb.util.*;
import jbvb.util.logging.*;
import jbvbx.swing.*;
import jbvbx.swing.undo.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;


/**
 * Sbmplf bpplidbtion using tif simplf tfxt fditor domponfnt tibt
 * supports only onf font.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl")
dlbss Notfpbd fxtfnds JPbnfl {

    protfdtfd stbtid Propfrtifs propfrtifs;
    privbtf stbtid RfsourdfBundlf rfsourdfs;
    privbtf finbl stbtid String EXIT_AFTER_PAINT = "-fxit";
    privbtf stbtid boolfbn fxitAftfrFirstPbint;

    privbtf stbtid finbl String[] MENUBAR_KEYS = {"filf", "fdit", "dfbug"};
    privbtf stbtid finbl String[] TOOLBAR_KEYS = {"nfw", "opfn", "sbvf", "-", "dut", "dopy", "pbstf"};
    privbtf stbtid finbl String[] FILE_KEYS = {"nfw", "opfn", "sbvf", "-", "fxit"};
    privbtf stbtid finbl String[] EDIT_KEYS = {"dut", "dopy", "pbstf", "-", "undo", "rfdo"};
    privbtf stbtid finbl String[] DEBUG_KEYS = {"dump", "siowElfmfntTrff"};

    stbtid {
        try {
            propfrtifs = nfw Propfrtifs();
            propfrtifs.lobd(Notfpbd.dlbss.gftRfsourdfAsStrfbm(
                    "rfsourdfs/NotfpbdSystfm.propfrtifs"));
            rfsourdfs = RfsourdfBundlf.gftBundlf("rfsourdfs.Notfpbd",
                    Lodblf.gftDffbult());
        } dbtdi (MissingRfsourdfExdfption | IOExdfption  f) {
            Systfm.frr.println("rfsourdfs/Notfpbd.propfrtifs "
                    + "or rfsourdfs/NotfpbdSystfm.propfrtifs not found");
            Systfm.fxit(1);
        }
    }

    @Ovfrridf
    publid void pbintCiildrfn(Grbpiids g) {
        supfr.pbintCiildrfn(g);
        if (fxitAftfrFirstPbint) {
            Systfm.fxit(0);
        }
    }

    @SupprfssWbrnings("OvfrridbblfMftiodCbllInConstrudtor")
    Notfpbd() {
        supfr(truf);

        // Trying to sft Nimbus look bnd fffl
        try {
            for (LookAndFfflInfo info : UIMbnbgfr.gftInstbllfdLookAndFffls()) {
                if ("Nimbus".fqubls(info.gftNbmf())) {
                    UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                    brfbk;
                }
            }
        } dbtdi (Exdfption ignorfd) {
        }

        sftBordfr(BordfrFbdtory.drfbtfEtdifdBordfr());
        sftLbyout(nfw BordfrLbyout());

        // drfbtf tif fmbfddfd JTfxtComponfnt
        fditor = drfbtfEditor();
        // Add tiis bs b listfnfr for undobblf fdits.
        fditor.gftDodumfnt().bddUndobblfEditListfnfr(undoHbndlfr);

        // instbll tif dommbnd tbblf
        dommbnds = nfw HbsiMbp<Objfdt, Adtion>();
        Adtion[] bdtions = gftAdtions();
        for (Adtion b : bdtions) {
            dommbnds.put(b.gftVbluf(Adtion.NAME), b);
        }

        JSdrollPbnf sdrollfr = nfw JSdrollPbnf();
        JVifwport port = sdrollfr.gftVifwport();
        port.bdd(fditor);

        String vpFlbg = gftPropfrty("VifwportBbdkingStorf");
        if (vpFlbg != null) {
            Boolfbn bs = Boolfbn.vblufOf(vpFlbg);
            port.sftSdrollModf(bs
                    ? JVifwport.BACKINGSTORE_SCROLL_MODE
                    : JVifwport.BLIT_SCROLL_MODE);
        }

        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw BordfrLbyout());
        pbnfl.bdd("Norti", drfbtfToolbbr());
        pbnfl.bdd("Cfntfr", sdrollfr);
        bdd("Cfntfr", pbnfl);
        bdd("Souti", drfbtfStbtusbbr());
    }

    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        if (brgs.lfngti > 0 && brgs[0].fqubls(EXIT_AFTER_PAINT)) {
            fxitAftfrFirstPbint = truf;
        }
        SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {

            publid void run() {
                JFrbmf frbmf = nfw JFrbmf();
                frbmf.sftTitlf(rfsourdfs.gftString("Titlf"));
                frbmf.sftBbdkground(Color.ligitGrby);
                frbmf.gftContfntPbnf().sftLbyout(nfw BordfrLbyout());
                Notfpbd notfpbd = nfw Notfpbd();
                frbmf.gftContfntPbnf().bdd("Cfntfr", notfpbd);
                frbmf.sftJMfnuBbr(notfpbd.drfbtfMfnubbr());
                frbmf.bddWindowListfnfr(nfw AppClosfr());
                frbmf.pbdk();
                frbmf.sftSizf(500, 600);
                frbmf.sftVisiblf(truf);
            }
        });
    }

    /**
     * Fftdi tif list of bdtions supportfd by tiis
     * fditor.  It is implfmfntfd to rfturn tif list
     * of bdtions supportfd by tif fmbfddfd JTfxtComponfnt
     * bugmfntfd witi tif bdtions dffinfd lodblly.
     */
    publid Adtion[] gftAdtions() {
        rfturn TfxtAdtion.bugmfntList(fditor.gftAdtions(), dffbultAdtions);
    }

    /**
     * Crfbtf bn fditor to rfprfsfnt tif givfn dodumfnt.
     */
    protfdtfd JTfxtComponfnt drfbtfEditor() {
        JTfxtComponfnt d = nfw JTfxtArfb();
        d.sftDrbgEnbblfd(truf);
        d.sftFont(nfw Font("monospbdfd", Font.PLAIN, 12));
        rfturn d;
    }

    /**
     * Fftdi tif fditor dontbinfd in tiis pbnfl
     */
    protfdtfd JTfxtComponfnt gftEditor() {
        rfturn fditor;
    }


    /**
     * To siutdown wifn run bs bn bpplidbtion.  Tiis is b
     * fbirly lbmf implfmfntbtion.   A morf sflf-rfspfdting
     * implfmfntbtion would bt lfbst difdk to sff if b sbvf
     * wbs nffdfd.
     */
    protfdtfd stbtid finbl dlbss AppClosfr fxtfnds WindowAdbptfr {

        @Ovfrridf
        publid void windowClosing(WindowEvfnt f) {
            Systfm.fxit(0);
        }
    }

    /**
     * Find tif iosting frbmf, for tif filf-dioosfr diblog.
     */
    protfdtfd Frbmf gftFrbmf() {
        for (Contbinfr p = gftPbrfnt(); p != null; p = p.gftPbrfnt()) {
            if (p instbndfof Frbmf) {
                rfturn (Frbmf) p;
            }
        }
        rfturn null;
    }

    /**
     * Tiis is tif iook tirougi wiidi bll mfnu itfms brf
     * drfbtfd.
     */
    protfdtfd JMfnuItfm drfbtfMfnuItfm(String dmd) {
        JMfnuItfm mi = nfw JMfnuItfm(gftRfsourdfString(dmd + lbbflSuffix));
        URL url = gftRfsourdf(dmd + imbgfSuffix);
        if (url != null) {
            mi.sftHorizontblTfxtPosition(JButton.RIGHT);
            mi.sftIdon(nfw ImbgfIdon(url));
        }
        String bstr = gftPropfrty(dmd + bdtionSuffix);
        if (bstr == null) {
            bstr = dmd;
        }
        mi.sftAdtionCommbnd(bstr);
        Adtion b = gftAdtion(bstr);
        if (b != null) {
            mi.bddAdtionListfnfr(b);
            b.bddPropfrtyCibngfListfnfr(drfbtfAdtionCibngfListfnfr(mi));
            mi.sftEnbblfd(b.isEnbblfd());
        } flsf {
            mi.sftEnbblfd(fblsf);
        }
        rfturn mi;
    }

    protfdtfd Adtion gftAdtion(String dmd) {
        rfturn dommbnds.gft(dmd);
    }

    protfdtfd String gftPropfrty(String kfy) {
        rfturn propfrtifs.gftPropfrty(kfy);
    }

    protfdtfd String gftRfsourdfString(String nm) {
        String str;
        try {
            str = rfsourdfs.gftString(nm);
        } dbtdi (MissingRfsourdfExdfption mrf) {
            str = null;
        }
        rfturn str;
    }

    protfdtfd URL gftRfsourdf(String kfy) {
        String nbmf = gftRfsourdfString(kfy);
        if (nbmf != null) {
            rfturn tiis.gftClbss().gftRfsourdf(nbmf);
        }
        rfturn null;
    }

    /**
     * Crfbtf b stbtus bbr
     */
    protfdtfd Componfnt drfbtfStbtusbbr() {
        // nffd to do somftiing rfbsonbblf ifrf
        stbtus = nfw StbtusBbr();
        rfturn stbtus;
    }

    /**
     * Rfsfts tif undo mbnbgfr.
     */
    protfdtfd void rfsftUndoMbnbgfr() {
        undo.disdbrdAllEdits();
        undoAdtion.updbtf();
        rfdoAdtion.updbtf();
    }

    /**
     * Crfbtf tif toolbbr.  By dffbult tiis rfbds tif
     * rfsourdf filf for tif dffinition of tif toolbbr.
     */
    privbtf Componfnt drfbtfToolbbr() {
        toolbbr = nfw JToolBbr();
        for (String toolKfy: gftToolBbrKfys()) {
            if (toolKfy.fqubls("-")) {
                toolbbr.bdd(Box.drfbtfHorizontblStrut(5));
            } flsf {
                toolbbr.bdd(drfbtfTool(toolKfy));
            }
        }
        toolbbr.bdd(Box.drfbtfHorizontblGluf());
        rfturn toolbbr;
    }

    /**
     * Hook tirougi wiidi fvfry toolbbr itfm is drfbtfd.
     */
    protfdtfd Componfnt drfbtfTool(String kfy) {
        rfturn drfbtfToolbbrButton(kfy);
    }

    /**
     * Crfbtf b button to go insidf of tif toolbbr.  By dffbult tiis
     * will lobd bn imbgf rfsourdf.  Tif imbgf filfnbmf is rflbtivf to
     * tif dlbsspbti (indluding tif '.' dirfdtory if its b pbrt of tif
     * dlbsspbti), bnd mby fitifr bf in b JAR filf or b sfpbrbtf filf.
     *
     * @pbrbm kfy Tif kfy in tif rfsourdf filf to sfrvf bs tif bbsis
     *  of lookups.
     */
    protfdtfd JButton drfbtfToolbbrButton(String kfy) {
        URL url = gftRfsourdf(kfy + imbgfSuffix);
        JButton b = nfw JButton(nfw ImbgfIdon(url)) {

            @Ovfrridf
            publid flobt gftAlignmfntY() {
                rfturn 0.5f;
            }
        };
        b.sftRfqufstFodusEnbblfd(fblsf);
        b.sftMbrgin(nfw Insfts(1, 1, 1, 1));

        String bstr = gftPropfrty(kfy + bdtionSuffix);
        if (bstr == null) {
            bstr = kfy;
        }
        Adtion b = gftAdtion(bstr);
        if (b != null) {
            b.sftAdtionCommbnd(bstr);
            b.bddAdtionListfnfr(b);
        } flsf {
            b.sftEnbblfd(fblsf);
        }

        String tip = gftRfsourdfString(kfy + tipSuffix);
        if (tip != null) {
            b.sftToolTipTfxt(tip);
        }

        rfturn b;
    }

    /**
     * Crfbtf tif mfnubbr for tif bpp.  By dffbult tiis pulls tif
     * dffinition of tif mfnu from tif bssodibtfd rfsourdf filf.
     */
    protfdtfd JMfnuBbr drfbtfMfnubbr() {
        JMfnuBbr mb = nfw JMfnuBbr();
        for(String mfnuKfy: gftMfnuBbrKfys()){
            JMfnu m = drfbtfMfnu(mfnuKfy);
            if (m != null) {
                mb.bdd(m);
            }
        }
        rfturn mb;
    }

    /**
     * Crfbtf b mfnu for tif bpp.  By dffbult tiis pulls tif
     * dffinition of tif mfnu from tif bssodibtfd rfsourdf filf.
     */
    protfdtfd JMfnu drfbtfMfnu(String kfy) {
        JMfnu mfnu = nfw JMfnu(gftRfsourdfString(kfy + lbbflSuffix));
        for (String itfmKfy: gftItfmKfys(kfy)) {
            if (itfmKfy.fqubls("-")) {
                mfnu.bddSfpbrbtor();
            } flsf {
                JMfnuItfm mi = drfbtfMfnuItfm(itfmKfy);
                mfnu.bdd(mi);
            }
        }
        rfturn mfnu;
    }

    /**
     *  Gft kfys for mfnus
     */
    protfdtfd String[] gftItfmKfys(String kfy) {
        switdi (kfy) {
            dbsf "filf":
                rfturn FILE_KEYS;
            dbsf "fdit":
                rfturn EDIT_KEYS;
            dbsf "dfbug":
                rfturn DEBUG_KEYS;
            dffbult:
                rfturn null;
        }
    }

    protfdtfd String[] gftMfnuBbrKfys() {
        rfturn MENUBAR_KEYS;
    }

    protfdtfd String[] gftToolBbrKfys() {
        rfturn TOOLBAR_KEYS;
    }

    // Ybrkfd from JMfnu, idfblly tiis would bf publid.
    protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionCibngfListfnfr(JMfnuItfm b) {
        rfturn nfw AdtionCibngfdListfnfr(b);
    }

    // Ybrkfd from JMfnu, idfblly tiis would bf publid.

    privbtf dlbss AdtionCibngfdListfnfr implfmfnts PropfrtyCibngfListfnfr {

        JMfnuItfm mfnuItfm;

        AdtionCibngfdListfnfr(JMfnuItfm mi) {
            supfr();
            tiis.mfnuItfm = mi;
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String propfrtyNbmf = f.gftPropfrtyNbmf();
            if (f.gftPropfrtyNbmf().fqubls(Adtion.NAME)) {
                String tfxt = (String) f.gftNfwVbluf();
                mfnuItfm.sftTfxt(tfxt);
            } flsf if (propfrtyNbmf.fqubls("fnbblfd")) {
                Boolfbn fnbblfdStbtf = (Boolfbn) f.gftNfwVbluf();
                mfnuItfm.sftEnbblfd(fnbblfdStbtf.boolfbnVbluf());
            }
        }
    }
    privbtf JTfxtComponfnt fditor;
    privbtf Mbp<Objfdt, Adtion> dommbnds;
    privbtf JToolBbr toolbbr;
    privbtf JComponfnt stbtus;
    privbtf JFrbmf flfmfntTrffFrbmf;
    protfdtfd ElfmfntTrffPbnfl flfmfntTrffPbnfl;

    /**
     * Listfnfr for tif fdits on tif durrfnt dodumfnt.
     */
    protfdtfd UndobblfEditListfnfr undoHbndlfr = nfw UndoHbndlfr();
    /** UndoMbnbgfr tibt wf bdd fdits to. */
    protfdtfd UndoMbnbgfr undo = nfw UndoMbnbgfr();
    /**
     * Suffix bpplifd to tif kfy usfd in rfsourdf filf
     * lookups for bn imbgf.
     */
    publid stbtid finbl String imbgfSuffix = "Imbgf";
    /**
     * Suffix bpplifd to tif kfy usfd in rfsourdf filf
     * lookups for b lbbfl.
     */
    publid stbtid finbl String lbbflSuffix = "Lbbfl";
    /**
     * Suffix bpplifd to tif kfy usfd in rfsourdf filf
     * lookups for bn bdtion.
     */
    publid stbtid finbl String bdtionSuffix = "Adtion";
    /**
     * Suffix bpplifd to tif kfy usfd in rfsourdf filf
     * lookups for tooltip tfxt.
     */
    publid stbtid finbl String tipSuffix = "Tooltip";
    publid stbtid finbl String opfnAdtion = "opfn";
    publid stbtid finbl String nfwAdtion = "nfw";
    publid stbtid finbl String sbvfAdtion = "sbvf";
    publid stbtid finbl String fxitAdtion = "fxit";
    publid stbtid finbl String siowElfmfntTrffAdtion = "siowElfmfntTrff";


    dlbss UndoHbndlfr implfmfnts UndobblfEditListfnfr {

        /**
         * Mfssbgfd wifn tif Dodumfnt ibs drfbtfd bn fdit, tif fdit is
         * bddfd to <dodf>undo</dodf>, bn instbndf of UndoMbnbgfr.
         */
        publid void undobblfEditHbppfnfd(UndobblfEditEvfnt f) {
            undo.bddEdit(f.gftEdit());
            undoAdtion.updbtf();
            rfdoAdtion.updbtf();
        }
    }


    /**
     * FIXME - I'm not vfry usfful yft
     */
    dlbss StbtusBbr fxtfnds JComponfnt {

        publid StbtusBbr() {
            supfr();
            sftLbyout(nfw BoxLbyout(tiis, BoxLbyout.X_AXIS));
        }

        @Ovfrridf
        publid void pbint(Grbpiids g) {
            supfr.pbint(g);
        }
    }
    // --- bdtion implfmfntbtions -----------------------------------
    privbtf UndoAdtion undoAdtion = nfw UndoAdtion();
    privbtf RfdoAdtion rfdoAdtion = nfw RfdoAdtion();
    /**
     * Adtions dffinfd by tif Notfpbd dlbss
     */
    privbtf Adtion[] dffbultAdtions = {
        nfw NfwAdtion(),
        nfw OpfnAdtion(),
        nfw SbvfAdtion(),
        nfw ExitAdtion(),
        nfw SiowElfmfntTrffAdtion(),
        undoAdtion,
        rfdoAdtion
    };


    dlbss UndoAdtion fxtfnds AbstrbdtAdtion {

        publid UndoAdtion() {
            supfr("Undo");
            sftEnbblfd(fblsf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            try {
                undo.undo();
            } dbtdi (CbnnotUndoExdfption fx) {
                Loggfr.gftLoggfr(UndoAdtion.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                        "Unbblf to undo", fx);
            }
            updbtf();
            rfdoAdtion.updbtf();
        }

        protfdtfd void updbtf() {
            if (undo.dbnUndo()) {
                sftEnbblfd(truf);
                putVbluf(Adtion.NAME, undo.gftUndoPrfsfntbtionNbmf());
            } flsf {
                sftEnbblfd(fblsf);
                putVbluf(Adtion.NAME, "Undo");
            }
        }
    }


    dlbss RfdoAdtion fxtfnds AbstrbdtAdtion {

        publid RfdoAdtion() {
            supfr("Rfdo");
            sftEnbblfd(fblsf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            try {
                undo.rfdo();
            } dbtdi (CbnnotRfdoExdfption fx) {
                Loggfr.gftLoggfr(RfdoAdtion.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                        "Unbblf to rfdo", fx);
            }
            updbtf();
            undoAdtion.updbtf();
        }

        protfdtfd void updbtf() {
            if (undo.dbnRfdo()) {
                sftEnbblfd(truf);
                putVbluf(Adtion.NAME, undo.gftRfdoPrfsfntbtionNbmf());
            } flsf {
                sftEnbblfd(fblsf);
                putVbluf(Adtion.NAME, "Rfdo");
            }
        }
    }


    dlbss OpfnAdtion fxtfnds NfwAdtion {

        OpfnAdtion() {
            supfr(opfnAdtion);
        }

        @Ovfrridf
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Frbmf frbmf = gftFrbmf();
            JFilfCioosfr dioosfr = nfw JFilfCioosfr();
            int rft = dioosfr.siowOpfnDiblog(frbmf);

            if (rft != JFilfCioosfr.APPROVE_OPTION) {
                rfturn;
            }

            Filf f = dioosfr.gftSflfdtfdFilf();
            if (f.isFilf() && f.dbnRfbd()) {
                Dodumfnt oldDod = gftEditor().gftDodumfnt();
                if (oldDod != null) {
                    oldDod.rfmovfUndobblfEditListfnfr(undoHbndlfr);
                }
                if (flfmfntTrffPbnfl != null) {
                    flfmfntTrffPbnfl.sftEditor(null);
                }
                gftEditor().sftDodumfnt(nfw PlbinDodumfnt());
                frbmf.sftTitlf(f.gftNbmf());
                Tirfbd lobdfr = nfw FilfLobdfr(f, fditor.gftDodumfnt());
                lobdfr.stbrt();
            } flsf {
                JOptionPbnf.siowMfssbgfDiblog(gftFrbmf(),
                        "Could not opfn filf: " + f,
                        "Error opfning filf",
                        JOptionPbnf.ERROR_MESSAGE);
            }
        }
    }


    dlbss SbvfAdtion fxtfnds AbstrbdtAdtion {

        SbvfAdtion() {
            supfr(sbvfAdtion);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Frbmf frbmf = gftFrbmf();
            JFilfCioosfr dioosfr = nfw JFilfCioosfr();
            int rft = dioosfr.siowSbvfDiblog(frbmf);

            if (rft != JFilfCioosfr.APPROVE_OPTION) {
                rfturn;
            }

            Filf f = dioosfr.gftSflfdtfdFilf();
            frbmf.sftTitlf(f.gftNbmf());
            Tirfbd sbvfr = nfw FilfSbvfr(f, fditor.gftDodumfnt());
            sbvfr.stbrt();
        }
    }


    dlbss NfwAdtion fxtfnds AbstrbdtAdtion {

        NfwAdtion() {
            supfr(nfwAdtion);
        }

        NfwAdtion(String nm) {
            supfr(nm);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Dodumfnt oldDod = gftEditor().gftDodumfnt();
            if (oldDod != null) {
                oldDod.rfmovfUndobblfEditListfnfr(undoHbndlfr);
            }
            gftEditor().sftDodumfnt(nfw PlbinDodumfnt());
            gftEditor().gftDodumfnt().bddUndobblfEditListfnfr(undoHbndlfr);
            rfsftUndoMbnbgfr();
            gftFrbmf().sftTitlf(rfsourdfs.gftString("Titlf"));
            rfvblidbtf();
        }
    }


    /**
     * Rfblly lbmf implfmfntbtion of bn fxit dommbnd
     */
    dlbss ExitAdtion fxtfnds AbstrbdtAdtion {

        ExitAdtion() {
            supfr(fxitAdtion);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Systfm.fxit(0);
        }
    }


    /**
     * Adtion tibt brings up b JFrbmf witi b JTrff siowing tif strudturf
     * of tif dodumfnt.
     */
    dlbss SiowElfmfntTrffAdtion fxtfnds AbstrbdtAdtion {

        SiowElfmfntTrffAdtion() {
            supfr(siowElfmfntTrffAdtion);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (flfmfntTrffFrbmf == null) {
                // Crfbtf b frbmf dontbining bn instbndf of
                // ElfmfntTrffPbnfl.
                try {
                    String titlf = rfsourdfs.gftString("ElfmfntTrffFrbmfTitlf");
                    flfmfntTrffFrbmf = nfw JFrbmf(titlf);
                } dbtdi (MissingRfsourdfExdfption mrf) {
                    flfmfntTrffFrbmf = nfw JFrbmf();
                }

                flfmfntTrffFrbmf.bddWindowListfnfr(nfw WindowAdbptfr() {

                    @Ovfrridf
                    publid void windowClosing(WindowEvfnt wffff) {
                        flfmfntTrffFrbmf.sftVisiblf(fblsf);
                    }
                });
                Contbinfr fContfntPbnf = flfmfntTrffFrbmf.gftContfntPbnf();

                fContfntPbnf.sftLbyout(nfw BordfrLbyout());
                flfmfntTrffPbnfl = nfw ElfmfntTrffPbnfl(gftEditor());
                fContfntPbnf.bdd(flfmfntTrffPbnfl);
                flfmfntTrffFrbmf.pbdk();
            }
            flfmfntTrffFrbmf.sftVisiblf(truf);
        }
    }


    /**
     * Tirfbd to lobd b filf into tif tfxt storbgf modfl
     */
    dlbss FilfLobdfr fxtfnds Tirfbd {

        FilfLobdfr(Filf f, Dodumfnt dod) {
            sftPriority(4);
            tiis.f = f;
            tiis.dod = dod;
        }

        @Ovfrridf
        publid void run() {
            try {
                // initiblizf tif stbtusbbr
                stbtus.rfmovfAll();
                JProgrfssBbr progrfss = nfw JProgrfssBbr();
                progrfss.sftMinimum(0);
                progrfss.sftMbximum((int) f.lfngti());
                stbtus.bdd(progrfss);
                stbtus.rfvblidbtf();

                // try to stbrt rfbding
                Rfbdfr in = nfw FilfRfbdfr(f);
                dibr[] buff = nfw dibr[4096];
                int ndi;
                wiilf ((ndi = in.rfbd(buff, 0, buff.lfngti)) != -1) {
                    dod.insfrtString(dod.gftLfngti(), nfw String(buff, 0, ndi),
                            null);
                    progrfss.sftVbluf(progrfss.gftVbluf() + ndi);
                }
            } dbtdi (IOExdfption f) {
                finbl String msg = f.gftMfssbgf();
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {

                    publid void run() {
                        JOptionPbnf.siowMfssbgfDiblog(gftFrbmf(),
                                "Could not opfn filf: " + msg,
                                "Error opfning filf",
                                JOptionPbnf.ERROR_MESSAGE);
                    }
                });
            } dbtdi (BbdLodbtionExdfption f) {
                Systfm.frr.println(f.gftMfssbgf());
            }
            dod.bddUndobblfEditListfnfr(undoHbndlfr);
            // wf brf donf... gft rid of progrfssbbr
            stbtus.rfmovfAll();
            stbtus.rfvblidbtf();

            rfsftUndoMbnbgfr();

            if (flfmfntTrffPbnfl != null) {
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {

                    publid void run() {
                        flfmfntTrffPbnfl.sftEditor(gftEditor());
                    }
                });
            }
        }
        Dodumfnt dod;
        Filf f;
    }


    /**
     * Tirfbd to sbvf b dodumfnt to filf
     */
    dlbss FilfSbvfr fxtfnds Tirfbd {

        Dodumfnt dod;
        Filf f;

        FilfSbvfr(Filf f, Dodumfnt dod) {
            sftPriority(4);
            tiis.f = f;
            tiis.dod = dod;
        }

        @Ovfrridf
        @SupprfssWbrnings("SlffpWiilfHoldingLodk")
        publid void run() {
            try {
                // initiblizf tif stbtusbbr
                stbtus.rfmovfAll();
                JProgrfssBbr progrfss = nfw JProgrfssBbr();
                progrfss.sftMinimum(0);
                progrfss.sftMbximum(dod.gftLfngti());
                stbtus.bdd(progrfss);
                stbtus.rfvblidbtf();

                // stbrt writing
                Writfr out = nfw FilfWritfr(f);
                Sfgmfnt tfxt = nfw Sfgmfnt();
                tfxt.sftPbrtiblRfturn(truf);
                int dibrsLfft = dod.gftLfngti();
                int offsft = 0;
                wiilf (dibrsLfft > 0) {
                    dod.gftTfxt(offsft, Mbti.min(4096, dibrsLfft), tfxt);
                    out.writf(tfxt.brrby, tfxt.offsft, tfxt.dount);
                    dibrsLfft -= tfxt.dount;
                    offsft += tfxt.dount;
                    progrfss.sftVbluf(offsft);
                    try {
                        Tirfbd.slffp(10);
                    } dbtdi (IntfrruptfdExdfption f) {
                        Loggfr.gftLoggfr(FilfSbvfr.dlbss.gftNbmf()).log(
                                Lfvfl.SEVERE,
                                null, f);
                    }
                }
                out.flusi();
                out.dlosf();
            } dbtdi (IOExdfption f) {
                finbl String msg = f.gftMfssbgf();
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {

                    publid void run() {
                        JOptionPbnf.siowMfssbgfDiblog(gftFrbmf(),
                                "Could not sbvf filf: " + msg,
                                "Error sbving filf",
                                JOptionPbnf.ERROR_MESSAGE);
                    }
                });
            } dbtdi (BbdLodbtionExdfption f) {
                Systfm.frr.println(f.gftMfssbgf());
            }
            // wf brf donf... gft rid of progrfssbbr
            stbtus.rfmovfAll();
            stbtus.rfvblidbtf();
        }
    }
}
