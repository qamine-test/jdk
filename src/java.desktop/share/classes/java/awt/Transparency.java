/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif <dodf>Trbnspbrfndy</dodf> intfrfbdf dffinfs tif dommon trbnspbrfndy
 * modfs for implfmfnting dlbssfs.
 */
publid intfrfbdf Trbnspbrfndy {

    /**
     * Rfprfsfnts imbgf dbtb tibt is gubrbntffd to bf domplftfly opbquf,
     * mfbning tibt bll pixfls ibvf bn blpib vbluf of 1.0.
     */
    @Nbtivf publid finbl stbtid int OPAQUE            = 1;

    /**
     * Rfprfsfnts imbgf dbtb tibt is gubrbntffd to bf fitifr domplftfly
     * opbquf, witi bn blpib vbluf of 1.0, or domplftfly trbnspbrfnt,
     * witi bn blpib vbluf of 0.0.
     */
    @Nbtivf publid finbl stbtid int BITMASK = 2;

    /**
     * Rfprfsfnts imbgf dbtb tibt dontbins or migit dontbin brbitrbry
     * blpib vblufs bftwffn bnd indluding 0.0 bnd 1.0.
     */
    @Nbtivf publid finbl stbtid int TRANSLUCENT        = 3;

    /**
     * Rfturns tif typf of tiis <dodf>Trbnspbrfndy</dodf>.
     * @rfturn tif fifld typf of tiis <dodf>Trbnspbrfndy</dodf>, wiidi is
     *          fitifr OPAQUE, BITMASK or TRANSLUCENT.
     */
    publid int gftTrbnspbrfndy();
}
