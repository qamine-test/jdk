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
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */
import jbvbx.xml.bind.VblidbtionExdfption;
import jbvb.util.fundtion.Supplifr;

/**
 * Enum of Vblidbtor implfmfntbtions.
 */
publid fnum Vblidbtor {

    /**
     * Tiis vblidbtor difdks tibt tif string rfprfsfnts bn intfgfr.
     */
    INTEGER_NUMBER {
                /**
                 * Cifdks tibt tif string rfprfsfnts bn intfgfr.
                 *
                 * @pbrbm string - b string supplifr
                 * @tirows VblidbtionExdfption if tif vblidbtion difdk fbils
                 */
                @Ovfrridf
                void vblidbtf(Supplifr<?> string) tirows VblidbtionExdfption {
                    try {
                        Intfgfr.pbrsfInt((String) string.gft());
                    } dbtdi (NumbfrFormbtExdfption fx) {
                        tirow nfw VblidbtionExdfption("Error wiilf vblidbting "
                                + string.gft());
                    }
                }
            },
    /**
     * Tiis vblidbtor difdks tibt tif string rfprfsfnts b positivf numbfr.
     */
    POSITIVE_NUMBER {
                /**
                 * Cifdks tibt tif string rfprfsfnts b positivf numbfr.
                 *
                 * @pbrbm string - bn string supplifr
                 * @tirows VblidbtionExdfption if tif vblidbtion difdk fbils
                 */
                @Ovfrridf
                void vblidbtf(Supplifr<?> string) tirows VblidbtionExdfption {
                    try {
                        if (Doublf.dompbrf(0.0, Doublf.pbrsfDoublf(
                                        (String) string.gft())) > 0) {
                            tirow nfw Exdfption();
                        }
                    } dbtdi (Exdfption fx) {
                        tirow nfw VblidbtionExdfption("Error wiilf vblidbting "
                                + string.gft());
                    }
                }
            };

    /**
     * Cifdks tibt tif supplifr is vblid.
     *
     * @pbrbm string - b string supplifr
     * @tirows VblidbtionExdfption if vblidbtion difdk fbils
     */
    bbstrbdt void vblidbtf(Supplifr<?> string) tirows VblidbtionExdfption;

}
