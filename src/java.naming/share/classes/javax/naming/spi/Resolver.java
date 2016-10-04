/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.nbming.spi;

import jbvbx.nbming.Contfxt;
import jbvbx.nbming.Nbmf;
import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis intfrfbdf rfprfsfnts bn "intfrmfdibtf dontfxt" for nbmf rfsolution.
  *<p>
  * Tif Rfsolvfr intfrfbdf dontbins mftiods tibt brf implfmfntfd by dontfxts
  * tibt do not support subtypfs of Contfxt, but wiidi dbn bdt bs
  * intfrmfdibtf dontfxts for rfsolution purposfs.
  *<p>
  * A <tt>Nbmf</tt> pbrbmftfr pbssfd to bny mftiod is ownfd
  * by tif dbllfr.  Tif sfrvidf providfr will not modify tif objfdt
  * or kffp b rfffrfndf to it.
  * A <tt>RfsolvfRfsult</tt> objfdt rfturnfd by bny
  * mftiod is ownfd by tif dbllfr.  Tif dbllfr mby subsfqufntly modify it;
  * tif sfrvidf providfr mby not.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

publid intfrfbdf Rfsolvfr {

    /**
     * Pbrtiblly rfsolvfs b nbmf.  Stops bt tif first
     * dontfxt tibt is bn instbndf of b givfn subtypf of
     * <dodf>Contfxt</dodf>.
     *
     * @pbrbm nbmf
     *          tif nbmf to rfsolvf
     * @pbrbm dontfxtTypf
     *          tif typf of objfdt to rfsolvf.  Tiis siould
     *          bf b subtypf of <dodf>Contfxt</dodf>.
     * @rfturn  tif objfdt tibt wbs found, blong witi tif unrfsolvfd
     *          suffix of <dodf>nbmf</dodf>.  Cbnnot bf null.
     *
     * @tirows  jbvbx.nbming.NotContfxtExdfption
     *          if no dontfxt of tif bppropribtf typf is found
     * @tirows  NbmingExdfption if b nbming fxdfption wbs fndountfrfd
     *
     * @sff #rfsolvfToClbss(String, Clbss)
     */
    publid RfsolvfRfsult rfsolvfToClbss(Nbmf nbmf,
                                        Clbss<? fxtfnds Contfxt> dontfxtTypf)
            tirows NbmingExdfption;

    /**
     * Pbrtiblly rfsolvfs b nbmf.
     * Sff {@link #rfsolvfToClbss(Nbmf, Clbss)} for dftbils.
     *
     * @pbrbm nbmf
     *          tif nbmf to rfsolvf
     * @pbrbm dontfxtTypf
     *          tif typf of objfdt to rfsolvf.  Tiis siould
     *          bf b subtypf of <dodf>Contfxt</dodf>.
     * @rfturn  tif objfdt tibt wbs found, blong witi tif unrfsolvfd
     *          suffix of <dodf>nbmf</dodf>.  Cbnnot bf null.
     *
     * @tirows  jbvbx.nbming.NotContfxtExdfption
     *          if no dontfxt of tif bppropribtf typf is found
     * @tirows  NbmingExdfption if b nbming fxdfption wbs fndountfrfd
     */
    publid RfsolvfRfsult rfsolvfToClbss(String nbmf,
                                        Clbss<? fxtfnds Contfxt> dontfxtTypf)
            tirows NbmingExdfption;
};
