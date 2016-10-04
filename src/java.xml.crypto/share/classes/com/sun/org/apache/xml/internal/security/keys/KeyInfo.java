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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys;

import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.encryption.EncryptedKey;
import com.sun.org.bpbche.xml.internbl.security.encryption.XMLCipher;
import com.sun.org.bpbche.xml.internbl.security.encryption.XMLEncryptionException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.DEREncodedKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.content.KeyInfoReference;
import com.sun.org.bpbche.xml.internbl.security.keys.content.KeyNbme;
import com.sun.org.bpbche.xml.internbl.security.keys.content.KeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.content.MgmtDbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.PGPDbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.RetrievblMethod;
import com.sun.org.bpbche.xml.internbl.security.keys.content.SPKIDbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.X509Dbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues.DSAKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues.RSAKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.EncryptionConstbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This clbss stbnd for KeyInfo Element thbt mby contbin keys, nbmes,
 * certificbtes bnd other public key mbnbgement informbtion,
 * such bs in-bbnd key distribution or key bgreement dbtb.
 * <BR />
 * KeyInfo Element hbs two bbsic functions:
 * One is KeyResolve for getting the public key in signbture vblidbtion processing.
 * the other one is toElement for getting the element in signbture generbtion processing.
 * <BR />
 * The <CODE>lengthXXX()</CODE> methods provide bccess to the internbl Key
 * objects:
 * <UL>
 * <LI>If the <CODE>KeyInfo</CODE> wbs constructed from bn Element
 * (Signbture verificbtion), the <CODE>lengthXXX()</CODE> methods sebrches
 * for child elements of <CODE>ds:KeyInfo</CODE> for known types. </LI>
 * <LI>If the <CODE>KeyInfo</CODE> wbs constructed from scrbtch (during
 * Signbture generbtion), the <CODE>lengthXXX()</CODE> methods return the number
 * of <CODE>XXXs</CODE> objects blrebdy pbssed to the KeyInfo</LI>
 * </UL>
 * <BR />
 * The <CODE>bddXXX()</CODE> methods bre used for bdding Objects of the
 * bppropribte type to the <CODE>KeyInfo</CODE>. This is used during signbture
 * generbtion.
 * <BR />
 * The <CODE>itemXXX(int i)</CODE> methods return the i'th object of the
 * corresponding type.
 * <BR />
 * The <CODE>contbinsXXX()</CODE> methods return <I>whether</I> the KeyInfo
 * contbins the corresponding type.
 *
 */
public clbss KeyInfo extends SignbtureElementProxy {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(KeyInfo.clbss.getNbme());

    // We need bt lebst one StorbgeResolver otherwise
    // the KeyResolvers would not be cblled.
    // The defbult StorbgeResolver is null.

    privbte List<X509Dbtb> x509Dbtbs = null;
    privbte List<EncryptedKey> encryptedKeys = null;

    privbte stbtic finbl List<StorbgeResolver> nullList;
    stbtic {
        List<StorbgeResolver> list = new ArrbyList<StorbgeResolver>(1);
        list.bdd(null);
        nullList = jbvb.util.Collections.unmodifibbleList(list);
    }

    /** Field storbgeResolvers */
    privbte List<StorbgeResolver> storbgeResolvers = nullList;

    /**
     * Stores the individubl (per-KeyInfo) {@link KeyResolverSpi}s
     */
    privbte List<KeyResolverSpi> internblKeyResolvers = new ArrbyList<KeyResolverSpi>();

    privbte boolebn secureVblidbtion;

    /**
     * Constructor KeyInfo
     * @pbrbm doc
     */
    public KeyInfo(Document doc) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyInfo
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public KeyInfo(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);

        Attr bttr = element.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            element.setIdAttributeNode(bttr, true);
        }
    }

    /**
     * Set whether secure processing is enbbled or not. The defbult is fblse.
     */
    public void setSecureVblidbtion(boolebn secureVblidbtion) {
        this.secureVblidbtion = secureVblidbtion;
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm Id ID
     */
    public void setId(String id) {
        if (id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> bttribute
     *
     * @return the <code>Id</code> bttribute
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Method bddKeyNbme
     *
     * @pbrbm keynbmeString
     */
    public void bddKeyNbme(String keynbmeString) {
        this.bdd(new KeyNbme(this.doc, keynbmeString));
    }

    /**
     * Method bdd
     *
     * @pbrbm keynbme
     */
    public void bdd(KeyNbme keynbme) {
        this.constructionElement.bppendChild(keynbme.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddKeyVblue
     *
     * @pbrbm pk
     */
    public void bddKeyVblue(PublicKey pk) {
        this.bdd(new KeyVblue(this.doc, pk));
    }

    /**
     * Method bddKeyVblue
     *
     * @pbrbm unknownKeyVblueElement
     */
    public void bddKeyVblue(Element unknownKeyVblueElement) {
        this.bdd(new KeyVblue(this.doc, unknownKeyVblueElement));
    }

    /**
     * Method bdd
     *
     * @pbrbm dsbkeyvblue
     */
    public void bdd(DSAKeyVblue dsbkeyvblue) {
        this.bdd(new KeyVblue(this.doc, dsbkeyvblue));
    }

    /**
     * Method bdd
     *
     * @pbrbm rsbkeyvblue
     */
    public void bdd(RSAKeyVblue rsbkeyvblue) {
        this.bdd(new KeyVblue(this.doc, rsbkeyvblue));
    }

    /**
     * Method bdd
     *
     * @pbrbm pk
     */
    public void bdd(PublicKey pk) {
        this.bdd(new KeyVblue(this.doc, pk));
    }

    /**
     * Method bdd
     *
     * @pbrbm keyvblue
     */
    public void bdd(KeyVblue keyvblue) {
        this.constructionElement.bppendChild(keyvblue.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddMgmtDbtb
     *
     * @pbrbm mgmtdbtb
     */
    public void bddMgmtDbtb(String mgmtdbtb) {
        this.bdd(new MgmtDbtb(this.doc, mgmtdbtb));
    }

    /**
     * Method bdd
     *
     * @pbrbm mgmtdbtb
     */
    public void bdd(MgmtDbtb mgmtdbtb) {
        this.constructionElement.bppendChild(mgmtdbtb.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddPGPDbtb
     *
     * @pbrbm pgpdbtb
     */
    public void bdd(PGPDbtb pgpdbtb) {
        this.constructionElement.bppendChild(pgpdbtb.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddRetrievblMethod
     *
     * @pbrbm uri
     * @pbrbm trbnsforms
     * @pbrbm Type
     */
    public void bddRetrievblMethod(String uri, Trbnsforms trbnsforms, String Type) {
        this.bdd(new RetrievblMethod(this.doc, uri, trbnsforms, Type));
    }

    /**
     * Method bdd
     *
     * @pbrbm retrievblmethod
     */
    public void bdd(RetrievblMethod retrievblmethod) {
        this.constructionElement.bppendChild(retrievblmethod.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bdd
     *
     * @pbrbm spkidbtb
     */
    public void bdd(SPKIDbtb spkidbtb) {
        this.constructionElement.bppendChild(spkidbtb.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddX509Dbtb
     *
     * @pbrbm x509dbtb
     */
    public void bdd(X509Dbtb x509dbtb) {
        if (x509Dbtbs == null) {
            x509Dbtbs = new ArrbyList<X509Dbtb>();
        }
        x509Dbtbs.bdd(x509dbtb);
        this.constructionElement.bppendChild(x509dbtb.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddEncryptedKey
     *
     * @pbrbm encryptedKey
     * @throws XMLEncryptionException
     */

    public void bdd(EncryptedKey encryptedKey) throws XMLEncryptionException {
        if (encryptedKeys == null) {
            encryptedKeys = new ArrbyList<EncryptedKey>();
        }
        encryptedKeys.bdd(encryptedKey);
        XMLCipher cipher = XMLCipher.getInstbnce();
        this.constructionElement.bppendChild(cipher.mbrtibl(encryptedKey));
    }

    /**
     * Method bddDEREncodedKeyVblue
     *
     * @pbrbm pk
     * @throws XMLSecurityException
     */
    public void bddDEREncodedKeyVblue(PublicKey pk) throws XMLSecurityException {
        this.bdd(new DEREncodedKeyVblue(this.doc, pk));
    }

    /**
     * Method bdd
     *
     * @pbrbm derEncodedKeyVblue
     */
    public void bdd(DEREncodedKeyVblue derEncodedKeyVblue) {
        this.constructionElement.bppendChild(derEncodedKeyVblue.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddKeyInfoReference
     *
     * @pbrbm URI
     * @throws XMLSecurityException
     */
    public void bddKeyInfoReference(String URI) throws XMLSecurityException {
        this.bdd(new KeyInfoReference(this.doc, URI));
    }

    /**
     * Method bdd
     *
     * @pbrbm keyInfoReference
     */
    public void bdd(KeyInfoReference keyInfoReference) {
        this.constructionElement.bppendChild(keyInfoReference.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddUnknownElement
     *
     * @pbrbm element
     */
    public void bddUnknownElement(Element element) {
        this.constructionElement.bppendChild(element);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method lengthKeyNbme
     *
     * @return the number of the KeyNbme tbgs
     */
    public int lengthKeyNbme() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_KEYNAME);
    }

    /**
     * Method lengthKeyVblue
     *
     *@return the number of the KeyVblue tbgs
     */
    public int lengthKeyVblue() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_KEYVALUE);
    }

    /**
     * Method lengthMgmtDbtb
     *
     *@return the number of the MgmtDbtb tbgs
     */
    public int lengthMgmtDbtb() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_MGMTDATA);
    }

    /**
     * Method lengthPGPDbtb
     *
     *@return the number of the PGPDbt. tbgs
     */
    public int lengthPGPDbtb() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_PGPDATA);
    }

    /**
     * Method lengthRetrievblMethod
     *
     *@return the number of the RetrievblMethod tbgs
     */
    public int lengthRetrievblMethod() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_RETRIEVALMETHOD);
    }

    /**
     * Method lengthSPKIDbtb
     *
     *@return the number of the SPKIDbtb tbgs
     */
    public int lengthSPKIDbtb() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_SPKIDATA);
    }

    /**
     * Method lengthX509Dbtb
     *
     *@return the number of the X509Dbtb tbgs
     */
    public int lengthX509Dbtb() {
        if (x509Dbtbs != null) {
            return x509Dbtbs.size();
        }
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509DATA);
    }

    /**
     * Method lengthDEREncodedKeyVblue
     *
     *@return the number of the DEREncodedKeyVblue tbgs
     */
    public int lengthDEREncodedKeyVblue() {
        return this.length(Constbnts.SignbtureSpec11NS, Constbnts._TAG_DERENCODEDKEYVALUE);
    }

    /**
     * Method lengthKeyInfoReference
     *
     *@return the number of the KeyInfoReference tbgs
     */
    public int lengthKeyInfoReference() {
        return this.length(Constbnts.SignbtureSpec11NS, Constbnts._TAG_KEYINFOREFERENCE);
    }

    /**
     * Method lengthUnknownElement
     * NOTE possibly buggy.
     * @return the number of the UnknownElement tbgs
     */
    public int lengthUnknownElement() {
        int res = 0;
        NodeList nl = this.constructionElement.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node current = nl.item(i);

            /**
             * $todo$ using this method, we don't see unknown Elements
             *  from Signbture NS; revisit
             */
            if ((current.getNodeType() == Node.ELEMENT_NODE)
                && current.getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)) {
                res++;
            }
        }

        return res;
    }

    /**
     * Method itemKeyNbme
     *
     * @pbrbm i
     * @return the bsked KeyNbme element, null if the index is too big
     * @throws XMLSecurityException
     */
    public KeyNbme itemKeyNbme(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_KEYNAME, i);

        if (e != null) {
            return new KeyNbme(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemKeyVblue
     *
     * @pbrbm i
     * @return the bsked KeyVblue element, null if the index is too big
     * @throws XMLSecurityException
     */
    public KeyVblue itemKeyVblue(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_KEYVALUE, i);

        if (e != null) {
            return new KeyVblue(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemMgmtDbtb
     *
     * @pbrbm i
     * @return the bsked MgmtDbtb element, null if the index is too big
     * @throws XMLSecurityException
     */
    public MgmtDbtb itemMgmtDbtb(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_MGMTDATA, i);

        if (e != null) {
            return new MgmtDbtb(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemPGPDbtb
     *
     * @pbrbm i
     * @return the bsked PGPDbtb element, null if the index is too big
     * @throws XMLSecurityException
     */
    public PGPDbtb itemPGPDbtb(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_PGPDATA, i);

        if (e != null) {
            return new PGPDbtb(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemRetrievblMethod
     *
     * @pbrbm i
     *@return the bsked RetrievblMethod element, null if the index is too big
     * @throws XMLSecurityException
     */
    public RetrievblMethod itemRetrievblMethod(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_RETRIEVALMETHOD, i);

        if (e != null) {
            return new RetrievblMethod(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemSPKIDbtb
     *
     * @pbrbm i
     * @return the bsked SPKIDbtb element, null if the index is too big
     * @throws XMLSecurityException
     */
    public SPKIDbtb itemSPKIDbtb(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_SPKIDATA, i);

        if (e != null) {
            return new SPKIDbtb(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemX509Dbtb
     *
     * @pbrbm i
     * @return the bsked X509Dbtb element, null if the index is too big
     * @throws XMLSecurityException
     */
    public X509Dbtb itemX509Dbtb(int i) throws XMLSecurityException {
        if (x509Dbtbs != null) {
            return x509Dbtbs.get(i);
        }
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509DATA, i);

        if (e != null) {
            return new X509Dbtb(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemEncryptedKey
     *
     * @pbrbm i
     * @return the bsked EncryptedKey element, null if the index is too big
     * @throws XMLSecurityException
     */
    public EncryptedKey itemEncryptedKey(int i) throws XMLSecurityException {
        if (encryptedKeys != null) {
            return encryptedKeys.get(i);
        }
        Element e =
            XMLUtils.selectXencNode(
                this.constructionElement.getFirstChild(), EncryptionConstbnts._TAG_ENCRYPTEDKEY, i);

        if (e != null) {
            XMLCipher cipher = XMLCipher.getInstbnce();
            cipher.init(XMLCipher.UNWRAP_MODE, null);
            return cipher.lobdEncryptedKey(e);
        }
        return null;
    }

    /**
     * Method itemDEREncodedKeyVblue
     *
     * @pbrbm i
     * @return the bsked DEREncodedKeyVblue element, null if the index is too big
     * @throws XMLSecurityException
     */
    public DEREncodedKeyVblue itemDEREncodedKeyVblue(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDs11Node(
                this.constructionElement.getFirstChild(), Constbnts._TAG_DERENCODEDKEYVALUE, i);

        if (e != null) {
            return new DEREncodedKeyVblue(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemKeyInfoReference
     *
     * @pbrbm i
     * @return the bsked KeyInfoReference element, null if the index is too big
     * @throws XMLSecurityException
     */
    public KeyInfoReference itemKeyInfoReference(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDs11Node(
                this.constructionElement.getFirstChild(), Constbnts._TAG_KEYINFOREFERENCE, i);

        if (e != null) {
            return new KeyInfoReference(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemUnknownElement
     *
     * @pbrbm i index
     * @return the element number of the unknown elements
     */
    public Element itemUnknownElement(int i) {
        NodeList nl = this.constructionElement.getChildNodes();
        int res = 0;

        for (int j = 0; j < nl.getLength(); j++) {
            Node current = nl.item(j);

            /**
             * $todo$ using this method, we don't see unknown Elements
             *  from Signbture NS; revisit
             */
            if ((current.getNodeType() == Node.ELEMENT_NODE)
                && current.getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)) {
                res++;

                if (res == i) {
                    return (Element) current;
                }
            }
        }

        return null;
    }

    /**
     * Method isEmpty
     *
     * @return true if the element hbs no descendbnts.
     */
    public boolebn isEmpty() {
        return this.constructionElement.getFirstChild() == null;
    }

    /**
     * Method contbinsKeyNbme
     *
     * @return If the KeyInfo contbins b KeyNbme node
     */
    public boolebn contbinsKeyNbme() {
        return this.lengthKeyNbme() > 0;
    }

    /**
     * Method contbinsKeyVblue
     *
     * @return If the KeyInfo contbins b KeyVblue node
     */
    public boolebn contbinsKeyVblue() {
        return this.lengthKeyVblue() > 0;
    }

    /**
     * Method contbinsMgmtDbtb
     *
     * @return If the KeyInfo contbins b MgmtDbtb node
     */
    public boolebn contbinsMgmtDbtb() {
        return this.lengthMgmtDbtb() > 0;
    }

    /**
     * Method contbinsPGPDbtb
     *
     * @return If the KeyInfo contbins b PGPDbtb node
     */
    public boolebn contbinsPGPDbtb() {
        return this.lengthPGPDbtb() > 0;
    }

    /**
     * Method contbinsRetrievblMethod
     *
     * @return If the KeyInfo contbins b RetrievblMethod node
     */
    public boolebn contbinsRetrievblMethod() {
        return this.lengthRetrievblMethod() > 0;
    }

    /**
     * Method contbinsSPKIDbtb
     *
     * @return If the KeyInfo contbins b SPKIDbtb node
     */
    public boolebn contbinsSPKIDbtb() {
        return this.lengthSPKIDbtb() > 0;
    }

    /**
     * Method contbinsUnknownElement
     *
     * @return If the KeyInfo contbins b UnknownElement node
     */
    public boolebn contbinsUnknownElement() {
        return this.lengthUnknownElement() > 0;
    }

    /**
     * Method contbinsX509Dbtb
     *
     * @return If the KeyInfo contbins b X509Dbtb node
     */
    public boolebn contbinsX509Dbtb() {
        return this.lengthX509Dbtb() > 0;
    }

    /**
     * Method contbinsDEREncodedKeyVblue
     *
     * @return If the KeyInfo contbins b DEREncodedKeyVblue node
     */
    public boolebn contbinsDEREncodedKeyVblue() {
        return this.lengthDEREncodedKeyVblue() > 0;
    }

    /**
     * Method contbinsKeyInfoReference
     *
     * @return If the KeyInfo contbins b KeyInfoReference node
     */
    public boolebn contbinsKeyInfoReference() {
        return this.lengthKeyInfoReference() > 0;
    }

    /**
     * This method returns the public key.
     *
     * @return If the KeyInfo contbins b PublicKey node
     * @throws KeyResolverException
     */
    public PublicKey getPublicKey() throws KeyResolverException {
        PublicKey pk = this.getPublicKeyFromInternblResolvers();

        if (pk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b key using the per-KeyInfo key resolvers");
            }

            return pk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b key using the per-KeyInfo key resolvers");
        }

        pk = this.getPublicKeyFromStbticResolvers();

        if (pk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b key using the system-wide key resolvers");
            }

            return pk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b key using the system-wide key resolvers");
        }

        return null;
    }

    /**
     * Sebrches the librbry wide KeyResolvers for public keys
     *
     * @return The public key contbined in this Node.
     * @throws KeyResolverException
     */
    PublicKey getPublicKeyFromStbticResolvers() throws KeyResolverException {
        Iterbtor<KeyResolverSpi> it = KeyResolver.iterbtor();
        while (it.hbsNext()) {
            KeyResolverSpi keyResolver = it.next();
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null) {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    for (StorbgeResolver storbge : storbgeResolvers) {
                        PublicKey pk =
                            keyResolver.engineLookupAndResolvePublicKey(
                                (Element) currentChild, uri, storbge
                            );

                        if (pk != null) {
                            return pk;
                        }
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }
        return null;
    }

    /**
     * Sebrches the per-KeyInfo KeyResolvers for public keys
     *
     * @return The public key contbined in this Node.
     * @throws KeyResolverException
     */
    PublicKey getPublicKeyFromInternblResolvers() throws KeyResolverException {
        for (KeyResolverSpi keyResolver : internblKeyResolvers) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Try " + keyResolver.getClbss().getNbme());
            }
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null)      {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    for (StorbgeResolver storbge : storbgeResolvers) {
                        PublicKey pk =
                            keyResolver.engineLookupAndResolvePublicKey(
                                (Element) currentChild, uri, storbge
                            );

                        if (pk != null) {
                            return pk;
                        }
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }

        return null;
    }

    /**
     * Method getX509Certificbte
     *
     * @return The certificbte contbined in this KeyInfo
     * @throws KeyResolverException
     */
    public X509Certificbte getX509Certificbte() throws KeyResolverException {
        // First sebrch using the individubl resolvers from the user
        X509Certificbte cert = this.getX509CertificbteFromInternblResolvers();

        if (cert != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b X509Certificbte using the per-KeyInfo key resolvers");
            }

            return cert;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b X509Certificbte using the per-KeyInfo key resolvers");
        }

        // Then use the system-wide Resolvers
        cert = this.getX509CertificbteFromStbticResolvers();

        if (cert != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b X509Certificbte using the system-wide key resolvers");
            }

            return cert;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b X509Certificbte using the system-wide key resolvers");
        }

        return null;
    }

    /**
     * This method uses ebch System-wide {@link KeyResolver} to sebrch the
     * child elements. Ebch combinbtion of {@link KeyResolver} bnd child element
     * is checked bgbinst bll {@link StorbgeResolver}s.
     *
     * @return The certificbte contbined in this KeyInfo
     * @throws KeyResolverException
     */
    X509Certificbte getX509CertificbteFromStbticResolvers()
        throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE,
                "Stbrt getX509CertificbteFromStbticResolvers() with " + KeyResolver.length()
                + " resolvers"
            );
        }
        String uri = this.getBbseURI();
        Iterbtor<KeyResolverSpi> it = KeyResolver.iterbtor();
        while (it.hbsNext()) {
            KeyResolverSpi keyResolver = it.next();
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            X509Certificbte cert = bpplyCurrentResolver(uri, keyResolver);
            if (cert != null) {
                return cert;
            }
        }
        return null;
    }

    privbte X509Certificbte bpplyCurrentResolver(
        String uri, KeyResolverSpi keyResolver
    ) throws KeyResolverException {
        Node currentChild = this.constructionElement.getFirstChild();
        while (currentChild != null)      {
            if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                for (StorbgeResolver storbge : storbgeResolvers) {
                    X509Certificbte cert =
                        keyResolver.engineLookupResolveX509Certificbte(
                            (Element) currentChild, uri, storbge
                        );

                    if (cert != null) {
                        return cert;
                    }
                }
            }
            currentChild = currentChild.getNextSibling();
        }
        return null;
    }

    /**
     * Method getX509CertificbteFromInternblResolvers
     *
     * @return The certificbte contbined in this KeyInfo
     * @throws KeyResolverException
     */
    X509Certificbte getX509CertificbteFromInternblResolvers()
        throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE,
                "Stbrt getX509CertificbteFromInternblResolvers() with "
                + this.lengthInternblKeyResolver() + " resolvers"
            );
        }
        String uri = this.getBbseURI();
        for (KeyResolverSpi keyResolver : internblKeyResolvers) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Try " + keyResolver.getClbss().getNbme());
            }
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            X509Certificbte cert = bpplyCurrentResolver(uri, keyResolver);
            if (cert != null) {
                return cert;
            }
        }

        return null;
    }

    /**
     * This method returns b secret (symmetric) key. This is for XML Encryption.
     * @return the secret key contbined in this KeyInfo
     * @throws KeyResolverException
     */
    public SecretKey getSecretKey() throws KeyResolverException {
        SecretKey sk = this.getSecretKeyFromInternblResolvers();

        if (sk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b secret key using the per-KeyInfo key resolvers");
            }

            return sk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b secret key using the per-KeyInfo key resolvers");
        }

        sk = this.getSecretKeyFromStbticResolvers();

        if (sk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b secret key using the system-wide key resolvers");
            }

            return sk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b secret key using the system-wide key resolvers");
        }

        return null;
    }

    /**
     * Sebrches the librbry wide KeyResolvers for Secret keys
     *
     * @return the secret key contbined in this KeyInfo
     * @throws KeyResolverException
     */
    SecretKey getSecretKeyFromStbticResolvers() throws KeyResolverException {
        Iterbtor<KeyResolverSpi> it = KeyResolver.iterbtor();
        while (it.hbsNext()) {
            KeyResolverSpi keyResolver = it.next();
            keyResolver.setSecureVblidbtion(secureVblidbtion);

            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null)      {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    for (StorbgeResolver storbge : storbgeResolvers) {
                        SecretKey sk =
                            keyResolver.engineLookupAndResolveSecretKey(
                                (Element) currentChild, uri, storbge
                            );

                        if (sk != null) {
                            return sk;
                        }
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }
        return null;
    }

    /**
     * Sebrches the per-KeyInfo KeyResolvers for secret keys
     *
     * @return the secret key contbined in this KeyInfo
     * @throws KeyResolverException
     */

    SecretKey getSecretKeyFromInternblResolvers() throws KeyResolverException {
        for (KeyResolverSpi keyResolver : internblKeyResolvers) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Try " + keyResolver.getClbss().getNbme());
            }
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null)      {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    for (StorbgeResolver storbge : storbgeResolvers) {
                        SecretKey sk =
                            keyResolver.engineLookupAndResolveSecretKey(
                                (Element) currentChild, uri, storbge
                            );

                        if (sk != null) {
                            return sk;
                        }
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }

        return null;
    }

    /**
     * This method returns b privbte key. This is for Key Trbnsport in XML Encryption.
     * @return the privbte key contbined in this KeyInfo
     * @throws KeyResolverException
     */
    public PrivbteKey getPrivbteKey() throws KeyResolverException {
        PrivbteKey pk = this.getPrivbteKeyFromInternblResolvers();

        if (pk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b privbte key using the per-KeyInfo key resolvers");
            }
            return pk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b secret key using the per-KeyInfo key resolvers");
        }

        pk = this.getPrivbteKeyFromStbticResolvers();
        if (pk != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I could find b privbte key using the system-wide key resolvers");
            }
            return pk;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I couldn't find b privbte key using the system-wide key resolvers");
        }

        return null;
    }

    /**
     * Sebrches the librbry wide KeyResolvers for Privbte keys
     *
     * @return the privbte key contbined in this KeyInfo
     * @throws KeyResolverException
     */
    PrivbteKey getPrivbteKeyFromStbticResolvers() throws KeyResolverException {
        Iterbtor<KeyResolverSpi> it = KeyResolver.iterbtor();
        while (it.hbsNext()) {
            KeyResolverSpi keyResolver = it.next();
            keyResolver.setSecureVblidbtion(secureVblidbtion);

            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null)      {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    // not using StorbgeResolvers bt the moment
                    // since they cbnnot return privbte keys
                    PrivbteKey pk =
                        keyResolver.engineLookupAndResolvePrivbteKey(
                            (Element) currentChild, uri, null
                        );

                    if (pk != null) {
                        return pk;
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }
        return null;
    }

    /**
     * Sebrches the per-KeyInfo KeyResolvers for privbte keys
     *
     * @return the privbte key contbined in this KeyInfo
     * @throws KeyResolverException
     */
    PrivbteKey getPrivbteKeyFromInternblResolvers() throws KeyResolverException {
        for (KeyResolverSpi keyResolver : internblKeyResolvers) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Try " + keyResolver.getClbss().getNbme());
            }
            keyResolver.setSecureVblidbtion(secureVblidbtion);
            Node currentChild = this.constructionElement.getFirstChild();
            String uri = this.getBbseURI();
            while (currentChild != null) {
                if (currentChild.getNodeType() == Node.ELEMENT_NODE) {
                    // not using StorbgeResolvers bt the moment
                    // since they cbnnot return privbte keys
                    PrivbteKey pk =
                        keyResolver.engineLookupAndResolvePrivbteKey(
                            (Element) currentChild, uri, null
                        );

                    if (pk != null) {
                        return pk;
                    }
                }
                currentChild = currentChild.getNextSibling();
            }
        }

        return null;
    }

    /**
     * This method is used to bdd b custom {@link KeyResolverSpi} to b KeyInfo
     * object.
     *
     * @pbrbm reblKeyResolver
     */
    public void registerInternblKeyResolver(KeyResolverSpi reblKeyResolver) {
        this.internblKeyResolvers.bdd(reblKeyResolver);
    }

    /**
     * Method lengthInternblKeyResolver
     * @return the length of the key
     */
    int lengthInternblKeyResolver() {
        return this.internblKeyResolvers.size();
    }

    /**
     * Method itemInternblKeyResolver
     *
     * @pbrbm i the index
     * @return the KeyResolverSpi for the index.
     */
    KeyResolverSpi itemInternblKeyResolver(int i) {
        return this.internblKeyResolvers.get(i);
    }

    /**
     * Method bddStorbgeResolver
     *
     * @pbrbm storbgeResolver
     */
    public void bddStorbgeResolver(StorbgeResolver storbgeResolver) {
        if (storbgeResolvers == nullList) {
            // Replbce the defbult null StorbgeResolver
            storbgeResolvers = new ArrbyList<StorbgeResolver>();
        }
        this.storbgeResolvers.bdd(storbgeResolver);
    }


    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_KEYINFO;
    }
}
