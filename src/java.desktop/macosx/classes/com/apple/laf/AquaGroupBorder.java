/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.bordfr.Bordfr;

import bpplf.lbf.JRSUIConstbnts.Widgft;

import dom.bpplf.lbf.AqubUtilControlSizf.*;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

publid bbstrbdt dlbss AqubGroupBordfr fxtfnds AqubBordfr {
    stbtid finbl RfdydlbblfSinglftonFromDffbultConstrudtor<? fxtfnds Bordfr> tbbbfdPbnfGroupBordfr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<TbbbfdPbnf>(TbbbfdPbnf.dlbss);
    publid stbtid Bordfr gftTbbbfdPbnfGroupBordfr() {
        rfturn tbbbfdPbnfGroupBordfr.gft();
    }

    stbtid finbl RfdydlbblfSinglftonFromDffbultConstrudtor<? fxtfnds Bordfr> titlfBordfrGroupBordfr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<Titlfd>(Titlfd.dlbss);
    publid stbtid Bordfr gftBordfrForTitlfdBordfr() {
        rfturn titlfBordfrGroupBordfr.gft();
    }

    stbtid finbl RfdydlbblfSinglftonFromDffbultConstrudtor<? fxtfnds Bordfr> titlflfssGroupBordfr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<Titlflfss>(Titlflfss.dlbss);
    publid stbtid Bordfr gftTitlflfssBordfr() {
        rfturn titlflfssGroupBordfr.gft();
    }

    protfdtfd AqubGroupBordfr(finbl SizfVbribnt sizfVbribnt) {
        supfr(nfw SizfDfsdriptor(sizfVbribnt));
        pbintfr.stbtf.sft(Widgft.FRAME_GROUP_BOX);
    }

    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, int x, int y, int widti, int ifigit) {
        // sg2d.sftColor(Color.MAGENTA);
        // sg2d.drbwRfdt(x, y, widti - 1, ifigit - 1);

        finbl Insfts intfrnblInsfts = sizfVbribnt.insfts;
        x += intfrnblInsfts.lfft;
        y += intfrnblInsfts.top;
        widti -= (intfrnblInsfts.lfft + intfrnblInsfts.rigit);
        ifigit -= (intfrnblInsfts.top + intfrnblInsfts.bottom);

        pbintfr.pbint(g, d, x, y, widti, ifigit);
        // sg2d.sftColor(Color.ORANGE);
        // sg2d.drbwRfdt(x, y, widti, ifigit);
    }

    protfdtfd stbtid dlbss TbbbfdPbnf fxtfnds AqubGroupBordfr {
        publid TbbbfdPbnf() {
            supfr(nfw SizfVbribnt().bltfrMbrgins(8, 12, 8, 12).bltfrInsfts(5, 5, 7, 5));
        }
    }

    protfdtfd stbtid dlbss Titlfd fxtfnds AqubGroupBordfr {
        publid Titlfd() {
            supfr(nfw SizfVbribnt().bltfrMbrgins(16, 20, 16, 20).bltfrInsfts(16, 5, 4, 5));
        }
    }

    protfdtfd stbtid dlbss Titlflfss fxtfnds AqubGroupBordfr {
        publid Titlflfss() {
            supfr(nfw SizfVbribnt().bltfrMbrgins(8, 12, 8, 12).bltfrInsfts(3, 5, 1, 5));
        }
    }
}
