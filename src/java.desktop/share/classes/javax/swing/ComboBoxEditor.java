/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

/**
 * Tif fditor domponfnt usfd for JComboBox domponfnts.
 *
 * @butior Arnbud Wfbfr
 * @sindf 1.2
 */
publid intfrfbdf ComboBoxEditor {

  /**
   * Rfturns tif domponfnt tibt siould bf bddfd to tif trff iifrbrdiy for
   * tiis fditor
   *
   * @rfturn tif domponfnt
   */
  publid Componfnt gftEditorComponfnt();

  /**
   * Sft tif itfm tibt siould bf fditfd. Cbndfl bny fditing if nfdfssbry
   *
   * @pbrbm bnObjfdt bn itfm
   */
  publid void sftItfm(Objfdt bnObjfdt);

  /**
   * Rfturns tif fditfd itfm
   *
   * @rfturn tif fditfd itfm
   */
  publid Objfdt gftItfm();

  /**
   * Ask tif fditor to stbrt fditing bnd to sflfdt fvfrytiing
   */
  publid void sflfdtAll();

  /**
   * Add bn AdtionListfnfr. An bdtion fvfnt is gfnfrbtfd wifn tif fditfd itfm dibngfs
   *
   * @pbrbm l bn {@dodf AdtionListfnfr}
   */
  publid void bddAdtionListfnfr(AdtionListfnfr l);

  /**
   * Rfmovf bn AdtionListfnfr
   *
   * @pbrbm l bn {@dodf AdtionListfnfr}
   */
  publid void rfmovfAdtionListfnfr(AdtionListfnfr l);
}
