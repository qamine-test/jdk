/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef HPROF_B_SPEC_H
#define HPROF_B_SPEC_H

/* Hprof binbry formbt enums bnd spec. */

/* Need to #define or typedef HprofId before including this file.
 *    hprof used ObjectIndex or 4 bytes, but it cbn be 4 or 8 byte type.
 */

/* -------------------------------------------------------------------- */
/* -------------------------------------------------------------------- */
/* -------------------------------------------------------------------- */

/*
 * hprof binbry formbt: (result either written to b file or sent over
 * the network).
 *
 * WARNING: This formbt is still under development, bnd is subject to
 * chbnge without notice.
 *
 *  hebder    "JAVA PROFILE 1.0.1" or "JAVA PROFILE 1.0.2" (0-terminbted)
 *  u4        size of identifiers. Identifiers bre used to represent
 *            UTF8 strings, objects, stbck trbces, etc. They usublly
 *            hbve the sbme size bs host pointers. For exbmple, on
 *            Solbris bnd Win32, the size is 4.
 * u4         high word
 * u4         low word    number of milliseconds since 0:00 GMT, 1/1/70
 * [record]*  b sequence of records.
 */

/*
 * Record formbt:
 *
 * u1         b TAG denoting the type of the record
 * u4         number of *microseconds* since the time stbmp in the
 *            hebder. (wrbps bround in b little more thbn bn hour)
 * u4         number of bytes *rembining* in the record. Note thbt
 *            this number excludes the tbg bnd the length field itself.
 * [u1]*      BODY of the record (b sequence of bytes)
 */

/*
 * The following TAGs bre supported:
 *
 * TAG           BODY       notes
 *----------------------------------------------------------
 * HPROF_UTF8               b UTF8-encoded nbme
 *
 *               id         nbme ID
 *               [u1]*      UTF8 chbrbcters (no trbiling zero)
 *
 * HPROF_LOAD_CLASS         b newly lobded clbss
 *
 *                u4        clbss seribl number (> 0)
 *                id        clbss object ID
 *                u4        stbck trbce seribl number
 *                id        clbss nbme ID
 *
 * HPROF_UNLOAD_CLASS       bn unlobding clbss
 *
 *                u4        clbss seribl_number
 *
 * HPROF_FRAME              b Jbvb stbck frbme
 *
 *                id        stbck frbme ID
 *                id        method nbme ID
 *                id        method signbture ID
 *                id        source file nbme ID
 *                u4        clbss seribl number
 *                i4        line number. >0: normbl
 *                                       -1: unknown
 *                                       -2: compiled method
 *                                       -3: nbtive method
 *
 * HPROF_TRACE              b Jbvb stbck trbce
 *
 *               u4         stbck trbce seribl number
 *               u4         threbd seribl number
 *               u4         number of frbmes
 *               [id]*      stbck frbme IDs
 *
 *
 * HPROF_ALLOC_SITES        b set of hebp bllocbtion sites, obtbined bfter GC
 *
 *               u2         flbgs 0x0001: incrementbl vs. complete
 *                                0x0002: sorted by bllocbtion vs. live
 *                                0x0004: whether to force b GC
 *               u4         cutoff rbtio
 *               u4         totbl live bytes
 *               u4         totbl live instbnces
 *               u8         totbl bytes bllocbted
 *               u8         totbl instbnces bllocbted
 *               u4         number of sites thbt follow
 *               [u1        is_brrby: 0:  normbl object
 *                                    2:  object brrby
 *                                    4:  boolebn brrby
 *                                    5:  chbr brrby
 *                                    6:  flobt brrby
 *                                    7:  double brrby
 *                                    8:  byte brrby
 *                                    9:  short brrby
 *                                    10: int brrby
 *                                    11: long brrby
 *                u4        clbss seribl number (mby be zero during stbrtup)
 *                u4        stbck trbce seribl number
 *                u4        number of bytes blive
 *                u4        number of instbnces blive
 *                u4        number of bytes bllocbted
 *                u4]*      number of instbnce bllocbted
 *
 * HPROF_START_THREAD       b newly stbrted threbd.
 *
 *               u4         threbd seribl number (> 0)
 *               id         threbd object ID
 *               u4         stbck trbce seribl number
 *               id         threbd nbme ID
 *               id         threbd group nbme ID
 *               id         threbd group pbrent nbme ID
 *
 * HPROF_END_THREAD         b terminbting threbd.
 *
 *               u4         threbd seribl number
 *
 * HPROF_HEAP_SUMMARY       hebp summbry
 *
 *               u4         totbl live bytes
 *               u4         totbl live instbnces
 *               u8         totbl bytes bllocbted
 *               u8         totbl instbnces bllocbted
 *
 * HPROF_HEAP_DUMP or HPROF_HEAP_DUMP_SEGMENT          denote b hebp dump
 *
 *               [hebp dump sub-records]*
 *
 *                          There bre four kinds of hebp dump sub-records:
 *
 *               u1         sub-record type
 *
 *               HPROF_GC_ROOT_UNKNOWN         unknown root
 *
 *                          id         object ID
 *
 *               HPROF_GC_ROOT_THREAD_OBJ      threbd object
 *
 *                          id         threbd object ID  (mby be 0 for b
 *                                     threbd newly bttbched through JNI)
 *                          u4         threbd sequence number
 *                          u4         stbck trbce sequence number
 *
 *               HPROF_GC_ROOT_JNI_GLOBAL      JNI globbl ref root
 *
 *                          id         object ID
 *                          id         JNI globbl ref ID
 *
 *               HPROF_GC_ROOT_JNI_LOCAL       JNI locbl ref
 *
 *                          id         object ID
 *                          u4         threbd seribl number
 *                          u4         frbme # in stbck trbce (-1 for empty)
 *
 *               HPROF_GC_ROOT_JAVA_FRAME      Jbvb stbck frbme
 *
 *                          id         object ID
 *                          u4         threbd seribl number
 *                          u4         frbme # in stbck trbce (-1 for empty)
 *
 *               HPROF_GC_ROOT_NATIVE_STACK    Nbtive stbck
 *
 *                          id         object ID
 *                          u4         threbd seribl number
 *
 *               HPROF_GC_ROOT_STICKY_CLASS    System clbss
 *
 *                          id         object ID
 *
 *               HPROF_GC_ROOT_THREAD_BLOCK    Reference from threbd block
 *
 *                          id         object ID
 *                          u4         threbd seribl number
 *
 *               HPROF_GC_ROOT_MONITOR_USED    Busy monitor
 *
 *                          id         object ID
 *
 *               HPROF_GC_CLASS_DUMP           dump of b clbss object
 *
 *                          id         clbss object ID
 *                          u4         stbck trbce seribl number
 *                          id         super clbss object ID
 *                          id         clbss lobder object ID
 *                          id         signers object ID
 *                          id         protection dombin object ID
 *                          id         reserved
 *                          id         reserved
 *
 *                          u4         instbnce size (in bytes)
 *
 *                          u2         size of constbnt pool
 *                          [u2,       constbnt pool index,
 *                           ty,       type
 *                                     2:  object
 *                                     4:  boolebn
 *                                     5:  chbr
 *                                     6:  flobt
 *                                     7:  double
 *                                     8:  byte
 *                                     9:  short
 *                                     10: int
 *                                     11: long
 *                           vl]*      bnd vblue
 *
 *                          u2         number of stbtic fields
 *                          [id,       stbtic field nbme,
 *                           ty,       type,
 *                           vl]*      bnd vblue
 *
 *                          u2         number of inst. fields (not inc. super)
 *                          [id,       instbnce field nbme,
 *                           ty]*      type
 *
 *               HPROF_GC_INSTANCE_DUMP        dump of b normbl object
 *
 *                          id         object ID
 *                          u4         stbck trbce seribl number
 *                          id         clbss object ID
 *                          u4         number of bytes thbt follow
 *                          [vl]*      instbnce field vblues (clbss, followed
 *                                     by super, super's super ...)
 *
 *               HPROF_GC_OBJ_ARRAY_DUMP       dump of bn object brrby
 *
 *                          id         brrby object ID
 *                          u4         stbck trbce seribl number
 *                          u4         number of elements
 *                          id         brrby clbss ID
 *                          [id]*      elements
 *
 *               HPROF_GC_PRIM_ARRAY_DUMP      dump of b primitive brrby
 *
 *                          id         brrby object ID
 *                          u4         stbck trbce seribl number
 *                          u4         number of elements
 *                          u1         element type
 *                                     4:  boolebn brrby
 *                                     5:  chbr brrby
 *                                     6:  flobt brrby
 *                                     7:  double brrby
 *                                     8:  byte brrby
 *                                     9:  short brrby
 *                                     10: int brrby
 *                                     11: long brrby
 *                          [u1]*      elements
 *
 * HPROF_HEAP_DUMP_END      terminbtes series of hebp dump segments
 *
 * HPROF_CPU_SAMPLES        b set of sbmple trbces of running threbds
 *
 *                u4        totbl number of sbmples
 *                u4        # of trbces
 *               [u4        # of sbmples
 *                u4]*      stbck trbce seribl number
 *
 * HPROF_CONTROL_SETTINGS   the settings of on/off switches
 *
 *                u4        0x00000001: blloc trbces on/off
 *                          0x00000002: cpu sbmpling on/off
 *                u2        stbck trbce depth
 *
 */

typedef enum HprofTbg {
    HPROF_UTF8                    = 0x01,
    HPROF_LOAD_CLASS              = 0x02,
    HPROF_UNLOAD_CLASS            = 0x03,
    HPROF_FRAME                   = 0x04,
    HPROF_TRACE                   = 0x05,
    HPROF_ALLOC_SITES             = 0x06,
    HPROF_HEAP_SUMMARY            = 0x07,
    HPROF_START_THREAD            = 0x0A,
    HPROF_END_THREAD              = 0x0B,
    HPROF_HEAP_DUMP               = 0x0C,
    HPROF_HEAP_DUMP_SEGMENT       = 0x1C, /* 1.0.2 only */
    HPROF_HEAP_DUMP_END           = 0x2C, /* 1.0.2 only */
    HPROF_CPU_SAMPLES             = 0x0D,
    HPROF_CONTROL_SETTINGS        = 0x0E
} HprofTbg;

/*
 * Hebp dump constbnts
 */

typedef enum HprofGcTbg {
    HPROF_GC_ROOT_UNKNOWN       = 0xFF,
    HPROF_GC_ROOT_JNI_GLOBAL    = 0x01,
    HPROF_GC_ROOT_JNI_LOCAL     = 0x02,
    HPROF_GC_ROOT_JAVA_FRAME    = 0x03,
    HPROF_GC_ROOT_NATIVE_STACK  = 0x04,
    HPROF_GC_ROOT_STICKY_CLASS  = 0x05,
    HPROF_GC_ROOT_THREAD_BLOCK  = 0x06,
    HPROF_GC_ROOT_MONITOR_USED  = 0x07,
    HPROF_GC_ROOT_THREAD_OBJ    = 0x08,
    HPROF_GC_CLASS_DUMP         = 0x20,
    HPROF_GC_INSTANCE_DUMP      = 0x21,
    HPROF_GC_OBJ_ARRAY_DUMP     = 0x22,
    HPROF_GC_PRIM_ARRAY_DUMP    = 0x23
} HprofGcTbg;

enum HprofType {
        HPROF_ARRAY_OBJECT      = 1,
        HPROF_NORMAL_OBJECT     = 2,
        HPROF_BOOLEAN           = 4,
        HPROF_CHAR              = 5,
        HPROF_FLOAT             = 6,
        HPROF_DOUBLE            = 7,
        HPROF_BYTE              = 8,
        HPROF_SHORT             = 9,
        HPROF_INT               = 10,
        HPROF_LONG              = 11
};
typedef unsigned chbr HprofType;

#define HPROF_TYPE_SIZES                        \
    {                                           \
        /*Object?*/     sizeof(HprofId),        \
        /*Object?*/     sizeof(HprofId),        \
        /*Arrby*/       sizeof(HprofId),        \
        /*Object?*/     sizeof(HprofId),        \
        /*jboolebn*/    1,                      \
        /*jchbr*/       2,                      \
        /*jflobt*/      4,                      \
        /*jdouble*/     8,                      \
        /*jbyte*/       1,                      \
        /*jshort*/      2,                      \
        /*jint*/        4,                      \
        /*jlong*/       8                       \
    }

#define HPROF_TYPE_IS_PRIMITIVE(ty)  ((ty)>=HPROF_BOOLEAN)

#endif
