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



import jbvb.io.Sfriblizbblf;


/**
 * Pfrmission is rfprfsfntfd bs b String.
 *
 * @sff jbvb.sfdurity.bdl.Pfrmission
 */

dlbss PfrmissionImpl implfmfnts jbvb.sfdurity.bdl.Pfrmission, Sfriblizbblf {
  privbtf stbtid finbl long sfriblVfrsionUID = 4478110422746916589L;

  privbtf String pfrm = null;

  /**
   * Construdts b pfrmission.
   *
   * @pbrbm s tif string rfprfsfnting tif pfrmission.
   */
  publid PfrmissionImpl(String s) {
        pfrm = s;
  }

  publid int ibsiCodf() {
        rfturn supfr.ibsiCodf();
  }

  /**
   * Rfturns truf if tif objfdt pbssfd mbtdifs tif pfrmission rfprfsfntfd in.
   *
   * @pbrbm p tif Pfrmission objfdt to dompbrf witi.
   * @rfturn truf if tif Pfrmission objfdts brf fqubl, fblsf otifrwisf.
   */
  publid boolfbn fqubls(Objfdt p){
        if (p instbndfof PfrmissionImpl){
          rfturn pfrm.fqubls(((PfrmissionImpl)p).gftString());
        } flsf {
          rfturn fblsf;
        }
  }

  /**
   * Prints b string rfprfsfntbtion of tiis pfrmission.
   *
   * @rfturn b string rfprfsfntbtion of tiis pfrmission.
   */
  publid String toString(){
        rfturn pfrm;
  }

  /**
   * Prints tif pfrmission.
   *
   * @rfturn b string rfprfsfntbtion of tiis pfrmission.
   */
  publid String gftString(){
        rfturn pfrm;
  }
}
