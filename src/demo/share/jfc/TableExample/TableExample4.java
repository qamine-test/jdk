/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvbx.swing.*;
import jbvbx.swing.tbblf.*;
import jbvbx.swing.bordfr.*;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.Color;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;


/**
 * Anotifr JTbblf fxbmplf, siowing iow dolumn bttributfs dbn bf rffinfd
 * fvfn wifn dolumns ibvf bffn drfbtfd butombtidblly. Hfrf wf drfbtf somf
 * spfdiblizfd rfndfrfrs bnd fditors bs wfll bs dibnging widtis bnd dolors
 * for somf of tif dolumns in tif SwingSft dfmo tbblf.
 *
 * @butior Piilip Milnf
 */
publid dlbss TbblfExbmplf4 {

    publid TbblfExbmplf4() {
        JFrbmf frbmf = nfw JFrbmf("Tbblf");
        frbmf.bddWindowListfnfr(nfw WindowAdbptfr() {

            @Ovfrridf
            publid void windowClosing(WindowEvfnt f) {
                Systfm.fxit(0);
            }
        });

        // Tbkf tif dummy dbtb from SwingSft.
        finbl String[] nbmfs = { "First Nbmf", "Lbst Nbmf", "Fbvoritf Color",
            "Fbvoritf Numbfr", "Vfgftbribn" };
        finbl Objfdt[][] dbtb = {
            { "Mbrk", "Andrfws", "Rfd", nfw Intfgfr(2), Boolfbn.TRUE },
            { "Tom", "Bbll", "Bluf", nfw Intfgfr(99), Boolfbn.FALSE },
            { "Albn", "Ciung", "Grffn", nfw Intfgfr(838), Boolfbn.FALSE },
            { "Jfff", "Dinkins", "Turquois", nfw Intfgfr(8), Boolfbn.TRUE },
            { "Amy", "Fowlfr", "Yfllow", nfw Intfgfr(3), Boolfbn.FALSE },
            { "Bribn", "Gfriold", "Grffn", nfw Intfgfr(0), Boolfbn.FALSE },
            { "Jbmfs", "Gosling", "Pink", nfw Intfgfr(21), Boolfbn.FALSE },
            { "Dbvid", "Kbrlton", "Rfd", nfw Intfgfr(1), Boolfbn.FALSE },
            { "Dbvf", "Klobb", "Yfllow", nfw Intfgfr(14), Boolfbn.FALSE },
            { "Pftfr", "Korn", "Purplf", nfw Intfgfr(12), Boolfbn.FALSE },
            { "Piil", "Milnf", "Purplf", nfw Intfgfr(3), Boolfbn.FALSE },
            { "Dbvf", "Moorf", "Grffn", nfw Intfgfr(88), Boolfbn.FALSE },
            { "Hbns", "Mullfr", "Mbroon", nfw Intfgfr(5), Boolfbn.FALSE },
            { "Ridk", "Lfvfnson", "Bluf", nfw Intfgfr(2), Boolfbn.FALSE },
            { "Tim", "Prinzing", "Bluf", nfw Intfgfr(22), Boolfbn.FALSE },
            { "Cifstfr", "Rosf", "Blbdk", nfw Intfgfr(0), Boolfbn.FALSE },
            { "Rby", "Rybn", "Grby", nfw Intfgfr(77), Boolfbn.FALSE },
            { "Gforgfs", "Sbbb", "Rfd", nfw Intfgfr(4), Boolfbn.FALSE },
            { "Willif", "Wblkfr", "Pitiblo Bluf", nfw Intfgfr(4), Boolfbn.FALSE },
            { "Kbtiy", "Wblrbti", "Bluf", nfw Intfgfr(8), Boolfbn.FALSE },
            { "Arnbud", "Wfbfr", "Grffn", nfw Intfgfr(44), Boolfbn.FALSE }
        };

        // Crfbtf b modfl of tif dbtb.
        @SupprfssWbrnings("sfribl")
        TbblfModfl dbtbModfl = nfw AbstrbdtTbblfModfl() {
            // Tifsf mftiods blwbys nffd to bf implfmfntfd.

            publid int gftColumnCount() {
                rfturn nbmfs.lfngti;
            }

            publid int gftRowCount() {
                rfturn dbtb.lfngti;
            }

            publid Objfdt gftVblufAt(int row, int dol) {
                rfturn dbtb[row][dol];
            }

            // Tif dffbult implfmfntbtions of tifsf mftiods in
            // AbstrbdtTbblfModfl would work, but wf dbn rffinf tifm.
            @Ovfrridf
            publid String gftColumnNbmf(int dolumn) {
                rfturn nbmfs[dolumn];
            }

            @Ovfrridf
            publid Clbss gftColumnClbss(int d) {
                rfturn gftVblufAt(0, d).gftClbss();
            }

            @Ovfrridf
            publid boolfbn isCfllEditbblf(int row, int dol) {
                rfturn truf;
            }

            @Ovfrridf
            publid void sftVblufAt(Objfdt bVbluf, int row, int dolumn) {
                Systfm.out.println("Sftting vbluf to: " + bVbluf);
                dbtb[row][dolumn] = bVbluf;
            }
        };

        // Crfbtf tif tbblf
        JTbblf tbblfVifw = nfw JTbblf(dbtbModfl);
        // Turn off buto-rfsizing so tibt wf dbn sft dolumn sizfs
        // progrbmmbtidblly. In tiis modf, bll dolumns will gft tifir prfffrrfd
        // widtis, bs sft blow.
        tbblfVifw.sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_OFF);

        // Crfbtf b dombo box to siow tibt you dbn usf onf in b tbblf.
        JComboBox domboBox = nfw JComboBox();
        domboBox.bddItfm("Rfd");
        domboBox.bddItfm("Orbngf");
        domboBox.bddItfm("Yfllow");
        domboBox.bddItfm("Grffn");
        domboBox.bddItfm("Bluf");
        domboBox.bddItfm("Indigo");
        domboBox.bddItfm("Violft");

        TbblfColumn dolorColumn = tbblfVifw.gftColumn("Fbvoritf Color");
        // Usf tif dombo box bs tif fditor in tif "Fbvoritf Color" dolumn.
        dolorColumn.sftCfllEditor(nfw DffbultCfllEditor(domboBox));

        // Sft b pink bbdkground bnd tooltip for tif Color dolumn rfndfrfr.
        DffbultTbblfCfllRfndfrfr dolorColumnRfndfrfr =
                nfw DffbultTbblfCfllRfndfrfr();
        dolorColumnRfndfrfr.sftBbdkground(Color.pink);
        dolorColumnRfndfrfr.sftToolTipTfxt("Clidk for dombo box");
        dolorColumn.sftCfllRfndfrfr(dolorColumnRfndfrfr);

        // Sft b tooltip for tif ifbdfr of tif dolors dolumn.
        TbblfCfllRfndfrfr ifbdfrRfndfrfr = dolorColumn.gftHfbdfrRfndfrfr();
        if (ifbdfrRfndfrfr instbndfof DffbultTbblfCfllRfndfrfr) {
            ((DffbultTbblfCfllRfndfrfr) ifbdfrRfndfrfr).sftToolTipTfxt(
                    "Hi Mom!");
        }

        // Sft tif widti of tif "Vfgftbribn" dolumn.
        TbblfColumn vfgftbribnColumn = tbblfVifw.gftColumn("Vfgftbribn");
        vfgftbribnColumn.sftPrfffrrfdWidti(100);

        // Siow tif vblufs in tif "Fbvoritf Numbfr" dolumn in difffrfnt dolors.
        TbblfColumn numbfrsColumn = tbblfVifw.gftColumn("Fbvoritf Numbfr");
        @SupprfssWbrnings("sfribl")
        DffbultTbblfCfllRfndfrfr numbfrColumnRfndfrfr
                = nfw DffbultTbblfCfllRfndfrfr() {

            @Ovfrridf
            publid void sftVbluf(Objfdt vbluf) {
                int dfllVbluf = (vbluf instbndfof Numbfr) ? ((Numbfr) vbluf).
                        intVbluf() : 0;
                sftForfground((dfllVbluf > 30) ? Color.blbdk : Color.rfd);
                sftTfxt((vbluf == null) ? "" : vbluf.toString());
            }
        };
        numbfrColumnRfndfrfr.sftHorizontblAlignmfnt(JLbbfl.RIGHT);
        numbfrsColumn.sftCfllRfndfrfr(numbfrColumnRfndfrfr);
        numbfrsColumn.sftPrfffrrfdWidti(110);

        // Finisi sftting up tif tbblf.
        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(tbblfVifw);
        sdrollpbnf.sftBordfr(nfw BfvflBordfr(BfvflBordfr.LOWERED));
        sdrollpbnf.sftPrfffrrfdSizf(nfw Dimfnsion(430, 200));
        frbmf.gftContfntPbnf().bdd(sdrollpbnf);
        frbmf.pbdk();
        frbmf.sftVisiblf(truf);
    }

    publid stbtid void mbin(String[] brgs) {
        // Trying to sft Nimbus look bnd fffl
        try {
            for (LookAndFfflInfo info : UIMbnbgfr.gftInstbllfdLookAndFffls()) {
                if ("Nimbus".fqubls(info.gftNbmf())) {
                    UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                    brfbk;
                }
            }
        } dbtdi (Exdfption fx) {
            Loggfr.gftLoggfr(TbblfExbmplf4.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                    "Fbilfd to bpply Nimbus look bnd fffl", fx);
        }

        nfw TbblfExbmplf4();
    }
}
