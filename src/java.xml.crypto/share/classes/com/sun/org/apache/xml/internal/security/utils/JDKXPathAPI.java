/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.trbnsform.TrbnsformerException;
import jbvbx.xml.xpbth.XPbth;
import jbvbx.xml.xpbth.XPbthConstbnts;
import jbvbx.xml.xpbth.XPbthExpression;
import jbvbx.xml.xpbth.XPbthExpressionException;
import jbvbx.xml.xpbth.XPbthFbctory;
import jbvbx.xml.xpbth.XPbthFbctoryConfigurbtionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An implementbtion for XPbth evblubtion thbt uses the JDK API.
 */
public clbss JDKXPbthAPI implements XPbthAPI {

    privbte XPbthFbctory xpf;

    privbte String xpbthStr;

    privbte XPbthExpression xpbthExpression;

    /**
     *  Use bn XPbth string to select b nodelist.
     *  XPbth nbmespbce prefixes bre resolved from the nbmespbceNode.
     *
     *  @pbrbm contextNode The node to stbrt sebrching from.
     *  @pbrbm xpbthnode
     *  @pbrbm str
     *  @pbrbm nbmespbceNode The node from which prefixes in the XPbth will be resolved to nbmespbces.
     *  @return A NodeIterbtor, should never be null.
     *
     * @throws TrbnsformerException
     */
    public NodeList selectNodeList(
        Node contextNode, Node xpbthnode, String str, Node nbmespbceNode
    ) throws TrbnsformerException {
        if (!str.equbls(xpbthStr) || xpbthExpression == null) {
            if (xpf == null) {
                xpf = XPbthFbctory.newInstbnce();
                try {
                    xpf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
                } cbtch (XPbthFbctoryConfigurbtionException ex) {
                    throw new TrbnsformerException("empty", ex);
                }
            }
            XPbth xpbth = xpf.newXPbth();
            xpbth.setNbmespbceContext(new DOMNbmespbceContext(nbmespbceNode));
            xpbthStr = str;
            try {
                xpbthExpression = xpbth.compile(xpbthStr);
            } cbtch (XPbthExpressionException ex) {
                throw new TrbnsformerException("empty", ex);
            }
        }
        try {
            return (NodeList)xpbthExpression.evblubte(contextNode, XPbthConstbnts.NODESET);
        } cbtch (XPbthExpressionException ex) {
            throw new TrbnsformerException("empty", ex);
        }
    }

    /**
     * Evblubte bn XPbth string bnd return true if the output is to be included or not.
     *  @pbrbm contextNode The node to stbrt sebrching from.
     *  @pbrbm xpbthnode The XPbth node
     *  @pbrbm str The XPbth expression
     *  @pbrbm nbmespbceNode The node from which prefixes in the XPbth will be resolved to nbmespbces.
     */
    public boolebn evblubte(Node contextNode, Node xpbthnode, String str, Node nbmespbceNode)
        throws TrbnsformerException {
        if (!str.equbls(xpbthStr) || xpbthExpression == null) {
            if (xpf == null) {
                xpf = XPbthFbctory.newInstbnce();
                try {
                    xpf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
                } cbtch (XPbthFbctoryConfigurbtionException ex) {
                    throw new TrbnsformerException("empty", ex);
                }
            }
            XPbth xpbth = xpf.newXPbth();
            xpbth.setNbmespbceContext(new DOMNbmespbceContext(nbmespbceNode));
            xpbthStr = str;
            try {
                xpbthExpression = xpbth.compile(xpbthStr);
            } cbtch (XPbthExpressionException ex) {
                throw new TrbnsformerException("empty", ex);
            }
        }
        try {
            Boolebn result = (Boolebn)xpbthExpression.evblubte(contextNode, XPbthConstbnts.BOOLEAN);
            return result.boolebnVblue();
        } cbtch (XPbthExpressionException ex) {
            throw new TrbnsformerException("empty", ex);
        }
    }

    /**
     * Clebr bny context informbtion from this object
     */
    public void clebr() {
        xpbthStr = null;
        xpbthExpression = null;
        xpf = null;
    }

}
