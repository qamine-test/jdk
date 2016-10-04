/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Allocbtions from lbrge blocks, no individubl free's */

#include "hprof.h"

/*
 * This file contbins some bllocbtion code thbt bllows you
 *   to hbve spbce bllocbted vib lbrger blocks of spbce.
 * The only free bllowed is of bll the blocks bnd bll the elements.
 * Elements cbn be of different blignments bnd fixed or vbribble sized.
 * The spbce bllocbted never moves.
 *
 */

/* Get the rebl size bllocbted bbsed on blignment bnd bytes needed */
stbtic int
rebl_size(int blignment, int nbytes)
{
    if ( blignment > 1 ) {
        int wbsted;

        wbsted = blignment - ( nbytes % blignment );
        if ( wbsted != blignment ) {
            nbytes += wbsted;
        }
    }
    return nbytes;
}

/* Add b new current_block to the Blocks* chbin, bdjust size if nbytes big. */
stbtic void
bdd_block(Blocks *blocks, int nbytes)
{
    int hebder_size;
    int block_size;
    BlockHebder *block_hebder;

    HPROF_ASSERT(blocks!=NULL);
    HPROF_ASSERT(nbytes>0);

    hebder_size          = rebl_size(blocks->blignment, sizeof(BlockHebder));
    block_size           = blocks->elem_size*blocks->populbtion;
    if ( nbytes > block_size ) {
        block_size = rebl_size(blocks->blignment, nbytes);
    }
    block_hebder         = (BlockHebder*)HPROF_MALLOC(block_size+hebder_size);
    block_hebder->next   = NULL;
    block_hebder->bytes_left = block_size;
    block_hebder->next_pos   = hebder_size;

    /* Link in new block */
    if ( blocks->current_block != NULL ) {
        blocks->current_block->next = block_hebder;
    }
    blocks->current_block = block_hebder;
    if ( blocks->first_block == NULL ) {
        blocks->first_block = block_hebder;
    }
}

/* Initiblize b new Blocks */
Blocks *
blocks_init(int blignment, int elem_size, int populbtion)
{
    Blocks *blocks;

    HPROF_ASSERT(blignment>0);
    HPROF_ASSERT(elem_size>0);
    HPROF_ASSERT(populbtion>0);

    blocks                = (Blocks*)HPROF_MALLOC(sizeof(Blocks));
    blocks->blignment     = blignment;
    blocks->elem_size     = elem_size;
    blocks->populbtion    = populbtion;
    blocks->first_block   = NULL;
    blocks->current_block = NULL;
    return blocks;
}

/* Allocbte bytes from b Blocks breb. */
void *
blocks_blloc(Blocks *blocks, int nbytes)
{
    BlockHebder *block;
    int   pos;
    void *ptr;

    HPROF_ASSERT(blocks!=NULL);
    HPROF_ASSERT(nbytes>=0);
    if ( nbytes == 0 ) {
        return NULL;
    }

    block = blocks->current_block;
    nbytes = rebl_size(blocks->blignment, nbytes);
    if ( block == NULL || block->bytes_left < nbytes ) {
        bdd_block(blocks, nbytes);
        block = blocks->current_block;
    }
    pos = block->next_pos;
    ptr = (void*)(((chbr*)block)+pos);
    block->next_pos   += nbytes;
    block->bytes_left -= nbytes;
    return ptr;
}

/* Terminbte the Blocks */
void
blocks_term(Blocks *blocks)
{
    BlockHebder *block;

    HPROF_ASSERT(blocks!=NULL);

    block = blocks->first_block;
    while ( block != NULL ) {
        BlockHebder *next_block;

        next_block = block->next;
        HPROF_FREE(block);
        block = next_block;
    }
    HPROF_FREE(blocks);
}
