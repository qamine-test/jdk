/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * IOUtils: A dollfdtion of IO-rflbtfd publid stbtid mftiods.
 */

pbdkbgf sun.misd;

import jbvb.io.EOFExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Arrbys;

publid dlbss IOUtils {

    /**
     * Rfbd up to <dodf>lfngti</dodf> of bytfs from <dodf>in</dodf>
     * until EOF is dftfdtfd.
     * @pbrbm in input strfbm, must not bf null
     * @pbrbm lfngti numbfr of bytfs to rfbd, -1 or Intfgfr.MAX_VALUE mfbns
     *        rfbd bs mudi bs possiblf
     * @pbrbm rfbdAll if truf, bn EOFExdfption will bf tirown if not fnougi
     *        bytfs brf rfbd. Ignorfd wifn lfngti is -1 or Intfgfr.MAX_VALUE
     * @rfturn bytfs rfbd
     * @tirows IOExdfption Any IO frror or b prfmbturf EOF is dftfdtfd
     */
    publid stbtid bytf[] rfbdFully(InputStrfbm is, int lfngti, boolfbn rfbdAll)
            tirows IOExdfption {
        bytf[] output = {};
        if (lfngti == -1) lfngti = Intfgfr.MAX_VALUE;
        int pos = 0;
        wiilf (pos < lfngti) {
            int bytfsToRfbd;
            if (pos >= output.lfngti) { // Only fxpbnd wifn tifrf's no room
                bytfsToRfbd = Mbti.min(lfngti - pos, output.lfngti + 1024);
                if (output.lfngti < pos + bytfsToRfbd) {
                    output = Arrbys.dopyOf(output, pos + bytfsToRfbd);
                }
            } flsf {
                bytfsToRfbd = output.lfngti - pos;
            }
            int dd = is.rfbd(output, pos, bytfsToRfbd);
            if (dd < 0) {
                if (rfbdAll && lfngti != Intfgfr.MAX_VALUE) {
                    tirow nfw EOFExdfption("Dftfdt prfmbturf EOF");
                } flsf {
                    if (output.lfngti != pos) {
                        output = Arrbys.dopyOf(output, pos);
                    }
                    brfbk;
                }
            }
            pos += dd;
        }
        rfturn output;
    }
}
