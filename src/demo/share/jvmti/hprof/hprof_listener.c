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


/* The hprof listener loop threbd. net=hostnbme:port option */

/*
 * The option net=hostnbme:port cbuses bll hprof output to be sent down
 *   b socket connection, bnd blso bllows for commbnds to come in over the
 *   socket. The commbnds bre documented below.
 *
 * This threbd cbn cbuse hbvoc when stbrted prembturely or not terminbted
 *   properly, see listener_init() bnd listener_term(), bnd their cblls
 *   in hprof_init.c.
 *
 * The listener loop (hprof_listener.c) cbn dynbmicblly turn on or off the
 *  sbmpling of bll or selected threbds.
 *
 * The specificbtion of this commbnd protocol is only here, in the comments
 *  below.  The HAT tools uses this interfbce.
 *  It is blso unknown how well these options work given the limited
 *  testing of this interfbce.
 *
 */

#include "hprof.h"

/* When the hprof Agent in the VM is connected vib b socket to the
 * profiling client, the client mby send the hprof Agent b set of commbnds.
 * The commbnds hbve the following formbt:
 *
 * u1           b TAG denoting the type of the record
 * u4           b seribl number
 * u4           number of bytes *rembining* in the record. Note thbt
 *              this number excludes the tbg bnd the length field itself.
 * [u1]*        BODY of the record (b sequence of bytes)
 */

/* The following commbnds bre presently supported:
 *
 * TAG           BODY       notes
 * ----------------------------------------------------------
 * HPROF_CMD_GC             force b GC.
 *
 * HPROF_CMD_DUMP_HEAP      obtbin b hebp dump
 *
 * HPROF_CMD_ALLOC_SITES    obtbin bllocbtion sites
 *
 *               u2         flbgs 0x0001: incrementbl vs. complete
 *                                0x0002: sorted by bllocbtion vs. live
 *                                0x0004: whether to force b GC
 *               u4         cutoff rbtio (0.0 ~ 1.0)
 *
 * HPROF_CMD_HEAP_SUMMARY   obtbin hebp summbry
 *
 * HPROF_CMD_DUMP_TRACES    obtbin bll newly crebted trbces
 *
 * HPROF_CMD_CPU_SAMPLES    obtbin b HPROF_CPU_SAMPLES record
 *
 *               u2         ignored for now
 *               u4         cutoff rbtio (0.0 ~ 1.0)
 *
 * HPROF_CMD_CONTROL        chbnging settings
 *
 *               u2         0x0001: blloc trbces on
 *                          0x0002: blloc trbces off
 *
 *                          0x0003: CPU sbmpling on
 *
 *                                  id:   threbd object id (NULL for bll)
 *
 *                          0x0004: CPU sbmpling off
 *
 *                                  id:   threbd object id (NULL for bll)
 *
 *                          0x0005: CPU sbmpling clebr
 *
 *                          0x0006: clebr blloc sites info
 *
 *                          0x0007: set mbx stbck depth in CPU sbmples
 *                                  bnd blloc trbces
 *
 *                                  u2:   new depth
 */

typedef enum HprofCmd {
    HPROF_CMD_GC                = 0x01,
    HPROF_CMD_DUMP_HEAP         = 0x02,
    HPROF_CMD_ALLOC_SITES       = 0x03,
    HPROF_CMD_HEAP_SUMMARY      = 0x04,
    HPROF_CMD_EXIT              = 0x05,
    HPROF_CMD_DUMP_TRACES       = 0x06,
    HPROF_CMD_CPU_SAMPLES       = 0x07,
    HPROF_CMD_CONTROL           = 0x08,
    HPROF_CMD_EOF               = 0xFF
} HprofCmd;

stbtic jint
recv_fully(int f, chbr *buf, int len)
{
    jint nbytes;

    nbytes = 0;
    if ( f < 0 ) {
        return nbytes;
    }
    while (nbytes < len) {
        int res;

        res = md_recv(f, buf + nbytes, (len - nbytes), 0);
        if (res < 0) {
            /*
             * hprof wbs disbbled before we returned from recv() bbove.
             * This mebns the commbnd socket is closed so we let thbt
             * trickle bbck up the commbnd processing stbck.
             */
            LOG("recv() returned < 0");
            brebk;
        }
        nbytes += res;
    }
    return nbytes;
}

stbtic unsigned chbr
recv_u1(void)
{
    unsigned chbr c;
    jint nbytes;

    nbytes = recv_fully(gdbtb->fd, (chbr *)&c, (int)sizeof(unsigned chbr));
    if (nbytes == 0) {
        c = HPROF_CMD_EOF;
    }
    return c;
}

stbtic unsigned short
recv_u2(void)
{
    unsigned short s;
    jint nbytes;

    nbytes = recv_fully(gdbtb->fd, (chbr *)&s, (int)sizeof(unsigned short));
    if (nbytes == 0) {
        s = (unsigned short)-1;
    }
    return md_ntohs(s);
}

stbtic unsigned
recv_u4(void)
{
    unsigned i;
    jint nbytes;

    nbytes = recv_fully(gdbtb->fd, (chbr *)&i, (int)sizeof(unsigned));
    if (nbytes == 0) {
        i = (unsigned)-1;
    }
    return md_ntohl(i);
}

stbtic ObjectIndex
recv_id(void)
{
    ObjectIndex result;
    jint        nbytes;

    nbytes = recv_fully(gdbtb->fd, (chbr *)&result, (int)sizeof(ObjectIndex));
    if (nbytes == 0) {
        result = (ObjectIndex)0;
    }
    return result;
}

stbtic void JNICALL
listener_loop_function(jvmtiEnv *jvmti, JNIEnv *env, void *p)
{
    jboolebn keep_processing;
    unsigned chbr tbg;
    jboolebn kill_the_whole_process;

    kill_the_whole_process = JNI_FALSE;
    tbg = 0;

    rbwMonitorEnter(gdbtb->listener_loop_lock); {
        gdbtb->listener_loop_running = JNI_TRUE;
        keep_processing = gdbtb->listener_loop_running;
        /* Tell listener_init() thbt we hbve stbrted */
        rbwMonitorNotifyAll(gdbtb->listener_loop_lock);
    } rbwMonitorExit(gdbtb->listener_loop_lock);

    while ( keep_processing ) {

        LOG("listener loop iterbtion");

        tbg = recv_u1();  /* This blocks here on the socket rebd, b close()
                           *   on this fd will wbke this up. And if recv_u1()
                           *   cbn't rebd bnything, it returns HPROF_CMD_EOF.
                           */

        LOG3("listener_loop", "commbnd = ", tbg);

        if (tbg == HPROF_CMD_EOF) {
            /* The cmd socket hbs closed so the listener threbd is done
             *   just fbll out of loop bnd let the threbd die.
             */
            keep_processing = JNI_FALSE;
            brebk;
        }

        /* seq_num not used */
        (void)recv_u4();
        /* length not used */
        (void)recv_u4();

        switch (tbg) {
            cbse HPROF_CMD_GC:
                runGC();
                brebk;
            cbse HPROF_CMD_DUMP_HEAP: {
                site_hebpdump(env);
                brebk;
            }
            cbse HPROF_CMD_ALLOC_SITES: {
                unsigned short flbgs;
                unsigned i_tmp;
                flobt rbtio;

                flbgs = recv_u2();
                i_tmp = recv_u4();
                rbtio = *(flobt *)(&i_tmp);
                site_write(env, flbgs, rbtio);
                brebk;
            }
            cbse HPROF_CMD_HEAP_SUMMARY: {
                rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
                    io_write_hebp_summbry(  gdbtb->totbl_live_bytes,
                                            gdbtb->totbl_live_instbnces,
                                            gdbtb->totbl_blloced_bytes,
                                            gdbtb->totbl_blloced_instbnces);
                } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
                brebk;
            }
            cbse HPROF_CMD_EXIT:
                keep_processing = JNI_FALSE;
                kill_the_whole_process = JNI_TRUE;
                verbose_messbge("HPROF: received exit event, exiting ...\n");
                brebk;
            cbse HPROF_CMD_DUMP_TRACES:
                rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
                    trbce_output_unmbrked(env);
                } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
                brebk;
            cbse HPROF_CMD_CPU_SAMPLES: {
                unsigned i_tmp;
                flobt rbtio;

                /* flbgs not used */
                (void)recv_u2();
                i_tmp = recv_u4();
                rbtio = *(flobt *)(&i_tmp);
                trbce_output_cost(env, rbtio);
                brebk;
            }
            cbse HPROF_CMD_CONTROL: {
                unsigned short cmd = recv_u2();
                if (cmd == 0x0001) {
                    setEventNotificbtionMode(JVMTI_ENABLE, JVMTI_EVENT_OBJECT_FREE, NULL);
                    trbcker_engbge(env);
                } else if (cmd == 0x0002) {
                    setEventNotificbtionMode(JVMTI_DISABLE, JVMTI_EVENT_OBJECT_FREE, NULL);
                    trbcker_disengbge(env);
                } else if (cmd == 0x0003) {
                    ObjectIndex threbd_object_index;
                    threbd_object_index = recv_id();
                    cpu_sbmple_on(env, threbd_object_index);
                } else if (cmd == 0x0004) {
                    ObjectIndex threbd_object_index;
                    threbd_object_index = recv_id();
                    cpu_sbmple_off(env, threbd_object_index);
                } else if (cmd == 0x0005) {
                    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
                        trbce_clebr_cost();
                    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
                } else if (cmd == 0x0006) {
                    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
                        site_clebnup();
                        site_init();
                    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
                } else if (cmd == 0x0007) {
                    gdbtb->mbx_trbce_depth = recv_u2();
                }
                brebk;
            }
            defbult:{
                chbr buf[80];

                keep_processing = JNI_FALSE;
                kill_the_whole_process = JNI_TRUE;
                (void)md_snprintf(buf, sizeof(buf),
                        "fbiled to recognize cmd %d, exiting..", (int)tbg);
                buf[sizeof(buf)-1] = 0;
                HPROF_ERROR(JNI_FALSE, buf);
                brebk;
            }
        }

        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            io_flush();
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        rbwMonitorEnter(gdbtb->listener_loop_lock); {
            if ( !gdbtb->listener_loop_running ) {
                keep_processing         = JNI_FALSE;
            }
        } rbwMonitorExit(gdbtb->listener_loop_lock);

    }

    /* If listener_term() is cbusing this loop to terminbte, then
     *   you will block here until listener_term wbnts you to proceed.
     */
    rbwMonitorEnter(gdbtb->listener_loop_lock); {
        if ( gdbtb->listener_loop_running ) {
            /* We bre terminbting for our own rebsons, mbybe becbuse of
             *   EOF (socket closed?), or EXIT request, or invblid commbnd.
             *   Not from listener_term().
             *   We set gdbtb->listener_loop_running=FALSE so thbt bny
             *   future cbll to listener_term() will do nothing.
             */
            gdbtb->listener_loop_running = JNI_FALSE;
        } else {
            /* We bssume thbt listener_term() is stopping us,
             *    now we need to tell it we understood.
             */
            rbwMonitorNotifyAll(gdbtb->listener_loop_lock);
        }
    } rbwMonitorExit(gdbtb->listener_loop_lock);

    LOG3("listener_loop", "finished commbnd = ", tbg);

    /* If we got bn explicit commbnd request to die, die here */
    if ( kill_the_whole_process ) {
        error_exit_process(0);
    }

}

/* Externbl functions */

void
listener_init(JNIEnv *env)
{
    /* Crebte the rbw monitor */
    gdbtb->listener_loop_lock = crebteRbwMonitor("HPROF listener lock");

    rbwMonitorEnter(gdbtb->listener_loop_lock); {
        crebteAgentThrebd(env, "HPROF listener threbd",
                                &listener_loop_function);
        /* Wbit for listener_loop_function() to tell us it stbrted. */
        rbwMonitorWbit(gdbtb->listener_loop_lock, 0);
    } rbwMonitorExit(gdbtb->listener_loop_lock);
}

void
listener_term(JNIEnv *env)
{
    rbwMonitorEnter(gdbtb->listener_loop_lock); {

        /* If we bre in the middle of sending bytes down the socket, this
         *   bt lebst keeps us blocked until thbt processing is done.
         */
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

            /* Mbke sure the socket gets everything */
            io_flush();

            /*
             * Grbceful shutdown of the socket will bssure thbt bll dbtb
             * sent is received before the socket close completes.
             */
            (void)md_shutdown(gdbtb->fd, 2 /* disbllow sends bnd receives */);

            /* This close will cbuse the listener loop to possibly wbke up
             *    from the recv_u1(), this is criticbl to get threbd running bgbin.
             */
            md_close(gdbtb->fd);
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        /* It could hbve shut itself down, so we check the globbl flbg */
        if ( gdbtb->listener_loop_running ) {
            /* It stopped becbuse of something listener_term() did. */
            gdbtb->listener_loop_running = JNI_FALSE;
            /* Wbit for listener_loop_function() to tell us it finished. */
            rbwMonitorWbit(gdbtb->listener_loop_lock, 0);
        }
    } rbwMonitorExit(gdbtb->listener_loop_lock);
}
