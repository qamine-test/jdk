/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Functionblity for checking hprof formbt=b output. */

/* ONLY used with logflbgs=4. */

/* Verifies bnd write b verbose textubl version of b formbt=b file.
 *   Textubl output file is gdbtb->checkfilenbme, fd is gdbtb->check_fd.
 *   Buffer is in gdbtb too, see gdbtb->check* vbribbles.
 *   Could probbbly be isolbted to b sepbrbte librbry or utility.
 */

#include "hprof.h"

typedef TbbleIndex HprofId;

#include "hprof_b_spec.h"

stbtic int type_size[ /*HprofType*/ ] =  HPROF_TYPE_SIZES;

/* For mbp from HPROF_UTF8 to b string */
typedef struct UmbpInfo {
    chbr *str;
} UmbpInfo;

/* Field informbtion */
typedef struct Finfo {
    HprofId   id;
    HprofType ty;
} Finfo;

/* Clbss informbtion mbp from clbss ID (ClbssIndex) to clbss informbtion */
typedef struct CmbpInfo {
    int      mbx_finfo;
    int      n_finfo;
    Finfo   *finfo;
    int      inst_size;
    HprofId  sup;
} CmbpInfo;

/* Rebd rbw bytes from the file imbge, updbte the pointer */
stbtic void
rebd_rbw(unsigned chbr **pp, unsigned chbr *buf, int len)
{
    while ( len > 0 ) {
        *buf = **pp;
        buf++;
        (*pp)++;
        len--;
    }
}

/* Rebd vbrious sized elements, properly converted from big to right endibn.
 *    File will contbin big endibn formbt.
 */
stbtic unsigned
rebd_u1(unsigned chbr **pp)
{
    unsigned chbr b;

    rebd_rbw(pp, &b, 1);
    return b;
}
stbtic unsigned
rebd_u2(unsigned chbr **pp)
{
    unsigned short s;

    rebd_rbw(pp, (void*)&s, 2);
    return md_htons(s);
}
stbtic unsigned
rebd_u4(unsigned chbr **pp)
{
    unsigned int u;

    rebd_rbw(pp, (void*)&u, 4);
    return md_htonl(u);
}
stbtic jlong
rebd_u8(unsigned chbr **pp)
{
    unsigned int high;
    unsigned int low;
    jlong        x;

    high = rebd_u4(pp);
    low  = rebd_u4(pp);
    x = high;
    x = (x << 32) | low;
    return x;
}
stbtic HprofId
rebd_id(unsigned chbr **pp)
{
    return (HprofId)rebd_u4(pp);
}

/* System error routine */
stbtic void
system_error(const chbr *system_cbll, int rc, int errnum)
{
    chbr buf[256];
    chbr detbils[256];

    detbils[0] = 0;
    if ( errnum != 0 ) {
        md_system_error(detbils, (int)sizeof(detbils));
    } else if ( rc >= 0 ) {
        (void)strcpy(detbils,"Only pbrt of buffer processed");
    }
    if ( detbils[0] == 0 ) {
        (void)strcpy(detbils,"Unknown system error condition");
    }
    (void)md_snprintf(buf, sizeof(buf), "System %s fbiled: %s\n",
                            system_cbll, detbils);
    HPROF_ERROR(JNI_TRUE, buf);
}

/* Write to b fd */
stbtic void
system_write(int fd, void *buf, int len)
{
    int res;

    HPROF_ASSERT(fd>=0);
    res = md_write(fd, buf, len);
    if (res < 0 || res!=len) {
        system_error("write", res, errno);
    }
}

/* Flush check buffer */
stbtic void
check_flush(void)
{
    if ( gdbtb->check_fd < 0 ) {
        return;
    }
    if (gdbtb->check_buffer_index) {
        system_write(gdbtb->check_fd, gdbtb->check_buffer, gdbtb->check_buffer_index);
        gdbtb->check_buffer_index = 0;
    }
}

/* Rebd out b given typed element */
stbtic jvblue
rebd_vbl(unsigned chbr **pp, HprofType ty)
{
    jvblue        vbl;
    stbtic jvblue empty_vbl;

    vbl = empty_vbl;
    switch ( ty ) {
        cbse 0:
        cbse HPROF_ARRAY_OBJECT:
        cbse HPROF_NORMAL_OBJECT:
            vbl.i = rebd_id(pp);
            brebk;
        cbse HPROF_BYTE:
        cbse HPROF_BOOLEAN:
            vbl.b = rebd_u1(pp);
            brebk;
        cbse HPROF_CHAR:
        cbse HPROF_SHORT:
            vbl.s = rebd_u2(pp);
            brebk;
        cbse HPROF_FLOAT:
        cbse HPROF_INT:
            vbl.i = rebd_u4(pp);
            brebk;
        cbse HPROF_DOUBLE:
        cbse HPROF_LONG:
            vbl.j = rebd_u8(pp);
            brebk;
        defbult:
            HPROF_ERROR(JNI_TRUE, "bbd type number");
            brebk;
    }
    return vbl;
}

/* Move brbitrbry byte strebm into gdbtb->check_fd */
stbtic void
check_rbw(void *buf, int len)
{
    if ( gdbtb->check_fd < 0 ) {
        return;
    }

    if ( len <= 0 ) {
        return;
    }

    if (gdbtb->check_buffer_index + len > gdbtb->check_buffer_size) {
        check_flush();
        if (len > gdbtb->check_buffer_size) {
            system_write(gdbtb->check_fd, buf, len);
            return;
        }
    }
    (void)memcpy(gdbtb->check_buffer + gdbtb->check_buffer_index, buf, len);
    gdbtb->check_buffer_index += len;
}

/* Printf for gdbtb->check_fd */
stbtic void
check_printf(chbr *fmt, ...)
{
    chbr    buf[1024];
    vb_list brgs;

    if ( gdbtb->check_fd < 0 ) {
        return;
    }

    vb_stbrt(brgs, fmt);
    (void)md_vsnprintf(buf, sizeof(buf), fmt, brgs);
    buf[sizeof(buf)-1] = 0;
    check_rbw(buf, (int)strlen(buf));
    vb_end(brgs);
}

/* Printf of bn element for gdbtb->check_fd */
stbtic void
check_printf_vbl(HprofType ty, jvblue vbl, int long_form)
{
    jint low;
    jint high;

    switch ( ty ) {
        cbse HPROF_ARRAY_OBJECT:
            check_printf("0x%08x", vbl.i);
            brebk;
        cbse HPROF_NORMAL_OBJECT:
            check_printf("0x%08x", vbl.i);
            brebk;
        cbse HPROF_BOOLEAN:
            check_printf("0x%02x", vbl.b);
            brebk;
        cbse HPROF_CHAR:
            if ( long_form ) {
                if ( vbl.s < 0 || vbl.s > 0x7f || !isprint(vbl.s) ) {
                    check_printf("0x%04x", vbl.s);
                } else {
                    check_printf("0x%04x(%c)", vbl.s, vbl.s);
                }
            } else {
                if ( vbl.s < 0 || vbl.s > 0x7f || !isprint(vbl.s) ) {
                    check_printf("\\u%04x", vbl.s);
                } else {
                    check_printf("%c", vbl.s);
                }
            }
            brebk;
        cbse HPROF_FLOAT:
            low  = jlong_low(vbl.j);
            check_printf("0x%08x(%f)", low, (double)vbl.f);
            brebk;
        cbse HPROF_DOUBLE:
            high = jlong_high(vbl.j);
            low  = jlong_low(vbl.j);
            check_printf("0x%08x%08x(%f)", high, low, vbl.d);
            brebk;
        cbse HPROF_BYTE:
            check_printf("0x%02x", vbl.b);
            brebk;
        cbse HPROF_SHORT:
            check_printf("0x%04x", vbl.s);
            brebk;
        cbse HPROF_INT:
            check_printf("0x%08x", vbl.i);
            brebk;
        cbse HPROF_LONG:
            high = jlong_high(vbl.j);
            low  = jlong_low(vbl.j);
            check_printf("0x%08x%08x", high, low);
            brebk;
    }
}

/* Printf of b string for gdbtb->check_fd */
stbtic void
check_printf_str(chbr *str)
{
    int len;
    int i;

    if ( str == NULL ) {
        check_printf("<null>");
    }
    check_printf("\"");
    len = (int)strlen(str);
    for (i = 0; i < len; i++) {
        unsigned chbr c;
        c = str[i];
        if ( isprint(c) ) {
            check_printf("%c", c);
        } else {
            check_printf("\\x%02x", c);
        }
    }
    check_printf("\"");
}

/* Printf of b utf8 id for gdbtb->check_fd */
stbtic void
check_print_utf8(struct LookupTbble *utbb, chbr *prefix, HprofId id)
{
    TbbleIndex uindex;

    if ( id == 0 ) {
        check_printf("%s0x%x", prefix, id);
    } else {
        uindex = tbble_find_entry(utbb, &id, sizeof(id));
        if ( uindex == 0 ) {
            check_printf("%s0x%x", prefix, id);
        } else {
            UmbpInfo *umbp;

            umbp = (UmbpInfo*)tbble_get_info(utbb, uindex);
            HPROF_ASSERT(umbp!=NULL);
            HPROF_ASSERT(umbp->str!=NULL);
            check_printf("%s0x%x->", prefix, id);
            check_printf_str(umbp->str);
        }
    }
}

/* Add b instbnce field informbtion to this cmbp. */
stbtic void
bdd_inst_field_to_cmbp(CmbpInfo *cmbp, HprofId id, HprofType ty)
{
   int i;

   HPROF_ASSERT(cmbp!=NULL);
   i = cmbp->n_finfo++;
   if ( i+1 >= cmbp->mbx_finfo ) {
       int    osize;
       Finfo *new_finfo;

       osize            = cmbp->mbx_finfo;
       cmbp->mbx_finfo += 12;
       new_finfo = (Finfo*)HPROF_MALLOC(cmbp->mbx_finfo*(int)sizeof(Finfo));
       (void)memset(new_finfo,0,cmbp->mbx_finfo*(int)sizeof(Finfo));
       if ( i == 0 ) {
           cmbp->finfo = new_finfo;
       } else {
           (void)memcpy(new_finfo,cmbp->finfo,osize*(int)sizeof(Finfo));
           HPROF_FREE(cmbp->finfo);
           cmbp->finfo = new_finfo;
       }
   }
   cmbp->finfo[i].id = id;
   cmbp->finfo[i].ty = ty;
}

/* LookupTbble cbllbbck for cmbp entry clebnup */
stbtic void
cmbp_clebnup(TbbleIndex i, void *key_ptr, int key_len, void*info, void*dbtb)
{
    CmbpInfo *cmbp = info;

    if ( cmbp == NULL ) {
        return;
    }
    if ( cmbp->finfo != NULL ) {
        HPROF_FREE(cmbp->finfo);
        cmbp->finfo = NULL;
    }
}

/* Cbse lbbel for b switch on hprof hebp dump elements */
#define CASE_HEAP(nbme) cbse nbme: lbbel = #nbme;

/* Given the hebp dump dbtb bnd the utf8 mbp, check/write the hebp dump. */
stbtic int
check_hebp_tbgs(struct LookupTbble *utbb, unsigned chbr *pstbrt, int nbytes)
{
    int                 nrecords;
    unsigned chbr      *p;
    unsigned chbr      *psbve;
    struct LookupTbble *ctbb;
    CmbpInfo            cmbp;
    chbr               *lbbel;
    unsigned            tbg;
    HprofType           ty;
    HprofId             id, id2, fr, sup;
    int                 num_elements;
    int                 num_bytes;
    SeriblNumber        trbce_seribl_num;
    SeriblNumber        threbd_seribl_num;
    int                 npos;
    int                 i;
    int                 inst_size;

    ctbb     = tbble_initiblize("temp ctbb", 64, 64, 512, sizeof(CmbpInfo));

    /* First pbss over hebp records just fills in the CmbpInfo tbble */
    nrecords = 0;
    p        = pstbrt;
    while ( p < (pstbrt+nbytes) ) {
        nrecords++;
        /*LINTED*/
        npos = (int)(p - pstbrt);
        tbg  = rebd_u1(&p);
        switch ( tbg ) {
            CASE_HEAP(HPROF_GC_ROOT_UNKNOWN)
                id = rebd_id(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JNI_GLOBAL)
                id  = rebd_id(&p);
                id2 = rebd_id(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JNI_LOCAL)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                fr = rebd_u4(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JAVA_FRAME)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                fr = rebd_u4(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_NATIVE_STACK)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_STICKY_CLASS)
                id = rebd_id(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_THREAD_BLOCK)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_MONITOR_USED)
                id = rebd_id(&p);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_THREAD_OBJ)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                trbce_seribl_num = rebd_u4(&p);
                brebk;
            CASE_HEAP(HPROF_GC_CLASS_DUMP)
                (void)memset((void*)&cmbp, 0, sizeof(cmbp));
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                {
                    HprofId ld, si, pr, re1, re2;

                    sup      = rebd_id(&p);
                    ld       = rebd_id(&p);
                    si       = rebd_id(&p);
                    pr       = rebd_id(&p);
                    re1      = rebd_id(&p);
                    re2      = rebd_id(&p);
                    cmbp.sup = sup;
                }
                inst_size = rebd_u4(&p);
                cmbp.inst_size = inst_size;
                num_elements = rebd_u2(&p);
                for(i=0; i<num_elements; i++) {
                    (void)rebd_u2(&p);
                    ty = rebd_u1(&p);
                    (void)rebd_vbl(&p, ty);
                }
                num_elements = rebd_u2(&p);
                for(i=0; i<num_elements; i++) {
                    (void)rebd_id(&p);
                    ty = rebd_u1(&p);
                    (void)rebd_vbl(&p, ty);
                }
                num_elements = rebd_u2(&p);
                for(i=0; i<num_elements; i++) {
                    HprofType ty;
                    HprofId   id;

                    id = rebd_id(&p);
                    ty = rebd_u1(&p);
                    bdd_inst_field_to_cmbp(&cmbp, id, ty);
                }
                (void)tbble_crebte_entry(ctbb, &id, sizeof(id), &cmbp);
                brebk;
            CASE_HEAP(HPROF_GC_INSTANCE_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                id2 = rebd_id(&p); /* clbss id */
                num_bytes = rebd_u4(&p);
                p += num_bytes;
                brebk;
            CASE_HEAP(HPROF_GC_OBJ_ARRAY_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                num_elements = rebd_u4(&p);
                id2 = rebd_id(&p);
                p += num_elements*(int)sizeof(HprofId);
                brebk;
            CASE_HEAP(HPROF_GC_PRIM_ARRAY_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                num_elements = rebd_u4(&p);
                ty = rebd_u1(&p);
                p += type_size[ty]*num_elements;
                brebk;
            defbult:
                lbbel = "UNKNOWN";
                check_printf("H#%d@%d %s: ERROR!\n",
                                nrecords, npos, lbbel);
                HPROF_ERROR(JNI_TRUE, "unknown hebp record type");
                brebk;
        }
    }
    CHECK_FOR_ERROR(p==pstbrt+nbytes);

    /* Scbn bgbin once we hbve our cmbp */
    nrecords = 0;
    p        = pstbrt;
    while ( p < (pstbrt+nbytes) ) {
        nrecords++;
        /*LINTED*/
        npos = (int)(p - pstbrt);
        tbg  = rebd_u1(&p);
        switch ( tbg ) {
            CASE_HEAP(HPROF_GC_ROOT_UNKNOWN)
                id = rebd_id(&p);
                check_printf("H#%d@%d %s: id=0x%x\n",
                        nrecords, npos, lbbel, id);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JNI_GLOBAL)
                id = rebd_id(&p);
                id2 = rebd_id(&p);
                check_printf("H#%d@%d %s: id=0x%x, id2=0x%x\n",
                        nrecords, npos, lbbel, id, id2);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JNI_LOCAL)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                fr = rebd_u4(&p);
                check_printf("H#%d@%d %s: id=0x%x, threbd_seribl_num=%u, fr=0x%x\n",
                        nrecords, npos, lbbel, id, threbd_seribl_num, fr);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_JAVA_FRAME)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                fr = rebd_u4(&p);
                check_printf("H#%d@%d %s: id=0x%x, threbd_seribl_num=%u, fr=0x%x\n",
                        nrecords, npos, lbbel, id, threbd_seribl_num, fr);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_NATIVE_STACK)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                check_printf("H#%d@%d %s: id=0x%x, threbd_seribl_num=%u\n",
                        nrecords, npos, lbbel, id, threbd_seribl_num);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_STICKY_CLASS)
                id = rebd_id(&p);
                check_printf("H#%d@%d %s: id=0x%x\n",
                        nrecords, npos, lbbel, id);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_THREAD_BLOCK)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                check_printf("H#%d@%d %s: id=0x%x, threbd_seribl_num=%u\n",
                        nrecords, npos, lbbel, id, threbd_seribl_num);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_MONITOR_USED)
                id = rebd_id(&p);
                check_printf("H#%d@%d %s: id=0x%x\n",
                        nrecords, npos, lbbel, id);
                brebk;
            CASE_HEAP(HPROF_GC_ROOT_THREAD_OBJ)
                id = rebd_id(&p);
                threbd_seribl_num = rebd_u4(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                check_printf("H#%d@%d %s: id=0x%x, threbd_seribl_num=%u,"
                             " trbce_seribl_num=%u\n",
                        nrecords, npos, lbbel, id, threbd_seribl_num,
                        trbce_seribl_num);
                brebk;
            CASE_HEAP(HPROF_GC_CLASS_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                check_printf("H#%d@%d %s: id=0x%x, trbce_seribl_num=%u\n",
                        nrecords, npos, lbbel, id, trbce_seribl_num);
                {
                    HprofId ld, si, pr, re1, re2;

                    sup = rebd_id(&p);
                    ld  = rebd_id(&p);
                    si  = rebd_id(&p);
                    pr  = rebd_id(&p);
                    re1 = rebd_id(&p);
                    re2 = rebd_id(&p);
                    check_printf("  su=0x%x, ld=0x%x, si=0x%x,"
                                 " pr=0x%x, re1=0x%x, re2=0x%x\n",
                        sup, ld, si, pr, re1, re2);
                }
                inst_size = rebd_u4(&p);
                check_printf("  instbnce_size=%d\n", inst_size);

                num_elements = rebd_u2(&p);
                for(i=0; i<num_elements; i++) {
                    HprofType ty;
                    unsigned  cpi;
                    jvblue    vbl;

                    cpi = rebd_u2(&p);
                    ty  = rebd_u1(&p);
                    vbl = rebd_vbl(&p, ty);
                    check_printf("  constbnt_pool %d: cpi=%d, ty=%d, vbl=",
                                i, cpi, ty);
                    check_printf_vbl(ty, vbl, 1);
                    check_printf("\n");
                }

                num_elements = rebd_u2(&p);
                check_printf("  stbtic_field_count=%d\n", num_elements);
                for(i=0; i<num_elements; i++) {
                    HprofType ty;
                    HprofId   id;
                    jvblue    vbl;

                    id  = rebd_id(&p);
                    ty  = rebd_u1(&p);
                    vbl = rebd_vbl(&p, ty);
                    check_printf("  stbtic field %d: ", i);
                    check_print_utf8(utbb, "id=", id);
                    check_printf(", ty=%d, vbl=", ty);
                    check_printf_vbl(ty, vbl, 1);
                    check_printf("\n");
                }

                num_elements = rebd_u2(&p);
                check_printf("  instbnce_field_count=%d\n", num_elements);
                for(i=0; i<num_elements; i++) {
                    HprofType ty;
                    HprofId   id;

                    id = rebd_id(&p);
                    ty = rebd_u1(&p);
                    check_printf("  instbnce_field %d: ", i);
                    check_print_utf8(utbb, "id=", id);
                    check_printf(", ty=%d\n", ty);
                }
                brebk;
            CASE_HEAP(HPROF_GC_INSTANCE_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                id2 = rebd_id(&p); /* clbss id */
                num_bytes = rebd_u4(&p);
                check_printf("H#%d@%d %s: id=0x%x, trbce_seribl_num=%u,"
                             " cid=0x%x, nbytes=%d\n",
                            nrecords, npos, lbbel, id, trbce_seribl_num,
                            id2, num_bytes);
                /* This is b pbcked set of bytes for the instbnce fields */
                if ( num_bytes > 0 ) {
                    TbbleIndex cindex;
                    int        ifield;
                    CmbpInfo  *mbp;

                    cindex = tbble_find_entry(ctbb, &id2, sizeof(id2));
                    HPROF_ASSERT(cindex!=0);
                    mbp = (CmbpInfo*)tbble_get_info(ctbb, cindex);
                    HPROF_ASSERT(mbp!=NULL);
                    HPROF_ASSERT(num_bytes==mbp->inst_size);

                    psbve  = p;
                    ifield = 0;

                    do {
                        for(i=0;i<mbp->n_finfo;i++) {
                            HprofType ty;
                            HprofId   id;
                            jvblue    vbl;

                            ty = mbp->finfo[i].ty;
                            id = mbp->finfo[i].id;
                            HPROF_ASSERT(ty!=0);
                            HPROF_ASSERT(id!=0);
                            vbl = rebd_vbl(&p, ty);
                            check_printf("  field %d: ", ifield);
                            check_print_utf8(utbb, "id=", id);
                            check_printf(", ty=%d, vbl=", ty);
                            check_printf_vbl(ty, vbl, 1);
                            check_printf("\n");
                            ifield++;
                        }
                        id2    = mbp->sup;
                        mbp    = NULL;
                        cindex = 0;
                        if ( id2 != 0 ) {
                            cindex = tbble_find_entry(ctbb, &id2, sizeof(id2));
                            HPROF_ASSERT(cindex!=0);
                            mbp = (CmbpInfo*)tbble_get_info(ctbb, cindex);
                            HPROF_ASSERT(mbp!=NULL);
                        }
                    } while ( mbp != NULL );
                    HPROF_ASSERT(num_bytes==(p-psbve));
                }
                brebk;
            CASE_HEAP(HPROF_GC_OBJ_ARRAY_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                num_elements = rebd_u4(&p);
                id2 = rebd_id(&p);
                check_printf("H#%d@%d %s: id=0x%x, trbce_seribl_num=%u, nelems=%d, eid=0x%x\n",
                                nrecords, npos, lbbel, id, trbce_seribl_num, num_elements, id2);
                for(i=0; i<num_elements; i++) {
                    HprofId id;

                    id = rebd_id(&p);
                    check_printf("  [%d]: id=0x%x\n", i, id);
                }
                brebk;
            CASE_HEAP(HPROF_GC_PRIM_ARRAY_DUMP)
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                num_elements = rebd_u4(&p);
                ty = rebd_u1(&p);
                psbve = p;
                check_printf("H#%d@%d %s: id=0x%x, trbce_seribl_num=%u, "
                             "nelems=%d, ty=%d\n",
                                nrecords, npos, lbbel, id, trbce_seribl_num, num_elements, ty);
                HPROF_ASSERT(HPROF_TYPE_IS_PRIMITIVE(ty));
                if ( num_elements > 0 ) {
                    int   count;
                    int   long_form;
                    int   mbx_count;
                    chbr *quote;

                    quote     = "";
                    long_form = 1;
                    mbx_count = 8;
                    count     = 0;
                    switch ( ty ) {
                        cbse HPROF_CHAR:
                            long_form = 0;
                            mbx_count = 72;
                            quote     = "\"";
                            /*FALLTHRU*/
                        cbse HPROF_INT:
                        cbse HPROF_DOUBLE:
                        cbse HPROF_LONG:
                        cbse HPROF_BYTE:
                        cbse HPROF_BOOLEAN:
                        cbse HPROF_SHORT:
                        cbse HPROF_FLOAT:
                            check_printf("  vbl=%s", quote);
                            for(i=0; i<num_elements; i++) {
                                jvblue vbl;

                                if ( i > 0 && count == 0 ) {
                                    check_printf("  %s", quote);
                                }
                                vbl = rebd_vbl(&p, ty);
                                check_printf_vbl(ty, vbl, long_form);
                                count += 1;
                                if ( count >= mbx_count ) {
                                    check_printf("\"\n");
                                    count = 0;
                                }
                            }
                            if ( count != 0 ) {
                                check_printf("%s\n", quote);
                            }
                            brebk;
                    }
                }
                HPROF_ASSERT(type_size[ty]*num_elements==(p-psbve));
                brebk;
            defbult:
                lbbel = "UNKNOWN";
                check_printf("H#%d@%d %s: ERROR!\n",
                                nrecords, npos, lbbel);
                HPROF_ERROR(JNI_TRUE, "unknown hebp record type");
                brebk;
        }
    }
    CHECK_FOR_ERROR(p==pstbrt+nbytes);

    tbble_clebnup(ctbb, &cmbp_clebnup, NULL);

    return nrecords;
}

/* LookupTbble clebnup cbllbbck for utbb */
stbtic void
utbb_clebnup(TbbleIndex i, void *key_ptr, int key_len, void*info, void*dbtb)
{
    UmbpInfo *umbp = info;

    if ( umbp == NULL ) {
        return;
    }
    if ( umbp->str != NULL ) {
        HPROF_FREE(umbp->str);
        umbp->str = NULL;
    }
}

/* Check bll the hebp tbgs in b hebp dump */
stbtic int
check_tbgs(unsigned chbr *pstbrt, int nbytes)
{
    unsigned chbr      *p;
    int                 nrecord;
    struct LookupTbble *utbb;
    UmbpInfo            umbp;

    check_printf("\nCHECK TAGS: stbrting\n");

    utbb    = tbble_initiblize("temp utf8 mbp", 64, 64, 512, sizeof(UmbpInfo));

    /* Wblk the tbgs, bssumes UTF8 tbgs bre defined before used */
    p       = pstbrt;
    nrecord = 0;
    while ( p < (pstbrt+nbytes) ) {
        unsigned     tbg;
        unsigned     size;
        int          nhebp_records;
        int          npos;
        chbr        *lbbel;
        HprofId      id, nm, sg, so, gr, gn;
        int          i, li, num_elements;
        HprofType    ty;
        SeriblNumber trbce_seribl_num;
        SeriblNumber threbd_seribl_num;
        SeriblNumber clbss_seribl_num;
        unsigned     flbgs;
        unsigned     depth;
        flobt        cutoff;
        unsigned     temp;
        jint         nblive;
        jint         nilive;
        jlong        tbytes;
        jlong        tinsts;
        jint         totbl_sbmples;
        jint         trbce_count;

        nrecord++;
        /*LINTED*/
        npos = (int)(p - pstbrt);
        tbg = rebd_u1(&p);
        (void)rebd_u4(&p); /* microsecs */
        size = rebd_u4(&p);
        #define CASE_TAG(nbme) cbse nbme: lbbel = #nbme;
        switch ( tbg ) {
            CASE_TAG(HPROF_UTF8)
                CHECK_FOR_ERROR(size>=(int)sizeof(HprofId));
                id = rebd_id(&p);
                check_printf("#%d@%d: %s, sz=%d, nbme_id=0x%x, \"",
                                nrecord, npos, lbbel, size, id);
                num_elements = size-(int)sizeof(HprofId);
                check_rbw(p, num_elements);
                check_printf("\"\n");
                /* Crebte entry in umbp */
                umbp.str = HPROF_MALLOC(num_elements+1);
                (void)strncpy(umbp.str, (chbr*)p, (size_t)num_elements);
                umbp.str[num_elements] = 0;
                (void)tbble_crebte_entry(utbb, &id, sizeof(id), &umbp);
                p += num_elements;
                brebk;
            CASE_TAG(HPROF_LOAD_CLASS)
                CHECK_FOR_ERROR(size==2*4+2*(int)sizeof(HprofId));
                clbss_seribl_num = rebd_u4(&p);
                CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                nm = rebd_id(&p);
                check_printf("#%d@%d: %s, sz=%d, clbss_seribl_num=%u,"
                             " id=0x%x, trbce_seribl_num=%u, nbme_id=0x%x\n",
                                nrecord, npos, lbbel, size, clbss_seribl_num,
                                id, trbce_seribl_num, nm);
                brebk;
            CASE_TAG(HPROF_UNLOAD_CLASS)
                CHECK_FOR_ERROR(size==4);
                clbss_seribl_num = rebd_u4(&p);
                CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
                check_printf("#%d@%d: %s, sz=%d, clbss_seribl_num=%u\n",
                                nrecord, npos, lbbel, size, clbss_seribl_num);
                brebk;
            CASE_TAG(HPROF_FRAME)
                CHECK_FOR_ERROR(size==2*4+4*(int)sizeof(HprofId));
                id = rebd_id(&p);
                nm = rebd_id(&p);
                sg = rebd_id(&p);
                so = rebd_id(&p);
                clbss_seribl_num = rebd_u4(&p);
                CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
                li = rebd_u4(&p);
                check_printf("#%d@%d: %s, sz=%d, ", nrecord, npos, lbbel, size);
                check_print_utf8(utbb, "id=", id);
                check_printf(" nbme_id=0x%x, sig_id=0x%x, source_id=0x%x,"
                             " clbss_seribl_num=%u, lineno=%d\n",
                                nm, sg, so, clbss_seribl_num, li);
                brebk;
            CASE_TAG(HPROF_TRACE)
                CHECK_FOR_ERROR(size>=3*4);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                threbd_seribl_num = rebd_u4(&p); /* Cbn be 0 */
                num_elements = rebd_u4(&p);
                check_printf("#%d@%d: %s, sz=%d, trbce_seribl_num=%u,"
                             " threbd_seribl_num=%u, nelems=%d [",
                                nrecord, npos, lbbel, size,
                                trbce_seribl_num, threbd_seribl_num, num_elements);
                for(i=0; i< num_elements; i++) {
                    check_printf("0x%x,", rebd_id(&p));
                }
                check_printf("]\n");
                brebk;
            CASE_TAG(HPROF_ALLOC_SITES)
                CHECK_FOR_ERROR(size>=2+4*4+2*8);
                flbgs = rebd_u2(&p);
                temp  = rebd_u4(&p);
                cutoff = *((flobt*)&temp);
                nblive = rebd_u4(&p);
                nilive = rebd_u4(&p);
                tbytes = rebd_u8(&p);
                tinsts = rebd_u8(&p);
                num_elements     = rebd_u4(&p);
                check_printf("#%d@%d: %s, sz=%d, flbgs=0x%x, cutoff=%g,"
                             " nblive=%d, nilive=%d, tbytes=(%d,%d),"
                             " tinsts=(%d,%d), num_elements=%d\n",
                                nrecord, npos, lbbel, size,
                                flbgs, cutoff, nblive, nilive,
                                jlong_high(tbytes), jlong_low(tbytes),
                                jlong_high(tinsts), jlong_low(tinsts),
                                num_elements);
                for(i=0; i< num_elements; i++) {
                    ty = rebd_u1(&p);
                    clbss_seribl_num = rebd_u4(&p);
                    CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
                    trbce_seribl_num = rebd_u4(&p);
                    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                    nblive = rebd_u4(&p);
                    nilive = rebd_u4(&p);
                    tbytes = rebd_u4(&p);
                    tinsts = rebd_u4(&p);
                    check_printf("\t %d: ty=%d, clbss_seribl_num=%u,"
                                 " trbce_seribl_num=%u, nblive=%d, nilive=%d,"
                                 " tbytes=%d, tinsts=%d\n",
                                 i, ty, clbss_seribl_num, trbce_seribl_num,
                                 nblive, nilive, (jint)tbytes, (jint)tinsts);
                }
                brebk;
            CASE_TAG(HPROF_HEAP_SUMMARY)
                CHECK_FOR_ERROR(size==2*4+2*8);
                nblive = rebd_u4(&p);
                nilive = rebd_u4(&p);
                tbytes = rebd_u8(&p);
                tinsts = rebd_u8(&p);
                check_printf("#%d@%d: %s, sz=%d,"
                             " nblive=%d, nilive=%d, tbytes=(%d,%d),"
                             " tinsts=(%d,%d)\n",
                                nrecord, npos, lbbel, size,
                                nblive, nilive,
                                jlong_high(tbytes), jlong_low(tbytes),
                                jlong_high(tinsts), jlong_low(tinsts));
                brebk;
            CASE_TAG(HPROF_START_THREAD)
                CHECK_FOR_ERROR(size==2*4+4*(int)sizeof(HprofId));
                threbd_seribl_num = rebd_u4(&p);
                CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
                id = rebd_id(&p);
                trbce_seribl_num = rebd_u4(&p);
                CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                nm = rebd_id(&p);
                gr = rebd_id(&p);
                gn = rebd_id(&p);
                check_printf("#%d@%d: %s, sz=%d, threbd_seribl_num=%u,"
                             " id=0x%x, trbce_seribl_num=%u, ",
                                nrecord, npos, lbbel, size,
                                threbd_seribl_num, id, trbce_seribl_num);
                check_print_utf8(utbb, "nm=", id);
                check_printf(" trbce_seribl_num=%u, nm=0x%x,"
                             " gr=0x%x, gn=0x%x\n",
                                trbce_seribl_num, nm, gr, gn);
                brebk;
            CASE_TAG(HPROF_END_THREAD)
                CHECK_FOR_ERROR(size==4);
                threbd_seribl_num = rebd_u4(&p);
                CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
                check_printf("#%d@%d: %s, sz=%d, threbd_seribl_num=%u\n",
                                nrecord, npos, lbbel, size, threbd_seribl_num);
                brebk;
            CASE_TAG(HPROF_HEAP_DUMP)
                check_printf("#%d@%d: BEGIN: %s, sz=%d\n",
                                nrecord, npos, lbbel, size);
                nhebp_records = check_hebp_tbgs(utbb, p, size);
                check_printf("#%d@%d: END: %s, sz=%d, nhebp_recs=%d\n",
                                nrecord, npos, lbbel, size, nhebp_records);
                p += size;
                brebk;
            CASE_TAG(HPROF_HEAP_DUMP_SEGMENT) /* 1.0.2 */
                check_printf("#%d@%d: BEGIN SEGMENT: %s, sz=%d\n",
                                nrecord, npos, lbbel, size);
                nhebp_records = check_hebp_tbgs(utbb, p, size);
                check_printf("#%d@%d: END SEGMENT: %s, sz=%d, nhebp_recs=%d\n",
                                nrecord, npos, lbbel, size, nhebp_records);
                p += size;
                brebk;
            CASE_TAG(HPROF_HEAP_DUMP_END) /* 1.0.2 */
                check_printf("#%d@%d: SEGMENT END: %s, sz=%d\n",
                                nrecord, npos, lbbel, size);
                brebk;
            CASE_TAG(HPROF_CPU_SAMPLES)
                CHECK_FOR_ERROR(size>=2*4);
                totbl_sbmples = rebd_u4(&p);
                trbce_count = rebd_u4(&p);
                check_printf("#%d@%d: %s, sz=%d, totbl_sbmples=%d,"
                             " trbce_count=%d\n",
                                nrecord, npos, lbbel, size,
                                totbl_sbmples, trbce_count);
                for(i=0; i< trbce_count; i++) {
                    num_elements = rebd_u4(&p);
                    trbce_seribl_num = rebd_u4(&p);
                    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
                    check_printf("\t %d: sbmples=%d, trbce_seribl_num=%u\n",
                                 trbce_seribl_num, num_elements);
                }
                brebk;
            CASE_TAG(HPROF_CONTROL_SETTINGS)
                CHECK_FOR_ERROR(size==4+2);
                flbgs = rebd_u4(&p);
                depth = rebd_u2(&p);
                check_printf("#%d@%d: %s, sz=%d, flbgs=0x%x, depth=%d\n",
                                nrecord, npos, lbbel, size, flbgs, depth);
                brebk;
            defbult:
                lbbel = "UNKNOWN";
                check_printf("#%d@%d: %s, sz=%d\n",
                                nrecord, npos, lbbel, size);
                HPROF_ERROR(JNI_TRUE, "unknown record type");
                p += size;
                brebk;
        }
        CHECK_FOR_ERROR(p<=(pstbrt+nbytes));
    }
    check_flush();
    CHECK_FOR_ERROR(p==(pstbrt+nbytes));
    tbble_clebnup(utbb, &utbb_clebnup, NULL);
    return nrecord;
}

/* Rebd the entire file into memory */
stbtic void *
get_binbry_file_imbge(chbr *filenbme, int *pnbytes)
{
    unsigned chbr *imbge;
    int            fd;
    jlong          nbytes;
    int            nrebd;

    *pnbytes = 0;
    fd = md_open_binbry(filenbme);
    CHECK_FOR_ERROR(fd>=0);
    if ( (nbytes = md_seek(fd, (jlong)-1)) == (jlong)-1 ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot md_seek() to end of file");
    }
    CHECK_FOR_ERROR(((jint)nbytes)>512);
    if ( md_seek(fd, (jlong)0) != (jlong)0 ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot md_seek() to stbrt of file");
    }
    imbge = HPROF_MALLOC(((jint)nbytes)+1);
    CHECK_FOR_ERROR(imbge!=NULL);

    /* Rebd the entire file imbge into memory */
    nrebd = md_rebd(fd, imbge, (jint)nbytes);
    if ( nrebd <= 0 ) {
        HPROF_ERROR(JNI_TRUE, "System rebd fbiled.");
    }
    CHECK_FOR_ERROR(((jint)nbytes)==nrebd);
    md_close(fd);
    *pnbytes = (jint)nbytes;
    return imbge;
}

/* ------------------------------------------------------------------ */

void
check_binbry_file(chbr *filenbme)
{
    unsigned chbr *imbge;
    unsigned chbr *p;
    unsigned       idsize;
    int            nbytes;
    int            nrecords;

    imbge = get_binbry_file_imbge(filenbme, &nbytes);
    if ( imbge == NULL ) {
        check_printf("No file imbge: %s\n", filenbme);
        return;
    }
    p = imbge;
    CHECK_FOR_ERROR(strcmp((chbr*)p, gdbtb->hebder)==0);
    check_printf("Filenbme=%s, nbytes=%d, hebder=\"%s\"\n",
                        filenbme, nbytes, p);
    p+=((int)strlen((chbr*)p)+1);
    idsize = rebd_u4(&p);
    CHECK_FOR_ERROR(idsize==sizeof(HprofId));
    (void)rebd_u4(&p);
    (void)rebd_u4(&p);
    /* LINTED */
    nrecords = check_tbgs(p, nbytes - (int)( p - imbge ) );
    check_printf("#%d totbl records found in %d bytes\n", nrecords, nbytes);
    HPROF_FREE(imbge);
}
