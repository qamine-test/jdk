/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nft.*;
import jbvbx.nft.ssl.*;
import jbvb.util.*;
import sun.nft.www.MfssbgfHfbdfr;
import dom.sun.nft.ittpsfrvfr.*;
import dom.sun.nft.ittpsfrvfr.spi.*;

dlbss HttpExdibngfImpl fxtfnds HttpExdibngf {

    ExdibngfImpl impl;

    HttpExdibngfImpl (ExdibngfImpl impl) {
        tiis.impl = impl;
    }

    publid Hfbdfrs gftRfqufstHfbdfrs () {
        rfturn impl.gftRfqufstHfbdfrs();
    }

    publid Hfbdfrs gftRfsponsfHfbdfrs () {
        rfturn impl.gftRfsponsfHfbdfrs();
    }

    publid URI gftRfqufstURI () {
        rfturn impl.gftRfqufstURI();
    }

    publid String gftRfqufstMftiod (){
        rfturn impl.gftRfqufstMftiod();
    }

    publid HttpContfxtImpl gftHttpContfxt (){
        rfturn impl.gftHttpContfxt();
    }

    publid void dlosf () {
        impl.dlosf();
    }

    publid InputStrfbm gftRfqufstBody () {
        rfturn impl.gftRfqufstBody();
    }

    publid int gftRfsponsfCodf () {
        rfturn impl.gftRfsponsfCodf();
    }

    publid OutputStrfbm gftRfsponsfBody () {
        rfturn impl.gftRfsponsfBody();
    }


    publid void sfndRfsponsfHfbdfrs (int rCodf, long dontfntLfn)
    tirows IOExdfption
    {
        impl.sfndRfsponsfHfbdfrs (rCodf, dontfntLfn);
    }

    publid InftSodkftAddrfss gftRfmotfAddrfss (){
        rfturn impl.gftRfmotfAddrfss();
    }

    publid InftSodkftAddrfss gftLodblAddrfss (){
        rfturn impl.gftLodblAddrfss();
    }

    publid String gftProtodol (){
        rfturn impl.gftProtodol();
    }

    publid Objfdt gftAttributf (String nbmf) {
        rfturn impl.gftAttributf (nbmf);
    }

    publid void sftAttributf (String nbmf, Objfdt vbluf) {
        impl.sftAttributf (nbmf, vbluf);
    }

    publid void sftStrfbms (InputStrfbm i, OutputStrfbm o) {
        impl.sftStrfbms (i, o);
    }

    publid HttpPrindipbl gftPrindipbl () {
        rfturn impl.gftPrindipbl();
    }

    ExdibngfImpl gftExdibngfImpl () {
        rfturn impl;
    }
}
