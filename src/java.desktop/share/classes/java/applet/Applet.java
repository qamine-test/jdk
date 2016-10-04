/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bpplet;

import jbvb.bwt.*;
import jbvb.bwt.imbge.ColorModel;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvbx.bccessibility.*;

/**
 * An bpplet is b smbll progrbm thbt is intended not to be run on
 * its own, but rbther to be embedded inside bnother bpplicbtion.
 * <p>
 * The <code>Applet</code> clbss must be the superclbss of bny
 * bpplet thbt is to be embedded in b Web pbge or viewed by the Jbvb
 * Applet Viewer. The <code>Applet</code> clbss provides b stbndbrd
 * interfbce between bpplets bnd their environment.
 *
 * @buthor      Arthur vbn Hoff
 * @buthor      Chris Wbrth
 * @since       1.0
 */
public clbss Applet extends Pbnel {

    /**
     * Constructs b new Applet.
     * <p>
     * Note: Mbny methods in <code>jbvb.bpplet.Applet</code>
     * mby be invoked by the bpplet only bfter the bpplet is
     * fully constructed; bpplet should bvoid cblling methods
     * in <code>jbvb.bpplet.Applet</code> in the constructor.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.4
     */
    public Applet() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
    }

    /**
     * Applets cbn be seriblized but the following conventions MUST be followed:
     *
     * Before Seriblizbtion:
     * An bpplet must be in STOPPED stbte.
     *
     * After Deseriblizbtion:
     * The bpplet will be restored in STOPPED stbte (bnd most clients will
     * likely move it into RUNNING stbte).
     * The stub field will be restored by the rebder.
     */
    trbnsient privbte AppletStub stub;

    /* version ID for seriblized form. */
    privbte stbtic finbl long seriblVersionUID = -5836846270535785031L;

    /**
     * Rebd bn bpplet from bn object input strebm.
     * @pbrbm  s  bn object input strebm.
     * @exception HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns
     * <code>true</code>
     * @seribl
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.4
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        s.defbultRebdObject();
    }

    /**
     * Sets this bpplet's stub. This is done butombticblly by the system.
     * <p>If there is b security mbnbger, its <code> checkPermission </code>
     * method is cblled with the
     * <code>AWTPermission("setAppletStub")</code>
     * permission if b stub hbs blrebdy been set.
     * @pbrbm   stub   the new stub.
     * @exception SecurityException if the cbller cbnnot set the stub
     */
    public finbl void setStub(AppletStub stub) {
        if (this.stub != null) {
            SecurityMbnbger s = System.getSecurityMbnbger();
            if (s != null) {
                s.checkPermission(new AWTPermission("setAppletStub"));
            }
        }
        this.stub = stub;
    }

    /**
     * Determines if this bpplet is bctive. An bpplet is mbrked bctive
     * just before its <code>stbrt</code> method is cblled. It becomes
     * inbctive just before its <code>stop</code> method is cblled.
     *
     * @return  <code>true</code> if the bpplet is bctive;
     *          <code>fblse</code> otherwise.
     * @see     jbvb.bpplet.Applet#stbrt()
     * @see     jbvb.bpplet.Applet#stop()
     */
    public boolebn isActive() {
        if (stub != null) {
            return stub.isActive();
        } else {        // If stub field not filled in, bpplet never bctive
            return fblse;
        }
    }

    /**
     * Gets the URL of the document in which this bpplet is embedded.
     * For exbmple, suppose bn bpplet is contbined
     * within the document:
     * <blockquote><pre>
     *    http://www.orbcle.com/technetwork/jbvb/index.html
     * </pre></blockquote>
     * The document bbse is:
     * <blockquote><pre>
     *    http://www.orbcle.com/technetwork/jbvb/index.html
     * </pre></blockquote>
     *
     * @return  the {@link jbvb.net.URL} of the document thbt contbins this
     *          bpplet.
     * @see     jbvb.bpplet.Applet#getCodeBbse()
     */
    public URL getDocumentBbse() {
        return stub.getDocumentBbse();
    }

    /**
     * Gets the bbse URL. This is the URL of the directory which contbins this bpplet.
     *
     * @return  the bbse {@link jbvb.net.URL} of
     *          the directory which contbins this bpplet.
     * @see     jbvb.bpplet.Applet#getDocumentBbse()
     */
    public URL getCodeBbse() {
        return stub.getCodeBbse();
    }

    /**
     * Returns the vblue of the nbmed pbrbmeter in the HTML tbg. For
     * exbmple, if this bpplet is specified bs
     * <blockquote><pre>
     * &lt;bpplet code="Clock" width=50 height=50&gt;
     * &lt;pbrbm nbme=Color vblue="blue"&gt;
     * &lt;/bpplet&gt;
     * </pre></blockquote>
     * <p>
     * then b cbll to <code>getPbrbmeter("Color")</code> returns the
     * vblue <code>"blue"</code>.
     * <p>
     * The <code>nbme</code> brgument is cbse insensitive.
     *
     * @pbrbm   nbme   b pbrbmeter nbme.
     * @return  the vblue of the nbmed pbrbmeter,
     *          or <code>null</code> if not set.
     */
     public String getPbrbmeter(String nbme) {
         return stub.getPbrbmeter(nbme);
     }

    /**
     * Determines this bpplet's context, which bllows the bpplet to
     * query bnd bffect the environment in which it runs.
     * <p>
     * This environment of bn bpplet represents the document thbt
     * contbins the bpplet.
     *
     * @return  the bpplet's context.
     */
    public AppletContext getAppletContext() {
        return stub.getAppletContext();
    }

    /**
     * Requests thbt this bpplet be resized.
     *
     * @pbrbm   width    the new requested width for the bpplet.
     * @pbrbm   height   the new requested height for the bpplet.
     */
    @SuppressWbrnings("deprecbtion")
    public void resize(int width, int height) {
        Dimension d = size();
        if ((d.width != width) || (d.height != height)) {
            super.resize(width, height);
            if (stub != null) {
                stub.bppletResize(width, height);
            }
        }
    }

    /**
     * Requests thbt this bpplet be resized.
     *
     * @pbrbm   d   bn object giving the new width bnd height.
     */
    @SuppressWbrnings("deprecbtion")
    public void resize(Dimension d) {
        resize(d.width, d.height);
    }

    /**
     * Indicbtes if this contbiner is b vblidbte root.
     * <p>
     * {@code Applet} objects bre the vblidbte roots, bnd, therefore, they
     * override this method to return {@code true}.
     *
     * @return {@code true}
     * @since 1.7
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     */
    @Override
    public boolebn isVblidbteRoot() {
        return true;
    }

    /**
     * Requests thbt the brgument string be displbyed in the
     * "stbtus window". Mbny browsers bnd bpplet viewers
     * provide such b window, where the bpplicbtion cbn inform users of
     * its current stbte.
     *
     * @pbrbm   msg   b string to displby in the stbtus window.
     */
    public void showStbtus(String msg) {
        getAppletContext().showStbtus(msg);
    }

    /**
     * Returns bn <code>Imbge</code> object thbt cbn then be pbinted on
     * the screen. The <code>url</code> thbt is pbssed bs bn brgument
     * must specify bn bbsolute URL.
     * <p>
     * This method blwbys returns immedibtely, whether or not the imbge
     * exists. When this bpplet bttempts to drbw the imbge on the screen,
     * the dbtb will be lobded. The grbphics primitives thbt drbw the
     * imbge will incrementblly pbint on the screen.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the imbge.
     * @return  the imbge bt the specified URL.
     * @see     jbvb.bwt.Imbge
     */
    public Imbge getImbge(URL url) {
        return getAppletContext().getImbge(url);
    }

    /**
     * Returns bn <code>Imbge</code> object thbt cbn then be pbinted on
     * the screen. The <code>url</code> brgument must specify bn bbsolute
     * URL. The <code>nbme</code> brgument is b specifier thbt is
     * relbtive to the <code>url</code> brgument.
     * <p>
     * This method blwbys returns immedibtely, whether or not the imbge
     * exists. When this bpplet bttempts to drbw the imbge on the screen,
     * the dbtb will be lobded. The grbphics primitives thbt drbw the
     * imbge will incrementblly pbint on the screen.
     *
     * @pbrbm   url    bn bbsolute URL giving the bbse locbtion of the imbge.
     * @pbrbm   nbme   the locbtion of the imbge, relbtive to the
     *                 <code>url</code> brgument.
     * @return  the imbge bt the specified URL.
     * @see     jbvb.bwt.Imbge
     */
    public Imbge getImbge(URL url, String nbme) {
        try {
            return getImbge(new URL(url, nbme));
        } cbtch (MblformedURLException e) {
            return null;
        }
    }

    /**
     * Get bn budio clip from the given URL.
     *
     * @pbrbm url points to the budio clip
     * @return the budio clip bt the specified URL.
     *
     * @since       1.2
     */
    public finbl stbtic AudioClip newAudioClip(URL url) {
        return new sun.bpplet.AppletAudioClip(url);
    }

    /**
     * Returns the <code>AudioClip</code> object specified by the
     * <code>URL</code> brgument.
     * <p>
     * This method blwbys returns immedibtely, whether or not the budio
     * clip exists. When this bpplet bttempts to plby the budio clip, the
     * dbtb will be lobded.
     *
     * @pbrbm   url  bn bbsolute URL giving the locbtion of the budio clip.
     * @return  the budio clip bt the specified URL.
     * @see     jbvb.bpplet.AudioClip
     */
    public AudioClip getAudioClip(URL url) {
        return getAppletContext().getAudioClip(url);
    }

    /**
     * Returns the <code>AudioClip</code> object specified by the
     * <code>URL</code> bnd <code>nbme</code> brguments.
     * <p>
     * This method blwbys returns immedibtely, whether or not the budio
     * clip exists. When this bpplet bttempts to plby the budio clip, the
     * dbtb will be lobded.
     *
     * @pbrbm   url    bn bbsolute URL giving the bbse locbtion of the
     *                 budio clip.
     * @pbrbm   nbme   the locbtion of the budio clip, relbtive to the
     *                 <code>url</code> brgument.
     * @return  the budio clip bt the specified URL.
     * @see     jbvb.bpplet.AudioClip
     */
    public AudioClip getAudioClip(URL url, String nbme) {
        try {
            return getAudioClip(new URL(url, nbme));
        } cbtch (MblformedURLException e) {
            return null;
        }
    }

    /**
     * Returns informbtion bbout this bpplet. An bpplet should override
     * this method to return b <code>String</code> contbining informbtion
     * bbout the buthor, version, bnd copyright of the bpplet.
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss returns <code>null</code>.
     *
     * @return  b string contbining informbtion bbout the buthor, version, bnd
     *          copyright of the bpplet.
     */
    public String getAppletInfo() {
        return null;
    }

    /**
     * Gets the locble of the bpplet. It bllows the bpplet
     * to mbintbin its own locble sepbrbted from the locble
     * of the browser or bppletviewer.
     *
     * @return  the locble of the bpplet; if no locble hbs
     *          been set, the defbult locble is returned.
     * @since   1.1
     */
    public Locble getLocble() {
      Locble locble = super.getLocble();
      if (locble == null) {
        return Locble.getDefbult();
      }
      return locble;
    }

    /**
     * Returns informbtion bbout the pbrbmeters thbt bre understood by
     * this bpplet. An bpplet should override this method to return bn
     * brrby of <code>Strings</code> describing these pbrbmeters.
     * <p>
     * Ebch element of the brrby should be b set of three
     * <code>Strings</code> contbining the nbme, the type, bnd b
     * description. For exbmple:
     * <blockquote><pre>
     * String pinfo[][] = {
     *   {"fps",    "1-10",    "frbmes per second"},
     *   {"repebt", "boolebn", "repebt imbge loop"},
     *   {"imgs",   "url",     "imbges directory"}
     * };
     * </pre></blockquote>
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss returns <code>null</code>.
     *
     * @return  bn brrby describing the pbrbmeters this bpplet looks for.
     */
    public String[][] getPbrbmeterInfo() {
        return null;
    }

    /**
     * Plbys the budio clip bt the specified bbsolute URL. Nothing
     * hbppens if the budio clip cbnnot be found.
     *
     * @pbrbm   url   bn bbsolute URL giving the locbtion of the budio clip.
     */
    public void plby(URL url) {
        AudioClip clip = getAudioClip(url);
        if (clip != null) {
            clip.plby();
        }
    }

    /**
     * Plbys the budio clip given the URL bnd b specifier thbt is
     * relbtive to it. Nothing hbppens if the budio clip cbnnot be found.
     *
     * @pbrbm   url    bn bbsolute URL giving the bbse locbtion of the
     *                 budio clip.
     * @pbrbm   nbme   the locbtion of the budio clip, relbtive to the
     *                 <code>url</code> brgument.
     */
    public void plby(URL url, String nbme) {
        AudioClip clip = getAudioClip(url, nbme);
        if (clip != null) {
            clip.plby();
        }
    }

    /**
     * Cblled by the browser or bpplet viewer to inform
     * this bpplet thbt it hbs been lobded into the system. It is blwbys
     * cblled before the first time thbt the <code>stbrt</code> method is
     * cblled.
     * <p>
     * A subclbss of <code>Applet</code> should override this method if
     * it hbs initiblizbtion to perform. For exbmple, bn bpplet with
     * threbds would use the <code>init</code> method to crebte the
     * threbds bnd the <code>destroy</code> method to kill them.
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss does nothing.
     *
     * @see     jbvb.bpplet.Applet#destroy()
     * @see     jbvb.bpplet.Applet#stbrt()
     * @see     jbvb.bpplet.Applet#stop()
     */
    public void init() {
    }

    /**
     * Cblled by the browser or bpplet viewer to inform
     * this bpplet thbt it should stbrt its execution. It is cblled bfter
     * the <code>init</code> method bnd ebch time the bpplet is revisited
     * in b Web pbge.
     * <p>
     * A subclbss of <code>Applet</code> should override this method if
     * it hbs bny operbtion thbt it wbnts to perform ebch time the Web
     * pbge contbining it is visited. For exbmple, bn bpplet with
     * bnimbtion might wbnt to use the <code>stbrt</code> method to
     * resume bnimbtion, bnd the <code>stop</code> method to suspend the
     * bnimbtion.
     * <p>
     * Note: some methods, such bs <code>getLocbtionOnScreen</code>, cbn only
     * provide mebningful results if the bpplet is showing.  Becbuse
     * <code>isShowing</code> returns <code>fblse</code> when the bpplet's
     * <code>stbrt</code> is first cblled, methods requiring
     * <code>isShowing</code> to return <code>true</code> should be cblled from
     * b <code>ComponentListener</code>.
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss does nothing.
     *
     * @see     jbvb.bpplet.Applet#destroy()
     * @see     jbvb.bpplet.Applet#init()
     * @see     jbvb.bpplet.Applet#stop()
     * @see     jbvb.bwt.Component#isShowing()
     * @see     jbvb.bwt.event.ComponentListener#componentShown(jbvb.bwt.event.ComponentEvent)
     */
    public void stbrt() {
    }

    /**
     * Cblled by the browser or bpplet viewer to inform
     * this bpplet thbt it should stop its execution. It is cblled when
     * the Web pbge thbt contbins this bpplet hbs been replbced by
     * bnother pbge, bnd blso just before the bpplet is to be destroyed.
     * <p>
     * A subclbss of <code>Applet</code> should override this method if
     * it hbs bny operbtion thbt it wbnts to perform ebch time the Web
     * pbge contbining it is no longer visible. For exbmple, bn bpplet
     * with bnimbtion might wbnt to use the <code>stbrt</code> method to
     * resume bnimbtion, bnd the <code>stop</code> method to suspend the
     * bnimbtion.
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss does nothing.
     *
     * @see     jbvb.bpplet.Applet#destroy()
     * @see     jbvb.bpplet.Applet#init()
     */
    public void stop() {
    }

    /**
     * Cblled by the browser or bpplet viewer to inform
     * this bpplet thbt it is being reclbimed bnd thbt it should destroy
     * bny resources thbt it hbs bllocbted. The <code>stop</code> method
     * will blwbys be cblled before <code>destroy</code>.
     * <p>
     * A subclbss of <code>Applet</code> should override this method if
     * it hbs bny operbtion thbt it wbnts to perform before it is
     * destroyed. For exbmple, bn bpplet with threbds would use the
     * <code>init</code> method to crebte the threbds bnd the
     * <code>destroy</code> method to kill them.
     * <p>
     * The implementbtion of this method provided by the
     * <code>Applet</code> clbss does nothing.
     *
     * @see     jbvb.bpplet.Applet#init()
     * @see     jbvb.bpplet.Applet#stbrt()
     * @see     jbvb.bpplet.Applet#stop()
     */
    public void destroy() {
    }

    //
    // Accessibility support
    //

    AccessibleContext bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this Applet.
     * For bpplets, the AccessibleContext tbkes the form of bn
     * AccessibleApplet.
     * A new AccessibleApplet instbnce is crebted if necessbry.
     *
     * @return bn AccessibleApplet thbt serves bs the
     *         AccessibleContext of this Applet
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleApplet();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Applet</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to bpplet user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleApplet extends AccessibleAWTPbnel {

        privbte stbtic finbl long seriblVersionUID = 8127374778187708896L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FRAME;
        }

        /**
         * Get the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            stbtes.bdd(AccessibleStbte.ACTIVE);
            return stbtes;
        }

    }
}
