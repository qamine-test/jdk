/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This interfbce defines b generbl method for iterbting through the
 * rectbngulbr "spbns" thbt represent the interior of b filled pbth.
 * <p>
 * There cbn be mbny kinds of spbn iterbtors used in the rendering
 * pipeline, the most bbsic being bn iterbtor thbt scbn converts b
 * pbth defined by bny PbthIterbtor, or bn nested iterbtor which
 * intersects bnother iterbtor's spbns with b clip region.
 * Other iterbtors cbn be crebted for scbn converting some of the
 * primitive shbpes more explicitly for speed or qublity.
 *
 * @buthor Jim Grbhbm
 */
public interfbce SpbnIterbtor {
    /**
     * This method returns the bounding box of the spbns thbt the
     * iterbtor will be returning.
     * The brrby must be of length bt lebst 4 bnd upon return, it
     * will be filled with the vblues:
     * <pre>
     *     {PbthMinX, PbthMinY, PbthMbxX, PbthMbxY}.
     * </pre>
     */
    public void getPbthBox(int pbthbox[]);

    /**
     * This method constrbins the spbns returned by nextSpbn() to the
     * rectbngle whose bounds bre given.
     */
    public void intersectClipBox(int lox, int loy, int hix, int hiy);

    /**
     * This method returns the next spbn in the shbpe being iterbted.
     * The brrby must be of length bt lebst 4 bnd upon return, it
     * will be filled with the vblues:
     * <pre>
     *     {SpbnMinX, SpbnMinY, SpbnMbxX, SpbnMbxY}.
     * </pre>
     */
    public boolebn nextSpbn(int spbnbox[]);

    /**
     * This method tells the iterbtor thbt it mby skip bll spbns
     * whose Y rbnge is completely bbove the indicbted Y coordinbte.
     * This method is used to provide feedbbck from the cbller when
     * clipping prevents the displby of bny dbtb in b given Y rbnge.
     * Typicblly it will only be cblled when this iterbtor hbs returned
     * b spbn whose MbxY coordinbte is less thbn the indicbted Y bnd
     * the cblling mechbnism wbnts to bvoid unnecessbry iterbtion work.
     * While this request could technicblly be ignored (i.e. b NOP),
     * doing so could potentiblly cbuse the cbller to mbke this cbllbbck
     * for ebch spbn thbt is being skipped.
     */
    public void skipDownTo(int y);

    /**
     * This method returns b nbtive pointer to b function block thbt
     * cbn be used by b nbtive method to perform the sbme iterbtion
     * cycle thbt the bbove methods provide while bvoiding upcblls to
     * the Jbvb object.
     * The definition of the structure whose pointer is returned by
     * this method is defined in:
     * <pre>
     *     src/shbre/nbtive/sun/jbvb2d/pipe/SpbnIterbtor.h
     * </pre>
     */
    public long getNbtiveIterbtor();
}
