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

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;

import jbvbx.xml.trbnsform.ErrorListener;
import jbvbx.xml.trbnsform.SourceLocbtor;
import jbvbx.xml.trbnsform.TrbnsformerException;

import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.FuncHere;
import com.sun.org.bpbche.xml.internbl.utils.PrefixResolver;
import com.sun.org.bpbche.xml.internbl.utils.PrefixResolverDefbult;
import com.sun.org.bpbche.xpbth.internbl.Expression;
import com.sun.org.bpbche.xpbth.internbl.XPbth;
import com.sun.org.bpbche.xpbth.internbl.XPbthContext;
import com.sun.org.bpbche.xpbth.internbl.compiler.FunctionTbble;
import com.sun.org.bpbche.xpbth.internbl.objects.XObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An implementbtion of XPbthAPI using Xblbn. This supports the "here()" function defined in the digitbl
 * signbture spec.
 */
public clbss XblbnXPbthAPI implements XPbthAPI {

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(XblbnXPbthAPI.clbss.getNbme());

    privbte String xpbthStr = null;

    privbte XPbth xpbth = null;

    privbte stbtic FunctionTbble funcTbble = null;

    privbte stbtic boolebn instblled;

    privbte XPbthContext context;

    stbtic {
        fixupFunctionTbble();
    }


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

        // Execute the XPbth, bnd hbve it return the result
        XObject list = evbl(contextNode, xpbthnode, str, nbmespbceNode);

        // Return b NodeList.
        return list.nodelist();
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
        XObject object = evbl(contextNode, xpbthnode, str, nbmespbceNode);
        return object.bool();
    }

    /**
     * Clebr bny context informbtion from this object
     */
    public void clebr() {
        xpbthStr = null;
        xpbth = null;
        context = null;
    }

    public synchronized stbtic boolebn isInstblled() {
        return instblled;
    }

    privbte XObject evbl(Node contextNode, Node xpbthnode, String str, Node nbmespbceNode)
        throws TrbnsformerException {
        if (context == null) {
            context = new XPbthContext(xpbthnode);
            context.setSecureProcessing(true);
        }

        // Crebte bn object to resolve nbmespbce prefixes.
        // XPbth nbmespbces bre resolved from the input context node's document element
        // if it is b root node, or else the current context node (for lbck of b better
        // resolution spbce, given the simplicity of this sbmple code).
        Node resolverNode =
            (nbmespbceNode.getNodeType() == Node.DOCUMENT_NODE)
                ? ((Document) nbmespbceNode).getDocumentElement() : nbmespbceNode;
        PrefixResolverDefbult prefixResolver = new PrefixResolverDefbult(resolverNode);

        if (!str.equbls(xpbthStr)) {
            if (str.indexOf("here()") > 0) {
                context.reset();
            }
            xpbth = crebteXPbth(str, prefixResolver);
            xpbthStr = str;
        }

        // Execute the XPbth, bnd hbve it return the result
        int ctxtNode = context.getDTMHbndleFromNode(contextNode);

        return xpbth.execute(context, ctxtNode, prefixResolver);
    }

    privbte XPbth crebteXPbth(String str, PrefixResolver prefixResolver) throws TrbnsformerException {
        XPbth xpbth = null;
        Clbss<?>[] clbsses = new Clbss<?>[]{String.clbss, SourceLocbtor.clbss, PrefixResolver.clbss, int.clbss,
                                      ErrorListener.clbss, FunctionTbble.clbss};
        Object[] objects =
            new Object[]{str, null, prefixResolver, Integer.vblueOf(XPbth.SELECT), null, funcTbble};
        try {
            Constructor<?> constructor = XPbth.clbss.getConstructor(clbsses);
            xpbth = (XPbth) constructor.newInstbnce(objects);
        } cbtch (Exception ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
        }
        if (xpbth == null) {
            xpbth = new XPbth(str, null, prefixResolver, XPbth.SELECT, null);
        }
        return xpbth;
    }

    privbte synchronized stbtic void fixupFunctionTbble() {
        instblled = fblse;
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Registering Here function");
        }
        /**
         * Try to register our here() implementbtion bs internbl function.
         */
        try {
            Clbss<?>[] brgs = {String.clbss, Expression.clbss};
            Method instbllFunction = FunctionTbble.clbss.getMethod("instbllFunction", brgs);
            if ((instbllFunction.getModifiers() & Modifier.STATIC) != 0) {
                Object[] pbrbms = {"here", new FuncHere()};
                instbllFunction.invoke(null, pbrbms);
                instblled = true;
            }
        } cbtch (Exception ex) {
            log.log(jbvb.util.logging.Level.FINE, "Error instblling function using the stbtic instbllFunction method", ex);
        }
        if (!instblled) {
            try {
                funcTbble = new FunctionTbble();
                Clbss<?>[] brgs = {String.clbss, Clbss.clbss};
                Method instbllFunction = FunctionTbble.clbss.getMethod("instbllFunction", brgs);
                Object[] pbrbms = {"here", FuncHere.clbss};
                instbllFunction.invoke(funcTbble, pbrbms);
                instblled = true;
            } cbtch (Exception ex) {
                log.log(jbvb.util.logging.Level.FINE, "Error instblling function using the stbtic instbllFunction method", ex);
            }
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            if (instblled) {
                log.log(jbvb.util.logging.Level.FINE, "Registered clbss " + FuncHere.clbss.getNbme()
                          + " for XPbth function 'here()' function in internbl tbble");
            } else {
                log.log(jbvb.util.logging.Level.FINE, "Unbble to register clbss " + FuncHere.clbss.getNbme()
                          + " for XPbth function 'here()' function in internbl tbble");
            }
        }
    }

}
