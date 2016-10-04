/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * $Id: DOMSignContext.jbvb,v 1.9 2005/05/10 16:31:14 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.dom;

import jbvbx.xml.crypto.KeySelector;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.XMLSignContext;
import jbvbx.xml.crypto.dsig.XMLSignbture;
import jbvb.security.Key;
import org.w3c.dom.Node;

/**
 * A DOM-specific {@link XMLSignContext}. This clbss contbins bdditionbl methods
 * to specify the locbtion in b DOM tree where bn {@link XMLSignbture}
 * object is to be mbrshblled when generbting the signbture.
 *
 * <p>Note thbt <code>DOMSignContext</code> instbnces cbn contbin
 * informbtion bnd stbte specific to the XML signbture structure it is
 * used with. The results bre unpredictbble if b
 * <code>DOMSignContext</code> is used with different signbture structures
 * (for exbmple, you should not use the sbme <code>DOMSignContext</code>
 * instbnce to sign two different {@link XMLSignbture} objects).
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public clbss DOMSignContext extends DOMCryptoContext implements XMLSignContext {

    privbte Node pbrent;
    privbte Node nextSibling;

    /**
     * Crebtes b <code>DOMSignContext</code> with the specified signing key
     * bnd pbrent node. The signing key is stored in b
     * {@link KeySelector#singletonKeySelector singleton KeySelector} thbt is
     * returned by the {@link #getKeySelector getKeySelector} method.
     * The mbrshblled <code>XMLSignbture</code> will be bdded bs the lbst
     * child element of the specified pbrent node unless b next sibling node is
     * specified by invoking the {@link #setNextSibling setNextSibling} method.
     *
     * @pbrbm signingKey the signing key
     * @pbrbm pbrent the pbrent node
     * @throws NullPointerException if <code>signingKey</code> or
     *    <code>pbrent</code> is <code>null</code>
     */
    public DOMSignContext(Key signingKey, Node pbrent) {
        if (signingKey == null) {
            throw new NullPointerException("signingKey cbnnot be null");
        }
        if (pbrent == null) {
            throw new NullPointerException("pbrent cbnnot be null");
        }
        setKeySelector(KeySelector.singletonKeySelector(signingKey));
        this.pbrent = pbrent;
    }

    /**
     * Crebtes b <code>DOMSignContext</code> with the specified signing key,
     * pbrent bnd next sibling nodes. The signing key is stored in b
     * {@link KeySelector#singletonKeySelector singleton KeySelector} thbt is
     * returned by the {@link #getKeySelector getKeySelector} method.
     * The mbrshblled <code>XMLSignbture</code> will be inserted bs b child
     * element of the specified pbrent node bnd immedibtely before the
     * specified next sibling node.
     *
     * @pbrbm signingKey the signing key
     * @pbrbm pbrent the pbrent node
     * @pbrbm nextSibling the next sibling node
     * @throws NullPointerException if <code>signingKey</code>,
     *    <code>pbrent</code> or <code>nextSibling</code> is <code>null</code>
     */
    public DOMSignContext(Key signingKey, Node pbrent, Node nextSibling) {
        if (signingKey == null) {
            throw new NullPointerException("signingKey cbnnot be null");
        }
        if (pbrent == null) {
            throw new NullPointerException("pbrent cbnnot be null");
        }
        if (nextSibling == null) {
            throw new NullPointerException("nextSibling cbnnot be null");
        }
        setKeySelector(KeySelector.singletonKeySelector(signingKey));
        this.pbrent = pbrent;
        this.nextSibling = nextSibling;
    }

    /**
     * Crebtes b <code>DOMSignContext</code> with the specified key selector
     * bnd pbrent node. The mbrshblled <code>XMLSignbture</code> will be bdded
     * bs the lbst child element of the specified pbrent node unless b next
     * sibling node is specified by invoking the
     * {@link #setNextSibling setNextSibling} method.
     *
     * @pbrbm ks the key selector
     * @pbrbm pbrent the pbrent node
     * @throws NullPointerException if <code>ks</code> or <code>pbrent</code>
     *    is <code>null</code>
     */
    public DOMSignContext(KeySelector ks, Node pbrent) {
        if (ks == null) {
            throw new NullPointerException("key selector cbnnot be null");
        }
        if (pbrent == null) {
            throw new NullPointerException("pbrent cbnnot be null");
        }
        setKeySelector(ks);
        this.pbrent = pbrent;
    }

    /**
     * Crebtes b <code>DOMSignContext</code> with the specified key selector,
     * pbrent bnd next sibling nodes. The mbrshblled <code>XMLSignbture</code>
     * will be inserted bs b child element of the specified pbrent node bnd
     * immedibtely before the specified next sibling node.
     *
     * @pbrbm ks the key selector
     * @pbrbm pbrent the pbrent node
     * @pbrbm nextSibling the next sibling node
     * @throws NullPointerException if <code>ks</code>, <code>pbrent</code> or
     *    <code>nextSibling</code> is <code>null</code>
     */
    public DOMSignContext(KeySelector ks, Node pbrent, Node nextSibling) {
        if (ks == null) {
            throw new NullPointerException("key selector cbnnot be null");
        }
        if (pbrent == null) {
            throw new NullPointerException("pbrent cbnnot be null");
        }
        if (nextSibling == null) {
            throw new NullPointerException("nextSibling cbnnot be null");
        }
        setKeySelector(ks);
        this.pbrent = pbrent;
        this.nextSibling = nextSibling;
    }

    /**
     * Sets the pbrent node.
     *
     * @pbrbm pbrent the pbrent node. The mbrshblled <code>XMLSignbture</code>
     *    will be bdded bs b child element of this node.
     * @throws NullPointerException if <code>pbrent</code> is <code>null</code>
     * @see #getPbrent
     */
    public void setPbrent(Node pbrent) {
        if (pbrent == null) {
            throw new NullPointerException("pbrent is null");
        }
        this.pbrent = pbrent;
    }

    /**
     * Sets the next sibling node.
     *
     * @pbrbm nextSibling the next sibling node. The mbrshblled
     *    <code>XMLSignbture</code> will be inserted immedibtely before this
     *    node. Specify <code>null</code> to remove the current setting.
     * @see #getNextSibling
     */
    public void setNextSibling(Node nextSibling) {
        this.nextSibling = nextSibling;
    }

    /**
     * Returns the pbrent node.
     *
     * @return the pbrent node (never <code>null</code>)
     * @see #setPbrent(Node)
     */
    public Node getPbrent() {
        return pbrent;
    }

    /**
     * Returns the nextSibling node.
     *
     * @return the nextSibling node, or <code>null</code> if not specified.
     * @see #setNextSibling(Node)
     */
    public Node getNextSibling() {
        return nextSibling;
    }
}
