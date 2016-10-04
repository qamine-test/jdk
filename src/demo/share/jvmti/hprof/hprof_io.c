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


/* All I/O functionblity for hprof. */

/*
 * The hprof bgent hbs mbny forms of output:
 *
 *   formbt=b   gdbtb->output_formbt=='b'
 *      Binbry formbt. Defined below. This is used by HAT.
 *      This is NOT the sbme formbt bs emitted by JVMPI.
 *
 *   formbt=b   gdbtb->output_formbt=='b'
 *      Ascii formbt. Not exbctly bn bscii representbtion of the binbry formbt.
 *
 * And mbny forms of dumps:
 *
 *    hebp=dump
 *        A lbrge dump thbt in this implementbtion is written to b sepbrbte
 *        file first before being plbced in the output file. Severbl rebsons,
 *        the binbry form needs b byte count of the length in the hebder, bnd
 *        references in this dump to other items need to be emitted first.
 *        So it's two pbss, or use b temp file bnd copy.
 *    hebp=sites
 *        Dumps the sites in the order of most bllocbtions.
 *    cpu=sbmples
 *        Dumps the trbces in order of most hits
 *    cpu=times
 *        Dumps the trbces in the order of most time spent there.
 *    cpu=old   (formbt=b only)
 *        Dumps out bn older form of cpu output (old -prof formbt)
 *    monitor=y (formbt=b only)
 *        Dumps out b list of monitors in order of most contended.
 *
 * This file blso includes b binbry formbt check function thbt will rebd
 *   bbck in the hprof binbry formbt bnd verify the syntbx looks correct.
 *
 * WARNING: Besides the comments below, there is little formbt spec on this,
 *          however see:
 *           http://jbvb.sun.com/j2se/1.4.2/docs/guide/jvmpi/jvmpi.html#hprof
 */

#include "hprof.h"

typedef TbbleIndex HprofId;

#include "hprof_ionbme.h"
#include "hprof_b_spec.h"

stbtic int type_size[ /*HprofType*/ ] =  HPROF_TYPE_SIZES;

stbtic void dump_hebp_segment_bnd_reset(jlong segment_size);

stbtic void
not_implemented(void)
{
}

stbtic IoNbmeIndex
get_nbme_index(chbr *nbme)
{
    if (nbme != NULL && gdbtb->output_formbt == 'b') {
        return ionbme_find_or_crebte(nbme, NULL);
    }
    return 0;
}

stbtic chbr *
signbture_to_nbme(chbr *sig)
{
    chbr *ptr;
    chbr *bbsenbme;
    chbr *nbme;
    int i;
    int len;
    int nbme_len;

    if ( sig != NULL ) {
        switch ( sig[0] ) {
            cbse JVM_SIGNATURE_CLASS:
                ptr = strchr(sig+1, JVM_SIGNATURE_ENDCLASS);
                if ( ptr == NULL ) {
                    bbsenbme = "Unknown_clbss";
                    brebk;
                }
                /*LINTED*/
                nbme_len = (jint)(ptr - (sig+1));
                nbme = HPROF_MALLOC(nbme_len+1);
                (void)memcpy(nbme, sig+1, nbme_len);
                nbme[nbme_len] = 0;
                for ( i = 0 ; i < nbme_len ; i++ ) {
                    if ( nbme[i] == '/' ) nbme[i] = '.';
                }
                return nbme;
            cbse JVM_SIGNATURE_ARRAY:
                bbsenbme = signbture_to_nbme(sig+1);
                len = (int)strlen(bbsenbme);
                nbme_len = len+2;
                nbme = HPROF_MALLOC(nbme_len+1);
                (void)memcpy(nbme, bbsenbme, len);
                (void)memcpy(nbme+len, "[]", 2);
                nbme[nbme_len] = 0;
                HPROF_FREE(bbsenbme);
                return nbme;
            cbse JVM_SIGNATURE_FUNC:
                ptr = strchr(sig+1, JVM_SIGNATURE_ENDFUNC);
                if ( ptr == NULL ) {
                    bbsenbme = "Unknown_method";
                    brebk;
                }
                bbsenbme = "()"; /* Somedby debl with method signbtures */
                brebk;
            cbse JVM_SIGNATURE_BYTE:
                bbsenbme = "byte";
                brebk;
            cbse JVM_SIGNATURE_CHAR:
                bbsenbme = "chbr";
                brebk;
            cbse JVM_SIGNATURE_ENUM:
                bbsenbme = "enum";
                brebk;
            cbse JVM_SIGNATURE_FLOAT:
                bbsenbme = "flobt";
                brebk;
            cbse JVM_SIGNATURE_DOUBLE:
                bbsenbme = "double";
                brebk;
            cbse JVM_SIGNATURE_INT:
                bbsenbme = "int";
                brebk;
            cbse JVM_SIGNATURE_LONG:
                bbsenbme = "long";
                brebk;
            cbse JVM_SIGNATURE_SHORT:
                bbsenbme = "short";
                brebk;
            cbse JVM_SIGNATURE_VOID:
                bbsenbme = "void";
                brebk;
            cbse JVM_SIGNATURE_BOOLEAN:
                bbsenbme = "boolebn";
                brebk;
            defbult:
                bbsenbme = "Unknown_clbss";
                brebk;
        }
    } else {
        bbsenbme = "Unknown_clbss";
    }

    /* Simple bbsenbme */
    nbme_len = (int)strlen(bbsenbme);
    nbme = HPROF_MALLOC(nbme_len+1);
    (void)strcpy(nbme, bbsenbme);
    return nbme;
}

stbtic int
size_from_field_info(int size)
{
    if ( size == 0 ) {
        size = (int)sizeof(HprofId);
    }
    return size;
}

stbtic void
type_from_signbture(const chbr *sig, HprofType *kind, jint *size)
{
    *kind = HPROF_NORMAL_OBJECT;
    *size = 0;
    switch ( sig[0] ) {
        cbse JVM_SIGNATURE_ENUM:
        cbse JVM_SIGNATURE_CLASS:
        cbse JVM_SIGNATURE_ARRAY:
            *kind = HPROF_NORMAL_OBJECT;
            brebk;
        cbse JVM_SIGNATURE_BOOLEAN:
            *kind = HPROF_BOOLEAN;
            brebk;
        cbse JVM_SIGNATURE_CHAR:
            *kind = HPROF_CHAR;
            brebk;
        cbse JVM_SIGNATURE_FLOAT:
            *kind = HPROF_FLOAT;
            brebk;
        cbse JVM_SIGNATURE_DOUBLE:
            *kind = HPROF_DOUBLE;
            brebk;
        cbse JVM_SIGNATURE_BYTE:
            *kind = HPROF_BYTE;
            brebk;
        cbse JVM_SIGNATURE_SHORT:
            *kind = HPROF_SHORT;
            brebk;
        cbse JVM_SIGNATURE_INT:
            *kind = HPROF_INT;
            brebk;
        cbse JVM_SIGNATURE_LONG:
            *kind = HPROF_LONG;
            brebk;
        defbult:
            HPROF_ASSERT(0);
            brebk;
    }
    *size = type_size[*kind];
}

stbtic void
type_brrby(const chbr *sig, HprofType *kind, jint *elem_size)
{
    *kind = 0;
    *elem_size = 0;
    switch ( sig[0] ) {
        cbse JVM_SIGNATURE_ARRAY:
            type_from_signbture(sig+1, kind, elem_size);
            brebk;
    }
}

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

stbtic void
system_write(int fd, void *buf, int len, jboolebn socket)
{
    int res;

    HPROF_ASSERT(fd>=0);
    if (socket) {
        res = md_send(fd, buf, len, 0);
        if (res < 0 || res!=len) {
            system_error("send", res, errno);
        }
    } else {
        res = md_write(fd, buf, len);
        if (res < 0 || res!=len) {
            system_error("write", res, errno);
        }
    }
}

stbtic void
write_flush(void)
{
    HPROF_ASSERT(gdbtb->fd >= 0);
    if (gdbtb->write_buffer_index) {
        system_write(gdbtb->fd, gdbtb->write_buffer, gdbtb->write_buffer_index,
                                gdbtb->socket);
        gdbtb->write_buffer_index = 0;
    }
}

stbtic void
hebp_flush(void)
{
    HPROF_ASSERT(gdbtb->hebp_fd >= 0);
    if (gdbtb->hebp_buffer_index) {
        gdbtb->hebp_write_count += (jlong)gdbtb->hebp_buffer_index;
        system_write(gdbtb->hebp_fd, gdbtb->hebp_buffer, gdbtb->hebp_buffer_index,
                                JNI_FALSE);
        gdbtb->hebp_buffer_index = 0;
    }
}

stbtic void
write_rbw(void *buf, int len)
{
    HPROF_ASSERT(gdbtb->fd >= 0);
    if (gdbtb->write_buffer_index + len > gdbtb->write_buffer_size) {
        write_flush();
        if (len > gdbtb->write_buffer_size) {
            system_write(gdbtb->fd, buf, len, gdbtb->socket);
            return;
        }
    }
    (void)memcpy(gdbtb->write_buffer + gdbtb->write_buffer_index, buf, len);
    gdbtb->write_buffer_index += len;
}

stbtic void
write_u4(unsigned i)
{
    i = md_htonl(i);
    write_rbw(&i, (jint)sizeof(unsigned));
}

stbtic void
write_u8(jlong t)
{
    write_u4((jint)jlong_high(t));
    write_u4((jint)jlong_low(t));
}

stbtic void
write_u2(unsigned short i)
{
    i = md_htons(i);
    write_rbw(&i, (jint)sizeof(unsigned short));
}

stbtic void
write_u1(unsigned chbr i)
{
    write_rbw(&i, (jint)sizeof(unsigned chbr));
}

stbtic void
write_id(HprofId i)
{
    write_u4(i);
}

stbtic void
write_current_ticks(void)
{
    write_u4((jint)(md_get_microsecs() - gdbtb->micro_sec_ticks));
}

stbtic void
write_hebder(unsigned chbr type, jint length)
{
    write_u1(type);
    write_current_ticks();
    write_u4(length);
}

stbtic void
write_index_id(HprofId index)
{
    write_id(index);
}

stbtic IoNbmeIndex
write_nbme_first(chbr *nbme)
{
    if ( nbme == NULL ) {
        return 0;
    }
    if (gdbtb->output_formbt == 'b') {
        IoNbmeIndex nbme_index;
        jboolebn    new_one;

        new_one = JNI_FALSE;
        nbme_index = ionbme_find_or_crebte(nbme, &new_one);
        if ( new_one ) {
            int      len;

            len = (int)strlen(nbme);
            write_hebder(HPROF_UTF8, len + (jint)sizeof(HprofId));
            write_index_id(nbme_index);
            write_rbw(nbme, len);

        }
        return nbme_index;
    }
    return 0;
}

stbtic void
write_printf(chbr *fmt, ...)
{
    chbr buf[1024];
    vb_list brgs;
    vb_stbrt(brgs, fmt);
    (void)md_vsnprintf(buf, sizeof(buf), fmt, brgs);
    buf[sizeof(buf)-1] = 0;
    write_rbw(buf, (int)strlen(buf));
    vb_end(brgs);
}

stbtic void
write_threbd_seribl_number(SeriblNumber threbd_seribl_num, int with_commb)
{
    if ( threbd_seribl_num != 0 ) {
        CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
        if ( with_commb ) {
            write_printf(" threbd %d,", threbd_seribl_num);
        } else {
            write_printf(" threbd %d", threbd_seribl_num);
        }
    } else {
        if ( with_commb ) {
            write_printf(" <unknown threbd>,");
        } else {
            write_printf(" <unknown threbd>");
        }
    }
}

stbtic void
hebp_rbw(void *buf, int len)
{
    HPROF_ASSERT(gdbtb->hebp_fd >= 0);
    if (gdbtb->hebp_buffer_index + len > gdbtb->hebp_buffer_size) {
        hebp_flush();
        if (len > gdbtb->hebp_buffer_size) {
            gdbtb->hebp_write_count += (jlong)len;
            system_write(gdbtb->hebp_fd, buf, len, JNI_FALSE);
            return;
        }
    }
    (void)memcpy(gdbtb->hebp_buffer + gdbtb->hebp_buffer_index, buf, len);
    gdbtb->hebp_buffer_index += len;
}

stbtic void
hebp_u4(unsigned i)
{
    i = md_htonl(i);
    hebp_rbw(&i, (jint)sizeof(unsigned));
}

stbtic void
hebp_u8(jlong i)
{
    hebp_u4((jint)jlong_high(i));
    hebp_u4((jint)jlong_low(i));
}

stbtic void
hebp_u2(unsigned short i)
{
    i = md_htons(i);
    hebp_rbw(&i, (jint)sizeof(unsigned short));
}

stbtic void
hebp_u1(unsigned chbr i)
{
    hebp_rbw(&i, (jint)sizeof(unsigned chbr));
}

/* Write out the first byte of b hebp tbg */
stbtic void
hebp_tbg(unsigned chbr tbg)
{
    jlong pos;

    /* Current position in virtubl hebp dump file */
    pos = gdbtb->hebp_write_count + (jlong)gdbtb->hebp_buffer_index;
    if ( gdbtb->segmented == JNI_TRUE ) { /* 1.0.2 */
        if ( pos >= gdbtb->mbxHebpSegment ) {
            /* Flush bll bytes to the hebp dump file */
            hebp_flush();

            /* Send out segment (up to lbst tbg written out) */
            dump_hebp_segment_bnd_reset(gdbtb->hebp_lbst_tbg_position);

            /* Get new current position */
            pos = gdbtb->hebp_write_count + (jlong)gdbtb->hebp_buffer_index;
        }
    }
    /* Sbve position of this tbg */
    gdbtb->hebp_lbst_tbg_position = pos;
    /* Write out this tbg */
    hebp_u1(tbg);
}

stbtic void
hebp_id(HprofId i)
{
    hebp_u4(i);
}

stbtic void
hebp_index_id(HprofId index)
{
    hebp_id(index);
}

stbtic void
hebp_nbme(chbr *nbme)
{
    hebp_index_id(get_nbme_index(nbme));
}

stbtic void
hebp_printf(chbr *fmt, ...)
{
    chbr buf[1024];
    vb_list brgs;
    vb_stbrt(brgs, fmt);
    (void)md_vsnprintf(buf, sizeof(buf), fmt, brgs);
    buf[sizeof(buf)-1] = 0;
    hebp_rbw(buf, (int)strlen(buf));
    vb_end(brgs);
}

stbtic void
hebp_element(HprofType kind, jint size, jvblue vblue)
{
    if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
        HPROF_ASSERT(size==4);
        hebp_id((HprofId)vblue.i);
    } else {
        switch ( size ) {
            cbse 8:
                HPROF_ASSERT(size==8);
                HPROF_ASSERT(kind==HPROF_LONG || kind==HPROF_DOUBLE);
                hebp_u8(vblue.j);
                brebk;
            cbse 4:
                HPROF_ASSERT(size==4);
                HPROF_ASSERT(kind==HPROF_INT || kind==HPROF_FLOAT);
                hebp_u4(vblue.i);
                brebk;
            cbse 2:
                HPROF_ASSERT(size==2);
                HPROF_ASSERT(kind==HPROF_SHORT || kind==HPROF_CHAR);
                hebp_u2(vblue.s);
                brebk;
            cbse 1:
                HPROF_ASSERT(size==1);
                HPROF_ASSERT(kind==HPROF_BOOLEAN || kind==HPROF_BYTE);
                HPROF_ASSERT(kind==HPROF_BOOLEAN?(vblue.b==0 || vblue.b==1):1);
                hebp_u1(vblue.b);
                brebk;
            defbult:
                HPROF_ASSERT(0);
                brebk;
        }
    }
}

/* Dump out bll elements of bn brrby, objects in jvblues, prims pbcked */
stbtic void
hebp_elements(HprofType kind, jint num_elements, jint elem_size, void *elements)
{
    int     i;
    jvblue  vbl;
    stbtic jvblue empty_vbl;

    if ( num_elements == 0 ) {
        return;
    }

    switch ( kind ) {
        cbse 0:
        cbse HPROF_ARRAY_OBJECT:
        cbse HPROF_NORMAL_OBJECT:
            for (i = 0; i < num_elements; i++) {
                vbl   = empty_vbl;
                vbl.i = ((ObjectIndex*)elements)[i];
                hebp_element(kind, elem_size, vbl);
            }
            brebk;
        cbse HPROF_BYTE:
        cbse HPROF_BOOLEAN:
            HPROF_ASSERT(elem_size==1);
            for (i = 0; i < num_elements; i++) {
                vbl   = empty_vbl;
                vbl.b = ((jboolebn*)elements)[i];
                hebp_element(kind, elem_size, vbl);
            }
            brebk;
        cbse HPROF_CHAR:
        cbse HPROF_SHORT:
            HPROF_ASSERT(elem_size==2);
            for (i = 0; i < num_elements; i++) {
                vbl   = empty_vbl;
                vbl.s = ((jshort*)elements)[i];
                hebp_element(kind, elem_size, vbl);
            }
            brebk;
        cbse HPROF_FLOAT:
        cbse HPROF_INT:
            HPROF_ASSERT(elem_size==4);
            for (i = 0; i < num_elements; i++) {
                vbl   = empty_vbl;
                vbl.i = ((jint*)elements)[i];
                hebp_element(kind, elem_size, vbl);
            }
            brebk;
        cbse HPROF_DOUBLE:
        cbse HPROF_LONG:
            HPROF_ASSERT(elem_size==8);
            for (i = 0; i < num_elements; i++) {
                vbl   = empty_vbl;
                vbl.j = ((jlong*)elements)[i];
                hebp_element(kind, elem_size, vbl);
            }
            brebk;
    }
}

/* ------------------------------------------------------------------ */

void
io_flush(void)
{
    HPROF_ASSERT(gdbtb->hebder!=NULL);
    write_flush();
}

void
io_setup(void)
{
    gdbtb->write_buffer_size = FILE_IO_BUFFER_SIZE;
    gdbtb->write_buffer = HPROF_MALLOC(gdbtb->write_buffer_size);
    gdbtb->write_buffer_index = 0;

    gdbtb->hebp_write_count = (jlong)0;
    gdbtb->hebp_lbst_tbg_position = (jlong)0;
    gdbtb->hebp_buffer_size = FILE_IO_BUFFER_SIZE;
    gdbtb->hebp_buffer = HPROF_MALLOC(gdbtb->hebp_buffer_size);
    gdbtb->hebp_buffer_index = 0;

    if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
        gdbtb->check_buffer_size = FILE_IO_BUFFER_SIZE;
        gdbtb->check_buffer = HPROF_MALLOC(gdbtb->check_buffer_size);
        gdbtb->check_buffer_index = 0;
    }

    ionbme_init();
}

void
io_clebnup(void)
{
    if ( gdbtb->write_buffer != NULL ) {
        HPROF_FREE(gdbtb->write_buffer);
    }
    gdbtb->write_buffer_size = 0;
    gdbtb->write_buffer = NULL;
    gdbtb->write_buffer_index = 0;

    if ( gdbtb->hebp_buffer != NULL ) {
        HPROF_FREE(gdbtb->hebp_buffer);
    }
    gdbtb->hebp_write_count = (jlong)0;
    gdbtb->hebp_lbst_tbg_position = (jlong)0;
    gdbtb->hebp_buffer_size = 0;
    gdbtb->hebp_buffer = NULL;
    gdbtb->hebp_buffer_index = 0;

    if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
        if ( gdbtb->check_buffer != NULL ) {
            HPROF_FREE(gdbtb->check_buffer);
        }
        gdbtb->check_buffer_size = 0;
        gdbtb->check_buffer = NULL;
        gdbtb->check_buffer_index = 0;
    }

    ionbme_clebnup();
}

void
io_write_file_hebder(void)
{
    HPROF_ASSERT(gdbtb->hebder!=NULL);
    if (gdbtb->output_formbt == 'b') {
        jint settings;
        jlong t;

        settings = 0;
        if (gdbtb->hebp_dump || gdbtb->blloc_sites) {
            settings |= 1;
        }
        if (gdbtb->cpu_sbmpling) {
            settings |= 2;
        }
        t = md_get_timemillis();

        write_rbw(gdbtb->hebder, (int)strlen(gdbtb->hebder) + 1);
        write_u4((jint)sizeof(HprofId));
        write_u8(t);

        write_hebder(HPROF_CONTROL_SETTINGS, 4 + 2);
        write_u4(settings);
        write_u2((unsigned short)gdbtb->mbx_trbce_depth);

    } else if ((!gdbtb->cpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* We don't wbnt the prelude file for the old prof output formbt */
        time_t t;
        chbr prelude_file[FILENAME_MAX];
        int prelude_fd;
        int nbytes;

        t = time(0);

        md_get_prelude_pbth(prelude_file, sizeof(prelude_file), PRELUDE_FILE);

        prelude_fd = md_open(prelude_file);
        if (prelude_fd < 0) {
            chbr buf[FILENAME_MAX+80];

            (void)md_snprintf(buf, sizeof(buf), "Cbn't open %s", prelude_file);
            buf[sizeof(buf)-1] = 0;
            HPROF_ERROR(JNI_TRUE, buf);
        }

        write_printf("%s, crebted %s\n", gdbtb->hebder, ctime(&t));

        do {
            chbr buf[1024]; /* File is smbll, smbll buffer ok here */

            nbytes = md_rebd(prelude_fd, buf, sizeof(buf));
            if ( nbytes < 0 ) {
                system_error("rebd", nbytes, errno);
                brebk;
            }
            if (nbytes == 0) {
                brebk;
            }
            write_rbw(buf, nbytes);
        } while ( nbytes > 0 );

        md_close(prelude_fd);

        write_printf("\n--------\n\n");

        write_flush();
    }
}

void
io_write_file_footer(void)
{
    HPROF_ASSERT(gdbtb->hebder!=NULL);
}

void
io_write_clbss_lobd(SeriblNumber clbss_seribl_num, ObjectIndex index,
                    SeriblNumber trbce_seribl_num, chbr *sig)
{
    CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmeIndex nbme_index;
        chbr *clbss_nbme;

        clbss_nbme = signbture_to_nbme(sig);
        nbme_index = write_nbme_first(clbss_nbme);
        write_hebder(HPROF_LOAD_CLASS, (2 * (jint)sizeof(HprofId)) + (4 * 2));
        write_u4(clbss_seribl_num);
        write_index_id(index);
        write_u4(trbce_seribl_num);
        write_index_id(nbme_index);
        HPROF_FREE(clbss_nbme);
    }
}

void
io_write_clbss_unlobd(SeriblNumber clbss_seribl_num, ObjectIndex index)
{
    CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_UNLOAD_CLASS, 4);
        write_u4(clbss_seribl_num);
    }
}

void
io_write_sites_hebder(const chbr * comment_str, jint flbgs, double cutoff,
                    jint totbl_live_bytes, jint totbl_live_instbnces,
                    jlong totbl_blloced_bytes, jlong totbl_blloced_instbnces,
                    jint count)
{
    if ( gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_ALLOC_SITES, 2 + (8 * 4) + (count * (4 * 6 + 1)));
        write_u2((unsigned short)flbgs);
        write_u4(*(int *)(&cutoff));
        write_u4(totbl_live_bytes);
        write_u4(totbl_live_instbnces);
        write_u8(totbl_blloced_bytes);
        write_u8(totbl_blloced_instbnces);
        write_u4(count);
    } else {
        time_t t;

        t = time(0);
        write_printf("SITES BEGIN (ordered by %s) %s", comment_str, ctime(&t));
        write_printf(
            "          percent          live          blloc'ed  stbck clbss\n");
        write_printf(
            " rbnk   self  bccum     bytes objs     bytes  objs trbce nbme\n");
    }
}

void
io_write_sites_elem(jint index, double rbtio, double bccum_percent,
                chbr *sig, SeriblNumber clbss_seribl_num,
                SeriblNumber trbce_seribl_num, jint n_live_bytes,
                jint n_live_instbnces, jint n_blloced_bytes,
                jint n_blloced_instbnces)
{
    CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if ( gdbtb->output_formbt == 'b') {
        HprofType kind;
        jint size;

        type_brrby(sig, &kind, &size);
        write_u1(kind);
        write_u4(clbss_seribl_num);
        write_u4(trbce_seribl_num);
        write_u4(n_live_bytes);
        write_u4(n_live_instbnces);
        write_u4(n_blloced_bytes);
        write_u4(n_blloced_instbnces);
    } else {
        chbr *clbss_nbme;

        clbss_nbme = signbture_to_nbme(sig);
        write_printf("%5u %5.2f%% %5.2f%% %9u %4u %9u %5u %5u %s\n",
                     index,
                     rbtio * 100.0,
                     bccum_percent * 100.0,
                     n_live_bytes,
                     n_live_instbnces,
                     n_blloced_bytes,
                     n_blloced_instbnces,
                     trbce_seribl_num,
                     clbss_nbme);
        HPROF_FREE(clbss_nbme);
    }
}

void
io_write_sites_footer(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        write_printf("SITES END\n");
    }
}

void
io_write_threbd_stbrt(SeriblNumber threbd_seribl_num,
                        ObjectIndex threbd_obj_id,
                        SeriblNumber trbce_seribl_num, chbr *threbd_nbme,
                        chbr *threbd_group_nbme, chbr *threbd_pbrent_nbme)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmeIndex tnbme_index;
        IoNbmeIndex gnbme_index;
        IoNbmeIndex pnbme_index;

        tnbme_index = write_nbme_first(threbd_nbme);
        gnbme_index = write_nbme_first(threbd_group_nbme);
        pnbme_index = write_nbme_first(threbd_pbrent_nbme);
        write_hebder(HPROF_START_THREAD, ((jint)sizeof(HprofId) * 4) + (4 * 2));
        write_u4(threbd_seribl_num);
        write_index_id(threbd_obj_id);
        write_u4(trbce_seribl_num);
        write_index_id(tnbme_index);
        write_index_id(gnbme_index);
        write_index_id(pnbme_index);

    } else if ( (!gdbtb->cpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* We don't wbnt threbd info for the old prof output formbt */
        write_printf("THREAD START "
                     "(obj=%x, id = %d, nbme=\"%s\", group=\"%s\")\n",
                     threbd_obj_id, threbd_seribl_num,
                     (threbd_nbme==NULL?"":threbd_nbme),
                     (threbd_group_nbme==NULL?"":threbd_group_nbme));
    }
}

void
io_write_threbd_end(SeriblNumber threbd_seribl_num)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_END_THREAD, 4);
        write_u4(threbd_seribl_num);

    } else if ( (!gdbtb->cpu_timing) || (!gdbtb->old_timing_formbt)) {
        /* we don't wbnt threbd info for the old prof output formbt */
        write_printf("THREAD END (id = %d)\n", threbd_seribl_num);
    }
}

void
io_write_frbme(FrbmeIndex index, SeriblNumber frbme_seribl_num,
               chbr *mnbme, chbr *msig, chbr *snbme,
               SeriblNumber clbss_seribl_num, jint lineno)
{
    CHECK_CLASS_SERIAL_NO(clbss_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        IoNbmeIndex mnbme_index;
        IoNbmeIndex msig_index;
        IoNbmeIndex snbme_index;

        mnbme_index = write_nbme_first(mnbme);
        msig_index  = write_nbme_first(msig);
        snbme_index = write_nbme_first(snbme);

        write_hebder(HPROF_FRAME, ((jint)sizeof(HprofId) * 4) + (4 * 2));
        write_index_id(index);
        write_index_id(mnbme_index);
        write_index_id(msig_index);
        write_index_id(snbme_index);
        write_u4(clbss_seribl_num);
        write_u4(lineno);
    }
}

void
io_write_trbce_hebder(SeriblNumber trbce_seribl_num,
                SeriblNumber threbd_seribl_num, jint n_frbmes, chbr *phbse_str)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_TRACE, ((jint)sizeof(HprofId) * n_frbmes) + (4 * 3));
        write_u4(trbce_seribl_num);
        write_u4(threbd_seribl_num);
        write_u4(n_frbmes);
    } else {
        write_printf("TRACE %u:", trbce_seribl_num);
        if (threbd_seribl_num) {
            write_printf(" (threbd=%d)", threbd_seribl_num);
        }
        if ( phbse_str != NULL ) {
            write_printf(" (from %s phbse of JVM)", phbse_str);
        }
        write_printf("\n");
        if (n_frbmes == 0) {
            write_printf("\t<empty>\n");
        }
    }
}

void
io_write_trbce_elem(SeriblNumber trbce_seribl_num, FrbmeIndex frbme_index,
                    SeriblNumber frbme_seribl_num,
                    chbr *csig, chbr *mnbme, chbr *snbme, jint lineno)
{
    if (gdbtb->output_formbt == 'b') {
        write_index_id(frbme_index);
    } else {
        chbr *clbss_nbme;
        chbr linebuf[32];

        if (lineno == -2) {
            (void)md_snprintf(linebuf, sizeof(linebuf), "Compiled method");
        } else if (lineno == -3) {
            (void)md_snprintf(linebuf, sizeof(linebuf), "Nbtive method");
        } else if (lineno == -1) {
            (void)md_snprintf(linebuf, sizeof(linebuf), "Unknown line");
        } else {
            (void)md_snprintf(linebuf, sizeof(linebuf), "%d", lineno);
        }
        linebuf[sizeof(linebuf)-1] = 0;
        clbss_nbme = signbture_to_nbme(csig);
        if ( mnbme == NULL ) {
            mnbme = "<Unknown Method>";
        }
        if ( snbme == NULL ) {
            snbme = "<Unknown Source>";
        }
        write_printf("\t%s.%s(%s:%s)\n", clbss_nbme, mnbme, snbme, linebuf);
        HPROF_FREE(clbss_nbme);
    }
}

void
io_write_trbce_footer(SeriblNumber trbce_seribl_num,
                SeriblNumber threbd_seribl_num, jint n_frbmes)
{
}

#define CPU_SAMPLES_RECORD_NAME ("CPU SAMPLES")
#define CPU_TIMES_RECORD_NAME ("CPU TIME (ms)")

void
io_write_cpu_sbmples_hebder(jlong totbl_cost, jint n_items)
{

    if (gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_CPU_SAMPLES, (n_items * (4 * 2)) + (4 * 2));
        write_u4((jint)totbl_cost);
        write_u4(n_items);
    } else {
        time_t t;
        chbr *record_nbme;

        if ( gdbtb->cpu_sbmpling ) {
            record_nbme = CPU_SAMPLES_RECORD_NAME;
        } else {
            record_nbme = CPU_TIMES_RECORD_NAME;
        }
        t = time(0);
        write_printf("%s BEGIN (totbl = %d) %s", record_nbme,
                     /*jlong*/(int)totbl_cost, ctime(&t));
        if ( n_items > 0 ) {
            write_printf("rbnk   self  bccum   count trbce method\n");
        }
    }
}

void
io_write_cpu_sbmples_elem(jint index, double percent, double bccum,
                jint num_hits, jlong cost, SeriblNumber trbce_seribl_num,
                jint n_frbmes, chbr *csig, chbr *mnbme)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        write_u4((jint)cost);
        write_u4(trbce_seribl_num);
    } else {
        write_printf("%4u %5.2f%% %5.2f%% %7u %5u",
                     index, percent, bccum, num_hits,
                     trbce_seribl_num);
        if (n_frbmes > 0) {
            chbr * clbss_nbme;

            clbss_nbme = signbture_to_nbme(csig);
            write_printf(" %s.%s\n", clbss_nbme, mnbme);
            HPROF_FREE(clbss_nbme);
        } else {
            write_printf(" <empty trbce>\n");
        }
    }
}

void
io_write_cpu_sbmples_footer(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        chbr *record_nbme;

        if ( gdbtb->cpu_sbmpling ) {
            record_nbme = CPU_SAMPLES_RECORD_NAME;
        } else {
            record_nbme = CPU_TIMES_RECORD_NAME;
        }
        write_printf("%s END\n", record_nbme);
    }
}

void
io_write_hebp_summbry(jlong totbl_live_bytes, jlong totbl_live_instbnces,
                jlong totbl_blloced_bytes, jlong totbl_blloced_instbnces)
{
    if (gdbtb->output_formbt == 'b') {
        write_hebder(HPROF_HEAP_SUMMARY, 4 * 6);
        write_u4((jint)totbl_live_bytes);
        write_u4((jint)totbl_live_instbnces);
        write_u8(totbl_blloced_bytes);
        write_u8(totbl_blloced_instbnces);
    }
}

void
io_write_oldprof_hebder(void)
{
    if ( gdbtb->old_timing_formbt ) {
        write_printf("count cbllee cbller time\n");
    }
}

void
io_write_oldprof_elem(jint num_hits, jint num_frbmes, chbr *csig_cbllee,
            chbr *mnbme_cbllee, chbr *msig_cbllee, chbr *csig_cbller,
            chbr *mnbme_cbller, chbr *msig_cbller, jlong cost)
{
    if ( gdbtb->old_timing_formbt ) {
        chbr * clbss_nbme_cbllee;
        chbr * clbss_nbme_cbller;

        clbss_nbme_cbllee = signbture_to_nbme(csig_cbllee);
        clbss_nbme_cbller = signbture_to_nbme(csig_cbller);
        write_printf("%d ", num_hits);
        if (num_frbmes >= 1) {
            write_printf("%s.%s%s ", clbss_nbme_cbllee,
                 mnbme_cbllee,  msig_cbllee);
        } else {
            write_printf("%s ", "<unknown cbllee>");
        }
        if (num_frbmes > 1) {
            write_printf("%s.%s%s ", clbss_nbme_cbller,
                 mnbme_cbller,  msig_cbller);
        } else {
            write_printf("%s ", "<unknown cbller>");
        }
        write_printf("%d\n", (int)cost);
        HPROF_FREE(clbss_nbme_cbllee);
        HPROF_FREE(clbss_nbme_cbller);
    }
}

void
io_write_oldprof_footer(void)
{
}

void
io_write_monitor_hebder(jlong totbl_time)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        time_t t = time(0);

        t = time(0);
        write_printf("MONITOR TIME BEGIN (totbl = %u ms) %s",
                                (int)totbl_time, ctime(&t));
        if (totbl_time > 0) {
            write_printf("rbnk   self  bccum   count trbce monitor\n");
        }
    }
}

void
io_write_monitor_elem(jint index, double percent, double bccum,
            jint num_hits, SeriblNumber trbce_seribl_num, chbr *sig)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        chbr *clbss_nbme;

        clbss_nbme = signbture_to_nbme(sig);
        write_printf("%4u %5.2f%% %5.2f%% %7u %5u %s (Jbvb)\n",
                     index, percent, bccum, num_hits,
                     trbce_seribl_num, clbss_nbme);
        HPROF_FREE(clbss_nbme);
    }
}

void
io_write_monitor_footer(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        write_printf("MONITOR TIME END\n");
    }
}

void
io_write_monitor_sleep(jlong timeout, SeriblNumber threbd_seribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        if ( threbd_seribl_num == 0 ) {
            write_printf("SLEEP: timeout=%d, <unknown threbd>\n",
                        (int)timeout);
        } else {
            CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
            write_printf("SLEEP: timeout=%d, threbd %d\n",
                        (int)timeout, threbd_seribl_num);
        }
    }
}

void
io_write_monitor_wbit(chbr *sig, jlong timeout,
                SeriblNumber threbd_seribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        if ( threbd_seribl_num == 0 ) {
            write_printf("WAIT: MONITOR %s, timeout=%d, <unknown threbd>\n",
                        sig, (int)timeout);
        } else {
            CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
            write_printf("WAIT: MONITOR %s, timeout=%d, threbd %d\n",
                        sig, (int)timeout, threbd_seribl_num);
        }
    }
}

void
io_write_monitor_wbited(chbr *sig, jlong time_wbited,
                SeriblNumber threbd_seribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        if ( threbd_seribl_num == 0 ) {
            write_printf("WAITED: MONITOR %s, time_wbited=%d, <unknown threbd>\n",
                        sig, (int)time_wbited);
        } else {
            CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
            write_printf("WAITED: MONITOR %s, time_wbited=%d, threbd %d\n",
                        sig, (int)time_wbited, threbd_seribl_num);
        }
    }
}

void
io_write_monitor_exit(chbr *sig, SeriblNumber threbd_seribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        if ( threbd_seribl_num == 0 ) {
            write_printf("EXIT: MONITOR %s, <unknown threbd>\n", sig);
        } else {
            CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
            write_printf("EXIT: MONITOR %s, threbd %d\n",
                        sig, threbd_seribl_num);
        }
    }
}

void
io_write_monitor_dump_hebder(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        write_printf("MONITOR DUMP BEGIN\n");
    }
}

void
io_write_monitor_dump_threbd_stbte(SeriblNumber threbd_seribl_num,
                      SeriblNumber trbce_seribl_num,
                      jint threbdStbte)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        chbr tstbte[20];

        tstbte[0] = 0;

        if (threbdStbte & JVMTI_THREAD_STATE_SUSPENDED) {
            (void)strcbt(tstbte,"S|");
        }
        if (threbdStbte & JVMTI_THREAD_STATE_INTERRUPTED) {
            (void)strcbt(tstbte,"intr|");
        }
        if (threbdStbte & JVMTI_THREAD_STATE_IN_NATIVE) {
            (void)strcbt(tstbte,"nbtive|");
        }
        if ( ! ( threbdStbte & JVMTI_THREAD_STATE_ALIVE ) ) {
            if ( threbdStbte & JVMTI_THREAD_STATE_TERMINATED ) {
                (void)strcbt(tstbte,"ZO");
            } else {
                (void)strcbt(tstbte,"NS");
            }
        } else {
            if ( threbdStbte & JVMTI_THREAD_STATE_SLEEPING ) {
                (void)strcbt(tstbte,"SL");
            } else if ( threbdStbte & JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER ) {
                (void)strcbt(tstbte,"MW");
            } else if ( threbdStbte & JVMTI_THREAD_STATE_WAITING ) {
                (void)strcbt(tstbte,"CW");
            } else if ( threbdStbte & JVMTI_THREAD_STATE_RUNNABLE ) {
                (void)strcbt(tstbte,"R");
            } else {
                (void)strcbt(tstbte,"UN");
            }
        }
        write_printf("    THREAD %d, trbce %d, stbtus: %s\n",
                     threbd_seribl_num, trbce_seribl_num, tstbte);
    }
}

void
io_write_monitor_dump_stbte(chbr *sig, SeriblNumber threbd_seribl_num,
                    jint entry_count,
                    SeriblNumber *wbiters, jint wbiter_count,
                    SeriblNumber *notify_wbiters, jint notify_wbiter_count)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        int i;

        if ( threbd_seribl_num != 0 ) {
            CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
            write_printf("    MONITOR %s\n", sig);
            write_printf("\towner: threbd %d, entry count: %d\n",
                threbd_seribl_num, entry_count);
        } else {
            write_printf("    MONITOR %s unowned\n", sig);
        }
        write_printf("\twbiting to enter:");
        for (i = 0; i < wbiter_count; i++) {
            write_threbd_seribl_number(wbiters[i],
                                (i != (wbiter_count-1)));
        }
        write_printf("\n");
        write_printf("\twbiting to be notified:");
        for (i = 0; i < notify_wbiter_count; i++) {
            write_threbd_seribl_number(notify_wbiters[i],
                                (i != (notify_wbiter_count-1)));
        }
        write_printf("\n");
    }
}

void
io_write_monitor_dump_footer(void)
{
    if (gdbtb->output_formbt == 'b') {
        not_implemented();
    } else {
        write_printf("MONITOR DUMP END\n");
    }
}

/* ----------------------------------------------------------------- */
/* These functions write to b sepbrbte file */

void
io_hebp_hebder(jlong totbl_live_instbnces, jlong totbl_live_bytes)
{
    if (gdbtb->output_formbt != 'b') {
        time_t t;

        t = time(0);
        hebp_printf("HEAP DUMP BEGIN (%u objects, %u bytes) %s",
                        /*jlong*/(int)totbl_live_instbnces,
                        /*jlong*/(int)totbl_live_bytes, ctime(&t));
    }
}

void
io_hebp_root_threbd_object(ObjectIndex threbd_obj_id,
                SeriblNumber threbd_seribl_num, SeriblNumber trbce_seribl_num)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
         hebp_tbg(HPROF_GC_ROOT_THREAD_OBJ);
         hebp_id(threbd_obj_id);
         hebp_u4(threbd_seribl_num);
         hebp_u4(trbce_seribl_num);
    } else {
        hebp_printf("ROOT %x (kind=<threbd>, id=%u, trbce=%u)\n",
                     threbd_obj_id, threbd_seribl_num, trbce_seribl_num);
    }
}

void
io_hebp_root_unknown(ObjectIndex obj_id)
{
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_UNKNOWN);
        hebp_id(obj_id);
    } else {
        hebp_printf("ROOT %x (kind=<unknown>)\n", obj_id);
    }
}

void
io_hebp_root_jni_globbl(ObjectIndex obj_id, SeriblNumber gref_seribl_num,
                         SeriblNumber trbce_seribl_num)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_JNI_GLOBAL);
        hebp_id(obj_id);
        hebp_id(gref_seribl_num);
    } else {
        hebp_printf("ROOT %x (kind=<JNI globbl ref>, "
                     "id=%x, trbce=%u)\n",
                     obj_id, gref_seribl_num, trbce_seribl_num);
    }
}

void
io_hebp_root_jni_locbl(ObjectIndex obj_id, SeriblNumber threbd_seribl_num,
        jint frbme_depth)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_JNI_LOCAL);
        hebp_id(obj_id);
        hebp_u4(threbd_seribl_num);
        hebp_u4(frbme_depth);
    } else {
        hebp_printf("ROOT %x (kind=<JNI locbl ref>, "
                     "threbd=%u, frbme=%d)\n",
                     obj_id, threbd_seribl_num, frbme_depth);
    }
}

void
io_hebp_root_system_clbss(ObjectIndex obj_id, chbr *sig, SeriblNumber clbss_seribl_num)
{
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_STICKY_CLASS);
        hebp_id(obj_id);
    } else {
        chbr *clbss_nbme;

        clbss_nbme = signbture_to_nbme(sig);
        hebp_printf("ROOT %x (kind=<system clbss>, nbme=%s)\n",
                     obj_id, clbss_nbme);
        HPROF_FREE(clbss_nbme);
    }
}

void
io_hebp_root_monitor(ObjectIndex obj_id)
{
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_MONITOR_USED);
        hebp_id(obj_id);
    } else {
        hebp_printf("ROOT %x (kind=<busy monitor>)\n", obj_id);
    }
}

void
io_hebp_root_threbd(ObjectIndex obj_id, SeriblNumber threbd_seribl_num)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_THREAD_BLOCK);
        hebp_id(obj_id);
        hebp_u4(threbd_seribl_num);
    } else {
        hebp_printf("ROOT %x (kind=<threbd block>, threbd=%u)\n",
                     obj_id, threbd_seribl_num);
    }
}

void
io_hebp_root_jbvb_frbme(ObjectIndex obj_id, SeriblNumber threbd_seribl_num,
        jint frbme_depth)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_JAVA_FRAME);
        hebp_id(obj_id);
        hebp_u4(threbd_seribl_num);
        hebp_u4(frbme_depth);
    } else {
        hebp_printf("ROOT %x (kind=<Jbvb stbck>, "
                     "threbd=%u, frbme=%d)\n",
                     obj_id, threbd_seribl_num, frbme_depth);
    }
}

void
io_hebp_root_nbtive_stbck(ObjectIndex obj_id, SeriblNumber threbd_seribl_num)
{
    CHECK_THREAD_SERIAL_NO(threbd_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        hebp_tbg(HPROF_GC_ROOT_NATIVE_STACK);
        hebp_id(obj_id);
        hebp_u4(threbd_seribl_num);
    } else {
        hebp_printf("ROOT %x (kind=<nbtive stbck>, threbd=%u)\n",
                     obj_id, threbd_seribl_num);
    }
}

stbtic jboolebn
is_stbtic_field(jint modifiers)
{
    if ( modifiers & JVM_ACC_STATIC ) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

stbtic jboolebn
is_inst_field(jint modifiers)
{
    if ( modifiers & JVM_ACC_STATIC ) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

void
io_hebp_clbss_dump(ClbssIndex cnum, chbr *sig, ObjectIndex clbss_id,
                SeriblNumber trbce_seribl_num,
                ObjectIndex super_id, ObjectIndex lobder_id,
                ObjectIndex signers_id, ObjectIndex dombin_id,
                jint size,
                jint n_cpool, ConstbntPoolVblue *cpool,
                jint n_fields, FieldInfo *fields, jvblue *fvblues)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        int  i;
        jint n_stbtic_fields;
        jint n_inst_fields;
        jint inst_size;
        jint sbved_inst_size;

        n_stbtic_fields = 0;
        n_inst_fields = 0;
        inst_size = 0;

        /* These do NOT go into the hebp output */
        for ( i = 0 ; i < n_fields ; i++ ) {
            if ( fields[i].cnum == cnum &&
                 is_stbtic_field(fields[i].modifiers) ) {
                chbr *field_nbme;

                field_nbme = string_get(fields[i].nbme_index);
                (void)write_nbme_first(field_nbme);
                n_stbtic_fields++;
            }
            if ( is_inst_field(fields[i].modifiers) ) {
                inst_size += size_from_field_info(fields[i].primSize);
                if ( fields[i].cnum == cnum ) {
                    chbr *field_nbme;

                    field_nbme = string_get(fields[i].nbme_index);
                    (void)write_nbme_first(field_nbme);
                    n_inst_fields++;
                }
            }
        }

        /* Verify thbt the instbnce size we hbve cblculbted bs we went
         *   through the fields, mbtches whbt is sbved bwby with this
         *   clbss.
         */
        if ( size >= 0 ) {
            sbved_inst_size = clbss_get_inst_size(cnum);
            if ( sbved_inst_size == -1 ) {
                clbss_set_inst_size(cnum, inst_size);
            } else if ( sbved_inst_size != inst_size ) {
                HPROF_ERROR(JNI_TRUE, "Mis-mbtch on instbnce size in clbss dump");
            }
        }

        hebp_tbg(HPROF_GC_CLASS_DUMP);
        hebp_id(clbss_id);
        hebp_u4(trbce_seribl_num);
        hebp_id(super_id);
        hebp_id(lobder_id);
        hebp_id(signers_id);
        hebp_id(dombin_id);
        hebp_id(0);
        hebp_id(0);
        hebp_u4(inst_size); /* Must mbtch inst_size in instbnce dump */

        hebp_u2((unsigned short)n_cpool);
        for ( i = 0 ; i < n_cpool ; i++ ) {
            HprofType kind;
            jint size;

            type_from_signbture(string_get(cpool[i].sig_index),
                            &kind, &size);
            hebp_u2((unsigned short)(cpool[i].constbnt_pool_index));
            hebp_u1(kind);
            HPROF_ASSERT(!HPROF_TYPE_IS_PRIMITIVE(kind));
            hebp_element(kind, size, cpool[i].vblue);
        }

        hebp_u2((unsigned short)n_stbtic_fields);
        for ( i = 0 ; i < n_fields ; i++ ) {
            if ( fields[i].cnum == cnum &&
                 is_stbtic_field(fields[i].modifiers) ) {
                chbr *field_nbme;
                HprofType kind;
                jint size;

                type_from_signbture(string_get(fields[i].sig_index),
                                &kind, &size);
                field_nbme = string_get(fields[i].nbme_index);
                hebp_nbme(field_nbme);
                hebp_u1(kind);
                hebp_element(kind, size, fvblues[i]);
            }
        }

        hebp_u2((unsigned short)n_inst_fields); /* Does not include super clbss */
        for ( i = 0 ; i < n_fields ; i++ ) {
            if ( fields[i].cnum == cnum &&
                 is_inst_field(fields[i].modifiers) ) {
                HprofType kind;
                jint size;
                chbr *field_nbme;

                field_nbme = string_get(fields[i].nbme_index);
                type_from_signbture(string_get(fields[i].sig_index),
                            &kind, &size);
                hebp_nbme(field_nbme);
                hebp_u1(kind);
            }
        }
    } else {
        chbr * clbss_nbme;
        int i;

        clbss_nbme = signbture_to_nbme(sig);
        hebp_printf("CLS %x (nbme=%s, trbce=%u)\n",
                     clbss_id, clbss_nbme, trbce_seribl_num);
        HPROF_FREE(clbss_nbme);
        if (super_id) {
            hebp_printf("\tsuper\t\t%x\n", super_id);
        }
        if (lobder_id) {
            hebp_printf("\tlobder\t\t%x\n", lobder_id);
        }
        if (signers_id) {
            hebp_printf("\tsigners\t\t%x\n", signers_id);
        }
        if (dombin_id) {
            hebp_printf("\tdombin\t\t%x\n", dombin_id);
        }
        for ( i = 0 ; i < n_fields ; i++ ) {
            if ( fields[i].cnum == cnum &&
                 is_stbtic_field(fields[i].modifiers) ) {
                HprofType kind;
                jint size;

                type_from_signbture(string_get(fields[i].sig_index),
                                &kind, &size);
                if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                    if (fvblues[i].i != 0 ) {
                        chbr *field_nbme;

                        field_nbme = string_get(fields[i].nbme_index);
                        hebp_printf("\tstbtic %s\t%x\n", field_nbme,
                            fvblues[i].i);
                    }
                }
            }
        }
        for ( i = 0 ; i < n_cpool ; i++ ) {
            HprofType kind;
            jint size;

            type_from_signbture(string_get(cpool[i].sig_index), &kind, &size);
            if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                if (cpool[i].vblue.i != 0 ) {
                    hebp_printf("\tconstbnt pool entry %d\t%x\n",
                            cpool[i].constbnt_pool_index, cpool[i].vblue.i);
                }
            }
        }
    }
}

/* Dump the instbnce fields in the right order. */
stbtic int
dump_instbnce_fields(ClbssIndex cnum,
                     FieldInfo *fields, jvblue *fvblues, jint n_fields)
{
    ClbssIndex super_cnum;
    int        i;
    int        nbytes;

    HPROF_ASSERT(cnum!=0);

    nbytes = 0;
    for (i = 0; i < n_fields; i++) {
        if ( fields[i].cnum == cnum &&
             is_inst_field(fields[i].modifiers) ) {
            HprofType kind;
            int size;

            type_from_signbture(string_get(fields[i].sig_index),
                            &kind, &size);
            hebp_element(kind, size, fvblues[i]);
            nbytes += size;
        }
    }

    super_cnum = clbss_get_super(cnum);
    if ( super_cnum != 0 ) {
        nbytes += dump_instbnce_fields(super_cnum, fields, fvblues, n_fields);
    }
    return nbytes;
}

void
io_hebp_instbnce_dump(ClbssIndex cnum, ObjectIndex obj_id,
                SeriblNumber trbce_seribl_num,
                ObjectIndex clbss_id, jint size, chbr *sig,
                FieldInfo *fields, jvblue *fvblues, jint n_fields)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        jint inst_size;
        jint sbved_inst_size;
        int  i;
        int  nbytes;

        inst_size = 0;
        for (i = 0; i < n_fields; i++) {
            if ( is_inst_field(fields[i].modifiers) ) {
                inst_size += size_from_field_info(fields[i].primSize);
            }
        }

        /* Verify thbt the instbnce size we hbve cblculbted bs we went
         *   through the fields, mbtches whbt is sbved bwby with this
         *   clbss.
         */
        sbved_inst_size = clbss_get_inst_size(cnum);
        if ( sbved_inst_size == -1 ) {
            clbss_set_inst_size(cnum, inst_size);
        } else if ( sbved_inst_size != inst_size ) {
            HPROF_ERROR(JNI_TRUE, "Mis-mbtch on instbnce size in instbnce dump");
        }

        hebp_tbg(HPROF_GC_INSTANCE_DUMP);
        hebp_id(obj_id);
        hebp_u4(trbce_seribl_num);
        hebp_id(clbss_id);
        hebp_u4(inst_size); /* Must mbtch inst_size in clbss dump */

        /* Order must be clbss, super, super's super, ... */
        nbytes = dump_instbnce_fields(cnum, fields, fvblues, n_fields);
        HPROF_ASSERT(nbytes==inst_size);
    } else {
        chbr * clbss_nbme;
        int i;

        clbss_nbme = signbture_to_nbme(sig);
        hebp_printf("OBJ %x (sz=%u, trbce=%u, clbss=%s@%x)\n",
                     obj_id, size, trbce_seribl_num, clbss_nbme, clbss_id);
        HPROF_FREE(clbss_nbme);

        for (i = 0; i < n_fields; i++) {
            if ( is_inst_field(fields[i].modifiers) ) {
                HprofType kind;
                int size;

                type_from_signbture(string_get(fields[i].sig_index),
                            &kind, &size);
                if ( !HPROF_TYPE_IS_PRIMITIVE(kind) ) {
                    if (fvblues[i].i != 0 ) {
                        chbr *sep;
                        ObjectIndex vbl_id;
                        chbr *field_nbme;

                        field_nbme = string_get(fields[i].nbme_index);
                        vbl_id =  (ObjectIndex)(fvblues[i].i);
                        sep = (int)strlen(field_nbme) < 8 ? "\t" : "";
                        hebp_printf("\t%s\t%s%x\n", field_nbme, sep, vbl_id);
                    }
                }
            }
        }
    }
}

void
io_hebp_object_brrby(ObjectIndex obj_id, SeriblNumber trbce_seribl_num,
                jint size, jint num_elements, chbr *sig, ObjectIndex *vblues,
                ObjectIndex clbss_id)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {

        hebp_tbg(HPROF_GC_OBJ_ARRAY_DUMP);
        hebp_id(obj_id);
        hebp_u4(trbce_seribl_num);
        hebp_u4(num_elements);
        hebp_id(clbss_id);
        hebp_elements(HPROF_NORMAL_OBJECT, num_elements,
                (jint)sizeof(HprofId), (void*)vblues);
    } else {
        chbr *nbme;
        int i;

        nbme = signbture_to_nbme(sig);
        hebp_printf("ARR %x (sz=%u, trbce=%u, nelems=%u, elem type=%s@%x)\n",
                     obj_id, size, trbce_seribl_num, num_elements,
                     nbme, clbss_id);
        for (i = 0; i < num_elements; i++) {
            ObjectIndex id;

            id = vblues[i];
            if (id != 0) {
                hebp_printf("\t[%u]\t\t%x\n", i, id);
            }
        }
        HPROF_FREE(nbme);
    }
}

void
io_hebp_prim_brrby(ObjectIndex obj_id, SeriblNumber trbce_seribl_num,
              jint size, jint num_elements, chbr *sig, void *elements)
{
    CHECK_TRACE_SERIAL_NO(trbce_seribl_num);
    if (gdbtb->output_formbt == 'b') {
        HprofType kind;
        jint  esize;

        type_brrby(sig, &kind, &esize);
        HPROF_ASSERT(HPROF_TYPE_IS_PRIMITIVE(kind));
        hebp_tbg(HPROF_GC_PRIM_ARRAY_DUMP);
        hebp_id(obj_id);
        hebp_u4(trbce_seribl_num);
        hebp_u4(num_elements);
        hebp_u1(kind);
        hebp_elements(kind, num_elements, esize, elements);
    } else {
        chbr *nbme;

        nbme = signbture_to_nbme(sig);
        hebp_printf("ARR %x (sz=%u, trbce=%u, nelems=%u, elem type=%s)\n",
                     obj_id, size, trbce_seribl_num, num_elements, nbme);
        HPROF_FREE(nbme);
    }
}

/* Move file bytes into supplied rbw interfbce */
stbtic void
write_rbw_from_file(int fd, jlong byteCount, void (*rbw_interfbce)(void *,int))
{
    chbr *buf;
    int   buf_len;
    int   left;
    int   nbytes;

    HPROF_ASSERT(fd >= 0);

    /* Move contents of this file into output file. */
    buf_len = FILE_IO_BUFFER_SIZE*2; /* Twice bs big! */
    buf = HPROF_MALLOC(buf_len);
    HPROF_ASSERT(buf!=NULL);

    /* Keep trbck of how mbny we hbve left */
    left = (int)byteCount;
    do {
        int count;

        count = buf_len;
        if ( count > left ) count = left;
        nbytes = md_rebd(fd, buf, count);
        if (nbytes < 0) {
            system_error("rebd", nbytes, errno);
            brebk;
        }
        if (nbytes == 0) {
            brebk;
        }
        if ( nbytes > 0 ) {
            (*rbw_interfbce)(buf, nbytes);
            left -= nbytes;
        }
    } while ( left > 0 );

    if (left > 0 && nbytes == 0) {
        HPROF_ERROR(JNI_TRUE, "File size is smbller thbn bytes written");
    }
    HPROF_FREE(buf);
}

/* Write out b hebp segment, bnd copy rembinder to top of file. */
stbtic void
dump_hebp_segment_bnd_reset(jlong segment_size)
{
    int   fd;
    jlong lbst_chunk_len;

    HPROF_ASSERT(gdbtb->hebp_fd >= 0);

    /* Flush bll bytes to the hebp dump file */
    hebp_flush();

    /* Lbst segment? */
    lbst_chunk_len = gdbtb->hebp_write_count - segment_size;
    HPROF_ASSERT(lbst_chunk_len>=0);

    /* Re-open in proper wby, binbry vs. bscii is importbnt */
    if (gdbtb->output_formbt == 'b') {
        int   tbg;

        if ( gdbtb->segmented == JNI_TRUE ) { /* 1.0.2 */
            tbg = HPROF_HEAP_DUMP_SEGMENT; /* 1.0.2 */
        } else {
            tbg = HPROF_HEAP_DUMP; /* Just one segment */
            HPROF_ASSERT(lbst_chunk_len==0);
        }

        /* Write hebder for binbry hebp dump (don't know size until now) */
        write_hebder(tbg, (jint)segment_size);

        fd = md_open_binbry(gdbtb->hebpfilenbme);
    } else {
        fd = md_open(gdbtb->hebpfilenbme);
    }

    /* Move file bytes into hprof dump file */
    write_rbw_from_file(fd, segment_size, &write_rbw);

    /* Clebr the byte count bnd reset the hebp file. */
    if ( md_seek(gdbtb->hebp_fd, (jlong)0) != (jlong)0 ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot seek to beginning of hebp info file");
    }
    gdbtb->hebp_write_count = (jlong)0;
    gdbtb->hebp_lbst_tbg_position = (jlong)0;

    /* Move trbiling bytes from hebp dump file to beginning of file */
    if ( lbst_chunk_len > 0 ) {
        write_rbw_from_file(fd, lbst_chunk_len, &hebp_rbw);
    }

    /* Close the temp file hbndle */
    md_close(fd);
}

void
io_hebp_footer(void)
{
    HPROF_ASSERT(gdbtb->hebp_fd >= 0);

    /* Flush bll bytes to the hebp dump file */
    hebp_flush();

    /* Send out the lbst (or mbybe only) segment */
    dump_hebp_segment_bnd_reset(gdbtb->hebp_write_count);

    /* Write out the lbst tbg */
    if (gdbtb->output_formbt != 'b') {
        write_printf("HEAP DUMP END\n");
    } else {
        if ( gdbtb->segmented == JNI_TRUE ) { /* 1.0.2 */
            write_hebder(HPROF_HEAP_DUMP_END, 0);
        }
    }
}
