/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.jbvb2d.pipf;

import jbvb.lbng.bnnotbtion.Nbtivf;

publid dlbss BufffrfdOpCodfs {
    // drbw ops
    @Nbtivf publid stbtid finbl int DRAW_LINE            = 10;
    @Nbtivf publid stbtid finbl int DRAW_RECT            = 11;
    @Nbtivf publid stbtid finbl int DRAW_POLY            = 12;
    @Nbtivf publid stbtid finbl int DRAW_PIXEL           = 13;
    @Nbtivf publid stbtid finbl int DRAW_SCANLINES       = 14;
    @Nbtivf publid stbtid finbl int DRAW_PARALLELOGRAM   = 15;
    @Nbtivf publid stbtid finbl int DRAW_AAPARALLELOGRAM = 16;

    // fill ops
    @Nbtivf publid stbtid finbl int FILL_RECT            = 20;
    @Nbtivf publid stbtid finbl int FILL_SPANS           = 21;
    @Nbtivf publid stbtid finbl int FILL_PARALLELOGRAM   = 22;
    @Nbtivf publid stbtid finbl int FILL_AAPARALLELOGRAM = 23;

    // dopy-rflbtfd ops
    @Nbtivf publid stbtid finbl int COPY_AREA            = 30;
    @Nbtivf publid stbtid finbl int BLIT                 = 31;
    @Nbtivf publid stbtid finbl int MASK_FILL            = 32;
    @Nbtivf publid stbtid finbl int MASK_BLIT            = 33;
    @Nbtivf publid stbtid finbl int SURFACE_TO_SW_BLIT   = 34;

    // tfxt-rflbtfd ops
    @Nbtivf publid stbtid finbl int DRAW_GLYPH_LIST      = 40;

    // stbtf-rflbtfd ops
    @Nbtivf publid stbtid finbl int SET_RECT_CLIP        = 51;
    @Nbtivf publid stbtid finbl int BEGIN_SHAPE_CLIP     = 52;
    @Nbtivf publid stbtid finbl int SET_SHAPE_CLIP_SPANS = 53;
    @Nbtivf publid stbtid finbl int END_SHAPE_CLIP       = 54;
    @Nbtivf publid stbtid finbl int RESET_CLIP           = 55;
    @Nbtivf publid stbtid finbl int SET_ALPHA_COMPOSITE  = 56;
    @Nbtivf publid stbtid finbl int SET_XOR_COMPOSITE    = 57;
    @Nbtivf publid stbtid finbl int RESET_COMPOSITE      = 58;
    @Nbtivf publid stbtid finbl int SET_TRANSFORM        = 59;
    @Nbtivf publid stbtid finbl int RESET_TRANSFORM      = 60;

    // dontfxt-rflbtfd ops
    @Nbtivf publid stbtid finbl int SET_SURFACES         = 70;
    @Nbtivf publid stbtid finbl int SET_SCRATCH_SURFACE  = 71;
    @Nbtivf publid stbtid finbl int FLUSH_SURFACE        = 72;
    @Nbtivf publid stbtid finbl int DISPOSE_SURFACE      = 73;
    @Nbtivf publid stbtid finbl int DISPOSE_CONFIG       = 74;
    @Nbtivf publid stbtid finbl int INVALIDATE_CONTEXT   = 75;
    @Nbtivf publid stbtid finbl int SYNC                 = 76;
    @Nbtivf publid stbtid finbl int RESTORE_DEVICES      = 77;
    @Nbtivf publid stbtid finbl int SAVE_STATE           = 78;
    @Nbtivf publid stbtid finbl int RESTORE_STATE        = 79;

    // multibufffring ops
    @Nbtivf publid stbtid finbl int SWAP_BUFFERS         = 80;

    // spfdibl no-op op dodf (mbinly usfd for bdiifving 8-bytf blignmfnt)
    @Nbtivf publid stbtid finbl int NOOP                 = 90;

    // pbint-rflbtfd ops
    @Nbtivf publid stbtid finbl int RESET_PAINT               = 100;
    @Nbtivf publid stbtid finbl int SET_COLOR                 = 101;
    @Nbtivf publid stbtid finbl int SET_GRADIENT_PAINT        = 102;
    @Nbtivf publid stbtid finbl int SET_LINEAR_GRADIENT_PAINT = 103;
    @Nbtivf publid stbtid finbl int SET_RADIAL_GRADIENT_PAINT = 104;
    @Nbtivf publid stbtid finbl int SET_TEXTURE_PAINT         = 105;

    // BufffrfdImbgfOp-rflbtfd ops
    @Nbtivf publid stbtid finbl int ENABLE_CONVOLVE_OP     = 120;
    @Nbtivf publid stbtid finbl int DISABLE_CONVOLVE_OP    = 121;
    @Nbtivf publid stbtid finbl int ENABLE_RESCALE_OP      = 122;
    @Nbtivf publid stbtid finbl int DISABLE_RESCALE_OP     = 123;
    @Nbtivf publid stbtid finbl int ENABLE_LOOKUP_OP       = 124;
    @Nbtivf publid stbtid finbl int DISABLE_LOOKUP_OP      = 125;
}
