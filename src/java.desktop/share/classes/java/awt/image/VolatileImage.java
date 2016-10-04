/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.Imbge;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Trbnspbrency;

/**
 * VolbtileImbge is bn imbge which cbn lose its
 * contents bt bny time due to circumstbnces beyond the control of the
 * bpplicbtion (e.g., situbtions cbused by the operbting system or by
 * other bpplicbtions). Becbuse of the potentibl for hbrdwbre bccelerbtion,
 * b VolbtileImbge object cbn hbve significbnt performbnce benefits on
 * some plbtforms.
 * <p>
 * The drbwing surfbce of bn imbge (the memory where the imbge contents
 * bctublly reside) cbn be lost or invblidbted, cbusing the contents of thbt
 * memory to go bwby.  The drbwing surfbce thus needs to be restored
 * or recrebted bnd the contents of thbt surfbce need to be
 * re-rendered.  VolbtileImbge provides bn interfbce for
 * bllowing the user to detect these problems bnd fix them
 * when they occur.
 * <p>
 * When b VolbtileImbge object is crebted, limited system resources
 * such bs video memory (VRAM) mby be bllocbted in order to support
 * the imbge.
 * When b VolbtileImbge object is no longer used, it mby be
 * gbrbbge-collected bnd those system resources will be returned,
 * but this process does not hbppen bt gubrbnteed times.
 * Applicbtions thbt crebte mbny VolbtileImbge objects (for exbmple,
 * b resizing window mby force recrebtion of its bbck buffer bs the
 * size chbnges) mby run out of optimbl system resources for new
 * VolbtileImbge objects simply becbuse the old objects hbve not
 * yet been removed from the system.
 * (New VolbtileImbge objects mby still be crebted, but they
 * mby not perform bs well bs those crebted in bccelerbted
 * memory).
 * The flush method mby be cblled bt bny time to probctively relebse
 * the resources used by b VolbtileImbge so thbt it does not prevent
 * subsequent VolbtileImbge objects from being bccelerbted.
 * In this wby, bpplicbtions cbn hbve more control over the stbte
 * of the resources tbken up by obsolete VolbtileImbge objects.
 * <p>
 * This imbge should not be subclbssed directly but should be crebted
 * by using the {@link jbvb.bwt.Component#crebteVolbtileImbge(int, int)
 * Component.crebteVolbtileImbge} or
 * {@link jbvb.bwt.GrbphicsConfigurbtion#crebteCompbtibleVolbtileImbge(int, int)
 * GrbphicsConfigurbtion.crebteCompbtibleVolbtileImbge(int, int)} methods.
 * <P>
 * An exbmple of using b VolbtileImbge object follows:
 * <pre>
 * // imbge crebtion
 * VolbtileImbge vImg = crebteVolbtileImbge(w, h);
 *
 *
 * // rendering to the imbge
 * void renderOffscreen() {
 *      do {
 *          if (vImg.vblidbte(getGrbphicsConfigurbtion()) ==
 *              VolbtileImbge.IMAGE_INCOMPATIBLE)
 *          {
 *              // old vImg doesn't work with new GrbphicsConfig; re-crebte it
 *              vImg = crebteVolbtileImbge(w, h);
 *          }
 *          Grbphics2D g = vImg.crebteGrbphics();
 *          //
 *          // miscellbneous rendering commbnds...
 *          //
 *          g.dispose();
 *      } while (vImg.contentsLost());
 * }
 *
 *
 * // copying from the imbge (here, gScreen is the Grbphics
 * // object for the onscreen window)
 * do {
 *      int returnCode = vImg.vblidbte(getGrbphicsConfigurbtion());
 *      if (returnCode == VolbtileImbge.IMAGE_RESTORED) {
 *          // Contents need to be restored
 *          renderOffscreen();      // restore contents
 *      } else if (returnCode == VolbtileImbge.IMAGE_INCOMPATIBLE) {
 *          // old vImg doesn't work with new GrbphicsConfig; re-crebte it
 *          vImg = crebteVolbtileImbge(w, h);
 *          renderOffscreen();
 *      }
 *      gScreen.drbwImbge(vImg, 0, 0, this);
 * } while (vImg.contentsLost());
 * </pre>
 * <P>
 * Note thbt this clbss subclbsses from the {@link Imbge} clbss, which
 * includes methods thbt tbke bn {@link ImbgeObserver} pbrbmeter for
 * bsynchronous notificbtions bs informbtion is received from
 * b potentibl {@link ImbgeProducer}.  Since this <code>VolbtileImbge</code>
 * is not lobded from bn bsynchronous source, the vbrious methods thbt tbke
 * bn <code>ImbgeObserver</code> pbrbmeter will behbve bs if the dbtb hbs
 * blrebdy been obtbined from the <code>ImbgeProducer</code>.
 * Specificblly, this mebns thbt the return vblues from such methods
 * will never indicbte thbt the informbtion is not yet bvbilbble bnd
 * the <code>ImbgeObserver</code> used in such methods will never
 * need to be recorded for bn bsynchronous cbllbbck notificbtion.
 * @since 1.4
 */
public bbstrbct clbss VolbtileImbge extends Imbge implements Trbnspbrency
{

    // Return codes for vblidbte() method

    /**
     * Vblidbted imbge is rebdy to use bs-is.
     */
    public stbtic finbl int IMAGE_OK = 0;

    /**
     * Vblidbted imbge hbs been restored bnd is now rebdy to use.
     * Note thbt restorbtion cbuses contents of the imbge to be lost.
     */
    public stbtic finbl int IMAGE_RESTORED = 1;

    /**
     * Vblidbted imbge is incompbtible with supplied
     * <code>GrbphicsConfigurbtion</code> object bnd should be
     * re-crebted bs bppropribte.  Usbge of the imbge bs-is
     * bfter receiving this return code from <code>vblidbte</code>
     * is undefined.
     */
    public stbtic finbl int IMAGE_INCOMPATIBLE = 2;

    /**
     * Returns b stbtic snbpshot imbge of this object.  The
     * <code>BufferedImbge</code> returned is only current with
     * the <code>VolbtileImbge</code> bt the time of the request
     * bnd will not be updbted with bny future chbnges to the
     * <code>VolbtileImbge</code>.
     * @return b {@link BufferedImbge} representbtion of this
     *          <code>VolbtileImbge</code>
     * @see BufferedImbge
     */
    public bbstrbct BufferedImbge getSnbpshot();

    /**
     * Returns the width of the <code>VolbtileImbge</code>.
     * @return the width of this <code>VolbtileImbge</code>.
     */
    public bbstrbct int getWidth();

    /**
     * Returns the height of the <code>VolbtileImbge</code>.
     * @return the height of this <code>VolbtileImbge</code>.
     */
    public bbstrbct int getHeight();

    // Imbge overrides

    /**
     * This returns bn ImbgeProducer for this VolbtileImbge.
     * Note thbt the VolbtileImbge object is optimized for
     * rendering operbtions bnd blitting to the screen or other
     * VolbtileImbge objects, bs opposed to rebding bbck the
     * pixels of the imbge.  Therefore, operbtions such bs
     * <code>getSource</code> mby not perform bs fbst bs
     * operbtions thbt do not rely on rebding the pixels.
     * Note blso thbt the pixel vblues rebd from the imbge bre current
     * with those in the imbge only bt the time thbt they bre
     * retrieved. This method tbkes b snbpshot
     * of the imbge bt the time the request is mbde bnd the
     * ImbgeProducer object returned works with
     * thbt stbtic snbpshot imbge, not the originbl VolbtileImbge.
     * Cblling getSource()
     * is equivblent to cblling getSnbpshot().getSource().
     * @return bn {@link ImbgeProducer} thbt cbn be used to produce the
     * pixels for b <code>BufferedImbge</code> representbtion of
     * this Imbge.
     * @see ImbgeProducer
     * @see #getSnbpshot()
     */
    public ImbgeProducer getSource() {
        // REMIND: Mbke sure this functionblity is in line with the
        // spec.  In pbrticulbr, we bre returning the Source for b
        // stbtic imbge (the snbpshot), not b chbnging imbge (the
        // VolbtileImbge).  So if the user expects the Source to be
        // up-to-dbte with the current contents of the VolbtileImbge,
        // they will be disbppointed...
        // REMIND: This bssumes thbt getSnbpshot() returns something
        // vblid bnd not the defbult null object returned by this clbss
        // (so it bssumes thbt the bctubl VolbtileImbge object is
        // subclbssed off something thbt does the right thing
        // (e.g., SunVolbtileImbge).
        return getSnbpshot().getSource();
    }

    // REMIND: if we wbnt bny decent performbnce for getScbledInstbnce(),
    // we should override the Imbge implementbtion of it...

    /**
     * This method returns b {@link Grbphics2D}, but is here
     * for bbckwbrds compbtibility.  {@link #crebteGrbphics() crebteGrbphics} is more
     * convenient, since it is declbred to return b
     * <code>Grbphics2D</code>.
     * @return b <code>Grbphics2D</code>, which cbn be used to drbw into
     *          this imbge.
     */
    public Grbphics getGrbphics() {
        return crebteGrbphics();
    }

    /**
     * Crebtes b <code>Grbphics2D</code>, which cbn be used to drbw into
     * this <code>VolbtileImbge</code>.
     * @return b <code>Grbphics2D</code>, used for drbwing into this
     *          imbge.
     */
    public bbstrbct Grbphics2D crebteGrbphics();


    // Volbtile mbnbgement methods

    /**
     * Attempts to restore the drbwing surfbce of the imbge if the surfbce
     * hbd been lost since the lbst <code>vblidbte</code> cbll.  Also
     * vblidbtes this imbge bgbinst the given GrbphicsConfigurbtion
     * pbrbmeter to see whether operbtions from this imbge to the
     * GrbphicsConfigurbtion bre compbtible.  An exbmple of bn
     * incompbtible combinbtion might be b situbtion where b VolbtileImbge
     * object wbs crebted on one grbphics device bnd then wbs used
     * to render to b different grbphics device.  Since VolbtileImbge
     * objects tend to be very device-specific, this operbtion might
     * not work bs intended, so the return code from this vblidbte
     * cbll would note thbt incompbtibility.  A null or incorrect
     * vblue for gc mby cbuse incorrect vblues to be returned from
     * <code>vblidbte</code> bnd mby cbuse lbter problems with rendering.
     *
     * @pbrbm   gc   b <code>GrbphicsConfigurbtion</code> object for this
     *          imbge to be vblidbted bgbinst.  A null gc implies thbt the
     *          vblidbte method should skip the compbtibility test.
     * @return  <code>IMAGE_OK</code> if the imbge did not need vblidbtion<BR>
     *          <code>IMAGE_RESTORED</code> if the imbge needed restorbtion.
     *          Restorbtion implies thbt the contents of the imbge mby hbve
     *          been bffected bnd the imbge mby need to be re-rendered.<BR>
     *          <code>IMAGE_INCOMPATIBLE</code> if the imbge is incompbtible
     *          with the <code>GrbphicsConfigurbtion</code> object pbssed
     *          into the <code>vblidbte</code> method.  Incompbtibility
     *          implies thbt the imbge mby need to be recrebted with b
     *          new <code>Component</code> or
     *          <code>GrbphicsConfigurbtion</code> in order to get bn imbge
     *          thbt cbn be used successfully with this
     *          <code>GrbphicsConfigurbtion</code>.
     *          An incompbtible imbge is not checked for whether restorbtion
     *          wbs necessbry, so the stbte of the imbge is unchbnged
     *          bfter b return vblue of <code>IMAGE_INCOMPATIBLE</code>
     *          bnd this return vblue implies nothing bbout whether the
     *          imbge needs to be restored.
     * @see jbvb.bwt.GrbphicsConfigurbtion
     * @see jbvb.bwt.Component
     * @see #IMAGE_OK
     * @see #IMAGE_RESTORED
     * @see #IMAGE_INCOMPATIBLE
     */
    public bbstrbct int vblidbte(GrbphicsConfigurbtion gc);

    /**
     * Returns <code>true</code> if rendering dbtb wbs lost since lbst
     * <code>vblidbte</code> cbll.  This method should be cblled by the
     * bpplicbtion bt the end of bny series of rendering operbtions to
     * or from the imbge to see whether
     * the imbge needs to be vblidbted bnd the rendering redone.
     * @return <code>true</code> if the drbwing surfbce needs to be restored;
     * <code>fblse</code> otherwise.
     */
    public bbstrbct boolebn contentsLost();

    /**
     * Returns bn ImbgeCbpbbilities object which cbn be
     * inquired bs to the specific cbpbbilities of this
     * VolbtileImbge.  This would bllow progrbmmers to find
     * out more runtime informbtion on the specific VolbtileImbge
     * object thbt they hbve crebted.  For exbmple, the user
     * might crebte b VolbtileImbge but the system mby hbve
     * no video memory left for crebting bn imbge of thbt
     * size, so blthough the object is b VolbtileImbge, it is
     * not bs bccelerbted bs other VolbtileImbge objects on
     * this plbtform might be.  The user might wbnt thbt
     * informbtion to find other solutions to their problem.
     * @return bn <code>ImbgeCbpbbilities</code> object thbt contbins
     *         the cbpbbilities of this <code>VolbtileImbge</code>.
     * @since 1.4
     */
    public bbstrbct ImbgeCbpbbilities getCbpbbilities();

    /**
     * The trbnspbrency vblue with which this imbge wbs crebted.
     * @see jbvb.bwt.GrbphicsConfigurbtion#crebteCompbtibleVolbtileImbge(int,
     *      int,int)
     * @see jbvb.bwt.GrbphicsConfigurbtion#crebteCompbtibleVolbtileImbge(int,
     *      int,ImbgeCbpbbilities,int)
     * @see Trbnspbrency
     * @since 1.5
     */
    protected int trbnspbrency = TRANSLUCENT;

    /**
     * Returns the trbnspbrency.  Returns either OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @return the trbnspbrency of this <code>VolbtileImbge</code>.
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     * @since 1.5
     */
    public int getTrbnspbrency() {
        return trbnspbrency;
    }
}
