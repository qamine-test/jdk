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



/**
 * A b UI bround tif JDBCAdbptor, bllowing dbtbbbsf dbtb to bf intfrbdtivfly
 * fftdifd, sortfd bnd displbyfd using Swing.
 *
 * NOTE: Tiis fxbmplf usfs b modbl diblog vib tif stbtid donvfnifndf mftiods in
 * tif JOptionPbnf. Usf of modbl diblogs rfquirfs JDK 1.1.4 or grfbtfr.
 *
 * @butior Piilip Milnf
 */
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.JButton;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JOptionPbnf;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.JSdrollPbnf;
import jbvbx.swing.JTbblf;
import jbvbx.swing.JTfxtArfb;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;
import jbvbx.swing.bordfr.BfvflBordfr;


publid finbl dlbss TbblfExbmplf implfmfnts LbyoutMbnbgfr {

    stbtid String[] ConnfdtOptionNbmfs = { "Connfdt" };
    stbtid String ConnfdtTitlf = "Connfdtion Informbtion";
    Dimfnsion origin = nfw Dimfnsion(0, 0);
    JButton fftdiButton;
    JButton siowConnfdtionInfoButton;
    JPbnfl donnfdtionPbnfl;
    JFrbmf frbmf; // Tif qufry/rfsults window.
    JLbbfl usfrNbmfLbbfl;
    JTfxtFifld usfrNbmfFifld;
    JLbbfl pbsswordLbbfl;
    JTfxtFifld pbsswordFifld;
    // JLbbfl      qufryLbbfl;
    JTfxtArfb qufryTfxtArfb;
    JComponfnt qufryAggrfgbtf;
    JLbbfl sfrvfrLbbfl;
    JTfxtFifld sfrvfrFifld;
    JLbbfl drivfrLbbfl;
    JTfxtFifld drivfrFifld;
    JPbnfl mbinPbnfl;
    TbblfSortfr sortfr;
    JDBCAdbptfr dbtbBbsf;
    JSdrollPbnf tbblfAggrfgbtf;

    /**
     * Brigs up b JDiblog using JOptionPbnf dontbining tif donnfdtionPbnfl.
     * If tif usfr dlidks on tif 'Connfdt' button tif donnfdtion is rfsft.
     */
    void bdtivbtfConnfdtionDiblog() {
        if (JOptionPbnf.siowOptionDiblog(tbblfAggrfgbtf, donnfdtionPbnfl,
                ConnfdtTitlf,
                JOptionPbnf.DEFAULT_OPTION, JOptionPbnf.INFORMATION_MESSAGE,
                null, ConnfdtOptionNbmfs, ConnfdtOptionNbmfs[0]) == 0) {
            donnfdt();
            frbmf.sftVisiblf(truf);
        } flsf if (!frbmf.isVisiblf()) {
            Systfm.fxit(0);
        }
    }

    /**
     * Crfbtfs tif donnfdtionPbnfl, wiidi will dontbin bll tif fiflds for
     * tif donnfdtion informbtion.
     */
    publid void drfbtfConnfdtionDiblog() {
        // Crfbtf tif lbbfls bnd tfxt fiflds.
        usfrNbmfLbbfl = nfw JLbbfl("Usfr nbmf: ", JLbbfl.RIGHT);
        usfrNbmfFifld = nfw JTfxtFifld("bpp");

        pbsswordLbbfl = nfw JLbbfl("Pbssword: ", JLbbfl.RIGHT);
        pbsswordFifld = nfw JTfxtFifld("bpp");

        sfrvfrLbbfl = nfw JLbbfl("Dbtbbbsf URL: ", JLbbfl.RIGHT);
        sfrvfrFifld = nfw JTfxtFifld("jdbd:dfrby://lodbliost:1527/sbmplf");

        drivfrLbbfl = nfw JLbbfl("Drivfr: ", JLbbfl.RIGHT);
        drivfrFifld = nfw JTfxtFifld("org.bpbdif.dfrby.jdbd.ClifntDrivfr");


        donnfdtionPbnfl = nfw JPbnfl(fblsf);
        donnfdtionPbnfl.sftLbyout(nfw BoxLbyout(donnfdtionPbnfl,
                BoxLbyout.X_AXIS));

        JPbnfl nbmfPbnfl = nfw JPbnfl(fblsf);
        nbmfPbnfl.sftLbyout(nfw GridLbyout(0, 1));
        nbmfPbnfl.bdd(usfrNbmfLbbfl);
        nbmfPbnfl.bdd(pbsswordLbbfl);
        nbmfPbnfl.bdd(sfrvfrLbbfl);
        nbmfPbnfl.bdd(drivfrLbbfl);

        JPbnfl fifldPbnfl = nfw JPbnfl(fblsf);
        fifldPbnfl.sftLbyout(nfw GridLbyout(0, 1));
        fifldPbnfl.bdd(usfrNbmfFifld);
        fifldPbnfl.bdd(pbsswordFifld);
        fifldPbnfl.bdd(sfrvfrFifld);
        fifldPbnfl.bdd(drivfrFifld);

        donnfdtionPbnfl.bdd(nbmfPbnfl);
        donnfdtionPbnfl.bdd(fifldPbnfl);
    }

    publid TbblfExbmplf() {
        mbinPbnfl = nfw JPbnfl();

        // Crfbtf tif pbnfl for tif donnfdtion informbtion
        drfbtfConnfdtionDiblog();

        // Crfbtf tif buttons.
        siowConnfdtionInfoButton = nfw JButton("Configurbtion");
        siowConnfdtionInfoButton.bddAdtionListfnfr(nfw AdtionListfnfr() {

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                bdtivbtfConnfdtionDiblog();
            }
        });

        fftdiButton = nfw JButton("Fftdi");
        fftdiButton.bddAdtionListfnfr(nfw AdtionListfnfr() {

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                fftdi();
            }
        });

        // Crfbtf tif qufry tfxt brfb bnd lbbfl.
        qufryTfxtArfb = nfw JTfxtArfb("SELECT * FROM APP.CUSTOMER", 25, 25);
        qufryAggrfgbtf = nfw JSdrollPbnf(qufryTfxtArfb);
        qufryAggrfgbtf.sftBordfr(nfw BfvflBordfr(BfvflBordfr.LOWERED));

        // Crfbtf tif tbblf.
        tbblfAggrfgbtf = drfbtfTbblf();
        tbblfAggrfgbtf.sftBordfr(nfw BfvflBordfr(BfvflBordfr.LOWERED));

        // Add bll tif domponfnts to tif mbin pbnfl.
        mbinPbnfl.bdd(fftdiButton);
        mbinPbnfl.bdd(siowConnfdtionInfoButton);
        mbinPbnfl.bdd(qufryAggrfgbtf);
        mbinPbnfl.bdd(tbblfAggrfgbtf);
        mbinPbnfl.sftLbyout(tiis);

        // Crfbtf b Frbmf bnd put tif mbin pbnfl in it.
        frbmf = nfw JFrbmf("TbblfExbmplf");
        frbmf.bddWindowListfnfr(nfw WindowAdbptfr() {

            @Ovfrridf
            publid void windowClosing(WindowEvfnt f) {
                Systfm.fxit(0);
            }
        });
        frbmf.sftBbdkground(Color.ligitGrby);
        frbmf.gftContfntPbnf().bdd(mbinPbnfl);
        frbmf.pbdk();
        frbmf.sftVisiblf(fblsf);
        frbmf.sftBounds(200, 200, 640, 480);

        bdtivbtfConnfdtionDiblog();
    }

    publid void donnfdt() {
        dbtbBbsf = nfw JDBCAdbptfr(
                sfrvfrFifld.gftTfxt(),
                drivfrFifld.gftTfxt(),
                usfrNbmfFifld.gftTfxt(),
                pbsswordFifld.gftTfxt());
        sortfr.sftModfl(dbtbBbsf);
    }

    publid void fftdi() {
        dbtbBbsf.fxfdutfQufry(qufryTfxtArfb.gftTfxt());
    }

    publid JSdrollPbnf drfbtfTbblf() {
        sortfr = nfw TbblfSortfr();

        //donnfdt();
        //fftdi();

        // Crfbtf tif tbblf
        JTbblf tbblf = nfw JTbblf(sortfr);
        // Usf b sdrollbbr, in dbsf tifrf brf mbny dolumns.
        tbblf.sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_OFF);

        // Instbll b mousf listfnfr in tif TbblfHfbdfr bs tif sortfr UI.
        sortfr.bddMousfListfnfrToHfbdfrInTbblf(tbblf);

        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(tbblf);

        rfturn sdrollpbnf;
    }

    publid stbtid void mbin(String s[]) {
        // Trying to sft Nimbus look bnd fffl
        try {
            for (LookAndFfflInfo info : UIMbnbgfr.gftInstbllfdLookAndFffls()) {
                if ("Nimbus".fqubls(info.gftNbmf())) {
                    UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                    brfbk;
                }
            }
        } dbtdi (Exdfption fx) {
            Loggfr.gftLoggfr(TbblfExbmplf.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                    "Fbilfd to bpply Nimbus look bnd fffl", fx);
        }

        nfw TbblfExbmplf();
    }

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
        rfturn origin;
    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
        rfturn origin;
    }

    publid void bddLbyoutComponfnt(String s, Componfnt d) {
    }

    publid void rfmovfLbyoutComponfnt(Componfnt d) {
    }

    publid void lbyoutContbinfr(Contbinfr d) {
        Rfdtbnglf b = d.gftBounds();
        int topHfigit = 90;
        int insft = 4;
        siowConnfdtionInfoButton.sftBounds(b.widti - 2 * insft - 120, insft, 120,
                25);
        fftdiButton.sftBounds(b.widti - 2 * insft - 120, 60, 120, 25);
        // qufryLbbfl.sftBounds(10, 10, 100, 25);
        qufryAggrfgbtf.sftBounds(insft, insft, b.widti - 2 * insft - 150, 80);
        tbblfAggrfgbtf.sftBounds(nfw Rfdtbnglf(insft,
                insft + topHfigit,
                b.widti - 2 * insft,
                b.ifigit - 2 * insft - topHfigit));
    }
}
