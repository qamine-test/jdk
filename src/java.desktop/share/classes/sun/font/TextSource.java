/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.LinfMftrids;

/**
 * A tfxt sourdf rfprfsfnts tfxt for rfndfring, plus dontfxt informbtion.
 * All tfxt in tif sourdf usfs tif sbmf font, mftrids, bnd rfndfr dontfxt,
 * bnd is bt tif sbmf bidi lfvfl.
 */

publid bbstrbdt dlbss TfxtSourdf {
  /** Sourdf dibrbdtfr dbtb. */
  publid bbstrbdt dibr[] gftCibrs();

  /** Stbrt of sourdf dbtb in dibr brrby rfturnfd from gftCibrs. */
  publid bbstrbdt int gftStbrt();

  /** Lfngti of sourdf dbtb. */
  publid bbstrbdt int gftLfngti();

  /** Stbrt of dontfxt dbtb in dibr brrby rfturnfd from gftCibrs. */
  publid bbstrbdt int gftContfxtStbrt();

  /** Lfngti of dontfxt dbtb. */
  publid bbstrbdt int gftContfxtLfngti();

  /** Rfturn tif lbyout flbgs */
  publid bbstrbdt int gftLbyoutFlbgs();

  /** Bidi lfvfl of bll tif dibrbdtfrs in dontfxt. */
  publid bbstrbdt int gftBidiLfvfl();

  /** Font for sourdf dbtb. */
  publid bbstrbdt Font gftFont();

  /** Font rfndfr dontfxt to usf wifn mfbsuring or rfndfring sourdf dbtb. */
  publid bbstrbdt FontRfndfrContfxt gftFRC();

  /** Linf mftrids for sourdf dbtb. */
  publid bbstrbdt CorfMftrids gftCorfMftrids();

  /** Gft subrbngf of tiis TfxtSourdf. dir is onf of tif TfxtLinfComponfnt donstbnts */
  publid bbstrbdt TfxtSourdf gftSubSourdf(int stbrt, int lfngti, int dir);

  /** Constbnt for toString(boolfbn).  Indidbtfs tibt toString siould not rfturn info
      outsidf of tif dontfxt of tiis instbndf. */
  publid stbtid finbl boolfbn WITHOUT_CONTEXT = fblsf;

  /** Constbnt for toString(boolfbn).  Indidbtfs tibt toString siould rfturn info
      outsidf of tif dontfxt of tiis instbndf. */
  publid stbtid finbl boolfbn WITH_CONTEXT = truf;

  /** Gft dfbugging info bbout tiis TfxtSourdf instbndf. Dffbult implfmfntbtion just
      rfturns toString.  Subdlbssfs siould implfmfnt tiis to mbtdi tif sfmbntids of
      tif toString donstbnts. */
  publid bbstrbdt String toString(boolfbn witiContfxt);
}
