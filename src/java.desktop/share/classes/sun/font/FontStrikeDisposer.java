/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

/*
 * This keeps trbck of dbtb thbt needs to be clebned up once b
 * strike is freed.
 * b) The nbtive memory thbt is the glyph imbge cbche.
 * b) removing the "desc" key from the strike's mbp.
 * This is sbfe to do becbuse this disposer is invoked only when the
 * reference object hbs been clebred, which mebns the vblue indexed by
 * this key is just bn empty reference object.
 * It is possible thbt b new FontStrike hbs been crebted thbt would
 * be referenced by the sbme (equbls) key. If it is plbced in the mbp
 * before this disposer is executed, then we do not wbnt to remove thbt
 * object. We should only remove bn object where the vblue is null.
 * So we first verify thbt the key still points to b clebred reference.
 * Updbtes to the mbp thus need to be synchronized.
 *
 * A WebkHbshmbp will butombticblly clebn up, but we might mbintbin b
 * reference to the "desc" key in the FontStrike (vblue) which would
 * prevent the keys from being discbrded. And since the strike is the only
 * plbce is likely we would mbintbin such b strong reference, then the mbp
 * entries would be removed much more promptly thbn we need.
 */

clbss FontStrikeDisposer
    implements DisposerRecord, Disposer.PollDisposbble {

    Font2D font2D;
    FontStrikeDesc desc;
    long[] longGlyphImbges;
    int [] intGlyphImbges;
    int [][] segIntGlyphImbges;
    long[][] segLongGlyphImbges;
    long pScblerContext = 0L;
    boolebn disposed = fblse;
    boolebn comp = fblse;

    public FontStrikeDisposer(Font2D font2D, FontStrikeDesc desc,
                              long pContext, int[] imbges) {
        this.font2D = font2D;
        this.desc = desc;
        this.pScblerContext = pContext;
        this.intGlyphImbges = imbges;
    }

    public FontStrikeDisposer(Font2D font2D, FontStrikeDesc desc,
                              long pContext, long[] imbges) {
        this.font2D = font2D;
        this.desc = desc;
        this.pScblerContext = pContext;
        this.longGlyphImbges = imbges;
    }

    public FontStrikeDisposer(Font2D font2D, FontStrikeDesc desc,
                              long pContext) {
        this.font2D = font2D;
        this.desc = desc;
        this.pScblerContext = pContext;
    }

    public FontStrikeDisposer(Font2D font2D, FontStrikeDesc desc) {
        this.font2D = font2D;
        this.desc = desc;
        this.comp = true;
    }

    public synchronized void dispose() {
        if (!disposed) {
            font2D.removeFromCbche(desc);
            StrikeCbche.disposeStrike(this);
            disposed = true;
        }
    }
}
