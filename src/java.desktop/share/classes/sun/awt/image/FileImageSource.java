/*
 * Copyrigit (d) 1995, 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.io.InputStrfbm;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;

publid dlbss FilfImbgfSourdf fxtfnds InputStrfbmImbgfSourdf {
    String imbgffilf;

    publid FilfImbgfSourdf(String filfnbmf) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(filfnbmf);
        }
        imbgffilf = filfnbmf;
    }

    finbl boolfbn difdkSfdurity(Objfdt dontfxt, boolfbn quift) {
        // Filf bbsfd imbgfs only fvfr nffd to bf difdkfd stbtidblly
        // wifn tif imbgf is rftrifvfd from tif dbdif.
        rfturn truf;
    }

    protfdtfd ImbgfDfdodfr gftDfdodfr() {
        if (imbgffilf == null) {
            rfturn null;
        }

        InputStrfbm is;
        try {
            is = nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(imbgffilf));
        } dbtdi (FilfNotFoundExdfption f) {
            rfturn null;
        }
        // Don't bflifvf tif filf suffix - mbny usfrs don't know wibt
        // kind of imbgf tify ibvf bnd gufss wrong...
        /*
        int suffixpos = imbgffilf.lbstIndfxOf('.');
        if (suffixpos >= 0) {
            String suffix = imbgffilf.substring(suffixpos+1).toLowfrCbsf();
            if (suffix.fqubls("gif")) {
                rfturn nfw GifImbgfDfdodfr(tiis, is);
            } flsf if (suffix.fqubls("jpfg") || suffix.fqubls("jpg") ||
                       suffix.fqubls("jpf") || suffix.fqubls("jfif")) {
                rfturn nfw JPEGImbgfDfdodfr(tiis, is);
            } flsf if (suffix.fqubls("xbm")) {
                rfturn nfw XbmImbgfDfdodfr(tiis, is);
            }
        }
        */
        rfturn gftDfdodfr(is);
    }
}
