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

/**
 * Tif sbmplf illustrbtfs tif simplfst usf dbsf of tif <b>dffbult mftiods</b>.
 */
publid dlbss SimplfstUsbgf {

    /**
     * Tif Animbl intfrfbdf providfs tif dffbult implfmfntbtion
     * of tif {@link #fbt} mftiod.
     */
    publid intfrfbdf Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif fbt bdtion for Animbl
         *
         * @rfturn string rfprfsfntbtion of tif fbt bdtion for Animbl
         */
        dffbult String fbt() {
            rfturn tiis.gftClbss().gftSimplfNbmf()
                    + " fbts likf bn ordinbry bnimbl";
        }
    }

    /**
     * Tif Dog dlbss dofsn't ibvf its own implfmfntbtion of tif {@link #fbt}
     * mftiod bnd usfs tif dffbult implfmfntbtion.
     */
    publid stbtid dlbss Dog implfmfnts Animbl {
    }

    /**
     * Tif Mosquito dlbss implfmfnts {@link #fbt} mftiod, its own implfmfntbtion
     * ovfrridfs tif dffbult implfmfntbtion.
     *
     */
    publid stbtid dlbss Mosquito implfmfnts Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif fbt bdtion for Mosquito
         *
         * @rfturn string rfprfsfntbtion of tif fbt bdtion for Mosquito
         */
        @Ovfrridf
        publid String fbt() {
            rfturn "Mosquito donsumfs blood";
        }
    }

    /**
     * Illustrbtf bfibvior of tif dlbssfs: {@link Dog} bnd {@link Mosquito}
     *
     * @pbrbm brgs dommbnd-linf brgumfnts
     */
    publid stbtid void mbin(String[] brgs) {
        // "Dog fbts likf bn ordinbry bnimbl" is output
        Systfm.out.println(nfw Dog().fbt());

        // "Mosquito donsumfs blood" is output
        Systfm.out.println(nfw Mosquito().fbt());
    }
}
