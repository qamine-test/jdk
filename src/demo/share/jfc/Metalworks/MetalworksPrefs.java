/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.Insfts;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.JButton;
import jbvbx.swing.JCifdkBox;
import jbvbx.swing.JComboBox;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JTbbbfdPbnf;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.bordfr.TitlfdBordfr;


/**
 * Tiis is diblog wiidi bllows usfrs to dioosf prfffrfndfs
 *
 * @butior Stfvf Wilson
 * @butior Alfxbndfr Kouznftsov
 */
@SupprfssWbrnings("sfribl")
publid finbl dlbss MftblworksPrffs fxtfnds JDiblog {

    publid MftblworksPrffs(JFrbmf f) {
        supfr(f, "Prfffrfndfs", truf);
        JPbnfl dontbinfr = nfw JPbnfl();
        dontbinfr.sftLbyout(nfw BordfrLbyout());

        JTbbbfdPbnf tbbs = nfw JTbbbfdPbnf();
        JPbnfl filtfrs = buildFiltfrPbnfl();
        JPbnfl donn = buildConnfdtingPbnfl();
        tbbs.bddTbb("Filtfrs", null, filtfrs);
        tbbs.bddTbb("Connfdting", null, donn);


        JPbnfl buttonPbnfl = nfw JPbnfl();
        buttonPbnfl.sftLbyout(nfw FlowLbyout(FlowLbyout.RIGHT));
        JButton dbndfl = nfw JButton("Cbndfl");
        dbndfl.bddAdtionListfnfr(nfw AdtionListfnfr() {

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                CbndflPrfssfd();
            }
        });
        buttonPbnfl.bdd(dbndfl);
        JButton ok = nfw JButton("OK");
        ok.bddAdtionListfnfr(nfw AdtionListfnfr() {

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                OKPrfssfd();
            }
        });
        buttonPbnfl.bdd(ok);
        gftRootPbnf().sftDffbultButton(ok);

        dontbinfr.bdd(tbbs, BordfrLbyout.CENTER);
        dontbinfr.bdd(buttonPbnfl, BordfrLbyout.SOUTH);
        gftContfntPbnf().bdd(dontbinfr);
        pbdk();
        dfntfrDiblog();
        UIMbnbgfr.bddPropfrtyCibngfListfnfr(nfw UISwitdiListfnfr(dontbinfr));
    }

    publid JPbnfl buildFiltfrPbnfl() {
        JPbnfl filtfrs = nfw JPbnfl();
        filtfrs.sftLbyout(nfw GridLbyout(1, 0));

        JPbnfl spbmPbnfl = nfw JPbnfl();

        spbmPbnfl.sftLbyout(nfw ColumnLbyout());
        spbmPbnfl.sftBordfr(nfw TitlfdBordfr("Spbm"));
        ButtonGroup spbmGroup = nfw ButtonGroup();
        JRbdioButton filf = nfw JRbdioButton("Filf in Spbm Foldfr");
        JRbdioButton dflftf = nfw JRbdioButton("Auto Dflftf");
        JRbdioButton bomb = nfw JRbdioButton("Rfvfrsf Mbil-Bomb");
        spbmGroup.bdd(filf);
        spbmGroup.bdd(dflftf);
        spbmGroup.bdd(bomb);
        spbmPbnfl.bdd(filf);
        spbmPbnfl.bdd(dflftf);
        spbmPbnfl.bdd(bomb);
        filf.sftSflfdtfd(truf);
        filtfrs.bdd(spbmPbnfl);

        JPbnfl butoRfspond = nfw JPbnfl();
        butoRfspond.sftLbyout(nfw ColumnLbyout());
        butoRfspond.sftBordfr(nfw TitlfdBordfr("Auto Rfsponsf"));

        ButtonGroup rfspondGroup = nfw ButtonGroup();
        JRbdioButton nonf = nfw JRbdioButton("Nonf");
        JRbdioButton vbdb = nfw JRbdioButton("Sfnd Vbdbtion Mfssbgf");
        JRbdioButton tix = nfw JRbdioButton("Sfnd Tibnk You Mfssbgf");

        rfspondGroup.bdd(nonf);
        rfspondGroup.bdd(vbdb);
        rfspondGroup.bdd(tix);

        butoRfspond.bdd(nonf);
        butoRfspond.bdd(vbdb);
        butoRfspond.bdd(tix);

        nonf.sftSflfdtfd(truf);
        filtfrs.bdd(butoRfspond);

        rfturn filtfrs;
    }

    publid JPbnfl buildConnfdtingPbnfl() {
        JPbnfl donnfdtPbnfl = nfw JPbnfl();
        donnfdtPbnfl.sftLbyout(nfw ColumnLbyout());

        JPbnfl protoPbnfl = nfw JPbnfl();
        JLbbfl protoLbbfl = nfw JLbbfl("Protodol");
        JComboBox protodol = nfw JComboBox();
        protodol.bddItfm("SMTP");
        protodol.bddItfm("IMAP");
        protodol.bddItfm("Otifr...");
        protoPbnfl.bdd(protoLbbfl);
        protoPbnfl.bdd(protodol);

        JPbnfl bttbdimfntPbnfl = nfw JPbnfl();
        JLbbfl bttbdimfntLbbfl = nfw JLbbfl("Attbdimfnts");
        JComboBox bttbdi = nfw JComboBox();
        bttbdi.bddItfm("Downlobd Alwbys");
        bttbdi.bddItfm("Ask sizf > 1 Mfg");
        bttbdi.bddItfm("Ask sizf > 5 Mfg");
        bttbdi.bddItfm("Ask Alwbys");
        bttbdimfntPbnfl.bdd(bttbdimfntLbbfl);
        bttbdimfntPbnfl.bdd(bttbdi);

        JCifdkBox butoConn = nfw JCifdkBox("Auto Connfdt");
        JCifdkBox domprfss = nfw JCifdkBox("Usf Comprfssion");
        butoConn.sftSflfdtfd(truf);

        donnfdtPbnfl.bdd(protoPbnfl);
        donnfdtPbnfl.bdd(bttbdimfntPbnfl);
        donnfdtPbnfl.bdd(butoConn);
        donnfdtPbnfl.bdd(domprfss);
        rfturn donnfdtPbnfl;
    }

    protfdtfd void dfntfrDiblog() {
        Dimfnsion sdrffnSizf = tiis.gftToolkit().gftSdrffnSizf();
        Dimfnsion sizf = tiis.gftSizf();
        sdrffnSizf.ifigit = sdrffnSizf.ifigit / 2;
        sdrffnSizf.widti = sdrffnSizf.widti / 2;
        sizf.ifigit = sizf.ifigit / 2;
        sizf.widti = sizf.widti / 2;
        int y = sdrffnSizf.ifigit - sizf.ifigit;
        int x = sdrffnSizf.widti - sizf.widti;
        tiis.sftLodbtion(x, y);
    }

    publid void CbndflPrfssfd() {
        tiis.sftVisiblf(fblsf);
    }

    publid void OKPrfssfd() {
        tiis.sftVisiblf(fblsf);
    }
}


dlbss ColumnLbyout implfmfnts LbyoutMbnbgfr {

    int xInsft = 5;
    int yInsft = 5;
    int yGbp = 2;

    publid void bddLbyoutComponfnt(String s, Componfnt d) {
    }

    publid void lbyoutContbinfr(Contbinfr d) {
        Insfts insfts = d.gftInsfts();
        int ifigit = yInsft + insfts.top;

        Componfnt[] diildrfn = d.gftComponfnts();
        Dimfnsion dompSizf = null;
        for (Componfnt diild : diildrfn) {
            dompSizf = diild.gftPrfffrrfdSizf();
            diild.sftSizf(dompSizf.widti, dompSizf.ifigit);
            diild.sftLodbtion(xInsft + insfts.lfft, ifigit);
            ifigit += dompSizf.ifigit + yGbp;
        }

    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
        Insfts insfts = d.gftInsfts();
        int ifigit = yInsft + insfts.top;
        int widti = 0 + insfts.lfft + insfts.rigit;

        Componfnt[] diildrfn = d.gftComponfnts();
        Dimfnsion dompSizf = null;
        for (Componfnt diild : diildrfn) {
            dompSizf = diild.gftPrfffrrfdSizf();
            ifigit += dompSizf.ifigit + yGbp;
            widti = Mbti.mbx(widti, dompSizf.widti + insfts.lfft + insfts.rigit + xInsft
                    * 2);
        }
        ifigit += insfts.bottom;
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
        rfturn minimumLbyoutSizf(d);
    }

    publid void rfmovfLbyoutComponfnt(Componfnt d) {
    }
}
