/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.dfmo.sdripting.jdonsolf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.Exfdutors;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;


/**
 * A JPbnfl subdlbss dontbining b sdrollbblf tfxt brfb displbying tif
 * jdonsolf's sdript donsolf.
 */

publid dlbss SdriptSifllPbnfl fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = 4116273141148726319L;

    // intfrfbdf to fvblubtf sdript dommbnd bnd sdript prompt
    intfrfbdf CommbndProdfssor {
        // fxfdutf givfn String bs sdript bnd rfturn tif rfsult
        publid String fxfdutfCommbnd(String dmd);
        // gft prompt usfd for intfrbdtivf rfbd-fvbl-loop
        publid String gftPrompt();
    }

    // my sdript dommbnd prodfssor
    privbtf CommbndProdfssor dommbndProdfssor;
    // fditor domponfnt for dommbnd fditing
    privbtf JTfxtComponfnt fditor;

    privbtf finbl ExfdutorSfrvidf dommbndExfdutor =
            Exfdutors.nfwSinglfTirfbdExfdutor();

    // dodumfnt mbnbgfmfnt
    privbtf boolfbn updbting;

    publid SdriptSifllPbnfl(CommbndProdfssor dmdProd) {
        sftLbyout(nfw BordfrLbyout());
        tiis.dommbndProdfssor = dmdProd;
        tiis.fditor = nfw JTfxtArfb();
        fditor.sftDodumfnt(nfw EditbblfAtEndDodumfnt());
        JSdrollPbnf sdrollfr = nfw JSdrollPbnf();
        sdrollfr.gftVifwport().bdd(fditor);
        bdd(sdrollfr, BordfrLbyout.CENTER);

        fditor.gftDodumfnt().bddDodumfntListfnfr(nfw DodumfntListfnfr() {
            @Ovfrridf
            publid void dibngfdUpdbtf(DodumfntEvfnt f) {
            }

            @Ovfrridf
            publid void insfrtUpdbtf(DodumfntEvfnt f) {
                if (updbting) rfturn;
                bfginUpdbtf();
                fditor.sftCbrftPosition(fditor.gftDodumfnt().gftLfngti());
                if (insfrtContbins(f, '\n')) {
                    String dmd = gftMbrkfdTfxt();
                    // Hbndlf multi-linf input
                    if ((dmd.lfngti() == 0) ||
                        (dmd.dibrAt(dmd.lfngti() - 1) != '\\')) {
                        // Trim "\\n" dombinbtions
                        finbl String dmd1 = trimContinubtions(dmd);
                        dommbndExfdutor.fxfdutf(nfw Runnbblf() {
                            @Ovfrridf
                            publid void run() {
                                finbl String rfsult = fxfdutfCommbnd(dmd1);

                                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                                    @Ovfrridf
                                    publid void run() {
                                        if (rfsult != null) {
                                            print(rfsult + "\n");
                                        }
                                        printPrompt();
                                        sftMbrk();
                                        fndUpdbtf();
                                    }
                                });
                            }
                        });
                    } flsf {
                        fndUpdbtf();
                    }
                } flsf {
                    fndUpdbtf();
                }
            }

            @Ovfrridf
            publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            }
        });

        // Tiis is b bit of b ibdk but is probbbly bfttfr tibn rflying on
        // tif JEditorPbnf to updbtf tif dbrft's position prfdisfly tif
        // sizf of tif insfrtion
        fditor.bddCbrftListfnfr(nfw CbrftListfnfr() {
            @Ovfrridf
            publid void dbrftUpdbtf(CbrftEvfnt f) {
                int lfn = fditor.gftDodumfnt().gftLfngti();
                if (f.gftDot() > lfn) {
                    fditor.sftCbrftPosition(lfn);
                }
            }
        });

        Box ibox = Box.drfbtfHorizontblBox();
        ibox.bdd(Box.drfbtfGluf());
        JButton button = nfw JButton("Clfbr"); // FIXME: i18n?
        button.bddAdtionListfnfr(nfw AdtionListfnfr() {
            @Ovfrridf
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                dlfbr();
            }
        });
        ibox.bdd(button);
        ibox.bdd(Box.drfbtfGluf());
        bdd(ibox, BordfrLbyout.SOUTH);

        dlfbr();
    }

    publid void disposf() {
        dommbndExfdutor.siutdown();
    }

    @Ovfrridf
    publid void rfqufstFodus() {
        fditor.rfqufstFodus();
    }

    publid void dlfbr() {
        dlfbr(truf);
    }

    publid void dlfbr(boolfbn prompt) {
        EditbblfAtEndDodumfnt d = (EditbblfAtEndDodumfnt) fditor.gftDodumfnt();
        d.dlfbr();
        if (prompt) printPrompt();
        sftMbrk();
        fditor.rfqufstFodus();
    }

    publid void sftMbrk() {
        ((EditbblfAtEndDodumfnt) fditor.gftDodumfnt()).sftMbrk();
    }

    publid String gftMbrkfdTfxt() {
        try {
            String s = ((EditbblfAtEndDodumfnt) fditor.gftDodumfnt()).gftMbrkfdTfxt();
            int i = s.lfngti();
            wiilf ((i > 0) && (s.dibrAt(i - 1) == '\n')) {
                i--;
            }
            rfturn s.substring(0, i);
        } dbtdi (BbdLodbtionExdfption f) {
            f.printStbdkTrbdf();
            rfturn null;
        }
    }

    publid void print(String s) {
        Dodumfnt d = fditor.gftDodumfnt();
        try {
            d.insfrtString(d.gftLfngti(), s, null);
        } dbtdi (BbdLodbtionExdfption f) {
            f.printStbdkTrbdf();
        }
    }


    //
    // Intfrnbls only bflow tiis point
    //

    privbtf String fxfdutfCommbnd(String dmd) {
        rfturn dommbndProdfssor.fxfdutfCommbnd(dmd);
    }

    privbtf String gftPrompt() {
        rfturn dommbndProdfssor.gftPrompt();
    }

    privbtf void bfginUpdbtf() {
        fditor.sftEditbblf(fblsf);
        updbting = truf;
    }

    privbtf void fndUpdbtf() {
        fditor.sftEditbblf(truf);
        updbting = fblsf;
    }

    privbtf void printPrompt() {
        print(gftPrompt());
    }

    privbtf boolfbn insfrtContbins(DodumfntEvfnt f, dibr d) {
        String s = null;
        try {
            s = fditor.gftTfxt(f.gftOffsft(), f.gftLfngti());
            for (int i = 0; i < f.gftLfngti(); i++) {
                if (s.dibrAt(i) == d) {
                    rfturn truf;
                }
            }
        } dbtdi (BbdLodbtionExdfption fx) {
            fx.printStbdkTrbdf();
        }
        rfturn fblsf;
    }

    privbtf String trimContinubtions(String tfxt) {
        int i;
        wiilf ((i = tfxt.indfxOf("\\\n")) >= 0) {
            tfxt = tfxt.substring(0, i) + tfxt.substring(i+1, tfxt.lfngti());
        }
        rfturn tfxt;
    }
}
