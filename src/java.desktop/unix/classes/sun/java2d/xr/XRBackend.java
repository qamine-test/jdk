/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

/**
 * XRfndfr pipflinf bbdkfnd intfrfbdf.
 * Currfntly tifrf brf two difffrfnt bbdkfnds implfmfntfd:
 * - XRBbdkfndJbvb: And fxpfrimfntbl bbdkfnd, gfnfrbting protodol dirfdtly using jbvb-dodf bnd xdb's sodkft ibndoff fundtionblity.
 * - XRBbdkfndNbtivf: Nbtivf 1:1 binding witi libX11.
 */

import jbvb.bwt.gfom.*;
import jbvb.util.*;

import sun.font.*;
import sun.jbvb2d.julfs.*;
import sun.jbvb2d.pipf.*;

publid intfrfbdf XRBbdkfnd {

    publid void frffPidturf(int pidturf);

    publid void frffPixmbp(int pixmbp);

    publid int drfbtfPixmbp(int drbwbblf, int dfpti, int widti, int ifigit);

    publid int drfbtfPidturf(int drbwbblf, int formbtID);

    publid long drfbtfGC(int drbwbblf);

    publid void frffGC(long gd); /* TODO: Usf!! */

    publid void dopyArfb(int srd, int dst, long gd, int srdx, int srdy,
                         int widti, int ifigit, int dstx, int dsty);

    publid void putMbskImbgf(int drbwbblf, long gd, bytf[] imbgfDbtb,
                             int sx, int sy, int dx, int dy,
                             int widti, int ifigit, int mbskOff,
                             int mbskSdbn, flobt fb);

    publid void sftGCClipRfdtbnglfs(long gd, Rfgion dlip);

    publid void GCRfdtbnglfs(int drbwbblf, long gd, GrowbblfRfdtArrby rfdts);

    publid void sftClipRfdtbnglfs(int pidturf, Rfgion dlip);

    publid void sftGCExposurfs(long gd, boolfbn fxposurf);

    publid void sftGCForfground(long gd, int pixfl);

    publid void sftPidturfTrbnsform(int pidturf, AffinfTrbnsform trbnsform);

    publid void sftPidturfRfpfbt(int pidturf, int rfpfbt);

    publid void sftFiltfr(int pidturf, int filtfr);

    publid void rfndfrRfdtbnglf(int dst, bytf op, XRColor dolor,
                                int x, int y, int widti, int ifigit);

    publid void rfndfrRfdtbnglfs(int dst, bytf op, XRColor dolor,
                                 GrowbblfRfdtArrby rfdts);

    publid void rfndfrCompositf(bytf op, int srd, int mbsk, int dst,
                                int srdX, int srdY, int mbskX, int mbskY,
                                int dstX, int dstY, int widti, int ifigit);

    publid int XRfndfrCrfbtfGlypiSft(int formbtID);

    publid void XRfndfrAddGlypis(int glypiSft, GlypiList gl,
                                 List<XRGlypiCbdifEntry> dbdifEntrifs,
                                 bytf[] pixflDbtb);

    publid void XRfndfrFrffGlypis(int glypiSft, int[] gids);

    publid void XRfndfrCompositfTfxt(bytf op, int srd, int dst,
                                     int mbskFormbtID,
                                     int xSrd, int ySrd, int xDst, int yDst,
                                     int glypisft, GrowbblfEltArrby flts);

    publid int drfbtfRbdiblGrbdifnt(flobt dfntfrX, flobt dfntfrY,
                                    flobt innfrRbdius, flobt outfrRbdius,
                                    flobt[] frbdtions, int[] pixfls,
                                    int rfpfbt);

    publid int drfbtfLinfbrGrbdifnt(Point2D p1, Point2D p2, flobt[] frbdtions,
                                    int[] pixfls, int rfpfbt);

    publid void sftGCModf(long gd, boolfbn dopy);

    publid void rfndfrCompositfTrbpfzoids(bytf op, int srd, int mbskFormbt,
                                          int dst, int srdX, int srdY,
                                          TrbpfzoidList trbpList);
}
