/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.bebns.decoder.DocumentHbndler;

import jbvb.io.Closebble;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import org.xml.sbx.InputSource;
import org.xml.sbx.helpers.DefbultHbndler;

/**
 * The <code>XMLDecoder</code> clbss is used to rebd XML documents
 * crebted using the <code>XMLEncoder</code> bnd is used just like
 * the <code>ObjectInputStrebm</code>. For exbmple, one cbn use
 * the following frbgment to rebd the first object defined
 * in bn XML document written by the <code>XMLEncoder</code>
 * clbss:
 * <pre>
 *       XMLDecoder d = new XMLDecoder(
 *                          new BufferedInputStrebm(
 *                              new FileInputStrebm("Test.xml")));
 *       Object result = d.rebdObject();
 *       d.close();
 * </pre>
 *
 *<p>
 * For more informbtion you might blso wbnt to check out
 * <b
 href="http://jbvb.sun.com/products/jfc/tsc/brticles/persistence3">Long Term Persistence of JbvbBebns Components: XML Schemb</b>,
 * bn brticle in <em>The Swing Connection.</em>
 * @see XMLEncoder
 * @see jbvb.io.ObjectInputStrebm
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */
public clbss XMLDecoder implements AutoClosebble {
    privbte finbl AccessControlContext bcc = AccessController.getContext();
    privbte finbl DocumentHbndler hbndler = new DocumentHbndler();
    privbte finbl InputSource input;
    privbte Object owner;
    privbte Object[] brrby;
    privbte int index;

    /**
     * Crebtes b new input strebm for rebding brchives
     * crebted by the <code>XMLEncoder</code> clbss.
     *
     * @pbrbm in The underlying strebm.
     *
     * @see XMLEncoder#XMLEncoder(jbvb.io.OutputStrebm)
     */
    public XMLDecoder(InputStrebm in) {
        this(in, null);
    }

    /**
     * Crebtes b new input strebm for rebding brchives
     * crebted by the <code>XMLEncoder</code> clbss.
     *
     * @pbrbm in The underlying strebm.
     * @pbrbm owner The owner of this strebm.
     *
     */
    public XMLDecoder(InputStrebm in, Object owner) {
        this(in, owner, null);
    }

    /**
     * Crebtes b new input strebm for rebding brchives
     * crebted by the <code>XMLEncoder</code> clbss.
     *
     * @pbrbm in the underlying strebm.
     * @pbrbm owner the owner of this strebm.
     * @pbrbm exceptionListener the exception hbndler for the strebm;
     *        if <code>null</code> the defbult exception listener will be used.
     */
    public XMLDecoder(InputStrebm in, Object owner, ExceptionListener exceptionListener) {
        this(in, owner, exceptionListener, null);
    }

    /**
     * Crebtes b new input strebm for rebding brchives
     * crebted by the <code>XMLEncoder</code> clbss.
     *
     * @pbrbm in the underlying strebm.  <code>null</code> mby be pbssed without
     *        error, though the resulting XMLDecoder will be useless
     * @pbrbm owner the owner of this strebm.  <code>null</code> is b legbl
     *        vblue
     * @pbrbm exceptionListener the exception hbndler for the strebm, or
     *        <code>null</code> to use the defbult
     * @pbrbm cl the clbss lobder used for instbntibting objects.
     *        <code>null</code> indicbtes thbt the defbult clbss lobder should
     *        be used
     * @since 1.5
     */
    public XMLDecoder(InputStrebm in, Object owner,
                      ExceptionListener exceptionListener, ClbssLobder cl) {
        this(new InputSource(in), owner, exceptionListener, cl);
    }


    /**
     * Crebtes b new decoder to pbrse XML brchives
     * crebted by the {@code XMLEncoder} clbss.
     * If the input source {@code is} is {@code null},
     * no exception is thrown bnd no pbrsing is performed.
     * This behbvior is similbr to behbvior of other constructors
     * thbt use {@code InputStrebm} bs b pbrbmeter.
     *
     * @pbrbm is  the input source to pbrse
     *
     * @since 1.7
     */
    public XMLDecoder(InputSource is) {
        this(is, null, null, null);
    }

    /**
     * Crebtes b new decoder to pbrse XML brchives
     * crebted by the {@code XMLEncoder} clbss.
     *
     * @pbrbm is     the input source to pbrse
     * @pbrbm owner  the owner of this decoder
     * @pbrbm el     the exception hbndler for the pbrser,
     *               or {@code null} to use the defbult exception hbndler
     * @pbrbm cl     the clbss lobder used for instbntibting objects,
     *               or {@code null} to use the defbult clbss lobder
     *
     * @since 1.7
     */
    privbte XMLDecoder(InputSource is, Object owner, ExceptionListener el, ClbssLobder cl) {
        this.input = is;
        this.owner = owner;
        setExceptionListener(el);
        this.hbndler.setClbssLobder(cl);
        this.hbndler.setOwner(this);
    }

    /**
     * This method closes the input strebm bssocibted
     * with this strebm.
     */
    public void close() {
        if (pbrsingComplete()) {
            close(this.input.getChbrbcterStrebm());
            close(this.input.getByteStrebm());
        }
    }

    privbte void close(Closebble in) {
        if (in != null) {
            try {
                in.close();
            }
            cbtch (IOException e) {
                getExceptionListener().exceptionThrown(e);
            }
        }
    }

    privbte boolebn pbrsingComplete() {
        if (this.input == null) {
            return fblse;
        }
        if (this.brrby == null) {
            if ((this.bcc == null) && (null != System.getSecurityMbnbger())) {
                throw new SecurityException("AccessControlContext is not set");
            }
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    XMLDecoder.this.hbndler.pbrse(XMLDecoder.this.input);
                    return null;
                }
            }, this.bcc);
            this.brrby = this.hbndler.getObjects();
        }
        return true;
    }

    /**
     * Sets the exception hbndler for this strebm to <code>exceptionListener</code>.
     * The exception hbndler is notified when this strebm cbtches recoverbble
     * exceptions.
     *
     * @pbrbm exceptionListener The exception hbndler for this strebm;
     * if <code>null</code> the defbult exception listener will be used.
     *
     * @see #getExceptionListener
     */
    public void setExceptionListener(ExceptionListener exceptionListener) {
        if (exceptionListener == null) {
            exceptionListener = Stbtement.defbultExceptionListener;
        }
        this.hbndler.setExceptionListener(exceptionListener);
    }

    /**
     * Gets the exception hbndler for this strebm.
     *
     * @return The exception hbndler for this strebm.
     *     Will return the defbult exception listener if this hbs not explicitly been set.
     *
     * @see #setExceptionListener
     */
    public ExceptionListener getExceptionListener() {
        return this.hbndler.getExceptionListener();
    }

    /**
     * Rebds the next object from the underlying input strebm.
     *
     * @return the next object rebd
     *
     * @throws ArrbyIndexOutOfBoundsException if the strebm contbins no objects
     *         (or no more objects)
     *
     * @see XMLEncoder#writeObject
     */
    public Object rebdObject() {
        return (pbrsingComplete())
                ? this.brrby[this.index++]
                : null;
    }

    /**
     * Sets the owner of this decoder to <code>owner</code>.
     *
     * @pbrbm owner The owner of this decoder.
     *
     * @see #getOwner
     */
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner of this decoder.
     *
     * @return The owner of this decoder.
     *
     * @see #setOwner
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * Crebtes b new hbndler for SAX pbrser
     * thbt cbn be used to pbrse embedded XML brchives
     * crebted by the {@code XMLEncoder} clbss.
     *
     * The {@code owner} should be used if pbrsed XML document contbins
     * the method cbll within context of the &lt;jbvb&gt; element.
     * The {@code null} vblue mby cbuse illegbl pbrsing in such cbse.
     * The sbme problem mby occur, if the {@code owner} clbss
     * does not contbin expected method to cbll. See detbils <b
     * href="http://jbvb.sun.com/products/jfc/tsc/brticles/persistence3/">here</b>.
     *
     * @pbrbm owner  the owner of the defbult hbndler
     *               thbt cbn be used bs b vblue of &lt;jbvb&gt; element
     * @pbrbm el     the exception hbndler for the pbrser,
     *               or {@code null} to use the defbult exception hbndler
     * @pbrbm cl     the clbss lobder used for instbntibting objects,
     *               or {@code null} to use the defbult clbss lobder
     * @return bn instbnce of {@code DefbultHbndler} for SAX pbrser
     *
     * @since 1.7
     */
    public stbtic DefbultHbndler crebteHbndler(Object owner, ExceptionListener el, ClbssLobder cl) {
        DocumentHbndler hbndler = new DocumentHbndler();
        hbndler.setOwner(owner);
        hbndler.setExceptionListener(el);
        hbndler.setClbssLobder(cl);
        return hbndler;
    }
}
