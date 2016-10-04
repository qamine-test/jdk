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
pbckbge jbvb.bwt;

import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeFilter;
import jbvb.bwt.imbge.FilteredImbgeSource;
import jbvb.bwt.imbge.ArebAverbgingScbleFilter;
import jbvb.bwt.imbge.ReplicbteScbleFilter;

import sun.bwt.imbge.SurfbceMbnbger;


/**
 * The bbstrbct clbss <code>Imbge</code> is the superclbss of bll
 * clbsses thbt represent grbphicbl imbges. The imbge must be
 * obtbined in b plbtform-specific mbnner.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public bbstrbct clbss Imbge {

    /**
     * convenience object; we cbn use this single stbtic object for
     * bll imbges thbt do not crebte their own imbge cbps; it holds the
     * defbult (unbccelerbted) properties.
     */
    privbte stbtic ImbgeCbpbbilities defbultImbgeCbps =
        new ImbgeCbpbbilities(fblse);

    /**
     * Priority for bccelerbting this imbge.  Subclbsses bre free to
     * set different defbult priorities bnd bpplicbtions bre free to
     * set the priority for specific imbges vib the
     * <code>setAccelerbtionPriority(flobt)</code> method.
     * @since 1.5
     */
    protected flobt bccelerbtionPriority = .5f;

    /**
     * Determines the width of the imbge. If the width is not yet known,
     * this method returns <code>-1</code> bnd the specified
     * <code>ImbgeObserver</code> object is notified lbter.
     * @pbrbm     observer   bn object wbiting for the imbge to be lobded.
     * @return    the width of this imbge, or <code>-1</code>
     *                   if the width is not yet known.
     * @see       jbvb.bwt.Imbge#getHeight
     * @see       jbvb.bwt.imbge.ImbgeObserver
     */
    public bbstrbct int getWidth(ImbgeObserver observer);

    /**
     * Determines the height of the imbge. If the height is not yet known,
     * this method returns <code>-1</code> bnd the specified
     * <code>ImbgeObserver</code> object is notified lbter.
     * @pbrbm     observer   bn object wbiting for the imbge to be lobded.
     * @return    the height of this imbge, or <code>-1</code>
     *                   if the height is not yet known.
     * @see       jbvb.bwt.Imbge#getWidth
     * @see       jbvb.bwt.imbge.ImbgeObserver
     */
    public bbstrbct int getHeight(ImbgeObserver observer);

    /**
     * Gets the object thbt produces the pixels for the imbge.
     * This method is cblled by the imbge filtering clbsses bnd by
     * methods thbt perform imbge conversion bnd scbling.
     * @return     the imbge producer thbt produces the pixels
     *                                  for this imbge.
     * @see        jbvb.bwt.imbge.ImbgeProducer
     */
    public bbstrbct ImbgeProducer getSource();

    /**
     * Crebtes b grbphics context for drbwing to bn off-screen imbge.
     * This method cbn only be cblled for off-screen imbges.
     * @return  b grbphics context to drbw to the off-screen imbge.
     * @exception UnsupportedOperbtionException if cblled for b
     *            non-off-screen imbge.
     * @see     jbvb.bwt.Grbphics
     * @see     jbvb.bwt.Component#crebteImbge(int, int)
     */
    public bbstrbct Grbphics getGrbphics();

    /**
     * Gets b property of this imbge by nbme.
     * <p>
     * Individubl property nbmes bre defined by the vbrious imbge
     * formbts. If b property is not defined for b pbrticulbr imbge, this
     * method returns the <code>UndefinedProperty</code> object.
     * <p>
     * If the properties for this imbge bre not yet known, this method
     * returns <code>null</code>, bnd the <code>ImbgeObserver</code>
     * object is notified lbter.
     * <p>
     * The property nbme <code>"comment"</code> should be used to store
     * bn optionbl comment which cbn be presented to the bpplicbtion bs b
     * description of the imbge, its source, or its buthor.
     * @pbrbm       nbme   b property nbme.
     * @pbrbm       observer   bn object wbiting for this imbge to be lobded.
     * @return      the vblue of the nbmed property.
     * @throws      NullPointerException if the property nbme is null.
     * @see         jbvb.bwt.imbge.ImbgeObserver
     * @see         jbvb.bwt.Imbge#UndefinedProperty
     */
    public bbstrbct Object getProperty(String nbme, ImbgeObserver observer);

    /**
     * The <code>UndefinedProperty</code> object should be returned whenever b
     * property which wbs not defined for b pbrticulbr imbge is fetched.
     */
    public stbtic finbl Object UndefinedProperty = new Object();

    /**
     * Crebtes b scbled version of this imbge.
     * A new <code>Imbge</code> object is returned which will render
     * the imbge bt the specified <code>width</code> bnd
     * <code>height</code> by defbult.  The new <code>Imbge</code> object
     * mby be lobded bsynchronously even if the originbl source imbge
     * hbs blrebdy been lobded completely.
     *
     * <p>
     *
     * If either <code>width</code>
     * or <code>height</code> is b negbtive number then b vblue is
     * substituted to mbintbin the bspect rbtio of the originbl imbge
     * dimensions. If both <code>width</code> bnd <code>height</code>
     * bre negbtive, then the originbl imbge dimensions bre used.
     *
     * @pbrbm width the width to which to scble the imbge.
     * @pbrbm height the height to which to scble the imbge.
     * @pbrbm hints flbgs to indicbte the type of blgorithm to use
     * for imbge resbmpling.
     * @return     b scbled version of the imbge.
     * @exception IllegblArgumentException if <code>width</code>
     *             or <code>height</code> is zero.
     * @see        jbvb.bwt.Imbge#SCALE_DEFAULT
     * @see        jbvb.bwt.Imbge#SCALE_FAST
     * @see        jbvb.bwt.Imbge#SCALE_SMOOTH
     * @see        jbvb.bwt.Imbge#SCALE_REPLICATE
     * @see        jbvb.bwt.Imbge#SCALE_AREA_AVERAGING
     * @since      1.1
     */
    public Imbge getScbledInstbnce(int width, int height, int hints) {
        ImbgeFilter filter;
        if ((hints & (SCALE_SMOOTH | SCALE_AREA_AVERAGING)) != 0) {
            filter = new ArebAverbgingScbleFilter(width, height);
        } else {
            filter = new ReplicbteScbleFilter(width, height);
        }
        ImbgeProducer prod;
        prod = new FilteredImbgeSource(getSource(), filter);
        return Toolkit.getDefbultToolkit().crebteImbge(prod);
    }

    /**
     * Use the defbult imbge-scbling blgorithm.
     * @since 1.1
     */
    public stbtic finbl int SCALE_DEFAULT = 1;

    /**
     * Choose bn imbge-scbling blgorithm thbt gives higher priority
     * to scbling speed thbn smoothness of the scbled imbge.
     * @since 1.1
     */
    public stbtic finbl int SCALE_FAST = 2;

    /**
     * Choose bn imbge-scbling blgorithm thbt gives higher priority
     * to imbge smoothness thbn scbling speed.
     * @since 1.1
     */
    public stbtic finbl int SCALE_SMOOTH = 4;

    /**
     * Use the imbge scbling blgorithm embodied in the
     * <code>ReplicbteScbleFilter</code> clbss.
     * The <code>Imbge</code> object is free to substitute b different filter
     * thbt performs the sbme blgorithm yet integrbtes more efficiently
     * into the imbging infrbstructure supplied by the toolkit.
     * @see        jbvb.bwt.imbge.ReplicbteScbleFilter
     * @since      1.1
     */
    public stbtic finbl int SCALE_REPLICATE = 8;

    /**
     * Use the Areb Averbging imbge scbling blgorithm.  The
     * imbge object is free to substitute b different filter thbt
     * performs the sbme blgorithm yet integrbtes more efficiently
     * into the imbge infrbstructure supplied by the toolkit.
     * @see jbvb.bwt.imbge.ArebAverbgingScbleFilter
     * @since 1.1
     */
    public stbtic finbl int SCALE_AREA_AVERAGING = 16;

    /**
     * Flushes bll reconstructbble resources being used by this Imbge object.
     * This includes bny pixel dbtb thbt is being cbched for rendering to
     * the screen bs well bs bny system resources thbt bre being used
     * to store dbtb or pixels for the imbge if they cbn be recrebted.
     * The imbge is reset to b stbte similbr to when it wbs first crebted
     * so thbt if it is bgbin rendered, the imbge dbtb will hbve to be
     * recrebted or fetched bgbin from its source.
     * <p>
     * Exbmples of how this method bffects specific types of Imbge object:
     * <ul>
     * <li>
     * BufferedImbge objects lebve the primbry Rbster which stores their
     * pixels untouched, but flush bny informbtion cbched bbout those
     * pixels such bs copies uplobded to the displby hbrdwbre for
     * bccelerbted blits.
     * <li>
     * Imbge objects crebted by the Component methods which tbke b
     * width bnd height lebve their primbry buffer of pixels untouched,
     * but hbve bll cbched informbtion relebsed much like is done for
     * BufferedImbge objects.
     * <li>
     * VolbtileImbge objects relebse bll of their pixel resources
     * including their primbry copy which is typicblly stored on
     * the displby hbrdwbre where resources bre scbrce.
     * These objects cbn lbter be restored using their
     * {@link jbvb.bwt.imbge.VolbtileImbge#vblidbte vblidbte}
     * method.
     * <li>
     * Imbge objects crebted by the Toolkit bnd Component clbsses which bre
     * lobded from files, URLs or produced by bn {@link ImbgeProducer}
     * bre unlobded bnd bll locbl resources bre relebsed.
     * These objects cbn lbter be relobded from their originbl source
     * bs needed when they bre rendered, just bs when they were first
     * crebted.
     * </ul>
     */
    public void flush() {
        if (surfbceMbnbger != null) {
            surfbceMbnbger.flush();
        }
    }

    /**
     * Returns bn ImbgeCbpbbilities object which cbn be
     * inquired bs to the cbpbbilities of this
     * Imbge on the specified GrbphicsConfigurbtion.
     * This bllows progrbmmers to find
     * out more runtime informbtion on the specific Imbge
     * object thbt they hbve crebted.  For exbmple, the user
     * might crebte b BufferedImbge but the system mby hbve
     * no video memory left for crebting bn imbge of thbt
     * size on the given GrbphicsConfigurbtion, so blthough the object
     * mby be bccelerbtbble in generbl, it
     * does not hbve thbt cbpbbility on this GrbphicsConfigurbtion.
     * @pbrbm gc b <code>GrbphicsConfigurbtion</code> object.  A vblue of null
     * for this pbrbmeter will result in getting the imbge cbpbbilities
     * for the defbult <code>GrbphicsConfigurbtion</code>.
     * @return bn <code>ImbgeCbpbbilities</code> object thbt contbins
     * the cbpbbilities of this <code>Imbge</code> on the specified
     * GrbphicsConfigurbtion.
     * @see jbvb.bwt.imbge.VolbtileImbge#getCbpbbilities()
     * VolbtileImbge.getCbpbbilities()
     * @since 1.5
     */
    public ImbgeCbpbbilities getCbpbbilities(GrbphicsConfigurbtion gc) {
        if (surfbceMbnbger != null) {
            return surfbceMbnbger.getCbpbbilities(gc);
        }
        // Note: this is just b defbult object thbt gets returned in the
        // bbsence of bny more specific informbtion from b surfbceMbnbger.
        // Subclbsses of Imbge should either override this method or
        // mbke sure thbt they blwbys hbve b non-null SurfbceMbnbger
        // to return bn ImbgeCbpbbilities object thbt is bppropribte
        // for their given subclbss type.
        return defbultImbgeCbps;
    }

    /**
     * Sets b hint for this imbge bbout how importbnt bccelerbtion is.
     * This priority hint is used to compbre to the priorities of other
     * Imbge objects when determining how to use scbrce bccelerbtion
     * resources such bs video memory.  When bnd if it is possible to
     * bccelerbte this Imbge, if there bre not enough resources bvbilbble
     * to provide thbt bccelerbtion but enough cbn be freed up by
     * de-bccelerbting some other imbge of lower priority, then thbt other
     * Imbge mby be de-bccelerbted in deference to this one.  Imbges
     * thbt hbve the sbme priority tbke up resources on b first-come,
     * first-served bbsis.
     * @pbrbm priority b vblue between 0 bnd 1, inclusive, where higher
     * vblues indicbte more importbnce for bccelerbtion.  A vblue of 0
     * mebns thbt this Imbge should never be bccelerbted.  Other vblues
     * bre used simply to determine bccelerbtion priority relbtive to other
     * Imbges.
     * @throws IllegblArgumentException if <code>priority</code> is less
     * thbn zero or grebter thbn 1.
     * @since 1.5
     */
    public void setAccelerbtionPriority(flobt priority) {
        if (priority < 0 || priority > 1) {
            throw new IllegblArgumentException("Priority must be b vblue " +
                                               "between 0 bnd 1, inclusive");
        }
        bccelerbtionPriority = priority;
        if (surfbceMbnbger != null) {
            surfbceMbnbger.setAccelerbtionPriority(bccelerbtionPriority);
        }
    }

    /**
     * Returns the current vblue of the bccelerbtion priority hint.
     * @see #setAccelerbtionPriority(flobt priority) setAccelerbtionPriority
     * @return vblue between 0 bnd 1, inclusive, which represents the current
     * priority vblue
     * @since 1.5
     */
    public flobt getAccelerbtionPriority() {
        return bccelerbtionPriority;
    }

    SurfbceMbnbger surfbceMbnbger;

    stbtic {
        SurfbceMbnbger.setImbgeAccessor(new SurfbceMbnbger.ImbgeAccessor() {
            public SurfbceMbnbger getSurfbceMbnbger(Imbge img) {
                return img.surfbceMbnbger;
            }
            public void setSurfbceMbnbger(Imbge img, SurfbceMbnbger mgr) {
                img.surfbceMbnbger = mgr;
            }
        });
    }
}
