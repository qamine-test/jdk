/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;

/**
 * Intfrfbdf Attributf is tif bbsf intfrfbdf implfmfntfd by bny bnd fvfry
 * printing bttributf dlbss to indidbtf tibt tif dlbss rfprfsfnts b
 * printing bttributf. All printing bttributfs brf sfriblizbblf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid intfrfbdf Attributf fxtfnds Sfriblizbblf {

  /**
   * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
   * for tiis printing bttributf vbluf wifn it is bddfd to bn bttributf sft.
   *
   * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
   *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
   */
  publid Clbss<? fxtfnds Attributf> gftCbtfgory();

  /**
   * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
   * instbndf.
   * <P>
   * <I>Notf:</I> Tiis mftiod is intfndfd to providf b dffbult, nonlodblizfd
   * string for tif bttributf's dbtfgory. If two bttributf objfdts rfturn tif
   * sbmf dbtfgory from tif <CODE>gftCbtfgory()</CODE> mftiod, tify siould
   * rfturn tif sbmf nbmf from tif <CODE>gftNbmf()</CODE> mftiod.
   *
   * @rfturn  Attributf dbtfgory nbmf.
   */
  publid String gftNbmf();

}
