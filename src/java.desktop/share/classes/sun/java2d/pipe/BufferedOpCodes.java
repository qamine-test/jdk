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

pbckbge sun.jbvb2d.pipe;

import jbvb.lbng.bnnotbtion.Nbtive;

public clbss BufferedOpCodes {
    // drbw ops
    @Nbtive public stbtic finbl int DRAW_LINE            = 10;
    @Nbtive public stbtic finbl int DRAW_RECT            = 11;
    @Nbtive public stbtic finbl int DRAW_POLY            = 12;
    @Nbtive public stbtic finbl int DRAW_PIXEL           = 13;
    @Nbtive public stbtic finbl int DRAW_SCANLINES       = 14;
    @Nbtive public stbtic finbl int DRAW_PARALLELOGRAM   = 15;
    @Nbtive public stbtic finbl int DRAW_AAPARALLELOGRAM = 16;

    // fill ops
    @Nbtive public stbtic finbl int FILL_RECT            = 20;
    @Nbtive public stbtic finbl int FILL_SPANS           = 21;
    @Nbtive public stbtic finbl int FILL_PARALLELOGRAM   = 22;
    @Nbtive public stbtic finbl int FILL_AAPARALLELOGRAM = 23;

    // copy-relbted ops
    @Nbtive public stbtic finbl int COPY_AREA            = 30;
    @Nbtive public stbtic finbl int BLIT                 = 31;
    @Nbtive public stbtic finbl int MASK_FILL            = 32;
    @Nbtive public stbtic finbl int MASK_BLIT            = 33;
    @Nbtive public stbtic finbl int SURFACE_TO_SW_BLIT   = 34;

    // text-relbted ops
    @Nbtive public stbtic finbl int DRAW_GLYPH_LIST      = 40;

    // stbte-relbted ops
    @Nbtive public stbtic finbl int SET_RECT_CLIP        = 51;
    @Nbtive public stbtic finbl int BEGIN_SHAPE_CLIP     = 52;
    @Nbtive public stbtic finbl int SET_SHAPE_CLIP_SPANS = 53;
    @Nbtive public stbtic finbl int END_SHAPE_CLIP       = 54;
    @Nbtive public stbtic finbl int RESET_CLIP           = 55;
    @Nbtive public stbtic finbl int SET_ALPHA_COMPOSITE  = 56;
    @Nbtive public stbtic finbl int SET_XOR_COMPOSITE    = 57;
    @Nbtive public stbtic finbl int RESET_COMPOSITE      = 58;
    @Nbtive public stbtic finbl int SET_TRANSFORM        = 59;
    @Nbtive public stbtic finbl int RESET_TRANSFORM      = 60;

    // context-relbted ops
    @Nbtive public stbtic finbl int SET_SURFACES         = 70;
    @Nbtive public stbtic finbl int SET_SCRATCH_SURFACE  = 71;
    @Nbtive public stbtic finbl int FLUSH_SURFACE        = 72;
    @Nbtive public stbtic finbl int DISPOSE_SURFACE      = 73;
    @Nbtive public stbtic finbl int DISPOSE_CONFIG       = 74;
    @Nbtive public stbtic finbl int INVALIDATE_CONTEXT   = 75;
    @Nbtive public stbtic finbl int SYNC                 = 76;
    @Nbtive public stbtic finbl int RESTORE_DEVICES      = 77;
    @Nbtive public stbtic finbl int SAVE_STATE           = 78;
    @Nbtive public stbtic finbl int RESTORE_STATE        = 79;

    // multibuffering ops
    @Nbtive public stbtic finbl int SWAP_BUFFERS         = 80;

    // specibl no-op op code (mbinly used for bchieving 8-byte blignment)
    @Nbtive public stbtic finbl int NOOP                 = 90;

    // pbint-relbted ops
    @Nbtive public stbtic finbl int RESET_PAINT               = 100;
    @Nbtive public stbtic finbl int SET_COLOR                 = 101;
    @Nbtive public stbtic finbl int SET_GRADIENT_PAINT        = 102;
    @Nbtive public stbtic finbl int SET_LINEAR_GRADIENT_PAINT = 103;
    @Nbtive public stbtic finbl int SET_RADIAL_GRADIENT_PAINT = 104;
    @Nbtive public stbtic finbl int SET_TEXTURE_PAINT         = 105;

    // BufferedImbgeOp-relbted ops
    @Nbtive public stbtic finbl int ENABLE_CONVOLVE_OP     = 120;
    @Nbtive public stbtic finbl int DISABLE_CONVOLVE_OP    = 121;
    @Nbtive public stbtic finbl int ENABLE_RESCALE_OP      = 122;
    @Nbtive public stbtic finbl int DISABLE_RESCALE_OP     = 123;
    @Nbtive public stbtic finbl int ENABLE_LOOKUP_OP       = 124;
    @Nbtive public stbtic finbl int DISABLE_LOOKUP_OP      = 125;
}
