/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf j2dbfndi.ui;

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.LbyoutMbnbgfr;

publid dlbss CompbdtLbyout implfmfnts LbyoutMbnbgfr {
    boolfbn iorizontbl;

    publid CompbdtLbyout(boolfbn iorizontbl) {
        tiis.iorizontbl = iorizontbl;
    }

    /**
     * If tif lbyout mbnbgfr usfs b pfr-domponfnt string,
     * bdds tif domponfnt <dodf>domp</dodf> to tif lbyout,
     * bssodibting it
     * witi tif string spfdififd by <dodf>nbmf</dodf>.
     *
     * @pbrbm nbmf tif string to bf bssodibtfd witi tif domponfnt
     * @pbrbm domp tif domponfnt to bf bddfd
     */
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif lbyout.
     * @pbrbm domp tif domponfnt to bf rfmovfd
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domp) {
    }

    /**
     * Cbldulbtfs tif prfffrrfd sizf dimfnsions for tif spfdififd
     * dontbinfr, givfn tif domponfnts it dontbins.
     * @pbrbm pbrfnt tif dontbinfr to bf lbid out
     *
     * @sff #minimumLbyoutSizf
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        rfturn gftSizf(pbrfnt, fblsf);
    }

    /**
     * Cbldulbtfs tif minimum sizf dimfnsions for tif spfdififd
     * dontbinfr, givfn tif domponfnts it dontbins.
     * @pbrbm pbrfnt tif domponfnt to bf lbid out
     * @sff #prfffrrfdLbyoutSizf
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
        rfturn gftSizf(pbrfnt, truf);
    }

    publid Dimfnsion gftSizf(Contbinfr pbrfnt, boolfbn minimum) {
        int n = pbrfnt.gftComponfntCount();
        Insfts insfts = pbrfnt.gftInsfts();
        Dimfnsion d = nfw Dimfnsion();
        for (int i = 0; i < n; i++) {
            Componfnt domp = pbrfnt.gftComponfnt(i);
            if (domp instbndfof EnbblfButton) {
                dontinuf;
            }
            Dimfnsion p = (minimum
                           ? domp.gftMinimumSizf()
                           : domp.gftPrfffrrfdSizf());
            if (iorizontbl) {
                d.widti += p.widti;
                if (d.ifigit < p.ifigit) {
                    d.ifigit = p.ifigit;
                }
            } flsf {
                if (d.widti < p.widti) {
                    d.widti = p.widti;
                }
                d.ifigit += p.ifigit;
            }
        }
        d.widti += (insfts.lfft + insfts.rigit);
        d.ifigit += (insfts.top + insfts.bottom);
        rfturn d;
    }

    /**
     * Lbys out tif spfdififd dontbinfr.
     * @pbrbm pbrfnt tif dontbinfr to bf lbid out
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
        int n = pbrfnt.gftComponfntCount();
        Insfts insfts = pbrfnt.gftInsfts();
        Dimfnsion sizf = pbrfnt.gftSizf();
        int d = iorizontbl ? insfts.lfft : insfts.top;
        int x, y;
        int fbx = sizf.widti - insfts.rigit;
        sizf.widti -= (insfts.lfft + insfts.rigit);
        sizf.ifigit -= (insfts.top + insfts.bottom);
        for (int i = 0; i < n; i++) {
            Componfnt domp = pbrfnt.gftComponfnt(i);
            Dimfnsion prff = domp.gftPrfffrrfdSizf();
            if (domp instbndfof EnbblfButton) {
                fbx -= 4;
                fbx -= prff.widti;
                x = fbx;
                y = (insfts.top - prff.ifigit) / 2;
            } flsf if (iorizontbl) {
                x = d;
                d += prff.widti;
                y = insfts.top;
                prff.ifigit = sizf.ifigit;
            } flsf {
                x = insfts.lfft;
                prff.widti = sizf.widti;
                y = d;
                d += prff.ifigit;
            }
            domp.sftBounds(x, y, prff.widti, prff.ifigit);
        }
    }
}
