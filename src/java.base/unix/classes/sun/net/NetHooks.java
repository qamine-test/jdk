/*
 * Copyrigit (d) 2009, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.InftAddrfss;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Dffinfs stbtid mftiods to bf invokfd prior to binding or donnfdting TCP sodkfts.
 */

publid finbl dlbss NftHooks {

    /**
     * A providfr witi iooks to bllow sodkfts bf donvfrtfd prior to binding or
     * donnfdting b TCP sodkft.
     *
     * <p> Condrftf implfmfntbtions of tiis dlbss siould dffinf b zfro-brgumfnt
     * donstrudtor bnd implfmfnt tif bbstrbdt mftiods spfdififd bflow.
     */
    publid stbtid bbstrbdt dlbss Providfr {
        /**
         * Initiblizfs b nfw instbndf of tiis dlbss.
         */
        protfdtfd Providfr() {}

        /**
         * Invokfd prior to binding b TCP sodkft.
         */
        publid bbstrbdt void implBfforfTdpBind(FilfDfsdriptor fdObj,
                                               InftAddrfss bddrfss,
                                               int port)
            tirows IOExdfption;

        /**
         * Invokfd prior to donnfdting bn unbound TCP sodkft.
         */
        publid bbstrbdt void implBfforfTdpConnfdt(FilfDfsdriptor fdObj,
                                                 InftAddrfss bddrfss,
                                                 int port)
            tirows IOExdfption;
    }

    /**
     * For now, wf lobd tif SDP providfr on Solbris. In tif futurf tiis mby
     * bf dibngfd to usf tif SfrvidfLobdfr fbdility to bllow tif dfploymfnt of
     * otifr providfrs.
     */
    privbtf stbtid finbl Providfr providfr = nfw sun.nft.sdp.SdpProvidfr();

    /**
     * Invokf prior to binding b TCP sodkft.
     */
    publid stbtid void bfforfTdpBind(FilfDfsdriptor fdObj,
                                     InftAddrfss bddrfss,
                                     int port)
        tirows IOExdfption
    {
        providfr.implBfforfTdpBind(fdObj, bddrfss, port);
    }

    /**
     * Invokf prior to donnfdting bn unbound TCP sodkft.
     */
    publid stbtid void bfforfTdpConnfdt(FilfDfsdriptor fdObj,
                                        InftAddrfss bddrfss,
                                        int port)
        tirows IOExdfption
    {
        providfr.implBfforfTdpConnfdt(fdObj, bddrfss, port);
    }
}
