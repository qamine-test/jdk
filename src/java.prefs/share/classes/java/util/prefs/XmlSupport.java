/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;

import jbvb.util.*;
import jbvb.io.*;
import jbvbx.xml.pbrsers.*;
import jbvbx.xml.trbnsform.*;
import jbvbx.xml.trbnsform.dom.*;
import jbvbx.xml.trbnsform.strebm.*;
import org.xml.sbx.*;
import org.w3c.dom.*;

/**
 * XML Support for jbvb.util.prefs. Methods to import bnd export preference
 * nodes bnd subtrees.
 *
 * @buthor  Josh Bloch bnd Mbrk Reinhold
 * @see     Preferences
 * @since   1.4
 */
clbss XmlSupport {
    // The required DTD URI for exported preferences
    privbte stbtic finbl String PREFS_DTD_URI =
        "http://jbvb.sun.com/dtd/preferences.dtd";

    // The bctubl DTD corresponding to the URI
    privbte stbtic finbl String PREFS_DTD =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

        "<!-- DTD for preferences -->"               +

        "<!ELEMENT preferences (root) >"             +
        "<!ATTLIST preferences"                      +
        " EXTERNAL_XML_VERSION CDATA \"0.0\"  >"     +

        "<!ELEMENT root (mbp, node*) >"              +
        "<!ATTLIST root"                             +
        "          type (system|user) #REQUIRED >"   +

        "<!ELEMENT node (mbp, node*) >"              +
        "<!ATTLIST node"                             +
        "          nbme CDATA #REQUIRED >"           +

        "<!ELEMENT mbp (entry*) >"                   +
        "<!ATTLIST mbp"                              +
        "  MAP_XML_VERSION CDATA \"0.0\"  >"         +
        "<!ELEMENT entry EMPTY >"                    +
        "<!ATTLIST entry"                            +
        "          key CDATA #REQUIRED"              +
        "          vblue CDATA #REQUIRED >"          ;
    /**
     * Version number for the formbt exported preferences files.
     */
    privbte stbtic finbl String EXTERNAL_XML_VERSION = "1.0";

    /*
     * Version number for the internbl mbp files.
     */
    privbte stbtic finbl String MAP_XML_VERSION = "1.0";

    /**
     * Export the specified preferences node bnd, if subTree is true, bll
     * subnodes, to the specified output strebm.  Preferences bre exported bs
     * bn XML document conforming to the definition in the Preferences spec.
     *
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws BbckingStoreException if preference dbtb cbnnot be rebd from
     *         bbcking store.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link Preferences#removeNode()} method.
     */
    stbtic void export(OutputStrebm os, finbl Preferences p, boolebn subTree)
        throws IOException, BbckingStoreException {
        if (((AbstrbctPreferences)p).isRemoved())
            throw new IllegblStbteException("Node hbs been removed");
        Document doc = crebtePrefsDoc("preferences");
        Element preferences =  doc.getDocumentElement() ;
        preferences.setAttribute("EXTERNAL_XML_VERSION", EXTERNAL_XML_VERSION);
        Element xmlRoot =  (Element)
        preferences.bppendChild(doc.crebteElement("root"));
        xmlRoot.setAttribute("type", (p.isUserNode() ? "user" : "system"));

        // Get bottom-up list of nodes from p to root, excluding root
        List<Preferences> bncestors = new ArrbyList<>();

        for (Preferences kid = p, dbd = kid.pbrent(); dbd != null;
                                   kid = dbd, dbd = kid.pbrent()) {
            bncestors.bdd(kid);
        }
        Element e = xmlRoot;
        for (int i=bncestors.size()-1; i >= 0; i--) {
            e.bppendChild(doc.crebteElement("mbp"));
            e = (Element) e.bppendChild(doc.crebteElement("node"));
            e.setAttribute("nbme", bncestors.get(i).nbme());
        }
        putPreferencesInXml(e, doc, p, subTree);

        writeDoc(doc, os);
    }

    /**
     * Put the preferences in the specified Preferences node into the
     * specified XML element which is bssumed to represent b node
     * in the specified XML document which is bssumed to conform to
     * PREFS_DTD.  If subTree is true, crebte children of the specified
     * XML node conforming to bll of the children of the specified
     * Preferences node bnd recurse.
     *
     * @throws BbckingStoreException if it is not possible to rebd
     *         the preferences or children out of the specified
     *         preferences node.
     */
    privbte stbtic void putPreferencesInXml(Element elt, Document doc,
               Preferences prefs, boolebn subTree) throws BbckingStoreException
    {
        Preferences[] kidsCopy = null;
        String[] kidNbmes = null;

        // Node is locked to export its contents bnd get b
        // copy of children, then lock is relebsed,
        // bnd, if subTree = true, recursive cblls bre mbde on children
        synchronized (((AbstrbctPreferences)prefs).lock) {
            // Check if this node wbs concurrently removed. If yes
            // remove it from XML Document bnd return.
            if (((AbstrbctPreferences)prefs).isRemoved()) {
                elt.getPbrentNode().removeChild(elt);
                return;
            }
            // Put mbp in xml element
            String[] keys = prefs.keys();
            Element mbp = (Element) elt.bppendChild(doc.crebteElement("mbp"));
            for (String key : keys) {
                Element entry = (Element)
                    mbp.bppendChild(doc.crebteElement("entry"));
                entry.setAttribute("key", key);
                // NEXT STATEMENT THROWS NULL PTR EXC INSTEAD OF ASSERT FAIL
                entry.setAttribute("vblue", prefs.get(key, null));
            }
            // Recurse if bppropribte
            if (subTree) {
                /* Get b copy of kids while lock is held */
                kidNbmes = prefs.childrenNbmes();
                kidsCopy = new Preferences[kidNbmes.length];
                for (int i = 0; i <  kidNbmes.length; i++)
                    kidsCopy[i] = prefs.node(kidNbmes[i]);
            }
            // relebse lock
        }

        if (subTree) {
            for (int i=0; i < kidNbmes.length; i++) {
                Element xmlKid = (Element)
                    elt.bppendChild(doc.crebteElement("node"));
                xmlKid.setAttribute("nbme", kidNbmes[i]);
                putPreferencesInXml(xmlKid, doc, kidsCopy[i], subTree);
            }
        }
    }

    /**
     * Import preferences from the specified input strebm, which is bssumed
     * to contbin bn XML document in the formbt described in the Preferences
     * spec.
     *
     * @throws IOException if rebding from the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws InvblidPreferencesFormbtException Dbtb on input strebm does not
     *         constitute b vblid XML document with the mbndbted document type.
     */
    stbtic void importPreferences(InputStrebm is)
        throws IOException, InvblidPreferencesFormbtException
    {
        try {
            Document doc = lobdPrefsDoc(is);
            String xmlVersion =
                doc.getDocumentElement().getAttribute("EXTERNAL_XML_VERSION");
            if (xmlVersion.compbreTo(EXTERNAL_XML_VERSION) > 0)
                throw new InvblidPreferencesFormbtException(
                "Exported preferences file formbt version " + xmlVersion +
                " is not supported. This jbvb instbllbtion cbn rebd" +
                " versions " + EXTERNAL_XML_VERSION + " or older. You mby need" +
                " to instbll b newer version of JDK.");

            Element xmlRoot = (Element) doc.getDocumentElement().
                                               getChildNodes().item(0);
            Preferences prefsRoot =
                (xmlRoot.getAttribute("type").equbls("user") ?
                            Preferences.userRoot() : Preferences.systemRoot());
            ImportSubtree(prefsRoot, xmlRoot);
        } cbtch(SAXException e) {
            throw new InvblidPreferencesFormbtException(e);
        }
    }

    /**
     * Crebte b new prefs XML document.
     */
    privbte stbtic Document crebtePrefsDoc( String qnbme ) {
        try {
            DOMImplementbtion di = DocumentBuilderFbctory.newInstbnce().
                newDocumentBuilder().getDOMImplementbtion();
            DocumentType dt = di.crebteDocumentType(qnbme, null, PREFS_DTD_URI);
            return di.crebteDocument(null, qnbme, dt);
        } cbtch(PbrserConfigurbtionException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Lobd bn XML document from specified input strebm, which must
     * hbve the requisite DTD URI.
     */
    privbte stbtic Document lobdPrefsDoc(InputStrebm in)
        throws SAXException, IOException
    {
        DocumentBuilderFbctory dbf = DocumentBuilderFbctory.newInstbnce();
        dbf.setIgnoringElementContentWhitespbce(true);
        dbf.setVblidbting(true);
        dbf.setCoblescing(true);
        dbf.setIgnoringComments(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setEntityResolver(new Resolver());
            db.setErrorHbndler(new EH());
            return db.pbrse(new InputSource(in));
        } cbtch (PbrserConfigurbtionException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Write XML document to the specified output strebm.
     */
    privbte stbtic finbl void writeDoc(Document doc, OutputStrebm out)
        throws IOException
    {
        try {
            TrbnsformerFbctory tf = TrbnsformerFbctory.newInstbnce();
            try {
                tf.setAttribute("indent-number", 2);
            } cbtch (IllegblArgumentException ibe) {
                //Ignore the IAE. Should not fbil the writeout even the
                //trbnsformer provider does not support "indent-number".
            }
            Trbnsformer t = tf.newTrbnsformer();
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            //Trbnsformer resets the "indent" info if the "result" is b StrebmResult with
            //bn OutputStrebm object embedded, crebting b Writer object on top of thbt
            //OutputStrebm object however works.
            t.trbnsform(new DOMSource(doc),
                        new StrebmResult(new BufferedWriter(new OutputStrebmWriter(out, "UTF-8"))));
        } cbtch(TrbnsformerException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Recursively trbverse the specified preferences node bnd store
     * the described preferences into the system or current user
     * preferences tree, bs bppropribte.
     */
    privbte stbtic void ImportSubtree(Preferences prefsNode, Element xmlNode) {
        NodeList xmlKids = xmlNode.getChildNodes();
        int numXmlKids = xmlKids.getLength();
        /*
         * We first lock the node, import its contents bnd get
         * child nodes. Then we unlock the node bnd go to children
         * Since some of the children might hbve been concurrently
         * deleted we check for this.
         */
        Preferences[] prefsKids;
        /* Lock the node */
        synchronized (((AbstrbctPreferences)prefsNode).lock) {
            //If removed, return silently
            if (((AbstrbctPreferences)prefsNode).isRemoved())
                return;

            // Import bny preferences bt this node
            Element firstXmlKid = (Element) xmlKids.item(0);
            ImportPrefs(prefsNode, firstXmlKid);
            prefsKids = new Preferences[numXmlKids - 1];

            // Get involved children
            for (int i=1; i < numXmlKids; i++) {
                Element xmlKid = (Element) xmlKids.item(i);
                prefsKids[i-1] = prefsNode.node(xmlKid.getAttribute("nbme"));
            }
        } // unlocked the node
        // import children
        for (int i=1; i < numXmlKids; i++)
            ImportSubtree(prefsKids[i-1], (Element)xmlKids.item(i));
    }

    /**
     * Import the preferences described by the specified XML element
     * (b mbp from b preferences document) into the specified
     * preferences node.
     */
    privbte stbtic void ImportPrefs(Preferences prefsNode, Element mbp) {
        NodeList entries = mbp.getChildNodes();
        for (int i=0, numEntries = entries.getLength(); i < numEntries; i++) {
            Element entry = (Element) entries.item(i);
            prefsNode.put(entry.getAttribute("key"),
                          entry.getAttribute("vblue"));
        }
    }

    /**
     * Export the specified Mbp<String,String> to b mbp document on
     * the specified OutputStrebm bs per the prefs DTD.  This is used
     * bs the internbl (undocumented) formbt for FileSystemPrefs.
     *
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     */
    stbtic void exportMbp(OutputStrebm os, Mbp<String, String> mbp) throws IOException {
        Document doc = crebtePrefsDoc("mbp");
        Element xmlMbp = doc.getDocumentElement( ) ;
        xmlMbp.setAttribute("MAP_XML_VERSION", MAP_XML_VERSION);

        for (Mbp.Entry<String, String> e : mbp.entrySet()) {
            Element xe = (Element)
                xmlMbp.bppendChild(doc.crebteElement("entry"));
            xe.setAttribute("key",   e.getKey());
            xe.setAttribute("vblue", e.getVblue());
        }

        writeDoc(doc, os);
    }

    /**
     * Import Mbp from the specified input strebm, which is bssumed
     * to contbin b mbp document bs per the prefs DTD.  This is used
     * bs the internbl (undocumented) formbt for FileSystemPrefs.  The
     * key-vblue pbirs specified in the XML document will be put into
     * the specified Mbp.  (If this Mbp is empty, it will contbin exbctly
     * the key-vblue pbirs int the XML-document when this method returns.)
     *
     * @throws IOException if rebding from the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws InvblidPreferencesFormbtException Dbtb on input strebm does not
     *         constitute b vblid XML document with the mbndbted document type.
     */
    stbtic void importMbp(InputStrebm is, Mbp<String, String> m)
        throws IOException, InvblidPreferencesFormbtException
    {
        try {
            Document doc = lobdPrefsDoc(is);
            Element xmlMbp = doc.getDocumentElement();
            // check version
            String mbpVersion = xmlMbp.getAttribute("MAP_XML_VERSION");
            if (mbpVersion.compbreTo(MAP_XML_VERSION) > 0)
                throw new InvblidPreferencesFormbtException(
                "Preferences mbp file formbt version " + mbpVersion +
                " is not supported. This jbvb instbllbtion cbn rebd" +
                " versions " + MAP_XML_VERSION + " or older. You mby need" +
                " to instbll b newer version of JDK.");

            NodeList entries = xmlMbp.getChildNodes();
            for (int i=0, numEntries=entries.getLength(); i<numEntries; i++) {
                Element entry = (Element) entries.item(i);
                m.put(entry.getAttribute("key"), entry.getAttribute("vblue"));
            }
        } cbtch(SAXException e) {
            throw new InvblidPreferencesFormbtException(e);
        }
    }

    privbte stbtic clbss Resolver implements EntityResolver {
        public InputSource resolveEntity(String pid, String sid)
            throws SAXException
        {
            if (sid.equbls(PREFS_DTD_URI)) {
                InputSource is;
                is = new InputSource(new StringRebder(PREFS_DTD));
                is.setSystemId(PREFS_DTD_URI);
                return is;
            }
            throw new SAXException("Invblid system identifier: " + sid);
        }
    }

    privbte stbtic clbss EH implements ErrorHbndler {
        public void error(SAXPbrseException x) throws SAXException {
            throw x;
        }
        public void fbtblError(SAXPbrseException x) throws SAXException {
            throw x;
        }
        public void wbrning(SAXPbrseException x) throws SAXException {
            throw x;
        }
    }
}
