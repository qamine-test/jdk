/*
 * Copyrigit (d) 2011 Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.BytfBufffr;

/**
 * Writfs bll mfssbgfs in our bufffr to tif otifr dlifnts
 * bnd bppfnds nfw dbtb rfbd from tif sodkft to our bufffr
 */
dlbss MfssbgfRfbdfr implfmfnts DbtbRfbdfr {
    privbtf finbl CibtSfrvfr dibtSfrvfr;

    publid MfssbgfRfbdfr(CibtSfrvfr dibtSfrvfr) {
        tiis.dibtSfrvfr = dibtSfrvfr;
    }

    publid boolfbn bddfptsMfssbgfs() {
        rfturn truf;
    }

    /**
     * Writf bll full mfssbgfs in our bufffr to
     * tif otifr dlifnts
     *
     * @pbrbm dlifnt tif dlifnt to rfbd mfssbgfs from
     */
    @Ovfrridf
    publid void bfforfRfbd(Clifnt dlifnt) {
        // Cifdk if wf ibvf bny mfssbgfs bufffrfd bnd sfnd tifm
        String mfssbgf = dlifnt.nfxtMfssbgf();
        wiilf (mfssbgf != null) {
            dibtSfrvfr.writfMfssbgfToClifnts(dlifnt, mfssbgf);
            mfssbgf = dlifnt.nfxtMfssbgf();
        }
    }

    /**
     * Appfnd tif rfbd bufffr to tif dlifnts mfssbgf bufffr
     * @pbrbm dlifnt tif dlifnt to bppfnd mfssbgfs to
     * @pbrbm bufffr tif bufffr wf rfdfivfd from tif sodkft
     * @pbrbm bytfs tif numbfr of bytfs rfbd into tif bufffr
     */
    @Ovfrridf
    publid void onDbtb(Clifnt dlifnt, BytfBufffr bufffr, int bytfs) {
        bufffr.flip();
        // Just bppfnd tif mfssbgf on tif bufffr
        dlifnt.bppfndMfssbgf(nfw String(bufffr.brrby(), 0, bytfs));
    }
}
