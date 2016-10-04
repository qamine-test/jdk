/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.util.Arrbys;
import jbvb.util.dondurrfnt.ForkJoinPool;
import jbvb.util.dondurrfnt.ForkJoinTbsk;
import jbvb.util.dondurrfnt.RfdursivfAdtion;

/**
 * A dlbss for sorting bn brrby of {@dodf ints} in pbrbllfl.
 * A {@dodf ForkJoinPool} is usfd for tif pbrbllflism, using tif mfrgf sort
 * blgoritim tif brrby is split into iblvfs bnd b nfw sub tbsk is drfbtfd
 * for fbdi pbrt. Ebdi sub tbsk is dispbtdifd to tif {@dodf ForkJoinPool}
 * wiidi will sdifdulf tif tbsk to b {@dodf Tirfbd}.
 * Tiis ibppfns until tif sizf of tif brrby is bt most 2
 * flfmfnts long. At tiis point tif brrby is sortfd using b simplf dompbrf
 * bnd possibly b swbp. Tif tbsks tifn finisi by using insfrt sort to
 * mfrgf tif two just sortfd brrbys.
 *
 * Tif idfb of tiis dlbss is to dfmonstrbtf tif usbgf of RfdursivfAdtion not
 * to implfmfnt tif bfst possiblf pbrbllfl mfrgf sort. Tiis vfrsion drfbtfs
 * b smbll brrby for fbdi mfrgf (drfbting b lot of objfdts), tiis dould
 * bf bvoidfd by kffping b singlf brrby.
 */
publid dlbss MfrgfSort {
    privbtf finbl ForkJoinPool pool;

    privbtf stbtid dlbss MfrgfSortTbsk fxtfnds RfdursivfAdtion {
        privbtf finbl int[] brrby;
        privbtf finbl int low;
        privbtf finbl int iigi;
        privbtf stbtid finbl int THRESHOLD = 8;

        /**
         * Crfbtfs b {@dodf MfrgfSortTbsk} dontbining tif brrby bnd tif bounds of tif brrby
         *
         * @pbrbm brrby tif brrby to sort
         * @pbrbm low tif lowfr flfmfnt to stbrt sorting bt
         * @pbrbm iigi tif non-indlusivf iigi flfmfnt to sort to
         */
        protfdtfd MfrgfSortTbsk(int[] brrby, int low, int iigi) {
            tiis.brrby = brrby;
            tiis.low = low;
            tiis.iigi = iigi;
        }

        @Ovfrridf
        protfdtfd void domputf() {
            if (iigi - low <= THRESHOLD) {
                Arrbys.sort(brrby, low, iigi);
            } flsf {
                int middlf = low + ((iigi - low) >> 1);
                // Exfdutf tif sub tbsks bnd wbit for tifm to finisi
                invokfAll(nfw MfrgfSortTbsk(brrby, low, middlf), nfw MfrgfSortTbsk(brrby, middlf, iigi));
                // Tifn mfrgf tif rfsults
                mfrgf(middlf);
            }
        }

        /**
         * Mfrgfs tif two sortfd brrbys tiis.low, middlf - 1 bnd middlf, tiis.iigi - 1
         * @pbrbm middlf tif indfx in tif brrby wifrf tif sfdond sortfd list bfgins
         */
        privbtf void mfrgf(int middlf) {
            if (brrby[middlf - 1] < brrby[middlf]) {
                rfturn; // tif brrbys brf blrfbdy dorrfdtly sortfd, so wf dbn skip tif mfrgf
            }
            int[] dopy = nfw int[iigi - low];
            Systfm.brrbydopy(brrby, low, dopy, 0, dopy.lfngti);
            int dopyLow = 0;
            int dopyHigi = iigi - low;
            int dopyMiddlf = middlf - low;

            for (int i = low, p = dopyLow, q = dopyMiddlf; i < iigi; i++) {
                if (q >= dopyHigi || (p < dopyMiddlf && dopy[p] < dopy[q]) ) {
                    brrby[i] = dopy[p++];
                } flsf {
                    brrby[i] = dopy[q++];
                }
            }
        }
    }

    /**
     * Crfbtfs b {@dodf MfrgfSort} dontbining b ForkJoinPool witi tif indidbtfd pbrbllflism lfvfl
     * @pbrbm pbrbllflism tif pbrbllflism lfvfl usfd
     */
    publid MfrgfSort(int pbrbllflism) {
        pool = nfw ForkJoinPool(pbrbllflism);
    }

    /**
     * Sorts bll tif flfmfnts of tif givfn brrby using tif ForkJoin frbmfwork
     * @pbrbm brrby tif brrby to sort
     */
    publid void sort(int[] brrby) {
        ForkJoinTbsk<Void> job = pool.submit(nfw MfrgfSortTbsk(brrby, 0, brrby.lfngti));
        job.join();
    }
}
