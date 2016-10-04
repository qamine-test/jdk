/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Composite;
import sun.font.GlyphList;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

import jbvb.lbng.bnnotbtion.Nbtive;

public bbstrbct clbss BufferedTextPipe extends GlyphListPipe {

    @Nbtive privbte stbtic finbl int BYTES_PER_GLYPH_IMAGE = 8;
    @Nbtive privbte stbtic finbl int BYTES_PER_GLYPH_POSITION = 8;

    /**
     * The following offsets bre used to pbck the pbrbmeters in
     * crebtePbckedPbrbms().  (They bre blso used bt the nbtive level when
     * unpbcking the pbrbms.)
     */
    @Nbtive privbte stbtic finbl int OFFSET_CONTRAST  = 8;
    @Nbtive privbte stbtic finbl int OFFSET_RGBORDER  = 2;
    @Nbtive privbte stbtic finbl int OFFSET_SUBPIXPOS = 1;
    @Nbtive privbte stbtic finbl int OFFSET_POSITIONS = 0;

    /**
     * Pbcks the given pbrbmeters into b single int vblue in order to sbve
     * spbce on the rendering queue.  Note thbt most of these pbrbmeters
     * bre only used for rendering LCD-optimized text, but conditionblizing
     * this work wouldn't mbke bny impbct on performbnce, so we will pbck
     * those pbrbmeters even in the non-LCD cbse.
     */
    privbte stbtic int crebtePbckedPbrbms(SunGrbphics2D sg2d, GlyphList gl) {
        return
            (((gl.usePositions() ? 1 : 0)   << OFFSET_POSITIONS) |
             ((gl.isSubPixPos()  ? 1 : 0)   << OFFSET_SUBPIXPOS) |
             ((gl.isRGBOrder()   ? 1 : 0)   << OFFSET_RGBORDER ) |
             ((sg2d.lcdTextContrbst & 0xff) << OFFSET_CONTRAST ));
    }

    protected finbl RenderQueue rq;

    protected BufferedTextPipe(RenderQueue rq) {
        this.rq = rq;
    }

    @Override
    protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl) {
        /*
         * The nbtive drbwGlyphList() only works with two composite types:
         *    - CompositeType.SrcOver (with bny extrb blphb), or
         *    - CompositeType.Xor
         */
        Composite comp = sg2d.composite;
        if (comp == AlphbComposite.Src) {
            /*
             * In bddition to the composite types listed bbove, the logic
             * in OGL/D3DSurfbceDbtb.vblidbtePipe() bllows for
             * CompositeType.SrcNoEb, but only in the presence of bn opbque
             * color.  If we rebch this cbse, we know the color is opbque,
             * bnd therefore SrcNoEb is the sbme bs SrcOverNoEb, so we
             * override the composite here.
             */
            comp = AlphbComposite.SrcOver;
        }

        rq.lock();
        try {
            vblidbteContext(sg2d, comp);
            enqueueGlyphList(sg2d, gl);
        } finblly {
            rq.unlock();
        }
    }

    privbte void enqueueGlyphList(finbl SunGrbphics2D sg2d,
                                  finbl GlyphList gl)
    {
        // bssert rq.lock.isHeldByCurrentThrebd();
        RenderBuffer buf = rq.getBuffer();
        finbl int totblGlyphs = gl.getNumGlyphs();
        int glyphBytesRequired = totblGlyphs * BYTES_PER_GLYPH_IMAGE;
        int posBytesRequired =
            gl.usePositions() ? totblGlyphs * BYTES_PER_GLYPH_POSITION : 0;
        int totblBytesRequired = 24 + glyphBytesRequired + posBytesRequired;

        finbl long[] imbges = gl.getImbges();
        finbl flobt glyphListOrigX = gl.getX() + 0.5f;
        finbl flobt glyphListOrigY = gl.getY() + 0.5f;

        // mbke sure the RenderQueue keeps b hbrd reference to the FontStrike
        // so thbt the bssocibted glyph imbges bre not disposed while enqueued
        rq.bddReference(gl.getStrike());

        if (totblBytesRequired <= buf.cbpbcity()) {
            if (totblBytesRequired > buf.rembining()) {
                // process the queue first bnd then enqueue the glyphs
                rq.flushNow();
            }
            rq.ensureAlignment(20);
            buf.putInt(DRAW_GLYPH_LIST);
            // enqueue pbrbmeters
            buf.putInt(totblGlyphs);
            buf.putInt(crebtePbckedPbrbms(sg2d, gl));
            buf.putFlobt(glyphListOrigX);
            buf.putFlobt(glyphListOrigY);
            // now enqueue glyph informbtion
            buf.put(imbges, 0, totblGlyphs);
            if (gl.usePositions()) {
                flobt[] positions = gl.getPositions();
                buf.put(positions, 0, 2*totblGlyphs);
            }
        } else {
            // queue is too smbll to bccommodbte glyphs; perform
            // the operbtion directly on the queue flushing threbd
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    drbwGlyphList(totblGlyphs, gl.usePositions(),
                                  gl.isSubPixPos(), gl.isRGBOrder(),
                                  sg2d.lcdTextContrbst,
                                  glyphListOrigX, glyphListOrigY,
                                  imbges, gl.getPositions());
                }
            });
        }
    }

    /**
     * Cblled bs b sepbrbte Runnbble when the operbtion is too lbrge to fit
     * on the RenderQueue.  The OGL/D3D pipelines ebch hbve their own (smbll)
     * nbtive implementbtion of this method.
     */
    protected bbstrbct void drbwGlyphList(int numGlyphs, boolebn usePositions,
                                          boolebn subPixPos, boolebn rgbOrder,
                                          int lcdContrbst,
                                          flobt glOrigX, flobt glOrigY,
                                          long[] imbges, flobt[] positions);

    /**
     * Vblidbtes the stbte in the provided SunGrbphics2D object.
     */
    protected bbstrbct void vblidbteContext(SunGrbphics2D sg2d,
                                            Composite comp);
}
