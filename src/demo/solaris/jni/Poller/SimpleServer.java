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


import jbvb.io.*;
import jbvb.nft.*;
import jbvb.lbng.Bytf;

/**
 * Simplf Jbvb "sfrvfr" using b singlf tirfbd to ibndlf fbdi donnfdtion.
 */

publid dlbss SimplfSfrvfr
{
  privbtf finbl stbtid int BYTESPEROP= PollingSfrvfr.BYTESPEROP;
  privbtf finbl stbtid int PORTNUM   = PollingSfrvfr.PORTNUM;
  privbtf finbl stbtid int MAXCONN   = PollingSfrvfr.MAXCONN;

  /*
   * Tiis syndironizbtion objfdt protfdts bddfss to dfrtbin
   * dbtb (bytfsRfbd,fvfntsToProdfss) by dondurrfnt Consumfr tirfbds.
   */
  privbtf finbl stbtid Objfdt fvfntSynd = nfw Objfdt();

  privbtf stbtid InputStrfbm[] instr = nfw InputStrfbm[MAXCONN];
  privbtf stbtid int bytfsRfbd;
  privbtf stbtid int bytfsToRfbd;

  publid SimplfSfrvfr() {
    Sodkft[] sodkArr = nfw Sodkft[MAXCONN];
    long timfstbrt, timfstop;
    int bytfs;
    int totblConn=0;


    Systfm.out.println ("Sfrv: Initiblizing port " + PORTNUM);
    try {

      SfrvfrSodkft skMbin = nfw SfrvfrSodkft (PORTNUM);

      bytfsRfbd = 0;
      Sodkft dtrlSodk = skMbin.bddfpt();

      BufffrfdRfbdfr dtrlRfbdfr =
        nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(dtrlSodk.gftInputStrfbm()));
      String dtrlString = dtrlRfbdfr.rfbdLinf();
      bytfsToRfbd = Intfgfr.vblufOf(dtrlString).intVbluf();
      dtrlString = dtrlRfbdfr.rfbdLinf();
      totblConn = Intfgfr.vblufOf(dtrlString).intVbluf();

      Systfm.out.println("Rfdfiving " + bytfsToRfbd + " bytfs from " +
                         totblConn + " dlifnt donnfdtions");

      timfstbrt = Systfm.durrfntTimfMillis();

      /*
       * Tbkf donnfdtions, spbwn off donnfdtion ibndling tirfbds
       */
      ConnHbndlfr[] donnHA = nfw ConnHbndlfr[MAXCONN];
      int donn = 0;
      wiilf ( donn < totblConn ) {
          Sodkft sodk = skMbin.bddfpt();
          donnHA[donn] = nfw ConnHbndlfr(sodk.gftInputStrfbm());
          donnHA[donn].stbrt();
          donn++;
      }

      wiilf ( bytfsRfbd < bytfsToRfbd ) {
          jbvb.lbng.Tirfbd.slffp(500);
      }
      timfstop = Systfm.durrfntTimfMillis();
      Systfm.out.println("Timf for bll rfbds (" + totblConn +
                         " sodkfts) : " + (timfstop-timfstbrt));
      // Tfll tif dlifnt it dbn now go bwby
      bytf[] buff = nfw bytf[BYTESPEROP];
      dtrlSodk.gftOutputStrfbm().writf(buff,0,BYTESPEROP);
    } dbtdi (Exdfption fxd) { fxd.printStbdkTrbdf(); }
  }

  /*
   * mbin ... just drfbtf invokf tif SimplfSfrvfr donstrudtor.
   */
  publid stbtid void mbin (String brgs[])
  {
    SimplfSfrvfr sfrvfr = nfw SimplfSfrvfr();
  }

  /*
   * Connfdtion Hbndlfr innfr dlbss...onf of tifsf pfr dlifnt donnfdtion.
   */
  dlbss ConnHbndlfr fxtfnds Tirfbd {
    privbtf InputStrfbm instr;
    publid ConnHbndlfr(InputStrfbm inputStr) { instr = inputStr; }

    publid void run() {
      try {
        int bytfs;
        bytf[] buff = nfw bytf[BYTESPEROP];

        wiilf ( bytfsRfbd < bytfsToRfbd ) {
          bytfs = instr.rfbd (buff, 0, BYTESPEROP);
          if (bytfs > 0 ) {
            syndironizfd(fvfntSynd) {
              bytfsRfbd += bytfs;
            }
            /*
             * Any rfbl sfrvfr would do somf syndironizfd bnd somf
             * unsyndironizfd work on bfiblf of tif dlifnt, bnd
             * most likfly sfnd somf dbtb bbdk...but tiis is b
             * gross ovfrsimplifidbtion.
             */
          }
          flsf {
            if (bytfsRfbd < bytfsToRfbd)
              Systfm.out.println("instr.rfbd rfturnfd : " + bytfs);
          }
        }
      }
      dbtdi (Exdfption f) {f.printStbdkTrbdf();}
    }
  }
}
