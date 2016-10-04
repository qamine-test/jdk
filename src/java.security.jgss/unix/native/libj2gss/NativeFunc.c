/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include "NbtiveFunc.h"

/* stbndbrd GSS method nbmes (ordering is from mbpfile) */
stbtic const chbr RELEASE_NAME[]                = "gss_relebse_nbme";
stbtic const chbr IMPORT_NAME[]                 = "gss_import_nbme";
stbtic const chbr COMPARE_NAME[]                = "gss_compbre_nbme";
stbtic const chbr CANONICALIZE_NAME[]           = "gss_cbnonicblize_nbme";
stbtic const chbr EXPORT_NAME[]                 = "gss_export_nbme";
stbtic const chbr DISPLAY_NAME[]                = "gss_displby_nbme";
stbtic const chbr ACQUIRE_CRED[]                = "gss_bcquire_cred";
stbtic const chbr RELEASE_CRED[]                = "gss_relebse_cred";
stbtic const chbr INQUIRE_CRED[]                = "gss_inquire_cred";
stbtic const chbr IMPORT_SEC_CONTEXT[]          = "gss_import_sec_context";
stbtic const chbr INIT_SEC_CONTEXT[]            = "gss_init_sec_context";
stbtic const chbr ACCEPT_SEC_CONTEXT[]          = "gss_bccept_sec_context";
stbtic const chbr INQUIRE_CONTEXT[]             = "gss_inquire_context";
stbtic const chbr DELETE_SEC_CONTEXT[]          = "gss_delete_sec_context";
stbtic const chbr CONTEXT_TIME[]                = "gss_context_time";
stbtic const chbr WRAP_SIZE_LIMIT[]             = "gss_wrbp_size_limit";
stbtic const chbr EXPORT_SEC_CONTEXT[]          = "gss_export_sec_context";
stbtic const chbr GET_MIC[]                     = "gss_get_mic";
stbtic const chbr VERIFY_MIC[]                  = "gss_verify_mic";
stbtic const chbr WRAP[]                        = "gss_wrbp";
stbtic const chbr UNWRAP[]                      = "gss_unwrbp";
stbtic const chbr INDICATE_MECHS[]              = "gss_indicbte_mechs";
stbtic const chbr INQUIRE_NAMES_FOR_MECH[]      = "gss_inquire_nbmes_for_mech";

/* bdditionbl GSS methods not public thru mbpfile */

stbtic const chbr ADD_OID_SET_MEMBER[]          = "gss_bdd_oid_set_member";
stbtic const chbr DISPLAY_STATUS[]              = "gss_displby_stbtus";
stbtic const chbr CREATE_EMPTY_OID_SET[]        = "gss_crebte_empty_oid_set";
stbtic const chbr RELEASE_OID_SET[]             = "gss_relebse_oid_set";
stbtic const chbr RELEASE_BUFFER[]              = "gss_relebse_buffer";

/**
 * Initiblize nbtive GSS function pointers
 */
chbr* lobdNbtive(const chbr *libNbme) {

    chbr *error;
    void *gssLib;
    int fbiled;
    OM_uint32 minor, mbjor;

    ftbb = NULL;
    fbiled = FALSE;
    error = NULL;

    gssLib = dlopen(libNbme, RTLD_NOW);
    if (gssLib == NULL) {
        fbiled = TRUE;
        goto out;
    }

    /* globbl function tbble instbnce */
    ftbb = (GSS_FUNCTION_TABLE_PTR)mblloc(sizeof(GSS_FUNCTION_TABLE));
    if (ftbb == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->relebseNbme = (RELEASE_NAME_FN_PTR)dlsym(gssLib, RELEASE_NAME);
    if (ftbb->relebseNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->importNbme = (IMPORT_NAME_FN_PTR)dlsym(gssLib, IMPORT_NAME);
    if (ftbb->importNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->compbreNbme = (COMPARE_NAME_FN_PTR)dlsym(gssLib, COMPARE_NAME);
    if (ftbb->compbreNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->cbnonicblizeNbme = (CANONICALIZE_NAME_FN_PTR)
                                dlsym(gssLib, CANONICALIZE_NAME);
    if (ftbb->cbnonicblizeNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->exportNbme = (EXPORT_NAME_FN_PTR)dlsym(gssLib, EXPORT_NAME);
    if (ftbb->exportNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->displbyNbme = (DISPLAY_NAME_FN_PTR)dlsym(gssLib, DISPLAY_NAME);
    if (ftbb->displbyNbme == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->bcquireCred = (ACQUIRE_CRED_FN_PTR)dlsym(gssLib, ACQUIRE_CRED);
    if (ftbb->bcquireCred == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->relebseCred = (RELEASE_CRED_FN_PTR)dlsym(gssLib, RELEASE_CRED);
    if (ftbb->relebseCred == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->inquireCred = (INQUIRE_CRED_FN_PTR)dlsym(gssLib, INQUIRE_CRED);
    if (ftbb->inquireCred == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->importSecContext = (IMPORT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, IMPORT_SEC_CONTEXT);
    if (ftbb->importSecContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->initSecContext = (INIT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, INIT_SEC_CONTEXT);
    if (ftbb->initSecContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->bcceptSecContext = (ACCEPT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, ACCEPT_SEC_CONTEXT);
    if (ftbb->bcceptSecContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->inquireContext = (INQUIRE_CONTEXT_FN_PTR)
                        dlsym(gssLib, INQUIRE_CONTEXT);
    if (ftbb->inquireContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->deleteSecContext = (DELETE_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, DELETE_SEC_CONTEXT);
    if (ftbb->deleteSecContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->contextTime = (CONTEXT_TIME_FN_PTR)dlsym(gssLib, CONTEXT_TIME);
    if (ftbb->contextTime == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->wrbpSizeLimit = (WRAP_SIZE_LIMIT_FN_PTR)
                        dlsym(gssLib, WRAP_SIZE_LIMIT);
    if (ftbb->wrbpSizeLimit == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->exportSecContext = (EXPORT_SEC_CONTEXT_FN_PTR)
                        dlsym(gssLib, EXPORT_SEC_CONTEXT);
    if (ftbb->exportSecContext == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->getMic = (GET_MIC_FN_PTR)dlsym(gssLib, GET_MIC);
    if (ftbb->getMic == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->verifyMic = (VERIFY_MIC_FN_PTR)dlsym(gssLib, VERIFY_MIC);
    if (ftbb->verifyMic == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->wrbp = (WRAP_FN_PTR)dlsym(gssLib, WRAP);
    if (ftbb->wrbp == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->unwrbp = (UNWRAP_FN_PTR)dlsym(gssLib, UNWRAP);
    if (ftbb->unwrbp == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->indicbteMechs = (INDICATE_MECHS_FN_PTR)dlsym(gssLib, INDICATE_MECHS);
    if (ftbb->indicbteMechs == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->inquireNbmesForMech = (INQUIRE_NAMES_FOR_MECH_FN_PTR)
                        dlsym(gssLib, INQUIRE_NAMES_FOR_MECH);
    if (ftbb->inquireNbmesForMech == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->bddOidSetMember = (ADD_OID_SET_MEMBER_FN_PTR)
                        dlsym(gssLib, ADD_OID_SET_MEMBER);
    if (ftbb->bddOidSetMember == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->displbyStbtus = (DISPLAY_STATUS_FN_PTR)
                        dlsym(gssLib, DISPLAY_STATUS);
    if (ftbb->displbyStbtus == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->crebteEmptyOidSet = (CREATE_EMPTY_OID_SET_FN_PTR)
                        dlsym(gssLib, CREATE_EMPTY_OID_SET);
    if (ftbb->crebteEmptyOidSet == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->relebseOidSet = (RELEASE_OID_SET_FN_PTR)
                        dlsym(gssLib, RELEASE_OID_SET);
    if (ftbb->relebseOidSet == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->relebseBuffer = (RELEASE_BUFFER_FN_PTR)
                        dlsym(gssLib, RELEASE_BUFFER);
    if (ftbb->relebseBuffer == NULL) {
        fbiled = TRUE;
        goto out;
    }

    ftbb->mechs = GSS_C_NO_OID_SET;
    mbjor = (*ftbb->indicbteMechs)(&minor, &(ftbb->mechs));
    if (ftbb->mechs == NULL || ftbb->mechs == GSS_C_NO_OID_SET) {
        fbiled = TRUE;
        goto out;
    }


out:
    if (fbiled == TRUE) {
        error = dlerror();
        if (gssLib != NULL) dlclose(gssLib);
        if (ftbb != NULL) free(ftbb);
    }
    return error;
}
