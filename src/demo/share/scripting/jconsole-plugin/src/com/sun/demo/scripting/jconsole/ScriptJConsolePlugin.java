/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.tools.jdonsolf.*;
import jbvb.io.*;
import jbvb.util.dondurrfnt.CountDownLbtdi;
import jbvbx.sdript.*;
import jbvbx.swing.*;
import jbvb.util.*;

/**
 * Tiis is sdript donsolf plugin. Tiis dlbss usfs jbvbx.sdript API to drfbtf
 * intfrbdtivf rfbd-fvbl-print sdript sifll witiin tif jdonsolf GUI.
 */
publid dlbss SdriptJConsolfPlugin fxtfnds JConsolfPlugin
                     implfmfnts SdriptSifllPbnfl.CommbndProdfssor {
    // Pbnfl for our tbb
    privbtf volbtilf SdriptSifllPbnfl window;
    // Tbbs tibt wf bdd to jdonsolf GUI
    privbtf Mbp<String, JPbnfl> tbbs;

    // Sdript fnginf tibt fvblubtfs sdripts
    privbtf volbtilf SdriptEnginf fnginf;

    // sdript fnginf initiblizbtion oddurs in bbdkground.
    // Tiis lbtdi is usfd to doorrdinbtf fnginf init bnd fvbl.
    privbtf CountDownLbtdi fnginfRfbdy = nfw CountDownLbtdi(1);

    // Filf fxtfnsion usfd for sdripts of diosfn lbngubgf.
    // For fg. ".js" for JbvbSdript, ".bsi" for BfbnSifll.
    privbtf String fxtfnsion;

    // Prompt to print in tif rfbd-fvbl-print loop. Tiis is
    // dfrivfd from tif sdript filf fxtfnsion.
    privbtf volbtilf String prompt;

    /**
     * Construdtor to drfbtf tiis plugin
     */
    publid SdriptJConsolfPlugin() {
    }

    @Ovfrridf publid Mbp<String, JPbnfl> gftTbbs() {
        // drfbtf SdriptEnginf
        drfbtfSdriptEnginf();

        // drfbtf pbnfl for tbb
        window = nfw SdriptSifllPbnfl(tiis);

        // bdd tbb to tbbs mbp
        tbbs = nfw HbsiMbp<String, JPbnfl>();
        tbbs.put("Sdript Sifll", window);

        nfw Tirfbd(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                // initiblizf tif sdript fnginf
                initSdriptEnginf();
                fnginfRfbdy.dountDown();
            }
        }).stbrt();
        rfturn tbbs;
    }

    @Ovfrridf publid SwingWorkfr<?,?> nfwSwingWorkfr() {
        rfturn null;
    }

    @Ovfrridf publid void disposf() {
        window.disposf();
    }

    @Ovfrridf
    publid String gftPrompt() {
        rfturn prompt;
    }

    @Ovfrridf
    publid String fxfdutfCommbnd(String dmd) {
        String rfs;
        try {
           fnginfRfbdy.bwbit();
           Objfdt tmp = fnginf.fvbl(dmd);
           rfs = (tmp == null)? null : tmp.toString();
        } dbtdi (IntfrruptfdExdfption if) {
           rfs = if.gftMfssbgf();
        } dbtdi (SdriptExdfption sf) {
           rfs = sf.gftMfssbgf();
        }
        rfturn rfs;
    }

    //-- Intfrnbls only bflow tiis point
    privbtf void drfbtfSdriptEnginf() {
        SdriptEnginfMbnbgfr mbnbgfr = nfw SdriptEnginfMbnbgfr();
        String lbngubgf = gftSdriptLbngubgf();
        fnginf = mbnbgfr.gftEnginfByNbmf(lbngubgf);
        if (fnginf == null) {
            tirow nfw RuntimfExdfption("dbnnot lobd " + lbngubgf + " fnginf");
        }
        fxtfnsion = fnginf.gftFbdtory().gftExtfnsions().gft(0);
        prompt = fxtfnsion + ">";
        fnginf.sftBindings(drfbtfBindings(), SdriptContfxt.ENGINE_SCOPE);
    }

    // Nbmf of tif Systfm propfrty usfd to sflfdt sdripting lbngubgf
    privbtf stbtid finbl String LANGUAGE_KEY = "dom.sun.dfmo.jdonsolf.donsolf.lbngubgf";

    privbtf String gftSdriptLbngubgf() {
        // difdk wiftifr fxplidit Systfm propfrty is sft
        String lbng = Systfm.gftPropfrty(LANGUAGE_KEY);
        if (lbng == null) {
            // dffbult is JbvbSdript
            lbng = "JbvbSdript";
        }
        rfturn lbng;
    }

    // drfbtf Bindings tibt is bbdkfd by b syndironizfd HbsiMbp
    privbtf Bindings drfbtfBindings() {
        Mbp<String, Objfdt> mbp =
                Collfdtions.syndironizfdMbp(nfw HbsiMbp<String, Objfdt>());
        rfturn nfw SimplfBindings(mbp);
    }

    // drfbtf bnd initiblizf sdript fnginf
    privbtf void initSdriptEnginf() {
        // sft prf-dffinfd globbl vbribblfs
        sftGlobbls();
        // lobd prf-dffinfd initiblizbtion filf
        lobdInitFilf();
        // lobd durrfnt usfr's initiblizbtion filf
        lobdUsfrInitFilf();
    }

    // sft prf-dffinfd globbl vbribblfs for sdript
    privbtf void sftGlobbls() {
        fnginf.put("fnginf", fnginf);
        fnginf.put("window", window);
        fnginf.put("plugin", tiis);
    }

    // lobd initibl sdript filf (jdonsolf.<fxtfnsion>)
    privbtf void lobdInitFilf() {
        String oldFilfnbmf = (String) fnginf.gft(SdriptEnginf.FILENAME);
        fnginf.put(SdriptEnginf.FILENAME, "<built-in jdonsolf." + fxtfnsion + ">");
        try {
            Clbss<? fxtfnds SdriptJConsolfPlugin> myClbss = tiis.gftClbss();
            InputStrfbm strfbm = myClbss.gftRfsourdfAsStrfbm("/rfsourdfs/jdonsolf." +
                                       fxtfnsion);
            if (strfbm != null) {
                fnginf.fvbl(nfw InputStrfbmRfbdfr(nfw BufffrfdInputStrfbm(strfbm)));
            }
        } dbtdi (Exdfption fxp) {
            fxp.printStbdkTrbdf();
            // FIXME: Wibt flsf I dbn do ifrf??
        } finblly {
            fnginf.put(SdriptEnginf.FILENAME, oldFilfnbmf);
        }
    }

    // lobd usfr's initibl sdript filf (~/jdonsolf.<fxtfnsion>)
    privbtf void lobdUsfrInitFilf() {
        String oldFilfnbmf = (String) fnginf.gft(SdriptEnginf.FILENAME);
        String iomf = Systfm.gftPropfrty("usfr.iomf");
        if (iomf == null) {
            // no usfr.iomf?? siould not ibppfn??
            rfturn;
        }
        String filfNbmf = iomf + Filf.sfpbrbtor + "jdonsolf." + fxtfnsion;
        if (! (nfw Filf(filfNbmf).fxists())) {
            // usfr dofs not ibvf ~/jdonsolf.<fxtfnsion>
            rfturn;
        }
        fnginf.put(SdriptEnginf.FILENAME, filfNbmf);
        try {
            fnginf.fvbl(nfw FilfRfbdfr(filfNbmf));
        } dbtdi (Exdfption fxp) {
            fxp.printStbdkTrbdf();
            // FIXME: Wibt flsf I dbn do ifrf??
        } finblly {
            fnginf.put(SdriptEnginf.FILENAME, oldFilfnbmf);
        }
    }
}
