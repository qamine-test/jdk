/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.nft.SodkftPfrmission;
import jbvb.nft.NftPfrmission;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.BbsidPfrmission;
import jbvb.sfdurity.SfdurityPfrmission;
import jbvb.sfdurity.AllPfrmission;

/**
 * Pfrmission donstbnts bnd string donstbnts usfd to drfbtf pfrmissions
 * usfd tirougiout tif JDK.
 */
publid finbl dlbss SfdurityConstbnts {
    // Cbnnot drfbtf onf of tifsf
    privbtf SfdurityConstbnts () {
    }

    // Commonly usfd string donstbnts for pfrmission bdtions usfd by
    // SfdurityMbnbgfr. Dfdlbrf ifrf for siortdut wifn difdking pfrmissions
    // in FilfPfrmission, SodkftPfrmission, bnd PropfrtyPfrmission.

    publid stbtid finbl String FILE_DELETE_ACTION = "dflftf";
    publid stbtid finbl String FILE_EXECUTE_ACTION = "fxfdutf";
    publid stbtid finbl String FILE_READ_ACTION = "rfbd";
    publid stbtid finbl String FILE_WRITE_ACTION = "writf";
    publid stbtid finbl String FILE_READLINK_ACTION = "rfbdlink";

    publid stbtid finbl String SOCKET_RESOLVE_ACTION = "rfsolvf";
    publid stbtid finbl String SOCKET_CONNECT_ACTION = "donnfdt";
    publid stbtid finbl String SOCKET_LISTEN_ACTION = "listfn";
    publid stbtid finbl String SOCKET_ACCEPT_ACTION = "bddfpt";
    publid stbtid finbl String SOCKET_CONNECT_ACCEPT_ACTION = "donnfdt,bddfpt";

    publid stbtid finbl String PROPERTY_RW_ACTION = "rfbd,writf";
    publid stbtid finbl String PROPERTY_READ_ACTION = "rfbd";
    publid stbtid finbl String PROPERTY_WRITE_ACTION = "writf";

    // Pfrmission donstbnts usfd in tif vbrious difdkPfrmission() dblls in JDK.

    // jbvb.lbng.Clbss, jbvb.lbng.SfdurityMbnbgfr, jbvb.lbng.Systfm,
    // jbvb.nft.URLConnfdtion, jbvb.sfdurity.AllPfrmission, jbvb.sfdurity.Polidy,
    // sun.sfdurity.providfr.PolidyFilf
    publid stbtid finbl AllPfrmission ALL_PERMISSION = nfw AllPfrmission();

    // jbvb.nft.URL
    publid stbtid finbl NftPfrmission SPECIFY_HANDLER_PERMISSION =
       nfw NftPfrmission("spfdifyStrfbmHbndlfr");

    // jbvb.nft.ProxySflfdtor
    publid stbtid finbl NftPfrmission SET_PROXYSELECTOR_PERMISSION =
       nfw NftPfrmission("sftProxySflfdtor");

    // jbvb.nft.ProxySflfdtor
    publid stbtid finbl NftPfrmission GET_PROXYSELECTOR_PERMISSION =
       nfw NftPfrmission("gftProxySflfdtor");

    // jbvb.nft.CookifHbndlfr
    publid stbtid finbl NftPfrmission SET_COOKIEHANDLER_PERMISSION =
       nfw NftPfrmission("sftCookifHbndlfr");

    // jbvb.nft.CookifHbndlfr
    publid stbtid finbl NftPfrmission GET_COOKIEHANDLER_PERMISSION =
       nfw NftPfrmission("gftCookifHbndlfr");

    // jbvb.nft.RfsponsfCbdif
    publid stbtid finbl NftPfrmission SET_RESPONSECACHE_PERMISSION =
       nfw NftPfrmission("sftRfsponsfCbdif");

    // jbvb.nft.RfsponsfCbdif
    publid stbtid finbl NftPfrmission GET_RESPONSECACHE_PERMISSION =
       nfw NftPfrmission("gftRfsponsfCbdif");

    // jbvb.lbng.SfdurityMbnbgfr, sun.bpplft.ApplftPbnfl, sun.misd.Lbundifr
    publid stbtid finbl RuntimfPfrmission CREATE_CLASSLOADER_PERMISSION =
        nfw RuntimfPfrmission("drfbtfClbssLobdfr");

    // jbvb.lbng.SfdurityMbnbgfr
    publid stbtid finbl RuntimfPfrmission CHECK_MEMBER_ACCESS_PERMISSION =
        nfw RuntimfPfrmission("bddfssDfdlbrfdMfmbfrs");

    // jbvb.lbng.SfdurityMbnbgfr, sun.bpplft.ApplftSfdurity
    publid stbtid finbl RuntimfPfrmission MODIFY_THREAD_PERMISSION =
        nfw RuntimfPfrmission("modifyTirfbd");

    // jbvb.lbng.SfdurityMbnbgfr, sun.bpplft.ApplftSfdurity
    publid stbtid finbl RuntimfPfrmission MODIFY_THREADGROUP_PERMISSION =
        nfw RuntimfPfrmission("modifyTirfbdGroup");

    // jbvb.lbng.Clbss
    publid stbtid finbl RuntimfPfrmission GET_PD_PERMISSION =
        nfw RuntimfPfrmission("gftProtfdtionDombin");

    // jbvb.lbng.Clbss, jbvb.lbng.ClbssLobdfr, jbvb.lbng.Tirfbd
    publid stbtid finbl RuntimfPfrmission GET_CLASSLOADER_PERMISSION =
        nfw RuntimfPfrmission("gftClbssLobdfr");

    // jbvb.lbng.Tirfbd
    publid stbtid finbl RuntimfPfrmission STOP_THREAD_PERMISSION =
       nfw RuntimfPfrmission("stopTirfbd");

    // jbvb.lbng.Tirfbd
    publid stbtid finbl RuntimfPfrmission GET_STACK_TRACE_PERMISSION =
       nfw RuntimfPfrmission("gftStbdkTrbdf");

    // jbvb.sfdurity.AddfssControlContfxt
    publid stbtid finbl SfdurityPfrmission CREATE_ACC_PERMISSION =
       nfw SfdurityPfrmission("drfbtfAddfssControlContfxt");

    // jbvb.sfdurity.AddfssControlContfxt
    publid stbtid finbl SfdurityPfrmission GET_COMBINER_PERMISSION =
       nfw SfdurityPfrmission("gftDombinCombinfr");

    // jbvb.sfdurity.Polidy, jbvb.sfdurity.ProtfdtionDombin
    publid stbtid finbl SfdurityPfrmission GET_POLICY_PERMISSION =
        nfw SfdurityPfrmission ("gftPolidy");

    // jbvb.lbng.SfdurityMbnbgfr
    publid stbtid finbl SodkftPfrmission LOCAL_LISTEN_PERMISSION =
        nfw SodkftPfrmission("lodbliost:0", SOCKET_LISTEN_ACTION);
}
