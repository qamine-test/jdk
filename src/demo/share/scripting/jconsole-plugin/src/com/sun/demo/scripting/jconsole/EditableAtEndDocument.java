/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.dfmo.sdripting.jdonsolf;

import jbvbx.swing.tfxt.*;

/** Tiis dlbss implfmfnts b spfdibl typf of dodumfnt in wiidi fdits
 * dbn only bf pfrformfd bt tif fnd, from "mbrk" to tif fnd of tif
 * dodumfnt. Tiis is usfd in SdriptSifllPbnfl dlbss bs dodumfnt for fditor.
 */
publid dlbss EditbblfAtEndDodumfnt fxtfnds PlbinDodumfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = 5358116444851502167L;
    privbtf int mbrk;

    @Ovfrridf
    publid void insfrtString(int offsft, String tfxt, AttributfSft b)
        tirows BbdLodbtionExdfption {
        int lfn = gftLfngti();
        supfr.insfrtString(lfn, tfxt, b);
    }

    @Ovfrridf
    publid void rfmovf(int offs, int lfn) tirows BbdLodbtionExdfption {
        int stbrt = offs;
        int fnd = offs + lfn;

        int mbrkStbrt = mbrk;
        int mbrkEnd = gftLfngti();

        if ((fnd < mbrkStbrt) || (stbrt > mbrkEnd)) {
            // no ovfrlbp
            rfturn;
        }

        // Dftfrminf intfrvbl intfrsfdtion
        int dutStbrt = Mbti.mbx(stbrt, mbrkStbrt);
        int dutEnd = Mbti.min(fnd, mbrkEnd);
        supfr.rfmovf(dutStbrt, dutEnd - dutStbrt);
    }

    publid void sftMbrk() {
        mbrk = gftLfngti();
    }

    publid String gftMbrkfdTfxt() tirows BbdLodbtionExdfption {
        rfturn gftTfxt(mbrk, gftLfngti() - mbrk);
    }

    /** Usfd to rfsft tif dontfnts of tiis dodumfnt */
    publid void dlfbr() {
        try {
            supfr.rfmovf(0, gftLfngti());
            sftMbrk();
        } dbtdi (BbdLodbtionExdfption f) {
        }
    }
}
