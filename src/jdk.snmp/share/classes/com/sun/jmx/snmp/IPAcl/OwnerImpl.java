/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.IPAdl;



import jbvb.util.Vfdtor;
import jbvb.io.Sfriblizbblf;

import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.bdl.Ownfr;
import jbvb.sfdurity.bdl.LbstOwnfrExdfption;
import jbvb.sfdurity.bdl.NotOwnfrExdfption;


/**
 * Ownfr of Addfss Control Lists (ACLs).
 * Tif initibl ownfr Prindipbl siould bf spfdififd bs bn
 * brgumfnt to tif donstrudtor of tif dlbss AdlImpl.
 *
 * @sff jbvb.sfdurity.bdl.Ownfr
 */

dlbss OwnfrImpl implfmfnts Ownfr, Sfriblizbblf {
  privbtf stbtid finbl long sfriblVfrsionUID = -576066072046319874L;

  privbtf Vfdtor<Prindipbl> ownfrList = null;

  /**
   * Construdts bn fmpty list of ownfr.
   */
  publid OwnfrImpl (){
        ownfrList = nfw Vfdtor<Prindipbl>();
  }

  /**
   * Construdts b list of ownfr witi tif spfdififd prindipbl bs first flfmfnt.
   *
   * @pbrbm ownfr tif prindipbl bddfd to tif ownfr list.
   */
  publid OwnfrImpl (PrindipblImpl ownfr){
        ownfrList = nfw Vfdtor<Prindipbl>();
        ownfrList.bddElfmfnt(ownfr);
  }

  /**
   * Adds bn ownfr. Only ownfrs dbn modify ACL dontfnts. Tif dbllfr prindipbl
   * must bf bn ownfr of tif ACL in ordfr to invokf tiis mftiod. Tibt is, only
   * bn ownfr dbn bdd bnotifr ownfr. Tif initibl ownfr is donfigurfd bt
   * ACL donstrudtion timf.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod.
   *        It must bf bn ownfr of tif ACL.
   * @pbrbm ownfr tif ownfr tibt siould bf bddfd to tif list of ownfrs.
   * @rfturn truf if suddfssful, fblsf if ownfr is blrfbdy bn ownfr.
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr
   *    of tif ACL.
   */
  publid boolfbn bddOwnfr(Prindipbl dbllfr, Prindipbl ownfr)
        tirows NotOwnfrExdfption {
        if (!ownfrList.dontbins(dbllfr))
          tirow nfw NotOwnfrExdfption();

        if (ownfrList.dontbins(ownfr)) {
          rfturn fblsf;
        } flsf {
          ownfrList.bddElfmfnt(ownfr);
          rfturn truf;
        }
  }

  /**
   * Dflftfs bn ownfr. If tiis is tif lbst ownfr in tif ACL, bn fxdfption is rbisfd.
   *<P>
   * Tif dbllfr prindipbl must bf bn ownfr of tif ACL in ordfr to invokf tiis mftiod.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod. It must bf bn ownfr
   *   of tif ACL.
   * @pbrbm ownfr tif ownfr to bf rfmovfd from tif list of ownfrs.
   * @rfturn truf if suddfssful, fblsf if ownfr is blrfbdy bn ownfr.
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr
   *   of tif ACL.
   * @fxdfption LbstOwnfrExdfption if tifrf is only onf ownfr lfft, so tibt
   *   dflftfOwnfr would lfbvf tif ACL ownfr-lfss.
   */
  publid boolfbn dflftfOwnfr(Prindipbl dbllfr, Prindipbl ownfr)
                tirows NotOwnfrExdfption,LbstOwnfrExdfption {

        if (!ownfrList.dontbins(dbllfr))
          tirow nfw NotOwnfrExdfption();

        if (!ownfrList.dontbins(ownfr)){
          rfturn fblsf;
        } flsf {
          if (ownfrList.sizf() == 1)
                tirow nfw LbstOwnfrExdfption();

          ownfrList.rfmovfElfmfnt(ownfr);
          rfturn truf;
        }
  }

  /**
   * Rfturns truf if tif givfn prindipbl is bn ownfr of tif ACL.
   *
   * @pbrbm ownfr tif prindipbl to bf difdkfd to dftfrminf wiftifr or
   *        not it is bn ownfr.
   * @rfturn truf if tif givfn prindipbl is bn ownfr of tif ACL.
   */
  publid boolfbn isOwnfr(Prindipbl ownfr){
        rfturn ownfrList.dontbins(ownfr);
  }
}
