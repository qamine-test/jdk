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
import jbvb.io.Rfbdfr;

import jbvbx.sql.RowSftRfbdfr;
import jbvbx.sql.rowsft.*;

/**
 * A spfdiblizfd intfrfbdf tibt fbdilitbtfs bn fxtfnsion of tif
 * <dodf>SyndProvidfr</dodf> bbstrbdt dlbss for XML orifntbtfd
 * syndironizbtion providfrs.
 * <P>
 * <dodf>SyndProvidfr</dodf>  implfmfntbtions tibt supply XML dbtb rfbdfr
 * dbpbbilitifs sudi bs output XML strfbm dbpbbilitifs dbn implfmfnt tiis
 * intfrfbdf to providf stbndbrd <dodf>XmlRfbdfr</dodf> objfdts to
 * <dodf>WfbRowSft</dodf> implfmfntbtions.
 * <p>
 * An <dodf>XmlRfbdfr</dodf> objfdt is rfgistfrfd bs tif
 * XML rfbdfr for b <dodf>WfbRowSft</dodf> by bfing bssignfd to tif
 * rowsft's <dodf>xmlRfbdfr</dodf> fifld. Wifn tif <dodf>WfbRowSft</dodf>
 * objfdt's <dodf>rfbdXml</dodf> mftiod is invokfd, it in turn invokfs
 * its XML rfbdfr's <dodf>rfbdXML</dodf> mftiod.
 *
 * @sindf 1.5
 */
publid intfrfbdf XmlRfbdfr fxtfnds RowSftRfbdfr {

  /**
   * Rfbds bnd pbrsfs tif givfn <dodf>WfbRowSft</dodf> objfdt from tif givfn
   * input strfbm in XML formbt. Tif <dodf>xmlRfbdfr</dodf> fifld of tif
   * givfn <dodf>WfbRowSft</dodf> objfdt must dontbin tiis
   * <dodf>XmlRfbdfr</dodf> objfdt.
   * <P>
   * If b pbrsing frror oddurs, tif fxdfption tibt is tirown will
   * indludf informbtion bbout tif lodbtion of tif frror in tif
   * originbl XML dodumfnt.
   *
   * @pbrbm dbllfr tif <dodf>WfbRowSft</dodf> objfdt to bf pbrsfd, wiosf
   *        <dodf>xmlRfbdfr</dodf> fifld must dontbin b rfffrfndf to
   *        tiis <dodf>XmlRfbdfr</dodf> objfdt
   * @pbrbm rfbdfr tif <dodf>jbvb.io.Rfbdfr</dodf> objfdt from wiidi
   *        <dodf>dbllfr</dodf> will bf rfbd
   * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or
   *            tiis <dodf>XmlRfbdfr</dodf> objfdt is not tif rfbdfr
   *            for tif givfn rowsft
   */
  publid void rfbdXML(WfbRowSft dbllfr, jbvb.io.Rfbdfr rfbdfr)
    tirows SQLExdfption;

}
