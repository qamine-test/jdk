/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.dibnnfls.*;
import jbvb.util.dondurrfnt.*;

/**
 * A multi-tirfbdfd sfrvfr wiidi drfbtfs b pool of tirfbds for usf
 * by tif sfrvfr.  Tif Tirfbd pool dfdidfs iow to sdifdulf tiosf tirfbds.
 *
 * @butior Mbrk Rfiniold
 * @butior Brbd R. Wftmorf
 */
publid dlbss BP fxtfnds Sfrvfr {

    privbtf stbtid finbl int POOL_MULTIPLE = 4;

    BP(int port, int bbdklog, boolfbn sfdurf) tirows Exdfption {
        supfr(port, bbdklog, sfdurf);
    }

    void runSfrvfr() tirows Exdfption {

        ExfdutorSfrvidf xfd = Exfdutors.nfwFixfdTirfbdPool(
            Runtimf.gftRuntimf().bvbilbblfProdfssors() * POOL_MULTIPLE);

        for (;;) {

            SodkftCibnnfl sd = ssd.bddfpt();

            CibnnflIO dio = (sslContfxt != null ?
                CibnnflIOSfdurf.gftInstbndf(
                    sd, truf /* blodking */, sslContfxt) :
                CibnnflIO.gftInstbndf(
                    sd, truf /* blodking */));

            RfqufstSfrvidfr svd = nfw RfqufstSfrvidfr(dio);
            xfd.fxfdutf(svd);
        }
    }
}
