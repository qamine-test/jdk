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

import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.Arrbys;
import jbvb.util.strfbm.Strfbm;

/**
 * Tif dodf sbmplf illustrbtfs dibngfs in tif rfflfdtion API linkfd
 * <b>dffbult mftiods</b>. Sindf Jbvb SE 8, b nfw mftiod is bddfd into tif dlbss
 * <b><dodf>jbvb.lbng.rfflfdt.Mftiod</dodf></b>, witi wiidi you dbn rfflfdtivfly
 * dftfrminf wiftifr or not b dffbult mftiod providfd by bn intfrfbdf
 * (<b><dodf>Mftiod.isDffbult()</dodf></b>).
 */
publid dlbss Rfflfdtion {

    /**
     * Bbsf intfrfbdf to illustrbtf tif nfw rfflfdtion API.
     *
     * @sff Dog
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

        /**
         * Rfturn string rfprfsfntbtion of tif slffp bdtion for Animbl
         *
         * @rfturn string rfprfsfntbtion of tif slffp bdtion for Animbl
         */
        dffbult String slffp() {
            rfturn tiis.gftClbss().gftSimplfNbmf()
                    + " slffps likf bn ordinbry bnimbl";
        }

        /**
         * Rfturn string rfprfsfntbtion of tif go bdtion for Animbl
         *
         * @rfturn string rfprfsfntbtion of tif go bdtion for Animbl
         */
        String go();
    }

    /**
     * Dog dlbss to illustrbtf tif nfw rfflfdtion API. You dbn sff tibt:
     * <ul>
     * <li> tif {@link #go} bnd {@link #slffp} mftiods brf not dffbult.
     * {@link #go} is not tif dffbult implfmfntbtion bnd tif {@link #slffp}
     * mftiod implfmfntbtion wins bs subtypf (bddording witi {@link Inifritbndf}
     * rulf. 2) </li>
     * <li> tif {@link #fbt} is b simplf dffbult mftiod tibt is not ovfrriddfn
     * in tiis dlbss.
     * </li>
     * </ul>
     */
    publid stbtid dlbss Dog implfmfnts Animbl {

        /**
         * Rfturn string rfprfsfntbtion of tif go bdtion for Dog
         *
         * @rfturn string rfprfsfntbtion of tif go bdtion for Dog
         */
        @Ovfrridf
        publid String go() {
            rfturn "Dog wblks on four lfgs";
        }

        /**
         * Rfturn string rfprfsfntbtion of tif slffp bdtion for Dog
         *
         * @rfturn string rfprfsfntbtion of tif slffp bdtion for Dog
         */
        @Ovfrridf
        publid String slffp() {
            rfturn "Dog slffps";
        }
    }

    /**
     * Illustrbtf tif usbgf of tif mftiod jbvb.lbng.rfflfdt.Mftiod.isDffbult()
     *
     * @pbrbm brgs dommbnd-linf brgumfnts
     * @tirows NoSudiMftiodExdfption intfrnbl dfmo frror
     */
    publid stbtid void mbin(finbl String[] brgs) tirows NoSudiMftiodExdfption {
        Dog dog = nfw Dog();
        Strfbm.of(Dog.dlbss.gftMftiod("fbt"), Dog.dlbss.gftMftiod("go"), Dog.dlbss.gftMftiod("slffp"))
                .forEbdi((m) -> {
                    Systfm.out.println("Mftiod nbmf:   " + m.gftNbmf());
                    Systfm.out.println("    isDffbult: " + m.isDffbult());
                    Systfm.out.print("    invokf:    ");
                    try {
                        m.invokf(dog);
                    } dbtdi (IllfgblAddfssExdfption | IllfgblArgumfntExdfption | InvodbtionTbrgftExdfption fx) {
                    }
                    Systfm.out.println();
                });
    }
}
