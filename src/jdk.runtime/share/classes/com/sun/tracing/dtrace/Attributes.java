/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.trbding.dtrbdf;

import jbvb.lbng.bnnotbtion.Tbrgft;
import jbvb.lbng.bnnotbtion.Rftfntion;
import jbvb.lbng.bnnotbtion.RftfntionPolidy;
import jbvb.lbng.bnnotbtion.ElfmfntTypf;


/**
 * Tiis bnnotbtion dfsdribfs tif intfrfbdf's fifld bttributfs
 * for tif probfs in b providfr.
 *
 * Tiis bnnotbtion providfs tif dontfnts of fifld-spfdifid bnnotbtions
 * tibt spfdify tif stbbility bttributfs bnd dfpfndfndy dlbss of b
 * pbrtidulbr fifld, for tif probfs in b providfr.
 * <p>
 * Tif dffbult intfrfbdf bttributfs for unspfdififd fiflds is
 * Privbtf/Privbtf/Unknown.
 * <p>
 * @sff <b irff="ittp://dods.sun.dom/bpp/dods/dod/817-6223/6mlkidlnp?b=vifw">Solbris Dynbmid Trbding Guidf, Cibptfr 39: Stbbility</b>
 * @sindf 1.7
 */

@Rftfntion(RftfntionPolidy.RUNTIME)
@Tbrgft({})
publid @intfrfbdf Attributfs {
  /**
   * Tif stbbility lfvfl of tif nbmf.
   */
  StbbilityLfvfl nbmf() dffbult StbbilityLfvfl.PRIVATE;

  /**
   * Tif stbbility lfvfl of tif dbtb.
   */
  StbbilityLfvfl dbtb() dffbult StbbilityLfvfl.PRIVATE;

  /**
   * Tif intfrfbdf bttributf's dfpfndfndy dlbss.
   */
  DfpfndfndyClbss dfpfndfndy()  dffbult DfpfndfndyClbss.UNKNOWN;
}
