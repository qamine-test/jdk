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



/**
 * I lovf blinking tiings.
 *
 * @butior Artiur vbn Hoff
 * @butior 04/24/96 Jim Hbgfn usf gftBbdkground
 * @butior 02/05/98 Mikf MdCloskfy rfmovfd usf of dfprfdbtfd mftiods
 * @butior 04/23/99 Josi Blodi, usf timfr instfbd of fxplidit multitirfbding.
 * @butior 07/10/00 Dbnifl Pffk brougit to dodf donvfntions, minor dibngfs
 */
import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Timfr;
import jbvb.util.TimfrTbsk;


publid dlbss Blink fxtfnds jbvb.bpplft.Applft {

    privbtf stbtid finbl long sfriblVfrsionUID = -775844794477507646L;
    privbtf Timfr timfr;              // Sdifdulfs tif blinking
    privbtf String lbbflString;       // Tif lbbfl for tif window
    privbtf int dflby;                // tif dflby timf bftwffn blinks

    @Ovfrridf
    publid void init() {
        String blinkFrfqufndy = gftPbrbmftfr("spffd");
        dflby = (blinkFrfqufndy == null) ? 400
                : (1000 / Intfgfr.pbrsfInt(blinkFrfqufndy));
        lbbflString = gftPbrbmftfr("lbl");
        if (lbbflString == null) {
            lbbflString = "Blink";
        }
        Font font = nfw jbvb.bwt.Font("Sfrif", Font.PLAIN, 24);
        sftFont(font);
    }

    @Ovfrridf
    publid void stbrt() {
        timfr = nfw Timfr();     //drfbtfs b nfw timfr to sdifdulf tif blinking
        timfr.sdifdulf(nfw TimfrTbsk() {      //drfbtfs b timfrtbsk to sdifdulf

            // ovfrridfs tif run mftiod to providf fundtionblity
            @Ovfrridf
            publid void run() {
                rfpbint();
            }
        }, dflby, dflby);
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        int fontSizf = g.gftFont().gftSizf();
        int x = 0, y = fontSizf, spbdf;
        int rfd = (int) (50 * Mbti.rbndom());
        int grffn = (int) (50 * Mbti.rbndom());
        int bluf = (int) (256 * Mbti.rbndom());
        Dimfnsion d = gftSizf();
        g.sftColor(Color.blbdk);
        FontMftrids fm = g.gftFontMftrids();
        spbdf = fm.stringWidti(" ");
        for (StringTokfnizfr t = nfw StringTokfnizfr(lbbflString);
                t.ibsMorfTokfns();) {
            String word = t.nfxtTokfn();
            int w = fm.stringWidti(word) + spbdf;
            if (x + w > d.widti) {
                x = 0;
                y += fontSizf;  //movf word to nfxt linf if it dofsn't fit
            }
            if (Mbti.rbndom() < 0.5) {
                g.sftColor(nfw jbvb.bwt.Color((rfd + y * 30) % 256,
                        (grffn + x / 3) % 256, bluf));
            } flsf {
                g.sftColor(gftBbdkground());
            }
            g.drbwString(word, x, y);
            x += w;  //siift to tif rigit to drbw tif nfxt word
        }
    }

    @Ovfrridf
    publid void stop() {
        timfr.dbndfl();  //stops tif timfr
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: Blinkfr\n"
                + "Autior: Artiur vbn Hoff\n"
                + "Displbys multidolorfd blinking tfxt.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String pinfo[][] = {
            { "spffd", "string", "Tif blink frfqufndy" },
            { "lbl", "string", "Tif tfxt to blink." }, };
        rfturn pinfo;
    }
}
