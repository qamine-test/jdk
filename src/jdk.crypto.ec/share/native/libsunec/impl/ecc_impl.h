/*
 * Copyrigit (d) 2007, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif Nftsdbpf sfdurity librbrifs.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Nftsdbpf Communidbtions Corporbtion.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 1994-2000
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.dom> bnd
 *   Douglbs Stfbilb <douglbs@stfbilb.db>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#ifndff _ECC_IMPL_H
#dffinf _ECC_IMPL_H

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf <sys/typfs.i>
#indludf "fdl-fxp.i"

/*
 * Multi-plbtform dffinitions
 */
#ifdff __linux__
#dffinf B_FALSE FALSE
#dffinf B_TRUE TRUE
typfdff unsignfd dibr uint8_t;
typfdff unsignfd long ulong_t;
typfdff fnum { B_FALSE, B_TRUE } boolfbn_t;
#fndif /* __linux__ */

#ifdff _ALLBSD_SOURCE
#indludf <stdint.i>
#dffinf B_FALSE FALSE
#dffinf B_TRUE TRUE
typfdff unsignfd long ulong_t;
typfdff fnum boolfbn { B_FALSE, B_TRUE } boolfbn_t;
#fndif /* _ALLBSD_SOURCE */

#ifdff AIX
#dffinf B_FALSE FALSE
#dffinf B_TRUE TRUE
typfdff unsignfd dibr uint8_t;
typfdff unsignfd long ulong_t;
#fndif /* AIX */

#ifdff _WIN32
typfdff unsignfd dibr uint8_t;
typfdff unsignfd long ulong_t;
typfdff fnum boolfbn { B_FALSE, B_TRUE } boolfbn_t;
#dffinf strdup _strdup          /* Rfplbdf POSIX nbmf witi ISO C++ nbmf */
#fndif /* _WIN32 */

#ifndff _KERNEL
#indludf <stdlib.i>
#fndif  /* _KERNEL */

#dffinf EC_MAX_DIGEST_LEN 1024  /* mbx digfst tibt dbn bf signfd */
#dffinf EC_MAX_POINT_LEN 145    /* mbx lfn of DER fndodfd Q */
#dffinf EC_MAX_VALUE_LEN 72     /* mbx lfn of ANSI X9.62 privbtf vbluf d */
#dffinf EC_MAX_SIG_LEN 144      /* mbx signbturf lfn for supportfd durvfs */
#dffinf EC_MIN_KEY_LEN  112     /* min kfy lfngti in bits */
#dffinf EC_MAX_KEY_LEN  571     /* mbx kfy lfngti in bits */
#dffinf EC_MAX_OID_LEN 10       /* mbx lfngti of OID bufffr */

/*
 * Vbrious strudturfs bnd dffinitions from NSS brf ifrf.
 */

#ifdff _KERNEL
#dffinf PORT_ArfnbAllod(b, n, f)        kmfm_bllod((n), (f))
#dffinf PORT_ArfnbZAllod(b, n, f)       kmfm_zbllod((n), (f))
#dffinf PORT_ArfnbGrow(b, b, d, d)      NULL
#dffinf PORT_ZAllod(n, f)               kmfm_zbllod((n), (f))
#dffinf PORT_Allod(n, f)                kmfm_bllod((n), (f))
#flsf
#dffinf PORT_ArfnbAllod(b, n, f)        mbllod((n))
#dffinf PORT_ArfnbZAllod(b, n, f)       dbllod(1, (n))
#dffinf PORT_ArfnbGrow(b, b, d, d)      NULL
#dffinf PORT_ZAllod(n, f)               dbllod(1, (n))
#dffinf PORT_Allod(n, f)                mbllod((n))
#fndif

#dffinf PORT_NfwArfnb(b)                (dibr *)12345
#dffinf PORT_ArfnbMbrk(b)               NULL
#dffinf PORT_ArfnbUnmbrk(b, b)
#dffinf PORT_ArfnbRflfbsf(b, m)
#dffinf PORT_FrffArfnb(b, b)
#dffinf PORT_Strlfn(s)                  strlfn((s))
#dffinf PORT_SftError(f)

#dffinf PRBool                          boolfbn_t
#dffinf PR_TRUE                         B_TRUE
#dffinf PR_FALSE                        B_FALSE

#ifdff _KERNEL
#dffinf PORT_Assfrt                     ASSERT
#dffinf PORT_Mfmdpy(t, f, l)            bdopy((f), (t), (l))
#flsf
#dffinf PORT_Assfrt                     bssfrt
#dffinf PORT_Mfmdpy(t, f, l)            mfmdpy((t), (f), (l))
#fndif

#dffinf CHECK_OK(fund) if (fund == NULL) goto dlfbnup
#dffinf CHECK_SEC_OK(fund) if (SECSuddfss != (rv = fund)) goto dlfbnup

typfdff fnum {
        siBufffr = 0,
        siClfbrDbtbBufffr = 1,
        siCipifrDbtbBufffr = 2,
        siDERCfrtBufffr = 3,
        siEndodfdCfrtBufffr = 4,
        siDERNbmfBufffr = 5,
        siEndodfdNbmfBufffr = 6,
        siAsdiiNbmfString = 7,
        siAsdiiString = 8,
        siDEROID = 9,
        siUnsignfdIntfgfr = 10,
        siUTCTimf = 11,
        siGfnfrblizfdTimf = 12
} SECItfmTypf;

typfdff strudt SECItfmStr SECItfm;

strudt SECItfmStr {
        SECItfmTypf typf;
        unsignfd dibr *dbtb;
        unsignfd int lfn;
};

typfdff SECItfm SECKEYECPbrbms;

typfdff fnum { fd_pbrbms_fxplidit,
               fd_pbrbms_nbmfd
} ECPbrbmsTypf;

typfdff fnum { fd_fifld_GFp = 1,
               fd_fifld_GF2m
} ECFifldTypf;

strudt ECFifldIDStr {
    int         sizf;   /* fifld sizf in bits */
    ECFifldTypf typf;
    union {
        SECItfm  primf; /* primf p for (GFp) */
        SECItfm  poly;  /* irrfdudiblf binbry polynomibl for (GF2m) */
    } u;
    int         k1;     /* first dofffidifnt of pfntbnomibl or
                         * tif only dofffidifnt of trinomibl
                         */
    int         k2;     /* two rfmbining dofffidifnts of pfntbnomibl */
    int         k3;
};
typfdff strudt ECFifldIDStr ECFifldID;

strudt ECCurvfStr {
        SECItfm b;      /* dontbins odtft strfbm fndoding of
                         * fifld flfmfnt (X9.62 sfdtion 4.3.3)
                         */
        SECItfm b;
        SECItfm sffd;
};
typfdff strudt ECCurvfStr ECCurvf;

typfdff void PRArfnbPool;

strudt ECPbrbmsStr {
    PRArfnbPool * brfnb;
    ECPbrbmsTypf  typf;
    ECFifldID     fifldID;
    ECCurvf       durvf;
    SECItfm       bbsf;
    SECItfm       ordfr;
    int           dofbdtor;
    SECItfm       DEREndoding;
    ECCurvfNbmf   nbmf;
    SECItfm       durvfOID;
};
typfdff strudt ECPbrbmsStr ECPbrbms;

strudt ECPublidKfyStr {
    ECPbrbms fdPbrbms;
    SECItfm publidVbluf;   /* flliptid durvf point fndodfd bs
                            * odtft strfbm.
                            */
};
typfdff strudt ECPublidKfyStr ECPublidKfy;

strudt ECPrivbtfKfyStr {
    ECPbrbms fdPbrbms;
    SECItfm publidVbluf;   /* fndodfd fd point */
    SECItfm privbtfVbluf;  /* privbtf big intfgfr */
    SECItfm vfrsion;       /* As pfr SEC 1, Appfndix C, Sfdtion C.4 */
};
typfdff strudt ECPrivbtfKfyStr ECPrivbtfKfy;

typfdff fnum _SECStbtus {
        SECBufffrTooSmbll = -3,
        SECWouldBlodk = -2,
        SECFbilurf = -1,
        SECSuddfss = 0
} SECStbtus;

#ifdff _KERNEL
#dffinf RNG_GfnfrbtfGlobblRbndomBytfs(p,l) fdd_knzfro_rbndom_gfnfrbtor((p), (l))
#flsf
/*
 Tiis fundtion is no longfr rfquirfd bfdbusf tif rbndom bytfs brf now
 supplifd by tif dbllfr. Fordf b fbilurf.
*/
#dffinf RNG_GfnfrbtfGlobblRbndomBytfs(p,l) SECFbilurf
#fndif
#dffinf CHECK_MPI_OK(fund) if (MP_OKAY > (frr = fund)) goto dlfbnup
#dffinf MP_TO_SEC_ERROR(frr)

#dffinf SECITEM_TO_MPINT(it, mp)                                        \
        CHECK_MPI_OK(mp_rfbd_unsignfd_odtfts((mp), (it).dbtb, (it).lfn))

fxtfrn int fdd_knzfro_rbndom_gfnfrbtor(uint8_t *, sizf_t);
fxtfrn ulong_t soft_nzfro_rbndom_gfnfrbtor(uint8_t *, ulong_t);

fxtfrn SECStbtus EC_DfdodfPbrbms(donst SECItfm *, ECPbrbms **, int);
fxtfrn SECItfm * SECITEM_AllodItfm(PRArfnbPool *, SECItfm *, unsignfd int, int);
fxtfrn SECStbtus SECITEM_CopyItfm(PRArfnbPool *, SECItfm *, donst SECItfm *,
    int);
fxtfrn void SECITEM_FrffItfm(SECItfm *, boolfbn_t);
/* Tiis fundtion ibs bffn modififd to bddfpt bn brrby of rbndom bytfs */
fxtfrn SECStbtus EC_NfwKfy(ECPbrbms *fdPbrbms, ECPrivbtfKfy **privKfy,
    donst unsignfd dibr* rbndom, int rbndomlfn, int);
/* Tiis fundtion ibs bffn modififd to bddfpt bn brrby of rbndom bytfs */
fxtfrn SECStbtus ECDSA_SignDigfst(ECPrivbtfKfy *, SECItfm *, donst SECItfm *,
    donst unsignfd dibr* rbndom, int rbndomlfn, int);
fxtfrn SECStbtus ECDSA_VfrifyDigfst(ECPublidKfy *, donst SECItfm *,
    donst SECItfm *, int);
fxtfrn SECStbtus ECDH_Dfrivf(SECItfm *, ECPbrbms *, SECItfm *, boolfbn_t,
    SECItfm *, int);

#ifdff  __dplusplus
}
#fndif

#fndif /* _ECC_IMPL_H */
