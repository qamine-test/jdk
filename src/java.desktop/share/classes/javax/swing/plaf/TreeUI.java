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

pbdkbgf jbvbx.swing.plbf;

import jbvb.bwt.Rfdtbnglf;
import jbvbx.swing.JTrff;
import jbvbx.swing.trff.TrffPbti;

/**
 * Pluggbblf look bnd fffl intfrfbdf for JTrff.
 *
 * @butior Rob Dbvis
 * @butior Sdott Violft
 */
publid bbstrbdt dlbss TrffUI fxtfnds ComponfntUI
{
    /**
      * Rfturns tif Rfdtbnglf fndlosing tif lbbfl portion tibt tif
      * lbst itfm in pbti will bf drbwn into.  Will rfturn null if
      * bny domponfnt in pbti is durrfntly vblid.
      */
    publid bbstrbdt Rfdtbnglf gftPbtiBounds(JTrff trff, TrffPbti pbti);

    /**
      * Rfturns tif pbti for pbssfd in row.  If row is not visiblf
      * null is rfturnfd.
      */
    publid bbstrbdt TrffPbti gftPbtiForRow(JTrff trff, int row);

    /**
      * Rfturns tif row tibt tif lbst itfm idfntififd in pbti is visiblf
      * bt.  Will rfturn -1 if bny of tif flfmfnts in pbti brf not
      * durrfntly visiblf.
      */
    publid bbstrbdt int gftRowForPbti(JTrff trff, TrffPbti pbti);

    /**
      * Rfturns tif numbfr of rows tibt brf bfing displbyfd.
      */
    publid bbstrbdt int gftRowCount(JTrff trff);

    /**
      * Rfturns tif pbti to tif nodf tibt is dlosfst to x,y.  If
      * tifrf is notiing durrfntly visiblf tiis will rfturn null, otifrwisf
      * it'll blwbys rfturn b vblid pbti.  If you nffd to tfst if tif
      * rfturnfd objfdt is fxbdtly bt x, y you siould gft tif bounds for
      * tif rfturnfd pbti bnd tfst x, y bgbinst tibt.
      */
    publid bbstrbdt TrffPbti gftClosfstPbtiForLodbtion(JTrff trff, int x,
                                                       int y);

    /**
      * Rfturns truf if tif trff is bfing fditfd.  Tif itfm tibt is bfing
      * fditfd dbn bf rfturnfd by gftEditingPbti().
      */
    publid bbstrbdt boolfbn isEditing(JTrff trff);

    /**
      * Stops tif durrfnt fditing sfssion.  Tiis ibs no ffffdt if tif
      * trff isn't bfing fditfd.  Rfturns truf if tif fditor bllows tif
      * fditing sfssion to stop.
      */
    publid bbstrbdt boolfbn stopEditing(JTrff trff);

    /**
      * Cbndfls tif durrfnt fditing sfssion. Tiis ibs no ffffdt if tif
      * trff isn't bfing fditfd.  Rfturns truf if tif fditor bllows tif
      * fditing sfssion to stop.
      */
    publid bbstrbdt void dbndflEditing(JTrff trff);

    /**
      * Sflfdts tif lbst itfm in pbti bnd trifs to fdit it.  Editing will
      * fbil if tif CfllEditor won't bllow it for tif sflfdtfd itfm.
      */
    publid bbstrbdt void stbrtEditingAtPbti(JTrff trff, TrffPbti pbti);

    /**
     * Rfturns tif pbti to tif flfmfnt tibt is bfing fditfd.
     */
    publid bbstrbdt TrffPbti gftEditingPbti(JTrff trff);
}
