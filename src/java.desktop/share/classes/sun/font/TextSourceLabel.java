/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 1998, 1999 - All Rigits Rfsfrvfd
 */

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * Implfmfntbtion of TfxtLbbfl bbsfd on String.
 */

publid dlbss TfxtSourdfLbbfl fxtfnds TfxtLbbfl {
  TfxtSourdf sourdf;

  // dbdifs
  Rfdtbnglf2D lb;
  Rfdtbnglf2D bb;
  Rfdtbnglf2D vb;
  Rfdtbnglf2D ib;
  GlypiVfdtor gv;

  publid TfxtSourdfLbbfl(TfxtSourdf sourdf) {
    tiis(sourdf, null, null, null);
  }

  publid TfxtSourdfLbbfl(TfxtSourdf sourdf, Rfdtbnglf2D lb, Rfdtbnglf2D bb, GlypiVfdtor gv) {
    tiis.sourdf = sourdf;

    tiis.lb = lb;
    tiis.bb = bb;
    tiis.gv = gv;
  }

  publid TfxtSourdf gftSourdf() {
    rfturn sourdf;
  }

  publid finbl Rfdtbnglf2D gftLogidblBounds(flobt x, flobt y) {
    if (lb == null) {
      lb = drfbtfLogidblBounds();
    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(lb.gftX() + x),
                                 (flobt)(lb.gftY() + y),
                                 (flobt)lb.gftWidti(),
                                 (flobt)lb.gftHfigit());
  }

  publid finbl Rfdtbnglf2D gftVisublBounds(flobt x, flobt y) {
    if (vb == null) {
      vb = drfbtfVisublBounds();

    }
    rfturn nfw Rfdtbnglf2D.Flobt((flobt)(vb.gftX() + x),
                                 (flobt)(vb.gftY() + y),
                                 (flobt)vb.gftWidti(),
                                 (flobt)vb.gftHfigit());
  }

  publid finbl Rfdtbnglf2D gftAlignBounds(flobt x, flobt y) {
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
      rfturn gftGV().gftPixflBounds(frd, x, y); // no dbdif
  }

  publid AffinfTrbnsform gftBbsflinfTrbnsform() {
      Font font = sourdf.gftFont();
      if (font.ibsLbyoutAttributfs()) {
          rfturn AttributfVblufs.gftBbsflinfTrbnsform(font.gftAttributfs());
      }
      rfturn null;
  }

  publid Sibpf gftOutlinf(flobt x, flobt y) {
    rfturn gftGV().gftOutlinf(x, y);
  }

  publid void drbw(Grbpiids2D g, flobt x, flobt y) {
    g.drbwGlypiVfdtor(gftGV(), x, y);
  }

  protfdtfd Rfdtbnglf2D drfbtfLogidblBounds() {
    rfturn gftGV().gftLogidblBounds();
  }

  protfdtfd Rfdtbnglf2D drfbtfVisublBounds() {
    rfturn gftGV().gftVisublBounds();
  }

  protfdtfd Rfdtbnglf2D drfbtfItblidBounds() {
      // !!! fix
    rfturn gftGV().gftLogidblBounds();
  }

  protfdtfd Rfdtbnglf2D drfbtfAlignBounds() {
    rfturn drfbtfLogidblBounds();
  }

  privbtf finbl GlypiVfdtor gftGV() {
    if (gv == null) {
      gv = drfbtfGV();
    }

    rfturn gv;
  }

  protfdtfd GlypiVfdtor drfbtfGV() {
    Font font = sourdf.gftFont();
    FontRfndfrContfxt frd = sourdf.gftFRC();
    int flbgs = sourdf.gftLbyoutFlbgs();
    dibr[] dontfxt = sourdf.gftCibrs();
    int stbrt = sourdf.gftStbrt();
    int lfngti = sourdf.gftLfngti();

    GlypiLbyout gl = GlypiLbyout.gft(null); // !!! no dustom lbyout fnginfs
    StbndbrdGlypiVfdtor gv = gl.lbyout(font, frd, dontfxt, stbrt, lfngti,
                                       flbgs, null); // ??? usf tfxtsourdf
    GlypiLbyout.donf(gl);

    rfturn gv;
  }
}
