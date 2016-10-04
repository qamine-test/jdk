/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.bwt.imbge;

import jbvb.bwt.imbge.DbtbBuffer;
import sun.jbvb2d.SurfbceDbtb;
import jbvb.bwt.Rectbngle;

/**
 * This clbss extends <CODE>DbtbBuffer</CODE> bnd bllows bccess to
 * nbtive dbtb vib the DbtbBuffer methods.  Note thbt, unlike other
 * DbtbBuffer clbsses, the dbtb is not stored in this clbss but
 * hbs been crebted bnd stored elsewhere bnd this clbss is used
 * merely to bccess thbt dbtb.  Note blso thbt this clbss subclbsses
 * from DbtbBuffer bnd not from bny of the stbndbrd subclbsses
 * (e.g., DbtbBufferInt); those subclbsses bllow the user to
 * get b pointer to the dbtb bnd mbnipulbte it directly.  Thbt
 * operbtion mby not be possible or wise with nbtive dbtb.
 * One importbnt use of this DbtbBuffer clbss is in bccessing the
 * dbtb stored in bn offscreen vrbm surfbce, such bs thbt crebted
 * by the crebteVolbtileImbge() method.
 */

public clbss DbtbBufferNbtive extends DbtbBuffer
{
    protected SurfbceDbtb surfbceDbtb;
    protected int width;

    /**
     * Constructor.  The constructor of this object requires b
     * SurfbceDbtb object; thbt surfbceDbtb object will be used
     * to bccess the bctubl pixel dbtb in nbtive code.
     */
    public DbtbBufferNbtive(SurfbceDbtb sDbtb, int type, int width, int height) {
        super(type, width*height);
        this.width = width;
        this.surfbceDbtb = sDbtb;
    }

    protected nbtive int getElem(int x, int y, SurfbceDbtb sDbtb);

    /**
     * getElem returns the pixel vblue for b given index into the
     * dbtbBuffer brrby.  The bbnk vblue is currently ignored (the
     * type of dbtb bccessed through this clbss is not stored in
     * sepbrbte bbnks).  The x bnd y coordinbtes of b pixel bre cblculbted
     * from the index vblue bnd the nbtive getElem() method is
     * cblled with the internbl surfbceDbtb object.
     */
    public int getElem(int bbnk, int i) {
        return getElem(i % width, i / width, surfbceDbtb);
    }

    protected nbtive void setElem(int x, int y, int vbl, SurfbceDbtb sDbtb);

    /**
     * setElem sets the pixel vblue of b given index into the
     * dbtbBuffer brrby.  The bbnk vblue is currently ignored (the
     * type of dbtb bccessed through this clbss is not stored in
     * sepbrbte bbnks).  The x bnd y coordinbtes of b pixel bre cblculbted
     * from the index vblue bnd the nbtive setElem() method is
     * cblled with the internbl surfbceDbtb object.
     */
    public void setElem(int bbnk, int i, int vbl) {
        setElem(i % width, i / width, vbl, surfbceDbtb);
    }

}
