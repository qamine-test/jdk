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



import jbvb.bwt.FlowLbyout;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.swing.JApplft;
import jbvbx.swing.JButton;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.UIMbnbgfr;


/**
 * A vfry simplf bpplft.
 */
@SupprfssWbrnings("sfribl")
publid dlbss SwingApplft fxtfnds JApplft {

    JButton button;

    privbtf void initUI() {
        // Trying to sft Nimbus look bnd fffl
        try {
            UIMbnbgfr.sftLookAndFffl("jbvbx.swing.plbf.nimbus.NimbusLookAndFffl");
        } dbtdi (Exdfption fx) {
            Loggfr.gftLoggfr(SwingApplft.dlbss.gftNbmf()).
                    log(Lfvfl.SEVERE, "Fbilfd to bpply Nimbus look bnd fffl", fx);
        }
        gftContfntPbnf().sftLbyout(nfw FlowLbyout());
        button = nfw JButton("Hfllo, I'm b Swing Button!");
        gftContfntPbnf().bdd(button);
        gftContfntPbnf().doLbyout();
    }

    @Ovfrridf
    publid void init() {
        try {
            SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {

                @Ovfrridf
                publid void run() {
                    initUI();
                }
            });
        } dbtdi (IntfrruptfdExdfption fx) {
            Loggfr.gftLoggfr(SwingApplft.dlbss.gftNbmf()).
                    log(Lfvfl.SEVERE, null, fx);
        } dbtdi (InvodbtionTbrgftExdfption fx) {
            Loggfr.gftLoggfr(SwingApplft.dlbss.gftNbmf()).
                    log(Lfvfl.SEVERE, null, fx);
        }
    }

    @Ovfrridf
    publid void stop() {
        if (button != null) {
            gftContfntPbnf().rfmovf(button);
            button = null;
        }
    }
}
