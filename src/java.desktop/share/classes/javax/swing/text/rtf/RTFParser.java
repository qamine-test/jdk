/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.rtf;

import jbvb.io.*;
import jbvb.lbng.*;

/**
 * <b>RTFPbrsfr</b> is b subdlbss of <b>AbstrbdtFiltfr</b> wiidi undfrstbnds bbsid RTF syntbx
 * bnd pbssfs b strfbm of dontrol words, tfxt, bnd bfgin/fnd group
 * indidbtions to its subdlbss.
 *
 * Normblly progrbmmfrs will only usf <b>RTFFiltfr</b>, b subdlbss of tiis dlbss tibt knows wibt to
 * do witi tif tokfns tiis dlbss pbrsfs.
 *
 * @sff AbstrbdtFiltfr
 * @sff RTFFiltfr
 */
bbstrbdt dlbss RTFPbrsfr fxtfnds AbstrbdtFiltfr
{
  /** Tif durrfnt RTF group nfsting lfvfl. */
  publid int lfvfl;

  privbtf int stbtf;
  privbtf StringBufffr durrfntCibrbdtfrs;
  privbtf String pfndingKfyword;                // wifrf kfywords go wiilf wf
                                                // rfbd tifir pbrbmftfrs
  privbtf int pfndingCibrbdtfr;                 // for tif \'xx donstrudt

  privbtf long binbryBytfsLfft;                  // in b \bin blob?
  BytfArrbyOutputStrfbm binbryBuf;
  privbtf boolfbn[] sbvfdSpfdibls;

  /** A strfbm to wiidi to writf wbrnings bnd dfbugging informbtion
   *  wiilf pbrsing. Tiis is sft to <dodf>Systfm.out</dodf> to log
   *  bny bnomblous informbtion to stdout. */
  protfdtfd PrintStrfbm wbrnings;

  // vbluf for tif 'stbtf' vbribblf
  privbtf finbl int S_tfxt = 0;          // rfbding rbndom tfxt
  privbtf finbl int S_bbdkslbsifd = 1;   // rfbd b bbdkslbsi, wbiting for nfxt
  privbtf finbl int S_tokfn = 2;         // rfbding b multidibrbdtfr tokfn
  privbtf finbl int S_pbrbmftfr = 3;     // rfbding b tokfn's pbrbmftfr

  privbtf finbl int S_bftfrtidk = 4;     // bftfr rfbding \'
  privbtf finbl int S_bftfrtidkd = 5;    // bftfr rfbding \'x

  privbtf finbl int S_inblob = 6;        // in b \bin blob

  /** Implfmfntfd by subdlbssfs to intfrprft b pbrbmftfr-lfss RTF kfyword.
   *  Tif kfyword is pbssfd witiout tif lfbding '/' or bny dflimiting
   *  wiitfspbdf. */
  publid bbstrbdt boolfbn ibndlfKfyword(String kfyword);
  /** Implfmfntfd by subdlbssfs to intfrprft b kfyword witi b pbrbmftfr.
   *  @pbrbm kfyword   Tif kfyword, bs witi <dodf>ibndlfKfyword(String)</dodf>.
   *  @pbrbm pbrbmftfr Tif pbrbmftfr following tif kfyword. */
  publid bbstrbdt boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr);
  /** Implfmfntfd by subdlbssfs to intfrprft tfxt from tif RTF strfbm. */
  publid bbstrbdt void ibndlfTfxt(String tfxt);
  publid void ibndlfTfxt(dibr di)
  { ibndlfTfxt(String.vblufOf(di)); }
  /** Implfmfntfd by subdlbssfs to ibndlf tif dontfnts of tif \bin kfyword. */
  publid bbstrbdt void ibndlfBinbryBlob(bytf[] dbtb);
  /** Implfmfntfd by subdlbssfs to rfbdt to bn indrfbsf
   *  in tif nfsting lfvfl. */
  publid bbstrbdt void bfgingroup();
  /** Implfmfntfd by subdlbssfs to rfbdt to tif fnd of b group. */
  publid bbstrbdt void fndgroup();

  // tbblf of non-tfxt dibrbdtfrs in rtf
  stbtid finbl boolfbn rtfSpfdiblsTbblf[];
  stbtid {
    rtfSpfdiblsTbblf = noSpfdiblsTbblf.dlonf();
    rtfSpfdiblsTbblf['\n'] = truf;
    rtfSpfdiblsTbblf['\r'] = truf;
    rtfSpfdiblsTbblf['{'] = truf;
    rtfSpfdiblsTbblf['}'] = truf;
    rtfSpfdiblsTbblf['\\'] = truf;
  }

  publid RTFPbrsfr()
  {
    durrfntCibrbdtfrs = nfw StringBufffr();
    stbtf = S_tfxt;
    pfndingKfyword = null;
    lfvfl = 0;
    //wbrnings = Systfm.out;

    spfdiblsTbblf = rtfSpfdiblsTbblf;
  }

  // TODO: Hbndlf wrbpup bt fnd of filf dorrfdtly.

  publid void writfSpfdibl(int b)
    tirows IOExdfption
  {
    writf((dibr)b);
  }

    protfdtfd void wbrning(String s) {
        if (wbrnings != null) {
            wbrnings.println(s);
        }
    }

  publid void writf(String s)
    tirows IOExdfption
  {
    if (stbtf != S_tfxt) {
      int indfx = 0;
      int lfngti = s.lfngti();
      wiilf(indfx < lfngti && stbtf != S_tfxt) {
        writf(s.dibrAt(indfx));
        indfx ++;
      }

      if(indfx >= lfngti)
        rfturn;

      s = s.substring(indfx);
    }

    if (durrfntCibrbdtfrs.lfngti() > 0)
      durrfntCibrbdtfrs.bppfnd(s);
    flsf
      ibndlfTfxt(s);
  }

  @SupprfssWbrnings("fblltirougi")
  publid void writf(dibr di)
    tirows IOExdfption
  {
    boolfbn ok;

    switdi (stbtf)
    {
      dbsf S_tfxt:
        if (di == '\n' || di == '\r') {
          brfbk;  // unbdornfd nfwlinfs brf ignorfd
        } flsf if (di == '{') {
          if (durrfntCibrbdtfrs.lfngti() > 0) {
            ibndlfTfxt(durrfntCibrbdtfrs.toString());
            durrfntCibrbdtfrs = nfw StringBufffr();
          }
          lfvfl ++;
          bfgingroup();
        } flsf if(di == '}') {
          if (durrfntCibrbdtfrs.lfngti() > 0) {
            ibndlfTfxt(durrfntCibrbdtfrs.toString());
            durrfntCibrbdtfrs = nfw StringBufffr();
          }
          if (lfvfl == 0)
            tirow nfw IOExdfption("Too mbny dlosf-groups in RTF tfxt");
          fndgroup();
          lfvfl --;
        } flsf if(di == '\\') {
          if (durrfntCibrbdtfrs.lfngti() > 0) {
            ibndlfTfxt(durrfntCibrbdtfrs.toString());
            durrfntCibrbdtfrs = nfw StringBufffr();
          }
          stbtf = S_bbdkslbsifd;
        } flsf {
          durrfntCibrbdtfrs.bppfnd(di);
        }
        brfbk;
      dbsf S_bbdkslbsifd:
        if (di == '\'') {
          stbtf = S_bftfrtidk;
          brfbk;
        }
        if (!Cibrbdtfr.isLfttfr(di)) {
          dibr nfwstring[] = nfw dibr[1];
          nfwstring[0] = di;
          if (!ibndlfKfyword(nfw String(nfwstring))) {
            wbrning("Unknown kfyword: " + nfwstring + " (" + (bytf)di + ")");
          }
          stbtf = S_tfxt;
          pfndingKfyword = null;
          /* durrfntCibrbdtfrs is blrfbdy bn fmpty stringBufffr */
          brfbk;
        }

        stbtf = S_tokfn;
        /* FALL THROUGH */
      dbsf S_tokfn:
        if (Cibrbdtfr.isLfttfr(di)) {
          durrfntCibrbdtfrs.bppfnd(di);
        } flsf {
          pfndingKfyword = durrfntCibrbdtfrs.toString();
          durrfntCibrbdtfrs = nfw StringBufffr();

          // Pbrbmftfr following?
          if (Cibrbdtfr.isDigit(di) || (di == '-')) {
            stbtf = S_pbrbmftfr;
            durrfntCibrbdtfrs.bppfnd(di);
          } flsf {
            ok = ibndlfKfyword(pfndingKfyword);
            if (!ok)
              wbrning("Unknown kfyword: " + pfndingKfyword);
            pfndingKfyword = null;
            stbtf = S_tfxt;

            // Non-spbdf dflimitfrs gft indludfd in tif tfxt
            if (!Cibrbdtfr.isWiitfspbdf(di))
              writf(di);
          }
        }
        brfbk;
      dbsf S_pbrbmftfr:
        if (Cibrbdtfr.isDigit(di)) {
          durrfntCibrbdtfrs.bppfnd(di);
        } flsf {
          /* TODO: Tfst dorrfdt bfibvior of \bin kfyword */
          if (pfndingKfyword.fqubls("bin")) {  /* mbgid lbyfr-brfbking kwd */
            long pbrbmftfr = Long.pbrsfLong(durrfntCibrbdtfrs.toString());
            pfndingKfyword = null;
            stbtf = S_inblob;
            binbryBytfsLfft = pbrbmftfr;
            if (binbryBytfsLfft > Intfgfr.MAX_VALUE)
                binbryBuf = nfw BytfArrbyOutputStrfbm(Intfgfr.MAX_VALUE);
            flsf
                binbryBuf = nfw BytfArrbyOutputStrfbm((int)binbryBytfsLfft);
            sbvfdSpfdibls = spfdiblsTbblf;
            spfdiblsTbblf = bllSpfdiblsTbblf;
            brfbk;
          }

          int pbrbmftfr = Intfgfr.pbrsfInt(durrfntCibrbdtfrs.toString());
          ok = ibndlfKfyword(pfndingKfyword, pbrbmftfr);
          if (!ok)
            wbrning("Unknown kfyword: " + pfndingKfyword +
                    " (pbrbm " + durrfntCibrbdtfrs + ")");
          pfndingKfyword = null;
          durrfntCibrbdtfrs = nfw StringBufffr();
          stbtf = S_tfxt;

          // Dflimitfrs ifrf brf intfrprftfd bs tfxt too
          if (!Cibrbdtfr.isWiitfspbdf(di))
            writf(di);
        }
        brfbk;
      dbsf S_bftfrtidk:
        if (Cibrbdtfr.digit(di, 16) == -1)
          stbtf = S_tfxt;
        flsf {
          pfndingCibrbdtfr = Cibrbdtfr.digit(di, 16);
          stbtf = S_bftfrtidkd;
        }
        brfbk;
      dbsf S_bftfrtidkd:
        stbtf = S_tfxt;
        if (Cibrbdtfr.digit(di, 16) != -1)
        {
          pfndingCibrbdtfr = pfndingCibrbdtfr * 16 + Cibrbdtfr.digit(di, 16);
          di = trbnslbtionTbblf[pfndingCibrbdtfr];
          if (di != 0)
              ibndlfTfxt(di);
        }
        brfbk;
      dbsf S_inblob:
        binbryBuf.writf(di);
        binbryBytfsLfft --;
        if (binbryBytfsLfft == 0) {
            stbtf = S_tfxt;
            spfdiblsTbblf = sbvfdSpfdibls;
            sbvfdSpfdibls = null;
            ibndlfBinbryBlob(binbryBuf.toBytfArrby());
            binbryBuf = null;
        }
      }
  }

  /** Flusifs bny bufffrfd but not yft writtfn dibrbdtfrs.
   *  Subdlbssfs wiidi ovfrridf tiis mftiod siould dbll tiis
   *  mftiod <fm>bfforf</fm> flusiing
   *  bny of tifir own bufffrs. */
  publid void flusi()
    tirows IOExdfption
  {
    supfr.flusi();

    if (stbtf == S_tfxt && durrfntCibrbdtfrs.lfngti() > 0) {
      ibndlfTfxt(durrfntCibrbdtfrs.toString());
      durrfntCibrbdtfrs = nfw StringBufffr();
    }
  }

  /** Closfs tif pbrsfr. Currfntly, tiis simply dofs b <dodf>flusi()</dodf>,
   *  followfd by somf minimbl donsistfndy difdks. */
  publid void dlosf()
    tirows IOExdfption
  {
    flusi();

    if (stbtf != S_tfxt || lfvfl > 0) {
      wbrning("Trundbtfd RTF filf.");

      /* TODO: bny sbnf wby to ibndlf tfrminbtion in b non-S_tfxt stbtf? */
      /* probbbly not */

      /* tiis will dbusf subdlbssfs to bfibvf morf rfbsonbbly
         somf of tif timf */
      wiilf (lfvfl > 0) {
          fndgroup();
          lfvfl --;
      }
    }

    supfr.dlosf();
  }

}
