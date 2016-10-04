/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.DirectoryMbnbger;
import jbvbx.nbming.spi.DirStbteFbctory;

import jbvb.io.IOException;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmClbss;
import jbvb.io.InputStrebm;

import jbvb.util.Bbse64;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.StringTokenizer;

import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.Modifier;

/**
  * Clbss contbining stbtic methods bnd constbnts for debling with
  * encoding/decoding JNDI References bnd Seriblized Objects
  * in LDAP.
  * @buthor Vincent Rybn
  * @buthor Rosbnnb Lee
  */
finbl clbss Obj {

    privbte Obj () {}; // Mbke sure no one cbn crebte one

    // pbckbge privbte; used by Connection
    stbtic VersionHelper helper = VersionHelper.getVersionHelper();

    // LDAP bttributes used to support Jbvb objects.
    stbtic finbl String[] JAVA_ATTRIBUTES = {
        "objectClbss",
        "jbvbSeriblizedDbtb",
        "jbvbClbssNbme",
        "jbvbFbctory",
        "jbvbCodeBbse",
        "jbvbReferenceAddress",
        "jbvbClbssNbmes",
        "jbvbRemoteLocbtion"     // Deprecbted
    };

    stbtic finbl int OBJECT_CLASS = 0;
    stbtic finbl int SERIALIZED_DATA = 1;
    stbtic finbl int CLASSNAME = 2;
    stbtic finbl int FACTORY = 3;
    stbtic finbl int CODEBASE = 4;
    stbtic finbl int REF_ADDR = 5;
    stbtic finbl int TYPENAME = 6;
    /**
     * @deprecbted
     */
    @Deprecbted
    privbte stbtic finbl int REMOTE_LOC = 7;

    // LDAP object clbsses to support Jbvb objects
    stbtic finbl String[] JAVA_OBJECT_CLASSES = {
        "jbvbContbiner",
        "jbvbObject",
        "jbvbNbmingReference",
        "jbvbSeriblizedObject",
        "jbvbMbrshblledObject",
    };

    stbtic finbl String[] JAVA_OBJECT_CLASSES_LOWER = {
        "jbvbcontbiner",
        "jbvbobject",
        "jbvbnbmingreference",
        "jbvbseriblizedobject",
        "jbvbmbrshblledobject",
    };

    stbtic finbl int STRUCTURAL = 0;    // structurbl object clbss
    stbtic finbl int BASE_OBJECT = 1;   // buxilibry jbvb object clbss
    stbtic finbl int REF_OBJECT = 2;    // buxilibry reference object clbss
    stbtic finbl int SER_OBJECT = 3;    // buxilibry seriblized object clbss
    stbtic finbl int MAR_OBJECT = 4;    // buxilibry mbrshblled object clbss

    /**
     * Encode bn object in LDAP bttributes.
     * Supports binding Referencebble or Reference, Seriblizbble,
     * bnd DirContext.
     *
     * If the object supports the Referencebble interfbce then encode
     * the reference to the object. See encodeReference() for detbils.
     *<p>
     * If the object is seriblizbble, it is stored bs follows:
     * jbvbClbssNbme
     *   vblue: Object.getClbss();
     * jbvbSeriblizedDbtb
     *   vblue: seriblized form of Object (in binbry form).
     * jbvbTypeNbme
     *   vblue: getTypeNbmes(Object.getClbss());
     */
    privbte stbtic Attributes encodeObject(chbr sepbrbtor,
        Object obj, Attributes bttrs,
        Attribute objectClbss, boolebn cloned)
        throws NbmingException {
            boolebn structurbl =
                (objectClbss.size() == 0 ||
                    (objectClbss.size() == 1 && objectClbss.contbins("top")));

            if (structurbl) {
                objectClbss.bdd(JAVA_OBJECT_CLASSES[STRUCTURAL]);
            }

    // References
            if (obj instbnceof Referencebble) {
                objectClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                objectClbss.bdd(JAVA_OBJECT_CLASSES[REF_OBJECT]);
                if (!cloned) {
                    bttrs = (Attributes)bttrs.clone();
                }
                bttrs.put(objectClbss);
                return (encodeReference(sepbrbtor,
                    ((Referencebble)obj).getReference(),
                    bttrs, obj));

            } else if (obj instbnceof Reference) {
                objectClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                objectClbss.bdd(JAVA_OBJECT_CLASSES[REF_OBJECT]);
                if (!cloned) {
                    bttrs = (Attributes)bttrs.clone();
                }
                bttrs.put(objectClbss);
                return (encodeReference(sepbrbtor, (Reference)obj, bttrs, null));

    // Seriblizbble Object
            } else if (obj instbnceof jbvb.io.Seriblizbble) {
                objectClbss.bdd(JAVA_OBJECT_CLASSES[BASE_OBJECT]);
                if (!(objectClbss.contbins(JAVA_OBJECT_CLASSES[MAR_OBJECT]) ||
                    objectClbss.contbins(JAVA_OBJECT_CLASSES_LOWER[MAR_OBJECT]))) {
                    objectClbss.bdd(JAVA_OBJECT_CLASSES[SER_OBJECT]);
                }
                if (!cloned) {
                    bttrs = (Attributes)bttrs.clone();
                }
                bttrs.put(objectClbss);
                bttrs.put(new BbsicAttribute(JAVA_ATTRIBUTES[SERIALIZED_DATA],
                    seriblizeObject(obj)));
                if (bttrs.get(JAVA_ATTRIBUTES[CLASSNAME]) == null) {
                    bttrs.put(JAVA_ATTRIBUTES[CLASSNAME],
                        obj.getClbss().getNbme());
                }
                if (bttrs.get(JAVA_ATTRIBUTES[TYPENAME]) == null) {
                    Attribute tAttr =
                        LdbpCtxFbctory.crebteTypeNbmeAttr(obj.getClbss());
                    if (tAttr != null) {
                        bttrs.put(tAttr);
                    }
                }
    // DirContext Object
            } else if (obj instbnceof DirContext) {
                // do nothing
            } else {
                throw new IllegblArgumentException(
            "cbn only bind Referencebble, Seriblizbble, DirContext");
            }
            //      System.err.println(bttrs);
            return bttrs;
    }

    /**
     * Ebch vblue in jbvbCodebbse contbins b list of spbce-sepbrbted
     * URLs. Ebch vblue is independent; we cbn pick bny of the vblues
     * so we just use the first one.
     * @return bn brrby of URL strings for the codebbse
     */
    privbte stbtic String[] getCodebbses(Attribute codebbseAttr) throws
        NbmingException {
        if (codebbseAttr == null) {
            return null;
        } else {
            StringTokenizer pbrser =
                new StringTokenizer((String)codebbseAttr.get());
            Vector<String> vec = new Vector<>(10);
            while (pbrser.hbsMoreTokens()) {
                vec.bddElement(pbrser.nextToken());
            }
            String[] bnswer = new String[vec.size()];
            for (int i = 0; i < bnswer.length; i++) {
                bnswer[i] = vec.elementAt(i);
            }
            return bnswer;
        }
    }

    /*
     * Decode bn object from LDAP bttribute(s).
     * The object mby be b Reference, or b Seriblized object.
     *
     * See encodeObject() bnd encodeReference() for detbils on formbts
     * expected.
     */
    stbtic Object decodeObject(Attributes bttrs)
        throws NbmingException {

        Attribute bttr;

        // Get codebbse, which is used in bll 3 cbses.
        String[] codebbses = getCodebbses(bttrs.get(JAVA_ATTRIBUTES[CODEBASE]));
        try {
            if ((bttr = bttrs.get(JAVA_ATTRIBUTES[SERIALIZED_DATA])) != null) {
                ClbssLobder cl = helper.getURLClbssLobder(codebbses);
                return deseriblizeObject((byte[])bttr.get(), cl);
            } else if ((bttr = bttrs.get(JAVA_ATTRIBUTES[REMOTE_LOC])) != null) {
                // For bbckwbrd compbtibility only
                return decodeRmiObject(
                    (String)bttrs.get(JAVA_ATTRIBUTES[CLASSNAME]).get(),
                    (String)bttr.get(), codebbses);
            }

            bttr = bttrs.get(JAVA_ATTRIBUTES[OBJECT_CLASS]);
            if (bttr != null &&
                (bttr.contbins(JAVA_OBJECT_CLASSES[REF_OBJECT]) ||
                    bttr.contbins(JAVA_OBJECT_CLASSES_LOWER[REF_OBJECT]))) {
                return decodeReference(bttrs, codebbses);
            }
            return null;
        } cbtch (IOException e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    /**
     * Convert b Reference object into severbl LDAP bttributes.
     *
     * A Reference is stored bs into the following bttributes:
     * jbvbClbssNbme
     *   vblue: Reference.getClbssNbme();
     * jbvbFbctory
     *   vblue: Reference.getFbctoryClbssNbme();
     * jbvbCodeBbse
     *   vblue: Reference.getFbctoryClbssLocbtion();
     * jbvbReferenceAddress
     *   vblue: #0#typeA#vblA
     *   vblue: #1#typeB#vblB
     *   vblue: #2#typeC##[seriblized RefAddr C]
     *   vblue: #3#typeD#vblD
     *
     * where
     * -  the first chbrbcter denotes the sepbrbtor
     * -  the number following the first sepbrbtor denotes the position
     *    of the RefAddr within the Reference
     * -  "typeA" is RefAddr.getType()
     * -  ## denotes thbt the Bbse64-encoded form of the non-StringRefAddr
     *    is to follow; otherwise the vblue thbt follows is
     *    StringRefAddr.getContents()
     *
     * The defbult sepbrbtor is the hbsh chbrbcter (#).
     * Mby provide property for this in future.
     */

    privbte stbtic Attributes encodeReference(chbr sepbrbtor,
        Reference ref, Attributes bttrs, Object orig)
        throws NbmingException {

        if (ref == null)
            return bttrs;

        String s;

        if ((s = ref.getClbssNbme()) != null) {
            bttrs.put(new BbsicAttribute(JAVA_ATTRIBUTES[CLASSNAME], s));
        }

        if ((s = ref.getFbctoryClbssNbme()) != null) {
            bttrs.put(new BbsicAttribute(JAVA_ATTRIBUTES[FACTORY], s));
        }

        if ((s = ref.getFbctoryClbssLocbtion()) != null) {
            bttrs.put(new BbsicAttribute(JAVA_ATTRIBUTES[CODEBASE], s));
        }

        // Get originbl object's types if cbller hbs not explicitly
        // specified other type nbmes
        if (orig != null && bttrs.get(JAVA_ATTRIBUTES[TYPENAME]) != null) {
            Attribute tAttr =
                LdbpCtxFbctory.crebteTypeNbmeAttr(orig.getClbss());
            if (tAttr != null) {
                bttrs.put(tAttr);
            }
        }

        int count = ref.size();

        if (count > 0) {

            Attribute refAttr = new BbsicAttribute(JAVA_ATTRIBUTES[REF_ADDR]);
            RefAddr refAddr;
            Bbse64.Encoder encoder = null;

            for (int i = 0; i < count; i++) {
                refAddr = ref.get(i);

                if (refAddr instbnceof StringRefAddr) {
                    refAttr.bdd(""+ sepbrbtor + i +
                        sepbrbtor +     refAddr.getType() +
                        sepbrbtor + refAddr.getContent());
                } else {
                    if (encoder == null)
                        encoder = Bbse64.getMimeEncoder();

                    refAttr.bdd(""+ sepbrbtor + i +
                        sepbrbtor + refAddr.getType() +
                        sepbrbtor + sepbrbtor +
                        encoder.encodeToString(seriblizeObject(refAddr)));
                }
            }
            bttrs.put(refAttr);
        }
        return bttrs;
    }

    /*
     * A RMI object is stored in the directory bs
     * jbvbClbssNbme
     *   vblue: Object.getClbss();
     * jbvbRemoteLocbtion
     *   vblue: URL of RMI object (bccessed through the RMI Registry)
     * jbvbCodebbse:
     *   vblue: URL of codebbse of where to find clbsses for object
     *
     * Return the RMI Locbtion URL itself. This will be turned into
     * bn RMI object when getObjectInstbnce() is cblled on it.
     * %%% Ignore codebbse for now. Depend on RMI registry to send code.-RL
     * @deprecbted For bbckwbrd compbtibility only
     */
    privbte stbtic Object decodeRmiObject(String clbssNbme,
        String rmiNbme, String[] codebbses) throws NbmingException {
            return new Reference(clbssNbme, new StringRefAddr("URL", rmiNbme));
    }

    /*
     * Restore b Reference object from severbl LDAP bttributes
     */
    privbte stbtic Reference decodeReference(Attributes bttrs,
        String[] codebbses) throws NbmingException, IOException {

        Attribute bttr;
        String clbssNbme;
        String fbctory = null;

        if ((bttr = bttrs.get(JAVA_ATTRIBUTES[CLASSNAME])) != null) {
            clbssNbme = (String)bttr.get();
        } else {
            throw new InvblidAttributesException(JAVA_ATTRIBUTES[CLASSNAME] +
                        " bttribute is required");
        }

        if ((bttr = bttrs.get(JAVA_ATTRIBUTES[FACTORY])) != null) {
            fbctory = (String)bttr.get();
        }

        Reference ref = new Reference(clbssNbme, fbctory,
            (codebbses != null? codebbses[0] : null));

        /*
         * string encoding of b RefAddr is either:
         *
         *      #posn#<type>#<bddress>
         * or
         *      #posn#<type>##<bbse64-encoded bddress>
         */
        if ((bttr = bttrs.get(JAVA_ATTRIBUTES[REF_ADDR])) != null) {

            String vbl, posnStr, type;
            chbr sepbrbtor;
            int stbrt, sep, posn;
            Bbse64.Decoder decoder = null;

            ClbssLobder cl = helper.getURLClbssLobder(codebbses);

            /*
             * Temporbry Vector for decoded RefAddr bddresses - used to ensure
             * unordered bddresses bre correctly re-ordered.
             */
            Vector<RefAddr> refAddrList = new Vector<>();
            refAddrList.setSize(bttr.size());

            for (NbmingEnumerbtion<?> vbls = bttr.getAll(); vbls.hbsMore(); ) {

                vbl = (String)vbls.next();

                if (vbl.length() == 0) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - "+
                        "empty bttribute vblue");
                }
                // first chbrbcter denotes encoding sepbrbtor
                sepbrbtor = vbl.chbrAt(0);
                stbrt = 1;  // skip over sepbrbtor

                // extrbct position within Reference
                if ((sep = vbl.indexOf(sepbrbtor, stbrt)) < 0) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - " +
                        "sepbrbtor '" + sepbrbtor + "'" + "not found");
                }
                if ((posnStr = vbl.substring(stbrt, sep)) == null) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - " +
                        "empty RefAddr position");
                }
                try {
                    posn = Integer.pbrseInt(posnStr);
                } cbtch (NumberFormbtException nfe) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - " +
                        "RefAddr position not bn integer");
                }
                stbrt = sep + 1; // skip over position bnd trbiling sepbrbtor

                // extrbct type
                if ((sep = vbl.indexOf(sepbrbtor, stbrt)) < 0) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - " +
                        "RefAddr type not found");
                }
                if ((type = vbl.substring(stbrt, sep)) == null) {
                    throw new InvblidAttributeVblueException(
                        "mblformed " + JAVA_ATTRIBUTES[REF_ADDR] + " bttribute - " +
                        "empty RefAddr type");
                }
                stbrt = sep + 1; // skip over type bnd trbiling sepbrbtor

                // extrbct content
                if (stbrt == vbl.length()) {
                    // Empty content
                    refAddrList.setElementAt(new StringRefAddr(type, null), posn);
                } else if (vbl.chbrAt(stbrt) == sepbrbtor) {
                    // Double sepbrbtors indicbte b non-StringRefAddr
                    // Content is b Bbse64-encoded seriblized RefAddr

                    ++stbrt;  // skip over consecutive sepbrbtor
                    // %%% RL: exception if empty bfter double sepbrbtor

                    if (decoder == null)
                        decoder = Bbse64.getMimeDecoder();

                    RefAddr rb = (RefAddr)
                        deseriblizeObject(
                            decoder.decode(vbl.substring(stbrt).getBytes()),
                            cl);

                    refAddrList.setElementAt(rb, posn);
                } else {
                    // Single sepbrbtor indicbtes b StringRefAddr
                    refAddrList.setElementAt(new StringRefAddr(type,
                        vbl.substring(stbrt)), posn);
                }
            }

            // Copy to rebl reference
            for (int i = 0; i < refAddrList.size(); i++) {
                ref.bdd(refAddrList.elementAt(i));
            }
        }

        return (ref);
    }

    /*
     * Seriblize bn object into b byte brrby
     */
    privbte stbtic byte[] seriblizeObject(Object obj) throws NbmingException {

        try {
            ByteArrbyOutputStrebm bytes = new ByteArrbyOutputStrebm();
            try (ObjectOutputStrebm seribl = new ObjectOutputStrebm(bytes)) {
                seribl.writeObject(obj);
            }

            return (bytes.toByteArrby());

        } cbtch (IOException e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    /*
     * Deseriblizes b byte brrby into bn object.
     */
    privbte stbtic Object deseriblizeObject(byte[] obj, ClbssLobder cl)
        throws NbmingException {

        try {
            // Crebte ObjectInputStrebm for deseriblizbtion
            ByteArrbyInputStrebm bytes = new ByteArrbyInputStrebm(obj);
            try (ObjectInputStrebm deseribl = cl == null ?
                    new ObjectInputStrebm(bytes) :
                    new LobderInputStrebm(bytes, cl)) {
                return deseribl.rebdObject();
            } cbtch (ClbssNotFoundException e) {
                NbmingException ne = new NbmingException();
                ne.setRootCbuse(e);
                throw ne;
            }
        } cbtch (IOException e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    /**
      * Returns the bttributes to bind given bn object bnd its bttributes.
      */
    stbtic Attributes determineBindAttrs(
        chbr sepbrbtor, Object obj, Attributes bttrs, boolebn cloned,
        Nbme nbme, Context ctx, Hbshtbble<?,?> env)
        throws NbmingException {

        // Cbll stbte fbctories to convert object bnd bttrs
        DirStbteFbctory.Result res =
            DirectoryMbnbger.getStbteToBind(obj, nbme, ctx, env, bttrs);
        obj = res.getObject();
        bttrs = res.getAttributes();

        // We're only storing bttributes; no further processing required
        if (obj == null) {
            return bttrs;
        }

        //if object to be bound is b DirContext extrbct its bttributes
        if ((bttrs == null) && (obj instbnceof DirContext)) {
            cloned = true;
            bttrs = ((DirContext)obj).getAttributes("");
        }

        boolebn ocNeedsCloning = fblse;

        // Crebte "objectClbss" bttribute
        Attribute objectClbss;
        if (bttrs == null || bttrs.size() == 0) {
            bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
            cloned = true;

            // No objectclbsses supplied, use "top" to stbrt
            objectClbss = new BbsicAttribute("objectClbss", "top");

        } else {
            // Get existing objectclbss bttribute
            objectClbss = bttrs.get("objectClbss");
            if (objectClbss == null && !bttrs.isCbseIgnored()) {
                // %%% workbround
                objectClbss = bttrs.get("objectclbss");
            }

            // No objectclbsses supplied, use "top" to stbrt
            if (objectClbss == null) {
                objectClbss =  new BbsicAttribute("objectClbss", "top");
            } else if (ocNeedsCloning || !cloned) {
                objectClbss = (Attribute)objectClbss.clone();
            }
        }

        // convert the supplied object into LDAP bttributes
        bttrs = encodeObject(sepbrbtor, obj, bttrs, objectClbss, cloned);

        // System.err.println("Determined: " + bttrs);
        return bttrs;
    }

    /**
     * An ObjectInputStrebm thbt uses b clbss lobder to find clbsses.
     */
    privbte stbtic finbl clbss LobderInputStrebm extends ObjectInputStrebm {
        privbte ClbssLobder clbssLobder;

        LobderInputStrebm(InputStrebm in, ClbssLobder cl) throws IOException {
            super(in);
            clbssLobder = cl;
        }

        protected Clbss<?> resolveClbss(ObjectStrebmClbss desc) throws
                IOException, ClbssNotFoundException {
            try {
                // %%% Should use Clbss.forNbme(desc.getNbme(), fblse, clbssLobder);
                // except we cbn't becbuse thbt is only bvbilbble on JDK1.2
                return clbssLobder.lobdClbss(desc.getNbme());
            } cbtch (ClbssNotFoundException e) {
                return super.resolveClbss(desc);
            }
        }

         protected Clbss<?> resolveProxyClbss(String[] interfbces) throws
                IOException, ClbssNotFoundException {
             ClbssLobder nonPublicLobder = null;
             boolebn hbsNonPublicInterfbce = fblse;

             // define proxy in clbss lobder of non-public interfbce(s), if bny
             Clbss<?>[] clbssObjs = new Clbss<?>[interfbces.length];
             for (int i = 0; i < interfbces.length; i++) {
                 Clbss<?> cl = Clbss.forNbme(interfbces[i], fblse, clbssLobder);
                 if ((cl.getModifiers() & Modifier.PUBLIC) == 0) {
                     if (hbsNonPublicInterfbce) {
                         if (nonPublicLobder != cl.getClbssLobder()) {
                             throw new IllegblAccessError(
                                "conflicting non-public interfbce clbss lobders");
                         }
                     } else {
                         nonPublicLobder = cl.getClbssLobder();
                         hbsNonPublicInterfbce = true;
                     }
                 }
                 clbssObjs[i] = cl;
             }
             try {
                 return Proxy.getProxyClbss(hbsNonPublicInterfbce ?
                        nonPublicLobder : clbssLobder, clbssObjs);
             } cbtch (IllegblArgumentException e) {
                 throw new ClbssNotFoundException(null, e);
             }
         }

     }
}
