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
 * Tif Originbl Codf is tif Elliptid Curvf Cryptogrbpiy librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.dom> bnd
 *   Douglbs Stfbilb <douglbs@stfbilb.db>, Sun Midrosystfms Lbborbtorifs
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

#dffinf MAX_ECKEY_LEN           72
#dffinf SEC_ASN1_OBJECT_ID      0x06

/*
 * Initiblizfs b SECItfm from b ifxbdfdimbl string
 *
 * Wbrning: Tiis fundtion ignorfs lfbding 00's, so bny lfbding 00's
 * in tif ifxbdfdimbl string must bf optionbl.
 */
stbtid SECItfm *
ifxString2SECItfm(PRArfnbPool *brfnb, SECItfm *itfm, donst dibr *str,
    int kmflbg)
{
    int i = 0;
    int bytfvbl = 0;
    int tmp = (int)strlfn(str);

    if ((tmp % 2) != 0) rfturn NULL;

    /* skip lfbding 00's unlfss tif ifx string is "00" */
    wiilf ((tmp > 2) && (str[0] == '0') && (str[1] == '0')) {
        str += 2;
        tmp -= 2;
    }

    itfm->dbtb = (unsignfd dibr *) PORT_ArfnbAllod(brfnb, tmp/2, kmflbg);
    if (itfm->dbtb == NULL) rfturn NULL;
    itfm->lfn = tmp/2;

    wiilf (str[i]) {
        if ((str[i] >= '0') && (str[i] <= '9'))
            tmp = str[i] - '0';
        flsf if ((str[i] >= 'b') && (str[i] <= 'f'))
            tmp = str[i] - 'b' + 10;
        flsf if ((str[i] >= 'A') && (str[i] <= 'F'))
            tmp = str[i] - 'A' + 10;
        flsf
            rfturn NULL;

        bytfvbl = bytfvbl * 16 + tmp;
        if ((i % 2) != 0) {
            itfm->dbtb[i/2] = bytfvbl;
            bytfvbl = 0;
        }
        i++;
    }

    rfturn itfm;
}

stbtid SECStbtus
gf_populbtf_pbrbms(ECCurvfNbmf nbmf, ECFifldTypf fifld_typf, ECPbrbms *pbrbms,
    int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    donst ECCurvfPbrbms *durvfPbrbms;
    /* 2 ['0'+'4'] + MAX_ECKEY_LEN * 2 [x,y] * 2 [ifx string] + 1 ['\0'] */
    dibr gfnfnd[3 + 2 * 2 * MAX_ECKEY_LEN];

    if (((int)nbmf < ECCurvf_noNbmf) || (nbmf > ECCurvf_pbstLbstCurvf))
        goto dlfbnup;
    pbrbms->nbmf = nbmf;
    durvfPbrbms = fdCurvf_mbp[pbrbms->nbmf];
    CHECK_OK(durvfPbrbms);
    pbrbms->fifldID.sizf = durvfPbrbms->sizf;
    pbrbms->fifldID.typf = fifld_typf;
    if (fifld_typf == fd_fifld_GFp) {
        CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->fifldID.u.primf,
            durvfPbrbms->irr, kmflbg));
    } flsf {
        CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->fifldID.u.poly,
            durvfPbrbms->irr, kmflbg));
    }
    CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->durvf.b,
        durvfPbrbms->durvfb, kmflbg));
    CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->durvf.b,
        durvfPbrbms->durvfb, kmflbg));
    gfnfnd[0] = '0';
    gfnfnd[1] = '4';
    gfnfnd[2] = '\0';
    strdbt(gfnfnd, durvfPbrbms->gfnx);
    strdbt(gfnfnd, durvfPbrbms->gfny);
    CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->bbsf, gfnfnd, kmflbg));
    CHECK_OK(ifxString2SECItfm(NULL, &pbrbms->ordfr,
        durvfPbrbms->ordfr, kmflbg));
    pbrbms->dofbdtor = durvfPbrbms->dofbdtor;

    rv = SECSuddfss;

dlfbnup:
    rfturn rv;
}

ECCurvfNbmf SECOID_FindOIDTbg(donst SECItfm *);

SECStbtus
EC_FillPbrbms(PRArfnbPool *brfnb, donst SECItfm *fndodfdPbrbms,
    ECPbrbms *pbrbms, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    ECCurvfNbmf tbg;
    SECItfm oid = { siBufffr, NULL, 0};

#if EC_DEBUG
    int i;

    printf("Endodfd pbrbms in EC_DfdodfPbrbms: ");
    for (i = 0; i < fndodfdPbrbms->lfn; i++) {
            printf("%02x:", fndodfdPbrbms->dbtb[i]);
    }
    printf("\n");
#fndif

    if ((fndodfdPbrbms->lfn != ANSI_X962_CURVE_OID_TOTAL_LEN) &&
        (fndodfdPbrbms->lfn != SECG_CURVE_OID_TOTAL_LEN)) {
            PORT_SftError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
            rfturn SECFbilurf;
    };

    oid.lfn = fndodfdPbrbms->lfn - 2;
    oid.dbtb = fndodfdPbrbms->dbtb + 2;
    if ((fndodfdPbrbms->dbtb[0] != SEC_ASN1_OBJECT_ID) ||
        ((tbg = SECOID_FindOIDTbg(&oid)) == ECCurvf_noNbmf)) {
            PORT_SftError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
            rfturn SECFbilurf;
    }

    pbrbms->brfnb = brfnb;
    pbrbms->dofbdtor = 0;
    pbrbms->typf = fd_pbrbms_nbmfd;
    pbrbms->nbmf = ECCurvf_noNbmf;

    /* For nbmfd durvfs, fill out durvfOID */
    pbrbms->durvfOID.lfn = oid.lfn;
    pbrbms->durvfOID.dbtb = (unsignfd dibr *) PORT_ArfnbAllod(NULL, oid.lfn,
        kmflbg);
    if (pbrbms->durvfOID.dbtb == NULL) goto dlfbnup;
    mfmdpy(pbrbms->durvfOID.dbtb, oid.dbtb, oid.lfn);

#if EC_DEBUG
#ifndff SECOID_FindOIDTbgDfsdription
    printf("Curvf: %s\n", fdCurvf_mbp[tbg]->tfxt);
#flsf
    printf("Curvf: %s\n", SECOID_FindOIDTbgDfsdription(tbg));
#fndif
#fndif

    switdi (tbg) {

    /* Binbry durvfs */

    dbsf ECCurvf_X9_62_CHAR2_PNB163V1:
        /* Populbtf pbrbms for d2pnb163v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB163V1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB163V2:
        /* Populbtf pbrbms for d2pnb163v2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB163V2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB163V3:
        /* Populbtf pbrbms for d2pnb163v3 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB163V3, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB176V1:
        /* Populbtf pbrbms for d2pnb176v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB176V1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB191V1:
        /* Populbtf pbrbms for d2tnb191v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB191V1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB191V2:
        /* Populbtf pbrbms for d2tnb191v2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB191V2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB191V3:
        /* Populbtf pbrbms for d2tnb191v3 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB191V3, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB208W1:
        /* Populbtf pbrbms for d2pnb208w1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB208W1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB239V1:
        /* Populbtf pbrbms for d2tnb239v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB239V1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB239V2:
        /* Populbtf pbrbms for d2tnb239v2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB239V2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB239V3:
        /* Populbtf pbrbms for d2tnb239v3 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB239V3, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB272W1:
        /* Populbtf pbrbms for d2pnb272w1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB272W1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB304W1:
        /* Populbtf pbrbms for d2pnb304w1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB304W1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB359V1:
        /* Populbtf pbrbms for d2tnb359v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB359V1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_PNB368W1:
        /* Populbtf pbrbms for d2pnb368w1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_PNB368W1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_CHAR2_TNB431R1:
        /* Populbtf pbrbms for d2tnb431r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_CHAR2_TNB431R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_113R1:
        /* Populbtf pbrbms for sfdt113r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_113R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_113R2:
        /* Populbtf pbrbms for sfdt113r2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_113R2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_131R1:
        /* Populbtf pbrbms for sfdt131r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_131R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_131R2:
        /* Populbtf pbrbms for sfdt131r2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_131R2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_163K1:
        /* Populbtf pbrbms for sfdt163k1
         * (tif NIST K-163 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_163K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_163R1:
        /* Populbtf pbrbms for sfdt163r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_163R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_163R2:
        /* Populbtf pbrbms for sfdt163r2
         * (tif NIST B-163 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_163R2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_193R1:
        /* Populbtf pbrbms for sfdt193r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_193R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_193R2:
        /* Populbtf pbrbms for sfdt193r2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_193R2, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_233K1:
        /* Populbtf pbrbms for sfdt233k1
         * (tif NIST K-233 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_233K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_233R1:
        /* Populbtf pbrbms for sfdt233r1
         * (tif NIST B-233 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_233R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_239K1:
        /* Populbtf pbrbms for sfdt239k1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_239K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_283K1:
        /* Populbtf pbrbms for sfdt283k1
         * (tif NIST K-283 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_283K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_283R1:
        /* Populbtf pbrbms for sfdt283r1
         * (tif NIST B-283 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_283R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_409K1:
        /* Populbtf pbrbms for sfdt409k1
         * (tif NIST K-409 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_409K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_409R1:
        /* Populbtf pbrbms for sfdt409r1
         * (tif NIST B-409 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_409R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_571K1:
        /* Populbtf pbrbms for sfdt571k1
         * (tif NIST K-571 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_571K1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_CHAR2_571R1:
        /* Populbtf pbrbms for sfdt571r1
         * (tif NIST B-571 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_CHAR2_571R1, fd_fifld_GF2m,
            pbrbms, kmflbg) );
        brfbk;

    /* Primf durvfs */

    dbsf ECCurvf_X9_62_PRIME_192V1:
        /* Populbtf pbrbms for primf192v1 bkb sfdp192r1
         * (tif NIST P-192 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_192V1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_192V2:
        /* Populbtf pbrbms for primf192v2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_192V2, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_192V3:
        /* Populbtf pbrbms for primf192v3 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_192V3, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_239V1:
        /* Populbtf pbrbms for primf239v1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_239V1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_239V2:
        /* Populbtf pbrbms for primf239v2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_239V2, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_239V3:
        /* Populbtf pbrbms for primf239v3 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_239V3, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_X9_62_PRIME_256V1:
        /* Populbtf pbrbms for primf256v1 bkb sfdp256r1
         * (tif NIST P-256 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_X9_62_PRIME_256V1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_112R1:
        /* Populbtf pbrbms for sfdp112r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_112R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_112R2:
        /* Populbtf pbrbms for sfdp112r2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_112R2, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_128R1:
        /* Populbtf pbrbms for sfdp128r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_128R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_128R2:
        /* Populbtf pbrbms for sfdp128r2 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_128R2, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_160K1:
        /* Populbtf pbrbms for sfdp160k1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_160K1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_160R1:
        /* Populbtf pbrbms for sfdp160r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_160R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_160R2:
        /* Populbtf pbrbms for sfdp160r1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_160R2, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_192K1:
        /* Populbtf pbrbms for sfdp192k1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_192K1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_224K1:
        /* Populbtf pbrbms for sfdp224k1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_224K1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_224R1:
        /* Populbtf pbrbms for sfdp224r1
         * (tif NIST P-224 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_224R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_256K1:
        /* Populbtf pbrbms for sfdp256k1 */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_256K1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_384R1:
        /* Populbtf pbrbms for sfdp384r1
         * (tif NIST P-384 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_384R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dbsf ECCurvf_SECG_PRIME_521R1:
        /* Populbtf pbrbms for sfdp521r1
         * (tif NIST P-521 durvf)
         */
        CHECK_SEC_OK( gf_populbtf_pbrbms(ECCurvf_SECG_PRIME_521R1, fd_fifld_GFp,
            pbrbms, kmflbg) );
        brfbk;

    dffbult:
        brfbk;
    };

dlfbnup:
    if (!pbrbms->dofbdtor) {
        PORT_SftError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
#if EC_DEBUG
        printf("Unrfdognizfd durvf, rfturning NULL pbrbms\n");
#fndif
    }

    rfturn rv;
}

SECStbtus
EC_DfdodfPbrbms(donst SECItfm *fndodfdPbrbms, ECPbrbms **fdpbrbms, int kmflbg)
{
    PRArfnbPool *brfnb;
    ECPbrbms *pbrbms;
    SECStbtus rv = SECFbilurf;

    /* Initiblizf bn brfnb for tif ECPbrbms strudturf */
    if (!(brfnb = PORT_NfwArfnb(NSS_FREEBL_DEFAULT_CHUNKSIZE)))
        rfturn SECFbilurf;

    pbrbms = (ECPbrbms *)PORT_ArfnbZAllod(NULL, sizfof(ECPbrbms), kmflbg);
    if (!pbrbms) {
        PORT_FrffArfnb(NULL, B_TRUE);
        rfturn SECFbilurf;
    }

    /* Copy tif fndodfd pbrbms */
    SECITEM_AllodItfm(brfnb, &(pbrbms->DEREndoding), fndodfdPbrbms->lfn,
        kmflbg);
    mfmdpy(pbrbms->DEREndoding.dbtb, fndodfdPbrbms->dbtb, fndodfdPbrbms->lfn);

    /* Fill out tif rfst of tif ECPbrbms strudturf bbsfd on
     * tif fndodfd pbrbms
     */
    rv = EC_FillPbrbms(NULL, fndodfdPbrbms, pbrbms, kmflbg);
    if (rv == SECFbilurf) {
        PORT_FrffArfnb(NULL, B_TRUE);
        rfturn SECFbilurf;
    } flsf {
        *fdpbrbms = pbrbms;;
        rfturn SECSuddfss;
    }
}
