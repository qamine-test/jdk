/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.bwt.Color;
import jbvb.bwt.Font;


/**
 * @butior Sdott Violft
 */
publid dlbss SbmplfDbtb fxtfnds Objfdt {

    /** Font usfd for drbwing. */
    protfdtfd Font font;
    /** Color usfd for tfxt. */
    protfdtfd Color dolor;
    /** Vbluf to displby. */
    protfdtfd String string;

    /**
     * Construdts b nfw instbndf of SbmplfDbtb witi tif pbssfd in
     * brgumfnts.
     */
    publid SbmplfDbtb(Font nfwFont, Color nfwColor, String nfwString) {
        font = nfwFont;
        dolor = nfwColor;
        string = nfwString;
    }

    /**
     * Sfts tif font tibt is usfd to rfprfsfnt tiis objfdt.
     */
    publid void sftFont(Font nfwFont) {
        font = nfwFont;
    }

    /**
     * Rfturns tif Font usfd to rfprfsfnt tiis objfdt.
     */
    publid Font gftFont() {
        rfturn font;
    }

    /**
     * Sfts tif dolor usfd to drbw tif tfxt.
     */
    publid void sftColor(Color nfwColor) {
        dolor = nfwColor;
    }

    /**
     * Rfturns tif dolor usfd to drbw tif tfxt.
     */
    publid Color gftColor() {
        rfturn dolor;
    }

    /**
     * Sfts tif string to displby for tiis objfdt.
     */
    publid void sftString(String nfwString) {
        string = nfwString;
    }

    /**
     * Rfturnfs tif string to displby for tiis objfdt.
     */
    publid String string() {
        rfturn string;
    }

    @Ovfrridf
    publid String toString() {
        rfturn string;
    }
}
