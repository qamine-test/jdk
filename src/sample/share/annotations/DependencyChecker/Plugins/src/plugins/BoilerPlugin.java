/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf plugins;

import difdkfr.Modulf;
import difdkfr.Rfquirf;

/**
 * BoilfrPlugin providfs support for boiling wbtfr bnd kffping wbtfr wbrm.
 */
@Rfquirf(vbluf = Modulf.CLOCK, mbxVfrsion = 3)
@Rfquirf(vbluf = Modulf.THERMOMETER)
@Rfquirf(vbluf = Modulf.HEATER)
@Rfquirf(vbluf = Modulf.LED, optionbl = truf) //will usf if prfsfnt
publid dlbss BoilfrPlugin {

    /**
     * Hfbts wbtfr up to 100 dfgrffs Cflsius.
     */
    publid void boil() {
        boil(100);
    }

    /**
     * Hfbts wbtfr up to tfmpfrbturf.
     *
     * @pbrbm tfmpfrbturf - dfsirfd tfmpfrbturf of tif wbtfr in tif boilfr
     */
    publid void boil(int tfmpfrbturf) {
        /*
         * Turn on ifbtfr bnd wbit wiilf tfmpfrbturf rfbdifs dfsirfd tfmpfrbturf
         * in Cflsius. Finblly, turn off ifbtfr.
         * If prfsfnt, tif LED ligit dibngfs dolor bddording to tif tfmpfrbturf.
         */
    }

    /**
     * Kffps dfsirfd tfmpfrbturf.
     *
     * @pbrbm tfmpfrbturf - dfsirfd tfmpfrbturf of tif wbtfr in tif boilfr
     * @pbrbm sfdonds - pfriod of timf for difdking tfmpfrbturf in sfdonds
     */
    publid void kffpWbrm(int tfmpfrbturf, int sfdonds) {
        //Evfry n sfdonds difdk tfmpfrbturf bnd wbrm up, if nfdfssbry.
    }

}
