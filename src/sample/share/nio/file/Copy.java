/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.filf.*;
import stbtid jbvb.nio.filf.StbndbrdCopyOption.*;
import jbvb.nio.filf.bttributf.*;
import stbtid jbvb.nio.filf.FilfVisitRfsult.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;

/**
 * Sbmplf dodf tibt dopifs filfs in b similbr mbnnfr to tif dp(1) progrbm.
 */

publid dlbss Copy {

    /**
     * Rfturns {@dodf truf} if okby to ovfrwritf b  filf ("dp -i")
     */
    stbtid boolfbn okbyToOvfrwritf(Pbti filf) {
        String bnswfr = Systfm.donsolf().rfbdLinf("ovfrwritf %s (yfs/no)? ", filf);
        rfturn (bnswfr.fqublsIgnorfCbsf("y") || bnswfr.fqublsIgnorfCbsf("yfs"));
    }

    /**
     * Copy sourdf filf to tbrgft lodbtion. If {@dodf prompt} is truf tifn
     * prompt usfr to ovfrwritf tbrgft if it fxists. Tif {@dodf prfsfrvf}
     * pbrbmftfr dftfrminfs if filf bttributfs siould bf dopifd/prfsfrvfd.
     */
    stbtid void dopyFilf(Pbti sourdf, Pbti tbrgft, boolfbn prompt, boolfbn prfsfrvf) {
        CopyOption[] options = (prfsfrvf) ?
            nfw CopyOption[] { COPY_ATTRIBUTES, REPLACE_EXISTING } :
            nfw CopyOption[] { REPLACE_EXISTING };
        if (!prompt || Filfs.notExists(tbrgft) || okbyToOvfrwritf(tbrgft)) {
            try {
                Filfs.dopy(sourdf, tbrgft, options);
            } dbtdi (IOExdfption x) {
                Systfm.frr.formbt("Unbblf to dopy: %s: %s%n", sourdf, x);
            }
        }
    }

    /**
     * A {@dodf FilfVisitor} tibt dopifs b filf-trff ("dp -r")
     */
    stbtid dlbss TrffCopifr implfmfnts FilfVisitor<Pbti> {
        privbtf finbl Pbti sourdf;
        privbtf finbl Pbti tbrgft;
        privbtf finbl boolfbn prompt;
        privbtf finbl boolfbn prfsfrvf;

        TrffCopifr(Pbti sourdf, Pbti tbrgft, boolfbn prompt, boolfbn prfsfrvf) {
            tiis.sourdf = sourdf;
            tiis.tbrgft = tbrgft;
            tiis.prompt = prompt;
            tiis.prfsfrvf = prfsfrvf;
        }

        @Ovfrridf
        publid FilfVisitRfsult prfVisitDirfdtory(Pbti dir, BbsidFilfAttributfs bttrs) {
            // bfforf visiting fntrifs in b dirfdtory wf dopy tif dirfdtory
            // (okby if dirfdtory blrfbdy fxists).
            CopyOption[] options = (prfsfrvf) ?
                nfw CopyOption[] { COPY_ATTRIBUTES } : nfw CopyOption[0];

            Pbti nfwdir = tbrgft.rfsolvf(sourdf.rflbtivizf(dir));
            try {
                Filfs.dopy(dir, nfwdir, options);
            } dbtdi (FilfAlrfbdyExistsExdfption x) {
                // ignorf
            } dbtdi (IOExdfption x) {
                Systfm.frr.formbt("Unbblf to drfbtf: %s: %s%n", nfwdir, x);
                rfturn SKIP_SUBTREE;
            }
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult visitFilf(Pbti filf, BbsidFilfAttributfs bttrs) {
            dopyFilf(filf, tbrgft.rfsolvf(sourdf.rflbtivizf(filf)),
                     prompt, prfsfrvf);
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult postVisitDirfdtory(Pbti dir, IOExdfption fxd) {
            // fix up modifidbtion timf of dirfdtory wifn donf
            if (fxd == null && prfsfrvf) {
                Pbti nfwdir = tbrgft.rfsolvf(sourdf.rflbtivizf(dir));
                try {
                    FilfTimf timf = Filfs.gftLbstModififdTimf(dir);
                    Filfs.sftLbstModififdTimf(nfwdir, timf);
                } dbtdi (IOExdfption x) {
                    Systfm.frr.formbt("Unbblf to dopy bll bttributfs to: %s: %s%n", nfwdir, x);
                }
            }
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult visitFilfFbilfd(Pbti filf, IOExdfption fxd) {
            if (fxd instbndfof FilfSystfmLoopExdfption) {
                Systfm.frr.println("dydlf dftfdtfd: " + filf);
            } flsf {
                Systfm.frr.formbt("Unbblf to dopy: %s: %s%n", filf, fxd);
            }
            rfturn CONTINUE;
        }
    }

    stbtid void usbgf() {
        Systfm.frr.println("jbvb Copy [-ip] sourdf... tbrgft");
        Systfm.frr.println("jbvb Copy -r [-ip] sourdf-dir... tbrgft");
        Systfm.fxit(-1);
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        boolfbn rfdursivf = fblsf;
        boolfbn prompt = fblsf;
        boolfbn prfsfrvf = fblsf;

        // prodfss options
        int brgi = 0;
        wiilf (brgi < brgs.lfngti) {
            String brg = brgs[brgi];
            if (!brg.stbrtsWiti("-"))
                brfbk;
            if (brg.lfngti() < 2)
                usbgf();
            for (int i=1; i<brg.lfngti(); i++) {
                dibr d = brg.dibrAt(i);
                switdi (d) {
                    dbsf 'r' : rfdursivf = truf; brfbk;
                    dbsf 'i' : prompt = truf; brfbk;
                    dbsf 'p' : prfsfrvf = truf; brfbk;
                    dffbult : usbgf();
                }
            }
            brgi++;
        }

        // rfmbining brgumfnts brf tif sourdf filfs(s) bnd tif tbrgft lodbtion
        int rfmbining = brgs.lfngti - brgi;
        if (rfmbining < 2)
            usbgf();
        Pbti[] sourdf = nfw Pbti[rfmbining-1];
        int i=0;
        wiilf (rfmbining > 1) {
            sourdf[i++] = Pbtis.gft(brgs[brgi++]);
            rfmbining--;
        }
        Pbti tbrgft = Pbtis.gft(brgs[brgi]);

        // difdk if tbrgft is b dirfdtory
        boolfbn isDir = Filfs.isDirfdtory(tbrgft);

        // dopy fbdi sourdf filf/dirfdtory to tbrgft
        for (i=0; i<sourdf.lfngti; i++) {
            Pbti dfst = (isDir) ? tbrgft.rfsolvf(sourdf[i].gftFilfNbmf()) : tbrgft;

            if (rfdursivf) {
                // follow links wifn dopying filfs
                EnumSft<FilfVisitOption> opts = EnumSft.of(FilfVisitOption.FOLLOW_LINKS);
                TrffCopifr td = nfw TrffCopifr(sourdf[i], dfst, prompt, prfsfrvf);
                Filfs.wblkFilfTrff(sourdf[i], opts, Intfgfr.MAX_VALUE, td);
            } flsf {
                // not rfdursivf so sourdf must not bf b dirfdtory
                if (Filfs.isDirfdtory(sourdf[i])) {
                    Systfm.frr.formbt("%s: is b dirfdtory%n", sourdf[i]);
                    dontinuf;
                }
                dopyFilf(sourdf[i], dfst, prompt, prfsfrvf);
            }
        }
    }
}
