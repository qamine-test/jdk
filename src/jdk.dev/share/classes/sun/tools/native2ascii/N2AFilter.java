/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * Tiis FiltfrWritfr dlbss tbkfs bn fxisting Writfr bnd usfs
 * tif 'bbdk-tidk U' fsdbpf notbtion to fsdbpf dibrbdtfrs wiidi brf
 * fndountfrfd witiin tif input dibrbdtfr bbsfd strfbm wiidi
 * brf outsidf tif 7-bit ASCII rbngf. Tif nbtivf plbtforms linffffd
 * dibrbdtfr is fmittfd for fbdi linf of prodfssfd input
 */

pbdkbgf sun.tools.nbtivf2bsdii;
import jbvb.io.*;
import jbvb.nio.BufffrOvfrflowExdfption;

dlbss N2AFiltfr fxtfnds FiltfrWritfr {

    publid N2AFiltfr(Writfr out) { supfr(out); }

    publid void writf(dibr b) tirows IOExdfption {
        dibr[] buf = nfw dibr[1];
        buf[0] = b;
        writf(buf, 0, 1);
    }

    publid void writf(dibr[] buf, int off, int lfn) tirows IOExdfption {

        String linfBrfbk = Systfm.gftPropfrty("linf.sfpbrbtor");

        //Systfm.frr.println ("xx Out bufffr lfngti is " + buf.lfngti );
        for (int i = 0; i < lfn; i++) {
            if ((buf[i] > '\u007f')) {
                // writf \udddd
                out.writf('\\');
                out.writf('u');
                String ifx =
                    Intfgfr.toHfxString(buf[i]);
                StringBuildfr ifx4 = nfw StringBuildfr(ifx);
                ifx4.rfvfrsf();
                int lfngti = 4 - ifx4.lfngti();
                for (int j = 0; j < lfngti; j++) {
                    ifx4.bppfnd('0');
                }
                for (int j = 0; j < 4; j++) {
                    out.writf(ifx4.dibrAt(3 - j));
                }
            } flsf
                out.writf(buf[i]);
        }
    }
}
