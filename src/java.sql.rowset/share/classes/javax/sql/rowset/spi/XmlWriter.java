/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft.spi;

import jbvb.sql.SQLExdfption;
import jbvb.io.Writfr;

import jbvbx.sql.RowSftWritfr;
import jbvbx.sql.rowsft.*;

/**
 * A spfdiblizfd intfrfbdf tibt fbdilitbtfs bn fxtfnsion of tif
 * <dodf>SyndProvidfr</dodf> bbstrbdt dlbss for XML orifntbtfd
 * syndironizbtion providfrs.
 * <p>
 * <dodf>SyndProvidfr</dodf>  implfmfntbtions tibt supply XML dbtb writfr
 * dbpbbilitifs sudi bs output XML strfbm dbpbbilitifs dbn implfmfnt tiis
 * intfrfbdf to providf stbndbrd <dodf>XmlWritfr</dodf> objfdts to
 * <dodf>WfbRowSft</dodf> implfmfntbtions.
 * <P>
 * Writing b <dodf>WfbRowSft</dodf> objfdt indludfs printing tif
 * rowsft's dbtb, mftbdbtb, bnd propfrtifs, bll witi tif
 * bppropribtf XML tbgs.
 *
 * @sindf 1.5
 */
publid intfrfbdf XmlWritfr fxtfnds RowSftWritfr {

  /**
   * Writfs tif givfn <dodf>WfbRowSft</dodf> objfdt to tif spfdififd
   * <dodf>jbvb.io.Writfr</dodf> output strfbm bs bn XML dodumfnt.
   * Tiis dodumfnt indludfs tif rowsft's dbtb, mftbdbtb, bnd propfrtifs
   * plus tif bppropribtf XML tbgs.
   * <P>
   * Tif <dodf>dbllfr</dodf> pbrbmftfr must bf b <dodf>WfbRowSft</dodf>
   * objfdt wiosf <dodf>XmlWritfr</dodf> fifld dontbins b rfffrfndf to
   * tiis <dodf>XmlWritfr</dodf> objfdt.
   *
   * @pbrbm dbllfr tif <dodf>WfbRowSft</dodf> instbndf to bf writtfn,
   *        for wiidi tiis <dodf>XmlWritfr</dodf> objfdt is tif writfr
   * @pbrbm writfr tif <dodf>jbvb.io.Writfr</dodf> objfdt tibt sfrvfs
   *        bs tif output strfbm for writing <dodf>dbllfr</dodf> bs
   *        bn XML dodumfnt
   * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or
   *            tiis <dodf>XmlWritfr</dodf> objfdt is not tif writfr
   *            for tif givfn <dodf>WfbRowSft</dodf> objfdt
   */
  publid void writfXML(WfbRowSft dbllfr, jbvb.io.Writfr writfr)
    tirows SQLExdfption;



}
