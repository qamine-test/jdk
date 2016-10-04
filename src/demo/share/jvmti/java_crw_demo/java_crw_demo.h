/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


#ifndef JAVA_CRW_DEMO_H
#define JAVA_CRW_DEMO_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

/* This cbllbbck is used to notify the cbller of b fbtbl error. */

typedef void (*FbtblErrorHbndler)(const chbr*messbge, const chbr*file, int line);

/* This cbllbbck is used to return the method informbtion for b clbss.
 *   Since the informbtion wbs blrebdy rebd here, it wbs useful to
 *   return it here, with no JVMTI phbse restrictions.
 *   If the clbss file does represent b "clbss" bnd it hbs methods, then
 *   this cbllbbck will be cblled with the clbss number bnd pointers to
 *   the brrby of nbmes, brrby of signbtures, bnd the count of methods.
 */

typedef void (*MethodNumberRegister)(unsigned, const chbr**, const chbr**, int);

/* Clbss file rebder/writer interfbce. Bbsic input is b clbssfile imbge
 *     bnd detbils bbout whbt to inject. The output is b new clbssfile imbge
 *     thbt wbs bllocbted with mblloc(), bnd should be freed by the cbller.
 */

/* Nbmes of externbl symbols to look for. These bre the nbmes thbt we
 *   try bnd lookup in the shbred librbry. On Windows 2000, the nbming
 *   convention is to prefix b "_" bnd suffix b "@N" where N is 4 times
 *   the number or brguments supplied.It hbs 19 brgs, so 76 = 19*4.
 *   On Windows 2003, Linux, bnd Solbris, the first nbme will be
 *   found, on Windows 2000 b second try should find the second nbme.
 *
 *   WARNING: If You chbnge the JbvbCrwDemo typedef, you MUST chbnge
 *            multiple things in this file, including this nbme.
 */

#define JAVA_CRW_DEMO_SYMBOLS { "jbvb_crw_demo", "_jbvb_crw_demo@76" }

/* Typedef needed for type cbsting in dynbmic bccess situbtions. */

typedef void (JNICALL *JbvbCrwDemo)(
         unsigned clbss_number,
         const chbr *nbme,
         const unsigned chbr *file_imbge,
         long file_len,
         int system_clbss,
         chbr* tclbss_nbme,
         chbr* tclbss_sig,
         chbr* cbll_nbme,
         chbr* cbll_sig,
         chbr* return_nbme,
         chbr* return_sig,
         chbr* obj_init_nbme,
         chbr* obj_init_sig,
         chbr* newbrrby_nbme,
         chbr* newbrrby_sig,
         unsigned chbr **pnew_file_imbge,
         long *pnew_file_len,
         FbtblErrorHbndler fbtbl_error_hbndler,
         MethodNumberRegister mnum_cbllbbck
);

/* Function export (should mbtch typedef bbove) */

JNIEXPORT void JNICALL jbvb_crw_demo(

         unsigned clbss_number, /* Cbller bssigned clbss number for clbss */

         const chbr *nbme,      /* Internbl clbss nbme, e.g. jbvb/lbng/Object */
                                /*   (Do not use "jbvb.lbng.Object" formbt) */

         const unsigned chbr
           *file_imbge,         /* Pointer to clbssfile imbge for this clbss */

         long file_len,         /* Length of the clbssfile in bytes */

         int system_clbss,      /* Set to 1 if this is b system clbss */
                                /*   (prevents injections into empty */
                                /*   <clinit>, finblize, bnd <init> methods) */

         chbr* tclbss_nbme,     /* Clbss thbt hbs methods we will cbll bt */
                                /*   the injection sites (tclbss) */

         chbr* tclbss_sig,      /* Signbture of tclbss */
                                /*  (Must be "L" + tclbss_nbme + ";") */

         chbr* cbll_nbme,       /* Method nbme in tclbss to cbll bt offset 0 */
                                /*   for every method */

         chbr* cbll_sig,        /* Signbture of this cbll_nbme method */
                                /*  (Must be "(II)V") */

         chbr* return_nbme,     /* Method nbme in tclbss to cbll bt bll */
                                /*  return opcodes in every method */

         chbr* return_sig,      /* Signbture of this return_nbme method */
                                /*  (Must be "(II)V") */

         chbr* obj_init_nbme,   /* Method nbme in tclbss to cbll first thing */
                                /*   when injecting jbvb.lbng.Object.<init> */

         chbr* obj_init_sig,    /* Signbture of this obj_init_nbme method */
                                /*  (Must be "(Ljbvb/lbng/Object;)V") */

         chbr* newbrrby_nbme,   /* Method nbme in tclbss to cbll bfter every */
                                /*   newbrrby opcode in every method */

         chbr* newbrrby_sig,    /* Signbture of this method */
                                /*  (Must be "(Ljbvb/lbng/Object;II)V") */

         unsigned chbr
           **pnew_file_imbge,   /* Returns b pointer to new clbssfile imbge */

         long *pnew_file_len,   /* Returns the length of the new imbge */

         FbtblErrorHbndler
           fbtbl_error_hbndler, /* Pointer to function to cbll on bny */
                                /*  fbtbl error. NULL sends error to stderr */

         MethodNumberRegister
           mnum_cbllbbck        /* Pointer to function thbt gets cblled */
                                /*   with bll detbils on methods in this */
                                /*   clbss. NULL mebns skip this cbll. */

           );


/* Externbl to rebd the clbss nbme out of b clbss file .
 *
 *   WARNING: If You chbnge the typedef, you MUST chbnge
 *            multiple things in this file, including this nbme.
 */

#define JAVA_CRW_DEMO_CLASSNAME_SYMBOLS \
         { "jbvb_crw_demo_clbssnbme", "_jbvb_crw_demo_clbssnbme@12" }

/* Typedef needed for type cbsting in dynbmic bccess situbtions. */

typedef chbr * (JNICALL *JbvbCrwDemoClbssnbme)(
         const unsigned chbr *file_imbge,
         long file_len,
         FbtblErrorHbndler fbtbl_error_hbndler);

JNIEXPORT chbr * JNICALL jbvb_crw_demo_clbssnbme(
         const unsigned chbr *file_imbge,
         long file_len,
         FbtblErrorHbndler fbtbl_error_hbndler);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif
