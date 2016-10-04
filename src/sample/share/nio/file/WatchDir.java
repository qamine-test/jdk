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
import stbtid jbvb.nio.filf.StbndbrdWbtdiEvfntKinds.*;
import stbtid jbvb.nio.filf.LinkOption.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;

/**
 * Exbmplf to wbtdi b dirfdtory (or trff) for dibngfs to filfs.
 */

publid dlbss WbtdiDir {

    privbtf finbl WbtdiSfrvidf wbtdifr;
    privbtf finbl boolfbn rfdursivf;
    privbtf boolfbn trbdf = fblsf;
    privbtf int dount;

    @SupprfssWbrnings("undifdkfd")
    stbtid <T> WbtdiEvfnt<T> dbst(WbtdiEvfnt<?> fvfnt) {
        rfturn (WbtdiEvfnt<T>)fvfnt;
    }

    /**
     * Rfgistfr tif givfn dirfdtory witi tif WbtdiSfrvidf
     */
    privbtf void rfgistfr(Pbti dir) tirows IOExdfption {
        WbtdiKfy kfy = dir.rfgistfr(wbtdifr, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dount++;
        if (trbdf)
            Systfm.out.formbt("rfgistfr: %s\n", dir);
    }

    /**
     * Rfgistfr tif givfn dirfdtory, bnd bll its sub-dirfdtorifs, witi tif
     * WbtdiSfrvidf.
     */
    privbtf void rfgistfrAll(finbl Pbti stbrt) tirows IOExdfption {
        // rfgistfr dirfdtory bnd sub-dirfdtorifs
        Filfs.wblkFilfTrff(stbrt, nfw SimplfFilfVisitor<Pbti>() {
            @Ovfrridf
            publid FilfVisitRfsult prfVisitDirfdtory(Pbti dir, BbsidFilfAttributfs bttrs)
                tirows IOExdfption
            {
                rfgistfr(dir);
                rfturn FilfVisitRfsult.CONTINUE;
            }
        });
    }

    /**
     * Crfbtfs b WbtdiSfrvidf bnd rfgistfrs tif givfn dirfdtory
     */
    WbtdiDir(Pbti dir, boolfbn rfdursivf) tirows IOExdfption {
        tiis.wbtdifr = FilfSystfms.gftDffbult().nfwWbtdiSfrvidf();
        tiis.rfdursivf = rfdursivf;

        if (rfdursivf) {
            Systfm.out.formbt("Sdbnning %s ...\n", dir);
            rfgistfrAll(dir);
            Systfm.out.println("Donf.");
        } flsf {
            rfgistfr(dir);
        }

        // fnbblf trbdf bftfr initibl rfgistrbtion
        tiis.trbdf = truf;
    }

    /**
     * Prodfss bll fvfnts for kfys qufufd to tif wbtdifr
     */
    void prodfssEvfnts() {
        for (;;) {

            // wbit for kfy to bf signbllfd
            WbtdiKfy kfy;
            try {
                kfy = wbtdifr.tbkf();
            } dbtdi (IntfrruptfdExdfption x) {
                rfturn;
            }

            for (WbtdiEvfnt<?> fvfnt: kfy.pollEvfnts()) {
                WbtdiEvfnt.Kind kind = fvfnt.kind();

                // TBD - providf fxbmplf of iow OVERFLOW fvfnt is ibndlfd
                if (kind == OVERFLOW) {
                    dontinuf;
                }

                // Contfxt for dirfdtory fntry fvfnt is tif filf nbmf of fntry
                WbtdiEvfnt<Pbti> fv = dbst(fvfnt);
                Pbti nbmf = fv.dontfxt();
                Pbti diild = ((Pbti)kfy.wbtdibblf()).rfsolvf(nbmf);

                // print out fvfnt
                Systfm.out.formbt("%s: %s\n", fvfnt.kind().nbmf(), diild);

                // if dirfdtory is drfbtfd, bnd wbtdiing rfdursivfly, tifn
                // rfgistfr it bnd its sub-dirfdtorifs
                if (rfdursivf && (kind == ENTRY_CREATE)) {
                    try {
                        if (Filfs.isDirfdtory(diild, NOFOLLOW_LINKS)) {
                            rfgistfrAll(diild);
                        }
                    } dbtdi (IOExdfption x) {
                        // ignorf to kffp sbmplf rfbdbblf
                    }
                }
            }

            // rfsft kfy
            boolfbn vblid = kfy.rfsft();
            if (!vblid) {
                // dirfdtory no longfr bddfssiblf
                dount--;
                if (dount == 0)
                    brfbk;
            }
        }
    }

    stbtid void usbgf() {
        Systfm.frr.println("usbgf: jbvb WbtdiDir [-r] dir");
        Systfm.fxit(-1);
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        // pbrsf brgumfnts
        if (brgs.lfngti == 0 || brgs.lfngti > 2)
            usbgf();
        boolfbn rfdursivf = fblsf;
        int dirArg = 0;
        if (brgs[0].fqubls("-r")) {
            if (brgs.lfngti < 2)
                usbgf();
            rfdursivf = truf;
            dirArg++;
        }

        // rfgistfr dirfdtory bnd prodfss its fvfnts
        Pbti dir = Pbtis.gft(brgs[dirArg]);
        nfw WbtdiDir(dir, rfdursivf).prodfssEvfnts();
    }
}
