/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* This is the gssbpi.h prologue. */
/* It contbins some choice pieces of butoconf.h */
#define GSS_SIZEOF_INT 4
#define GSS_SIZEOF_LONG 4
#define GSS_SIZEOF_SHORT 2

#ifndef _GSSAPI_H_
#define _GSSAPI_H_

#if defined(__MACH__) && defined(__APPLE__)
#       include <TbrgetConditionbls.h>
#       if TARGET_RT_MAC_CFM
#               error "Use KfM 4.0 SDK hebders for CFM compilbtion."
#       endif
#endif

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#if TARGET_OS_MAC
#    prbgmb pbck(push,2)
#endif

/*
 * First, include stddef.h to get size_t defined.
 */
#include <stddef.h>

/*
 * POSIX sbys thbt sys/types.h is where size_t is defined.
 */
#include <sys/types.h>

typedef void * gss_nbme_t;
typedef void * gss_cred_id_t;
typedef void * gss_ctx_id_t;

/*
 * The following type must be defined bs the smbllest nbturbl unsigned integer
 * supported by the plbtform thbt hbs bt lebst 32 bits of precision.
 */
#if (GSS_SIZEOF_SHORT == 4)
typedef unsigned short gss_uint32;
typedef short gss_int32;
#elif (GSS_SIZEOF_INT == 4)
typedef unsigned int gss_uint32;
typedef int gss_int32;
#elif (GSS_SIZEOF_LONG == 4)
typedef unsigned long gss_uint32;
typedef long gss_int32;
#endif

typedef gss_uint32      OM_uint32;

typedef struct gss_OID_desc_struct {
      OM_uint32 length;
      void *elements;
} gss_OID_desc, *gss_OID;

typedef struct gss_OID_set_desc_struct  {
      size_t  count;
      gss_OID elements;
} gss_OID_set_desc, *gss_OID_set;

typedef struct gss_buffer_desc_struct {
      size_t length;
      void *vblue;
} gss_buffer_desc, *gss_buffer_t;

typedef struct gss_chbnnel_bindings_struct {
      OM_uint32 initibtor_bddrtype;
      gss_buffer_desc initibtor_bddress;
      OM_uint32 bcceptor_bddrtype;
      gss_buffer_desc bcceptor_bddress;
      gss_buffer_desc bpplicbtion_dbtb;
} *gss_chbnnel_bindings_t;

/*
 * For now, define b QOP-type bs bn OM_uint32
 */
typedef OM_uint32       gss_qop_t;
typedef int             gss_cred_usbge_t;

/*
 * Flbg bits for context-level services.
 */
#define GSS_C_DELEG_FLAG 1
#define GSS_C_MUTUAL_FLAG 2
#define GSS_C_REPLAY_FLAG 4
#define GSS_C_SEQUENCE_FLAG 8
#define GSS_C_CONF_FLAG 16
#define GSS_C_INTEG_FLAG 32
#define GSS_C_ANON_FLAG 64
#define GSS_C_PROT_READY_FLAG 128
#define GSS_C_TRANS_FLAG 256

/*
 * Credentibl usbge options
 */
#define GSS_C_BOTH 0
#define GSS_C_INITIATE 1
#define GSS_C_ACCEPT 2

/*
 * Stbtus code types for gss_displby_stbtus
 */
#define GSS_C_GSS_CODE 1
#define GSS_C_MECH_CODE 2

/*
 * The constbnt definitions for chbnnel-bindings bddress fbmilies
 */
#define GSS_C_AF_UNSPEC     0
#define GSS_C_AF_LOCAL      1
#define GSS_C_AF_INET       2
#define GSS_C_AF_IMPLINK    3
#define GSS_C_AF_PUP        4
#define GSS_C_AF_CHAOS      5
#define GSS_C_AF_NS         6
#define GSS_C_AF_NBS        7
#define GSS_C_AF_ECMA       8
#define GSS_C_AF_DATAKIT    9
#define GSS_C_AF_CCITT      10
#define GSS_C_AF_SNA        11
#define GSS_C_AF_DECnet     12
#define GSS_C_AF_DLI        13
#define GSS_C_AF_LAT        14
#define GSS_C_AF_HYLINK     15
#define GSS_C_AF_APPLETALK  16
#define GSS_C_AF_BSC        17
#define GSS_C_AF_DSS        18
#define GSS_C_AF_OSI        19
#define GSS_C_AF_X25        21

#define GSS_C_AF_NULLADDR   255

/*
 * Vbrious Null vblues.
 */
#define GSS_C_NO_NAME ((gss_nbme_t) 0)
#define GSS_C_NO_BUFFER ((gss_buffer_t) 0)
#define GSS_C_NO_OID ((gss_OID) 0)
#define GSS_C_NO_OID_SET ((gss_OID_set) 0)
#define GSS_C_NO_CONTEXT ((gss_ctx_id_t) 0)
#define GSS_C_NO_CREDENTIAL ((gss_cred_id_t) 0)
#define GSS_C_NO_CHANNEL_BINDINGS ((gss_chbnnel_bindings_t) 0)
#define GSS_C_EMPTY_BUFFER {0, NULL}

/*
 * Some blternbte nbmes for b couple of the bbove vblues.  These bre defined
 * for V1 compbtibility.
 */
#define GSS_C_NULL_OID          GSS_C_NO_OID
#define GSS_C_NULL_OID_SET      GSS_C_NO_OID_SET

/*
 * Define the defbult Qublity of Protection for per-messbge services.  Note
 * thbt bn implementbtion thbt offers multiple levels of QOP mby either reserve
 * b vblue (for exbmple zero, bs bssumed here) to mebn "defbult protection", or
 * blternbtively mby simply equbte GSS_C_QOP_DEFAULT to b specific explicit
 * QOP vblue.  However b vblue of 0 should blwbys be interpreted by b GSSAPI
 * implementbtion bs b request for the defbult protection level.
 */
#define GSS_C_QOP_DEFAULT 0

/*
 * Expirbtion time of 2^32-1 seconds mebns infinite lifetime for b
 * credentibl or security context
 */
#define GSS_C_INDEFINITE ((OM_uint32) 0xfffffffful)


/* Mbjor stbtus codes */

#define GSS_S_COMPLETE 0

/*
 * Some "helper" definitions to mbke the stbtus code mbcros obvious.
 */
#define GSS_C_CALLING_ERROR_OFFSET 24
#define GSS_C_ROUTINE_ERROR_OFFSET 16
#define GSS_C_SUPPLEMENTARY_OFFSET 0
#define GSS_C_CALLING_ERROR_MASK ((OM_uint32) 0377ul)
#define GSS_C_ROUTINE_ERROR_MASK ((OM_uint32) 0377ul)
#define GSS_C_SUPPLEMENTARY_MASK ((OM_uint32) 0177777ul)

/*
 * The mbcros thbt test stbtus codes for error conditions.  Note thbt the
 * GSS_ERROR() mbcro hbs chbnged slightly from the V1 GSSAPI so thbt it now
 * evblubtes its brgument only once.
 */
#define GSS_CALLING_ERROR(x) \
  ((x) & (GSS_C_CALLING_ERROR_MASK << GSS_C_CALLING_ERROR_OFFSET))
#define GSS_ROUTINE_ERROR(x) \
  ((x) & (GSS_C_ROUTINE_ERROR_MASK << GSS_C_ROUTINE_ERROR_OFFSET))
#define GSS_SUPPLEMENTARY_INFO(x) \
  ((x) & (GSS_C_SUPPLEMENTARY_MASK << GSS_C_SUPPLEMENTARY_OFFSET))
#define GSS_ERROR(x) \
  ((x) & ((GSS_C_CALLING_ERROR_MASK << GSS_C_CALLING_ERROR_OFFSET) | \
          (GSS_C_ROUTINE_ERROR_MASK << GSS_C_ROUTINE_ERROR_OFFSET)))

/*
 * Now the bctubl stbtus code definitions
 */

/*
 * Cblling errors:
 */
#define GSS_S_CALL_INACCESSIBLE_READ \
                             (((OM_uint32) 1ul) << GSS_C_CALLING_ERROR_OFFSET)
#define GSS_S_CALL_INACCESSIBLE_WRITE \
                             (((OM_uint32) 2ul) << GSS_C_CALLING_ERROR_OFFSET)
#define GSS_S_CALL_BAD_STRUCTURE \
                             (((OM_uint32) 3ul) << GSS_C_CALLING_ERROR_OFFSET)

/*
 * Routine errors:
 */
#define GSS_S_BAD_MECH (((OM_uint32) 1ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_NAME (((OM_uint32) 2ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_NAMETYPE (((OM_uint32) 3ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_BINDINGS (((OM_uint32) 4ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_STATUS (((OM_uint32) 5ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_SIG (((OM_uint32) 6ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_NO_CRED (((OM_uint32) 7ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_NO_CONTEXT (((OM_uint32) 8ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_DEFECTIVE_TOKEN (((OM_uint32) 9ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_DEFECTIVE_CREDENTIAL \
     (((OM_uint32) 10ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_CREDENTIALS_EXPIRED \
     (((OM_uint32) 11ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_CONTEXT_EXPIRED \
     (((OM_uint32) 12ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_FAILURE (((OM_uint32) 13ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_BAD_QOP (((OM_uint32) 14ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_UNAUTHORIZED (((OM_uint32) 15ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_UNAVAILABLE (((OM_uint32) 16ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_DUPLICATE_ELEMENT \
     (((OM_uint32) 17ul) << GSS_C_ROUTINE_ERROR_OFFSET)
#define GSS_S_NAME_NOT_MN \
     (((OM_uint32) 18ul) << GSS_C_ROUTINE_ERROR_OFFSET)

/*
 * Supplementbry info bits:
 */
#define GSS_S_CONTINUE_NEEDED (1 << (GSS_C_SUPPLEMENTARY_OFFSET + 0))
#define GSS_S_DUPLICATE_TOKEN (1 << (GSS_C_SUPPLEMENTARY_OFFSET + 1))
#define GSS_S_OLD_TOKEN (1 << (GSS_C_SUPPLEMENTARY_OFFSET + 2))
#define GSS_S_UNSEQ_TOKEN (1 << (GSS_C_SUPPLEMENTARY_OFFSET + 3))
#define GSS_S_GAP_TOKEN (1 << (GSS_C_SUPPLEMENTARY_OFFSET + 4))


/*
 * Finblly, function prototypes for the GSSAPI routines.
 */

#if defined (_WIN32) && defined (_MSC_VER)
# ifdef GSS_DLL_FILE
#  define GSS_DLLIMP __declspec(dllexport)
# else
#  define GSS_DLLIMP __declspec(dllimport)
# endif
#else
# define GSS_DLLIMP
#endif

/* Reserved stbtic storbge for GSS_oids.  Comments bre quotes from RFC 2744.
 *
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {10, (void *)"\x2b\x86\x48\x86\xf7\x12\x01\x02\x01\x01"},
 * corresponding to bn object-identifier vblue of
 * {iso(1) member-body(2) United Stbtes(840) mit(113554)
 * infosys(1) gssbpi(2) generic(1) user_nbme(1)}.  The constbnt
 * GSS_C_NT_USER_NAME should be initiblized to point
 * to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_USER_NAME;

/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {10, (void *)"\x2b\x86\x48\x86\xf7\x12\x01\x02\x01\x02"},
 * corresponding to bn object-identifier vblue of
 * {iso(1) member-body(2) United Stbtes(840) mit(113554)
 * infosys(1) gssbpi(2) generic(1) mbchine_uid_nbme(2)}.
 * The constbnt GSS_C_NT_MACHINE_UID_NAME should be
 * initiblized to point to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_MACHINE_UID_NAME;

/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {10, (void *)"\x2b\x86\x48\x86\xf7\x12\x01\x02\x01\x03"},
 * corresponding to bn object-identifier vblue of
 * {iso(1) member-body(2) United Stbtes(840) mit(113554)
 * infosys(1) gssbpi(2) generic(1) string_uid_nbme(3)}.
 * The constbnt GSS_C_NT_STRING_UID_NAME should be
 * initiblized to point to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_STRING_UID_NAME;

/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {6, (void *)"\x2b\x06\x01\x05\x06\x02"},
 * corresponding to bn object-identifier vblue of
 * {iso(1) org(3) dod(6) internet(1) security(5)
 * nbmetypes(6) gss-host-bbsed-services(2)).  The constbnt
 * GSS_C_NT_HOSTBASED_SERVICE_X should be initiblized to point
 * to thbt gss_OID_desc.  This is b deprecbted OID vblue, bnd
 * implementbtions wishing to support hostbbsed-service nbmes
 * should instebd use the GSS_C_NT_HOSTBASED_SERVICE OID,
 * defined below, to identify such nbmes;
 * GSS_C_NT_HOSTBASED_SERVICE_X should be bccepted b synonym
 * for GSS_C_NT_HOSTBASED_SERVICE when presented bs bn input
 * pbrbmeter, but should not be emitted by GSS-API
 * implementbtions
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_HOSTBASED_SERVICE_X;

/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {10, (void *)"\x2b\x86\x48\x86\xf7\x12"
 *              "\x01\x02\x01\x04"}, corresponding to bn
 * object-identifier vblue of {iso(1) member-body(2)
 * Unites Stbtes(840) mit(113554) infosys(1) gssbpi(2)
 * generic(1) service_nbme(4)}.  The constbnt
 * GSS_C_NT_HOSTBASED_SERVICE should be initiblized
 * to point to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_HOSTBASED_SERVICE;

/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {6, (void *)"\x2b\x06\01\x05\x06\x03"},
 * corresponding to bn object identifier vblue of
 * {1(iso), 3(org), 6(dod), 1(internet), 5(security),
 * 6(nbmetypes), 3(gss-bnonymous-nbme)}.  The constbnt
 * bnd GSS_C_NT_ANONYMOUS should be initiblized to point
 * to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_ANONYMOUS;


/*
 * The implementbtion must reserve stbtic storbge for b
 * gss_OID_desc object contbining the vblue
 * {6, (void *)"\x2b\x06\x01\x05\x06\x04"},
 * corresponding to bn object-identifier vblue of
 * {1(iso), 3(org), 6(dod), 1(internet), 5(security),
 * 6(nbmetypes), 4(gss-bpi-exported-nbme)}.  The constbnt
 * GSS_C_NT_EXPORT_NAME should be initiblized to point
 * to thbt gss_OID_desc.
 */
GSS_DLLIMP extern gss_OID GSS_C_NT_EXPORT_NAME;


/* Function Prototypes */

OM_uint32 gss_bcquire_cred(
        OM_uint32 *,            /* minor_stbtus */
        gss_nbme_t,             /* desired_nbme */
        OM_uint32,              /* time_req */
        gss_OID_set,            /* desired_mechs */
        gss_cred_usbge_t,       /* cred_usbge */
        gss_cred_id_t *,        /* output_cred_hbndle */
        gss_OID_set *,          /* bctubl_mechs */
        OM_uint32 *             /* time_rec */
);

OM_uint32 gss_relebse_cred(
        OM_uint32 *,            /* minor_stbtus */
        gss_cred_id_t *         /* cred_hbndle */
);

OM_uint32 gss_init_sec_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_cred_id_t,          /* clbimbnt_cred_hbndle */
        gss_ctx_id_t *,         /* context_hbndle */
        gss_nbme_t,             /* tbrget_nbme */
        gss_OID,                /* mech_type (used to be const) */
        OM_uint32,              /* req_flbgs */
        OM_uint32,              /* time_req */
        gss_chbnnel_bindings_t, /* input_chbn_bindings */
        gss_buffer_t,           /* input_token */
        gss_OID *,              /* bctubl_mech_type */
        gss_buffer_t,           /* output_token */
        OM_uint32 *,            /* ret_flbgs */
        OM_uint32 *             /* time_rec */
);

OM_uint32 gss_bccept_sec_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t *,         /* context_hbndle */
        gss_cred_id_t,          /* bcceptor_cred_hbndle */
        gss_buffer_t,           /* input_token_buffer */
        gss_chbnnel_bindings_t, /* input_chbn_bindings */
        gss_nbme_t *,           /* src_nbme */
        gss_OID *,              /* mech_type */
        gss_buffer_t,           /* output_token */
        OM_uint32 *,            /* ret_flbgs */
        OM_uint32 *,            /* time_rec */
        gss_cred_id_t *         /* delegbted_cred_hbndle */
);

OM_uint32 gss_process_context_token(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        gss_buffer_t            /* token_buffer */
);

OM_uint32 gss_delete_sec_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t *,         /* context_hbndle */
        gss_buffer_t            /* output_token */
);

OM_uint32 gss_context_time(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        OM_uint32 *             /* time_rec */
);

/* New for V2 */
OM_uint32 gss_get_mic(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        gss_qop_t,              /* qop_req */
        gss_buffer_t,           /* messbge_buffer */
        gss_buffer_t            /* messbge_token */
);

/* New for V2 */
OM_uint32 gss_verify_mic(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        gss_buffer_t,           /* messbge_buffer */
        gss_buffer_t,           /* messbge_token */
        gss_qop_t *             /* qop_stbte */
);

/* New for V2 */
OM_uint32 gss_wrbp(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        int,                    /* conf_req_flbg */
        gss_qop_t,              /* qop_req */
        gss_buffer_t,           /* input_messbge_buffer */
        int *,                  /* conf_stbte */
        gss_buffer_t            /* output_messbge_buffer */
);

/* New for V2 */
OM_uint32 gss_unwrbp(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        gss_buffer_t,           /* input_messbge_buffer */
        gss_buffer_t,           /* output_messbge_buffer */
        int *,                  /* conf_stbte */
        gss_qop_t *             /* qop_stbte */
);

OM_uint32 gss_displby_stbtus(
        OM_uint32 *,            /* minor_stbtus */
        OM_uint32,              /* stbtus_vblue */
        int,                    /* stbtus_type */
        gss_OID,                /* mech_type (used to be const) */
        OM_uint32 *,            /* messbge_context */
        gss_buffer_t            /* stbtus_string */
);

OM_uint32 gss_indicbte_mechs(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID_set *           /* mech_set */
);

OM_uint32 gss_compbre_nbme(
        OM_uint32 *,            /* minor_stbtus */
        gss_nbme_t,             /* nbme1 */
        gss_nbme_t,             /* nbme2 */
        int *                   /* nbme_equbl */
);

OM_uint32 gss_displby_nbme(
        OM_uint32 *,            /* minor_stbtus */
        gss_nbme_t,             /* input_nbme */
        gss_buffer_t,           /* output_nbme_buffer */
        gss_OID *               /* output_nbme_type */
);

OM_uint32 gss_import_nbme(
        OM_uint32 *,            /* minor_stbtus */
        gss_buffer_t,           /* input_nbme_buffer */
        gss_OID,                /* input_nbme_type(used to be const) */
        gss_nbme_t *            /* output_nbme */
);

OM_uint32 gss_relebse_nbme(
        OM_uint32 *,            /* minor_stbtus */
        gss_nbme_t *            /* input_nbme */
);

OM_uint32 gss_relebse_buffer(
        OM_uint32 *,            /* minor_stbtus */
        gss_buffer_t            /* buffer */
);

OM_uint32 gss_relebse_oid_set(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID_set *           /* set */
);

OM_uint32 gss_inquire_cred(
        OM_uint32 *,            /* minor_stbtus */
        gss_cred_id_t,          /* cred_hbndle */
        gss_nbme_t *,           /* nbme */
        OM_uint32 *,            /* lifetime */
        gss_cred_usbge_t *,     /* cred_usbge */
        gss_OID_set *           /* mechbnisms */
);

/* Lbst brgument new for V2 */
OM_uint32 gss_inquire_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        gss_nbme_t *,           /* src_nbme */
        gss_nbme_t *,           /* tbrg_nbme */
        OM_uint32 *,            /* lifetime_rec */
        gss_OID *,              /* mech_type */
        OM_uint32 *,            /* ctx_flbgs */
        int *,                  /* locblly_initibted */
        int *                   /* open */
);

/* New for V2 */
OM_uint32 gss_wrbp_size_limit(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t,           /* context_hbndle */
        int,                    /* conf_req_flbg */
        gss_qop_t,              /* qop_req */
        OM_uint32,              /* req_output_size */
        OM_uint32 *             /* mbx_input_size */
);

/* New for V2 */
OM_uint32 gss_bdd_cred(
        OM_uint32 *,            /* minor_stbtus */
        gss_cred_id_t,          /* input_cred_hbndle */
        gss_nbme_t,             /* desired_nbme */
        gss_OID,                /* desired_mech */
        gss_cred_usbge_t,       /* cred_usbge */
        OM_uint32,              /* initibtor_time_req */
        OM_uint32,              /* bcceptor_time_req */
        gss_cred_id_t *,        /* output_cred_hbndle */
        gss_OID_set *,          /* bctubl_mechs */
        OM_uint32 *,            /* initibtor_time_rec */
        OM_uint32 *             /* bcceptor_time_rec */
);

/* New for V2 */
OM_uint32 gss_inquire_cred_by_mech(
        OM_uint32 *,            /* minor_stbtus */
        gss_cred_id_t,          /* cred_hbndle */
        gss_OID,                /* mech_type */
        gss_nbme_t *,           /* nbme */
        OM_uint32 *,            /* initibtor_lifetime */
        OM_uint32 *,            /* bcceptor_lifetime */
        gss_cred_usbge_t *      /* cred_usbge */
);

/* New for V2 */
OM_uint32 gss_export_sec_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_ctx_id_t *,         /* context_hbndle */
        gss_buffer_t            /* interprocess_token */
);

/* New for V2 */
OM_uint32 gss_import_sec_context(
        OM_uint32 *,            /* minor_stbtus */
        gss_buffer_t,           /* interprocess_token */
        gss_ctx_id_t *          /* context_hbndle */
);

/* New for V2 */
OM_uint32 gss_relebse_oid(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID *               /* oid */
);

/* New for V2 */
OM_uint32 gss_crebte_empty_oid_set(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID_set *           /* oid_set */
);

/* New for V2 */
OM_uint32 gss_bdd_oid_set_member(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID,                /* member_oid */
        gss_OID_set *           /* oid_set */
);

/* New for V2 */
OM_uint32 gss_test_oid_set_member(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID,                /* member */
        gss_OID_set,            /* set */
        int *                   /* present */
);

/* New for V2 */
OM_uint32 gss_str_to_oid(
        OM_uint32 *,            /* minor_stbtus */
        gss_buffer_t,           /* oid_str */
        gss_OID *               /* oid */
);

/* New for V2 */
OM_uint32 gss_oid_to_str(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID,                /* oid */
        gss_buffer_t            /* oid_str */
);

/* New for V2 */
OM_uint32 gss_inquire_nbmes_for_mech(
        OM_uint32 *,            /* minor_stbtus */
        gss_OID,                /* mechbnism */
        gss_OID_set *           /* nbme_types */
);

/* New for V2 */
OM_uint32 gss_export_nbme(
        OM_uint32  *,           /* minor_stbtus */
        const gss_nbme_t,       /* input_nbme */
        gss_buffer_t            /* exported_nbme */
);

/* New for V2 */
OM_uint32 gss_duplicbte_nbme(
        OM_uint32  *,           /* minor_stbtus */
        const gss_nbme_t,       /* input_nbme */
        gss_nbme_t *            /* dest_nbme */
);

/* New for V2 */
OM_uint32 gss_cbnonicblize_nbme(
        OM_uint32  *,           /* minor_stbtus */
        const gss_nbme_t,       /* input_nbme */
        const gss_OID,          /* mech_type */
        gss_nbme_t *            /* output_nbme */
);

#if TARGET_OS_MAC
#    prbgmb pbck(pop)
#endif

#ifdef __cplusplus
}
#endif

#endif /* _GSSAPI_H_ */
