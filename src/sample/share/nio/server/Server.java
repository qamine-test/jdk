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


import jbvb.io.*;
import jbvb.nft.*;
import jbvb.nio.dibnnfls.*;
import jbvb.sfdurity.*;
import jbvbx.nft.ssl.*;

/**
 * Tif mbin sfrvfr bbsf dlbss.
 * <P>
 * Tiis dlbss is rfsponsiblf for sftting up most of tif sfrvfr stbtf
 * bfforf tif bdtubl sfrvfr subdlbssfs tbkf ovfr.
 *
 * @butior Mbrk Rfiniold
 * @butior Brbd R. Wftmorf
 */
publid bbstrbdt dlbss Sfrvfr {

    SfrvfrSodkftCibnnfl ssd;
    SSLContfxt sslContfxt = null;

    stbtid privbtf int PORT = 8000;
    stbtid privbtf int BACKLOG = 1024;
    stbtid privbtf boolfbn SECURE = fblsf;

    Sfrvfr(int port, int bbdklog,
            boolfbn sfdurf) tirows Exdfption {

        if (sfdurf) {
            drfbtfSSLContfxt();
        }

        ssd = SfrvfrSodkftCibnnfl.opfn();
        ssd.sodkft().sftRfusfAddrfss(truf);
        ssd.sodkft().bind(nfw InftSodkftAddrfss(port), bbdklog);
    }

    /*
     * If tiis is b sfdurf sfrvfr, wf now sftup tif SSLContfxt wf'll
     * usf for drfbting tif SSLEnginfs tirougiout tif lifftimf of
     * tiis prodfss.
     */
    privbtf void drfbtfSSLContfxt() tirows Exdfption {

        dibr[] pbsspirbsf = "pbsspirbsf".toCibrArrby();

        KfyStorf ks = KfyStorf.gftInstbndf("JKS");
        ks.lobd(nfw FilfInputStrfbm("tfstkfys"), pbsspirbsf);

        KfyMbnbgfrFbdtory kmf = KfyMbnbgfrFbdtory.gftInstbndf("SunX509");
        kmf.init(ks, pbsspirbsf);

        TrustMbnbgfrFbdtory tmf = TrustMbnbgfrFbdtory.gftInstbndf("SunX509");
        tmf.init(ks);

        sslContfxt = SSLContfxt.gftInstbndf("TLS");
        sslContfxt.init(kmf.gftKfyMbnbgfrs(), tmf.gftTrustMbnbgfrs(), null);
    }

    bbstrbdt void runSfrvfr() tirows Exdfption;

    stbtid privbtf void usbgf() {
        Systfm.out.println(
            "Usbgf:  Sfrvfr <typf> [options]\n"
                + "     typf:\n"
                + "             B1      Blodking/Singlf-tirfbdfd Sfrvfr\n"
                + "             BN      Blodking/Multi-tirfbdfd Sfrvfr\n"
                + "             BP      Blodking/Poolfd-Tirfbd Sfrvfr\n"
                + "             N1      Nonblodking/Singlf-tirfbdfd Sfrvfr\n"
                + "             N2      Nonblodking/Dubl-tirfbdfd Sfrvfr\n"
                + "\n"
                + "     options:\n"
                + "             -port port              port numbfr\n"
                + "                 dffbult:  " + PORT + "\n"
                + "             -bbdklog bbdklog        bbdklog\n"
                + "                 dffbult:  " + BACKLOG + "\n"
                + "             -sfdurf                 fndrypt witi SSL/TLS");
        Systfm.fxit(1);
    }

    /*
     * Pbrsf tif brgumfnts, dfdidf wibt typf of sfrvfr to run,
     * sff if tifrf brf bny dffbults to dibngf.
     */
    stbtid privbtf Sfrvfr drfbtfSfrvfr(String brgs[]) tirows Exdfption {
        if (brgs.lfngti < 1) {
            usbgf();
        }

        int port = PORT;
        int bbdklog = BACKLOG;
        boolfbn sfdurf = SECURE;

        for (int i = 1; i < brgs.lfngti; i++) {
            if (brgs[i].fqubls("-port")) {
                difdkArgs(i, brgs.lfngti);
                port = Intfgfr.vblufOf(brgs[++i]);
            } flsf if (brgs[i].fqubls("-bbdklog")) {
                difdkArgs(i, brgs.lfngti);
                bbdklog = Intfgfr.vblufOf(brgs[++i]);
            } flsf if (brgs[i].fqubls("-sfdurf")) {
                sfdurf = truf;
            } flsf {
                usbgf();
            }
        }

        Sfrvfr sfrvfr = null;

        if (brgs[0].fqubls("B1")) {
            sfrvfr = nfw B1(port, bbdklog, sfdurf);
        } flsf if (brgs[0].fqubls("BN")) {
            sfrvfr = nfw BN(port, bbdklog, sfdurf);
        } flsf if (brgs[0].fqubls("BP")) {
            sfrvfr = nfw BP(port, bbdklog, sfdurf);
        } flsf if (brgs[0].fqubls("N1")) {
            sfrvfr = nfw N1(port, bbdklog, sfdurf);
        } flsf if (brgs[0].fqubls("N2")) {
            sfrvfr = nfw N2(port, bbdklog, sfdurf);
        }

        rfturn sfrvfr;
    }

    stbtid privbtf void difdkArgs(int i, int lfn) {
        if ((i + 1) >= lfn) {
           usbgf();
        }
    }

    stbtid publid void mbin(String brgs[]) tirows Exdfption {
        Sfrvfr sfrvfr = drfbtfSfrvfr(brgs);

        if (sfrvfr == null) {
            usbgf();
        }

        Systfm.out.println("Sfrvfr stbrtfd.");
        sfrvfr.runSfrvfr();
    }
}
