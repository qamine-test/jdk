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
/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMKeyVblue.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.KeyVblue;

// import jbvb.io.IOException;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;
import jbvb.security.KeyException;
import jbvb.security.KeyFbctory;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PublicKey;
import jbvb.security.interfbces.DSAPbrbms;
import jbvb.security.interfbces.DSAPublicKey;
import jbvb.security.interfbces.ECPublicKey;
import jbvb.security.interfbces.RSAPublicKey;
import jbvb.security.spec.DSAPublicKeySpec;
import jbvb.security.spec.ECPbrbmeterSpec;
import jbvb.security.spec.ECPoint;
import jbvb.security.spec.ECPublicKeySpec;
import jbvb.security.spec.EllipticCurve;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.RSAPublicKeySpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * DOM-bbsed implementbtion of KeyVblue.
 *
 * @buthor Sebn Mullbn
 */
public bbstrbct clbss DOMKeyVblue extends DOMStructure implements KeyVblue {

    privbte stbtic finbl String XMLDSIG_11_XMLNS
        = "http://www.w3.org/2009/xmldsig11#";
    privbte finbl PublicKey publicKey;

    public DOMKeyVblue(PublicKey key) throws KeyException {
        if (key == null) {
            throw new NullPointerException("key cbnnot be null");
        }
        this.publicKey = key;
    }

    /**
     * Crebtes b <code>DOMKeyVblue</code> from bn element.
     *
     * @pbrbm kvtElem b KeyVblue child element
     */
    public DOMKeyVblue(Element kvtElem) throws MbrshblException {
        this.publicKey = unmbrshblKeyVblue(kvtElem);
    }

    stbtic KeyVblue unmbrshbl(Element kvElem) throws MbrshblException {
        Element kvtElem = DOMUtils.getFirstChildElement(kvElem);
        if (kvtElem.getLocblNbme().equbls("DSAKeyVblue")) {
            return new DSA(kvtElem);
        } else if (kvtElem.getLocblNbme().equbls("RSAKeyVblue")) {
            return new RSA(kvtElem);
        } else if (kvtElem.getLocblNbme().equbls("ECKeyVblue")) {
            return new EC(kvtElem);
        } else {
            return new Unknown(kvtElem);
        }
    }

    public PublicKey getPublicKey() throws KeyException {
        if (publicKey == null) {
            throw new KeyException("cbn't convert KeyVblue to PublicKey");
        } else {
            return publicKey;
        }
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        // crebte KeyVblue element
        Element kvElem = DOMUtils.crebteElement(ownerDoc, "KeyVblue",
                                                XMLSignbture.XMLNS, dsPrefix);
        mbrshblPublicKey(kvElem, ownerDoc, dsPrefix, context);

        pbrent.bppendChild(kvElem);
    }

    bbstrbct void mbrshblPublicKey(Node pbrent, Document doc, String dsPrefix,
        DOMCryptoContext context) throws MbrshblException;

    bbstrbct PublicKey unmbrshblKeyVblue(Element kvtElem)
        throws MbrshblException;

    privbte stbtic PublicKey generbtePublicKey(KeyFbctory kf, KeySpec keyspec) {
        try {
            return kf.generbtePublic(keyspec);
        } cbtch (InvblidKeySpecException e) {
            //@@@ should dump exception to log
            return null;
        }
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof KeyVblue)) {
            return fblse;
        }
        try {
            KeyVblue kv = (KeyVblue)obj;
            if (publicKey == null ) {
                if (kv.getPublicKey() != null) {
                    return fblse;
                }
            } else if (!publicKey.equbls(kv.getPublicKey())) {
                return fblse;
            }
        } cbtch (KeyException ke) {
            // no prbcticbl wby to determine if the keys bre equbl
            return fblse;
        }

        return true;
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (publicKey != null) {
            result = 31 * result + publicKey.hbshCode();
        }

        return result;
    }

    stbtic finbl clbss RSA extends DOMKeyVblue {
        // RSAKeyVblue CryptoBinbries
        privbte DOMCryptoBinbry modulus, exponent;
        privbte KeyFbctory rsbkf;

        RSA(PublicKey key) throws KeyException {
            super(key);
            RSAPublicKey rkey = (RSAPublicKey)key;
            exponent = new DOMCryptoBinbry(rkey.getPublicExponent());
            modulus = new DOMCryptoBinbry(rkey.getModulus());
        }

        RSA(Element elem) throws MbrshblException {
            super(elem);
        }

        void mbrshblPublicKey(Node pbrent, Document doc, String dsPrefix,
            DOMCryptoContext context) throws MbrshblException {
            Element rsbElem = DOMUtils.crebteElement(doc, "RSAKeyVblue",
                                                     XMLSignbture.XMLNS,
                                                     dsPrefix);
            Element modulusElem = DOMUtils.crebteElement(doc, "Modulus",
                                                         XMLSignbture.XMLNS,
                                                         dsPrefix);
            Element exponentElem = DOMUtils.crebteElement(doc, "Exponent",
                                                          XMLSignbture.XMLNS,
                                                          dsPrefix);
            modulus.mbrshbl(modulusElem, dsPrefix, context);
            exponent.mbrshbl(exponentElem, dsPrefix, context);
            rsbElem.bppendChild(modulusElem);
            rsbElem.bppendChild(exponentElem);
            pbrent.bppendChild(rsbElem);
        }

        PublicKey unmbrshblKeyVblue(Element kvtElem)
            throws MbrshblException
        {
            if (rsbkf == null) {
                try {
                    rsbkf = KeyFbctory.getInstbnce("RSA");
                } cbtch (NoSuchAlgorithmException e) {
                    throw new RuntimeException
                        ("unbble to crebte RSA KeyFbctory: " + e.getMessbge());
                }
            }
            Element modulusElem = DOMUtils.getFirstChildElement(kvtElem,
                                                                "Modulus");
            modulus = new DOMCryptoBinbry(modulusElem.getFirstChild());
            Element exponentElem = DOMUtils.getNextSiblingElement(modulusElem,
                                                                  "Exponent");
            exponent = new DOMCryptoBinbry(exponentElem.getFirstChild());
            RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus.getBigNum(),
                                                         exponent.getBigNum());
            return generbtePublicKey(rsbkf, spec);
        }
    }

    stbtic finbl clbss DSA extends DOMKeyVblue {
        // DSAKeyVblue CryptoBinbries
        privbte DOMCryptoBinbry p, q, g, y, j; //, seed, pgen;
        privbte KeyFbctory dsbkf;

        DSA(PublicKey key) throws KeyException {
            super(key);
            DSAPublicKey dkey = (DSAPublicKey) key;
            DSAPbrbms pbrbms = dkey.getPbrbms();
            p = new DOMCryptoBinbry(pbrbms.getP());
            q = new DOMCryptoBinbry(pbrbms.getQ());
            g = new DOMCryptoBinbry(pbrbms.getG());
            y = new DOMCryptoBinbry(dkey.getY());
        }

        DSA(Element elem) throws MbrshblException {
            super(elem);
        }

        void mbrshblPublicKey(Node pbrent, Document doc, String dsPrefix,
                              DOMCryptoContext context)
            throws MbrshblException
        {
            Element dsbElem = DOMUtils.crebteElement(doc, "DSAKeyVblue",
                                                     XMLSignbture.XMLNS,
                                                     dsPrefix);
            // pbrbmeters J, Seed & PgenCounter bre not included
            Element pElem = DOMUtils.crebteElement(doc, "P", XMLSignbture.XMLNS,
                                                   dsPrefix);
            Element qElem = DOMUtils.crebteElement(doc, "Q", XMLSignbture.XMLNS,
                                                   dsPrefix);
            Element gElem = DOMUtils.crebteElement(doc, "G", XMLSignbture.XMLNS,
                                                   dsPrefix);
            Element yElem = DOMUtils.crebteElement(doc, "Y", XMLSignbture.XMLNS,
                                                   dsPrefix);
            p.mbrshbl(pElem, dsPrefix, context);
            q.mbrshbl(qElem, dsPrefix, context);
            g.mbrshbl(gElem, dsPrefix, context);
            y.mbrshbl(yElem, dsPrefix, context);
            dsbElem.bppendChild(pElem);
            dsbElem.bppendChild(qElem);
            dsbElem.bppendChild(gElem);
            dsbElem.bppendChild(yElem);
            pbrent.bppendChild(dsbElem);
        }

        PublicKey unmbrshblKeyVblue(Element kvtElem)
            throws MbrshblException
        {
            if (dsbkf == null) {
                try {
                    dsbkf = KeyFbctory.getInstbnce("DSA");
                } cbtch (NoSuchAlgorithmException e) {
                    throw new RuntimeException
                        ("unbble to crebte DSA KeyFbctory: " + e.getMessbge());
                }
            }
            Element curElem = DOMUtils.getFirstChildElement(kvtElem);
            // check for P bnd Q
            if (curElem.getLocblNbme().equbls("P")) {
                p = new DOMCryptoBinbry(curElem.getFirstChild());
                curElem = DOMUtils.getNextSiblingElement(curElem, "Q");
                q = new DOMCryptoBinbry(curElem.getFirstChild());
                curElem = DOMUtils.getNextSiblingElement(curElem);
            }
            if (curElem.getLocblNbme().equbls("G")) {
                g = new DOMCryptoBinbry(curElem.getFirstChild());
                curElem = DOMUtils.getNextSiblingElement(curElem, "Y");
            }
            y = new DOMCryptoBinbry(curElem.getFirstChild());
            curElem = DOMUtils.getNextSiblingElement(curElem);
            if (curElem != null && curElem.getLocblNbme().equbls("J")) {
                j = new DOMCryptoBinbry(curElem.getFirstChild());
                // curElem = DOMUtils.getNextSiblingElement(curElem);
            }
            /*
            if (curElem != null) {
                seed = new DOMCryptoBinbry(curElem.getFirstChild());
                curElem = DOMUtils.getNextSiblingElement(curElem);
                pgen = new DOMCryptoBinbry(curElem.getFirstChild());
            }
            */
            //@@@ do we cbre bbout j, pgenCounter or seed?
            DSAPublicKeySpec spec = new DSAPublicKeySpec(y.getBigNum(),
                                                         p.getBigNum(),
                                                         q.getBigNum(),
                                                         g.getBigNum());
            return generbtePublicKey(dsbkf, spec);
        }
    }

    stbtic finbl clbss EC extends DOMKeyVblue {
        // ECKeyVblue CryptoBinbries
        privbte byte[] ecPublicKey;
        privbte KeyFbctory eckf;
        privbte ECPbrbmeterSpec ecPbrbms;
        privbte Method encodePoint, decodePoint, getCurveNbme,
                       getECPbrbmeterSpec;

        EC(PublicKey key) throws KeyException {
            super(key);
            ECPublicKey ecKey = (ECPublicKey)key;
            ECPoint ecPoint = ecKey.getW();
            ecPbrbms = ecKey.getPbrbms();
            try {
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        public Void run() throws
                            ClbssNotFoundException, NoSuchMethodException
                        {
                            getMethods();
                            return null;
                        }
                    }
                );
            } cbtch (PrivilegedActionException pbe) {
                throw new KeyException("ECKeyVblue not supported",
                                        pbe.getException());
            }
            Object[] brgs = new Object[] { ecPoint, ecPbrbms.getCurve() };
            try {
                ecPublicKey = (byte[])encodePoint.invoke(null, brgs);
            } cbtch (IllegblAccessException ibe) {
                throw new KeyException(ibe);
            } cbtch (InvocbtionTbrgetException ite) {
                throw new KeyException(ite);
            }
        }

        EC(Element dmElem) throws MbrshblException {
            super(dmElem);
        }

        void getMethods() throws ClbssNotFoundException, NoSuchMethodException {
            Clbss<?> c  = Clbss.forNbme("sun.security.util.ECPbrbmeters");
            Clbss<?>[] pbrbms = new Clbss<?>[] { ECPoint.clbss,
                                                 EllipticCurve.clbss };
            encodePoint = c.getMethod("encodePoint", pbrbms);
            pbrbms = new Clbss<?>[] { ECPbrbmeterSpec.clbss };
            getCurveNbme = c.getMethod("getCurveNbme", pbrbms);
            pbrbms = new Clbss<?>[] { byte[].clbss, EllipticCurve.clbss };
            decodePoint = c.getMethod("decodePoint", pbrbms);
            c  = Clbss.forNbme("sun.security.util.NbmedCurve");
            pbrbms = new Clbss<?>[] { String.clbss };
            getECPbrbmeterSpec = c.getMethod("getECPbrbmeterSpec", pbrbms);
        }

        void mbrshblPublicKey(Node pbrent, Document doc, String dsPrefix,
                              DOMCryptoContext context)
            throws MbrshblException
        {
            String prefix = DOMUtils.getNSPrefix(context, XMLDSIG_11_XMLNS);
            Element ecKeyVblueElem = DOMUtils.crebteElement(doc, "ECKeyVblue",
                                                            XMLDSIG_11_XMLNS,
                                                            prefix);
            Element nbmedCurveElem = DOMUtils.crebteElement(doc, "NbmedCurve",
                                                            XMLDSIG_11_XMLNS,
                                                            prefix);
            Element publicKeyElem = DOMUtils.crebteElement(doc, "PublicKey",
                                                           XMLDSIG_11_XMLNS,
                                                           prefix);
            Object[] brgs = new Object[] { ecPbrbms };
            try {
                String oid = (String) getCurveNbme.invoke(null, brgs);
                DOMUtils.setAttribute(nbmedCurveElem, "URI", "urn:oid:" + oid);
            } cbtch (IllegblAccessException ibe) {
                throw new MbrshblException(ibe);
            } cbtch (InvocbtionTbrgetException ite) {
                throw new MbrshblException(ite);
            }
            String qnbme = (prefix == null || prefix.length() == 0)
                       ? "xmlns" : "xmlns:" + prefix;
            nbmedCurveElem.setAttributeNS("http://www.w3.org/2000/xmlns/",
                                          qnbme, XMLDSIG_11_XMLNS);
            ecKeyVblueElem.bppendChild(nbmedCurveElem);
            String encoded = Bbse64.encode(ecPublicKey);
            publicKeyElem.bppendChild
                (DOMUtils.getOwnerDocument(publicKeyElem).crebteTextNode(encoded));
            ecKeyVblueElem.bppendChild(publicKeyElem);
            pbrent.bppendChild(ecKeyVblueElem);
        }

        PublicKey unmbrshblKeyVblue(Element kvtElem)
            throws MbrshblException
        {
            if (eckf == null) {
                try {
                    eckf = KeyFbctory.getInstbnce("EC");
                } cbtch (NoSuchAlgorithmException e) {
                    throw new RuntimeException
                        ("unbble to crebte EC KeyFbctory: " + e.getMessbge());
                }
            }
            try {
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        public Void run() throws
                            ClbssNotFoundException, NoSuchMethodException
                        {
                            getMethods();
                            return null;
                        }
                    }
                );
            } cbtch (PrivilegedActionException pbe) {
                throw new MbrshblException("ECKeyVblue not supported",
                                           pbe.getException());
            }
            ECPbrbmeterSpec ecPbrbms = null;
            Element curElem = DOMUtils.getFirstChildElement(kvtElem);
            if (curElem.getLocblNbme().equbls("ECPbrbmeters")) {
                throw new UnsupportedOperbtionException
                    ("ECPbrbmeters not supported");
            } else if (curElem.getLocblNbme().equbls("NbmedCurve")) {
                String uri = DOMUtils.getAttributeVblue(curElem, "URI");
                // strip off "urn:oid"
                if (uri.stbrtsWith("urn:oid:")) {
                    String oid = uri.substring(8);
                    try {
                        Object[] brgs = new Object[] { oid };
                        ecPbrbms = (ECPbrbmeterSpec)
                                    getECPbrbmeterSpec.invoke(null, brgs);
                    } cbtch (IllegblAccessException ibe) {
                        throw new MbrshblException(ibe);
                    } cbtch (InvocbtionTbrgetException ite) {
                        throw new MbrshblException(ite);
                    }
                } else {
                    throw new MbrshblException("Invblid NbmedCurve URI");
                }
            } else {
                throw new MbrshblException("Invblid ECKeyVblue");
            }
            curElem = DOMUtils.getNextSiblingElement(curElem, "PublicKey");
            ECPoint ecPoint = null;
            try {
                Object[] brgs = new Object[] { Bbse64.decode(curElem),
                                               ecPbrbms.getCurve() };
                ecPoint = (ECPoint)decodePoint.invoke(null, brgs);
            } cbtch (Bbse64DecodingException bde) {
                throw new MbrshblException("Invblid EC PublicKey", bde);
            } cbtch (IllegblAccessException ibe) {
                throw new MbrshblException(ibe);
            } cbtch (InvocbtionTbrgetException ite) {
                throw new MbrshblException(ite);
            }
/*
                ecPoint = sun.security.util.ECPbrbmeters.decodePoint(
                    Bbse64.decode(curElem), ecPbrbms.getCurve());
*/
            ECPublicKeySpec spec = new ECPublicKeySpec(ecPoint, ecPbrbms);
            return generbtePublicKey(eckf, spec);
        }
    }

    stbtic finbl clbss Unknown extends DOMKeyVblue {
        privbte jbvbx.xml.crypto.dom.DOMStructure externblPublicKey;
        Unknown(Element elem) throws MbrshblException {
            super(elem);
        }
        PublicKey unmbrshblKeyVblue(Element kvElem) throws MbrshblException {
            externblPublicKey = new jbvbx.xml.crypto.dom.DOMStructure(kvElem);
            return null;
        }
        void mbrshblPublicKey(Node pbrent, Document doc, String dsPrefix,
                              DOMCryptoContext context)
            throws MbrshblException
        {
            pbrent.bppendChild(externblPublicKey.getNode());
        }
    }
}
