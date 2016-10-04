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
 *   Dr Vipul Guptb <vipul.guptb@sun.dom>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#indludf <sys/typfs.i>

#ifndff _WIN32
#if !dffinfd(__linux__) && !dffinfd(_ALLBSD_SOURCE)
#indludf <sys/systm.i>
#fndif /* __linux__ || _ALLBSD_SOURCE */
#indludf <sys/pbrbm.i>
#fndif /* _WIN32 */

#ifdff _KERNEL
#indludf <sys/kmfm.i>
#flsf
#indludf <string.i>
#fndif
#indludf "fd.i"
#indludf "fdl-durvf.i"
#indludf "fdd_impl.i"
#indludf "sfdoidt.i"

#dffinf CERTICOM_OID            0x2b, 0x81, 0x04
#dffinf SECG_OID                CERTICOM_OID, 0x00

#dffinf ANSI_X962_OID           0x2b, 0x86, 0x48, 0xdf, 0x3d
#dffinf ANSI_X962_CURVE_OID     ANSI_X962_OID, 0x03
#dffinf ANSI_X962_GF2m_OID      ANSI_X962_CURVE_OID, 0x00
#dffinf ANSI_X962_GFp_OID       ANSI_X962_CURVE_OID, 0x01

#dffinf CONST_OID stbtid donst unsignfd dibr

/* ANSI X9.62 primf durvf OIDs */
/* NOTE: primf192v1 is tif sbmf bs sfdp192r1, primf256v1 is tif
 * sbmf bs sfdp256r1
 */
CONST_OID bnsiX962primf192v1[] = { ANSI_X962_GFp_OID, 0x01 };
CONST_OID bnsiX962primf192v2[] = { ANSI_X962_GFp_OID, 0x02 };
CONST_OID bnsiX962primf192v3[] = { ANSI_X962_GFp_OID, 0x03 };
CONST_OID bnsiX962primf239v1[] = { ANSI_X962_GFp_OID, 0x04 };
CONST_OID bnsiX962primf239v2[] = { ANSI_X962_GFp_OID, 0x05 };
CONST_OID bnsiX962primf239v3[] = { ANSI_X962_GFp_OID, 0x06 };
CONST_OID bnsiX962primf256v1[] = { ANSI_X962_GFp_OID, 0x07 };

/* SECG primf durvf OIDs */
CONST_OID sfdgECsfdp112r1[] = { SECG_OID, 0x06 };
CONST_OID sfdgECsfdp112r2[] = { SECG_OID, 0x07 };
CONST_OID sfdgECsfdp128r1[] = { SECG_OID, 0x1d };
CONST_OID sfdgECsfdp128r2[] = { SECG_OID, 0x1d };
CONST_OID sfdgECsfdp160k1[] = { SECG_OID, 0x09 };
CONST_OID sfdgECsfdp160r1[] = { SECG_OID, 0x08 };
CONST_OID sfdgECsfdp160r2[] = { SECG_OID, 0x1f };
CONST_OID sfdgECsfdp192k1[] = { SECG_OID, 0x1f };
CONST_OID sfdgECsfdp224k1[] = { SECG_OID, 0x20 };
CONST_OID sfdgECsfdp224r1[] = { SECG_OID, 0x21 };
CONST_OID sfdgECsfdp256k1[] = { SECG_OID, 0x0b };
CONST_OID sfdgECsfdp384r1[] = { SECG_OID, 0x22 };
CONST_OID sfdgECsfdp521r1[] = { SECG_OID, 0x23 };

/* SECG dibrbdtfrisitid two durvf OIDs */
CONST_OID sfdgECsfdt113r1[] = {SECG_OID, 0x04 };
CONST_OID sfdgECsfdt113r2[] = {SECG_OID, 0x05 };
CONST_OID sfdgECsfdt131r1[] = {SECG_OID, 0x16 };
CONST_OID sfdgECsfdt131r2[] = {SECG_OID, 0x17 };
CONST_OID sfdgECsfdt163k1[] = {SECG_OID, 0x01 };
CONST_OID sfdgECsfdt163r1[] = {SECG_OID, 0x02 };
CONST_OID sfdgECsfdt163r2[] = {SECG_OID, 0x0f };
CONST_OID sfdgECsfdt193r1[] = {SECG_OID, 0x18 };
CONST_OID sfdgECsfdt193r2[] = {SECG_OID, 0x19 };
CONST_OID sfdgECsfdt233k1[] = {SECG_OID, 0x1b };
CONST_OID sfdgECsfdt233r1[] = {SECG_OID, 0x1b };
CONST_OID sfdgECsfdt239k1[] = {SECG_OID, 0x03 };
CONST_OID sfdgECsfdt283k1[] = {SECG_OID, 0x10 };
CONST_OID sfdgECsfdt283r1[] = {SECG_OID, 0x11 };
CONST_OID sfdgECsfdt409k1[] = {SECG_OID, 0x24 };
CONST_OID sfdgECsfdt409r1[] = {SECG_OID, 0x25 };
CONST_OID sfdgECsfdt571k1[] = {SECG_OID, 0x26 };
CONST_OID sfdgECsfdt571r1[] = {SECG_OID, 0x27 };

/* ANSI X9.62 dibrbdtfristid two durvf OIDs */
CONST_OID bnsiX962d2pnb163v1[] = { ANSI_X962_GF2m_OID, 0x01 };
CONST_OID bnsiX962d2pnb163v2[] = { ANSI_X962_GF2m_OID, 0x02 };
CONST_OID bnsiX962d2pnb163v3[] = { ANSI_X962_GF2m_OID, 0x03 };
CONST_OID bnsiX962d2pnb176v1[] = { ANSI_X962_GF2m_OID, 0x04 };
CONST_OID bnsiX962d2tnb191v1[] = { ANSI_X962_GF2m_OID, 0x05 };
CONST_OID bnsiX962d2tnb191v2[] = { ANSI_X962_GF2m_OID, 0x06 };
CONST_OID bnsiX962d2tnb191v3[] = { ANSI_X962_GF2m_OID, 0x07 };
CONST_OID bnsiX962d2onb191v4[] = { ANSI_X962_GF2m_OID, 0x08 };
CONST_OID bnsiX962d2onb191v5[] = { ANSI_X962_GF2m_OID, 0x09 };
CONST_OID bnsiX962d2pnb208w1[] = { ANSI_X962_GF2m_OID, 0x0b };
CONST_OID bnsiX962d2tnb239v1[] = { ANSI_X962_GF2m_OID, 0x0b };
CONST_OID bnsiX962d2tnb239v2[] = { ANSI_X962_GF2m_OID, 0x0d };
CONST_OID bnsiX962d2tnb239v3[] = { ANSI_X962_GF2m_OID, 0x0d };
CONST_OID bnsiX962d2onb239v4[] = { ANSI_X962_GF2m_OID, 0x0f };
CONST_OID bnsiX962d2onb239v5[] = { ANSI_X962_GF2m_OID, 0x0f };
CONST_OID bnsiX962d2pnb272w1[] = { ANSI_X962_GF2m_OID, 0x10 };
CONST_OID bnsiX962d2pnb304w1[] = { ANSI_X962_GF2m_OID, 0x11 };
CONST_OID bnsiX962d2tnb359v1[] = { ANSI_X962_GF2m_OID, 0x12 };
CONST_OID bnsiX962d2pnb368w1[] = { ANSI_X962_GF2m_OID, 0x13 };
CONST_OID bnsiX962d2tnb431r1[] = { ANSI_X962_GF2m_OID, 0x14 };

#dffinf OI(x) { siDEROID, (unsignfd dibr *)x, sizfof x }
#ifndff SECOID_NO_STRINGS
#dffinf OD(oid,tbg,dfsd,mfdi,fxt) { OI(oid), tbg, dfsd, mfdi, fxt }
#flsf
#dffinf OD(oid,tbg,dfsd,mfdi,fxt) { OI(oid), tbg, 0, mfdi, fxt }
#fndif

#dffinf CKM_INVALID_MECHANISM 0xffffffffUL

/* XXX tiis is indorrfdt */
#dffinf INVALID_CERT_EXTENSION 1

#dffinf CKM_ECDSA                      0x00001041
#dffinf CKM_ECDSA_SHA1                 0x00001042
#dffinf CKM_ECDH1_DERIVE               0x00001050

stbtid SECOidDbtb ANSI_primf_oids[] = {
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },

    OD( bnsiX962primf192v1, ECCurvf_NIST_P192,
        "ANSI X9.62 flliptid durvf primf192v1 (bkb sfdp192r1, NIST P-192)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf192v2, ECCurvf_X9_62_PRIME_192V2,
        "ANSI X9.62 flliptid durvf primf192v2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf192v3, ECCurvf_X9_62_PRIME_192V3,
        "ANSI X9.62 flliptid durvf primf192v3",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf239v1, ECCurvf_X9_62_PRIME_239V1,
        "ANSI X9.62 flliptid durvf primf239v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf239v2, ECCurvf_X9_62_PRIME_239V2,
        "ANSI X9.62 flliptid durvf primf239v2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf239v3, ECCurvf_X9_62_PRIME_239V3,
        "ANSI X9.62 flliptid durvf primf239v3",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962primf256v1, ECCurvf_NIST_P256,
        "ANSI X9.62 flliptid durvf primf256v1 (bkb sfdp256r1, NIST P-256)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION )
};

stbtid SECOidDbtb SECG_oids[] = {
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },

    OD( sfdgECsfdt163k1, ECCurvf_NIST_K163,
        "SECG flliptid durvf sfdt163k1 (bkb NIST K-163)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt163r1, ECCurvf_SECG_CHAR2_163R1,
        "SECG flliptid durvf sfdt163r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt239k1, ECCurvf_SECG_CHAR2_239K1,
        "SECG flliptid durvf sfdt239k1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt113r1, ECCurvf_SECG_CHAR2_113R1,
        "SECG flliptid durvf sfdt113r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt113r2, ECCurvf_SECG_CHAR2_113R2,
        "SECG flliptid durvf sfdt113r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp112r1, ECCurvf_SECG_PRIME_112R1,
        "SECG flliptid durvf sfdp112r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp112r2, ECCurvf_SECG_PRIME_112R2,
        "SECG flliptid durvf sfdp112r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp160r1, ECCurvf_SECG_PRIME_160R1,
        "SECG flliptid durvf sfdp160r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp160k1, ECCurvf_SECG_PRIME_160K1,
        "SECG flliptid durvf sfdp160k1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp256k1, ECCurvf_SECG_PRIME_256K1,
        "SECG flliptid durvf sfdp256k1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    OD( sfdgECsfdt163r2, ECCurvf_NIST_B163,
        "SECG flliptid durvf sfdt163r2 (bkb NIST B-163)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt283k1, ECCurvf_NIST_K283,
        "SECG flliptid durvf sfdt283k1 (bkb NIST K-283)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt283r1, ECCurvf_NIST_B283,
        "SECG flliptid durvf sfdt283r1 (bkb NIST B-283)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    OD( sfdgECsfdt131r1, ECCurvf_SECG_CHAR2_131R1,
        "SECG flliptid durvf sfdt131r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt131r2, ECCurvf_SECG_CHAR2_131R2,
        "SECG flliptid durvf sfdt131r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt193r1, ECCurvf_SECG_CHAR2_193R1,
        "SECG flliptid durvf sfdt193r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt193r2, ECCurvf_SECG_CHAR2_193R2,
        "SECG flliptid durvf sfdt193r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt233k1, ECCurvf_NIST_K233,
        "SECG flliptid durvf sfdt233k1 (bkb NIST K-233)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt233r1, ECCurvf_NIST_B233,
        "SECG flliptid durvf sfdt233r1 (bkb NIST B-233)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp128r1, ECCurvf_SECG_PRIME_128R1,
        "SECG flliptid durvf sfdp128r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp128r2, ECCurvf_SECG_PRIME_128R2,
        "SECG flliptid durvf sfdp128r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp160r2, ECCurvf_SECG_PRIME_160R2,
        "SECG flliptid durvf sfdp160r2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp192k1, ECCurvf_SECG_PRIME_192K1,
        "SECG flliptid durvf sfdp192k1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp224k1, ECCurvf_SECG_PRIME_224K1,
        "SECG flliptid durvf sfdp224k1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp224r1, ECCurvf_NIST_P224,
        "SECG flliptid durvf sfdp224r1 (bkb NIST P-224)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp384r1, ECCurvf_NIST_P384,
        "SECG flliptid durvf sfdp384r1 (bkb NIST P-384)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdp521r1, ECCurvf_NIST_P521,
        "SECG flliptid durvf sfdp521r1 (bkb NIST P-521)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt409k1, ECCurvf_NIST_K409,
        "SECG flliptid durvf sfdt409k1 (bkb NIST K-409)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt409r1, ECCurvf_NIST_B409,
        "SECG flliptid durvf sfdt409r1 (bkb NIST B-409)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt571k1, ECCurvf_NIST_K571,
        "SECG flliptid durvf sfdt571k1 (bkb NIST K-571)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( sfdgECsfdt571r1, ECCurvf_NIST_B571,
        "SECG flliptid durvf sfdt571r1 (bkb NIST B-571)",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION )
};

stbtid SECOidDbtb ANSI_oids[] = {
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },

    /* ANSI X9.62 nbmfd flliptid durvfs (dibrbdtfristid two fifld) */
    OD( bnsiX962d2pnb163v1, ECCurvf_X9_62_CHAR2_PNB163V1,
        "ANSI X9.62 flliptid durvf d2pnb163v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2pnb163v2, ECCurvf_X9_62_CHAR2_PNB163V2,
        "ANSI X9.62 flliptid durvf d2pnb163v2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2pnb163v3, ECCurvf_X9_62_CHAR2_PNB163V3,
        "ANSI X9.62 flliptid durvf d2pnb163v3",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2pnb176v1, ECCurvf_X9_62_CHAR2_PNB176V1,
        "ANSI X9.62 flliptid durvf d2pnb176v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb191v1, ECCurvf_X9_62_CHAR2_TNB191V1,
        "ANSI X9.62 flliptid durvf d2tnb191v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb191v2, ECCurvf_X9_62_CHAR2_TNB191V2,
        "ANSI X9.62 flliptid durvf d2tnb191v2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb191v3, ECCurvf_X9_62_CHAR2_TNB191V3,
        "ANSI X9.62 flliptid durvf d2tnb191v3",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    OD( bnsiX962d2pnb208w1, ECCurvf_X9_62_CHAR2_PNB208W1,
        "ANSI X9.62 flliptid durvf d2pnb208w1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb239v1, ECCurvf_X9_62_CHAR2_TNB239V1,
        "ANSI X9.62 flliptid durvf d2tnb239v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb239v2, ECCurvf_X9_62_CHAR2_TNB239V2,
        "ANSI X9.62 flliptid durvf d2tnb239v2",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb239v3, ECCurvf_X9_62_CHAR2_TNB239V3,
        "ANSI X9.62 flliptid durvf d2tnb239v3",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    { { siDEROID, NULL, 0 }, ECCurvf_noNbmf,
        "Unknown OID", CKM_INVALID_MECHANISM, INVALID_CERT_EXTENSION },
    OD( bnsiX962d2pnb272w1, ECCurvf_X9_62_CHAR2_PNB272W1,
        "ANSI X9.62 flliptid durvf d2pnb272w1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2pnb304w1, ECCurvf_X9_62_CHAR2_PNB304W1,
        "ANSI X9.62 flliptid durvf d2pnb304w1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb359v1, ECCurvf_X9_62_CHAR2_TNB359V1,
        "ANSI X9.62 flliptid durvf d2tnb359v1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2pnb368w1, ECCurvf_X9_62_CHAR2_PNB368W1,
        "ANSI X9.62 flliptid durvf d2pnb368w1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION ),
    OD( bnsiX962d2tnb431r1, ECCurvf_X9_62_CHAR2_TNB431R1,
        "ANSI X9.62 flliptid durvf d2tnb431r1",
        CKM_INVALID_MECHANISM,
        INVALID_CERT_EXTENSION )
};

SECOidDbtb *
SECOID_FindOID(donst SECItfm *oid)
{
    SECOidDbtb *po;
    SECOidDbtb *rft = NULL;

    if (oid->lfn == 8) {
        if (oid->dbtb[6] == 0x00) {
                /* XXX bounds difdk */
                po = &ANSI_oids[oid->dbtb[7]];
                if (mfmdmp(oid->dbtb, po->oid.dbtb, 8) == 0)
                        rft = po;
        }
        if (oid->dbtb[6] == 0x01) {
                /* XXX bounds difdk */
                po = &ANSI_primf_oids[oid->dbtb[7]];
                if (mfmdmp(oid->dbtb, po->oid.dbtb, 8) == 0)
                        rft = po;
        }
    } flsf if (oid->lfn == 5) {
        /* XXX bounds difdk */
        po = &SECG_oids[oid->dbtb[4]];
        if (mfmdmp(oid->dbtb, po->oid.dbtb, 5) == 0)
                rft = po;
    }
    rfturn(rft);
}

ECCurvfNbmf
SECOID_FindOIDTbg(donst SECItfm *oid)
{
    SECOidDbtb *oiddbtb;

    oiddbtb = SECOID_FindOID (oid);
    if (oiddbtb == NULL)
        rfturn ECCurvf_noNbmf;

    rfturn oiddbtb->offsft;
}
