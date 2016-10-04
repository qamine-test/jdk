/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>

#indludf "jbvb_nft_InftAddrfss.i"
#indludf "nft_util.i"

/************************************************************************
 * InftAddrfss
 */

jdlbss ib_dlbss;
jdlbss ibd_dlbss;
jfifldID ib_ioldfrID;
jfifldID ibd_bddrfssID;
jfifldID ibd_fbmilyID;
jfifldID ibd_iostNbmfID;
jfifldID ib_prfffrIPv6AddrfssID;

stbtid int ib_initiblizfd = 0;

/*
 * Clbss:     jbvb_nft_InftAddrfss
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_nft_InftAddrfss_init(JNIEnv *fnv, jdlbss dls) {
    if (!ib_initiblizfd) {
        jdlbss d = (*fnv)->FindClbss(fnv,"jbvb/nft/InftAddrfss");
        CHECK_NULL(d);
        ib_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
        CHECK_NULL(ib_dlbss);
        d = (*fnv)->FindClbss(fnv,"jbvb/nft/InftAddrfss$InftAddrfssHoldfr");
        CHECK_NULL(d);
        ibd_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
        ib_ioldfrID = (*fnv)->GftFifldID(fnv, ib_dlbss, "ioldfr", "Ljbvb/nft/InftAddrfss$InftAddrfssHoldfr;");
        CHECK_NULL(ib_ioldfrID);
        ib_prfffrIPv6AddrfssID = (*fnv)->GftStbtidFifldID(fnv, ib_dlbss, "prfffrIPv6Addrfss", "Z");
        CHECK_NULL(ib_prfffrIPv6AddrfssID);

        ibd_bddrfssID = (*fnv)->GftFifldID(fnv, ibd_dlbss, "bddrfss", "I");
        CHECK_NULL(ibd_bddrfssID);
        ibd_fbmilyID = (*fnv)->GftFifldID(fnv, ibd_dlbss, "fbmily", "I");
        CHECK_NULL(ibd_fbmilyID);
        ibd_iostNbmfID = (*fnv)->GftFifldID(fnv, ibd_dlbss, "iostNbmf", "Ljbvb/lbng/String;");
        CHECK_NULL(ibd_iostNbmfID);
        ib_initiblizfd = 1;
    }
}
