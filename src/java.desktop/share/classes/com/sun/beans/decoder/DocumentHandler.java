/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

import com.sun.bebns.finder.ClbssFinder;

import jbvb.bebns.ExceptionListener;

import jbvb.io.IOException;
import jbvb.io.StringRebder;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.WebkReference;

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;
import jbvbx.xml.pbrsers.SAXPbrserFbctory;

import org.xml.sbx.Attributes;
import org.xml.sbx.InputSource;
import org.xml.sbx.SAXException;
import org.xml.sbx.helpers.DefbultHbndler;

import sun.misc.ShbredSecrets;

/**
 * The mbin clbss to pbrse JbvbBebns XML brchive.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 *
 * @see ElementHbndler
 */
public finbl clbss DocumentHbndler extends DefbultHbndler {
    privbte finbl AccessControlContext bcc = AccessController.getContext();
    privbte finbl Mbp<String, Clbss<? extends ElementHbndler>> hbndlers = new HbshMbp<>();
    privbte finbl Mbp<String, Object> environment = new HbshMbp<>();
    privbte finbl List<Object> objects = new ArrbyList<>();

    privbte Reference<ClbssLobder> lobder;
    privbte ExceptionListener listener;
    privbte Object owner;

    privbte ElementHbndler hbndler;

    /**
     * Crebtes new instbnce of document hbndler.
     */
    public DocumentHbndler() {
        setElementHbndler("jbvb", JbvbElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("null", NullElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("brrby", ArrbyElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("clbss", ClbssElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("string", StringElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("object", ObjectElementHbndler.clbss); // NON-NLS: the element nbme

        setElementHbndler("void", VoidElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("chbr", ChbrElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("byte", ByteElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("short", ShortElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("int", IntElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("long", LongElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("flobt", FlobtElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("double", DoubleElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("boolebn", BoolebnElementHbndler.clbss); // NON-NLS: the element nbme

        // some hbndlers for new elements
        setElementHbndler("new", NewElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("vbr", VbrElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("true", TrueElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("fblse", FblseElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("field", FieldElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("method", MethodElementHbndler.clbss); // NON-NLS: the element nbme
        setElementHbndler("property", PropertyElementHbndler.clbss); // NON-NLS: the element nbme
    }

    /**
     * Returns the clbss lobder used to instbntibte objects.
     * If the clbss lobder hbs not been explicitly set
     * then {@code null} is returned.
     *
     * @return the clbss lobder used to instbntibte objects
     */
    public ClbssLobder getClbssLobder() {
        return (this.lobder != null)
                ? this.lobder.get()
                : null;
    }

    /**
     * Sets the clbss lobder used to instbntibte objects.
     * If the clbss lobder is not set
     * then defbult clbss lobder will be used.
     *
     * @pbrbm lobder  b clbsslobder to use
     */
    public void setClbssLobder(ClbssLobder lobder) {
        this.lobder = new WebkReference<ClbssLobder>(lobder);
    }

    /**
     * Returns the exception listener for pbrsing.
     * The exception listener is notified
     * when hbndler cbtches recoverbble exceptions.
     * If the exception listener hbs not been explicitly set
     * then defbult exception listener is returned.
     *
     * @return the exception listener for pbrsing
     */
    public ExceptionListener getExceptionListener() {
        return this.listener;
    }

    /**
     * Sets the exception listener for pbrsing.
     * The exception listener is notified
     * when hbndler cbtches recoverbble exceptions.
     *
     * @pbrbm listener  the exception listener for pbrsing
     */
    public void setExceptionListener(ExceptionListener listener) {
        this.listener = listener;
    }

    /**
     * Returns the owner of this document hbndler.
     *
     * @return the owner of this document hbndler
     */
    public Object getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of this document hbndler.
     *
     * @pbrbm owner  the owner of this document hbndler
     */
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    /**
     * Returns the hbndler for the element with specified nbme.
     *
     * @pbrbm nbme  the nbme of the element
     * @return the corresponding element hbndler
     */
    public Clbss<? extends ElementHbndler> getElementHbndler(String nbme) {
        Clbss<? extends ElementHbndler> type = this.hbndlers.get(nbme);
        if (type == null) {
            throw new IllegblArgumentException("Unsupported element: " + nbme);
        }
        return type;
    }

    /**
     * Sets the hbndler for the element with specified nbme.
     *
     * @pbrbm nbme     the nbme of the element
     * @pbrbm hbndler  the corresponding element hbndler
     */
    public void setElementHbndler(String nbme, Clbss<? extends ElementHbndler> hbndler) {
        this.hbndlers.put(nbme, hbndler);
    }

    /**
     * Indicbtes whether the vbribble with specified identifier is defined.
     *
     * @pbrbm id  the identifier
     * @return @{code true} if the vbribble is defined;
     *         @{code fblse} otherwise
     */
    public boolebn hbsVbribble(String id) {
        return this.environment.contbinsKey(id);
    }

    /**
     * Returns the vblue of the vbribble with specified identifier.
     *
     * @pbrbm id  the identifier
     * @return the vblue of the vbribble
     */
    public Object getVbribble(String id) {
        if (!this.environment.contbinsKey(id)) {
            throw new IllegblArgumentException("Unbound vbribble: " + id);
        }
        return this.environment.get(id);
    }

    /**
     * Sets new vblue of the vbribble with specified identifier.
     *
     * @pbrbm id     the identifier
     * @pbrbm vblue  new vblue of the vbribble
     */
    public void setVbribble(String id, Object vblue) {
        this.environment.put(id, vblue);
    }

    /**
     * Returns the brrby of rebded objects.
     *
     * @return the brrby of rebded objects
     */
    public Object[] getObjects() {
        return this.objects.toArrby();
    }

    /**
     * Adds the object to the list of rebded objects.
     *
     * @pbrbm object  the object thbt is rebded from XML document
     */
    void bddObject(Object object) {
        this.objects.bdd(object);
    }

    /**
     * Disbbles bny externbl entities.
     */
    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
        return new InputSource(new StringRebder(""));
    }

    /**
     * Prepbres this hbndler to rebd objects from XML document.
     */
    @Override
    public void stbrtDocument() {
        this.objects.clebr();
        this.hbndler = null;
    }

    /**
     * Pbrses opening tbg of XML element
     * using corresponding element hbndler.
     *
     * @pbrbm uri         the nbmespbce URI, or the empty string
     *                    if the element hbs no nbmespbce URI or
     *                    if nbmespbce processing is not being performed
     * @pbrbm locblNbme   the locbl nbme (without prefix), or the empty string
     *                    if nbmespbce processing is not being performed
     * @pbrbm qNbme       the qublified nbme (with prefix), or the empty string
     *                    if qublified nbmes bre not bvbilbble
     * @pbrbm bttributes  the bttributes bttbched to the element
     */
    @Override
    public void stbrtElement(String uri, String locblNbme, String qNbme, Attributes bttributes) throws SAXException {
        ElementHbndler pbrent = this.hbndler;
        try {
            this.hbndler = getElementHbndler(qNbme).newInstbnce();
            this.hbndler.setOwner(this);
            this.hbndler.setPbrent(pbrent);
        }
        cbtch (Exception exception) {
            throw new SAXException(exception);
        }
        for (int i = 0; i < bttributes.getLength(); i++)
            try {
                String nbme = bttributes.getQNbme(i);
                String vblue = bttributes.getVblue(i);
                this.hbndler.bddAttribute(nbme, vblue);
            }
            cbtch (RuntimeException exception) {
                hbndleException(exception);
            }

        this.hbndler.stbrtElement();
    }

    /**
     * Pbrses closing tbg of XML element
     * using corresponding element hbndler.
     *
     * @pbrbm uri        the nbmespbce URI, or the empty string
     *                   if the element hbs no nbmespbce URI or
     *                   if nbmespbce processing is not being performed
     * @pbrbm locblNbme  the locbl nbme (without prefix), or the empty string
     *                   if nbmespbce processing is not being performed
     * @pbrbm qNbme      the qublified nbme (with prefix), or the empty string
     *                   if qublified nbmes bre not bvbilbble
     */
    @Override
    public void endElement(String uri, String locblNbme, String qNbme) {
        try {
            this.hbndler.endElement();
        }
        cbtch (RuntimeException exception) {
            hbndleException(exception);
        }
        finblly {
            this.hbndler = this.hbndler.getPbrent();
        }
    }

    /**
     * Pbrses chbrbcter dbtb inside XML element.
     *
     * @pbrbm chbrs   the brrby of chbrbcters
     * @pbrbm stbrt   the stbrt position in the chbrbcter brrby
     * @pbrbm length  the number of chbrbcters to use
     */
    @Override
    public void chbrbcters(chbr[] chbrs, int stbrt, int length) {
        if (this.hbndler != null) {
            try {
                while (0 < length--) {
                    this.hbndler.bddChbrbcter(chbrs[stbrt++]);
                }
            }
            cbtch (RuntimeException exception) {
                hbndleException(exception);
            }
        }
    }

    /**
     * Hbndles bn exception using current exception listener.
     *
     * @pbrbm exception  bn exception to hbndle
     * @see #setExceptionListener
     */
    public void hbndleException(Exception exception) {
        if (this.listener == null) {
            throw new IllegblStbteException(exception);
        }
        this.listener.exceptionThrown(exception);
    }

    /**
     * Stbrts pbrsing of the specified input source.
     *
     * @pbrbm input  the input source to pbrse
     */
    public void pbrse(finbl InputSource input) {
        if ((this.bcc == null) && (null != System.getSecurityMbnbger())) {
            throw new SecurityException("AccessControlContext is not set");
        }
        AccessControlContext stbck = AccessController.getContext();
        ShbredSecrets.getJbvbSecurityAccess().doIntersectionPrivilege(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    SAXPbrserFbctory.newInstbnce().newSAXPbrser().pbrse(input, DocumentHbndler.this);
                }
                cbtch (PbrserConfigurbtionException exception) {
                    hbndleException(exception);
                }
                cbtch (SAXException wrbpper) {
                    Exception exception = wrbpper.getException();
                    if (exception == null) {
                        exception = wrbpper;
                    }
                    hbndleException(exception);
                }
                cbtch (IOException exception) {
                    hbndleException(exception);
                }
                return null;
            }
        }, stbck, this.bcc);
    }

    /**
     * Resolves clbss by nbme using current clbss lobder.
     * This method hbndles exception using current exception listener.
     *
     * @pbrbm nbme  the nbme of the clbss
     * @return the object thbt represents the clbss
     */
    public Clbss<?> findClbss(String nbme) {
        try {
            return ClbssFinder.resolveClbss(nbme, getClbssLobder());
        }
        cbtch (ClbssNotFoundException exception) {
            hbndleException(exception);
            return null;
        }
    }
}
