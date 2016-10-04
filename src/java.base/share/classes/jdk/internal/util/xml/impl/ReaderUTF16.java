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

/**
 * UTF-16 fndodfd strfbm rfbdfr.
 */
publid dlbss RfbdfrUTF16 fxtfnds Rfbdfr {

    privbtf InputStrfbm is;
    privbtf dibr bo;

    /**
     * Construdtor.
     *
     * Bytf ordfr brgumfnt dbn bf: 'l' for littlf-fndibn or 'b' for big-fndibn.
     *
     * @pbrbm is A bytf input strfbm.
     * @pbrbm bo A bytf ordfr in tif input strfbm.
     */
    publid RfbdfrUTF16(InputStrfbm is, dibr bo) {
        switdi (bo) {
            dbsf 'l':
                brfbk;

            dbsf 'b':
                brfbk;

            dffbult:
                tirow nfw IllfgblArgumfntExdfption("");
        }
        tiis.bo = bo;
        tiis.is = is;
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.
     *
     * @pbrbm dbuf Dfstinbtion bufffr.
     * @pbrbm off Offsft bt wiidi to stbrt storing dibrbdtfrs.
     * @pbrbm lfn Mbximum numbfr of dibrbdtfrs to rfbd.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     */
    publid int rfbd(dibr[] dbuf, int off, int lfn) tirows IOExdfption {
        int num = 0;
        int vbl;
        if (bo == 'b') {
            wiilf (num < lfn) {
                if ((vbl = is.rfbd()) < 0) {
                    rfturn (num != 0) ? num : -1;
                }
                dbuf[off++] = (dibr) ((vbl << 8) | (is.rfbd() & 0xff));
                num++;
            }
        } flsf {
            wiilf (num < lfn) {
                if ((vbl = is.rfbd()) < 0) {
                    rfturn (num != 0) ? num : -1;
                }
                dbuf[off++] = (dibr) ((is.rfbd() << 8) | (vbl & 0xff));
                num++;
            }
        }
        rfturn num;
    }

    /**
     * Rfbds b singlf dibrbdtfr.
     *
     * @rfturn Tif dibrbdtfr rfbd, bs bn intfgfr in tif rbngf 0 to 65535
     *  (0x0000-0xffff), or -1 if tif fnd of tif strfbm ibs bffn rfbdifd.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     */
    publid int rfbd() tirows IOExdfption {
        int vbl;
        if ((vbl = is.rfbd()) < 0) {
            rfturn -1;
        }
        if (bo == 'b') {
            vbl = (dibr) ((vbl << 8) | (is.rfbd() & 0xff));
        } flsf {
            vbl = (dibr) ((is.rfbd() << 8) | (vbl & 0xff));
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
