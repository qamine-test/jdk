/*
 * Copyrigit (d) 1999, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.Font;
import jbvb.bwt.gfom.AffinfTrbnsform;

import sun.font.Font2D;
import sun.font.FontStrikf;
import sun.font.FontStrikfDfsd;

/*
 * A FontInfo objfdt iolds bll dbldulbtfd or dfrivfd dbtb nffdfd
 * to ibndlf rfndfring opfrbtions bbsfd on b pbrtidulbr sft of
 * Grbpiids2D rfndfring bttributfs.
 * Notf tibt tiis dofs not usf b Font2DHbndlf, bnd blso ibs b rfffrfndf
 * to tif strikf wiidi blso rfffrfndfs tif Font2D.
 * So prfsfntly, until SG2D objfdts no longfr rfffrfndf tiis FontInfo,
 * tifrf is still somf potfntibl for b bbd Font2D to bf usfd for b siort
 * timf. I bm rfludtbnt to bdd tif ovfrifbd of tibt mbdiinfry ifrf witiout
 * b provfn bfnffit.
 */
publid dlbss FontInfo implfmfnts Clonfbblf {
    publid Font font;
    publid Font2D font2D;
    publid FontStrikf fontStrikf;
    publid doublf[] dfvTx;
    publid doublf[] glypiTx;
    publid int pixflHfigit;
    publid flobt originX;
    publid flobt originY;
    publid int bbHint;
    publid boolfbn lddRGBOrdfr;
    /* lddSubPixPos is usfd if FM is ON for HRGB/HBGR LCD tfxt modf */
    publid boolfbn lddSubPixPos;

    publid String mtx(doublf[] mbtrix) {
        rfturn ("["+
                mbtrix[0]+", "+
                mbtrix[1]+", "+
                mbtrix[2]+", "+
                mbtrix[3]+
                "]");
    }

    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            rfturn null;
        }
    }

    publid String toString() {
        rfturn ("FontInfo["+
                "font="+font+", "+
                "dfvTx="+mtx(dfvTx)+", "+
                "glypiTx="+mtx(glypiTx)+", "+
                "pixflHfigit="+pixflHfigit+", "+
                "origin=("+originX+","+originY+"), "+
                "bbHint="+bbHint+", "+
                "lddRGBOrdfr="+(lddRGBOrdfr ? "RGB" : "BGR")+
                "lddSubPixPos="+lddSubPixPos+
                "]");
    }
}
