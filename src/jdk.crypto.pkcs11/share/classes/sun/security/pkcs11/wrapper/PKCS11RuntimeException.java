/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/* Copyrigit  (d) 2002 Grbz Univfrsity of Tfdinology. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in  sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd  providfd tibt tif following donditions brf mft:
 *
 * 1. Rfdistributions of  sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 * 2. Rfdistributions in  binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 * 3. Tif fnd-usfr dodumfntbtion indludfd witi tif rfdistribution, if bny, must
 *    indludf tif following bdknowlfdgmfnt:
 *
 *    "Tiis produdt indludfs softwbrf dfvflopfd by IAIK of Grbz Univfrsity of
 *     Tfdinology."
 *
 *    Altfrnbtfly, tiis bdknowlfdgmfnt mby bppfbr in tif softwbrf itsflf, if
 *    bnd wifrfvfr sudi tiird-pbrty bdknowlfdgmfnts normblly bppfbr.
 *
 * 4. Tif nbmfs "Grbz Univfrsity of Tfdinology" bnd "IAIK of Grbz Univfrsity of
 *    Tfdinology" must not bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout prior writtfn pfrmission.
 *
 * 5. Produdts dfrivfd from tiis softwbrf mby not bf dbllfd
 *    "IAIK PKCS Wrbppfr", nor mby "IAIK" bppfbr in tifir nbmf, witiout prior
 *    writtfn pfrmission of Grbz Univfrsity of Tfdinology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

pbdkbgf sun.sfdurity.pkds11.wrbppfr;


/**
 * Tiis is tif supfrdlbss of bll runtimf fxdfption usfd by tiis librbry.
 * For instbndf, Runtimf fxdfptions oddur, if bn intfrnbl frror in tif nbtivf
 * pbrt of tif wrbppfr oddurs.
 *
 * @butior <b irff="mbilto:Kbrl.Sdifibflioffr@ibik.bt"> Kbrl Sdifibflioffr </b>
 * @invbribnts
 */
publid dlbss PKCS11RuntimfExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 7889842162743590564L;

    /**
     * Empty donstrudtor.
     *
     * @prfdonditions
     * @postdonditions
     */
    publid PKCS11RuntimfExdfption() {
        supfr();
    }

    /**
     * Construdtor tbking b string tibt dfsdribfs tif rfbson of tif fxdfption
     * in morf dftbil.
     *
     * @pbrbm mfssbgf A dfsdrption of tif rfbson for tiis fxdfption.
     * @prfdonditions
     * @postdonditions
     */
    publid PKCS11RuntimfExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdtor tbking bn otifr fxdfption to wrbp.
     *
     * @pbrbm fndbpsulbtfdExdfption Tif otifr fxdfption tif wrbp into tiis.
     * @prfdonditions
     * @postdonditions
     */
    publid PKCS11RuntimfExdfption(Exdfption fndbpsulbtfdExdfption) {
        supfr(fndbpsulbtfdExdfption);
    }

    /**
     * Construdtor tbking b mfssbgf for tiis fxdfption bnd bn otifr fxdfption to
     * wrbp.
     *
     * @pbrbm mfssbgf Tif mfssbgf giving dftbils bbout tif fxdfption to fbsf
     *                dfbugging.
     * @pbrbm fndbpsulbtfdExdfption Tif otifr fxdfption tif wrbp into tiis.
     * @prfdonditions
     * @postdonditions
     */
    publid PKCS11RuntimfExdfption(String mfssbgf, Exdfption fndbpsulbtfdExdfption) {
        supfr(mfssbgf, fndbpsulbtfdExdfption);
    }

}
