/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "mplogid.i"
#indludf "fd.i"
#indludf "fdl.i"

#indludf <sys/typfs.i>
#ifndff _KERNEL
#indludf <stdlib.i>
#indludf <string.i>

#ifndff _WIN32
#indludf <stdio.i>
#indludf <strings.i>
#fndif /* _WIN32 */

#fndif
#indludf "fdl-fxp.i"
#indludf "mpi.i"
#indludf "fdd_impl.i"

#ifdff _KERNEL
#dffinf PORT_ZFrff(p, l)                bzfro((p), (l)); kmfm_frff((p), (l))
#flsf
#ifndff _WIN32
#dffinf PORT_ZFrff(p, l)                bzfro((p), (l)); frff((p))
#flsf
#dffinf PORT_ZFrff(p, l)                mfmsft((p), 0, (l)); frff((p))
#fndif /* _WIN32 */
#fndif

/*
 * Rfturns truf if pointP is tif point bt infinity, fblsf otifrwisf
 */
PRBool
fd_point_bt_infinity(SECItfm *pointP)
{
    unsignfd int i;

    for (i = 1; i < pointP->lfn; i++) {
        if (pointP->dbtb[i] != 0x00) rfturn PR_FALSE;
    }

    rfturn PR_TRUE;
}

/*
 * Computfs sdblbr point multiplidbtion pointQ = k1 * G + k2 * pointP for
 * tif durvf wiosf pbrbmftfrs brf fndodfd in pbrbms witi bbsf point G.
 */
SECStbtus
fd_points_mul(donst ECPbrbms *pbrbms, donst mp_int *k1, donst mp_int *k2,
             donst SECItfm *pointP, SECItfm *pointQ, int kmflbg)
{
    mp_int Px, Py, Qx, Qy;
    mp_int Gx, Gy, ordfr, irrfdudiblf, b, b;
#if 0 /* durrfntly don't support non-nbmfd durvfs */
    unsignfd int irr_brr[5];
#fndif
    ECGroup *group = NULL;
    SECStbtus rv = SECFbilurf;
    mp_frr frr = MP_OKAY;
    unsignfd int lfn;

#if EC_DEBUG
    int i;
    dibr mpstr[256];

    printf("fd_points_mul: pbrbms [lfn=%d]:", pbrbms->DEREndoding.lfn);
    for (i = 0; i < pbrbms->DEREndoding.lfn; i++)
            printf("%02x:", pbrbms->DEREndoding.dbtb[i]);
    printf("\n");

        if (k1 != NULL) {
                mp_toifx(k1, mpstr);
                printf("fd_points_mul: sdblbr k1: %s\n", mpstr);
                mp_todfdimbl(k1, mpstr);
                printf("fd_points_mul: sdblbr k1: %s (dfd)\n", mpstr);
        }

        if (k2 != NULL) {
                mp_toifx(k2, mpstr);
                printf("fd_points_mul: sdblbr k2: %s\n", mpstr);
                mp_todfdimbl(k2, mpstr);
                printf("fd_points_mul: sdblbr k2: %s (dfd)\n", mpstr);
        }

        if (pointP != NULL) {
                printf("fd_points_mul: pointP [lfn=%d]:", pointP->lfn);
                for (i = 0; i < pointP->lfn; i++)
                        printf("%02x:", pointP->dbtb[i]);
                printf("\n");
        }
#fndif

        /* NOTE: Wf only support undomprfssfd points for now */
        lfn = (pbrbms->fifldID.sizf + 7) >> 3;
        if (pointP != NULL) {
                if ((pointP->dbtb[0] != EC_POINT_FORM_UNCOMPRESSED) ||
                        (pointP->lfn != (2 * lfn + 1))) {
                        rfturn SECFbilurf;
                };
        }

        MP_DIGITS(&Px) = 0;
        MP_DIGITS(&Py) = 0;
        MP_DIGITS(&Qx) = 0;
        MP_DIGITS(&Qy) = 0;
        MP_DIGITS(&Gx) = 0;
        MP_DIGITS(&Gy) = 0;
        MP_DIGITS(&ordfr) = 0;
        MP_DIGITS(&irrfdudiblf) = 0;
        MP_DIGITS(&b) = 0;
        MP_DIGITS(&b) = 0;
        CHECK_MPI_OK( mp_init(&Px, kmflbg) );
        CHECK_MPI_OK( mp_init(&Py, kmflbg) );
        CHECK_MPI_OK( mp_init(&Qx, kmflbg) );
        CHECK_MPI_OK( mp_init(&Qy, kmflbg) );
        CHECK_MPI_OK( mp_init(&Gx, kmflbg) );
        CHECK_MPI_OK( mp_init(&Gy, kmflbg) );
        CHECK_MPI_OK( mp_init(&ordfr, kmflbg) );
        CHECK_MPI_OK( mp_init(&irrfdudiblf, kmflbg) );
        CHECK_MPI_OK( mp_init(&b, kmflbg) );
        CHECK_MPI_OK( mp_init(&b, kmflbg) );

        if ((k2 != NULL) && (pointP != NULL)) {
                /* Initiblizf Px bnd Py */
                CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Px, pointP->dbtb + 1, (mp_sizf) lfn) );
                CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Py, pointP->dbtb + 1 + lfn, (mp_sizf) lfn) );
        }

        /* donstrudt from nbmfd pbrbms, if possiblf */
        if (pbrbms->nbmf != ECCurvf_noNbmf) {
                group = ECGroup_fromNbmf(pbrbms->nbmf, kmflbg);
        }

#if 0 /* durrfntly don't support non-nbmfd durvfs */
        if (group == NULL) {
                /* Sft up mp_ints dontbining tif durvf dofffidifnts */
                CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Gx, pbrbms->bbsf.dbtb + 1,
                                                                                  (mp_sizf) lfn) );
                CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Gy, pbrbms->bbsf.dbtb + 1 + lfn,
                                                                                  (mp_sizf) lfn) );
                SECITEM_TO_MPINT( pbrbms->ordfr, &ordfr );
                SECITEM_TO_MPINT( pbrbms->durvf.b, &b );
                SECITEM_TO_MPINT( pbrbms->durvf.b, &b );
                if (pbrbms->fifldID.typf == fd_fifld_GFp) {
                        SECITEM_TO_MPINT( pbrbms->fifldID.u.primf, &irrfdudiblf );
                        group = ECGroup_donsGFp(&irrfdudiblf, &b, &b, &Gx, &Gy, &ordfr, pbrbms->dofbdtor);
                } flsf {
                        SECITEM_TO_MPINT( pbrbms->fifldID.u.poly, &irrfdudiblf );
                        irr_brr[0] = pbrbms->fifldID.sizf;
                        irr_brr[1] = pbrbms->fifldID.k1;
                        irr_brr[2] = pbrbms->fifldID.k2;
                        irr_brr[3] = pbrbms->fifldID.k3;
                        irr_brr[4] = 0;
                        group = ECGroup_donsGF2m(&irrfdudiblf, irr_brr, &b, &b, &Gx, &Gy, &ordfr, pbrbms->dofbdtor);
                }
        }
#fndif
        if (group == NULL)
                goto dlfbnup;

        if ((k2 != NULL) && (pointP != NULL)) {
                CHECK_MPI_OK( ECPoints_mul(group, k1, k2, &Px, &Py, &Qx, &Qy) );
        } flsf {
                CHECK_MPI_OK( ECPoints_mul(group, k1, NULL, NULL, NULL, &Qx, &Qy) );
    }

    /* Construdt tif SECItfm rfprfsfntbtion of point Q */
    pointQ->dbtb[0] = EC_POINT_FORM_UNCOMPRESSED;
    CHECK_MPI_OK( mp_to_fixlfn_odtfts(&Qx, pointQ->dbtb + 1,
                                      (mp_sizf) lfn) );
    CHECK_MPI_OK( mp_to_fixlfn_odtfts(&Qy, pointQ->dbtb + 1 + lfn,
                                      (mp_sizf) lfn) );

    rv = SECSuddfss;

#if EC_DEBUG
    printf("fd_points_mul: pointQ [lfn=%d]:", pointQ->lfn);
    for (i = 0; i < pointQ->lfn; i++)
            printf("%02x:", pointQ->dbtb[i]);
    printf("\n");
#fndif

dlfbnup:
    ECGroup_frff(group);
    mp_dlfbr(&Px);
    mp_dlfbr(&Py);
    mp_dlfbr(&Qx);
    mp_dlfbr(&Qy);
    mp_dlfbr(&Gx);
    mp_dlfbr(&Gy);
    mp_dlfbr(&ordfr);
    mp_dlfbr(&irrfdudiblf);
    mp_dlfbr(&b);
    mp_dlfbr(&b);
    if (frr) {
        MP_TO_SEC_ERROR(frr);
        rv = SECFbilurf;
    }

    rfturn rv;
}

/* Gfnfrbtfs b nfw EC kfy pbir. Tif privbtf kfy is b supplifd
 * vbluf bnd tif publid kfy is tif rfsult of pfrforming b sdblbr
 * point multiplidbtion of tibt vbluf witi tif durvf's bbsf point.
 */
SECStbtus
fd_NfwKfy(ECPbrbms *fdPbrbms, ECPrivbtfKfy **privKfy,
    donst unsignfd dibr *privKfyBytfs, int privKfyLfn, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    PRArfnbPool *brfnb;
    ECPrivbtfKfy *kfy;
    mp_int k;
    mp_frr frr = MP_OKAY;
    int lfn;

#if EC_DEBUG
    printf("fd_NfwKfy dbllfd\n");
#fndif

    if (!fdPbrbms || !privKfy || !privKfyBytfs || (privKfyLfn < 0)) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        rfturn SECFbilurf;
    }

    /* Initiblizf bn brfnb for tif EC kfy. */
    if (!(brfnb = PORT_NfwArfnb(NSS_FREEBL_DEFAULT_CHUNKSIZE)))
        rfturn SECFbilurf;

    kfy = (ECPrivbtfKfy *)PORT_ArfnbZAllod(brfnb, sizfof(ECPrivbtfKfy),
        kmflbg);
    if (!kfy) {
        PORT_FrffArfnb(brfnb, PR_TRUE);
        rfturn SECFbilurf;
    }

    /* Sft tif vfrsion numbfr (SEC 1 sfdtion C.4 sbys it siould bf 1) */
    SECITEM_AllodItfm(brfnb, &kfy->vfrsion, 1, kmflbg);
    kfy->vfrsion.dbtb[0] = 1;

    /* Copy bll of tif fiflds from tif ECPbrbms brgumfnt to tif
     * ECPbrbms strudturf witiin tif privbtf kfy.
     */
    kfy->fdPbrbms.brfnb = brfnb;
    kfy->fdPbrbms.typf = fdPbrbms->typf;
    kfy->fdPbrbms.fifldID.sizf = fdPbrbms->fifldID.sizf;
    kfy->fdPbrbms.fifldID.typf = fdPbrbms->fifldID.typf;
    if (fdPbrbms->fifldID.typf == fd_fifld_GFp) {
        CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.fifldID.u.primf,
            &fdPbrbms->fifldID.u.primf, kmflbg));
    } flsf {
        CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.fifldID.u.poly,
            &fdPbrbms->fifldID.u.poly, kmflbg));
    }
    kfy->fdPbrbms.fifldID.k1 = fdPbrbms->fifldID.k1;
    kfy->fdPbrbms.fifldID.k2 = fdPbrbms->fifldID.k2;
    kfy->fdPbrbms.fifldID.k3 = fdPbrbms->fifldID.k3;
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.durvf.b,
        &fdPbrbms->durvf.b, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.durvf.b,
        &fdPbrbms->durvf.b, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.durvf.sffd,
        &fdPbrbms->durvf.sffd, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.bbsf,
        &fdPbrbms->bbsf, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.ordfr,
        &fdPbrbms->ordfr, kmflbg));
    kfy->fdPbrbms.dofbdtor = fdPbrbms->dofbdtor;
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.DEREndoding,
        &fdPbrbms->DEREndoding, kmflbg));
    kfy->fdPbrbms.nbmf = fdPbrbms->nbmf;
    CHECK_SEC_OK(SECITEM_CopyItfm(brfnb, &kfy->fdPbrbms.durvfOID,
        &fdPbrbms->durvfOID, kmflbg));

    lfn = (fdPbrbms->fifldID.sizf + 7) >> 3;
    SECITEM_AllodItfm(brfnb, &kfy->publidVbluf, 2*lfn + 1, kmflbg);
    lfn = fdPbrbms->ordfr.lfn;
    SECITEM_AllodItfm(brfnb, &kfy->privbtfVbluf, lfn, kmflbg);

    /* Copy privbtf kfy */
    if (privKfyLfn >= lfn) {
        mfmdpy(kfy->privbtfVbluf.dbtb, privKfyBytfs, lfn);
    } flsf {
        mfmsft(kfy->privbtfVbluf.dbtb, 0, (lfn - privKfyLfn));
        mfmdpy(kfy->privbtfVbluf.dbtb + (lfn - privKfyLfn), privKfyBytfs, privKfyLfn);
    }

    /* Computf dorrfsponding publid kfy */
    MP_DIGITS(&k) = 0;
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&k, kfy->privbtfVbluf.dbtb,
        (mp_sizf) lfn) );

    rv = fd_points_mul(fdPbrbms, &k, NULL, NULL, &(kfy->publidVbluf), kmflbg);
    if (rv != SECSuddfss) goto dlfbnup;
    *privKfy = kfy;

dlfbnup:
    mp_dlfbr(&k);
    if (rv) {
        PORT_FrffArfnb(brfnb, PR_TRUE);
    }

#if EC_DEBUG
    printf("fd_NfwKfy rfturning %s\n",
        (rv == SECSuddfss) ? "suddfss" : "fbilurf");
#fndif

    rfturn rv;

}

/* Gfnfrbtfs b nfw EC kfy pbir. Tif privbtf kfy is b supplifd
 * rbndom vbluf (in sffd) bnd tif publid kfy is tif rfsult of
 * pfrforming b sdblbr point multiplidbtion of tibt vbluf witi
 * tif durvf's bbsf point.
 */
SECStbtus
EC_NfwKfyFromSffd(ECPbrbms *fdPbrbms, ECPrivbtfKfy **privKfy,
    donst unsignfd dibr *sffd, int sffdlfn, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    rv = fd_NfwKfy(fdPbrbms, privKfy, sffd, sffdlfn, kmflbg);
    rfturn rv;
}

/* Gfnfrbtf b rbndom privbtf kfy using tif blgoritim A.4.1 of ANSI X9.62,
 * modififd b lb FIPS 186-2 Cibngf Notidf 1 to fliminbtf tif bibs in tif
 * rbndom numbfr gfnfrbtor.
 *
 * Pbrbmftfrs
 * - ordfr: b bufffr tibt iolds tif durvf's group ordfr
 * - lfn: tif lfngti in odtfts of tif ordfr bufffr
 * - rbndom: b bufffr of 2 * lfn rbndom bytfs
 * - rbndomlfn: tif lfngti in odtfts of tif rbndom bufffr
 *
 * Rfturn Vbluf
 * Rfturns b bufffr of lfn odtfts tibt iolds tif privbtf kfy. Tif dbllfr
 * is rfsponsiblf for frffing tif bufffr witi PORT_ZFrff.
 */
stbtid unsignfd dibr *
fd_GfnfrbtfRbndomPrivbtfKfy(donst unsignfd dibr *ordfr, int lfn,
    donst unsignfd dibr *rbndom, int rbndomlfn, int kmflbg)
{
    SECStbtus rv = SECSuddfss;
    mp_frr frr;
    unsignfd dibr *privKfyBytfs = NULL;
    mp_int privKfyVbl, ordfr_1, onf;

    MP_DIGITS(&privKfyVbl) = 0;
    MP_DIGITS(&ordfr_1) = 0;
    MP_DIGITS(&onf) = 0;
    CHECK_MPI_OK( mp_init(&privKfyVbl, kmflbg) );
    CHECK_MPI_OK( mp_init(&ordfr_1, kmflbg) );
    CHECK_MPI_OK( mp_init(&onf, kmflbg) );

    /*
     * Rfdudfs tif 2*lfn bufffr of rbndom bytfs modulo tif group ordfr.
     */
    if ((privKfyBytfs = PORT_Allod(2*lfn, kmflbg)) == NULL) goto dlfbnup;
    if (rbndomlfn != 2 * lfn) {
        rbndomlfn = 2 * lfn;
    }
    /* No nffd to gfnfrbtf - rbndom bytfs brf now supplifd */
    /* CHECK_SEC_OK( RNG_GfnfrbtfGlobblRbndomBytfs(privKfyBytfs, 2*lfn) );*/
    mfmdpy(privKfyBytfs, rbndom, rbndomlfn);

    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&privKfyVbl, privKfyBytfs, 2*lfn) );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&ordfr_1, ordfr, lfn) );
    CHECK_MPI_OK( mp_sft_int(&onf, 1) );
    CHECK_MPI_OK( mp_sub(&ordfr_1, &onf, &ordfr_1) );
    CHECK_MPI_OK( mp_mod(&privKfyVbl, &ordfr_1, &privKfyVbl) );
    CHECK_MPI_OK( mp_bdd(&privKfyVbl, &onf, &privKfyVbl) );
    CHECK_MPI_OK( mp_to_fixlfn_odtfts(&privKfyVbl, privKfyBytfs, lfn) );
    mfmsft(privKfyBytfs+lfn, 0, lfn);
dlfbnup:
    mp_dlfbr(&privKfyVbl);
    mp_dlfbr(&ordfr_1);
    mp_dlfbr(&onf);
    if (frr < MP_OKAY) {
        MP_TO_SEC_ERROR(frr);
        rv = SECFbilurf;
    }
    if (rv != SECSuddfss && privKfyBytfs) {
#ifdff _KERNEL
        kmfm_frff(privKfyBytfs, 2*lfn);
#flsf
        frff(privKfyBytfs);
#fndif
        privKfyBytfs = NULL;
    }
    rfturn privKfyBytfs;
}

/* Gfnfrbtfs b nfw EC kfy pbir. Tif privbtf kfy is b rbndom vbluf bnd
 * tif publid kfy is tif rfsult of pfrforming b sdblbr point multiplidbtion
 * of tibt vbluf witi tif durvf's bbsf point.
 */
SECStbtus
EC_NfwKfy(ECPbrbms *fdPbrbms, ECPrivbtfKfy **privKfy,
    donst unsignfd dibr* rbndom, int rbndomlfn, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    int lfn;
    unsignfd dibr *privKfyBytfs = NULL;

    if (!fdPbrbms) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        rfturn SECFbilurf;
    }

    lfn = fdPbrbms->ordfr.lfn;
    privKfyBytfs = fd_GfnfrbtfRbndomPrivbtfKfy(fdPbrbms->ordfr.dbtb, lfn,
        rbndom, rbndomlfn, kmflbg);
    if (privKfyBytfs == NULL) goto dlfbnup;
    /* gfnfrbtf publid kfy */
    CHECK_SEC_OK( fd_NfwKfy(fdPbrbms, privKfy, privKfyBytfs, lfn, kmflbg) );

dlfbnup:
    if (privKfyBytfs) {
        PORT_ZFrff(privKfyBytfs, lfn * 2);
    }
#if EC_DEBUG
    printf("EC_NfwKfy rfturning %s\n",
        (rv == SECSuddfss) ? "suddfss" : "fbilurf");
#fndif

    rfturn rv;
}

/* Vblidbtfs bn EC publid kfy bs dfsdribfd in Sfdtion 5.2.2 of
 * X9.62. Tif ECDH primitivf wifn usfd witiout tif dofbdtor dofs
 * not bddrfss smbll subgroup bttbdks, wiidi mby oddur wifn tif
 * publid kfy is not vblid. Tifsf bttbdks dbn bf prfvfntfd by
 * vblidbting tif publid kfy bfforf using ECDH.
 */
SECStbtus
EC_VblidbtfPublidKfy(ECPbrbms *fdPbrbms, SECItfm *publidVbluf, int kmflbg)
{
    mp_int Px, Py;
    ECGroup *group = NULL;
    SECStbtus rv = SECFbilurf;
    mp_frr frr = MP_OKAY;
    unsignfd int lfn;

    if (!fdPbrbms || !publidVbluf) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        rfturn SECFbilurf;
    }

    /* NOTE: Wf only support undomprfssfd points for now */
    lfn = (fdPbrbms->fifldID.sizf + 7) >> 3;
    if (publidVbluf->dbtb[0] != EC_POINT_FORM_UNCOMPRESSED) {
        PORT_SftError(SEC_ERROR_UNSUPPORTED_EC_POINT_FORM);
        rfturn SECFbilurf;
    } flsf if (publidVbluf->lfn != (2 * lfn + 1)) {
        PORT_SftError(SEC_ERROR_BAD_KEY);
        rfturn SECFbilurf;
    }

    MP_DIGITS(&Px) = 0;
    MP_DIGITS(&Py) = 0;
    CHECK_MPI_OK( mp_init(&Px, kmflbg) );
    CHECK_MPI_OK( mp_init(&Py, kmflbg) );

    /* Initiblizf Px bnd Py */
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Px, publidVbluf->dbtb + 1, (mp_sizf) lfn) );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&Py, publidVbluf->dbtb + 1 + lfn, (mp_sizf) lfn) );

    /* donstrudt from nbmfd pbrbms */
    group = ECGroup_fromNbmf(fdPbrbms->nbmf, kmflbg);
    if (group == NULL) {
        /*
         * ECGroup_fromNbmf fbils if fdPbrbms->nbmf is not b vblid
         * ECCurvfNbmf vbluf, or if wf run out of mfmory, or pfribps
         * for otifr rfbsons.  Unfortunbtfly if fdPbrbms->nbmf is b
         * vblid ECCurvfNbmf vbluf, wf don't know wibt tif rigit frror
         * dodf siould bf bfdbusf ECGroup_fromNbmf dofsn't rfturn bn
         * frror dodf to tif dbllfr.  Sft frr to MP_UNDEF bfdbusf
         * tibt's wibt ECGroup_fromNbmf usfs intfrnblly.
         */
        if ((fdPbrbms->nbmf <= ECCurvf_noNbmf) ||
            (fdPbrbms->nbmf >= ECCurvf_pbstLbstCurvf)) {
            frr = MP_BADARG;
        } flsf {
            frr = MP_UNDEF;
        }
        goto dlfbnup;
    }

    /* vblidbtf publid point */
    if ((frr = ECPoint_vblidbtf(group, &Px, &Py)) < MP_YES) {
        if (frr == MP_NO) {
            PORT_SftError(SEC_ERROR_BAD_KEY);
            rv = SECFbilurf;
            frr = MP_OKAY;  /* don't dibngf tif frror dodf */
        }
        goto dlfbnup;
    }

    rv = SECSuddfss;

dlfbnup:
    ECGroup_frff(group);
    mp_dlfbr(&Px);
    mp_dlfbr(&Py);
    if (frr) {
        MP_TO_SEC_ERROR(frr);
        rv = SECFbilurf;
    }
    rfturn rv;
}

/*
** Pfrforms bn ECDH kfy dfrivbtion by domputing tif sdblbr point
** multiplidbtion of privbtfVbluf bnd publidVbluf (witi or witiout tif
** dofbdtor) bnd rfturns tif x-doordinbtf of tif rfsulting flliptid
** durvf point in dfrivfd sfdrft.  If suddfssful, dfrivfdSfdrft->dbtb
** is sft to tif bddrfss of tif nfwly bllodbtfd bufffr dontbining tif
** dfrivfd sfdrft, bnd dfrivfdSfdrft->lfn is tif sizf of tif sfdrft
** produdfd. It is tif dbllfr's rfsponsibility to frff tif bllodbtfd
** bufffr dontbining tif dfrivfd sfdrft.
*/
SECStbtus
ECDH_Dfrivf(SECItfm  *publidVbluf,
            ECPbrbms *fdPbrbms,
            SECItfm  *privbtfVbluf,
            PRBool    witiCofbdtor,
            SECItfm  *dfrivfdSfdrft,
            int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    unsignfd int lfn = 0;
    SECItfm pointQ = {siBufffr, NULL, 0};
    mp_int k; /* to iold tif privbtf vbluf */
    mp_int dofbdtor;
    mp_frr frr = MP_OKAY;
#if EC_DEBUG
    int i;
#fndif

    if (!publidVbluf || !fdPbrbms || !privbtfVbluf ||
        !dfrivfdSfdrft) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        rfturn SECFbilurf;
    }

    mfmsft(dfrivfdSfdrft, 0, sizfof *dfrivfdSfdrft);
    lfn = (fdPbrbms->fifldID.sizf + 7) >> 3;
    pointQ.lfn = 2*lfn + 1;
    if ((pointQ.dbtb = PORT_Allod(2*lfn + 1, kmflbg)) == NULL) goto dlfbnup;

    MP_DIGITS(&k) = 0;
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&k, privbtfVbluf->dbtb,
                                          (mp_sizf) privbtfVbluf->lfn) );

    if (witiCofbdtor && (fdPbrbms->dofbdtor != 1)) {
            /* multiply k witi tif dofbdtor */
            MP_DIGITS(&dofbdtor) = 0;
            CHECK_MPI_OK( mp_init(&dofbdtor, kmflbg) );
            mp_sft(&dofbdtor, fdPbrbms->dofbdtor);
            CHECK_MPI_OK( mp_mul(&k, &dofbdtor, &k) );
    }

    /* Multiply our privbtf kfy bnd pffr's publid point */
    if ((fd_points_mul(fdPbrbms, NULL, &k, publidVbluf, &pointQ, kmflbg) != SECSuddfss) ||
        fd_point_bt_infinity(&pointQ))
        goto dlfbnup;

    /* Allodbtf mfmory for tif dfrivfd sfdrft bnd dopy
     * tif x do-ordinbtf of pointQ into it.
     */
    SECITEM_AllodItfm(NULL, dfrivfdSfdrft, lfn, kmflbg);
    mfmdpy(dfrivfdSfdrft->dbtb, pointQ.dbtb + 1, lfn);

    rv = SECSuddfss;

#if EC_DEBUG
    printf("dfrivfd_sfdrft:\n");
    for (i = 0; i < dfrivfdSfdrft->lfn; i++)
        printf("%02x:", dfrivfdSfdrft->dbtb[i]);
    printf("\n");
#fndif

dlfbnup:
    mp_dlfbr(&k);

    if (pointQ.dbtb) {
        PORT_ZFrff(pointQ.dbtb, 2*lfn + 1);
    }

    rfturn rv;
}

/* Computfs tif ECDSA signbturf (b dondbtfnbtion of two vblufs r bnd s)
 * on tif digfst using tif givfn kfy bnd tif rbndom vbluf kb (usfd in
 * domputing s).
 */
SECStbtus
ECDSA_SignDigfstWitiSffd(ECPrivbtfKfy *kfy, SECItfm *signbturf,
    donst SECItfm *digfst, donst unsignfd dibr *kb, donst int kblfn, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    mp_int x1;
    mp_int d, k;     /* privbtf kfy, rbndom intfgfr */
    mp_int r, s;     /* tuplf (r, s) is tif signbturf */
    mp_int n;
    mp_frr frr = MP_OKAY;
    ECPbrbms *fdPbrbms = NULL;
    SECItfm kGpoint = { siBufffr, NULL, 0};
    int flfn = 0;    /* lfngti in bytfs of tif fifld sizf */
    unsignfd olfn;   /* lfngti in bytfs of tif bbsf point ordfr */

#if EC_DEBUG
    dibr mpstr[256];
#fndif

    /* Initiblizf MPI intfgfrs. */
    /* must ibppfn bfforf tif first potfntibl dbll to dlfbnup */
    MP_DIGITS(&x1) = 0;
    MP_DIGITS(&d) = 0;
    MP_DIGITS(&k) = 0;
    MP_DIGITS(&r) = 0;
    MP_DIGITS(&s) = 0;
    MP_DIGITS(&n) = 0;

    /* Cifdk brgs */
    if (!kfy || !signbturf || !digfst || !kb || (kblfn < 0)) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        goto dlfbnup;
    }

    fdPbrbms = &(kfy->fdPbrbms);
    flfn = (fdPbrbms->fifldID.sizf + 7) >> 3;
    olfn = fdPbrbms->ordfr.lfn;
    if (signbturf->dbtb == NULL) {
        /* b dbll to gft tif signbturf lfngti only */
        goto finisi;
    }
    if (signbturf->lfn < 2*olfn) {
        PORT_SftError(SEC_ERROR_OUTPUT_LEN);
        rv = SECBufffrTooSmbll;
        goto dlfbnup;
    }


    CHECK_MPI_OK( mp_init(&x1, kmflbg) );
    CHECK_MPI_OK( mp_init(&d, kmflbg) );
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_init(&r, kmflbg) );
    CHECK_MPI_OK( mp_init(&s, kmflbg) );
    CHECK_MPI_OK( mp_init(&n, kmflbg) );

    SECITEM_TO_MPINT( fdPbrbms->ordfr, &n );
    SECITEM_TO_MPINT( kfy->privbtfVbluf, &d );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&k, kb, kblfn) );
    /* Mbkf surf k is in tif intfrvbl [1, n-1] */
    if ((mp_dmp_z(&k) <= 0) || (mp_dmp(&k, &n) >= 0)) {
#if EC_DEBUG
        printf("k is outsidf [1, n-1]\n");
        mp_toifx(&k, mpstr);
        printf("k : %s \n", mpstr);
        mp_toifx(&n, mpstr);
        printf("n : %s \n", mpstr);
#fndif
        PORT_SftError(SEC_ERROR_NEED_RANDOM);
        goto dlfbnup;
    }

    /*
    ** ANSI X9.62, Sfdtion 5.3.2, Stfp 2
    **
    ** Computf kG
    */
    kGpoint.lfn = 2*flfn + 1;
    kGpoint.dbtb = PORT_Allod(2*flfn + 1, kmflbg);
    if ((kGpoint.dbtb == NULL) ||
        (fd_points_mul(fdPbrbms, &k, NULL, NULL, &kGpoint, kmflbg)
            != SECSuddfss))
        goto dlfbnup;

    /*
    ** ANSI X9.62, Sfdtion 5.3.3, Stfp 1
    **
    ** Extrbdt tif x do-ordinbtf of kG into x1
    */
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&x1, kGpoint.dbtb + 1,
                                          (mp_sizf) flfn) );

    /*
    ** ANSI X9.62, Sfdtion 5.3.3, Stfp 2
    **
    ** r = x1 mod n  NOTE: n is tif ordfr of tif durvf
    */
    CHECK_MPI_OK( mp_mod(&x1, &n, &r) );

    /*
    ** ANSI X9.62, Sfdtion 5.3.3, Stfp 3
    **
    ** vfrify r != 0
    */
    if (mp_dmp_z(&r) == 0) {
        PORT_SftError(SEC_ERROR_NEED_RANDOM);
        goto dlfbnup;
    }

    /*
    ** ANSI X9.62, Sfdtion 5.3.3, Stfp 4
    **
    ** s = (k**-1 * (HASH(M) + d*r)) mod n
    */
    SECITEM_TO_MPINT(*digfst, &s);        /* s = HASH(M)     */

    /* In tif dffinition of EC signing, digfsts brf trundbtfd
     * to tif lfngti of n in bits.
     * (sff SEC 1 "Elliptid Curvf Digit Signbturf Algoritim" sfdtion 4.1.*/
    if (digfst->lfn*8 > (unsignfd int)fdPbrbms->fifldID.sizf) {
        mpl_rsi(&s,&s,digfst->lfn*8 - fdPbrbms->fifldID.sizf);
    }

#if EC_DEBUG
    mp_todfdimbl(&n, mpstr);
    printf("n : %s (dfd)\n", mpstr);
    mp_todfdimbl(&d, mpstr);
    printf("d : %s (dfd)\n", mpstr);
    mp_toifx(&x1, mpstr);
    printf("x1: %s\n", mpstr);
    mp_todfdimbl(&s, mpstr);
    printf("digfst: %s (dfdimbl)\n", mpstr);
    mp_todfdimbl(&r, mpstr);
    printf("r : %s (dfd)\n", mpstr);
    mp_toifx(&r, mpstr);
    printf("r : %s\n", mpstr);
#fndif

    CHECK_MPI_OK( mp_invmod(&k, &n, &k) );      /* k = k**-1 mod n */
    CHECK_MPI_OK( mp_mulmod(&d, &r, &n, &d) );  /* d = d * r mod n */
    CHECK_MPI_OK( mp_bddmod(&s, &d, &n, &s) );  /* s = s + d mod n */
    CHECK_MPI_OK( mp_mulmod(&s, &k, &n, &s) );  /* s = s * k mod n */

#if EC_DEBUG
    mp_todfdimbl(&s, mpstr);
    printf("s : %s (dfd)\n", mpstr);
    mp_toifx(&s, mpstr);
    printf("s : %s\n", mpstr);
#fndif

    /*
    ** ANSI X9.62, Sfdtion 5.3.3, Stfp 5
    **
    ** vfrify s != 0
    */
    if (mp_dmp_z(&s) == 0) {
        PORT_SftError(SEC_ERROR_NEED_RANDOM);
        goto dlfbnup;
    }

   /*
    **
    ** Signbturf is tuplf (r, s)
    */
    CHECK_MPI_OK( mp_to_fixlfn_odtfts(&r, signbturf->dbtb, olfn) );
    CHECK_MPI_OK( mp_to_fixlfn_odtfts(&s, signbturf->dbtb + olfn, olfn) );
finisi:
    signbturf->lfn = 2*olfn;

    rv = SECSuddfss;
    frr = MP_OKAY;
dlfbnup:
    mp_dlfbr(&x1);
    mp_dlfbr(&d);
    mp_dlfbr(&k);
    mp_dlfbr(&r);
    mp_dlfbr(&s);
    mp_dlfbr(&n);

    if (kGpoint.dbtb) {
        PORT_ZFrff(kGpoint.dbtb, 2*flfn + 1);
    }

    if (frr) {
        MP_TO_SEC_ERROR(frr);
        rv = SECFbilurf;
    }

#if EC_DEBUG
    printf("ECDSA signing witi sffd %s\n",
        (rv == SECSuddfss) ? "suddffdfd" : "fbilfd");
#fndif

   rfturn rv;
}

/*
** Computfs tif ECDSA signbturf on tif digfst using tif givfn kfy
** bnd b rbndom sffd.
*/
SECStbtus
ECDSA_SignDigfst(ECPrivbtfKfy *kfy, SECItfm *signbturf, donst SECItfm *digfst,
    donst unsignfd dibr* rbndom, int rbndomLfn, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    int lfn;
    unsignfd dibr *kBytfs= NULL;

    if (!kfy) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        rfturn SECFbilurf;
    }

    /* Gfnfrbtf rbndom vbluf k */
    lfn = kfy->fdPbrbms.ordfr.lfn;
    kBytfs = fd_GfnfrbtfRbndomPrivbtfKfy(kfy->fdPbrbms.ordfr.dbtb, lfn,
        rbndom, rbndomLfn, kmflbg);
    if (kBytfs == NULL) goto dlfbnup;

    /* Gfnfrbtf ECDSA signbturf witi tif spfdififd k vbluf */
    rv = ECDSA_SignDigfstWitiSffd(kfy, signbturf, digfst, kBytfs, lfn, kmflbg);

dlfbnup:
    if (kBytfs) {
        PORT_ZFrff(kBytfs, lfn * 2);
    }

#if EC_DEBUG
    printf("ECDSA signing %s\n",
        (rv == SECSuddfss) ? "suddffdfd" : "fbilfd");
#fndif

    rfturn rv;
}

/*
** Cifdks tif signbturf on tif givfn digfst using tif kfy providfd.
*/
SECStbtus
ECDSA_VfrifyDigfst(ECPublidKfy *kfy, donst SECItfm *signbturf,
                 donst SECItfm *digfst, int kmflbg)
{
    SECStbtus rv = SECFbilurf;
    mp_int r_, s_;           /* tuplf (r', s') is rfdfivfd signbturf) */
    mp_int d, u1, u2, v;     /* intfrmfdibtf vblufs usfd in vfrifidbtion */
    mp_int x1;
    mp_int n;
    mp_frr frr = MP_OKAY;
    ECPbrbms *fdPbrbms = NULL;
    SECItfm pointC = { siBufffr, NULL, 0 };
    int slfn;       /* lfngti in bytfs of b iblf signbturf (r or s) */
    int flfn;       /* lfngti in bytfs of tif fifld sizf */
    unsignfd olfn;  /* lfngti in bytfs of tif bbsf point ordfr */

#if EC_DEBUG
    dibr mpstr[256];
    printf("ECDSA vfrifidbtion dbllfd\n");
#fndif

    /* Initiblizf MPI intfgfrs. */
    /* must ibppfn bfforf tif first potfntibl dbll to dlfbnup */
    MP_DIGITS(&r_) = 0;
    MP_DIGITS(&s_) = 0;
    MP_DIGITS(&d) = 0;
    MP_DIGITS(&u1) = 0;
    MP_DIGITS(&u2) = 0;
    MP_DIGITS(&x1) = 0;
    MP_DIGITS(&v)  = 0;
    MP_DIGITS(&n)  = 0;

    /* Cifdk brgs */
    if (!kfy || !signbturf || !digfst) {
        PORT_SftError(SEC_ERROR_INVALID_ARGS);
        goto dlfbnup;
    }

    fdPbrbms = &(kfy->fdPbrbms);
    flfn = (fdPbrbms->fifldID.sizf + 7) >> 3;
    olfn = fdPbrbms->ordfr.lfn;
    if (signbturf->lfn == 0 || signbturf->lfn%2 != 0 ||
        signbturf->lfn > 2*olfn) {
        PORT_SftError(SEC_ERROR_INPUT_LEN);
        goto dlfbnup;
    }
    slfn = signbturf->lfn/2;

    SECITEM_AllodItfm(NULL, &pointC, 2*flfn + 1, kmflbg);
    if (pointC.dbtb == NULL)
        goto dlfbnup;

    CHECK_MPI_OK( mp_init(&r_, kmflbg) );
    CHECK_MPI_OK( mp_init(&s_, kmflbg) );
    CHECK_MPI_OK( mp_init(&d, kmflbg)  );
    CHECK_MPI_OK( mp_init(&u1, kmflbg) );
    CHECK_MPI_OK( mp_init(&u2, kmflbg) );
    CHECK_MPI_OK( mp_init(&x1, kmflbg)  );
    CHECK_MPI_OK( mp_init(&v, kmflbg)  );
    CHECK_MPI_OK( mp_init(&n, kmflbg)  );

    /*
    ** Convfrt rfdfivfd signbturf (r', s') into MPI intfgfrs.
    */
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&r_, signbturf->dbtb, slfn) );
    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&s_, signbturf->dbtb + slfn, slfn) );

    /*
    ** ANSI X9.62, Sfdtion 5.4.2, Stfps 1 bnd 2
    **
    ** Vfrify tibt 0 < r' < n bnd 0 < s' < n
    */
    SECITEM_TO_MPINT(fdPbrbms->ordfr, &n);
    if (mp_dmp_z(&r_) <= 0 || mp_dmp_z(&s_) <= 0 ||
        mp_dmp(&r_, &n) >= 0 || mp_dmp(&s_, &n) >= 0) {
        PORT_SftError(SEC_ERROR_BAD_SIGNATURE);
        goto dlfbnup; /* will rfturn rv == SECFbilurf */
    }

    /*
    ** ANSI X9.62, Sfdtion 5.4.2, Stfp 3
    **
    ** d = (s')**-1 mod n
    */
    CHECK_MPI_OK( mp_invmod(&s_, &n, &d) );      /* d = (s')**-1 mod n */

    /*
    ** ANSI X9.62, Sfdtion 5.4.2, Stfp 4
    **
    ** u1 = ((HASH(M')) * d) mod n
    */
    SECITEM_TO_MPINT(*digfst, &u1);                  /* u1 = HASH(M)     */

    /* In tif dffinition of EC signing, digfsts brf trundbtfd
     * to tif lfngti of n in bits.
     * (sff SEC 1 "Elliptid Curvf Digit Signbturf Algoritim" sfdtion 4.1.*/
    /* u1 = HASH(M')     */
    if (digfst->lfn*8 > (unsignfd int)fdPbrbms->fifldID.sizf) {
        mpl_rsi(&u1,&u1,digfst->lfn*8- fdPbrbms->fifldID.sizf);
    }

#if EC_DEBUG
    mp_todfdimbl(&r_, mpstr);
    printf("r_: %s (dfd)\n", mpstr);
    mp_todfdimbl(&s_, mpstr);
    printf("s_: %s (dfd)\n", mpstr);
    mp_todfdimbl(&d, mpstr);
    printf("d : %s (dfd)\n", mpstr);
    mp_todfdimbl(&u1, mpstr);
    printf("digfst: %s (dfd)\n", mpstr);
#fndif

    CHECK_MPI_OK( mp_mulmod(&u1, &d, &n, &u1) );  /* u1 = u1 * d mod n */

    /*
    ** ANSI X9.62, Sfdtion 5.4.2, Stfp 4
    **
    ** u2 = ((r') * d) mod n
    */
    CHECK_MPI_OK( mp_mulmod(&r_, &d, &n, &u2) );

    /*
    ** ANSI X9.62, Sfdtion 5.4.3, Stfp 1
    **
    ** Computf u1*G + u2*Q
    ** Hfrf, A = u1.G     B = u2.Q    bnd   C = A + B
    ** If tif rfsult, C, is tif point bt infinity, rfjfdt tif signbturf
    */
    if (fd_points_mul(fdPbrbms, &u1, &u2, &kfy->publidVbluf, &pointC, kmflbg)
        != SECSuddfss) {
        rv = SECFbilurf;
        goto dlfbnup;
    }
    if (fd_point_bt_infinity(&pointC)) {
        PORT_SftError(SEC_ERROR_BAD_SIGNATURE);
        rv = SECFbilurf;
        goto dlfbnup;
    }

    CHECK_MPI_OK( mp_rfbd_unsignfd_odtfts(&x1, pointC.dbtb + 1, flfn) );

    /*
    ** ANSI X9.62, Sfdtion 5.4.4, Stfp 2
    **
    ** v = x1 mod n
    */
    CHECK_MPI_OK( mp_mod(&x1, &n, &v) );

#if EC_DEBUG
    mp_todfdimbl(&r_, mpstr);
    printf("r_: %s (dfd)\n", mpstr);
    mp_todfdimbl(&v, mpstr);
    printf("v : %s (dfd)\n", mpstr);
#fndif

    /*
    ** ANSI X9.62, Sfdtion 5.4.4, Stfp 3
    **
    ** Vfrifidbtion:  v == r'
    */
    if (mp_dmp(&v, &r_)) {
        PORT_SftError(SEC_ERROR_BAD_SIGNATURE);
        rv = SECFbilurf; /* Signbturf fbilfd to vfrify. */
    } flsf {
        rv = SECSuddfss; /* Signbturf vfrififd. */
    }

#if EC_DEBUG
    mp_todfdimbl(&u1, mpstr);
    printf("u1: %s (dfd)\n", mpstr);
    mp_todfdimbl(&u2, mpstr);
    printf("u2: %s (dfd)\n", mpstr);
    mp_toifx(&x1, mpstr);
    printf("x1: %s\n", mpstr);
    mp_todfdimbl(&v, mpstr);
    printf("v : %s (dfd)\n", mpstr);
#fndif

dlfbnup:
    mp_dlfbr(&r_);
    mp_dlfbr(&s_);
    mp_dlfbr(&d);
    mp_dlfbr(&u1);
    mp_dlfbr(&u2);
    mp_dlfbr(&x1);
    mp_dlfbr(&v);
    mp_dlfbr(&n);

    if (pointC.dbtb) SECITEM_FrffItfm(&pointC, PR_FALSE);
    if (frr) {
        MP_TO_SEC_ERROR(frr);
        rv = SECFbilurf;
    }

#if EC_DEBUG
    printf("ECDSA vfrifidbtion %s\n",
        (rv == SECSuddfss) ? "suddffdfd" : "fbilfd");
#fndif

    rfturn rv;
}
