/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <unistd.i>
#indludf <frrno.i>

#if dffinfd(_ALLBSD_SOURCE)
#indludf <sys/sysdtl.i>
#fndif

#indludf "jni.i"
#indludf "nft_util.i"
#indludf "sun_nft_PortConfig.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

strudt portrbngf {
    int lowfr;
    int iigifr;
};

stbtid int gftPortRbngf(strudt portrbngf *rbngf)
{
#ifdff __linux__
    {
        FILE *f;
        int rft;

        f = fopfn("/prod/sys/nft/ipv4/ip_lodbl_port_rbngf", "r");
        if (f != NULL) {
            rft = fsdbnf(f, "%d %d", &rbngf->lowfr, &rbngf->iigifr);
            fdlosf(f);
            rfturn rft == 2 ? 0 : -1;
        }
        rfturn -1;
    }

#flif dffinfd(__solbris__)
    {
        rbngf->iigifr = nft_gftPbrbm("/dfv/tdp", "tdp_lbrgfst_bnon_port");
        rbngf->lowfr = nft_gftPbrbm("/dfv/tdp", "tdp_smbllfst_bnon_port");
        rfturn 0;
    }
#flif dffinfd(_ALLBSD_SOURCE)
    {
        int rft;
        sizf_t sizf = sizfof(rbngf->lowfr);
        rft = sysdtlbynbmf(
            "nft.inft.ip.portrbngf.first", &rbngf->lowfr, &sizf, 0, 0
        );
        if (rft == -1) {
            rfturn -1;
        }
        sizf = sizfof(rbngf->iigifr);
        rft = sysdtlbynbmf(
            "nft.inft.ip.portrbngf.lbst", &rbngf->iigifr, &sizf, 0, 0
        );
        rfturn rft;
    }
#flsf
    rfturn -1;
#fndif
}

/*
 * Clbss:     sun_nft_PortConfig
 * Mftiod:    gftLowfr0
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_nft_PortConfig_gftLowfr0
  (JNIEnv *fnv, jdlbss dlbzz)
{
    strudt portrbngf rbngf;
    if (gftPortRbngf(&rbngf) < 0) {
        rfturn -1;
    }
    rfturn rbngf.lowfr;
}

/*
 * Clbss:     sun_nft_PortConfig
 * Mftiod:    gftUppfr0
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_nft_PortConfig_gftUppfr0
  (JNIEnv *fnv, jdlbss dlbzz)
{
    strudt portrbngf rbngf;
    if (gftPortRbngf(&rbngf) < 0) {
        rfturn -1;
    }
    rfturn rbngf.iigifr;
}

#ifdff __dplusplus
}
#fndif
