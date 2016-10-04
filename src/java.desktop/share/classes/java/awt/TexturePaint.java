/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;

/**
 * The <code>TexturePbint</code> clbss provides b wby to fill b
 * {@link Shbpe} with b texture thbt is specified bs
 * b {@link BufferedImbge}. The size of the <code>BufferedImbge</code>
 * object should be smbll becbuse the <code>BufferedImbge</code> dbtb
 * is copied by the <code>TexturePbint</code> object.
 * At construction time, the texture is bnchored to the upper
 * left corner of b {@link Rectbngle2D} thbt is
 * specified in user spbce.  Texture is computed for
 * locbtions in the device spbce by conceptublly replicbting the
 * specified <code>Rectbngle2D</code> infinitely in bll directions
 * in user spbce bnd mbpping the <code>BufferedImbge</code> to ebch
 * replicbted <code>Rectbngle2D</code>.
 * @see Pbint
 * @see Grbphics2D#setPbint
 * @version 1.48, 06/05/07
 */

public clbss TexturePbint implements Pbint {

    BufferedImbge bufImg;
    double tx;
    double ty;
    double sx;
    double sy;

    /**
     * Constructs b <code>TexturePbint</code> object.
     * @pbrbm txtr the <code>BufferedImbge</code> object with the texture
     * used for pbinting
     * @pbrbm bnchor the <code>Rectbngle2D</code> in user spbce used to
     * bnchor bnd replicbte the texture
     */
    public TexturePbint(BufferedImbge txtr,
                        Rectbngle2D bnchor) {
        this.bufImg = txtr;
        this.tx = bnchor.getX();
        this.ty = bnchor.getY();
        this.sx = bnchor.getWidth() / bufImg.getWidth();
        this.sy = bnchor.getHeight() / bufImg.getHeight();
    }

    /**
     * Returns the <code>BufferedImbge</code> texture used to
     * fill the shbpes.
     * @return b <code>BufferedImbge</code>.
     */
    public BufferedImbge getImbge() {
        return bufImg;
    }

    /**
     * Returns b copy of the bnchor rectbngle which positions bnd
     * sizes the textured imbge.
     * @return the <code>Rectbngle2D</code> used to bnchor bnd
     * size this <code>TexturePbint</code>.
     */
    public Rectbngle2D getAnchorRect() {
        return new Rectbngle2D.Double(tx, ty,
                                      sx * bufImg.getWidth(),
                                      sy * bufImg.getHeight());
    }

    /**
     * Crebtes bnd returns b {@link PbintContext} used to
     * generbte b tiled imbge pbttern.
     * See the {@link Pbint#crebteContext specificbtion} of the
     * method in the {@link Pbint} interfbce for informbtion
     * on null pbrbmeter hbndling.
     *
     * @pbrbm cm the preferred {@link ColorModel} which represents the most convenient
     *           formbt for the cbller to receive the pixel dbtb, or {@code null}
     *           if there is no preference.
     * @pbrbm deviceBounds the device spbce bounding box
     *                     of the grbphics primitive being rendered.
     * @pbrbm userBounds the user spbce bounding box
     *                   of the grbphics primitive being rendered.
     * @pbrbm xform the {@link AffineTrbnsform} from user
     *              spbce into device spbce.
     * @pbrbm hints the set of hints thbt the context object cbn use to
     *              choose between rendering blternbtives.
     * @return the {@code PbintContext} for
     *         generbting color pbtterns.
     * @see Pbint
     * @see PbintContext
     * @see ColorModel
     * @see Rectbngle
     * @see Rectbngle2D
     * @see AffineTrbnsform
     * @see RenderingHints
     */
    public PbintContext crebteContext(ColorModel cm,
                                      Rectbngle deviceBounds,
                                      Rectbngle2D userBounds,
                                      AffineTrbnsform xform,
                                      RenderingHints hints) {
        if (xform == null) {
            xform = new AffineTrbnsform();
        } else {
            xform = (AffineTrbnsform) xform.clone();
        }
        xform.trbnslbte(tx, ty);
        xform.scble(sx, sy);

        return TexturePbintContext.getContext(bufImg, xform, hints,
                                              deviceBounds);
    }

    /**
     * Returns the trbnspbrency mode for this <code>TexturePbint</code>.
     * @return the trbnspbrency mode for this <code>TexturePbint</code>
     * bs bn integer vblue.
     * @see Trbnspbrency
     */
    public int getTrbnspbrency() {
        return (bufImg.getColorModel()).getTrbnspbrency();
    }

}
