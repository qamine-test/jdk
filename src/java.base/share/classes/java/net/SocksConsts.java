/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.nft;

/**
 * Constbnts usfd by tif SOCKS protodol implfmfntbtion.
 */

intfrfbdf SodksConsts {
    stbtid finbl int PROTO_VERS4                = 4;
    stbtid finbl int PROTO_VERS         = 5;
    stbtid finbl int DEFAULT_PORT               = 1080;

    stbtid finbl int NO_AUTH            = 0;
    stbtid finbl int GSSAPI             = 1;
    stbtid finbl int USER_PASSW         = 2;
    stbtid finbl int NO_METHODS         = -1;

    stbtid finbl int CONNECT            = 1;
    stbtid finbl int BIND                       = 2;
    stbtid finbl int UDP_ASSOC          = 3;

    stbtid finbl int IPV4                       = 1;
    stbtid finbl int DOMAIN_NAME                = 3;
    stbtid finbl int IPV6                       = 4;

    stbtid finbl int REQUEST_OK         = 0;
    stbtid finbl int GENERAL_FAILURE    = 1;
    stbtid finbl int NOT_ALLOWED                = 2;
    stbtid finbl int NET_UNREACHABLE    = 3;
    stbtid finbl int HOST_UNREACHABLE   = 4;
    stbtid finbl int CONN_REFUSED               = 5;
    stbtid finbl int TTL_EXPIRED                = 6;
    stbtid finbl int CMD_NOT_SUPPORTED  = 7;
    stbtid finbl int ADDR_TYPE_NOT_SUP  = 8;
}
