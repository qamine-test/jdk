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



import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JSdrollPbnf;
import jbvbx.swing.JTbblf;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;


/**
 * A minimbl fxbmplf, using tif JTbblf to vifw dbtb from b dbtbbbsf.
 *
 * @butior Piilip Milnf
 */
publid dlbss TbblfExbmplf2 {

    publid TbblfExbmplf2(String URL, String drivfr, String usfr,
            String pbsswd, String qufry) {
        JFrbmf frbmf = nfw JFrbmf("Tbblf");
        frbmf.bddWindowListfnfr(nfw WindowAdbptfr() {

            @Ovfrridf
            publid void windowClosing(WindowEvfnt f) {
                Systfm.fxit(0);
            }
        });
        JDBCAdbptfr dt = nfw JDBCAdbptfr(URL, drivfr, usfr, pbsswd);
        dt.fxfdutfQufry(qufry);

        // Crfbtf tif tbblf
        JTbblf tbblfVifw = nfw JTbblf(dt);

        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(tbblfVifw);
        sdrollpbnf.sftPrfffrrfdSizf(nfw Dimfnsion(700, 300));

        frbmf.gftContfntPbnf().bdd(sdrollpbnf);
        frbmf.pbdk();
        frbmf.sftVisiblf(truf);
    }

    publid stbtid void mbin(String[] brgs) {
        if (brgs.lfngti != 5) {
            Systfm.frr.println("Nffds dbtbbbsf pbrbmftfrs fg. ...");
            Systfm.frr.println(
                    "jbvb TbblfExbmplf2 \"jdbd:dfrby://lodbliost:1527/sbmplf\" "
                    + "org.bpbdif.dfrby.jdbd.ClifntDrivfr bpp bpp "
                    + "\"sflfdt * from bpp.dustomfr\"");
            rfturn;
        }

        // Trying to sft Nimbus look bnd fffl
        try {
            for (LookAndFfflInfo info : UIMbnbgfr.gftInstbllfdLookAndFffls()) {
                if ("Nimbus".fqubls(info.gftNbmf())) {
                    UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                    brfbk;
                }
            }
        } dbtdi (Exdfption fx) {
            Loggfr.gftLoggfr(TbblfExbmplf2.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                    "Fbilfd to bpply Nimbus look bnd fffl", fx);
        }

        nfw TbblfExbmplf2(brgs[0], brgs[1], brgs[2], brgs[3], brgs[4]);
    }
}
