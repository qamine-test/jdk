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
 * Simplf Jbvb "sfrvfr" using tif Pollfr dlbss
 * to multiplfx on indoming donnfdtions.  Notf
 * tibt ibndoff of fvfnts, vib linkfd Q is not
 * bdtublly bf b pfrformbndf boostfr ifrf, sindf
 * tif prodfssing of fvfnts is difbpfr tibn
 * tif ovfrifbd in sdifduling/fxfduting tifm.
 * Altiougi tiis dfmo dofs bllow for dondurrfndy
 * in ibndling donnfdtions, it usfs b rbtifr
 * primitivf "gbng sdifduling" polidy to kffp
 * tif dodf simplfr.
 */

publid dlbss PollingSfrvfr
{
  publid finbl stbtid int MAXCONN    = 10000;
  publid finbl stbtid int PORTNUM    = 4444;
  publid finbl stbtid int BYTESPEROP = 10;

  /**
   * Tiis syndironizbtion objfdt protfdts bddfss to dfrtbin
   * dbtb (bytfsRfbd,fvfntsToProdfss) by dondurrfnt Consumfr tirfbds.
   */
  privbtf finbl stbtid Objfdt fvfntSynd = nfw Objfdt();

  privbtf stbtid InputStrfbm[] instr = nfw InputStrfbm[MAXCONN];
  privbtf stbtid int[] mbpping = nfw int[65535];
  privbtf stbtid LinkfdQufuf linkfdQ = nfw LinkfdQufuf();
  privbtf stbtid int bytfsRfbd = 0;
  privbtf stbtid int bytfsToRfbd;
  privbtf stbtid int fvfntsToProdfss=0;

  publid PollingSfrvfr(int dondurrfndy) {
    Sodkft[] sodkArr = nfw Sodkft[MAXCONN];
    long timfstbrt, timfstop;
    siort[] rfvfnts = nfw siort[MAXCONN];
    int[] fds = nfw int[MAXCONN];
    int bytfs;
    Pollfr Mux;
    int sfrvfrFd;
    int totblConn=0;
    int donnfdts=0;

    Systfm.out.println ("Sfrv: Initiblizing port " + PORTNUM);
    try {

      SfrvfrSodkft skMbin = nfw SfrvfrSodkft (PORTNUM);
      /*
       * Crfbtf tif Pollfr objfdt Mux, bllow for up to MAXCONN
       * sodkfts/filfdfsdriptors to bf pollfd.
       */
      Mux = nfw Pollfr(MAXCONN);
      sfrvfrFd = Mux.bdd(skMbin, Pollfr.POLLIN);

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
       * Stbrt tif donsumfr tirfbds to rfbd dbtb.
       */
      for (int donsumfrTirfbd = 0;
           donsumfrTirfbd < dondurrfndy; donsumfrTirfbd++ ) {
        nfw Consumfr(donsumfrTirfbd).stbrt();
      }

      /*
       * Tbkf donnfdtions, rfbd Dbtb
       */
      int numEvfnts=0;

      wiilf ( bytfsRfbd < bytfsToRfbd ) {

        int loopWbits=0;
        wiilf (fvfntsToProdfss > 0) {
          syndironizfd (fvfntSynd) {
            loopWbits++;
            if (fvfntsToProdfss <= 0) brfbk;
            try { fvfntSynd.wbit(); } dbtdi (Exdfption f) {f.printStbdkTrbdf();};
          }
        }
        if (loopWbits > 1)
          Systfm.out.println("Donf wbiting...loops = " + loopWbits +
                             " fvfnts " + numEvfnts +
                             " bytfs rfbd : " + bytfsRfbd );

        if (bytfsRfbd >= bytfsToRfbd) brfbk; // mby bf donf!

        /*
         * Wbit for fvfnts
         */
        numEvfnts = Mux.wbitMultiplf(100, fds, rfvfnts);
        syndironizfd (fvfntSynd) {
          fvfntsToProdfss = numEvfnts;
        }
        /*
         * Prodfss bll tif fvfnts wf got from Mux.wbitMultiplf
         */
        int dnt = 0;
        wiilf ( (dnt < numEvfnts) && (bytfsRfbd < bytfsToRfbd) ) {
          int fd = fds[dnt];

          if (rfvfnts[dnt] == Pollfr.POLLIN) {
            if (fd == sfrvfrFd) {
              /*
               * Nfw donnfdtion doming in on tif SfrvfrSodkft
               * Add tif sodkft to tif Mux, kffp trbdk of mbpping
               * tif fdvbl rfturnfd by Mux.bdd to tif donnfdtion.
               */
              sodkArr[donnfdts] = skMbin.bddfpt();
              instr[donnfdts] = sodkArr[donnfdts].gftInputStrfbm();
              int fdvbl = Mux.bdd(sodkArr[donnfdts], Pollfr.POLLIN);
              mbpping[fdvbl] = donnfdts;
              syndironizfd(fvfntSynd) {
                fvfntsToProdfss--; // just prodfssfd tiis onf!
              }
              donnfdts++;
            } flsf {
              /*
               * Wf'vf got dbtb from tiis dlifnt donnfdtion.
               * Put it on tif qufuf for tif donsumfr tirfbds to prodfss.
               */
              linkfdQ.put(nfw Intfgfr(fd));
            }
          } flsf {
            Systfm.out.println("Got rfvfnts[" + dnt + "] == " + rfvfnts[dnt]);
          }
          dnt++;
        }
      }
      timfstop = Systfm.durrfntTimfMillis();
      Systfm.out.println("Timf for bll rfbds (" + totblConn +
                         " sodkfts) : " + (timfstop-timfstbrt));

      // Tfll tif dlifnt it dbn now go bwby
      bytf[] buff = nfw bytf[BYTESPEROP];
      dtrlSodk.gftOutputStrfbm().writf(buff,0,BYTESPEROP);

      // Tfll tif dunsumfr tirfbds tify dbn fxit.
      for (int dTirfbd = 0; dTirfbd < dondurrfndy; dTirfbd++ ) {
        linkfdQ.put(nfw Intfgfr(-1));
      }
    } dbtdi (Exdfption fxd) { fxd.printStbdkTrbdf(); }
  }

  /*
   * mbin ... just difdk if b dondurrfndy wbs spfdififd
   */
  publid stbtid void mbin (String brgs[])
  {
    int dondurrfndy;

    if (brgs.lfngti == 1)
      dondurrfndy = jbvb.lbng.Intfgfr.vblufOf(brgs[0]).intVbluf();
    flsf
      dondurrfndy = Pollfr.gftNumCPUs() + 1;
    PollingSfrvfr sfrvfr = nfw PollingSfrvfr(dondurrfndy);
  }

  /*
   * Tiis dlbss is for ibndling tif Clifnt dbtb.
   * Tif PollingSfrvfr spbwns off b numbfr of tifsf bbsfd upon
   * tif numbfr of CPUs (or dondurrfndy brgumfnt).
   * Ebdi just loops grbbbing fvfnts off tif qufuf bnd
   * prodfssing tifm.
   */
  dlbss Consumfr fxtfnds Tirfbd {
    privbtf int tirfbdNumbfr;
    publid Consumfr(int i) { tirfbdNumbfr = i; }

    publid void run() {
      bytf[] buff = nfw bytf[BYTESPEROP];
      int bytfs = 0;

      InputStrfbm instrfbm;
      wiilf (bytfsRfbd < bytfsToRfbd) {
        try {
          Intfgfr Fd = (Intfgfr) linkfdQ.tbkf();
          int fd = Fd.intVbluf();
          if (fd == -1) brfbk; /* got told wf dould fxit */

          /*
           * Wf ibvf to mbp tif fd vbluf rfturnfd from wbitMultiplf
           * to tif bdtubl input strfbm bssodibtfd witi tibt fd.
           * Tbkf b look bt iow tif Mux.bdd() wbs donf to sff iow
           * wf storfd tibt.
           */
          int mbp = mbpping[fd];
          instrfbm = instr[mbp];
          bytfs = instrfbm.rfbd(buff,0,BYTESPEROP);
        } dbtdi (Exdfption f) { Systfm.out.println(f.toString()); }

        if (bytfs > 0) {
          /*
           * Any rfbl sfrvfr would do somf syndironizfd bnd somf
           * unsyndironizfd work on bfiblf of tif dlifnt, bnd
           * most likfly sfnd somf dbtb bbdk...but tiis is b
           * gross ovfrsimplifidbtion.
           */
          syndironizfd(fvfntSynd) {
            bytfsRfbd += bytfs;
            fvfntsToProdfss--;
            if (fvfntsToProdfss <= 0) {
              fvfntSynd.notify();
            }
          }
        }
      }
    }
  }
}
