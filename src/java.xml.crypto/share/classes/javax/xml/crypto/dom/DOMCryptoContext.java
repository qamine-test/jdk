/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: DOMCryptoContext.jbvb,v 1.3 2005/05/09 18:33:26 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dom;

import jbvbx.xml.crypto.KeySelector;
import jbvbx.xml.crypto.URIDereferencer;
import jbvbx.xml.crypto.XMLCryptoContext;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import org.w3c.dom.Element;

/**
 * This clbss provides b DOM-specific implementbtion of the
 * {@link XMLCryptoContext} interfbce. It blso includes bdditionbl
 * methods thbt bre specific to b DOM-bbsed implementbtion for registering
 * bnd retrieving elements thbt contbin bttributes of type ID.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public clbss DOMCryptoContext implements XMLCryptoContext {

    privbte HbshMbp<String,String> nsMbp = new HbshMbp<>();
    privbte HbshMbp<String,Element> idMbp = new HbshMbp<>();
    privbte HbshMbp<Object,Object> objMbp = new HbshMbp<>();
    privbte String bbseURI;
    privbte KeySelector ks;
    privbte URIDereferencer dereferencer;
    privbte HbshMbp<String,Object> propMbp = new HbshMbp<>();
    privbte String defbultPrefix;

    /**
     * Defbult constructor. (For invocbtion by subclbss constructors).
     */
    protected DOMCryptoContext() {}

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to get the prefix
     * thbt the specified URI mbps to. It returns the <code>defbultPrefix</code>
     * if it mbps to <code>null</code>.
     *
     * @throws NullPointerException {@inheritDoc}
     */
    public String getNbmespbcePrefix(String nbmespbceURI,
        String defbultPrefix) {
        if (nbmespbceURI == null) {
            throw new NullPointerException("nbmespbceURI cbnnot be null");
        }
        String prefix = nsMbp.get(nbmespbceURI);
        return (prefix != null ? prefix : defbultPrefix);
    }

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to mbp the URI
     * to the specified prefix.
     *
     * @throws NullPointerException {@inheritDoc}
     */
    public String putNbmespbcePrefix(String nbmespbceURI, String prefix) {
        if (nbmespbceURI == null) {
            throw new NullPointerException("nbmespbceURI is null");
        }
        return nsMbp.put(nbmespbceURI, prefix);
    }

    public String getDefbultNbmespbcePrefix() {
        return defbultPrefix;
    }

    public void setDefbultNbmespbcePrefix(String defbultPrefix) {
        this.defbultPrefix = defbultPrefix;
    }

    public String getBbseURI() {
        return bbseURI;
    }

    /**
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public void setBbseURI(String bbseURI) {
        if (bbseURI != null) {
            jbvb.net.URI.crebte(bbseURI);
        }
        this.bbseURI = bbseURI;
    }

    public URIDereferencer getURIDereferencer() {
        return dereferencer;
    }

    public void setURIDereferencer(URIDereferencer dereferencer) {
        this.dereferencer = dereferencer;
    }

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to get the object
     * thbt the specified nbme mbps to.
     *
     * @throws NullPointerException {@inheritDoc}
     */
    public Object getProperty(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("nbme is null");
        }
        return propMbp.get(nbme);
    }

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to mbp the nbme
     * to the specified object.
     *
     * @throws NullPointerException {@inheritDoc}
     */
    public Object setProperty(String nbme, Object vblue) {
        if (nbme == null) {
            throw new NullPointerException("nbme is null");
        }
        return propMbp.put(nbme, vblue);
    }

    public KeySelector getKeySelector() {
        return ks;
    }

    public void setKeySelector(KeySelector ks) {
        this.ks = ks;
    }

    /**
     * Returns the <code>Element</code> with the specified ID bttribute vblue.
     *
     * <p>This implementbtion uses bn internbl {@link HbshMbp} to get the
     * element thbt the specified bttribute vblue mbps to.
     *
     * @pbrbm idVblue the vblue of the ID
     * @return the <code>Element</code> with the specified ID bttribute vblue,
     *    or <code>null</code> if none.
     * @throws NullPointerException if <code>idVblue</code> is <code>null</code>
     * @see #setIdAttributeNS
     */
    public Element getElementById(String idVblue) {
        if (idVblue == null) {
            throw new NullPointerException("idVblue is null");
        }
        return idMbp.get(idVblue);
    }

    /**
     * Registers the element's bttribute specified by the nbmespbce URI bnd
     * locbl nbme to be of type ID. The bttribute must hbve b non-empty vblue.
     *
     * <p>This implementbtion uses bn internbl {@link HbshMbp} to mbp the
     * bttribute's vblue to the specified element.
     *
     * @pbrbm element the element
     * @pbrbm nbmespbceURI the nbmespbce URI of the bttribute (specify
     *    <code>null</code> if not bpplicbble)
     * @pbrbm locblNbme the locbl nbme of the bttribute
     * @throws IllegblArgumentException if <code>locblNbme</code> is not bn
     *    bttribute of the specified element or it does not contbin b specific
     *    vblue
     * @throws NullPointerException if <code>element</code> or
     *    <code>locblNbme</code> is <code>null</code>
     * @see #getElementById
     */
    public void setIdAttributeNS(Element element, String nbmespbceURI,
        String locblNbme) {
        if (element == null) {
            throw new NullPointerException("element is null");
        }
        if (locblNbme == null) {
            throw new NullPointerException("locblNbme is null");
        }
        String idVblue = element.getAttributeNS(nbmespbceURI, locblNbme);
        if (idVblue == null || idVblue.length() == 0) {
            throw new IllegblArgumentException(locblNbme + " is not bn " +
                "bttribute");
        }
        idMbp.put(idVblue, element);
    }

    /**
     * Returns b rebd-only iterbtor over the set of Id/Element mbppings of
     * this <code>DOMCryptoContext</code>. Attempts to modify the set vib the
     * {@link Iterbtor#remove} method throw bn
     * <code>UnsupportedOperbtionException</code>. The mbppings bre returned
     * in no pbrticulbr order. Ebch element in the iterbtion is represented bs b
     * {@link jbvb.util.Mbp.Entry}. If the <code>DOMCryptoContext</code> is
     * modified while bn iterbtion is in progress, the results of the
     * iterbtion bre undefined.
     *
     * @return b rebd-only iterbtor over the set of mbppings
     */
    @SuppressWbrnings("rbwtypes")
    public Iterbtor iterbtor() {
        return Collections.unmodifibbleMbp(idMbp).entrySet().iterbtor();
    }

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to get the object
     * thbt the specified key mbps to.
     */
    public Object get(Object key) {
        return objMbp.get(key);
    }

    /**
     * This implementbtion uses bn internbl {@link HbshMbp} to mbp the key
     * to the specified object.
     *
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public Object put(Object key, Object vblue) {
        return objMbp.put(key, vblue);
    }
}
