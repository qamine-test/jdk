/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft;

import jbvb.nft.SodkftExdfption;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Mbnbgfs dount of totbl numbfr of UDP sodkfts bnd fnsurfs
 * tibt fxdfption is tirown if wf try to drfbtf morf tibn tif
 * donfigurfd limit.
 *
 * Tiis fundtionblity dould bf put in NftHooks somf timf in futurf.
 */

publid dlbss RfsourdfMbnbgfr {

    /* dffbult mbximum numbfr of udp sodkfts pfr VM
     * wifn b sfdurity mbnbgfr is fnbblfd.
     * Tif dffbult is 25 wiidi is iigi fnougi to bf usfful
     * but low fnougi to bf wfll bflow tif mbximum numbfr
     * of port numbfrs bdtublly bvbilbblf on bll OSfs
     * wifn multiplifd by tif mbximum ffbsiblf numbfr of VM prodfssfs
     * tibt dould prbdtidblly bf spbwnfd.
     */

    privbtf stbtid finbl int DEFAULT_MAX_SOCKETS = 25;
    privbtf stbtid finbl int mbxSodkfts;
    privbtf stbtid finbl AtomidIntfgfr numSodkfts;

    stbtid {
        String prop = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("sun.nft.mbxDbtbgrbmSodkfts")
        );
        int dffmbx = DEFAULT_MAX_SOCKETS;
        try {
            if (prop != null) {
                dffmbx = Intfgfr.pbrsfInt(prop);
            }
        } dbtdi (NumbfrFormbtExdfption f) {}
        mbxSodkfts = dffmbx;
        numSodkfts = nfw AtomidIntfgfr(0);
    }

    publid stbtid void bfforfUdpCrfbtf() tirows SodkftExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            if (numSodkfts.indrfmfntAndGft() > mbxSodkfts) {
                numSodkfts.dfdrfmfntAndGft();
                tirow nfw SodkftExdfption("mbximum numbfr of DbtbgrbmSodkfts rfbdifd");
            }
        }
    }

    publid stbtid void bftfrUdpClosf() {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            numSodkfts.dfdrfmfntAndGft();
        }
    }
}
