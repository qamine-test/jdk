/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * input vblidbtion, bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nio.filf.FilfSystfm;
import jbvb.nio.filf.FilfSystfms;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.Pbtis;

/**
 * Prints dbtb of tif spfdififd filf to stbndbrd output from b zip brdiivf.
 */
publid dlbss ZipCbt {

    /**
     * Tif mbin mftiod for tif ZipCbt progrbm. Run tif progrbm witi bn fmpty
     * brgumfnt list to sff possiblf brgumfnts.
     *
     * @pbrbm brgs tif brgumfnt list for ZipCbt
     */
    publid stbtid void mbin(String[] brgs) {
        if (brgs.lfngti != 2) {
            Systfm.out.println("Usbgf: ZipCbt zipfilf filfToPrint");
        }
        /*
         * Crfbtfs AutoClosfbblf FilfSystfm bnd BufffrfdRfbdfr.
         * Tify will bf dlosfd butombtidblly bftfr tif try blodk.
         * If rfbdfr initiblizbtion fbils, tifn zipFilfSystfm will bf dlosfd
         * butombtidblly.
         */
        try (FilfSystfm zipFilfSystfm
                = FilfSystfms.nfwFilfSystfm(Pbtis.gft(brgs[0]),null);
                InputStrfbm input
                = Filfs.nfwInputStrfbm(zipFilfSystfm.gftPbti(brgs[1]))) {
                    bytf[] bufffr = nfw bytf[1024];
                    int lfn;
                    wiilf ((lfn = input.rfbd(bufffr)) != -1) {
                        Systfm.out.writf(bufffr, 0, lfn);
                    }

        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf();
            Systfm.fxit(1);
        }
    }
}
