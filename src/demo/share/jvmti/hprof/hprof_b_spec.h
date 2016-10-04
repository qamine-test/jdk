/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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

#ifndff HPROF_B_SPEC_H
#dffinf HPROF_B_SPEC_H

/* Hprof binbry formbt fnums bnd spfd. */

/* Nffd to #dffinf or typfdff HprofId bfforf indluding tiis filf.
 *    iprof usfd ObjfdtIndfx or 4 bytfs, but it dbn bf 4 or 8 bytf typf.
 */

/* -------------------------------------------------------------------- */
/* -------------------------------------------------------------------- */
/* -------------------------------------------------------------------- */

/*
 * iprof binbry formbt: (rfsult fitifr writtfn to b filf or sfnt ovfr
 * tif nftwork).
 *
 * WARNING: Tiis formbt is still undfr dfvflopmfnt, bnd is subjfdt to
 * dibngf witiout notidf.
 *
 *  ifbdfr    "JAVA PROFILE 1.0.1" or "JAVA PROFILE 1.0.2" (0-tfrminbtfd)
 *  u4        sizf of idfntififrs. Idfntififrs brf usfd to rfprfsfnt
 *            UTF8 strings, objfdts, stbdk trbdfs, ftd. Tify usublly
 *            ibvf tif sbmf sizf bs iost pointfrs. For fxbmplf, on
 *            Solbris bnd Win32, tif sizf is 4.
 * u4         iigi word
 * u4         low word    numbfr of millisfdonds sindf 0:00 GMT, 1/1/70
 * [rfdord]*  b sfqufndf of rfdords.
 */

/*
 * Rfdord formbt:
 *
 * u1         b TAG dfnoting tif typf of tif rfdord
 * u4         numbfr of *midrosfdonds* sindf tif timf stbmp in tif
 *            ifbdfr. (wrbps bround in b littlf morf tibn bn iour)
 * u4         numbfr of bytfs *rfmbining* in tif rfdord. Notf tibt
 *            tiis numbfr fxdludfs tif tbg bnd tif lfngti fifld itsflf.
 * [u1]*      BODY of tif rfdord (b sfqufndf of bytfs)
 */

/*
 * Tif following TAGs brf supportfd:
 *
 * TAG           BODY       notfs
 *----------------------------------------------------------
 * HPROF_UTF8               b UTF8-fndodfd nbmf
 *
 *               id         nbmf ID
 *               [u1]*      UTF8 dibrbdtfrs (no trbiling zfro)
 *
 * HPROF_LOAD_CLASS         b nfwly lobdfd dlbss
 *
 *                u4        dlbss sfribl numbfr (> 0)
 *                id        dlbss objfdt ID
 *                u4        stbdk trbdf sfribl numbfr
 *                id        dlbss nbmf ID
 *
 * HPROF_UNLOAD_CLASS       bn unlobding dlbss
 *
 *                u4        dlbss sfribl_numbfr
 *
 * HPROF_FRAME              b Jbvb stbdk frbmf
 *
 *                id        stbdk frbmf ID
 *                id        mftiod nbmf ID
 *                id        mftiod signbturf ID
 *                id        sourdf filf nbmf ID
 *                u4        dlbss sfribl numbfr
 *                i4        linf numbfr. >0: normbl
 *                                       -1: unknown
 *                                       -2: dompilfd mftiod
 *                                       -3: nbtivf mftiod
 *
 * HPROF_TRACE              b Jbvb stbdk trbdf
 *
 *               u4         stbdk trbdf sfribl numbfr
 *               u4         tirfbd sfribl numbfr
 *               u4         numbfr of frbmfs
 *               [id]*      stbdk frbmf IDs
 *
 *
 * HPROF_ALLOC_SITES        b sft of ifbp bllodbtion sitfs, obtbinfd bftfr GC
 *
 *               u2         flbgs 0x0001: indrfmfntbl vs. domplftf
 *                                0x0002: sortfd by bllodbtion vs. livf
 *                                0x0004: wiftifr to fordf b GC
 *               u4         dutoff rbtio
 *               u4         totbl livf bytfs
 *               u4         totbl livf instbndfs
 *               u8         totbl bytfs bllodbtfd
 *               u8         totbl instbndfs bllodbtfd
 *               u4         numbfr of sitfs tibt follow
 *               [u1        is_brrby: 0:  normbl objfdt
 *                                    2:  objfdt brrby
 *                                    4:  boolfbn brrby
 *                                    5:  dibr brrby
 *                                    6:  flobt brrby
 *                                    7:  doublf brrby
 *                                    8:  bytf brrby
 *                                    9:  siort brrby
 *                                    10: int brrby
 *                                    11: long brrby
 *                u4        dlbss sfribl numbfr (mby bf zfro during stbrtup)
 *                u4        stbdk trbdf sfribl numbfr
 *                u4        numbfr of bytfs blivf
 *                u4        numbfr of instbndfs blivf
 *                u4        numbfr of bytfs bllodbtfd
 *                u4]*      numbfr of instbndf bllodbtfd
 *
 * HPROF_START_THREAD       b nfwly stbrtfd tirfbd.
 *
 *               u4         tirfbd sfribl numbfr (> 0)
 *               id         tirfbd objfdt ID
 *               u4         stbdk trbdf sfribl numbfr
 *               id         tirfbd nbmf ID
 *               id         tirfbd group nbmf ID
 *               id         tirfbd group pbrfnt nbmf ID
 *
 * HPROF_END_THREAD         b tfrminbting tirfbd.
 *
 *               u4         tirfbd sfribl numbfr
 *
 * HPROF_HEAP_SUMMARY       ifbp summbry
 *
 *               u4         totbl livf bytfs
 *               u4         totbl livf instbndfs
 *               u8         totbl bytfs bllodbtfd
 *               u8         totbl instbndfs bllodbtfd
 *
 * HPROF_HEAP_DUMP or HPROF_HEAP_DUMP_SEGMENT          dfnotf b ifbp dump
 *
 *               [ifbp dump sub-rfdords]*
 *
 *                          Tifrf brf four kinds of ifbp dump sub-rfdords:
 *
 *               u1         sub-rfdord typf
 *
 *               HPROF_GC_ROOT_UNKNOWN         unknown root
 *
 *                          id         objfdt ID
 *
 *               HPROF_GC_ROOT_THREAD_OBJ      tirfbd objfdt
 *
 *                          id         tirfbd objfdt ID  (mby bf 0 for b
 *                                     tirfbd nfwly bttbdifd tirougi JNI)
 *                          u4         tirfbd sfqufndf numbfr
 *                          u4         stbdk trbdf sfqufndf numbfr
 *
 *               HPROF_GC_ROOT_JNI_GLOBAL      JNI globbl rff root
 *
 *                          id         objfdt ID
 *                          id         JNI globbl rff ID
 *
 *               HPROF_GC_ROOT_JNI_LOCAL       JNI lodbl rff
 *
 *                          id         objfdt ID
 *                          u4         tirfbd sfribl numbfr
 *                          u4         frbmf # in stbdk trbdf (-1 for fmpty)
 *
 *               HPROF_GC_ROOT_JAVA_FRAME      Jbvb stbdk frbmf
 *
 *                          id         objfdt ID
 *                          u4         tirfbd sfribl numbfr
 *                          u4         frbmf # in stbdk trbdf (-1 for fmpty)
 *
 *               HPROF_GC_ROOT_NATIVE_STACK    Nbtivf stbdk
 *
 *                          id         objfdt ID
 *                          u4         tirfbd sfribl numbfr
 *
 *               HPROF_GC_ROOT_STICKY_CLASS    Systfm dlbss
 *
 *                          id         objfdt ID
 *
 *               HPROF_GC_ROOT_THREAD_BLOCK    Rfffrfndf from tirfbd blodk
 *
 *                          id         objfdt ID
 *                          u4         tirfbd sfribl numbfr
 *
 *               HPROF_GC_ROOT_MONITOR_USED    Busy monitor
 *
 *                          id         objfdt ID
 *
 *               HPROF_GC_CLASS_DUMP           dump of b dlbss objfdt
 *
 *                          id         dlbss objfdt ID
 *                          u4         stbdk trbdf sfribl numbfr
 *                          id         supfr dlbss objfdt ID
 *                          id         dlbss lobdfr objfdt ID
 *                          id         signfrs objfdt ID
 *                          id         protfdtion dombin objfdt ID
 *                          id         rfsfrvfd
 *                          id         rfsfrvfd
 *
 *                          u4         instbndf sizf (in bytfs)
 *
 *                          u2         sizf of donstbnt pool
 *                          [u2,       donstbnt pool indfx,
 *                           ty,       typf
 *                                     2:  objfdt
 *                                     4:  boolfbn
 *                                     5:  dibr
 *                                     6:  flobt
 *                                     7:  doublf
 *                                     8:  bytf
 *                                     9:  siort
 *                                     10: int
 *                                     11: long
 *                           vl]*      bnd vbluf
 *
 *                          u2         numbfr of stbtid fiflds
 *                          [id,       stbtid fifld nbmf,
 *                           ty,       typf,
 *                           vl]*      bnd vbluf
 *
 *                          u2         numbfr of inst. fiflds (not ind. supfr)
 *                          [id,       instbndf fifld nbmf,
 *                           ty]*      typf
 *
 *               HPROF_GC_INSTANCE_DUMP        dump of b normbl objfdt
 *
 *                          id         objfdt ID
 *                          u4         stbdk trbdf sfribl numbfr
 *                          id         dlbss objfdt ID
 *                          u4         numbfr of bytfs tibt follow
 *                          [vl]*      instbndf fifld vblufs (dlbss, followfd
 *                                     by supfr, supfr's supfr ...)
 *
 *               HPROF_GC_OBJ_ARRAY_DUMP       dump of bn objfdt brrby
 *
 *                          id         brrby objfdt ID
 *                          u4         stbdk trbdf sfribl numbfr
 *                          u4         numbfr of flfmfnts
 *                          id         brrby dlbss ID
 *                          [id]*      flfmfnts
 *
 *               HPROF_GC_PRIM_ARRAY_DUMP      dump of b primitivf brrby
 *
 *                          id         brrby objfdt ID
 *                          u4         stbdk trbdf sfribl numbfr
 *                          u4         numbfr of flfmfnts
 *                          u1         flfmfnt typf
 *                                     4:  boolfbn brrby
 *                                     5:  dibr brrby
 *                                     6:  flobt brrby
 *                                     7:  doublf brrby
 *                                     8:  bytf brrby
 *                                     9:  siort brrby
 *                                     10: int brrby
 *                                     11: long brrby
 *                          [u1]*      flfmfnts
 *
 * HPROF_HEAP_DUMP_END      tfrminbtfs sfrifs of ifbp dump sfgmfnts
 *
 * HPROF_CPU_SAMPLES        b sft of sbmplf trbdfs of running tirfbds
 *
 *                u4        totbl numbfr of sbmplfs
 *                u4        # of trbdfs
 *               [u4        # of sbmplfs
 *                u4]*      stbdk trbdf sfribl numbfr
 *
 * HPROF_CONTROL_SETTINGS   tif sfttings of on/off switdifs
 *
 *                u4        0x00000001: bllod trbdfs on/off
 *                          0x00000002: dpu sbmpling on/off
 *                u2        stbdk trbdf dfpti
 *
 */

typfdff fnum HprofTbg {
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
 * Hfbp dump donstbnts
 */

typfdff fnum HprofGdTbg {
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
} HprofGdTbg;

fnum HprofTypf {
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
typfdff unsignfd dibr HprofTypf;

#dffinf HPROF_TYPE_SIZES                        \
    {                                           \
        /*Objfdt?*/     sizfof(HprofId),        \
        /*Objfdt?*/     sizfof(HprofId),        \
        /*Arrby*/       sizfof(HprofId),        \
        /*Objfdt?*/     sizfof(HprofId),        \
        /*jboolfbn*/    1,                      \
        /*jdibr*/       2,                      \
        /*jflobt*/      4,                      \
        /*jdoublf*/     8,                      \
        /*jbytf*/       1,                      \
        /*jsiort*/      2,                      \
        /*jint*/        4,                      \
        /*jlong*/       8                       \
    }

#dffinf HPROF_TYPE_IS_PRIMITIVE(ty)  ((ty)>=HPROF_BOOLEAN)

#fndif
