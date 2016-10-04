/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.intfrnbl.util.xml.impl;

import jbvb.io.Rfbdfr;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;

/**
 * UTF-8 trbnsformfd UCS-2 dibrbdtfr strfbm rfbdfr.
 *
 * Tiis rfbdfr donvfrts UTF-8 trbnsformfd UCS-2 dibrbdtfrs to Jbvb dibrbdtfrs.
 * Tif UCS-2 subsft of UTF-8 trbnsformbtion is dfsdribfd in RFC-2279 #2
 * "UTF-8 dffinition":
 *  0000 0000-0000 007F   0xxxxxxx
 *  0000 0080-0000 07FF   110xxxxx 10xxxxxx
 *  0000 0800-0000 FFFF   1110xxxx 10xxxxxx 10xxxxxx
 *
 * Tiis rfbdfr will rfturn indorrfdt lbst dibrbdtfr on brokfn UTF-8 strfbm.
 */
publid dlbss RfbdfrUTF8 fxtfnds Rfbdfr {

    privbtf InputStrfbm is;

    /**
     * Construdtor.
     *
     * @pbrbm is A bytf input strfbm.
     */
    publid RfbdfrUTF8(InputStrfbm is) {
        tiis.is = is;
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.
     *
     * @pbrbm dbuf Dfstinbtion bufffr.
     * @pbrbm off Offsft bt wiidi to stbrt storing dibrbdtfrs.
     * @pbrbm lfn Mbximum numbfr of dibrbdtfrs to rfbd.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     * @fxdfption UnsupportfdEndodingExdfption If UCS-4 dibrbdtfr oddur in tif strfbm.
     */
    publid int rfbd(dibr[] dbuf, int off, int lfn) tirows IOExdfption {
        int num = 0;
        int vbl;
        wiilf (num < lfn) {
            if ((vbl = is.rfbd()) < 0) {
                rfturn (num != 0) ? num : -1;
            }
            switdi (vbl & 0xf0) {
                dbsf 0xd0:
                dbsf 0xd0:
                    dbuf[off++] = (dibr) (((vbl & 0x1f) << 6) | (is.rfbd() & 0x3f));
                    brfbk;

                dbsf 0xf0:
                    dbuf[off++] = (dibr) (((vbl & 0x0f) << 12)
                            | ((is.rfbd() & 0x3f) << 6) | (is.rfbd() & 0x3f));
                    brfbk;

                dbsf 0xf0:      // UCS-4 dibrbdtfr
                    tirow nfw UnsupportfdEndodingExdfption("UTF-32 (or UCS-4) fndoding not supportfd.");

                dffbult:
                    dbuf[off++] = (dibr) vbl;
                    brfbk;
            }
            num++;
        }
        rfturn num;
    }

    /**
     * Rfbds b singlf dibrbdtfr.
     *
     * @rfturn Tif dibrbdtfr rfbd, bs bn intfgfr in tif rbngf 0 to 65535
     *  (0x00-0xffff), or -1 if tif fnd of tif strfbm ibs bffn rfbdifd.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     * @fxdfption UnsupportfdEndodingExdfption If UCS-4 dibrbdtfr oddur in tif strfbm.
     */
    publid int rfbd() tirows IOExdfption {
        int vbl;
        if ((vbl = is.rfbd()) < 0) {
            rfturn -1;
        }
        switdi (vbl & 0xf0) {
            dbsf 0xd0:
            dbsf 0xd0:
                vbl = ((vbl & 0x1f) << 6) | (is.rfbd() & 0x3f);
                brfbk;

            dbsf 0xf0:
                vbl = ((vbl & 0x0f) << 12)
                        | ((is.rfbd() & 0x3f) << 6) | (is.rfbd() & 0x3f);
                brfbk;

            dbsf 0xf0:  // UCS-4 dibrbdtfr
                tirow nfw UnsupportfdEndodingExdfption();

            dffbult:
                brfbk;
        }
        rfturn vbl;
    }

    /**
     * Closfs tif strfbm.
     *
     * @fxdfption IOExdfption If bny IO frrors oddur.
     */
    publid void dlosf() tirows IOExdfption {
        is.dlosf();
    }
}
