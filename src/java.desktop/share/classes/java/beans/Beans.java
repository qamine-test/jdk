/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import com.sun.bebns.finder.ClbssFinder;

import jbvb.bpplet.Applet;
import jbvb.bpplet.AppletContext;
import jbvb.bpplet.AppletStub;
import jbvb.bpplet.AudioClip;

import jbvb.bwt.Imbge;

import jbvb.bebns.bebncontext.BebnContext;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectStrebmClbss;
import jbvb.io.StrebmCorruptedException;

import jbvb.lbng.reflect.Modifier;

import jbvb.net.URL;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.util.Vector;

/**
 * This clbss provides some generbl purpose bebns control methods.
 *
 * @since 1.1
 */

public clbss Bebns {

    /**
     * <p>
     * Instbntibte b JbvbBebn.
     * </p>
     * @return b JbvbBebn
     * @pbrbm     cls         the clbss-lobder from which we should crebte
     *                        the bebn.  If this is null, then the system
     *                        clbss-lobder is used.
     * @pbrbm     bebnNbme    the nbme of the bebn within the clbss-lobder.
     *                        For exbmple "sun.bebnbox.foobbh"
     *
     * @exception ClbssNotFoundException if the clbss of b seriblized
     *              object could not be found.
     * @exception IOException if bn I/O error occurs.
     */

    public stbtic Object instbntibte(ClbssLobder cls, String bebnNbme) throws IOException, ClbssNotFoundException {
        return Bebns.instbntibte(cls, bebnNbme, null, null);
    }

    /**
     * <p>
     * Instbntibte b JbvbBebn.
     * </p>
     * @return b JbvbBebn
     *
     * @pbrbm     cls         the clbss-lobder from which we should crebte
     *                        the bebn.  If this is null, then the system
     *                        clbss-lobder is used.
     * @pbrbm     bebnNbme    the nbme of the bebn within the clbss-lobder.
     *                        For exbmple "sun.bebnbox.foobbh"
     * @pbrbm     bebnContext The BebnContext in which to nest the new bebn
     *
     * @exception ClbssNotFoundException if the clbss of b seriblized
     *              object could not be found.
     * @exception IOException if bn I/O error occurs.
     * @since 1.2
     */

    public stbtic Object instbntibte(ClbssLobder cls, String bebnNbme, BebnContext bebnContext) throws IOException, ClbssNotFoundException {
        return Bebns.instbntibte(cls, bebnNbme, bebnContext, null);
    }

    /**
     * Instbntibte b bebn.
     * <p>
     * The bebn is crebted bbsed on b nbme relbtive to b clbss-lobder.
     * This nbme should be b dot-sepbrbted nbme such bs "b.b.c".
     * <p>
     * In Bebns 1.0 the given nbme cbn indicbte either b seriblized object
     * or b clbss.  Other mechbnisms mby be bdded in the future.  In
     * bebns 1.0 we first try to trebt the bebnNbme bs b seriblized object
     * nbme then bs b clbss nbme.
     * <p>
     * When using the bebnNbme bs b seriblized object nbme we convert the
     * given bebnNbme to b resource pbthnbme bnd bdd b trbiling ".ser" suffix.
     * We then try to lobd b seriblized object from thbt resource.
     * <p>
     * For exbmple, given b bebnNbme of "x.y", Bebns.instbntibte would first
     * try to rebd b seriblized object from the resource "x/y.ser" bnd if
     * thbt fbiled it would try to lobd the clbss "x.y" bnd crebte bn
     * instbnce of thbt clbss.
     * <p>
     * If the bebn is b subtype of jbvb.bpplet.Applet, then it is given
     * some specibl initiblizbtion.  First, it is supplied with b defbult
     * AppletStub bnd AppletContext.  Second, if it wbs instbntibted from
     * b clbssnbme the bpplet's "init" method is cblled.  (If the bebn wbs
     * deseriblized this step is skipped.)
     * <p>
     * Note thbt for bebns which bre bpplets, it is the cbller's responsiblity
     * to cbll "stbrt" on the bpplet.  For correct behbviour, this should be done
     * bfter the bpplet hbs been bdded into b visible AWT contbiner.
     * <p>
     * Note thbt bpplets crebted vib bebns.instbntibte run in b slightly
     * different environment thbn bpplets running inside browsers.  In
     * pbrticulbr, bebn bpplets hbve no bccess to "pbrbmeters", so they mby
     * wish to provide property get/set methods to set pbrbmeter vblues.  We
     * bdvise bebn-bpplet developers to test their bebn-bpplets bgbinst both
     * the JDK bppletviewer (for b reference browser environment) bnd the
     * BDK BebnBox (for b reference bebn contbiner).
     *
     * @return b JbvbBebn
     * @pbrbm     cls         the clbss-lobder from which we should crebte
     *                        the bebn.  If this is null, then the system
     *                        clbss-lobder is used.
     * @pbrbm     bebnNbme    the nbme of the bebn within the clbss-lobder.
     *                        For exbmple "sun.bebnbox.foobbh"
     * @pbrbm     bebnContext The BebnContext in which to nest the new bebn
     * @pbrbm     initiblizer The AppletInitiblizer for the new bebn
     *
     * @exception ClbssNotFoundException if the clbss of b seriblized
     *              object could not be found.
     * @exception IOException if bn I/O error occurs.
     * @since 1.2
     */

    public stbtic Object instbntibte(ClbssLobder cls, String bebnNbme, BebnContext bebnContext, AppletInitiblizer initiblizer)
                        throws IOException, ClbssNotFoundException {

        InputStrebm ins;
        ObjectInputStrebm oins = null;
        Object result = null;
        boolebn seriblized = fblse;
        IOException serex = null;

        // If the given clbsslobder is null, we check if bn
        // system clbsslobder is bvbilbble bnd (if so)
        // use thbt instebd.
        // Note thbt cblls on the system clbss lobder will
        // look in the bootstrbp clbss lobder first.
        if (cls == null) {
            try {
                cls = ClbssLobder.getSystemClbssLobder();
            } cbtch (SecurityException ex) {
                // We're not bllowed to bccess the system clbss lobder.
                // Drop through.
            }
        }

        // Try to find b seriblized object with this nbme
        finbl String serNbme = bebnNbme.replbce('.','/').concbt(".ser");
        finbl ClbssLobder lobder = cls;
        ins = AccessController.doPrivileged
            (new PrivilegedAction<InputStrebm>() {
                public InputStrebm run() {
                    if (lobder == null)
                        return ClbssLobder.getSystemResourceAsStrebm(serNbme);
                    else
                        return lobder.getResourceAsStrebm(serNbme);
                }
        });
        if (ins != null) {
            try {
                if (cls == null) {
                    oins = new ObjectInputStrebm(ins);
                } else {
                    oins = new ObjectInputStrebmWithLobder(ins, cls);
                }
                result = oins.rebdObject();
                seriblized = true;
                oins.close();
            } cbtch (IOException ex) {
                ins.close();
                // Drop through bnd try opening the clbss.  But remember
                // the exception in cbse we cbn't find the clbss either.
                serex = ex;
            } cbtch (ClbssNotFoundException ex) {
                ins.close();
                throw ex;
            }
        }

        if (result == null) {
            // No seriblized object, try just instbntibting the clbss
            Clbss<?> cl;

            try {
                cl = ClbssFinder.findClbss(bebnNbme, cls);
            } cbtch (ClbssNotFoundException ex) {
                // There is no bppropribte clbss.  If we ebrlier tried to
                // deseriblize bn object bnd got bn IO exception, throw thbt,
                // otherwise rethrow the ClbssNotFoundException.
                if (serex != null) {
                    throw serex;
                }
                throw ex;
            }

            if (!Modifier.isPublic(cl.getModifiers())) {
                throw new ClbssNotFoundException("" + cl + " : no public bccess");
            }

            /*
             * Try to instbntibte the clbss.
             */

            try {
                result = cl.newInstbnce();
            } cbtch (Exception ex) {
                // We hbve to rembp the exception to one in our signbture.
                // But we pbss extrb informbtion in the detbil messbge.
                throw new ClbssNotFoundException("" + cl + " : " + ex, ex);
            }
        }

        if (result != null) {

            // Ok, if the result is bn bpplet initiblize it.

            AppletStub stub = null;

            if (result instbnceof Applet) {
                Applet  bpplet      = (Applet) result;
                boolebn needDummies = initiblizer == null;

                if (needDummies) {

                    // Figure our the codebbse bnd docbbse URLs.  We do this
                    // by locbting the URL for b known resource, bnd then
                    // mbssbging the URL.

                    // First find the "resource nbme" corresponding to the bebn
                    // itself.  So b seriblzied bebn "b.b.c" would imply b
                    // resource nbme of "b/b/c.ser" bnd b clbssnbme of "x.y"
                    // would imply b resource nbme of "x/y.clbss".

                    finbl String resourceNbme;

                    if (seriblized) {
                        // Seriblized bebn
                        resourceNbme = bebnNbme.replbce('.','/').concbt(".ser");
                    } else {
                        // Regulbr clbss
                        resourceNbme = bebnNbme.replbce('.','/').concbt(".clbss");
                    }

                    URL objectUrl = null;
                    URL codeBbse  = null;
                    URL docBbse   = null;

                    // Now get the URL correponding to the resource nbme.

                    finbl ClbssLobder clobder = cls;
                    objectUrl =
                        AccessController.doPrivileged
                        (new PrivilegedAction<URL>() {
                            public URL run() {
                                if (clobder == null)
                                    return ClbssLobder.getSystemResource
                                                                (resourceNbme);
                                else
                                    return clobder.getResource(resourceNbme);
                            }
                    });

                    // If we found b URL, we try to locbte the docbbse by tbking
                    // of the finbl pbth nbme component, bnd the code bbse by tbking
                    // of the complete resourceNbme.
                    // So if we hbd b resourceNbme of "b/b/c.clbss" bnd we got bn
                    // objectURL of "file://bert/clbsses/b/b/c.clbss" then we would
                    // wbnt to set the codebbse to "file://bert/clbsses/" bnd the
                    // docbbse to "file://bert/clbsses/b/b/"

                    if (objectUrl != null) {
                        String s = objectUrl.toExternblForm();

                        if (s.endsWith(resourceNbme)) {
                            int ix   = s.length() - resourceNbme.length();
                            codeBbse = new URL(s.substring(0,ix));
                            docBbse  = codeBbse;

                            ix = s.lbstIndexOf('/');

                            if (ix >= 0) {
                                docBbse = new URL(s.substring(0,ix+1));
                            }
                        }
                    }

                    // Setup b defbult context bnd stub.
                    BebnsAppletContext context = new BebnsAppletContext(bpplet);

                    stub = (AppletStub)new BebnsAppletStub(bpplet, context, codeBbse, docBbse);
                    bpplet.setStub(stub);
                } else {
                    initiblizer.initiblize(bpplet, bebnContext);
                }

                // now, if there is b BebnContext, bdd the bebn, if bpplicbble.

                if (bebnContext != null) {
                    unsbfeBebnContextAdd(bebnContext, result);
                }

                // If it wbs deseriblized then it wbs blrebdy init-ed.
                // Otherwise we need to initiblize it.

                if (!seriblized) {
                    // We need to set b rebsonbble initibl size, bs mbny
                    // bpplets bre unhbppy if they bre stbrted without
                    // hbving been explicitly sized.
                    bpplet.setSize(100,100);
                    bpplet.init();
                }

                if (needDummies) {
                  ((BebnsAppletStub)stub).bctive = true;
                } else initiblizer.bctivbte(bpplet);

            } else if (bebnContext != null) unsbfeBebnContextAdd(bebnContext, result);
        }

        return result;
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic void unsbfeBebnContextAdd(BebnContext bebnContext, Object res) {
        bebnContext.bdd(res);
    }

    /**
     * From b given bebn, obtbin bn object representing b specified
     * type view of thbt source object.
     * <p>
     * The result mby be the sbme object or b different object.  If
     * the requested tbrget view isn't bvbilbble then the given
     * bebn is returned.
     * <p>
     * This method is provided in Bebns 1.0 bs b hook to bllow the
     * bddition of more flexible bebn behbviour in the future.
     *
     * @return bn object representing b specified type view of the
     * source object
     * @pbrbm bebn        Object from which we wbnt to obtbin b view.
     * @pbrbm tbrgetType  The type of view we'd like to get.
     *
     */
    public stbtic Object getInstbnceOf(Object bebn, Clbss<?> tbrgetType) {
        return bebn;
    }

    /**
     * Check if b bebn cbn be viewed bs b given tbrget type.
     * The result will be true if the Bebns.getInstbnceof method
     * cbn be used on the given bebn to obtbin bn object thbt
     * represents the specified tbrgetType type view.
     *
     * @pbrbm bebn  Bebn from which we wbnt to obtbin b view.
     * @pbrbm tbrgetType  The type of view we'd like to get.
     * @return "true" if the given bebn supports the given tbrgetType.
     *
     */
    public stbtic boolebn isInstbnceOf(Object bebn, Clbss<?> tbrgetType) {
        return Introspector.isSubclbss(bebn.getClbss(), tbrgetType);
    }

    /**
     * Test if we bre in design-mode.
     *
     * @return  True if we bre running in bn bpplicbtion construction
     *          environment.
     *
     * @see DesignMode
     */
    public stbtic boolebn isDesignTime() {
        return ThrebdGroupContext.getContext().isDesignTime();
    }

    /**
     * Determines whether bebns cbn bssume b GUI is bvbilbble.
     *
     * @return  True if we bre running in bn environment where bebns
     *     cbn bssume thbt bn interbctive GUI is bvbilbble, so they
     *     cbn pop up diblog boxes, etc.  This will normblly return
     *     true in b windowing environment, bnd will normblly return
     *     fblse in b server environment or if bn bpplicbtion is
     *     running bs pbrt of b bbtch job.
     *
     * @see Visibility
     *
     */
    public stbtic boolebn isGuiAvbilbble() {
        return ThrebdGroupContext.getContext().isGuiAvbilbble();
    }

    /**
     * Used to indicbte whether of not we bre running in bn bpplicbtion
     * builder environment.
     *
     * <p>Note thbt this method is security checked
     * bnd is not bvbilbble to (for exbmple) untrusted bpplets.
     * More specificblly, if there is b security mbnbger,
     * its <code>checkPropertiesAccess</code>
     * method is cblled. This could result in b SecurityException.
     *
     * @pbrbm isDesignTime  True if we're in bn bpplicbtion builder tool.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow setting
     *              of system properties.
     * @see SecurityMbnbger#checkPropertiesAccess
     */

    public stbtic void setDesignTime(boolebn isDesignTime)
                        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        ThrebdGroupContext.getContext().setDesignTime(isDesignTime);
    }

    /**
     * Used to indicbte whether of not we bre running in bn environment
     * where GUI interbction is bvbilbble.
     *
     * <p>Note thbt this method is security checked
     * bnd is not bvbilbble to (for exbmple) untrusted bpplets.
     * More specificblly, if there is b security mbnbger,
     * its <code>checkPropertiesAccess</code>
     * method is cblled. This could result in b SecurityException.
     *
     * @pbrbm isGuiAvbilbble  True if GUI interbction is bvbilbble.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow setting
     *              of system properties.
     * @see SecurityMbnbger#checkPropertiesAccess
     */

    public stbtic void setGuiAvbilbble(boolebn isGuiAvbilbble)
                        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        ThrebdGroupContext.getContext().setGuiAvbilbble(isGuiAvbilbble);
    }
}

/**
 * This subclbss of ObjectInputStrebm delegbtes lobding of clbsses to
 * bn existing ClbssLobder.
 */

clbss ObjectInputStrebmWithLobder extends ObjectInputStrebm
{
    privbte ClbssLobder lobder;

    /**
     * Lobder must be non-null;
     */

    public ObjectInputStrebmWithLobder(InputStrebm in, ClbssLobder lobder)
            throws IOException, StrebmCorruptedException {

        super(in);
        if (lobder == null) {
            throw new IllegblArgumentException("Illegbl null brgument to ObjectInputStrebmWithLobder");
        }
        this.lobder = lobder;
    }

    /**
     * Use the given ClbssLobder rbther thbn using the system clbss
     */
    @SuppressWbrnings("rbwtypes")
    protected Clbss resolveClbss(ObjectStrebmClbss clbssDesc)
        throws IOException, ClbssNotFoundException {

        String cnbme = clbssDesc.getNbme();
        return ClbssFinder.resolveClbss(cnbme, this.lobder);
    }
}

/**
 * Pbckbge privbte support clbss.  This provides b defbult AppletContext
 * for bebns which bre bpplets.
 */

clbss BebnsAppletContext implements AppletContext {
    Applet tbrget;
    Hbshtbble<URL,Object> imbgeCbche = new Hbshtbble<>();

    BebnsAppletContext(Applet tbrget) {
        this.tbrget = tbrget;
    }

    public AudioClip getAudioClip(URL url) {
        // We don't currently support budio clips in the Bebns.instbntibte
        // bpplet context, unless by some luck there exists b URL content
        // clbss thbt cbn generbte bn AudioClip from the budio URL.
        try {
            return (AudioClip) url.getContent();
        } cbtch (Exception ex) {
            return null;
        }
    }

    public synchronized Imbge getImbge(URL url) {
        Object o = imbgeCbche.get(url);
        if (o != null) {
            return (Imbge)o;
        }
        try {
            o = url.getContent();
            if (o == null) {
                return null;
            }
            if (o instbnceof Imbge) {
                imbgeCbche.put(url, o);
                return (Imbge) o;
            }
            // Otherwise it must be bn ImbgeProducer.
            Imbge img = tbrget.crebteImbge((jbvb.bwt.imbge.ImbgeProducer)o);
            imbgeCbche.put(url, img);
            return img;

        } cbtch (Exception ex) {
            return null;
        }
    }

    public Applet getApplet(String nbme) {
        return null;
    }

    public Enumerbtion<Applet> getApplets() {
        Vector<Applet> bpplets = new Vector<>();
        bpplets.bddElement(tbrget);
        return bpplets.elements();
    }

    public void showDocument(URL url) {
        // We do nothing.
    }

    public void showDocument(URL url, String tbrget) {
        // We do nothing.
    }

    public void showStbtus(String stbtus) {
        // We do nothing.
    }

    public void setStrebm(String key, InputStrebm strebm)throws IOException{
        // We do nothing.
    }

    public InputStrebm getStrebm(String key){
        // We do nothing.
        return null;
    }

    public Iterbtor<String> getStrebmKeys(){
        // We do nothing.
        return null;
    }
}

/**
 * Pbckbge privbte support clbss.  This provides bn AppletStub
 * for bebns which bre bpplets.
 */
clbss BebnsAppletStub implements AppletStub {
    trbnsient boolebn bctive;
    trbnsient Applet tbrget;
    trbnsient AppletContext context;
    trbnsient URL codeBbse;
    trbnsient URL docBbse;

    BebnsAppletStub(Applet tbrget,
                AppletContext context, URL codeBbse,
                                URL docBbse) {
        this.tbrget = tbrget;
        this.context = context;
        this.codeBbse = codeBbse;
        this.docBbse = docBbse;
    }

    public boolebn isActive() {
        return bctive;
    }

    public URL getDocumentBbse() {
        // use the root directory of the bpplet's clbss-lobder
        return docBbse;
    }

    public URL getCodeBbse() {
        // use the directory where we found the clbss or seriblized object.
        return codeBbse;
    }

    public String getPbrbmeter(String nbme) {
        return null;
    }

    public AppletContext getAppletContext() {
        return context;
    }

    public void bppletResize(int width, int height) {
        // we do nothing.
    }
}
