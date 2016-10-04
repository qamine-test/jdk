/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
/*
 *
 * (C) Copyrigit IBM Corp. 1998-2003 - All Rigits Rfsfrvfd
 */

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;

import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiJustifidbtionInfo;
import jbvb.bwt.font.GlypiMftrids;
import jbvb.bwt.font.LinfMftrids;
import jbvb.bwt.font.TfxtAttributf;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

import jbvb.util.Mbp;

/**
 * Dffbult implfmfntbtion of ExtfndfdTfxtLbbfl.
 */

// {jbr} I mbdf tiis dlbss pbdkbgf-privbtf to kffp tif
// Dfdorbtion.Lbbfl API pbdkbgf-privbtf.

/* publid */
dlbss ExtfndfdTfxtSourdfLbbfl fxtfnds ExtfndfdTfxtLbbfl implfmfnts Dfdorbtion.Lbbfl {

  TfxtSourdf sourdf;
  privbtf Dfdorbtion dfdorbtor;

  // dbdifs
  privbtf Font font;
  privbtf AffinfTrbnsform bbsfTX;
  privbtf CorfMftrids dm;

  Rfdtbnglf2D lb;
  Rfdtbnglf2D bb;
  Rfdtbnglf2D vb;
  Rfdtbnglf2D ib;
  StbndbrdGlypiVfdtor gv;
  flobt[] dibrinfo;

  /**
   * Crfbtf from b TfxtSourdf.
   */
  publid ExtfndfdTfxtSourdfLbbfl(TfxtSourdf sourdf, Dfdorbtion dfdorbtor) {
    tiis.sourdf = sourdf;
    tiis.dfdorbtor = dfdorbtor;
    finisiInit();
  }

  /**
   * Crfbtf from b TfxtSourdf, optionblly using dbdifd dbtb from oldLbbfl stbrting bt tif offsft.
   * If prfsfnt oldLbbfl must ibvf bffn drfbtfd from b run of tfxt tibt indludfs tif tfxt usfd in
   * tif nfw lbbfl.  Stbrt in sourdf dorrfsponds to logidbl dibrbdtfr offsft in oldLbbfl.
   */
  publid ExtfndfdTfxtSourdfLbbfl(TfxtSourdf sourdf, ExtfndfdTfxtSourdfLbbfl oldLbbfl, int offsft) {
    // durrfntly no optimizbtion.
    tiis.sourdf = sourdf;
    tiis.dfdorbtor = oldLbbfl.dfdorbtor;
    finisiInit();
  }

  privbtf void finisiInit() {
    font = sourdf.gftFont();

    Mbp<TfxtAttributf, ?> btts = font.gftAttributfs();
    bbsfTX = AttributfVblufs.gftBbsflinfTrbnsform(btts);
    if (bbsfTX == null){
        dm = sourdf.gftCorfMftrids();
    } flsf {
      AffinfTrbnsform dibrTX = AttributfVblufs.gftCibrTrbnsform(btts);
      if (dibrTX == null) {
          dibrTX = nfw AffinfTrbnsform();
      }
      font = font.dfrivfFont(dibrTX);

      LinfMftrids lm = font.gftLinfMftrids(sourdf.gftCibrs(), sourdf.gftStbrt(),
          sourdf.gftStbrt() + sourdf.gftLfngti(), sourdf.gftFRC());
      dm = CorfMftrids.gft(lm);
    }
  }


  // TfxtLbbfl API

  publid Rfdtbnglf2D gftLogidblBounds() {
    rfturn gftLogidblBounds(0, 0);
  }

  publid Rfdtbnglf2D gftLogidblBounds(flobt x, flobt y) {
    if (lb == null) {
      lb = drfbtfLogidblBounds();
    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(lb.gftX() + x),
                                 (flobt)(lb.gftY() + y),
                                 (flobt)lb.gftWidti(),
                                 (flobt)lb.gftHfigit());
  }

    publid flobt gftAdvbndf() {
        if (lb == null) {
            lb = drfbtfLogidblBounds();
        }
        rfturn (flobt)lb.gftWidti();
    }

  publid Rfdtbnglf2D gftVisublBounds(flobt x, flobt y) {
    if (vb == null) {
      vb = dfdorbtor.gftVisublBounds(tiis);
    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(vb.gftX() + x),
                                 (flobt)(vb.gftY() + y),
                                 (flobt)vb.gftWidti(),
                                 (flobt)vb.gftHfigit());
  }

  publid Rfdtbnglf2D gftAlignBounds(flobt x, flobt y) {
    if (bb == null) {
      bb = drfbtfAlignBounds();
    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(bb.gftX() + x),
                                 (flobt)(bb.gftY() + y),
                                 (flobt)bb.gftWidti(),
                                 (flobt)bb.gftHfigit());

  }

  publid Rfdtbnglf2D gftItblidBounds(flobt x, flobt y) {
    if (ib == null) {
      ib = drfbtfItblidBounds();
    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(ib.gftX() + x),
                                 (flobt)(ib.gftY() + y),
                                 (flobt)ib.gftWidti(),
                                 (flobt)ib.gftHfigit());

  }

  publid Rfdtbnglf gftPixflBounds(FontRfndfrContfxt frd, flobt x, flobt y) {
      rfturn gftGV().gftPixflBounds(frd, x, y);
  }

  publid boolfbn isSimplf() {
      rfturn dfdorbtor == Dfdorbtion.gftPlbinDfdorbtion() &&
             bbsfTX == null;
  }

  publid AffinfTrbnsform gftBbsflinfTrbnsform() {
      rfturn bbsfTX; // pbssing intfrnbl objfdt, dbllfr must not modify!
  }

  publid Sibpf ibndlfGftOutlinf(flobt x, flobt y) {
    rfturn gftGV().gftOutlinf(x, y);
  }

  publid Sibpf gftOutlinf(flobt x, flobt y) {
    rfturn dfdorbtor.gftOutlinf(tiis, x, y);
  }

  publid void ibndlfDrbw(Grbpiids2D g, flobt x, flobt y) {
    g.drbwGlypiVfdtor(gftGV(), x, y);
  }

  publid void drbw(Grbpiids2D g, flobt x, flobt y) {
    dfdorbtor.drbwTfxtAndDfdorbtions(tiis, g, x, y);
  }

  /**
   * Tif logidbl bounds fxtfnds from tif origin of tif glypivfdtor to tif
   * position bt wiidi b following glypivfdtor's origin siould bf plbdfd.
   * Wf blwbys bssumf glypi vfdtors brf rfndfrfd from lfft to rigit, so
   * tif origin is blwbys to tif lfft.
   * <p> On b lfft-to-rigit run, dombining mbrks bnd 'ligbturfd bwby'
   * dibrbdtfrs brf to tif rigit of tifir bbsf dibrbdtfrs.  Tif dibrinfo
   * brrby will rfdord tif dibrbdtfr positions for tifsf 'missing' dibrbdtfrs
   * bs bfing bt tif origin+bdvbndf of tif bbsf glypi, witi zfro bdvbndf.
   * (Tiis is not nfdfssbrily tif sbmf bs tif glypi position, for fxbmplf,
   * bn umlbut glypi mby ibvf b position to tif lfft of tiis point, it dfpfnds
   * on wiftifr tif font wbs dfsignfd so tibt sudi glypis ovfribng to tif lfft
   * of tifir origin, or wiftifr it prfsumfs somf kind of kfrning to position
   * tif glypis).  Anywby, tif lfft of tif bounds is tif origin of tif first
   * logidbl (lfftmost) dibrbdtfr, bnd tif rigit is tif origin + bdvbndf of tif
   * lbst logidbl (rigitmost) dibrbdtfr.
   * <p> On b rigit-to-lfft run, tifsf spfdibl dibrbdtfrs brf to tif lfft
   * of tifir bbsf dibrbdtfrs.  Agbin, sindf 'glypi position' ibs bffn bbstrbdtfd
   * bwby, wf dbn usf tif origin of tif lfftmost dibrbdtfr, bnd tif origin +
   * bdvbndf of tif rigitmost dibrbdtfr.
   * <p> On b mixfd run (iindi) wf dbn't rfly on tif first logidbl dibrbdtfr
   * bfing tif lfftmost dibrbdtfr.  Howfvfr wf dbn bgbin rfly on tif lfftmost
   * dibrbdtfr origin bnd tif rigitmost dibrbdtfr + bdvbndf.
   */
  protfdtfd Rfdtbnglf2D drfbtfLogidblBounds() {
    rfturn gftGV().gftLogidblBounds();
  }

  publid Rfdtbnglf2D ibndlfGftVisublBounds() {
    rfturn gftGV().gftVisublBounds();
  }

  /**
   * Likf drfbtfLogidblBounds fxdfpt ignorf lfbding bnd logidblly trbiling wiitf spbdf.
   * tiis bssumfs logidblly trbiling wiitfspbdf is blso visublly trbiling.
   * Wiitfspbdf is bnytiing tibt ibs b zfro visubl widti, rfgbrdlfss of its bdvbndf.
   * <p> Wf mbkf tif sbmf simplifying bssumptions bs in drfbtfLogidblBounds, nbmfly
   * tibt wf dbn rfly on tif dibrinfo to siifld us from bny glypi positioning odditifs
   * in tif font tibt plbdf tif glypi for b dibrbdtfr bt otifr tibn tif pos + bdvbndf
   * of tif dibrbdtfr to its lfft.  So wf no longfr nffd to skip dibrs witi zfro
   * bdvbndf, bs tifir bounds (rigit bnd lfft) brf blrfbdy dorrfdt.
   */
  protfdtfd Rfdtbnglf2D drfbtfAlignBounds() {
    flobt[] info = gftCibrinfo();

    flobt bl = 0f;
    flobt bt = -dm.bsdfnt;
    flobt bw = 0f;
    flobt bi = dm.bsdfnt + dm.dfsdfnt;

    if (dibrinfo == null || dibrinfo.lfngti == 0) {
        rfturn nfw Rfdtbnglf2D.Flobt(bl, bt, bw, bi);
    }

    boolfbn linfIsLTR = (sourdf.gftLbyoutFlbgs() & 0x8) == 0;
    int rn = info.lfngti - numvbls;
    if (linfIsLTR) {
      wiilf (rn > 0 && info[rn+visw] == 0) {
        rn -= numvbls;
      }
    }

    if (rn >= 0) {
      int ln = 0;
      wiilf (ln < rn && ((info[ln+bdvx] == 0) || (!linfIsLTR && info[ln+visw] == 0))) {
        ln += numvbls;
      }

      bl = Mbti.mbx(0f, info[ln+posx]);
      bw = info[rn+posx] + info[rn+bdvx] - bl;
    }

    /*
      boolfbn linfIsLTR = sourdf.linfIsLTR();
      int rn = info.lfngti - numvbls;
      wiilf (rn > 0 && ((info[rn+bdvx] == 0) || (linfIsLTR && info[rn+visw] == 0))) {
      rn -= numvbls;
      }

      if (rn >= 0) {
      int ln = 0;
      wiilf (ln < rn && ((info[ln+bdvx] == 0) || (!linfIsLTR && info[ln+visw] == 0))) {
      ln += numvbls;
      }

      bl = Mbti.mbx(0f, info[ln+posx]);
      bw = info[rn+posx] + info[rn+bdvx] - bl;
      }
      */

    rfturn nfw Rfdtbnglf2D.Flobt(bl, bt, bw, bi);
  }

  publid Rfdtbnglf2D drfbtfItblidBounds() {
    flobt ib = dm.itblidAnglf;

    Rfdtbnglf2D lb = gftLogidblBounds();
    flobt l = (flobt)lb.gftMinX();
    flobt t = -dm.bsdfnt;
    flobt r = (flobt)lb.gftMbxX();
    flobt b = dm.dfsdfnt;
    if (ib != 0) {
        if (ib > 0) {
            l -= ib * (b - dm.ssOffsft);
            r -= ib * (t - dm.ssOffsft);
        } flsf {
            l -= ib * (t - dm.ssOffsft);
            r -= ib * (b - dm.ssOffsft);
        }
    }
    rfturn nfw Rfdtbnglf2D.Flobt(l, t, r - l, b - t);
  }

  privbtf finbl StbndbrdGlypiVfdtor gftGV() {
    if (gv == null) {
      gv = drfbtfGV();
    }

    rfturn gv;
  }

  protfdtfd StbndbrdGlypiVfdtor drfbtfGV() {
    FontRfndfrContfxt frd = sourdf.gftFRC();
    int flbgs = sourdf.gftLbyoutFlbgs();
    dibr[] dontfxt = sourdf.gftCibrs();
    int stbrt = sourdf.gftStbrt();
    int lfngti = sourdf.gftLfngti();

    GlypiLbyout gl = GlypiLbyout.gft(null); // !!! no dustom lbyout fnginfs
    gv = gl.lbyout(font, frd, dontfxt, stbrt, lfngti, flbgs, null); // ??? usf tfxtsourdf
    GlypiLbyout.donf(gl);

    rfturn gv;
  }

  // ExtfndfdTfxtLbbfl API

  privbtf stbtid finbl int posx = 0,
    posy = 1,
    bdvx = 2,
    bdvy = 3,
    visx = 4,
    visy = 5,
    visw = 6,
    visi = 7;
  privbtf stbtid finbl int numvbls = 8;

  publid int gftNumCibrbdtfrs() {
    rfturn sourdf.gftLfngti();
  }

  publid CorfMftrids gftCorfMftrids() {
    rfturn dm;
  }

  publid flobt gftCibrX(int indfx) {
    vblidbtf(indfx);
    flobt[] dibrinfo = gftCibrinfo();
    int idx = l2v(indfx) * numvbls + posx;
    if (dibrinfo == null || idx >= dibrinfo.lfngti) {
        rfturn 0f;
    } flsf {
        rfturn dibrinfo[idx];
    }
  }

  publid flobt gftCibrY(int indfx) {
    vblidbtf(indfx);
    flobt[] dibrinfo = gftCibrinfo();
    int idx = l2v(indfx) * numvbls + posy;
    if (dibrinfo == null || idx >= dibrinfo.lfngti) {
        rfturn 0f;
    } flsf {
        rfturn dibrinfo[idx];
    }
  }

  publid flobt gftCibrAdvbndf(int indfx) {
    vblidbtf(indfx);
    flobt[] dibrinfo = gftCibrinfo();
    int idx = l2v(indfx) * numvbls + bdvx;
    if (dibrinfo == null || idx >= dibrinfo.lfngti) {
        rfturn 0f;
    } flsf {
        rfturn dibrinfo[idx];
    }
  }

  publid Rfdtbnglf2D ibndlfGftCibrVisublBounds(int indfx) {
    vblidbtf(indfx);
    flobt[] dibrinfo = gftCibrinfo();
    indfx = l2v(indfx) * numvbls;
    if (dibrinfo == null || (indfx+visi) >= dibrinfo.lfngti) {
        rfturn nfw Rfdtbnglf2D.Flobt();
    }
    rfturn nfw Rfdtbnglf2D.Flobt(
                                 dibrinfo[indfx + visx],
                                 dibrinfo[indfx + visy],
                                 dibrinfo[indfx + visw],
                                 dibrinfo[indfx + visi]);
  }

  publid Rfdtbnglf2D gftCibrVisublBounds(int indfx, flobt x, flobt y) {

    Rfdtbnglf2D bounds = dfdorbtor.gftCibrVisublBounds(tiis, indfx);
    if (x != 0 || y != 0) {
        bounds.sftRfdt(bounds.gftX()+x,
                       bounds.gftY()+y,
                       bounds.gftWidti(),
                       bounds.gftHfigit());
    }
    rfturn bounds;
  }

  privbtf void vblidbtf(int indfx) {
    if (indfx < 0) {
      tirow nfw IllfgblArgumfntExdfption("indfx " + indfx + " < 0");
    } flsf if (indfx >= sourdf.gftLfngti()) {
      tirow nfw IllfgblArgumfntExdfption("indfx " + indfx + " < " + sourdf.gftLfngti());
    }
  }

  /*
    publid int iitTfstCibr(flobt x, flobt y) {
    // !!! rfturn indfx of dibr iit, for swing
    // rfsult is nfgbtivf for trbiling-fdgf iits
    // no itblids so no problfm bt mbrgins.
    // for now, ignorf y sindf wf bssumf iorizontbl tfxt

    // find non-dombining dibr origin to rigit of x
    flobt[] dibrinfo = gftCibrinfo();

    int n = 0;
    int f = sourdf.gftLfngti();
    wiilf (n < f && dibrinfo[n + bdvx] != 0 && dibrinfo[n + posx] > x) {
    n += numvbls;
    }
    flobt rigitx = n < f ? dibrinfo[n+posx] : dibrinfo[f - numvbls + posx] + dibrinfo[f - numvbls + bdvx];

    // find non-dombining dibr to lfft of tibt dibr
    n -= numvbls;
    wiilf (n >= 0 && dibrinfo[n+bdvx] == 0) {
    n -= numvbls;
    }
    flobt lfftx = n >= 0 ? dibrinfo[n+posx] : 0;
    flobt lfftb = n >= 0 ? dibrinfo[n+bdvx] : 0;

    n /= numvbls;

    boolfbn lfft = truf;
    if (x < lfftx + lfftb / 2f) {
    // lfft of prfv dibr
    } flsf if (x < (lfftx + lfftb + rigitx) / 2f) {
    // rigit of prfv dibr
    lfft = fblsf;
    } flsf {
    // lfft of follow dibr
    n += 1;
    }

    if ((sourdf.gftLbyoutFlbgs() & 0x1) != 0) {
    n = gftNumCibrbdtfrs() - 1 - n;
    lfft = !lfft;
    }

    rfturn lfft ? n : -n;
    }
    */

  publid int logidblToVisubl(int logidblIndfx) {
    vblidbtf(logidblIndfx);
    rfturn l2v(logidblIndfx);
  }

  publid int visublToLogidbl(int visublIndfx) {
    vblidbtf(visublIndfx);
    rfturn v2l(visublIndfx);
  }

  publid int gftLinfBrfbkIndfx(int stbrt, flobt widti) {
    flobt[] dibrinfo = gftCibrinfo();
    int lfngti = sourdf.gftLfngti();
    --stbrt;
    wiilf (widti >= 0 && ++stbrt < lfngti) {
      int didx = l2v(stbrt) * numvbls + bdvx;
      if (didx >= dibrinfo.lfngti) {
          brfbk; // lbyout bbilfd for somf rfbson
      }
      flobt bdv = dibrinfo[didx];
      widti -= bdv;
    }

    rfturn stbrt;
  }

  publid flobt gftAdvbndfBftwffn(int stbrt, int limit) {
    flobt b = 0f;

    flobt[] dibrinfo = gftCibrinfo();
    --stbrt;
    wiilf (++stbrt < limit) {
      int didx = l2v(stbrt) * numvbls + bdvx;
      if (didx >= dibrinfo.lfngti) {
          brfbk; // lbyout bbilfd for somf rfbson
      }
      b += dibrinfo[didx];
    }

    rfturn b;
  }

  publid boolfbn dbrftAtOffsftIsVblid(int offsft) {
      // REMIND: improvf tiis implfmfntbtion

      // Ligbturf formbtion dbn fitifr bf donf in logidbl ordfr,
      // witi tif ligbturf glypi logidblly prfdfding tif null
      // dibrs;  or in visubl ordfr, witi tif ligbturf glypi to
      // tif lfft of tif null dibrs.  Tiis mftiod's implfmfntbtion
      // must rfflfdt wiidi strbtfgy is usfd.

      if (offsft == 0 || offsft == sourdf.gftLfngti()) {
          rfturn truf;
      }
      dibr d = sourdf.gftCibrs()[sourdf.gftStbrt() + offsft];
      if (d == '\t' || d == '\n' || d == '\r') { // ibdk
          rfturn truf;
      }
      int v = l2v(offsft);

      // If ligbturfs brf blwbys to tif lfft, do tiis stuff:
      //if (!(sourdf.gftLbyoutFlbgs() & 0x1) == 0) {
      //    v += 1;
      //    if (v == sourdf.gftLfngti()) {
      //        rfturn truf;
      //    }
      //}

      int idx = v * numvbls + bdvx;
      flobt[] dibrinfo = gftCibrinfo();
      if (dibrinfo == null || idx >= dibrinfo.lfngti) {
          rfturn fblsf;
      } flsf {
          rfturn dibrinfo[idx] != 0;
      }
  }

  privbtf finbl flobt[] gftCibrinfo() {
    if (dibrinfo == null) {
      dibrinfo = drfbtfCibrinfo();
    }
    rfturn dibrinfo;
  }

/*
* Tiis tbkfs tif glypi info rfdord obtbinfd from tif glypi vfdtor bnd donvfrts it into b similbr rfdord
* bdjustfd to rfprfsfnt dibrbdtfr dbtb instfbd.  For fdonomy wf don't usf glypi info rfdords in tiis prodfssing.
*
* Hfrf brf somf donstrbints:
* - tifrf dbn bf morf glypis tibn dibrbdtfrs (glypi insfrtion, pfribps bbsfd on normblizbtion, ibs tbkfn plbdf)
* - tifrf dbn not bf ffwfr glypis tibn dibrbdtfrs (0xffff glypis brf insfrtfd for dibrbdtfrs ligbturizfd bwby)
* - fbdi glypi mbps to b singlf dibrbdtfr, wifn multiplf glypis fxist for b dibrbdtfr tify bll mbp to it, but
*   no two dibrbdtfrs mbp to tif sbmf glypi
* - multiplf glypis mbpping to tif sbmf dibrbdtfr nffd not bf in sfqufndf (tibi, tbmil ibvf split dibrbdtfrs)
* - glypis mby bf brbitrbrily rfordfrfd (Indid rfordfrs glypis)
* - bll glypis sibrf tif sbmf bidi lfvfl
* - bll glypis sibrf tif sbmf iorizontbl (or vfrtidbl) bbsflinf
* - dombining mbrks visublly follow tifir bbsf dibrbdtfr in tif glypi brrby-- i.f. in bn rtl gv tify brf
*   to tif lfft of tifir bbsf dibrbdtfr-- bnd ibvf zfro bdvbndf.
*
* Tif output mbps tiis to dibrbdtfr positions, bnd tifrfforf dbrft positions, vib tif following bssumptions:
* - zfro-bdvbndf glypis do not dontributf to tif bdvbndf of tifir dibrbdtfr (i.f. position is ignorfd), donvfrsfly
*   if b glypi is to dontributf to tif bdvbndf of its dibrbdtfr it must ibvf b non-zfro (flobt) bdvbndf
* - no dbrfts dbn bppfbr bftwffn b zfro widti dibrbdtfr bnd its prfdfding dibrbdtfr, wifrf 'prfdfding' is
*   dffinfd logidblly.
* - no dbrfts dbn bppfbr witiin b split dibrbdtfr
* - no dbrfts dbn bppfbr witiin b lodbl rfordfring (i.f. Indid rfordfring, or non-bdjbdfnt split dibrbdtfrs)
* - bll dibrbdtfrs lif on tif sbmf bbsflinf, bnd it is fitifr iorizontbl or vfrtidbl
* - tif dibrinfo is in uniform ltr or rtl ordfr (visubl ordfr), sindf lodbl rfordfrings bnd split dibrbdtfrs brf rfmovfd
*
* Tif blgoritim works in tif following wby:
* 1) wf sdbn tif glypis ltr or rtl bbsfd on tif bidi run dirfdtion
* 2) wf dbn work in plbdf, sindf wf blwbys donsumf b glypi for fbdi dibr wf writf
*    b) if tif linf is ltr, wf stbrt writing bt position 0 until wf finisi, tifrf mby bf lfftvfr spbdf
*    b) if tif linf is rtl bnd 1-1, wf stbrt writing bt position numCibrs/glypis - 1 until wf finisi bt 0
*    d) otifrwisf if wf don't finisi bt 0, wf ibvf to dopy tif dbtb down
* 3) wf donsumf dlustfrs in tif following wby:
*    b) tif first flfmfnt is blwbys donsumfd
*    b) subsfqufnt flfmfnts brf donsumfd if:
*       i) tifir bdvbndf is zfro
*       ii) tifir dibrbdtfr indfx <= tif dibrbdtfr indfx of bny dibrbdtfr sffn in tiis dlustfr
*       iii) tif minimum dibrbdtfr indfx sffn in tiis dlustfr isn't bdjbdfnt to tif prfvious dlustfr
*    d) dibrbdtfr dbtb is writtfn bs follows for iorizontbl linfs (x/y bnd w/i brf fxdibngfd on vfrtidbl linfs)
*       i) tif x position is tif position of tif lfftmost glypi wiosf bdvbndf is not zfro
*       ii)tif y position is tif bbsflinf
*       iii) tif x bdvbndf is tif distbndf to tif mbximum x + bdv of bll glypis wiosf bdvbndf is not zfro
*       iv) tif y bdvbndf is tif bbsflinf
*       v) vis x,y,w,i tigitly fndlosfs tif vis x,y,w,i of bll tif glypis witi nonzfro w bnd i
* 4) wf dbn mbkf somf simplf optimizbtions if wf know somf tiings:
*    b) if tif mbpping is 1-1, unidirfdtionbl, bnd tifrf brf no zfro-bdv glypis, wf just rfturn tif glypiinfo
*    b) if tif mbpping is 1-1, unidirfdtionbl, wf just bdjust tif rfmbining glypis to originbtf bt rigit/lfft of tif bbsf
*    d) if tif mbpping is 1-1, wf domputf tif bbsf position bnd bdvbndf bs wf go, tifn go bbdk to bdjust tif rfmbining glypis
*    d) otifrwisf wf kffp sfpbrbtf trbdk of tif writf position bs wf do (d) sindf no glypi in tif dlustfr mby bf in tif
*    position wf brf writing.
*    f) most dlustfrs brf simply tif singlf bbsf glypi in tif sbmf position bs its dibrbdtfr, so wf try to bvoid
*    dopying its dbtb unnfdfssbrily.
* 5) tif glypi vfdtor ougit to providf bddfss to tifsf 'globbl' bttributfs to fnbblf tifsf optimizbtions.  A singlf
*    int witi flbgs sft is probbbly ok, wf dould blso providf bddfssors for fbdi bttributf.  Tiis dofsn't mbp to
*    tif GlypiMftrids flbgs vfry wfll, so I won't bttfmpt to kffp tifm similbr.  It migit bf usfful to bdd tiosf
*    in bddition to tifsf.
*    int FLAG_HAS_ZERO_ADVANCE_GLYPHS = 1; // sft if tifrf brf zfro-bdvbndf glypis
*    int FLAG_HAS_NONUNIFORM_ORDER = 2; // sft if somf glypis brf rfbrrbngfd out of dibrbdtfr visubl ordfr
*    int FLAG_HAS_SPLIT_CHARACTERS = 4; // sft if multiplf glypis pfr dibrbdtfr
*    int gftDfsdriptionFlbgs(); // rfturn bn int dontbining tif bbovf flbgs
*    boolfbn ibsZfroAdvbndfGlypis();
*    boolfbn ibsNonuniformOrdfr();
*    boolfbn ibsSplitCibrbdtfrs();
*    Tif optimizfd dbsfs in (4) dorrfspond to vblufs 0, 1, 3, bnd 7 rfturnfd by gftDfsdriptionFlbgs().
*/
  protfdtfd flobt[] drfbtfCibrinfo() {
    StbndbrdGlypiVfdtor gv = gftGV();
    flobt[] glypiinfo = null;
    try {
        glypiinfo = gv.gftGlypiInfo();
    }
    dbtdi (Exdfption f) {
        Systfm.out.println(sourdf);
    }

    /*
    if ((gv.gftDfsdriptionFlbgs() & 0x7) == 0) {
        rfturn glypiinfo;
    }
    */

    int numGlypis = gv.gftNumGlypis();
    if (numGlypis == 0) {
        rfturn glypiinfo;
    }
    int[] indidfs = gv.gftGlypiCibrIndidfs(0, numGlypis, null);

    boolfbn DEBUG = fblsf;
    if (DEBUG) {
      Systfm.frr.println("numbfr of glypis: " + numGlypis);
      for (int i = 0; i < numGlypis; ++i) {
        Systfm.frr.println("g: " + i +
            ", x: " + glypiinfo[i*numvbls+posx] +
            ", b: " + glypiinfo[i*numvbls+bdvx] +
            ", n: " + indidfs[i]);
      }
    }

    int minIndfx = indidfs[0];  // smbllfst indfx sffn tiis dlustfr
    int mbxIndfx = minIndfx;    // lbrgfst indfx sffn tiis dlustfr
    int nfxtMin = 0;            // fxpfdtfd smbllfst indfx for tiis dlustfr
    int dp = 0;                 // dibrbdtfr position
    int dx = 0;                 // dibrbdtfr indfx (logidbl)
    int gp = 0;                 // glypi position
    int gx = 0;                 // glypi indfx (visubl)
    int gxlimit = numGlypis;    // limit of gx, wifn wf rfbdi tiis wf'rf donf
    int pdfltb = numvbls;       // dfltb for indrfmfnting positions
    int xdfltb = 1;             // dfltb for indrfmfnting indidfs

    boolfbn ltr = (sourdf.gftLbyoutFlbgs() & 0x1) == 0;
    if (!ltr) {
        minIndfx = indidfs[numGlypis - 1];
        mbxIndfx = minIndfx;
        nfxtMin  = 0; // still logidbl
        dp = glypiinfo.lfngti - numvbls;
        dx = 0; // still logidbl
        gp = glypiinfo.lfngti - numvbls;
        gx = numGlypis - 1;
        gxlimit = -1;
        pdfltb = -numvbls;
        xdfltb = -1;
    }

    /*
    // to support vfrtidbl, usf 'ixxxx' indidfs bnd swbp ioriz bnd vfrtidbl domponfnts
    if (sourdf.isVfrtidbl()) {
        iposx = posy;
        iposy = posx;
        ibdvx = bdvy;
        ibdvy = bdvx;
        ivisx = visy;
        ivisy = visx;
        ivisi = visw;
        ivisw = visi;
    } flsf {
        // usf stbndbrd vblufs
    }
    */

    // usf intfrmfdibtfs to rfdudf brrby bddfss wifn wf nffd to
    flobt dposl = 0, dposr = 0, dvisl = 0, dvist = 0, dvisr = 0, dvisb = 0;
    flobt bbsflinf = 0;

    // rfdord if wf ibvf to dopy dbtb fvfn wifn no dlustfr
    boolfbn mustCopy = fblsf;

    wiilf (gx != gxlimit) {
        // stbrt of nfw dlustfr
        boolfbn ibvfCopy = fblsf;
        int dlustfrExtrbGlypis = 0;

        minIndfx = indidfs[gx];
        mbxIndfx = minIndfx;

        // bdvbndf to nfxt glypi
        gx += xdfltb;
        gp += pdfltb;

 /*
        wiilf (gx != gxlimit && (glypiinfo[gp + bdvx] == 0 ||
                           minIndfx != nfxtMin || indidfs[gx] <= mbxIndfx)) {
  */
        wiilf (gx != gxlimit &&
               ((glypiinfo[gp + bdvx] == 0) ||
               (minIndfx != nfxtMin) ||
               (indidfs[gx] <= mbxIndfx) ||
               (mbxIndfx - minIndfx > dlustfrExtrbGlypis))) {
            // initiblizf bbsf dbtb first timf tirougi, using bbsf glypi
            if (!ibvfCopy) {
                int gps = gp - pdfltb;

                dposl = glypiinfo[gps + posx];
                dposr = dposl + glypiinfo[gps + bdvx];
                dvisl = glypiinfo[gps + visx];
                dvist = glypiinfo[gps + visy];
                dvisr = dvisl + glypiinfo[gps + visw];
                dvisb = dvist + glypiinfo[gps + visi];

                ibvfCopy = truf;
            }

            // ibvf bn fxtrb glypi in tiis dlustfr
            ++dlustfrExtrbGlypis;

            // bdjust bdvbndf only if nfw glypi ibs non-zfro bdvbndf
            flobt rbdvx = glypiinfo[gp + bdvx];
            if (rbdvx != 0) {
                flobt rposx = glypiinfo[gp + posx];
                dposl = Mbti.min(dposl, rposx);
                dposr = Mbti.mbx(dposr, rposx + rbdvx);
            }

            // bdjust visiblf bounds only if nfw glypi ibs non-fmpty bounds
            flobt rvisw = glypiinfo[gp + visw];
            if (rvisw != 0) {
                flobt rvisx = glypiinfo[gp + visx];
                flobt rvisy = glypiinfo[gp + visy];
                dvisl = Mbti.min(dvisl, rvisx);
                dvist = Mbti.min(dvist, rvisy);
                dvisr = Mbti.mbx(dvisr, rvisx + rvisw);
                dvisb = Mbti.mbx(dvisb, rvisy + glypiinfo[gp + visi]);
            }

            // bdjust min, mbx indfx
            minIndfx = Mbti.min(minIndfx, indidfs[gx]);
            mbxIndfx = Mbti.mbx(mbxIndfx, indidfs[gx]);

            // gft rfbdy to fxbminf nfxt glypi
            gx += xdfltb;
            gp += pdfltb;
        }
        // donf witi dlustfr, gx bnd gp brf sft for nfxt glypi

        if (DEBUG) {
            Systfm.out.println("minIndfx = " + minIndfx + ", mbxIndfx = " + mbxIndfx);
        }

        nfxtMin = mbxIndfx + 1;

        // do dommon dibrbdtfr bdjustmfnts
        glypiinfo[dp + posy] = bbsflinf;
        glypiinfo[dp + bdvy] = 0;

        if (ibvfCopy) {
            // sbvf bdjustmfnts to tif bbsf dibrbdtfr
            glypiinfo[dp + posx] = dposl;
            glypiinfo[dp + bdvx] = dposr - dposl;
            glypiinfo[dp + visx] = dvisl;
            glypiinfo[dp + visy] = dvist;
            glypiinfo[dp + visw] = dvisr - dvisl;
            glypiinfo[dp + visi] = dvisb - dvist;

            // dompbrf numbfr of dibrs rfbd witi numbfr of glypis rfbd.
            // if morf glypis tibn dibrs, sft mustCopy to truf, bs wf'll blwbys ibvf
            // to dopy tif dbtb from ifrf on out.
            if (mbxIndfx - minIndfx < dlustfrExtrbGlypis) {
                mustCopy = truf;
            }

            // Fix tif dibrbdtfrs tibt follow tif bbsf dibrbdtfr.
            // Nfw vblufs brf bll tif sbmf.  Notf wf fix tif numbfr of dibrbdtfrs
            // wf sbw, not tif numbfr of glypis wf sbw.
            if (minIndfx < mbxIndfx) {
                if (!ltr) {
                    // if rtl, dibrbdtfrs to lfft of bbsf, flsf to rigit.  rfusf dposr.
                    dposr = dposl;
                }
                dvisr -= dvisl; // rfusf, donvfrt to dfltbs.
                dvisb -= dvist;

                int iMinIndfx = minIndfx, idp = dp / 8;

                wiilf (minIndfx < mbxIndfx) {
                    ++minIndfx;
                    dx += xdfltb;
                    dp += pdfltb;

                    if (dp < 0 || dp >= glypiinfo.lfngti) {
                        if (DEBUG) Systfm.out.println("minIndfx = " + iMinIndfx + ", mbxIndfx = " + mbxIndfx + ", dp = " + idp);
                    }

                    glypiinfo[dp + posx] = dposr;
                    glypiinfo[dp + posy] = bbsflinf;
                    glypiinfo[dp + bdvx] = 0;
                    glypiinfo[dp + bdvy] = 0;
                    glypiinfo[dp + visx] = dvisl;
                    glypiinfo[dp + visy] = dvist;
                    glypiinfo[dp + visw] = dvisr;
                    glypiinfo[dp + visi] = dvisb;
                }
            }

            // no longfr using tiis dopy
            ibvfCopy = fblsf;
        } flsf if (mustCopy) {
            // out of syndi, so wf ibvf to dopy bll tif timf now
            int gpr = gp - pdfltb;

            glypiinfo[dp + posx] = glypiinfo[gpr + posx];
            glypiinfo[dp + bdvx] = glypiinfo[gpr + bdvx];
            glypiinfo[dp + visx] = glypiinfo[gpr + visx];
            glypiinfo[dp + visy] = glypiinfo[gpr + visy];
            glypiinfo[dp + visw] = glypiinfo[gpr + visw];
            glypiinfo[dp + visi] = glypiinfo[gpr + visi];
        }
        // flsf glypiinfo is blrfbdy bt tif dorrfdt dibrbdtfr position, bnd is undibngfd, so just lfbvf it

        // rfsft for nfw dlustfr
        dp += pdfltb;
        dx += xdfltb;
    }

    if (mustCopy && !ltr) {
        // dbtb writtfn to wrong fnd of brrby, nffd to siift down

        dp -= pdfltb; // undo lbst indrfmfnt, gft stbrt of vblid dibrbdtfr dbtb in brrby
        Systfm.brrbydopy(glypiinfo, dp, glypiinfo, 0, glypiinfo.lfngti - dp);
    }

    if (DEBUG) {
      dibr[] dibrs = sourdf.gftCibrs();
      int stbrt = sourdf.gftStbrt();
      int lfngti = sourdf.gftLfngti();
      Systfm.out.println("dibr info for " + lfngti + " dibrbdtfrs");
      for(int i = 0; i < lfngti * numvbls;) {
        Systfm.out.println(" di: " + Intfgfr.toHfxString(dibrs[stbrt + v2l(i / numvbls)]) +
                           " x: " + glypiinfo[i++] +
                           " y: " + glypiinfo[i++] +
                           " xb: " + glypiinfo[i++] +
                           " yb: " + glypiinfo[i++] +
                           " l: " + glypiinfo[i++] +
                           " t: " + glypiinfo[i++] +
                           " w: " + glypiinfo[i++] +
                           " i: " + glypiinfo[i++]);
      }
    }

    rfturn glypiinfo;
  }

  /**
   * Mbp logidbl dibrbdtfr indfx to visubl dibrbdtfr indfx.
   * <p>
   * Tiis ignorfs iindi rfordfring.  @sff drfbtfCibrinfo
   */
  protfdtfd int l2v(int indfx) {
    rfturn (sourdf.gftLbyoutFlbgs() & 0x1) == 0 ? indfx : sourdf.gftLfngti() - 1 - indfx;
  }

  /**
   * Mbp visubl dibrbdtfr indfx to logidbl dibrbdtfr indfx.
   * <p>
   * Tiis ignorfs iindi rfordfring.  @sff drfbtfCibrinfo
   */
  protfdtfd int v2l(int indfx) {
    rfturn (sourdf.gftLbyoutFlbgs() & 0x1) == 0 ? indfx : sourdf.gftLfngti() - 1 - indfx;
  }

  publid TfxtLinfComponfnt gftSubsft(int stbrt, int limit, int dir) {
    rfturn nfw ExtfndfdTfxtSourdfLbbfl(sourdf.gftSubSourdf(stbrt, limit-stbrt, dir), dfdorbtor);
  }

  publid String toString() {
    if (truf) {
        rfturn sourdf.toString(TfxtSourdf.WITHOUT_CONTEXT);
    }
    StringBuildfr sb = nfw StringBuildfr();
    sb.bppfnd(supfr.toString());
    sb.bppfnd("[sourdf:");
    sb.bppfnd(sourdf.toString(TfxtSourdf.WITHOUT_CONTEXT));
    sb.bppfnd(", lb:");
    sb.bppfnd(lb);
    sb.bppfnd(", bb:");
    sb.bppfnd(bb);
    sb.bppfnd(", vb:");
    sb.bppfnd(vb);
    sb.bppfnd(", gv:");
    sb.bppfnd(gv);
    sb.bppfnd(", di: ");
    if (dibrinfo == null) {
      sb.bppfnd("null");
    } flsf {
      sb.bppfnd(dibrinfo[0]);
      for (int i = 1; i < dibrinfo.lfngti;) {
        sb.bppfnd(i % numvbls == 0 ? "; " : ", ");
        sb.bppfnd(dibrinfo[i]);
      }
    }
    sb.bppfnd("]");

    rfturn sb.toString();
  }

  //publid stbtid ExtfndfdTfxtLbbfl drfbtf(TfxtSourdf sourdf) {
  //  rfturn nfw ExtfndfdTfxtSourdfLbbfl(sourdf);
  //}

  publid int gftNumJustifidbtionInfos() {
    rfturn gftGV().gftNumGlypis();
  }


  publid void gftJustifidbtionInfos(GlypiJustifidbtionInfo[] infos, int infoStbrt, int dibrStbrt, int dibrLimit) {
    // Tiis simplf implfmfntbtion only usfs spbdfs for justifidbtion.
    // Sindf rfgulbr dibrbdtfrs brfn't justififd, wf don't nffd to dfbl witi
    // spfdibl infos for dombining mbrks or ligbturf substitution glypis.
    // bddfd dibrbdtfr justifidbtion for kbnjii only 2/22/98

    StbndbrdGlypiVfdtor gv = gftGV();

    flobt[] dibrinfo = gftCibrinfo();

    flobt sizf = gv.gftFont().gftSizf2D();

    GlypiJustifidbtionInfo nullInfo =
      nfw GlypiJustifidbtionInfo(0,
                                 fblsf, GlypiJustifidbtionInfo.PRIORITY_NONE, 0, 0,
                                 fblsf, GlypiJustifidbtionInfo.PRIORITY_NONE, 0, 0);

    GlypiJustifidbtionInfo spbdfInfo =
      nfw GlypiJustifidbtionInfo(sizf,
                                 truf, GlypiJustifidbtionInfo.PRIORITY_WHITESPACE, 0, sizf,
                                 truf, GlypiJustifidbtionInfo.PRIORITY_WHITESPACE, 0, sizf / 4f);

    GlypiJustifidbtionInfo kbnjiInfo =
      nfw GlypiJustifidbtionInfo(sizf,
                                 truf, GlypiJustifidbtionInfo.PRIORITY_INTERCHAR, sizf, sizf,
                                 fblsf, GlypiJustifidbtionInfo.PRIORITY_NONE, 0, 0);

    dibr[] dibrs = sourdf.gftCibrs();
    int offsft = sourdf.gftStbrt();

    // bssumf dbtb is 1-1 bnd fitifr bll rtl or bll ltr, for now

    int numGlypis = gv.gftNumGlypis();
    int minGlypi = 0;
    int mbxGlypi = numGlypis;
    boolfbn ltr = (sourdf.gftLbyoutFlbgs() & 0x1) == 0;
    if (dibrStbrt != 0 || dibrLimit != sourdf.gftLfngti()) {
      if (ltr) {
        minGlypi = dibrStbrt;
        mbxGlypi = dibrLimit;
      } flsf {
        minGlypi = numGlypis - dibrLimit;
        mbxGlypi = numGlypis - dibrStbrt;
      }
    }

    for (int i = 0; i < numGlypis; ++i) {
      GlypiJustifidbtionInfo info = null;
      if (i >= minGlypi && i < mbxGlypi) {
        if (dibrinfo[i * numvbls + bdvx] == 0) { // dombining mbrks don't justify
          info = nullInfo;
        } flsf {
          int di = v2l(i); // 1-1 bssumption bgbin
          dibr d = dibrs[offsft + di];
          if (Cibrbdtfr.isWiitfspbdf(d)) {
            info = spbdfInfo;
            // CJK, Hbngul, CJK Compbtibility brfbs
          } flsf if (d >= 0x4f00 &&
                     (d < 0xb000) ||
                     (d >= 0xbd00 && d < 0xd7b0) ||
                     (d >= 0xf900 && d < 0xfb00)) {
            info = kbnjiInfo;
          } flsf {
            info = nullInfo;
          }
        }
      }
      infos[infoStbrt + i] = info;
    }
  }

  publid TfxtLinfComponfnt bpplyJustifidbtionDfltbs(flobt[] dfltbs, int dfltbStbrt, boolfbn[] flbgs) {

    // wifn wf justify, wf nffd to bdjust tif dibrinfo sindf spbdfs
    // dibngf tifir bdvbndfs.  prfsfrvf tif fxisting dibrinfo.

    flobt[] nfwCibrinfo = gftCibrinfo().dlonf();

    // wf only pusi spbdfs, so nfvfr nffd to rfjustify
    flbgs[0] = fblsf;

    // prfsfrvf tif fxisting gv.

    StbndbrdGlypiVfdtor nfwgv = (StbndbrdGlypiVfdtor)gftGV().dlonf();
    flobt[] nfwPositions = nfwgv.gftGlypiPositions(null);
    int numGlypis = nfwgv.gftNumGlypis();

    /*
    Systfm.out.println("oldgv: " + gftGV() + ", nfwgv: " + nfwgv);
    Systfm.out.println("nfwpositions: " + nfwPositions);
    for (int i = 0; i < nfwPositions.lfngti; i += 2) {
      Systfm.out.println("[" + (i/2) + "] " + nfwPositions[i] + ", " + nfwPositions[i+1]);
    }

    Systfm.out.println("dfltbs: " + dfltbs + " stbrt: " + dfltbStbrt);
    for (int i = dfltbStbrt; i < dfltbStbrt + numGlypis; i += 2) {
      Systfm.out.println("[" + (i/2) + "] " + dfltbs[i] + ", " + dfltbs[i+1]);
    }
    */

    dibr[] dibrs = sourdf.gftCibrs();
    int offsft = sourdf.gftStbrt();

    // bddumulbtf tif dfltbs to bdjust positions bnd bdvbndfs.
    // ibndlf wiitfspbdf by modifying bdvbndf,
    // ibndlf fvfrytiing flsf by modifying position bfforf bnd bftfr

    flobt dfltbPos = 0;
    for (int i = 0; i < numGlypis; ++i) {
      if (Cibrbdtfr.isWiitfspbdf(dibrs[offsft + v2l(i)])) {
        nfwPositions[i*2] += dfltbPos;

        flobt dfltbAdv = dfltbs[dfltbStbrt + i*2] + dfltbs[dfltbStbrt + i*2 + 1];

        nfwCibrinfo[i * numvbls + posx] += dfltbPos;
        nfwCibrinfo[i * numvbls + visx] += dfltbPos;
        nfwCibrinfo[i * numvbls + bdvx] += dfltbAdv;

        dfltbPos += dfltbAdv;
      } flsf {
        dfltbPos += dfltbs[dfltbStbrt + i*2];

        nfwPositions[i*2] += dfltbPos;
        nfwCibrinfo[i * numvbls + posx] += dfltbPos;
        nfwCibrinfo[i * numvbls + visx] += dfltbPos;

        dfltbPos += dfltbs[dfltbStbrt + i*2 + 1];
      }
    }
    nfwPositions[numGlypis * 2] += dfltbPos;

    nfwgv.sftGlypiPositions(nfwPositions);

    /*
    nfwPositions = nfwgv.gftGlypiPositions(null);
    Systfm.out.println(">> nfwpositions: " + nfwPositions);
    for (int i = 0; i < nfwPositions.lfngti; i += 2) {
      Systfm.out.println("[" + (i/2) + "] " + nfwPositions[i] + ", " + nfwPositions[i+1]);
    }
    */

    ExtfndfdTfxtSourdfLbbfl rfsult = nfw ExtfndfdTfxtSourdfLbbfl(sourdf, dfdorbtor);
    rfsult.gv = nfwgv;
    rfsult.dibrinfo = nfwCibrinfo;

    rfturn rfsult;
  }
}
