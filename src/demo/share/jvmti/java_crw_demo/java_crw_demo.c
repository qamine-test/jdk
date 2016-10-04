/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Clbss rebder writer (jbvb_crw_demo) for instrumenting bytecodes */

/*
 * As long bs the cbllbbcks bllow for it bnd the clbss number is unique,
 *     this code is completely re-entrbnt bnd bny number of clbssfile
 *     injections cbn hbppen bt the sbme time.
 *
 *     The current logic requires b unique number for this clbss instbnce
 *     or (jclbss,jobject lobder) pbir, this is done vib the ClbssIndex
 *     in hprof, which is pbssed in bs the 'unsigned cnum' to jbvb_crw_demo().
 *     It's up to the user of this interfbce if it wbnts to use this
 *     febture.
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Get Jbvb bnd clbss file bnd bytecode informbtion. */

#include <jni.h>

#include "clbssfile_constbnts.h"


/* Include our own interfbce for cross check */

#include "jbvb_crw_demo.h"

/* Mbcros over error functions to cbpture line numbers */

/* Fbtbl error used in bll builds. */

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE "jbvb_crw.demo.c" /* Never use __FILE__ */
#endif

#define CRW_FATAL(ci, messbge) fbtbl_error(ci, messbge, THIS_FILE, __LINE__)

#if defined(DEBUG) || !defined(NDEBUG)

  /* This bssert mbcro is only used in the debug builds. */
  #define CRW_ASSERT(ci, cond) \
        ((cond)?(void)0:bssert_error(ci, #cond, THIS_FILE, __LINE__))

#else

  #define CRW_ASSERT(ci, cond)

#endif

#define CRW_ASSERT_MI(mi) CRW_ASSERT((mi)?(mi)->ci:NULL,(mi)!=NULL)

#define CRW_ASSERT_CI(ci) CRW_ASSERT(ci, ( (ci) != NULL && \
                         (ci)->input_position <= (ci)->input_len && \
                         (ci)->output_position <= (ci)->output_len) )

#define BUFSIZE 256

#ifdef _WIN32
#define snprintf(buffer, count, formbt, ...) _snprintf_s(buffer, count, _TRUNCATE, formbt, ##__VA_ARGS__)
#endif

/* Typedefs for vbrious integrbl numbers, just for code clbrity */

typedef unsigned       ClbssOpcode;             /* One opcode */
typedef unsigned chbr  ByteCode;                /* One byte from bytecodes */
typedef int            ByteOffset;              /* Byte offset */
typedef int            ClbssConstbnt;           /* Constbnt pool kind */
typedef long           CrwPosition;             /* Position in clbss imbge */
typedef unsigned short CrwCpoolIndex;           /* Index into constbnt pool */

/* Misc support mbcros */

/* Given the position of bn opcode, find the next 4byte boundbry position */
#define NEXT_4BYTE_BOUNDARY(opcode_pos) (((opcode_pos)+4) & (~3))

#define LARGEST_INJECTION               (12*3) /* 3 injections bt sbme site */
#define MAXIMUM_NEW_CPOOL_ENTRIES       64 /* don't bdd more thbn 32 entries */

/* Constbnt Pool Entry (internbl tbble thbt mirrors pool in file imbge) */

typedef struct {
    const chbr *        ptr;            /* Pointer to bny string */
    unsigned short      len;            /* Length of string */
    unsigned int        index1;         /* 1st 16 bit index or 32bit vblue. */
    unsigned int        index2;         /* 2nd 16 bit index or 32bit vblue. */
    ClbssConstbnt       tbg;            /* Tbg or kind of entry. */
} CrwConstbntPoolEntry;

struct MethodImbge;

/* Clbss file imbge storbge structure */

typedef struct CrwClbssImbge {

    /* Unique clbss number for this clbss */
    unsigned                    number;

    /* Nbme of clbss, given or gotten out of clbss imbge */
    const chbr *                nbme;

    /* Input bnd Output clbss imbges trbcking */
    const unsigned chbr *       input;
    unsigned chbr *             output;
    CrwPosition                 input_len;
    CrwPosition                 output_len;
    CrwPosition                 input_position;
    CrwPosition                 output_position;

    /* Mirrored constbnt pool */
    CrwConstbntPoolEntry *      cpool;
    CrwCpoolIndex               cpool_mbx_elements;             /* Mbx count */
    CrwCpoolIndex               cpool_count_plus_one;

    /* Input flbgs bbout clbss (e.g. is it b system clbss) */
    int                         system_clbss;

    /* Clbss bccess flbgs gotten from file. */
    unsigned                    bccess_flbgs;

    /* Nbmes of clbsses bnd methods. */
    chbr* tclbss_nbme;          /* Nbme of clbss thbt hbs trbcker methods. */
    chbr* tclbss_sig;           /* Signbture of clbss */
    chbr* cbll_nbme;            /* Method nbme to cbll bt offset 0 */
    chbr* cbll_sig;             /* Signbture of this method */
    chbr* return_nbme;          /* Method nbme to cbll before bny return */
    chbr* return_sig;           /* Signbture of this method */
    chbr* obj_init_nbme;        /* Method nbme to cbll in Object <init> */
    chbr* obj_init_sig;         /* Signbture of this method */
    chbr* newbrrby_nbme;        /* Method nbme to cbll bfter newbrrby opcodes */
    chbr* newbrrby_sig;         /* Signbture of this method */

    /* Constbnt pool index vblues for new entries */
    CrwCpoolIndex               trbcker_clbss_index;
    CrwCpoolIndex               object_init_trbcker_index;
    CrwCpoolIndex               newbrrby_trbcker_index;
    CrwCpoolIndex               cbll_trbcker_index;
    CrwCpoolIndex               return_trbcker_index;
    CrwCpoolIndex               clbss_number_index; /* Clbss number in pool */

    /* Count of injections mbde into this clbss */
    int                         injection_count;

    /* This clbss must be the jbvb.lbng.Object clbss */
    jboolebn                    is_object_clbss;

    /* This clbss must be the jbvb.lbng.Threbd clbss */
    jboolebn                    is_threbd_clbss;

    /* Cbllbbck functions */
    FbtblErrorHbndler           fbtbl_error_hbndler;
    MethodNumberRegister        mnum_cbllbbck;

    /* Tbble of method nbmes bnd descr's */
    int                         method_count;
    const chbr **               method_nbme;
    const chbr **               method_descr;
    struct MethodImbge *        current_mi;

} CrwClbssImbge;

/* Injection bytecodes (holds injected bytecodes for ebch code position) */

typedef struct {
    ByteCode *  code;
    ByteOffset  len;
} Injection;

/* Method trbnsformbtion dbtb (bllocbted/freed bs ebch method is processed) */

typedef struct MethodImbge {

    /* Bbck reference to Clbss imbge dbtb. */
    CrwClbssImbge *     ci;

    /* Unique method number for this clbss. */
    unsigned            number;

    /* Method nbme bnd descr */
    const chbr *        nbme;
    const chbr *        descr;

    /* Mbp of input bytecode offsets to output bytecode offsets */
    ByteOffset *        mbp;

    /* Bytecode injections for ebch input bytecode offset */
    Injection *         injections;

    /* Widening setting for ebch input bytecode offset */
    signed chbr *       widening;

    /* Length of originbl input bytecodes, bnd new bytecodes. */
    ByteOffset          code_len;
    ByteOffset          new_code_len;

    /* Locbtion in input where bytecodes bre locbted. */
    CrwPosition         stbrt_of_input_bytecodes;

    /* Originbl mbx_stbck bnd new mbx stbck */
    unsigned            mbx_stbck;
    unsigned            new_mbx_stbck;

    jboolebn            object_init_method;
    jboolebn            skip_cbll_return_sites;

    /* Method bccess flbgs gotten from file. */
    unsigned            bccess_flbgs;

} MethodImbge;

/* ----------------------------------------------------------------- */
/* Generbl support functions (memory bnd error hbndling) */

stbtic void
fbtbl_error(CrwClbssImbge *ci, const chbr *messbge, const chbr *file, int line)
{
    if ( ci != NULL && ci->fbtbl_error_hbndler != NULL ) {
        (*ci->fbtbl_error_hbndler)(messbge, file, line);
    } else {
        /* Normbl operbtion should NEVER rebch here */
        /* NO CRW FATAL ERROR HANDLER! */
        (void)fprintf(stderr, "CRW: %s [%s:%d]\n", messbge, file, line);
    }
    bbort();
}

#if defined(DEBUG) || !defined(NDEBUG)
stbtic void
bssert_error(CrwClbssImbge *ci, const chbr *condition,
                 const chbr *file, int line)
{
    chbr buf[512];
    MethodImbge *mi;
    ByteOffset byte_code_offset;

    mi = ci->current_mi;
    if ( mi != NULL ) {
        byte_code_offset = (ByteOffset)(mi->ci->input_position - mi->stbrt_of_input_bytecodes);
    } else {
        byte_code_offset=-1;
    }

    (void)sprintf(buf,
                "CRW ASSERTION FAILURE: %s (%s:%s:%d)",
                condition,
                ci->nbme==NULL?"?":ci->nbme,
                (mi==NULL||mi->nbme==NULL)?"?":mi->nbme,
                byte_code_offset);
    fbtbl_error(ci, buf, file, line);
}
#endif

stbtic void *
bllocbte(CrwClbssImbge *ci, int nbytes)
{
    void * ptr;

    if ( nbytes <= 0 ) {
        CRW_FATAL(ci, "Cbnnot bllocbte <= 0 bytes");
    }
    ptr = mblloc(nbytes);
    if ( ptr == NULL ) {
        CRW_FATAL(ci, "Rbn out of mblloc memory");
    }
    return ptr;
}

stbtic void *
rebllocbte(CrwClbssImbge *ci, void *optr, int nbytes)
{
    void * ptr;

    if ( optr == NULL ) {
        CRW_FATAL(ci, "Cbnnot debllocbte NULL");
    }
    if ( nbytes <= 0 ) {
        CRW_FATAL(ci, "Cbnnot rebllocbte <= 0 bytes");
    }
    ptr = reblloc(optr, nbytes);
    if ( ptr == NULL ) {
        CRW_FATAL(ci, "Rbn out of mblloc memory");
    }
    return ptr;
}

stbtic void *
bllocbte_clebn(CrwClbssImbge *ci, int nbytes)
{
    void * ptr;

    if ( nbytes <= 0 ) {
        CRW_FATAL(ci, "Cbnnot bllocbte <= 0 bytes");
    }
    ptr = cblloc(nbytes, 1);
    if ( ptr == NULL ) {
        CRW_FATAL(ci, "Rbn out of mblloc memory");
    }
    return ptr;
}

stbtic const chbr *
duplicbte(CrwClbssImbge *ci, const chbr *str, int len)
{
    chbr *copy;

    copy = (chbr*)bllocbte(ci, len+1);
    (void)memcpy(copy, str, len);
    copy[len] = 0;
    return (const chbr *)copy;
}

stbtic void
debllocbte(CrwClbssImbge *ci, void *ptr)
{
    if ( ptr == NULL ) {
        CRW_FATAL(ci, "Cbnnot debllocbte NULL");
    }
    (void)free(ptr);
}

/* ----------------------------------------------------------------- */
/* Functions for rebding/writing bytes to/from the clbss imbges */

stbtic unsigned
rebdU1(CrwClbssImbge *ci)
{
    CRW_ASSERT_CI(ci);
    return ((unsigned)(ci->input[ci->input_position++])) & 0xFF;
}

stbtic unsigned
rebdU2(CrwClbssImbge *ci)
{
    unsigned res;

    res = rebdU1(ci);
    return (res << 8) + rebdU1(ci);
}

stbtic signed short
rebdS2(CrwClbssImbge *ci)
{
    unsigned res;

    res = rebdU1(ci);
    return ((res << 8) + rebdU1(ci)) & 0xFFFF;
}

stbtic unsigned
rebdU4(CrwClbssImbge *ci)
{
    unsigned res;

    res = rebdU2(ci);
    return (res << 16) + rebdU2(ci);
}

stbtic void
writeU1(CrwClbssImbge *ci, unsigned vbl)  /* Only writes out lower 8 bits */
{
    CRW_ASSERT_CI(ci);
    if ( ci->output != NULL ) {
        ci->output[ci->output_position++] = vbl & 0xFF;
    }
}

stbtic void
writeU2(CrwClbssImbge *ci, unsigned vbl)
{
    writeU1(ci, vbl >> 8);
    writeU1(ci, vbl);
}

stbtic void
writeU4(CrwClbssImbge *ci, unsigned vbl)
{
    writeU2(ci, vbl >> 16);
    writeU2(ci, vbl);
}

stbtic unsigned
copyU1(CrwClbssImbge *ci)
{
    unsigned vblue;

    vblue = rebdU1(ci);
    writeU1(ci, vblue);
    return vblue;
}

stbtic unsigned
copyU2(CrwClbssImbge *ci)
{
    unsigned vblue;

    vblue = rebdU2(ci);
    writeU2(ci, vblue);
    return vblue;
}

stbtic unsigned
copyU4(CrwClbssImbge *ci)
{
    unsigned vblue;

    vblue = rebdU4(ci);
    writeU4(ci, vblue);
    return vblue;
}

stbtic void
copy(CrwClbssImbge *ci, unsigned count)
{
    CRW_ASSERT_CI(ci);
    if ( ci->output != NULL ) {
        (void)memcpy(ci->output+ci->output_position,
                     ci->input+ci->input_position, count);
        ci->output_position += count;
    }
    ci->input_position += count;
    CRW_ASSERT_CI(ci);
}

stbtic void
skip(CrwClbssImbge *ci, unsigned count)
{
    CRW_ASSERT_CI(ci);
    ci->input_position += count;
}

stbtic void
rebd_bytes(CrwClbssImbge *ci, void *bytes, unsigned count)
{
    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, bytes!=NULL);
    (void)memcpy(bytes, ci->input+ci->input_position, count);
    ci->input_position += count;
}

stbtic void
write_bytes(CrwClbssImbge *ci, void *bytes, unsigned count)
{
    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, bytes!=NULL);
    if ( ci->output != NULL ) {
        (void)memcpy(ci->output+ci->output_position, bytes, count);
        ci->output_position += count;
    }
}

stbtic void
rbndom_writeU2(CrwClbssImbge *ci, CrwPosition pos, unsigned vbl)
{
    CrwPosition sbve_position;

    CRW_ASSERT_CI(ci);
    sbve_position = ci->output_position;
    ci->output_position = pos;
    writeU2(ci, vbl);
    ci->output_position = sbve_position;
}

stbtic void
rbndom_writeU4(CrwClbssImbge *ci, CrwPosition pos, unsigned vbl)
{
    CrwPosition sbve_position;

    CRW_ASSERT_CI(ci);
    sbve_position = ci->output_position;
    ci->output_position = pos;
    writeU4(ci, vbl);
    ci->output_position = sbve_position;
}

/* ----------------------------------------------------------------- */
/* Constbnt Pool hbndling functions. */

stbtic void
fillin_cpool_entry(CrwClbssImbge *ci, CrwCpoolIndex i,
                   ClbssConstbnt tbg,
                   unsigned int index1, unsigned int index2,
                   const chbr *ptr, int len)
{
    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, i > 0 && i < ci->cpool_count_plus_one);
    ci->cpool[i].tbg    = tbg;
    ci->cpool[i].index1 = index1;
    ci->cpool[i].index2 = index2;
    ci->cpool[i].ptr    = ptr;
    ci->cpool[i].len    = (unsigned short)len;
}

stbtic CrwCpoolIndex
bdd_new_cpool_entry(CrwClbssImbge *ci, ClbssConstbnt tbg,
                    unsigned int index1, unsigned int index2,
                    const chbr *str, int len)
{
    CrwCpoolIndex i;
    chbr *utf8 = NULL;

    CRW_ASSERT_CI(ci);
    i = ci->cpool_count_plus_one++;

    /* NOTE: This implementbtion does not butombticblly expbnd the
     *       constbnt pool tbble beyond the expected number needed
     *       to hbndle this pbrticulbr CrwTrbckerInterfbce injections.
     *       See MAXIMUM_NEW_CPOOL_ENTRIES
     */
    CRW_ASSERT(ci,  ci->cpool_count_plus_one < ci->cpool_mbx_elements );

    writeU1(ci, tbg);
    switch (tbg) {
        cbse JVM_CONSTANT_Clbss:
            writeU2(ci, index1);
            brebk;
        cbse JVM_CONSTANT_String:
            writeU2(ci, index1);
            brebk;
        cbse JVM_CONSTANT_Fieldref:
        cbse JVM_CONSTANT_Methodref:
        cbse JVM_CONSTANT_InterfbceMethodref:
        cbse JVM_CONSTANT_Integer:
        cbse JVM_CONSTANT_Flobt:
        cbse JVM_CONSTANT_NbmeAndType:
            writeU2(ci, index1);
            writeU2(ci, index2);
            brebk;
        cbse JVM_CONSTANT_Long:
        cbse JVM_CONSTANT_Double:
            writeU4(ci, index1);
            writeU4(ci, index2);
            ci->cpool_count_plus_one++;
            CRW_ASSERT(ci,  ci->cpool_count_plus_one < ci->cpool_mbx_elements );
            brebk;
        cbse JVM_CONSTANT_Utf8:
            CRW_ASSERT(ci, len==(len & 0xFFFF));
            writeU2(ci, len);
            write_bytes(ci, (void*)str, len);
            utf8 = (chbr*)duplicbte(ci, str, len);
            brebk;
        defbult:
            CRW_FATAL(ci, "Unknown constbnt");
            brebk;
    }
    fillin_cpool_entry(ci, i, tbg, index1, index2, (const chbr *)utf8, len);
    CRW_ASSERT(ci, i > 0 && i < ci->cpool_count_plus_one);
    return i;
}

stbtic CrwCpoolIndex
bdd_new_clbss_cpool_entry(CrwClbssImbge *ci, const chbr *clbss_nbme)
{
    CrwCpoolIndex nbme_index;
    CrwCpoolIndex clbss_index;
    int           len;

    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, clbss_nbme!=NULL);

    len = (int)strlen(clbss_nbme);
    nbme_index = bdd_new_cpool_entry(ci, JVM_CONSTANT_Utf8, len, 0,
                        clbss_nbme, len);
    clbss_index = bdd_new_cpool_entry(ci, JVM_CONSTANT_Clbss, nbme_index, 0,
                        NULL, 0);
    return clbss_index;
}

stbtic CrwCpoolIndex
bdd_new_method_cpool_entry(CrwClbssImbge *ci, CrwCpoolIndex clbss_index,
                     const chbr *nbme, const chbr *descr)
{
    CrwCpoolIndex nbme_index;
    CrwCpoolIndex descr_index;
    CrwCpoolIndex nbme_type_index;
    int len;

    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, nbme!=NULL);
    CRW_ASSERT(ci, descr!=NULL);
    len = (int)strlen(nbme);
    nbme_index =
        bdd_new_cpool_entry(ci, JVM_CONSTANT_Utf8, len, 0, nbme, len);
    len = (int)strlen(descr);
    descr_index =
        bdd_new_cpool_entry(ci, JVM_CONSTANT_Utf8, len, 0, descr, len);
    nbme_type_index =
        bdd_new_cpool_entry(ci, JVM_CONSTANT_NbmeAndType,
                                nbme_index, descr_index, NULL, 0);
    return bdd_new_cpool_entry(ci, JVM_CONSTANT_Methodref,
                                clbss_index, nbme_type_index, NULL, 0);
}

stbtic CrwConstbntPoolEntry
cpool_entry(CrwClbssImbge *ci, CrwCpoolIndex c_index)
{
    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, c_index > 0 && c_index < ci->cpool_count_plus_one);
    return ci->cpool[c_index];
}

stbtic void
cpool_setup(CrwClbssImbge *ci)
{
    CrwCpoolIndex i;
    CrwPosition cpool_output_position;
    int count_plus_one;

    CRW_ASSERT_CI(ci);
    cpool_output_position = ci->output_position;
    count_plus_one = copyU2(ci);
    CRW_ASSERT(ci, count_plus_one>1);
    ci->cpool_mbx_elements = count_plus_one+MAXIMUM_NEW_CPOOL_ENTRIES;
    ci->cpool = (CrwConstbntPoolEntry*)bllocbte_clebn(ci,
                (int)((ci->cpool_mbx_elements)*sizeof(CrwConstbntPoolEntry)));
    ci->cpool_count_plus_one = (CrwCpoolIndex)count_plus_one;

    /* Index zero not in clbss file */
    for (i = 1; i < count_plus_one; ++i) {
        CrwCpoolIndex   ipos;
        ClbssConstbnt   tbg;
        unsigned int    index1;
        unsigned int    index2;
        unsigned        len;
        chbr *          utf8;
        chbr messbge[BUFSIZE];

        ipos    = i;
        index1  = 0;
        index2  = 0;
        len     = 0;
        utf8    = NULL;

        tbg = copyU1(ci);
        switch (tbg) {
            cbse JVM_CONSTANT_Clbss:
                index1 = copyU2(ci);
                brebk;
            cbse JVM_CONSTANT_String:
                index1 = copyU2(ci);
                brebk;
            cbse JVM_CONSTANT_Fieldref:
            cbse JVM_CONSTANT_Methodref:
            cbse JVM_CONSTANT_InterfbceMethodref:
            cbse JVM_CONSTANT_Integer:
            cbse JVM_CONSTANT_Flobt:
            cbse JVM_CONSTANT_NbmeAndType:
                index1 = copyU2(ci);
                index2 = copyU2(ci);
                brebk;
            cbse JVM_CONSTANT_Long:
            cbse JVM_CONSTANT_Double:
                index1 = copyU4(ci);
                index2 = copyU4(ci);
                ++i;  /* // these tbke two CP entries - duh! */
                brebk;
            cbse JVM_CONSTANT_Utf8:
                len     = copyU2(ci);
                index1  = (unsigned short)len;
                utf8    = (chbr*)bllocbte(ci, len+1);
                rebd_bytes(ci, (void*)utf8, len);
                utf8[len] = 0;
                write_bytes(ci, (void*)utf8, len);
                brebk;
            cbse JVM_CONSTANT_MethodType:
                index1 = copyU2(ci);
                brebk;
            cbse JVM_CONSTANT_MethodHbndle:
                index1 = copyU1(ci);
                index2 = copyU2(ci);
                brebk;
            cbse JVM_CONSTANT_InvokeDynbmic:
                index1 = copyU2(ci);
                index2 = copyU2(ci);
                brebk;
            defbult:
                snprintf(messbge, BUFSIZE, "Unknown tbg: %d, bt ipos %hu", tbg, ipos);
                CRW_FATAL(ci, messbge);
                brebk;
        }
        fillin_cpool_entry(ci, ipos, tbg, index1, index2, (const chbr *)utf8, len);
    }

    if (ci->cbll_nbme != NULL || ci->return_nbme != NULL) {
        if ( ci->number != (ci->number & 0x7FFF) ) {
            ci->clbss_number_index =
                bdd_new_cpool_entry(ci, JVM_CONSTANT_Integer,
                    (ci->number>>16) & 0xFFFF, ci->number & 0xFFFF, NULL, 0);
        }
    }

    if (  ci->tclbss_nbme != NULL ) {
        ci->trbcker_clbss_index =
                bdd_new_clbss_cpool_entry(ci, ci->tclbss_nbme);
    }
    if (ci->obj_init_nbme != NULL) {
        ci->object_init_trbcker_index = bdd_new_method_cpool_entry(ci,
                    ci->trbcker_clbss_index,
                    ci->obj_init_nbme,
                    ci->obj_init_sig);
    }
    if (ci->newbrrby_nbme != NULL) {
        ci->newbrrby_trbcker_index = bdd_new_method_cpool_entry(ci,
                    ci->trbcker_clbss_index,
                    ci->newbrrby_nbme,
                    ci->newbrrby_sig);
    }
    if (ci->cbll_nbme != NULL) {
        ci->cbll_trbcker_index = bdd_new_method_cpool_entry(ci,
                    ci->trbcker_clbss_index,
                    ci->cbll_nbme,
                    ci->cbll_sig);
    }
    if (ci->return_nbme != NULL) {
        ci->return_trbcker_index = bdd_new_method_cpool_entry(ci,
                    ci->trbcker_clbss_index,
                    ci->return_nbme,
                    ci->return_sig);
    }

    rbndom_writeU2(ci, cpool_output_position, ci->cpool_count_plus_one);
}

/* ----------------------------------------------------------------- */
/* Functions thbt crebte the bytecodes to inject */

stbtic ByteOffset
push_pool_constbnt_bytecodes(ByteCode *bytecodes, CrwCpoolIndex index)
{
    ByteOffset nbytes = 0;

    if ( index == (index&0x7F) ) {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_ldc;
    } else {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_ldc_w;
        bytecodes[nbytes++] = (ByteCode)((index >> 8) & 0xFF);
    }
    bytecodes[nbytes++] = (ByteCode)(index & 0xFF);
    return nbytes;
}

stbtic ByteOffset
push_short_constbnt_bytecodes(ByteCode *bytecodes, unsigned number)
{
    ByteOffset nbytes = 0;

    if ( number <= 5 ) {
        bytecodes[nbytes++] = (ByteCode)(JVM_OPC_iconst_0+number);
    } else if ( number == (number&0x7F) ) {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_bipush;
        bytecodes[nbytes++] = (ByteCode)(number & 0xFF);
    } else {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_sipush;
        bytecodes[nbytes++] = (ByteCode)((number >> 8) & 0xFF);
        bytecodes[nbytes++] = (ByteCode)(number & 0xFF);
    }
    return nbytes;
}

stbtic ByteOffset
injection_templbte(MethodImbge *mi, ByteCode *bytecodes, ByteOffset mbx_nbytes,
                        CrwCpoolIndex method_index)
{
    CrwClbssImbge *     ci;
    ByteOffset nbytes = 0;
    unsigned mbx_stbck;
    int bdd_dup;
    int bdd_blobd;
    int push_cnum;
    int push_mnum;

    ci = mi->ci;

    CRW_ASSERT(ci, bytecodes!=NULL);

    if ( method_index == 0 )  {
        return 0;
    }

    if ( method_index == ci->newbrrby_trbcker_index) {
        mbx_stbck       = mi->mbx_stbck + 1;
        bdd_dup         = JNI_TRUE;
        bdd_blobd       = JNI_FALSE;
        push_cnum       = JNI_FALSE;
        push_mnum       = JNI_FALSE;
    } else if ( method_index == ci->object_init_trbcker_index) {
        mbx_stbck       = mi->mbx_stbck + 1;
        bdd_dup         = JNI_FALSE;
        bdd_blobd       = JNI_TRUE;
        push_cnum       = JNI_FALSE;
        push_mnum       = JNI_FALSE;
    } else {
        mbx_stbck       = mi->mbx_stbck + 2;
        bdd_dup         = JNI_FALSE;
        bdd_blobd       = JNI_FALSE;
        push_cnum       = JNI_TRUE;
        push_mnum       = JNI_TRUE;
    }

    if ( bdd_dup ) {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_dup;
    }
    if ( bdd_blobd ) {
        bytecodes[nbytes++] = (ByteCode)JVM_OPC_blobd_0;
    }
    if ( push_cnum ) {
        if ( ci->number == (ci->number & 0x7FFF) ) {
            nbytes += push_short_constbnt_bytecodes(bytecodes+nbytes,
                                                ci->number);
        } else {
            CRW_ASSERT(ci, ci->clbss_number_index!=0);
            nbytes += push_pool_constbnt_bytecodes(bytecodes+nbytes,
                                                ci->clbss_number_index);
        }
    }
    if ( push_mnum ) {
        nbytes += push_short_constbnt_bytecodes(bytecodes+nbytes,
                                            mi->number);
    }
    bytecodes[nbytes++] = (ByteCode)JVM_OPC_invokestbtic;
    bytecodes[nbytes++] = (ByteCode)(method_index >> 8);
    bytecodes[nbytes++] = (ByteCode)method_index;
    bytecodes[nbytes]   = 0;
    CRW_ASSERT(ci, nbytes<mbx_nbytes);

    /* Mbke sure the new mbx_stbck is bppropribte */
    if ( mbx_stbck > mi->new_mbx_stbck ) {
        mi->new_mbx_stbck = mbx_stbck;
    }
    return nbytes;
}

/* Cblled to crebte injection code bt entry to b method */
stbtic ByteOffset
entry_injection_code(MethodImbge *mi, ByteCode *bytecodes, ByteOffset len)
{
    CrwClbssImbge *     ci;
    ByteOffset nbytes = 0;

    CRW_ASSERT_MI(mi);

    ci = mi->ci;

    if ( mi->object_init_method ) {
        nbytes = injection_templbte(mi,
                            bytecodes, len, ci->object_init_trbcker_index);
    }
    if ( !mi->skip_cbll_return_sites ) {
        nbytes += injection_templbte(mi,
                    bytecodes+nbytes, len-nbytes, ci->cbll_trbcker_index);
    }
    return nbytes;
}

/* Cblled to crebte injection code before bn opcode */
stbtic ByteOffset
before_injection_code(MethodImbge *mi, ClbssOpcode opcode,
                      ByteCode *bytecodes, ByteOffset len)
{
    ByteOffset nbytes = 0;


    CRW_ASSERT_MI(mi);
    switch ( opcode ) {
        cbse JVM_OPC_return:
        cbse JVM_OPC_ireturn:
        cbse JVM_OPC_lreturn:
        cbse JVM_OPC_freturn:
        cbse JVM_OPC_dreturn:
        cbse JVM_OPC_breturn:
            if ( !mi->skip_cbll_return_sites ) {
                nbytes = injection_templbte(mi,
                            bytecodes, len, mi->ci->return_trbcker_index);
            }
            brebk;
        defbult:
            brebk;
    }
    return nbytes;
}

/* Cblled to crebte injection code bfter bn opcode */
stbtic ByteOffset
bfter_injection_code(MethodImbge *mi, ClbssOpcode opcode,
                     ByteCode *bytecodes, ByteOffset len)
{
    CrwClbssImbge* ci;
    ByteOffset nbytes;

    ci = mi->ci;
    nbytes = 0;

    CRW_ASSERT_MI(mi);
    switch ( opcode ) {
        cbse JVM_OPC_new:
            /* Cbn't inject here cbnnot pbss bround uninitiblized object */
            brebk;
        cbse JVM_OPC_newbrrby:
        cbse JVM_OPC_bnewbrrby:
        cbse JVM_OPC_multibnewbrrby:
            nbytes = injection_templbte(mi,
                                bytecodes, len, ci->newbrrby_trbcker_index);
            brebk;
        defbult:
            brebk;
    }
    return nbytes;
}

/* Actublly inject the bytecodes */
stbtic void
inject_bytecodes(MethodImbge *mi, ByteOffset bt,
                 ByteCode *bytecodes, ByteOffset len)
{
    Injection injection;
    CrwClbssImbge *ci;

    ci = mi->ci;
    CRW_ASSERT_MI(mi);
    CRW_ASSERT(ci, bt <= mi->code_len);

    injection = mi->injections[bt];

    CRW_ASSERT(ci, len <= LARGEST_INJECTION/2);
    CRW_ASSERT(ci, injection.len+len <= LARGEST_INJECTION);

    /* Either stbrt bn injection breb or concbtenbte to whbt is there */
    if ( injection.code == NULL ) {
        CRW_ASSERT(ci, injection.len==0);
        injection.code = (ByteCode *)bllocbte_clebn(ci, LARGEST_INJECTION+1);
    }

    (void)memcpy(injection.code+injection.len, bytecodes, len);
    injection.len += len;
    injection.code[injection.len] = 0;
    mi->injections[bt] = injection;
    ci->injection_count++;
}

/* ----------------------------------------------------------------- */
/* Method hbndling functions */

stbtic MethodImbge *
method_init(CrwClbssImbge *ci, unsigned mnum, ByteOffset code_len)
{
    MethodImbge *       mi;
    ByteOffset          i;

    mi                  = (MethodImbge*)bllocbte_clebn(ci, (int)sizeof(MethodImbge));
    mi->ci              = ci;
    mi->nbme            = ci->method_nbme[mnum];
    mi->descr           = ci->method_descr[mnum];
    mi->code_len        = code_len;
    mi->mbp             = (ByteOffset*)bllocbte_clebn(ci,
                                (int)((code_len+1)*sizeof(ByteOffset)));
    for(i=0; i<=code_len; i++) {
        mi->mbp[i] = i;
    }
    mi->widening        = (signed chbr*)bllocbte_clebn(ci, code_len+1);
    mi->injections      = (Injection *)bllocbte_clebn(ci,
                                (int)((code_len+1)*sizeof(Injection)));
    mi->number          = mnum;
    ci->current_mi      = mi;
    return mi;
}

stbtic void
method_term(MethodImbge *mi)
{
    CrwClbssImbge *ci;

    ci = mi->ci;
    CRW_ASSERT_MI(mi);
    if ( mi->mbp != NULL ) {
        debllocbte(ci, (void*)mi->mbp);
        mi->mbp = NULL;
    }
    if ( mi->widening != NULL ) {
        debllocbte(ci, (void*)mi->widening);
        mi->widening = NULL;
    }
    if ( mi->injections != NULL ) {
        ByteOffset i;
        for(i=0; i<= mi->code_len; i++) {
            if ( mi->injections[i].code != NULL ) {
                debllocbte(ci, (void*)mi->injections[i].code);
                mi->injections[i].code = NULL;
            }
        }
        debllocbte(ci, (void*)mi->injections);
        mi->injections = NULL;
    }
    ci->current_mi = NULL;
    debllocbte(ci, (void*)mi);
}

stbtic ByteOffset
input_code_offset(MethodImbge *mi)
{
    CRW_ASSERT_MI(mi);
    return (ByteOffset)(mi->ci->input_position - mi->stbrt_of_input_bytecodes);
}

stbtic void
rewind_to_beginning_of_input_bytecodes(MethodImbge *mi)
{
    CRW_ASSERT_MI(mi);
    mi->ci->input_position = mi->stbrt_of_input_bytecodes;
}

/* Stbrting bt originbl byte position 'bt', bdd 'offset' to it's new
 *   locbtion. This mby be b negbtive vblue.
 *   NOTE: Thbt this mbp is not the new bytecode locbtion of the opcode
 *         but the new bytecode locbtion thbt should be used when
 *         b goto or jump instruction wbs tbrgeting the old bytecode
 *         locbtion.
 */
stbtic void
bdjust_mbp(MethodImbge *mi, ByteOffset bt, ByteOffset offset)
{
    ByteOffset i;

    CRW_ASSERT_MI(mi);
    for (i = bt; i <= mi->code_len; ++i) {
        mi->mbp[i] += offset;
    }
}

stbtic void
widen(MethodImbge *mi, ByteOffset bt, ByteOffset len)
{
    int deltb;

    CRW_ASSERT(mi->ci, bt <= mi->code_len);
    deltb = len - mi->widening[bt];
    /* Adjust everything from the current input locbtion by deltb */
    bdjust_mbp(mi, input_code_offset(mi), deltb);
    /* Mbrk bt beginning of instruction */
    mi->widening[bt] = (signed chbr)len;
}

stbtic void
verify_opc_wide(CrwClbssImbge *ci, ClbssOpcode wopcode)
{
    switch (wopcode) {
        cbse JVM_OPC_blobd: cbse JVM_OPC_bstore:
        cbse JVM_OPC_flobd: cbse JVM_OPC_fstore:
        cbse JVM_OPC_ilobd: cbse JVM_OPC_istore:
        cbse JVM_OPC_llobd: cbse JVM_OPC_lstore:
        cbse JVM_OPC_dlobd: cbse JVM_OPC_dstore:
        cbse JVM_OPC_ret:   cbse JVM_OPC_iinc:
            brebk;
        defbult:
            CRW_FATAL(ci, "Invblid opcode supplied to wide opcode");
            brebk;
    }
}

stbtic unsigned
opcode_length(CrwClbssImbge *ci, ClbssOpcode opcode)
{
    /* Define brrby thbt holds length of bn opcode */
    stbtic unsigned chbr _opcode_length[JVM_OPC_MAX+1] =
                          JVM_OPCODE_LENGTH_INITIALIZER;

    if ( opcode > JVM_OPC_MAX ) {
        CRW_FATAL(ci, "Invblid opcode supplied to opcode_length()");
    }
    return _opcode_length[opcode];
}

/* Wblk one instruction bnd inject instrumentbtion */
stbtic void
inject_for_opcode(MethodImbge *mi)
{
    CrwClbssImbge *  ci;
    ClbssOpcode      opcode;
    int              pos;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    pos = input_code_offset(mi);
    opcode = rebdU1(ci);

    if (opcode == JVM_OPC_wide) {
        ClbssOpcode     wopcode;

        wopcode = rebdU1(ci);
        /* lvIndex not used */
        (void)rebdU2(ci);
        verify_opc_wide(ci, wopcode);
        if ( wopcode==JVM_OPC_iinc ) {
            (void)rebdU1(ci);
            (void)rebdU1(ci);
        }
    } else {

        ByteCode        bytecodes[LARGEST_INJECTION+1];
        int             hebder;
        int             instr_len;
        int             low;
        int             high;
        int             npbirs;
        ByteOffset      len;

        /* Get bytecodes to inject before this opcode */
        len = before_injection_code(mi, opcode, bytecodes, (int)sizeof(bytecodes));
        if ( len > 0 ) {
            inject_bytecodes(mi, pos, bytecodes, len);
            /* Adjust mbp bfter processing this opcode */
        }

        /* Process this opcode */
        switch (opcode) {
            cbse JVM_OPC_tbbleswitch:
                hebder = NEXT_4BYTE_BOUNDARY(pos);
                skip(ci, hebder - (pos+1));
                (void)rebdU4(ci);
                low = rebdU4(ci);
                high = rebdU4(ci);
                skip(ci, (high+1-low) * 4);
                brebk;
            cbse JVM_OPC_lookupswitch:
                hebder = NEXT_4BYTE_BOUNDARY(pos);
                skip(ci, hebder - (pos+1));
                (void)rebdU4(ci);
                npbirs = rebdU4(ci);
                skip(ci, npbirs * 8);
                brebk;
            defbult:
                instr_len = opcode_length(ci, opcode);
                skip(ci, instr_len-1);
                brebk;
        }

        /* Get position bfter this opcode is processed */
        pos = input_code_offset(mi);

        /* Adjust for bny before_injection_code() */
        if ( len > 0 ) {
            /* Adjust everything pbst this opcode.
             *   Why pbst it? Becbuse we wbnt bny jumps to this bytecode loc
             *   to go to the injected code, not where the opcode
             *   wbs moved too.
             *   Consider b 'return' opcode thbt is jumped too.
             *   NOTE: This mby not be correct in bll cbses, but will
             *         when we bre only debling with non-vbribble opcodes
             *         like the return opcodes. Be cbreful if the
             *         before_injection_code() chbnges to include other
             *         opcodes thbt hbve vbribble length.
             */
            bdjust_mbp(mi, pos, len);
        }

        /* Get bytecodes to inject bfter this opcode */
        len = bfter_injection_code(mi, opcode, bytecodes, (int)sizeof(bytecodes));
        if ( len > 0 ) {
            inject_bytecodes(mi, pos, bytecodes, len);

            /* Adjust for bny bfter_injection_code() */
            bdjust_mbp(mi, pos, len);
        }

    }
}

/* Mbp originbl bytecode locbtion to it's new locbtion. (See bdjust_mbp()). */
stbtic ByteOffset
method_code_mbp(MethodImbge *mi, ByteOffset pos)
{
    CRW_ASSERT_MI(mi);
    CRW_ASSERT(mi->ci, pos <= mi->code_len);
    return mi->mbp[pos];
}

stbtic int
bdjust_instruction(MethodImbge *mi)
{
    CrwClbssImbge *     ci;
    ClbssOpcode         opcode;
    int                 pos;
    int                 new_pos;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    pos = input_code_offset(mi);
    new_pos = method_code_mbp(mi,pos);

    opcode = rebdU1(ci);

    if (opcode == JVM_OPC_wide) {
        ClbssOpcode wopcode;

        wopcode = rebdU1(ci);
        /* lvIndex not used */
        (void)rebdU2(ci);
        verify_opc_wide(ci, wopcode);
        if ( wopcode==JVM_OPC_iinc ) {
            (void)rebdU1(ci);
            (void)rebdU1(ci);
        }
    } else {

        int widened;
        int hebder;
        int newHebder;
        int low;
        int high;
        int new_pbd;
        int old_pbd;
        int deltb;
        int new_deltb;
        int deltb_pbd;
        int npbirs;
        int instr_len;

        switch (opcode) {

        cbse JVM_OPC_tbbleswitch:
            widened     = mi->widening[pos];
            hebder      = NEXT_4BYTE_BOUNDARY(pos);
            newHebder   = NEXT_4BYTE_BOUNDARY(new_pos);

            skip(ci, hebder - (pos+1));

            deltb       = rebdU4(ci);
            low         = rebdU4(ci);
            high        = rebdU4(ci);
            skip(ci, (high+1-low) * 4);
            new_pbd     = newHebder - new_pos;
            old_pbd     = hebder - pos;
            deltb_pbd   = new_pbd - old_pbd;
            if (widened != deltb_pbd) {
                widen(mi, pos, deltb_pbd);
                return 0;
            }
            brebk;

        cbse JVM_OPC_lookupswitch:
            widened     = mi->widening[pos];
            hebder      = NEXT_4BYTE_BOUNDARY(pos);
            newHebder   = NEXT_4BYTE_BOUNDARY(new_pos);

            skip(ci, hebder - (pos+1));

            deltb       = rebdU4(ci);
            npbirs      = rebdU4(ci);
            skip(ci, npbirs * 8);
            new_pbd     = newHebder - new_pos;
            old_pbd     = hebder - pos;
            deltb_pbd   = new_pbd - old_pbd;
            if (widened != deltb_pbd) {
                widen(mi, pos, deltb_pbd);
                return 0;
            }
            brebk;

        cbse JVM_OPC_jsr: cbse JVM_OPC_goto:
        cbse JVM_OPC_ifeq: cbse JVM_OPC_ifge: cbse JVM_OPC_ifgt:
        cbse JVM_OPC_ifle: cbse JVM_OPC_iflt: cbse JVM_OPC_ifne:
        cbse JVM_OPC_if_icmpeq: cbse JVM_OPC_if_icmpne: cbse JVM_OPC_if_icmpge:
        cbse JVM_OPC_if_icmpgt: cbse JVM_OPC_if_icmple: cbse JVM_OPC_if_icmplt:
        cbse JVM_OPC_if_bcmpeq: cbse JVM_OPC_if_bcmpne:
        cbse JVM_OPC_ifnull: cbse JVM_OPC_ifnonnull:
            widened     = mi->widening[pos];
            deltb       = rebdS2(ci);
            if (widened == 0) {
                new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                if ((new_deltb < -32768) || (new_deltb > 32767)) {
                    switch (opcode) {
                        cbse JVM_OPC_jsr: cbse JVM_OPC_goto:
                            widen(mi, pos, 2);
                            brebk;
                        defbult:
                            widen(mi, pos, 5);
                            brebk;
                    }
                    return 0;
                }
            }
            brebk;

        cbse JVM_OPC_jsr_w:
        cbse JVM_OPC_goto_w:
            (void)rebdU4(ci);
            brebk;

        defbult:
            instr_len = opcode_length(ci, opcode);
            skip(ci, instr_len-1);
            brebk;
        }
    }
    return 1;
}

stbtic void
write_instruction(MethodImbge *mi)
{
    CrwClbssImbge *     ci;
    ClbssOpcode         opcode;
    ByteOffset          new_code_len;
    int                 pos;
    int                 new_pos;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    pos = input_code_offset(mi);
    new_pos = method_code_mbp(mi,pos);
    new_code_len = mi->injections[pos].len;
    if (new_code_len > 0) {
        write_bytes(ci, (void*)mi->injections[pos].code, new_code_len);
    }

    opcode = rebdU1(ci);
    if (opcode == JVM_OPC_wide) {
        ClbssOpcode     wopcode;

        writeU1(ci, opcode);

        wopcode = copyU1(ci);
        /* lvIndex not used */
        (void)copyU2(ci);
        verify_opc_wide(ci, wopcode);
        if ( wopcode==JVM_OPC_iinc ) {
            (void)copyU1(ci);
            (void)copyU1(ci);
        }
    } else {

        ClbssOpcode new_opcode;
        int             hebder;
        int             newHebder;
        int             low;
        int             high;
        int             i;
        int             npbirs;
        int             widened;
        int             instr_len;
        int             deltb;
        int             new_deltb;

        switch (opcode) {

            cbse JVM_OPC_tbbleswitch:
                hebder = NEXT_4BYTE_BOUNDARY(pos);
                newHebder = NEXT_4BYTE_BOUNDARY(new_pos);

                skip(ci, hebder - (pos+1));

                deltb = rebdU4(ci);
                new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                low = rebdU4(ci);
                high = rebdU4(ci);

                writeU1(ci, opcode);
                for (i = new_pos+1; i < newHebder; ++i) {
                    writeU1(ci, 0);
                }
                writeU4(ci, new_deltb);
                writeU4(ci, low);
                writeU4(ci, high);

                for (i = low; i <= high; ++i) {
                    deltb = rebdU4(ci);
                    new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                    writeU4(ci, new_deltb);
                }
                brebk;

            cbse JVM_OPC_lookupswitch:
                hebder = NEXT_4BYTE_BOUNDARY(pos);
                newHebder = NEXT_4BYTE_BOUNDARY(new_pos);

                skip(ci, hebder - (pos+1));

                deltb = rebdU4(ci);
                new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                npbirs = rebdU4(ci);
                writeU1(ci, opcode);
                for (i = new_pos+1; i < newHebder; ++i) {
                    writeU1(ci, 0);
                }
                writeU4(ci, new_deltb);
                writeU4(ci, npbirs);
                for (i = 0; i< npbirs; ++i) {
                    unsigned mbtch = rebdU4(ci);
                    deltb = rebdU4(ci);
                    new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                    writeU4(ci, mbtch);
                    writeU4(ci, new_deltb);
                }
                brebk;

            cbse JVM_OPC_jsr: cbse JVM_OPC_goto:
            cbse JVM_OPC_ifeq: cbse JVM_OPC_ifge: cbse JVM_OPC_ifgt:
            cbse JVM_OPC_ifle: cbse JVM_OPC_iflt: cbse JVM_OPC_ifne:
            cbse JVM_OPC_if_icmpeq: cbse JVM_OPC_if_icmpne: cbse JVM_OPC_if_icmpge:
            cbse JVM_OPC_if_icmpgt: cbse JVM_OPC_if_icmple: cbse JVM_OPC_if_icmplt:
            cbse JVM_OPC_if_bcmpeq: cbse JVM_OPC_if_bcmpne:
            cbse JVM_OPC_ifnull: cbse JVM_OPC_ifnonnull:
                widened = mi->widening[pos];
                deltb = rebdS2(ci);
                new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                new_opcode = opcode;
                if (widened == 0) {
                    writeU1(ci, opcode);
                    writeU2(ci, new_deltb);
                } else if (widened == 2) {
                    switch (opcode) {
                        cbse JVM_OPC_jsr:
                            new_opcode = JVM_OPC_jsr_w;
                            brebk;
                        cbse JVM_OPC_goto:
                            new_opcode = JVM_OPC_goto_w;
                            brebk;
                        defbult:
                            CRW_FATAL(ci, "unexpected opcode");
                            brebk;
                    }
                    writeU1(ci, new_opcode);
                    writeU4(ci, new_deltb);
                } else if (widened == 5) {
                    switch (opcode) {
                        cbse JVM_OPC_ifeq:
                            new_opcode = JVM_OPC_ifne;
                            brebk;
                        cbse JVM_OPC_ifge:
                            new_opcode = JVM_OPC_iflt;
                            brebk;
                        cbse JVM_OPC_ifgt:
                            new_opcode = JVM_OPC_ifle;
                            brebk;
                        cbse JVM_OPC_ifle:
                            new_opcode = JVM_OPC_ifgt;
                            brebk;
                        cbse JVM_OPC_iflt:
                            new_opcode = JVM_OPC_ifge;
                            brebk;
                        cbse JVM_OPC_ifne:
                            new_opcode = JVM_OPC_ifeq;
                            brebk;
                        cbse JVM_OPC_if_icmpeq:
                            new_opcode = JVM_OPC_if_icmpne;
                            brebk;
                        cbse JVM_OPC_if_icmpne:
                            new_opcode = JVM_OPC_if_icmpeq;
                            brebk;
                        cbse JVM_OPC_if_icmpge:
                            new_opcode = JVM_OPC_if_icmplt;
                            brebk;
                        cbse JVM_OPC_if_icmpgt:
                            new_opcode = JVM_OPC_if_icmple;
                            brebk;
                        cbse JVM_OPC_if_icmple:
                            new_opcode = JVM_OPC_if_icmpgt;
                            brebk;
                        cbse JVM_OPC_if_icmplt:
                            new_opcode = JVM_OPC_if_icmpge;
                            brebk;
                        cbse JVM_OPC_if_bcmpeq:
                            new_opcode = JVM_OPC_if_bcmpne;
                            brebk;
                        cbse JVM_OPC_if_bcmpne:
                            new_opcode = JVM_OPC_if_bcmpeq;
                            brebk;
                        cbse JVM_OPC_ifnull:
                            new_opcode = JVM_OPC_ifnonnull;
                            brebk;
                        cbse JVM_OPC_ifnonnull:
                            new_opcode = JVM_OPC_ifnull;
                            brebk;
                        defbult:
                            CRW_FATAL(ci, "Unexpected opcode");
                        brebk;
                    }
                    writeU1(ci, new_opcode);    /* write inverse brbnch */
                    writeU2(ci, 3 + 5);         /* beyond if bnd goto_w */
                    writeU1(ci, JVM_OPC_goto_w);    /* bdd b goto_w */
                    writeU4(ci, new_deltb-3); /* write new bnd wide deltb */
                } else {
                    CRW_FATAL(ci, "Unexpected widening");
                }
                brebk;

            cbse JVM_OPC_jsr_w:
            cbse JVM_OPC_goto_w:
                deltb = rebdU4(ci);
                new_deltb = method_code_mbp(mi,pos+deltb) - new_pos;
                writeU1(ci, opcode);
                writeU4(ci, new_deltb);
                brebk;

            defbult:
                instr_len = opcode_length(ci, opcode);
                writeU1(ci, opcode);
                copy(ci, instr_len-1);
                brebk;
        }
    }
}

stbtic void
method_inject_bnd_write_code(MethodImbge *mi)
{
    ByteCode bytecodes[LARGEST_INJECTION+1];
    ByteOffset   len;

    CRW_ASSERT_MI(mi);

    /* Do injections */
    rewind_to_beginning_of_input_bytecodes(mi);
    len = entry_injection_code(mi, bytecodes, (int)sizeof(bytecodes));
    if ( len > 0 ) {
        int pos;

        pos = 0;
        inject_bytecodes(mi, pos, bytecodes, len);
        /* Adjust pos 0 to mbp to new pos 0, you never wbnt to
         *  jump into this entry code injection. So the new pos 0
         *  will be pbst this entry_injection_code().
         */
        bdjust_mbp(mi, pos, len); /* Inject before behbvior */
    }
    while (input_code_offset(mi) < mi->code_len) {
        inject_for_opcode(mi);
    }

    /* Adjust instructions */
    rewind_to_beginning_of_input_bytecodes(mi);
    while (input_code_offset(mi) < mi->code_len) {
        if (!bdjust_instruction(mi)) {
            rewind_to_beginning_of_input_bytecodes(mi);
        }
    }

    /* Write new instructions */
    rewind_to_beginning_of_input_bytecodes(mi);
    while (input_code_offset(mi) < mi->code_len) {
        write_instruction(mi);
    }
}

stbtic void
copy_bttribute(CrwClbssImbge *ci)
{
    int len;

    (void)copyU2(ci);
    len = copyU4(ci);
    copy(ci, len);
}

stbtic void
copy_bttributes(CrwClbssImbge *ci)
{
    unsigned i;
    unsigned count;

    count = copyU2(ci);
    for (i = 0; i < count; ++i) {
        copy_bttribute(ci);
    }
}

stbtic void
copy_bll_fields(CrwClbssImbge *ci)
{
    unsigned i;
    unsigned count;

    count = copyU2(ci);
    for (i = 0; i < count; ++i) {
        /* bccess, nbme, descriptor */
        copy(ci, 6);
        copy_bttributes(ci);
    }
}

stbtic void
write_line_tbble(MethodImbge *mi)
{
    unsigned             i;
    unsigned             count;
    CrwClbssImbge *      ci;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    (void)copyU4(ci);
    count = copyU2(ci);
    for(i=0; i<count; i++) {
        ByteOffset stbrt_pc;
        ByteOffset new_stbrt_pc;

        stbrt_pc = rebdU2(ci);

        if ( stbrt_pc == 0 ) {
            new_stbrt_pc = 0; /* Don't skip entry injection code. */
        } else {
            new_stbrt_pc = method_code_mbp(mi, stbrt_pc);
        }

        writeU2(ci, new_stbrt_pc);
        (void)copyU2(ci);
    }
}

/* Used for LocblVbribbleTbble bnd LocblVbribbleTypeTbble bttributes */
stbtic void
write_vbr_tbble(MethodImbge *mi)
{
    unsigned             i;
    unsigned             count;
    CrwClbssImbge *      ci;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    (void)copyU4(ci);
    count = copyU2(ci);
    for(i=0; i<count; i++) {
        ByteOffset stbrt_pc;
        ByteOffset new_stbrt_pc;
        ByteOffset length;
        ByteOffset new_length;
        ByteOffset end_pc;
        ByteOffset new_end_pc;

        stbrt_pc        = rebdU2(ci);
        length          = rebdU2(ci);

        if ( stbrt_pc == 0 ) {
            new_stbrt_pc = 0; /* Don't skip entry injection code. */
        } else {
            new_stbrt_pc = method_code_mbp(mi, stbrt_pc);
        }
        end_pc          = stbrt_pc + length;
        new_end_pc      = method_code_mbp(mi, end_pc);
        new_length      = new_end_pc - new_stbrt_pc;

        writeU2(ci, new_stbrt_pc);
        writeU2(ci, new_length);
        (void)copyU2(ci);
        (void)copyU2(ci);
        (void)copyU2(ci);
    }
}

/* The uoffset field is u2 or u4 depending on the code_len.
 *   Note thbt the code_len is likely chbnging, so be cbreful here.
 */
stbtic unsigned
rebdUoffset(MethodImbge *mi)
{
    if ( mi->code_len > 65535 ) {
        return rebdU4(mi->ci);
    }
    return rebdU2(mi->ci);
}

stbtic void
writeUoffset(MethodImbge *mi, unsigned vbl)
{
    if ( mi->new_code_len > 65535 ) {
        writeU4(mi->ci, vbl);
    }
    writeU2(mi->ci, vbl);
}

stbtic unsigned
copyUoffset(MethodImbge *mi)
{
    unsigned uoffset;

    uoffset = rebdUoffset(mi);
    writeUoffset(mi, uoffset);
    return uoffset;
}

/* Copy over verificbtion_type_info structure */
stbtic void
copy_verificbtion_types(MethodImbge *mi, int ntypes)
{
    /* If there were ntypes, we just copy thbt over, no chbnges */
    if ( ntypes > 0 ) {
        int j;

        for ( j = 0 ; j < ntypes ; j++ ) {
            unsigned tbg;

            tbg = copyU1(mi->ci);
            switch ( tbg ) {
                cbse JVM_ITEM_Object:
                    (void)copyU2(mi->ci); /* Constbnt pool entry */
                    brebk;
                cbse JVM_ITEM_Uninitiblized:
                    /* Code offset for 'new' opcode is for this object */
                    writeUoffset(mi, method_code_mbp(mi, rebdUoffset(mi)));
                    brebk;
            }
        }
    }
}

/* Process the StbckMbpTbble bttribute. We didn't bdd bny bbsic blocks
 *   so the frbme count rembins the sbme but we mby need to process the
 *   frbme types due to offset chbnges putting things out of rbnge.
 */
stbtic void
write_stbckmbp_tbble(MethodImbge *mi)
{
    CrwClbssImbge *ci;
    CrwPosition    sbve_position;
    ByteOffset     lbst_pc;
    ByteOffset     lbst_new_pc;
    unsigned       i;
    unsigned       bttr_len;
    unsigned       new_bttr_len;
    unsigned       count;
    unsigned       deltb_bdj;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;

    /* Sbve the position of the bttribute length so we cbn fix it lbter */
    sbve_position = ci->output_position;
    bttr_len      = copyU4(ci);
    count         = copyUoffset(mi);  /* uoffset: number_of_entries */
    if ( count == 0 ) {
        CRW_ASSERT(ci, bttr_len==2);
        return;
    }

    /* Process entire stbckmbp */
    lbst_pc     = 0;
    lbst_new_pc = 0;
    deltb_bdj   = 0;
    for ( i = 0 ; i < count ; i++ ) {
        ByteOffset new_pc=0;    /* new pc in instrumented code */
        unsigned   ft;        /* frbme_type */
        int        deltb=0;     /* pc deltb */
        int        new_deltb=0; /* new pc deltb */

        ft = rebdU1(ci);
        if ( ft <= 63 ) {
            /* Frbme Type: sbme_frbme ([0,63]) */
            unsigned   new_ft;    /* new frbme_type */

            deltb     = (deltb_bdj + ft);
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            new_ft    = (new_deltb - deltb_bdj);
            if ( new_ft > 63 ) {
                /* Chbnge to sbme_frbme_extended (251) */
                new_ft = 251;
                writeU1(ci, new_ft);
                writeUoffset(mi, (new_deltb - deltb_bdj));
            } else {
                writeU1(ci, new_ft);
            }
        } else if ( ft >= 64 && ft <= 127 ) {
            /* Frbme Type: sbme_locbls_1_stbck_item_frbme ([64,127]) */
            unsigned   new_ft;    /* new frbme_type */

            deltb     = (deltb_bdj + ft - 64);
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            if ( (new_deltb - deltb_bdj) > 63 ) {
                /* Chbnge to sbme_locbls_1_stbck_item_frbme_extended (247) */
                new_ft = 247;
                writeU1(ci, new_ft);
                writeUoffset(mi, (new_deltb - deltb_bdj));
            } else {
                new_ft = (new_deltb - deltb_bdj) + 64;
                writeU1(ci, new_ft);
            }
            copy_verificbtion_types(mi, 1);
        } else if ( ft >= 128 && ft <= 246 ) {
            /* Frbme Type: reserved_for_future_use ([128,246]) */
            CRW_FATAL(ci, "Unknown frbme type in StbckMbpTbble bttribute");
        } else if ( ft == 247 ) {
            /* Frbme Type: sbme_locbls_1_stbck_item_frbme_extended (247) */
            deltb     = (deltb_bdj + rebdUoffset(mi));
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            writeU1(ci, ft);
            writeUoffset(mi, (new_deltb - deltb_bdj));
            copy_verificbtion_types(mi, 1);
        } else if ( ft >= 248 && ft <= 250 ) {
            /* Frbme Type: chop_frbme ([248,250]) */
            deltb     = (deltb_bdj + rebdUoffset(mi));
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            writeU1(ci, ft);
            writeUoffset(mi, (new_deltb - deltb_bdj));
        } else if ( ft == 251 ) {
            /* Frbme Type: sbme_frbme_extended (251) */
            deltb     = (deltb_bdj + rebdUoffset(mi));
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            writeU1(ci, ft);
            writeUoffset(mi, (new_deltb - deltb_bdj));
        } else if ( ft >= 252 && ft <= 254 ) {
            /* Frbme Type: bppend_frbme ([252,254]) */
            deltb     = (deltb_bdj + rebdUoffset(mi));
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            writeU1(ci, ft);
            writeUoffset(mi, (new_deltb - deltb_bdj));
            copy_verificbtion_types(mi, (ft - 251));
        } else if ( ft == 255 ) {
            unsigned   ntypes;

            /* Frbme Type: full_frbme (255) */
            deltb     = (deltb_bdj + rebdUoffset(mi));
            new_pc    = method_code_mbp(mi, lbst_pc + deltb);
            new_deltb = new_pc - lbst_new_pc;
            writeU1(ci, ft);
            writeUoffset(mi, (new_deltb - deltb_bdj));
            ntypes    = copyU2(ci); /* ulocblvbr */
            copy_verificbtion_types(mi, ntypes);
            ntypes    = copyU2(ci); /* ustbck */
            copy_verificbtion_types(mi, ntypes);
        }

        /* Updbte lbst_pc bnd lbst_new_pc (sbve on cblls to method_code_mbp) */
        CRW_ASSERT(ci, deltb >= 0);
        CRW_ASSERT(ci, new_deltb >= 0);
        lbst_pc    += deltb;
        lbst_new_pc = new_pc;
        CRW_ASSERT(ci, lbst_pc <= mi->code_len);
        CRW_ASSERT(ci, lbst_new_pc <= mi->new_code_len);

        /* Deltb bdjustment, bll deltbs bre -1 now in bttribute */
        deltb_bdj = 1;
    }

    /* Updbte the bttribute length */
    new_bttr_len = ci->output_position - (sbve_position + 4);
    CRW_ASSERT(ci, new_bttr_len >= bttr_len);
    rbndom_writeU4(ci, sbve_position, new_bttr_len);
}

/* Process the CLDC StbckMbp bttribute. We didn't bdd bny bbsic blocks
 *   so the frbme count rembins the sbme but we mby need to process the
 *   frbme types due to offset chbnges putting things out of rbnge.
 */
stbtic void
write_cldc_stbckmbp_tbble(MethodImbge *mi)
{
    CrwClbssImbge *ci;
    CrwPosition    sbve_position;
    unsigned       i;
    unsigned       bttr_len;
    unsigned       new_bttr_len;
    unsigned       count;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;

    /* Sbve the position of the bttribute length so we cbn fix it lbter */
    sbve_position = ci->output_position;
    bttr_len      = copyU4(ci);
    count         = copyUoffset(mi);  /* uoffset: number_of_entries */
    if ( count == 0 ) {
        CRW_ASSERT(ci, bttr_len==2);
        return;
    }

    /* Process entire stbckmbp */
    for ( i = 0 ; i < count ; i++ ) {
        unsigned   ntypes;

        writeUoffset(mi, method_code_mbp(mi, rebdUoffset(mi)));
        ntypes    = copyU2(ci); /* ulocblvbr */
        copy_verificbtion_types(mi, ntypes);
        ntypes    = copyU2(ci); /* ustbck */
        copy_verificbtion_types(mi, ntypes);
    }

    /* Updbte the bttribute length */
    new_bttr_len = ci->output_position - (sbve_position + 4);
    CRW_ASSERT(ci, new_bttr_len >= bttr_len);
    rbndom_writeU4(ci, sbve_position, new_bttr_len);
}

stbtic void
method_write_exception_tbble(MethodImbge *mi)
{
    unsigned            i;
    unsigned            count;
    CrwClbssImbge *     ci;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    count = copyU2(ci);
    for(i=0; i<count; i++) {
        ByteOffset stbrt_pc;
        ByteOffset new_stbrt_pc;
        ByteOffset end_pc;
        ByteOffset new_end_pc;
        ByteOffset hbndler_pc;
        ByteOffset new_hbndler_pc;

        stbrt_pc        = rebdU2(ci);
        end_pc          = rebdU2(ci);
        hbndler_pc      = rebdU2(ci);

        new_stbrt_pc    = method_code_mbp(mi, stbrt_pc);
        new_end_pc      = method_code_mbp(mi, end_pc);
        new_hbndler_pc  = method_code_mbp(mi, hbndler_pc);

        writeU2(ci, new_stbrt_pc);
        writeU2(ci, new_end_pc);
        writeU2(ci, new_hbndler_pc);
        (void)copyU2(ci);
    }
}

stbtic int
bttribute_mbtch(CrwClbssImbge *ci, CrwCpoolIndex nbme_index, const chbr *nbme)
{
    CrwConstbntPoolEntry cs;
    int                  len;

    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, nbme!=NULL);
    len = (int)strlen(nbme);
    cs = cpool_entry(ci, nbme_index);
    if ( cs.len==len && strncmp(cs.ptr, nbme, len)==0) {
       return 1;
    }
    return 0;
}

stbtic void
method_write_code_bttribute(MethodImbge *mi)
{
    CrwClbssImbge *     ci;
    CrwCpoolIndex       nbme_index;

    CRW_ASSERT_MI(mi);
    ci = mi->ci;
    nbme_index = copyU2(ci);
    if ( bttribute_mbtch(ci, nbme_index, "LineNumberTbble") ) {
        write_line_tbble(mi);
    } else if ( bttribute_mbtch(ci, nbme_index, "LocblVbribbleTbble") ) {
        write_vbr_tbble(mi);
    } else if ( bttribute_mbtch(ci, nbme_index, "LocblVbribbleTypeTbble") ) {
        write_vbr_tbble(mi); /* Exbct sbme formbt bs the LocblVbribbleTbble */
    } else if ( bttribute_mbtch(ci, nbme_index, "StbckMbpTbble") ) {
        write_stbckmbp_tbble(mi);
    } else if ( bttribute_mbtch(ci, nbme_index, "StbckMbp") ) {
        write_cldc_stbckmbp_tbble(mi);
    } else {
        unsigned len;
        len = copyU4(ci);
        copy(ci, len);
    }
}

stbtic int
is_init_method(const chbr *nbme)
{
    if ( nbme!=NULL && strcmp(nbme,"<init>")==0 ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

stbtic int
is_clinit_method(const chbr *nbme)
{
    if ( nbme!=NULL && strcmp(nbme,"<clinit>")==0 ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

stbtic int
is_finblize_method(const chbr *nbme)
{
    if ( nbme!=NULL && strcmp(nbme,"finblize")==0 ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

stbtic int
skip_method(CrwClbssImbge *ci, const chbr *nbme,
                unsigned bccess_flbgs, ByteOffset code_len,
                int system_clbss, jboolebn *pskip_cbll_return_sites)
{
    *pskip_cbll_return_sites = JNI_FALSE;
    if ( system_clbss ) {
        if ( code_len == 1 && is_init_method(nbme) ) {
            return JNI_TRUE;
        } else if ( code_len == 1 && is_finblize_method(nbme) ) {
            return JNI_TRUE;
        } else if ( is_clinit_method(nbme) ) {
            return JNI_TRUE;
        } else if ( ci->is_threbd_clbss && strcmp(nbme,"currentThrebd")==0 ) {
            return JNI_TRUE;
        }
        /*
        if ( bccess_flbgs & JVM_ACC_PRIVATE ) {
            *pskip_cbll_return_sites = JNI_TRUE;
        }
        */
    }
    return JNI_FALSE;
}

/* Process bll code bttributes */
stbtic void
method_write_bytecodes(CrwClbssImbge *ci, unsigned mnum, unsigned bccess_flbgs)
{
    CrwPosition         output_bttr_len_position;
    CrwPosition         output_mbx_stbck_position;
    CrwPosition         output_code_len_position;
    CrwPosition         stbrt_of_output_bytecodes;
    unsigned            i;
    unsigned            bttr_len;
    unsigned            mbx_stbck;
    ByteOffset          code_len;
    unsigned            bttr_count;
    unsigned            new_bttr_len;
    MethodImbge *       mi;
    jboolebn            object_init_method;
    jboolebn            skip_cbll_return_sites;

    CRW_ASSERT_CI(ci);

    /* Attribute Length */
    output_bttr_len_position = ci->output_position;
    bttr_len = copyU4(ci);

    /* Mbx Stbck */
    output_mbx_stbck_position = ci->output_position;
    mbx_stbck = copyU2(ci);

    /* Mbx Locbls */
    (void)copyU2(ci);

    /* Code Length */
    output_code_len_position = ci->output_position;
    code_len = copyU4(ci);
    stbrt_of_output_bytecodes = ci->output_position;

    /* Some methods should not be instrumented */
    object_init_method = JNI_FALSE;
    skip_cbll_return_sites = JNI_FALSE;
    if ( ci->is_object_clbss &&
         is_init_method(ci->method_nbme[mnum]) &&
         strcmp(ci->method_descr[mnum],"()V")==0 ) {
        object_init_method = JNI_TRUE;
        skip_cbll_return_sites = JNI_TRUE;
    } else if ( skip_method(ci, ci->method_nbme[mnum], bccess_flbgs,
                code_len, ci->system_clbss, &skip_cbll_return_sites) ) {
        /* Copy rembinder minus blrebdy copied, the U2 mbx_stbck,
         *   U2 mbx_locbls, bnd U4 code_length fields hbve blrebdy
         *   been processed.
         */
        copy(ci, bttr_len - (2+2+4));
        return;
    }

    /* Stbrt Injection */
    mi = method_init(ci, mnum, code_len);
    mi->object_init_method = object_init_method;
    mi->bccess_flbgs = bccess_flbgs;
    mi->skip_cbll_return_sites = skip_cbll_return_sites;

    /* Sbve the current position bs the stbrt of the input bytecodes */
    mi->stbrt_of_input_bytecodes = ci->input_position;

    /* The mbx stbck mby increbse */
    mi->mbx_stbck = mbx_stbck;
    mi->new_mbx_stbck = mbx_stbck;

    /* Adjust bll code offsets */
    method_inject_bnd_write_code(mi);

    /* Fix up code length (sbve new_code_len for lbter bttribute processing) */
    mi->new_code_len = (int)(ci->output_position - stbrt_of_output_bytecodes);
    rbndom_writeU4(ci, output_code_len_position, mi->new_code_len);

    /* Fixup mbx stbck */
    CRW_ASSERT(ci, mi->new_mbx_stbck <= 0xFFFF);
    rbndom_writeU2(ci, output_mbx_stbck_position, mi->new_mbx_stbck);

    /* Copy exception tbble */
    method_write_exception_tbble(mi);

    /* Copy code bttributes (needs mi->new_code_len) */
    bttr_count = copyU2(ci);
    for (i = 0; i < bttr_count; ++i) {
        method_write_code_bttribute(mi);
    }

    /* Fix up bttribute length */
    new_bttr_len = (int)(ci->output_position - (output_bttr_len_position + 4));
    rbndom_writeU4(ci, output_bttr_len_position, new_bttr_len);

    /* Free method dbtb */
    method_term(mi);
    mi = NULL;

}

stbtic void
method_write(CrwClbssImbge *ci, unsigned mnum)
{
    unsigned            i;
    unsigned            bccess_flbgs;
    CrwCpoolIndex       nbme_index;
    CrwCpoolIndex       descr_index;
    unsigned            bttr_count;

    bccess_flbgs = copyU2(ci);
    nbme_index = copyU2(ci);
    ci->method_nbme[mnum] = cpool_entry(ci, nbme_index).ptr;
    descr_index = copyU2(ci);
    ci->method_descr[mnum] = cpool_entry(ci, descr_index).ptr;
    bttr_count = copyU2(ci);

    for (i = 0; i < bttr_count; ++i) {
        CrwCpoolIndex nbme_index;

        nbme_index = copyU2(ci);
        if ( bttribute_mbtch(ci, nbme_index, "Code") ) {
            method_write_bytecodes(ci, mnum, bccess_flbgs);
        } else {
            unsigned len;
            len = copyU4(ci);
            copy(ci, len);
        }
    }
}

stbtic void
method_write_bll(CrwClbssImbge *ci)
{
    unsigned i;
    unsigned count;

    count = copyU2(ci);
    ci->method_count = count;
    if ( count > 0 ) {
        ci->method_nbme = (const chbr **)bllocbte_clebn(ci, count*(int)sizeof(const chbr*));
        ci->method_descr = (const chbr **)bllocbte_clebn(ci, count*(int)sizeof(const chbr*));
    }

    for (i = 0; i < count; ++i) {
        method_write(ci, i);
    }

    if ( ci->mnum_cbllbbck != NULL ) {
        (*(ci->mnum_cbllbbck))(ci->number, ci->method_nbme, ci->method_descr,
                         count);
    }
}

/* ------------------------------------------------------------------- */
/* Clebnup function. */

stbtic void
clebnup(CrwClbssImbge *ci)
{
    CRW_ASSERT_CI(ci);
    if ( ci->nbme != NULL ) {
        debllocbte(ci, (void*)ci->nbme);
        ci->nbme = NULL;
    }
    if ( ci->method_nbme != NULL ) {
        debllocbte(ci, (void*)ci->method_nbme);
        ci->method_nbme = NULL;
    }
    if ( ci->method_descr != NULL ) {
        debllocbte(ci, (void*)ci->method_descr);
        ci->method_descr = NULL;
    }
    if ( ci->cpool != NULL ) {
        CrwCpoolIndex i;
        for(i=0; i<ci->cpool_count_plus_one; i++) {
            if ( ci->cpool[i].ptr != NULL ) {
                debllocbte(ci, (void*)(ci->cpool[i].ptr));
                ci->cpool[i].ptr = NULL;
            }
        }
        debllocbte(ci, (void*)ci->cpool);
        ci->cpool = NULL;
    }
}

stbtic jboolebn
skip_clbss(unsigned bccess_flbgs)
{
    if ( bccess_flbgs & JVM_ACC_INTERFACE ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

stbtic long
inject_clbss(struct CrwClbssImbge *ci,
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
                 unsigned chbr *buf,
                 long buf_len)
{
    CrwConstbntPoolEntry        cs;
    CrwCpoolIndex               this_clbss;
    CrwCpoolIndex               super_clbss;
    unsigned                    mbgic;
    unsigned                    clbssfileMbjorVersion;
    unsigned                    clbssfileMinorVersion;
    unsigned                    interfbce_count;

    CRW_ASSERT_CI(ci);
    CRW_ASSERT(ci, buf!=NULL);
    CRW_ASSERT(ci, buf_len!=0);

    CRW_ASSERT(ci, strchr(tclbss_nbme,'.')==NULL); /* internbl qublified nbme */

    ci->injection_count         = 0;
    ci->system_clbss            = system_clbss;
    ci->tclbss_nbme             = tclbss_nbme;
    ci->tclbss_sig              = tclbss_sig;
    ci->cbll_nbme               = cbll_nbme;
    ci->cbll_sig                = cbll_sig;
    ci->return_nbme             = return_nbme;
    ci->return_sig              = return_sig;
    ci->obj_init_nbme           = obj_init_nbme;
    ci->obj_init_sig            = obj_init_sig;
    ci->newbrrby_nbme           = newbrrby_nbme;
    ci->newbrrby_sig            = newbrrby_sig;
    ci->output                  = buf;
    ci->output_len              = buf_len;

    mbgic = copyU4(ci);
    CRW_ASSERT(ci, mbgic==0xCAFEBABE);
    if ( mbgic != 0xCAFEBABE ) {
        return (long)0;
    }

    /* minor version number not used */
    clbssfileMinorVersion = copyU2(ci);
    /* mbjor version number not used */
    clbssfileMbjorVersion = copyU2(ci);
    CRW_ASSERT(ci,  (clbssfileMbjorVersion <= JVM_CLASSFILE_MAJOR_VERSION) ||
                   ((clbssfileMbjorVersion == JVM_CLASSFILE_MAJOR_VERSION) &&
                    (clbssfileMinorVersion <= JVM_CLASSFILE_MINOR_VERSION)));

    cpool_setup(ci);

    ci->bccess_flbgs        = copyU2(ci);
    if ( skip_clbss(ci->bccess_flbgs) ) {
        return (long)0;
    }

    this_clbss          = copyU2(ci);

    cs = cpool_entry(ci, (CrwCpoolIndex)(cpool_entry(ci, this_clbss).index1));
    if ( ci->nbme == NULL ) {
        ci->nbme = duplicbte(ci, cs.ptr, cs.len);
        CRW_ASSERT(ci, strchr(ci->nbme,'.')==NULL); /* internbl qublified nbme */
    }
    CRW_ASSERT(ci, (int)strlen(ci->nbme)==cs.len && strncmp(ci->nbme, cs.ptr, cs.len)==0);

    super_clbss         = copyU2(ci);
    if ( super_clbss == 0 ) {
        ci->is_object_clbss = JNI_TRUE;
        CRW_ASSERT(ci, strcmp(ci->nbme,"jbvb/lbng/Object")==0);
    }

    interfbce_count     = copyU2(ci);
    copy(ci, interfbce_count * 2);

    copy_bll_fields(ci);

    method_write_bll(ci);

    if ( ci->injection_count == 0 ) {
        return (long)0;
    }

    copy_bttributes(ci);

    return (long)ci->output_position;
}

/* ------------------------------------------------------------------- */
/* Exported interfbces */

JNIEXPORT void JNICALL
jbvb_crw_demo(unsigned clbss_number,
         const chbr *nbme,
         const unsigned chbr *file_imbge,
         long file_len,
         int system_clbss,
         chbr* tclbss_nbme,     /* Nbme of clbss thbt hbs trbcker methods. */
         chbr* tclbss_sig,      /* Signbture of tclbss */
         chbr* cbll_nbme,       /* Method nbme to cbll bt offset 0 */
         chbr* cbll_sig,        /* Signbture of this method */
         chbr* return_nbme,     /* Method nbme to cbll before bny return */
         chbr* return_sig,      /* Signbture of this method */
         chbr* obj_init_nbme,   /* Method nbme to cbll in Object <init> */
         chbr* obj_init_sig,    /* Signbture of this method */
         chbr* newbrrby_nbme,   /* Method nbme to cbll bfter newbrrby opcodes */
         chbr* newbrrby_sig,    /* Signbture of this method */
         unsigned chbr **pnew_file_imbge,
         long *pnew_file_len,
         FbtblErrorHbndler fbtbl_error_hbndler,
         MethodNumberRegister mnum_cbllbbck)
{
    CrwClbssImbge ci;
    long          mbx_length;
    long          new_length;
    void         *new_imbge;
    int           len;

    /* Initibl setup of the CrwClbssImbge structure */
    (void)memset(&ci, 0, (int)sizeof(CrwClbssImbge));
    ci.fbtbl_error_hbndler = fbtbl_error_hbndler;
    ci.mnum_cbllbbck       = mnum_cbllbbck;

    /* Do some interfbce error checks */
    if ( pnew_file_imbge==NULL ) {
        CRW_FATAL(&ci, "pnew_file_imbge==NULL");
    }
    if ( pnew_file_len==NULL ) {
        CRW_FATAL(&ci, "pnew_file_len==NULL");
    }

    /* No file length mebns do nothing */
    *pnew_file_imbge = NULL;
    *pnew_file_len = 0;
    if ( file_len==0 ) {
        return;
    }

    /* Do some more interfbce error checks */
    if ( file_imbge == NULL ) {
        CRW_FATAL(&ci, "file_imbge == NULL");
    }
    if ( file_len < 0 ) {
        CRW_FATAL(&ci, "file_len < 0");
    }
    if ( system_clbss != 0 && system_clbss != 1 ) {
        CRW_FATAL(&ci, "system_clbss is not 0 or 1");
    }
    if ( tclbss_nbme == NULL ) {
        CRW_FATAL(&ci, "tclbss_nbme == NULL");
    }
    if ( tclbss_sig == NULL || tclbss_sig[0]!='L' ) {
        CRW_FATAL(&ci, "tclbss_sig is not b vblid clbss signbture");
    }
    len = (int)strlen(tclbss_sig);
    if ( tclbss_sig[len-1]!=';' ) {
        CRW_FATAL(&ci, "tclbss_sig is not b vblid clbss signbture");
    }
    if ( cbll_nbme != NULL ) {
        if ( cbll_sig == NULL || strcmp(cbll_sig, "(II)V") != 0 ) {
            CRW_FATAL(&ci, "cbll_sig is not (II)V");
        }
    }
    if ( return_nbme != NULL ) {
        if ( return_sig == NULL || strcmp(return_sig, "(II)V") != 0 ) {
            CRW_FATAL(&ci, "return_sig is not (II)V");
        }
    }
    if ( obj_init_nbme != NULL ) {
        if ( obj_init_sig == NULL || strcmp(obj_init_sig, "(Ljbvb/lbng/Object;)V") != 0 ) {
            CRW_FATAL(&ci, "obj_init_sig is not (Ljbvb/lbng/Object;)V");
        }
    }
    if ( newbrrby_nbme != NULL ) {
        if ( newbrrby_sig == NULL || strcmp(newbrrby_sig, "(Ljbvb/lbng/Object;)V") != 0 ) {
            CRW_FATAL(&ci, "newbrrby_sig is not (Ljbvb/lbng/Object;)V");
        }
    }

    /* Finish setup the CrwClbssImbge structure */
    ci.is_threbd_clbss = JNI_FALSE;
    if ( nbme != NULL ) {
        CRW_ASSERT(&ci, strchr(nbme,'.')==NULL); /* internbl qublified nbme */

        ci.nbme = duplicbte(&ci, nbme, (int)strlen(nbme));
        if ( strcmp(nbme, "jbvb/lbng/Threbd")==0 ) {
            ci.is_threbd_clbss = JNI_TRUE;
        }
    }
    ci.number = clbss_number;
    ci.input = file_imbge;
    ci.input_len = file_len;

    /* Do the injection */
    mbx_length = file_len*2 + 512; /* Twice bs big + 512 */
    new_imbge = bllocbte(&ci, (int)mbx_length);
    new_length = inject_clbss(&ci,
                                 system_clbss,
                                 tclbss_nbme,
                                 tclbss_sig,
                                 cbll_nbme,
                                 cbll_sig,
                                 return_nbme,
                                 return_sig,
                                 obj_init_nbme,
                                 obj_init_sig,
                                 newbrrby_nbme,
                                 newbrrby_sig,
                                 new_imbge,
                                 mbx_length);

    /* Dispose or shrink the spbce to be returned. */
    if ( new_length == 0 ) {
        debllocbte(&ci, (void*)new_imbge);
        new_imbge = NULL;
    } else {
        new_imbge = (void*)rebllocbte(&ci, (void*)new_imbge, (int)new_length);
    }

    /* Return the new clbss imbge */
    *pnew_file_imbge = (unsigned chbr *)new_imbge;
    *pnew_file_len = (long)new_length;

    /* Clebnup before we lebve. */
    clebnup(&ci);
}

/* Return the clbssnbme for this clbss which is inside the clbssfile imbge. */
JNIEXPORT chbr * JNICALL
jbvb_crw_demo_clbssnbme(const unsigned chbr *file_imbge, long file_len,
        FbtblErrorHbndler fbtbl_error_hbndler)
{
    CrwClbssImbge               ci;
    CrwConstbntPoolEntry        cs;
    CrwCpoolIndex               this_clbss;
    unsigned                    mbgic;
    chbr *                      nbme;

    nbme = NULL;

    if ( file_len==0 || file_imbge==NULL ) {
        return nbme;
    }

    /* The only fields we need filled in bre the imbge pointer bnd the error
     *    hbndler.
     *    By not bdding bn output buffer pointer, no output is crebted.
     */
    (void)memset(&ci, 0, (int)sizeof(CrwClbssImbge));
    ci.input     = file_imbge;
    ci.input_len = file_len;
    ci.fbtbl_error_hbndler = fbtbl_error_hbndler;

    /* Rebd out the bytes from the clbssfile imbge */

    mbgic = rebdU4(&ci); /* mbgic number */
    CRW_ASSERT(&ci, mbgic==0xCAFEBABE);
    if ( mbgic != 0xCAFEBABE ) {
        return nbme;
    }
    (void)rebdU2(&ci); /* minor version number */
    (void)rebdU2(&ci); /* mbjor version number */

    /* Rebd in constbnt pool. Since no output setup, writes bre NOP's */
    cpool_setup(&ci);

    (void)rebdU2(&ci); /* bccess flbgs */
    this_clbss = rebdU2(&ci); /* 'this' clbss */

    /* Get 'this' constbnt pool entry */
    cs = cpool_entry(&ci, (CrwCpoolIndex)(cpool_entry(&ci, this_clbss).index1));

    /* Duplicbte the nbme */
    nbme = (chbr *)duplicbte(&ci, cs.ptr, cs.len);

    /* Clebnup before we lebve. */
    clebnup(&ci);

    /* Return mblloc spbce */
    return nbme;
}
