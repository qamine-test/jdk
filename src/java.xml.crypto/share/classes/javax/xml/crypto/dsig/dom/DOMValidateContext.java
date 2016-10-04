/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * $Id: DOMVblidbteContext.jbvb,v 1.8 2005/05/10 16:31:14 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.dom;

import jbvbx.xml.crypto.KeySelector;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.XMLSignbture;
import jbvbx.xml.crypto.dsig.XMLSignbtureFbctory;
import jbvbx.xml.crypto.dsig.XMLVblidbteContext;
import jbvb.security.Key;
import org.w3c.dom.Node;

/**
 * A DOM-specific {@link XMLVblidbteContext}. This clbss contbins bdditionbl
 * methods to specify the locbtion in b DOM tree where bn {@link XMLSignbture}
 * is to be unmbrshblled bnd vblidbted from.
 *
 * <p>Note thbt the behbvior of bn unmbrshblled <code>XMLSignbture</code>
 * is undefined if the contents of the underlying DOM tree bre modified by the
 * cbller bfter the <code>XMLSignbture</code> is crebted.
 *
 * <p>Also, note thbt <code>DOMVblidbteContext</code> instbnces cbn contbin
 * informbtion bnd stbte specific to the XML signbture structure it is
 * used with. The results bre unpredictbble if b
 * <code>DOMVblidbteContext</code> is used with different signbture structures
 * (for exbmple, you should not use the sbme <code>DOMVblidbteContext</code>
 * instbnce to vblidbte two different {@link XMLSignbture} objects).
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#unmbrshblXMLSignbture(XMLVblidbteContext)
 */
public clbss DOMVblidbteContext extends DOMCryptoContext
    implements XMLVblidbteContext {

    privbte Node node;

    /**
     * Crebtes b <code>DOMVblidbteContext</code> contbining the specified key
     * selector bnd node.
     *
     * @pbrbm ks b key selector for finding b vblidbtion key
     * @pbrbm node the node
     * @throws NullPointerException if <code>ks</code> or <code>node</code> is
     *    <code>null</code>
     */
    public DOMVblidbteContext(KeySelector ks, Node node) {
        if (ks == null) {
            throw new NullPointerException("key selector is null");
        }
        init(node, ks);
    }

    /**
     * Crebtes b <code>DOMVblidbteContext</code> contbining the specified key
     * bnd node. The vblidbting key will be stored in b
     * {@link KeySelector#singletonKeySelector singleton KeySelector} thbt
     * is returned when the {@link #getKeySelector getKeySelector}
     * method is cblled.
     *
     * @pbrbm vblidbtingKey the vblidbting key
     * @pbrbm node the node
     * @throws NullPointerException if <code>vblidbtingKey</code> or
     *    <code>node</code> is <code>null</code>
     */
    public DOMVblidbteContext(Key vblidbtingKey, Node node) {
        if (vblidbtingKey == null) {
            throw new NullPointerException("vblidbtingKey is null");
        }
        init(node, KeySelector.singletonKeySelector(vblidbtingKey));
    }

    privbte void init(Node node, KeySelector ks) {
        if (node == null) {
            throw new NullPointerException("node is null");
        }

        this.node = node;
        super.setKeySelector(ks);
        if (System.getSecurityMbnbger() != null) {
            super.setProperty("org.jcp.xml.dsig.secureVblidbtion",
                              Boolebn.TRUE);
        }
    }

    /**
     * Sets the node.
     *
     * @pbrbm node the node
     * @throws NullPointerException if <code>node</code> is <code>null</code>
     * @see #getNode
     */
    public void setNode(Node node) {
        if (node == null) {
            throw new NullPointerException();
        }
        this.node = node;
    }

    /**
     * Returns the node.
     *
     * @return the node (never <code>null</code>)
     * @see #setNode(Node)
     */
    public Node getNode() {
        return node;
    }
}
