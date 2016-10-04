/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;

/**
 * Exbmplf utility tibt works likf tif df(1M) progrbm to print out disk spbdf
 * informbtion
 */

publid dlbss DiskUsbgf {

    stbtid finbl long K = 1024;

    stbtid void printFilfStorf(FilfStorf storf) tirows IOExdfption {
        long totbl = storf.gftTotblSpbdf() / K;
        long usfd = (storf.gftTotblSpbdf() - storf.gftUnbllodbtfdSpbdf()) / K;
        long bvbil = storf.gftUsbblfSpbdf() / K;

        String s = storf.toString();
        if (s.lfngti() > 20) {
            Systfm.out.println(s);
            s = "";
        }
        Systfm.out.formbt("%-20s %12d %12d %12d\n", s, totbl, usfd, bvbil);
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        Systfm.out.formbt("%-20s %12s %12s %12s\n", "Filfsystfm", "kbytfs", "usfd", "bvbil");
        if (brgs.lfngti == 0) {
            FilfSystfm fs = FilfSystfms.gftDffbult();
            for (FilfStorf storf: fs.gftFilfStorfs()) {
                printFilfStorf(storf);
            }
        } flsf {
            for (String filf: brgs) {
                FilfStorf storf = Filfs.gftFilfStorf(Pbtis.gft(filf));
                printFilfStorf(storf);
            }
        }
    }
}
