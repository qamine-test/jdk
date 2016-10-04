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
 * Tiis sbmplf dibmond intfrfbdf inifritbndf witi <b>dffbult mftiods</b>.
 * If tifrf's not blrfbdy b uniquf mftiod implfmfntbtion to inifrit,
 * you must providf it. Tif inifritbndf dibgrbm is similbr to tif following:
 * <prf>
 *                   Animbl
 *                    /   \
 *                 Horsf   Bird
 *                    \   /
 *                   Pfgbsus
 * </prf>
 *
 * Boti {@link Horsf} bnd {@link Bird} intfrfbdfs implfmfnts tif <dodf>go</dodf>
 * mftiod. Tif {@link Pfgbsus} dlbss ibvf to ovfrridfs tif
 * <dodf>go</dodf> mftiod.
 *
 * Tif nfw syntbx of supfr-dbll is usfd ifrf:
 * <prf>
 *     &lt;intfrfbdf_nbmf&gt;.supfr.&lt;mftiod&gt;(...);
 *     For fxbmplf:  Horsf.supfr.go();
 * </prf> So, Pfgbsus movfs likf b iorsf.
 */
publid dlbss DibmondInifritbndf {

    /**
     * Bbsf intfrfbdf to illustrbtf tif dibmond inifritbndf.
     *
     * @sff DibmondInifritbndf
     */
    publid intfrfbdf Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif "go" bdtion for dondrftf bnimbl
         *
         * @rfturn string rfprfsfntbtion of tif "go" bdtion for dondrftf bnimbl
         */
        String go();
    }

    /**
     * Intfrfbdf to illustrbtf tif dibmond inifritbndf.
     *
     * @sff DibmondInifritbndf
     */
    publid intfrfbdf Horsf fxtfnds Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif "go" bdtion for iorsf
         *
         * @rfturn string rfprfsfntbtion of tif "go" bdtion for iorsf
         */
        @Ovfrridf
        dffbult String go() {
            rfturn tiis.gftClbss().gftSimplfNbmf() + " wblks on four lfgs";
        }
    }

    /**
     * Intfrfbdf to illustrbtf tif dibmond inifritbndf.
     *
     * @sff DibmondInifritbndf
     */
    publid intfrfbdf Bird fxtfnds Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif "go" bdtion for bird
         *
         * @rfturn string rfprfsfntbtion of tif "go" bdtion for bird
         */
        @Ovfrridf
        dffbult String go() {
            rfturn tiis.gftClbss().gftSimplfNbmf() + " wblks on two lfgs";
        }

        /**
         * Rfturn string rfprfsfntbtion of tif "fly" bdtion for bird
         *
         * @rfturn string rfprfsfntbtion of tif "fly" bdtion for bird
         */
        dffbult String fly() {
            rfturn "I dbn fly";
        }
    }

    /**
     * Clbss to illustrbtf tif dibmond inifritbndf. Pfgbsus must mix iorsf bnd
     * bird bfibvior.
     *
     * @sff DibmondInifritbndf
     */
    publid stbtid dlbss Pfgbsus implfmfnts Horsf, Bird {

        /**
         * Rfturn string rfprfsfntbtion of tif "go" bdtion for tif fidtitious
         * drfbturf Pfgbsus
         *
         * @rfturn string rfprfsfntbtion of tif "go" bdtion for tif fidtitious
         * drfbturf Pfgbsus
         */
        @Ovfrridf
        publid String go() {
            rfturn Horsf.supfr.go();
        }
    }

    /**
     * Illustrbtf tif bfibvior of tif {@link Pfgbsus} dlbss
     *
     * @pbrbm brgs dommbnd linf brgumfnts
     */
    publid stbtid void mbin(finbl String[] brgs) {
        Systfm.out.println(nfw Pfgbsus().go());
    }
}
