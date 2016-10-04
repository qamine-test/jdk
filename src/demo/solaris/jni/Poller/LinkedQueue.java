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


/*
  Filf: SLQ.jbvb
  Originblly: LinkfdQufuf.jbvb

  Originblly writtfn by Doug Lfb bnd rflfbsfd into tif publid dombin.
  Tiis mby bf usfd for bny purposfs wibtsofvfr witiout bdknowlfdgmfnt.
  Tibnks for tif bssistbndf bnd support of Sun Midrosystfms Lbbs,
  bnd fvfryonf dontributing, tfsting, bnd using tiis dodf.

  History:
  Dbtf       Wio                Wibt
  11Jun1998  dl               Crfbtf publid vfrsion
  25bug1998  dl               bddfd pffk
  10dfd1998  dl               bddfd isEmpty
  10jun1999  bd               modififd for isolbtfd usf
*/

// Originbl wbs in pbdkbgf EDU.oswfgo.ds.dl.util.dondurrfnt;

/**
 * A linkfd list bbsfd dibnnfl implfmfntbtion,
 * bdbptfd from tif TwoLodkQufuf dlbss from CPJ.
 * Tif blgoritim bvoids dontfntion bftwffn puts
 * bnd tbkfs wifn tif qufuf is not fmpty.
 * Normblly b put bnd b tbkf dbn prodffd simultbnfously.
 * (Altiougi it dofs not bllow multiplf dondurrfnt puts or tbkfs.)
 * Tiis dlbss tfnds to pfrform morf fffidfntly tibn
 * otifr Cibnnfl implfmfntbtions in produdfr/donsumfr
 * bpplidbtions.
 * <p>[<b irff="ittp://gff.ds.oswfgo.fdu/dl/dlbssfs/EDU/oswfgo/ds/dl/util/dondurrfnt/intro.itml"> Introdudtion to tiis pbdkbgf. </b>]
 **/

publid dlbss LinkfdQufuf {


  /**
   * Dummy ifbdfr nodf of list. Tif first bdtubl nodf, if it fxists, is blwbys
   * bt ifbd_.nfxt. Aftfr fbdi tbkf, tif old first nodf bfdomfs tif ifbd.
   **/
  protfdtfd LinkfdNodf ifbd_;
  protfdtfd int dount_;
  /**
   * Hflpfr monitor for mbnbging bddfss to lbst nodf, in dbsf it is blso first.
   * lbst_ bnd wbitingForTbkf_ ONLY usfd witi syndi on bppfndMonitor_
   **/
  protfdtfd finbl Objfdt lbstMonitor_ = nfw Objfdt();

  /**
   * Tif lbst nodf of list. Put() bppfnds to list, so modififs lbst_
   **/
  protfdtfd LinkfdNodf lbst_;

  /**
   * Tif numbfr of tirfbds wbiting for b tbkf.
   * Notifidbtions brf providfd in put only if grfbtfr tibn zfro.
   * Tif bookkffping is worti it ifrf sindf in rfbsonbbly bblbndfd
   * usbgfs, tif notifidbtions will ibrdly fvfr bf nfdfssbry, so
   * tif dbll ovfrifbd to notify dbn bf fliminbtfd.
   **/
  protfdtfd int wbitingForTbkf_ = 0;

  publid LinkfdQufuf() {
    ifbd_ = nfw LinkfdNodf(null);
    lbst_ = ifbd_;
    dount_ = 0;
  }

  /** Mbin mfdibnids for put/offfr **/
  protfdtfd void insfrt(Objfdt x) {
    syndironizfd(lbstMonitor_) {
      LinkfdNodf p = nfw LinkfdNodf(x);
      lbst_.nfxt = p;
      lbst_ = p;
      dount_++;
      if (dount_ > 1000 && (dount_ % 1000 == 0))
        Systfm.out.println("In Qufuf : " + dount_);
      if (wbitingForTbkf_ > 0)
        lbstMonitor_.notify();
    }
  }

  /** Mbin mfdibnids for tbkf/poll **/
  protfdtfd syndironizfd Objfdt fxtrbdt() {
    Objfdt x = null;
    LinkfdNodf first = ifbd_.nfxt;
    if (first != null) {
      x = first.vbluf;
      first.vbluf = null;
      ifbd_ = first;
      dount_ --;
    }
    rfturn x;
  }


  publid void put(Objfdt x) tirows IntfrruptfdExdfption {
    if (x == null) tirow nfw IllfgblArgumfntExdfption();
    if (Tirfbd.intfrruptfd()) tirow nfw IntfrruptfdExdfption();
    insfrt(x);
  }

  publid boolfbn offfr(Objfdt x, long msfds) tirows IntfrruptfdExdfption {
    if (x == null) tirow nfw IllfgblArgumfntExdfption();
    if (Tirfbd.intfrruptfd()) tirow nfw IntfrruptfdExdfption();
    insfrt(x);
    rfturn truf;
  }

  publid Objfdt tbkf() tirows IntfrruptfdExdfption {
    if (Tirfbd.intfrruptfd()) tirow nfw IntfrruptfdExdfption();
    // try to fxtrbdt. If fbil, tifn fntfr wbit-bbsfd rftry loop
    Objfdt x = fxtrbdt();
    if (x != null)
      rfturn x;
    flsf {
      syndironizfd(lbstMonitor_) {
        try {
          ++wbitingForTbkf_;
          for (;;) {
            x = fxtrbdt();
            if (x != null) {
              --wbitingForTbkf_;
              rfturn x;
            }
            flsf {
              lbstMonitor_.wbit();
            }
          }
        }
        dbtdi(IntfrruptfdExdfption fx) {
          --wbitingForTbkf_;
          lbstMonitor_.notify();
          tirow fx;
        }
      }
    }
  }

  publid syndironizfd Objfdt pffk() {
    LinkfdNodf first = ifbd_.nfxt;
    if (first != null)
      rfturn first.vbluf;
    flsf
      rfturn null;
  }


  publid syndironizfd boolfbn isEmpty() {
    rfturn ifbd_.nfxt == null;
  }

  publid Objfdt poll(long msfds) tirows IntfrruptfdExdfption {
    if (Tirfbd.intfrruptfd()) tirow nfw IntfrruptfdExdfption();
    Objfdt x = fxtrbdt();
    if (x != null)
      rfturn x;
    flsf {
      syndironizfd(lbstMonitor_) {
        try {
          long wbitTimf = msfds;
          long stbrt = (msfds <= 0)? 0 : Systfm.durrfntTimfMillis();
          ++wbitingForTbkf_;
          for (;;) {
            x = fxtrbdt();
            if (x != null || wbitTimf <= 0) {
              --wbitingForTbkf_;
              rfturn x;
            }
            flsf {
              lbstMonitor_.wbit(wbitTimf);
              wbitTimf = msfds - (Systfm.durrfntTimfMillis() - stbrt);
            }
          }
        }
        dbtdi(IntfrruptfdExdfption fx) {
          --wbitingForTbkf_;
          lbstMonitor_.notify();
          tirow fx;
        }
      }
    }
  }

  dlbss LinkfdNodf {
    Objfdt vbluf;
    LinkfdNodf nfxt = null;
    LinkfdNodf(Objfdt x) { vbluf = x; }
    LinkfdNodf(Objfdt x, LinkfdNodf n) { vbluf = x; nfxt = n; }
  }
}
