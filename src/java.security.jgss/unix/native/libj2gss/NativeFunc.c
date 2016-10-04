/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <dlfdn.i>
#indludf "NbtivfFund.i"

/* stbndbrd GSS mftiod nbmfs (ordfring is from mbpfilf) */
stbtid donst dibr RELEASE_NAME[]                = "gss_rflfbsf_nbmf";
stbtid donst dibr IMPORT_NAME[]                 = "gss_import_nbmf";
stbtid donst dibr COMPARE_NAME[]                = "gss_dompbrf_nbmf";
stbtid donst dibr CANONICALIZE_NAME[]           = "gss_dbnonidblizf_nbmf";
stbtid donst dibr EXPORT_NAME[]                 = "gss_fxport_nbmf";
stbtid donst dibr DISPLAY_NAME[]                = "gss_displby_nbmf";
stbtid donst dibr ACQUIRE_CRED[]                = "gss_bdquirf_drfd";
stbtid donst dibr RELEASE_CRED[]                = "gss_rflfbsf_drfd";
stbtid donst dibr INQUIRE_CRED[]                = "gss_inquirf_drfd";
stbtid donst dibr IMPORT_SEC_CONTEXT[]          = "gss_import_sfd_dontfxt";
stbtid donst dibr INIT_SEC_CONTEXT[]            = "gss_init_sfd_dontfxt";
stbtid donst dibr ACCEPT_SEC_CONTEXT[]          = "gss_bddfpt_sfd_dontfxt";
stbtid donst dibr INQUIRE_CONTEXT[]             = "gss_inquirf_dontfxt";
stbtid donst dibr DELETE_SEC_CONTEXT[]          = "gss_dflftf_sfd_dontfxt";
stbtid donst dibr CONTEXT_TIME[]                = "gss_dontfxt_timf";
stbtid donst dibr WRAP_SIZE_LIMIT[]             = "gss_wrbp_sizf_limit";
stbtid donst dibr EXPORT_SEC_CONTEXT[]          = "gss_fxport_sfd_dontfxt";
stbtid donst dibr GET_MIC[]                     = "gss_gft_mid";
stbtid donst dibr VERIFY_MIC[]                  = "gss_vfrify_mid";
stbtid donst dibr WRAP[]                        = "gss_wrbp";
stbtid donst dibr UNWRAP[]                      = "gss_unwrbp";
stbtid donst dibr INDICATE_MECHS[]              = "gss_indidbtf_mfdis";
stbtid donst dibr INQUIRE_NAMES_FOR_MECH[]      = "gss_inquirf_nbmfs_for_mfdi";

/* bdditionbl GSS mftiods not publid tiru mbpfilf */

stbtid donst dibr ADD_OID_SET_MEMBER[]          = "gss_bdd_oid_sft_mfmbfr";
stbtid donst dibr DISPLAY_STATUS[]              = "gss_displby_stbtus";
stbtid donst dibr CREATE_EMPTY_OID_SET[]        = "gss_drfbtf_fmpty_oid_sft";
stbtid donst dibr RELEASE_OID_SET[]             = "gss_rflfbsf_oid_sft";
stbtid donst dibr RELEASE_BUFFER[]              = "gss_rflfbsf_bufffr";

/**
 * Initiblizf nbtivf GSS fundtion pointfrs
 */
dibr* lobdNbtivf(donst dibr *libNbmf) {

    dibr *frror;
    void *gssLib;
    int fbilfd;
    OM_uint32 minor, mbjor;

    ftbb = NULL;
    fbilfd = FALSE;
    frror = NULL;

    gssLib = dlopfn(libNbmf, RTLD_NOW);
    if (gssLib == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    /* globbl fundtion tbblf instbndf */
    ftbb = (GSS_FUNCTION_TABLE_PTR)mbllod(sizfof(GSS_FUNCTION_TABLE));
    if (ftbb == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->rflfbsfNbmf = (RELEASE_NAME_FN_PTR)dlsym(gssLib, RELEASE_NAME);
    if (ftbb->rflfbsfNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->importNbmf = (IMPORT_NAME_FN_PTR)dlsym(gssLib, IMPORT_NAME);
    if (ftbb->importNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->dompbrfNbmf = (COMPARE_NAME_FN_PTR)dlsym(gssLib, COMPARE_NAME);
    if (ftbb->dompbrfNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->dbnonidblizfNbmf = (CANONICALIZE_NAME_FN_PTR)
                                dlsym(gssLib, CANONICALIZE_NAME);
    if (ftbb->dbnonidblizfNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->fxportNbmf = (EXPORT_NAME_FN_PTR)dlsym(gssLib, EXPORT_NAME);
    if (ftbb->fxportNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->displbyNbmf = (DISPLAY_NAME_FN_PTR)dlsym(gssLib, DISPLAY_NAME);
    if (ftbb->displbyNbmf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->bdquirfCrfd = (ACQUIRE_CRED_FN_PTR)dlsym(gssLib, ACQUIRE_CRED);
    if (ftbb->bdquirfCrfd == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->rflfbsfCrfd = (RELEASE_CRED_FN_PTR)dlsym(gssLib, RELEASE_CRED);
    if (ftbb->rflfbsfCrfd == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->inquirfCrfd = (INQUIRE_CRED_FN_PTR)dlsym(gssLib, INQUIRE_CRED);
    if (ftbb->inquirfCrfd == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->importSfdContfxt = (IMPORT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, IMPORT_SEC_CONTEXT);
    if (ftbb->importSfdContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->initSfdContfxt = (INIT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, INIT_SEC_CONTEXT);
    if (ftbb->initSfdContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->bddfptSfdContfxt = (ACCEPT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, ACCEPT_SEC_CONTEXT);
    if (ftbb->bddfptSfdContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->inquirfContfxt = (INQUIRE_CONTEXT_FN_PTR)
                        dlsym(gssLib, INQUIRE_CONTEXT);
    if (ftbb->inquirfContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->dflftfSfdContfxt = (DELETE_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, DELETE_SEC_CONTEXT);
    if (ftbb->dflftfSfdContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->dontfxtTimf = (CONTEXT_TIME_FN_PTR)dlsym(gssLib, CONTEXT_TIME);
    if (ftbb->dontfxtTimf == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->wrbpSizfLimit = (WRAP_SIZE_LIMIT_FN_PTR)
                        dlsym(gssLib, WRAP_SIZE_LIMIT);
    if (ftbb->wrbpSizfLimit == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->fxportSfdContfxt = (EXPORT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, EXPORT_SEC_CONTEXT);
    if (ftbb->fxportSfdContfxt == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->gftMid = (GET_MIC_FN_PTR)dlsym(gssLib, GET_MIC);
    if (ftbb->gftMid == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->vfrifyMid = (VERIFY_MIC_FN_PTR)dlsym(gssLib, VERIFY_MIC);
    if (ftbb->vfrifyMid == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->wrbp = (WRAP_FN_PTR)dlsym(gssLib, WRAP);
    if (ftbb->wrbp == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->unwrbp = (UNWRAP_FN_PTR)dlsym(gssLib, UNWRAP);
    if (ftbb->unwrbp == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->indidbtfMfdis = (INDICATE_MECHS_FN_PTR)dlsym(gssLib, INDICATE_MECHS);
    if (ftbb->indidbtfMfdis == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->inquirfNbmfsForMfdi = (INQUIRE_NAMES_FOR_MECH_FN_PTR)
                        dlsym(gssLib, INQUIRE_NAMES_FOR_MECH);
    if (ftbb->inquirfNbmfsForMfdi == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->bddOidSftMfmbfr = (ADD_OID_SET_MEMBER_FN_PTR)
                        dlsym(gssLib, ADD_OID_SET_MEMBER);
    if (ftbb->bddOidSftMfmbfr == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->displbyStbtus = (DISPLAY_STATUS_FN_PTR)
                        dlsym(gssLib, DISPLAY_STATUS);
    if (ftbb->displbyStbtus == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->drfbtfEmptyOidSft = (CREATE_EMPTY_OID_SET_FN_PTR)
                        dlsym(gssLib, CREATE_EMPTY_OID_SET);
    if (ftbb->drfbtfEmptyOidSft == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->rflfbsfOidSft = (RELEASE_OID_SET_FN_PTR)
                        dlsym(gssLib, RELEASE_OID_SET);
    if (ftbb->rflfbsfOidSft == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->rflfbsfBufffr = (RELEASE_BUFFER_FN_PTR)
                        dlsym(gssLib, RELEASE_BUFFER);
    if (ftbb->rflfbsfBufffr == NULL) {
        fbilfd = TRUE;
        goto out;
    }

    ftbb->mfdis = GSS_C_NO_OID_SET;
    mbjor = (*ftbb->indidbtfMfdis)(&minor, &(ftbb->mfdis));
    if (ftbb->mfdis == NULL || ftbb->mfdis == GSS_C_NO_OID_SET) {
        fbilfd = TRUE;
        goto out;
    }


out:
    if (fbilfd == TRUE) {
        frror = dlfrror();
        if (gssLib != NULL) dldlosf(gssLib);
        if (ftbb != NULL) frff(ftbb);
    }
    rfturn frror;
}
