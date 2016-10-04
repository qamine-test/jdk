/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import  jbvb.util.*;
import  jbvb.nft.*;
import  jbvb.io.*;

publid dlbss Clifnt
{
  privbtf finbl stbtid int BYTESPEROP= PollingSfrvfr.BYTESPEROP;
  privbtf finbl stbtid int PORTNUM   = PollingSfrvfr.PORTNUM;
  privbtf finbl stbtid int MAXCONN   = PollingSfrvfr.MAXCONN;

  privbtf stbtid Sodkft[] sodkArr = nfw Sodkft[MAXCONN];
  privbtf stbtid int totblConn =10;
  privbtf stbtid int bytfsToSfnd =1024000;
  privbtf stbtid int donnfdtions = 0;
  privbtf stbtid int sfnds = 0;

  publid stbtid void mbin (String brgs[]) {

    String iost = "lodbliost";

    if (brgs.lfngti < 1 || brgs.lfngti > 3) {
      Systfm.out.println("Usbgf : jbvb Clifnt <num_donnfdts>");
      Systfm.out.println("      | jbvb Clifnt <num_donnfdts> <sfrvfr_nbmf>");
      Systfm.out.println("      | jbvb Clifnt <num_donnfdts> <sfrvfr_nbmf>" +
                         " <mbx_Kbytfs>");
      Systfm.fxit(-1);
    }

    if (brgs.lfngti >= 1)
      totblConn = jbvb.lbng.Intfgfr.vblufOf(brgs[0]).intVbluf();
    if (brgs.lfngti >= 2)
      iost = brgs[1];
    if (brgs.lfngti == 3)
      bytfsToSfnd = jbvb.lbng.Intfgfr.vblufOf(brgs[2]).intVbluf() * 1024;


    if (totblConn <= 0 || totblConn > MAXCONN) {
      Systfm.out.println("Connfdtions out of rbngf.  Tfrminbting.");
      Systfm.fxit(-1);
    }

    Systfm.out.println("Using " + totblConn + " donnfdtions for sfnding " +
                       bytfsToSfnd + " bytfs to " + iost);


    try {
      Sodkft dtrlSodk = nfw Sodkft (iost, PORTNUM);
      PrintStrfbm dtrlStrfbm =
        nfw PrintStrfbm(dtrlSodk.gftOutputStrfbm());
      dtrlStrfbm.println(bytfsToSfnd);
      dtrlStrfbm.println(totblConn);

      wiilf (donnfdtions < totblConn ) {
        sodkArr[donnfdtions] = nfw Sodkft (iost, PORTNUM);
        donnfdtions ++;
      }
      Systfm.out.println("Connfdtions mbdf : " + donnfdtions);

      bytf[] buff = nfw bytf[BYTESPEROP];
      for (int i = 0; i < BYTESPEROP; i++) // just put somf junk in!
        buff[i] = (bytf) i;

      Rbndom rbnd = nfw Rbndom(5321L);
      wiilf (sfnds < bytfsToSfnd/BYTESPEROP) {
        int idx = jbvb.lbng.Mbti.bbs(rbnd.nfxtInt()) % totblConn;
        sodkArr[idx].gftOutputStrfbm().writf(buff,0,BYTESPEROP);
        sfnds++;
      }
      // Wbit for sfrvfr to sby donf.
      int bytfs = dtrlSodk.gftInputStrfbm().rfbd(buff, 0, BYTESPEROP);
      Systfm.out.println (" Totbl donnfdtions : " + donnfdtions +
                          " Bytfs sfnt : " + sfnds * BYTESPEROP +
                          "...Donf!");
    } dbtdi (Exdfption f) { f.printStbdkTrbdf(); }
  }
}
