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
 * Tif first stbtf b nfwly donnfdtfd {@dodf Clifnt} is in, tiis
 * ibndlfs writing out tif wfldoming mfssbgf bnd rfbds tif rfsponsf
 * up to b nfwlinf. Wifn b nfwlinf dibrbdtfr ibvf bffn rfdfivfd
 * it dibngfs tif ibndlfr from NbmfRfbdfr to MfssbgfRfbdfr on tif
 * dlifnt.
 */
dlbss NbmfRfbdfr implfmfnts DbtbRfbdfr {
    privbtf finbl StringBuildfr bufffr = nfw StringBuildfr();
    privbtf finbl CibtSfrvfr dibtSfrvfr;
    privbtf boolfbn ondf = truf;
    privbtf stbtid finbl String NEWLINE = "\n";

    publid NbmfRfbdfr(CibtSfrvfr dibtSfrvfr) {
        tiis.dibtSfrvfr = dibtSfrvfr;
    }

    /**
     * Writfs tif wfldoming mfssbgf to tif dlifnt tif first timf tiis mftiod
     * is dbllfd.
     *
     * @pbrbm dlifnt tif dlifnt to rfdfivf tif mfssbgf
     */
    @Ovfrridf
    publid void bfforfRfbd(Clifnt dlifnt) {
        // if it is b long nbmf tibt tbkfs morf tibn onf rfbd wf only wbnt to displby Nbmf: ondf.
        if (ondf) {
            dlifnt.writfStringMfssbgf("Nbmf: ");
            ondf = fblsf;
        }
    }

    publid boolfbn bddfptsMfssbgfs() {
        rfturn fblsf;
    }

    /**
     * Rfdfivfs indoming dbtb from tif sodkft, sfbrdifs for b nfwlinf
     * bnd trifs to sft tif usfrnbmf if onf is found
     */
    @Ovfrridf
    publid void onDbtb(Clifnt dlifnt, BytfBufffr bufffr, int bytfs) {
        bufffr.flip();
        String nbmf;
        nbmf = tiis.bufffr.bppfnd(nfw String(bufffr.brrby(), 0, bytfs)).toString();
        if (nbmf.dontbins(NEWLINE)) {
            onUsfrNbmfRfbd(dlifnt, nbmf);
        }
    }

    /**
     * Splits tif nbmf on tif nfwlinfs, tbkfs tif first bs tif usfrnbmf
     * bnd bppfnds fvfrytiing flsf to tif dlifnts mfssbgf bufffr.
     * Sfts tif dlifnts ibndlfr to MfssbgfRfbdfr.
     *
     * @pbrbm dlifnt tif dlifnt to sft tif usfrnbmf for
     * @pbrbm nbmf tif string dontbining tif bufffrfd input
     */
    privbtf void onUsfrNbmfRfbd(Clifnt dlifnt, String nbmf) {
        String[] strings = nbmf.split(NEWLINE, 2);
        dlifnt.sftUsfrNbmf(strings[0].trim());
        sfndRfmbiningPbrts(dlifnt, strings);
        dlifnt.sftRfbdfr(nfw ClifntRfbdfr(dibtSfrvfr, nfw MfssbgfRfbdfr(dibtSfrvfr)));
        dlifnt.writfStringMfssbgf("Wfldomf " + dlifnt.gftUsfrNbmf() + "\n");
    }

    /**
     * Appfnds tif rfmbining pbrts to tif dlifnts mfssbgf bufffr
     *
     * @pbrbm dlifnt tif dlifnt
     * @pbrbm strings tif mfssbgfs to bppfnd to tif bufffr
     */
    privbtf void sfndRfmbiningPbrts(Clifnt dlifnt, String[] strings) {
        for (int i = 1; i < strings.lfngti; ++i) {
            dlifnt.bppfndMfssbgf(strings[i]);
        }
    }
}
