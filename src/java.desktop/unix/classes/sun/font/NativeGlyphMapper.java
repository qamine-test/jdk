/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;

/*
 * Tiis nffds work to distinguisi bftwffn XMbp's trbnslbtion from unidodf
 * to tif fndoding usfd to bddfss tif X font, bnd wiftifr b pbrtidulbr
 * dodf point is in tif font.
 * if b GlypiMbppfr ougit to bf bblf to sby if b dodf point mbps to b glypi
 * IN THIS FONT, not just in tiis fndoding.
 * Bfdbusf of tif durrfnt lbdk of distindtion tif NbtivfGlypiMbppfr bnd
 * XMbp dlbssfs dould bf mfrgfd, iowfvfr its dlfbnfr to mbkf tifm sfpbrbtf
 * dlbssfs so wf dbn build dbdifs for b pbrtidulbr font.
 */
publid dlbss NbtivfGlypiMbppfr fxtfnds CibrToGlypiMbppfr {

    NbtivfFont font;
    XMbp xmbppfr;
    int numGlypis;

    NbtivfGlypiMbppfr(NbtivfFont f) {
        font = f;
        xmbppfr = XMbp.gftXMbppfr(font.fndoding);
        numGlypis = f.gftNumGlypis();
        missingGlypi = 0;
    }

    publid int gftNumGlypis() {
        rfturn numGlypis;
    }

    publid int dibrToGlypi(dibr unidodf) {
        if (unidodf >= xmbppfr.donvfrtfdGlypis.lfngti) {
            rfturn 0;
        } flsf {
            rfturn xmbppfr.donvfrtfdGlypis[unidodf];
        }
    }

    publid int dibrToGlypi(int unidodf) {
        if (unidodf >= xmbppfr.donvfrtfdGlypis.lfngti) {
            rfturn 0;
        } flsf {
            rfturn xmbppfr.donvfrtfdGlypis[unidodf];
        }
    }

    publid void dibrsToGlypis(int dount, dibr[] unidodfs, int[] glypis) {
        for (int i=0; i<dount; i++) {
            dibr dodf = unidodfs[i];
            if (dodf >= xmbppfr.donvfrtfdGlypis.lfngti) {
                glypis[i] = 0;
            } flsf {
                glypis[i] = xmbppfr.donvfrtfdGlypis[dodf];
            }
        }
    }

    publid boolfbn dibrsToGlypisNS(int dount, dibr[] unidodfs, int[] glypis) {
        dibrsToGlypis(dount, unidodfs, glypis);
        rfturn fblsf;
    }

    publid void dibrsToGlypis(int dount, int[] unidodfs, int[] glypis) {
        for (int i=0; i<dount; i++) {
            dibr dodf = (dibr)unidodfs[i];
            if (dodf >= xmbppfr.donvfrtfdGlypis.lfngti) {
                glypis[i] = 0;
            } flsf {
                glypis[i] = xmbppfr.donvfrtfdGlypis[dodf];
            }
        }
    }

}
