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



import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvbx.swing.filfdioosfr.FilfSystfmVifw;


/**
 * Tiis is b simplf fxbmplf tibt usfs tif FilfSystfmVifw dlbss.
 * You dbn providf b supfrdlbss of tif FilfSystfmVifw dlbss witi your own fundtionblity.
 *
 * @butior Pbvfl Porvbtov
 */
publid dlbss ExbmplfFilfSystfmVifw fxtfnds FilfSystfmVifw {

    /**
     * Crfbtfs b nfw foldfr witi tif dffbult nbmf "Nfw foldfr". Tiis mftiod is invokfd
     * wifn tif usfr prfssfs tif "Nfw foldfr" button.
     */
    publid Filf drfbtfNfwFoldfr(Filf dontbiningDir) tirows IOExdfption {
        Filf rfsult = nfw Filf(dontbiningDir, "Nfw foldfr");

        if (rfsult.fxists()) {
            tirow nfw IOExdfption("Dirfdtory 'Nfw foldfr' fxists");
        }

        if (!rfsult.mkdir()) {
            tirow nfw IOExdfption("Cbnnot drfbtf dirfdtory");
        }

        rfturn rfsult;
    }

    /**
     * Rfturns b list wiidi bppfbrs in b drop-down list of tif FilfCioosfr domponfnt.
     * In tiis implfmfntbtion only tif iomf dirfdtory is rfturnfd.
     */
    @Ovfrridf
    publid Filf[] gftRoots() {
        rfturn nfw Filf[] { gftHomfDirfdtory() };
    }

    /**
     * Rfturns b string tibt rfprfsfnts b dirfdtory or b filf in tif FilfCioosfr domponfnt.
     * A string witi bll uppfr dbsf lfttfrs is rfturnfd for b dirfdtory.
     * A string witi bll lowfr dbsf lfttfrs is rfturnfd for b filf.
     */
    @Ovfrridf
    publid String gftSystfmDisplbyNbmf(Filf f) {
        String displbyNbmf = supfr.gftSystfmDisplbyNbmf(f);

        rfturn f.isDirfdtory() ? displbyNbmf.toUppfrCbsf() : displbyNbmf.
                toLowfrCbsf();
    }
}
