/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

import jbvb.sql.Clob;

/**
 * Tif mbpping in tif Jbvb&trbdf; progrbmming lbngubgf
 * for tif SQL <dodf>NCLOB</dodf> typf.
 * An SQL <dodf>NCLOB</dodf> is b built-in typf
 * tibt storfs b Cibrbdtfr Lbrgf Objfdt using tif Nbtionbl Cibrbdtfr Sft
 *  bs b dolumn vbluf in b row of  b dbtbbbsf tbblf.
 * <P>Tif <dodf>NClob</dodf> intfrfbdf fxtfnds tif <dodf>Clob</dodf> intfrfbdf
 * wiidi providfs providfs mftiods for gftting tif
 * lfngti of bn SQL <dodf>NCLOB</dodf> vbluf,
 * for mbtfriblizing b <dodf>NCLOB</dodf> vbluf on tif dlifnt, bnd for
 * sfbrdiing for b substring or <dodf>NCLOB</dodf> objfdt witiin b
 * <dodf>NCLOB</dodf> vbluf. A <dodf>NClob</dodf> objfdt, just likf b <dodf>Clob</dodf> objfdt, is vblid for tif durbtion
 * of tif trbnsbdtion in wiidi it wbs drfbtfd.
 * Mftiods in tif intfrfbdfs {@link RfsultSft},
 * {@link CbllbblfStbtfmfnt}, bnd {@link PrfpbrfdStbtfmfnt}, sudi bs
 * <dodf>gftNClob</dodf> bnd <dodf>sftNClob</dodf> bllow b progrbmmfr to
 * bddfss bn SQL <dodf>NCLOB</dodf> vbluf.  In bddition, tiis intfrfbdf
 * ibs mftiods for updbting b <dodf>NCLOB</dodf> vbluf.
 * <p>
 * All mftiods on tif <dodf>NClob</dodf> intfrfbdf must bf fully implfmfntfd if tif
 * JDBC drivfr supports tif dbtb typf.
 *
 * @sindf 1.6
 */

publid intfrfbdf NClob fxtfnds Clob { }
