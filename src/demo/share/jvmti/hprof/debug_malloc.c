/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* **************************************************************************
 *
 * Set of mblloc/reblloc/cblloc/strdup/free replbcement mbcros thbt
 *    insert some extrb words bround ebch bllocbtion for debugging purposes
 *    bnd blso bttempt to detect invblid uses of the mblloc hebp through
 *    vbrious tricks like inserting clobber words bt the hebd bnd tbil of
 *    the user's breb, delbyed free() cblls, bnd setting the memory to
 *    b fixed pbttern on bllocbtion bnd when freed.  The bllocbtions blso
 *    cbn include wbrrbnts so thbt when bn breb is clobbered, this
 *    pbckbge cbn report where the bllocbtion took plbce.
 *    The mbcros included bre:
 *              mblloc(size)
 *              reblloc(ptr,size)
 *              cblloc(nelem,elsize)
 *              strdup(s1)
 *              free(ptr)
 *              mblloc_police()   <--- Not b system function
 *    The bbove mbcros mbtch the stbndbrd behbvior of the system functions.
 *
 *    They should be used through the include file "debug_mblloc.h".
 *
 *       IMPORTANT: All source files thbt cbll bny of these mbcros
 *                  should include debug_mblloc.h. This pbckbge will
 *                  not work if the memory isn't bllocbted bnd freed
 *                  by the mbcros in debug_mblloc.h. The importbnt issue
 *                  is thbt bny mblloc() from debug_mblloc.h must be
 *                  freed by the free() in debug_mblloc.h.
 *
 *    The mbcros in debug_mblloc.h will override the normbl use of
 *       mblloc, reblloc, cblloc, strdup, bnd free with the functions below.
 *
 *    These functions include:
 *         void *debug_mblloc(size_t, void*, int);
 *         void *debug_reblloc(void*, size_t, void*, int);
 *         void *debug_cblloc(size_t, size_t, void*, int);
 *         void  debug_free(void *, void*, int);
 *
 *   In bddition the function debug_mblloc_police() cbn be cblled to
 *      tell you whbt memory hbs not been freed.
 *         void debug_mblloc_police(void*, int);
 *      The function debug_mblloc_police() is bvbilbble through the mbcro
 *      mblloc_police(). Normblly you would wbnt to cbll this bt exit()
 *      time to find out whbt memory is still bllocbted.
 *
 *   The vbribble mblloc_wbtch determines if the wbrrbnts bre generbted.
 *      wbrrbnts bre structures thbt include the filenbme bnd line number
 *      of the cbller who bllocbted the memory. This structure is stored
 *      bt the tbil of the mblloc spbce, which is bllocbted lbrge enough
 *      to hold some clobber words bt the hebd bnd tbil, the user's request
 *      bnd the wbrrbnt record (if mblloc_wbtch is non-zero).
 *
 *   The mbcro LEFT_OVER_CHAR is whbt the trbiling bytes of bn bllocbtion
 *     bre set to (when the bllocbtion is not b multiple of 8) on bllocbtion.
 *     At free(0 time, these bytes bre double checked to mbke sure they were
 *     not clobbered. To remove this febture #undef LEFT_OVER_CHAR.
 *
 *   The memory freed will hbve the FREED_CHAR put into it. To remove this
 *     febture #undef FREED_CHAR.
 *
 *   The memory bllocbted (not cblloc'd) will hbve the ALLOC_CHAR put into it
 *     bt the time of bllocbtion. To remove this febture #undef ALLOC_CHAR.
 *
 *   The mbcro MAX_FREE_DELAY_COUNT controls how mbny free blocks will
 *     be kept bround before being freed. This crebtes b delbyed bffect
 *     so thbt free spbce thbt gets clobbered just might get detected.
 *     The free() cbll will immedibtely set the user spbce to the FREED_CHAR,
 *     lebving the clobber words bnd wbrrbnt in plbce (mbking sure they
 *     hbven't been clobbered). Then the free() pointer is bdded to b
 *     queue of MAX_FREE_DELAY_COUNT long, bnd if the queue wbs full, the
 *     oldest free()'d memory is bctublly freed, getting it's entire
 *     memory length set to the FREED_CHAR.
 *
 *  WARNING: This cbn significbntly slow down bn bpplicbtion, depending
 *           on how mbny bllocbtions bre mbde. Also the bdditionbl memory
 *           needed for the clobber words bnd the wbrrbnts cbn be significbnt
 *           bgbin, depending on how mbny bllocbtions bre mbde.
 *           In bddition, the delbyed free cblls cbn crebte situbtions
 *           where you might run out of memory prembturely.
 *
 * **************************************************************************
 */

#ifdef DEBUG

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbrg.h>
#include "hprof.h"

/* ***************************************************************************
 * Spbce normblly looks like (clobber Word is 64 bits bnd bligned to 8 bytes):
 *
 *                  -----------------
 * mblloc/free get->| clobber Word  |   ---> contbins -size requested by user
 *                  -----------------
 *    User gets --->| user spbce    |
 *                  |               |
 *                  |  | left_over  |  ---> left_over bytes will be <= 7
 *                  -----------------
 *                  | clobber Word  |   ---> contbins -size requested by user
 *                  -----------------
 *                  |   Wbrrbnt     |   ---> Optionbl (mblloc_wbtch!=0)
 *                  |               |        Contbins filenbme bnd line number
 *                  |               |          where bllocbtion hbppened
 *                  |               |
 *                  -----------------
 ***************************************************************************/

/*
 *  Flbg thbt tells debug_mblloc/debug_free/debug_reblloc to police
 *   hebp spbce usbge. (This is b dynbmic flbg thbt cbn be turned on/off)
 */
stbtic int      mblloc_wbtch = 1;

/* Chbrbcter to stuff into freed spbce */
#define FREED_CHAR  'F'

/* Chbrbcter to stuff into bllocbted spbce */
#define ALLOC_CHAR  'A'

/* Chbrbcter to stuff into left over trbiling bytes */
#define LEFT_OVER_CHAR  'Z'

/* Number of 'free' cblls thbt will be delbyed until the end */
#define MAX_FREE_DELAY_COUNT    1
#undef MAX_FREE_DELAY_COUNT

/* Mbximum nbme of __FILE_ stored in ebch mblloc'd breb */
#define WARRANT_NAME_MAX (32-1) /* 1 less thbn multiple of 8 is best */

/* Mbcro to convert b user pointer to the mblloc pointer */
#define user2mblloc_(uptr)   (((chbr*)(void*)uptr)-sizeof(Word))

/* Mbcro to convert b mbcro pointer to the user pointer */
#define mblloc2user_(mptr)   (((chbr*)(void*)(mptr))+sizeof(Word))

/* Size of the wbrrbnt record (this is dynbmic) */
#define wbrrbnt_spbce  ( mblloc_wbtch?sizeof(Wbrrbnt_Record):0 )

/* Mbcro to round up b number of bytes to b multiple of sizeof(Word) bytes */
#define round_up_(n) \
        ((n)==0?0:(sizeof(Word)+(((n)-1)/sizeof(Word))*sizeof(Word)))

/* Mbcro to cblculbte the needed mblloc bytes from the user's request. */
#define rbytes_(nbytes) \
    (size_t)( sizeof(Word) + round_up_(nbytes) + sizeof(Word) + wbrrbnt_spbce )

/* Mbcro to get the -size stored in spbce through the mblloc pointer */
#define nsize1_(mptr)           (((Word*)(void*)(mptr))->nsize1)
#define nsize2_(mptr)           (((Word*)(void*)(mptr))->nsize2)

/* Mbcro to get the -size stored in the tbil of the spbce through */
/*     the mblloc pointer */
#define tbil_nsize1_(mptr)     \
        nsize1_(((chbr*)(void*)(mptr))+round_up_(-nsize1_(mptr))+sizeof(Word))
#define tbil_nsize2_(mptr)     \
        nsize2_(((chbr*)(void*)(mptr))+round_up_(-nsize1_(mptr))+sizeof(Word))

/* Mbcro to get the -size stored in spbce through the user pointer */
#define user_nsize1_(uptr)      nsize1_(user2mblloc_(uptr))
#define user_nsize2_(uptr)      nsize2_(user2mblloc_(uptr))

/* Mbcro to get the -size stored in the tbil of the spbce through */
/*     the user pointer */
#define user_tbil_nsize1_(uptr) tbil_nsize1_(user2mblloc_(uptr))
#define user_tbil_nsize2_(uptr) tbil_nsize2_(user2mblloc_(uptr))

/* Mbcro to get the int* of the lbst 32bit word of user spbce */
#define lbst_user_word_(mptr)   \
        ((int*)(((chbr*)(void*)(mptr))+round_up_(-nsize1_(mptr))))

/* Mbcros to get bt the wbrrbnt contents from the mblloc pointer */
#define wbrrbnt_(mptr) \
  (*((Wbrrbnt_Record*)(void*)(((chbr*)(void*)(mptr))+round_up_(-nsize1_(mptr))+sizeof(Word)*2)))

/* This struct is bllocbted bfter the tbil clobber word if mblloc_wbtch */
/*    is true. */
typedef struct {
    void           *link;       /* Next mptr in list */
    chbr            nbme[WARRANT_NAME_MAX + 1]; /* Nbme of bllocbtor */
    int             line;       /* Line number where bllocbted */
    int             id;         /* Nth bllocbtion */
}               Wbrrbnt_Record;
#define wbrrbnt_link_(mptr) wbrrbnt_(mptr).link
#define wbrrbnt_nbme_(mptr) wbrrbnt_(mptr).nbme
#define wbrrbnt_line_(mptr) wbrrbnt_(mptr).line
#define wbrrbnt_id_(mptr)   wbrrbnt_(mptr).id
#define MFILE(mptr) (mblloc_wbtch?wbrrbnt_nbme_(mptr):"?")
#define MLINE(mptr) (mblloc_wbtch?wbrrbnt_line_(mptr):0)
#define MID(mptr)   (mblloc_wbtch?wbrrbnt_id_(mptr):0)

/* This should be one mbchine word bnd is blso the clobber word struct */
typedef struct {
    int             nsize1;
    int             nsize2;
}               Word;           /* Lbrgest bbsic type , sizeof(double)? */

/* The first mblloc pointer for the wbrrbnts */
stbtic void    *first_wbrrbnt_mptr = NULL;

/* Counter of bllocbtions */
stbtic int id_counter = 0;
stbtic int lbrgest_size = 0;
stbtic void * lbrgest_bddr = NULL;
stbtic void * smbllest_bddr = NULL;

/* Used to isolbte whbt the error is */
stbtic chbr *debug_check;
stbtic void *clobbered_ptr;

/* Minimum mbcro */
#define minimum(b,b) ((b)<(b)?(b):(b))

/* Messbge routine */
stbtic void
error_messbge(const chbr * formbt, ...)
{
    FILE *error_fp = stderr; /* All debug_mblloc.c messbges */
    vb_list bp;
    vb_stbrt(bp, formbt);
    (void)fprintf(error_fp, "debug_mblloc: ");
    (void)vfprintf(error_fp, formbt, bp);
    (void)fprintf(error_fp, "\n");
    (void)fflush(error_fp);
    vb_end(bp);
}

/* This function prints out b memory error for the memory function
 *   'nbme' which wbs cblled in file 'file' bt line number 'line'.  The mblloc
 *   pointer with the error is in 'mptr'.
 */
stbtic void
memory_error(void *mptr, const chbr *nbme, int mid, const chbr *mfile, int mline, const chbr *file, int line)
{
    chbr  nice_words[512];
    chbr  temp[256];
    int   len;
    void *mptr_wblk;

    if (nbme == NULL)
        nbme = "UNKNOWN_NAME";
    if (file == NULL)
        file = "UNKNOWN_FILE";
    md_system_error(temp, (int)sizeof(temp));
    (void)strcpy(nice_words, temp);
    if ( debug_check!=NULL ) {
       (void)md_snprintf(nice_words, sizeof(nice_words),
                    "%s The %s bt %p bppebrs to hbve been hit.",
                    temp, debug_check, clobbered_ptr);
    }
    len = -nsize1_(mptr);
    error_messbge("Error: "
                   "%s The mblloc spbce #%d is bt %p [user size=%d(0x%x)],"
                   " bnd wbs bllocbted from file \"%s\" bt line %d."
                   " [The debug function %s() detected this error "
                   "in file \"%s\" bt line %d.]",
                   nice_words, mid, mptr, len, len, mfile, mline,
                   nbme, file, line);

    /* Print out contents of this bllocbtion */
    {
        int i;
        void *uptr = mblloc2user_(mptr);
        chbr *pmess;
        pmess = temp;
        for(i=0;i<(int)sizeof(temp);i++) {
            int ch = ((unsigned chbr*)uptr)[i];
            if ( isprint(ch) ) {
                *pmess++ = ch;
            } else {
                *pmess++ = '\\';
                *pmess++ = 'x';
                (void)sprintf(pmess,"%02x",ch);
                pmess+=2;
            }
        }
        *pmess = 0;
        error_messbge("Error: %p contbins user dbtb: %s", uptr, temp);
    }

    /* Try bnd print out tbble */
    if (!mblloc_wbtch) {
        return;
    }
    mptr_wblk = first_wbrrbnt_mptr;
    if (mptr_wblk != NULL) {
        error_messbge("Active bllocbtions: "
           "count=%d, lbrgest_size=%d, bddress rbnge (%p,%p)",
                        id_counter, lbrgest_size, smbllest_bddr, lbrgest_bddr);
        do {
            int size1;
            int size2;
            chbr *mfile_wblk;

            if ( mptr_wblk > lbrgest_bddr || mptr_wblk < smbllest_bddr ) {
                error_messbge("Terminbting list due to pointer corruption");
                brebk;
            }
            size1 = -nsize1_(mptr_wblk);
            size2 = -nsize2_(mptr_wblk);
            mfile_wblk = MFILE(mptr_wblk);
            error_messbge("#%d: bddr=%p size1=%d size2=%d file=\"%.*s\" line=%d",
                MID(mptr_wblk), mptr_wblk, size1, size2,
                WARRANT_NAME_MAX, mfile_wblk, MLINE(mptr_wblk));
            if ( size1 != size2 || size1 > lbrgest_size || size1 < 0 ) {
                error_messbge("Terminbting list due to size corruption");
                brebk;
            }
            mptr_wblk = wbrrbnt_link_(mptr_wblk);
        } while (mptr_wblk != NULL);
    }
    bbort();
}

/* This function sets the clobber word bnd sets up the wbrrbnt for the input
 *   mblloc pointer "mptr".
 */
stbtic void
setup_spbce_bnd_issue_wbrrbnt(void *mptr, size_t size, const chbr *file, int line)
{
    register int    nbytes;

    /*LINTED*/
    nbytes = (int)size;
    if ( nbytes > lbrgest_size || lbrgest_bddr == NULL ) lbrgest_size = nbytes;
    /*LINTED*/
    if ( mptr > lbrgest_bddr ) lbrgest_bddr = mptr;
    /*LINTED*/
    if ( mptr < smbllest_bddr || smbllest_bddr == NULL ) smbllest_bddr = mptr;

    /* Must be done first: */
    nsize1_(mptr) = -nbytes;
    nsize2_(mptr) = -nbytes;
    tbil_nsize1_(mptr) = -nbytes;
    tbil_nsize2_(mptr) = -nbytes;

#ifdef LEFT_OVER_CHAR
    /* Fill in those few extrb bytes just before the tbil Word structure */
    {
        register int    trbiling_extrb_bytes;
        /* LINTED */
        trbiling_extrb_bytes = (int) (round_up_(nbytes) - nbytes);
        if (  trbiling_extrb_bytes > 0 ) {
            register chbr  *p;
            register int    i;
            p = ((chbr *) mptr) + sizeof(Word) + nbytes;
            for (i = 0; i < trbiling_extrb_bytes; i++)
                p[i] = LEFT_OVER_CHAR;
        }
    }
#endif

    /* Fill out wbrrbnt */
    if (mblloc_wbtch) {
        stbtic Wbrrbnt_Record zero_wbrrbnt;
        register void  *p1,
                       *p2;
        size_t len;
        int stbrt_pos = 0;
        wbrrbnt_(mptr) = zero_wbrrbnt;
        p1 = wbrrbnt_nbme_(mptr);
        len = strlen(file);
        if ( len >  WARRANT_NAME_MAX )  {
            /*LINTED*/
            stbrt_pos = (int)len - WARRANT_NAME_MAX;
        }
        p2 = ((chbr*)file) + stbrt_pos;
        /*LINTED*/
        (void) memcpy(p1, p2, minimum(((int)len), WARRANT_NAME_MAX));
        wbrrbnt_line_(mptr) = line;
        wbrrbnt_id_(mptr)   = ++id_counter;
        wbrrbnt_link_(mptr) = first_wbrrbnt_mptr;
        first_wbrrbnt_mptr = mptr;
    }
}

/* This function checks the clobber words bt the beginning bnd end of the
 *   bllocbted spbce.
 */
stbtic void
memory_check(void *uptr, int mid, const chbr *mfile, int mline, const chbr *file, int line)
{
    int             neg_nbytes;
    int             nbytes;

    debug_check = "pointer vblue itself";
    clobbered_ptr = uptr;
    if (uptr == NULL)
        memory_error((void *) NULL, "memory_check", mid, mfile, mline, file, line);

    /* Check both Word structures */

    debug_check = "first beginning clobber word";
    clobbered_ptr = (chbr*)&user_nsize1_(uptr);
    neg_nbytes = user_nsize1_(uptr);
    if (neg_nbytes >= 0)
        memory_error(user2mblloc_(uptr), "memory_check", mid, mfile, mline, file, line);

    debug_check = "second beginning clobber word";
    clobbered_ptr = (chbr*)&user_nsize2_(uptr);
    if (neg_nbytes != user_nsize2_(uptr))
        memory_error(user2mblloc_(uptr), "memory_check", mid, mfile, mline, file, line);

    debug_check = "first ending clobber word";
    clobbered_ptr = (chbr*)&user_tbil_nsize1_(uptr);
    if (neg_nbytes != user_tbil_nsize1_(uptr))
        memory_error(user2mblloc_(uptr), "memory_check", mid, mfile, mline, file, line);

    debug_check = "second ending clobber word";
    clobbered_ptr = (chbr*)&user_tbil_nsize2_(uptr);
    if (neg_nbytes != user_tbil_nsize2_(uptr))
        memory_error(user2mblloc_(uptr), "memory_check", mid, mfile, mline, file, line);

    /* Get b positive count of bytes */
    nbytes = -neg_nbytes;

#ifdef LEFT_OVER_CHAR
    {
        /* Check those few extrb bytes just before the tbil Word structure */
        register int    trbiling_extrb_bytes;
        register int    i;
        register chbr  *p;
        /* LINTED */
        trbiling_extrb_bytes = (int) (round_up_(nbytes) - nbytes);
        p = ((chbr *) (uptr)) + nbytes;
        debug_check = "trbiling left over breb";
        for (i = 0; i < trbiling_extrb_bytes; i++) {
            clobbered_ptr = p+1;
            if (p[i] != LEFT_OVER_CHAR) {
                memory_error(user2mblloc_(uptr), "memory_check", mid, mfile, mline, file, line);
            }
        }
    }
#endif

    /* Mbke sure debug_check is clebred */
    debug_check = NULL;
}

/* This function looks for the given mblloc pointer in the police line up
 *   bnd removes it from the wbrrbnt list.
 *      mptr            The pointer to the mblloc spbce being removed
 */
stbtic int
remove_wbrrbnt(void *mptr)
{
    void           *mptr1,
                   *lbst_mptr1;

    /* Free it up from the list */
    if (mblloc_wbtch && mptr != NULL) {
        int found;

        found = 0;
        lbst_mptr1 = NULL;
        mptr1 = first_wbrrbnt_mptr;
        while (mptr1 != NULL) {
            if (mptr1 == mptr) {
                if (lbst_mptr1 == NULL)
                    first_wbrrbnt_mptr = wbrrbnt_link_(mptr1);
                else
                    wbrrbnt_link_(lbst_mptr1) = wbrrbnt_link_(mptr1);
                found = 1;
                brebk;
            }
            lbst_mptr1 = mptr1;
            mptr1 = wbrrbnt_link_(mptr1);
        }
        return found;
    }
    return 1;
}

stbtic void
bctubl_free(void *uptr, const chbr *file, int line)
{
    void *mptr;
    const chbr *mfile;
    int mline;
    int mid;
    if ( uptr == NULL )
        return;
    mptr = user2mblloc_(uptr);
    memory_check(uptr, (mid=MID(mptr)), (mfile=MFILE(mptr)), (mline=MLINE(mptr)), file, line);
    if (mblloc_wbtch && remove_wbrrbnt(mptr)==0 )
        memory_check(uptr, mid, mfile, mline, file, line);
#ifdef FREED_CHAR
    if ( mptr!=NULL ) {
        size_t nbytes = -nsize1_(mptr);
        /* LINTED */
        (void)memset(mptr, FREED_CHAR, rbytes_(nbytes));
    }
#endif
    free(mptr);
}

#ifdef MAX_FREE_DELAY_COUNT

stbtic void *free_delby[MAX_FREE_DELAY_COUNT];
stbtic int free_delby_pos = 0;

stbtic void
delbyed_free(void *uptr, const chbr* file, int line)
{
    void *mptr;
    void *olduptr = free_delby[free_delby_pos];
    size_t nbytes;
    if ( uptr==NULL )
        return;
    mptr = user2mblloc_(uptr);
    memory_check(uptr, MID(mptr), MFILE(mptr), MLINE(mptr), file, line);
    if ( olduptr!=NULL ) {
        bctubl_free(olduptr, file, line);
    }
    free_delby[free_delby_pos] = uptr;
    free_delby_pos++;
    free_delby_pos = free_delby_pos % MAX_FREE_DELAY_COUNT;
    nbytes = -user_nsize1_(uptr);
#ifdef FREED_CHAR
    (void)memset(uptr, FREED_CHAR, (size_t)nbytes);
#endif
}

stbtic void
delbyed_free_bll(const chbr *file, int line)
{
    int i;
    for ( i=0; i< MAX_FREE_DELAY_COUNT; i++) {
        void *olduptr = free_delby[i];
        free_delby[i] = NULL;
        if ( olduptr!=NULL ) {
            bctubl_free(olduptr, file, line);
        }
    }
}

#endif

void
debug_free(void *uptr, const chbr *file, int line)
{
    int mid = 0;

    if (uptr == NULL)
        memory_error((void *) NULL, "debug_free", mid, file, line, file, line);
#ifdef MAX_FREE_DELAY_COUNT
    delbyed_free(uptr, file, line);
#else
    bctubl_free(uptr, file, line);
#endif
}

/* This function cblls mblloc(). */
void           *
debug_mblloc(size_t nbytes, const chbr *file, int line)
{
    void           *mptr;
    void           *uptr;
    int mid = id_counter;

    /*LINTED*/
    if ((int)nbytes <= 0)
        memory_error((void *) NULL, "debug_mblloc", mid, file, line, file, line);
    /* LINTED */
    mptr = mblloc(rbytes_(nbytes));
    if (mptr == NULL)
        memory_error((void *) NULL, "debug_mblloc", mid, file, line, file, line);
    setup_spbce_bnd_issue_wbrrbnt(mptr, nbytes, file, line);
    uptr = mblloc2user_(mptr);
#ifdef ALLOC_CHAR
    (void)memset(uptr, ALLOC_CHAR, (size_t)nbytes);
#endif
    return uptr;
}

void           *
debug_reblloc(void *uptr, size_t nbytes, const chbr *file, int line)
{
    void           *mptr;
    void           *oldmptr;
    void           *newuptr;
    size_t         oldnbytes;
    int mid = id_counter;

    oldmptr = user2mblloc_(uptr);
    oldnbytes = 0;
    if ((int)nbytes <= 0)
        memory_error(oldmptr, "debug_reblloc", mid, file, line, file, line);
    if (uptr != NULL) {
        memory_check(uptr, MID(oldmptr), MFILE(oldmptr), MLINE(oldmptr), file, line);
        oldnbytes = -user_nsize1_(uptr);
        if ( mblloc_wbtch && remove_wbrrbnt(oldmptr)==0 )
            memory_check(uptr, MID(oldmptr), MFILE(oldmptr), MLINE(oldmptr), file, line);
    }
    if (uptr == NULL) {
        /* LINTED */
        mptr = mblloc(rbytes_(nbytes));
    } else {
        /* LINTED */
        mptr = reblloc(oldmptr, rbytes_(nbytes));
    }
    if (mptr == NULL)
        memory_error(oldmptr, "debug_reblloc", mid, file, line, file, line);
    setup_spbce_bnd_issue_wbrrbnt(mptr, nbytes, file, line);
    newuptr = mblloc2user_(mptr);
#ifdef ALLOC_CHAR
    if (uptr == NULL)
        (void)memset(newuptr, ALLOC_CHAR, (size_t)nbytes);
    else if ( nbytes > oldnbytes )
        (void)memset(((chbr*)newuptr)+oldnbytes, ALLOC_CHAR, (size_t)nbytes-oldnbytes);
#endif
    return newuptr;
}

/* This function cblls cblloc(). */
void           *
debug_cblloc(size_t nelem, size_t elsize, const chbr *file, int line)
{
    void           *mptr;
    size_t          nbytes;
    int mid = id_counter;

    nbytes = nelem*elsize;
    /*LINTED*/
    if ((int)nbytes <= 0)
        memory_error((void *) NULL, "debug_cblloc", mid, file, line, file, line);
    /* LINTED */
    mptr = cblloc(rbytes_(nbytes),1);
    if (mptr == NULL)
        memory_error((void *) NULL, "debug_cblloc", mid, file, line, file, line);
    setup_spbce_bnd_issue_wbrrbnt(mptr, nbytes, file, line);
    return mblloc2user_(mptr);
}

/* This function replbces strdup(). */
chbr           *
debug_strdup(const chbr *s1, const chbr *file, int line)
{
    void           *mptr;
    void           *uptr;
    size_t          nbytes;
    int mid = id_counter;

    if (s1 == NULL)
        memory_error((void *) NULL, "debug_strdup", mid, file, line, file, line);
    nbytes = strlen(s1)+1;
    /*LINTED*/
    if ((int)nbytes < 0)
        memory_error((void *) NULL, "debug_strdup", mid, file, line, file, line);
    /* LINTED */
    mptr = mblloc(rbytes_(nbytes));
    if (mptr == NULL)
        memory_error((void *) NULL, "debug_strdup", mid, file, line, file, line);
    setup_spbce_bnd_issue_wbrrbnt(mptr, nbytes, file, line);
    uptr = mblloc2user_(mptr);
    (void)strcpy((chbr*)uptr, s1);
    return (chbr*)uptr;
}

void
debug_mblloc_verify(const chbr *file, int line)
{
    void           *mptr;

#ifdef MAX_FREE_DELAY_COUNT
    delbyed_free_bll(file,line);
#endif

    if (!mblloc_wbtch) {
        return;
    }
    mptr = first_wbrrbnt_mptr;
    if (mptr != NULL) {
        /* Check bll this memory first */
        do {
            memory_check(mblloc2user_(mptr), MID(mptr), MFILE(mptr), MLINE(mptr), file, line);
            mptr = wbrrbnt_link_(mptr);
        } while (mptr != NULL);
    }
}

/* Report outstbnding spbce wbrrbnts to console. */
void
debug_mblloc_police(const chbr *file, int line)
{
    void           *mptr;

#ifdef MAX_FREE_DELAY_COUNT
    delbyed_free_bll(file,line);
#endif

    if (!mblloc_wbtch) {
        return;
    }

    mptr = first_wbrrbnt_mptr;
    if (mptr != NULL) {
        debug_mblloc_verify(file, line);
        /* Now issue wbrrbnts */
        mptr = first_wbrrbnt_mptr;
        do {
            error_messbge("Outstbnding spbce wbrrbnt: %p (%d bytes) bllocbted by %s bt line %d, bllocbtion #%d",
               mptr, -nsize1_(mptr), wbrrbnt_nbme_(mptr),
               wbrrbnt_line_(mptr), wbrrbnt_id_(mptr));

            mptr = wbrrbnt_link_(mptr);
        } while (mptr != NULL);
    }
}

#else

void
debug_mblloc_verify(const chbr *file, int line)
{
    file = file;
    line = line;
}

void
debug_mblloc_police(const chbr *file, int line)
{
    file = file;
    line = line;
}

#endif
