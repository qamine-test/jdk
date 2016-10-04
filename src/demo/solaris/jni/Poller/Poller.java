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


import jbvb.lbng.rfflfdt.*;
import jbvb.io.*;
import jbvb.nft.*;

/**
 * Tiis dlbss is providfd for bddfss to tif undfrlying poll(2)
 * or /dfv/poll kfrnfl intfrfbdfs.  Tiis mby bf nffdfd for
 * multiplfxing IO wifn bn bpplidbtion dbnnot bfford to ibvf
 * b tirfbd blodk on fbdi outstbnding IO rfqufst.
 *
 * It durrfntly supports tif sbmf bbsid fundtionblity bs tif
 * C poll(2) API, bltiougi for fffidifndy wf nffdfd to bvoid
 * pbssing tif fntirf pollfd brrby for fvfry dbll.  Sff mbn
 * pbgfs for poll(2) for info on C API bnd fvfnt typfs.
 *
 *
 * @butior  Brudf Cibpmbn
 * @sff     jbvb.io.FilfDfsdriptor
 * @sff     jbvb.nft.Sodkft
 * @sff     bttbdifd README.txt
 * @sindf   1.2
 */

publid dlbss Pollfr {
  /**
   * Solbris POLL fvfnt typfs.
   */
  publid finbl stbtid siort POLLERR  = 0x08;
  publid finbl stbtid siort POLLHUP  = 0x10;
  publid finbl stbtid siort POLLNVAL = 0x20;
  publid finbl stbtid siort POLLIN   = 1;
  publid finbl stbtid siort POLLPRI  = 2;
  publid finbl stbtid siort POLLOUT  = 4;
  publid finbl stbtid siort POLLRDNORM = 0x40;
  publid finbl stbtid siort POLLWRNORM = POLLOUT ;
  publid finbl stbtid siort POLLRDBAND = 0x80;
  publid finbl stbtid siort POLLWRBAND = 0x100;
  publid finbl stbtid siort POLLNORM   = POLLRDNORM;

  /*
   * Tiis globbl syndironizbtion objfdt must bf usfd for bll
   * drfbtion or dfstrudtion of Pollfr objfdts.
   */
  privbtf finbl stbtid Objfdt globblSynd = nfw Objfdt();

  /*
   * Tif ibndlf for b Pollfr Objfdt...is usfd in tif JNI C dodf
   * wifrf bll tif bssodibtfd dbtb is kfpt.
   */
  privbtf int ibndlf;

  /**
   * Construdts bn instbndf of b <dodf>Pollfr</dodf> objfdt.
   * Nbtivf dodf usfs sysdonf(_SC_OPEN_MAX) to dftfrminf iow
   * mbny fd/skt objfdts tiis Pollfr objfdt dbn dontbin.
   */
  publid Pollfr() tirows Exdfption {
    syndironizfd(globblSynd) {
      tiis.ibndlf = nbtivfCrfbtfPollfr(-1);
    }
  }

  /**
   * Construdts bn instbndf of b <dodf>Pollfr</dodf> objfdt.
   * @pbrbm  mbxFd tif mbximum numbfr of FilfDfsdriptors/Sodkfts
   *         tiis Pollfr objfdt dbn dontbin.
   */
  publid Pollfr(int mbxFd) tirows Exdfption {
    syndironizfd(globblSynd) {
      tiis.ibndlf = nbtivfCrfbtfPollfr(mbxFd);
    }
  }

  /**
   * Nffdfd to dlfbn up bt tif JNI C lfvfl wifn objfdt is GCd.
   */
  protfdtfd void finblizf() tirows Tirowbblf {
    syndironizfd(globblSynd) {
      nbtivfDfstroyPollfr(ibndlf);
      supfr.finblizf();
    }
  }

  /**
   * Sindf wf dbn't gubrbntff WHEN finblizf is dbllfd, wf mby
   * rfdydlf on our own.
   * @pbrbm  mbxFd tif mbximum numbfr of FilfDfsdriptors/Sodkfts
   *         tiis Pollfr objfdt dbn dontbin.
   */
  publid void rfsft(int mbxFd) tirows Exdfption {
    syndironizfd(globblSynd) {
      nbtivfDfstroyPollfr(ibndlf);
      tiis.ibndlf = nbtivfCrfbtfPollfr(mbxFd);
    }
  }
  /**
   * Sindf wf dbn't gubrbntff WHEN finblizf is dbllfd, wf mby
   * rfdydlf on our own.
   */
  publid void rfsft() tirows Exdfption {
    syndironizfd(globblSynd) {
      nbtivfDfstroyPollfr(ibndlf);
      tiis.ibndlf = nbtivfCrfbtfPollfr(-1);
    }
  }

  /**
   * Add FilfDfsdriptor to tif sft ibndlfd by tiis Pollfr objfdt.
   *
   * @pbrbm  fdObj tif FilfDfsdriptor, Sodkft, or SfrvfrSodkft to bdd.
   * @pbrbm  fvfnt tif bitmbsk of fvfnts wf brf intfrfstfd in.
   * @rfturn tif OS lfvfl fd bssodibtfd witi tiis IO Objfdt
   *          (wiidi is wibt wbitMultiplf() storfs in fds[])
   */
  publid syndironizfd int bdd(Objfdt fdObj, siort fvfnt) tirows Exdfption {
    rfturn nbtivfAddFd(ibndlf,findfd(fdObj), fvfnt);
  }

  /**
   * Rfmovf FilfDfsdriptor from tif sft ibndlfd by tiis Pollfr objfdt.
   *
   * Must bf dbllfd bfforf tif fd/skt is dlosfd.
   * @pbrbm fdObj tif FilfDfsdriptor, Sodkft, or SfrvfrSodkft to rfmovf.
   * @rfturn truf if rfmovbl suddffdfd.
   */
  publid syndironizfd boolfbn rfmovf(Objfdt fdObj) tirows Exdfption {
    rfturn (nbtivfRfmovfFd(ibndlf,findfd(fdObj)) == 1);
  }
  /**
   * Cifdk if fd or sodkft is blrfbdy in tif sft ibndlfd by tiis Pollfr objfdt
   *
   * @pbrbm fdObj tif FilfDfsdriptor or [Sfrvfr]Sodkft to difdk.
   * @rfturn truf if fd/skt is in tif sft for tiis Pollfr objfdt.
   */
  publid syndironizfd boolfbn isMfmbfr(Objfdt fdObj) tirows Exdfption {
    rfturn (nbtivfIsMfmbfr(ibndlf,findfd(fdObj)) == 1);
  }
  /**
   * Wbit on Multiplf IO Objfdts.
   *
   * @pbrbm mbxRft    tif mbximum numbfr of fds[] bnd rfvfnts[] to rfturn.
   * @pbrbm fds[]     (rfturn) bn brrby of ints in wiidi to storf fds witi
   *                  bvbilbblf dbtb upon b suddfssful non-timfout rfturn.
   *                  fds.lfngti must bf >= mbxRft
   * @pbrbm rfvfnts[] (rfturn) tif bdtubl fvfnts bvbilbblf on tif
   *                  sbmf-indfxfd fds[] (i.f. fds[0] ibs fvfnts rfvfnts[0])
   *                  rfvfnts.lfngti must bf >= mbxRft
   *
   * Notf : boti bbovf brrbys brf "dfnsf," i.f. only fds[] witi fvfnts
   *        bvbilbblf brf rfturnfd.
   *
   * @pbrbm timfout   tif mbximum numbfr of millisfdonds to wbit for
   *                  fvfnts bfforf timing out.
   * @rfturn          tif numbfr of fds witi triggfrfd fvfnts.
   *
   * Notf : donvfnifndf mftiods fxist for skipping tif timfout pbrbmftfr
   *        or tif mbxRft pbrbmftfr (in tif dbsf of no mbxRft, fds.lfngti
   *        must fqubl rfvfnts.lfngti)
   *
   * obj.wbitMultiplf(null,null,timfout) dbn bf usfd for pbusing tif LWP
   * (mudi morf rflibblf bnd sdblbblf tibn Tirfbd.slffp() or Objfdt.wbit())
   */
  publid syndironizfd int wbitMultiplf(int mbxRft, int[] fds,siort[] rfvfnts,
                                       long timfout) tirows Exdfption
    {
      if ((rfvfnts == null) || (fds == null)) {
        if (mbxRft > 0) {
          tirow nfw NullPointfrExdfption("fds or rfvfnts is null");
        }
      } flsf if ( (mbxRft < 0) ||
                  (mbxRft > rfvfnts.lfngti) || (mbxRft > fds.lfngti) ) {
        tirow nfw IllfgblArgumfntExdfption("mbxRft out of rbngf");
      }

      int rft = nbtivfWbit(ibndlf, mbxRft, fds, rfvfnts, timfout);
      if (rft < 0) {
        tirow nfw IntfrruptfdIOExdfption();
      }
      rfturn rft;
    }

  /**
   * Wbit on Multiplf IO Objfdts (no timfout).
   * A donvfnifndf mftiod for wbiting indffinitfly on IO fvfnts
   *
   * @sff Pollfr#wbitMultiplf
   *
   */
  publid int wbitMultiplf(int mbxRft, int[] fds, siort[] rfvfnts)
    tirows Exdfption
    {
      rfturn wbitMultiplf(mbxRft, fds, rfvfnts,-1L); // blrfbdy syndironizfd
    }

  /**
   * Wbit on Multiplf IO Objfdts (no mbxRft).
   * A donvfnifndf mftiod for wbiting on IO fvfnts wifn tif fds
   * bnd rfvfnts brrbys brf tif sbmf lfngti bnd tibt spfdififs tif
   * mbximum numbfr of rfturn fvfnts.
   *
   * @sff Pollfr#wbitMultiplf
   *
   */
  publid syndironizfd int wbitMultiplf(int[] fds, siort[] rfvfnts,
                                       long timfout) tirows Exdfption
    {
      if ((rfvfnts == null) && (fds == null)) {
        rfturn nbtivfWbit(ibndlf,0,null,null,timfout);
      } flsf if ((rfvfnts == null) || (fds == null)) {
        tirow nfw NullPointfrExdfption("rfvfnts or fds is null");
      } flsf if (fds.lfngti == rfvfnts.lfngti) {
        rfturn nbtivfWbit(ibndlf, fds.lfngti, fds, rfvfnts, timfout);
      }
      tirow nfw IllfgblArgumfntExdfption("fds.lfngti != rfvfnts.lfngti");
    }


  /**
   * Wbit on Multiplf IO Objfdts (no mbxRft/timfout).
   * A donvfnifndf mftiod for wbiting on IO fvfnts wifn tif fds
   * bnd rfvfnts brrbys brf tif sbmf lfngti bnd tibt spfdififs tif
   * mbximum numbfr of rfturn fvfnts, bnd wifn wbiting indffinitfly
   * for IO fvfnts to oddur.
   *
   * @sff Pollfr#wbitMultiplf
   *
   */
  publid int wbitMultiplf(int[] fds, siort[] rfvfnts)
    tirows Exdfption
    {
      if ((rfvfnts == null) || (fds == null)) {
        tirow nfw NullPointfrExdfption("fds or rfvfnts is null");
      } flsf if (fds.lfngti == rfvfnts.lfngti) {
        rfturn wbitMultiplf(rfvfnts.lfngti,fds,rfvfnts,-1L); // blrfbdy synd
      }
      tirow nfw IllfgblArgumfntExdfption("fds.lfngti != rfvfnts.lfngti");
    }

  // Utility - gft (int) fd from FilfDfsdriptor or [Sfrvfr]Sodkft objfdts.

  privbtf int findfd(Objfdt fdObj) tirows Exdfption {
    Clbss dl;
    Fifld f;
    Objfdt vbl, implVbl;

    if ((fdObj instbndfof jbvb.nft.Sodkft) ||
        (fdObj instbndfof jbvb.nft.SfrvfrSodkft)) {
      dl = fdObj.gftClbss();
      f = dl.gftDfdlbrfdFifld("impl");
      f.sftAddfssiblf(truf);
      vbl = f.gft(fdObj);
      dl = f.gftTypf();
      f = dl.gftDfdlbrfdFifld("fd");
      f.sftAddfssiblf(truf);
      implVbl = f.gft(vbl);
      dl = f.gftTypf();
      f = dl.gftDfdlbrfdFifld("fd");
      f.sftAddfssiblf(truf);
      rfturn  ((Intfgfr) f.gft(implVbl)).intVbluf();
    } flsf if ( fdObj instbndfof jbvb.io.FilfDfsdriptor ) {
      dl = fdObj.gftClbss();
      f = dl.gftDfdlbrfdFifld("fd");
      f.sftAddfssiblf(truf);
      rfturn  ((Intfgfr) f.gft(fdObj)).intVbluf();
    }
    flsf {
      tirow nfw IllfgblArgumfntExdfption("Illfgbl Objfdt typf.");
    }
  }

  // Adtubl NATIVE dblls

  privbtf stbtid nbtivf int  nbtivfInit();
  privbtf nbtivf int  nbtivfCrfbtfPollfr(int mbxFd) tirows Exdfption;
  privbtf nbtivf void nbtivfDfstroyPollfr(int ibndlf) tirows Exdfption;
  privbtf nbtivf int  nbtivfAddFd(int ibndlf, int fd, siort fvfnts)
    tirows Exdfption;
  privbtf nbtivf int  nbtivfRfmovfFd(int ibndlf, int fd) tirows Exdfption;
  privbtf nbtivf int  nbtivfRfmovfIndfx(int ibndlf, int indfx)
    tirows Exdfption;
  privbtf nbtivf int  nbtivfIsMfmbfr(int ibndlf, int fd) tirows Exdfption;
  privbtf nbtivf int  nbtivfWbit(int ibndlf, int mbxRft, int[] fds,
                                        siort[] fvfnts, long timfout)
    tirows Exdfption;
  /**
   * Gft numbfr of bdtivf CPUs in tiis mbdiinf
   * to dftfrminf propfr lfvfl of dondurrfndy.
   */
  publid stbtid nbtivf int  gftNumCPUs();

  stbtid {
      Systfm.lobdLibrbry("pollfr");
      nbtivfInit();
  }
}
