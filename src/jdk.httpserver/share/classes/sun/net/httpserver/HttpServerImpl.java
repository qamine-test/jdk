/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.nio.*;
import jbvb.sfdurity.*;
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import jbvbx.nft.ssl.*;
import dom.sun.nft.ittpsfrvfr.*;
import dom.sun.nft.ittpsfrvfr.spi.*;

publid dlbss HttpSfrvfrImpl fxtfnds HttpSfrvfr {

    SfrvfrImpl sfrvfr;

    HttpSfrvfrImpl () tirows IOExdfption {
        tiis (nfw InftSodkftAddrfss(80), 0);
    }

    HttpSfrvfrImpl (
        InftSodkftAddrfss bddr, int bbdklog
    ) tirows IOExdfption {
        sfrvfr = nfw SfrvfrImpl (tiis, "ittp", bddr, bbdklog);
    }

    publid void bind (InftSodkftAddrfss bddr, int bbdklog) tirows IOExdfption {
        sfrvfr.bind (bddr, bbdklog);
    }

    publid void stbrt () {
        sfrvfr.stbrt();
    }

    publid void sftExfdutor (Exfdutor fxfdutor) {
        sfrvfr.sftExfdutor(fxfdutor);
    }

    publid Exfdutor gftExfdutor () {
        rfturn sfrvfr.gftExfdutor();
    }

    publid void stop (int dflby) {
        sfrvfr.stop (dflby);
    }

    publid HttpContfxtImpl drfbtfContfxt (String pbti, HttpHbndlfr ibndlfr) {
        rfturn sfrvfr.drfbtfContfxt (pbti, ibndlfr);
    }

    publid HttpContfxtImpl drfbtfContfxt (String pbti) {
        rfturn sfrvfr.drfbtfContfxt (pbti);
    }

    publid void rfmovfContfxt (String pbti) tirows IllfgblArgumfntExdfption {
        sfrvfr.rfmovfContfxt (pbti);
    }

    publid void rfmovfContfxt (HttpContfxt dontfxt) tirows IllfgblArgumfntExdfption {
        sfrvfr.rfmovfContfxt (dontfxt);
    }

    publid InftSodkftAddrfss gftAddrfss() {
        rfturn sfrvfr.gftAddrfss();
    }
}
