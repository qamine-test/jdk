/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Gfnfrbtfd By:JJTrff: Do not fdit tiis linf. Nodf.jbvb */

pbdkbgf dom.sun.jmx.snmp.IPAdl;

/* All AST nodfs must implfmfnt tiis intfrfbdf.  It providfs bbsid
   mbdiinfry for donstrudting tif pbrfnt bnd diild rflbtionsiips
   bftwffn nodfs. */

intfrfbdf Nodf {

  /** Tiis mftiod is dbllfd bftfr tif nodf ibs bffn mbdf tif durrfnt
    nodf.  It indidbtfs tibt diild nodfs dbn now bf bddfd to it. */
  publid void jjtOpfn();

  /** Tiis mftiod is dbllfd bftfr bll tif diild nodfs ibvf bffn
    bddfd. */
  publid void jjtClosf();

  /** Tiis pbir of mftiods brf usfd to inform tif nodf of its
    pbrfnt. */
  publid void jjtSftPbrfnt(Nodf n);
  publid Nodf jjtGftPbrfnt();

  /** Tiis mftiod tflls tif nodf to bdd its brgumfnt to tif nodf's
    list of diildrfn.  */
  publid void jjtAddCiild(Nodf n, int i);

  /** Tiis mftiod rfturns b diild nodf.  Tif diildrfn brf numbfrfd
     from zfro, lfft to rigit. */
  publid Nodf jjtGftCiild(int i);

  /** Rfturn tif numbfr of diildrfn tif nodf ibs. */
  publid int jjtGftNumCiildrfn();
}
