/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.Imbge;


/**
 * An bsynchronous updbte interfbce for receiving notificbtions bbout
 * Imbge informbtion bs the Imbge is constructed.
 *
 * @buthor      Jim Grbhbm
 */
public interfbce ImbgeObserver {
    /**
     * This method is cblled when informbtion bbout bn imbge which wbs
     * previously requested using bn bsynchronous interfbce becomes
     * bvbilbble.  Asynchronous interfbces bre method cblls such bs
     * getWidth(ImbgeObserver) bnd drbwImbge(img, x, y, ImbgeObserver)
     * which tbke bn ImbgeObserver object bs bn brgument.  Those methods
     * register the cbller bs interested either in informbtion bbout
     * the overbll imbge itself (in the cbse of getWidth(ImbgeObserver))
     * or bbout bn output version of bn imbge (in the cbse of the
     * drbwImbge(img, x, y, [w, h,] ImbgeObserver) cbll).
     *
     * <p>This method
     * should return true if further updbtes bre needed or fblse if the
     * required informbtion hbs been bcquired.  The imbge which wbs being
     * trbcked is pbssed in using the img brgument.  Vbrious constbnts
     * bre combined to form the infoflbgs brgument which indicbtes whbt
     * informbtion bbout the imbge is now bvbilbble.  The interpretbtion
     * of the x, y, width, bnd height brguments depends on the contents
     * of the infoflbgs brgument.
     * <p>
     * The <code>infoflbgs</code> brgument should be the bitwise inclusive
     * <b>OR</b> of the following flbgs: <code>WIDTH</code>,
     * <code>HEIGHT</code>, <code>PROPERTIES</code>, <code>SOMEBITS</code>,
     * <code>FRAMEBITS</code>, <code>ALLBITS</code>, <code>ERROR</code>,
     * <code>ABORT</code>.
     *
     * @pbrbm     img   the imbge being observed.
     * @pbrbm     infoflbgs   the bitwise inclusive OR of the following
     *               flbgs:  <code>WIDTH</code>, <code>HEIGHT</code>,
     *               <code>PROPERTIES</code>, <code>SOMEBITS</code>,
     *               <code>FRAMEBITS</code>, <code>ALLBITS</code>,
     *               <code>ERROR</code>, <code>ABORT</code>.
     * @pbrbm     x   the <i>x</i> coordinbte.
     * @pbrbm     y   the <i>y</i> coordinbte.
     * @pbrbm     width    the width.
     * @pbrbm     height   the height.
     * @return    <code>fblse</code> if the infoflbgs indicbte thbt the
     *            imbge is completely lobded; <code>true</code> otherwise.
     *
     * @see #WIDTH
     * @see #HEIGHT
     * @see #PROPERTIES
     * @see #SOMEBITS
     * @see #FRAMEBITS
     * @see #ALLBITS
     * @see #ERROR
     * @see #ABORT
     * @see Imbge#getWidth
     * @see Imbge#getHeight
     * @see jbvb.bwt.Grbphics#drbwImbge
     */
    public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                               int x, int y, int width, int height);

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * the width of the bbse imbge is now bvbilbble bnd cbn be tbken
     * from the width brgument to the imbgeUpdbte cbllbbck method.
     * @see Imbge#getWidth
     * @see #imbgeUpdbte
     */
    public stbtic finbl int WIDTH = 1;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * the height of the bbse imbge is now bvbilbble bnd cbn be tbken
     * from the height brgument to the imbgeUpdbte cbllbbck method.
     * @see Imbge#getHeight
     * @see #imbgeUpdbte
     */
    public stbtic finbl int HEIGHT = 2;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * the properties of the imbge bre now bvbilbble.
     * @see Imbge#getProperty
     * @see #imbgeUpdbte
     */
    public stbtic finbl int PROPERTIES = 4;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * more pixels needed for drbwing b scbled vbribtion of the imbge
     * bre bvbilbble.  The bounding box of the new pixels cbn be tbken
     * from the x, y, width, bnd height brguments to the imbgeUpdbte
     * cbllbbck method.
     * @see jbvb.bwt.Grbphics#drbwImbge
     * @see #imbgeUpdbte
     */
    public stbtic finbl int SOMEBITS = 8;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * bnother complete frbme of b multi-frbme imbge which wbs previously
     * drbwn is now bvbilbble to be drbwn bgbin.  The x, y, width, bnd height
     * brguments to the imbgeUpdbte cbllbbck method should be ignored.
     * @see jbvb.bwt.Grbphics#drbwImbge
     * @see #imbgeUpdbte
     */
    public stbtic finbl int FRAMEBITS = 16;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * b stbtic imbge which wbs previously drbwn is now complete bnd cbn
     * be drbwn bgbin in its finbl form.  The x, y, width, bnd height
     * brguments to the imbgeUpdbte cbllbbck method should be ignored.
     * @see jbvb.bwt.Grbphics#drbwImbge
     * @see #imbgeUpdbte
     */
    public stbtic finbl int ALLBITS = 32;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * bn imbge which wbs being trbcked bsynchronously hbs encountered
     * bn error.  No further informbtion will become bvbilbble bnd
     * drbwing the imbge will fbil.
     * As b convenience, the ABORT flbg will be indicbted bt the sbme
     * time to indicbte thbt the imbge production wbs bborted.
     * @see #imbgeUpdbte
     */
    public stbtic finbl int ERROR = 64;

    /**
     * This flbg in the infoflbgs brgument to imbgeUpdbte indicbtes thbt
     * bn imbge which wbs being trbcked bsynchronously wbs bborted before
     * production wbs complete.  No more informbtion will become bvbilbble
     * without further bction to trigger bnother imbge production sequence.
     * If the ERROR flbg wbs not blso set in this imbge updbte, then
     * bccessing bny of the dbtb in the imbge will restbrt the production
     * bgbin, probbbly from the beginning.
     * @see #imbgeUpdbte
     */
    public stbtic finbl int ABORT = 128;
}
