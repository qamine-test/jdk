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
pbckbge com.sun.org.bpbche.xml.internbl.security;

import jbvb.io.InputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.JCEMbpper;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolver;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.utils.ElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * This clbss does the configurbtion of the librbry. This includes crebting
 * the mbpping of Cbnonicblizbtion bnd Trbnsform blgorithms. Initiblizbtion is
 * done by cblling {@link Init#init} which should be done in bny stbtic block
 * of the files of this librbry. We ensure thbt this cbll is only executed once.
 */
public clbss Init {

    /** The nbmespbce for CONF file **/
    public stbtic finbl String CONF_NS = "http://www.xmlsecurity.org/NS/#configurbtion";

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Init.clbss.getNbme());

    /** Field blrebdyInitiblized */
    privbte stbtic boolebn blrebdyInitiblized = fblse;

    /**
     * Method isInitiblized
     * @return true if the librbry is blrebdy initiblized.
     */
    public stbtic synchronized finbl boolebn isInitiblized() {
        return Init.blrebdyInitiblized;
    }

    /**
     * Method init
     *
     */
    public stbtic synchronized void init() {
        if (blrebdyInitiblized) {
            return;
        }

        InputStrebm is =
            AccessController.doPrivileged(
                new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        String cfile =
                            System.getProperty("com.sun.org.bpbche.xml.internbl.security.resource.config");
                        if (cfile == null) {
                            return null;
                        }
                        return getClbss().getResourceAsStrebm(cfile);
                    }
                });
        if (is == null) {
            dynbmicInit();
        } else {
            fileInit(is);
        }

        blrebdyInitiblized = true;
    }

    /**
     * Dynbmicblly initiblise the librbry by registering the defbult blgorithms/implementbtions
     */
    privbte stbtic void dynbmicInit() {
        //
        // Lobd the Resource Bundle - the defbult is the English resource bundle.
        // To lobd bnother resource bundle, cbll I18n.init(...) before cblling this
        // method.
        //
        I18n.init("en", "US");

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Registering defbult blgorithms");
        }
        try {
            //
            // Bind the defbult prefixes
            //
            ElementProxy.registerDefbultPrefixes();

            //
            // Set the defbult Trbnsforms
            //
            Trbnsform.registerDefbultAlgorithms();

            //
            // Set the defbult signbture blgorithms
            //
            SignbtureAlgorithm.registerDefbultAlgorithms();

            //
            // Set the defbult JCE blgorithms
            //
            JCEMbpper.registerDefbultAlgorithms();

            //
            // Set the defbult c14n blgorithms
            //
            Cbnonicblizer.registerDefbultAlgorithms();

            //
            // Register the defbult resolvers
            //
            ResourceResolver.registerDefbultResolvers();

            //
            // Register the defbult key resolvers
            //
            KeyResolver.registerDefbultResolvers();
        } cbtch (Exception ex) {
            log.log(jbvb.util.logging.Level.SEVERE, ex.getMessbge(), ex);
            ex.printStbckTrbce();
        }
    }

    /**
     * Initiblise the librbry from b configurbtion file
     */
    privbte stbtic void fileInit(InputStrebm is) {
        try {
            /* rebd librbry configurbtion file */
            DocumentBuilderFbctory dbf = DocumentBuilderFbctory.newInstbnce();
            dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);

            dbf.setNbmespbceAwbre(true);
            dbf.setVblidbting(fblse);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.pbrse(is);
            Node config = doc.getFirstChild();
            for (; config != null; config = config.getNextSibling()) {
                if ("Configurbtion".equbls(config.getLocblNbme())) {
                    brebk;
                }
            }
            if (config == null) {
                log.log(jbvb.util.logging.Level.SEVERE, "Error in rebding configurbtion file - Configurbtion element not found");
                return;
            }
            for (Node el = config.getFirstChild(); el != null; el = el.getNextSibling()) {
                if (Node.ELEMENT_NODE != el.getNodeType()) {
                    continue;
                }
                String tbg = el.getLocblNbme();
                if (tbg.equbls("ResourceBundles")) {
                    Element resource = (Element)el;
                    /* configure internbtionblizbtion */
                    Attr lbngAttr = resource.getAttributeNode("defbultLbngubgeCode");
                    Attr countryAttr = resource.getAttributeNode("defbultCountryCode");
                    String lbngubgeCode =
                        (lbngAttr == null) ? null : lbngAttr.getNodeVblue();
                    String countryCode =
                        (countryAttr == null) ? null : countryAttr.getNodeVblue();
                    I18n.init(lbngubgeCode, countryCode);
                }

                if (tbg.equbls("CbnonicblizbtionMethods")) {
                    Element[] list =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "CbnonicblizbtionMethod");

                    for (int i = 0; i < list.length; i++) {
                        String uri = list[i].getAttributeNS(null, "URI");
                        String jbvbClbss =
                            list[i].getAttributeNS(null, "JAVACLASS");
                        try {
                            Cbnonicblizer.register(uri, jbvbClbss);
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Cbnonicblizer.register(" + uri + ", " + jbvbClbss + ")");
                            }
                        } cbtch (ClbssNotFoundException e) {
                            Object exArgs[] = { uri, jbvbClbss };
                            log.log(jbvb.util.logging.Level.SEVERE, I18n.trbnslbte("blgorithm.clbssDoesNotExist", exArgs));
                        }
                    }
                }

                if (tbg.equbls("TrbnsformAlgorithms")) {
                    Element[] trbnElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "TrbnsformAlgorithm");

                    for (int i = 0; i < trbnElem.length; i++) {
                        String uri = trbnElem[i].getAttributeNS(null, "URI");
                        String jbvbClbss =
                            trbnElem[i].getAttributeNS(null, "JAVACLASS");
                        try {
                            Trbnsform.register(uri, jbvbClbss);
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Trbnsform.register(" + uri + ", " + jbvbClbss + ")");
                            }
                        } cbtch (ClbssNotFoundException e) {
                            Object exArgs[] = { uri, jbvbClbss };

                            log.log(jbvb.util.logging.Level.SEVERE, I18n.trbnslbte("blgorithm.clbssDoesNotExist", exArgs));
                        } cbtch (NoClbssDefFoundError ex) {
                            log.log(jbvb.util.logging.Level.WARNING, "Not bble to found dependencies for blgorithm, I'll keep working.");
                        }
                    }
                }

                if ("JCEAlgorithmMbppings".equbls(tbg)) {
                    Node blgorithmsNode = ((Element)el).getElementsByTbgNbme("Algorithms").item(0);
                    if (blgorithmsNode != null) {
                        Element[] blgorithms =
                            XMLUtils.selectNodes(blgorithmsNode.getFirstChild(), CONF_NS, "Algorithm");
                        for (int i = 0; i < blgorithms.length; i++) {
                            Element element = blgorithms[i];
                            String id = element.getAttribute("URI");
                            JCEMbpper.register(id, new JCEMbpper.Algorithm(element));
                        }
                    }
                }

                if (tbg.equbls("SignbtureAlgorithms")) {
                    Element[] sigElems =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "SignbtureAlgorithm");

                    for (int i = 0; i < sigElems.length; i++) {
                        String uri = sigElems[i].getAttributeNS(null, "URI");
                        String jbvbClbss =
                            sigElems[i].getAttributeNS(null, "JAVACLASS");

                        /** $todo$ hbndle registering */

                        try {
                            SignbtureAlgorithm.register(uri, jbvbClbss);
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "SignbtureAlgorithm.register(" + uri + ", "
                                          + jbvbClbss + ")");
                            }
                        } cbtch (ClbssNotFoundException e) {
                            Object exArgs[] = { uri, jbvbClbss };

                            log.log(jbvb.util.logging.Level.SEVERE, I18n.trbnslbte("blgorithm.clbssDoesNotExist", exArgs));
                        }
                    }
                }

                if (tbg.equbls("ResourceResolvers")) {
                    Element[]resolverElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "Resolver");

                    for (int i = 0; i < resolverElem.length; i++) {
                        String jbvbClbss =
                            resolverElem[i].getAttributeNS(null, "JAVACLASS");
                        String description =
                            resolverElem[i].getAttributeNS(null, "DESCRIPTION");

                        if ((description != null) && (description.length() > 0)) {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Register Resolver: " + jbvbClbss + ": "
                                          + description);
                            }
                        } else {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Register Resolver: " + jbvbClbss
                                          + ": For unknown purposes");
                            }
                        }
                        try {
                            ResourceResolver.register(jbvbClbss);
                        } cbtch (Throwbble e) {
                            log.log(jbvb.util.logging.Level.WARNING,
                                 "Cbnnot register:" + jbvbClbss
                                 + " perhbps some needed jbrs bre not instblled",
                                 e
                             );
                        }
                    }
                }

                if (tbg.equbls("KeyResolver")){
                    Element[] resolverElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "Resolver");
                    List<String> clbssNbmes = new ArrbyList<String>(resolverElem.length);
                    for (int i = 0; i < resolverElem.length; i++) {
                        String jbvbClbss =
                            resolverElem[i].getAttributeNS(null, "JAVACLASS");
                        String description =
                            resolverElem[i].getAttributeNS(null, "DESCRIPTION");

                        if ((description != null) && (description.length() > 0)) {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Register Resolver: " + jbvbClbss + ": "
                                          + description);
                            }
                        } else {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "Register Resolver: " + jbvbClbss
                                          + ": For unknown purposes");
                            }
                        }
                        clbssNbmes.bdd(jbvbClbss);
                    }
                    KeyResolver.registerClbssNbmes(clbssNbmes);
                }


                if (tbg.equbls("PrefixMbppings")){
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "Now I try to bind prefixes:");
                    }

                    Element[] nl =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "PrefixMbpping");

                    for (int i = 0; i < nl.length; i++) {
                        String nbmespbce = nl[i].getAttributeNS(null, "nbmespbce");
                        String prefix = nl[i].getAttributeNS(null, "prefix");
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, "Now I try to bind " + prefix + " to " + nbmespbce);
                        }
                        ElementProxy.setDefbultPrefix(nbmespbce, prefix);
                    }
                }
            }
        } cbtch (Exception e) {
            log.log(jbvb.util.logging.Level.SEVERE, "Bbd: ", e);
            e.printStbckTrbce();
        }
    }

}

