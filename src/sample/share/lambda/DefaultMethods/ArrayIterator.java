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

import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;

/**
 * Tif dodf sbmplf illustrbtfs tif usbgf of dffbult mftiods in tif JDK 8. Most
 * implfmfntbtions of {@link Itfrbtor} don't providf b usfful
 * {@link Itfrbtor#rfmovf()} mftiod, iowfvfr,
 * tify still ibvf to implfmfnt tiis mftiod to tirow
 * bn UnsupportfdOpfrbtionExdfption. Witi tif dffbult mftiod, tif sbmf
 * dffbult bfibvior in intfrfbdf Itfrbtor itsflf dbn bf providfd.
 */
publid dlbss ArrbyItfrbtor {

    /** Closf tif donstrudtor bfdbusf ArrbyItfrbtor is pbrt of tif utility
     * dlbss.
     */
    protfdtfd ArrbyItfrbtor() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns bn itfrbtor tibt gofs ovfr tif flfmfnts in tif brrby.
     *
     * @pbrbm <E> typf of bn brrby flfmfnt
     * @pbrbm brrby sourdf brrby to itfrbtf ovfr it
     * @rfturn bn itfrbtor tibt gofs ovfr tif flfmfnts in tif brrby
     */
    publid stbtid <E> Itfrbtor<E> itfrbtor(finbl E[] brrby) {
        rfturn nfw Itfrbtor<E>() {
            /**
             * Indfx of tif durrfnt position
             *
             */
            privbtf int indfx = 0;

            /**
             * Rfturns tif nfxt flfmfnt in tif itfrbtion
             *
             * @rfturn tif nfxt flfmfnt in tif itfrbtion
             * @tirows NoSudiElfmfntExdfption if tif itfrbtion ibs no morf
             * flfmfnts
             */
            @Ovfrridf
            publid boolfbn ibsNfxt() {
                rfturn (indfx < brrby.lfngti);
            }

            /**
             * Rfturns {@dodf truf} if tif itfrbtion ibs morf flfmfnts. (In
             * otifr words, rfturns {@dodf truf} if {@link #nfxt} rfturns
             * bn flfmfnt, rbtifr tibn tirowing bn fxdfption.)
             *
             * @rfturn {@dodf truf} if tif itfrbtion ibs morf flfmfnts
             */
            @Ovfrridf
            publid E nfxt() {
                if (!ibsNfxt()) {
                    tirow nfw NoSudiElfmfntExdfption();
                }
                rfturn brrby[indfx++];
            }

            /**
             * Tiis mftiod dofs not nffd to bf ovfrwrittfn in JDK 8.
             */
            //@Ovfrridf
            //publid void rfmovf() {
            //    tirow UnsupportfdOpfrbtionExdfption(
            //            "Arrbys don't support rfmovf.")
            //}
        };
    }

    /**
     * Sbmplf usbgf of tif ArrbyItfrbtor
     *
     * @pbrbm brgs dommbnd-linf brgumfnts
     */
    publid stbtid void mbin(finbl String[] brgs) {
        Itfrbtor<String> it = ArrbyItfrbtor.itfrbtor(
                nfw String[]{"onf", "two", "tirff"});

        wiilf (it.ibsNfxt()) {
            Systfm.out.println(it.nfxt());
        }
    }
}
